package com.ccm.mc.webapp.action.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.dao.common.MenuDao;
import com.ccm.api.dao.user.mongodb.AuthRoleDaoMongo;
import com.ccm.api.model.base.vo.MenuTreeVO;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.AuthRole;
import com.ccm.api.model.user.AuthUser;
import com.ccm.api.model.user.Authority;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.Employee;
import com.ccm.api.model.user.EmployeeI18n;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.RoleChannel;
import com.ccm.api.model.user.RoleMenu;
import com.ccm.api.model.user.UserRoleChannel;
import com.ccm.api.model.user.criteria.B2BUserCriteria;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.model.user.vo.B2BUserVO;
import com.ccm.api.model.user.vo.UserRoleVO;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.user.AuthManager;
import com.ccm.api.service.user.RoleManager;
import com.ccm.api.util.AesEncrypt;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.ccm.mc.webapp.util.RequestUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * Action for facilitating User Management feature.
 * 
 * @param <E>
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 6776558938712115191L;

	private String roleMenuJson;
	
	private B2BUser b2bUser;

	private String userId;

	// 查询条件
	private B2BUserCriteria b2bUserCriteria;

//	private Map<String, List<UserRoleVO>> userRoleMap;

//	private List<UserRoleVO> userRoleList;
	
	private Set<String> choosenRolesSet;
	
	private List<Role> allRolesList;
	// 返回结果
	private B2BUserSearchResult b2bUserSearchResult;

	private String from;

	private String top_session;

	// 所有可授权的菜单
	private Map<String, List<MenuTreeVO>> allAuthMenuMap;

	// 已有权限
	private List<String> authedRoleMap = new ArrayList<String>();

	// 授权子菜单
	//private List<Menu> authSubMenus;

	// 权限范围
	private String companyId;

	//选中角色模板
	private String[] roleIds;
	// 选中的权限
	private String[] menuIds;
	
	@SuppressWarnings("rawtypes")
	private List hotels = new ArrayList<HotelVO>();// 管理员用户拥有的酒店
	private List<Channel> channels = new ArrayList<Channel>();;//管理员用户拥有的渠道

	@Resource
	private MenuDao menuDao;

	@Autowired
	private HotelManager hotelManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private DictCodeManager dictCodeManager;
	@Autowired
	private EmailManager emailManager;
	private List<DictCode> languageList;
	@Autowired
	private ChannelManager channelManager;

	//功能权限
	@Resource
	private AuthManager authManager;
	@Autowired
	private AuthRoleDaoMongo authRoleDao;
	private String arJson;
	private String[] authIds;
	//某角色已有的所有权限
	private List<String> authList;
	private AuthUser authUser=new AuthUser();
	private List<String> roleList;
	// 所有可授权的功能权限
	private List<Authority> userAuthority=new ArrayList<Authority>();
	// 所有可授权的功能权限
	private Map<String, List<Authority>> allAuthMap;
	// 所有可授权的功能权限
	private List<Authority> allAuthority;
		
	public void logout() throws IOException {
		SecurityContextHolder.clearContext();
		getResponse().sendRedirect("/main.do");
	}

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	public String list() {
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage() + "_" + locale.getCountry();
		String companyId = null;
		
		// 所有酒店管理员列表
		if (CompanyType.CHANNEL.equals(from)) {
					companyId = CompanyType.CHANNEL;
			}
		// 所有酒店管理员列表
		else if (CompanyType.HOTEL.equals(from)) {

			companyId = CompanyType.HOTEL;
		}
		// 运营人员列表，酒店雇员列表
		else {
			companyId = getCurLoginUser().getUserId();
		}

		b2bUser = this.getCurLoginUser();

		int pageSize = this.getCurrentPageSize("b2buser");
		int pageNo = this.getCurrentPageNo("b2buser");
		if (b2bUserCriteria == null) {
			b2bUserCriteria = new B2BUserCriteria();
		}
		b2bUserCriteria.setPageNum(pageNo);
		b2bUserCriteria.setPageSize(pageSize);
		b2bUserCriteria.setLanguage(language);
		
		if (StringUtils.hasText(companyId)) {

			// 获取当前登录用户下所有用户
			b2bUserCriteria.setCompanyId(companyId);

			b2bUserSearchResult = userManager.search(b2bUserCriteria);

			List<B2BUserVO> uList=b2bUserSearchResult.getResultList();
			for (B2BUserVO b2bUserVO : uList) {
				//标识是否为admin操作此用户
				b2bUserVO.setIsAdmin(isAdmin(b2bUser));
			}
			
			this.getRequest().setAttribute("resultSize",
					b2bUserSearchResult.getTotalCount());

		} else {

			// 如果没有companyid, 该用户没有创建用户，那么不要显示任何用户
			this.getRequest().setAttribute("resultSize", 0);
		}

		b2bUser = this.getCurLoginUser();

		return "list";
	}

	/**
	 * 解锁用户，只有admin账户有权限
	 * @return
	 */
	public String unlock(){
		if(null!=userId){
			userManager.unlockUser(userId);
		}
		return list();
	}
	
	public boolean isAdmin(B2BUser b2bUser){
		Boolean b=false;
		
		if(null!=b2bUser){
			Set<Role> roles=b2bUser.getRoles();
			for (Role role : roles) {
				if("1".equals(role.getRoleId())){
					b=true;
				}
			}
		}
	
		return b;
	}
	
	/**
	 * 编辑运营人员，酒店雇员,编辑酒店管理员
	 */
	public String edit() {
		//获取所有角色的权限
		allAuthMap=authManager.getAuthMap();
		//获取角色的功能权限json
		List<AuthRole> arList=authRoleDao.getAll();
		Map<String,List<String>> jsonARMap=new HashMap<String,List<String>>();
		for(AuthRole ar:arList){
			if(CommonUtil.isEmpty(jsonARMap.get(ar.getId()))){
				jsonARMap.put(ar.getId(), new ArrayList<String>());
			}
			jsonARMap.get(ar.getId()).addAll(ar.getAuthIds());
		}
		arJson=JSONObject.toJSON(jsonARMap).toString();
		
		//获取用户所有的权限和所有的角色
		if(userId != null || (!"".equals(userId))){
			authUser=authManager.getAuthUser(userId);
			if(authUser!=null){
				roleList=authUser.getRoleIds();
				authList=authManager.getUserAuth(userId);
				if(null!=authList){
					for(String auid:authList){
						Authority authr=authManager.get(auid);
						if(null!=authr){
							userAuthority.add(authr);
						}
					}
				}
				
			}
		}

		//获取所有权限
		List<Authority> auths=authManager.getAllAuth();
		if(null!=auths){
			allAuthority=auths;
		}
		
		// 床型列表
		boolean isNew = (userId == null || "".equals(userId));
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage() + "_" + locale.getCountry();

		// 所有多语言字典项
		languageList = dictCodeManager.searchByDictName(DictName.MORELANGUAGE,
				language);

		// 筛选不需要的语言
		String[] languageFilter = { language };

		// 将中文编码的多语言去除(默认的就是中国)
		for (int i = 0; i < languageList.size(); i++) {
			for (String lf : languageFilter) {
				if (lf.equalsIgnoreCase(languageList.get(i).getCodeNo())) {
					languageList.remove(i);
				}
			}
		}

		B2BUser tmpB2bUser = this.getCurLoginUser();
		allRolesList=roleManager.getAllShowedRoles();
		if (isNew) {

			b2bUser = new B2BUser();
			b2bUser.setIsLive(true);

		} else {
			b2bUser = userManager.getB2bUser(userId, language);

			List<EmployeeI18n> employeeI18nList = b2bUser.getEmployeeI18nList();
			if (employeeI18nList != null && employeeI18nList.size() > 0) {
				for (EmployeeI18n e : employeeI18nList) {
					if (language.equalsIgnoreCase(e.getLanguage())) {
						employeeI18nList.remove(e);
						break;
					}
				}
			}
			b2bUser.setEmployeeI18nList(employeeI18nList);
			List<String> choosenRolesList=roleManager.getRoleIdsByUserId(userId);
			choosenRolesSet=new HashSet<String>(choosenRolesList);
			
			//用户权限
			authedRoleMap= new ArrayList<String>();
			List<Menu> mList = menuDao.getCanAuthedMenus(userId, language);
			for (Menu m : mList) {
				authedRoleMap.add(m.getMenuId());
			}
		}

		//fetch menu from role
		List<RoleMenu> roleMenuList=menuDao.getMenuByShowRoles(language);
		Map<String,List<String>> jsonRoleMenuMap=new HashMap<String,List<String>>();
		for(RoleMenu rm:roleMenuList){
			if(CommonUtil.isEmpty(jsonRoleMenuMap.get(rm.getRoleId()))){
				jsonRoleMenuMap.put(rm.getRoleId(), new ArrayList<String>());
			}
			jsonRoleMenuMap.get(rm.getRoleId()).add(rm.getMenuId());
		}
		roleMenuJson=JSONObject.toJSON(jsonRoleMenuMap).toString();
		
//		allAuthMenuMap = roleManager.getAllAuthedMenu(userId,
//				language);
		allAuthMenuMap = roleManager.getAllMenu(language);
	
		// 查看用户分配的酒店列表
		if (CompanyType.HOTEL.equals(from)||CompanyType.CHANNEL.equals(from)) {
			hotels = hotelManager.getAllHotels(language);
		} else if (CompanyType.HOTEL.equals(tmpB2bUser.getCompanyId())||CompanyType.CHANNEL.equals(tmpB2bUser.getCompanyId())) {
			hotels = hotelManager.getHotelInfoChainByUserId(tmpB2bUser.getUserId(), language);
		}
		getUserHotels();
		return "edit";
	}

	public String showUserName() {

		B2BUser user = getCurLoginUser();
		if (user != null) {
			String companyId = null;

			// 所有酒店管理员列表
			if (CompanyType.HOTEL.equals(from)) {

				companyId = CompanyType.HOTEL;
			}
			// 运营人员列表，酒店雇员列表
			else {
				companyId = user.getUserId();
			}

			if (StringUtils.hasText(companyId)) {

				List<B2BUser> userList = userManager
						.getB2BUserByCompanyId(companyId);
				this.getRequest().setAttribute("desUserId", userId);
				this.getRequest().setAttribute("userList", userList);

			}
		}

		return "showUserName";

	}

	public String copyUserRole() {

		String desUserId = b2bUser.getUserId();
		if (!StringUtils.hasText(desUserId)) {
			String companyId = null;
			// 保存酒店管理员
			if(CompanyType.CHANNEL.equals(from)){
				companyId = CompanyType.CHANNEL;
			}
			else if (CompanyType.HOTEL.equals(from)) {
				companyId = CompanyType.HOTEL;
			}
			// 保存运营人员，酒店雇员
			else {
				companyId = getCurLoginUser().getUserId();
			}
			b2bUser.setCompanyId(companyId);
			b2bUser.setEmployee(new Employee());
			try {
				b2bUser = userManager.createUserInfo(b2bUser);
			} catch (Exception e) {
				writeMessage(false, e.getMessage());
				return null;
			}
			desUserId = b2bUser.getUserId();
		}

		if (!StringUtils.hasText(userId) || !StringUtils.hasText(desUserId)) {
			writeMessage(false, "userIdIsNull");
			return null;
		}

		if ("1".equals(desUserId)) {
			writeMessage(false, "noCopyToAdmin");
			return null;
		}
		if (userId.equals(desUserId)) {
			writeMessage(false, "org2desUserIdIsSame");
			return null;
		}
		userManager.copyUserRoleByUserId(userId, desUserId);
		writeMessage(true, desUserId);
		return null;
	}

	/**
	 * 保存用户
	 */
	public String save() throws Exception {

		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage() + "_" + locale.getCountry();
		String companyId = null;

		// 保存渠道
		if(CompanyType.CHANNEL.equals(from)){
			companyId = CompanyType.CHANNEL;
		}
		// 保存酒店管理员
		else if (CompanyType.HOTEL.equals(from)) {
			companyId = CompanyType.HOTEL;
		}
		/* 限制非运营用户创建运营权限*/
//		else if (CompanyType.PLAT_COMPANY_ID.equals(from)) {
//			companyId = CompanyType.PLAT_COMPANY_ID;
//		}
		// 保存运营人员，酒店雇员
		else {
			companyId = getCurLoginUser().getUserId();
		}

		String userI18ns = getRequest().getParameter("f_userI18ns");
		// 保存多语言列表
		List<EmployeeI18n> employeeI18nList = new ArrayList<EmployeeI18n>();
		try {
			employeeI18nList.add(userManager.getDefaultLanguageI18n(b2bUser,
					language));
			// 组装多语言数据
			if (org.apache.commons.lang.StringUtils.isNotBlank(userI18ns)) {
				List<Map<String, String>> i18nMapList = MoreLanguageUtil
						.rebuildI18nMapList(userI18ns);

				for (Map<String, String> i18nMap : i18nMapList) {
					// 创建多语言对象,并且设置值
					EmployeeI18n i18n = new EmployeeI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setTitle(i18nMap.get("title"));
					i18n.setDept(i18nMap.get("dept"));
					i18n.setName(i18nMap.get("name"));
					employeeI18nList.add(i18n);
				}
			}
			// 将多语言设置进去
			b2bUser.setEmployeeI18nList(employeeI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean isNew = (b2bUser.getUserId() == null || "".equals(b2bUser
				.getUserId()));

		if (isNew) {

			//验证密码的合法性：密码必须由数字，字母或者特殊字符中的至少两种组成
			String pass=b2bUser.getPassword();
			Pattern pN = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z#@!~%^&*]{7,20}$"); 
	        Matcher mN = pN.matcher(pass); 
	        boolean flgN = mN.matches(); 
	        if(!flgN){
	        	saveMessage(getText("login.Password.Reg"));
	        	return "edit";
	        }
	        
			b2bUser.setCompanyId(companyId);

			b2bUser = userManager.createUserInfo(b2bUser);

		} else {
			userManager.updateUserInfo(b2bUser);

		}

		List<UserRoleVO> userRoleList = new ArrayList<UserRoleVO>();

		// 所有权限
		UserRoleVO userRoleVO = null;
		if (roleIds != null) {
			for (int i = 0; i < roleIds.length; i++) {
				userRoleVO = new UserRoleVO();

				userRoleVO.setUserId(b2bUser.getUserId());
				userRoleVO.setRoleId(roleIds[i]);

				userRoleList.add(userRoleVO);
			}

		}
		
		if (menuIds != null) {
			for (int i = 0; i < menuIds.length; i++) {
				userRoleVO = new UserRoleVO();

				userRoleVO.setUserId(b2bUser.getUserId());
				userRoleVO.setRoleId(menuIds[i]);

				userRoleList.add(userRoleVO);
			}

		}
		
		// 保存用户分配酒店
		String[] hotelIds = this.getRequest().getParameterValues("hotelId");

		if (hotelIds != null) {
			for (int i = 0; i < hotelIds.length; i++) {
				String hotelId = hotelIds[i];
				userRoleVO = new UserRoleVO();
				userRoleVO.setUserId(b2bUser.getUserId());
				userRoleVO.setRoleId(null);
				userRoleVO.setHotelId(hotelId);
				userRoleList.add(userRoleVO);

			}
		}
		
		// 保存用户分配渠道
		List<RoleChannel> roleChannelList=roleManager.getRoleChannelByRoles(roleIds);
		List<UserRoleChannel> userRoleChannels = new ArrayList<UserRoleChannel>();
		/* save channel for user*/
		if(CommonUtil.isNotEmpty(roleChannelList)){
			for(RoleChannel rc:roleChannelList){
				UserRoleChannel urc = new UserRoleChannel();
				urc.setUserId(b2bUser.getUserId());
				urc.setRoleId(rc.getRoleId());
				urc.setChannelId(rc.getChannelId());
				userRoleChannels.add(urc);
			}
		}
		
		userManager.setRoleOfUserOfMc(b2bUser.getUserId(), userRoleList);
		userManager.setRoleOfUserOfMcChannel(b2bUser.getUserId(),null, userRoleChannels);
		
		//保存用户功能权限
		AuthUser au=new AuthUser();
		au.setId(b2bUser.getUserId());
		authList=new ArrayList<>();
		if(CommonUtil.isNotEmpty(authIds)){
			Collections.addAll(authList, authIds);
		}
		au.setAuthIds(authList);
		roleList=new ArrayList<>();
		Collections.addAll(roleList, roleIds);
		au.setRoleIds(roleList);
		authManager.saveAuthUser(au);
		
		return "saveSuccess";
	}

	/**
	 * 进入重置运营人员，酒店管理员，酒店雇员密码页面
	 * 
	 * @return
	 */
	public String editPwd() {
		if (userId != null) {
			b2bUser = userManager.getB2bUser(userId);
		}
		return "resetPwd";
	}

	/**
	 * 重置运营人员，酒店管理员，酒店雇员密码
	 * 
	 * @return
	 */
	public String resetPwd() {
		String pass = b2bUser.getPassword();

		// 验证密码的合法性：密码必须由数字，字母或者特殊字符中的至少两种组成
		Pattern pN = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z#@!~%^&*]{7,20}$");
		Matcher mN = pN.matcher(pass);
		boolean flgN = mN.matches();

		if (!flgN) {
			b2bUser = userManager.getB2bUser(b2bUser.getUserId());
			saveMessage(getText("login.Password.Reg"));
			return "resetPwd";
		} else {
			boolean bool = userManager.passwordAllowEidt(b2bUser.getUserId(), pass);
			if(!bool){
				saveMessage(getText("ccm.password.editPassword"));
				return "resetPwd";
			}
			userManager.resetPassword(b2bUser.getUserId(), pass);
			b2bUser = userManager.getB2bUser(b2bUser.getUserId());
			// saveMessage("密码重置成功。");
			saveMessage(getText("ccm.SupervisorOperator.Message.003"));
			return "resetPwdsuccess";
		}
	}

	/**
	 * 删除运营人员，酒店雇员,酒店管理员
	 */
	public String delete() {
		userManager.removeUser(userId);
		List<Object> args = new ArrayList<Object>();
		args.add(b2bUser.getUsername());
		System.out.println(getText("user.deleted", args));
		saveMessage(getText("user.deleted", args));

		// return "delete";
		return list();
	}

	/**
	 * 账号管理（设置）
	 * 
	 * @return
	 */
	public String manager() {
		b2bUser = getCurLoginUser();
		return "userManager";
	}

	/**
	 * 用户修改密码
	 * 
	 * @return
	 */
	public void changePwd() {
		String loginUserId = getCurLoginUser().getUserId();
		JSONObject json = new JSONObject();
		try {
			boolean bool = userManager.passwordAllowEidt(loginUserId, b2bUser.getPassword());
			if(!bool){
				json.put("code", "error");
				json.put("msg", getText("ccm.password.editPassword"));
			}else{
				userManager.changePassword(loginUserId,b2bUser.getConfirmPassword(), b2bUser.getPassword());
				json.put("code", "success");
			}
		} catch (Exception e) {
			json.put("code", "error");
			json.put("msg", e.getMessage() + getText("ccm.error.065"));
		}
		ajaxResponse(json.toJSONString());
	}

	/**
	 * 用户修改邮箱
	 * 
	 * @return
	 */
	public String changeEmail() {
		String loginUserId = getCurLoginUser().getUserId();
		userManager.changeEmail(loginUserId, b2bUser.getEmployee().getEmail());
		// 返回修改后的user对象
		b2bUser = userManager.getB2bUser(loginUserId);

		// 更新UserDetails
		B2BUser userDetils = getCurLoginUser();
		userDetils.getEmployee().setEmail(b2bUser.getEmployee().getEmail());

		return "userManager";
	}
	
	/**
	 * 忘记密码，通过用户名查找邮箱，给邮箱发送邮件连接去重置密码
	 */
	public void forgetPassword(){
		String username = getRequest().getParameter("username");
		B2BUserVO userVO = userManager.getUserByLoginName(username);
		JSONObject json = new JSONObject();
		if(userVO==null){
			json.put("code", "-1");
			json.put("msg",getText("ccm.user.notuser"));
			ajaxResponse(json.toJSONString());
		}else{
			if(!userVO.getIsLive()){
				json.put("code", "-2");
				json.put("msg", getText("ccm.user.AccountDeactive"));
				ajaxResponse(json.toJSONString());
			}else if(userVO.getIslocak()){
				json.put("code", "-3");
				json.put("msg", getText("ccm.user.message"));
				ajaxResponse(json.toJSONString());
			}else if(!StringUtils.hasLength(userVO.getEmail())){
				json.put("code", "-4");
				json.put("msg", getText("ccm.user.Pleasebindmailbox"));
				ajaxResponse(json.toJSONString());
			}else{
				json.put("code", "0");
				Date createDate = DateUtil.currentDateTime();
				String salt = DateUtil.getDateTime("yyyyMMddHHmmss", createDate);
				
				String key=AesEncrypt.encrypt(userVO.getUserId()+":"+userVO.getUsername()+":"+salt);
				
				String url = RequestUtil.getAppURL(getRequest())+"/user_toResstPwd.do?key="+key;
				String resuleCode = emailManager.sendForgetPasswordEmail(userVO.getEmail(), url, username);
				if(resuleCode.equalsIgnoreCase("1")){
					json.put("msg", getText("ccm.user.sentEmailMsg").replace("{0}", userVO.getEmail()));
				}else if(resuleCode.equalsIgnoreCase("4")){
					json.put("msg", getText("ccm.user.invalidemail")+":"+userVO.getEmail());
				}else{
					json.put("msg", getText("ccm.error.010"));
				}
				ajaxResponse(json.toJSONString());
			}
		}
	}
	
	/**
	 * 校验、前往重置密码
	 * @return
	 */
	public String toResstPwd(){
		String key = getRequest().getParameter("key");
		try {
			String keyEn=AesEncrypt.decrypt(key);
			//key 不存在或者解密异常
			if(!StringUtils.hasText(key)||keyEn.equalsIgnoreCase(key)){
				getRequest().setAttribute("messageInfo", getText("ccm.user.Illegalaccess"));
			}else{
				String userid=keyEn.split(":")[0];
				String username=keyEn.split(":")[1];
				String salt=keyEn.split(":")[2];
				Date date = DateUtil.convertStringToDate("yyyyMMddHHmmss", salt);
				if(DateUtil.addMinutes(date, 30).before(new Date())){
					getRequest().setAttribute("messageInfo", getText("ccm.user.link"));
				}else{
					getRequest().setAttribute("key", key);
					getRequest().setAttribute("userid", userid);
					getRequest().setAttribute("username", username);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			getRequest().setAttribute("messageInfo", getText("ccm.user.Illegalaccess"));
		}
		return "forgetPwd";
	}
	
	/**
	 * 忘记密码---重置密码
	 */
	public void resetForgetPwd(){
		JSONObject json = new JSONObject();
		try {
			String userid = getRequest().getParameter("userid");
			String password = getRequest().getParameter("password");
			String key = getRequest().getParameter("key");
			String keyEn=AesEncrypt.decrypt(key);
			if(!StringUtils.hasText(key)||keyEn.equalsIgnoreCase(key)){
				json.put("code", "error");
				json.put("msg", getText("ccm.user.Illegalaccess"));
			}else{
				String userid_key=keyEn.split(":")[0];
//				String username=keyEn.split(":")[1];
				String salt=keyEn.split(":")[2];
				Date date = DateUtil.convertStringToDate("yyyyMMddHHmmss", salt);
				if(DateUtil.addMinutes(date, 30).before(new Date())|| !userid_key.equalsIgnoreCase(userid)){
					json.put("code", "error");
					json.put("msg", getText("ccm.user.link"));
				}else{
					boolean bool = userManager.passwordAllowEidt(userid, password);
					if(!bool){
						json.put("code", "error");
						json.put("msg", getText("ccm.password.editPassword"));
					}else{
						userManager.resetForgetPassword(userid, password);
						json.put("code", "success");
					}
				}
			}
		} catch (Exception e) {
			json.put("code", "error");
			log.error(e.getMessage());
		}
		ajaxResponse(json.toJSONString());
	}
	
	
	/**
	 * 获取用户已分配的渠道
	 */
	private void getUserChannels() {
		List<String> userChannelIds = userManager.getChannelIdListByUserId(userId);
		if (userChannelIds != null && !userChannelIds.isEmpty()) {
			Map<String, Integer> uhMap = new HashMap<String, Integer>();
			for (int i = 0; i < userChannelIds.size(); i++) {
				uhMap.put(userChannelIds.get(i), i);
			}
			getRequest().setAttribute("userChannels", uhMap);
		}
	}

	/************* 以上是整理过的 *****************/

	/**
	 * Default: just returns "success"
	 */
	public String execute() {
		return SUCCESS;
	}

	public B2BUserCriteria getB2bUserCriteria() {
		return b2bUserCriteria;
	}

	public void setB2bUserCriteria(B2BUserCriteria b2bUserCriteria) {
		this.b2bUserCriteria = b2bUserCriteria;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public B2BUser getB2bUser() {
		return b2bUser;
	}

	public void setB2bUser(B2BUser b2bUser) {
		this.b2bUser = b2bUser;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getAuthedRoleMap() {
		return authedRoleMap;
	}

	public void setAuthedRoleMap(List<String> authedRoleMap) {
		this.authedRoleMap = authedRoleMap;
	}

	public RoleManager getRoleManager() {
		return roleManager;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public B2BUserSearchResult getB2bUserSearchResult() {
		return b2bUserSearchResult;
	}

	public void setB2bUserSearchResult(B2BUserSearchResult b2bUserSearchResult) {
		this.b2bUserSearchResult = b2bUserSearchResult;
	}

	public void setTop_session(String top_session) {
		this.top_session = top_session;
	}

	public String getTop_session() {
		return top_session;
	}

	@SuppressWarnings("unchecked")
	public List<HotelVO> getHotels() {
		return hotels;
	}

	public void setHotels(List<HotelVO> hotels) {
		this.hotels = hotels;
	}

	/**
	 * 获取用户已分配的酒店
	 */
	private void getUserHotels() {
		List<String> userHotelIds = userManager.getHotelIdListByUserId(userId);
		if (userHotelIds != null && !userHotelIds.isEmpty()) {
			Map<String, Integer> uhMap = new HashMap<String, Integer>();
			for (int i = 0; i < userHotelIds.size(); i++) {
				uhMap.put(userHotelIds.get(i), i);
			}
			getRequest().setAttribute("userHotels", uhMap);
		}
	}

	public List<DictCode> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<DictCode> languageList) {
		this.languageList = languageList;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public List<Role> getAllRolesList() {
		return allRolesList;
	}

	public void setAllRolesList(List<Role> allRolesList) {
		this.allRolesList = allRolesList;
	}

	public Set<String> getChoosenRolesSet() {
		return choosenRolesSet;
	}

	public void setChoosenRolesSet(Set<String> choosenRolesSet) {
		this.choosenRolesSet = choosenRolesSet;
	}

	public String getRoleMenuJson() {
		return roleMenuJson;
	}

	public void setRoleMenuJson(String roleMenuJson) {
		this.roleMenuJson = roleMenuJson;
	}

	public Map<String, List<MenuTreeVO>> getAllAuthMenuMap() {
		return allAuthMenuMap;
	}

	public void setAllAuthMenuMap(Map<String, List<MenuTreeVO>> allAuthMenuMap) {
		this.allAuthMenuMap = allAuthMenuMap;
	}

	public String[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}

	public String getArJson() {
		return arJson;
	}

	public void setArJson(String arJson) {
		this.arJson = arJson;
	}

	public AuthUser getAuthUser() {
		return authUser;
	}

	public void setAuthUser(AuthUser authUser) {
		this.authUser = authUser;
	}

	public String[] getAuthIds() {
		return authIds;
	}

	public void setAuthIds(String[] authIds) {
		this.authIds = authIds;
	}

	public List<String> getAuthList() {
		return authList;
	}

	public void setAuthList(List<String> authList) {
		this.authList = authList;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public Map<String, List<Authority>> getAllAuthMap() {
		return allAuthMap;
	}

	public void setAllAuthMap(Map<String, List<Authority>> allAuthMap) {
		this.allAuthMap = allAuthMap;
	}

	public List<Authority> getUserAuthority() {
		return userAuthority;
	}

	public void setUserAuthority(List<Authority> userAuthority) {
		this.userAuthority = userAuthority;
	}

	public List<Authority> getAllAuthority() {
		return allAuthority;
	}

	public void setAllAuthority(List<Authority> allAuthority) {
		this.allAuthority = allAuthority;
	}

}
