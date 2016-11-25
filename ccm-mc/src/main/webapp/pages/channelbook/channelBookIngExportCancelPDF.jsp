<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>

<style>
.title_channelbooking {
	color: #0066CC;
	font-weight: bold;
}

.tr_channelbook_td{
	text-align: center;
	width: 25%;
}
.tr_channelbook_td_title{
	background-color: #DCE4EF;
	text-align: center;
}

</style>
<div class="CCMmainConter w1200 " style="width: 960px;">
	<div class="title_channelbooking"><fmt:message key="ccm.WBEUI.ConfiramtionofCancellation"/></div>
	<table class="table" border="1"  bordercolor="#9263B7">
		<tbody>
			<tr class="tr_channelbook">
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.Status"/></span></td>
				<td style="border-top:0px;" class="tr_channelbook_td" colspan="3"><span class="STYLE5">${wbeOrderVO.staDesp}</span></td>
			</tr>
			<tr class="tr_channelbook">
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.ConfirmationNumber"/></span> </td>
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${wbeOrderVO.masterId }</span></td>
				<!-- 渠道订单号 -->
				<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key='ccm.Channel.ChannelReservationNumber'/></span> </td>
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${wbeOrderVO.crsno }</span></td>
				
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
				<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${ wbeOrderVO.ratePlanCode}</span></td>
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
	</table>
	<c:forEach items="${wbeOrderVO.wbeGuestVoList }" var="wbeGuestVo" varStatus="i">
		<table class="table" border="1"  bordercolor="#9263B7">
			<tbody>
				<tr class="tr_channelbook">
					<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;" colspan="4"><span class="STYLE5"><fmt:message key="ccm.WBEUI.GuestInformation"/>${i.index+1 }</span> </td>
				</tr>
				<tr class="tr_channelbook">
					<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.GuestName"/></span> </td>
					<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${wbeGuestVo.name2 }.${wbeGuestVo.name1 }&nbsp;${wbeGuestVo.name3 }</span></td>
					<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.MobilePhone"/></span></td>
					<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5">${wbeGuestVo.tel}</span></td>
				</tr>
				<tr class="tr_channelbook">
					<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"><fmt:message key="ccm.WBEUI.EstimatedTimeofArrival"/></span> </td>
					<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5"><fmt:formatDate value="${wbeGuestVo.arr}" type="both" pattern="HH:mm"/></span></td>
					<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;"><span class="STYLE5"></span></td>
					<td style="border-top:0px;" class="tr_channelbook_td"><span class="STYLE5"></span></td>
				</tr>
				<tr class="tr_channelbook">
					<td class="tr_channelbook_td_title tr_channelbook_td" style="border-top:0px;" ><span class="STYLE5"><fmt:message key="ccm.WBEUI.SpecialRequest"/></span> </td>
					<td style="border-top:0px;" class="tr_channelbook_td" colspan="3"> <span class="STYLE5">${wbeGuestVo.need}</span></td>
				</tr>
			</tbody>
		</table>
	</c:forEach> 
	
		
	
</div>
	<div align="center">
		<a href="javascript:submit();" class="btn_1 blue"><fmt:message key="ccm.WBEUI.DownloadConfriamtion"/></a>
	</div>

	<form action="" name="nextForm" id="nextForm" method="post">
		<input type="hidden" name="wbeOrderVO.sta" value="${wbeOrderVO.sta}">
		<input type="hidden" name="wbeOrderVO.staDesp" value="${wbeOrderVO.staDesp}">
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


<script type="text/javascript">
	
	
	function submit(){
		document.nextForm.action = "/channelBookIng_exportCancelPDF.do"
		document.nextForm.submit();
	}
</script>
