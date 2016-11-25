 <%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="java.util.Map"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
$(document).ready(function(){
	// start date
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
		showMonthAfterYear : true,
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
		              ]
	}
	
	var start = $("#InputDateStart");
	var end = $("#InputDateEnd");
	$("#InputDateStart").datepicker($.extend(dpconfig, {
		minDate:new Date(),
		onClose : function(dateText, inst) {
			if (end.val() != '') {
				var testStartDate = start.datepicker('getDate');
				var testEndDate = end.datepicker('getDate');
				if (testStartDate > testEndDate){
					var startDate = new Date(testStartDate);
					startDate.setDate(startDate.getDate());
					end.datepicker('setDate', startDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var startDate = new Date(start.datepicker('getDate'));
			startDate.setDate(startDate.getDate());
			end.datepicker('option', 'minDate', startDate);
		}
	}));
	// end date
	$("#InputDateEnd").datepicker($.extend(dpconfig, {
		minDate:new Date(),
		onClose : function(dateText, inst) {
			if (end.val() != '') {
				var testStartDate = start.datepicker('getDate');
				var testEndDate = end.datepicker('getDate');
				if (testStartDate > testEndDate){
					var endDate = new Date(testEndDate);
					endDate.setDate(endDate.getDate());
					start.datepicker('setDate', endDate);
				}
			} 
		},
		onSelect : function(selectedDateTime) {
			var endDate = new Date(end.datepicker('getDate'));
			endDate.setDate(endDate.getDate());
			start.datepicker('option', 'maxDate', endDate);
		}
	}));
	
	$( ".ccm_table1" ).tooltip( "destroy" );
});
function setRatePlan(){
	var channelCode = $("#amc_targetGDS").val();
	
	$.ajax({
		  type: "POST",
		  url: '/adsBeq_ajaxGetRatePlan.do',
		  data: {"field":channelCode},
		  success: function(data){
			  var opt = "<option value=''>ALL</option>";
			  if(data != null){
				  data.sort(function (a,b){
						return a > b;
					  });
				  
				  $.each(data,function(key,value){
	          			opt += '<option value="'+value+'">'+ value +'</option>';
	              });
			  }
			  $("#amc_ratePlanCode").html(opt);
		  }
	});
	<s:iterator value="channelMap" var="c" >
		if(channelCode == "${c.key}"){
			$("#InputDateEnd").datepicker("option", "maxDate", "${c.value}");
		}
	</s:iterator>
	
	setRoomtype();
}

function setRoomtype(){
	$.ajax({
		  type: "POST",
		  url: '/adsBeq_ajaxGetRoomType.do',
		  data: {"field":$("#amc_targetGDS").val()+"_"+$("#amc_ratePlanCode").val()},
		  success: function(data){
			  var opt = "<option value=''>ALL</option>";
			  if(data != null){
				  data.sort(function (a,b){
						return a > b;
					  });
				  
	        		$.each(data,function(key,value){
	        			opt += '<option value="'+value+'">'+ value +'</option>';
	            	});
			  }
			  $("#amc_roomTypeCode").html(opt);
		  }
	});
}

function sendRoomMsg(){
	
	if($("#amc_targetGDS").val() ==''){
		alert("<fmt:message key='ccm.Channel.message.ChannelIsRequired'/>");
		return;
	}
	if($("#amc_adsType").val() ==''){
		alert("<fmt:message key='ccm.Channel.message.MessageTypeIsRequired'/>");
		return;
	}
	if($("#InputDateStart").val() ==''){
		alert("<fmt:message key='ccm.Channel.message.BeginDateIsRequired'/>");
		return;
	}
	if($("#InputDateEnd").val() ==''){
		alert("<fmt:message key='ccm.Channel.message.EndDateIsRequired'/>");
		return;
	}
	
	
	
	$.ajax({
		  type: "POST",
		  url: '/adsBeq_ajaxSendRoomMsg.do',
		  data: $('#adsForm').serialize(),
		  beforeSend : function() {
				$('#btn_send').addClass('no_ald');
				$('#btn_send').attr('disabled', 'disabled');
		  },
		  success: function(data){
			  alert(data);
			  location.reload();
		  }
	});
}

function closeDiv(){
	location.reload();
// 	$.magnificPopup.close();
}

$(document).ready(function(){
	$('.w1202').change(function(){
		if(!strIsNull($(this).val())){		
			//验证开始日期
			var InputDateStart = $('#InputDateStart').val();
			var start = $("#InputDateStart");
			var end = $("#InputDateEnd");
			if(!strIsNull(InputDateStart)){
				var code = validateYYYYMMDD(InputDateStart);
				if(code!='success'){
					alert(getErrorMsg(code,'开始日期','YYYYMMDD'));
					$(this).val('');
					$(this).focus();
					return false;
				}else{	
					$(this).val(addHRToStr(InputDateStart));
					
					if (end.val() != '') {
						var testStartDate = start.datepicker('getDate');
						var testEndDate = end.datepicker('getDate');
						if (testStartDate > testEndDate){
							var startDate = new Date(testStartDate);
							startDate.setDate(startDate.getDate()+1);
							end.datepicker('setDate', startDate);
						}
					}
					
				}
			}	
			
		}
	});
})

$(document).ready(function(){
	$('.w1203').change(function(){
		if(!strIsNull($(this).val())){		
			//验证结束日期
			var InputDateEnd = $('#InputDateEnd').val();
			var start = $("#InputDateStart");
			var end = $("#InputDateEnd");
			if(!strIsNull(InputDateEnd)){
				var code = validateYYYYMMDD(InputDateEnd);
				if(code!='success'){
					alert(getErrorMsg(code,'结束日期','YYYYMMDD'));
					$(this).val('');
					$(this).focus();
					return false;
				}else{
					$(this).val(addHRToStr(InputDateEnd));
					
					if (end.val() != '') {
						var testStartDate = start.datepicker('getDate');
						var testEndDate = end.datepicker('getDate');
						if (testStartDate > testEndDate){
							var endDate = new Date(testEndDate);
							endDate.setDate(endDate.getDate()-1);
							start.datepicker('setDate', endDate);
						}
					} 
				}
			}
			
		}
	});
})

$(document).ready(function(){
	$('.w1201').change(function(){
		if(!strIsNull($(this).val())){		
		
			if(compareNow($(this).val(),null)=='dateTimeLTNow')
			{	
				alert("不能小于当前时间!");		
				$(this).val('');
				$(this).focus();
			}	
			
		}
	});
})

//-->
</script>
<html><body  class="nobg">
  <div class="div_maincon_a mpb12">
    <div class="div_mc mrd6">
    <form action="/adsBeq_searchLog.do" id="adsForm" name="adsForm" method="post">
      <div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Channel.ChannelCode"/>：</span>
					</div>
					<div class="i_input">
						<s:select name="amc.targetGDS" cssClass="w120" list="channelMap" listValue="key" onchange="setRatePlan();" headerKey="" headerValue=""></s:select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.RestrictionsManagement.RateCode"/>：</span>
					</div>
					<div class="i_input">
						<s:select name="amc.ratePlanCode" cssClass="w120" list="#{ }" onchange="setRoomtype();"></s:select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Channel.RoomTypeCode"/>：</span>
					</div>
					<div class="i_input">
						<s:select name="amc.roomTypeCode" cssClass="w120" list="#{ }" ></s:select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.Channel.MessageType"/>：</span>
					</div>
					<div class="i_input">
						<select name="amc.adsType" id="amc_adsType" class="w120">
							<option></option>
							<option value="FIDELIO_AllMsgRQ"><fmt:message key="ccm.Channel.Alldynamicmessage"/></option>
							<option value="FIDELIO_AvailabilityStatusRQ"><fmt:message key="ccm.Channel.Availablity"/></option>
							<option value="FIDELIO_BlockStatusNotifRQ"><fmt:message key="ccm.Channel.Block"/></option>
							<option value="FIDELIO_RateUpdateNotifRQ"><fmt:message key="ccm.RestrictionsManagement.Rate"/></option>
							<option value="FIDELIO_AvailUpdateNotifRQ"><fmt:message key="ccm.Channel.AvailabilityRestriction"/></option>
							<option value="FIDELIO_AvailStatusNotifRQ"><fmt:message key="common.RoomInventory"/></option>
							<option value="FIDELIO_HotelProductNotifRQ"><fmt:message key="ccm.MessagePushLog.staticmessage"/></option>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="common.time.BeginDate"/>：</span>
					</div>
					<div class="i_input">
					<input name="amc.startDate" id="InputDateStart" type="text" value="<fmt:formatDate value="${amc.startDate}" pattern="yyyy-MM-dd"/>" class="w1201 w1202"/>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="common.time.EndDate"/>：</span>
					</div>
					<div class="i_input">
					<input name="amc.endDate" id="InputDateEnd" type="text" value="<fmt:formatDate value="${amc.endDate}" pattern="yyyy-MM-dd"/>" class="w1201 w1203"/>
					</div>
				</li>
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button type="button" class="btn_2 black mgR12" id="btn_send" onclick="javascript:sendRoomMsg();"><fmt:message key="ccm.Channel.Push"/></button>
<!-- 				<button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);">重置</button> -->
				<button type="button" class="btn_2 white popup-close" onclick="closeDiv();"><fmt:message key="common.button.close"/></button>
			</div>
		</div>
	</div>
      </form>
			</div>
  </div>
</body>
</html>
