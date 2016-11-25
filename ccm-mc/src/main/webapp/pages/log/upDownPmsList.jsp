<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<div class="bt">
			<!--  <a href="0HOTEL-2.html" class="btn_2 blue">新建</a>-->
		</div>
		<fmt:message key="ccm.PMSMessageLog.PMSMessageUploadorDownload" />
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow"><%@ include file="/common/messages.jsp"%>
				<!-- 酒店代码 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode" /></span>
					</div>
					 <div class="i_input" style="position:relative;">
	                	<select id="soc_hotelCode" name="soc.hotelCodeList" class="fxt w120">
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelCode}"
									 ${fn:contains(soc.hotelCodeList, hotel.hotelCode)?"selected":""}
								>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
	                </div>
				</li>
				<!-- 上传下传消息 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PMSMessageLog.UploadorDownloadMessage"/></span>
					</div>
					<div class="i_input">
						<s:select list="#request.uploadorDownloadMessage" key="soc.upDown" cssClass="fxt w120"></s:select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="common.time.BeginDate"/></span>
					</div>
					<div class="i_input">
						<input type="text" value="<s:date name="soc.createStart" format="yyyy-MM-dd HH"/>" name="soc.createStart" id="checkinStart" class="fxt w120 calendar">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="common.time.EndDate"/></span>
					</div>
					<div class="i_input">
						<input type="text" value="<s:date name="soc.createEnd" format="yyyy-MM-dd HH"/>" name="soc.createEnd" id="checkinEnd" class="fxt w120 calendar">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PMSMessageLog.CCMMessageNumber"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.ccmMsgId" cssClass="fxt w120" />
					</div>
				</li>
				<!-- 订单号 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PMSMessageLog.ExternalMessageNumber"/> </span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.extMsgId" cssClass="fxt w120" />
					</div>
				</li>
				<!-- 消息模块 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PMSMessageLog.MessageModule"/></span>
					</div>
					<div id="actions_show" class="i_input" style="position:relative;">
						<select id="soc_messageType" class="fxt w120" name="soc.messageTypes" multiple="multiple">
							<c:forEach var="m" items="${messageModule}">
								<option value="${m }" 
									${fn:contains(soc.messageTypes, m)?"selected":""}
								>${m }</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PMSMessageLog.ReservationNo"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.orderId" cssClass="fxt w120" />
					</div>
				</li>
				<!-- 日志状态 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PMSMessageLog.LogStatus"/></span>
					</div>
					<div id="actions_show" class="i_input" style="position:relative;">
						<select id="soc_status" class="fxt w120" name="soc.downPmsMsgStatusList" multiple="multiple">
							<c:forEach var="m" items="${logStatus}">
								<option value="${m }" 
								${fn:contains(soc.downPmsMsgStatusList, m)?"selected":""}
								>${m }</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<!-- 消息类型 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PMSMessageLog.MessageType"/></span>
					</div>
					<div id="actions_show" class="i_input" style="position:relative;">
						<select id="soc_action" class="fxt w120" name="soc.actions" multiple="multiple">
							<c:forEach var="m" items="${messageType}">
								<option value="${m }" 
								${fn:contains(soc.actions, m)?"selected":""}
								>${m }</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<!-- 消息内容房型 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.reports.MessageContentRoomType"/> </span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.messageContentRoomType" cssClass="fxt w120" />
					</div>
				</li>
				<!-- 消息内容日期 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.reports.MessageContentDate"/></span>
					</div>
					<div class="i_input">
						<input type="text" value="<s:date name="soc.messageContentDate" format="yyyy-MM-dd"/>" name="soc.messageContentDate" id="messageContentDate" class="fxt w120 calendar">
					</div>
				</li>
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<!--  <a class="out greylink" href="#" id="exportCustom">导出订单</a>(每次最多只能导出1000条记录)&nbsp;-->
				<button type="button" class="btn_2 black mgR12" onclick="javascript:searchOrder();"><fmt:message key="common.button.searchSelect"/></button>
				<button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
			</div>
		</div>
	</div>
	</form>
	<div id="show" class="ccm-popup width900 zoom-anim-dialog mfp-hide" style="height: 550px;"></div>
	<div class="c_whitebg">
		<div class="bt_wp">
		 <form id="order" name=order method="post" action="">
			<s:if test='soc.upDown=="up"'>
				<display:table name="upDownPmsLogResult.upMsgLogList" form="order" id="order" class="ccm_table1" requestURI="" pagesize="20" size="upDownPmsLogResult.totalCount" partialList="true">
					<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
					<display:column property="createdTime" sortable="true"  titleKey="ccm.ReservationMonitorReport.CreatedTime" headerClass="sorted" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
					<display:column property="extMsgId"  titleKey="ccm.PMSMessageLog.CCMMessageNumber" headerClass="sorted"/>
					<display:column property="beginMsgId" sortable="true"  titleKey="ccm.PMSMessageLog.ExternalMessageNumber" headerClass="sorted"/>
					<display:column property="messageType" sortable="true" titleKey="ccm.PMSMessageLog.MessageModule" headerClass="sorted" />
					<display:column property="message" sortable="true" titleKey="ccm.reports.MessageContentRoomType" headerClass="sorted" />
					<display:column property="extOrderId" sortable="true"  titleKey="ccm.PMSMessageLog.ExternalReservationNo" headerClass="sorted" />
					<display:column property="status" sortable="true"  titleKey="ccm.PMSMessageLog.MessageStatus" headerClass="sorted" />
					<display:column titleKey="common.View" headerClass="sorted">
						<a href="#show" onclick="show('/upDownLog_detail.do?udpcId=${order.receiveMsgLogId}&upDown=up&hotelCode=${order.hotelCode}')" class="link ccm-popup-click"><fmt:message key="common.ViewDetails"/></a>
					</display:column>
				</display:table>
			</s:if>
			<s:elseif test='soc.upDown=="down"'>
				<display:table name="upDownPmsLogResult.resultList" form="order" id="order" class="ccm_table1" requestURI="" pagesize="20" size="upDownPmsLogResult.totalCount" partialList="true">
					<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
					<display:column property="createdTime" sortable="true"  titleKey="ccm.ReservationMonitorReport.CreatedTime" headerClass="sorted" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
					<display:column property="msgId" sortable="true"  titleKey="ccm.PMSMessageLog.CCMMessageNumber" headerClass="sorted" />
					<display:column property="linkedMsgId" sortable="true"  titleKey="ccm.PMSMessageLog.ExternalMessageNumber" headerClass="sorted" />
					<display:column property="messageType" sortable="true"  titleKey="ccm.PMSMessageLog.MessageModule" headerClass="sorted" />
					<display:column property="action" sortable="true"  titleKey="ccm.PMSMessageLog.MessageType" headerClass="sorted" />
					<display:column property="orderId" sortable="true"  titleKey="ccm.PMSMessageLog.ReservationNo"  headerClass="sorted" />
					<display:column property="status" sortable="true"  titleKey="ccm.PMSMessageLog.MessageStatus" headerClass="sorted" />
					<display:column titleKey="common.View" headerClass="sorted">
						<a href="#show" onclick="show('/upDownLog_detail.do?udpcId=${order.sendMsgLogId}&upDown=down&hotelCode=${order.hotelCode}')" class="link ccm-popup-click"><fmt:message key="common.ViewDetails"/></a>
					</display:column>
				</display:table>
			</s:elseif>
			</form>
		</div>
	</div>

<!-- 内容区域 end-->
<script type="text/javascript">
	//
	$(document).ready(function() {  
		//初始化    
		$('#soc_status').multiselect({
			dropRight: true,
	        enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:192,
			buttonWidth: '194px'
	    });
		$('#soc_messageType').multiselect({
			dropRight: true,
	        enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:192,
			buttonWidth: '194px'
	    });
		$('#soc_hotelCode').multiselect({
			dropRight: true,
	        enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:192,
			buttonWidth: '194px'
	    });
		$('#soc_action').multiselect({
			dropRight: true,
	        enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:192,
			buttonWidth: '194px',
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
	    });
		
	});
	
	$('#exportCustom').click(function() {
		document.searchForm.action = "/excel_orderCustomExport.do"
		document.searchForm.submit();
	});

	function show(url) {
		$('#show').load(url);
	}

	function searchOrder() {
		document.searchForm.action = "/upDownLog_list.do";
		$('#searchForm').submit();
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
	
	//消息内容日期
	$("#messageContentDate").datepicker($.extend(dpconfig, {
		
	}));
		
	
	$("#checkinStart").datetimepicker($.extend(dpconfig, {
		showHour: true,
		showMinute: false,
		timeFormat : 'HH',
		defaultDate : "+0w",
		changeMonth : true,
		numberOfMonths : 1,
		dateFormat : 'yy-mm-dd',
		onClose : function(dateText, inst) {
			if ($("#checkinEnd").val() != '') {
				var testStartDate = $("#checkinStart").datetimepicker('getDate');
				var testEndDate = $("#checkinEnd").datetimepicker('getDate');
				if (testStartDate > testEndDate)
					$("#checkinEnd").datetimepicker('setDate', testStartDate);
			} else {
				$("#checkinEnd").val(dateText);
			}
		},
		onSelect : function(selectedDateTime) {
			$("#checkinEnd").datetimepicker('option', 'minDate', $("#checkinStart").datetimepicker('getDate'));
		}

	}));

	$("#checkinEnd").datetimepicker($.extend(dpconfig, {
		showHour: true,
		showMinute: false,
		timeFormat : 'HH',
		defaultDate : "+0w",
		changeMonth : true,
		numberOfMonths : 1,
		dateFormat : 'yy-mm-dd',
		onClose : function(dateText, inst) {
			if ($("#checkinStart").val() != '') {
				var testStartDate = $("#checkinStart").datetimepicker('getDate');
				var testEndDate = $("#checkinEnd").datetimepicker('getDate');
				if (testStartDate > testEndDate)
					$("#checkinStart").datetimepicker('setDate', testEndDate);
			} else {
				$("#checkinStart").val(dateText);
			}
		},
		onSelect : function(selectedDateTime) {
			$("#checkinStart").datetimepicker('option', 'maxDate', $("#checkinEnd").datetimepicker('getDate'));
		}

	}));
</script>