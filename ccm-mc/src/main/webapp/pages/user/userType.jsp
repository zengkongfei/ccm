<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<s:if test="from==2">
	<s:set var="prefix" value="'hotelAdmin'"></s:set>
</s:if>
<s:elseif test="from==4">
	<s:set var="prefix" value="'channelAdmin'"></s:set>
</s:elseif>
<s:elseif test="#attr.SPRING_SECURITY_CONTEXT.authentication.principal.companyId==2">
	<s:set var="prefix" value="'hotelUser'"></s:set>
</s:elseif>
<s:else>
	<s:set var="prefix" value="'user'"></s:set>
</s:else>