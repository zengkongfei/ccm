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
<div class="CCMmainConter" style="width: :960px;">
	<%@ include file="/common/messages.jsp"%>
	<div class="title_wp"><fmt:message key="ccm.Reservations.ModifyRSVN"/></div>
	<s:form id="order" action="" method="post">
		<input  name='orderNo'  type="hidden" value="${order.masterId }"/>
		<input  name='order.masterId'  type="hidden" value="${order.masterId }"/>
		<input id="hotelId" name='order.hotelId' value="${ order.hotelId}" type="hidden"/>
		<input name='order.chainCode' value="CCM" type="hidden"/>
		<input name='order.channelId' value="${ order.channelId}" type="hidden"/>
		<input id="ratePlanId" name='order.ratePlanId' value="${ order.ratePlanId}" type="hidden"/>
	<div class="nm_box">
		<div class="p_title"><fmt:message key="ccm.ReservationsManagment.ReservationInformation"/></div>
		<ul class="inq_wp frow">
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.EnglishSurname"/></span>
				</div>
				<div class="i_input">
					<input value="${order.name2}" id="name2" name="order.name2" oninput="changeUsername();" class="formnormal fxt w240 required" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.EnglishName"/></span>
				</div>
				<div class="i_input">
					<input value="${order.name}" id="name" name="order.name" oninput="changeUsername();" class="formnormal fxt w240 required" />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.ChineseName"/></span>
				</div>
				<div class="i_input">
					<input value="${order.name4}" name="order.name4" id="name4" oninput="changeUsername();" class="formnormal fxt w240 required"  />
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
					<%-- <s:textfield key="order.masterId" cssClass="formnormal fxt w240" readonly="true" /> --%>
					<input value="${order.masterId }" class="formnormal fxt w240" readonly="true"  />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.AltId"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.pmsId" cssClass="formnormal fxt w240" readonly="true"/>
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
					<input value="<s:date name="order.arr" format="yyyy-MM-dd" />" name="order.arr" id="arr" class="formnormal fxt w240" readonly="readonly"/>
				</div>
			</li>

			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="common.Adults"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.gstno-order.child"  name="order.gstno" cssClass="formnormal fxt w70" readonly="true" />
				</div>
				<div class="i_title" style="width:50px;">
					<span class="text"><fmt:message key="ccm.Rates.Children"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.child" cssClass="formnormal fxt w60" style="width: 36px;" readonly="true"/>
				</div>
			</li>
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
					<input value="<s:date name="order.dep" format="yyyy-MM-dd" />" name="order.dep" id="dep" class="formnormal fxt w240" readonly="readonly"  />
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.RoomType"/></span>
				</div>
				<div class="i_input">
					<s:textfield key="order.type" cssClass="formnormal fxt w240" readonly="true"/>
					<%-- <select name="order.type" id="roomType"  class="fxt w120 required">
						<c:forEach items="${ratePlanVOList }" var="ratePlanVO">
							<c:forEach items="${priceCalcList }" var="priceCalc">
								<c:if test="${ratePlanVO.rp.ratePlanCode==priceCalc.ratePlanCode }">
										<option value="${priceCalc.roomTypeCode}"
											<c:if test="${priceCalc.roomTypeCode==order.type}">selected=selected</c:if>
											>
											${priceCalc.roomTypeCode}
										</option>
									</c:if>
							</c:forEach>
						</c:forEach> --%>
						
						<%-- <c:if test="${roomTypeList!=null }">
							<c:forEach items="${roomTypeList }" var="roomType">
								<option value="${roomType.roomTypeCode}"
									<c:if test="${roomType.roomTypeCode==order.type}">selected=selected</c:if>
								>${roomType.roomTypeCode }</option>
							</c:forEach>
						</c:if> --%>
					</select>
				</div>
			</li>
			<li class="col3_1">
				<div class="i_title">
					<span class="text"><fmt:message key="ccm.ReservationsManagment.ReservationType"/></span>
				</div>
				<div class="i_input">
					<%-- <input value="${order.payment}" class="formnormal fxt w240" /> --%>
					<select class="formnormal fxt w240 required" style="width: 140px;"  id="guarrntee" name="order.payment" onchange="guaranteeChange()" >
						<c:if test="${hotelGuaranteeVOList!=null }">
							<c:forEach items="${hotelGuaranteeVOList }" var="hotelGuarantee">
								<option value="${hotelGuarantee.policyName }"
									<c:if test="${hotelGuarantee.policyName==order.payment}">selected=selected</c:if>
								>${hotelGuarantee.policyName }</option>
							</c:forEach>
						</c:if>
			        </select>
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
					<s:textfield key="order.ratePlanCode" cssClass="formnormal fxt w240" readonly="true"/>
					<%-- <select class="formnormal fxt w240 required" style="width: 140px;"  id="ratePlan" name="order.ratePlanCode" onchange="getRateByRatePlanId()">
						<c:if test="${ratePlanList!=null }">
							<c:forEach items="${ratePlanList }" var="ratePlan">
								<option value="${ratePlan.ratePlanCode }" ratePlanId="${ratePlan.ratePlanId }"
									<c:if test="${ratePlan.ratePlanCode==order.ratePlanCode}">selected=selected</c:if>
								>${ratePlan.ratePlanCode }</option>
							</c:forEach>
						</c:if>
				</select> --%>
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
			<%-- <li class="col3_1">
				<div class="i_title">
					<span class="text">AccessCode</span>
				</div>
				<div class="i_input">
					<select name="order.QualifyingIdValue" class="formnormal fxt w240" >
						<c:forEach items="${accessCodelistMap }" var="accessCode" >
							<option value="${accessCode.accessCode }" 
								<c:if test="${accessCode.accessCode==order.qualifyingIdValue }">
								selected=selected
								</c:if>
							>${accessCode.accessCode }</option>
						</c:forEach>
					</select>
				</div>
			</li> --%>
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
					<%-- <div style="width:500px;height:30px; border:#d9d9d9 1px solid;padding:6px;">${order.ref}</div> --%>
					<textarea name="order.ref" style="width:500px;height:30px; padding:0px;">${order.ref}</textarea>
				</div>
			</li>
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
						<input value="${order.name2}${order.name}${order.name4}" id="guestName" class="formnormal fxt w240" readonly="readonly" />
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
						<input value='${order.mobile!=""?order.mobile:order.business!=""?order.business:order.home!=""?order.home:""}' class="formnormal fxt w240" readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Email"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.email" cssClass="formnormal fxt w240"  readonly="true" />
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
						<s:textfield key="order.cityName" cssClass="formnormal fxt w240"  readonly="true" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Country"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.countryCode" cssClass="formnormal fxt w240"  readonly="true" />
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
			
		</div>
		<div class="nm_box float-left mgL24" style="width: 440px; min-height: 360px; display: none;" id="guaranteeChangehtml">
			<div class="p_title"><fmt:message key="common.Guarantee"/></div>
			<div class="nav_wp">
				<ul class="nav nav-tabs" id="nav2">
					<li class="active"><a href="javascript:void[0];" id="tab5"><fmt:message key="common.CreditCard"/></a>
					</li>
				</ul>
			</div>
			<ul class="inq_wp list_input" id="credit">
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Reservations.CreditCardType"/></span>
					</div>
					<div class="i_input">
						<select name="order.cardCode" class="formnormal fxt w240" >
							<option value="VA" <c:if test="${order.cardCode=='VA'}">selected=selected</c:if> >VISA</option>
							<option value="MA" <c:if test="${order.cardCode=='MA'}">selected=selected</c:if> >Master Card</option>
							<option value="JC" <c:if test="${order.cardCode=='JC'}">selected=selected</c:if> >JCB</option>
							<option value="AE" <c:if test="${order.cardCode=='VA'}">selected=selected</c:if> >American Express</option>
							<option value="DR" <c:if test="${order.cardCode=='DR'}">selected=selected</c:if> >Diners</option>
						</select>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.Number"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.cardNumber" cssClass="formnormal fxt w240"  />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="common.ExpDate"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.expirationDate" cssClass="formnormal fxt w240" />
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.Name"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="order.cardHolderName" cssClass="formnormal fxt w240" />
					</div>
				</li>
			</ul>
			
		</div>
	</div>
	<hr class="dashed">
	<div class="i_input" style="text-align: center;">
		<button type="button" class="btn_1 green mgR12 f_save"><fmt:message key="common.button.save"/>	</button>
		<a class="btn_1 green" href="/order_list.do"><fmt:message key="common.Return"/></a>
	</div>
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
						 ${setrate}${order.currencyCode}
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
			<button type="button" class="btn_2 white popup-close" ><fmt:message key="common.button.close"/></button>
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
						 ${amount} ${order.currencyCode}
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
/* $(document).ready(function() {
	
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
			               '<fmt:message key="calendar.week.saturday"/>' 
			              ],
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
			               '<fmt:message key="calendar.month.december"/>' 
			              ],
			monthNamesShort:[
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
							'<fmt:message key="calendar.month.december"/>' 
			              ]
		}
		$("#arr").datepicker($.extend(dpconfig, {
			defaultDate : "+0w",
			changeMonth : true,
			numberOfMonths : 1,
			minDate:new Date(),
			onSelect : function(selectedDate, obj) {
				$(this).val(selectedDate);
				getDetai();
				$("#dep").datepicker("option", {
					"minDate" : selectedDate
				});
			}
		}));
		
		$("#dep").datepicker($.extend(dpconfig,{
			defaultDate : "+0w",
			changeMonth : true,
			numberOfMonths : 1,
			dateFormat : 'yy-mm-dd',
			onSelect : function(selectedDate, obj) {
				$(this).val(selectedDate);
				getDetai();
				$("#arr").datepicker("option", {
					"maxDate" : selectedDate
				});
			}
		}));
		
	}); */
	
/* function getRateByRatePlanId() {
	var ratePlanId = $("#ratePlan option:selected").attr("ratePlanId");
	$("#ratePlanId").val(ratePlanId);
	$.ajax({
		type : "post",
		url : "order_getRateByRatePlanId.do",
		data : {
			"ratePlanId" : ratePlanId
		},
		cache : false,
		dataType : 'json',
		beforeSend : function() {
		},
		success : function(data) {
			var guarrnteeList = eval(data.guarrnteeList);
			var html = '<option value=""><fmt:message key="common.select.plesesSelect"/></option>';

			var html2 = html;
			for ( var i = 0; i < guarrnteeList.length; i++) {
				html2 = html2
						+ '<option value="'+guarrnteeList[i].policyName+'">'
						+ guarrnteeList[i].policyName
						+ '</option>'
			}
			$("#guarrntee").html(html2);
		}
	});
} */
	
	
$(document).ready(function() {
	 var payment = '${order.payment}';
	 if(payment=='CCGTD'){
		 $("#guaranteeChangehtml").show();
		 $("#guaranteeChangehtml").find("input").addClass("required");
	 }
	var g = '${ratePlanVO.guarrnteeList[0].policyName}';
	 if((payment==null||payment=='')&&g=='CCGTD'){
		 $("#guaranteeChangehtml").show();
		 $("#guaranteeChangehtml").find("input").addClass("required");
	 }
})
	
	function guaranteeChange(obj){
		var v = $("#guarrntee option:selected").text();
		if(v=="CCGTD"){
			$("#guaranteeChangehtml").show();
			$("#guaranteeChangehtml").find("input").addClass("required");
		}else{
			$("#guaranteeChangehtml").hide();
			$("#guaranteeChangehtml").find("input").removeClass("required");
			 $("#guaranteeChangehtml").find("input").addClass("error");
		}
	}
function changeUsername(){
	var name = $("#name").val();
	var name2 = $("#name2").val();
	var name4 = $("#name4").val();
	$("#guestName").val(name2+name+name4);
} 
<!--
	$('#firstPrice').val('<s:property value="#firstPrice"/>');
	
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
		if($("#guarrntee").val()!='CCGTD'){
			 $("#guaranteeChangehtml").find("input").val("");
		}
		if($('#order').valid()){
			$.ajax({
				type : "post",
				url : "order_editOrder.do",
				data : $("#order").serialize(),
				cache : false,
				dataType : 'text',
				beforeSend : function() {
				},
				success : function(data) {
					if(data!=''||data!=null){
						alert(data);
					}else{
						alert("error");
					}
				}
			});
		}
	});
	
	/* function getDetai(){
		var data = {"order.chainCode":'${order.chainCode}',"order.hotelCode":'${order.hotelCode}',"order.gstno":'${order.gstno}',"order.channel":'${order.channel}',
					"order.hotelId":'${order.hotelId}',"order.channelId":'${order.channelId}',"order.arr":$("#arr").val(),"order.dep":$("#dep").val()
		};
		if($("#arr").val()>=$("#dep").val()){
			return;
		}
		$.ajax({
			type : "post",
			url : "order_changeOrderDetail.do",
			data : data,
			cache : false,
			dataType : 'json',
			beforeSend : function() {
			},
			success : function(data) {
				priceCalcJsonArr = data.priceCalcJsonArr;
				ratePlanVOJsonArr = data.ratePlanVOJsonArr;
				setRatePlanHtml();
			},
			error:function(){ 
		   	 	alert("error"); 
			}
		})
	}
	var priceCalcJsonArr = '';
	var ratePlanVOJsonArr = '';
	
	//房型html
	function setRoomTypeHtml(){
		
	}
	//房价html
	function setRatePlanHtml(){
		console.log(ratePlanVOJsonArr);
	} */
	
	
	
	
//-->
</script>