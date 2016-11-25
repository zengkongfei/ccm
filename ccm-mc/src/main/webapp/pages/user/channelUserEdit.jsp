<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<jsp:include page="/pages/user/userType.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/js/effective-validate-min.js'/>${global_js_revision}"></script>
<SCRIPT LANGUAGE="javascript">
	$(document).ready(function() {
		<s:if test="b2bUser.userId==null">
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
				          required: '<span class="infotext"><fmt:message key="ccm.SupervisorOperator.ErrorMessage.002"/></span>',
				          rangelength: '<span class="infotext"><fmt:message key="ccm.error.069"/></span>',
				          pattern: '<span class="infotext"><fmt:message key="ccm.error.070"/></span>'
				     },
				     'b2bUser.confirmPassword' : {
		    	          equalTo: '<span class="infotext"><fmt:message key="ccm.error.071"/></span>'
		    	     }
				}
	
			});
		</s:if>
		
		$("table.normal input[id^=checkbox_]").change(function(){
			var inputs=$("input",$(this).parents("tr"));
	  		inputs.attr("checked",this.checked);
 		});
		
		$("#departmentChosen").find("option").each(function() {
			if ($(this).val() == '${b2bUser.employee.dept}') {
				$(this).attr("selected", true);
				showTitle();
			}
		});

		$("#positionChosen").find("option").each(function() {
			if ($(this).val() == '${b2bUser.employee.title}') {
				$(this).attr("selected", true);
			}
		});

		$('#departmentChosen').bind('change',function(){
			showTitle();
		});

	});
	
	function showTitle() {
		var dp_1 = new Array('', '总经理', '助理');
		var dp_2 = new Array('', '高级总监', '总监', '大区经理', '高级销售经理', '销售经理', '房控专员', '销售支持');
		var dp_3 = new Array('', '总监', '运营经理', '运营专员', '客服经理', '客服专员', '结算专员');
		var dp_4 = new Array('', '总监', '媒体与拓展经理', '市场主管', '市场策划', 'seo经理', 'seo专员');
		var dp_5 = new Array('', '总监', '副总监', 'ued经理', '产品经理', '产品助理', '交互设计师', '视觉设计师', '前端工程师');
		var dp_6 = new Array('', '总监', '资深开发工程师', '开发工程师', '资深开发主管', '开发主管', '开发经理', '测试工程师');
		var dp_7 = new Array('', '总编', '副总编', '主编', '编辑');
		var dp_8 = new Array('', '财务经理', '财务专员');

		var $posit = $('#positionChosen');
		var $i = $('#departmentChosen option:selected').val();
		function ShowPosion(v) {
			for (i = 0; i < v.length; i++) {
				$posit.append("<option value="+v[i]+">" + v[i] + "</option>");
			}
		}
		$posit = $('#positionChosen').empty();
		switch ($i) {
		case '总经办':
			ShowPosion(dp_1);
			break;
		case '销售部':
			ShowPosion(dp_2);
			break;
		case '运营部':
			ShowPosion(dp_3);
			break;
		case '市场部':
			ShowPosion(dp_4);
			break;
		case '产品部':
			ShowPosion(dp_5);
			break;
		case '技术部':
			ShowPosion(dp_6);
			break;
		case '编辑部':
			ShowPosion(dp_7);
			break;
		case '财务部':
			ShowPosion(dp_8);
			break;
		}
	}

	function dosubmit(url) {
		//验证密码的合法性：密码必须由数字，字母或者特殊字符中的至少两种组成
		<s:if test="b2bUser.username==null">
			var reg=/((?=.*[a-z])(?=.*\d)|(?=[a-z])(?=.*[#@!~%^&*])|(?=.*\d)(?=.*[#@!~%^&*]))[a-z\d#@!~%^&*]{7,20}/i
			if(! reg.test($('#password').val()) ){
			alert('<fmt:message key="login.Password.Reg"/>');
			return false;
			}
		</s:if>
		var chackEmail = /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+([\.]{1})+([a-zA-Z0-9_-])+/;
		if(!chackEmail.test($("#userForm_b2bUser_employee_email").val())){
			alert("<fmt:message key='ccm.error.075'/>");
			return  false;
		}
		
		//验证多语言并且重组数据 
		var flag = executeMoreLanguage(url);
		if(!flag){
			return;
		}
		var hotels = $('input[name="hotelId"]');
		//如果存在hotel列表 
		if(hotels.length > 0 && $('input[name="hotelId"]:checked').length == 0){
			alert('<fmt:message key="ccm.error.072"/>.');
			return false;
		}
		
		var isLive = $("#isLive").prop("checked");
		$("#isLive").val(isLive);
		if ($("#userForm").valid()) {
			document.userForm.action=url;
			document.userForm.submit();
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
			$('input[name="channelId"]').each(function(){
				$(this).attr('disabled',false);
				$(this).parent().show();
			});
		}else{
			$('input[name="channelId"]').each(function(){
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
	
	function selectAll(){
		$('input[name="hotelId"]').each(function(){
			if($(this).attr('disabled') != 'disabled'){
				$(this).prop('checked',true);
			}
		});
	}
	
	function reverseAll(){
		$('input[name="hotelId"]').each(function(){
			if($(this).attr('disabled') != 'disabled'){
				if($(this).prop('checked')){
					$(this).prop('checked',false);
				}else{
					$(this).prop('checked',true);
				}
			}
		});
	}
	/*
	//渠道权限  全选反选
	function selectAllChannel(){
		$('input[name="channelId"]').each(function(){
			if($(this).attr('disabled') != 'disabled'){
				$(this).prop('checked',true);
			}
		});
	}
	function reverseAllChannel(){
		$('input[name="channelId"]').each(function(){
			if($(this).attr('disabled') != 'disabled'){
				if($(this).prop('checked')){
					$(this).prop('checked',false);
				}else{
					$(this).prop('checked',true);
				}
			}
		});
	}
	*/
	function copyUserRole(){
		$('#AddNewElement').load('/${prefix}_showUserName.do?from=${param.from}&userId=${userId}');
	}
	
</script>
<div class="title_wp"><fmt:message key="ccm.SupervisorOperator.EditAccountNumber"/></div>
<%@ include file="/common/messages.jsp"%>
<s:form id="userForm" action="" method="post">
<appfuse:ccmToken name="token"></appfuse:ccmToken>
	<s:hidden key="b2bUser.userId" />
	<s:hidden key="b2bUser.employeeId" />
	<s:hidden key="b2bUser.employee.employeeId" />
	<s:hidden id="f_userI18ns" name="f_userI18ns" />
	<div class="c_whitebg pdA12">
		<div class="mgB24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"> <fmt:message key="login.Username"/></span>
					</div>
					<div class="i_input">
						<s:if test="b2bUser.userId!=null">
							<s:textfield key="b2bUser.username" cssClass="formnormal w240 required" readonly="true" />
						</s:if>
						<s:else>
							<s:textfield name="b2bUser.username" id="b2bUser.username" cssClass="formnormal w240 required" />
						</s:else>
					</div></li>
				<s:if test="b2bUser.username==null">
					<li>
						<div class="i_title">
							<span class="star"></span><span class="text"><fmt:message key="login.Password"/></span>
						</div>
						<div class="i_input">
							<s:password name="b2bUser.password" id="password" showPassword="false" cssClass="formnormal w240 required" />
						</div></li>
					<li>
						<div class="i_title">
							<span class="star"></span><span class="text"><fmt:message key="ccm.SupervisorOperator.RepeatPassword"/></span>
						</div>
						<div class="i_input">
							<s:password key="b2bUser.confirmPassword" id="b2bUser.confirmPassword" showPassword="false" cssClass="formnormal w240 required" />
						</div></li>
				</s:if>
				<s:if test="from!=\"2\"">
					<li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.SupervisorOperator.Name"/></span>
						</div>
						<div class="i_input">
							<s:textfield key="b2bUser.employee.name" cssClass="formnormal w240" />
							&nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_name"><fmt:message key="common.MultipleLanguagesSetup"/></button>
						</div>
					</li>
					<li id="ml_switch_name" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty b2bUser.employeeI18nList}">
							<c:forEach items="${b2bUser.employeeI18nList}" var="employeeI18n" varStatus="vstatus"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == employeeI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" 
													value="${employeeI18n.name}" />  
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_name');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
							</c:forEach>
						</c:if>
						
						<tr id="mdl_switch_name" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select>&nbsp;&nbsp;
								<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name"/> <br> 
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_name');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_name')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
					<li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.SupervisorOperator.Department"/></span>
						</div>
						<div class="i_input">
							<s:textfield key="b2bUser.employee.dept" cssClass="formnormal w240" />
							&nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_dept"><fmt:message key="common.MultipleLanguagesSetup"/></button>
						</div>
					</li>
					<li id="ml_switch_dept" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty b2bUser.employeeI18nList}">
							<c:forEach items="${b2bUser.employeeI18nList}" var="employeeI18n" varStatus="vstatus"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == employeeI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" 
													value="${employeeI18n.dept}" />  
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_dept');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
							</c:forEach>
						</c:if>
						
						<tr id="mdl_switch_dept" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select>&nbsp;&nbsp;
								<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name"/> <br> 
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_dept');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_dept')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.HotelUser.ContactWay"/></span>
						</div>
						<div class="i_input">
							<s:textfield key="b2bUser.employee.telNo" cssClass="formnormal w240" />
						</div></li>
						<li>
						<div class="i_title">
							<span class="star"></span><span class="text"><fmt:message key="common.Email"/></span>
						</div>
						<div class="i_input">
							<s:textfield key="b2bUser.employee.email" cssClass="formnormal w240 required" />
						</div></li>
				<li>
					<div class="i_title">
						<span class="text" ><fmt:message key="ccm.user.AccountReActive"/></span>
					</div>
					<div class="i_input">
						<input type="checkbox" name="b2bUser.isLive" id="isLive"
						<c:if test="${b2bUser.isLive}" > checked="chekced"</c:if>
						>
					</div>
				</li>
				</s:if>
			</ul>
		</div>
		<s:if test="allAuthMenuMap!=null">
			<div class="title_wp p_title"><fmt:message key="ccm.SupervisorOperator.Authority"/>	
			<div class="bt"><s:if test='b2bUser.userId==null || (b2bUser.userId!=null && b2bUser.userId!="1")'><a href="#AddNewElement" onclick="javascript:copyUserRole();" class="btn_2 blue ccm-popup-click"><fmt:message key="ccm.SupervisorOperator.Copy"/></a></s:if></div>
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
										<input type=checkbox name="roleIds" id="roleIds" value="<s:property value='#menuTree.menu.menuId'/>" <s:if test="authedRoleMap.contains(#menuTree.menu.menuId)"> checked="checked" </s:if> />
										<s:property value="#menuTree.menu.displayName" escape="false" />
									</p>
									<s:if test="#menuTree.menuset!=null">
										<div class="sec" style="vertical-align: top; margin-left: 15px;">
											<s:iterator value="#menuTree.menuset" id='submenu' status="st">
												<p>
													<input type=checkbox name="roleIds" id="roleIds" value="<s:property value='#submenu.menuId'/>" <s:if test="authedRoleMap.contains(#submenu.menuId)"> checked="checked" </s:if> />
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
		
			<div class="p_title"><fmt:message key="ccm.channelUser.ChannelAuthority"/></div>
			<div style="height:35px;text-align:center;">
				<fmt:message key="ccm.Channel.ChannelCode"/>:<input type="text" id="channelQuery" class="fxt w150" onkeyup="choiceChannel();"> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- 
				渠道权限  全选反选
				<button type="button" class="btn_3 white " onclick="selectAllChannel();"><fmt:message key="common.select.selectAll"/></button>&nbsp;&nbsp;
                <button type="button" class="btn_3 white " onclick="reverseAllChannel();"><fmt:message key="common.select.Unselect"/>	</button>
				 -->
				</div>
			<div style="height:120px;overflow-y:scroll;">
				<ul style="margin:0; padding:0; list-style:none;">
					<s:iterator value="channels" id="h">
						<li style="padding-right:1%; float:left; margin-bottom:6px; height:20px;width:15.5%;">
							<input class="input_checkbox" type="radio" name="channelId" value="${channelId}" 
								channelCode = "${channelCode}"
								<s:if test="#request.userChannels.containsKey(channelId)">checked="checked"</s:if> /> 
								<s:property value="channelCode" />
						</li>
					</s:iterator>
				</ul>
			</div>
			
			
			<div class="p_title"><fmt:message key="ccm.HotelUser.PropertyAuthority"/></div>
			<div style="height:35px;text-align:center;">
				<fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/>:<input type="text" id="hotelQuery" class="fxt w150" onkeyup="choiceHotel();"> 
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn_3 white " onclick="selectAll();"><fmt:message key="common.select.selectAll"/></button>&nbsp;&nbsp;
                <button type="button" class="btn_3 white " onclick="reverseAll();"><fmt:message key="common.select.Unselect"/>	</button>
			</div>
			<div style="height:260px;overflow-y:scroll;">
				<ul style="margin:0; padding:0; list-style:none;">
					<s:iterator value="hotels" id="h">
						<li style="padding-right:1%; float:left; margin-bottom:6px; height:20px;width:15.5%;">
							<input class="input_checkbox" type="checkbox" name="hotelId" value="${hotelId}" 
								hotelCode = "${hotelCode}"
								<s:if test="#request.userHotels.containsKey(hotelId)">checked="checked"</s:if> /> 
								<s:property value="hotelCode" />
						</li>
					</s:iterator>
				</ul>
			</div>
	</div>
	<div style="clear: both;"></div>
	<div class="listinputCtrl" >
		<button type="button" class="btn_1 green mgR12" onclick="javascript:dosubmit('<c:url value="/${prefix}_save.do?from=${param.from}'"/>);"><fmt:message key="common.button.OK"/></button>
		<a class="btn_1 white" href="/${prefix}_list.do?from=${param.from}"><fmt:message key="common.Return"/></a>
	</div>
</s:form>
<div id="AddNewElement" class="ccm-popup width600 zoom-anim-dialog mfp-hide"></div>

<script type="text/javascript">
$('.moreLanguageSwitch').click(function(){
	var moreLanguageSwitch = $('#ml_'+$(this).attr('id'));
	var modelLanguage = $('#mdl_'+$(this).attr('id'));
	
	if(moreLanguageSwitch.is(':hidden')){
		moreLanguageSwitch.show();
		
		//如果仅剩一行记录
		if(moreLanguageSwitch.find('table>tbody>tr').length==2){
			moreLanguageSwitch.find('table>tbody').find("tr:last").before('<tr><td class="w20">1.</td>'+modelLanguage.html()+'</tr>');
		}
	}else{
		moreLanguageSwitch.hide();
	}
});
//添加一项多语言
function addLanguage(t,switch_Id){
	addLanguageRow(t,switch_Id);
}

//移除一行语言
function deleteRow(t,switch_Id){
	deleteLanguageRow(t,switch_Id);
}

function executeMoreLanguage(url){
	var from = url.split('from=')[1];
	//拼接 多语言Json格式: 
	var moreLanguagesJson = '';
	var flag = true;
	//酒店用户
	if(from==2){
		var tempNameCodes = '';
		//循环遍历拼接多语言字符串
		$('#ml_switch_hotelname').find('table>tbody>tr:not(:last)').each(function(){
			
			//不能加载多语言模型行
			if($(this).attr('id') != 'mdl_switch_hotelname'){
				var sel = $(this).find('select[name="language.codeNo"]');
				var name = $(this).find('input[name="language.name"]').val();
				var selStr = ','+sel.val()+',';
				
				//如果未选择语言类型
				if(strIsNull(sel.val())){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Name"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
					alert(i18n_replace(str,arry));
					//alert('【姓名】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
					flag = false;
					return false;
				}
				//判断语言种类是否已重复
				if(tempNameCodes.indexOf(selStr)>=0){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Name"/>');
					arry.push(sel.find("option:selected").text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
					alert(i18n_replace(str,arry));
					//alert('【姓名】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
					flag = false;
					return false;
				}
				//如果未填写名称 
				if(strIsNull(name)){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Name"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
					alert(i18n_replace(str,arry));
					//alert('【姓名】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
					flag = false;
					return false;
				}
					
				//拼接语言种类
				tempNameCodes += selStr;
				moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',name:'"+escapeAcutes(name)+"'}";
			}
		});
		
		//校验不通过,返回
		if(!flag)return flag;
		
		var tempNameCodes = '';
		//循环遍历拼接多语言字符串
		$('#ml_switch_hoteldept').find('table>tbody>tr:not(:last)').each(function(){
			
			//不能加载多语言模型行
			if($(this).attr('id') != 'mdl_switch_hoteldept'){
				var sel = $(this).find('select[name="language.codeNo"]');
				var name = $(this).find('input[name="language.name"]').val();
				var selStr = ','+sel.val()+',';
				
				//如果未选择语言类型
				if(strIsNull(sel.val())){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Department"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
					alert(i18n_replace(str,arry));
					//alert('【部门】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
					flag = false;
					return false;
				}
				//判断语言种类是否已重复
				if(tempNameCodes.indexOf(selStr)>=0){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Department"/>');
					arry.push(sel.find("option:selected").text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
					alert(i18n_replace(str,arry));
					//alert('【部门】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
					flag = false;
					return false;
				}
				//如果未填写名称 
				if(strIsNull(name)){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Department"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
					alert(i18n_replace(str,arry));
					//alert('【部门】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
					flag = false;
					return false;
				}
					
				//拼接语言种类
				tempNameCodes += selStr;
				moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',dept:'"+escapeAcutes(name)+"'}";
			}
		});
	}else{
		var tempNameCodes = '';
		//循环遍历拼接多语言字符串
		$('#ml_switch_name').find('table>tbody>tr:not(:last)').each(function(){
			
			//不能加载多语言模型行
			if($(this).attr('id') != 'mdl_switch_name'){
				var sel = $(this).find('select[name="language.codeNo"]');
				var name = $(this).find('input[name="language.name"]').val();
				var selStr = ','+sel.val()+',';
				
				//如果未选择语言类型
				if(strIsNull(sel.val())){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Name"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
					alert(i18n_replace(str,arry));
					//alert('【姓名】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
					flag = false;
					return false;
				}
				//判断语言种类是否已重复
				if(tempNameCodes.indexOf(selStr)>=0){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Name"/>');
					arry.push(sel.find("option:selected").text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
					alert(i18n_replace(str,arry));
					//alert('【姓名】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
					flag = false;
					return false;
				}
				//如果未填写名称 
				if(strIsNull(name)){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Name"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
					alert(i18n_replace(str,arry));
					//alert('【姓名】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
					flag = false;
					return false;
				}
					
				//拼接语言种类
				tempNameCodes += selStr;
				moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',name:'"+escapeAcutes(name)+"'}";
			}
		});
		
		//校验不通过,返回
		if(!flag)return flag;
		
		var tempNameCodes = '';
		//循环遍历拼接多语言字符串
		$('#ml_switch_dept').find('table>tbody>tr:not(:last)').each(function(){
			
			//不能加载多语言模型行
			if($(this).attr('id') != 'mdl_switch_dept'){
				var sel = $(this).find('select[name="language.codeNo"]');
				var name = $(this).find('input[name="language.name"]').val();
				var selStr = ','+sel.val()+',';
				
				//如果未选择语言类型
				if(strIsNull(sel.val())){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Department"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
					alert(i18n_replace(str,arry));
					//alert('【部门】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
					flag = false;
					return false;
				}
				//判断语言种类是否已重复
				if(tempNameCodes.indexOf(selStr)>=0){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Department"/>');
					arry.push(sel.find("option:selected").text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
					alert(i18n_replace(str,arry));
					//alert('【部门】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
					flag = false;
					return false;
				}
				//如果未填写名称 
				if(strIsNull(name)){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.SupervisorOperator.Department"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
					alert(i18n_replace(str,arry));
					//alert('【部门】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
					flag = false;
					return false;
				}
					
				//拼接语言种类
				tempNameCodes += selStr;
				moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',dept:'"+escapeAcutes(name)+"'}";
			}
		});
	
	}
	
	//如果校验通过
	if(flag){
		//如果不为空,拼接成json
		if(!strIsNull(moreLanguagesJson)){
			moreLanguagesJson = '['+moreLanguagesJson.substring(1)+']';
			$('#f_userI18ns').val(moreLanguagesJson);
		}
	}
	return flag;
}
jQuery.extend(jQuery.validator.messages, {
	required : "<fmt:message key='common.RequiredField'/>"			
});
</script>






