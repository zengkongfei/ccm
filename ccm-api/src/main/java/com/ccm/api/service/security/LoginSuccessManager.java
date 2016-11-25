/**
 * 
 */
package com.ccm.api.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.ccm.api.SecurityHolder;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.user.UserManager;

/**
 * @author Jenny
 * 
 */
public class LoginSuccessManager extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private UserManager userManager;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		B2BUser user = SecurityHolder.getB2BUser();

		try {
			// 更新最后登录时间,sessionkey
//			B2BUser pUser = userManager.get(user.getCompanyId());
//			if (pUser != null) {
//
//				if (user.getSessionKey() != null && !user.getSessionKey().equals(pUser.getSessionKey())) {
//					user.setSessionKey(pUser.getSessionKey());
//					user.setLastLoginTime(DateUtil.currentDateTime());
//					user.setInitStatus(pUser.getInitStatus());
//					userManager.updateUserSessionKey(user);
//				}
//			}
			userManager.unlockUser(user.getUserId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

//		response.sendRedirect("/");
		super.onAuthenticationSuccess(request, response, authentication);

	}
}
