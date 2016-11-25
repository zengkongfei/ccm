<%@page trimDirectiveWhitespaces="true"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.appfuse.org/tags/struts" prefix="appfuse" %>
<%@ taglib uri="http://ccm.com/tags/functions" prefix="ccm_fn" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="global_js_revision" value='<%=com.ccm.api.AppConfig.getString("ccm.mc.buildtime")%>'/>
<c:set var="global_js_revision" value="?r=${global_js_revision}"/>
<c:set var="datePattern"><fmt:message key="date.format"/></c:set>
<fmt:setLocale value="${sessionScope['org.apache.struts2.action.LOCALE']}" />  
<fmt:setBundle basename="i18n/Applicationi18nResources" /> 
