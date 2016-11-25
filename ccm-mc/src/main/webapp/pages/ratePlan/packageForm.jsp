<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.PackageService.PackageServiceEstablishment"/> </div>
        <s:form id="packageForm" action="/package_save.do" method="post">
        <appfuse:ccmToken name="token"></appfuse:ccmToken>
        <s:hidden id="f_packageId" name="packagevo.packageId"/>
        <s:hidden id="f_packageMId" name="packagevo.packageMId"/>
        <s:hidden id="f_packageI18ns" name="f_packageI18ns" />
        <div class="c_whitebg pdA12">
          <div class="mgB24">
            <ul class="list_input">
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PackageService.PackageServiceCode"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_packageCode" name="packagevo.packageCode" cssClass="fxt w150 required"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.PackageService.PackageServiceName"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_packageName" name="packagevo.packageName" cssClass="fxt w150 required"></s:textfield>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_packageName"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_packageName" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty packagevo.packageI18nList}">
							<c:forEach items="${packagevo.packageI18nList}" var="packageI18" varStatus="vstatus"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == packageI18.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" 
													value="${packageI18.packageName}" /> 
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_packageName');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
							</c:forEach>
						</c:if>
						
						<tr id="mdl_switch_packageName" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select>&nbsp;&nbsp;
								<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name"/> 
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_packageName');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_packageName')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PackageService.IsSvcOrTax"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_issvcortax" name="packagevo.issvcortax"/>
                  </label>
                </div>
              </li>
              
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.PackageService.ExtraFeeorNot"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_isExtraCharge" name="packagevo.isExtraCharge"/>
                  </label>
                </div>
              </li>
            </ul>
            
            <ul id="div_rule" class="list_input">
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PackageService.Calculation"/>：</span></div>
                <div class="i_input">
					<%-- <s:select id="f_calculation" list="#{'1':'按每晚','2':'只算到达日','3':'只算离店日','4':'在一周内的某（几）天','5':'在一段时间内（几月几日~几月几日）'}" 
						name="packagevo.calculation" cssClass="fxt w220" headerKey="" headerValue="请选择">
		            </s:select> --%>
		             <select id="f_calculation" name="packagevo.calculation" class="fxt w220">
		            	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
		            	<c:forEach items="${calculationList }" var="calculation" varStatus="i">
		            	<option value="${i.index+1 }" <c:if test="${i.index+1==packagevo.calculation }">selected="selected"</c:if>   >${ calculation} </option>
		            	</c:forEach>
			         </select>
                </div>
              </li>
              <li style="display: none;" id="li_week">
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key='ccm.error.035'/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox name="packagevo.isApplyToSunday"/><fmt:message key="common.week.sunday"/>
                  </label>
                  <label class="checkbox inline">
                    <s:checkbox name="packagevo.isApplyToMonday"/><fmt:message key="common.week.monday"/>
                  </label>
                  <label class="checkbox inline">
                    <s:checkbox name="packagevo.isApplyToTuesday"/><fmt:message key="common.week.tuesday"/>
                  </label>
                  <label class="checkbox inline">
                    <s:checkbox name="packagevo.isApplyToWednesday"/><fmt:message key="common.week.wednesday"/>
                  </label>
                  <label class="checkbox inline">
                    <s:checkbox name="packagevo.isApplyToThursday"/><fmt:message key="common.week.thursday"/>
                  </label>
                  <label class="checkbox inline">
                    <s:checkbox name="packagevo.isApplyToFriday"/><fmt:message key="common.week.friday"/>	
                  </label>
                  <label class="checkbox inline">
                    <s:checkbox name="packagevo.isApplyToSaturday"/><fmt:message key="common.week.saturday"/>
                  </label>
                </div>
              </li>
              <li style="display: none;" id="li_date">
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key='ccm.error.036'/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:textfield id="f_beginDate" cssClass="fxt w150" name="packagevo.beginDate" ></s:textfield>--
                    <s:textfield id="f_endDate" cssClass="fxt w150" name="packagevo.endDate" ></s:textfield>
                  </label>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PackageService.CalculationRule"/>：</span></div>
                <div class="i_input">
					<%-- <s:select id="f_rule" list="#{'1':'固定价格','4':'每人','5':'每房间数'}" 
						name="packagevo.rule" cssClass="fxt w180" headerKey="" headerValue="请选择">
		            </s:select> --%>
		            <s:select id="f_rule" list="#request.rule" name="packagevo.rule" cssClass="fxt w180" >
			         </s:select>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PackageService.CalculationType"/>：</span></div>
                <div class="i_input">
					<%-- <s:select id="f_basicType" list="#{'1':'按百分比','2':'按具体值'}" 
						name="packagevo.basicType" cssClass="fxt w180">
		            </s:select> --%>
		            <select id="f_basicType" list="#basicTypeList.rule" name="packagevo.basicType" class="fxt w180">
			            	<%-- <option value=""><fmt:message key="common.select.plesesSelect"/></option>--%>
			            	<%-- <c:forEach items="${basicTypeList }" var="basicType" varStatus="i">
			            	<option value="${i.index+1 }" <c:if test="${basicType==packagevo.basicType }">selected="selected"</c:if>   >${ basicType} </option>
			            	</c:forEach> --%>
		            </select>
                </div>
              </li>
              <li style="display: none;" id="li_percent">
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key='ccm.error.037'/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_percent" name="packagevo.percent" cssClass="fxt w150 number"></s:textfield>
                </div>
              </li>
              <li style="display: none;" id="li_amount">
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PackageService.RelativeAmount"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_amount" name="packagevo.amount" cssClass="fxt w150 number"></s:textfield>
                </div>
              </li>
          	</ul>
          	<ul class="list_input">
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="common.Description"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_description" name="packagevo.description" cssClass="fxt w360 h80"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_description"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_description" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty packagevo.packageI18nList}">
							<c:forEach items="${packagevo.packageI18nList}" var="packageI18n" varStatus="vstatus"> 
								<c:if test="${not empty packageI18n.description }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == packageI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="common.Description"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.description" >${packageI18n.description}</textarea>
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_description');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>
								</c:if>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_description" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
								<fmt:message key="common.Description"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.description" ></textarea>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_description');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="#javascript:void[0];" class="link" onclick="addLanguage(this,'switch_description')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.PackageService.Alert"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_message" name="packagevo.message" cssClass="fxt w360 h80"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_message"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_message" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty packagevo.packageI18nList}">
							<c:forEach items="${packagevo.packageI18nList}" var="packageI18n" varStatus="vstatus"> 
								<c:if test="${not empty packageI18n.message }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == packageI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="ccm.PackageService.Alert"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.message" >${packageI18n.message}</textarea>
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_message');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>
								</c:if>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_message" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/></option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select> <br>
								<fmt:message key="ccm.PackageService.Alert"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.message" ></textarea>
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_message');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_message')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
            </ul>
          </div>
          <hr class="dashed">
          <div class="listinputCtrl">
            <button type="button" class="btn_1 green mgR12 f_save"><fmt:message key="common.button.OK"/></button>
            <a class="btn_1 white" href="javascript:history.go(-1);"><fmt:message key="common.Return"/></a>
          </div>
        </div>
        </s:form>
<script>
$(document).ready(function() {
	jQuery.extend(jQuery.validator.messages, {
		required : "<fmt:message key='common.RequiredField'/>"			
	});
	//日期显示
	var dpconfig = {
			dateFormat : "yy-mm-dd",
			showMonthAfterYear : true,
			dayNamesMin : [ 
			               '<fmt:message key="calendar.week.sunday"/>', 
			               '<fmt:message key="calendar.week.monday"/>', 
			               '<fmt:message key="calendar.week.tuesday"/>', 
			               '<fmt:message key="calendar.week.wednesday"/>', 
			               '<fmt:message key="calendar.week.thursday"/>', 
			               '<fmt:message key="calendar.week.friday"/>', 
			               '<fmt:message key="calendar.week.saturday"/>' 
			              ],
			yearSuffix : '<fmt:message key="time.year"/>',
			monthNames : [ 
			               '<fmt:message key="calendar.month.january"/>', 
			               '<fmt:message key="calendar.month.february"/>', 
			               '<fmt:message key="calendar.month.march"/>', 
			               '<fmt:message key="calendar.month.april"/>', 
			               '<fmt:message key="calendar.month.may"/>', 
			               '<fmt:message key="calendar.month.june"/>', 
			               '<fmt:message key="calendar.month.july"/>', 
			               '<fmt:message key="calendar.month.august"/>',
			               '<fmt:message key="calendar.month.september"/>', 
			               '<fmt:message key="calendar.month.october"/>', 
			               '<fmt:message key="calendar.month.november"/>', 
			               '<fmt:message key="calendar.month.december"/>' 
			              ],
			monthNamesShort:[
							'<fmt:message key="calendar.month.january"/>', 
							'<fmt:message key="calendar.month.february"/>', 
							'<fmt:message key="calendar.month.march"/>', 
							'<fmt:message key="calendar.month.april"/>', 
							'<fmt:message key="calendar.month.may"/>', 
							'<fmt:message key="calendar.month.june"/>', 
							'<fmt:message key="calendar.month.july"/>', 
							'<fmt:message key="calendar.month.august"/>',
							'<fmt:message key="calendar.month.september"/>', 
							'<fmt:message key="calendar.month.october"/>', 
							'<fmt:message key="calendar.month.november"/>', 
							'<fmt:message key="calendar.month.december"/>' 
			              ]
		}
	//开始/结束时间
	$("#f_beginDate").datepicker($.extend(dpconfig, {
		minDate:new Date(),
		onClose : function(v) {
			$("#f_endDate").datepicker("option", "minDate", v);
		}
	}));
	$("#f_endDate").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			$("#f_beginDate").datepicker("option", "maxDate", v);
		}
	}));
	isCheckSvcOrTax();
	
	isExtraChargeChange();
	
	//是否是税费
	$("#f_issvcortax").change(function(){
		isCheckSvcOrTax();
		isExtraChargeChange();
	});
	
	function isCheckSvcOrTax(){
		if($("#f_issvcortax").is(':checked')){
			$("#f_isExtraCharge").attr("disabled",true);
			$("#f_isExtraCharge").attr("checked",false);
		}else{
			$("#f_isExtraCharge").attr("disabled",false);
		}
	}
	
	//是否额外收费改变
	$("#f_isExtraCharge").change(function(){
		isExtraChargeChange();
	});
	
	calculationChange();
	
	//计算方式选择
	$("#f_calculation").change(function(){
		calculationChange();
	});
	
	basicTypeChange();
	
	//计算类型选择
	$("#f_basicType").change(function(){
		basicTypeChange();
	});
	
	//计算规则
	frule();
	$("#f_rule").change(function(){
		frule()
	});
	
	
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
	
	//设置打包服务编码不能编辑
	if(null!=$('#f_packageId').val() && ''!=$('#f_packageId').val()){
		$('#f_packageCode').attr("readonly", true);
	}
	
	//保存
	$('.f_save').click(function(){
		//验证表单
		if(!$("#packageForm").valid()){
			return;
		}
		//提交验证
		if(!checkForm()){
			return;
		}
		
		//修改
		if($('#f_packageId').val().length>0){
			$("#packageForm").submit();
			//禁止重复提交
			 $('.f_save').addClass('no_ald');
			 $('.f_save').attr("disabled","disabled");	
			return;
		}
		//新增时验证打包服务代码
		$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"package_isPackageCode.do",
	   	  data:{"packagevo.packageCode":$('#f_packageCode').val()},
		  success:function(data){
			  if("false" == data){
				 alert("<fmt:message key='ccm.error.038'/>");
		  	  }else{
		  		 $("#packageForm").submit();
		  		//禁止重复提交
				 $('.f_save').addClass('no_ald');
				 $('.f_save').attr("disabled","disabled");	
		  	  }
	       }
	    });
	});
});
//计算规则
function frule(){
	//debugger;
	var rule = $("#f_rule").val();
	if(rule==1){//价格
		if($("#f_basicType option:first").val()!=1){
			$("#f_basicType").prepend("<option value='1'><fmt:message key='ccm.PackageService.ByPercentage'/></option>");
		}
		$("#f_basicType option[value='2']").remove();
		
	}else if(strIsNull(rule)){
		if(strIsNull($("#f_basicType option[value='2']").val())){
			$("#f_basicType").append("<option value='2'><fmt:message key='ccm.PackageService.ByConcreteValue'/></option>");
		}
		if(strIsNull($("#f_basicType option[value='1']").val())){
			$("#f_basicType").prepend("<option value='1'><fmt:message key='ccm.PackageService.ByPercentage'/></option>");
		}
	}else{
		if(strIsNull($("#f_basicType option[value='2']").val())){
			$("#f_basicType").prepend("<option value='2'><fmt:message key='ccm.PackageService.ByConcreteValue'/></option>");
		}
		$("#f_basicType option[value='1']").remove();
	}
	basicTypeChange();
}

//是否额外收费改变
function isExtraChargeChange(){
	var isExtraCharge = $("#f_isExtraCharge").is(':checked');
	if(isExtraCharge){
		$("#div_rule").show();
		basicTypeChange();
	}else{
		$("#div_rule").hide();
		$("#div_rule :input").val("");
		$("#div_rule :checkbox").removeAttr('checked');
		$("#f_basicType").val("");
		$("#f_percent").val("0");
		$("#f_amount").val("0");
	}
}

//计算方式选择
function calculationChange(){
	var calculation = $("#f_calculation").val();
	if(calculation=='4'){
		$("#li_week").show();
		$("#li_date").hide();
		$("#li_date :input").val("");
	}else if(calculation=='5'){
		$("#li_date").show();
		$("#li_week").hide();
		$("#li_week :checkbox").removeAttr('checked');
	}else{
		$("#li_week").hide();
		$("#li_date").hide();
		$("#li_date :input").val("");
		$("#li_week :checkbox").removeAttr('checked');
	}
}

//计算类型选择
function basicTypeChange(){
	var basicType = $("#f_basicType").val();
	if(basicType=='1'){
		$("#li_percent").show();
		$("#li_amount").hide();
		$("#li_amount :input").val("0");
	}else{
		$("#li_amount").show();
		$("#li_percent").hide();
		$("#li_percent :input").val("0");
	}
}

//提交验证
function checkForm(){
	if($('#f_isExtraCharge').is(':checked')){
		if($('#f_calculation').val()==""){
			alert("<fmt:message key='ccm.error.039'/>");
			return  false;
		}
		
		if($('#f_calculation').val()=="4"){
			var boo = false;
			$('#li_week').find(':checkbox').each(function(){
				if($(this).is(":checked")){
					boo = true;
				}
			});
			if(!boo){
				alert("<fmt:message key='ccm.error.040'/>");
				return false;
			}
		}
		if($('#f_calculation').val()=="5"){
			var beginDate = $('#f_beginDate').val();
			var endDate = $('#f_endDate').val();
			if(beginDate=="" || endDate==""){
				alert("<fmt:message key='ccm.error.041'/>");
				return false;
			}

			//开始时间
			var beginDateCode = validateYYYYMMDD(beginDate);
			if(beginDateCode!='success'){
				alert(getErrorMsg(beginDateCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
				return false;
			}
			
			//结束时间
			var endDateCode = validateYYYYMMDD(endDate);
			if(endDateCode!='success'){
				alert(getErrorMsg(endDateCode,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-DD'));
				return false;
			}
		}
		
		if($('#f_rule').val()==""){
			alert("<fmt:message key='ccm.error.042'/>");
			return false;
		}
		
		if($('#f_basicType').val()==""){
			alert("<fmt:message key='ccm.error.043'/>");
			return false;
		}
		
		if($('#f_basicType').val()=="1"){
			if($('#f_percent').val()=="" || parseFloat($('#f_percent').val())==0){
				alert("<fmt:message key='ccm.error.044'/>");
				return false;
			}
		}
		if($('#f_basicType').val()=="2"){
			if($('#f_amount').val()=="" || parseFloat($('#f_amount').val())==0){
				alert("<fmt:message key='ccm.error.045'/>");
				return false;
			}
		}
	}
	
	//验证多语言并且重组数据 
	var flag = executeMoreLanguage();
	
	if(!flag){
		return false;
	}
	
	return true;
}

//添加一项多语言
function addLanguage(t,switch_Id){
	addLanguageRow(t,switch_Id);
}

//移除一行语言
function deleteRow(t,switch_Id){
	deleteLanguageRow(t,switch_Id);
}

function executeMoreLanguage(){
	
	//拼接 多语言Json格式: 
	var moreLanguagesJson = '';
	var codes = '';
	var flag = true;
	
	var tempNameCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_packageName').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_packageName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var name = $(this).find('input[name="language.name"]').val();
			
			var selStr = ','+sel.val()+',';
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PackageService.PackageServiceName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【打包服务名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			
			//判断语言种类是否已重复
			if(tempNameCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PackageService.PackageServiceName"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【打包服务名称】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			
			//如果未填写名称 
			if(strIsNull(name)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PackageService.PackageServiceName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
				alert(i18n_replace(str,arry));
				//alert('【打包服务名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
				flag = false;
				return false;
			}
				
			//拼接语言种类
			tempNameCodes += ',' + sel.val() + ',';
			
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',name:'"+escapeAcutes(name)+"'}";
		}
	});
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempDescCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_description').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_description'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var description = $(this).find('textarea[name="language.description"]').val();
			
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="common.Description"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			
			//判断语言种类是否已重复
			if(tempDescCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="common.Description"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【描述】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="common.Description"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.PackageService.PackageServiceName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【描述】多语言的语种:【'+sel.find("option:selected").text()+'】在【打包服务名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}

			//拼接语言种类
			tempDescCodes += selStr;
			
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',description:'"+escapeAcutes(description)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	var tempMessCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_message').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_message'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var message = $(this).find('textarea[name="language.message"]').val();
			
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PackageService.Alert"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【提醒】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			
			//判断语言种类是否已重复
			if(tempMessCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PackageService.Alert"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【提醒】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			
			//如果不在模板名称多语言范围内 
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.PackageService.Alert"/>');
				arry.push(sel.find("option:selected").text());
				arry.push('<fmt:message key="ccm.PackageService.PackageServiceName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【提醒】多语言的语种:【'+sel.find("option:selected").text()+'】在【打包服务名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}

			//拼接语言种类
			tempMessCodes += selStr;
			
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',message:'"+escapeAcutes(message)+"'}";
		}
	});

	//如果校验通过
	if(flag){
		//如果不为空,拼接成json
		if(!strIsNull(moreLanguagesJson)){
			moreLanguagesJson = '['+moreLanguagesJson.substring(1)+']';
			$('#f_packageI18ns').val(moreLanguagesJson);
		}
	}

	return flag;
}
</script>
