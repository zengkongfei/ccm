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
</style>
<div class="CCMmainConter w1200 " style="width: 960px;">
	<div class="title_channelbooking"><fmt:message key="ccm.WBEUI.Cancellation"/></div>
<div class="row">
<div class="col-md-10 col-md-10">
	<table class="table" border="1" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
		<tbody>
			<tr class="tr_channelbook">
				<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.HotelCodeOrHotelName"/></span>
				</td>
				<td colspan="9">
					<select id="hotel" class="fxt w101 mgR12" >
						<option value=""><fmt:message key="common.select.plesesSelect"/>	</option>
						<c:forEach items="${hotelVOList }" var="hotelVO">
							<option value="${hotelVO.hotelId }" chainCode="${hotelVO.chainCode }" hotelCode="${hotelVO.hotelCode }"
								<c:if test="${wbeOrderVO.hotelId==hotelVO.hotelId }">selected="selected"</c:if>
							>${hotelVO.hotelName }-${hotelVO.hotelCode }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr class="tr_channelbook">
				<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.ConfirmationNumber"/>	</span>&nbsp;<span style="color: red">*</span></td>
				<td colspan="5"><input id="masterId" placeholder="<fmt:message key="ccm.WBEUI.ConfirmationNumber"/>" value="${wbeOrderVO.masterId }" class="fxt w204"></td>
			</tr>
			<tr class="tr_channelbook">
				<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.WBEUI.GuestLastName"/></span></td>
				<td colspan="5"><input id="name1" placeholder="<fmt:message key="ccm.WBEUI.GuestLastName"/>" value="${wbeOrderVO.wbeGuestVO.name2 }" class="fxt w204"></td>
			</tr>
			<tr class="tr_channelbook">
				<td class="td_title_channelbook"><span class="STYLE5"><fmt:message key="ccm.Channel.ChannelReservationNumber"/></td>
				<td colspan="5"><input id="crsno" placeholder="<fmt:message key="ccm.Channel.ChannelReservationNumber"/>" value="${wbeOrderVO.crsno }" class="fxt w204"></td>
			</tr>
		</tbody>
	</table>
	</div>
		<div class="col-md-1 col-lg-1 " style="margin-top: 90px;">
			<a href="javascript:search();" class="btn_1 blue"><fmt:message key="ccm.WBEUI.Search"/></a>
		</div>
	</div>
	
	<table class="table" border="1" id="body_table"  bordercolor="#9263B7">
		<c:if test="${!empty wbeOrderVO.masterId }">
		<tbody>
			<tr class="tr_channelbook">
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.Status"/></span></td>
				<td style="border-top:0px;" class="tr_channelbook_td" colspan="3"><span class="STYLE5">${wbeOrderVO.sta}</span></td>
			</tr>
			<tr class="tr_channelbook">
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.ConfirmationNumber"/></span> </td>
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${wbeOrderVO.masterId }</span></td>
				<!-- 渠道订单号 -->
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.Channel.ChannelReservationNumber"/></span></td>
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${wbeOrderVO.crsno}</span></td>
			</tr>
			<tr class="tr_channelbook">
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.ArrivalDate"/></span> </td>
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5"><fmt:formatDate value="${wbeOrderVO.arr}" type="both" pattern="yyyy-MM-dd "/></span></td>
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.DepartureDate"/></span></td>
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5"><fmt:formatDate value="${wbeOrderVO.dep}" type="both" pattern="yyyy-MM-dd "/></span></td>
			</tr>
			<tr class="tr_channelbook">
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.RoomType"/></span> </td>
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${wbeOrderVO.roomTypeCode }</span></td>
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.RateCode"/></span></td>
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${wbeOrderVO.ratePlanCode }</span></td>
			</tr>
			<tr class="tr_channelbook">
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.RoomsandAudlts"/></span> </td>
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${wbeOrderVO.number }/${wbeOrderVO.numberOfUnits }</span></td>
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.TotalAmount"/></span></td>
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${wbeOrderVO.charge }${wbeOrderVO.currencyCode }</span></td>
			</tr>
			<c:forEach items="${wbeOrderVO.dailRateList }" var="dailRate" varStatus="i">
				<c:if test="${i.index==0 }">
					<tr class="tr_channelbook">
						<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;vertical-align:middle;" rowspan="${fn:length(wbeOrderVO.dailRateList)+1}"><span class="STYLE5"><fmt:message key="ccm.WBEUI.Rateperroompernight"/></span> </td>
						<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5"><fmt:formatDate value="${dailRate.priceDate}" type="both" pattern="yyyy-MM-dd "/></span></td>
						<td class=" tr_channelbook_td" style="border-top:0px;" colspan="2"><span class="STYLE5">${dailRate.price}${wbeOrderVO.currencyCode }</span></td>
					</tr>
				</c:if>
				<c:if test="${i.index!=0 }">
					<tr class="tr_channelbook">
						<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5"><fmt:formatDate value="${dailRate.priceDate}" type="both" pattern="yyyy-MM-dd "/></span></td>
						<td class=" tr_channelbook_td" style="border-top:0px;" colspan="2"><span class="STYLE5">${dailRate.price}${wbeOrderVO.currencyCode }</span></td>
					</tr>
				</c:if>
			</c:forEach>
			<tr class="tr_channelbook">
				<td class=" tr_channelbook_td" style="border-top:0px;" colspan="3"><span class="STYLE5"><fmt:message key="ccm.WBEUI.RatesPernight"/>： ${wbeOrderVO.charge }${wbeOrderVO.currencyCode }</span></td>
			</tr>
		</tbody>
		</c:if>
	</table>
		<c:if test="${!empty wbeOrderVO.masterId }">
			<div align="center" id="submitDiv">
				<a href="javascript:submit();" class="btn_1 blue" id="submit"><fmt:message key="ccm.WBEUI.ConfirmtoCancel"/></a>
			</div>
		</c:if>
</div>
			<form action="" name="nextForm" id="nextForm" method="post">
		<input type="hidden" name="wbeOrderVO.sta" value="${wbeOrderVO.sta}">
		<input type="hidden" name="wbeOrderVO.masterId" value="${wbeOrderVO.masterId}">
		<input type="hidden" name="wbeOrderVO.hotelId" value="${wbeOrderVO.hotelId }">
		<input type="hidden" name="wbeOrderVO.hotelCode" value="${wbeOrderVO.hotelCode }">
		<input type="hidden" name="wbeOrderVO.chainCode" value="${wbeOrderVO.chainCode }">
		<input type="hidden" name="wbeOrderVO.channelCode" value="${wbeOrderVO.channelCode }">
		<input type="hidden" name="wbeOrderVO.channelId" value="${wbeOrderVO.channelId }">
		<input type="hidden" name="wbeOrderVO.numberOfUnits" value="${wbeOrderVO.numberOfUnits}">
		<input type="hidden" name="wbeOrderVO.arr" value="<fmt:formatDate value="${wbeOrderVO.arr}" type="both" pattern="yyyy-MM-dd "/>">
		<input type="hidden" name="wbeOrderVO.dep" value="<fmt:formatDate value="${wbeOrderVO.dep}" type="both" pattern="yyyy-MM-dd "/>">
		<input type="hidden" name="wbeOrderVO.number" value="${wbeOrderVO.number }">
		<input type="hidden" name="wbeOrderVO.roomTypeCode" value="${wbeOrderVO.roomTypeCode }">
		<input type="hidden" name="wbeOrderVO.roomTypeName" value="${wbeOrderVO.roomTypeName }">
		<input type="hidden" name="wbeOrderVO.ratePlanCode" value="${wbeOrderVO.ratePlanCode }">
		<input type="hidden" name="wbeOrderVO.ratePlanId" value="${wbeOrderVO.ratePlanId }">
		<input type="hidden" name="wbeOrderVO.accessCode" value="${wbeOrderVO.accessCode }">
		<input type="hidden" name="wbeOrderVO.accessCodeType" value="${wbeOrderVO.accessCodeType }">
		<input type="hidden" name="wbeOrderVO.inventType" value="${wbeOrderVO.inventType }">
		<input type="hidden" name="wbeOrderVO.charge" value="${wbeOrderVO.charge }">
		<input type="hidden" name="wbeOrderVO.oneRoomCharge" value="${wbeOrderVO.oneRoomCharge }">
		
		<input type="hidden" name="wbeOrderVO.payment" value="${wbeOrderVO.payment }">
		<input type="hidden" name="wbeOrderVO.cardCode" value="${wbeOrderVO.cardCode }">
		<input type="hidden" name="wbeOrderVO.cardHolderName" value="${wbeOrderVO.cardHolderName }">
		<input type="hidden" name="wbeOrderVO.cardNumber" value="${wbeOrderVO.cardNumber }">
		<input type="hidden" name="wbeOrderVO.expirationDate" value="${wbeOrderVO.expirationDate}">
		<input type="hidden" name="wbeOrderVO.currencyCode" value="${wbeOrderVO.currencyCode }">
		
		<input type="hidden" name="wbeOrderVO.crsno" value="${wbeOrderVO.crsno }">
		
		<div id="guestInput">
			<c:forEach items="${wbeOrderVO.wbeGuestVoList }" var="wbeGuestVo" varStatus="i">
				<input type="hidden" name="wbeOrderVO.wbeGuestVoList[${i.index }].name1" value="${wbeGuestVo.name1 }">
				<input type="hidden" name="wbeOrderVO.wbeGuestVoList[${i.index }].name2" value="${wbeGuestVo.name2 }">
				<input type="hidden" name="wbeOrderVO.wbeGuestVoList[${i.index }].name3" value="${wbeGuestVo.name3 }">
				<input type="hidden" name="wbeOrderVO.wbeGuestVoList[${i.index }].tel" value="${wbeGuestVo.tel }">
				<input type="hidden" name="wbeOrderVO.wbeGuestVoList[${i.index }].need" value="${wbeGuestVo.need }">
				<input type="hidden" name="wbeOrderVO.wbeGuestVoList[${i.index }].arr" value="<fmt:formatDate value="${wbeGuestVo.arr}" type="both" pattern="HH:mm"/>">
			</c:forEach>
		</div>
		<div id="dailRate">
			<c:forEach items="${wbeOrderVO.dailRateList }" var="dailRate" varStatus="i">
				<input type="hidden" name="wbeOrderVO.dailRateList[${i.index }].priceDate" value="<fmt:formatDate value="${dailRate.priceDate}" type="both" pattern="yyyy-MM-dd "/>">
				<input type="hidden" name="wbeOrderVO.dailRateList[${i.index }].price" value="${dailRate.price }">
			</c:forEach>
		</div>
		
	</form>
	
	<form action="" name="searchForm" id="searchForm" method="post">
		<input type="hidden" name="hotelId" value="${wbeOrderVO.hotelId }">
		<input type="hidden" name="masterId" value="${wbeOrderVO.masterId }">
		<input type="hidden" name="name1" value="${wbeOrderVO.wbeGuestVO.name1 }">
		
		<input type="hidden" name="crsno" value="${wbeOrderVO.crsno }">
		
	</form>
	


<script type="text/javascript">
$(document).ready(function() {
	var wbeOrderVO ='${wbeOrderVO}';
	var url = window.location.href;
	var index = url.indexOf("channelBookIng_cancelSearch.do");
	if(index>=0){
		if(wbeOrderVO==null||wbeOrderVO==''){
			$("#body_table").html('<tr><td class="td_title_channelbook2"><span class="STYLE6"><fmt:message key="common.Norecordwasfound"/></span></td>');
		}
	}
	
	
	var restype ='${wbeOrderVO.restype}';
	var arr = '<fmt:formatDate value="${wbeOrderVO.arr}" type="both" pattern="yyyy-MM-dd "/>';
	var arr=arr.split("-");
	var starttime=new Date(arr[0],arr[1],arr[2]).getTime(); 
	var now = new Date().getTime();
	if(restype=='RESERVED' && starttime>now){
		$("#submitDiv").show();
	}else{
		$("#submitDiv").hide();
	}
})


	function submit(){
		document.nextForm.action = "/channelBookIng_cancel.do"
		document.nextForm.submit();
	}
	function search(){
		var hotelId = $("#hotel").val(); 
		var name1 = $("#name1").val();
		var masterId = $("#masterId").val().trim();
		var crsno = $("#crsno").val().trim();
		
		if(masterId==''){
			alert('<fmt:message key="ccm.WBEUI.message.006"/>');
			return;
		}
		
		$("#searchForm").find("input[name='hotelId']").val(hotelId);
		$("#searchForm").find("input[name='masterId']").val(masterId);
		$("#searchForm").find("input[name='name1']").val(name1);
		$("#searchForm").find("input[name='crsno']").val(crsno);
		
		document.searchForm.action = "/channelBookIng_cancelSearch.do"
		document.searchForm.submit();
		
	}
</script>
