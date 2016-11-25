<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices"%>
<%@ page import="javax.servlet.http.Cookie"%>

<%
	if (request.getSession(false) != null) {
		session.invalidate();
	}
	Cookie terminate = new Cookie(TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);
	String contextPath = request.getContextPath();
	terminate.setPath(contextPath != null && contextPath.length() > 0 ? contextPath : "/");
	terminate.setMaxAge(0);
	response.addCookie(terminate);
	
	Cookie jse = new Cookie("JSESSIONID", null);
	jse.setPath(contextPath != null && contextPath.length() > 0 ? contextPath : "/");
	jse.setMaxAge(0);
	response.addCookie(jse);
	
%>

<c:redirect url="/user_logout.do" />