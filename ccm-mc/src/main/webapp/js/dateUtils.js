var success = 'success';         //校验成功
var failure = 'failure';         //校验失败
var dateStrNull = 'dateStrNull'; //日期字符串为空
var formatError = "formatError"; //不符合格式
var lengthLt8 = "lengthLt8";     //长度小于8
var monthLT1  = 'monthLT1';    //月份不能小于1
var monthGT12 = 'monthGT12';   //月份大于12
var dayGT28 = 'dayGT28';       //日期大于28
var dayGT29 = 'dayGT29';       //日期大于29
var dayGT30 = 'dayGT30';       //日期大于30
var dayGT31 = 'dayGT31';       //日期大于31
var dayLT1 = 'dayLT1';         //日期小于1
var hour24System = '24';       //采用24小时制
var hour12System = '12';       //采用12小时制
var hourGT23 = 'hourGT23';     //小时大于23
var hourGT11 = 'hourGT11';     //小时大于11
var minuteGT59 = 'minuteGT59'; //分钟大于59
var secondGT59 = 'secondGT59'; //秒钟大于59
var dateCompare = 'date';   //比较年月日
var dateTimeCompare = 'dateTime'; //比较年月日或者时分秒或者所有
var timeCompare = 'time';   //比较年时分秒
var dateTimeGTNow = 'dateTimeGTNow';  //大于当前年月日或者时分秒或者所有
var dateTimeLTNow = 'dateTimeLTNow';  //小于当前年月日或者时分秒或者所有
var equality = 'equality';       //时间相等

//时间格式的正则表达式
var YYYYMMDDReg = /[\d]{4}[\/-]{1}[\d]{1,2}[\/-]{1}[\d]{1,2}/g; 
var YYYYMMDDHHmmReg = /[\d]{4}[\/-]{1}[\d]{1,2}[\/-]{1}[\d]{1,2}\s[\d]{1,2}[:][\d]{1,2}/g;
var YYYYMMDDHHmmssReg = /[\d]{4}[\/-]{1}[\d]{1,2}[\/-]{1}[\d]{1,2}\s[\d]{1,2}[:][\d]{1,2}[:][\d]{1,2}/g;
var HHmmssReg = /[\d]{1,2}[:][\d]{1,2}[:][\d]{1,2}/g;
var HHmmReg = /[\d]{1,2}[:][\d]{1,2}/g;

/**
 * 校验是否为YYYY-MM-DD 日期格式
 * @param dateStr 时间字符串
 * @returns
 */
function validateYYYYMMDD(dateStr){
	//如果日期字符串为空
	if(strIsNull(dateStr)){
		return dateStrNull;
	}
	
	//如果小于8位
	if(dateStr.length < 8){
		return lengthLt8;
	}
	
	dateStr = addHRToStr(dateStr);
	
	var regResult = dateStr.replace(YYYYMMDDReg,'');
	//符合格式
	if(regResult==''){
		var line_one = dateStr.indexOf('-');       //获取第一个横-
		var line_two = dateStr.lastIndexOf('-');   //获取第二个横-
	
		var year_str = dateStr.substring(0,line_one);   //获取年
		var month_str = dateStr.substring(line_one+1,line_two); //获取月
		var day_str = dateStr.substring(line_two+1);  //获取日
		
		//转换成数字格式 
		var year_num = getIntValue(year_str);
		var month_num = getIntValue(month_str);
		var day_num = getIntValue(day_str);
		
		//校验月份
		var monthResult = validateMonth(month_num);
		if(monthResult!=success){
			return monthResult;
		}
		
		//如果日期小于1
		if(day_num<1){
			return dayLT1;
		}
		//校验日期
		return validateDay(year_num,month_num,day_num);
	}else{
		return formatError;
	}
	return failure;
}

/**
 * 校验是否为YYYY-MM-DD HH:mm 日期+时分格式
 * @param dateStr 时间字符串
 * @param hourSys 小时制(默认24小时制)
 * @returns
 */
function validateYYYYMMDDHHmm(dateStr,hourSys){
	//如果日期字符串为空
	if(strIsNull(dateStr)){
		return dateStrNull;
	}
	
	//如果小时制为空,则设置为24小时制
	if(strIsNull(hourSys)){
		hourSys = hour24System;
	}
	
	var regResult = dateStr.replace(YYYYMMDDHHmmReg,'');
	//符合格式
	if(regResult==''){
		var line_one = dateStr.indexOf('-');       //获取第一个横-
		var line_two = dateStr.lastIndexOf('-');   //获取第二个横-
		var space = dateStr.indexOf(' ');          //获取空格
		var colon_one = dateStr.indexOf(':');  //获取第一个冒号

		var year_str = dateStr.substring(0,line_one);   //获取年
		var month_str = dateStr.substring(line_one+1,line_two); //获取月
		var day_str = dateStr.substring(line_two+1,space);  //获取日
		var hour_str = dateStr.substring(space+1,colon_one); //获取小时
		var minute_str = dateStr.substring(colon_one+1);  //获取分钟
		
		//转换成数字格式 
		var year_num = getIntValue(year_str);
		var month_num = getIntValue(month_str);
		var day_num = getIntValue(day_str);

		var hour_num = getIntValue(hour_str);
		var minute_num = getIntValue(minute_str);
		
		//校验月份
		var monthResult = validateMonth(month_num);
		if(monthResult!=success){
			return monthResult;
		}
		
		//如果日期小于1
		if(day_num<1){
			return dayLT1;
		}
		//校验日期
		var dayResult = validateDay(year_num,month_num,day_num);
		if(dayResult!=success){
			return dayResult;
		}
		
		//校验小时
		var hourResult = validateHour(hour_num,hourSys);
		if(hourResult!=success){
			return hourResult;
		}
		
		return validateMinute(minute_num);
	}else{
		return formatError;
	}
	return failure;
}

/**
 * 校验是否为YYYY-MM-DD HH:mm:ss 日期+时分秒格式
 * @param dateStr 时间字符串
 * @param hourSys 小时制(默认24小时制)
 * @returns
 */
function validateYYYYMMDDHHmmss(dateStr,hourSys){
	//如果日期字符串为空
	if(strIsNull(dateStr)){
		return dateStrNull;
	}
	
	//如果小时制为空,则设置为24小时制
	if(strIsNull(hourSys)){
		hourSys = hour24System;
	}
	
	var regResult = dateStr.replace(YYYYMMDDHHmmssReg,'');
	//符合格式
	if(regResult==''){
		var line_one = dateStr.indexOf('-');       //获取第一个横-
		var line_two = dateStr.lastIndexOf('-');   //获取第二个横-
		var space = dateStr.indexOf(' ');          //获取空格
		var colon_one = dateStr.indexOf(':');      //获取第一个冒号
		var colon_two = dateStr.lastIndexOf(':');  //获取第二个冒号
		
		var year_str = dateStr.substring(0,line_one);   //获取年
		var month_str = dateStr.substring(line_one+1,line_two); //获取月
		var day_str = dateStr.substring(line_two+1,space);  //获取日
		
		var hour_str = dateStr.substring(space+1,colon_one); //获取小时
		var minute_str = dateStr.substring(colon_one+1,colon_two);  //获取分钟
		var second_st = dateStr.substring(colon_two+1);  //获取分钟
		
		//转换成数字格式 
		var year_num = getIntValue(year_str);
		var month_num = getIntValue(month_str);
		var day_num = getIntValue(day_str);
		
		var hour_num = getIntValue(hour_str);
		var minute_num = getIntValue(minute_str);
		var second_num = getIntValue(second_st);
		
		//校验月份
		var monthResult = validateMonth(month_num);
		if(monthResult!=success){
			return monthResult;
		}
		
		//如果日期小于1
		if(day_num<1){
			return dayLT1;
		}
		//校验日期
		var dayResult = validateDay(year_num,month_num,day_num);
		if(dayResult!=success){
			return dayResult;
		}
		
		//校验小时
		var hourResult = validateHour(hour_num,hourSys);
		if(hourResult!=success){
			return hourResult;
		}
		
		//校验分钟
		var minuteResult = validateMinute(minute_num);
		if(minuteResult!=success){
			return minuteResult;
		}
		
		//校验秒
		return validateSecond(second_num);
	}else{
		return formatError;
	}
	return failure;
}

/**
 * 校验是否为HH:mm:ss 时分秒格式
 * @param dateStr 时间字符串
 * @param hourSys 小时制(默认24小时制)
 * @returns
 */
function validateHHmmss(dateStr,hourSys){
	//如果日期字符串为空
	if(strIsNull(dateStr)){
		return dateStrNull;
	}
	
	//如果小时制为空,则设置为24小时制
	if(strIsNull(hourSys)){
		hourSys = hour24System;
	}
	
	var regResult = dateStr.replace(HHmmssReg,'');
	//符合格式
	if(regResult==''){
		var colon_one = dateStr.indexOf(':');      //获取第一个冒号
		var colon_two = dateStr.lastIndexOf(':');  //获取第二个冒号
	
		var hour_str = dateStr.substring(0,colon_one); //获取小时
		var minute_str = dateStr.substring(colon_one+1,colon_two);  //获取分钟
		var second_st = dateStr.substring(colon_two+1);  //获取分钟
		
		//转换成数字格式 
		var hour_num = getIntValue(hour_str);
		var minute_num = getIntValue(minute_str);
		var second_num = getIntValue(second_st);

		//校验小时
		var hourResult = validateHour(hour_num,hourSys);
		if(hourResult!=success){
			return hourResult;
		}
		
		//校验分钟
		var minuteResult = validateMinute(minute_num);
		if(minuteResult!=success){
			return minuteResult;
		}
		
		//校验秒
		return validateSecond(second_num);
	}else{
		return formatError;
	}
	return failure;
}

/**
 * 校验是否为HH:mm 时分格式
 * @param dateStr 时间字符串
 * @param hourSys 小时制(默认24小时制)
 * @returns
 */
function validateHHmm(dateStr,hourSys){
	//如果日期字符串为空
	if(strIsNull(dateStr)){
		return dateStrNull;
	}
	
	//如果小时制为空,则设置为24小时制
	if(strIsNull(hourSys)){
		hourSys = hour24System;
	}
	
	var regResult = dateStr.replace(HHmmReg,'');
	//符合格式
	if(regResult==''){
		var colon_one = dateStr.indexOf(':');      //获取第一个冒号
		var hour_str = dateStr.substring(0,colon_one); //获取小时
		var minute_str = dateStr.substring(colon_one+1);  //获取分钟
		
		//转换成数字格式 
		var hour_num = getIntValue(hour_str);
		var minute_num = getIntValue(minute_str);

		//校验小时
		var hourResult = validateHour(hour_num,hourSys);
		if(hourResult!=success){
			return hourResult;
		}
		
		//校验分钟
		return validateMinute(minute_num);
	}else{
		return formatError;
	}
	return failure;
}

/**
 * 添加横线到日期的格式
 * @param oldStr
 * @returns {String}
 */
function addHRToStr(oldStr){
	//如果存在-或者/
	if(oldStr.indexOf('-')>=0 || oldStr.indexOf('/')>=0){
		return oldStr;
	}
	
	//如果小于6或者大于8
	if(oldStr.length != 8 ){
		return oldStr;
	}
	
	var idx1 = 4;
	var idx2 = 6;
	
	if(oldStr.length == 6){
		idx2 = 5;
	}else if(oldStr.length == 7){
		idx2 = 6;
	}
	
	var newStr = oldStr.substring(0,4) + "-" + oldStr.substring(4,idx2) + "-" + oldStr.substring(idx2);
	return newStr;
}

/**
 * 校验是否大于当前时间(自动检测传入的时间格式)
 * @param dateStr
 * @returns
 */
function isMorethanNow(dateStr){
	var result = 'equals';
	//如果是YYYY-MM-DD
	if(dateStr.replace(YYYYMMDDReg,'')==''){
		result = compareNow(dateStr,dateCompare);
		
	//如果是YYYY-MM-DD HH:mm:ss
	}else if(dateStr.replace(YYYYMMDDHHmmssReg,'')==''){
		result = compareNow(dateStr,dateTimeCompare);
		
	//如果是YYYY-MM-DD HH:mm
	}else if(dateStr.replace(YYYYMMDDHHmmReg,'')==''){
		result = compareNow(dateStr+":59",dateTimeCompare);
		
	//如果是HH:mm:ss
	}else if(dateStr.replace(HHmmssReg,'')==''){
		result = compareNow(dateStr,timeCompare);
	
	//如果是HH:mm
	}else if(dateStr.replace(HHmmReg,'')==''){
		result = compareNow(dateStr+":59",timeCompare);
	}
	//如果大于
	if(result == dateTimeGTNow){
		return 'true';
	}else if(result == dateTimeLTNow){
		return 'false';
	}else{
		return 'equals';
	}
}

/**
 * 比较当前的日期或者时间(日期,时间)
 * @param dateStr
 * @param compareType 比较方式 date,time,dateTime (默认比较年月日)
 */
function compareNow(dateStr,compareType){
	//如果比较方式为空
	if(strIsNull(compareType)){
		compareType = dateCompare;
	}

	//获取当前日期
	var now = new Date();
	//比较日期
	if(compareType == dateCompare){
		var nowDate = new Date(now.getFullYear()+'/'+(now.getMonth()+1)+'/'+now.getDate());
		var fd_date = new Date(dateStr.replace(/-/g,'/'));
		
		if(fd_date>nowDate){
			return dateTimeGTNow;
		}else if(fd_date<nowDate){
			return dateTimeLTNow;
		}else{
			return equality;
		}
	//比较年月日时分秒
	}else if(compareType == dateTimeCompare){
		var fd_date = new Date(dateStr.replace(/-/g,'/'));
		
		if(fd_date>now){
			return dateTimeGTNow;
		}else if(fd_date<now){
			return dateTimeLTNow;
		}else{
			return equality;
		}
	//比较时分秒	
	}else if(compareType == timeCompare){
		var nowTimeStr = now.getHours()+''+ now.getMinutes()+''+ now.getSeconds();
		var dateStrNoSpace = dateStr.replace(/:/g,'');
		
		if(dateStrNoSpace>nowTimeStr){
			return dateTimeGTNow;
		}else if(dateStrNoSpace<nowTimeStr){
			return dateTimeLTNow;
		}else{
			return equality;
		}	
	}
}

/**
 * 校验月份是否合法
 * @param month_num 月份
 * @returns
 */
function validateMonth(month_num){
	//如果月份大于12或小于1
	if(month_num>12){
		return monthGT12;
	}else if(month_num<1){
		return monthLT1;
	}
	return success;
}

/**
 * 校验日期是否合法
 * @param year_num  年份
 * @param month_num 月份
 * @param day_num 日期
 * @returns
 */
function validateDay(year_num,month_num,day_num){
	if(day_num<1){
		return dayLT1;
		
	//如果月份是1,3,5,7,8,10,12
	}else if((month_num==1||month_num==3||month_num==5||month_num==7
			||month_num==8||month_num==10||month_num==12)&&day_num>31){
		return dayGT31;
		
	//如果月份是4,6,9,11
	}else if((month_num==4||month_num==6||month_num==9||month_num==11)
			&&day_num>30){
		return dayGT30;
		
	//如果月份是2
	}else if(month_num==2){
		//如果为闰年
		if(isLeapYear(year_num)&&day_num>29){
			return dayGT29;
		}else if(!isLeapYear(year_num)&&day_num>28){
			return dayGT28;
		}
	}
	return success;
}

/**
 * 校验是否为闰年
 * @param year_num
 * @returns
 */
function isLeapYear(year_num){

	/*年份为4的倍数不一定是闰年
	 * (1)年份不是100的倍数但是4的倍数都是闰年 
	 * (2)年份是100的倍数且是400的倍数的年份是闰年 (如1900年不是闰年,而2000年是闰年)
	 */
	return year_num%4==0&&(year_num%100!=0||year_num%400==0);
}

/**
 * 校验小时
 * @param hourSys 小时制
 * @param hour_num
 * @returns
 */
function validateHour(hour_num,hourSys){
	//24小时制
	if(hourSys == hour24System&&hour_num > 23){
		return hourGT23;
	
	//12小时制
	}else if(hourSys == hour12System&&hour_num > 11){
		return hourGT11;
	}
	return success;
}

/**
 * 校验分钟
 * @param minute_num
 * @returns
 */
function validateMinute(minute_num){
	//如果小时大于24
	if(minute_num>59){
		return minuteGT59;
	}
	return success;
}

/**
 * 校验秒钟
 * @param second_num
 * @returns
 */
function validateSecond(second_num){
	//如果小时大于24
	if(second_num>59){
		return secondGT59;
	}
	return success;
}

/**
 * 根据字符串获取整数值
 * @param str
 * @returns
 */
function getIntValue(str){
	if(strIsNull(str)){
		return 0;
	}
	
	return str.replace(/^0+/g,'');
}

/**
 * 字符串是否为空
 * @param dateStr
 */
function strIsNull(str){
	if(str!=null
			&&str.replace(/(^\s*)|(\s*$)/g,'').length>0){
		return false;
	}
	return true;
}

/**
 * 得到错误的提示信息
 * @param errCode 错误码
 * @param controlName 控件名
 * @param format 格式 
 */
function getErrorMsg(errCode,controlName,format){
	if(errCode == dateStrNull){
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError001;
		return i18n_replace(str,arry);
		//return controlName+'不能为空';
	}else if(errCode == formatError){
		var arry = new Array();
		arry.push(controlName);
		arry.push(format);
		var str = resources.DateError020;
		return i18n_replace(str,arry);
		//return controlName+'填写的时间格式有误,格式:'+format;
	}else if(errCode == lengthLt8){
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError003;
		return i18n_replace(str,arry);
//		return controlName+'填写的时间格式有误,长度小于8';
	}else if(errCode == monthLT1){
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError004;
		return i18n_replace(str,arry);
//		return controlName+'填写的月份有误,月份不能小于1';
	}else if(errCode == monthGT12){
//		return controlName+'填写的月份有误,月份不能大于12';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError005;
		return i18n_replace(str,arry);
	}else if(errCode == dayLT1){
//		return controlName+'填写的日期有误,日期不能小于1';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError006;
		return i18n_replace(str,arry);
	}else if(errCode == dayGT28){
//		return controlName+'填写的日期有误,平年的2月份日期不能大于28';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError007;
		return i18n_replace(str,arry);
	}else if(errCode == dayGT29){
//		return controlName+'填写的日期有误,闰年的2月份日期不能大于29';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError008;
		return i18n_replace(str,arry);
	}else if(errCode == dayGT30){
//		return controlName+'填写的日期有误,月份为4,6,9,11时,日期不能大于30';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError009;
		return i18n_replace(str,arry);
	}else if(errCode == dayGT31){
//		return controlName+'填写的日期有误,月份为1,3,5,7,8,10,12时,日期不能大于31';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError010;
		return i18n_replace(str,arry);
	}else if(errCode == hourGT23){
//		return controlName+'填写的小时有误,24小时制的小时数不能超过23';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError011;
		return i18n_replace(str,arry);
	}else if(errCode == hourGT11){
//		return controlName+'填写的小时有误,12小时制的小时数不能超过11';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError12;
		return i18n_replace(str,arry);
	}else if(errCode == minuteGT59){
//		return controlName+'填写的分有误,分钟数不能超过59';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError013;
		return i18n_replace(str,arry);
	}else if(errCode == secondGT59){
//		return controlName+'填写的秒有误,秒钟数不能超过59';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError014;
		return i18n_replace(str,arry);
	}else if(errCode == dateGTNow){
//		return controlName+'填写日期有误,不能大于当前日期';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError015;
		return i18n_replace(str,arry);
	}else if(errCode == dateLTNow){
//		return controlName+'填写日期有误,不能小于当前日期';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError016;
		return i18n_replace(str,arry);
	}else if(errCode == timeGTNow){
//		return controlName+'填写时间有误,不能大于当前时间';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError017;
		return i18n_replace(str,arry);
	}else if(errCode == timeLTNow){
//		return controlName+'填写时间有误,不能小于当前时间';
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError018;
		return i18n_replace(str,arry);
	}else if(errCode == failure ){
		var arry = new Array();
		arry.push(controlName);
		var str = resources.DateError019;
		return i18n_replace(str,arry);
//		return controlName+'填写有误';
	}
}


$(document).ready(function(){
	$('.datetip001').change(function(){
		if(!strIsNull($(this).val())){
			validateDateFormart($(this));
		}
	});
})

function validateDateFormart(thi)
{
	var dateStr = thi.val();	
	var expireDateCode = validateYYYYMMDD(dateStr);
	if(expireDateCode!='success')
	{
		alert(getErrorMsg(expireDateCode,'','YYYYMMDD'));
		$(thi).val('');
		$(thi).focus();
	}
	else
	{
		$(thi).val(addHRToStr(dateStr));
	}
}

function changeStartByEnd(start,end)
{
	if(!strIsNull(start.val()))
	{
		if (start.val() != '') 
		{
			var testStartDate = start.datepicker('getDate');
			var testEndDate = end.datepicker('getDate');
			if (testStartDate > testEndDate)
			{
				var endDate = new Date(testEndDate);
				endDate.setDate(endDate.getDate()-1);
				start.datepicker('setDate', endDate);
			}
		} 
	}
}
function changeEndByStart(start,end)
{
	if(!strIsNull(end.val()))
	{
		if (end.val() != '') 
		{
			var testStartDate = start.datepicker('getDate');
			var testEndDate = end.datepicker('getDate');
			if (testStartDate > testEndDate)
			{
				var startDate = new Date(testStartDate);
				startDate.setDate(startDate.getDate()+1);
				end.datepicker('setDate', startDate);
			}
		}	
	}
}

function validateYYYYMMDDLine(dateStr){
		
		//如果日期字符串为空
		if(strIsNull(dateStr)){
			return dateStrNull;
		}
		
		//如果小于8位
		if(dateStr.length < 8){
			return lengthLt8;
		}
		
		dateStr = addHRToStr(dateStr);
		
		var regResult = dateStr.replace(YYYYMMDDReg,'');
		//符合格式
		if(regResult==''){
			var DATE_FORMAT = /^\d{4}[-]\d{2}[-]\d{2}$/;  	
			if(DATE_FORMAT.test(dateStr)){  		
				return success; 	
	        }else{
	        	return formatError;
	        }  
			
		}else{
			return formatError;
		}
		return failure;
	}



function compareDateStrs(dateStr1,dateStr2){
	dateStr1=dateStr1.replace(/-/g,"/");
	dateStr2=dateStr2.replace(/-/g,"/");
	var d1=Date.parse(dateStr1);
	var d2=Date.parse(dateStr2);
	if(d1>=d2){
		return true;
	}else{
		return false;
	}
}


//计算天数差的函数，参数为YYYY-MM-DD格式
function DateDiff(sDate1,sDate2){    
    var  aDate,  oDate1,  oDate2,  iDays;  
    aDate  =  sDate1.split("-");  
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);    //转换为MM-DD-YYYY格式  
    aDate  =  sDate2.split("-");  
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0]);  
    iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);    //把相差的毫秒数转换为天数  
    return  iDays;  
}

//返回 yyyy-MM-dd格式的当前日期字符串
function nowFormat(){
	// 获取当前日期
	var now = new Date();
	year = now.getFullYear();    // 获取完整的年份(4位,1970-????)
	month = now.getMonth() + 1; // 获取当前月份(0-11,0代表1月)
	day = now.getDate();       // 获取当前日(1-31)
	if (month < 10) {
		month = '0' + month;
	}
	return year + '-' + month + '-' + day;
}

function jtimepicker(start, end){
	start.timepicker({
	    onSelect : function(dateText) {
			if (end.val() != '') {
				var testStartDate = start.datetimepicker('getDate');
				var testEndDate = end.datetimepicker('getDate');
				if (testStartDate >= testEndDate){
					var temp=testStartDate;
					temp.setMinutes(temp.getMinutes()+1);
	                end.timepicker('setDate', temp); 
				}
			} else {
				var temp=start.datetimepicker('getDate');
				temp.setMinutes(temp.getMinutes()+1);
				end.timepicker('setDate', temp);
			}
			end.datetimepicker('option', 'minTime', start.timepicker('getDate'));
		}
	});

	end.timepicker({
	    onSelect : function(dateText) {
			if (start.val() != '') {
				var testStartDate = start.datetimepicker('getDate');
				var testEndDate = end.datetimepicker('getDate');
				if (testStartDate >= testEndDate){
					var temp=testEndDate;
					temp.setMinutes(temp.getMinutes()-1);
					start.timepicker('setDate', temp);
				}
			} else {
				var temp=end.datetimepicker('getDate');
				temp.setMinutes(temp.getMinutes()-1);
				start.timepicker('setDate', temp);
			}
			start.datetimepicker('option', 'maxTime', end.timepicker('getDate'));
		}
	});
}

//验证是否为数字和字母
function checkValueType(value) {
    var Regx = /^[A-Za-z0-9]*$/;
    if (Regx.test(value)) {
        return true;
    }else {
        return false;
    }
}

/**
 * 将时分 或者时分秒转换为  带日期的时分或者时分秒
 * @param time
 */
function showDateTime(time){
	var da = (new Date()).format("yyyy-MM-dd");
	return da+" "+time;
}


Date.prototype.format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


