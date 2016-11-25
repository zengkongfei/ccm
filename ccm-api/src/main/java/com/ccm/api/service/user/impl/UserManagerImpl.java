package com.ccm.api.service.user.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ccm.api.Constants;
import com.ccm.api.SecurityHolder;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.common.MenuDao;
import com.ccm.api.dao.order.OrderDownloadLogDao;
import com.ccm.api.dao.user.EmployeeDao;
import com.ccm.api.dao.user.RoleDao;
import com.ccm.api.dao.user.UserDao;
import com.ccm.api.dao.user.mongodb.AuthRoleDaoMongo;
import com.ccm.api.dao.user.mongodb.AuthUserDaoMongo;
import com.ccm.api.dao.user.mongodb.AuthorityDaoMongo;
import com.ccm.api.model.ads.OnlineUser;
import com.ccm.api.model.base.vo.MenuTreeVO;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.RoleUsage;
import com.ccm.api.model.constant.TaobaoApi;
import com.ccm.api.model.constant.UserInitStatus;
import com.ccm.api.model.constant.YesNo;
import com.ccm.api.model.order.OrderDownloadLog;
import com.ccm.api.model.user.Authority;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.Employee;
import com.ccm.api.model.user.EmployeeI18n;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.UserPassword;
import com.ccm.api.model.user.UserRole;
import com.ccm.api.model.user.UserRoleChannel;
import com.ccm.api.model.user.criteria.B2BUserCriteria;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.model.user.vo.B2BUserVO;
import com.ccm.api.model.user.vo.UserRoleVO;
import com.ccm.api.service.ads.OnlineUserManager;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.taobaoAPI.TaobaoShopManager;
import com.ccm.api.service.user.UserManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.SHA256Util;
import com.taobao.api.ApiException;
import com.taobao.api.domain.Shop;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.UserSellerGetResponse;

/**
 * 用户管理类
 */
@Service("userManager")
public class UserManagerImpl extends GenericManagerImpl<B2BUser, String> implements UserManager, UserDetailsService {
	

	private PasswordEncoder passwordEncoder;

	private UserDao userDao;

	private RoleDao roleDao;
	
	@Resource
	private MenuDao menuDao;
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private TaobaoShopManager taobaoShopManager;

	@Resource
	private OrderDownloadLogDao orderDownloadLogDao;

	@Autowired(required = false)
	@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;

	@Resource
	private OnlineUserManager onlineUserManager;

	/**
	 * 缓存用户ID和sessionKey 对应表
	 */
	private static Map<String, String> userSessionMap;
	/**
	 * 缓存chainCode和sessionKey 对应表
	 */
	private static Map<String, String> chainUserSessionMap = new HashMap<String, String>();;

	
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.dao = userDao;
		this.userDao = userDao;
	}

	/************* 以下是整理过的 *****************/

	/**
	 * 从淘宝那里取出用户数据保存到数据库中
	 */
	public String saveUserFromTaobao(String sessionId) {

		B2BUser tbuser = getUserFromTaobao(sessionId);
		String chainCode = null;
		if (tbuser != null) {
			chainCode = getOnlineChainCode(tbuser);

			// 查是否已经建档
			String session = getUserSessionKey(tbuser.getUserId());

			// 没有则新建
			if (!StringUtils.hasText(session)) {

				Date createDate = DateUtil.currentDateTime();
				tbuser.setCreatedTime(createDate);

				String salt = DateUtil.getDateTime("yyyyMMddHHmmss", tbuser.getCreatedTime());
				tbuser.setPassword(SHA256Util.SHA256Encrypt(sessionId, salt));

				String companyId = CompanyType.B2BUSER;
				tbuser.setCompanyId(companyId);

				tbuser.setAccountExpired(false);
				tbuser.setAccountLocked(false);
				tbuser.setEnabled(true);
				tbuser.setCredentialsExpired(false);

				tbuser.setLastLoginTime(tbuser.getCreatedTime());
				tbuser.setChainCode(chainCode);

				// 同时增加雇员信息
				Employee employee = tbuser.getEmployee();
				if (employee == null) {
					employee = new Employee();
				}
				employee.setCreatedTime(createDate);

				employee.setContactFlag(YesNo.NO);
				employee.setDelFlag(false);
				employee.setCompanyId(companyId);
				employee = employeeDao.save(employee);

				tbuser.setEmployeeId(employee.getEmployeeId());

				// 设置为用户角色，因为一些公共的都分配给了用户角色，比如密码修改等
				tbuser.addRole(roleDao.getRoleByName(Constants.COADMIN_ROLE));
				userDao.createUser(tbuser);

				// 最后刷新时间插一条记录
				OrderDownloadLog log = new OrderDownloadLog();
				log.setUserId(tbuser.getUserId());
				log.setLastLoadTime(DateUtil.currentDateTime());
				orderDownloadLogDao.save(log);
			}
			// 否则修改sessionKey
			else {

				tbuser = userDao.getUserById(tbuser.getUserId());

				if (tbuser != null) {

					String salt = DateUtil.getDateTime("yyyyMMddHHmmss", tbuser.getCreatedTime());
					tbuser.setPassword(SHA256Util.SHA256Encrypt(sessionId, salt));
					tbuser.setSessionKey(sessionId);
					tbuser.setLastLoginTime(DateUtil.currentDateTime());
					tbuser.setChainCode(chainCode);
					userDao.updateUserSessionKeyPwd(tbuser);

					initUserSession();
				}
			}

			setUserDetail(tbuser, sessionId);

			// 缓存起来
			userSessionMap.put(tbuser.getUserId(), tbuser.getSessionKey());
			chainUserSessionMap.put(tbuser.getChainCode(), tbuser.getSessionKey());
		}
		return "";
	}

	/**
	 * 创建用户
	 */
	public B2BUser createUserInfo(B2BUser b2bUser) {
		B2BUser returnUser = null;
		try {
			// 密码加密
			Date createDate = new Date();
			if (passwordEncoder != null) {
				String salt = DateUtil.getDateTime("yyyyMMddHHmmss", createDate);
				b2bUser.setPassword(SHA256Util.SHA256Encrypt(b2bUser.getPassword(), salt));
			} else {
				log.warn("PasswordEncoder not set, skipping password encryption...");
			}

			B2BUser tmpUser = userDao.getUserByUsername(b2bUser.getUsername());
			if (tmpUser != null) {
				throw new BizException("createPlatUser.userExist", b2bUser.getUsername() + " 该用户名已经存在.");
			} else {
				// 同时增加雇员信息
				Employee employee = b2bUser.getEmployee();
				employee.setCreatedTime(createDate);

				employee.setContactFlag(YesNo.NO);
				employee.setDelFlag(false);
				employee.setCompanyId(b2bUser.getCompanyId());
				employee = employeeDao.save(employee);
				List<EmployeeI18n> employeeI18nList = b2bUser.getEmployeeI18nList();
				if(employeeI18nList==null){
					employeeI18nList = new ArrayList<EmployeeI18n>();
					employeeI18nList.add(this.getDefaultLanguageI18n(b2bUser,LanguageCode.MAIN_LANGUAGE_CODE));
				}
				for(EmployeeI18n e :employeeI18nList){
					e.setEmployeeId(employee.getEmployeeId());
					employeeDao.addEmployeeI18n(e);
				}

				b2bUser.setEmployeeId(employee.getEmployeeId());

				b2bUser.setAccountExpired(false);
				b2bUser.setAccountLocked(false);
				b2bUser.setEnabled(true);
				b2bUser.setCredentialsExpired(false);
				b2bUser.setCreatedTime(employee.getCreatedTime());

				// 设置为用户角色，因为一些公共的都分配给了用户角色，比如密码修改等
				if (b2bUser.getCompanyId().equals(CompanyType.HOTEL)) {
					b2bUser.addRole(roleDao.getRoleByName(Constants.COADMIN_ROLE));
				}
				// 运营人员角色
				else if (b2bUser.getCompanyId().equals(CompanyType.PLAT_COMPANY_ID)) {
					b2bUser.addRole(roleDao.getRoleByName(Constants.ADMIN_OPER));
				}
				b2bUser.addRole(roleDao.getRoleByName(Constants.USER_ROLE));

				b2bUser.setPasswordIsInit(false);
				b2bUser.setIslocak(false);
				returnUser = userDao.saveUser(b2bUser);
				
				UserPassword userPassword = new UserPassword();
				userPassword.setUserId(returnUser.getUserId());
				userPassword.setPassword1(returnUser.getPassword());
				userPassword.setPassword2(returnUser.getPassword());
				userPassword.setPassword3(returnUser.getPassword());
				userPassword.setPassword4(returnUser.getPassword());
				userPassword.setPasswordlastModifyTime(createDate);
				userPassword.setPasswordFailNumber(0);
				userPassword.setCreatedTime(createDate);
				
				userDao.addUserPassword(userPassword);
			}

		} catch (BizException e) {

			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			log.info("创建用户时系统错误");
			throw new BizException("comUserRegist.sysException", "创建用户时系统错误.");
		}

		return returnUser;
	}

	/**
	 * 更新用户
	 */
	public B2BUser updateUserInfo(B2BUser b2bUser) {

		// 更新雇员基本信息
		Employee employee = b2bUser.getEmployee();

		Employee dbEmployee = employeeDao.get(b2bUser.getEmployeeId());

		dbEmployee.setName(employee.getName());
		dbEmployee.setDept(employee.getDept());
		dbEmployee.setTitle(employee.getTitle());

		dbEmployee.setTelNo(employee.getTelNo());
		dbEmployee.setMobile(employee.getMobile());
		dbEmployee.setQq(employee.getQq());
		dbEmployee.setEmail(employee.getEmail());

		employeeDao.save(dbEmployee);
		List<EmployeeI18n> employeeI18nList = b2bUser.getEmployeeI18nList();
		if(employeeI18nList!=null){
			employeeDao.deleteEmployeeI18nByEmployeeId(b2bUser.getEmployeeId());
			for(EmployeeI18n e :employeeI18nList){
				e.setEmployeeId(b2bUser.getEmployeeId());
				employeeDao.addEmployeeI18n(e);
			}
		}
		userDao.updateIsLive(b2bUser);

		return b2bUser;
	}

	public void setRoleOfUserOfMc(String userId, List<UserRoleVO> userRoles) {

		// 注意：userRoles只包含选中的权限，不包含未选中的权限

		// 比较”数据库中选中的权限“和”当前选中的权限“：
		// 对于数据库里选中，但是当前没有选中的，删除该权限
		// 对于数据库里没有选中，但是当前选中的，加上该权限
		// 对于不变的，不管

		List<UserRoleVO> dbUserRoles = userDao.getChecekedRoleOfUser(userId);

		List<UserRoleVO> toBeDeleted = new ArrayList<UserRoleVO>();
		List<UserRoleVO> toBeAdded = new ArrayList<UserRoleVO>();
		List<UserRoleVO> noChanges = new ArrayList<UserRoleVO>();

		// 找出不变的和需要删除的
		for (UserRoleVO dbRoleVO : dbUserRoles) {

			if (dbRoleVO.getRoleId() != null) {

				int index = Arrays.binarySearch(Role.DEFAULT_ROLES, dbRoleVO.getRoleId());

				if (index >= 0) {
					continue;// 忽略缺省的角色
				}
			}

			Boolean isFind = false;
			for (UserRoleVO roleVO : userRoles) {
				if ((dbRoleVO.getRoleId() == null || dbRoleVO.getRoleId().equals(roleVO.getRoleId())) && dbRoleVO.getUserId().equals(roleVO.getUserId())) {
					if (!StringUtils.hasText(dbRoleVO.getHotelId()) && !StringUtils.hasText(roleVO.getHotelId())) {
						// 如果两个都为空
						isFind = true;
						break;
					} else if (dbRoleVO.getHotelId() != null && roleVO.getHotelId() != null && dbRoleVO.getHotelId().equals(roleVO.getHotelId())) {
						// 如果两个相同
						isFind = true;
						break;
					}
				}
			}
			if (isFind) {
				noChanges.add(dbRoleVO);
			} else {
				toBeDeleted.add(dbRoleVO);
			}
		}
		// 找出需要新加的
		for (UserRoleVO userRoleVO : userRoles) {
			Boolean isFind = false;
			for (UserRoleVO roleVO : noChanges) {
				if (((userRoleVO.getRoleId() != null && userRoleVO.getRoleId().equals(roleVO.getRoleId())) || userRoleVO.getRoleId() == null) && userRoleVO.getUserId().equals(roleVO.getUserId())) {
					if (!StringUtils.hasText(userRoleVO.getHotelId()) && !StringUtils.hasText(roleVO.getHotelId())) {
						// 如果两个都为空
						isFind = true;
						break;
					} else if (userRoleVO.getHotelId() != null && roleVO.getHotelId() != null && userRoleVO.getHotelId().equals(roleVO.getHotelId())) {
						// 如果两个相同
						isFind = true;
						break;
					}
				}
			}
			if (!isFind) {
				toBeAdded.add(userRoleVO);
			}
		}

		// 数据库操作
		for (UserRoleVO roleVO : toBeAdded) {
			userDao.saveHotelUserRole(roleVO.getRoleId(), roleVO.getUserId(), roleVO.getHotelId());
		}

		for (UserRoleVO roleVO : toBeDeleted) {
			userDao.deleteHotelUserRole(roleVO.getRoleId(), roleVO.getUserId(), roleVO.getHotelId());
		}

	}

	/**
	 * 先删除当前用户下默认Role.DEFAULT_ROLES之外的角色，再 复制选择的用户下默认Role.DEFAULT_ROLES之外的角色到当前用户下
	 */
	public void copyUserRoleByUserId(String userId, String curUserId) {
		userDao.deleteUserRoleByURID(curUserId);
		userDao.saveUserRoleBatch(userId, curUserId);

	}

	/**
	 * 更新用户sessionKey与更新时间
	 * 
	 * @param user
	 */
	public void updateUserSessionKey(B2BUser user) {
		userDao.updateUserSessionKeyPwd(user);
	}

	/**
	 * 删除用户，雇员，角色
	 */
	public void removeUser(String userId) {
		log.debug("removing user: " + userId);

		B2BUser user = userDao.getUserById(userId);

		// 删除用户 ,角色
		userDao.remove(userId);

		// 删除对应的employee
		if (user != null && StringUtils.hasText(user.getEmployeeId())) {
			employeeDao.remove(user.getEmployeeId());
			employeeDao.deleteEmployeeI18nByEmployeeId(user.getEmployeeId());
		}

	}

	@Override
	public String getUserSessionKey(String userId) {
		String sessionKey = "";
		if (userSessionMap == null) {
			initUserSession();
		}
		sessionKey = userSessionMap.get(userId);
		if (!StringUtils.hasText(sessionKey)) {
			B2BUser b = userDao.getUserById(userId);
			if (b != null) {
				sessionKey = b.getSessionKey();
				userSessionMap.put(userId, sessionKey);
			}
		}
		return sessionKey;
	}

	@Override
	public String getChainUserSessionKey(String chainCode) {
		String sessionKey = chainUserSessionMap.get(chainCode);
		if (CommonUtil.isEmpty(sessionKey)) {
			sessionKey = userDao.getSessionKeyByChainCode(chainCode);
			chainUserSessionMap.put(chainCode, sessionKey);
		}
		return sessionKey;
	}

	/**
	 * {@inheritDoc}
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		B2BUser user = getUserByUsername(username);

		if (user == null) {
			log.warn("uh oh, user not found...");
			throw new UsernameNotFoundException("user '" + username + "' not found...");
		} else {

			// CCM-MC使用
			Object obj = SecurityHolder.getSession().getAttribute("hotelId");
			if (obj != null) {
				user.setHotelId((String) obj);
			}

			String salt = DateUtil.getDateTime("yyyyMMddHHmmss", user.getCreatedTime());
			user.setSalt(salt);

			List<Role> roles = userDao.getUserRoles(user.getUserId());
			user.setRoles(new HashSet<Role>(roles));

			Locale locale = (Locale) SecurityHolder.getSession().getAttribute(Constants.PREFERRED_LOCALE_KEY);
			String language = null;
			if(locale==null){
				language = LanguageCode.MAIN_LANGUAGE_CODE;
			}else{
				language = locale.getLanguage()+"_"+locale.getCountry();
			}
			if (StringUtils.hasText(user.getEmployeeId())) {
//				Employee employee = employeeDao.get(user.getEmployeeId());
				Employee employee = employeeDao.getEmployee(user.getEmployeeId(), language);
				user.setEmployee(employee);
			}

		}

		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	public B2BUserSearchResult search(B2BUserCriteria userCriteria) {
		return userDao.searchUser(userCriteria);
	}

	/**
	 * {@inheritDoc}
	 */
	public B2BUser getB2bUser(String userId) {

		B2BUser b2bUser = userDao.get(userId);

		if (b2bUser.getEmployeeId() != null && !"".equals(b2bUser.getEmployeeId())) {
			Employee employee = employeeDao.get(b2bUser.getEmployeeId());
			b2bUser.setEmployee(employee);
		}

		return b2bUser;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public B2BUser getB2bUser(String userId,String language) {

		B2BUser b2bUser = userDao.get(userId);

		if (b2bUser.getEmployeeId() != null && !"".equals(b2bUser.getEmployeeId())) {
			Employee employee = employeeDao.getEmployee(b2bUser.getEmployeeId(),language);
			List<EmployeeI18n> employeeI18nList = employeeDao.getEmployeeI18nByEmployeeId(b2bUser.getEmployeeId(),language);
			b2bUser.setEmployee(employee);
			b2bUser.setEmployeeI18nList(employeeI18nList);
		}

		return b2bUser;
	}

	/**
	 * 根据用户ID获取酒店ID
	 */
	public List<String> getHotelIdListByUserId(String userId) {
		return userDao.getHotelIdOfUser(userId);
	}

	/**
	 * 重新设置用户信息及获取权限信息
	 * 
	 * @param user
	 * @param credentials
	 */
	public void setUserDetail(B2BUser user, Object credentials) {

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, credentials);
		auth.setDetails(user);
		Authentication authenticatedUser = authenticationManager.authenticate(auth);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

	}

	/**
	 * 根据用户ID查询父及兄弟用户
	 */
	public List<String> getUserIdByCurUserId(String userId) {

		List<String> result = new ArrayList<String>();

		List<B2BUser> userList = null;

		B2BUser usera = get(userId);

		// 当前用户是父用户
		if (CompanyType.B2BUSER.equals(usera.getCompanyId())) {
			userList = userDao.getB2BUserByCompanyId(userId);

		}
		// 当前用户是子用户
		else {
			userId = usera.getCompanyId();
			userList = userDao.getB2BUserByCompanyId(usera.getCompanyId());

		}
		if (userList != null && !userList.isEmpty()) {
			for (B2BUser user : userList) {
				result.add(user.getUserId());// 添加子用户
			}
		}
		result.add(userId);// 添加父用户
		return result;
	}

	/************* 以上是整理过的 *****************/

	/***
	 * 根据用户id获取用户集团代码
	 * 
	 * @param tbuser
	 * @return
	 */
	public String getOnlineChainCode(B2BUser tbuser) {
		OnlineUser ou = new OnlineUser();
		ou.setUserId(tbuser.getUserId());
		List<OnlineUser> ouList = onlineUserManager.searchOnlineUser(ou); // 判断用户类型，从onlineUser获取
		if (ouList != null && ouList.size() > 0) {
			OnlineUser ouCCM = ouList.get(0);
			if (StringUtils.hasText(ouCCM.getChainCode())) {
				// 直连集团用户
				return ouCCM.getChainCode();
			}
		}
		return null;
	}

	@Override
	public Map<String, String> getAllUserSession() {

		Map<String, String> map = new HashMap<String, String>();

		if (userSessionMap == null) {

			initUserSession();

		}

		map.putAll(userSessionMap);

		return map;
	}

	/**
	 * 根据父用户ID查询所有子用户
	 */
	public List<B2BUser> getB2BUserByCompanyId(String companyId) {
		return userDao.getB2BUserByCompanyId(companyId);
	}

	/**
	 * 添加酒店时保存用户酒店关系
	 */
	public void saveUserHotel(String userId, String hotelId, Set<Role> rSet) {
		if (rSet != null) {
			Iterator<Role> i = rSet.iterator();
			while (i.hasNext()) {
				String roleId = i.next().getRoleId();
				userDao.saveHotelUserRole(roleId, userId, hotelId);
			}
		}

	}

	/**
	 * 更新用户的账务设置
	 * 
	 * @param user
	 */
	public void changeUserAccount(B2BUser user) {
		userDao.changeUserAccount(user);
	}

	/**
	 * 从淘宝登录成功后获取淘宝用户的店铺地址
	 * 
	 * @param user
	 * @return
	 */
	public B2BUser getUserTaobaoShop(B2BUser user) {
		Shop s = taobaoShopManager.getShop(user.getUsername());
		if (s != null && s.getSid() != null) {
			user.setShopurl("shop" + s.getSid().toString() + ".taobao.com");
		}
		return user;
	}

	/**
	 * 淘宝设置用户权限
	 */
	public void setRoleOfUser(String userId, List<UserRoleVO> userRoles) {

		// 数据库操作
		userDao.deleteUserRoles(userId);

		if (userRoles == null) {
			return;
		}

		for (UserRoleVO roleVO : userRoles) {
			userDao.saveHotelUserRole(roleVO.getRoleId(), roleVO.getUserId(), roleVO.getHotelId());
		}

	}

	@Override
	public List<B2BUser> getUsers() {
		return userDao.getAllDistinct();
	}

	@Override
	public B2BUser getUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.getUserByUsername(username);
	}

	/**
	 * {@inheritDoc}
	 */
	public B2BUser comUserRegist(B2BUser b2bUser) {

		B2BUser returnUser = null;

		try {

			Date createDate = DateUtil.currentDateTime();

			// 密码加密
			if (passwordEncoder != null) {

				String salt = DateUtil.getDateTime("yyyyMMddHHmmss", createDate);

				b2bUser.setPassword(SHA256Util.SHA256Encrypt(b2bUser.getPassword(), salt));
			} else {
				log.warn("PasswordEncoder not set, skipping password encryption...");
			}

			B2BUser tmpUser = userDao.getUserByUsername(b2bUser.getUsername());
			if (tmpUser != null) {
				throw new BizException("comUserRegist.userExist", "注册用户名已经存在.");
			} else {

				b2bUser.setAccountExpired(false);
				b2bUser.setAccountLocked(false);
				b2bUser.setEnabled(true);
				b2bUser.setCredentialsExpired(false);

				// 设置角色为商家默认管理员角色,同时又用户角色，因为一些公共的都分配给了用户，比如密码修改等

				b2bUser.addRole(roleDao.getRoleByName(Constants.COADMIN_ROLE));

				b2bUser.addRole(roleDao.getRoleByName(Constants.USER_ROLE));

				returnUser = userDao.saveUser(b2bUser);
			}

		} catch (BizException e) {

			throw e;

		} catch (Exception e) {
			e.printStackTrace();
			log.info("注册时系统错误");
			throw new BizException("comUserRegist.sysException", "注册时系统错误.");
		}

		return returnUser;
	}

	@Override
	public void relateWithCompany(String userId, String companyId) {
		B2BUser b2bUser = userDao.get(userId);
		b2bUser.setCompanyId(companyId);
		userDao.save(b2bUser);
	}

	/**
	 * {@inheritDoc}
	 */
	public void changePassword(String userId, String oldPassword, String newPassword) {

		if (passwordEncoder == null) {
			log.warn("PasswordEncoder not set, skipping password encryption...");
			return;
		}

		B2BUser b2bUser = getB2bUser(userId);
		String salt = DateUtil.getDateTime("yyyyMMddHHmmss", b2bUser.getCreatedTime());
		if (!SHA256Util.SHA256Encrypt(oldPassword, salt).equals(b2bUser.getPassword())) {
			throw new BizException("changePassword.wrongPassword", "现有密码输入有误.");
		}
		String password = SHA256Util.SHA256Encrypt(newPassword, salt);
		userDao.changePassword(userId, password);
		this.updateUserPassword(userId, password);
		if(!b2bUser.getPasswordIsInit()){
			userDao.updatePasswordIsInit(1, userId);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void resetPassword(String userId, String newPassword) {

		if (passwordEncoder == null) {
			log.warn("PasswordEncoder not set, skipping password encryption...");
			return;
		}

		B2BUser b2bUser = getB2bUser(userId);
		String salt = DateUtil.getDateTime("yyyyMMddHHmmss", b2bUser.getCreatedTime());
		String password = SHA256Util.SHA256Encrypt(newPassword, salt);
		
		userDao.changePassword(userId, password);
		this.updateUserPassword(userId, password);
		//解锁
		userDao.updateLocak(0, userId);
		//密码错误次数改为0
		userDao.updatePassFailNumber(0, userId);
		//密码状态改为初始密码状态
		userDao.updatePasswordIsInit(0, userId);
	}

	@Override
	public Map<String, List<UserRoleVO>> getRoleOfUser(String userId) {

		Map<String, List<UserRoleVO>> result = new HashMap<String, List<UserRoleVO>>();

		String roleUsage = userDao.getRoleUsageOfUser(userId);

		if (RoleUsage.PLAT.equals(roleUsage)) {
			// 对于运营用户，根据所有运营相关的角色并确定那些checked
			List<String> usageList = new ArrayList<String>();
			usageList.add(RoleUsage.PLAT);
			List<UserRoleVO> checkedRoles = userDao.getChecekedRoleOfUser(userId, usageList);
			for (UserRoleVO roleVO : checkedRoles) {
				// 排除系统管理员的权限
				if (StringUtils.hasText(roleVO.getRoleCategory())) {
					List<UserRoleVO> roleList = result.get(roleVO.getRoleCategory());
					if (roleList == null) {
						roleList = new ArrayList<UserRoleVO>();
						result.put(roleVO.getRoleCategory(), roleList);
					}
					roleList.add(roleVO);
				}
			}
		} else if (RoleUsage.CHAIN.equals(roleUsage)) {
			// 对于集团用户，首先显示所有集团相关的角色，并确定那些checked.
			List<String> usageList = new ArrayList<String>();
			usageList.add(RoleUsage.CHAIN);
			List<UserRoleVO> chainRoles = userDao.getChecekedRoleOfUser(userId, usageList);
			result.put("集团权限", chainRoles);
			// 然后对于每个酒店，显示所有相关的角色，并确定那些checked
			// List<Hotel> hotels = hotelDao.getUserRelatedHotelIds(userId);

			List<String> hotelUsageList = new ArrayList<String>();
			hotelUsageList.add(RoleUsage.HOTEL);
			// for (Hotel h : hotels) {
			// List<UserRoleVO> roles = userDao.getChecekedRoleOfUser(userId,
			// hotelUsageList, h.getHotelId(), h.getHotelName());
			// result.put(h.getHotelName(), roles);
			// }

		} else if (RoleUsage.HOTEL.equals(roleUsage)) {
			// 对于酒店用户，显示所有相关的角色，并确定那些checked
			List<String> usageList = new ArrayList<String>();
			usageList.add(RoleUsage.HOTEL);
			usageList.add(RoleUsage.HOTEL_OR_CHAIN);
			List<UserRoleVO> checkedRoles = userDao.getChecekedRoleOfUser(userId, usageList);
			for (UserRoleVO roleVO : checkedRoles) {
				// 排除商家管理员的权限
				if (StringUtils.hasText(roleVO.getRoleCategory())) {
					List<UserRoleVO> roleList = result.get(roleVO.getRoleCategory());
					if (roleList == null) {
						roleList = new ArrayList<UserRoleVO>();
						result.put(roleVO.getRoleCategory(), roleList);
					}
					roleList.add(roleVO);
				}
			}
		}

		return result;
	}

	@Override
	public void changeEmail(String userId, String newEmail) {
		B2BUser user = get(userId);
		if (user != null && StringUtils.hasText(user.getEmployeeId())) {
			Employee e = employeeDao.get(user.getEmployeeId());
			e.setEmail(newEmail);
			employeeDao.save(e);
		}
	}

	/**
	 * 根据sessionId读用户的淘宝资料
	 * 
	 * @param sessionId
	 * @return
	 */
	private B2BUser getUserFromTaobao(String sessionId) {
		B2BUser user = new B2BUser();

		UserSellerGetRequest req = new UserSellerGetRequest();
		req.setFields("user_id,nick");
		try {
			UserSellerGetResponse response = TaobaoApi.taobaoClient.execute(req, sessionId);
			if (response.isSuccess() && response.getUser() != null) {
				user.setUserId(response.getUser().getUserId() + "");
				user.setUsername(response.getUser().getNick());
				user.setSessionKey(sessionId);
				return user;
			}
			return null;
		} catch (ApiException e) {
			e.printStackTrace();
			throw new BizException("TB00001");
		}
	}

	/**
	 * 清session 缓存
	 */
	@Override
	public void cleanSessionCache() {
		userSessionMap = null;
		chainUserSessionMap = null;
	}

	/**
	 * 加载所有用户的sessionKey
	 */
	private void initUserSession() {
		userSessionMap = new HashMap<String, String>();
		List<B2BUser> userList = userDao.getAllUserSessionKey();
		for (B2BUser user : userList) {
			userSessionMap.put(user.getUserId(), user.getSessionKey() == null ? "" : user.getSessionKey());
			chainUserSessionMap.put(user.getChainCode(), user.getSessionKey() == null ? "" : user.getSessionKey());
		}
	}

	@Override
	public void updateStatus(int initStatus) {

		B2BUser user = SecurityHolder.getB2BUser();
		boolean flag = false;

		// 当前用户第一次新增酒店
		if (UserInitStatus.HotelCreated.intValue() == initStatus && user.getInitStatus() == null) {
			flag = true;
		}

		// 进入酒店下一步
		if (UserInitStatus.HotelNext.intValue() == initStatus && user.getInitStatus().intValue() == UserInitStatus.HotelCreated.intValue()) {
			flag = true;
		}

		// 当前用户第一次新增房型
		if (UserInitStatus.RoomTypeCreated.intValue() == initStatus && user.getInitStatus().intValue() == UserInitStatus.HotelNext.intValue()) {
			flag = true;
		}

		// 进入房型下一步
		if (UserInitStatus.RoomTypeNext.intValue() == initStatus && user.getInitStatus().intValue() == UserInitStatus.RoomTypeCreated.intValue()) {
			flag = true;
		}

		// 当前用户第一次新增房价
		if (UserInitStatus.RatePlanCreated.intValue() == initStatus && user.getInitStatus().intValue() == UserInitStatus.RoomTypeNext.intValue()) {
			flag = true;
		}

		// 进入房价下一步
		if (UserInitStatus.RatePlanNext.intValue() == initStatus && user.getInitStatus().intValue() == UserInitStatus.RatePlanCreated.intValue()) {
			flag = true;
		}

		// 当前用户第一次新增产品
		if (UserInitStatus.ProductCreated.intValue() == initStatus && user.getInitStatus().intValue() == UserInitStatus.RatePlanNext.intValue()) {
			flag = true;
		}

		// 已完成
		if (UserInitStatus.ProductEnd.intValue() == initStatus && user.getInitStatus().intValue() == UserInitStatus.ProductCreated.intValue()) {
			flag = true;
		}

		if (flag) {
			userDao.updateStatus(user.getUserId(), initStatus);
			user.setInitStatus(initStatus);
		}
	}

	@Override
	public B2BUser getUserByHotelId(String hotelId) {
		List<B2BUser> b2buserList = userDao.getUserByHotelId(hotelId);
		if (b2buserList != null && b2buserList.size() > 0) {
			return b2buserList.get(0);
		}
		return null;
	}

	/**
	 * 根据用户ID获取酒店代码
	 */
	public List<String> getHoteCodeListByUserId(String userId) {
		return userDao.getHotelCodeOfUser(userId);
	}

	@Override
	public List<B2BUserVO> getAllB2BUser() {
		return userDao.getAllB2BUser();
	}

	@Override
	public EmployeeI18n getDefaultLanguageI18n(B2BUser b2bUser,String language) {
		EmployeeI18n i18n = new EmployeeI18n();
		i18n.setTitle(b2bUser.getEmployee().getTitle());
		i18n.setEmployeeId(b2bUser.getEmployeeId());
		i18n.setDept(b2bUser.getEmployee().getDept());
		i18n.setName(b2bUser.getEmployee().getName());
		i18n.setLanguage(language);
		return i18n;
	}

	@Override
	public void setRoleOfUserOfMcChannel(String userId,String roleId,List<UserRoleChannel> userRoleChannels) {

		// 注意：userRoles只包含选中的权限，不包含未选中的权限

		// 比较”数据库中选中的权限“和”当前选中的权限“：
		// 对于数据库里选中，但是当前没有选中的，删除该权限
		// 对于数据库里没有选中，但是当前选中的，加上该权限
		// 对于不变的，不管
		
		UserRole conidtion=new UserRole();
		conidtion.setUserId(userId);
		conidtion.setRoleId(roleId);
		List<UserRoleChannel> dbUserRoles = null;
		if(CommonUtil.isEmpty(conidtion.getRoleId())){
			dbUserRoles = userDao.getChecekedChannelRoleOfUser(conidtion.getUserId());
		}else{
			dbUserRoles = userDao.getCheckedChannelRoleByUserRole(conidtion);
		}
	
		List<UserRoleChannel> toBeDeleted = new ArrayList<UserRoleChannel>();
		List<UserRoleChannel> toBeAdded = new ArrayList<UserRoleChannel>();

		Set<UserRoleChannel> addUserRoleChannelSet=new HashSet<UserRoleChannel>(userRoleChannels);
		// 找出不变的和需要删除的
		for (UserRoleChannel dbRoleVO : dbUserRoles) {

			if (dbRoleVO.getRoleId() != null) {

				int index = Arrays.binarySearch(Role.DEFAULT_ROLES, dbRoleVO.getRoleId());

				if (index >= 0) {
					continue;// 忽略缺省的角色
				}
			}
			Boolean isFind = false;
			for (UserRoleChannel roleVO : userRoleChannels) {
				if(CommonUtil.isEmpty(dbRoleVO.getRoleId())||CommonUtil.isEmpty(dbRoleVO.getChannelId())){
					// old data has no roleId
					break;
				}
				else if (dbRoleVO.getRoleId().equals(roleVO.getRoleId()) 
						&&dbRoleVO.getChannelId().equals(roleVO.getChannelId())) {
					// old data is the same as new data
						// 如果两个相同
						addUserRoleChannelSet.remove(roleVO);
						isFind = true;
						break;
				}
			}
			if (isFind==false) {
				toBeDeleted.add(dbRoleVO);
			}
		}
		
		toBeAdded.addAll(addUserRoleChannelSet);
	
		// 数据库操作
		for (UserRoleChannel roleVO : toBeAdded) {
			userDao.saveCHannelUserRole(roleVO.getRoleId(), roleVO.getUserId(), roleVO.getChannelId());
		}

		for (UserRoleChannel roleVO : toBeDeleted) {
			userDao.deleteCHannelUserRole(roleVO.getRoleId(), roleVO.getUserId(), roleVO.getChannelId());
		}
		
	}

	@Override
	public List<String> getChannelIdListByUserId(String userId) {
		return userDao.getChannelIdListByUserId(userId);
	}
	
	/**
	 * 取用户所有已有权限
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserRole> getUserRolesByUserId(String userId){
		return userDao.getUserRolesByUserId(userId);
	}

	@Override
	public boolean isLock(String userid) {
		UserPassword userPassword = userDao.getUserPasswordInfo(userid);
		if(userPassword==null){
			return true;
		}
		if(userPassword.getPasswordFaillastModifyTime()==null){
			return false;
		}
		
		long passwordFaillastModifyTime = userPassword.getPasswordFaillastModifyTime().getTime();
		long nowTime = System.currentTimeMillis();
		
		/**
		 * 上一次输错密码时间为10分钟之前，自动解锁，输入错误次数重置为0
		 */
		if(passwordFaillastModifyTime<nowTime-10*60*1000){
			userDao.updateLocak(0, userid);
			userDao.updatePassFailNumber(0, userid);
			return false;
		}else{
			if(userPassword.getIslocak()){
				/**
				 * 如果输入密码错误次数大于0，则重置为0
				 */
				if(userPassword.getPasswordFailNumber()>0){
					userDao.updatePassFailNumber(0, userid);
				}
				return true;
			}else{
				
				/**
				 * 输出密码错误次数大于6，且输入错误时间在10分钟之内
				 * 同时改为被锁定,错误次数重置为0
				 */
				if(userPassword.getPasswordFailNumber()>=Constants.ALLOW_PASSWORD_FAIL_NUMBER){
					userDao.updateLocak(1, userid);
					userDao.updatePassFailNumber(0, userid);
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public void updateLock(String userid, int lock) {
		userDao.updateLocak(lock, userid);
	}

	@Override
	public UserPassword getUserPasswordInfo(String userid) {
		return userDao.getUserPasswordInfo(userid);
	}

	@Override
	public void updatePassFailNumber(int number, String userId) {
		UserPassword userPassword = userDao.getUserPasswordInfo(userId);
		if(userPassword!=null){
			long passwordFaillastModifyTime = userPassword.getPasswordFaillastModifyTime().getTime();
			long nowTime = System.currentTimeMillis();
			/**
			 * 上一次输入密码错误时间大于10分钟，这次错误数重置为1
			 */
			if(passwordFaillastModifyTime<nowTime-10*60*1000){
				userDao.updatePassFailNumber(1, userId);
			}else{
				int passwordFailNumber = userPassword.getPasswordFailNumber()+number;
				//大于等于6次，锁定账户
				if(passwordFailNumber>=Constants.ALLOW_PASSWORD_FAIL_NUMBER){
					userDao.updateLocak(1, userId);
				}
				userDao.updatePassFailNumber(passwordFailNumber, userId);
			}
		}
	}

	@Override
	public void updateUserPassword(String userId, String password) {
		UserPassword userPassword = userDao.getUserPasswordInfo(userId);
		/**
		 * password-->pass1  
		 * pass1-->pass2  
		 * pass2-->pass3  
		 * pass3-->pass4  
		 */
		UserPassword userPassword_new = new UserPassword();
		userPassword_new.setUserId(userId);
		userPassword_new.setPassword1(password);
		userPassword_new.setPassword2(userPassword.getPassword1());
		userPassword_new.setPassword3(userPassword.getPassword2());
		userPassword_new.setPassword4(userPassword.getPassword3());
		userPassword_new.setPasswordFailNumber(0);//失败次数重置为0
		userPassword_new.setPasswordlastModifyTime(new Date());//当前时间
		userPassword_new.setPasswordFaillastModifyTime(userPassword.getPasswordFaillastModifyTime());
		
		userDao.updateUserPassword(userPassword_new);
		
	}

	@Override
	public boolean checkUserPassowrd(B2BUser user, String password) {
		if(StringUtils.hasLength(password)){
			String salt = DateUtil.getDateTime("yyyyMMddHHmmss", user.getCreatedTime());
			String enpass = SHA256Util.SHA256Encrypt(password, salt);
			if(enpass.equalsIgnoreCase(user.getPassword())){
				return true;
			}
		}
		/**
		 * 密码输入错误的情况
		 */
		UserPassword userPassword = userDao.getUserPasswordInfo(user.getUserId());
		if(userPassword!=null){
			userPassword.setPasswordFaillastModifyTime(new Date());
			userDao.updateUserPassword(userPassword);
			
			this.updatePassFailNumber(1, user.getUserId());
		}
		return false;
	}
	
	@Override
	public void unlockUser(String userId) {
		userDao.updateLocak(0, userId);
		userDao.updatePassFailNumber(0, userId);
	}

	@Override
	public boolean passwordAllowEidt(String userid, String password) {
		UserPassword userPassword = userDao.getUserPasswordInfo(userid);
		if(userPassword!=null&&StringUtils.hasLength(password)){
			B2BUser b2bUser = getB2bUser(userid);
			String salt = DateUtil.getDateTime("yyyyMMddHHmmss", b2bUser.getCreatedTime());
			String enpass = SHA256Util.SHA256Encrypt(password, salt);
			/**
			 * 新密码不能与前4次密码相同
			 */
			if(enpass.equalsIgnoreCase(userPassword.getPassword1())||
				enpass.equalsIgnoreCase(userPassword.getPassword2())||
				enpass.equalsIgnoreCase(userPassword.getPassword3())||
				enpass.equalsIgnoreCase(userPassword.getPassword4())
			){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}

	@Override
	public int inputFailPasswordNumber(B2BUser user, String password) {
		if(StringUtils.hasLength(password)){
			String salt = DateUtil.getDateTime("yyyyMMddHHmmss", user.getCreatedTime());
			String enpass = SHA256Util.SHA256Encrypt(password, salt);
			if(enpass.equalsIgnoreCase(user.getPassword())){
				return Constants.ALLOW_PASSWORD_FAIL_NUMBER;
			}
		}else{
			return Constants.ALLOW_PASSWORD_FAIL_NUMBER;
		}
		/**
		 * 密码输入错误的情况
		 */
		UserPassword userPassword = userDao.getUserPasswordInfo(user.getUserId());
		if(userPassword!=null){
			userPassword.setPasswordFaillastModifyTime(new Date());
			userDao.updateUserPassword(userPassword);
			
			this.updatePassFailNumber(1, user.getUserId());
			return Constants.ALLOW_PASSWORD_FAIL_NUMBER-(userPassword.getPasswordFailNumber());
		}
		return Constants.ALLOW_PASSWORD_FAIL_NUMBER;
	}

	@Override
	public B2BUserVO getUserByLoginName(String username) {
		return userDao.getUserByLoginName( username);
	}

	@Override
	public void resetForgetPassword(String userId, String password) {
		B2BUser b2bUser = getB2bUser(userId);
		String salt = DateUtil.getDateTime("yyyyMMddHHmmss", b2bUser.getCreatedTime());
		String pwd = SHA256Util.SHA256Encrypt(password, salt);
		
		userDao.changePassword(userId, pwd);
		
	}
	
	@Override
	public Integer removeUserRoleChannelByUserId(String userId){
		return 	userDao.removeUserRoleChannelByUserId(userId);
	}
	
	@Override
	public Integer removeUserRoleChannelByRoleId(String roleId){
		return 	userDao.removeUserRoleChannelByRoleId(roleId);
	}
	
	@Override
	public List<String> getUserIdByRoleId(String roleId){
		return 	userDao.getUserIdByRoleId(roleId);
	}
	
	@Override
	public void saveUserRoleChannel(String roleId,String userId,String channelId){
		userDao.saveCHannelUserRole(roleId,  userId,channelId);
	}
	
	public Map<String, List<MenuTreeVO>> getMenuByRoles(List<String>roleIds,String language){
		List<Menu> allMenuList = menuDao.getAllMenu(language);
		Map<String, MenuTreeVO> authMap = new HashMap<String, MenuTreeVO>();
		for (Menu menu : allMenuList) {
			if (menu.getMenuType().equals("2")) {
				if (authMap.containsKey(menu.getParentId())) {
					authMap.get(menu.getParentId()).addMenu(menu);
				}
			} else {
				MenuTreeVO tmpMenuTreeVO = new MenuTreeVO();
				tmpMenuTreeVO.setMenu(menu);
				authMap.put(menu.getMenuId(), tmpMenuTreeVO);
			}

		}
		
		
		// 组装权限列表
				Map<String, List<MenuTreeVO>> retmap = new HashMap<String, List<MenuTreeVO>>();

				for (String menuId : authMap.keySet()) {
					MenuTreeVO menuTreeVO = authMap.get(menuId);
					if (menuTreeVO.getMenu() != null) {
						if (retmap.containsKey(menuTreeVO.getMenu().getMenuName())) {
							retmap.get(menuTreeVO.getMenu().getMenuName()).add(menuTreeVO);
						} else {
							List<MenuTreeVO> list = new ArrayList<MenuTreeVO>();
							list.add(menuTreeVO);
							retmap.put(menuTreeVO.getMenu().getMenuName(), list);
						}
					}
				}
				return retmap;
	}

	
}
