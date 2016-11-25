<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<script type="text/javascript" src="<c:url value='/js/effective-validate-min.js'/>${global_js_revision}"></script>
<SCRIPT LANGUAGE="javascript">

String.prototype.startWith=function(s){
	  if(s==null||s==""||this.length==0||s.length>this.length)
	   return false;
	  if(this.substr(0,s.length)==s)
	     return true;
	  else
	     return false;
	  return true;
	 }
	 
	$(document).ready(function() {
		
		$("table.normal input[id^=checkbox_]").change(function(){
			var inputs=$("input",$(this).parents("tr"));
	  		inputs.attr("checked",this.checked);
 		});

	});

	function dosubmit(url) {
		if ($("#roleForm").valid()) {
			document.roleForm.action=url;
			document.roleForm.submit();
		}

	}
	
	//酒店代码筛选
	function choiceHotel(){
		
		var code = $('#hotelQuery').val().toUpperCase();
		if(strIsNull(code)){
			$('input[name="hotelId"]').each(function(){
				$(this).attr('disabled',false);
				$(this).parent().show();
			});
		}else{
			$('input[name="hotelId"]').each(function(){
				var hotelCode = $(this).attr('hotelCode').toUpperCase();
				if(hotelCode.indexOf(code) >= 0){
					$(this).attr('disabled',false);
					$(this).parent().show();
				}else{
					$(this).attr('disabled',true);
					$(this).parent().hide();
				}
			});
		}
	}
	
	//渠道代码筛选
	function choiceChannel(){
		var code = $('#channelQuery').val().toUpperCase();
		if(strIsNull(code)){
			$('input[name="channelIds"]').each(function(){
				$(this).attr('disabled',false);
				$(this).parent().show();
			});
		}else{
			$('input[name="channelIds"]').each(function(){
				var channelCode = $(this).attr('channelCode').toUpperCase();
				if(channelCode.indexOf(code) >= 0){
					$(this).attr('disabled',false);
					$(this).parent().show();
				}else{
					$(this).attr('disabled',true);
					$(this).parent().hide();
				}
			});
		}
	}
	
	//渠道权限  全选反选
	function selectAllChannel(){
		$('input[name="channelIds"]').each(function(){
			if($(this).attr('disabled') != 'disabled'){
				$(this).prop('checked',true);
			}
		});
	}
	function reverseAllChannel(){
		$('input[name="channelIds"]').each(function(){
			if($(this).attr('disabled') != 'disabled'){
				if($(this).prop('checked')){
					$(this).prop('checked',false);
				}else{
					$(this).prop('checked',true);
				}
			}
		});
	}
	
	function copyRole(){
		$('#AddNewElement').load('/${prefix}_showUserName.do?from=${param.from}&userId=${userId}');
	}
	
</script>




<div class="title_wp"><fmt:message key="ccm.Role.roleAccessEdit"/>:</div>
<%@ include file="/common/messages.jsp"%>
<s:form id="roleForm" action="" method="post">
<appfuse:ccmToken name="token"></appfuse:ccmToken>
	<s:hidden key="roleId" />
	<div class="c_whitebg pdA12">
		<div class="mgB24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.Role.roleCode"/>	</span>
					</div>
					<div class="i_input">
						<s:if test="role.name!=null">
							<s:textfield key="role.name" cssClass="formnormal w240 required" readonly="true" />
						</s:if>
						<s:else>
							<s:textfield name="role.name" id="roleCode" cssClass="formnormal w240 required" />
						</s:else>
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.Role.roleName"/></span>
					</div>
					<div class="i_input">
						<s:if test="role.cnName!=null">
							<s:textfield key="role.cnName" cssClass="formnormal w240 required" />
						</s:if>
						<s:else>
							<s:textfield name="role.cnName" id="role.cnName" cssClass="formnormal w240 required" />
						</s:else>
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.Role.roleDesc"/></span>
					</div>
					<div class="i_input">
						<s:if test="role.description!=null">
							<s:textfield key="role.description" cssClass="formnormal w240 required"/>
						</s:if>
						<s:else>
							<s:textfield name="role.description" id="role.description" cssClass="formnormal w240 required" />
						</s:else>
					</div>
				</li>
			</ul>
		</div>
		
		
		
		<s:if test="allAuthMenuMap!=null">
			<div class="title_wp p_title"><fmt:message key="ccm.SupervisorOperator.Authority"/>	
			<!--<div class="bt"><a href="#AddNewElement" onclick="javascript:copyRole();" class="btn_2 blue ccm-popup-click"><fmt:message key="ccm.SupervisorOperator.Copy"/></a></div>-->
			</div>
			<table class="ccm_table1">
				<thead>
					<tr>
						<s:iterator value="allAuthMenuMap">
							<th><s:property value="key" escape="false" />
							</th>
						</s:iterator>
					</tr>
				</thead>
				<tbody>
					<tr>
						<s:iterator value="allAuthMenuMap">
							<td style="vertical-align: top; text-align: left"><s:iterator value="value" id='menuTree' status="st">
									<p>
										<input type=checkbox name="menuIds" id="menuIds" value="<s:property value='#menuTree.menu.menuId'/>" <s:if test="authedRoleMap.contains(#menuTree.menu.menuId)"> checked="checked" </s:if> />
										<s:property value="#menuTree.menu.displayName" escape="false" />
									</p>
									<s:if test="#menuTree.menuset!=null">
										<div class="sec" style="vertical-align: top; margin-left: 15px;">
											<s:iterator value="#menuTree.menuset" id='submenu' status="st">
												<p>
													<input type=checkbox name="menuIds" id="menuIds" value="<s:property value='#submenu.menuId'/>" <s:if test="authedRoleMap.contains(#submenu.menuId)"> checked="checked" </s:if> />
													<s:property value="#submenu.displayName" escape="false" />
												</p>
											</s:iterator>
										</div>
									</s:if>
								</s:iterator></td>
						</s:iterator>
					</tr>
				</tbody>
			</table>
		</s:if>
		
		
		<!-- 功能权限 -->
		<s:if test="allAuthMenuMap!=null">
			<div class="title_wp p_title"><fmt:message key="ccm.Authority.FunctionAuthority"/>	
			<!--<div class="bt"><a href="#AddNewElement" onclick="javascript:copyRole();" class="btn_2 blue ccm-popup-click"><fmt:message key="ccm.SupervisorOperator.Copy"/></a></div>-->
			</div>
			<table class="ccm_table1">
				<thead>
					<tr>
						<s:iterator value="allAuthMenuMap">
							<th><s:property value="key" escape="false" /></th>
						</s:iterator>
					</tr>
				</thead>
				<tbody>
					<tr>	
						<c:forEach items="${allAuthMenuMap}" var="item">  
							<td style="vertical-align: top; text-align: left">
								<c:forEach items="${allAuthority}" var="auth">  
									<c:if test="${item.key eq auth.menu }">
										<input type=checkbox name="authIds" id="authIds" 
										value="<c:out value="${auth.id }"/>" 
										${fn:contains(authList, auth.id)?"checked='checked'":""}
										/>
										<c:out value="${auth.authNameCN }"/><br/>
									</c:if>
								</c:forEach>
							</td>
               			</c:forEach>
					</tr>
				</tbody>
			</table>
		</s:if>
							
		<!-- channel access -->
		<div class="p_title"><fmt:message key="ccm.channelUser.ChannelAuthority"/></div>
				<div style="height:35px;text-align:center;">
					<fmt:message key="ccm.Channel.ChannelCode"/>:<input type="text" id="channelQuery" class="fxt w150" onkeyup="choiceChannel();"> 
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					渠道权限  全选反选
					<button type="button" class="btn_3 white " onclick="selectAllChannel();"><fmt:message key="common.select.selectAll"/></button>&nbsp;&nbsp;
	                <button type="button" class="btn_3 white " onclick="reverseAllChannel();"><fmt:message key="common.select.Unselect"/>	</button>
					</div>
				<div style="height:120px;overflow-y:scroll;">
					<ul style="margin:0; padding:0; list-style:none;">
						<s:iterator value="channels" id="h">
							<li style="padding-right:1%; float:left; margin-bottom:6px; height:20px;width:15.5%;">
								<input class="input_checkbox" type="checkbox" name="channelIds" value="${channelId}" 
									channelCode = "${channelCode}"
									<s:if test="channelIdSet.contains(channelId)">checked="checked"</s:if> /> 
									<s:property value="channelCode" />
							</li>
						</s:iterator>
					</ul>
				</div>
	</div>
	<div style="clear: both;"></div>
	<div class="listinputCtrl" >
		<button type="button" class="btn_1 green mgR12" onclick="javascript:dosubmit('<c:url value="/role_save.do"/>');"><fmt:message key="common.button.OK"/></button>
		<a class="btn_1 white" href="/role_list.do"><fmt:message key="common.Return"/></a>
	</div>
</s:form>
<div id="AddNewElement" class="ccm-popup width600 zoom-anim-dialog mfp-hide"></div>

<script type="text/javascript">
$("#roleCode").blur(function(){
	if($(this).val()==null||$(this).val()==""){
		return;
	}
	
	var CONTENT_FORMAT = /^[A-Za-z0-9]+$/;  	
	if(!CONTENT_FORMAT.test($(this).val())){  
		alert("<fmt:message key='ccm.Role.roleCodeValid'/>");
		$(this).val("");
		return;
	}
	$(this).val($(this).val().toUpperCase());
	if($(this).val().startWith("ROLE_")==false){
		$(this).val("ROLE_"+$(this).val());
	}
	
	var postData="roleCode="+$("#roleCode").val();
	$.ajax({
        type: "post",
        url: "role_exist.do",
        data: postData,
        cache:false,
        success: function(data){
        	//清空数据
        	
        	if(data=="true"){
        		alert("<fmt:message key='ccm.Role.roleCodeduplicated'/>");
        		$("#roleCode").val("");
        	}
        	
        }
	});
});
jQuery.extend(jQuery.validator.messages, {
	required : "<fmt:message key='common.RequiredField'/>"			
});
</script>






