<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
ul.inq_wp li .i_title{
	width:68px;
	float:left;
	text-align:right;
	padding:5px 6px 4px 6px;
}
.w240{
	width: 140px;
}
</style>
<!-- 内容区域-->
<div class="CCMmainConter">
	<%@ include file="/common/messages.jsp"%>
	<div class="title_wp"><fmt:message key="ccm.ReservationsManagment.ReservationDetail"/></div>
	<s:form id="order" action="" method="post">
	<div class="nm_box">
		<div class="p_title"><fmt:message key="ccm.ReservationsManagment.ReservationInformation"/></div>
		<!-- 编辑订单 -->
		<a href="#edit" onclick="javascript:edit();" class="btn_2 black ccm-popup-click">
			<fmt:message key="common.button.edit"/>
		</a>
		<ul class="inq_wp frow">
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.EnglishSurname"/></span>
				</div>
				<div class="i_input">
					<input value="${order.name2}" class="formnormal fxt w240" readonly="readonly" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.EnglishName"/></span>
				</div>
				<div class="i_input">
					<input value="${order.name}" class="formnormal fxt w240" readonly="readonly" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.ChineseName"/></span>
				</div>
				<div class="i_input">
					<input value="${order.name4}" class="formnormal fxt w240" readonly="readonly" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.InventoryManagement.Property"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.hotelCode" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.CRSNo"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.masterId" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.Channel.ChannelReservationNumber"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.crsno" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.AltId"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.pmsId" cssClass="formnormal fxt w240"/>
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.InventoryManagement.Channels"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.channel" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.ArrivalDate"/></span>
				</div>
				<div class="i_input">
					<input value="<s:date name="order.arr" format="yyyy-MM-dd" />" class="formnormal fxt w240" readonly="readonly" />
				</div>
			</li>

			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="common.Adults"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.gstno-order.child" cssClass="formnormal fxt w40" readonly="true" />
				</div>
				<div class="i_title" style="width:34px;">
					<span class="text"><fmt:message key="ccm.Rates.Children"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.child" cssClass="formnormal fxt w40" readonly="true" />
				</div>
			</li>
			<!--  
			<li class="col3_1">
				<div class="i_title">
					<span class="text">Insert Source Type</span>
				</div>
				<div class="i_input">
					<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>-->
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.NoofRooms"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.numberOfUnits" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.DepartureDate"/></span>
				</div>
				<div class="i_input">
					<input value="<s:date name="order.dep" format="yyyy-MM-dd" />" class="formnormal fxt w240" readonly="readonly" />
				</div>
			</li>
			<!-- 
			<li class="col3_1">
				<div class="i_title">
					<span class="text">Origin</span>
				</div>
				<div class="i_input">
					<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li> -->
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.RoomType"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.type" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.ReservationType"/></span>
				</div>
				<div class="i_input">
					<input value="${order.payment}" class="formnormal fxt w240" readonly="readonly" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.Rates.Market"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.market" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.RestrictionsManagement.RateCode"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.ratePlanCode" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="common.Payment"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.cardCode" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.Rates.SourceCode"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.source" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.Status"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.restype" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.RateAmount"/></span>
				</div>
				<div class="i_input">
					<s:textfield id="firstPrice" cssClass="formnormal fxt" cssStyle="width:140px;" readonly="true" />
					&nbsp;<a href="#priceDetail" class="link mgR12 ccm-popup-click"><button type="button">..</button></a>${order.currencyCode}
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.RoomNo"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.roomno" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.DeductedAmount"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.deduct" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text" <s:if test="!(#request.packageDetail.isEmpty())">style="color: blue"</s:if>><fmt:message key="ccm.ReservationsManagment.Packages"/></span>
				</div>
				<div class="i_input">
					<a href="#packageDetail" class="link mgR12 ccm-popup-click"><button type="button">..</button></a>
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.TotalRateAmount"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.charge" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text">CREATE BY</span>
				</div>
				<div class="i_input">
					<s:textfield key="order.createBY" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
		</ul>
	</div>
	<div class="nm_box">
		<div class="p_title"><fmt:message key="ccm.ReservationsManagment.Comments"/></div>
		<ul class="inq_wp frow">
			<li style="padding-right:1%; float:left; margin-bottom:12px;width:65%;">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.ReservationComments"/></span>
				</div>
				<div class="i_input">
					<div style="width:500px;border:#d9d9d9 1px solid;padding:6px;">${order.ref}</div>
				</div>
			</li>
			<s:if test="order.cancelReasonCode!=null">
			<li style="padding-right:1%; float:left; margin-bottom:12px;width:65%;">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.CancellationReasonCode"/></span>
				</div>
				<div class="i_input">
					<div style="width:500px;border:#d9d9d9 1px solid;padding:6px;">${order.cancelReasonCode}</div>
				</div>
			</li>
			<li style="padding-right:1%; float:left; margin-bottom:12px;width:65%;">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.CancellationReasonDescription"/></span>
				</div>
				<div class="i_input">
					<div style="width:500px;border:#d9d9d9 1px solid;padding:6px;">${order.cancelRef}</div>
				</div>
			</li>
			</s:if>
		</ul>
	</div>
	<div class="frow">
		<div class="nm_box float-left" style="width: 440px; min-height: 360px;">
			<div class="p_title"><fmt:message key="ccm.ReservationsManagment.Profiles"/></div>
			<div class="nav_wp">
				<ul class="nav nav-tabs" id="nav1">
					<li class="active"><a href="javascript:void[0];" id="tab1"><fmt:message key="ccm.ReservationsManagment.Guest"/></a>
					</li>
					<li><a href="javascript:void[0];" id="tab2"><fmt:message key="common.TA"/></a>
					</li>
					<li><a href="javascript:void[0];" id="tab3"><fmt:message key="common.Company"/></a>
					</li>
					<!--
				<li><a href="javascript:void[0];" id="tab4">团队</a>
				</li>-->
				</ul>
			</div>
			<ul class="inq_wp list_input" id="guest">
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.GuestName"/></span>
					</div>
					<div class="i_input">
						<input value="${order.name2}${order.name}${order.name4}" class="formnormal fxt w240" readonly="readonly" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Address"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.addressLine" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Comm"/></span>
					</div>
					<div class="i_input">
						<input value='${order.mobile!=""?order.mobile:order.business!=""?order.business:order.home!=""?order.home:""}' class="formnormal fxt w240" readonly="readonly" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Email"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.email" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.PostalCode"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.postCode" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.City"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.cityName" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Country"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.countryCode" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.State"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.stateProv" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key='ccm.InvoiceTitle'/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.invoiceTitle" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key='ccm.order.IDNo'/></span>
					</div>
					<div class="i_input">
						<input class="formnormal fxt w240" readonly="true"  value="${order.ident }" id="ident"/>
					</div>
				</li>
			</ul>
			
			<ul class="inq_wp list_input" id="ta" style="display: none;">
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.GuestName"/></span>
					</div>
					<div class="i_input">
						<input value="${order.channel}" class="formnormal fxt w240" readonly="readonly" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Address"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Comm"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.PostalCode"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.City"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Country"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.State"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
			</ul>
			<ul class="inq_wp list_input" id="company" style="display: none;">
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.GuestName"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.qualifyingIdValue" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Address"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Comm"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.PostalCode"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.City"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Country"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.State"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
			</ul>
			<!--
		<ul class="inq_wp list_input" id="group" style="display: none;">
			<li>
				<div class="i_title">
					<span class="text"><fmt:message key="common.GuestName"/></span>
				</div>
				<div class="i_input">
					<input value="" class="formnormal fxt w240" readonly="readonly">
				</div>
			</li>
			<li>
				<div class="i_title">
					<span class="text"><fmt:message key="common.Address"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li>
				<div class="i_title">
					<span class="text"><fmt:message key="common.Comm"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li>
				<div class="i_title">
					<span class="text"><fmt:message key="common.PostalCode"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li>
				<div class="i_title">
					<span class="text"><fmt:message key="common.City"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li>
				<div class="i_title">
					<span class="text"><fmt:message key="common.Country"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li>
				<div class="i_title">
					<span class="text"><fmt:message key="common.State"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
		</ul>-->
		</div>
		<div class="nm_box float-left mgL24" style="width: 440px; min-height: 360px;">
			<div class="p_title"><fmt:message key="common.Guarantee"/></div>
			<div class="nav_wp">
				<ul class="nav nav-tabs" id="nav2">
					<li class="active"><a href="javascript:void[0];" id="tab5"><fmt:message key="common.CreditCard"/></a>
					</li>
					<!--  
				<li><a href="javascript:void[0];" id="tab6">定金规则</a>
				</li>
				<li><a href="javascript:void[0];" id="tab7">取消规则</a>
				</li>-->
				</ul>
			</div>
			<ul class="inq_wp list_input" id="credit">
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Payment"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.cardCode" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Number"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.cardNumber" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.ExpDate"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.expirationDate" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.Name"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.cardHolderName" cssClass="formnormal fxt w240" readonly="true" />
					</div>
				</li>

			</ul>
			<!--  
		<ul class="inq_wp list_input" id="deposit" style="display: none;">
			<li>
				<div class="i_title">
					<span class="text">说明</span>
				</div>
				<div class="i_input">
					<input value="${order.name2}${order.name}" class="formnormal fxt w240" readonly="readonly">
				</div>
			</li>
			<li>
				<div class="i_title">
					<span class="text">预订日期</span>
				</div>
				<div class="i_input">
					<s:textfield key="order.addressLine" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li>
				<div class="i_title">
					<span class="text">Due Amt.</span>
				</div>
				<div class="i_input">
					<s:textfield key="order.ref" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
		</ul>
		<ul class="inq_wp list_input" id="cancel" style="display: none;">
			<li>
				<div class="i_title">
					<span class="text">描述</span>
				</div>
				<div class="i_input">
					<input value="${order.name2}${order.name}" class="formnormal fxt w240" readonly="readonly">
				</div>
			</li>
			<li>
				<div class="i_title">
					<span class="text">取消日期</span>
				</div>
				<div class="i_input">
					<s:textfield key="order.addressLine" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
			<li>
				<div class="i_title">
					<span class="text">金额</span>
				</div>
				<div class="i_input">
					<s:textfield key="order.ref" cssClass="formnormal fxt w240" readonly="true" />
				</div>
			</li>
		</ul>-->
		</div>
	</div>
	<hr class="dashed">
	<!-- 
	<div class="i_input" style="text-align: center;">
		<button type="button" class="btn_1 green mgR12 f_save"><fmt:message key="common.button.save"/>	</button>
		<a class="btn_1 green" href="/channelBookIng_list.do"><fmt:message key="common.Return"/></a>
	</div>
	 -->
	</s:form>
	
	<!--房价明细-->
	<div id="priceDetail" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
		<div class="c_whitebg pdA12">
		<div class="mgB24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.date"/></span>
					</div>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.RateCode"/></span>
					</div>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.RateAmounte"/></span>
					</div>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.InventoryManagement.RoomTypes"/> </span>
					</div>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.PackagesAmounte"/></span>
					</div>
				</li>
				<s:iterator value="priceDetail">
				<s:date name="date" format="yyyy-MM-dd" var="dateDay"/>
				<s:date name="order.arr" format="yyyy-MM-dd" var="arrDay"/>
				<s:if test="#dateDay==#arrDay"><s:set value="setrate" name="firstPrice"/></s:if>
				<li>
					<div class="i_title">
						${dateDay}
					</div>
					<div class="i_title">
						${order.ratePlanCode}
					</div>
					<div class="i_title">
						${order.currencyCode} ${setrate}
					</div>
					<div class="i_title">
						${order.type}
					</div>
					<div class="i_title">
						${packages}
					</div>
				</li>
				</s:iterator>
			</ul>
		</div>
		<div class="b_crl" style="text-align: center;">
			<button type="button" class="btn_2 white popup-close" onclick="refreshC();"><fmt:message key="common.button.close"/></button>
		</div>
		</div>
	</div>
	<!--包价明细-->
	<div id="packageDetail" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
		<div class="c_whitebg pdA12">
		<div class="mgB24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.date"/></span>
					</div>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.Code"/></span>
					</div>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.RateAmounte"/></span>
					</div>
				</li>
				<s:iterator value="#request.packageDetail">
				<li>
					<div class="i_title">
						<s:date name="date" format="yyyy-MM-dd" />
					</div>
					<div class="i_title">
						${packageCode}
					</div>
					<div class="i_title">
						${order.currencyCode} ${amount}
					</div>
				</li>
				</s:iterator>
			</ul>
		</div>
		<div class="b_crl" style="text-align: center;">
			<button type="button" class="btn_2 white popup-close" onclick="refreshC();"><fmt:message key="common.button.close"/></button>
		</div>
		</div>
	</div>
</div>
<!-- 内容区域 end-->
<script type="text/javascript">
//编辑订单
function edit(){
	$('#edit').load('/order_edit.do');
}
<!--
	$('#firstPrice').val('<s:property value="#firstPrice"/>');
	var ident = '${order.ident}';
	if(ident.length>6){
		var identX = '';
		for(var i=0;i<ident.length-6;i++){
			identX=identX+"X";
		}
		$("#ident").val(identX+ident.substring(ident.length-6,ident.length));
	}
	
	
	$('#tab1').click(function() {
		$('#guest').show();
		$('#ta').hide();
		$('#company').hide();
		$('#group').hide();
		$('#nav1').children('li').removeClass('active');
		$(this).parent().addClass('active');
	})
	$('#tab2').click(function() {
		$('#ta').show();
		$('#guest').hide();
		$('#company').hide();
		$('#group').hide();
		$('#nav1').children('li').removeClass('active');
		$(this).parent().addClass('active');
	})
	$('#tab3').click(function() {
		$('#company').show();
		$('#ta').hide();
		$('#guest').hide();
		$('#group').hide();
		$('#nav1').children('li').removeClass('active');
		$(this).parent().addClass('active');
	})
	$('#tab4').click(function() {
		$('#group').show();
		$('#ta').hide();
		$('#guest').hide();
		$('#company').hide();
		$('#nav1').children('li').removeClass('active');
		$(this).parent().addClass('active');
	})
	$('#tab5').click(function() {
		$('#credit').show();
		$('#deposit').hide();
		$('#cancel').hide();
		$('#nav2').children('li').removeClass('active');
		$(this).parent().addClass('active');
	})
	$('#tab6').click(function() {
		$('#deposit').show();
		$('#credit').hide();
		$('#cancel').hide();
		$('#nav2').children('li').removeClass('active');
		$(this).parent().addClass('active');
	})
	$('#tab7').click(function() {
		$('#cancel').show();
		$('#credit').hide();
		$('#deposit').hide();
		$('#nav2').children('li').removeClass('active');
		$(this).parent().addClass('active');
	})
	
	//保存
	$('.f_save').click(function(){
		$.ajax({
			url : '/channelBookIng_save.do?order.masterId=${order.masterId}&order.pmsId=' + $('#order_order_pmsId').val(),
			beforeSend : function() {
				
			},
			cache : false,
			dataType : "text",
			type : 'POST',
			complete : function(x, t) {
				if (t == 'error') {
					alert('error<fmt:message key="common.ChangeFailed"/>');
				}
			},
			success : function(data) {
				if (data == 'true') {
					alert('<fmt:message key="common.ChangedSuccessfully"/>');
				} else {
					alert('<fmt:message key="common.ChangeFailed"/>');
				}
			}
		});
	});
//-->
</script>