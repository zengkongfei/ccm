<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="/css/membercenter.console.css">
<link rel="stylesheet" href="/css/ucenter.css">
<script type="text/javascript" src="<c:url value='/js/effective-validate-min.js'/>${global_js_revision}"></script>

<SCRIPT LANGUAGE="javascript">
	/**
	 * @Description: 修改邮箱
	 * @date 2012-2-20
	 * @version 1.0
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 */
	function updataEmail(emialStr){
		$("#spanEmial").hide();
		$("#updateEmail").hide();
		$("#email").val(emialStr).show();
		$("#okEmail").show();
	}

	/**
	 * 点击email的取消按钮
	 */
	function closeEmail(){
		$("#spanEmial").show();
		$("#updateEmail").show();
		$("#email").hide();
		$("#okEmail").hide();
		$("#emailInfo").text("");
	}

	/**
	 * 点击email的确定按钮
	 */
	function submitEmail(){
		var email = $("#email").val().replace(/\s+/g,"");
		if(email == ""){
			return showMsg("#emailInfo","<fmt:message key='ccm.error.074'/>");
		}else{
			hideMsg("#emailInfo");
		}

		//var chackEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		var chackEmail = /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+([\.]{1})+([a-zA-Z0-9_-])+/;
		
		if(!chackEmail.test(email)){
			return showMsg("#emailInfo","<fmt:message key='ccm.error.075'/>");
		}else{
			hideMsg("#emailInfo");
		}

		if(confirm("<fmt:message key='ccm.error.076'/>?")){
			$("#emailForm").submit();
		}
	}

	/**
	 * @Description: 修改密码
	 * @date 2012-2-20
	 * @version 1.0
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 */
	function updataPwd(pwdStr){
		/* $("#updatePwd").hide();
		$("#qiangPwd").hide();
		$("#newPwd").show();
		$("#okPwd").show(); */
		$("#ol").show();
		$("#editpassword").show();
		$("#editpasswordsuccess").hide();
		$("#table").hide();
		$("#ol").find(".ui-iconfontstep-press").addClass("ui-iconfontstep-active");
	}
	
	function cancelPwd(){
		$("#ol").hide();
		$("#editpassword").hide();
		$("#table").show();
	}

	/**
	 * 点击修改密码的取消按钮
	 */
	function closePwd(){
		$("#updatePwd").show();
		$("#qiangPwd").show();
		$("#newPwd").hide();
		$("#okPwd").hide();
		$("#pwdInfo").text("");
		$("#newPwd").val('');
		$("#newPwdConfirm").val('');
	}

	/**
	 * 点击修改密码的确认按钮
	 */
	function submitPwd(){
		var oldPassword     = $("#oldPwd").val().replace(/\s+/g,"");     //原始密码
		var newPassword     = $("#newPwd").val().replace(/\s+/g,""); //新密码
		var newPwdConfirm    = $("#newPwdConfirm").val().replace(/\s+/g,"");    //重复密码

		var reg3=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z#@!~%^&*]{7,20}$/;
		if(reg3.test(newPassword)){
			$(".pwdStrength-item-2").removeClass("pwdStrength-item-red").addClass("pwdStrength-item-green");
		}else{
			$(".pwdStrength-item-2").removeClass("pwdStrength-item-green").addClass("pwdStrength-item-red");
			return false;
		}
		
		if(newPassword!=newPwdConfirm){
			$(".pwdStrength-item-3").removeClass("pwdStrength-item-green").addClass("pwdStrength-item-red");
			return false;
		}else{
			$(".pwdStrength-item-3").removeClass("pwdStrength-item-red").addClass("pwdStrength-item-green");
		}

		if(confirm("<fmt:message key='ccm.error.081'/>?")){
			$.ajax({
		   	  type:"POST",
		   	  async:false,
		   	  url:"user_changePwd.do",
		   	  data:$("#pwdForm").serialize(),
			  success:function(data){
			  	var data = eval("("+data+")");
				if(data.code=='success'){
					$("#editpassword").hide();
					$("#editpasswordsuccess").show();
					$("#ol").find(".ui-iconfontstep-end").addClass("ui-iconfontstep-active");
					$("#ol").find(".ui-iconfontstep-press").removeClass("ui-iconfontstep-active");
				}else{
					alert(data.msg);
					return false;
				}		  
		      }
		    });	
		}
	}

	/**
	 * 显示信息
	 */
	function showMsg(astr,bstr){
		$(astr).text(bstr);
		return false;
	}

	/**
     * 清除信息
     */
	function hideMsg(astr){
		$(astr).text("");
	}

	
	function $$$(id){
		return document.getElementById(id);
	}

	/**
	 * 密码强度特效
	 **/
	function showPwd(txt){
		$$$("divdi").style.backgroundColor="white";
		$$$("divzhong").style.backgroundColor="white";
		$$$("divgao").style.backgroundColor="white";
		var reg1=/^.{7,}$/;
		var reg2=/[-+=/.,'"~!@#$%^&*()<>?]/;
		var reg3=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z#@!~%^&*]{7,20}$/;
		if(txt!=""){
			if(reg1.test(txt) && reg2.test(txt)){
				$$$("divdi").style.backgroundColor="green";
				$$$("divzhong").style.backgroundColor="green";
				$$$("divgao").style.backgroundColor="green";
			}else if(reg1.test(txt) || reg2.test(txt)){
				$$$("divdi").style.backgroundColor="yellow";
				$$$("divzhong").style.backgroundColor="yellow";
			}else{
				$$$("divdi").style.backgroundColor="red";
			}
			if(reg3.test(txt)&&reg1.test(txt)){
				$(".pwdStrength-item-2").addClass("pwdStrength-item-green");
			}else{
				$(".pwdStrength-item-2").removeClass("pwdStrength-item-green").addClass("pwdStrength-item-red");
			}
		}
	}
	
	function showConfirm(txt){
		var newPassword  = $("#newPwd").val().replace(/\s+/g,""); //新密码

		if(txt!=newPassword){
			$(".pwdStrength-item-3").addClass("pwdStrength-item-red");
			return false;
		}else{
			$(".pwdStrength-item-3").addClass("pwdStrength-item-green");
		}
	}

</script>


<div class="CCMmainConter w1200">
	<div class="fright"></div>
	<div class="title_wp">
		<fmt:message key="ccm.AccountManagement" />
	</div>

	<ol class="ui-iconfontstep ui-iconfontstep-3" style="display: none;"
		id="ol">
		<li class="ui-iconfontstep-start"><em><i class="iconfont"
				title="菱形">y</i><strong></strong><i class="ui-iconfontstep-stepNum">1</i>
		</em> <span><fmt:message key="ccm.user.Authentication" /></span>
		</li>
		<li class="ui-iconfontstep-press "><em><i
				class="iconfont" title="菱形">y</i><strong></strong><i
				class="ui-iconfontstep-stepNum">2</i> </em> <span><fmt:message key="ccm.user.changepassword" /></span>
		</li>
		<li class="ui-iconfontstep-end "><em><i class="iconfont"
				title="菱形">y</i><strong></strong><i
				class="iconfont ui-iconfontstep-stepNumEnd" title="成功">r</i> </em> <span><fmt:message key="ccm.error.064" /></span>
		</li>
	</ol>

	<c:if test="${messageInfo != '' && messageInfo != null}">
		<div class="remindtip" id="remindtip">
			<span><fmt:message key="ccm.Channel.message.Attention" />：${messageInfo}</span>
			<div class="clearfix"></div>
		</div>
	</c:if>
	<%@ include file="/common/messages.jsp"%>
	<div class="fright"></div>
	<div class="ccm_2wp clearfix">
		<table class="ccm_right" id="table" width="90%" border="0"
			cellspacing="0" cellpadding="0">
			<tr>
				<th scope="row"><fmt:message key="login.Username" />：</th>
				<td>${b2bUser.username }</td>
			</tr>
			<tr>
				<th scope="row"><fmt:message key="login.Password" />：</th>
				<td>
					<div id="qiangPwd">
						********
						<input id="userPwd" type="hidden" value="${b2bUser.password}">
				</td>
				<td>
					<div id="updatePwd">
						<a href="#" onclick="javascript:updataPwd()"><span><fmt:message
									key="ccm.error.082" />
						</span> </a>
					</div>
				</td>
			</tr>
			<form id="emailForm" action="user_changeEmail.do" method="post">
				<tr>
					<th scope="row"><fmt:message key="common.Email" />：</th>
					<td><span id="spanEmial">${b2bUser.employee.email }</span> <input
						style="display: none" class="formnormal w240"
						name="b2bUser.employee.email" id="email"> <span
						class="infotext" id="emailInfo"></span>
					</td>
					<td>
						<div id="updateEmail">
							<a href="#"
								onclick="javascript:updataEmail('${b2bUser.employee.email }')"><span><fmt:message
										key="ccm.error.083" />
							</span> </a>
						</div>
						<div id="okEmail" style="display: none">
							<a class="searchbt" href="#" onclick="javascript:submitEmail()"><span><fmt:message
										key="common.button.OK" />
							</span> </a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="#"
								onclick="javascript:closeEmail()"><span><fmt:message
										key="common.button.Cancel" />
							</span> </a>
						</div>
					</td>
				</tr>
			</form>
		</table>

		<div class="ccm_right" id="editpassword" style="display: none;">
			<form action="/user_changePwd.do" id="pwdForm" method="post">
				<div id="pass-form">
					<div class="ui-form-item     ">
						<label class="ui-label"><fmt:message key="ccm.user.originalpassword"/>：</label> 
						<input autocomplete="off" name="b2bUser.confirmPassword" class="ui-input" type="password" id="oldPwd" data-widget-cid="widget-1">
					</div>
					<div class="ui-form-item     ">
						<label class="ui-label"><fmt:message key="ccm.user.newlogpassword"/>：</label> 
						<input autocomplete="off" class="ui-input" type="password" id="newPwd" oninput="showPwd(this.value)" name="b2bUser.password" >
						<div class="pwdStrengthWrap fn-clear">
							<span class="fn-left"><fmt:message key="ccm.user.degreeofsafety"/>:</span>
							<div id="qiangPwd">
								<div style="display: inline;">
									<div id="divdi" style="border: 1px green solid; float: left; width: 70px">
										<center>
											<fmt:message key="ccm.Low" />
										</center>
									</div>
									<div id="divzhong" style="border: 1px green solid; float: left; width: 70px">
										<center>
											<fmt:message key="ccm.Medium" />
										</center>
									</div>
									<div id="divgao" style="border: 1px green solid; float: left; width: 70px">
										<center>
											<fmt:message key="ccm.High" />
										</center>
									</div>
								</div>
							</div>
							<div id="newPwdStrengthWrap" class="fn-left"></div>
						</div>
						<ul class="pwdStrength">
							<li class="pwdStrength-item-2"><fmt:message key="login.Password.Reg" /></li>
						</ul>
					</div>
					<div class="ui-form-item">
						<label class="ui-label"><fmt:message key="ccm.SupervisorOperator.RepeatPassword"/>：</label> 
						<input name="newPwdConfirm" class="ui-input" type="password" id="newPwdConfirm" oninput="showConfirm(this.value)">
						<ul class="pwdStrength">
							<li class="pwdStrength-item-3"><fmt:message key="ccm.error.071"/></li>
						</ul>
					</div>
					<div class="ui-form-item">
						<label class="ui-label"></label> 
						<span style="color: red; font-size: 14px;"><fmt:message key="ccm.user.password90"/></span>
					</div>
					<div class="ui-form-item">
						<button type="button" class="btn_3 blue confirmthis" onclick="submitPwd()">
							<fmt:message key="common.button.OK" />
						</button>
					</div>
				</div>
			</form>
		</div>
		<div class="ccm_right" id="editpasswordsuccess" style="display: none;">
			
			<div class="ui-form-item     ">
						<label class="ui-label"><fmt:message key="ccm.error.064" /></label> 
					</div>
		</div>


	</div>

	<div class="clearfix"></div>
</div>