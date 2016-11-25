package com.ccm.mc.webapp.action.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.ccm.api.dao.common.MenuDao;
import com.ccm.api.model.base.vo.MenuTreeVO;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.DictName;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.Employee;
import com.ccm.api.model.user.EmployeeI18n;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.UserRoleChannel;
import com.ccm.api.model.user.criteria.B2BUserCriteria;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.model.user.vo.B2BUserVO;
import com.ccm.api.model.user.vo.UserRoleVO;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.common.DictCodeManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.user.RoleManager;
import com.ccm.api.util.MoreLanguageUtil;
import com.ccm.mc.webapp.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 渠道用户
 * @author Administrator
 *
 */
public class ChannelUserAction  extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5693783953535774768L;

	
	private B2BUser b2bUser;

	private String userId;

	// 查询条件
	private B2BUserCriteria b2bUserCriteria;

	private Map<String, List<UserRoleVO>> userRoleMap;

	private List<UserRoleVO> userRoleList;

	// 返回结果
	private B2BUserSearchResult b2bUserSearchResult;

	private String from;

	private String top_session;

	// 所有可授权的菜单
	private Map<String, List<MenuTreeVO>> allAuthMenuMap;

	// 已有权限

	private List<String> authedRoleMap = new ArrayList<String>();

	// 授权子菜单
	private List<Menu> authSubMenus;

	// 权限范围
	private String companyId;

	// 选中的权限
	private String[] roleIds;

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
	private List<DictCode> languageList;
	@Autowired
	private ChannelManager channelManager;
	
	
	/**
	 * 用户列表
	 * 
	 * @return
	 */
	public String list() {
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		String companyId = null;

		// 所有酒店管理员列表
		if (CompanyType.CHANNEL.equals(from)) {
			companyId = CompanyType.CHANNEL;
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
			
			this.getRequest().setAttribute("resultSize", b2bUserSearchResult.getTotalCount());

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
		boolean isNew = (userId == null || "".equals(userId));
		Locale locale = ActionContext.getContext().getLocale();
		String language = locale.getLanguage()+"_"+locale.getCountry();
		
		//所有多语言字典项
		languageList = dictCodeManager.searchByDictName(DictName.MORELANGUAGE,language);

		//筛选不需要的语言
		String[] languageFilter = {language};
		
		//将中文编码的多语言去除(默认的就是中国)
		for (int i = 0; i < languageList.size(); i++) {
			for (String lf : languageFilter) {
				if(lf.equalsIgnoreCase(languageList.get(i).getCodeNo())){
					languageList.remove(i);
				}
			}
		}
		
		B2BUser tmpB2bUser = this.getCurLoginUser();

		if (isNew) {
			b2bUser = new B2BUser();
			b2bUser.setIsLive(true);
		} else {
			b2bUser = userManager.getB2bUser(userId,language);
			
			List<EmployeeI18n> employeeI18nList = b2bUser.getEmployeeI18nList();
			if(employeeI18nList!=null&&employeeI18nList.size()>0){
				for(EmployeeI18n e:employeeI18nList){
					if(language.equalsIgnoreCase(e.getLanguage())){
						employeeI18nList.remove(e);
						break;
					}
				}
			}
			b2bUser.setEmployeeI18nList(employeeI18nList);

			List<Menu> mList = menuDao.getCanAuthedMenus(userId,language);
			for (Menu m : mList) {
				authedRoleMap.add(m.getMenuId());
			}
		}

		allAuthMenuMap = roleManager.getAllAuthedMenu(tmpB2bUser.getUserId(),language);

		// 查看用户分配的酒店列表
		if (CompanyType.CHANNEL.equals(from)) {
			hotels = hotelManager.getAllHotels(language);
			channels = channelManager.getAllChannel();
			getUserHotels();
			getUserChannels();
		} else if (CompanyType.CHANNEL.equals(tmpB2bUser.getCompanyId())) {
			hotels = hotelManager.getHotelInfoChainByUserId(tmpB2bUser.getUserId(),language);
			channels = channelManager.getChannelInfoChainByUserId(tmpB2bUser.getUserId());
			getUserHotels();
			getUserChannels();
		}
		
		return "edit";
	}

	public String copyUserRole() {

		String desUserId = b2bUser.getUserId();
		if (!StringUtils.hasText(desUserId)) {
			String companyId = null;
			// 保存酒店管理员
			if (CompanyType.CHANNEL.equals(from)) {
				companyId = CompanyType.CHANNEL;
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
		String language = locale.getLanguage()+"_"+locale.getCountry();
		String companyId = null;

		// 保存酒店管理员
		if (CompanyType.CHANNEL.equals(from)) {
			companyId = CompanyType.CHANNEL;
		}
		// 保存运营人员，酒店雇员
		else {
			companyId = getCurLoginUser().getUserId();
		}

		String userI18ns = getRequest().getParameter("f_userI18ns");
		//保存多语言列表
		List<EmployeeI18n> employeeI18nList = new ArrayList<EmployeeI18n>();
		try {
			employeeI18nList.add(userManager.getDefaultLanguageI18n(b2bUser,language));
			//组装多语言数据
			if(org.apache.commons.lang.StringUtils.isNotBlank(userI18ns)){
				List<Map<String, String>> i18nMapList = MoreLanguageUtil.rebuildI18nMapList(userI18ns);
				
				for (Map<String, String> i18nMap : i18nMapList) {
					//创建多语言对象,并且设置值
					EmployeeI18n i18n = new EmployeeI18n();
					i18n.setLanguage(i18nMap.get("codeNo"));
					i18n.setTitle(i18nMap.get("title"));
					i18n.setDept(i18nMap.get("dept"));
					i18n.setName(i18nMap.get("name"));
					employeeI18nList.add(i18n);
				}
			}
			//将多语言设置进去
			b2bUser.setEmployeeI18nList(employeeI18nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean isNew = (b2bUser.getUserId() == null || "".equals(b2bUser.getUserId()));
		
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
		List<UserRoleChannel> userRoleChannels = new ArrayList<UserRoleChannel>();

		// 所有权限
		UserRoleVO userRoleVO = null;
		if (roleIds != null) {
			for (int i = 0; i < roleIds.length; i++) {
				userRoleVO = new UserRoleVO();
				userRoleVO.setUserId(b2bUser.getUserId());
				userRoleVO.setRoleId(roleIds[i]);

				userRoleList.add(userRoleVO);
				
				UserRoleChannel urc = new UserRoleChannel();
				urc.setUserId(b2bUser.getUserId());
				urc.setRoleId(roleIds[i]);
				
				userRoleChannels.add(urc);
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
		String[] channelIds = this.getRequest().getParameterValues("channelId");
		//渠道权限 单选
		if ((channelIds != null) && ( channelIds.length==1)) {
			for (int i = 0; i < channelIds.length; i++) {
				String channelId = channelIds[i];
				UserRoleChannel urc = new UserRoleChannel();
				urc.setUserId(b2bUser.getUserId());
				urc.setRoleId(null);
				urc.setChannelId(channelId);
				userRoleChannels.add(urc);
			}
		}

		userManager.setRoleOfUserOfMc(b2bUser.getUserId(), userRoleList);
		userManager.setRoleOfUserOfMcChannel(b2bUser.getUserId(), null,userRoleChannels);

		return "saveSuccess";
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

		return list();
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
		
		String pass=b2bUser.getPassword();
		
		//验证密码的合法性：密码必须由数字，字母或者特殊字符中的至少两种组成
		Pattern pN = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z#@!~%^&*]{7,20}$"); 
        Matcher mN = pN.matcher(pass); 
        boolean flgN = mN.matches(); 

		if(!flgN){
			b2bUser = userManager.getB2bUser(b2bUser.getUserId());
			saveMessage(getText("login.Password.Reg"));
			return "resetPwd";
		}else{
			boolean bool = userManager.passwordAllowEidt(b2bUser.getUserId(), pass);
			if(!bool){
				saveMessage(getText("ccm.password.editPassword"));
				return "resetPwd";
			}
			userManager.resetPassword(b2bUser.getUserId(), pass);
			b2bUser = userManager.getB2bUser(b2bUser.getUserId());
			//saveMessage("密码重置成功。");
			saveMessage(getText("ccm.SupervisorOperator.Message.003"));
			return "resetPwdsuccess";
		}
		
	}
	
	public String showUserName() {

		B2BUser user = getCurLoginUser();
		if (user != null) {
			String companyId = null;

			// 保存渠道用户
			if (CompanyType.CHANNEL.equals(from)) {
				companyId = CompanyType.CHANNEL;
			}
			// 保存运营人员，酒店雇员
			else {
				companyId = getCurLoginUser().getUserId();
			}

			if (StringUtils.hasText(companyId)) {

				List<B2BUser> userList = userManager.getB2BUserByCompanyId(companyId);
				this.getRequest().setAttribute("desUserId", userId);
				this.getRequest().setAttribute("userList", userList);

			}
		}

		return "showUserName";

	}
	public void logout() throws IOException{
		SecurityContextHolder.clearContext();
		getResponse().sendRedirect("/main.do");
	}
//	/**
//	 * 账号管理（设置）
//	 * 
//	 * @return
//	 */
//	public String manager() {
//		b2bUser = getCurLoginUser();
//		return "userManager";
//	}
//
//	/**
//	 * 用户修改密码
//	 * 
//	 * @return
//	 */
//	public String changePwd() {
//		String loginUserId = getCurLoginUser().getUserId();
//		try {
//			boolean bool = userManager.passwordAllowEidt(b2bUser.getUserId(), b2bUser.getPassword());
//			if(!bool){
//				saveMessage(getText("ccm.password.editPassword"));
//			}else{
//				userManager.changePassword(loginUserId, b2bUser.getConfirmPassword(), b2bUser.getPassword());
//				saveMessage("密码修改成功。");
//			}
//		} catch (Exception e) {
//			saveMessage(e.getMessage() + "密码修改失败。");
//		}
//		// 返回修改后的user对象
//		b2bUser = userManager.getB2bUser(loginUserId);
//		return "userManager";
//	}
//
//	/**
//	 * 用户修改邮箱
//	 * 
//	 * @return
//	 */
//	public String changeEmail() {
//		String loginUserId = getCurLoginUser().getUserId();
//		userManager.changeEmail(loginUserId, b2bUser.getEmployee().getEmail());
//		// 返回修改后的user对象
//		b2bUser = userManager.getB2bUser(loginUserId);
//
//		// 更新UserDetails
//		B2BUser userDetils = getCurLoginUser();
//		userDetils.getEmployee().setEmail(b2bUser.getEmployee().getEmail());
//
//		return "userManager";
//	}

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

	public Map<String, List<UserRoleVO>> getUserRoleMap() {
		return userRoleMap;
	}

	public void setUserRoleMap(Map<String, List<UserRoleVO>> userRoleMap) {
		this.userRoleMap = userRoleMap;
	}

	public List<UserRoleVO> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRoleVO> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Map<String, List<MenuTreeVO>> getAllAuthMenuMap() {
		return allAuthMenuMap;
	}

	public void setAllAuthMenuMap(Map<String, List<MenuTreeVO>> allAuthMenuMap) {
		this.allAuthMenuMap = allAuthMenuMap;
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

	public List<Menu> getAuthSubMenus() {
		return authSubMenus;
	}

	public void setAuthSubMenus(List<Menu> authSubMenus) {
		this.authSubMenus = authSubMenus;
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
	
}
