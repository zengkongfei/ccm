<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<div class="title_wp">
	<div class="bt">
	</div>
	<fmt:message key="ccm.orderFail.ReservationMonitorFailureLog"/>
</div>
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/></span>
					</div>
					<div class="i_input" style="position: relative;">
						<select id="soc_hotelId" name="soc.hotelIdList" style="display: none;" class="fxt w120" multiple="multiple" >
							<c:forEach items="${hotelList}" var="hotel">
									<option value="${hotel.hotelId}"
									 	${fn:contains(soc.hotelIdList, hotel.hotelId)?"selected":""}
									>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.PMSMessageLog.MessageType"/></span>
					</div>
					<div class="i_input">
						<select id="soc_sta" name="soc.sta"  class="fxt w120 " >
							<option value=""><fmt:message key="common.select.plesesSelect"/></option>
							<option value="ADD" <c:if test="${soc.sta=='ADD' }">selected</c:if>
							>ADD</option>
							<option value="CANCEL" <c:if test="${soc.sta=='CANCEL' }">selected</c:if>
							>CANCEL</option>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.orderFail.StatusofFax"/></span>
					</div>
					<div class="i_input">
						<select id="soc_sta" name="soc.faxStatus"  class="fxt w120 " >
							<option value=""><fmt:message key="common.All"/></option>
							<option value="SUCCESS" <c:if test="${soc.faxStatus=='SUCCESS' }">selected</c:if>
							><fmt:message key="common.success"/></option>
							<option value="FAIL" <c:if test="${soc.faxStatus=='FAIL' }">selected</c:if>
							><fmt:message key="common.fail"/></option>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.CRSNo"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.bizId"  cssClass="fxt w120 " />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><span style="color:red">*</span><fmt:message key="common.time.BeginDate"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.beginDate" id="beginDate" cssClass="fxt w120 calendar required" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><span style="color:red">*</span><fmt:message key="common.time.EndDate"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.endDate" id="endDate" cssClass="fxt w120 calendar required" />
					</div>
				</li>
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12" onclick="javascript:searchOrder();"><fmt:message key="common.button.searchSelect"/></button>
				<button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
				<button type="button" class="btn_2 white" onclick="doExport();"><fmt:message key="common.button.File"/></button>
			</div>
		</div>
	</div>
</form>
<div id="show" class="ccm-popup width900 zoom-anim-dialog mfp-hide"></div>
<div class="c_whitebg">
	<div class="bt_wp">
		<form id="faxsendLog" name="faxsendLog" method="post" action="">
			<display:table excludedParams="roomTypeChk" form="faxsendLog" name="result.resultList" id="orderFail" class="ccm_table1" requestURI="" pagesize="20" size="result.totalCount" partialList="true">
				<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
				<display:column property="bizId" sortable="true" titleKey="ccm.ReservationsManagment.CRSNo" headerClass="sorted" />
				<display:column property="pmsId" sortable="true" titleKey="ccm.ReservationsManagment.AltId" headerClass="sorted" />
				<%-- <display:column property="cancelId" sortable="true" titleKey="ccm.orderFail.CancellationNumber" headerClass="sorted" /> --%>
				<display:column property="faxStatus" sortable="true" titleKey="ccm.orderFail.FaxResult" headerClass="sorted" />
				<display:column property="sendTime" sortable="true" titleKey="ccm.orderFail.FaxFailureTime" headerClass="sorted" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
			</display:table>
		</form>
	</div>
</div>

<!-- 内容区域 end-->
<script type="text/javascript">
	function searchOrder() {
		//请选择酒店
		if($('#soc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		if($('#searchForm').valid()){
			document.searchForm.action = "/orderFail_list.do";
			$('#searchForm').submit();
		}
	}
	
	function doExport(){
		if($('#soc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		if($('#searchForm').valid()){
			$("#searchForm").attr("action", "/orderFail_export.do");
			$("#searchForm").submit();
		}
	}
	$(document).ready(function() {
	   $('#soc_hotelId').parent().append(headHtml);
	   setTimeout(function () { 
		 //初始化
		  $('#soc_hotelId').multiselect({
			dropRight: true,
	        enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:192,
			buttonWidth: '194px',
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>',
	    });
	   }, 50);
		$('#soc_hotelId').next().remove();
	});
	
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
	
	jQuery.extend(jQuery.validator.messages, {
		required : "<fmt:message key='common.RequiredField'/>"			
	});	
	
	
	var start = $("#beginDate");
	var end = $("#endDate");
	start.datetimepicker($.extend(dpconfig,{
		maxDate:new Date(),
		onClose : function(dateText, inst) {
			if (end.val() != '') {
				var testStartDate = start.datetimepicker('getDate');
				var testEndDate = end.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					end.datetimepicker('setDate', testStartDate);
			}
		},
		onSelect : function(selectedDateTime) {
			end.datetimepicker('option', 'minDate', start.datetimepicker('getDate'));
		}
	}));

	end.datetimepicker($.extend(dpconfig,{
		maxDate:new Date(new Date().getTime()+24*60*60*1000),
		onClose : function(dateText, inst) {
			if (start.val() != '') {
				var testStartDate = start.datetimepicker('getDate');
				var testEndDate = end.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					start.datetimepicker('setDate', testEndDate);
			} else {
				start.val(dateText);
			}
		},
		onSelect : function(selectedDateTime) {
			start.datetimepicker('option', 'maxDate', end.datetimepicker('getDate'));
		}
	}));
	
</script>