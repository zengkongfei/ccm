<%@page import="org.springframework.security.web.WebAttributes"%>
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
<script type="text/javascript">
if(top.location!=self.location)top.location=self.location;
</script>
<head>
<title><fmt:message key="title.ChinaonlineChannelManagement"/></title>
<link rel="stylesheet" href="<c:url value='/css/site_co.css'/>">
<link rel="stylesheet" href="<c:url value='/css/main.css'/>">
<script src="<c:url value='js/jquery-1.10.2.min.js'/>${global_js_revision}"></script>
</head>
 <%
    HttpSession s = request.getSession(); 
   	s.setAttribute("LoginActionForChart","LoginActionForChart");
 %>
<body class="nobg">
	<div class="div_login_form">
		<div class="div_inner_con">
			<form method="post" name="loginForm" id="loginForm" action="<c:url value='/j_spring_security_check'/>">
				<div class="mrd12">
					<span class="EIB vlmiddle span_login_left"><fmt:message key="login.Username"/></span><input type="text" class="input_login_form" name="j_username" value="" id="j_username" />
				</div>
				<div class="mrd12" id="pwd">
					<span class="EIB vlmiddle span_login_left"><fmt:message key="login.Password"/></span><input type="password" class="input_login_form" name="j_password" id="j_password" value="" />
				</div>
				<div class="mrd12" id="pwd">
					<span class="EIB vlmiddle span_login_left"><fmt:message key="login.Identifying"/></span>
					<input type="text" class="input_login_form" style="width: 100px" name="inputCode" id="inputCode" value=""  onkeydown="keyLogin();"/>
					<img src="/imageCode.do" onclick="this.src='/imageCode.do?a='+Math.random()+100"  style="cursor:pointer;">
					<a href="javascript:forgetPassword();"><fmt:message key="ccm.user.forgotpassword"/></a>
				</div>
				<div class="mrd12">
					<span class="EIB vlmiddle span_login_left"></span> <a class="btn_2 green" href="javascript:void(0)" onClick="doLogin()"><fmt:message key="login.Login"/></a> <span style="color: red; display: none;" id="error">&nbsp;<fmt:message key='login.errorMessage.UsernamePassword'/></span>
				</div>
			</form>
			
		</div>

	</div>
	<!--页面底部 START-->
	<jsp:include page="/common/footer.jsp"></jsp:include>
	<!--页面底部 END-->
</body>
<script type="text/javascript">
	function keyLogin(){
	  if (event.keyCode==13){
		  doLogin();
	  }
	}
		// 在用户名处获得焦点
		$("#j_username").focus();
		
	<c:if test="${param.error=='true'}">
	var msg = "${SPRING_SECURITY_LAST_EXCEPTION.message}";
	if(msg == "Bad credentials"){
		msg = "<fmt:message key='login.errorMessage.UsernamePassword'/>";
	}
	if(msg=="验证码有误！"){
		$("#inputCode").focus();
		msg = "<fmt:message key='login.errorMessage.IdentifyingError'/>";
	}
	if(msg=="请从正确的页面访问！"){
		$("#inputCode").focus();
		msg = "<fmt:message key='login.errorMessage.IdentifyingError'/>";
	}
	if(msg=="账户未激活！"){
		$("#inputCode").focus();
		msg = "<fmt:message key='ccm.user.AccountDeactive'/>";
	}
	if(msg=="用户名与用户类型不匹配！"){
		$("#inputCode").focus();
		msg = "<fmt:message key='login.errorMessage.usernameandtheusertypemismatch'/>";
	}
	if(msg=="用户被锁定"){
		$("#inputCode").focus();
		msg = "<fmt:message key='ccm.user.message'/>";
	}
		
	showMsg("#error",msg);
	</c:if>

	

	/**
	 * @Description: 运营登录页面js校验
	 * @date 2012-2-20
	 * @version 1.0
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 */
	function doLogin() {
		var userName = $("#j_username").val().replace(/\s+/g, "");
		var password = $("#j_password").val().replace(/\s+/g, "");
		var inputCode = $("#inputCode").val().replace(/\s+/g, "");
		
		var userType = $("input[name='userType']:checked").val();

		if (userName == "") {
			$("#j_username").focus();
			return showMsg("#error", "<fmt:message key='login.errorMessage.Username'/>");
		} else {
			hideMsg("#error");
		}
		if (password == "") {
			$("#j_password").focus();
			return showMsg("#error", "<fmt:message key='login.errorMessage.Password'/>");
		} else {
			hideMsg("#error");
		}
		if (inputCode == "") {
			$("#inputCode").focus();
			return showMsg("#error", "<fmt:message key='login.errorMessage.Identifying'/>");
		} else {
			hideMsg("#error");
		}
		
		//document.loginForm.action="<c:url value="/plat/operator_login.action"/>";
		document.loginForm.submit();

	}

	/**
	 * 显示信息
	 */
	function showMsg(astr, bstr) {
		$(astr).show();
		$(astr).text(bstr);
		return false;
	}

	/**
	 * 清除信息
	 */
	function hideMsg(astr) {
		$(astr).text("");
	}
	/*
	$('.radio').change(function(){
		if($(this).val()==1){
			$.ajax({
				url : '/menu_hotels.do',
				beforeSend : function() {

				},
				cache : false,
				data : "",
				dataType : "json",
				success : function(data) {
					if (data.success == "OK") {
						$('#pwd').after('<div class="mrd12"><span class="EIB vlmiddle span_login_left">酒店</span><select name="hotelId" id="hotel"></select></div>')
						$.each(data.data,function(n,value) {   
					           $('#hotel').append("<option value=\""+value.hotelId+"\">"+value.hotelCode+"_"+value.hotelName+"</option>");      
					    });  
					} else {
						alert('获取酒店失败');
					}
				}
			});
		}else{
			$('#hotel').parent().remove();
		}
		
	});*/
	
	$(document).ready(function() {
		var local = "${sessionScope['org.apache.struts2.action.LOCALE']}";
		if(local==null||local==''){
			var current_lang_map;  
			currentLang = navigator.language;
			if(!currentLang)
			    currentLang = navigator.browserLanguage;
			
			currentLang = currentLang.replace("-","_");
			i18n(currentLang);
		}
	})
	
	function forgetPassword(){
		var username=$("#j_username").val().trim();
		$.ajax({
			url : '/user_forgetPassword.do',
			beforeSend : function() {
			},
			cache : false,
			type:"POST",
			data : {"username":username},
			dataType : "json",
			success : function(data) {
				alert(data.msg);
			}
		});
	}
	
</script>
</html>