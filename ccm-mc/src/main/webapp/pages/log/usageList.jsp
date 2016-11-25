<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<fmt:message key="ccm.usagereports.AllotmentUsageAnd" />
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Channel.ChannelCode"/></span>
					</div>
					<div id="channel_show" class="i_input" style="position:relative;">
						<select id="uc_channelIdList" name="uc.channelIdList" class="fxt w120" multiple="multiple">
							<c:forEach items="${channelList}" var="channel">
								<option value="${channel.channelId}"
								 	${fn:contains(uc.channelIdList, channel.channelId)?"selected":""}
								>${channel.channelCode}</option>	
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
					    <span class="requiredStar"></span>
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/></span>
					</div>
					<div id="hotel_show" class="i_input" style="position:relative;">
						<select id="uc_hotelIdList" name="uc.hotelIdList" class="fxt w120" multiple="multiple">
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelId}"
								 	${fn:contains(uc.hotelIdList, hotel.hotelId)?"selected":""}
								>${hotel.hotelCode}</option>	
							</c:forEach>
						</select>
					</div>
				</li>	
				<!-- 房型代码 -->
				<li class="col3_1" id="">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.Channel.RoomTypeCode"/>
						</span>
					</div>
					<div id="roomType_show" class="i_input" style="position:relative;">
						<select id="uc_roomTypeList" name="uc.roomTypeList" class="fxt w120" multiple="multiple">
						</select>
					</div>
				</li>
				<!-- 消息内容日期-->
				<li class="col3_1">
					<div class="i_title">
						<span class="requiredStar"></span>
						<span class="text"><fmt:message key="ccm.reports.MessageContentDateBegin"/></span>
					</div>
					<div class="i_input">
						<input type="text" value="<s:date name="uc.resvDateBegin" format="yyyy-MM-dd"/>" 
						name="uc.resvDateBegin" id="uc_resvDateBegin" class="fxt w120 calendar">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="requiredStar"></span>
						<span class="text"><fmt:message key="ccm.reports.MessageContentDateEnd"/></span>
					</div>
					<div class="i_input">
						<input type="text" value="<s:date name="uc.resvDateEnd" format="yyyy-MM-dd"/>" 
						name="uc.resvDateEnd" id="uc_resvDateEnd" class="fxt w120 calendar">
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
		<form id="usageForm" name="usageForm" method="post" action="">
			<display:table varTotals="sumMap" name="usageResult.resultList" id="usage" class="ccm_table1" requestURI="" pagesize="20" 
				size="usageResult.totalCount" partialList="true" form="usageForm" >	
				<display:column property="resvDate" sortable="true"  titleKey="ccm.usagereports.ResvDate" headerClass="sorted" format="{0,date,yyyy-MM-dd}" />
				<display:column property="channelCode" sortable="true"  titleKey="ccm.Channel.ChannelCode" headerClass="sorted"/>	
				<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted"/>
				<display:column property="roomType" sortable="true" titleKey="ccm.ReservationsManagment.RoomType" headerClass="sorted"/>
				<display:column property="allotment" sortable="true" titleKey="ccm.usagereports.allotment" headerClass="sorted" />
				<display:column property="allotmentSold" sortable="true" titleKey="ccm.usagereports.soldAllotment" headerClass="sorted" />
				<display:column property="freeSellSold" sortable="true" titleKey="ccm.usagereports.freeSell" headerClass="sorted" />
			</display:table>
		</form>
	</div>
</div>

<script type="text/javascript">
	
	$(document).ready(function() {
		
		//初始化房型代码
		$('#uc_roomTypeList').multiselect({
			dropRight: true,
            enableCaseInsensitiveFiltering: true,
			maxHeight: 300,
			maxWidth:200,
			buttonWidth: '202px',
			includeSelectAllOption: true,
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
        });

		//酒店代码初始化
		$('#uc_hotelIdList').multiselect({
			dropRight: true,
            enableCaseInsensitiveFiltering: true,
			maxHeight: 300,
			maxWidth:200,
			buttonWidth: '202px',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
        });
		//渠道代码初始化
		$('#uc_channelIdList').multiselect({
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
		
		//加载房型代码
		ajaxGetRoomType('${uc.roomTypeList}');
	});

	//加载房型代码
	function ajaxGetRoomType(ms){
		
		$('#uc_roomTypeList').empty();
		var hotelIds = '';
		$('#hotel_show input:checked').each(function(){ 
			hotelIds += $(this).val() + ',';
		});
		hotelIds = hotelIds.substr(0,hotelIds.lastIndexOf(','));
		
		if($('#hotel_show input:checked').length >= 1){
			$.ajax({
			   	 type:"POST",
			   	 async:false,
			     dataType : "json",
			   	 url:"/usage_ajaxGetRoomType.do",
			   	 data:{"hotelIds":hotelIds},
				 success:function(data){
					   
					  if(data.length > 0){	
						  if(null==ms || ms.length < 1){
								 for(var i =0 ; i < data.length ; i++)
								  {
									  var roomTypes = data[i] ;	  
									  $('#uc_roomTypeList').append('<option value="'+roomTypes.roomTypes+'" selected>'+roomTypes.roomTypes+'</option>');  
								  }
							 }else{
								 for(var i =0 ; i < data.length ; i++)
								 {
									  var roomTypes = data[i];
									  if(ms.indexOf(roomTypes.roomTypes) > 0){
										  $('#uc_roomTypeList').append('<option value="'+roomTypes.roomTypes+'" selected>'+roomTypes.roomTypes+'</option>');	  
									  }else{
										  $('#uc_roomTypeList').append('<option value="'+roomTypes.roomTypes+'">'+roomTypes.roomTypes+'</option>');	  
									  }
									 
								 }
						  }
						  
						 $('#uc_roomTypeList').multiselect('rebuild');
					  }
			     }
		    });
		}
	}
	
	function searchOrder() {
		
		//请选择酒店
		if($('#uc_hotelIdList').val()==null||$('#uc_hotelIdList').val()==""){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		
		//验证日期非空
		if($('#uc_resvDateBegin').val()==null||$('#uc_resvDateBegin').val()==""){
			alert('<fmt:message key="ccm.usagereports.Required"/>'+'<fmt:message key="ccm.reports.MessageContentDateBegin"/>');
			return false;
		}
		if($('#uc_resvDateEnd').val()==null||$('#uc_resvDateEnd').val()==""){
			alert('<fmt:message key="ccm.usagereports.Required"/>'+'<fmt:message key="ccm.reports.MessageContentDateEnd"/>');
			return false;
		}
		//验证日期合法性
		var resvDateBegin = $('#uc_resvDateBegin').val();
		if(!strIsNull(resvDateBegin)){
			var code = validateYYYYMMDD(resvDateBegin);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.reports.MessageContentDateBegin"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var resvDateEnd = $('#uc_resvDateEnd').val();
		if(!strIsNull(resvDateEnd)){
			var code = validateYYYYMMDD(resvDateEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.reports.MessageContentDateEnd"/>','yyyy-MM-dd'));
				return false;
			}
		}
		//只能选择1到31天的数据
		var diff=DateDiff(resvDateEnd,resvDateBegin);
		if((diff>31) ||(diff<0)){
			alert('<fmt:message key="ccm.reports.MessageContentDateRange"/>');
			return false;
		}
		
		document.searchForm.action = "/usage_list.do";
		$('#searchForm').submit();
	}

	 function exportOrder()
	 {
		//请选择酒店
		if($('#uc_hotelIdList').val()==null||$('#uc_hotelIdList').val()==""){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		
		//验证日期非空
		if($('#uc_resvDateBegin').val()==null||$('#uc_resvDateBegin').val()==""){
			alert('<fmt:message key="ccm.usagereports.Required"/>'+'<fmt:message key="ccm.reports.MessageContentDateBegin"/>');
			return false;
		}
		if($('#uc_resvDateEnd').val()==null||$('#uc_resvDateEnd').val()==""){
			alert('<fmt:message key="ccm.usagereports.Required"/>'+'<fmt:message key="ccm.reports.MessageContentDateEnd"/>');
			return false;
		}
		//验证日期合法性
		var resvDateBegin = $('#uc_resvDateBegin').val();
		if(!strIsNull(resvDateBegin)){
			var code = validateYYYYMMDD(resvDateBegin);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.reports.MessageContentDateBegin"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var resvDateEnd = $('#uc_resvDateEnd').val();
		if(!strIsNull(resvDateEnd)){
			var code = validateYYYYMMDD(resvDateEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.reports.MessageContentDateEnd"/>','yyyy-MM-dd'));
				return false;
			}
		}
		//只能选择1到31天的数据
		var diff=DateDiff(resvDateEnd,resvDateBegin);
		if((diff>31) ||(diff<0)){
			alert('<fmt:message key="ccm.reports.MessageContentDateRange"/>');
			return false;
		}	
	
		document.searchForm.action = "/usage_export.do";
		$('#searchForm').submit();
	 }
	 
	var dpconfig = {
		dateFormat : "yy-mm-dd",
		dayNamesMin : [ '<fmt:message key="calendar.week.sunday"/>',
				'<fmt:message key="calendar.week.monday"/>',
				'<fmt:message key="calendar.week.tuesday"/>',
				'<fmt:message key="calendar.week.wednesday"/>',
				'<fmt:message key="calendar.week.thursday"/>',
				'<fmt:message key="calendar.week.friday"/>',
				'<fmt:message key="calendar.week.saturday"/>' ],
		yearSuffix : '<fmt:message key="time.year"/>',
		monthNames : [ '<fmt:message key="calendar.month.january"/>',
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
				'<fmt:message key="calendar.month.december"/>' ],
		monthNamesShort : [ '<fmt:message key="calendar.month.january"/>',
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
				'<fmt:message key="calendar.month.december"/>' ],
		hourText : '<fmt:message key="time.Hour"/>',
		minuteText : '<fmt:message key="time.Minute"/>',
		timeText : '<fmt:message key="time.Time"/>',
		currentText : '<fmt:message key="time.Present"/>',
		closeText : '<fmt:message key="common.button.close"/>'
	}
	//消息内容日期
	var cs = $("#uc_resvDateBegin");
	var es = $("#uc_resvDateEnd");
	cs.datepicker($.extend(dpconfig,{
		onClose : function(dateText, inst) {
			if (es.val() != '') {
				var testStartDate = cs.datepicker('getDate');
				var testEndDate = es.datepicker('getDate');
				if (testStartDate > testEndDate){
					var startDate = new Date(testStartDate);
					startDate.setDate(startDate.getDate());
					es.datepicker('setDate', startDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var startDate = new Date(cs.datepicker('getDate'));
			startDate.setDate(startDate.getDate());
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
					endDate.setDate(endDate.getDate());
					cs.datepicker('setDate', endDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var endDate = new Date(es.datepicker('getDate'));
			endDate.setDate(endDate.getDate());
			cs.datepicker('option', 'maxDate', endDate);
		}
	}));
	
	$('#uc_hotelIdList').bind('change',function(){
		//限制最多选两家酒店
		var hotelIds = '';
		$('#hotel_show input:checked').each(function(){ 
			hotelIds += $(this).val() + ',';
		});
		hotelIds = hotelIds.substr(0,hotelIds.lastIndexOf(','));
		var hsTwo=hotelIds.split(',');
		if(null!=hsTwo&&hsTwo.length>2){
			 alert(resources.NoMoreThanTwo+"!");
			//清除选择
			if($('#uc_hotelIdList option').length>0){
		    	$("#uc_hotelIdList option").each(function(){ 
		    		
					$(this).attr("selected", false);
				});
		    	$('#uc_hotelIdList').multiselect('rebuild');
			}
			return; 
		}
		//加载房型代码
		ajaxGetRoomType(null);
	});
</script>