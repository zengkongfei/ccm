<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<div class="bt">
			<!--  <a href="0HOTEL-2.html" class="btn_2 blue">新建</a>-->
		</div>
		<fmt:message key="ccm.ReservationProductionSummaryReport"/>
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">

				<li class="col3_1">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.Channel.ChannelCode"/>
						</span>
					</div>
					<!--渠道查看隐藏层-->
					<div id="channels_show" class="i_input" style="position:relative;">
						<input id="channelCodeHi" value="${soc.channelCodeList }" type="hidden"/>
						<select id="soc_channelCode" name="soc.channelCodeList" class="fxt w120" multiple="multiple">
							<c:forEach items="${channelList}" var="channel">
								
								<option value="${channel.channelCode}"
								 	${fn:contains(soc.channelCodeList, channel.channelCode)?"selected":""}
								>${channel.channelCode}</option>
								
							</c:forEach>
						</select>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.ArrivalStartDate"/></span>
					</div>
					<div class="i_input">
						<input id="checkinStart" name="soc.checkinStart" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.checkinStart }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.ArrivalEndDate"/></span>
					</div>
					<div class="i_input">
						<input id="checkinEnd" name="soc.checkinEnd" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.checkinEnd }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.BasicConfiguration.ChainCode"/></span>
					</div>
					<div id="chains_show" class="i_input" style="position:relative;">
						<select id="soc_chainCode" name="soc.chainCodeList" class="fxt w120" multiple="multiple">
							<c:forEach items="${chainList}" var="rl">
								<option value="${rl.chainCode}"
								 	${fn:contains(soc.chainCodeList, rl.chainCode)?"selected":""}
								>${rl.chainCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.DepartureStartDate"/></span>
					</div>
					<div class="i_input">
						<input id="checkoutStart" name="soc.checkoutStart" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.checkoutStart }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.DepartureEndDate"/></span>
					</div>
					<div class="i_input">
						<input id="checkoutEnd" name="soc.checkoutEnd" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.checkoutEnd }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1" id="soc_hotelIdLi">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/></span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="soc_hotelId" name="soc.hotelIdList" class="fxt w120" multiple="multiple">
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
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.ResvCreatedStartDate"/></span>
					</div>
					<div class="i_input">
						<input id="createStart" name="soc.createStart" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.createStart }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.ResvCreatedEndDate"/></span>
					</div>
					<div class="i_input">
						<input id="createEnd" name="soc.createEnd" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.createEnd }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<!-- 订单状态 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.ReservationStatus"/></span>
					</div>
					<div id="orderStatus_show" class="i_input" style="position:relative;">
						<select id="soc_orderStatus" name="soc.statusList" class="fxt w120" multiple="multiple">
							<c:forEach items="${orderStatus}" var="rl">
								<option value="${rl.key}"
								 	${fn:contains(soc.statusList,rl.key)?"selected":""}
								>${rl.value}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationUpdateStartDate"/></span>
					</div>
					<div class="i_input">
						<input id="lastModifyStart" name="soc.lastModifyStart" class="fxt w120 calendar datetip001" 
							value="<fmt:formatDate value='${soc.lastModifyStart }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationUpdateEndDate"/></span>
					</div>
					<div class="i_input">
						<input id="lastModifyEnd" name="soc.lastModifyEnd" class="fxt w120 calendar datetip001" 
							value="<fmt:formatDate value='${soc.lastModifyEnd }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12" onclick="javascript:searchOrder();"><fmt:message key="common.button.searchSelect"/>	</button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:exportOrder();"><fmt:message key="common.button.File"/>	</button>
			</div>
		</div>
	</div>
	</form>
	<div class="c_whitebg">
		<div class="bt_wp">
			<form id="orderSearchForm" name="orderSearchForm" method="post" action="">
			<display:table name="orderSearchResult.masterVOList" id="orderProductCollect" class="ccm_table1" 
				requestURI="" pagesize="20" size="orderSearchResult.totalCount" partialList="true" 
				style="width:100%"  form="orderSearchForm">
				<display:column property="channel" sortable="true" titleKey="ccm.Channel.ChannelCode" headerClass="sorted" />
				<display:column property="sta" sortable="true" titleKey="ccm.ReservationMonitorReport.ReservationStatus" headerClass="sorted" />
				<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
				<display:column property="numberOfOrders" sortable="true" titleKey="com.ReservationMonitorReport.TotalNumberOfReservations" headerClass="sorted" />
				<display:column property="numberOfUnits" sortable="true" titleKey="ccm.ReservationProductionSummaryReport.TotalNoofRooms" headerClass="sorted" />
				<display:column property="sumArrDays" sortable="true" titleKey="ccm.ReservationProductionSummaryReport.TotalNoofNights" headerClass="sorted" />
				<display:column property="totalCharge" sortable="true" titleKey="com.ReservationMonitorReport.TotalAmount" headerClass="sorted" />
				<display:column property="avgCharge" sortable="true" titleKey="com.ReservationMonitorReport.AverageRates" headerClass="sorted" />
				<display:column property="avgArrDays" sortable="true" titleKey="com.ReservationMonitorReport.LengthOfStay" headerClass="sorted" />
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
	function searchOrder() {
	    if($('input[name="soc.channelCodeList"]').val() == ""){
			alert('<fmt:message key="ccm.RestrictionsManagement.error.channelNull"/>');
			return false;
		}
		
		//请选择酒店
		if($('#soc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		
		
		//验证日期
		var checkinStart = $('#checkinStart').val();
		if(!strIsNull(checkinStart)){
			var code = validateYYYYMMDD(checkinStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.ArrivalStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var checkinEnd = $('#checkinEnd').val();
		if(!strIsNull(checkinEnd)){
			var code = validateYYYYMMDD(checkinEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.ArrivalEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		var checkoutStart = $('#checkoutStart').val();
		if(!strIsNull(checkoutStart)){
			var code = validateYYYYMMDD(checkoutStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.DepartureStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var checkoutEnd = $('#checkoutEnd').val();
		if(!strIsNull(checkoutEnd)){
			var code = validateYYYYMMDD(checkoutEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.DepartureEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		var createStart = $('#createStart').val();
		if(!strIsNull(createStart)){
			var code = validateYYYYMMDD(createStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationMonitorReport.ResvCreatedStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var createEnd = $('#createEnd').val();
		if(!strIsNull(createEnd)){
			var code = validateYYYYMMDD(createEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationMonitorReport.ResvCreatedEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var lastModifyStart = $('#lastModifyStart').val();
		if(!strIsNull(lastModifyStart)){
			var code = validateYYYYMMDD(lastModifyStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationUpdateStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var lastModifyEnd = $('#lastModifyEnd').val();
		if(!strIsNull(lastModifyEnd)){
			var code = validateYYYYMMDD(lastModifyEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationUpdateEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		if((strIsNull(checkinStart)||strIsNull(checkinEnd))
				&&(strIsNull(checkoutStart)||strIsNull(checkoutEnd))
				&&(strIsNull(createStart)||strIsNull(createEnd))
				&&(strIsNull(lastModifyStart)||strIsNull(lastModifyEnd))
				){
			alert('<fmt:message key="ccm.ReservationProductionDetailReport.ErrorMessage.4DateNUll"/>!');
			return false;
		}
		
		if($('#searchForm').valid()){
			document.searchForm.action = "/orderProduct_collectQuery.do";
			$('#searchForm').submit();
		}
	}
	
	//导出excel
	function exportOrder() {
		
		if($('input[name="soc.channelCodeList"]').val() == ""){
			alert('<fmt:message key="ccm.RestrictionsManagement.error.channelNull"/>');
			return false;
		}
		
		//请选择酒店
		if($('#soc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		
		
		//验证日期
		var checkinStart = $('#checkinStart').val();
		if(!strIsNull(checkinStart)){
			var code = validateYYYYMMDD(checkinStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.ArrivalStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var checkinEnd = $('#checkinEnd').val();
		if(!strIsNull(checkinEnd)){
			var code = validateYYYYMMDD(checkinEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.ArrivalEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		var checkoutStart = $('#checkoutStart').val();
		if(!strIsNull(checkoutStart)){
			var code = validateYYYYMMDD(checkoutStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.DepartureStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var checkoutEnd = $('#checkoutEnd').val();
		if(!strIsNull(checkoutEnd)){
			var code = validateYYYYMMDD(checkoutEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.DepartureEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		var createStart = $('#createStart').val();
		if(!strIsNull(createStart)){
			var code = validateYYYYMMDD(createStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationMonitorReport.ResvCreatedStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var createEnd = $('#createEnd').val();
		if(!strIsNull(createEnd)){
			var code = validateYYYYMMDD(createEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationMonitorReport.ResvCreatedEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		var lastModifyStart = $('#lastModifyStart').val();
		if(!strIsNull(lastModifyStart)){
			var code = validateYYYYMMDD(lastModifyStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationUpdateStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var lastModifyEnd = $('#lastModifyEnd').val();
		if(!strIsNull(lastModifyEnd)){
			var code = validateYYYYMMDD(lastModifyEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationUpdateEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
	
		if((strIsNull(checkinStart)||strIsNull(checkinEnd))
				&&(strIsNull(checkoutStart)||strIsNull(checkoutEnd))
				&&(strIsNull(createStart)||strIsNull(createEnd))
				&&(strIsNull(lastModifyStart)||strIsNull(lastModifyEnd))
				){
			alert('<fmt:message key="ccm.ReservationProductionDetailReport.ErrorMessage.4DateNUll"/>!');
			return false;
		}
		
		//如果通过校验
		if($('#searchForm').valid()){
			document.searchForm.action = "/orderProduct_collectExport.do";
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
	
	//到店时间
	var cs = $("#checkinStart");
	var es = $("#checkinEnd");
	cs.datepicker($.extend(dpconfig,{
		onClose : function(dateText, inst) {
			if (es.val() != '') {
				var testStartDate = cs.datepicker('getDate');
				var testEndDate = es.datepicker('getDate');
				if (testStartDate > testEndDate){
					var startDate = new Date(testStartDate);
					startDate.setDate(startDate.getDate()+1);
					es.datepicker('setDate', startDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var startDate = new Date(cs.datepicker('getDate'));
			startDate.setDate(startDate.getDate()+1);
			es.datepicker('option', 'minDate', startDate);
		}
	}));

	es.datepicker($.extend(dpconfig,{
		hour:23,
		minute:59,
		onClose : function(dateText, inst) {
			if (es.val() != '') {
				var testStartDate = cs.datepicker('getDate');
				var testEndDate = es.datepicker('getDate');
				if (testStartDate > testEndDate){
					var endDate = new Date(testEndDate);
					endDate.setDate(endDate.getDate()-1);
					cs.datepicker('setDate', endDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var endDate = new Date(es.datepicker('getDate'));
			endDate.setDate(endDate.getDate()-1);
			cs.datepicker('option', 'maxDate', endDate);
		}
	}));
	
	
	//离店时间
	var os = $("#checkoutStart");
	var oe = $("#checkoutEnd");
	os.datepicker($.extend(dpconfig,{
		onClose : function(dateText, inst) {
			if (oe.val() != '') {
				var testStartDate = os.datepicker('getDate');
				var testEndDate = oe.datepicker('getDate');
				if (testStartDate > testEndDate){
					var startDate = new Date(testStartDate);
					startDate.setDate(startDate.getDate()+1);
					oe.datepicker('setDate', startDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var startDate = new Date(os.datepicker('getDate'));
			startDate.setDate(startDate.getDate()+1);
			oe.datepicker('option', 'minDate', startDate);
		}
	}));

	oe.datepicker($.extend(dpconfig,{
		hour:23,
		minute:59,
		onClose : function(dateText, inst) {
			if (oe.val() != '') {
				var testStartDate = os.datepicker('getDate');
				var testEndDate = oe.datepicker('getDate');
				if (testStartDate > testEndDate){
					var endDate = new Date(testEndDate);
					endDate.setDate(endDate.getDate()-1);
					os.datepicker('setDate', endDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var endDate = new Date(oe.datepicker('getDate'));
			endDate.setDate(endDate.getDate()-1);
			os.datepicker('option', 'maxDate', endDate);
		}
	}));
	

	var start = $("#createStart");
	var end = $("#createEnd");
	start.datepicker($.extend(dpconfig,{
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

	end.datepicker($.extend(dpconfig,{
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
	
	
	var mstart = $("#lastModifyStart");
	var mend = $("#lastModifyEnd");
	mstart.datepicker($.extend(dpconfig,{
		onClose : function(dateText, inst) {
			if (mend.val() != '') {
				var testStartDate = mstart.datepicker('getDate');
				var testEndDate = mend.datepicker('getDate');
				if (testStartDate > testEndDate){
					var startDate = new Date(testStartDate);
					startDate.setDate(startDate.getDate()+1);
					mend.datepicker('setDate', startDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var startDate = new Date(mstart.datepicker('getDate'));
			startDate.setDate(startDate.getDate()+1);
			mend.datepicker('option', 'minDate', startDate);
		}
	}));
	mend.datepicker($.extend(dpconfig,{
		hour:23,
		minute:59,
		onClose : function(dateText, inst) {
			if (mend.val() != '') {
				var testStartDate = mstart.datepicker('getDate');
				var testEndDate = mend.datepicker('getDate');
				if (testStartDate > testEndDate){
					var endDate = new Date(testEndDate);
					endDate.setDate(endDate.getDate()-1);
					mstart.datepicker('setDate', endDate);
				}
			} 
		},
		onSelect : function(selectedDateTime) {
			var endDate = new Date(mend.datepicker('getDate'));
			endDate.setDate(endDate.getDate()-1);
			mstart.datepicker('option', 'maxDate', endDate);
		}
	}));
	
	
	$(document).ready(function() {
	
		//如果没有选中任何渠道,则默认选中第一个渠道
	    if($('#soc_channelCode option:selected').length==0){
	    	$("#soc_channelCode option:first").prop("selected", 'selected');
	    }
	    
	    //如果没有选中任何集团,则默认选中第一个集团
	    if($('#soc_chainCode option:selected').length==0){
	    	$("#soc_chainCode option:first").prop("selected", 'selected');
	    }
	    
		//动态加载酒店代码
		$('#chains_show').bind("change",function(){
			$('#soc_hotelId').empty();
			showChainCode();
		});	
		
		//订单状态初始化
		$('#soc_orderStatus').multiselect({
			numberDisplayed: 2,
			dropRight: true,
            enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:200,
			buttonWidth: '202px',
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
        });
		
		//渠道代码初始化
		$('#soc_channelCode').multiselect({
			dropRight: true,
            enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:200,
			buttonWidth: '202px',
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
        });
		
		//集团代码初始化 soc_chainCode
		$('#soc_chainCode').multiselect({
            enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:200,
			buttonWidth: '202px',
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
        });
		
		//初始化
		$('#soc_hotelId').multiselect({
			dropRight: true,
            enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:200,
			buttonWidth: '202px',
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
        });
		
		//订单状态多选
		$('#orderStatus_click').bind('click',function(){
			$('#orderStatus_show').slideDown();
		});
		$('#orderStatus_show .closethis').bind('click',function(){
			$('#orderStatus_show').slideUp();
		});
		//全选
		$("#orderStatus_show .selectAll").bind('click',function(){
			var checklist = document.getElementsByName("orderStatusCodeChk");
			for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = true;
			}
		});
		//反选
		$("#orderStatus_show .reverseSel").bind('click',function(){
			var checklist = document.getElementsByName("orderStatusCodeChk");
			for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = !checklist[i].checked;
			}

		});
		//订单状态选择
		var orderStatusCodeName='';
		$('#orderStatus_show input:checked').next('span').each(function(){ 
			orderStatusCodeName += $(this).find("span.span_orderStatusCode").text()+",";
		});
		
		if(orderStatusCodeName!=''){
			$('#orderStatus_click .typeName').text(orderStatusCodeName.substr(0,orderStatusCodeName.lastIndexOf(',')));
			$('#orderStatus_show input:checked').each(function(){ 
				$('#orderStatus_click .typeCode').append('<input type="text" name="soc.statusList" value="'+$(this).val()+'">');
			});
		}
		$('#orderStatus_show .confirmthis').click(function(){
			$('#orderStatus_click .typeCode').empty();
			showOrderStatusCode();
			$('#orderStatus_show').hide();
		});
		
		//如果是最开始跳入
		if('${isInit}' == '1'){
			
			$('#soc_hotelId').empty();
			showChainCode();
			
			//订单状态全选
			if($('#soc_orderStatus option').length>0){
		    	$("#soc_orderStatus option").each(function(){ 
		    		
					$(this).attr("selected", true);
				});
		    	$('#soc_orderStatus').multiselect('rebuild');
			}
			
		}

	});
	
	function showChainCode(){
		var chainCodeName='';
		$('#chains_show input:checked').next('span').each(function(){ 
			chainCodeName += $(this).find("span.span_chainCode").text()+",";
		});
		
		var chainCodes = '';
		$('#chains_show input:checked').each(function(){ 
			$('#chains_click .typeCode').append(
					'<input type="text" name="soc.chainCodeList" value="'+$(this).val()+'">');
			chainCodes += $(this).val() + ',';
		});
		$('#chains_click .typeName').text(chainCodeName.substr(0,chainCodeName.lastIndexOf(',')));
		chainCodes = chainCodes.substr(0,chainCodes.lastIndexOf(','));
		
		$('#soc_hotelId').next().find('button').html('<fmt:message key="common.select.plesesSelect"/> <b class="caret"></b>');
		$('option', $('#soc_hotelId')).remove();
		
		if($('#chains_show input:checked').length >= 1){
			$.ajax({
			   	 type:"POST",
			   	 async:false,
			     dataType : "json",
			   	 url:"/orderProduct_ajaxGetHotels.do",
			   	 data:{"chainCodes":chainCodes},
				 success:function(data){
					  if(data.length > 0){
						  for(var i =0 ; i < data.length ; i++){
							  var hotel = data[i] ;
							  
							  //初始化
							  $('#soc_hotelId').append(
										'<option value="'+hotel.hotelId+'" selected>'+hotel.hotelCode+'</option>');
							  
						  }
						  $('#soc_hotelId').next().find('button').html('<fmt:message key="common.select.selectAll"/> <b class="caret"></b>');
						  $('#soc_hotelId').multiselect('rebuild');
					  }
			     }
		    });
		}
		
	}
	
	function showChannelCode(){
		var channelCodeName='';
		$('#channels_show input:checked').next('span').each(function(){ 
			channelCodeName += $(this).find("span.span_channelCode").text()+",";
		});
		$('#channels_click .typeName').text(channelCodeName.substr(0,channelCodeName.lastIndexOf(',')));
		$('#channels_show input:checked').each(function(){ 
			$('#channels_click .typeCode').append('<input type="text" name="soc.channelCodeList" value="'+$(this).val()+'">');
		});
	}
	
	$("#soc_hotelIdLi").click(function(){
		if($('#soc_chainCode').val()<1){
			return false;
		}
		$('#soc_hotelId').empty();
		showChainCode();
	});	
	
</script>

<script type="text/javascript">
//已选中的值
var optionVal=$("#soc_channelCode").val()+'';
var optionArr = new Array();
optionArr=optionVal.split(",");
//实际传到后台的值  <input id="channelCodeHi" value="${amc.channelCodeList }" type="hidden"/>
var codes= $("#channelCodeHi").val()+'';
codes=codes.replace("[","");
codes=codes.replace("]","");
codes=codes.replace(/\s+/g,"");
var codeArr = new Array();
codeArr=codes.split(",");
var arrflag=true;
for(var i=0;i<optionArr.length;i++){   
	arrflag=true;
	for(var j=0;j<codeArr.length;j++){  
		if(optionArr[i]==codeArr[j]){
			arrflag=false;
			break;
		}
	}  
	if(arrflag){
		var vo=optionArr[i];
		$("#soc_channelCode").find("option[value="+vo+"]").removeAttr("selected");
	}
}  
</script>