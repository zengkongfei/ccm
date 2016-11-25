<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>

<style>
.title_channelbooking {
	color: #0066CC;
	font-weight: bold;
}

.tr_channelbook {
	bordercolor: #FFFFFF;
	text-align:center;
}

.td_title_channelbook {
	text-align: center;
	background-color: #DCE4EF;
	text-align: right;
	font-weight: bold;
	width: 90px;
}

.td_title_channelbook2 {
	text-align: center;
	background-color: #DCE4EF;
	font-weight: bold;
}

.STYLE5 {
	color: #0066CC;
	font-weight: bold;
}

.STYLE6 {
	color: #FFFFFF;
	font-weight: bold;
}
.table th, .table td {
    text-align: center;
}
.w88{
	width: 140px;
}
.w457{
	width: 457px;
}
ul.inq_wp li .i_title{
	width: 103px;
	text-align: center;
	padding: 5px 0px 4px 0px;
}
.title_channelbooking_guest{
	padding: 8px 0px 4px 0px;
}
input.formnormal{
	border: #d9d9d9 1px solid;
	color: #000;
	background-color: #FFFFFF;
}
</style>
<div class="CCMmainConter w1200 " style="width: 960px;">
	<div class="title_channelbooking"><fmt:message key="ccm.WBEUI.searchTitle"/></div>
	<div class="row">
		<div class="col-md-7 col-md-7" style="width: 30%;">
			<table class="table" border="1" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
				<tbody>
					<tr class="tr_channelbook">
						<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.HotelCodeOrHotelName"/></span> </td>
						<td ><input readonly="readonly" value="${wbeOrderVO.hotelCode }" class="fxt w204"> </td>
					</tr>
					<tr class="tr_channelbook">
						<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.ArrivalDate"/></span>
						</td>
						<td ><input  value="<fmt:formatDate value="${wbeOrderVO.arr}" type="both" pattern="yyyy-MM-dd "/>"     class="fxt w204" type="text" readonly="readonly"></td>
					</tr>
					<tr class="tr_channelbook">
						<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.DepartureDate"/></span> </td>
						<td ><input id="dep" value="<fmt:formatDate value="${wbeOrderVO.dep}" type="both" pattern="yyyy-MM-dd "/>" class="fxt w204" type="text" readonly="readonly"></td>
					</tr>
					<tr class="tr_channelbook">
						<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.RoomTypes"/></span>
						</td>
						<td ><input value="${wbeOrderVO.roomTypeCode }" class="fxt w204" readonly="readonly"></td>
					</tr>
					<tr class="tr_channelbook">
						<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.Audlts"/></span></td>
						<td >
							<select class="fxt w101 mgR12" id="numberOfUnits" onchange="changeRateDetail();">
							</select>
							
							<!-- 房间数 -->
							<span class="STYLE5"><fmt:message key="ccm.WBEUI.NoofRooms"/></span>
							<!-- 
							<c:if test="${!empty wbeOrderVO.crsno}">
							   <span id="numberCrsno"></span>
							</c:if>
							<c:if test="${empty wbeOrderVO.crsno}">
							</c:if>
							 -->
							<select class="fxt w101 mgR12" id="number" onchange="changeCharge();"></select>
							
						</td>
					</tr>
					<tr class="tr_channelbook">
						<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.BookingDepositReport.AccessCode"/></span></td>
						<td colspan="5"><input value="${wbeOrderVO.accessCode}" class="fxt w204" readonly="readonly"></td>
					</tr>
					<tr class="tr_channelbook">
						<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.TotalAmount"/></span></td>
						<td colspan="5"><input value="${wbeOrderVO.charge}${wbeOrderVO.currencyCode }" id="charge" class="fxt w204" readonly="readonly"></td>
					</tr>
					<!-- 渠道订单号 -->
					<tr class="tr_channelbook">
						<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.Channel.ChannelReservationNumber"/></span></td>
						<td colspan="5"><input value="${wbeOrderVO.crsno }" id="charge" class="fxt w204" readonly="readonly"></td>
					</tr>
					
					<tr class="tr_channelbook">
						<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.MethodofGuarantee"/></span>&nbsp;<span style="color: red">*</span></td>
						<td colspan="5">
							<select class="fxt w101 mgR12" id="guarrntee" onchange="guarrnteeChange(this)" >
									
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="col-md-5 col-lg-5 " style="margin-top: 200px;">
			<table class="table" border="1" bordercolor="#9263B7"  style="width: 320px;" align="center">
				<tbody id="rateDetail">
					<!-- <tr >
						<td class="td_title_channelbook2" height="100%;" rowspan="4" style="border-top:0px;vertical-align:middle;" align="center"> <span >每日每间房价</span> </td>
						<td style="border-top:0px;">2016/2/27</td>
						<td style="border-top:0px;">500</td>
					</tr>
					<tr >
						<td >2016/2/27</td>
						<td >500</td>
					</tr>
					<tr >
						<td >2016/2/27</td>
						<td >500</td>
					</tr>
					<tr >
						<td >2016/2/27</td>
						<td >500</td>
					</tr> -->
				</tbody>
			</table>
		</div>
		<div class="col-md-2 col-lg-2 " >
			<a href="javascript:back();" class="btn_1 blue"><fmt:message key="ccm.WBEUI.SearchAgain"/></a>
		</div>
	</div>
		<div id="guarrntee_card" style="display: none;" ishow="0">
			<ul class="inq_wp list_input" id="credit">
				<li>
					<div class="i_title">
						<span style="color: red">*</span>&nbsp;<span class="text"><fmt:message key="ccm.Reservations.CreditCardType"/></span>
					</div>
					<div class="i_input">
						<select  class=" fxt w240" >
							<option value=""><fmt:message key="common.select.plesesSelect"/></option>
							<option value="VA" >VISA</option>
							<option value="MA" >Master Card</option>
							<option value="JC" >JCB</option>
							<option value="AE" >American Express</option>
							<option value="DR" >Diners</option>
						</select>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span style="color: red">*</span>&nbsp;<span class="text"><fmt:message key="common.Number"/></span>
					</div>
					<div class="i_input">
						<input  class="formnormal fxt w240"  />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span style="color: red">*</span>&nbsp;<span class="text"><fmt:message key="common.ExpDate"/></span>
					</div>
					<div class="i_input">
						<input id="expDate"  class="fxt w240 calendar required" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span style="color: red">*</span>&nbsp;<span class="text"><fmt:message key="ccm.ReservationsManagment.Name"/></span>
					</div>
					<div class="i_input">
						<input  class="formnormal fxt w240" />
					</div>
				</li>

			</ul>
		</div>
	
	<div class="title_channelbooking"><fmt:message key="ccm.WBEUI.GuestInformation"/></div>
	<div id="guestList">
		<div class="guestList">
			<div class="title_channelbooking_guest"><strong> <fmt:message key="ccm.WBEUI.Guest"/>1</strong></div>
			<div>
				<ul class="inq_wp frow">
					<li class="col3_1">
						<div class="i_title">
							<span style="color: red">*</span>&nbsp;<span class="text"><fmt:message key="ccm.WBEUI.LastName"/></span>
						</div>
						<div class="i_input">
							<input value="" class="formnormal fxt w88 onlyAlpha"   />
						</div>
					</li>
					<li class="col3_1">
						<div class="i_title">
							<span style="color: red">*</span>&nbsp;<span class="text"><fmt:message key="ccm.WBEUI.FirstName"/></span>
						</div>
						<div class="i_input">
							<input value="" class="formnormal fxt w88 onlyAlpha"  />
						</div>
					</li>
					<li class="col3_1">
						<div class="i_title">
							<span class="text"><span class="text"><fmt:message key="ccm.WBEUI.ChineseName"/></span></span>
						</div>
						<div class="i_input">
							<input value="" class="formnormal fxt w88"  />
						</div>
					</li>
				</ul>
				<ul class="inq_wp frow">
					<li class="col3_1">
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.WBEUI.MobilePhone"/></span>
						</div>
						<div class="i_input">
							<input value="" class="formnormal fxt w88"  />
						</div>
					</li>
					<li class="col3_1">
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.WBEUI.EstimatedTimeofArrival"/></span>
						</div>
						<div class="i_input">
							<input value="" class="formnormal fxt w88 calendar arr" readonly="readonly" />
						</div>
					</li>
				</ul>
				<ul class="inq_wp frow">
					<li class="col3_1" style="width:100%">
						<div class="i_title">
							<span class="text"><fmt:message key="ccm.WBEUI.SpecialRequest"/></span>
						</div>
						<div class="i_input">
							<input value="" class="formnormal fxt w457"  />
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	<input type="hidden" value="${channelGoodsVOListJsonArr}" id="channelGoodsVOListJsonArr">
	<input type="hidden" value="${dateList}" id="dateList">
</div>
	<div align="center">
		<button type="button" class="btn_1 blue" onclick="submit(this)"> <fmt:message key="ccm.WBEUI.ConfirmtoSubmit"/></button>
	</div>

			<s:form action="" name="nextForm" id="nextForm" method="post">
				<appfuse:ccmToken name="token"></appfuse:ccmToken>
				<input type="hidden" name="wbeOrderVO.hotelId" value="${wbeOrderVO.hotelId }">
				<input type="hidden" name="wbeOrderVO.hotelCode" value="${wbeOrderVO.hotelCode }">
				<input type="hidden" name="wbeOrderVO.hotelName" value="${wbeOrderVO.hotelName }">
				<input type="hidden" name="wbeOrderVO.chainCode" value="${wbeOrderVO.chainCode }">
				<input type="hidden" name="wbeOrderVO.channelCode" value="${wbeOrderVO.channelCode }">
				<input type="hidden" name="wbeOrderVO.channelId" value="${wbeOrderVO.channelId }">
				<input type="hidden" name="wbeOrderVO.numberOfUnits" >
				<input type="hidden" name="wbeOrderVO.arr" value="<fmt:formatDate value="${wbeOrderVO.arr}" type="both" pattern="yyyy-MM-dd "/>">
				<input type="hidden" name="wbeOrderVO.dep" value="<fmt:formatDate value="${wbeOrderVO.dep}" type="both" pattern="yyyy-MM-dd "/>">
				<input type="hidden" name="wbeOrderVO.number" >
				<input type="hidden" name="wbeOrderVO.roomTypeCode" value="${wbeOrderVO.roomTypeCode }">
				<input type="hidden" name="wbeOrderVO.roomTypeName" value="${wbeOrderVO.roomTypeName }">
				<input type="hidden" name="wbeOrderVO.ratePlanCode" value="${wbeOrderVO.ratePlanCode }">
				<input type="hidden" name="wbeOrderVO.ratePlanId" value="${wbeOrderVO.ratePlanId }">
				<input type="hidden" name="wbeOrderVO.accessCode" value="${wbeOrderVO.accessCode }">
				<input type="hidden" name="wbeOrderVO.accessCodeType" value="${wbeOrderVO.accessCodeType }">
				<input type="hidden" name="wbeOrderVO.inventType" value="${wbeOrderVO.inventType }">
				<input type="hidden" name="wbeOrderVO.createUDFBy" value="${wbeOrderVO.createUDFBy }">
				<input type="hidden" name="wbeOrderVO.currencyCode" value="${wbeOrderVO.currencyCode }">
				
				<input type="hidden" name="wbeOrderVO.crsno" value="${wbeOrderVO.crsno }">

				<input type="hidden" name="wbeOrderVO.payment" >
				<input type="hidden" name="wbeOrderVO.cardCode" >
				<input type="hidden" name="wbeOrderVO.cardHolderName" >
				<input type="hidden" name="wbeOrderVO.cardNumber" >
				<input type="hidden" name="wbeOrderVO.expirationDate" >
				
				<div id="guestInput"></div>
				<div id="dailRate"></div>
				
			</s:form>
			


<script type="text/javascript">
	var guestHtml = '';
	$(document).ready(function() {
		var a = $("#channelGoodsVOListJsonArr").val();
		a = a.replace(/\"/g,"'");
		$("#channelGoodsVOListJsonArr").val(a);
		
		init();
		guestHtml = $("#guestList").html();
		
		initDate();
		
	});
	
	function initDate(){
		//日期显示
		var dpconfig = {
			dateFormat : "yy-mm",
			dayNamesMin : [
					'<fmt:message key="calendar.week.sunday"/>',
					'<fmt:message key="calendar.week.monday"/>',
					'<fmt:message key="calendar.week.tuesday"/>',
					'<fmt:message key="calendar.week.wednesday"/>',
					'<fmt:message key="calendar.week.thursday"/>',
					'<fmt:message key="calendar.week.friday"/>',
					'<fmt:message key="calendar.week.saturday"/>' ],
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
					'<fmt:message key="calendar.month.december"/>' ],
			monthNamesShort : [
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
					'<fmt:message key="calendar.month.december"/>' ],
				hourText:'<fmt:message key="time.Hour"/>',
				minuteText:'<fmt:message key="time.Minute"/>',
				timeText:'<fmt:message key="time.Time"/>',
				timeOnlyTitle:'<fmt:message key="time.Time"/>',
				currentText:'<fmt:message key="time.Present"/>',
				closeText:'<fmt:message key="common.button.close"/>'
		}

		$('.arr').timepicker($.extend(dpconfig, {showSecond:false,timeFormat: 'HH:mm'}));
		//$('.arr').timepicker($.extend(dpconfig, {showSecond:true,timeFormat: 'HH:mm'}));
		
		$("#expDate").datepicker($.extend(dpconfig, {
			defaultDate : "+0w",
			changeMonth : true,
			numberOfMonths : 1,
			minDate : new Date(),
			onSelect : function(selectedDate, obj) {
				$(this).val(selectedDate);
				$("#dep").datepicker("option", {
					"minDate" : selectedDate
				});
			}
		}));
		
		/* $(".arr").datepicker($.extend(dpconfig, {
			defaultDate : "+0w",
			changeMonth : true,
			numberOfMonths : 1,
			minDate : new Date(),
			onSelect : function(selectedDate, obj) {
				$(this).val(selectedDate);
				$("#dep").datepicker("option", {
					"minDate" : selectedDate
				});
			}
		})); */
		
		/* $(".onlyAlpha").keypress(
				function(event) {
					var eventObj = event || e;
					var keyCode = eventObj.keyCode || eventObj.which;
					if ((keyCode >= 65 && keyCode <= 90)|| (keyCode >= 97 && keyCode <= 122))
						return true;
					else
						return false;
				}).focus(function() {
			this.style.imeMode = 'disabled';
		}).bind("paste", function() {
			var clipboard = window.clipboardData.getData("Text");
			if (/^[a-zA-Z]+$/.test(clipboard))
				return true;
			else
				return false;
		}); */
	}
	
	
	function init(){	
		var channelGoodsVOListJson = eval("(" + $("#channelGoodsVOListJsonArr").val() + ")");
		var dateListJson = eval("(" + $("#dateList").val() + ")");
		var roomTypeCode = '${wbeOrderVO.roomTypeCode}';
		var inventType = '${wbeOrderVO.inventType}';
		var wbeCalendarRowList = channelGoodsVOListJson[roomTypeCode];
		for(var i=0;i<wbeCalendarRowList.length;i++){
			var wbeCalendarRow = wbeCalendarRowList[i];
			if(wbeCalendarRow.inventType==inventType){
				var size = 0;
				var maxCellNumberOfUnits = 0;//最大几人价
				var minCellInventAvai = 0;//最大预定房间数
				
				var cellMap = wbeCalendarRow.cellMap;
				
				var numberOfUnitArray = new Array();
				var numberOfUnitArrayTemp = new Array();
				
				for(var j=0;j<dateListJson.length;j++){
					for(var key in cellMap){
						if(dateListJson[j]==getDateStr(key)){
							var date = getDateStr(key);
							var wbeCalendarCell = cellMap[key];
							var cellInventAvai = wbeCalendarCell.cellInventAvai;
							var rateAmountMap = wbeCalendarCell.rateAmountMap;
							//可选的最大房量
							if(minCellInventAvai==0){
								minCellInventAvai = wbeCalendarCell.cellInventAvai;
							}else{
								if(minCellInventAvai>wbeCalendarCell.cellInventAvai){
									minCellInventAvai = wbeCalendarCell.cellInventAvai;
								}
							}
							if(j==0){
								for(var k in rateAmountMap){
									numberOfUnitArrayTemp.push(k);
								}
							}else{
								for(var k in rateAmountMap){
									if(numberOfUnitArrayTemp.contains(k)){
										numberOfUnitArray.push(k);
									}
								}
								numberOfUnitArrayTemp = numberOfUnitArray;
								numberOfUnitArray = new Array();
							}
							size++;
						}
					}
				}
				numberOfUnitArray = numberOfUnitArrayTemp;
				
				//拆分渠道订单号
				var crsno = '${wbeOrderVO.crsno}';
				var crsnoArr= new Array(); //定义一数组 
				if(crsno!=null && crsno!=""&&crsno!=undefined){
					crsnoArr=crsno.split(","); //字符分割 
					var crsnoArrLsength=crsnoArr.length;
					//如果渠道订单号多于9个或者大于房间数，则不通过
					if( crsnoArrLsength> 9 ||crsnoArrLsength>minCellInventAvai){
						alert('<fmt:message key="ccm.WBEUI.BookingOrderOver"/>');
						window.location.href="/channelBookIng_bookPage.do";
					}else{
						//$("#numberCrsno").html(crsnoArrLsength+"");
						//changeChargeCrsno();
					}
				}
				
				sethtml(size,minCellInventAvai,numberOfUnitArray);
			}
		}
	}
	
	//设置入住成人、房间数，每日每间房价的html
	//size:几日，minCellInventAvai：最大房间数，numberOfUnitArray：几人价集合
	function sethtml(size,minCellInventAvai,numberOfUnitArray){
		var numberOfUnits = '${wbeOrderVO.numberOfUnits}';
		var numberOfUnitsHtml ='';
		for(var i=0;i<numberOfUnitArray.length;i++){
			var selectedHtml='';
			if(numberOfUnits == numberOfUnitArray[i]){
				selectedHtml = 'selected="selected"';
			}
			numberOfUnitsHtml = numberOfUnitsHtml+'<option value="'+numberOfUnitArray[i]+'" '+selectedHtml+'>'+numberOfUnitArray[i]+'</option>';
		}
		$("#numberOfUnits").html(numberOfUnitsHtml);
		
		var numberHtml ='';
		if(minCellInventAvai>9){
			minCellInventAvai=9;
		}
		for(var i=1;i<=minCellInventAvai;i++){
			numberHtml = numberHtml+'<option value="'+i+'">'+i+'</option>';
		}
		$("#number").html(numberHtml);
		
		var channelGoodsVOListJson = eval("(" + $("#channelGoodsVOListJsonArr").val() + ")");
		var dateListJson = eval("(" + $("#dateList").val() + ")");
		var roomTypeCode = '${wbeOrderVO.roomTypeCode}';
		var inventType = '${wbeOrderVO.inventType}';
		var wbeCalendarRowList = channelGoodsVOListJson[roomTypeCode];
		
		var rateDetailHtml = '';
		var guarrnteeHtml = '<option value="" ><fmt:message key="common.select.plesesSelect"/>	</option>';
		var total = 0;
		for(var i=0;i<wbeCalendarRowList.length;i++){
			var wbeCalendarRow = wbeCalendarRowList[i];
			if(wbeCalendarRow.inventType==inventType){
				var cellMap = wbeCalendarRow.cellMap;
				for(var j=0;j<dateListJson.length;j++){
					for(var key in cellMap){
						if(dateListJson[j]==getDateStr(key)){
							var date = getDateStr(key);
							var cellPrice = parseFloat(cellMap[key].cellPrice);
							total = total+cellPrice;
							if(j==0){
								rateDetailHtml = rateDetailHtml+'<tr >'
												+'<td class="td_title_channelbook2" height="100%;" rowspan="'+size+'" style="border-top:0px;vertical-align:middle;" align="center"> <span ><fmt:message key="ccm.WBEUI.Rateperroompernight"/>	</span> </td>'
												+'<td style="border-top:0px;">'+date+'</td>'
												+'<td style="border-top:0px;">'+cellMap[key].cellPrice+'</td></tr>';
							}else{
								rateDetailHtml = rateDetailHtml+'<tr >'
												+'<td>'+date+'</td>'
												+'<td>'+cellMap[key].cellPrice+'</td></tr>';
							}
							if(j==0){
								var guarrnteeList = cellMap[key].guarrnteeList;
								for(var n=0;n<guarrnteeList.length;n++){
									if(guarrnteeList[n].policyName==undefined){
										guarrnteeHtml='';
										continue;										
									}
									guarrnteeHtml = guarrnteeHtml+'<option value="'+guarrnteeList[n].policyName+'" guaranteeId="'+guarrnteeList[n].guaranteeId+'">'+guarrnteeList[n].policyName+'</option>';
								}
							}
							
						}
					}
				}
			}
		}
		$("#rateDetail").html(rateDetailHtml);
		$("#guarrntee").html(guarrnteeHtml);
		$("#charge").val(total+'${wbeOrderVO.currencyCode }');
	}
	
	//改变担保
	function guarrnteeChange(obj){
		var guarrnteeType = $(obj).val();
		if(guarrnteeType=="CCGTD"){
			$("#guarrntee_card").show();
			$("#guarrntee_card").attr("ishow",1);
		}else{
			$("#guarrntee_card").hide();
			$("#guarrntee_card").attr("ishow",0);
		}
	}
	
	//改变房间数
	function changeCharge(){
		var number = $("#number").val();
		var rateDetail = $("#rateDetail").find("tr");
		var total = 0;
		for(var i=0;i<rateDetail.length;i++){
			var td = $(rateDetail).eq(i).find("td");
			var price = parseFloat($(td).eq(td.length-1).html());
			total = total+price;
		}
		
		total = number*total;
		$("#charge").val(total.toFixed(2)+'${wbeOrderVO.currencyCode }');
		var oldNumber = $("#guestList").find(".guestList").length;
		if(number>oldNumber){
			for(var i=0;i<number-oldNumber;i++){
				$("#guestList").append(guestHtml);
			}
			for(var i=0;i<number;i++){
				$(".title_channelbooking_guest").find("strong").eq(i).html("<fmt:message key='ccm.WBEUI.Guest'/>"+(i+1));
			}
		}else{
			for(var i=oldNumber;i>=number;i--){
				$("#guestList").find(".guestList").eq(i).remove();;
			}
		}
		
		initDate();
	}
	
	//改变入住成人
	function changeRateDetail(){
		var numberOfUnits = $("#numberOfUnits").val();
		var channelGoodsVOListJson = eval("(" + $("#channelGoodsVOListJsonArr").val() + ")");
		var dateListJson = eval("(" + $("#dateList").val() + ")");
		var roomTypeCode = '${wbeOrderVO.roomTypeCode}';
		var inventType = '${wbeOrderVO.inventType}';
		var wbeCalendarRowList = channelGoodsVOListJson[roomTypeCode];
		
		var rateDetailHtml = '';
		for(var i=0;i<wbeCalendarRowList.length;i++){
			var wbeCalendarRow = wbeCalendarRowList[i];
			if(wbeCalendarRow.inventType==inventType){
				var cellMap = wbeCalendarRow.cellMap;
				for(var j=0;j<dateListJson.length;j++){
					for(var key in cellMap){
						if(dateListJson[j]==getDateStr(key)){
							var date = getDateStr(key);
							var rateAmountMap = cellMap[key].rateAmountMap;
							var cellPrice = 0;
							for(var k in rateAmountMap){
								if(k==numberOfUnits){
									cellPrice = rateAmountMap[k].baseAmount;
								}
							}
							
							if(j==0){
								rateDetailHtml = rateDetailHtml+'<tr >'
												+'<td class="td_title_channelbook2" height="100%;" rowspan="'+dateListJson.length+'" style="border-top:0px;vertical-align:middle;" align="center"> <span ><fmt:message key="ccm.WBEUI.Rateperroompernight"/></span> </td>'
												+'<td style="border-top:0px;">'+date+'</td>'
												+'<td style="border-top:0px;">'+cellPrice+'</td></tr>';
							}else{
								rateDetailHtml = rateDetailHtml+'<tr >'
												+'<td>'+date+'</td>'
												+'<td>'+cellPrice+'</td></tr>';
							}
						}
					}
				}
			}
		}
		$("#rateDetail").html(rateDetailHtml);
		changeCharge();
	}
	
	
	//提交
	function submit(obj){
		
		//房间数
		var number = $("#number").val();
		
		//渠道订单号
		var crsno = '${wbeOrderVO.crsno}';
		
		//如果渠道订单号多于9个或者大于房间数或者房间数和渠道订单号数不一致，则不通过 
		var crsnoArr= new Array(); //定义一数组 
		if(crsno!=null && crsno!=""&&crsno!=undefined){
			crsnoArr=crsno.split(","); //字符分割 
			var crsnoArrLsength=crsnoArr.length;
			if( crsnoArrLsength!=number ||crsnoArrLsength> 9 ||crsnoArrLsength>number){
				alert('<fmt:message key="ccm.WBEUI.RoomReservationNumber"/>');
				return false;
			}

			for(var i=0;i<crsnoArr.length;i++){
				//渠道订单号只能输入数字或字母组合
				if(!checkValueType(crsnoArr[i])){
					alert('<fmt:message key="ccm.WBEUI.CheckCRSNO"/>');
					return false;
				}
			}
			
		}
		
		var guarrntee = $("#guarrntee").val();
		if(guarrntee==''){
			alert("<fmt:message key='ccm.WBEUI.error.010'/>");
			return false;
		}
		var ishow = $("#guarrntee_card").attr("ishow");
		if(guarrntee=='CCGTD' && ishow==1){
			var cardCode = $("#guarrntee_card").find("select").val();
			var cardNumber = $("#guarrntee_card").find("input").eq(0).val().trim();
			var expirationDate = $("#guarrntee_card").find("input").eq(1).val().trim();
			var cardHolderName = $("#guarrntee_card").find("input").eq(2).val().trim();
			if(cardCode=='' || cardNumber=='' || expirationDate=='' || cardHolderName==''){
				alert("<fmt:message key='ccm.WBEUI.error.001'/>");
				return false;
			}
			$("#nextForm").find("input[name='wbeOrderVO.cardCode']").val(cardCode);
			$("#nextForm").find("input[name='wbeOrderVO.cardHolderName']").val(cardHolderName);
			$("#nextForm").find("input[name='wbeOrderVO.cardNumber']").val(cardNumber);
			$("#nextForm").find("input[name='wbeOrderVO.expirationDate']").val(expirationDate);
		}
		$("#nextForm").find("input[name='wbeOrderVO.payment']").val(guarrntee);
		
		$("#guestInput").html('');
		
		var length = $("#guestList").find(".guestList").length;
		var html='';
		for(var i=0;i<length;i++){
			var name1 = $("#guestList").find(".guestList").eq(i).find("input").eq(0).val().trim();
			var name2 = $("#guestList").find(".guestList").eq(i).find("input").eq(1).val().trim();
			var name3 = $("#guestList").find(".guestList").eq(i).find("input").eq(2).val().trim();
			var tel = $("#guestList").find(".guestList").eq(i).find("input").eq(3).val();
			var arr = $("#guestList").find(".guestList").eq(i).find("input").eq(4).val();
			var need = $("#guestList").find(".guestList").eq(i).find("input").eq(5).val();
			if(name1==''||name2==''){
				alert("<fmt:message key='ccm.WBEUI.error.002'/>");
				return false;
			}
			var regExp = /^[A-Za-z]*$/;
			/* if(!regExp.test(name1)||!regExp.test(name2)){
				alert("<fmt:message key='ccm.WBEUI.error.011'/>");
				return false;
			} */
			
			if(lenFor(need)>400){
				alert("<fmt:message key='ccm.WBEUI.message.007'/>");
				return false;
			}
			
			html = html+'<input type="hidden" name="wbeOrderVO.wbeGuestVoList['+i+'].name1" value="'+name1+'">'
				   +'<input type="hidden" name="wbeOrderVO.wbeGuestVoList['+i+'].name2" value="'+name2+'">'
				   +'<input type="hidden" name="wbeOrderVO.wbeGuestVoList['+i+'].name3" value="'+name3+'">'
				   +'<input type="hidden" name="wbeOrderVO.wbeGuestVoList['+i+'].tel" value="'+tel+'">'
				   +'<input type="hidden" name="wbeOrderVO.wbeGuestVoList['+i+'].arr" value="'+arr+'">'
				   +'<input type="hidden" name="wbeOrderVO.wbeGuestVoList['+i+'].need" value="'+need+'">';
		}
		
		var number = $("#number").val();
		var numberOfUnits = $("#numberOfUnits").val();
		
		$("#nextForm").find("input[name='wbeOrderVO.numberOfUnits']").val(numberOfUnits);
		$("#nextForm").find("input[name='wbeOrderVO.number']").val(number);
		
		
		$("#guestInput").html(html);
		setDailyRateFromHtml();
		
		$(obj).attr("disabled","disabled");	
		$(obj).addClass("black");

		document.nextForm.action = "/channelBookIng_booking.do"
		document.nextForm.submit();
	}
	
	
	function setDailyRateFromHtml(){
		var numberOfUnits = $("#numberOfUnits").val();
		var channelGoodsVOListJson = eval("(" + $("#channelGoodsVOListJsonArr").val() + ")");
		var dateListJson = eval("(" + $("#dateList").val() + ")");
		var roomTypeCode = '${wbeOrderVO.roomTypeCode}';
		var inventType = '${wbeOrderVO.inventType}';
		var wbeCalendarRowList = channelGoodsVOListJson[roomTypeCode];
		
		var html = '';
		for(var i=0;i<wbeCalendarRowList.length;i++){
			var wbeCalendarRow = wbeCalendarRowList[i];
			if(wbeCalendarRow.inventType==inventType){
				var cellMap = wbeCalendarRow.cellMap;
				for(var j=0;j<dateListJson.length;j++){
					for(var key in cellMap){
						if(dateListJson[j]==getDateStr(key)){
							var date = getDateStr(key);
							var rateAmountMap = cellMap[key].rateAmountMap;
							var cellPrice = 0;
							for(var k in rateAmountMap){
								if(k==numberOfUnits){
									cellPrice = rateAmountMap[k].baseAmount;
								}
							}
							html = html+'<input type="hidden" name="wbeOrderVO.dailRateList['+j+'].priceDate" value="'+date+'">'
							   +'<input type="hidden" name="wbeOrderVO.dailRateList['+j+'].price" value="'+cellPrice.toFixed(2)+'">'
						}
					}
				}
			}
		}
		$("#dailRate").html(html);
	}
	
	
	function back(){
		document.nextForm.action = "/channelBookIng_bookPage.do?tmenuId=1201"
		document.nextForm.submit();
	}
	
	//将毫秒转为日期，如：1456502400000
	function getDateStr(number){
		var myDate = new Date(parseInt(number));
		return myDate.Format("yyyy-MM-dd");
	}
	
	function dateDiff(sDate1, sDate2){  //sDate1和sDate2是2002-12-18格式  
        var aDate, oDate1, oDate2, iDays;  
        aDate = sDate1.split("-");  
        oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  //转换为12-18-2002格式  
        aDate = sDate2.split("-");  
        oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  
        iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 /24);  //把相差的毫秒数转换为天数  
        return iDays;  
	}
	
	// 对Date的扩展，将 Date 转化为指定格式的String
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	// 例子： 
	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
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
	

	Array.prototype.contains = function(item) {
		for (i = 0; i < this.length; i++) {
			if (this[i] == item) {
				return true;
			}
		}
		return false;
	};
	
	
	function lenFor (str){
		　　var byteLen=0,len=str.length;
		　　if(str){
		　　　　for(var i=0; i<len; i++){
		　　　　　　if(str.charCodeAt(i)>255){
		　　　　　　　　byteLen += 2;
		　　　　　　}
		　　　　　　else{
		　　　　　　　　byteLen++;
		　　　　　　}
		　　　　}
		　　　　return byteLen;
		　　}
		　　else{
		　　　　return 0;
		　　}
		}
</script>
