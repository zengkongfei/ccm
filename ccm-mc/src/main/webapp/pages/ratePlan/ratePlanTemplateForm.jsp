<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.RatePlanTemplate.RatePlanTemplateEstablishment"/> </div>
        <s:form id="ratePlanTemplateForm" action="/ratePlanTemplate_save.do" method="post">
        <appfuse:ccmToken name="token"></appfuse:ccmToken>
        <s:hidden id="f_ratePlanTemplateId" name="ratePlanTemplateVo.ratePlanTemplateId"/>
        <s:hidden id="f_ratePlanTemplateMId" name="ratePlanTemplateVo.ratePlanTemplateMId"/>
        <s:hidden id="f_ratePlanTemplateI18ns" name="f_ratePlanTemplateI18ns" />
        <div class="c_whitebg pdA12">
          <div class="mgB24">
            <ul class="list_input">
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.RatePlanTemplate.RatePlanTemplateCode"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_ratePlanTemplateCode" name="ratePlanTemplateVo.ratePlanTemplateCode" cssClass="fxt w150 required"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.RatePlanTemplate.RatePlanTemplateName"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_ratePlanTemplateName" name="ratePlanTemplateVo.ratePlanTemplateName" cssClass="fxt w150 required"></s:textfield>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_ratePlanTempName"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_ratePlanTempName" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty ratePlanTemplateVo.ratePlanTempI18nList}">
							<c:forEach items="${ratePlanTemplateVo.ratePlanTempI18nList}" var="ratePlanTempI18n" varStatus="vstatus"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == ratePlanTempI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" 
													value="${ratePlanTempI18n.ratePlanTemplateName}" />
									</td>
									<td class="w20">
										<div class="center">
											<a href="javascript:void[0];" onclick="deleteRow(this,'switch_ratePlanTempName');" class="link_1 del_ifself">x</a>
										</div>
									</td>
								</tr>	
							</c:forEach>
						</c:if>
						<tr id="mdl_switch_ratePlanTempName" style="display:none;">  
							<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
										<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
										<c:forEach items="${languageList}" var="lan" >
											<option value="${lan.codeNo}">${lan.codeLabel}</option>
										</c:forEach>
									</select>&nbsp;&nbsp;
								<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name"/> 
							</td>
							<td class="w20">
							<div class="center">
								<a href="javascript:void[0];" onclick="deleteRow(this,'switch_ratePlanTempName');" class="link_1 del_ifself">x</a>
							</div>
							</td>		
						</tr>		
						<tr>
							<td class="w20">&nbsp;</td>
							<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_ratePlanTempName')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
							</td>
							<td class="w20">&nbsp;</td>
						</tr>
					</table>		
				  </div>
              </li>
              <li>
                <div class="i_title"><span class="text"></span><span class="text"><fmt:message key="common.Description"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_description" name="ratePlanTemplateVo.description" cssClass="fxt w360 h80"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_description"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_description" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty ratePlanTemplateVo.ratePlanTempI18nList}">
							<c:forEach items="${ratePlanTemplateVo.ratePlanTempI18nList}" var="ratePlanTempI18n" varStatus="vstatus"> 
								<c:if test="${not empty ratePlanTempI18n.description }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == ratePlanTempI18n.language?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="common.Description"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.description" >${ratePlanTempI18n.description}</textarea>
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
	//设置房型编码不能编辑
	if(null!=$('#f_ratePlanTemplateId').val() && ''!=$('#f_ratePlanTemplateId').val()){
		$('#f_ratePlanTemplateCode').attr("readonly", true);
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
		if(!$("#ratePlanTemplateForm").valid()){
			return;
		}
		
		//验证多语言并且重组数据 
		var flag = executeMoreLanguage();
		
		if(!flag){
			return;
		}
		
		//修改
		if($('#f_ratePlanTemplateId').val().length>0){
			$("#ratePlanTemplateForm").submit();
			//禁止重复提交
			 $('.f_save').addClass('no_ald');
			 $('.f_save').attr("disabled","disabled");	
			return;
		}
		
		//新增时验证房价代码
		$.ajax({
	   	  type:"POST",
	   	  async:false,
	   	  url:"ratePlanTemplate_isRatePlanTempCode.do",
	   	  data:{"ratePlanTemplateVo.ratePlanTemplateCode":$('#f_ratePlanTemplateCode').val()},
		  success:function(data){
			  if("false" == data){
				 alert("<fmt:message key='ccm.error.033'/>");
		  	  }else{
		  		 $("#ratePlanTemplateForm").submit();
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
	
	var tempNameCodes = '';
	//循环遍历拼接多语言字符串
	$('#ml_switch_ratePlanTempName').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_ratePlanTempName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var name = $(this).find('input[name="language.name"]').val();
			
			var selStr = ','+sel.val()+',';
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.RatePlanTemplate.RatePlanTemplateName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
				alert(i18n_replace(str,arry));
				//alert('【房价模板名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			
			//判断语言种类是否已重复
			if(tempNameCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.RatePlanTemplate.RatePlanTemplateName"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【房价模板名称】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			
			//如果未填写名称 
			if(strIsNull(name)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.RatePlanTemplate.RatePlanTemplateName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
				alert(i18n_replace(str,arry));
				//alert('【房价模板名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
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
				alert('【描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
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
				arry.push('<fmt:message key="ccm.RatePlanTemplate.RatePlanTemplateName"/>');
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
				alert(i18n_replace(str,arry));
				//alert('【描述】多语言的语种:【'+sel.find("option:selected").text()+'】在【房价模板名称】多语言中未设置,请检查.');
				flag = false;
				return false;
			}

			//拼接语言种类
			tempDescCodes += ',' + sel.val() + ',';
			
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',description:'"+escapeAcutes(description)+"'}";
		}
	});

	//如果校验通过
	if(flag){
		//如果不为空,拼接成json
		if(!strIsNull(moreLanguagesJson)){
			moreLanguagesJson = '['+moreLanguagesJson.substring(1)+']';
			$('#f_ratePlanTemplateI18ns').val(moreLanguagesJson);
		}
	}

	return flag;
}
</script>

