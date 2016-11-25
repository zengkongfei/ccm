<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<jsp:include page="/pages/user/userType.jsp"></jsp:include>
<div class="pp_main">
	<div class="t_title"><fmt:message key="ccm.SupervisorOperator.Copy"/></div>
	<form id="copyUserRoleForm" name="copyUserRoleForm" action="/${prefix}_copyUserRole.do?from=${param.from}" method="post">
	<s:if test='#request.desUserId!=""'>
	<s:hidden name="b2bUser.userId" value="%{#attr.desUserId}"/>
	</s:if>
	<input type="hidden" name="b2bUser.isLive" id="iscLive" >
	<appfuse:ccmToken name="token"></appfuse:ccmToken>
		<div class="pdA24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="login.Username"/>：</span>
					</div>
					<div class="i_input" style="position:relative;">
						<select class="fxt w180 required" name="userId" id="userId">
							<c:forEach items="${userList}" var="user">
								<c:if test="${desUserId!=user.userId && user.userId!='1'}">
								<option value="${user.userId}" <c:if test="${userId==user.userId}">selected="selected"</c:if>>${user.username}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</li>
			</ul>
		</div>
		<div class="b_crl">
			<button type="button" class="btn_2 green mgR6" onclick="dosubmita();"><fmt:message key="common.button.save"/>	</button>
			<button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
		</div>
	</form>
</div>
<script src="<c:url value='/css/jquery-ui/jquery-ui.js'/>${global_js_revision}"></script>
<script src="<c:url value='/js/main.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/effective-validate-min.js'/>${global_js_revision}"></script>

<script type="text/javascript">

	var urla;

	function dosubmita() {

		$("#copyUserRoleForm").effectiveAndValidate({
			rules : {
				'userId' : {
					required : true,
				}
			},
			messages : {
				'userId' : {
					required : '<span class="infotext"><fmt:message key="common.select.plesesSelect"/>	</span>'
				}
			}

		});
		<s:if test='#request.desUserId==""'>
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
					rangelength : [ 6, 20 ],
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
		if (!$("#userForm").valid()) {
			$.magnificPopup.close();
		}
		var isLive = $("#isLive").prop("checked");
		$("#isLive").val(isLive);
		if ($("#copyUserRoleForm").valid() && $("#userForm").valid()) {
			urla=$("#copyUserRoleForm").attr("action")+"&"+$('#userForm').serialize()+"&"+$("#copyUserRoleForm").serialize();
		}
	</s:if>
	<s:else>
		if ($("#copyUserRoleForm").valid()) {
			urla = $("#copyUserRoleForm").attr("action")+"&"+$("#copyUserRoleForm").serialize();
		}
		
	</s:else>
		$.ajax({
			  type: "POST",
			  url: urla,
			  success: function(data){
				  data = eval("(" + data + ")");
				  if(true == data.success){
					  alert('<fmt:message key="ccm.SupervisorOperator.Successfully"/>');
					  window.location.href="/${prefix}_edit.do?from=${param.from}&userId="+data.message;
				  }else{
					  alert(data.message);
				  }
			  }
		});
	}
</script>