<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.appfuse.org/tags/struts" prefix="appfuse"%>
<%@ taglib uri="http://ccm.com/tags/functions" prefix="ccm_fn"%>

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ page language="java" isErrorPage="true"%>
<%@ page import="com.ccm.api.common.exception.BizException"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="global_js_revision" value='<%=com.ccm.api.AppConfig.getString("ccm.mc.buildtime")%>' />
<c:set var="global_js_revision" value="?r=${global_js_revision}" />
<c:set var="datePattern">
	<fmt:message key="date.format" />
</c:set>

<head>
<title><fmt:message key="errorPage.title" />
</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.7.1.min.js'/>"></script>
</head>

<body class="nobg">
	<div id="container">
		<div id="main" role="main">
			<div id="content" class="clearfix">
				<%
					if (exception != null && exception instanceof BizException) {
						BizException bizException = (BizException) exception;
				%>
				<div class="mainconent">
					<div id="HaveToDo" style="background: #fff; width: 500px;">
						<div class="mrd24 clearfix">
							<p class="font18 yahei" style="line-height: 30px; word-break: break-all;"><%=bizException.getErrMsg()%></p>
							<div class="blancket50"></div>
							<a id="HistoryBack" class="surebt" href="javascript:refb()"><span>返回</span> </a>
						</div>
					</div>

				</div>
				<%
					} else {
				%>
				<div class="mainconent">
					<div id="HaveToDo" style="background: #fff; ">
						<div class="mrd24 clearfix" align="center">
							<h1>
								<!-- <fmt:message key="errorPage.heading" />-->
							</h1>
							<p class="font18 yahei" style="line-height: 30px; word-break: break-all;">
								<br/>
								<%
									if (exception != null) {
								%>
								<!--  
								<%
									exception.printStackTrace(new java.io.PrintWriter(out));
								%>-->
								 提示信息
								<div class="blancket50"></div>
								抱歉，您访问的地址有误或会话超时，请重新登录或联系管理员！
								<div class="blancket50"></div>
								<!--  <form name="loading">
							         <font color="gray">系统将自动跳转到首页，请稍候……</font><br/><br/>
							         <input type="text" name="chart" size="47" style="font-family:Arial;font-weight:bolder;color:gray;background-color:white;padding:0px;border-style:none;">
							         <br/>
							         <input type="text" name="percent" size="47" style="font-family:Arial;color:gray;text-align:center;border-width:medium;border-style:none;margin-top:5px;">
							         <script language="javascript">
								         var bar=0
								         var line="||"
								         var amount="||"
								         count()
								         function count(){
								         bar=bar+2
								         amount=amount+line
								         document.loading.chart.value=amount
								         document.loading.percent.value=bar+"%"
								         if (bar<99){
								         	setTimeout("count()",90);}
								         else{
								         	//window.location = "/";}
								         }
							         </script>
								</form>-->
							<%
								} else if (request.getAttribute("errMsg") != null) {
							%>
									<c:out value="${errMsg }"></c:out>
							<%
								}
							%>
							</p>
							<div class="blancket50"></div>
							<a id="HistoryBack" class="surebt" href="/" style="text-decoration:none;color: blue;"><span>返回首页</span> </a>&nbsp;
							<a id="HistoryBack" class="surebt" href="javascript:history.back()" style="text-decoration:none;"><span>返回上一页</span> </a>&nbsp;
							<a id="HistoryBack" class="surebt" href="/logout.jsp" style="text-decoration:none;color: blue;"><span>重新登录</span> </a>
						</div>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
		<script type="text/javascript">
			jQuery(document).ready(function() {
				var wHeight = $(window).height(), wWidth = $(window).width(), wpHeight = $('#HaveToDo').height();
				var wpLeft = (wWidth - 500) / 2;
				var wpTop = (wHeight * (4 / 5) - wpHeight) / 2;
				$('#HaveToDo').css({
					'margin-top' : wpTop
				});
				$('#HaveToDo').animate({
					'margin-left' : wpLeft
				}, "normal");
				$('#HistoryBack').bind('click', function() {
					refb();
				})
				
				
			});

			function refb() {
				location.href = document.referrer;
			}
		</script>
</body>