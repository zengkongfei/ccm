function getAfterCurDate(d, date) {
	var y = "";
	var m = "";
	if (date == "yyyy年MM月") {
		return false;
	} else if(date.indexOf("/")>0){
		y = date.substr(0, date.indexOf("/"));
		m = date.substr(date.indexOf("/") + 1, 2);
	}else {
		y = date.substr(0, date.indexOf("年"));
		m = date.substr(date.indexOf("年") + 1, 2);
	}
	
	//获取当前时间
	var now = new Date();
	var currDate = new Date(now.getFullYear()+'/'+(now.getMonth()+1)+'/'+now.getDate());
	var date2 = new Date(y+"-"+m+"-"+d);

	if(date2 < currDate){
		return false;
	}
	return true;
}
function isNowYear(year) {
	var currDate = new Date();
	var currY = currDate.getFullYear();
	if (currY == year) {
		return true;
	} else {
		return false;
	}
}

//过滤非整数数字
function filterNumInput(e,inputValue){
	var char_code = e.charCode ? e.charCode : e.keyCode;
	
	//如果是左右方向键和删除键
	if(char_code == 37||char_code==39||char_code==8){
		return true;
	}
	//如果是0-9
	if(char_code>=48&&char_code<=57){
		//如果第一位输入0
		if(inputValue == '0'){
			return false;
		}
		return true;
	}
	
	return false;
}

//过滤非数字
function filterFloatInput(e,inputValue){
	
	var char_code = e.charCode ? e.charCode : e.keyCode;
	//如果是左右方向键和删除键
	if(char_code == 37||char_code==39||char_code==8){
		return true;
	}
	//如果是0-9
	if(char_code>=48&&char_code<=57){
		//如果第一位输入0
		if(inputValue == '0'){
			return false;
		}
		return true;
	}
	
	//如果输入了.
	if(char_code == 190){
		//如果作为第一位 
		if(inputValue == ''){
			return false;
		}else if(inputValue.indexOf('.')>=0){
			return false;
		}
		return true;
	}
	return false;
}

function setOnlyFloatValue(input){
	var inputValue = input.val();
	if(inputValue.indexOf('.') == 0){
		input.val('0'+inputValue);
	}
	if(inputValue.indexOf('.') == inputValue.length-1){
		input.val(inputValue.substring(0,inputValue.length-1));
	}
}

function escapeAcutes(str){
	str = str.replace(/[\']/g,"\\'");
	return str;
}

//添加一项多语言
function addLanguageRow(t,switch_Id){
	var len=$(t).parent().parent().parent().children().length-1;
	if(len>=10){
		alert('不超过10条');
		return false;
	}
	
	$(t).parent().parent().before('<tr><td class="w20">'+len+'.</td>'+$('#mdl_'+switch_Id).html()+'</tr>');
}

//移除一行语言
function deleteLanguageRow(t,switch_Id){
	$(t).parent().parent().parent().remove();
	
	var i = 1;
	//重新编号
	$('#ml_'+switch_Id).find('table>tbody>tr:not(:last)').each(function(){
		if(!$(this).is(':hidden')){
			$(this).find('td:eq(0)').text(i+'.');
			i++;
		}
	});
}

function forceNum(thi){
	if(!/^[-]?\d+\.?\d{1,2}$|^[-]?\d+$/.test(thi.value)){
		alert('只能输入数字，且最多2位小数!');
		thi.value='';
	}
}

