package com.ccm.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ccm.api.model.user.B2BUser;

public class SecurityHolder {

	public static B2BUser b2bUser = new B2BUser();

	public static B2BUser getB2BUser() {
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication auth = sc.getAuthentication();
		if (null == auth) {
			HttpSession session = getSession();
			if (session == null) {
				return null;
			}
			sc = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
			if(sc != null){
			    auth = sc.getAuthentication();
			}
		}
		if (auth != null && auth.getPrincipal() instanceof B2BUser) {
			return (B2BUser) auth.getPrincipal();
		} else {
			return null;
		}
	}

	public static String getUserId() {
		Object o = getB2BUser();
		if (o != null) {
			return ((B2BUser) o).getUserId();
		}
		return null;
	}

	public static String getUserName() {
		Object o = getB2BUser();
		if (o != null) {
			return ((B2BUser) o).getUsername();
		}
		return null;
	}

	public static HttpSession getSession() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		if (ra != null) {
			HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
			return request.getSession();
		}
		return null;
	}

}
