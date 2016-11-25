<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<div class="bt">
			<!--  <a href="0HOTEL-2.html" class="btn_2 blue">新建</a>-->
		</div>
		<fmt:message key="ccm.ReservationMonitorReport" />
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.InventoryManagement.Property" /></span>
					</div>
					<div class="i_input">
						<select id="soc_hotelId" name="soc.hotelIdList" class="fxt w120" multiple="multiple">
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelId}"
									 ${fn:contains(soc.hotelIdList, hotel.hotelId) or soc.hotelIdList ==null ?"selected":""}
								>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.ReservationMonitorReport.ResvCreatedStartDate" /></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.createStart" id="createStart" cssClass="fxt w120 calendar required" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.ReservationMonitorReport.ResvCreatedEndDate" /></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.createEnd" id="createEnd" cssClass="fxt w120 calendar required" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.ReservationStatus" /></span>
					</div>
					<div class="i_input" style="position:relative;">
						<div class="moreoption" id="orderStatus_click" style="width: 200px;">
							<div class="opts">
								<div class="text w360 typeCode" style="display: none;width: 180px;"></div>
								<div class="text w360 typeName" style="width: 180px;"><fmt:message key="common.select.plesesSelect"/></div>
							</div>
						</div>
						<!--预订类型查看隐藏层-->
						<div id="orderStatus_show" class="ft_layer abs" style="width: 200px;">
							<div class=" n_overFlowY">
								<div class="mgA6">
									<c:forEach items="${orderStatus}" var="rl" varStatus="idx">
										<label class="checkbox">
											<input type="checkbox" id="orderStatus_${idx.index }" value="${rl.key}" name="orderStatusCodeChk"
												<s:if test="soc.statusList.contains(#attr.rl.key)">checked="checked"</s:if>> 
											<span class=""> 
												<span class="span_orderStatusCode">${rl.value}</span>
											</span> 
										</label>
									</c:forEach>
								</div>
							</div>
							<div class="ft_ctr1">
								<button type="button" class="btn_3 white selectAll" style="float: left;"><fmt:message key="common.select.selectAll"/></button>
								<button type="button" class="btn_3 white reverseSel" style="float:left;"><fmt:message key="common.select.Unselect"/>	</button>
								<button type="button" class="btn_3 green mgR6 confirmthis"><fmt:message key="common.button.OK"/></button>
								<button type="button" class="btn_3 white closethis"><fmt:message key="common.button.close"/></button>
							</div>
						</div>
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
	<div class="c_whitebg">
		<div class="bt_wp">
			<form id="orderReportForm" name="orderReportForm" method="post" action="">
				<display:table name="orderSearchResult.resultList" id="order" 
					class="ccm_table1" requestURI="" pagesize="20" 
					size="orderSearchResult.totalCount" partialList="true"
					form="orderReportForm" >
					<display:column property="hotelCode" sortable="true"  titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
					<display:column property="channel" sortable="true"  titleKey="ccm.InventoryManagement.Channels" headerClass="sorted" />
					<display:column property="masterId" sortable="true"  titleKey="ccm.ReservationsManagment.CRSNo" headerClass="sorted" />
					<display:column property="sta" sortable="true" titleKey="ccm.ReservationMonitorReport.ReservationStatus" headerClass="sorted" />
					<display:column property="arr" sortable="true"  titleKey="ccm.ReservationMonitorReport.Arrival" format="{0,date,yyyy-MM-dd}" headerClass="sorted" />
					<display:column property="dep" sortable="true"  titleKey="ccm.ReservationMonitorReport.Departure" format="{0,date,yyyy-MM-dd}" headerClass="sorted" />
					<display:column property="changed" sortable="true"  titleKey="ccm.ReservationMonitorReport.CreatedTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted" />
					<display:column property="type" sortable="true" titleKey="ccm.Channel.RoomTypeCode" headerClass="sorted" />
					<display:column property="ref" sortable="true"  titleKey="ccm.ReservationsManagment.Comments" headerClass="sorted" />
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
	function exportOrder() {
		//请选择酒店
		if($('#soc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		
		if($('#searchForm').valid()){
			document.searchForm.action = "/excel_orderExport.do"
			document.searchForm.submit();
		}
	}

	function searchOrder() {
		//请选择酒店
		if($('#soc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		
		if($('#searchForm').valid()){
			document.searchForm.action = "/order_reportList.do";
			var title = $(".btn-group").find(".multiselect").attr("title").split(",");
			if(title.length>30){
				$('#soc_hotelId').empty();
				$("#soc_hotelId").append('<option value="-ALLCODE-" selected="selected"></option>');
			}
			$('#searchForm').submit();
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
	
	
	var start = $("#createStart");
	var end = $("#createEnd");
	start.datetimepicker($.extend(dpconfig,{
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
		hour:23,
		minute:59,
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
	
	$(document).ready(function() {
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
			
			//如果存在酒店,默认为所有酒店
			if($('#soc_hotelId option').length>0){
				$('#soc_hotelId').next().find('button').html('<fmt:message key="common.select.selectAll"/> <b class="caret"></b>');
				$('#soc_hotelId option').attr('selected','selected');
				$('#soc_hotelId').next().find('ul>li').find('input').prop('checked',true);
			}
			
			$('#soc_hotelId').next().find('ul>li').find(
				'input[name="multiselect-rall"]').prop('checked',false);
			
			//如果存在订单状态,默认全选状态
			if($("input[name='orderStatusCodeChk']").length>0){
				var statusCodes = '';
				$("input[name='orderStatusCodeChk']").each(function(){ 
					$(this).prop('checked',true);
					statusCodes += $(this).next('span').text()+",";
					$('#orderStatus_click .typeCode').append(
							'<input type="text" name="soc.statusList" value="'+$(this).val()+'">');
				});
				
				statusCodes = statusCodes.substr(0,statusCodes.lastIndexOf(','));
				$('#orderStatus_click .typeName').text(statusCodes);
			}
		}
	});
	
	
	function showOrderStatusCode(){
		var orderStatusCodeName='';
		$('#orderStatus_show input:checked').next('span').each(function(){ 
			orderStatusCodeName += $(this).find("span.span_orderStatusCode").text()+",";
		});
		
		$('#orderStatus_click .typeName').text(orderStatusCodeName.substr(0,orderStatusCodeName.lastIndexOf(',')));
		$('#orderStatus_show input:checked').each(function(){ 
			$('#orderStatus_click .typeCode').append(
					'<input type="text" name="soc.statusList" value="'+$(this).val()+'">');
		});
	}
</script>