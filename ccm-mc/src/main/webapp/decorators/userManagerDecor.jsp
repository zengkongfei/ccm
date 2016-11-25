<%@page import="org.springframework.util.StringUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!doctype html>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!-- Consider adding an manifest.appcache: h5bp.com/d/Offline -->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><fmt:message key="title.ChinaonlineChannelManagement" /><decorator:title />
</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width,initial-scale=1">
<jsp:include page="common_css_js.jsp"></jsp:include>
<decorator:head />
</head>
<body class="nobg" <decorator:getProperty property="body.style" writeEntireProperty="true"/>>
	<jsp:include page="/common/menu.jsp"></jsp:include>
	<!-- 内容区域 start-->
	<decorator:body />
	<!-- 内容区域 end-->
	<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>