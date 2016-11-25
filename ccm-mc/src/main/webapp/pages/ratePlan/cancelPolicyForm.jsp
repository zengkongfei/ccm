<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.CancellationRules.CancellationRulesEstablishment"/> </div>
        <s:form id="cancelForm" action="/cancel_save.do" method="post">
        
        <appfuse:ccmToken name="token"></appfuse:ccmToken>
        
        <s:hidden id="f_cancelId" name="cancelPolicyvo.cancelId"/>
        <s:hidden id="f_cancelMId" name="cancelPolicyvo.cancelMId"/>
        <s:hidden id="f_cancelPolicyI18ns" name="f_cancelPolicyI18ns" />
        <div class="c_whitebg pdA12">
          <div class="mgB24">
            <ul class="list_input">
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.CancellationRules.CancellationRulesCode"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_cancelPolicyCode" name="cancelPolicyvo.cancelPolicyCode" cssClass="fxt w150 required"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.CancellationRules.CancellationRulesName"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_policyName" name="cancelPolicyvo.policyName" cssClass="fxt w150 required"></s:textfield>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_policyName"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_policyName" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty cancelPolicyvo.cancelPolicyI18nList}">
							<c:forEach items="${cancelPolicyvo.cancelPolicyI18nList}" var="cancelPolicyI18n" varStatus="vstatus"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == cancelPolicyI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" 
													value="${cancelPolicyI18n.policyName}" /> 
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_policyName');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
							</c:forEach>
						</c:if>
						
						<tr id="mdl_switch_policyName" style="display:none;">  
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
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_policyName');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_policyName')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="common.Description"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_description" name="cancelPolicyvo.description" cssClass="fxt w360 h80"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_description"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_description" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty cancelPolicyvo.cancelPolicyI18nList}">
							<c:forEach items="${cancelPolicyvo.cancelPolicyI18nList}" var="cancelPolicyI18n" varStatus="vstatus"> 
								<c:if test="${not empty cancelPolicyI18n.description }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == cancelPolicyI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="common.Description"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.description" >${cancelPolicyI18n.description}</textarea>
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
										<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
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
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_description')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.CancellationRules.Noncancelable"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_isNonRefundable" name="cancelPolicyvo.isNonRefundable"/>
                  </label>
                </div>
              </li>
              <li class="col3_1" id="li_cancelDeadline">
                <div class="i_title"><span class="text"><fmt:message key="ccm.CancellationRules.CancellationDeadline"/>：</span></div>
                <div class="i_input">
                  <label class="radio inline">
                    <input type="radio" name="calcelDeadline" value="0">
                    <span class=""><fmt:message key="ccm.CancellationRules.AbsoluteTime"/></span>
                  </label>
                  <label class="radio inline">
                    <input type="radio" name="calcelDeadline" value="1" >
                    <span class=""><fmt:message key="ccm.CancellationRules.RelativeTime"/></span> 
                  </label>
                </div>
              </li>
              <li id="li_absoluteTime" style="display: none;">
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.CancellationRules.AbsoluteTime"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_absoluteTime" key="cancelPolicyvo.absoluteTime" cssClass="fxt w150" />
                </div>
              </li>
              <li id="li_offsetDropTime" style="display: none;">
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.CancellationRules.ReferenceTimePoint"/>：</span></div>
                <div class="i_input">
		            <select id="f_offsetDropTime" Class="fxt w80" name="cancelPolicyvo.offsetDropTime">
		            	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
			            <c:forEach items="${offsetDropTime }" var="offsetDrop" varStatus="i">
			            	<option value="${i.index+1 }"  <c:if test="${(i.index+1)==cancelPolicyvo.offsetDropTime }">selected="selected"</c:if> >${offsetDrop }</option>
			            </c:forEach>
		            </select>
		            <s:textfield id="f_offsetUnitMultiplier" key="cancelPolicyvo.offsetUnitMultiplier" cssClass="fxt w80"></s:textfield>
		            <select id="f_offsetTimeUnit" Class="fxt w80" name="cancelPolicyvo.offsetTimeUnit">
		            	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
			            <c:forEach items="${offsetUnitMultiplier }" var="UnitMultiplier" varStatus="i">
			            	<option value="${i.index+1 }"  <c:if test="${(i.index+1)==cancelPolicyvo.offsetTimeUnit }">selected="selected"</c:if> >${UnitMultiplier }</option>
			            </c:forEach>
		            </select>
		             <select id="f_offsetCutTime" Class="fxt w80" name="cancelPolicyvo.offsetCutTime">
			            <c:forEach var="x" begin="0" end="23" step="1"> 
			            	<option value="${x}"  <c:if test="${x==cancelPolicyvo.offsetCutTime}">selected="selected"</c:if> >${x}</option>
			            </c:forEach>
		            </select>
                </div>
              </li>
              <li id="li_feesInclusive">
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.CancellationRules.DeductedAmountorNot"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_feesInclusive" name="cancelPolicyvo.feesInclusive"/>
                  </label>
                </div>
              </li>
              <li id="li_basisType">
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.CancellationRules.DeductedAmountBaseNumber"/>：</span></div>
                <div class="i_input">
		            <select id="f_basisType" Class="fxt w80" name="cancelPolicyvo.basisType" >
		            	<option value=""><fmt:message key="common.select.plesesSelect"/></option>
			            <c:forEach items="${basisType }" var="basis" varStatus="k">
			            	<option value="${k.index+1 }"  <c:if test="${cancelPolicyvo.basisType-k.index-1==0}">selected="selected"</c:if> >${basis }</option>
			            </c:forEach>
		            </select>
                </div>
              </li>
              <li id="li_roomNight">
                 <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.CancellationRules.DeductedAmountByNights"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_numberOfNights" name="cancelPolicyvo.numberOfNights" cssClass="fxt w80 number"></s:textfield>
                </div>
              </li>
              <li id="li_percent">
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.CancellationRules.DeductedAmountByPercentage"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_percent" name="cancelPolicyvo.percent" cssClass="fxt w150 number"></s:textfield>
                </div>
              </li>
              <li id="li_amount">
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="ccm.CancellationRules.DeductedAmountByFixedAmount"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_amount" name="cancelPolicyvo.amount" cssClass="fxt w150 number"></s:textfield>
                </div>
              </li>
              <li id="li_taxInclusive">
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.CancellationRules.TaxInclusive"/>：</span></div>
                <div class="i_input">
                  <label class="checkbox inline">
                    <s:checkbox id="f_taxInclusive" name="cancelPolicyvo.taxInclusive"/>
                  </label>
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
<script type="text/javascript">
$(document).ready(function() {
	
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
	//日历显示
	$('#f_absoluteTime').datetimepicker($.extend(dpconfig,{}));
	
	//设置取消规则代码不能编辑
	if(null!=$('#f_cancelId').val() && ''!=$('#f_cancelId').val()){
		$('#f_cancelPolicyCode').attr("readonly", true);
	}
	
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
	
	//保存
	$('.f_save').click(function(){
		//验证表单
		if(!$("#cancelForm").valid()){
			return;
		}
		
		//验证多语言并且重组数据 
		var flag = executeMoreLanguage();
		if(!flag){
			return;
		}
		
		//设置radio绝对时间/相对时间
		var calcelDeadline = $('input:radio[name="calcelDeadline"]:checked').val();
		if(calcelDeadline==0){//清除相对
			$('#li_offsetDropTime input').val('');
			$('#li_offsetDropTime select').val('');
			
			//校验绝对时间
			var absoluteTime = $('#f_absoluteTime').val();
			if(!strIsNull(absoluteTime)){
				var absoluteTimeCode = validateYYYYMMDDHHmm(absoluteTime);
				if(absoluteTimeCode!='success'){
					alert(getErrorMsg(absoluteTimeCode,'<fmt:message key="ccm.CancellationRules.AbsoluteTime"/>','yyyy-MM-DD HH:mm'));
					return ;
				}else if(isMorethanNow(absoluteTime)=='false'){
					alert('<fmt:message key="ccm.error.047"/>');
					return ;
				}
			}
		}else{//清楚绝对
			$('#li_absoluteTime input').val('');
		}
		
		if ($('#f_feesInclusive').is(":checked") && $("#f_basisType").val()!="3"){
			if($('#f_numberOfNights').val()=="" && $('#f_percent').val()=="" && $('#f_amount').val()=="") {
				alert("<fmt:message key='ccm.error.048'/>");
				return;
			}			
			var i=0;
			if($('#f_numberOfNights').val()=="") {
				i++;
			}
			if($('#f_percent').val()=="") {
				i++;
			}
			if($('#f_amount').val()=="") {
				i++;
			}
			if(i!=2){
				alert("<fmt:message key='ccm.error.049'/>");
				return;
			}
		}
		
		//修改
		if($('#f_cancelId').val().length>0){
			$("#cancelForm").submit();
			//禁止重复提交
			 $('.f_save').addClass('no_ald');
			 $('.f_save').attr("disabled","disabled");	
			return;
		}
		//新增时验证取消规则代码
		$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"cancel_isCancelPolicyCode.do",
	   	  data:{"cancelPolicyvo.cancelPolicyCode":$('#f_cancelPolicyCode').val()},
		  success:function(data){
			  if("false" == data){
				 alert("<fmt:message key='ccm.error.050'/>");
		  	  }else{
		  		 $("#cancelForm").submit();
		  		//禁止重复提交
				 $('.f_save').addClass('no_ald');
				 $('.f_save').attr("disabled","disabled");	
		  	  }
	       }
	    });
	});
	
	//不能取消
	checkboxIsNonRefundable()
	$('#f_isNonRefundable').click(function(){
		checkboxIsNonRefundable();
	});
	
	//取消期限
	if (!$('#f_isNonRefundable').is(":checked")) {
		//单选框选中设置
		if($('#f_absoluteTime').val()==''){
			$('input:radio[name="calcelDeadline"]:last').attr('checked','true');
			radioChange();
		}
		if($('#f_offsetDropTime').val()=='' && $('#f_offsetUnitMultiplier').val()=='' && $('#f_offsetTimeUnit').val()==''){
			$('input[type=radio][name="calcelDeadline"]')[0].checked = true;
			radioChange();
		}
	}
	$('input:radio[name="calcelDeadline"]').change(function(){
		radioChange();
	});
	
	//是否扣款
	if('${cancelPolicyvo.cancelId}'==''){
		$('#f_feesInclusive').attr("checked",'true');
		$('#f_feesInclusive').val(true);
	}else if($('#f_basisType').val()!="" || $('#f_numberOfNights').val()!="" || $('#f_percent').val()!="" || $('#f_amount').val()!=""){
		$('#f_feesInclusive').attr("checked",'true');
		$('#f_feesInclusive').val(true);
	}
	checkboxFeesInclusive();
	$('#f_feesInclusive').click(function(){
		checkboxFeesInclusive();
	});
	
});
//不能取消
function checkboxIsNonRefundable(){
	$('#f_absoluteTime').removeClass('required');
	$('#f_offsetDropTime').removeClass('required');
	$('#f_offsetUnitMultiplier').removeClass('required');
	$('#f_offsetTimeUnit').removeClass('required');
	$('#f_basisType').removeClass('required');
	if ($('#f_isNonRefundable').is(":checked")) {
		$('input[type=radio][name="calcelDeadline"]')[0].checked = false;
		$('input[type=radio][name="calcelDeadline"]')[1].checked = false;
		$('#f_absoluteTime').val('');
    	$('#f_offsetDropTime').val('');
    	$('#f_offsetUnitMultiplier').val('');
    	$('#f_offsetTimeUnit').val('');
        $('#li_cancelDeadline').hide();
        $('#li_absoluteTime').hide();
        $('#li_offsetDropTime').hide();
        $('#f_feesInclusive').removeAttr("checked");
        $('#f_feesInclusive').val(false);
        $('#li_feesInclusive').hide();
    }else{
    	$('input[type=radio][name="calcelDeadline"]')[0].checked = true;
    	$('#f_absoluteTime').addClass('required');
    	$('#li_cancelDeadline').show();
    	$('#li_absoluteTime').show();
    	$('#li_offsetDropTime').hide();
    	$('#li_feesInclusive').show();
    }
	checkboxFeesInclusive();
}
//是否扣款
function checkboxFeesInclusive(){
	if ($('#f_feesInclusive').is(":checked")) {
		$('#li_basisType').show();
		$('#f_basisType').addClass('required');
		$('#li_roomNight').show();
		$('#li_percent').show();
		$('#li_amount').show();
		$('#li_taxInclusive').show();
   }else{
		$('#f_basisType').val('');
		$('#f_numberOfNights').val('');
		$('#f_percent').val('');
		$('#f_amount').val('');
		$('#f_taxInclusive').val(false);
		$('#li_basisType').hide();
		$('#f_basisType').removeClass('required');
		$('#li_roomNight').hide();
		$('#li_percent').hide();
		$('#li_amount').hide();
		$('#li_taxInclusive').hide();
   }
}

function radioChange(){
	var calcelDeadline = $('input:radio[name="calcelDeadline"]:checked').val();
	$('#f_absoluteTime').removeClass('required');
	$('#f_offsetDropTime').removeClass('required');
	$('#f_offsetUnitMultiplier').removeClass('required');
	$('#f_offsetTimeUnit').removeClass('required');
	if(calcelDeadline==0){
		$('#f_offsetDropTime').val('');
		$('#f_offsetUnitMultiplier').val('');
		$('#f_offsetTimeUnit').val('');
		$('#li_absoluteTime').show();
		$('#li_offsetDropTime').hide();
		$('#f_absoluteTime').addClass('required');
	}else if(calcelDeadline==1){
		$('#f_absoluteTime').val('');
		$('#li_absoluteTime').hide();
		$('#li_offsetDropTime').show();
		$('#f_offsetDropTime').addClass('required');
		$('#f_offsetUnitMultiplier').addClass('required');
		$('#f_offsetTimeUnit').addClass('required');
	}
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
	$('#ml_switch_policyName').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_policyName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var name = $(this).find('input[name="language.name"]').val();
			
			var selStr = ','+sel.val()+',';
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.CancellationRules.CancellationRulesName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【取消规则名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			
			//判断语言种类是否已重复
			if(tempNameCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.CancellationRules.CancellationRulesName"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【取消规则名称】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			
			//如果未填写名称 
			if(strIsNull(name)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.CancellationRules.CancellationRulesName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
				alert(i18n_replace(str,arry));
				//alert('【取消规则名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
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
				arry.push('<fmt:message key="ccm.CancellationRules.CancellationRulesName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【描述】多语言的语种:【'+sel.find("option:selected").text()+'】在【取消规则名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}

			//拼接语言种类
			tempDescCodes += selStr;
			
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',description:'"+escapeAcutes(description)+"'}";
		}
	});

	//如果校验通过
	if(flag){
		//如果不为空,拼接成json
		if(!strIsNull(moreLanguagesJson)){
			moreLanguagesJson = '['+moreLanguagesJson.substring(1)+']';
			$('#f_cancelPolicyI18ns').val(moreLanguagesJson);
		}
	}

	return flag;
}
</script>