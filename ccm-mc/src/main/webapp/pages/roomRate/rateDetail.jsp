<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<form name="rateDetailForm" action="/roomRate_editRateDetail.do" method="post" id="rateDetailForm">
<input type="hidden" name="rateDetailListStr" id="rateDetailListStr">
<input type="hidden" name="ratePlanId" id="ratePlanId">
<s:hidden name="ratePlanVO.handleRateDetailType"></s:hidden>
</form>

<script type="text/javascript">
var isNewAction=false;//标识是否为新建房价操作
var idxForEdit=0;//标识编辑的为哪个房价
var roomRateList = new Array();//临时数据，打开时填充已有的房型打包
var Adult='';
var Delete='';
var edit='';
var Split='';
$(document).ready(function() {
	
	
	//房型打包服务
	<s:iterator value="configMap['roomTypeList']" var="rt" status="sta">
		var roomRate = new RoomRate();
		roomRate.roomTypeId = "${rt.roomTypeId}";
		roomRate.roomTypeName = "${rt.roomTypeName}";
		roomRate.maxOccupancy = "${rt.maxOccupancy}";//最多入住人数
		roomRateList.push(roomRate);
	</s:iterator>
	
	var rateDetailListStr = '<s:property escape="false" value="rateDetailListStr"/>';
	if(rateDetailListStr != ""){
		Adult = '<fmt:message key="ccm.Rates.Adult"/>';
		Delete = '<fmt:message key="common.button.delete"/>';
		edit = '<fmt:message key="common.button.edit"/>';
		Split = '<fmt:message key="ccm.Rates.Split"/>';
		rateDetailList = convertStrToJson(rateDetailListStr);
		showRateDetailList();
	}
	
	//设置房型打包服务
	$('#roomPackageDiv .confirmthis').click(function(){
		var packageCode='';
		var packageName='';
		var idx = $('#roomType_idx').val();
		
		var roomRate = null;
		for(var i=0;i<roomRateList.length;i++){
			var roomTypeId = $("#roomTypeId").val();
			if(roomTypeId == roomRateList[i].roomTypeId){
				roomRate = roomRateList[i];
				break;
			}
		}
		var roomPackageList = new Array();
		$('#roomPackageDiv input:checked').each(function(){
			packageCode += $(this).val()+',';
			var roomPackage = new RoomPackage();
			roomPackage.packageId = $(this).attr("id");
			roomPackageList.push(roomPackage);
		});
		roomRate.roomPackageList = roomPackageList;
		
		$('#roomPackageDiv input:checked').next('span').each(function(){ 
			packageName += $(this).text()+',';
		});
		packageCode = packageCode.substr(0,packageCode.lastIndexOf(','));
		packageName = packageName.substr(0,packageName.lastIndexOf(','));
		
		
		$("#roomPackageId_"+idx).text(packageName);
		if(packageName != ""){
			$("#roomPackageId_"+idx).parent().next().hide();
		}else{
			$("#roomPackageId_"+idx).parent().next().show();
		}
		
		$('.packageClick .packageCode').text(packageCode);
		$('.packageClick .packageName').text(packageName);
		$('#roomPackageDiv').hide();
	});
	//保存房价
	$('#btn_saveRateDetail').bind('click',function(){
		
		var idx = $("#rateDetailIdx").val();
		
		var rateDetail = new RateDetail();
		rateDetail.effectiveDate=$("#effectiveDate_rateDetail").val();
		rateDetail.expireDate=$("#expireDate_rateDetail").val();
		rateDetail.delFlag=false;
		$('.rateDetailWeek input').each(function(){
			var isApplyToWeek = $(this).attr("id");
			rateDetail[isApplyToWeek]=$(this).is(":checked");
		});
		if($("#extraBed").val()!=''){
			rateDetail.extraBed=parseFloat($("#extraBed").val());
		}
		if($("#extraBedChild").val()!=''){
			rateDetail.extraBedChild=parseFloat($("#extraBedChild").val());
		}
		var rateAmountList = new Array();
		$('.editprice_dt input[name="baseAmount"]').each(function(){
			var baseAmount = $(this).val();
			if(baseAmount != ''){
				baseAmount = parseFloat($(this).val());
				var number = parseInt($(this).attr("numberOfUnits"));
				var rateAmount = new RateAmount();
				var rateAmountId = $(this).attr("rateAmountId");
				rateAmount.numberOfUnits = number;
				rateAmount.baseAmount = baseAmount;
				rateAmount.rateAmountId = rateAmountId;
				rateAmount.delFlag=false;
				rateAmountList.push(rateAmount);
			}
		});
		var baseAmount1 = $('.editprice_dt input[name="baseAmount"][numberofunits="1"]').val();
		if(baseAmount1 == ''){
			alert('<fmt:message key="ccm.Rates.ErrorMessage.1AdultIsRequired"/>！');
			return;
		}
		
		for(var i=0;i<rateAmountList.length;i++){
			var number = rateAmountList[i].numberOfUnits;
			if(number != i+1){
				alert('<fmt:message key="ccm.Rates.ErrorMessage.ContinuousAdultNumberRate"/>！');
				return;
			}
		}

		rateDetail.rateAmountList=rateAmountList;
		
		var checkedRoomRateList = new Array();
		var moreThanTwo=false;
		for(var i=0;i<roomRateList.length;i++){
			var isChecked = $("#"+roomRateList[i].roomTypeId).is(':checked');
			if(isChecked == true){
				roomRateList[i].delFlag=false;
				checkedRoomRateList.push(roomRateList[i]);	//房型、房型打包
				//判断是否包含最多人是否超过1
				var maxOccup=roomRateList[i].maxOccupancy;
				if(maxOccup!=null && maxOccup>1){
					moreThanTwo=true;
				}
			}
		}
		if(moreThanTwo){
			//判断是否包含二人价格
			var TwoAdultValue=$("#2AdultValue").val();
			if((TwoAdultValue==null )||(TwoAdultValue=='')||(TwoAdultValue=='null')||(TwoAdultValue.length<1) ){
				var isT=confirm('<fmt:message key="ccm.Rates.Message.2AdultConfirm"/>?');
				if(isT){
					return false;
				}
			}
			
		}
		
		if(checkedRoomRateList.length ==0){
			alert("<fmt:message key='ccm.Rates.ErrorMessage.SelectRoomType'/>！");
			return;
		}
		if(rateDetail.effectiveDate == '' || rateDetail.expireDate==''){
			alert("<fmt:message key='ccm.Rates.ErrorMessage.SelectStartDate'/>！");
			return;
		}
		
		//校验起始时间和结束时间
		if(parseInt($("#rateDetailIdx").val()) == -1){
			var effectiveDateCode = validateYYYYMMDD(rateDetail.effectiveDate);
			var effectiveDateCode2 = validateYYYYMMDDLine(rateDetail.effectiveDate);
			if(effectiveDateCode2!='success'){
				alert(getErrorMsg(effectiveDateCode2,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
				return false;
			}
			
			if(effectiveDateCode!='success'){
				alert(getErrorMsg(effectiveDateCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
				return false;
			}else if(isMorethanNow(rateDetail.effectiveDate)=='false'){
				alert('开始时间不能小于当前时间');
				return false;
			}
		}
		
		var expireDateCode = validateYYYYMMDD(rateDetail.expireDate);
		var expireDateCode2 = validateYYYYMMDDLine(rateDetail.expireDate);
		if(expireDateCode2!='success'){
			alert(getErrorMsg(expireDateCode2,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-DD'));
			return false;
		}
		if(expireDateCode!='success'){
			alert(getErrorMsg(expireDateCode,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-DD'));
			return false;
		}else if(isMorethanNow(rateDetail.expireDate)=='false'){
			alert('结束时间不能小于当前时间');
			return false;
		}
		
		var rateHeaderEffectiveDate=$("#effectiveDateOfHeader").text();
		var rateHeaderExpireDate=$("#expireDateOfHeader").text();
		if(!compareDateStrs(rateDetail.effectiveDate,rateHeaderEffectiveDate)){
			alert('开始日期小于总房价开始日期');
			return false;
		}
		
		if(!compareDateStrs(rateHeaderExpireDate,rateDetail.expireDate)){
			alert('结束日期大于总房价结束日期');
			return false;
		}
		
		if(getWeekStr(rateDetail) ==''){
			alert("<fmt:message key='ccm.Rates.ErrorMessage.SelectAWeekAtLeast'/>！");
			return;
		}
		if(rateAmountList.length == 0){
			alert("<fmt:message key='ccm.Rates.ErrorMessage.SetUpARateAtLeast'/>！");
			return;
		}
		rateDetail.roomRateList=clone(checkedRoomRateList);	
		var cloneRateDetail = clone(rateDetail);
		cloneRateDetail.ratePlanId = '${ratePlanId}';
		
		//房价覆盖提示	
		var r=true;//用户选择
		for(var i=0;i<rateDetailList.length;i++){
			var value=rateDetailList[i];
		//$.each(rateDetailList,function(key,value) {
   			   //判读所选日期与已存在房价是否重叠
			if(((isMorethanNow(value.effectiveDate)=='equals') || (isMorethanNow(value.effectiveDate)=='true'))
					&&( (rateDetail.effectiveDate<=value.expireDate)&&(rateDetail.expireDate>=value.effectiveDate) )){
				      //判读所选星期与已存在房价是否重叠
					  if((rateDetail.isApplyToSunday==value.isApplyToSunday)||
						(rateDetail.isApplyToMonday==value.isApplyToMonday)||
						(rateDetail.isApplyToTuesday==value.isApplyToTuesday)||
						(rateDetail.isApplyToWednesday==value.isApplyToWednesday)||
						(rateDetail.isApplyToThursday==value.isApplyToThursday)||
						(rateDetail.isApplyToFriday==value.isApplyToFriday)||
						(rateDetail.isApplyToSaturday==value.isApplyToSaturday)){
						//判读所选房型与已存在房价是否重叠
						//已存在的房型列表
					    var roomRateListVal=value.roomRateList;
					    //新输入的房型列表
					    var roomRateListDet=rateDetail.roomRateList;
					    $.each(roomRateListVal,function(keyIn,valueIn){	    
						   $.each(roomRateListDet,function(keyInIn,valueInIn) {
							   
							  	 if(valueIn.roomTypeId==valueInIn.roomTypeId)
							  	 {
							  		 //如果为新建房价
							  		if(isNewAction){
							  			
							  			if( ((rateDetail.expireDate==value.expireDate)&&(rateDetail.effectiveDate==value.effectiveDate))||
									  			((rateDetail.effectiveDate==value.effectiveDate)&&(rateDetail.expireDate<value.expireDate))||
									  			((rateDetail.effectiveDate>value.effectiveDate)&&(rateDetail.expireDate<value.expireDate))||
									  			((rateDetail.effectiveDate>value.effectiveDate)&&(rateDetail.expireDate=value.expireDate))){
									  			if(rateDetail.expireDate==rateDetail.effectiveDate){
											  		var effectiveD=new Date(rateDetail.effectiveDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+")<fmt:message key='ccm.rateChangeConfirmPoint'/>?");
									  			}else{
									  				var effectiveD=new Date(rateDetail.effectiveDate);
									  				var expireD=new Date(rateDetail.expireDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		expireD=expireD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+"-"+expireD+")<fmt:message key='ccm.rateChangeConfirmQuantum'/>?");
									  			}
									  		}else if((rateDetail.effectiveDate>=value.effectiveDate)&&(rateDetail.expireDate>value.expireDate)){
									  			if(rateDetail.effectiveDate==value.expireDate){
									  				var effectiveD=new Date(rateDetail.effectiveDate);
										  			effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
										  			r=confirm("("+effectiveD+")<fmt:message key='ccm.rateChangeConfirmPoint'/>?");
									  			}else{
									  				var effectiveD=new Date(rateDetail.effectiveDate);
								  					var expireD=new Date(value.expireDate);
										  			effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
										  			expireD=expireD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
										  			r=confirm("("+effectiveD+"-"+expireD+")<fmt:message key='ccm.rateChangeConfirmQuantum'/>?");
									  			}
									  		}else if((rateDetail.effectiveDate<value.effectiveDate)&&(rateDetail.expireDate>value.expireDate)){
									  			if(value.expireDate==value.effectiveDate){
											  		var effectiveD=new Date(value.effectiveDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+")<fmt:message key='ccm.rateChangeConfirmPoint'/>?");
									  			}else{
									  				var effectiveD=new Date(value.effectiveDate);
									  				var expireD=new Date(value.expireDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		expireD=expireD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+"-"+expireD+")<fmt:message key='ccm.rateChangeConfirmQuantum'/>?");
									  			}
									  		}else if((rateDetail.effectiveDate<value.effectiveDate)&&(rateDetail.expireDate<=value.expireDate)&&
									  				(rateDetail.expireDate>=value.effectiveDate)){
									  			if(value.effectiveDate==rateDetail.expireDate){
									  				var effectiveD=new Date(value.effectiveDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+")<fmt:message key='ccm.rateChangeConfirmPoint'/>?");
									  			}else{
									  				var effectiveD=new Date(value.effectiveDate);
									  				var expireD=new Date(rateDetail.expireDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		expireD=expireD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+"-"+expireD+")<fmt:message key='ccm.rateChangeConfirmQuantum'/>?");
									  			}
									  		}
							  	    //如果为编辑房价,与在编辑的情况下，只覆盖自己不需要提示		
							  		}else if((!isNewAction)&&(i!=idxForEdit)){
							  			
							  			if( ((rateDetail.expireDate==value.expireDate)&&(rateDetail.effectiveDate==value.effectiveDate))||
									  			((rateDetail.effectiveDate==value.effectiveDate)&&(rateDetail.expireDate<value.expireDate))||
									  			((rateDetail.effectiveDate>value.effectiveDate)&&(rateDetail.expireDate<value.expireDate))||
									  			((rateDetail.effectiveDate>value.effectiveDate)&&(rateDetail.expireDate=value.expireDate))){
									  			if(rateDetail.expireDate==rateDetail.effectiveDate){
											  		var effectiveD=new Date(rateDetail.effectiveDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+")<fmt:message key='ccm.rateChangeConfirmPoint'/>?");
									  			}else{
									  				var effectiveD=new Date(rateDetail.effectiveDate);
									  				var expireD=new Date(rateDetail.expireDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		expireD=expireD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+"-"+expireD+")<fmt:message key='ccm.rateChangeConfirmQuantum'/>?");
									  			}
									  		}else if((rateDetail.effectiveDate>=value.effectiveDate)&&(rateDetail.expireDate>value.expireDate)){
									  			if(rateDetail.effectiveDate==value.expireDate){
									  				var effectiveD=new Date(rateDetail.effectiveDate);
										  			effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
										  			r=confirm("("+effectiveD+")<fmt:message key='ccm.rateChangeConfirmPoint'/>?");
									  			}else{
									  				var effectiveD=new Date(rateDetail.effectiveDate);
								  					var expireD=new Date(value.expireDate);
										  			effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
										  			expireD=expireD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
										  			r=confirm("("+effectiveD+"-"+expireD+")<fmt:message key='ccm.rateChangeConfirmQuantum'/>?");
									  			}
									  		}else if((rateDetail.effectiveDate<value.effectiveDate)&&(rateDetail.expireDate>value.expireDate)){
									  			if(value.expireDate==value.effectiveDate){
											  		var effectiveD=new Date(value.effectiveDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+")<fmt:message key='ccm.rateChangeConfirmPoint'/>?");
									  			}else{
									  				var effectiveD=new Date(value.effectiveDate);
									  				var expireD=new Date(value.expireDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		expireD=expireD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+"-"+expireD+")<fmt:message key='ccm.rateChangeConfirmQuantum'/>?");
									  			}
									  		}else if((rateDetail.effectiveDate<value.effectiveDate)&&(rateDetail.expireDate<=value.expireDate)&&
									  				(rateDetail.expireDate>=value.effectiveDate)){
									  			if(value.effectiveDate==rateDetail.expireDate){
									  				var effectiveD=new Date(value.effectiveDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+")<fmt:message key='ccm.rateChangeConfirmPoint'/>?");
									  			}else{
									  				var effectiveD=new Date(value.effectiveDate);
									  				var expireD=new Date(rateDetail.expireDate);
											  		effectiveD=effectiveD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		expireD=expireD.format("MM<fmt:message key='common.month.month'/>dd<fmt:message key='time.day'/>");
											  		r=confirm("("+effectiveD+"-"+expireD+")<fmt:message key='ccm.rateChangeConfirmQuantum'/>?");
									  			}
									  		}
							  			
							  		}
							  		
							  	 } 
							  	 
							});
						});
					}	
				}
		}
		//);
		
		var rateDetailEditList = new Array();
		if(idx > -1){
			cloneRateDetail.rateDetailId = rateDetailList[idx].rateDetailId;
		}
		//ajax请求到后台保存或更新cloneRateDetail
		//根据选择判断是否更新
		if(r){
			rateDetailEditList.push(cloneRateDetail);
		}
		
		
		$("#rateDetailListStr").val(JSON.stringify(rateDetailEditList));
		
		$("#ratePlanId").val('${ratePlanId}');
	
		var arrs = new Array();
		arrs.push("rateDetailId");
		arrs.push("lastModifyTime");
		arrs.push("createdBy");
		arrs.push("createdTime");
		arrs.push("updatedBy");
		
		var idx = $("#rateDetailIdx").val();
		var old1 = rateDetailList[idx];
		var newArray=JSON.parse($("#rateDetailListStr").val());
		var new1=newArray[0];
		if(compareRateDetail(new1,old1,arrs)==false||compareRateDetail(old1,new1,arrs)==false){
			$(this).attr("disabled",true);
			rateDetailForm.submit();
		}else{
			$.magnificPopup.close();
		}
	});
	
	$('.hasDatepicker').blur(function(){
		if(!$(this).hasClass('removehasDatepicker')){
			validateDateFormart($(this));
		}
	});
	$('#expireDate_rate').blur(function(){
		if($(this).val() !=""){
			var thisDate = addHRToStr($(this).val());
			if(comppareDate(thisDate, maxExpireDate)){
				alert("该值不能小于"+maxExpireDate);
				$(this).val('');
				$(this).focus();
			}
		}
	});	
	sortBy('date');
	sortBy('date');
});

function comppareDate(a, b) {
    var arr = a.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();

    var arrs = b.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();
    if (starttimes >= lktimes)
        return false;
    else
        return true;
}
function validateDateFormart(thi){
	var dateStr = thi.val();
	if(!strIsNull(dateStr)){
		var name = $(thi).attr("name");
		if(name == 'ratePlanVO.rp.effectiveDate'){
			if(isMorethanNow(dateStr)=='false'){
				alert('该时间不能小于今天');
				$(thi).val('');
				$(thi).focus();
			}
		}
		if(name=='beginTime' || name == 'endTime'){
			var expireDateCode = validateYYYYMMDDHHmm(dateStr);
			if(expireDateCode!='success'){
				alert(getErrorMsg(expireDateCode,'','yyyy-MM-DD HH:mm'));
				$(thi).val('');
				$(thi).focus();
			}
		}else{
			var expireDateCode = validateYYYYMMDD(dateStr);
			if(expireDateCode!='success'){
				alert(getErrorMsg(expireDateCode,'','yyyy-MM-DD'));
				$(thi).val('');
				$(thi).focus();
			}else{
				thi.val(addHRToStr(dateStr));
			}
		}
	}
}
function showRateDetail(idx){
	//新建房价
	if(idx==-1){
		isNewAction=true;
	}
	$("#effectiveDate_rateDetail").val("");
	$("#expireDate_rateDetail").val("");
	//清空房型
	$('.roomRatePackage input:checked').each(function(){
		$(this).trigger("click");
	});
	//默认选中星期
	$('.rateDetailWeek input[type="checkbox"]').each(function(){
		$(this).prop("checked","true");
	});
	//清空价格
	$('.editprice_dt input[type="text"]').each(function(){
		$(this).val("");
	});
	//清空价格id
	$('.editprice_dt input[name="baseAmount"]').attr("rateAmountId","");
	//清空已有的房型和打包
	for(var k=0;k<roomRateList.length;k++){
		roomRateList[k].roomRateId = null;
		roomRateList[k].roomPackageList = null;
	}
	$("#rateDetailIdx").val(idx);
	if(idx > -1){
		idxForEdit=idx;
		var rateDetail = rateDetailList[idx];
		for(var i=0;i<rateDetail.roomRateList.length;i++){
			var roomRate = rateDetail.roomRateList[i];
			//设置已有的房型选中
			$(".roomRatePackage #"+roomRate.roomTypeId).trigger("click");
			
			//设置已有的打包到临时 roomRateList
			for(var k=0;k<roomRateList.length;k++){
				if(roomRate.roomTypeId == roomRateList[k].roomTypeId){
					roomRateList[k].roomRateId = roomRate.roomRateId;
					roomRateList[k].roomPackageList = roomRate.roomPackageList;
				}
			}
			
			var idx = $(".roomRatePackage #"+roomRate.roomTypeId).parents('tr')[0].rowIndex - 1;	 //获取当前行
			//设置已有的打包名字
			var packageName="";
			$('#roomPackageDiv input[type="checkbox"]').each(function(){ 
				for(var j=0;j<roomRate.roomPackageList.length;j++){
					var pkId = roomRate.roomPackageList[j].packageId;
					if($(this).attr("id")==pkId){
						packageName += ","+$(this).next('span').text();
					}
				}
			});
			$("#roomPackageId_"+idx).text(packageName.substr(1));
		}
		
		$("#effectiveDate_rateDetail").val(rateDetail.effectiveDate);
		$("#expireDate_rateDetail").val(rateDetail.expireDate);
		$('.rateDetailWeek input[type="checkbox"]').each(function(){
			var isApplyToWeek = $(this).attr("id");
			if(rateDetail[isApplyToWeek]){
				$(this).prop("checked","true");
			}else{
				$(this).prop("checked","");
			}
		});
		$("#extraBed").val(rateDetail.extraBed);
		$("#extraBedChild").val(rateDetail.extraBedChild);
		
		for(var i=0;i<rateDetail.rateAmountList.length;i++){
			var rateAmount = rateDetail.rateAmountList[i];
			var rateAmountInput = $('.editprice_dt input[numberOfUnits="'+rateAmount.numberOfUnits+'"]');
			rateAmountInput.val(rateAmount.baseAmount);
			rateAmountInput.attr("rateAmountId",rateAmount.rateAmountId);
		}
	}
}
function saveSplitRateDetail(){
		var idx = $("#splitRateDetailIdx").val();
		var rateDetail = rateDetailList[idx];
		var splitStart = $("#effectiveDate_splitRateDetail").val();
		var splitEnd = $("#expireDate_splitRateDetail").val();
		var extraBed=$("#extraBed").val();
		var extraBedChild=$("#extraBedChild").val();
		var rateAmountList = new Array();
		$('.splitRateDetailPrice input[name="baseAmount"]').each(function(){
			var baseAmount = $(this).val();
			if(baseAmount != ''){
				var rateAmount = new RateAmount();
				var number = $(this).attr("numberOfUnits");
				var rateAmountId = $(this).attr("rateAmountId");
				rateAmount.numberOfUnits = number;
				rateAmount.baseAmount = baseAmount;
				rateAmount.rateAmountId = rateAmountId;
				rateAmountList.push(rateAmount);
			}
		});
		
		var start = rateDetail.effectiveDate;
		var end =  rateDetail.expireDate;
		
		if(splitStart == '' || splitEnd==''){
			alert("<fmt:message key='ccm.InventoryManagement.error.beginDateNull'/>！");
			return;
		}
		
		//校验起始时间和结束时间
		var splitStartCode = validateYYYYMMDD(splitStart);
		if(splitStartCode!='success'){
			alert(getErrorMsg(splitStartCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
			return false;
		}else if(isMorethanNow(splitStart)=='false'){
			alert('开始时间不能小于当前时间');
			return false;
		}
		
		var splitEndCode = validateYYYYMMDD(splitEnd);
		if(splitEndCode!='success'){
			alert(getErrorMsg(splitEndCode,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-DD'));
			return false;
		}else if(isMorethanNow(splitEnd)=='false'){
			alert('结束时间不能小于当前时间');
			return false;
		}
		
		if((splitStart < start) || (splitEnd > end)){
			var arry = new Array();
			arry.push(rateDetail.effectiveDate);
			arry.push(rateDetail.expireDate);
			var str = '<fmt:message key="ccm.Rates.ErrorMessage.StartDateAndEndDate"/>';
			alert(i18n_replace(str,arry));
			//alert('开始、结束日期必须在'+rateDetail.effectiveDate+'~'+rateDetail.expireDate+'内！');
			return;
		}
		if(rateAmountList.length == 0){
			alert("<fmt:message key='ccm.Rates.ErrorMessage.SetUpARateAtLeast'/>！");
			return;
		}
		var rateDetail1 = null;
		var rateDetail2 = null;
		var rateDetail3 = null;
		if(splitStart != start){
			var rateDetail1 = clone(rateDetail);
			var d1 = new Date(splitStart.replace(/-/g,"/"));
			d1 = d1.setDate(d1.getDate()-1);		//减1天
			rateDetail1.expireDate = new Date(d1).format("yyyy-MM-dd");	
			var rateDetail2 = clone(rateDetail);
			rateDetail2.rateDetailId=null;
			rateDetail2.effectiveDate = splitStart;
			rateDetail2.expireDate = splitEnd;
			rateDetail2.rateAmountList=rateAmountList;
			rateDetail2.extraBed=extraBed;
			rateDetail2.extraBedChild=extraBedChild;
			
			for(var i=0;i<rateDetail2.rateAmountList.length;i++){
				rateDetail2.rateAmountList[i].rateAmountId=null;
			}
			for(var i=0;i<rateDetail2.roomRateList.length;i++){
				rateDetail2.roomRateList[i].roomRateId=null;
			}
		}else{
			var rateDetail1 = clone(rateDetail);
			rateDetail1.expireDate = splitEnd;
			rateDetail1.rateAmountList=rateAmountList;
			rateDetail1.extraBed=extraBed;
			rateDetail1.extraBedChild=extraBedChild;
		}
		
		if(splitEnd != end){
			var rateDetail3 = clone(rateDetail);
			rateDetail3.rateDetailId=null;
			var d2 = new Date(splitEnd.replace(/-/g,"/"));
			d2 = d2.setDate(d2.getDate()+1);			//加1天
			rateDetail3.effectiveDate = d2;
			rateDetail3.expireDate = end;
			for(var i=0;i<rateDetail3.rateAmountList.length;i++){
				rateDetail3.rateAmountList[i].rateAmountId=null;
			}
			for(var i=0;i<rateDetail3.roomRateList.length;i++){
				rateDetail3.roomRateList[i].roomRateId=null;
			}
		}
		var splitRateDetailList = new Array();
		rateDetail1.rateType="noSaveMongo";
		splitRateDetailList.push(rateDetail1);
		if(rateDetail2 != null){
			splitRateDetailList.push(rateDetail2);
		}
		if(rateDetail3 != null){
			rateDetail3.rateType="noSaveMongo";
			splitRateDetailList.push(rateDetail3);
		}
		$("#rateDetailListStr").val(JSON.stringify(splitRateDetailList));
		$("#ratePlanId").val('${ratePlanId}');
		$("#btn_saveSplitRateDetail").attr("disabled",true);
		$("#btn_saveSplitRateDetail").addClass("no_ald");
		$("#ratePlanVO_handleRateDetailType").val('splitRateDetail');
		document.rateDetailForm.action="/roomRate_splitDetail.do";
		document.rateDetailForm.submit();
}
function splitRateDetail(idx){
	$("#effectiveDate_splitRateDetail").val("");
	$("#expireDate_splitRateDetail").val("");
	//清空价格
	$('.splitRateDetailPrice input[type="text"]').each(function(){
		$(this).val("");
	});
	$("#splitRateDetailIdx").val(idx);
}
//oldRateDetail compare to newRateDetail
function compareRateDetail(o1,o2,unCheckKeys){
	
   if(typeof o1 != typeof o2){
	   
	   return false;
   }
	if(typeof o1 == 'object'){
		 if( o1 instanceof Array && o2 instanceof Array){
				if(o1.length!=o2.length){
					return false;
				}
			}
		for(var o in o1){
			o1['notNull'] = true;
			if(unCheckKeys!= undefined){
				
				for(i=0;i<unCheckKeys.length;i++){
					if(o==unCheckKeys[i]){
						return true;
					}
				}
			}
			if(typeof o2[o] == undefined)
				return false;
				
			if(!compareRateDetail(o1[o],o2[o],unCheckKeys))return false;
			}
		if(o1.notNull){
			return true;
			}
		
		return true;
	}else{
		
		if(o1 === o2){
			return true;
		}else{
			
			return false;
			}
		} 
}

function compareRoomPackageList(old1,new1){
	if(old1.roomRateList.length==new1.roomRateList.length){
		for(i=0;i<old1.roomRateList.length;i++){
			if(old1.roomRateList[i].roomPackageList.length==new1.roomRateList[i].roomPackageList.length){
				
				for(j=0;j<old1.roomRateList[i].roomPackageList.length;j++){
					var temp_o="{a:"+old1.roomRateList[i].roomPackageList[j]+"}";
					var temp_n="{a:"+new1.roomRateList[i].roomPackageList[j]+"}";	
					if(compareRateDetail(temp_o,temp_n)==false||compareRateDetail(temp_n,temp_o)==false){
							return false;
						}
					}
			}else{
				return false;
			}
		}
		return true;
	}else{
		return false;
	}
}
</script>