<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<jsp:include page="/pages/user/userType.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/js/effective-validate-min.js'/>${global_js_revision}"></script>
<SCRIPT LANGUAGE="javascript">
	$(document).ready(function() {
			$("#userForm").effectiveAndValidate( {
				
				rules : {
					
					'b2bUser.username' : {
						required : true,
						byterangelength : [ 3, 50 ],
						pattern : /^[\u4e00-\u9faf_0-9a-zA-Z-]+$/,
						pattern2 : /^[\u4e00-\u9fafa-zA-Z].*$/
					},
					'b2bUser.password' : {
						required : true,
						rangelength : [ 7, 20 ],
						pattern : /^[\x21-\x7e]+$/
					},
					'b2bUser.confirmPassword' : {
						equalTo : '#password'
					}
				},
				
				messages : {
				     'b2bUser.username' : {
				         required : '<span class="infotext"><fmt:message key="ccm.SupervisorOperator.ErrorMessage.001"/></span>',
				         byterangelength: '<span class="infotext"><fmt:message key="ccm.error.066"/></span>',
				         pattern: '<span class="infotext"><fmt:message key="ccm.error.067"/></span>',	
				         pattern2: '<span class="infotext"><fmt:message key="ccm.error.068"/></span>'
				     },
				     'b2bUser.password' : {
				          required: '<span class="infotext"><fmt:message key="login.errorMessage.Password"/></span>',
				          rangelength: '<span class="infotext"><fmt:message key="ccm.error.069"/></span>',
				          pattern: '<span class="infotext"><fmt:message key="ccm.error.070"/></span>'
				     },
				     'b2bUser.confirmPassword' : {
		    	          equalTo: '<span class="infotext"><fmt:message key="ccm.error.071"/></span>'
		    	     }
				}
	
			});

	});

	function dosubmit(url) {
		
		//验证密码的合法性：密码必须由数字，字母或者特殊字符中的至少两种组成
		var reg=/((?=.*[a-z])(?=.*\d)|(?=[a-z])(?=.*[#@!~%^&*])|(?=.*\d)(?=.*[#@!~%^&*]))[a-z\d#@!~%^&*]{7,20}/i
		if(! reg.test($('#password').val()) ){
			alert('<fmt:message key="login.Password.Reg"/>');
			return false;
		}
		
		if ($("#userForm").valid()) {
			document.userForm.action=url;
			document.userForm.submit();
		}

	}
	jQuery.extend(jQuery.validator.messages, {
		required : "<fmt:message key='common.RequiredField'/>"			
	});
</script>
<%@ include file="/common/messages.jsp"%>
<div class="title_wp"><fmt:message key="ccm.SupervisorOperator.ResetPassword"/></div>
<s:form id="userForm" action="" method="post">
	<s:hidden key="b2bUser.userId" />
	<s:hidden key="b2bUser.employee.employeeId" />
	<div class="c_whitebg pdA12">
		<div class="c_whitebg pdA12">
			<div class="mgB24">
				<ul class="list_input">
					<li>
						<div class="i_title">
							<span class="star"></span><span class="text"><fmt:message key="login.Username"/></span>
						</div>
						<div class="i_input">
							<s:textfield name="b2bUser.username" id="b2bUser.username" cssClass="fxt w240" readonly="true"/>
						</div>
					</li>
					<li>
						<div class="i_title">
							<span class="star"></span><span class="text"><fmt:message key="login.Password"/> </span>
						</div>
						<div class="i_input">
							<s:password name="b2bUser.password" id="password" showPassword="false" cssClass="fxt w240 required" />
						</div>
					</li>
					<li>
						<div class="i_title">
							<span class="star"></span><span class="text"><fmt:message key="ccm.SupervisorOperator.RepeatPassword"/></span>
						</div>
						<div class="i_input">
							<s:password key="b2bUser.confirmPassword" id="b2bUser.confirmPassword" showPassword="false" cssClass="fxt w240 required" />
						</div>
					</li>
				</ul>
			</div>
			<hr class="dashed">
			<div class="listinputCtrl">
				<button type="button" class="btn_1 green mgR12" onclick="javascript:dosubmit('/${prefix}_resetPwd.do?from=${param.from}');"><fmt:message key="common.button.OK"/></button>
				<a class="btn_1 white" href="/${prefix}_list.do?from=${param.from}"><fmt:message key="common.Return"/></a>
			</div>
		</div>
	</div>
</s:form>