<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<div class="bt">
			<!--  <a href="0HOTEL-2.html" class="btn_2 blue">新建</a>-->
		</div>
		<fmt:message key="ccm.PropertyRoomOccupancyReport" />
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/></span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="src_hotelId" name="src.hotelCodeList" class="fxt w120" multiple="multiple">
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelCode}"
									 ${fn:contains(src.hotelCodeList, hotel.hotelCode)?"selected":""}
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
						<input id="startDate" name="src.startDate" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${src.startDate }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><span style="color:red">*</span><fmt:message key="common.time.EndDate"/></span>
					</div>
					<div class="i_input">
						<input id="endDate" name="src.endDate" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${src.endDate }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" >
						<span class="text"><fmt:message key="ccm.PropertyRoomOccupancyReport.TrainingFacilitationServicesSupervisor"/></span>
					</div>
					<div class="i_input">
						<input id="specialist" name="src.specialist" class="fxt w180 " value="${src.specialist}" >
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.PropertyRoomOccupancyReport.Occupancy"/></span>
					</div>
					<div class="i_input">
						<input id="rentRate" name="src.rentRate" class="fxt w120 onlyFloat" value="${src.rentRate}" maxlength="8">
					</div>
				</li>
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12" onclick="javascript:search();"><fmt:message key="common.button.searchSelect"/></button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:dataExport();"><fmt:message key="common.button.File"/></button>
			</div>
		</div>
	</div>
</form>
	<div class="c_whitebg">
		<div class="bt_wp">
			<form id="roomRentSearchForm" name="roomRentSearchForm" method="post" action="">
				<display:table name="rentSearchResult.resultList" id="hotelRoomRents" class="ccm_table1" 
					requestURI="" pagesize="20" size="rentSearchResult.totalCount" partialList="true" 
					style="width:100%" form="roomRentSearchForm">
					<display:column property="specialist" sortable="true" titleKey="ccm.PropertyRoomOccupancyReport.TrainingFacilitationServicesSupervisor" headerClass="sorted" />
					<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
					<display:column property="date" sortable="true" titleKey="common.date" format="{0,date,yyyy-MM-dd}" headerClass="sorted" />
					<display:column sortable="true" titleKey="ccm.PropertyRoomOccupancyReport.Occupancy" headerClass="sorted" >
						${hotelRoomRents.rentRate}%
					</display:column>
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
		if($('#src_hotelId option:selected').length == 0){
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
		
		var rentRate = $('#rentRate').val();
		if(strIsNull(rentRate)){
			alert('<fmt:message key="ccm.PropertyRoomOccupancyReport.ErrorMessage.OccupancyMustFilled"/>!');
			return false;
		}
		
		if($('#searchForm').valid()){
			document.searchForm.action = "/roomRent_query.do";
			$('#searchForm').submit();
		}
	}
	
	//导出excel
	function dataExport() {
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
		
		var rentRate = $('#rentRate').val();
		if(strIsNull(rentRate)){
			alert('<fmt:message key="ccm.PropertyRoomOccupancyReport.ErrorMessage.OccupancyMustFilled"/>!');
			return false;
		}
		//如果通过校验
		if($('#searchForm').valid()){
			document.searchForm.action = "/roomRent_export.do"
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
	start.datepicker($.extend(dpconfig,{
		onClose : function(dateText, inst) {
		},
		onSelect : function(selectedDateTime) {
			var startDate = new Date(start.datepicker('getDate'));
			startDate.setDate(startDate.getDate()+1);
			end.datepicker('option', 'minDate', startDate);
		}
	}));

	end.datepicker($.extend(dpconfig,{
		hour:23,
		minute:59,
		onClose : function(dateText, inst) {
		},
		onSelect : function(selectedDateTime) {
			var endDate = new Date(end.datepicker('getDate'));
			endDate.setDate(endDate.getDate()-1);
			start.datepicker('option', 'maxDate', endDate);
		}
	}));
	
	$(document).ready(function() {
		//初始化
		$('#src_hotelId').multiselect({
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
			if($('#src_hotelId option').length>0){
				$('#src_hotelId').next().find('button').html('<fmt:message key="common.select.selectAll"/> <b class="caret"></b>');
				$('#src_hotelId option').attr('selected','selected');
				$('#src_hotelId').next().find('ul>li').find('input').prop('checked',true);
			}
			
			$('#soc_hotelId').next().find('ul>li').find(
				'input[name="multiselect-rall"]').prop('checked',false);
		}
	});
	
	
	
</script>