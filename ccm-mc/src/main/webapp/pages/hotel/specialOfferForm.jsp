<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="title_wp"><fmt:message key="ccm.HotelPromotions.EditHotelPromotions"/></div>
<%@ include file="/common/messages.jsp"%>
<s:form id="specialOfferForm" action="/specialOffer_save.do" method="post">
<appfuse:ccmToken name="token"></appfuse:ccmToken>
	<input type="hidden" id="f_specialOfferId" name="specialOfferVO.specialOfferId" 
		value="${specialOfferVO.specialOfferId}" />
	<s:hidden id="f_specialOfferI18ns" name="f_specialOfferI18ns" />
	<div class="c_whitebg pdA12">
		<div class="mgB24">
			<ul class="list_input">
				<!--  
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text">酒店：</span>
					</div>
					<div class="i_input">
						<select id="i_hotelId" name="specialOfferVO.hotelId" class="fxt w180 required">
							<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelId}" ${specialOfferVO.hotelId == hotel.hotelId?"selected":""}
								>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
						<c:if test="${not empty specialOfferVO.hotelId}">
              				<input type="hidden" id="f_hotelId" name="specialOfferVO.hotelId" value="${specialOfferVO.hotelId}" />
        				</c:if>
					</div>
				</li>
				-->
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="common.time.BeginDate"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="beginTime" cssClass="fxt w180 required" key="specialOfferVO.beginTime" ></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="common.time.EndDate"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="endTime" cssClass="fxt w180 required" key="specialOfferVO.endTime" ></s:textfield>
					</div>
				</li>
				
				
				<li>
	                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="ccm.HotelPromotions.Overview"/>：</span></div>
	                <div class="i_input">
	                  <s:textarea id="f_summary" name="specialOfferVO.summary" cssClass="fxt w360 h80 required"></s:textarea>
	                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_summary"><fmt:message key="common.MultipleLanguagesSetup"/></button>
	                </div>
              	</li>
	            <li id="ml_switch_summary" style="display:none;">
	              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
						<table class="ccm_table2" style="width: 500px;">
							<c:if test="${not empty specialOfferVO.specialOfferI18nList}">
								<c:forEach items="${specialOfferVO.specialOfferI18nList}" var="specialOfferI18n" varStatus="vstatus"> 
									<c:if test="${not empty specialOfferI18n.summary }">
									<tr>
									    <td class="w20">${vstatus.index + 1}.</td>
										<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
													<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
													<c:forEach items="${languageList}" var="lan" >
														<option value="${lan.codeNo}" ${lan.codeNo == specialOfferI18n.language?"selected":""}>${lan.codeLabel}</option>
													</c:forEach>
												</select> <br>
											<fmt:message key="ccm.HotelPromotions.Overview"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
													name="language.summary" >${specialOfferI18n.summary}</textarea>
										</td>
										<td class="w20">
											<div class="center">
												<a href="javascript:void[0];" onclick="deleteRow(this,'switch_summary');" class="link_1 del_ifself">x</a>
											</div>
										</td>
									</tr>
									</c:if>	
								</c:forEach>
							</c:if>
							<tr id="mdl_switch_summary" style="display:none;">  
								<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
											<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
											<c:forEach items="${languageList}" var="lan" >
												<option value="${lan.codeNo}">${lan.codeLabel}</option>
											</c:forEach>
										</select> <br>
									<fmt:message key="ccm.HotelPromotions.Overview"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
													name="language.summary" ></textarea>
								</td>
								<td class="w20">
								<div class="center">
									<a href="javascript:void[0];" onclick="deleteRow(this,'switch_summary');" class="link_1 del_ifself">x</a>
								</div>
								</td>		
							</tr>		
							<tr>
								<td class="w20">&nbsp;</td>
								<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_summary')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
								</td>
								<td class="w20">&nbsp;</td>
							</tr>
						</table>		
					  </div>
	             </li>
	             <li>
	                <div class="i_title"><span class="star"></span><span class="text"><fmt:message key="common.Detail"/>：</span></div>
	                <div class="i_input">
	                  <s:textarea id="f_detail" name="specialOfferVO.detail" cssClass="fxt w360 h80 required"></s:textarea>
	                  &nbsp;<button type="button" class="btn_3 white mgR6 moreLanguageSwitch" id="switch_detail"><fmt:message key="common.MultipleLanguagesSetup"/></button>
	                </div>
              	 </li>
	             <li id="ml_switch_detail" style="display:none;">
	              	<div style="margin-left:172px;width: 500px;border:#c1cfd9 1px solid;">
						<table class="ccm_table2" style="width: 500px;">
							<c:if test="${not empty specialOfferVO.specialOfferI18nList}">
								<c:forEach items="${specialOfferVO.specialOfferI18nList}" var="specialOfferI18n" varStatus="vstatus"> 
									<c:if test="${not empty specialOfferI18n.detail }">
									<tr>
									    <td class="w20">${vstatus.index + 1}.</td>
										<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;">
													<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
													<c:forEach items="${languageList}" var="lan" >
														<option value="${lan.codeNo}" ${lan.codeNo == specialOfferI18n.language?"selected":""}>${lan.codeLabel}</option>
													</c:forEach>
												</select> <br>
											<fmt:message key="common.Detail"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
													name="language.detail" >${specialOfferI18n.detail}</textarea>
										</td>
										<td class="w20">
											<div class="center">
												<a href="javascript:void[0];" onclick="deleteRow(this,'switch_detail');" class="link_1 del_ifself">x</a>
											</div>
										</td>
									</tr>
									</c:if>	
								</c:forEach>
							</c:if>
							<tr id="mdl_switch_detail" style="display:none;">  
								<td><fmt:message key="common.Language"/>:<select id="f_languageId" name="language.codeNo" class="fxt " style="width:139px;margin-top:5px;" >
											<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
											<c:forEach items="${languageList}" var="lan" >
												<option value="${lan.codeNo}">${lan.codeLabel}</option>
											</c:forEach>
										</select> <br>
									<fmt:message key="common.Detail"/>:<textarea  class="fxt w360 h80" style="margin-top:5px;margin-bottom:5px;" 
													name="language.detail" ></textarea>
								</td>
								<td class="w20">
								<div class="center">
									<a href="javascript:void[0];" onclick="deleteRow(this,'switch_detail');" class="link_1 del_ifself">x</a>
								</div>
								</td>		
							</tr>		
							<tr>
								<td class="w20">&nbsp;</td>
								<td><a href="javascript:void[0];" class="link" onclick="addLanguage(this,'switch_detail')">+<fmt:message key="common.AddLanguages"/></a> <span class="cl_grey pdL6"><fmt:message key="common.OnlyLanguages"/></span>
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
			<a class="btn_1 white" href="/specialOffer_list.do?menuId=${sessionScope.menuId}"><fmt:message key="common.Return"/></a>
		</div>
	</div>
</s:form>

<script>
	$(document).ready(function() {
		//设置酒店编码不能编辑
		if(!strIsNull($('#f_specialOfferId').val())){
			$('#i_hotelId').attr("disabled", true);
		}
		jQuery.extend(jQuery.validator.messages, {
			required : "<fmt:message key='common.RequiredField'/>"			
		});	
		var dpconfig = {
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
		var start = $("#beginTime");
		var end = $("#endTime");
		start.datepicker($.extend(dpconfig, {
			minDate : new Date(),
			onClose : function(v) {
				end.datepicker("option", "minDate", v);
			},
			onSelect : function(selectedDateTime) {
				end.datetimepicker('option', 'minDate', start.datetimepicker('getDate'));
			}
		}));

		end.datepicker($.extend(dpconfig, {
			minDate : new Date(),
			onClose : function(v) {
				start.datepicker("option", "maxDate", v);
			},
			onSelect : function(selectedDateTime) {
				start.datetimepicker('option', 'maxDate', end.datetimepicker('getDate'));
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
		
		//保存
		$('.f_save').click(function(){
			//验证表单
			if(!$("#specialOfferForm").valid()){
				return;
			}
			
			//上线时间校验
			var beginTime = $("#beginTime").val();
			var beginTimeCode = validateYYYYMMDD(beginTime);
			if(beginTimeCode!='success'){
				alert(getErrorMsg(beginTimeCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
				return false;
			}else if(isMorethanNow(beginTime)=='false'){
				alert('开始时间不能小于当前时间');
				return false;
			}
			
			//下线时间校验
			var endTime = $("#endTime").val();
			var endTimeCode = validateYYYYMMDD(endTime);
			if(endTimeCode!='success'){
				alert(getErrorMsg(endTimeCode,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-DD'));
				return false;
			}else if(isMorethanNow(endTime)=='false'){
				alert('结束时间不能小于当前时间');
				return false;
			}
			
			//验证多语言并且重组数据 
			var flag = executeMoreLanguage();
			
			if(!flag){
				return;
			}
			
			//提交
			$("#specialOfferForm").submit();
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
		$('#ml_switch_summary').find('table>tbody>tr:not(:last)').each(function(){
			
			//不能加载多语言模型行
			if($(this).attr('id') != 'mdl_switch_summary'){
				var sel = $(this).find('select[name="language.codeNo"]');
				var summary = $(this).find('textarea[name="language.summary"]').val();
				
				var selStr = ','+sel.val()+',';
				//如果未选择语言类型
				if(strIsNull(sel.val())){
					
					var arry = new Array();
					arry.push('<fmt:message key="ccm.HotelPromotions.Overview"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
					alert(i18n_replace(str,arry));
					//alert('【概要】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
					flag = false;
					return false;
				}
				
				//判断语言种类是否已重复
				if(tempNameCodes.indexOf(selStr)>=0){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.HotelPromotions.Overview"/>');
					arry.push(sel.find("option:selected").text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
					alert(i18n_replace(str,arry));
					//alert('【概要】多语言的语种:'+sel.find("option:selected").text()+'已重复,每种语言仅能设置一条.');
					flag = false;
					return false;
				}
				
				//如果未填写
				if(strIsNull(summary)){
					var arry = new Array();
					arry.push('<fmt:message key="ccm.HotelPromotions.Overview"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageDescription"/>';
					alert(i18n_replace(str,arry));
					//alert('【概要】多语言的第'+$(this).find('td:eq(0)').text()+'项的内容未填写,请检查.');  
					flag = false;
					return false;
				}
					
				//拼接语言种类
				tempNameCodes += selStr;
				
				moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',summary:'"+escapeAcutes(summary)+"'}";
			}
		});
		
		//校验不通过,返回
		if(!flag)return flag;
		
		var tempDescCodes = '';
		//循环遍历拼接多语言字符串
		$('#ml_switch_detail').find('table>tbody>tr:not(:last)').each(function(){
			
			//不能加载多语言模型行
			if($(this).attr('id') != 'mdl_switch_detail'){
				var sel = $(this).find('select[name="language.codeNo"]');
				var detail = $(this).find('textarea[name="language.detail"]').val();
				var selStr = ','+sel.val()+',';
				
				//如果未选择语言类型
				if(strIsNull(sel.val())){
					var arry = new Array();
					arry.push('<fmt:message key="common.Detail"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageType"/>';
					alert(i18n_replace(str,arry));
					//alert('【详情】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
					flag = false;
					return false;
				}
				
				//判断语言种类是否已重复
				if(tempDescCodes.indexOf(selStr)>=0){
					var arry = new Array();
					arry.push('<fmt:message key="common.Detail"/>');
					arry.push(sel.find("option:selected").text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeRepeated"/>';
					alert(i18n_replace(str,arry));
					//alert('【详情】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
					flag = false;
					return false;
				}
				
				//如果未填写
				if(strIsNull(detail)){
					var arry = new Array();
					arry.push('<fmt:message key="common.Detail"/>');
					arry.push($(this).find('td:eq(0)').text());
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageDescription"/>';
					alert(i18n_replace(str,arry));
					//alert('【详情】多语言的第'+$(this).find('td:eq(0)').text()+'项的内容未填写,请检查.');  
					flag = false;
					return false;
				}
				
				//如果不在模板名称多语言范围内 
				if(tempNameCodes.indexOf(selStr)<0){
					var arry = new Array();
					arry.push('<fmt:message key="common.Detail"/>');
					arry.push(sel.find("option:selected").text());
					arry.push('<fmt:message key="ccm.HotelPromotions.Overview"/>');
					var str = '<fmt:message key="ccm.Rates.ErrorMessage.MultiLanguageTypeError"/>';
					alert(i18n_replace(str,arry));
					//alert('【详情】多语言的语种:【'+sel.find("option:selected").text()+'】在【概要】多语言中未设置,请检查.');
					flag = false;
					return false;
				}

				//拼接语言种类
				tempDescCodes += selStr;
				moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',detail:'"+escapeAcutes(detail)+"'}";
			}
		});
		
		//校验不通过,返回
		if(!flag)return flag;
		
		//验证优惠概要和详情要相匹配
		$('#ml_switch_summary').find('table>tbody>tr:not(:last)').each(function(){
			
			//不能加载多语言模型行
			if($(this).attr('id') != 'mdl_switch_summary'){
				var sel = $(this).find('select[name="language.codeNo"]');
				var selStr = ','+sel.val()+',';
				
				//判断
				if(tempDescCodes.indexOf(selStr)<0){
					alert('【详情】多语言与【概要】多语言列表不一致,【优惠详情】多语言中不存在语种:'+sel.find("option:selected").text()+',请添加.');
					flag = false;
					return false;
				}
			}
		});
		
		//校验不通过,返回
		if(!flag)return flag;

		//如果校验通过
		if(flag){
			//如果不为空,拼接成json
			if(!strIsNull(moreLanguagesJson)){
				moreLanguagesJson = '['+moreLanguagesJson.substring(1)+']';
				$('#f_specialOfferI18ns').val(moreLanguagesJson);
			}
		}

		return flag;
	}
	
</script>