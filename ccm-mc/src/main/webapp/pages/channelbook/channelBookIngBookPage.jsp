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
}

.td_title_channelbook {
	align: center;
	background-color: #DCE4EF;
	text-align: right;
	font-weight: bold;
	width: 90px;
}

.td_title_channelbook2 {
	align: center;
	background-color: #507AAE;
	text-align: center;
	width: 90px;
}

.STYLE5 {
	color: #0066CC;
	font-weight: bold;
}

.STYLE6 {
	color: #FFFFFF;
	font-weight: bold;
}

.loading{
    width:160px;  
    height:56px;  
    position: absolute;  
    top:50%;  
    left:50%;  
    line-height:56px;  
    color:#fff;  
    padding-left:60px;  
    font-size:15px;  
   /*  background: url(img/loader.gif) no-repeat 10px 50%;   */
    opacity: 0.7;  
    z-index:9999;  
    -moz-border-radius:20px;  
    -webkit-border-radius:20px;  
    border-radius:20px;  
    filter:progid:DXImageTransform.Microsoft.Alpha(opacity=70);  
}  
</style>
<div class="CCMmainConter w1200 " style="width: 960px;">
	<div class="title_channelbooking"><fmt:message key="ccm.WBEUI.searchTitle"/></div>
<div class="row">
<div class="col-md-10 col-md-10">
	<table class="table" border="1" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
		<tbody>
			<tr class="tr_channelbook">
				<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.HotelCodeOrHotelName"/><span style="color: red">*</span>&nbsp;</span>
				</td>
				<td colspan="9">
					<select id="hotel" class="fxt w101 mgR12" onchange="getRoomTypeList()">
						<option value=""><fmt:message key="common.select.plesesSelect"/></option>
						<c:forEach items="${hotelVOList }" var="hotelVO">
							<option value="${hotelVO.hotelId }" chainCode="${hotelVO.chainCode }" hotelName="${hotelVO.hotelName}" hotelCode="${hotelVO.hotelCode }">${ hotelVO.hotelCode}-${hotelVO.hotelName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr class="tr_channelbook">
				<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.ArrivalDate"/></span> <span style="color: red">*</span>&nbsp;</td>
				<td colspan="9"><input id="arr" placeholder="<fmt:message key="ccm.WBEUI.ArrivalDate"/>" class="fxt w204 calendar required" readonly="readonly" type="text"></td>
			</tr>
			<tr class="tr_channelbook">
				<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.DepartureDate"/></span><span style="color: red">*</span>&nbsp; </td>
				<td colspan="9"><input id="dep" placeholder="<fmt:message key="ccm.WBEUI.DepartureDate"/>" class="fxt w204 calendar required" readonly="readonly" type="text"></td>
			</tr>
			<tr class="tr_channelbook">
				<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.Audlts"/><span style="color: red">*</span>&nbsp;</span>
				</td>
				<td colspan="9"><select class="fxt w101 mgR12" id="numberOfUnits"
					name="order.gstno">
						<c:forEach var="v" begin="1" end="5">
							<option value="${v}" >${v}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr class="tr_channelbook">
				<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.RoomTypes"/><span style="color: red">*</span>&nbsp;</span>
				</td>
				<td colspan="9">
					<select class="fxt w101 mgR12"  id="roomTypeList">
					</select>
				</td>
			</tr>
			<tr class="tr_channelbook">
				<td class="td_title_channelbook">
				<span class="STYLE5">
				<fmt:message key="ccm.BookingDepositReport.AccessCode"/></span></td>
				<td colspan="5">
				<input placeholder="<fmt:message key="ccm.BookingDepositReport.AccessCode"/>" 
				id="ACCESSCODE" class="fxt w204">
				</td>
			</tr>
			<!-- 渠道订单号 -->
			<tr class="tr_channelbook">
				<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.Channel.ChannelReservationNumber"/></td>
				<td colspan="5">
				<input id="crsno" 
				placeholder="<fmt:message key="ccm.Channel.ChannelReservationNumber"/>" 
				class="fxt w204">
				</td>
			</tr>
		</tbody>
	</table>
	</div>
		<div class="col-md-1 col-lg-1 " style="margin-top: 220px;">
			<a href="javascript:search();" class="btn_1 blue"><fmt:message key="ccm.WBEUI.Search"/></a>
		</div>
	</div>
	
	
	<div class="row">
		<div class="col-md-10 col-md-10">
			<table class="table" border="1" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
				<tbody id="body_table">
					<!-- <tr>
						<td class="td_title_channelbook2"><span class="STYLE6">日期</span></td>
						<td class="td_title_channelbook2"><span class="STYLE6">2016/2/27</span></td>
						<td class="td_title_channelbook2"><span class="STYLE6">2016/2/27</span></td>
						<td class="td_title_channelbook2"><span class="STYLE6">2016/2/27</span></td>
						<td class="td_title_channelbook2"><span class="STYLE6">2016/2/27</span></td>
					</tr>
					<tr>
						<td colspan="5" style="text-align: center" bgcolor="#D0D7E3"><strong>房型B</strong></td>
					</tr>
					<tr style="background-color: #E9ECF2;">
						<td style="text-align: center">可售保留房房量</td>
						<td>
							<div align="center"><span style="color: green">500${wbeOrderVO.currencyCode }</span></div>
							<div style="display: inline; float: left;">4</div>
							<div style="display: inline; float: right;">
								<input type="checkbox" name="checkbox1" value="checkbox" />
							</div>
						</td>
						<td>
							<div align="center"><span style="color: green">500${wbeOrderVO.currencyCode }</span></div>
							<div style="display: inline; float: left;">4</div>
							<div style="display: inline; float: right;">
								<input type="checkbox" name="checkbox1" value="checkbox" />
							</div>
						</td>
						<td>
							<div align="center"><span style="color: green">500${wbeOrderVO.currencyCode }</span></div>
							<div style="display: inline; float: left;">4</div>
							<div style="display: inline; float: right;">
								<input type="checkbox" name="checkbox1" value="checkbox" />
							</div>
						</td>
						<td>
							<div align="center"><span style="color: green">500${wbeOrderVO.currencyCode }</span></div>
							<div style="display: inline; float: left;">4</div>
							<div style="display: inline; float: right;">
								<input type="checkbox" name="checkbox1" value="checkbox" />
							</div>
						</td>
					</tr>
					<tr style="background-color: #D0D7E3;">
						<td style="text-align: center">可售保留房房量</td>
						<td>
							<div align="center"><span style="color: green">500${wbeOrderVO.currencyCode }</span></div>
							<div style="display: inline; float: left;">4</div>
							<div style="display: inline; float: right;">
								<input type="checkbox" name="checkbox1" value="checkbox" />
							</div>
						</td>
						<td align="center">
							<span style="color: red">N/A</span>
						</td>
						<td align="center">
							<span style="color: red">N/A</span>
						</td>
						<td>
							<div align="center"><span style="color: green">500${wbeOrderVO.currencyCode }</span></div>
							<div style="display: inline; float: left;">4</div>
							<div style="display: inline; float: right;">
								<input type="checkbox" name="checkbox1" value="checkbox" />
							</div>
						</td>
					</tr> -->
				</tbody>
			</table>
		</div>
		<div class="col-md-1 col-lg-1 " style="height: 100%;">
			<div style="position:absolute;margin-top:10px;display: none;" id="submitButten">
				<a href="javascript:submit();" class="btn_1 blue" ><fmt:message key="ccm.WBEUI.Reserve"/></a>
			
			</div>
		</div>
	</div>
	
	<div id="showDetail" class="ft_layer room_status room_open">
				<div class="off"></div>
				<div class="title">
					<strong class="cl_green"><fmt:message key="common.Detail"/></strong>
				</div>
				<div class="info">
					<hr class="dashed">
					<span class="cancelDescription"></span> 
				</div>
			</div>

</div>
			<form action="" name="nextForm" id="nextForm" method="post">
				<input type="hidden" name="wbeOrderVO.hotelId" value="${wbeOrderVO.hotelId }">
				<input type="hidden" name="wbeOrderVO.hotelCode" value="${wbeOrderVO.hotelCode }">
				<input type="hidden" name="wbeOrderVO.hotelName" value="${wbeOrderVO.hotelName }">
				<input type="hidden" name="wbeOrderVO.chainCode" value="${wbeOrderVO.chainCode }">
				<input type="hidden" name="wbeOrderVO.numberOfUnits" value="${wbeOrderVO.numberOfUnits }">
				<input type="hidden" name="wbeOrderVO.arr" value="${wbeOrderVO.arr }">
				<input type="hidden" name="wbeOrderVO.dep" value="${wbeOrderVO.dep }">
				<input type="hidden" name="wbeOrderVO.number" value="${wbeOrderVO.number }">
				<input type="hidden" name="wbeOrderVO.roomTypeCode" value="${wbeOrderVO.roomTypeCode }">
				<input type="hidden" name="wbeOrderVO.roomTypeName" value="${wbeOrderVO.roomTypeName }">
				<input type="hidden" name="wbeOrderVO.ratePlanCode" value="${wbeOrderVO.ratePlanCode }">
				<input type="hidden" name="wbeOrderVO.ratePlanId" value="${wbeOrderVO.ratePlanId }">
				<input type="hidden" name="wbeOrderVO.accessCode" value="${wbeOrderVO.accessCode }">
				<input type="hidden" name="wbeOrderVO.accessCodeType" value="${wbeOrderVO.accessCodeType }">
				<input type="hidden" name="wbeOrderVO.inventType" value="${wbeOrderVO.inventType }">
				<input type="hidden" name="wbeOrderVO.currencyCode" value="${wbeOrderVO.currencyCode }">
				
				<input type="hidden" name="wbeOrderVO.crsno" value="${wbeOrderVO.crsno }">
				
				<input type="hidden" name="channelGoodsVOListJsonArr" id="channelGoodsVOListJsonArr">
			</form>


<script type="text/javascript">
	$(document).ready(
			function() {
				//日期显示
				var dpconfig = {
					dateFormat : "yy-mm-dd",
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
							'<fmt:message key="calendar.month.december"/>' ]
				}
				$("#arr").datepicker($.extend(dpconfig, {
					defaultDate : "+0w",
					changeMonth : true,
					numberOfMonths : 1,
					minDate : new Date(),
					onSelect : function(selectedDate, obj) {
						$(this).val(selectedDate);
						var selectedTime = new Date(selectedDate);
						var min = new Date(selectedTime.getTime()+1000 * 60 * 60 * 24);
						$("#dep").datepicker("option", {
							"minDate" : min
						});
					}
				}));

				$("#dep").datepicker($.extend(dpconfig, {
					defaultDate : "+0w",
					changeMonth : true,
					numberOfMonths : 1,
					dateFormat : 'yy-mm-dd',
					onSelect : function(selectedDate, obj) {
						$(this).val(selectedDate);
						$("#arr").datepicker("option", {
							"maxDate" : selectedDate
						});
					}
				}));

			});
	
	function submit(){
		
		//渠道订单号不可重复
		var crsno= $("#crsno").val(); 
		var crsnoArr= new Array(); //定义一数组 
		if(crsno!=null && crsno!=""&&crsno!=undefined){
			crsnoArr=crsno.split(","); //字符分割 
			if(isRepeat(crsnoArr))
			{
				alert('<fmt:message key="ccm.WBEUI.BookingOrderRepeated"/>');
				return false;
			}else if(crsnoArr.length>9){
				alert('<fmt:message key="ccm.WBEUI.BookingOrderOver"/>');
				return false;
			}
			
		}
		
		var trList = $("#body_table").find("tr");
		var flag = false;
		var index = -1;
		for(var i=0;i<trList.length;i++){
			var ischecked = $(trList).eq(i).attr("ischecked");
			if(ischecked==1){
				index = i;
				break;
			}
		}
		if(index==-1){
			alert("<fmt:message key='ccm.WBEUI.error.003'/>");
			return false;
		}
		var channelbook_checkboxList = $(trList).eq(index).find("td");
		var indexArray = new Array();
		for(var i=0;i<channelbook_checkboxList.length;i++){
			if($(channelbook_checkboxList).eq(i).find(".channelbook_checkbox").is(':checked')){
				indexArray.push(i);
			}
		}
		if(indexArray.length>=2){
			for(var i=0;i<indexArray.length-1;i++){
				if(indexArray[i]+1!=indexArray[i+1]){
					alert("<fmt:message key='ccm.WBEUI.error.004'/>");
					return false;
				}
			}
		}
		var hotelId = $("#hotel").val();
		var hotelCode = $("#hotel").find("option:selected").attr("hotelCode");
		var hotelName = $("#hotel").find("option:selected").attr("hotelName");
		var chainCode = $("#hotel").find("option:selected").attr("chainCode");
		var accessCode = $("#ACCESSCODE").val();
		var numberOfUnits = $("#numberOfUnits").val();
		var roomtypecode = $(trList).eq(index).attr("roomtypecode");
		var roomTypeName = $(trList).eq(index).attr("roomTypeName");
		var ratecode = $(trList).eq(index).attr("ratecode");
		var rateplanid = $(trList).eq(index).attr("rateplanid");
		var inventType = $(trList).eq(index).attr("inventType");
		
		var crsno = $("#crsno").val();
		
		var arr = $(trList).eq(index).find("td").eq(indexArray[0]).attr("date");
		var dep = $(trList).eq(index).find("td").eq(indexArray[indexArray.length-1]).attr("date");
		var date_arr = new Date(arr);
		var date_dep = new Date(dep);
		var d2 = new Date(date_dep.getFullYear(), date_dep.getMonth(), date_dep.getDate() + 1);
		
		$("#nextForm").find("input[name='wbeOrderVO.hotelId']").val(hotelId);
		$("#nextForm").find("input[name='wbeOrderVO.hotelCode']").val(hotelCode);
		$("#nextForm").find("input[name='wbeOrderVO.hotelName']").val(hotelName);
		$("#nextForm").find("input[name='wbeOrderVO.chainCode']").val(chainCode);
		$("#nextForm").find("input[name='wbeOrderVO.numberOfUnits']").val(numberOfUnits);
		$("#nextForm").find("input[name='wbeOrderVO.arr']").val(date_arr.Format("yyyy-MM-dd"));
		$("#nextForm").find("input[name='wbeOrderVO.dep']").val(d2.Format("yyyy-MM-dd"));
		$("#nextForm").find("input[name='wbeOrderVO.roomTypeCode']").val(roomtypecode);
		$("#nextForm").find("input[name='wbeOrderVO.roomTypeName']").val(roomTypeName);
		$("#nextForm").find("input[name='wbeOrderVO.ratePlanId']").val(rateplanid);
		$("#nextForm").find("input[name='wbeOrderVO.ratePlanCode']").val(ratecode);
		$("#nextForm").find("input[name='wbeOrderVO.accessCode']").val(accessCode);
		$("#nextForm").find("input[name='wbeOrderVO.inventType']").val(inventType);
		$("#nextForm").find("input[name='wbeOrderVO.crsno']").val(crsno);
		
		document.nextForm.action = "/channelBookIng_bookInfo.do"
		document.nextForm.submit();
	}
	
	
	
	
	
	
	
	function getRoomTypeList(){
		var hotelId = $("#hotel").val(); 
		var hotelCode = $("#hotel").find("option:selected").attr("hotelCode");
		if(hotelId==''){
			$("#roomTypeList").html('');
			return;
		}
		
		 //获取放行列表    
		$.ajax({
			url : 'channelBookIng_getRoomTypeList.do',
			cache : false,
			data : {"channelGoodsVO.hotelId":hotelId},
			dataType : "text",
			type : 'post',
			success : function(data) {
				data = eval("(" + data + ")");
				var html = '<option value=""><fmt:message key="common.Unlimited"/></option>';
				for(var i=0;i<data.length;i++){
					var channelGoodsVO = data[i];
					html = html+'<option value="'+channelGoodsVO.roomTypeId+'" roomTypeCode="'+channelGoodsVO.roomTypeCode+'">'+channelGoodsVO.roomTypeCode+'-'+channelGoodsVO.roomTypeName+'</option>';
				}
				$("#roomTypeList").html(html);
			}
		});
		
	}
	
	//判断数组元素是否重复
	function isRepeat(arr){
		var hash = {};
		for(var i in arr) 
		{
			if(hash[arr[i]])
				return true;
			hash[arr[i]] = true;
		}
		return false;
	}
	
	
	
	function search(){
		
		//验证渠道订单号
		var crsno= $("#crsno").val(); 
		
		var crsnoArr= new Array(); //定义一数组 
		if(crsno!=null && crsno!=""&&crsno!=undefined){
			crsnoArr=crsno.split(","); //字符分割 
			if(isRepeat(crsnoArr))
			{
				//渠道订单号不可重复
				alert('<fmt:message key="ccm.WBEUI.BookingOrderRepeated"/>');
				return false;
			}else if(crsnoArr.length>9){
				//渠道订单号不可超过9个
				alert('<fmt:message key="ccm.WBEUI.BookingOrderOver"/>');
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
		
		var hotelId = $("#hotel").val(); 
		var hotelCode = $("#hotel").find("option:selected").attr("hotelCode");
		
		if(hotelId==''){
			alert("<fmt:message key='ccm.WBEUI.error.005'/>");
			return;
		}
		
		var arr = $("#arr").val();
		var dep = $("#dep").val();
		if(arr==''){
			alert("<fmt:message key='ccm.WBEUI.error.006'/>");
			return;
		}
		if(dep==''){
			alert("<fmt:message key='ccm.WBEUI.error.007'/>");
			return;
		}
		var n = dateDiff(arr,dep);
		if(n>14){
			alert("<fmt:message key='ccm.WBEUI.error.008'/>");
			return;
		}
		
		var roomTypeId = $("#roomTypeList").val();
		var roomTypeListLength = $("#roomTypeList").find("option").length;
		if(roomTypeListLength<2){
			alert("<fmt:message key='ccm.WBEUI.error.009'/>");
			return false;
		}
		if(roomTypeId==''||roomTypeId==undefined){
			 roomTypeId = $("#roomTypeList option").map(function(){return $(this).val();}).get().join(",")
		}
		
		var accessCode = $("#ACCESSCODE").val();
		var numberOfUnits = $("#numberOfUnits").val();
		var crsno = $("#crsno").val();
		
		$("#body_table").html('<tr><td class="td_title_channelbook2 loading"><span class="STYLE6">Loading ...</span></td>');
		
		//查询    
		$.ajax({
			url : 'channelBookIng_bookSearch.do',
			cache : false,
			data : {"wbeSearchCreteria.hotelId":hotelId,
				"wbeSearchCreteria.hotelCode":hotelCode,
				"wbeSearchCreteria.accessCode":accessCode,
				"wbeSearchCreteria.arrDate":arr,
				"wbeSearchCreteria.depDate":dep,
				"wbeSearchCreteria.numberOfUnits":numberOfUnits,
				"roomTypeIds":roomTypeId,
				"crsno":crsno
			},
			dataType : "json",
			type : 'post',
			success : function(data) {
				setBody(data);
			}
		});
		
	}
	
	function setBody(data){
		var dateList = data.dateList;
		if(data.channelGoodsVOListJsonArr.length==2){
			$("#body_table").html('<tr><td class="td_title_channelbook2"><span class="STYLE6"><fmt:message key="ccm.WBEUI.message.005"/></span></td>');
			
			$("#submitButten").show();
			return;
		}
		$("#channelGoodsVOListJsonArr").val(data.channelGoodsVOListJsonArr);
		var channelGoodsVOListJson = eval("(" + data.channelGoodsVOListJsonArr + ")");
		var body_tableHtml='';
		//头部日期tr
		var firstTr='<tr><td class="td_title_channelbook2"><span class="STYLE6"><fmt:message key="ccm.WBEUI.Date"/></span></td>';
		for(var i=0;i<dateList.length;i++){
			firstTr = firstTr+'<td class="td_title_channelbook2"><span class="STYLE6">'+dateList[i]+'</span></td>'
		}
		firstTr = firstTr+'</tr>';
		
		var hasData=false;
		//数据tr
		var tr = '';
		for(var key in channelGoodsVOListJson){
			//合并一正行单元格的房型td
			var wbeCalendarRowList = channelGoodsVOListJson[key];
			var roomTypeName = key;
			if(wbeCalendarRowList[0]!=undefined && wbeCalendarRowList[0].roomTypeName!=undefined){
				roomTypeName = wbeCalendarRowList[0].roomTypeName;
			}else{
				continue;
			}
			var roomTypeHtml = '<tr><td colspan="'+(dateList.length+1)+'" style="text-align: center" bgcolor="#D0D7E3"><strong>'+roomTypeName+'</strong></td></tr>';		
			
			var allotTR='';
			for(var i=0;i<wbeCalendarRowList.length;i++){
				var wbeCalendarRow = wbeCalendarRowList[i];
				if(wbeCalendarRow.inventType=='ALLOT'){
					allotTR =allotTR+ '<tr style="background-color: #E9ECF2;" roomTypeName="'+wbeCalendarRow.roomTypeName+'" inventType="'+wbeCalendarRow.inventType+'" roomTypeCode="'+wbeCalendarRow.roomTypeCode+'" rateCode="'+wbeCalendarRow.ratePlanCode+'" ratePlanId="'+wbeCalendarRow.ratePlanId+'" rowNumberOfUnits="'+wbeCalendarRow.rowNumberOfUnits+'">'
							+'<td style="text-align: center"><fmt:message key="ccm.WBEUI.NumberofAllotment"/></td>';
				}else if(wbeCalendarRow.inventType=='FREESELL'){
					allotTR =allotTR+ '<tr style="background-color: #D0D7E3;" roomTypeName="'+wbeCalendarRow.roomTypeName+'" inventType="'+wbeCalendarRow.inventType+'" roomTypeCode="'+wbeCalendarRow.roomTypeCode+'" rateCode="'+wbeCalendarRow.ratePlanCode+'" ratePlanId="'+wbeCalendarRow.ratePlanId+'" rowNumberOfUnits="'+wbeCalendarRow.rowNumberOfUnits+'">'
							+'<td style="text-align: center"><fmt:message key="ccm.WBEUI.NumberofFreeSell"/></td>';
				}
				
				var cellMap = wbeCalendarRow.cellMap;
				var cellList = '';
				for(var j=0;j<dateList.length;j++){
					var cellTd = '';
					var flag=false;
					for(var dateKey in cellMap){
						if(dateList[j]==getDateStr(dateKey)){
							var wbeCalendarCell = cellMap[dateKey];
							var cellPrice = wbeCalendarCell.cellPrice;
							var cellInventAvai = wbeCalendarCell.cellInventAvai;
							var cancelPolicyVO = wbeCalendarCell.cancelPolicyVO;
							var cancelPolicyDescription = '';
							if(cancelPolicyVO!=undefined){
								cancelPolicyDescription = cancelPolicyVO.description
								if(cancelPolicyDescription==undefined){
									cancelPolicyDescription = '';
								}
							}
							//无房价或者无房量
							if(cellPrice==''||cellInventAvai==''||cellInventAvai==0||cellInventAvai==undefined||cellPrice==undefined){
								cellTd='<td align="center"><span style="color: red">N/A</span>';
								flag=true;
								continue;
							}else{
								cellTd = '<td date="'+dateList[j]+'" onMouseleave="hideDetail()"  onMouseover="showDetail(this,'+"'"+cancelPolicyDescription+"'"+')"><div align="center">'+'${wbeOrderVO.currencyCode }'+'<span style="color: green">'+cellPrice+'</span></div>'
										+'<div style="display: inline; float: left;">'+cellInventAvai+'</div>'
										+'<div style="display: inline; float: right;">'
										+'<input type="checkbox" value="checkbox" class="channelbook_checkbox" onclick="checkedCheckbox(this);"/>'
										+'</div>';
								flag=true;
								hasData = true;
							}
						}
					}
					//无该日数据
					if(!flag){
						cellTd='<td align="center"><span style="color: red">N/A</span>';
					}
					cellTd = cellTd+"</td>";
					cellList = cellList+cellTd;
				}
				allotTR = allotTR+cellList+'</tr>';
			}
			tr = tr+roomTypeHtml+allotTR;
		}
		if(!hasData){
			$("#body_table").html('<tr><td class="td_title_channelbook2"><span class="STYLE6"><fmt:message key="ccm.WBEUI.message.005"/></span></td>');
			
			$("#submitButten").show();
			return;
		}
		
		
		body_tableHtml = firstTr + tr;
		$("#body_table").html(body_tableHtml);
		
		$("#submitButten").show();
		
	}
	 function hideDetail(){
		$('.room_status').hide();
	 }
	
	//控制只能选同一行
	function checkedCheckbox(obj){
		var ischecked = $(obj).is(':checked');
		//选中，只能选这一行，其余复选框不能选
		if(ischecked){
			$(".channelbook_checkbox").attr("disabled",true);
			$(obj).parents("tr").attr("ischecked",1);
		}
		//取消选中
		else{
			var checkboxList = $(obj).parents("tr").find(".channelbook_checkbox");
			var flag=false;
			for(var i=0;i<checkboxList.length;i++){
				if($(checkboxList).eq(i).is(':checked')){
					flag = true;
				}
			}
			if(!flag){
				$(".channelbook_checkbox").attr("disabled",false);
				$(obj).parents("tr").attr("ischecked",2);
			}
		}
		//当前行可以选
		$(obj).parents("tr").find(".channelbook_checkbox").attr("disabled",false);
	}
	
	function showDetail(obj,cancelPolicyDescription){
		var local = "${locale}";
		var h = 420;
		if(local=='en_US'){
			h = 435;
		}
	    //悬浮窗口显示
		var description = '<fmt:message key="rate.cancelRule"/>：'+cancelPolicyDescription;
		$p_left = $(obj).position().left + 400;
		$p_top = $(obj).position().top + h+60;
		$compareW = $(window).width() / 2 + 355;
		if ($p_left > $compareW) {
			$('.room_status').css({
				left : $p_left - 258,
				top : $p_top
			});
			$('.room_status .off,.room_status .open').addClass('Right');
		} else {
			$('.room_status').css({
				left : $p_left,
				top : $p_top
			});
			$('.room_status .off,.room_status .open').removeClass('Right');
		}
		//设置悬浮窗口
		$('.room_open .cancelDescription').text(description);
		$('.room_open').stop(true, true).slideDown();
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
</script>
