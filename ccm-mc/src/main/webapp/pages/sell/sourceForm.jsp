<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.Source.SourceEstablishment"/> </div>
        <s:form id="sourceForm" action="/source_save.do" method="post">
        <appfuse:ccmToken name="token"></appfuse:ccmToken>
        <s:hidden id="f_sourceId" name="sourcevo.sourceId"/>
        <s:hidden id="f_sourceMId" name="sourcevo.sourceMId"/>
        <s:hidden id="f_sourceI18ns" name="f_sourceI18ns" />
        <div class="c_whitebg pdA12">
          <div class="mgB24">
            <ul class="list_input">
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.Source.SourceCode"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_sourceCode" name="sourcevo.sourceCode" cssClass="fxt w150 required"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.ValidTime"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_effectiveDate" cssClass="fxt w150" name="sourcevo.effectiveDate" ></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.InvalidTime"/>	：</span></div>
                <div class="i_input">
                  <s:textfield id="f_expireDate" cssClass="fxt w150" name="sourcevo.expireDate" ></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.Description"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_description" name="sourcevo.description" cssClass="fxt w360 h80"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_description"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_description" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty sourcevo.sourceI18nList}">
							<c:forEach items="${sourcevo.sourceI18nList}" var="sourceI18n" varStatus="vstatus"> 
								<c:if test="${not empty sourceI18n.description }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == sourceI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="common.Description"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.description" >${sourceI18n.description}</textarea>
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
								<fmt:message key="common.select.plesesSelect"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
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
	//日期显示
	var dpconfig = {
		showMonthAfterYear : true,
		dateFormat : "yy-mm-dd",
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
	$("#f_effectiveDate").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			$("#f_expireDate").datepicker("option", "minDate", v);
		}
	}));
	$("#f_expireDate").datepicker($.extend(dpconfig, {
		onClose : function(v) {
			$("#f_effectiveDate").datepicker("option", "maxDate", v);
		}
	}));
	
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
	
	//设置来源编码不能编辑
	if(null!=$('#f_sourceId').val() && ''!=$('#f_sourceId').val()){
		$('#f_sourceCode').attr("readonly", true);
	}
	
	//保存
	$('.f_save').click(function(){
		//验证表单
		if(!$("#sourceForm").valid()){
			return;
		}
		
		//生效时间
		var effectiveDate = $('#f_effectiveDate').val();
		if(!strIsNull(effectiveDate)){
			var effectiveDateCode = validateYYYYMMDD(effectiveDate);
			if(effectiveDateCode!='success'){
				alert(getErrorMsg(effectiveDateCode,'<fmt:message key="common.ValidTime"/>','yyyy-MM-DD'));
				return ;
			}
		}
		
		//失效时间
		var expireDate = $('#f_expireDate').val();
		if(!strIsNull(expireDate)){
			var expireDateCode = validateYYYYMMDD(expireDate);
			if(expireDateCode!='success'){
				alert(getErrorMsg(expireDateCode,'<fmt:message key="common.InvalidTime"/>','yyyy-MM-DD'));
				return ;
			}
		}
		
		//验证多语言并且重组数据 
		var flag = executeMoreLanguage();
		
		if(!flag){
			return;
		}
		
		//修改
		if($('#f_sourceId').val().length>0){
			$("#sourceForm").submit();
			//禁止重复提交
	  		$('.f_save').addClass('no_ald');
	  		$('.f_save').attr("disabled","disabled");	
			return;
		}
		//新增时验证来源代码
		$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"source_isSourceCode.do",
	   	  data:{"sourcevo.sourceCode":$('#f_sourceCode').val()},
		  success:function(data){
			  if("false" == data){
				 alert("来源代码不能和已有代码重复，请重新输入！");
		  	  }else{
		  		 $("#sourceForm").submit();
		  		//禁止重复提交
			  	$('.f_save').addClass('no_ald');
			  	$('.f_save').attr("disabled","disabled");	
		  	  }
	       }
	    });
	});
});

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
				alert('【描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			
			//判断语言种类是否已重复
			if(tempDescCodes.indexOf(selStr)>=0){
				alert('【描述】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
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
			$('#f_sourceI18ns').val(moreLanguagesJson);
		}
	}

	return flag;
}

</script>