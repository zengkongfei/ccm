<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<div class="bt">
			<!--  <a href="0HOTEL-2.html" class="btn_2 blue">新建</a>-->
		</div>
		<fmt:message key="ccm.RateValidityPeriodReport" />
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.ReservationMonitorReport.PropertyCode" /></span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="spvc_hotelId" name="spvc.hotelCodeList" class="fxt w120" multiple="multiple">
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelCode}"
									 ${fn:contains(spvc.hotelCodeList, hotel.hotelCode)?"selected":""}
								>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><span style="color:red">*</span><fmt:message key="common.time.BeginDate"/></span>
					</div>
					<div class="i_input">
						<input id="startDate" name="spvc.startDate" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${spvc.startDate }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><span style="color:red">*</span><fmt:message key="common.time.EndDate"/></span>
					</div>
					<div class="i_input">
						<input id="endDate" name="spvc.endDate" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${spvc.endDate }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" >
						<span class="text"><fmt:message key="ccm.PropertyRoomOccupancyReport.TrainingFacilitationServicesSupervisor"/></span>
					</div>
					<div class="i_input">
						<input id="createStart" name="spvc.specialist" class="fxt w180 " value="${spvc.specialist}" >
					</div>
				</li>
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12" onclick="javascript:search();"><fmt:message key="common.button.searchSelect"/>	</button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:dataExport();"><fmt:message key="common.button.File"/>	</button>
			</div>
		</div>
	</div>
</form>
	<div class="c_whitebg">
		<div class="bt_wp">
			<form id="priceValidSearchForm" name="priceValidSearchForm" method="post" action="">
				<display:table name="priceSearchResult.resultList" id="priceValidTimes" class="ccm_table1" 
					requestURI="" pagesize="20" size="priceSearchResult.totalCount" partialList="true" 
					style="width:100%" form="priceValidSearchForm">
					<display:column property="specialist" sortable="true" titleKey="ccm.PropertyRoomOccupancyReport.TrainingFacilitationServicesSupervisor" headerClass="sorted" />
					<display:column property="hotelCode" sortable="true"  titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
					<display:column property="ratePlanCode" sortable="true" titleKey="ccm.RestrictionsManagement.RateCode" headerClass="sorted" />
					<display:column property="ratePlanName" sortable="true" titleKey="ccm.RateValidityPeriodReport.RateName" headerClass="sorted" />
					<display:column property="roomTypeCode" sortable="true" titleKey="ccm.Channel.RoomTypeCode" headerClass="sorted" />
					<display:column property="roomTypeName" sortable="true"  titleKey="ccm.RateValidityPeriodReport.RoomTypeName" headerClass="sorted" />
					<display:column property="weeks" sortable="true" titleKey="ccm.Rates.Weeks" headerClass="sorted" />
					<display:column property="effectiveDate" sortable="true" titleKey="common.time.BeginDate" format="{0,date,yyyy-MM-dd}" headerClass="sorted" />
					<display:column property="expireDate" sortable="true" titleKey="common.time.EndDate" format="{0,date,yyyy-MM-dd}" headerClass="sorted" />
				</display:table>
			</form>
		</div>
	</div>

<!-- 内容区域 end-->
<script type="text/javascript">
	$("#searchForm").effectiveAndValidate({
		errorPlacement : function(error, element) {
			var errWrap = $('<span class=\"error\"></span>');
			error.appendTo(errWrap);
			if (element.is(":radio"))
				errWrap.appendTo(element.parent().parent());
			else if (element.is(":checkbox"))
				errWrap.appendTo(element.parent().parent());
			else if(element.next().is("span"))
				errWrap.appendTo(element.parent().parent());
			else
				errWrap.appendTo(element.parent());
		}
	});
	
	//查询
	function search() {
		//请选择酒店
		if($('#spvc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		//验证日期
		var startDate = $('#startDate').val();
		if(!strIsNull(startDate)){
			var code = validateYYYYMMDD(startDate);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var endDate = $('#endDate').val();
		if(!strIsNull(endDate)){
			var code = validateYYYYMMDD(endDate);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		if(strIsNull(startDate)||strIsNull(endDate)){
			alert('<fmt:message key="ccm.PropertyRoomOccupancyReport.ErrorMessage.StartDateAndEndDate"/>!');
			return false;
		}
		
		if($('#searchForm').valid()){
			document.searchForm.action = "/priceValid_query.do";
			$('#searchForm').submit();
		}
	}
	
	//导出excel
	function dataExport() {
		//请选择酒店
		if($('#spvc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		//验证日期
		var startDate = $('#startDate').val();
		if(!strIsNull(startDate)){
			var code = validateYYYYMMDD(startDate);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var endDate = $('#endDate').val();
		if(!strIsNull(endDate)){
			var code = validateYYYYMMDD(endDate);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		if(strIsNull(startDate)||strIsNull(endDate)){
			alert('<fmt:message key="ccm.PropertyRoomOccupancyReport.ErrorMessage.StartDateAndEndDate"/>!');
			return false;
		}
		
		//如果通过校验
		if($('#searchForm').valid()){
			document.searchForm.action = "/priceValid_export.do"
			document.searchForm.submit();
		}
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
			              ]
		}
	
	var start = $("#startDate");
	var end = $("#endDate");
	start.datepicker($.extend(dpconfig, {
		onClose : function(dateText, inst) {
			if (end.val() != '') {
				var testStartDate = start.datepicker('getDate');
				var testEndDate = end.datepicker('getDate');
				if (testStartDate > testEndDate){
					var startDate = new Date(testStartDate);
					startDate.setDate(startDate.getDate()+1);
					end.datepicker('setDate', startDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var startDate = new Date(start.datepicker('getDate'));
			startDate.setDate(startDate.getDate()+1);
			end.datepicker('option', 'minDate', startDate);
		}
	}));

	end.datepicker($.extend(dpconfig, {
		hour:23,
		minute:59,
		onClose : function(dateText, inst) {
			if (end.val() != '') {
				var testStartDate = start.datepicker('getDate');
				var testEndDate = end.datepicker('getDate');
				if (testStartDate > testEndDate){
					var endDate = new Date(testEndDate);
					endDate.setDate(endDate.getDate()-1);
					start.datepicker('setDate', endDate);
				}
			} 
		},
		onSelect : function(selectedDateTime) {
			var endDate = new Date(end.datepicker('getDate'));
			endDate.setDate(endDate.getDate()-1);
			start.datepicker('option', 'maxDate', endDate);
		}
	}));
	
	$(document).ready(function() {
		
		//初始化
		$('#spvc_hotelId').multiselect({
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
		
		$('.onlyFloat').keydown(function(e){
			return filterFloatInput(e,$(this).val());
		});
		
		//如果是最开始跳入
		if('${isInit}' == '1'){
			//如果存在酒店,默认为所有酒店
			if($('#spvc_hotelId option').length>0){
				$('#spvc_hotelId').next().find('button').html('<fmt:message key="common.select.selectAll"/> <b class="caret"></b>');
				$('#spvc_hotelId option').attr('selected','selected');
				$('#spvc_hotelId').next().find('ul>li').find('input').prop('checked',true);
			}
			
			$('#spvc_hotelId').next().find('ul>li').find(
				'input[name="multiselect-rall"]').prop('checked',false);
		}
	});
	
	function showHotelCode(){
		var hotelCodeName='';
		$('#hotels_show input:checked').next('span').each(function(){ 
			hotelCodeName += $(this).find("span.span_hotelCode").text()+",";
		});
		
		$('#hotels_show input:checked').each(function(){ 
			$('#hotels_click .typeCode').append(
					'<input type="text" name="spvc.hotelCodeList" value="'+$(this).val()+'">');
		});
		$('#hotels_click .typeName').text(hotelCodeName.substr(0,hotelCodeName.lastIndexOf(',')));
		
	}
	
</script>