<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="ft_layer room_status room_open">
	<div class="open"></div>
	<div class="title">
		<strong class="cl_green"><fmt:message key="ccm.RestrictionsManagement.RoomOpenStatus"/></strong>【<fmt:message key="ccm.RestrictionsManagement.DoubleClickToClose"/>】
	</div>
	<div class="info">
		<hr class="dashed">
		<span class="channel"></span> 
		<span class="roomType"></span> 
		<span class="ratePlan"></span> 
		<span class="date"></span>
	</div>
</div>

<div class="ft_layer room_status room_off">
	<div class="off"></div>
	<div class="title">
		<strong class="cl_red"><fmt:message key="ccm.RestrictionsManagement.RestrictionStatus"/></strong>【<fmt:message key="ccm.RestrictionsManagement.DoubleClickToOpen"/>】
	</div>
	<div class="info">
		<hr class="dashed">
		<span class="channel"></span> 
		<span class="roomType"></span> 
		<span class="ratePlan"></span> 
		<span class="date"></span>
	</div>
</div>