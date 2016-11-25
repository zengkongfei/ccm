 <%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
<!--
function openwindow(url,name,iWidth,iHeight)
{
 var url;                                 //转向网页的地址;
 var name;                           //网页名称，可为空;
 var iWidth;                          //弹出窗口的宽度;
 var iHeight;                        //弹出窗口的高度;
 var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
 var iLeft = (window.screen.availWidth-10-iWidth)/2;           //获得窗口的水平位置;
 window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}

function subForm(){
	
	//1次最多只能查询50家酒店的数据
	var hotelCodes=$("#amc_hotelCode").val()+'';
	if(null!=hotelCodes){
		var hs=hotelCodes.split(",");
		if(hs.length>50){
			alert("<fmt:message key='ccm.reports.adsbeq50'/>！");
			return;
		}
	}
	//如果推送状态包含推送成功，则酒店代码为单选且必选
	var ss=$('#amc_status').val()+'';
	if(ss.indexOf('1')>=0){
		if('null'==hotelCodes || null==hotelCodes || ''==hotelCodes){
			alert("<fmt:message key='ccm.PleaseSelectThePropertyCode'/>！");
			return;
		}else{
			var hs=hotelCodes.split(",");
			if(hs.length>1){
				alert("<fmt:message key='ccm.reports.adsbeqOne'/>！");
				return;
			}
		}
	}
	
	if($("#adsForm").valid()){
		$("#adsForm").attr("action", "adsBeq_searchLog.do");
		$("#adsForm").submit();
	}
	
}

function exportLog(){
	
	//次最多只能查询50家酒店的数据
	var hotelCodes=$("#amc_hotelCode").val()+'';
	if(null!=hotelCodes){
		var hs=hotelCodes.split(",");
		if(hs.length>50){
			alert("<fmt:message key='ccm.reports.adsbeq50'/>！");
			return;
		}
	}
	//如果推送状态包含推送成功，则酒店代码为单选且必选
	var ss=$('#amc_status').val()+'';
	if(ss.indexOf('1')>=0){
		if('null'==hotelCodes || null==hotelCodes || ''==hotelCodes){
			alert("<fmt:message key='ccm.PleaseSelectThePropertyCode'/>！");
			return;
		}else{
			var hs=hotelCodes.split(",");
			if(hs.length>1){
				alert("<fmt:message key='ccm.reports.adsbeqOne'/>！");
				return;
			}
		}
	}
	
	$("#adsForm").attr("action", "adsBeq_exportLog.do");
	$("#adsForm").submit();
}
$(document).ready(function(){
	
  //如果没有选中任何渠道,则默认选中第一个渠道
  /*
  if($('#amc_channelCode option:selected').length==0){
		$("#amc_channelCode option:first").prop("selected", 'selected');
  }
  */
  
  //渠道代码初始化
  $('#amc_channelCode').multiselect({
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
	
  //如果没有选中任何集团,则默认选中第一个集团
  /*
  if($('#amc_chainCode option:selected').length==0){
		$("#amc_chainCode option:first").prop("selected", 'selected');
  }
  */
  
  //集团代码初始化
  $('#amc_chainCode').multiselect({
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
   
  //如果没有选中任何酒店,则默认选中第一个酒店
  /*
  if($('#amc_hotelCode option:selected').length==0){
		$("#amc_hotelCode option:first").prop("selected", 'selected');
  }
  */
  //如果推送状态包含推送成功，则酒店代码为单选且必选
  $('#amc_status').change(function(){
	  var ss=$('#amc_status').val()+'';
	  if(ss.indexOf('1')>=0){
		  $('#amc_hotelCode').removeAttr('multiple');
		  $('#amc_hotelCode').multiselect('rebuild');
	  }else{
		  $('#amc_hotelCode').attr('multiple','multiple');	
		  $('#amc_hotelCode').multiselect('rebuild');
	  }
  });
  
  $('#amc_hotelCode').change(function(){
	//1次最多只能查询50家酒店的数据
		var hotelCodes=$("#amc_hotelCode").val()+'';
		if(null!=hotelCodes){
			var hs=hotelCodes.split(",");
			if(hs.length>50){
				/*
				var hotelCodesSelected=$("#amc_hotelCode option:selected");
				var hotelCode=hotelCodesSelected[3].innerHTML;  //innerHTML innerText
				var index=hotelCodesSelected[3].index;
				hotelCodesSelected[3].remove();
				$('#amc_hotelCode').append(index,'<option value="'+hotelCode+'">'+hotelCode+'</option>');	  
				$('#amc_hotelCode').multiselect('rebuild');
				*/
				alert("<fmt:message key='ccm.reports.adsbeq50'/>！");
				return false;
			}
		}
  });
  
   //酒店代码初始化
   $('#amc_hotelCode').multiselect({
	    numberDisplayed: 2,
		dropRight: true,
        enableCaseInsensitiveFiltering: true,
		includeSelectAllOption: false,
		maxHeight: 300,
		maxWidth:200,
		buttonWidth: '202px',
		allSelectedText:'<fmt:message key="common.select.selectAll"/>',
		selectAllText:'<fmt:message key="common.select.selectAll"/>',
		dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
		nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
    });
    //刷新页面 加载酒店代码
    setHotelCode('${amc.hotelCodeList}');
   
    //房型代码初始化
    $('#amc_roomTypeCode').multiselect({
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
    //刷新页面 加载房型代码
    //setRoomTypeCode('${amc.roomTypeCodeList}');
    
    //推送状态
    $('#amc_status').multiselect({
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
    //消息类型 
     $('#amc_adsType').multiselect({
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

	var mstart = $("#InputDateStart");
	var mend = $("#InputDateEnd");
	mstart.datetimepicker($.extend(dpconfig,{
		onClose : function(dateText, inst) {
			if (mend.val() != '') {
				var testStartDate = mstart.datetimepicker('getDate');
				var testEndDate = mend.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					mend.datetimepicker('setDate', testStartDate);
			}
		},
		onSelect : function(selectedDateTime) {
			mend.datetimepicker('option', 'minDate', mstart.datetimepicker('getDate'));
		}
	}));
	mend.datetimepicker($.extend(dpconfig,{
		hour:23,
		minute:59,
		onClose : function(dateText, inst) {
			if (mstart.val() != '') {
				var testStartDate = mstart.datetimepicker('getDate');
				var testEndDate = mend.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					mstart.datetimepicker('setDate', testEndDate);
			} else {
				mstart.val(dateText);
			}
		},
		onSelect : function(selectedDateTime) {
			mstart.datetimepicker('option', 'maxDate', mend.datetimepicker('getDate'));
		}
	}));
	
	$( ".ccm_table1" ).tooltip( "destroy" );
	
});

function setHotelCode(ms){	
	var chainCodes=$("#amc_chainCode").val();
	if(null!=chainCodes && chainCodes.length > 0){
		$.ajax({
			  type: "POST",
			  url: '/adsBeq_ajaxGetHotelCode.do?chainCodes='+chainCodes,
			  data: {"chainCodes":chainCodes},
			  success: function(data){ 
				 $('#amc_hotelCode').empty();
				 if(data.length > 0){ 
				     if(null==ms || ms.length < 1){
						 for(var i =0 ; i < data.length ; i++)
						 {
							 var hotel = data[i] ;
							 $('#amc_hotelCode').append('<option value="'+hotel.hotelCode+'">'+hotel.hotelCode+'</option>');	  
						 }	 
					 }else{
						 for(var i =0 ; i < data.length ; i++)
						 {
							 var hotel = data[i] ;
							 if(ms.indexOf(hotel.hotelCode) > 0){
								  $('#amc_hotelCode').append('<option value="'+hotel.hotelCode+'" selected>'+hotel.hotelCode+'</option>');	  
							 }else{
								  $('#amc_hotelCode').append('<option value="'+hotel.hotelCode+'">'+hotel.hotelCode+'</option>');	  
							 }
							 
						 }
				    }
				    $('#amc_hotelCode').multiselect('rebuild');
				   
				 }
			  }
		});
		
	}else{
		 $('#amc_hotelCode').empty();
		 $('#amc_hotelCode').multiselect('rebuild');
	}
	 
   var ss=$('#amc_status').val()+'';
   if(ss.indexOf('1')>=0){
	  $('#amc_hotelCode').removeAttr('multiple');
	  $('#amc_hotelCode').multiselect('rebuild');
   }else{
	  $('#amc_hotelCode').attr('multiple','multiple');	
	  $('#amc_hotelCode').multiselect('rebuild');
   }
	  
}

function setRoomTypeCode(ms){
	
	var hotelCodes=$("#amc_hotelCode").val()+'';
	if('null'!=hotelCodes && null!=hotelCodes && hotelCodes.length > 0){
		
		var hs=hotelCodes.split(",");
		if(hs.length>50){
			alert("<fmt:message key='ccm.reports.adsbeq50'/>！");
		}else{
			$.ajax({
				  type: "POST",
				  url: '/adsBeq_ajaxGetRoomTypeCode.do?hotelCodes='+hotelCodes,
				  data: {"hotelCodes":hotelCodes},
				  success: function(data){
					     $('#amc_roomTypeCode').empty();
					     $('#amc_roomTypeCodeHidden').empty();
						
						 if(data.length > 0){ 
						     if(null==ms || ms.length < 1){
								 for(var i =0 ; i < data.length ; i++)
								 {
									 var roomTypeCode = data[i] ;
									 //保存所有房型代码
									 $('#amc_roomTypeCodeHidden').append('<option value="'+roomTypeCode+'" selected>'+roomTypeCode+'</option>');	  
									 
									 $('#amc_roomTypeCode').append('<option value="'+roomTypeCode+'">'+roomTypeCode+'</option>');	  
								 }
							 }else{
								 for(var i =0 ; i < data.length ; i++)
								 {
									 var roomTypeCode = data[i] ;
									 //保存所有房型代码
									 $('#amc_roomTypeCodeHidden').append('<option value="'+roomTypeCode+'" selected>'+roomTypeCode+'</option>');	  
									 
									 if(ms.indexOf(roomTypeCode) > 0){
										  $('#amc_roomTypeCode').append('<option value="'+roomTypeCode+'" selected>'+roomTypeCode+'</option>');	  
									 }else{
										  $('#amc_roomTypeCode').append('<option value="'+roomTypeCode+'">'+roomTypeCode+'</option>');	  
									 }
									 
								 }
						    }
						 }
						 $('#amc_roomTypeCode').multiselect('rebuild');
				  }
			});
		}

	}else{
		$('#amc_roomTypeCode').empty();
		$('#amc_roomTypeCode').multiselect('rebuild');
	}
}

function setRatePlanCode(){
	$.ajax({
		  type: "POST",
		  url: '/adsBeq_ajaxGetRatePlanCode.do',
		  data: {"hotelId":$("#amc_hotelId").val()},
		  success: function(resData){
			  var maps = new Array();
			  if(resData != null){
				  $.each(resData,function(key,val){
					  var map = new Object();
					  map.key = key;
					  map.value = val;
					  maps.push(map);
	              });
				  maps.sort(function (a,b){
						return a.value > b.value ;
				  });
			  }
			  var opt = "<option value=''></option>";
	       		$.each(maps,function(idx,map){
	       			opt += '<option value="'+map.key+'">'+ map.value +'</option>';
	           	});
				$("#amc_roomTypeCode").html(opt);
		  }
	});
}
jQuery.extend(jQuery.validator.messages, {
	required : "<fmt:message key='common.RequiredField'/>"
});
//-->
</script>

<form action="/adsBeq_searchLog.do" id="adsForm" name="adsForm" method="post">
   <div class="title_wp">
		<fmt:message key="ccm.MessagePushLog"/>
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.Channel.ChannelCode"/>：
						</span>
					</div>
					<div class="i_input" style="position:relative;">
						<input id="channelCodeHi" value="${amc.channelCodeList }" type="hidden"/>
						<select id="amc_channelCode" name="amc.channelCodeList" class="fxt w120" multiple="multiple">
							<c:forEach items="${channelList}" var="channel">	
								<option value="${channel.channelCode}"
								 	${fn:contains(amc.channelCodeList, channel.channelCode)?"selected":""}
								>${channel.channelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="common.time.BeginDate"/>：</span>
					</div>
					<div class="i_input">
					<input name="amc.startDate" id="InputDateStart" type="text" value="<fmt:formatDate value="${amc.startDate}" pattern="yyyy-MM-dd HH:mm"/>" class="fxt w120 calendar"/>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="common.time.EndDate"/>：</span>
					</div>
					<div class="i_input">
					<input name="amc.endDate" id="InputDateEnd" type="text" value="<fmt:formatDate value="${amc.endDate}" pattern="yyyy-MM-dd HH:mm"/>" class="fxt w120 calendar"/>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.BasicConfiguration.ChainCode"/>：
						</span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="amc_chainCode" name="amc.chainCodeList" class="fxt w120" multiple="multiple" onchange="setHotelCode(null);">
							<c:forEach items="${chainList}" var="chain">	
								<option value="${chain.chainCode}"
								 	${fn:contains(amc.chainCodeList, chain.chainCode)?"selected":""}
								>${chain.chainCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/>：
						</span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="amc_hotelCode" name="amc.hotelCodeList" class="fxt w120" multiple="multiple" onchange="setRoomTypeCode(null);">
							<!-- 
							<c:forEach items="${hotelList}" var="hotel">	
								<option value="${hotel.hotelCode}"
								 	${fn:contains(amc.hotelCodeList, hotel.hotelCode)?"selected":""}
								>${hotel.hotelCode}</option>
							</c:forEach>
							 -->
						</select>
					</div>
				</li>
				<!-- 房型代码 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.Channel.RoomTypeCode"/>：
						</span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="amc_roomTypeCode" name="amc.roomTypeCodeList" class="fxt w120" multiple="multiple" onclick="setRoomTypeCode(null);">	
							<c:forEach items="${roomTypeCodeListHidden}" var="roomTypeCode">	
								<option value="${roomTypeCode}"
								 	${fn:contains(amc.roomTypeCodeList, roomTypeCode)?"selected":""}
								>${roomTypeCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<!-- 房型代码 隐藏 -->	
				<li style="display: none;">
					<select id="amc_roomTypeCodeHidden" name="amc.roomTypeCodeListHidden" multiple="multiple">	
					   <!-- 
						<c:forEach items="${roomTypeCodeList}" var="roomTypeCode">	
							<option value="${roomTypeCode}" selected>${roomTypeCode}</option>
						</c:forEach>
						 -->
					</select>
				</li>	
					
				<!-- 房价代码 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.RestrictionsManagement.RateCode"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield name="amc.ratePlanCode" cssClass="fxt w120"></s:textfield>
					</div>
				</li>
				<!-- 消息类型 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.Channel.MessageType"/>：
						</span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="amc_adsType" name="amc.adsTypeList" class="fxt w120" multiple="multiple">
							<c:forEach items="${messageTypeList}" var="messageType">	
								<option value="${messageType}"
								 	${fn:contains(amc.adsTypeList, messageType)?"selected":""}
								>
								<c:if test="${messageType eq 'FIDELIO_HotelProductNotifRQ' }"><fmt:message key="ccm.MessagePushLog.HotelProductNotifRQ"/></c:if>
								<c:if test="${messageType eq 'FIDELIO_AvailabilityStatusRQ' }"><fmt:message key="ccm.MessagePushLog.Rooms"/></c:if>
								<c:if test="${messageType eq 'FIDELIO_RateUpdateNotifRQ' }"><fmt:message key="ccm.RestrictionsManagement.Rate"/></c:if>
								<c:if test="${messageType eq 'FIDELIO_AvailUpdateNotifRQ' }"><fmt:message key="ccm.Channel.AvailabilityRestriction"/></c:if>
								<c:if test="${messageType eq 'Switch_StayHistoryNotificationRQ' }">stayHistory</c:if>
								<c:if test="${messageType eq 'HOTELCODE' }"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/></c:if>
								<c:if test="${messageType eq 'ROOMCODE' }"><fmt:message key="ccm.Channel.RoomTypeCode"/></c:if>
								<c:if test="${messageType eq 'RATECODE' }"><fmt:message key="ccm.RestrictionsManagement.RateCode"/></c:if>
								<c:if test="${messageType eq 'OTAHotelResModify' }">OTAHotelResModify</c:if>
								
								</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<!-- 推送状态 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.MessagePushLog.PushStatus"/>：
						</span>
					</div>
					<div class="i_input" style="position:relative;">
						<select id="amc_status" name="amc.statusList" class="fxt w120" multiple="multiple">
							<c:forEach items="${pushStatusList}" var="status">	
								<option value="${status}"
								 	${fn:contains(amc.statusList, status)?"selected":""}
								>
								<c:if test="${status eq '0' }"><fmt:message key="ccm.MessagePushLog.Waiting"/></c:if>
								<c:if test="${status eq '1' }"><fmt:message key="ccm.MessagePushLog.SentSuccessfully"/></c:if>
								<c:if test="${status eq '9' }"><fmt:message key="ccm.MessagePushLog.FailedToSent"/></c:if>
								</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title">
						<span class="text">EchoToken：</span>
					</div>
					<div class="i_input">
						<s:textfield name="amc.echoToken" cssClass="fxt w120"></s:textfield>
					</div>
				</li>
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<!--  <a class="out greylink" href="#" id="exportCustom">导出订单</a>(每次最多只能导出1000条记录)&nbsp;-->
				<button type="button" class="btn_2 black mgR12" onclick="javascript:subForm();"><fmt:message key="common.button.searchSelect"/></button>
				<button type="button" class="btn_2 white" onclick="javascript:exportLog();"><fmt:message key="common.button.File"/></button>
				<button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
			</div>
		</div>
	</div>
 </form>
   
  <div class="bt_wp">
	<form id="displayForm" name="displayForm" method="post" action="">
    <display:table name="adsResult.resultList" form="displayForm" id="adsMsg" class="ccm_table1 adslogXXX"  requestURI="" pagesize="${amc.pageSize }" partialList="true" size="adsResult.totalCount" style="width:100%">
		<%--    
		<display:column title="" class="td_br_solid" media="html"><a href="javascript:sendToTb('${adsMsg.adsId}');">推送</a></display:column> 
		--%>
		<display:column titleKey="ccm.Receivingstate" class="td_br_solid" >
			<c:if test="${adsMsg.status eq '0' }"><fmt:message key="ccm.MessagePushLog.Waiting"/></c:if>
			<c:if test="${adsMsg.status eq '1' }"><fmt:message key="ccm.MessagePushLog.SentSuccessfully"/></c:if>
			<c:if test="${adsMsg.status eq '9' }"><fmt:message key="ccm.MessagePushLog.FailedToSent"/></c:if>
		</display:column>
		<display:column property="targetGDS" titleKey="ccm.Channel.ChannelCode" class="td_br_solid"/>
		<display:column property="chainCode" titleKey="ccm.BasicConfiguration.ChainCode" class="td_br_solid"/>
		<display:column property="hotelCode" titleKey="ccm.ReservationMonitorReport.PropertyCode" class="td_br_solid"/>
		<c:if test="${!adsMsg.adsType eq 'FIDELIO_HotelProductNotifRQ' }">
		<display:column property="roomTypeCode" titleKey="ccm.Channel.RoomTypeCode" class="td_br_solid"/>
		<display:column property="ratePlanCode" titleKey="ccm.RestrictionsManagement.RateCode" class="td_br_solid"/>
		</c:if>
		<display:column property="adsType"  titleKey="ccm.MessagePushLog.ProtocolType" class="td_br_solid"/>
		<display:column property="echoToken" title="EchoToken" class="td_br_solid"/>
		<display:column  titleKey="ccm.MessagePushLog.MessageContent" media="html" class="td_br_solid">
			<a href="javascript:openwindow('/adsBeq_getAdsMessageField.do?adsId=${adsMsg.adsId}&hotelCode=${adsMsg.hotelCode }&status=${adsMsg.status }&field=content','',1060,500);">
				<fmt:message key="common.View"/>
			</a>
			</display:column>
		<display:column  titleKey="ccm.RestrictionsManagement.InformationDetails" class="td_br_solid" maxLength="25">
			<a href="javascript:openwindow('/adsBeq_getAdsMessageField.do?adsId=${adsMsg.adsId}&hotelCode=${adsMsg.hotelCode }&status=${adsMsg.status }&field=actionValue','',1060,500);">
				<fmt:message key="common.View"/>
			</a>
		</display:column>
		<display:column titleKey="ccm.MessagePushLog.ExceptionReason" class="td_br_solid"  maxLength="25">
			${adsMsg.errMsg}
		</display:column>
		<display:column property="createdTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"  titleKey="ccm.MessagePushLog.MessageRecordingTime" class="td_br_solid"/>
		<display:column  title="" class="td_br_solid" maxLength="25">
			<c:if test="${adsMsg.adsType eq 'FIDELIO_HotelProductNotifRQ' }">${adsMsg.rquestTbData }</c:if>
		</display:column>
	</display:table>
	</form>
</div>

</div>

<script type="text/javascript">
//已选中的值
var optionVal=$("#amc_channelCode").val()+'';
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
		$("#amc_channelCode").find("option[value="+vo+"]").removeAttr("selected");
	}
}  
</script>