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
	<s:set var="lefMenu" value="#session['SubMenu'+#attr.SPRING_SECURITY_CONTEXT.authentication.principal.userId+'ParentId'+#session.menuId+#request['com.opensymphony.xwork2.ActionContext.locale']]"></s:set>
	<s:if test='#lefMenu!=null && #lefMenu.size()>0 && (#parameters.tmenuId==null || (#parameters.tmenuId[0]!="813" && #parameters.tmenuId[0]!="8120"))'>
		<div class="CCMmainConter w1200">
			<div class="ccm_2wp clearfix">
				<div class="ccm_left">
					<div class="lt_menu ccm_left_main">
						<div class="title">
							<s:property value="#session['menuName'+#request['com.opensymphony.xwork2.ActionContext.locale']+#attr.SPRING_SECURITY_CONTEXT.authentication.principal.userId+'ParentId'+#session.menuId]" />
						</div>
						<ul class="mlist n_overFlowY">
							<%
								String tmenuId = request.getParameter("tmenuId");
									if (StringUtils.hasText(tmenuId)) {
										session.setAttribute("tmenuId", tmenuId);
									}
							%>
							<s:iterator value="#lefMenu">
								<s:if test="menuId==92">
									<li><a href="<s:property value="url"/>?tmenuId=<s:property value="menuId"/>&from=2" <s:if test="menuId==#session.tmenuId">class="selected"</s:if> title="<s:property value="displayName"/>"><span><s:property value="displayName" /> </span> </a></li>
								</s:if>
								<s:elseif test="menuId==901">
									<li><a href="<s:property value="url"/>?tmenuId=<s:property value="menuId"/>&from=4" <s:if test="menuId==#session.tmenuId">class="selected"</s:if> title="<s:property value="displayName"/>"><span><s:property value="displayName" /> </span> </a></li>
								</s:elseif>
								<s:elseif test="menuId==91">
									<li><a href="<s:property value="url"/>?tmenuId=<s:property value="menuId"/>&from=1" <s:if test="menuId==#session.tmenuId">class="selected"</s:if> title="<s:property value="displayName"/>"><span><s:property value="displayName" /> </span> </a></li>
								</s:elseif>
								<s:else>
									<li><a href="<s:property value="url"/>?tmenuId=<s:property value="menuId"/>" <s:if test="menuId==#session.tmenuId">class="selected"</s:if> title="<s:property value="displayName"/>"><span><s:property value="displayName" /> </span> </a></li>
								</s:else>
							</s:iterator>
						</ul>
					</div>
				</div>
				<div class="ccm_2col">
					<div class="ccm_right" id="right">
						<!-- 内容区域 start-->
						<decorator:body />
						<!-- 内容区域 end-->
					</div>
				</div>
			</div>
		</div>
	</s:if>
	<s:else>
		<!-- 内容区域 start-->
		<decorator:body />
		<!-- 内容区域 end-->
	</s:else>
	<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>