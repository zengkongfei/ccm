function validateDateFormart(thi){
	var dateStr = thi.val();
	if(!strIsNull(dateStr)){
		if($(thi).attr("name") == 'rp.effectiveDate'){
			if(isMorethanNow(dateStr)=='false'){
				alert('该时间不能小于今天');
				$(thi).val('');
				$(thi).focus();
			}
		}
		
		var expireDateCode = validateYYYYMMDD(dateStr);
		if(expireDateCode!='success'){
			alert(getErrorMsg(expireDateCode,'','yyyy-MM-DD'));
			$(thi).val('');
			$(thi).focus();
		}else{
			$(thi).val(addHRToStr(dateStr));
		}
	}
}

function validateRateplanCode(){
	$.ajax({
	    type: "post",
	    dataType: "text",
	    url: "/roomRate_validateRateplanCode.do",
	    data: "rateplanCode="+$("#rp_ratePlanCode").val(),
	    success: function(data){
	    	if(data!="true"){
	    		alert(rateCodeAlreadyExistedInTheHotel+"！");
	    		$("#rp_ratePlanCode").val("");
			}
	    }
	});
}
function subForm(){
	if ($("#rateForm").valid()) {
		if(strIsNull($("input[type=checkbox][name='ratePlanVO.customList']:checked").val())){
			alert(accessCode);
			return;
		}
		var med ="";
		for(var i=0;i<soldableConditionJson.length;i++){
			if(soldableConditionJson[i].minEvenDay){
				med = soldableConditionJson[i].minEvenDay;
				break;
			}
		}
		if(med ==""){
			alert(MinimumStayThrough);
			return;
		}
		if(!setLanguageJsonArr()){
			return;
		}
		
		var initRtCode="";
		$('#roomTypeCodeDiv input:checked').each(function(){ 
			initRtCode += $(this).val()+',';
		});
		$('#roomTypeCode').val(initRtCode.substr(0,initRtCode.lastIndexOf(',')));
		
		//var effectiveDateCode = validateYYYYMMDD(effectiveDate);
		
		$("#rateForm").submit();
	}
}

//取消规则开始
var cancelRuleJson=[];
function delCancelRuleOne(idx){
	if(confirm(deleteMessage+"？")){
		cancelRuleJson.remove(idx);
		showCancelRule();
	}
}
function editCancelRuleTr(idx,thi){
	var cancelRuleOneJson = (cancelRuleJson[idx]);
	$('.CancelRuleNew select[name="cancelId"]').attr("idx",idx);
	$('.CancelRuleNew select[name="cancelId"]').val(cancelRuleOneJson.cancelId);
	$('.CancelRuleNew input[name="effectiveDate"]').val(cancelRuleOneJson.effectiveDate);
	$('.CancelRuleNew input[name="expireDate"]').val(cancelRuleOneJson.expireDate);

	var l_ps=$(thi).position().left;
	var r_ps=$(window).width()-l_ps-45;
	var t_ps=$(thi).position().top+16;
	var t_ps2=$(thi).position().top+24;
	$('.CancelRuleNew').slideDown();
	if($(this).hasClass('blue')){
		$('.CancelRuleNew').css({left:'',right:r_ps,top:t_ps2});
	}
	else{
		$('.CancelRuleNew').css({left:l_ps,right:'',top:t_ps});
	}
	
	$('.CancelRuleNew input[type="checkbox"]').each(function(){
		var isApplyToWeek = $(this).attr("id");
		if(cancelRuleOneJson[isApplyToWeek]){
			$(this).prop("checked","true");
		}else{
			$(this).prop("checked","");
		}
	});
}
function showCancelRule(){
	$("#cancelRuleTable").empty();
	for(var i=0;i< cancelRuleJson.length;i++){
		var cancelRuleOneJson = (cancelRuleJson[i]);
		var cancelRuleTr = "";
		cancelRuleTr +='<tr><td class="w180">'+cancelRuleOneJson.effectiveDate +' ~ '+cancelRuleOneJson.expireDate+'</td>'
		cancelRuleTr +='<td class="w180">'+getWeekStr(cancelRuleOneJson) +'</td>'
		cancelRuleTr +='<td><a href="javascript:;" onclick="editCancelRuleTr('+i+',this);" class="link">'+cancelRuleOneJson.policyName+'</a></td>'
		cancelRuleTr +='<td class="w20"><div class="center"><a href="javascript:delCancelRuleOne('+i+');" class="link_1">x</a></div></td></tr>'
		$("#cancelRuleTable").append(cancelRuleTr);
	}
	
	$("#cancelRuleJsonArr").val(JSON.stringify(cancelRuleJson));
}
//取消规则结束

//担保规则开始
var guaranteeJson=[];
function delGuaranteeOne(idx){
	if(confirm(deleteMessage+"？")){
		guaranteeJson.remove(idx);
		showGuarantee();
	}
}
function editGuaranteeTr(idx,thi){
	var guaranteeOneJson = (guaranteeJson[idx]);
	$('.GuaranteeRulesNew select[name="guaranteeId"]').attr("idx",idx);
	$('.GuaranteeRulesNew select[name="guaranteeId"]').val(guaranteeOneJson.guaranteeId);
	$('.GuaranteeRulesNew input[name="effectiveDate"]').val(guaranteeOneJson.effectiveDate);
	$('.GuaranteeRulesNew input[name="expireDate"]').val(guaranteeOneJson.expireDate);

	var l_ps=$(thi).position().left;
	var r_ps=$(window).width()-l_ps-45;
	var t_ps=$(thi).position().top+16;
	var t_ps2=$(thi).position().top+24;
	$('.GuaranteeRulesNew').slideDown();
	if($(this).hasClass('blue')){
		$('.GuaranteeRulesNew').css({left:'',right:r_ps,top:t_ps2});
	}
	else{
		$('.GuaranteeRulesNew').css({left:l_ps,right:'',top:t_ps});
	}
	
	$('.GuaranteeRulesNew input[type="checkbox"]').each(function(){
		var isApplyToWeek = $(this).attr("id");
		if(guaranteeOneJson[isApplyToWeek]){
			$(this).prop("checked","true");
		}else{
			$(this).prop("checked","");
		}
	});
}
function showGuarantee(){
	$("#guaranteeTable").empty();
	for(var i=0;i< guaranteeJson.length;i++){
		var guaranteeOneJson = (guaranteeJson[i]);
		var guaranteeTr = "";
		guaranteeTr +='<tr><td class="w180">'+guaranteeOneJson.effectiveDate +' ~ '+guaranteeOneJson.expireDate+'</td>'
		guaranteeTr +='<td class="w180">'+getWeekStr(guaranteeOneJson) +'</td>'
		guaranteeTr +='<td><a href="javascript:;" onclick="editGuaranteeTr('+i+',this);" class="link">'+guaranteeOneJson.policyName+'</a></td>'
		guaranteeTr +='<td class="w20"><div class="center"><a href="javascript:delGuaranteeOne('+i+');" class="link_1">x</a></div></td></tr>'
		$("#guaranteeTable").append(guaranteeTr);
	}
	
	$("#guaranteeJsonArr").val(JSON.stringify(guaranteeJson));
}
//担保规则结束

//添加一项多语言
function addLanguage(t,switch_Id){
	addLanguageRow(t,switch_Id);
}

//移除一行语言
function deleteRow(t,switch_Id){
	deleteLanguageRow(t,switch_Id);
}

function setLanguageJsonArr(){
	//这是房价名称和描述的多语言项
	var languageTRS = $('#ml_switch_description').find('table>tbody>tr:not(:last)');
	var languageRTS = $('#ml_switch_ratePlanName').find('table>tbody>tr:not(:last)');

	//如果长度项都是1
	if(languageTRS.length==1&&languageRTS.length==1){
		$("input[name='languageJsonArr']").val('');
		$('.NameMoreLanguageNew').slideUp();
		$('.DescMoreLanguageNew').slideUp();
		return true;
	}
	
	var moreLanguagesJson = '';
	var tempNameCodes = '';
	var flag = true;
	
	//循环遍历拼接多语言字符串
	languageRTS.each(function(){
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_ratePlanName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var name = $(this).find('input[name="language.name"]').val();
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push(rateCode);
				arry.push($(this).find('td:eq(0)').text());
				var str =multiLanguageType;
				alert(i18n_replace(str,arry));
				//alert('【房价名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			
			//如果未选择语言类型
			if(strIsNull(name)){
				var arry = new Array();
				arry.push(rateCode);
				arry.push($(this).find('td:eq(0)').text());
				var str = multiLanguageName;
				alert(i18n_replace(str,arry));
				//alert('【房价名称】多语言的第'+$(this).find('td:eq(0)').text()+'项的名称未填写,请检查'); 
				flag = false;
				return false;
			}
			
			//判断语言种类是否已重复
			if(tempNameCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push(rateCode);
				arry.push(sel.find("option:selected").text());
				var str = multiLanguageTypeRepeated;
				alert(i18n_replace(str,arry));
				//alert('【房价名称】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			
			//拼接语言种类
			tempNameCodes += ',' + sel.val() + ',';
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',ratePlanName:'"+escapeAcutes(name)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;

	var tempDescCodes = '';
	
	//循环遍历拼接多语言字符串
	languageTRS.each(function(){
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_description'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var description = $(this).find('textarea[name="language.description"]').val();
			var selStr = ','+sel.val()+',';
			
			//如果未选择语言类型
			if(strIsNull(sel.val())){
				var arry = new Array();
				arry.push(descriptionCorporate);
				arry.push($(this).find('td:eq(0)').text());
				var str =multiLanguageType;
				alert(i18n_replace(str,arry));
				//alert('【房价描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的语言种类未选择,请检查'); 
				flag = false;
				return false;
			}
			
			//如果未选择语言类型
			if(strIsNull(description)){
				var arry = new Array();
				arry.push(descriptionCorporate);
				arry.push($(this).find('td:eq(0)').text());
				var str = multiLanguageDescription;
				alert(i18n_replace(str,arry));
				//alert('【房价描述】多语言的第'+$(this).find('td:eq(0)').text()+'项的描述未填写,请检查'); 
				flag = false;
				return false;
			}
			
			//判断语言种类是否已重复
			if(tempDescCodes.indexOf(selStr)>=0){
				var arry = new Array();
				arry.push(descriptionCorporate);
				arry.push(sel.find("option:selected").text());
				var str = multiLanguageTypeRepeated;
				alert(i18n_replace(str,arry));
				//alert('【房价描述】多语言的语种:【'+sel.find("option:selected").text()+'】已重复,每种语言仅能设置一条.');
				flag = false;
				return false;
			}
			//判断
			if(tempNameCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push(rateCode);
				arry.push(descriptionCorporate);
				arry.push(rateCode);
				arry.push(sel.find("option:selected").text());
				var str = multiLanguageDescription;
				alert(i18n_replace(str,arry));
				//alert('【房价名称】多语言与【房价描述】多语言列表不一致,【房价名称】多语言中不存在语种:'+sel.find("option:selected").text()+',请添加.');
				flag = false;
				return false;
			}
			//拼接语言种类
			tempDescCodes += ',' + sel.val() + ',';
			
			moreLanguagesJson += ",{codeNo:'"+escapeAcutes(sel.val())+"',description:'"+escapeAcutes(description)+"'}";
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	//验证 房价名称和房价描述要相匹配
	languageRTS.each(function(){
		//不能加载多语言模型行
		if($(this).attr('id') != 'mdl_switch_ratePlanName'){
			var sel = $(this).find('select[name="language.codeNo"]');
			var selStr = ','+sel.val()+',';
			
			//判断
			if(tempDescCodes.indexOf(selStr)<0){
				var arry = new Array();
				arry.push(rateCode);
				arry.push(descriptionCorporate);
				arry.push(rateCode);
				arry.push(sel.find("option:selected").text());
				var str = multiLanguageDescription;
				alert(i18n_replace(str,arry));
				//alert('【房价名称】多语言与【房价描述】多语言列表不一致,【房价描述】多语言中不存在语种:'+sel.find("option:selected").text()+',请添加.');
				flag = false;
				return false;
			}
		}
	});
	
	//校验不通过,返回
	if(!flag)return flag;
	
	if(flag){
		//如果校验通过
		if(!strIsNull(moreLanguagesJson)){
			moreLanguagesJson = '['+moreLanguagesJson.substring(1)+']';
			$("input[name='languageJsonArr']").val(moreLanguagesJson);
			$('.NameMoreLanguageNew').slideUp();
			$('.DescMoreLanguageNew').slideUp();
		}
	}
	
	return flag;
}

//AccessCode
function showAccessCode(){
	var roomTypeName='';
	$('#Two_show input:checked').next('span').each(function(){ 
		roomTypeName += $(this).find("span.span_roomTypeCode").text()+",";
	});
	$('#Two_click .typeName').text(roomTypeName.substr(0,roomTypeName.lastIndexOf(',')));
}

function validateDateFormart(thi){
	var dateStr = thi.val();
	if(!strIsNull(dateStr)){
		dateStr = addHRToStr(dateStr);
		if($(thi).attr("name") == 'ratePlanVO.rp.effectiveDate'){
			if(isMorethanNow(dateStr)=='false'){
				alert('该时间不能小于今天');
				$(thi).val('');
				$(thi).focus();
			}
		}
		var expireDateCode = validateYYYYMMDD(dateStr);
		if(expireDateCode!='success'){
			alert(getErrorMsg(expireDateCode,'','yyyy-MM-DD'));
			$(thi).val('');
			$(thi).focus();
		}else{
			$(thi).val(dateStr);
		}
	}
}
function convertStrToJson(str){
	return eval("("+str+")");
}
function getWeekStr(guaranteeOneJson){
	var weekStr = "";
	if(guaranteeOneJson.isApplyToMonday){
		weekStr = ","+monday;
	}
	if(guaranteeOneJson.isApplyToTuesday){
		weekStr += ","+tuesday;
	}
	if(guaranteeOneJson.isApplyToWednesday){
		weekStr += ","+wednesday;
	}
	if(guaranteeOneJson.isApplyToThursday){
		weekStr += ","+thursday;
	}
	if(guaranteeOneJson.isApplyToFriday){
		weekStr += ","+friday;
	}
	if(guaranteeOneJson.isApplyToSaturday){
		weekStr += ","+saturday;
	}
	if(guaranteeOneJson.isApplyToSunday){
		weekStr += ","+sunday;
	}
	return weekStr.substr(1);
}

//设置为HTML编辑器
function setHtmlArea(textareaC){
	textareaC.htmlarea({
        toolbar: [
      			  ["html"],
                  ["bold", "italic", "underline", "strikethrough", "|", 
                   	"increaseFontSize","decreaseFontSize","forecolor"],
                  ["p", "h1", "h3", "h5"],
                  ["indent", "outdent"],
                  ["justifyleft", "justifycenter", "justifyright"],
                  ["orderedlist", "unorderedlist"],
                  ["link", "unlink", "horizontalrule"]
              ],
              toolbarText: $.extend({}, jHtmlArea.defaultOptions.toolbarText, {
                    "bold": "fett",
                    "italic": "kursiv",
                    "underline": "unterstreichen"
              })
    });
}

function addHtmlLanguage(t,switch_Id,c_name){
	var len=$(t).parent().parent().parent().children().length-1;
	if(len>=10){
		alert('不超过10条');
		return false;
	}
	
	$(t).parent().parent().before('<tr><td class="w20">'+len+'.</td>'+$('#mdl_'+switch_Id).html()+'</tr>');
	var newTxtArea = $(t).parent().parent().prev('tr').find('textarea[name="'+c_name+'"]');
	setHtmlArea(newTxtArea);
}
