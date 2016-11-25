<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<div class="CCMmainConter w1200" style="width: 960px;">
<form id="searchForm" name="searchForm" action="" method="post">
<s:hidden key="soc.hotelId"></s:hidden>
	<div class="title_wp">
		<div class="bt">
			<!--  <a href="0HOTEL-2.html" class="btn_2 blue">新建</a>-->
		</div>
		<fmt:message key="ccm.ReservationsManagment"/>
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<!--  
				<li class="col3_1">
					<div class="i_title">
						<span class="text">酒店</span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.hotelCode" cssClass="fxt w120" />
					</div>
				</li>
				-->
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.CRSNo"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.orderNo" cssClass="fxt w120" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.Name"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.guestName" cssClass="fxt w120" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.AltId"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.pmsId" cssClass="fxt w120" />
					</div>
				</li>
				<!--  
				<li class="col3_1">
					<div class="i_title">
						<span class="text">建立位置</span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="fxt w120" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text">取消号码</span>
					</div>
					<div class="i_input">
						<s:textfield key="" cssClass="fxt w120" />
					</div>
				</li>-->
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.ArrivalStartDate"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.checkinStart" id="checkinStart" cssClass="fxt w120 calendar datetip001" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.ArrivalEndDate"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.checkinEnd" id="checkinEnd" cssClass="fxt w120 calendar datetip001" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.InventoryManagement.Channels"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.channelCode" cssClass="fxt w120" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.DepartureStartDate"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.checkoutStart" id="checkoutStart" cssClass="fxt w120 calendar datetip001" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.DepartureEndDate"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.checkoutEnd" id="checkoutEnd" cssClass="fxt w120 calendar datetip001" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.Status"/></span>
					</div>
					<div class="i_input">
						<appfuse:select id="orderS" cssClass="fxt w120" name="soc.restype" defaultValue="${soc.restype}" dictKey="reservationStatusType" firstText="selectAll"></appfuse:select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.ResvCreatedStartDate"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.createStart" id="createStart" cssClass="fxt w120 calendar datetip001" />
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.ResvCreatedEndDate"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.createEnd" id="createEnd" cssClass="fxt w120 calendar datetip001" />
					</div>
				</li>
				<!-- 渠道订单号 -->
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.Channel.ChannelReservationNumber"/></span>
					</div>
					<div class="i_input">
						<s:textfield key="soc.crsno" cssClass="fxt w120" />
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
	<!--订单变更记录-->
	<div id="OrderChangesLog" class="ccm-popup width900 zoom-anim-dialog mfp-hide"></div>
	<div class="c_whitebg">
		<div class="bt_wp">
			<display:table name="orderSearchResult.resultList" id="order" class="ccm_table1" requestURI="" pagesize="20" size="orderSearchResult.totalCount" partialList="true">

				<display:column property="masterId" sortable="true" titleKey="ccm.ReservationsManagment.CRSNo" headerClass="sorted"/>
				<!-- 渠道订单号 -->
				<display:column property="crsno" sortable="true" titleKey="ccm.Channel.ChannelReservationNumber" headerClass="sorted"/>
				
				<display:column sortable="true"  titleKey="ccm.ReservationsManagment.GuestName" headerClass="sorted">
					${order.name2}${order.name}${order.name4}
				</display:column>
				<display:column property="arr" sortable="true" titleKey="ccm.ReservationsManagment.ArrivalDate" format="{0,date,yyyy-MM-dd}" headerClass="sorted"/>
				<display:column property="dep" sortable="true" titleKey="ccm.ReservationsManagment.DepartureDate" format="{0,date,yyyy-MM-dd}" headerClass="sorted"/>
				<display:column property="changed" sortable="true" titleKey="ccm.ReservationsManagment.ReservationCreatedDate" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted"/>
				<display:column property="restype" sortable="true" titleKey="ccm.ReservationsManagment.Status" headerClass="sorted"/>
				<display:column property="ratePlanCode" sortable="true" titleKey="ccm.RestrictionsManagement.RateCode" headerClass="sorted"/>
				<display:column property="type" sortable="true" titleKey="ccm.ReservationsManagment.RoomType" headerClass="sorted"/>
				<display:column sortable="true" titleKey="ccm.ReservationsManagment.TravelAgentSource" headerClass="sorted">
					${order.qualifyingIdType=="TRAVEL_AGENT"?order.qualifyingIdValue:""}
				</display:column>
				<display:column sortable="true" titleKey="ccm.ReservationsManagment.GroupCompany" headerClass="sorted">
					${order.qualifyingIdType=="CORPORATE"?order.qualifyingIdValue:""}
				</display:column>
				<display:column titleKey="common.button.Activity">
					<a href="javascript:detail('${order.masterId}')" class="link"><fmt:message key="common.ViewDetails"/></a>&nbsp;
					<a href="#OrderChangesLog" class="link mgR12 ccm-popup-click" onclick="showChangesLog('${order.masterId}')"><fmt:message key="ccm.ReservationsManagment.ChangesLog"/></a>
				</display:column>
			</display:table>
		</div>
	</div>
</form>
</div>



<div id="orderCancle" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
<form name="cancleForm" id="cancleForm" method="post">
<input name="orderNo" type="hidden">
	<div class="nm_box">
		<div class="p_title"><fmt:message key="ccm.ReservationsManagment.Comments"/></div>
		<ul class="inq_wp frow">
			<li style="padding-right:1%; float:left; margin-bottom:12px;width:65%;">
				<div class="i_title">
					<span class="text"><fmt:message key='ccm.Reservations.CancelledReason'/></span>
				</div>
				<div class="i_input">
					<div class="formnormal fxt w240">
						<select  class="formnormal fxt w240" id="cancelReasonCode">
							<option value="0"><fmt:message key="common.select.plesesSelect"/></option>
							<option value="1"><fmt:message key='ccm.Reservations.CancelledReason001'/></option>
							<option value="2"><fmt:message key='ccm.Reservations.CancelledReason002'/></option>
							<option value="3"><fmt:message key='ccm.Reservations.CancelledReason003'/></option>
							<option value="4"><fmt:message key='ccm.Reservations.CancelledReason004'/></option>
							<option value="5"><fmt:message key='ccm.Reservations.CancelledReason005'/></option>
							<option value="6"><fmt:message key='ccm.Reservations.CancelledReason006'/></option>
						</select>
					</div>
				</div>
			</li>
			<li style="padding-right:1%; float:left; margin-bottom:12px;width:90%;">
				<div class="i_title">
					<span class="text"><fmt:message key='ccm.Reservations.CancelledReason006'/></span>
				</div>
				<div class="i_input">
					<textarea name="cancelRef" id="cancelRef" style="width:500px;height:30px; padding:0px;"></textarea>
				</div>
			</li>
		</ul>
	</div>
	<div class="i_input" style="text-align: center;">
		<button type="button" class="btn_1 green mgR12 f_save" onclick="cancelOrder();"><fmt:message key="common.button.save" /></button>
		<a class="btn_1 green" href="#" onclick="cancleClose();"><fmt:message key="common.Return" /></a>
	</div>
</form>
</div>



<!-- 内容区域 end-->
<script type="text/javascript">

$(document).ready(function() {
	//日期显示
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
	
	
	$("#checkinStart").datepicker($.extend(dpconfig, {
		defaultDate : "+0w",
		changeMonth : true,
		numberOfMonths : 1,
		onSelect : function(selectedDate, obj) {
			$(this).val(selectedDate);
			$("#checkinEnd").datepicker("option", {
				"minDate" : selectedDate
			});
		}
	}));
	
	$("#checkinEnd").datepicker($.extend(dpconfig,{
		defaultDate : "+0w",
		changeMonth : true,
		numberOfMonths : 1,
		dateFormat : 'yy-mm-dd',
		onSelect : function(selectedDate, obj) {
			$(this).val(selectedDate);
			$("#checkinStart").datepicker("option", {
				"maxDate" : selectedDate
			});
		}
	}));
	
	$("#checkoutStart").datepicker($.extend(dpconfig,{
		defaultDate : "+0w",
		changeMonth : true,
		numberOfMonths : 1,
		dateFormat : 'yy-mm-dd',
		onSelect : function(selectedDate, obj) {
			$(this).val(selectedDate);
			$("#checkoutEnd").datepicker("option", {
				"minDate" : selectedDate
			});
		}
	}));

	$("#checkoutEnd").datepicker($.extend(dpconfig,{
		defaultDate : "+0w",
		changeMonth : true,
		numberOfMonths : 1,
		dateFormat : 'yy-mm-dd',
		onSelect : function(selectedDate, obj) {
			$(this).val(selectedDate);
			$("#checkoutStart").datepicker("option", {
				"maxDate" : selectedDate
			});
		}
	}));

	$("#createStart").datepicker($.extend(dpconfig,{
		defaultDate : "+0w",
		changeMonth : true,
		numberOfMonths : 1,
		dateFormat : 'yy-mm-dd',
		onSelect : function(selectedDate, obj) {
			$(this).val(selectedDate);
			$("#createEnd").datepicker("option", {
				"minDate" : selectedDate
			});
		}
	}));
	
	$("#createEnd").datepicker($.extend(dpconfig,{
		defaultDate : "+0w",
		changeMonth : true,
		numberOfMonths : 1,
		dateFormat : 'yy-mm-dd',
		onSelect : function(selectedDate, obj) {
			$(this).val(selectedDate);
			$("#createStart").datepicker("option", {
				"maxDate" : selectedDate
			});
		}
	}));
})


	$('#exportCustom').click(function() {
		document.searchForm.action = "/excel_orderCustomExport.do"
		document.searchForm.submit();
	});

	function searchOrder() {
		document.searchForm.action = "/channelBookIng_list.do";
		$('#searchForm').submit();
	}

	function detail(orderNo) {
		window.location.href = "/channelBookIng_detail.do?orderNo=" + escape(orderNo);
	}
	function modify(orderNo) {
		window.location.href = "/channelBookIng_modify.do?orderNo=" + escape(orderNo);
	}
	
	function copy(orderNo) {
		var bool = window.confirm("<fmt:message key='ccm.Reservations.copybooking'/>?");
		if(bool){
			window.location.href = "/channelBookIng_copy.do?orderNo=" + escape(orderNo);
		}
	}
	function cancel(orderNo) {
		var bool = window.confirm("<fmt:message key='ccm.Reservations.cancelbooking'/>?");
		if(!bool){
			 setTimeout(function () { 
				$.magnificPopup.close();
			 },1);
		}
		$("#cancleForm").find("input[name='orderNo']").val(orderNo);
	}

	function cancleClose(){
		$.magnificPopup.close();
		$("#cancelRef").val('');
	}
	
	function cancelOrder(){
		var orderNo = $("#cancleForm").find("input[name='orderNo']").val();
		var reason = $("#cancelReasonCode").val();
		var cancelRef = $("#cancelRef").val().trim();
		if(reason==0&&cancelRef==''){
			alert("<fmt:message key='ccm.Reservations.selectreason'/>");
			return false;
		}
		var text = $("#cancelReasonCode").find("option:selected").text();
		cancelRef =text+"    "+cancelRef;
		$.ajax({
			url : '/channelBookIng_cancelOrder.do?orderNo=' + orderNo,
			beforeSend : function() {
			},
			data:{"cancelRef":cancelRef},
			cache : false,
			dataType : "text",
			type : 'POST',
			complete : function(x, t) {
			},
			success : function(data) {
				if (data == 'success') {
					alert('<fmt:message key="ccm.Reservations.SuccessfullyCancelled"/>');
					$.magnificPopup.close();
					searchOrder();
				} else {
					alert(data);
				}
			}
		});
	}
	
	
	function resync(orderNo) {
		$.ajax({
			url : '/channelBookIng_resync.do?orderNo=' + orderNo,
			beforeSend : function() {

			},
			cache : false,
			dataType : "text",
			type : 'POST',
			complete : function(x, t) {
				if (t == 'error') {
					alert('<fmt:message key="ccm.ReservationsManagment.SendingSynchronizationFailed"/>');
				}
			},
			success : function(data) {
				if (data == 'true') {
					alert('<fmt:message key="ccm.ReservationsManagment.SynchronizationCompletedSuccessfully"/>');
				} else {
					alert('<fmt:message key="ccm.ReservationsManagment.SynchronizationFailed"/>');
				}
			}
		});
	}
	function showChangesLog(orderNo) {
		$('#OrderChangesLog').load('/channelBookIng_changesLog.do?orderNo=' + orderNo);
	}
</script>