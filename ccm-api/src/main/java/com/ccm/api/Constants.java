package com.ccm.api;

/**
 * Constant values used throughout the application.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public final class Constants {

	private Constants() {
		// hide me
	}

	// ~ Static fields/initializers
	// =============================================
	// int类型的值在一些版本JDK下会生成int PAGE_SIZE = null的错误 jsp类文件，以致页面打开编译报错
	// public static final int PAGE_SIZE = 20;
	public static final Integer PAGE_SIZE = 20;

	/**
	 * The name of the ResourceBundle used in this application
	 */
	public static final String BUNDLE_KEY = "ApplicationResources";

	/**
	 * File separator from System properties
	 */
	public static final String FILE_SEP = System.getProperty("file.separator");

	/**
	 * User home from System properties
	 */
	public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;

	/**
	 * The name of the configuration hashmap stored in application scope.
	 */
	public static final String CONFIG = "appConfig";

	/**
	 * Session scope attribute that holds the locale set by the user. By setting
	 * this key to the same one that Struts uses, we get synchronization in
	 * Struts w/o having to do extra work or have two session-level variables.
	 */
	public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

	/**
	 * The request scope attribute under which an editable user form is stored
	 */
	public static final String USER_KEY = "userForm";

	/**
	 * The request scope attribute that holds the user list
	 */
	public static final String USER_LIST = "userList";

	/**
	 * The request scope attribute for indicating a newly-registered user
	 */
	public static final String REGISTERED = "registered";

	/**
	 * The name of the Administrator role, as specified in web.xml
	 */
	public static final String ADMIN_ROLE = "ROLE_ADMIN";

	public static final String ADMIN_OPER = "ROLE_ADMIN_OPER";

	public static final String COADMIN_ROLE = "ROLE_COADMIN";

	public static final String ROLE_COADMIN_HOTEL = "30";// 酒店管理员角色ID

	/**
	 * The name of the User role, as specified in web.xml
	 */
	public static final String USER_ROLE = "ROLE_USER";

	/**
	 * The name of the user's role list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String USER_ROLES = "userRoles";

	/**
	 * The name of the available roles list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String AVAILABLE_ROLES = "availableRoles";

	/**
	 * The name of the CSS Theme setting.
	 */
	public static final String CSS_THEME = "csstheme";

	public static final String COUPONDEFID = "1";

	public static final String REABTE_EXPIREDATE = "EXPIREDATE";

	public static final Integer expiry = 60 * 60 * 24;// 1 days

	public static final Integer expiry_max = Integer.MAX_VALUE;
	
	/**
	 * 允许密码输入错误最大次数，到达这个数，用户锁定
	 */
	public static final Integer ALLOW_PASSWORD_FAIL_NUMBER=6;
}
