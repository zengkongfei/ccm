<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
	.tdValue{width:280px;}
	.tdValue2{width:620px;}
</style>
<div class="pp_main">
    <div style="height:500px;overflow-y:scroll;">
    	<div>
    		<table>
    			<tr>
    				<td>Confirmation Number：</td>
    				<td class="tdValue">${order.masterId}</td>
    				<td>Status：</td>
    				<td class="tdValue">${order.sta}</td>
    			</tr>
    			<tr>
    				<td>Hotel：</td>
    				<td class="tdValue">${order.hotelName}</td>
    				<td>Date：</td>
    				<td class="tdValue"><s:date name="order.changed" format="yyyy-MM-dd HH:mm:ss" /></td>
    			</tr>
    			<tr>
    				<td>To/Company：</td>
    				<td class="tdValue">${order.name4}</td>
    				<td>Tel：</td>
    				<td class="tdValue">${order.telephone}</td>
    			</tr>
    			<tr>
    				<td>From：</td>
    				<td class="tdValue">Reservation Department</td>
    				<td>Fax：</td>
    				<td class="tdValue">${order.fax}</td>
    			</tr>
    			<tr>
    				<td>Channel：</td>
    				<td class="tdValue">${order.channelCode}</td>
    				<td>Hotelmail：</td>
    				<td class="tdValue">${order.email}</td>
    			</tr>
    		</table>
    	</div>
    	<div style="border:1px solid #ddd;">
    		<br/>
			Thank you for the reservation at ${order.hotelName} and we are pleased to confirm the following reservation(s).
			<br/><br/>
		</div>
		<div>
    		<table>
    			<tr>
    				<td>Guest Name：</td>
    				<td class="tdValue2">${order.name4}</td>
    			</tr>
    			<tr>
    				<td>Mobile：</td>
    				<td class="tdValue2">${order.mobile}</td>
    			</tr>
    			    			<tr>
    				<td>Email：</td>
    				<td class="tdValue2">${order.masterEmail}</td>
    			</tr>
    			    			<tr>
    				<td>Address：</td>
    				<td class="tdValue2">${order.addressLine}</td>
    			</tr>
    			<tr>
    				<td>Arrival Date：</td>
    				<td class="tdValue2"><s:date name="order.arr" format="yyyy-MM-dd" /></td>
    			</tr>
    			<tr>
    				<td>Departure Date：</td>
    				<td class="tdValue2"><s:date name="order.dep" format="yyyy-MM-dd" /></td>
    			</tr>
    			<tr>
    				<td>Room Type：</td>
    				<td class="tdValue2">${order.roomTypeName}</td>
    			</tr>
    			<tr>
    				<td>Products：</td>
    				<td class="tdValue2">${order.ratePlanDesc}</td>
    			</tr>
    			<tr>
    				<td>Guarantee Type：</td>
    				<td class="tdValue2">${order.payment}</td>
    			</tr>
    			<tr>
    				<td>Number of adults：</td>
    				<td class="tdValue2">${order.gstno}</td>
    			</tr>
    			<tr>
    				<td>Number Of Rooms：</td>
    				<td class="tdValue2">${order.numberOfUnits}</td>
    			</tr>
    			<tr>
    				<td>Daily Rate：</td>
    				<td class="tdValue2">
    					<s:iterator value="order.mrList">
							<span class="nowp">
								<s:date name="date" format="yyyy-MM-dd" />：${order.currencyCode}<s:property value="setrate" />
							</span>
							<span>&nbsp;</span>
						</s:iterator>
					</td>
    			</tr>
    			<tr>
    				<td>Dynamic Package：</td>
    				<td class="tdValue2">
    					<s:iterator value="order.mpList">
							<s:if test="isDynamic">
								<s:if test="#ii>1">;</s:if>
								<s:property value="packageName" />
								<s:property value="quantity" />:
								Total<s:property value="amount" />${order.currencyCode};
								<s:set value="#ii+1" name="ii">
								</s:set>
							</s:if>
						</s:iterator>
					</td>
    			</tr>
    			<tr>
    				<td>Package：</td>
    				<td class="tdValue2">
    					<s:set value="1" name="jj"></s:set>
						<s:iterator value="order.mpList">
						<s:if test="!isDynamic">
							<s:if test="#jj>1">;</s:if>
							${packageName}:Total<s:property value="amount"/>${order.currencyCode}
							<s:set value="#jj+1" name="jj"></s:set>
						</s:if>
						</s:iterator>
					</td>
    			</tr>
    			<tr>
    				<td>Total Price：</td>
    				<td class="tdValue2">
    					${order.currencyCode} ${order.charge}
					</td>
    			</tr>
    			<tr>
					<td>Remarks：</td>
					<td class="tdValue2">${order.srqs}</td>
				</tr>
				<tr>
					<td>Remark：</td>
					<td class="tdValue2">${fn:replace(order.ref, order.srqs, "")}</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				
				<tr>
					<td>—Check-in Time：</td>
					<td class="tdValue2">${order.checkInTimeDesc}</td>
				</tr>
				
				<tr>
					<td>—Check-out Time：</td>
					<td class="tdValue2">${order.checkOutTimeDesc}</td>
				</tr>
				<tr>
					<td>—Cancellation Policy：</td>
					<td class="tdValue2">${order.cancelPolicyDesc}</td>
				</tr>
    		</table>
    	</div>
    	<hr>
    	<div>
			<div align="center">
				<span>Street Address：${order.address}</span>
				| <span> Tel:${order.telephone}</span>
				| <br><span> Fax:${order.fax}</span>
				| <span> PostCode:${order.postCode}</span>
			</div>
			<div class="copyright" align="center">Powered by CHINAonline</div>
		</div>
	</div>
	<div class="b_crl" style="text-align: center;">
		<button type="button" class="btn_2 white popup-close">Close</button>
	</div>
</div>
<script src="<c:url value='/js/main.js'/>"></script>