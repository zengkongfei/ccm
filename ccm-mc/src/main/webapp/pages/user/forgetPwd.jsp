<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<link rel="stylesheet" href="/css/membercenter.console.css">
<link rel="stylesheet" href="/css/ucenter.css">
<script type="text/javascript" src="<c:url value='/js/effective-validate-min.js'/>${global_js_revision}"></script>

<SCRIPT LANGUAGE="javascript">

	/**
	 * 点击修改密码的确认按钮
	 */
	function submitPwd(){
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
		   	  url:"user_resetForgetPwd.do",
		   	  data:$("#pwdForm").serialize(),
		      dataType : "json",
			  success:function(data){
				if(data.code=='success'){
					$("#editpassword").hide();
					$("#editpasswordsuccess").show();
					$("#ol").find(".ui-iconfontstep-end").addClass("ui-iconfontstep-active");
					$("#ol").find(".ui-iconfontstep-press").removeClass("ui-iconfontstep-active");
					window.setTimeout(
							function(){window.location.href="/login.jsp";}
							, 5000 );
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

	<ol class="ui-iconfontstep ui-iconfontstep-3" id="ol">
		<li class="ui-iconfontstep-start"><em><i class="iconfont"
				title="菱形">y</i><strong></strong><i class="ui-iconfontstep-stepNum">1</i>
		</em> <span><fmt:message key="ccm.user.Authentication" /></span>
		</li>
		<li class="ui-iconfontstep-press ui-iconfontstep-active"><em><i
				class="iconfont" title="菱形">y</i><strong></strong><i
				class="ui-iconfontstep-stepNum">2</i> </em> <span><fmt:message key="ccm.user.changepassword" /></span>
		</li>
		<li class="ui-iconfontstep-end "><em><i class="iconfont"
				title="菱形">y</i><strong></strong><i
				class="iconfont ui-iconfontstep-stepNumEnd" title="成功">r</i> </em> <span><fmt:message key="ccm.user.Changedsuccessfully" /></span>
		</li>
	</ol>

	<c:if test="${messageInfo != '' && messageInfo != null}">
		<div class="remindtip" id="remindtip">
			<span><fmt:message key="ccm.Channel.message.Attention" />：${messageInfo}</span>
			<div class="clearfix"></div>
		</div>
	</c:if>
	<c:if test="${messageInfo==null }">
	
	
	<%@ include file="/common/messages.jsp"%>
	<div class="fright"></div>
	<div class="ccm_2wp clearfix">
		<div class="ccm_right" id="editpassword" >
			<form action="/user_changePwd.do" id="pwdForm" method="post">
			
				<input name="userid" value="${userid }" type="hidden">
				<input name="key" value="${key }" type="hidden">
				<div id="pass-form">
					<div class="ui-form-item">
						<label class="ui-label"><fmt:message key="login.Username" />：</label> 
						${username}
					</div>
				
					<div class="ui-form-item">
						<label class="ui-label"><fmt:message key="ccm.user.newlogpassword" />：</label> 
						<input autocomplete="off" class="ui-input" type="password" id="newPwd" oninput="showPwd(this.value)" name="password" >
						<div class="pwdStrengthWrap fn-clear">
							<span class="fn-left"><fmt:message key="ccm.user.degreeofsafety" />:</span>
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
						<span style="color: red; font-size: 14px;"><fmt:message key="ccm.user.password90" /></span>
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
			<div class="ui-form-item">
				<label class="ui-label"><fmt:message key="ccm.error.064" /></label> 
			</div>
		</div>
	</div>
	</c:if>

	<div class="clearfix"></div>
</div>