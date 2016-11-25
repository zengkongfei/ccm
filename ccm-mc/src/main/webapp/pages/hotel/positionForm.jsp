<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
        <div class="title_wp"><fmt:message key="ccm.LandmarkList.LandmarkCreation"/> </div>
        <s:form id="positionForm" action="/position_save.do" method="post">
        <appfuse:ccmToken name="token"></appfuse:ccmToken>
        <s:hidden id="f_positionId" name="positionvo.positionId"/>
        <s:hidden id="f_relativePositionMId" name="positionvo.relativePositionMId"/>
        <s:hidden id="f_positionI18ns" name="f_positionI18ns" />
        <div class="c_whitebg pdA12">
          <div class="mgB24">
            <ul class="list_input">
              <li>
                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.LandmarkList.LandmarkName"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_name" name="positionvo.name" cssClass="fxt w240 required"></s:textfield>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_name"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_name" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty positionvo.positionI18nList}">
							<c:forEach items="${positionvo.positionI18nList}" var="positionI18" varStatus="vstatus"> 
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == positionI18.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select>&nbsp;&nbsp;
										<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name" 
													value="${positionI18.name}" /> 
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
								<fmt:message key="common.Name"/>:<input type="text" class="fxt w180 " style="margin-top:5px;margin-bottom:5px;" name="language.name"/> 
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
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.LandmarkList.Longitude"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_longitude" name="positionvo.longitude" cssClass="fxt w150 onlyFloat"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="ccm.LandmarkList.Latitude"/>：</span></div>
                <div class="i_input">
                  <s:textfield id="f_latitude" name="positionvo.latitude" cssClass="fxt w150 onlyFloat"></s:textfield>
                </div>
              </li>
              <li>
                <div class="i_title"><span class=""></span><span class="text"><fmt:message key="common.Description"/>：</span></div>
                <div class="i_input">
                  <s:textarea id="f_description" name="positionvo.description" cssClass="fxt w360 h80"></s:textarea>
                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_description"><fmt:message key="common.MultipleLanguagesSetup"/></button>
                </div>
              </li>
              <li id="ml_switch_description" style="display:none;">
              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
					<table class="ccm_table2" style="width: 500px;">
						<c:if test="${not empty positionvo.positionI18nList}">
							<c:forEach items="${positionvo.positionI18nList}" var="positionI18n" varStatus="vstatus"> 
								<c:if test="${not empty positionI18n.description }">
								<tr>
								    <td class="w20">${vstatus.index + 1}.</td>
									<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
												<option value=""><fmt:message key="common.select.plesesSelect"/></option>
												<c:forEach items="${languageList}" var="lan" >
													<option value="${lan.codeNo}" ${lan.codeNo == positionI18n.languageCode?"selected":""}>${lan.codeLabel}</option>
												</c:forEach>
											</select> <br>
										<fmt:message key="common.Description"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
												name="language.description" >${positionI18n.description}</textarea>
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
	
	$('.onlyFloat').keydown(function(e){
		return filterFloatInput(e,$(this).val());
	});
	
	$('.onlyFloat').blur(function(e){
		setOnlyFloatInput($(this));
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
	
	//保存
	$('.f_save').click(function(){
		
		//验证多语言并且重组数据 
		var flag = executeMoreLanguage();
		
		if(!flag){
			return;
		}
		
		//验证表单
		if(!$("#positionForm").valid()){
			return;
		}
		$("#positionForm").submit();
		//禁止重复提交
		$('.f_save').addClass('no_ald');
		$('.f_save').attr("disabled","disabled");
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
	$('#ml_switch_name').find('table>tbody>tr:not(:last)').each(function(){
		
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_name'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var name = $(this).find('input[name="language.name"]').val();
			
			var selStr = ','+sel.val()+',';
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.LandmarkList.LandmarkName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
				alert(i18n_replace(str,arry));
				//alert('【地标名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			
			//判断语言种类是否已重复
			if(tempNameCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.LandmarkList.LandmarkName"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
				alert(i18n_replace(str,arry));
				//alert('【地标名称】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			
			//如果未填写名称 
			if(strIsNull(name)){
				var arry = new Array();
				arry.push('<fmt:message key="ccm.LandmarkList.LandmarkName"/>');
				arry.push($(this).find('td:eq(0)').text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageName"/>';
				alert(i18n_replace(str,arry));
				//alert('【地标名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查.');  
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
				arry.push('<fmt:message key="ccm.LandmarkList.LandmarkName"/>');
				arry.push('<fmt:message key="common.Description"/>');
				arry.push('<fmt:message key="ccm.LandmarkList.LandmarkName"/>');
				arry.push(sel.find("option:selected").text());
				var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeListError"/>';
				alert(i18n_replace(str,arry));
				//alert('【描述】多语言的语种:【'+sel.find("option:selected").text()+'】在【地标名称】多语言中未设置,请检查.');
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
			$('#f_positionI18ns').val(moreLanguagesJson);
		}
	}

	return flag;
}
</script>
