<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<fmt:message key="ccm.OTALog.OTALog" />
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<%@ include file="/common/messages.jsp"%>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Channel.ChannelCode"/></span>
					</div>
					<div class="i_input">
						<select key="soc.channelCode" class="fxt w120" name="soc.channelCode" id="schannelCode">
							<c:forEach var="m" items="${channel}">
								<option value="${m }" <c:if test="${m==soc.channelCode }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">* </span><fmt:message key="ccm.BasicConfiguration.ChainCode" /></span>
					</div>
					<div class="i_input">
						<select key="soc.chainCode" class="fxt w120" name="soc.chainCode" id="sChainCode">
							<c:forEach var="m" items="${chainCode}">
								<option value="${m }" <c:if test="${m==soc.chainCode }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						</select>
					</div>
				</li> 
		
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">* </span><fmt:message key="ccm.ReservationMonitorReport.PropertyCode" /></span>
					</div>
					<div class="i_input">
				    	<!-- 
						<s:select  name="soc.hotelCode" id="hotelCode" key="soc.hotelCode" cssClass="w120" list="hotelList" listValue="hotelCode" headerKey="" headerValue="">
						</s:select>
					    -->
						<select key="soc.hotelCode" class="fxt w120" name="soc.hotelCode" id="hotelCode">
							<c:forEach var="m" items="${hotelCode}">
								<option value="${m }" 
								<c:if test="${m==soc.hotelCode }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						</select>
					</div>
				</li>	
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.CreatedTime"/></span>
					</div>
					<div class="i_input">
						<input 
						id="createdTime" 
						readonly="readonly"
						name="soc.createdTime" 
						class="fxt w120 calendar" 
						value="<fmt:formatDate value='${soc.createdTime }' 
						pattern='yyyy-MM-dd HH:mm'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.OTALog.LastModifyTime"/></span>
					</div>
					<div class="i_input">
						<input 
						id="lastModifyTime" 
						readonly="readonly"
						name="soc.lastModifyTime" 
						class="fxt w120 calendar" 
						value="<fmt:formatDate value='${soc.lastModifyTime }' 
						pattern='yyyy-MM-dd HH:mm'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.OTALog.Action"/></span>
					</div>
					<div class="i_input">
						<select class="fxt w120" name="soc.action">
							<c:forEach var="m" items="${action}">
								<option value="${m }" <c:if test="${m==soc.action }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						</select>	
					</div>
				</li>   	
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.OTALog.TransformStatus"/></span>
					</div>
					<div class="i_input">
						<select class="fxt w120" name="soc.transformStatus">
							<c:forEach var="m" items="${transformStatus}">
								<option value="${m }" <c:if test="${m==soc.transformStatus }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						</select>	
					</div>
				</li>	
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.OTALog.Status"/></span>
					</div>
					<div class="i_input">
						<select class="fxt w120" name="soc.status">
							<c:forEach var="m" items="${status}">
								<option value="${m }" <c:if test="${m==soc.status }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						</select>
						
					</div>
				</li> 	
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.OTALog.OxiTrxId" /></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.oxiTrxId" cssClass="fxt w120"></s:textfield>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.OTALog.MessageType"/></span>
					</div>
					<div class="i_input">
						<select class="fxt w120" name="soc.messageType">
							<c:forEach var="m" items="${messageType}">
								<option value="${m }" <c:if test="${m==soc.messageType }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						</select>
						
					</div>
				</li> 	
				
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.otalog.RoomTypeCode" /></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.roomTypeCode" cssClass="fxt w120"></s:textfield>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.otalog.RatePlanCode" /></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.ratePlanCode" cssClass="fxt w120"></s:textfield>
					</div>
				</li>

				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.otalog.RoomTypeAllocationStart"/></span>
					</div>
					<div class="i_input">
						<input 
						id="startDate" 
						name="soc.startDate" 
						class="fxt w120 calendar datetip001" 
						value="<fmt:formatDate value='${soc.startDate }' 
						pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12" onclick="javascript:searchOrder();"><fmt:message key="common.button.searchSelect"/>	</button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:exportOrder();"><fmt:message key="common.button.File"/></button>
			</div>
		</div>
	</div>
	</form> 
	<div id="show" class="ccm-popup width900 zoom-anim-dialog mfp-hide" style="height: 550px;"></div>
<!-- 查询结果 -->
<div class="c_whitebg">
	<div class="bt_wp">	
	 <form id="displayForm" name="displayForm" method="post" action="">		
	<display:table name="sendOTALogResult.resultList" form="displayForm" id="order" class="ccm_table1" requestURI="" pagesize="20" size="sendOTALogResult.totalCount" partialList="true">
		<display:column property="channelCode" sortable="true" titleKey="ccm.Channel.ChannelCode" headerClass="sorted" />
		<display:column property="chainCode" sortable="true" titleKey="ccm.BasicConfiguration.ChainCode" headerClass="sorted" />
		<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
		<display:column property="createdTime" sortable="true"  titleKey="ccm.ReservationMonitorReport.CreatedTime" headerClass="sorted" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		<display:column property="lastModifyTime" sortable="true"  titleKey="ccm.OTALog.LastModifyTime" headerClass="sorted" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		<display:column property="action" sortable="true" titleKey="ccm.OTALog.Action" headerClass="sorted" />
		<display:column property="transformStatus" sortable="true" titleKey="ccm.OTALog.TransformStatus" headerClass="sorted" />
		<display:column property="status" sortable="true" titleKey="ccm.OTALog.Status" headerClass="sorted" />
		<display:column property="oxiTrxId" sortable="true" titleKey="ccm.OTALog.OxiTrxId" headerClass="sorted" />
		<display:column property="messageType" sortable="true" titleKey="ccm.OTALog.MessageType" headerClass="sorted" />
		<display:column property="comments" sortable="true" titleKey="ccm.OTALog.Comments" headerClass="sorted" />
		
		<display:column property="ratePlanCode" sortable="true" titleKey="ccm.otalog.RatePlanCode" headerClass="sorted" />
		<display:column property="roomTypeCode" sortable="true" titleKey="ccm.otalog.RoomTypeCode" headerClass="sorted" />
		<display:column property="startDate" sortable="true" titleKey="ccm.otalog.RoomTypeAllocationStart" format="{0,date,yyyy-MM-dd}" headerClass="sorted"/>
				
		<display:column titleKey="common.View" headerClass="sorted">
			<a href="#show" onclick="show('/sendOTALog_detail.do?sendOTALogId=${order.sendOTALogId}&hotelCode=${order.hotelCode}')" 
			class="link ccm-popup-click">
				<fmt:message key="common.ViewDetails"/>
			</a> 
		</display:column>
		
	</display:table>	
	</form>	
	</div>
</div>


<!-- 内容区域 end-->

<script type="text/javascript">

$(document).ready(function() {	
	
	//初始化
	$('#schannelCode').multiselect({
	    enableCaseInsensitiveFiltering: true,
		buttonWidth: '135px',
		allSelectedText:'<fmt:message key="common.select.selectAll"/>',
		selectAllText:'<fmt:message key="common.select.selectAll"/>',
		dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
		nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
	});
	
	//初始化
	$('#sChainCode').multiselect({
	    enableCaseInsensitiveFiltering: true,
		buttonWidth: '135px',
		allSelectedText:'<fmt:message key="common.select.selectAll"/>',
		selectAllText:'<fmt:message key="common.select.selectAll"/>',
		dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
		nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
	});
	
	//初始化
	$('#hotelCode').multiselect({
	    enableCaseInsensitiveFiltering: true,
	    buttonWidth: '135px',
	    allSelectedText:'<fmt:message key="common.select.selectAll"/>',
		selectAllText:'<fmt:message key="common.select.selectAll"/>',
		dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
		nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
	});
	
	
});

 function exportOrder() 
 {
	//请选择酒店
	if($('#hotelCode').val().length == 0){
		alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
		return false;
	}
	document.searchForm.action = "/sendOTALog_export.do";
	$('#searchForm').submit();
 }

 function searchOrder()
 {
	//请选择酒店
	if($('#hotelCode').val().length == 0){
		alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
		return false;
	}
	document.searchForm.action = "/sendOTALog_list.do";
	$('#searchForm').submit();
 }

function show(url)
{
	$('#show').load(url);	
}

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
			              ],
			                hourText:'<fmt:message key="time.Hour"/>',
							minuteText:'<fmt:message key="time.Minute"/>',
							timeText:'<fmt:message key="time.Time"/>',
							currentText:'<fmt:message key="time.Present"/>',
							closeText:'<fmt:message key="common.button.close"/>'
		}
	
	$("#createdTime").datetimepicker($.extend(dpconfig,{
	}));
	$("#lastModifyTime").datetimepicker($.extend(dpconfig,{
	}));
	
	$("#startDate").datepicker($.extend(dpconfig,{
	}));
	
</script>