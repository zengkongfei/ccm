<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<div class="title_wp">
	<div class="bt">
	</div>
	<fmt:message key="ccm.orderFail.InterfaceMonitorFailureLog"/>
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
						<select id="soc_hotelId" name="soc.hotelCodeList" style="display: none;" class="fxt w120" multiple="multiple" >
							<c:forEach items="${hotelList}" var="hotel">
									<option value="${hotel.hotelCode}"
									 	${fn:contains(soc.hotelCodeList, hotel.hotelCode)?"selected":""}
									>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PropertyInterfaceMonitor.logStatus"/></span>
					</div>
					<div class="i_input">
						<select  name="soc.status"  class="fxt w120 " >
							<option value=""><fmt:message key="common.select.plesesSelect"/></option>
							<option value="tsi" <c:if test="${soc.status=='tsi' }">selected</c:if>
							><fmt:message key="ccm.PropertyInterfaceMonitor.Interface"/></option>
							<option value="tsr" <c:if test="${soc.status=='tsr' }">selected</c:if>
							><fmt:message key="ccm.PropertyInterfaceMonitor.Reservation"/></option>
							<option value="tsm" <c:if test="${soc.status=='tsm' }">selected</c:if>
							><fmt:message key="ccm.PropertyInterfaceMonitor.MessageJam"/></option>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PropertyInterfaceMonitor.CaseStatus"/></span>
					</div>
					<div class="i_input">
						<select  name="soc.caseStatus"  class="fxt w120 " >
							<option value=""><fmt:message key="common.select.plesesSelect"/></option>
							<option value="1" <c:if test="${soc.caseStatus=='1' }">selected</c:if>><fmt:message key="ccm.PropertyInterfaceMonitor.Created"/></option>
							<option value="2" <c:if test="${soc.caseStatus=='2' }">selected</c:if>><fmt:message key="ccm.PropertyInterfaceMonitor.CreateFailed"/></option>
							<option value="3" <c:if test="${soc.caseStatus=='3' }">selected</c:if>><fmt:message key="ccm.PropertyInterfaceMonitor.Closed"/></option>
							<option value="4" <c:if test="${soc.caseStatus=='4' }">selected</c:if>><fmt:message key="ccm.PropertyInterfaceMonitor.CloseFailed"/></option>
						</select>
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
		<form id="shijicareLog" name="shijicareLog" method="post" action="">
			<display:table excludedParams="roomTypeChk" form="shijicareLog" name="result.resultList" id="shijicareLog" class="ccm_table1" requestURI="" pagesize="20" size="result.totalCount" partialList="true">
				<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
				<display:column sortable="true" titleKey="ccm.PropertyInterfaceMonitor.logStatus" headerClass="sorted">
					<c:if test="${shijicareLog.assignTo=='tsi'}"><fmt:message key="ccm.PropertyInterfaceMonitor.Interface"/></c:if>
					<c:if test="${shijicareLog.assignTo=='tsr'}"><fmt:message key="ccm.PropertyInterfaceMonitor.Reservation"/></c:if>
					<c:if test="${shijicareLog.assignTo=='tsm'}"><fmt:message key="ccm.PropertyInterfaceMonitor.MessageJam"/></c:if>
				</display:column>
				
				<display:column sortable="true" titleKey="ccm.PropertyInterfaceMonitor.CaseStatus" class="td_br_solid"   maxLength="25">
					<c:if test="${shijicareLog.status=='SUCCESS' && !shijicareLog.isclose }"><fmt:message key="ccm.PropertyInterfaceMonitor.Created"/></c:if>
					<c:if test="${shijicareLog.status=='FAIL' }"><fmt:message key="ccm.PropertyInterfaceMonitor.CreateFailed"/></c:if>
					<c:if test="${shijicareLog.status=='SUCCESS' && shijicareLog.isclose && shijicareLog.closeCode=='1'}"><fmt:message key="ccm.PropertyInterfaceMonitor.Closed"/></c:if>
					<c:if test="${shijicareLog.status=='SUCCESS' && shijicareLog.isclose && shijicareLog.closeCode!='1'}"><fmt:message key="ccm.PropertyInterfaceMonitor.CloseFailed"/></c:if>
				</display:column>
				
				<display:column sortable="true" title="CASE ID" class="td_br_solid"   maxLength="25">
					<c:if test="${shijicareLog.status=='SUCCESS' }">${shijicareLog.resultMsg }</c:if>
				</display:column>
				<display:column  titleKey="ccm.MessagePushLog.ExceptionReason" class="td_br_solid"   maxLength="25">
					<c:if test="${shijicareLog.status=='FAIL' }">${shijicareLog.resultMsg }</c:if>
					<c:if test="${shijicareLog.isclose && shijicareLog.closeCode!='1'}">${shijicareLog.closeDesp }</c:if>
				</display:column>
				<display:column property="createdTime" sortable="true" titleKey="ccm.orderFail.FailureTimeCaseSetup" headerClass="sorted" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
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
			document.searchForm.action = "/interfaceFail_list.do";
			$('#searchForm').submit();
		}
	}
	
	function doExport(){
		if($('#soc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		if($('#searchForm').valid()){
			$("#searchForm").attr("action", "/interfaceFail_export.do");
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