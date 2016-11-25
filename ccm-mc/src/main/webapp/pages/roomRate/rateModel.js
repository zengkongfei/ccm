function RateDetail() {
	var rateDetail = new Object();
	rateDetail.rateDetailId;// 序号
	rateDetail.ratePlanId;// 房价序号
	rateDetail.effectiveDate;// 生效时间
	rateDetail.expireDate;// 失效时间
	rateDetail.isApplyToMonday;// 周一适用
	rateDetail.isApplyToTuesday;// 周二适用
	rateDetail.isApplyToWednesday;// 周三适用
	rateDetail.isApplyToThursday;// 周四适用
	rateDetail.isApplyToFriday;// 周五适用
	rateDetail.isApplyToSaturday;// 周六适用
	rateDetail.isApplyToSunday;// 周日适用
	rateDetail.extraBed;// 加床（成人）
	rateDetail.extraBedChild;// 加床（小孩）
	rateDetail.roomRateList;
	rateDetail.rateAmountList;
	return rateDetail;
}

//房型房价
function RoomRate(){
	var roomRate = new Object();
	roomRate.roomRateId;		//序号    
	roomRate.roomTypeId;		//房型序号
	roomRate.roomTypeName;		//房型名称
	roomRate.rateDetailId;		//房价明细序号
	roomRate.roomPackageList;
	return roomRate;
}

//房型特定打包服务
function RoomPackage(){
	var roomPackage = new Object();
	roomPackage.roomPackageId;	//序号     
	roomPackage.roomRateId;		//房型房价序号
	roomPackage.packageId;		//打包服务序号 
	return roomPackage;
}
//房价价格
function RateAmount(){
	var rateAmount = new Object();
	rateAmount.rateAmountId;		//序号
	rateAmount.rateDetailId;		//房价明细序号  
	rateAmount.numberOfUnits;		//人数         
	rateAmount.baseAmount;			//金额    
	rateAmount.ageQualifyingCode;	//年龄分类
	return rateAmount;
}

//可卖条件
function soldAbleCondition(){
	var obj = new Object();
	obj.maxEvenDay;
	obj.minEvenDay;
	obj.maxBeforehandDay;
	obj.minBeforehandDay;
	obj.lastMinutesBeginTime; 
	obj.lastMinutesEndTime;
	obj.lastMinutesPercent;
	obj.lastMinutesAmount;
	obj.limitBuy;
	obj.bookTime;
	obj.soldNum;
	return obj;
}

function filterRTName(thi){
	roomTypeNameCode = $(thi).text();
	showRateDetailList();
}
var isDateAsc=true;
var isWeekAsc=true;
var isPriceAsc=true;
function sortBy(sb){
	if(sb == "date"){
		if(isDateAsc){
			rateDetailList.sort(function (a,b)
				{
					return (a.effectiveDate+a.expireDate) > (b.effectiveDate+b.expireDate) ? -1: 1;
				});
			isDateAsc = false;
		}else{
			rateDetailList.sort(function (a,b)
				{
					return (a.effectiveDate+a.expireDate) < (b.effectiveDate+b.expireDate) ? -1: 1;
				});
			isDateAsc = true;
		}
	}else if(sb == "week"){
		if(isWeekAsc){
			rateDetailList.sort(function (a,b)
				{
					return getWeekStr(a) > getWeekStr(b) ? -1: 1;
				});
			isWeekAsc = false;
		}else{
			rateDetailList.sort(function (a,b)
				{
					return getWeekStr(a) < getWeekStr(b) ? -1: 1;
				});
			isWeekAsc = true;
		}
	}
	else if(sb == "price"){
		if(isPriceAsc){
			rateDetailList.sort(function (a,b)
				{
					return getFirstPrice(a.rateAmountList) - getFirstPrice(b.rateAmountList);
				});
			isPriceAsc = false;
		}else{
			rateDetailList.sort(function (a,b)
				{
				return getFirstPrice(b.rateAmountList) - getFirstPrice(a.rateAmountList);
				});
			isPriceAsc = true;
		}
	}
	showRateDetailList();
}
//默认仅显示有效时间
var filterDate = true;
function filterDateRateDetailList(f){
	filterDate = f;
	showRateDetailList();
}

//房型名字code
var roomTypeNameCode=""
function showRateDetailList(){
	$('#normalTB tbody').empty();
	for(var i=0;i<rateDetailList.length;i++){
		var rateDetail = rateDetailList[i];
		var isFindRTName = false;
		var isEffectiveDate = false;
		if(filterDate){
			var d1 = new Date(rateDetail.expireDate.replace(/-/g,"/")+" 23:59:59");
			var d2 = new Date();
			if(d1 >= d2){
				isEffectiveDate = true;
			}
		}else{
			isEffectiveDate = true;
		}
		
		var rateTr = '<tr><td>'+rateDetail.effectiveDate+' ~ '+rateDetail.expireDate+'</td>';
	    rateTr+= '<td><div class="toleft"><span>'+getWeekStr(rateDetail)+'</span></div></td>';
	    rateTr+= '<td><div class="toleft">';
		var roomRateList = rateDetail.roomRateList;
		
		for(var j=0;j<roomRateList.length;j++){
			var roomTypeName = roomRateList[j].roomTypeName;

			if(roomTypeNameCode != "" && $.trim(roomTypeName) == $.trim(roomTypeNameCode)){
				isFindRTName = true;
				rateTr+='<a class="focus_room" href="#" onclick="filterRTName();">'+ roomTypeName +'</a>';
			}else{
				rateTr+='<a class="link_1" href="#" onclick="filterRTName(this);">'+ roomTypeName +'</a>';
			}
			if(j != roomRateList.length-1){
				rateTr+=',';
			}
		}
	    rateTr+= '</div></td><td><div class="toright c_rel"><span class="price" >'+getFirstPrice(rateDetail.rateAmountList)+'</span>';
	    rateTr+= ' <div class="ft_layer p_hover">';
		var rateAmountList = rateDetail.rateAmountList;
		for(var j=0;j<rateAmountList.length;j++){
			if(rateAmountList[j].baseAmount != ''){
				rateTr+= '<p>'+rateAmountList[j].numberOfUnits+Adult+'：'+rateAmountList[j].baseAmount+' </p>';
			}
		}
	    rateTr+= '</div></div></td>';
	    rateTr+= '<td><a href="#splitRateDetailDiv" class="link mgR12  ccm-popup-click" onclick="splitRateDetail('+i+');">'+Split+'</a>';
	    rateTr+= '<a href="#AddNewPrice" class="link mgR12  ccm-popup-click" onclick="showRateDetail('+i+');">'+edit+'	</a>';
	    rateTr+= '<a href="javascript:delRateDetail('+i+');" class="link del_ifself">'+Delete+'</a></td></tr>';
	    if((roomTypeNameCode == "" || isFindRTName) && isEffectiveDate){
	    	$("#normalTB").append(rateTr);
	    }
	}
	unbindSpanPrice();
	bindSpanPrice();
	bind_ccm_popup();
	hiddenOperate();
}