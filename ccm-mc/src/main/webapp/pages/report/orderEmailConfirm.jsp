<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.ccm.api.util.CommonUtil"%>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<div class="bt">
			<!--  <a href="0HOTEL-2.html" class="btn_2 blue">新建</a>-->
		</div>
		<fmt:message key="ccm.ReservationEmailConfirmationLetter" />
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/></span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="soecc_hotelId" name="soecc.hotelCodes" class="fxt w120" multiple="multiple">
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelCode}"
									 ${fn:contains(soecc.hotelCodes, hotel.hotelCode)?"selected":""}
								>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" >
						<span class="text"><span style="color:red">*</span><fmt:message key="common.time.BeginDate"/></span>
					</div>
					<div class="i_input">
						<input id="startDate" name="soecc.startDate" class="fxt w150 calendar" 
							value="<fmt:formatDate value='${soecc.startDate }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" >
						<span class="text"><span style="color:red">*</span><fmt:message key="common.time.EndDate"/></span>
					</div>
					<div class="i_input">
						<input id="endDate" name="soecc.endDate" class="fxt w150 calendar" 
							value="<fmt:formatDate value='${soecc.endDate }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<!--渠道代码-->
				<li class="col3_1">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.Channel.ChannelCode"/>
						</span>
					</div>
					<div id="channels_show" class="i_input" style="position:relative;">
						<input id="channelCodeHi" value="${soecc.channelCodes }" type="hidden"/>
						<select id="soecc_channelCode" name="soecc.channelCodes" class="fxt w120" multiple="multiple">
							<c:forEach items="${channelList}" var="channel">
								<option value="${channel.channelCode}"
								 	${fn:contains(soecc.channelCodes, channel.channelCode)?"selected":""}
								>${channel.channelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title" >
						<span class="text"><fmt:message key="ccm.ReservationEmailConfirmationLetter.ConfirmationNo"/></span>
					</div>
					<div class="i_input">
						<input id="createEnd" name="soecc.masterId" class="fxt w150 onlyFloat" value="${soecc.masterId }" >
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" >
						<span class="text"><fmt:message key="ccm.ReservationEmailConfirmationLetter.EmailAddress"/></span>
					</div>
					<div class="i_input">
						<input id="createEnd" name="soecc.emailAddress" class="fxt w150 onlyFloat" value="${soecc.emailAddress }" maxlength="400">
					</div>
				</li>
				<!-- 订单状态 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.ReservationStatus"/></span>
					</div>
					<div id="orderStatus_show" class="i_input" style="position:relative;">
						<select id="soecc_orderStatus" name="soecc.statusList" class="fxt w120" multiple="multiple">
							<c:forEach items="${orderStatus}" var="rl">
								<option value="${rl.key}"
								 	${fn:contains(soecc.statusList,rl.key)?"selected":""}
								>${rl.value}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<!-- 邮件类型 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key='ccm.EmailType'/></span>
					</div>
					<div id="orderStatus_show" class="i_input" style="position:relative;">
						<select id="soecc_emailType" name="soecc.emailType" class="fxt w120">
							<c:forEach items="${emailType}" var="rl">
								<option value="${rl.key}"
								 	${fn:contains(soecc.emailType,rl.key)?"selected":""}
								>${rl.value}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<!-- 
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key='ccm.EmailType'/></span>
					</div>
					<div class="i_input">
						<s:select list="#request.emailType" key="soecc.emailType" cssClass="fxt w120"></s:select>
					</div>
				</li>
				 -->
				 
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12" onclick="javascript:search();"><fmt:message key="common.button.searchSelect"/>	</button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
				<button type="button" class="btn_2 white mgR12" onclick="javascript:dataExport();"><fmt:message key="common.button.File"/>	</button>
			</div>
		</div>
	</div>
	<div id="show" class="ccm-popup width750 zoom-anim-dialog mfp-hide" style="height:570px;"></div>
</form>	
	<div class="c_whitebg">
		<div class="bt_wp">
			<form id="orderEmailSearchForm" name="orderEmailSearchForm" method="post" action="">
				<display:table name="orderEmailConfirmResult.resultList" id="orderEmailConfirm" class="ccm_table1" 
					requestURI="" pagesize="20" size="orderEmailConfirmResult.totalCount" partialList="true" 
					style="width:100%" form="orderEmailSearchForm">
					<display:column property="channelCode" sortable="true" titleKey="ccm.Channel.ChannelCode" headerClass="sorted" />
					<display:column property="hotelCode" sortable="true"  titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
					<display:column property="masterId" sortable="true" titleKey="ccm.ReservationEmailConfirmationLetter.ConfirmationNo" headerClass="sorted" />
					<display:column property="sta" sortable="true" titleKey="ccm.ReservationMonitorReport.ReservationStatus" headerClass="sorted" />
					<display:column property="sendTime" sortable="true" titleKey="ccm.ReservationEmailConfirmationLetter.EmailSendingDate" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted" />
					<display:column property="emailAddress" sortable="true"  titleKey="ccm.ReservationEmailConfirmationLetter.EmailAddress" headerClass="sorted" maxLength="40" />
					<display:column property="resultCode" sortable="true" titleKey="ccm.ReservationEmailConfirmationLetter.SendingStatus" headerClass="sorted" />
					<display:column property="sendCount" sortable="true"  titleKey="ccm.ReservationEmailConfirmationLetter.SendNumber" headerClass="sorted" />
					<display:column titleKey="common.View" headerClass="sorted">
						<a href="#show" onclick="show('/orderEmailConfirm_ajaxDetail.do?smsSendId=${orderEmailConfirm.smsSendId}&soecc.emailType=${soecc.emailType}')" class="link ccm-popup-click"><fmt:message key="common.ViewDetails"/></a>
						<a href="javascript:send('${orderEmailConfirm.smsSendId}','${soecc.emailType}');" class="link" ><fmt:message key="ccm.ReservationEmailConfirmationLetter.Resend"/></a>
						<a href="javascript:doWord('${orderEmailConfirm.smsSendId}');" class="link" ><fmt:message key="common.button.File"/></a>
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
	function validateCondition() {
		//请选择酒店
		if($('#soecc_hotelId option:selected').length == 0){
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
		//请选择邮件类型
		if($('#soecc_emailType option:selected').val() == ""){
			alert('<fmt:message key="ccm.PleaseSelectTheEmailType"/>');
			return false;
		}
		return true;
	}
	
	//查询
	function search() {
		if(validateCondition() && $('#searchForm').valid()){
			document.searchForm.action = "/orderEmailConfirm_query.do";
			$('#searchForm').submit();
		}
	}
	
	//导出excel
	function dataExport() {
		//请选择酒店
		if($('#soecc_hotelId option:selected').length == 0){
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
		if(validateCondition() && $('#searchForm').valid()){
			document.searchForm.action = "/orderEmailConfirm_export.do"
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
		
		//订单状态初始化
		$('#soecc_emailType').multiselect({
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
		//订单状态初始化
		$('#soecc_orderStatus').multiselect({
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
		$('#soecc_channelCode').multiselect({
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
		
		//初始化
		$('#soecc_hotelId').multiselect({
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
        });
		
		//如果是最开始跳入
		if('${isInit}' == '1'){
			
			//订单状态全选
			if($('#soecc_orderStatus option').length>0){
		    	$("#soecc_orderStatus option").each(function(){ 
		    		
					$(this).attr("selected", true);
				});
		    	$('#soecc_orderStatus').multiselect('rebuild');
			}
			
			//如果存在酒店,默认为所有酒店
			if($('#soecc_hotelId option').length>0){
				$('#soecc_hotelId').next().find('button').html('<fmt:message key="common.select.selectAll"/> <b class="caret"></b>');
				$('#soecc_hotelId option').attr('selected','selected');
				$('#soecc_hotelId').next().find('ul>li').find('input').prop('checked',true);
			}
			
			$('#soecc_hotelId').next().find('ul>li').find(
				'input[name="multiselect-rall"]').prop('checked',false);
			
			//如果存在渠道,默认为全选状态
		    if($('#soecc_channelCode option').length>0){
		    	$("#soecc_channelCode option").each(function(){ 
					$(this).attr("selected", true);
				});
		    	$('#soecc_channelCode').multiselect('rebuild');
			}
			
		}
	});
	
	function show(url) {
		$('#show').load(url);
	}
	
	function send(smsSendId,emailType){
		//加载房型代码
		$.ajax({
		   	 type:"POST",
		   	 async:false,
		     dataType : "text",
		   	 url:"/orderEmailConfirm_ajaxReSend.do",
		   	 data:{"smsSendId":smsSendId,"soecc.emailType":emailType},
			 success:function(data){
				 if(data == 'smsSendIsNull' || data == 'mslIsNull'){
					 alert('<fmt:message key="ccm.error.001"/>!');
				 }else if(data == 'SUCCESS'){
					 alert('<fmt:message key="ccm.error.002"/>!');
				 }else{
					 alert(data);
				 }
		     },
		     error:function(){
		    	 alert('<fmt:message key="ccm.error.003"/>!');
		     }
	    });
		
	}
	
	function doWord(smsSendId){
		var s = $('#sessionKey').val();
		//提交
		$.ajax({
			url : '/orderEmailConfirm_ajaxIsWord.do',
			beforeSend : function() {
			},
			cache : false,
			data : {smsSendId:smsSendId},
			dataType : "text",
			type : 'POST',
			success : function(data) {
				if(data == 'smsSendIsNull' || data == 'mslIsNull'){
					 alert('<fmt:message key="ccm.error.004"/>!');
				}else{
					document.searchForm.action = "/orderEmailConfirm_ajaxWordExport.do?smsSendId="+smsSendId;
					document.searchForm.submit();
				}
			}
		});
	}
</script>

<script type="text/javascript">
//已选中的值
var optionVal=$("#soecc_channelCode").val()+'';
var optionArr = new Array();
optionArr=optionVal.split(",");
//实际传到后台的值  
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
		$("#soecc_channelCode").find("option[value="+vo+"]").removeAttr("selected");
	}
}  
</script>