<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<div class="bt">
			<!--  <a href="0HOTEL-2.html" class="btn_2 blue">新建</a>-->
		</div>
		<fmt:message key="ccm.RoomInventoryandRestrictionsReport" />
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/></span>
					</div>
					<div class="i_input" style="position:relative;">
					    <select id="rspsc_hotelCodes" name="rspsc.hotelCodes" class="fxt w120" multiple="multiple">
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelCode}"
									 ${fn:contains(rspsc.hotelCodes, hotel.hotelCode)?"selected":""}
								>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Channel.RoomTypeCode"/></span>
					</div>
					<div class="i_input" style="position:relative;">
						<div class="moreoption" id="roomTypes_click" style="width: 200px;">
							<div class="opts">
								<div class="text w360 typeCode" style="display: none;width: 180px;"></div>
								<div class="text w360 typeName" style="width: 180px;"><fmt:message key="common.select.plesesSelect"/></div>
							</div>
						</div>
						<!--渠道查看隐藏层-->
						<div id="roomTypes_show" class="ft_layer abs" style="width: 200px;">
							<div class=" n_overFlowY">
								<div class="mgA6">
									<c:forEach items="${roomTypeList}" var="rl" varStatus="idx">
										<label class="checkbox">
											<input type="checkbox" id="roomTypeCode_${idx.index }" value="${rl.roomTypeCode}" name="roomTypeCodeChk"
												<s:if test="rspsc.roomTypeCodes.contains(#attr.rl.roomTypeCode)">checked="checked"</s:if>> 
											<span class=""> 
												<span class="span_roomTypeCode">${rl.roomTypeCode }</span>
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
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.RestrictionsManagement.RateCode"/></span>
					</div>
					<div class="i_input" style="position:relative;">
						<div class="moreoption" id="ratePlans_click" style="width: 200px;">
							<div class="opts">
								<div class="text w360 typeCode" style="display: none;width: 180px;"></div>
								<div class="text w360 typeName" style="width: 180px;"><fmt:message key="common.select.plesesSelect"/></div>
							</div>
						</div>
						<!--渠道查看隐藏层-->
						<div id="ratePlans_show" class="ft_layer abs" style="width: 200px;">
							<div class=" n_overFlowY">
								<div class="mgA6">
									<c:forEach items="${rateplanList}" var="rl" varStatus="idx">
										<label class="checkbox">
											<input type="checkbox" id="ratePlanCode_${idx.index }" value="${rl.ratePlanCode}" name="ratePlanCodeChk"
												<s:if test="rspsc.ratePlanCodes.contains(#attr.rl.ratePlanCode)">checked="checked"</s:if>> 
											<span class=""> 
												<span class="span_ratePlanCode">${rl.ratePlanCode }</span>
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
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Channel.ChannelCode"/></span>
					</div>
					<div class="i_input" style="position:relative;">
						<div class="moreoption" id="channels_click" style="width: 200px;">
							<div class="opts">
								<div class="text w360 typeCode" style="display: none;width: 180px;"></div>
								<div class="text w360 typeName" style="width: 180px;"><fmt:message key="common.select.plesesSelect"/></div>
							</div>
						</div>
						<!--渠道查看隐藏层-->
						<div id="channels_show" class="ft_layer abs" style="width: 200px;">
							<div class=" n_overFlowY">
								<div class="mgA6">
									<c:forEach items="${channelList}" var="rl" varStatus="idx">
										<label class="checkbox">
											<input type="checkbox" id="channelCode_${idx.index }" value="${rl.channelCode}" name="channelCodeChk"
												<s:if test="rspsc.channelCodes.contains(#attr.rl.channelCode)">checked="checked"</s:if>> 
											<span class=""> 
												<span class="span_channelCode">${rl.channelCode }</span>
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
				<li class="col3_1">
					<div class="i_title" >
						<span class="text"><span style="color:red">*</span><fmt:message key="common.time.BeginDate"/></span>
					</div>
					<div class="i_input">
						<input id="startDate" name="rspsc.startDate" class="fxt w180 calendar" 
							value="<fmt:formatDate value='${rspsc.startDate }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" >
						<span class="text"><span style="color:red">*</span><fmt:message key="common.time.EndDate"/></span>
					</div>
					<div class="i_input">
						<input id="endDate" name="rspsc.endDate" class="fxt w180 calendar" 
							value="<fmt:formatDate value='${rspsc.endDate }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="common.type"/></span>
					</div>
					<div class="i_input" style="position:relative;">
						<div class="moreoption" id="dataTypes_click" style="width: 200px;">
							<div class="opts">
								<div class="text w360 typeCode" style="display: none;width: 180px;"></div>
								<div class="text w360 typeName" style="width: 180px;"><fmt:message key="common.select.plesesSelect"/></div>
							</div>
						</div>
						<!--渠道查看隐藏层-->
						<div id="dataTypes_show" class="ft_layer abs" style="width: 200px;">
							<div class=" n_overFlowY">
								<div class="mgA6">
									<c:forEach items="${dataTypeMap}" var="rl" varStatus="idx">
										<label class="checkbox">
											<input type="checkbox" id="dataType_${idx.index }" value="${rl.key}" name="dataTypeChk"
												<s:if test="rspsc.type.contains(#attr.rl.key)">checked="checked"</s:if>> 
											<span class=""> 
												<span class="span_dataTypeName">${rl.value }</span>
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
				<li class="col3_1">
					<div class="i_title" >
						<span class="text"><fmt:message key="ccm.PropertyRoomOccupancyReport.TrainingFacilitationServicesSupervisor"/></span>
					</div>
					<div class="i_input">
						<input id="createStart" name="rspsc.specialist" class="fxt w180 " value="${rspsc.specialist}" >
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
			<form id="roomStatusSearchForm" name="roomStatusSearchForm" method="post" action="">
				<display:table name="switchSearchResult.resultList" id="roomStatusAndProductSwitchs" class="ccm_table1" 
					requestURI="" pagesize="20" size="switchSearchResult.totalCount" partialList="true" 
					style="width:100%" form="roomStatusSearchForm">
					<display:column property="channelCode" sortable="true" titleKey="ccm.Channel.ChannelCode" headerClass="sorted" />
					<display:column property="hotelCode" sortable="true"  titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
					<display:column property="specialist" sortable="true" titleKey="ccm.PropertyRoomOccupancyReport.TrainingFacilitationServicesSupervisor" headerClass="sorted" />
					<display:column property="roomTypeCode" sortable="true" titleKey="ccm.Channel.RoomTypeCode" headerClass="sorted" />
					<display:column property="ratePlanCode" sortable="true"  titleKey="ccm.RestrictionsManagement.RateCode" headerClass="sorted" />
					<display:column property="type" sortable="true" titleKey="common.type" headerClass="sorted" />
					<display:column property="date" sortable="true" titleKey="common.date" format="{0,date,yyyy-MM-dd}" headerClass="sorted" />
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
		if($('#rspsc_hotelCodes option:selected').length == 0){
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
			document.searchForm.action = "/roomStatusAndProductSwitch_query.do";
			$('#searchForm').submit();
		}
	}
	
	//导出excel
	function dataExport() {
		//请选择酒店
		if($('#rspsc_hotelCodes option:selected').length == 0){
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
			document.searchForm.action = "/roomStatusAndProductSwitch_export.do"
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
		$('#rspsc_hotelCodes').multiselect({
			dropRight: true,
            enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:200,
			buttonWidth: '202px',
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>',
			onChange: function(option, checked, select) {
				var selectedOptions = $('#rspsc_hotelCodes option:selected');
				
				$('#roomTypes_show .mgA6').html('');
				$('#roomTypes_click .typeCode').html('');
				$('#roomTypes_click .typeName').text('----------');
				
				$('#ratePlans_show .mgA6').html('');
				$('#ratePlans_click .typeCode').html('');
				$('#ratePlans_click .typeName').text('----------');
				
				if(selectedOptions.length == 1){
					//加载房型和房价
					var hotelCode = selectedOptions.val();
					
					//加载房型代码
					$.ajax({
					   	 type:"POST",
					   	 async:false,
					     dataType : "json",
					   	 url:"roomStatusAndProductSwitch_ajaxGetRoomType.do",
					   	 data:{"hotelCode":hotelCode},
						 success:function(data){
							  if(data.length > 0){
								  var roomCodeName='';
								  for(var i =0 ; i < data.length ; i++){
									  var roomType = data[i] ;
									  $('#roomTypes_show .mgA6').append(
											  '<label class="checkbox"> ' +
												  '<input type="checkbox" ' +
												  'id="roomTypeCode_'+i+'" ' +
												  'value="'+roomType.roomTypeCode+'" ' +
												  'name="roomTypeCodeChk" ' + 
												  'checked="checked" /> ' + 
												  '<span class=""> ' +
												  '<span class="span_roomTypeCode">'+roomType.roomTypeCode+'</span> ' +
												  '</span> ' +
											  '</label>');
									  $('#roomTypes_click .typeCode').append(
												'<input type="text" name="rspsc.roomTypeCodes" value="'+roomType.roomTypeCode+'">');
									  roomCodeName += roomType.roomTypeCode+",";
								  }
								  $('#roomTypes_click .typeName').text(roomCodeName.substr(0,roomCodeName.lastIndexOf(',')));
							  }
					     }
				    });
					
					
					//加载房价代码
					$.ajax({
					   	 type:"POST",
					   	 async:false,
					     dataType : "json",
					   	 url:"roomStatusAndProductSwitch_ajaxGetRateplan.do",
					   	 data:{"hotelCode":hotelCode},
						 success:function(data){
							  if(data.length > 0){
								  var rateCodeName='';
								  for(var i =0 ; i < data.length ; i++){
									  var ratePlan = data[i] ;
									  $('#ratePlans_show .mgA6').append(
											  '<label class="checkbox"> ' +
												  '<input type="checkbox" ' +
												  'id="ratePlanCode_'+i+'" ' +
												  'value="'+ratePlan.ratePlanCode+'" ' +
												  'name="ratePlanCodeChk" ' + 
												  'checked="checked" /> ' + 
												  '<span class=""> ' +
												  '<span class="span_ratePlanCode">'+ratePlan.ratePlanCode+'</span> ' +
												  '</span> ' +
											  '</label>');
									  $('#ratePlans_click .typeCode').append(
												'<input type="text" name="rspsc.ratePlanCodes" value="'+ratePlan.ratePlanCode+'">');
									  rateCodeName += ratePlan.ratePlanCode+",";
								  }
								  $('#ratePlans_click .typeName').text(rateCodeName.substr(0,rateCodeName.lastIndexOf(',')));
							  }
					     }
				    });
				}
            }
        });
		
		//房型代码多选
		$('#roomTypes_click').bind('click',function(){
			$('#roomTypes_show').slideDown();
		});
		$('#roomTypes_show .closethis').bind('click',function(){
			$('#roomTypes_show').slideUp();
		});
		//全选
		$("#roomTypes_show .selectAll").bind('click',function(){
			var checklist = document.getElementsByName("roomTypeCodeChk");
			for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = true;
			}
		});
		//反选
		$("#roomTypes_show .reverseSel").bind('click',function(){
			var checklist = document.getElementsByName("roomTypeCodeChk");
			for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = !checklist[i].checked;
			}
		});
		
		//房型选择
		var roomCodeName='';
		$('#roomTypes_show input:checked').next('span').each(function(){ 
			roomCodeName += $(this).find("span.span_roomTypeCode").text()+",";
		});
		
		if(roomCodeName!=''){
			$('#roomTypes_click .typeName').text(roomCodeName.substr(0,roomCodeName.lastIndexOf(',')));
			$('#roomTypes_show input:checked').each(function(){ 
				$('#roomTypes_click .typeCode').append(
						'<input type="text" name="rspsc.roomTypeCodes" value="'+$(this).val()+'">');
			});
		}
		$('#roomTypes_show .confirmthis').click(function(){
			$('#roomTypes_click .typeCode').empty();
			showRoomCode();
			$('#roomTypes_show').hide();
		});
		
		
		//房价代码多选
		$('#ratePlans_click').bind('click',function(){
			$('#ratePlans_show').slideDown();
		});
		$('#ratePlans_show .closethis').bind('click',function(){
			$('#ratePlans_show').slideUp();
		});
		//全选
		$("#ratePlans_show .selectAll").bind('click',function(){
			var checklist = document.getElementsByName("ratePlanCodeChk");
			for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = true;
			}
		});
		//反选
		$("#ratePlans_show .reverseSel").bind('click',function(){
			var checklist = document.getElementsByName("ratePlanCodeChk");
			for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = !checklist[i].checked;
			}
		});
		
		//房型选择
		var rateCodeName='';
		$('#ratePlans_show input:checked').next('span').each(function(){ 
			rateCodeName += $(this).find("span.span_ratePlanCode").text()+",";
		});
		
		if(rateCodeName!=''){
			$('#ratePlans_click .typeName').text(rateCodeName.substr(0,rateCodeName.lastIndexOf(',')));
			$('#ratePlans_show input:checked').each(function(){ 
				$('#ratePlans_click .typeCode').append(
						'<input type="text" name="rspsc.ratePlanCodes" value="'+$(this).val()+'">');
			});
		}
		$('#ratePlans_show .confirmthis').click(function(){
			$('#ratePlans_click .typeCode').empty();
			showRateCode();
			$('#ratePlans_show').hide();
		});
		
		
		//渠道代码多选 
		$('#channels_click').bind('click',function(){
			$('#channels_show').slideDown();
		});
		$('#channels_show .closethis').bind('click',function(){
			$('#channels_show').slideUp();
		});
		//全选
		$("#channels_show .selectAll").bind('click',function(){
			var checklist = document.getElementsByName("channelCodeChk");
			for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = true;
			}
		});
		//反选
		$("#channels_show .reverseSel").bind('click',function(){
			var checklist = document.getElementsByName("channelCodeChk");
			for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = !checklist[i].checked;
			}
		});
		//渠道选择
		var channelCodeName='';
		$('#channels_show input:checked').next('span').each(function(){ 
			channelCodeName += $(this).find("span.span_channelCode").text()+",";
		});
		
		//如果没有选中任何渠道,则默认选中第一个渠道
		if(channelCodeName!=''){
			$('#channels_click .typeName').text(channelCodeName.substr(0,channelCodeName.lastIndexOf(',')));
			$('#channels_show input:checked').each(function(){ 
				$('#channels_click .typeCode').append(
						'<input type="text" name="rspsc.channelCodes" value="'+$(this).val()+'">');
			});
		}
		
		$('#channels_show .confirmthis').click(function(){
			$('#channels_click .typeCode').empty();
			showChannelCode();
			$('#channels_show').hide();
		});
		
		//数据类型多选 
		$('#dataTypes_click').bind('click',function(){
			$('#dataTypes_show').slideDown();
		});
		$('#dataTypes_show .closethis').bind('click',function(){
			$('#dataTypes_show').slideUp();
		});
		//全选
		$("#dataTypes_show .selectAll").bind('click',function(){
			var checklist = document.getElementsByName("dataTypeChk");
			for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = true;
			}
		});
		//反选
		$("#dataTypes_show .reverseSel").bind('click',function(){
			var checklist = document.getElementsByName("dataTypeChk");
			for(var i=0;i<checklist.length;i++){
			      checklist[i].checked = !checklist[i].checked;
			}
		});
		//数据类型选择
		var dataTypeName='';
		$('#dataTypes_show input:checked').next('span').each(function(){ 
			dataTypeName += $(this).find("span.span_dataTypeName").text()+",";
		});
		
		//如果没有选中任何渠道,则默认选中第一个渠道
		if(dataTypeName!=''){
			$('#dataTypes_click .typeName').text(dataTypeName.substr(0,dataTypeName.lastIndexOf(',')));
			$('#dataTypes_show input:checked').each(function(){ 
				$('#dataTypes_click .typeCode').append(
						'<input type="text" name="rspsc.type" value="'+$(this).val()+'">');
			});
		}
		
		$('#dataTypes_show .confirmthis').click(function(){
			$('#dataTypes_click .typeCode').empty();
			showDataType();
			$('#dataTypes_show').hide();
		});
		
		//如果是最开始跳入
		if('${isInit}' == '1'){
			//如果存在酒店,默认为所有酒店
			if($('#rspsc_hotelCodes option').length>0){
				$('#rspsc_hotelCodes').next().find('button').html('<fmt:message key="common.select.selectAll"/> <b class="caret"></b>');
				$('#rspsc_hotelCodes option').attr('selected','selected');
				$('#rspsc_hotelCodes').next().find('ul>li').find('input').prop('checked',true);
			}
			
			$('#rspsc_hotelCodes').next().find('ul>li').find(
				'input[name="multiselect-rall"]').prop('checked',false);
			//如果存在渠道,默认为第一个渠道
			if($("input[name='channelCodeChk']").length>0){
				
				var channelCodes = '';
				$("input[name='channelCodeChk']").each(function(){ 
					$(this).prop('checked',true);
					channelCodes += $(this).next('span').text()+",";
					$('#channels_click .typeCode').append(
							'<input type="text" name="rspsc.channelCodes" value="'+$(this).val()+'">');
				});
				
				channelCodes = channelCodes.substr(0,channelCodes.lastIndexOf(','));
				$('#channels_click .typeName').text(channelCodes);
			}
			
			//全选数据类型
			if($("input[name='dataTypeChk']").length>0){
				
				var dataTypeNames = '';
				var dataTypeIds = '';
				$("input[name='dataTypeChk']").each(function(){ 
					$(this).prop('checked',true);
					dataTypeNames += $(this).next('span').text()+",";
					dataTypeIds += $(this).val() + ',';
				});
				
				dataTypeNames = dataTypeNames.substr(0,dataTypeNames.lastIndexOf(','));
				dataTypeIds = dataTypeIds.substr(0,dataTypeIds.lastIndexOf(','));
				$('#dataTypes_click .typeName').text(dataTypeNames);
				$('#dataTypes_click .typeCode').append(
						'<input type="text" name="rspsc.type" value="'+dataTypeIds+'">');
			}
		}
	});
	
	function showRoomCode(){
		var roomCodeName='';
		$('#roomTypes_show input:checked').next('span').each(function(){ 
			roomCodeName += $(this).find("span.span_roomTypeCode").text()+",";
		});
		
		$('#roomTypes_show input:checked').each(function(){ 
			$('#roomTypes_click .typeCode').append(
					'<input type="text" name="rspsc.roomTypeCodes" value="'+$(this).val()+'">');
		});
		$('#roomTypes_click .typeName').text(roomCodeName.substr(0,roomCodeName.lastIndexOf(',')));
	}
	
	function showRateCode(){
		var rateCodeName='';
		$('#ratePlans_show input:checked').next('span').each(function(){ 
			rateCodeName += $(this).find("span.span_ratePlanCode").text()+",";
		});
		
		$('#ratePlans_show input:checked').each(function(){ 
			$('#ratePlans_click .typeCode').append(
					'<input type="text" name="rspsc.ratePlanCodes" value="'+$(this).val()+'">');
		});
		$('#ratePlans_click .typeName').text(rateCodeName.substr(0,rateCodeName.lastIndexOf(',')));
	}
	
	function showChannelCode(){
		var channelCodeName='';
		$('#channels_show input:checked').next('span').each(function(){ 
			channelCodeName += $(this).find("span.span_channelCode").text()+",";
		});
		$('#channels_click .typeName').text(channelCodeName.substr(0,channelCodeName.lastIndexOf(',')));
		$('#channels_show input:checked').each(function(){ 
			$('#channels_click .typeCode').append(
					'<input type="text" name="rspsc.channelCodes" value="'+$(this).val()+'">');
		});
	}
	
	function showDataType(){
		var dataTypeName='';
		$('#dataTypes_show input:checked').next('span').each(function(){ 
			dataTypeName += $(this).find("span.span_dataTypeName").text()+",";
		});
		$('#dataTypes_click .typeName').text(dataTypeName.substr(0,dataTypeName.lastIndexOf(',')));
		$('#dataTypes_show input:checked').each(function(){ 
			$('#dataTypes_click .typeCode').append(
					'<input type="text" name="rspsc.type" value="'+$(this).val()+'">');
		});
	}
	
	
</script>