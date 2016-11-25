/**
 * 
 */
package com.ccm.mc.webapp.filter;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.ccm.api.Constants;
import com.ccm.api.dao.user.UserDao;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.user.UserManager;
import com.ccm.api.util.AesEncrypt;

/**
 * @author Jenny
 * 
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public static final String VALIDATE_CODE = "validateCode";
	public static final String USERNAME = "j_username";
	public static final String PASSWORD = "j_password";
	public static final String USERTYPE = "userType";
	public static final String HOTELID = "hotelId";
	public static final String INPUTCODE = "inputCode";
	
	private UserDao userDao;

	private UserManager userManager;
	private MessageSource messageSource;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		//判断验证码
		this.checkValidateCode(request);

		// String hotelId = "";
		// if (userType.equals("1")) {
		// hotelId = obtainHotelId(request);
		// if (!StringUtils.hasText(hotelId)) {
		// throw new AuthenticationServiceException("请选择酒店！");
		// }
		// request.getSession().setAttribute("hotelId", hotelId);
		// }

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		// 验证用户账号与密码是否对应
		username = username.trim();

		B2BUser user = this.userDao.getUserByUsername(username);

		if (user == null) {
			/*
			 * 在我们配置的simpleUrlAuthenticationFailureHandler处理登录失败的处理类在这么一段
			 * 这样我们可以在登录失败后，向用户提供相应的信息。 if (forwardToDestination) {
			 * request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			 * exception); } else { HttpSession session =
			 * request.getSession(false);
			 * 
			 * if (session != null || allowSessionCreation) {
			 * request.getSession(
			 * ).setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			 * exception); } }
			 */
			throw new AuthenticationServiceException("用户名或者密码错误！");
		}else if(user!=null && (user.getIsLive()==null||!user.getIsLive())){
			throw new AuthenticationServiceException("账户未激活！");
		}
		if(user!=null){
			boolean b = userManager.isLock(user.getUserId());
			if(b){
				throw new AuthenticationServiceException("用户被锁定");
			}
			int number = userManager.inputFailPasswordNumber(user, password);
			if(number!=Constants.ALLOW_PASSWORD_FAIL_NUMBER){
				HttpSession session = request.getSession(false);
				Locale l = (Locale) session.getAttribute(Constants.PREFERRED_LOCALE_KEY);
				String value = messageSource.getMessage("ccm.user.message1", new Object[] {number}, l);
				throw new AuthenticationServiceException(value);
			}
		}

		// UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		// Place the last username attempted into HttpSession for views

		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * 检测验证码
	 * 
	 * @param request
	 */
	protected void checkValidateCode(HttpServletRequest request) {
//		try {
//			String uuid = request.getParameter("uuid");
//			HttpSession session = request.getSession();
//			String key = (String) session.getAttribute("loginKey");;
//			String code = (String) session.getAttribute("loginUniqueCode");
//			String loginUniqueCodeType = (String) session.getAttribute("loginUniqueCodeType");
//			String encryptCode = AesEncrypt.encrypt(uuid, key);
//			//session 清空session 中的唯一码和key
//			session.removeAttribute("loginUniqueCodeType");
//			session.removeAttribute("loginKey");
//			session.removeAttribute("loginUniqueCode");
//			if(!"ok".equalsIgnoreCase(loginUniqueCodeType)||!encryptCode.equalsIgnoreCase(code)){
//				throw new AuthenticationServiceException("请从正确的页面访问！");
//			}
//		} catch (Exception e) {
//			throw new AuthenticationServiceException("请从正确的页面访问！");
//		}
		HttpSession session = request.getSession();
		String inputCode = request.getParameter(INPUTCODE);
		Object obj = session.getAttribute("randomImg");
    	if(obj!=null){
    		String userName = obtainUsername(request);
    		String code = obj.toString();
    		if(!code.equalsIgnoreCase(inputCode)){
    			throw new AuthenticationServiceException("验证码有误！");
    		}
    	}else{
    		throw new AuthenticationServiceException("请从正确的页面访问！");
    	}
    	session.removeAttribute("randomImg");
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainUserType(HttpServletRequest request) {
		Object obj = request.getParameter(USERTYPE);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainHotelId(HttpServletRequest request) {
		Object obj = request.getParameter(HOTELID);
		return null == obj ? "" : obj.toString();
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}
