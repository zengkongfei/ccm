<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div style="overflow-y:scroll;">
	<display:table name="masterChangesLogList" id="order" class="ccm_table1" requestURI="">
		<display:column property="user" sortable="true" titleKey="ccm.UserActivityLog.UserName" headerClass="sorted"/>
		<display:column property="createdTime" sortable="true" titleKey="ccm.UserActivityLog.ActionTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted"/>
		<display:column property="pmsId" sortable="true" titleKey="ccm.ReservationsManagment.AltId" headerClass="sorted"/>
		<display:column sortable="true"  titleKey="ccm.ReservationsManagment.GuestName" headerClass="sorted">
			${order.lastName}${order.firstName}${order.chineseName}
		</display:column>
		<display:column property="arrivalDate" sortable="true" titleKey="ccm.ReservationsManagment.ArrivalDate" format="{0,date,yyyy-MM-dd}" headerClass="sorted"/>
		<display:column property="departureDate" sortable="true" titleKey="ccm.ReservationsManagment.DepartureDate" format="{0,date,yyyy-MM-dd}" headerClass="sorted"/>
		<display:column property="masterCreateTime" sortable="true" titleKey="ccm.ReservationsManagment.ReservationCreatedDate" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted"/>
		<display:column property="masterCancelTime" sortable="true" titleKey="ccm.report.CacellationDate" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted"/>
		<display:column property="masterModifyTime" sortable="true" titleKey="ccm.ReservationsManagment.ReservationModifyDate" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted"/>
		<display:column property="restype" sortable="true" titleKey="ccm.ReservationsManagment.Status" headerClass="sorted"/>
		<display:column property="rateCode" sortable="true" titleKey="ccm.RestrictionsManagement.RateCode" headerClass="sorted"/>
		<display:column property="roomCode" sortable="true" titleKey="ccm.ReservationsManagment.RoomType" headerClass="sorted"/>
		<display:column property="source" sortable="true" titleKey="ccm.Rates.SourceCode" headerClass="sorted"/>
		<display:column property="ext" sortable="true" titleKey="ccm.ReservationsManagment.RoomNo" headerClass="sorted"/>
	</display:table>	
</div>
<div style="text-align: center;">
	<button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
</div>
<script src="<c:url value='/js/main.js'/>"></script>