<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="title_wp"><fmt:message key="ccm.Channel.EditChannel"/></div>
<%@ include file="/common/messages.jsp"%>
<s:form id="channelForm" action="" method="post">
<appfuse:ccmToken name="token"></appfuse:ccmToken>
	<s:hidden key="channel.channelId"></s:hidden>
	<div class="c_whitebg pdA12">
		<div class="mgB24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.Channel.ChannelCode"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield key="channel.channelCode" maxlength="10" cssClass="fxt w120 required"></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.Channel.OnlineTime"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="start" cssClass="fxt w120 required" key="channel.addTime" ></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.Channel.OfflineTime"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="end" cssClass="fxt w120 required" key="channel.delTime" ></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="common.AvailableorNot"/>：</span>
					</div>
					<div class="i_input">
						<label class="radio inline"> <input type="radio" name="channel.isEnabled" id="optionsRadios3" value="true" <s:if test="channel.isEnabled">checked="checked"</s:if> <s:if test="channel.channelId==null">checked="checked"</s:if>> <span class=""><fmt:message key="common.Yes"/></span> </label> <label class="radio inline"> <input type="radio" name="channel.isEnabled" id="optionsRadios4" value="false" <s:if test="!channel.isEnabled">checked="checked"</s:if>> <span class=""><fmt:message key="common.Not"/></span> </label>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="star"></span><span class="text"><fmt:message key="ccm.Channel.RateTypeCheck"/>：</span>
					</div>
					<div class="i_input">
						<label class="radio inline"> 
						<input type="radio" class="required" name="channel.validRoomPrice" id="optionsRadios４" value="1" <s:if test="channel.validRoomPrice==1">checked="checked"</s:if>> 
						<span class=""><fmt:message key="ccm.Channel.RateCheck"/></span> 
						</label> 
						<label class="radio inline"> 
						<input type="radio" class="required" name="channel.validRoomPrice" id="optionsRadios5" value="2" <s:if test="channel.validRoomPrice==2">checked="checked"</s:if>> 
						<span class=""><fmt:message key="ccm.Channel.ChannelRate"/></span> 
						</label>
						<label class="radio inline"> 
						<input type="radio" class="required" name="channel.validRoomPrice" id="optionsRadios6" value="3" <s:if test="channel.validRoomPrice==3">checked="checked"</s:if>> 
						<span class=""><fmt:message key="ccm.Channel.CCMRate"/></span> 
						</label>
					</div>
				</li>
				<!-- start of OTA Receive -->
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.ReceiveorNot"/>：</span>
					</div>
					<div class="i_input">
						<span class="checkbox inline"><input type="checkbox" id="isReceive" ${channel.receiveAddress != null &&channel.receiveAddress !=''  ? 'checked="checked"' : '' } onclick="receive(this);"></span>
					</div>
				</li>
				<div id="isReceiveDiv">
				<li>
					<div class="i_title">
						<span class="star"></span><span class=""></span><span class="text"><fmt:message key="ccm.Channel.OTAReceiveAddress"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="receiveAddress" cssClass="fxt w180 required" key="channel.receiveAddress"></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.OTAReceiveType"/>：</span>
					</div>
					<div class="i_input">
						<label class="checkbox inline"> 
						<input type="checkbox"  name="channel.receiveBlock" id="optionsRadios8" value="true" <s:if test="channel.receiveBlock">checked="checked"</s:if>> 
						<span class=""><fmt:message key="ccm.Channel.HotelBlock"/></span> 
						</label>
						<!-- <label class="checkbox inline"> 
						<input type="checkbox"  name="channel.receiveRavl" id="optionsRadios9" value="true" <s:if test="channel.receiveRavl">checked="checked"</s:if>> 
						<span class=""><fmt:message key="ccm.Channel.HotelInventory"/></span> 
						</label> 
						<label class="checkbox inline"> 
						<input type="checkbox" name="channel.receiveRate" id="optionsRadios10" value="true" <s:if test="channel.receiveRate">checked="checked"</s:if>> 
						<span class=""><fmt:message key="ccm.Channel.RatePlan"/></span> 
						</label>
						<label class="checkbox inline"> 
						<input type="checkbox" name="channel.receiveRtav" id="optionsRadios11" value="true" <s:if test="channel.receiveRtav">checked="checked"</s:if>> 
						<span class=""><fmt:message key="ccm.Channel.Restriction"/></span> 
						</label>
						<label class="checkbox inline"> 
						<input type="checkbox" name="channel.receiveRstu" id="optionsRadios12" value="true" <s:if test="channel.receiveRstu">checked="checked"</s:if>> 
						<span class=""><fmt:message key="common.RoomInventory"/></span>
						</label>-->
					</div>
				</li>
				</div>
				<!-- end of OTA Receive -->
				
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.PushorNot"/>：</span>
					</div>
					<div class="i_input">
						<span class="checkbox inline"><input type="checkbox" id="isPush" ${channel.pushAddress != null &&channel.pushAddress !=''  ? 'checked="checked"' : '' } onclick="push(this);"></span>
					</div>
				</li>
				<div id="isPushDiv">
				<li>
					<div class="i_title">
						<span class="star"></span><span class=""></span><span class="text"><fmt:message key="ccm.Channel.ADSPushAddress"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="pushAddress" cssClass="fxt w180 required" key="channel.pushAddress"></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.ADSPushType"/>：</span>
					</div>
					<div class="i_input">
						<label class="checkbox inline"> 
						<input type="checkbox"  name="channel.pushRavl" id="optionsRadios4" value="true" <s:if test="channel.pushRavl">checked="checked"</s:if>> 
						<span class=""><fmt:message key="ccm.Channel.HotelInventory"/></span> 
						</label> 
						<label class="checkbox inline"> 
						<input type="checkbox" name="channel.pushRate" id="optionsRadios5" value="true" <s:if test="channel.pushRate">checked="checked"</s:if>> 
						<span class=""><fmt:message key="ccm.Channel.RatePlan"/></span> 
						</label>
						<label class="checkbox inline"> 
						<input type="checkbox" name="channel.pushRtav" id="optionsRadios6" value="true" <s:if test="channel.pushRtav">checked="checked"</s:if>> 
						<span class=""><fmt:message key="ccm.Channel.Restriction"/></span> 
						</label>
						<label class="checkbox inline"> 
						<input type="checkbox" name="channel.pushRstu" id="optionsRadios7" value="true" <s:if test="channel.pushRstu">checked="checked"</s:if>> 
						<span class=""><fmt:message key="common.RoomInventory"/></span>
						</label>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class="star"></span><span class=""></span><span class="text"><fmt:message key="ccm.Channel.MaxPushDays"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="maxPushDay" cssClass="fxt w120 required" key="channel.maxPushDay"></s:textfield>
					</div>
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<div class="i_title"> -->
<!-- 						<span class=""></span><span class="text"><fmt:message key="ccm.error.028"/>：</span> -->
<!-- 					</div> -->
<!-- 					<div class="i_input"> -->
<%-- 						<input type="text" id="pushTime" cssClass="fxt w120" key="channel.pushTime" value="<s:date name="channel.pushTime" format="HH:mm:ss"/>" /> --%>
<!-- 					</div> -->
				</li>
				<li>
				<div class="i_title">
						<span></span><span class=""></span><span class="text"><fmt:message key="ccm.Channel.PushReservationCheckInMessageAddress"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="pushStayHistoryAddress" cssClass="fxt w180" key="channel.pushStayHistoryAddress"></s:textfield>
					</div>
				</li>
				</div>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.UseChannelReservationNumberornot"/>：</span>
					</div>
					<div class="i_input">
						<span class="checkbox inline"><s:checkbox key="channel.isChannelOrderId"/></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.DuplicateReservationCheck"/>：</span>
					</div>
					<div class="i_input">
						<span class="checkbox inline"><s:checkbox key="channel.verifyChannelOrderId"/><fmt:message key="ccm.Channel.ChannelReservationNumber"/></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.OVERRIDEorNot"/>：</span>
					</div>
					<div class="i_input">
						<span class="checkbox inline"><s:checkbox name="channel.isOverRide"/></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.HotelInventory"/>：</span>
					</div>
					<div class="i_input">
						<span class="checkbox inline"><s:checkbox name="channel.isRoomNumber"/></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Rates.GuaranteeType"/>：</span>
					</div>
					<div class="i_input">
						<span class="checkbox inline"><s:checkbox name="channel.isGuarantee"/></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.DefaultGuaranteeRules"/>：</span>
					</div>
					<div class="i_input">
						<s:select name="channel.guaranteeId" cssClass="fxt w120" list="#request.hgvoList" listKey="guaranteeId" listValue="guaranteeCode"></s:select>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Rates.CancellationType"/>：</span>
					</div>
					<div class="i_input">
						<span class="checkbox inline"><s:checkbox name="channel.isCancel"/></span>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Rates.isOTAChannelUrl"/>：</span>
					</div>
					<div class="i_input">
						<label class="checkbox inline"><s:checkbox key="channel.isOTA"/><fmt:message key="ccm.Channel.isOTA"/></label>
						<label class="checkbox inline"><s:checkbox key="channel.isChannelPushUrl"/><fmt:message key="ccm.Channel.isChannelPushUrl"/></label>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.PushAddress"/>：</span>
					</div>
					<div class="i_input">
						 <s:textfield key="channel.pushRateUrl" cssClass="fxt w240"/>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.PushType"/>：</span>
					</div>
					<div class="i_input">
						<label class="checkbox inline"><s:checkbox key="channel.pushHotel" onclick="pushRate();" /><fmt:message key="ccm.Channel.PropertyCodeContent"/></label>
						<label class="checkbox inline"><s:checkbox key="channel.pushRoom" onclick="pushRate();" /><fmt:message key="ccm.Channel.RoomTypeContent"/></label>
						<label class="checkbox inline"><s:checkbox key="channel.pushRateContent" onclick="pushRate();" /><fmt:message key="ccm.Channel.RateCodeContent"/></label>
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.useAmountAfterTax"/>:</span>
					</div>
					<div class="i_input">
						<label class="radio inline"> 
						<input type="radio" class="required" name="channel.useAmountAfterTax"  value="true" <s:if test="channel.useAmountAfterTax">checked="checked"</s:if> <s:if test="channel.useAmountAfterTax==null">checked="checked"</s:if>> <span class=""><fmt:message key="common.Yes"/></span> </label> <label class="radio inline"> <input type="radio" name="channel.useAmountAfterTax" value="false" <s:if test="!channel.useAmountAfterTax">checked="checked"</s:if>> <span class=""><fmt:message key="common.Not"/></span> </label>
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.allotNotificationEmail"/>:</span>
					</div>
					<div class="i_input">
						<s:textfield id="allotNotificationEmail" cssClass="fxt w180" key="channel.allotNotificationEmail"></s:textfield>
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.isPushClose"/>：</span>
					</div>
					<div class="i_input">
						<label class="radio inline"> 
						<input type="radio" name="channel.isPushClose" id="optionsRadios3" value="true" 
						<s:if test="channel.isPushClose">checked="checked"</s:if>
						 <s:if test="channel.channelId==null">checked="checked"</s:if>> <span class="">
						 <fmt:message key="common.Yes"/></span> </label> 
						 <label class="radio inline"> 
						 <input type="radio" name="channel.isPushClose" id="optionsRadios4" value="false" 
						 <s:if test="!channel.isPushClose">checked="checked"</s:if>> <span class="">
						 <fmt:message key="common.Not"/></span> 
						 </label>
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.channel.MaximumOrderPerMinute"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield key="channel.ordernNumberOfTimes" cssClass="fxt w240" maxLength="11" onkeyup="value=value.replace(/[^\d]/g,'');if(value==0) value=1;" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
					</div>
				</li>
				
				<li>
                <div class="i_title"><span class="text"><fmt:message key="ccm.channel.OutOfTheMonitorRange"/>：</span></div>
                <div class="i_input">
                  <input id="invalidTimeBegin" name="channel.invalidTimeBegin" cssClass="fxt w88 " maxlength="50" value="<fmt:formatDate value="${channel.invalidTimeBegin}" type="both" pattern="HH:mm"/>" />
                </div>
                <div class="i_input">
                  -<input id="invalidTimeEnd" name="channel.invalidTimeEnd" cssClass="fxt w88 " maxlength="50" value="<fmt:formatDate value="${channel.invalidTimeEnd}" type="both" pattern="HH:mm"/>" />
                </div>
              </li>
			</ul>
		</div>
		<hr class="dashed">
		<div class="listinputCtrl">
			<button type="button" class="btn_1 green mgR12 f_save" onclick="dosubmit()"><fmt:message key="common.button.OK"/></button>
			<a class="btn_1 white" href="/channel_list.do?menuId=${sessionScope.menuId}"><fmt:message key="common.Return"/></a>
		</div>
	</div>
</s:form>

<script>
	$(document).ready(function() {
		
		jQuery.extend(jQuery.validator.messages, {
			required : "<fmt:message key='common.RequiredField'/>"			
		});	
		
		var dpconfig = {
				showMonthAfterYear : true,
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
		var start = $("#start");
		var end = $("#end");
		start.datetimepicker($.extend(dpconfig,{
			minDate : new Date(),
			onClose : function(dateText, inst) {
				if (end.val() != '') {
					var testStartDate = start.datetimepicker('getDate');
					var testEndDate = end.datetimepicker('getDate');
					if (testStartDate > testEndDate){
						end.datetimepicker('setDate', testStartDate);
					}
				} else {
					end.val(testStartDate);
				}
			},
			onSelect : function(selectedDateTime) {
				end.datetimepicker($.extend(dpconfig,{minDate:start.datetimepicker('getDate')}));
			}
		}));

		end.datetimepicker($.extend(dpconfig,{
			minDate : new Date(),
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
				start.datetimepicker($.extend(dpconfig,{maxDate:end.datetimepicker('getDate')}));
			}
		}));
		
		var pushTime = $("#pushTime");
		pushTime.timepicker({
			showSecond: true,
			timeFormat: 'hh:mm:ss',
			hour:02
		});
		
		checkboxIsOverRide();
		$('#channelForm_channel_isOverRide').click(function(){
			checkboxIsOverRide();
			checkboxIsGuarantee();
		})
		checkboxIsGuarantee();
		$('#channelForm_channel_isGuarantee').click(function(){
			checkboxIsGuarantee();
		})
		
		$('#invalidTimeBegin').timepicker($.extend(dpconfig, {
			showSecond:false,timeFormat: 'HH:mm',minDate:'00:00:00'
		}));
		$('#invalidTimeEnd').timepicker($.extend(dpconfig, {
			showSecond:false,timeFormat: 'HH:mm',minDate:'00:00:00'
		}));
		
		pushRate();
		
	});
	
	//是否OVER RIDE
	function checkboxIsOverRide(){
		if ($('#channelForm_channel_isOverRide').is(":checked")) {
	        $('#channelForm_channel_isRoomNumber').parent().parent().parent().show();
	        $('#channelForm_channel_isGuarantee').parent().parent().parent().show();
	        $('#channelForm_channel_isCancel').parent().parent().parent().show();
	    }else{
	    	$('#channelForm_channel_isRoomNumber').attr('checked',false).parent().parent().parent().hide();
	        $('#channelForm_channel_isGuarantee').attr('checked',false).parent().parent().parent().hide();
	        $('#channelForm_channel_isCancel').attr('checked',false).parent().parent().parent().hide();
	    }
	}
	//担保规则
	function checkboxIsGuarantee(){
		if ($('#channelForm_channel_isGuarantee').is(":checked")) {
			$('#channelForm_channel_guaranteeId').parent().parent().show();
		}else{
			$("#channelForm_channel_guaranteeId option:first").prop("selected", 'selected');
			$('#channelForm_channel_guaranteeId').parent().parent().hide();
		}
	}

	function dosubmit(url) {
		$("#channelForm").effectiveAndValidate({

			rules : {

				'channel.channnelCode' : {
					required : true
				},
				'channel.addTime' : {
					required : true
				},
				'channel.delTime' : {
					required : true
				},
				'channel.maxPushDay' : {
					digits : true
				}
			},

			messages : {
				'channel.channnelCode' : {
					required : '<span class="infotext"><fmt:message key="ccm.error.085"/></span>'
				},
				'channel.addTime' : {
					required : '<span class="infotext"><fmt:message key="ccm.error.086"/></span>'
				},
				'channel.delTime' : {
					required : '<span class="infotext"><fmt:message key="ccm.error.087"/></span>'
				}
			},
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
		
		//上线时间校验
		var addTime = $("#start").val();
		var addTimeCode = validateYYYYMMDDHHmm(addTime);
		if(addTimeCode!='success'){
			alert(getErrorMsg(addTimeCode,'<fmt:message key="ccm.Channel.OnlineTime"/>','yyyy-MM-DD HH:mm'));
			return false;
		}else if(strIsNull('${channel.channelId}') && isMorethanNow(addTime)=='false'){
			alert('<fmt:message key="ccm.Channel.ErrorMessage.onlineTimeLessPresent"/>');
			return false;
		}
		
		//渠道代码长度校验，最长10个字符
		var channelCode = $("#channelForm").find("input[name='channel.channelCode']").val().trim();
		if(channelCode.length>10){
			alert('<fmt:message key="ccm.channel.error.01"/>');
			return false;
		}
		
		//下线时间校验
		var endTime = $("#end").val();
		var endTimeCode = validateYYYYMMDDHHmm(endTime);
		if(endTimeCode!='success'){
			alert(getErrorMsg(endTimeCode,'<fmt:message key="ccm.Channel.OfflineTime"/>','yyyy-MM-DD HH:mm'));
			return false;
		}else if(strIsNull('${channel.channelId}') && isMorethanNow(endTime)=='false'){
			alert('<fmt:message key="ccm.Channel.ErrorMessage.offlineTimeLessPresent"/>');
			return false;
		}

		//推送时间校验
		var pushTime = $("#pushTime").val();
		if(!strIsNull(pushTime)){
			var pushTimeCode = validateHHmmss(pushTime);
			if(pushTimeCode!='success'){
				alert(getErrorMsg(pushTimeCode,'<fmt:message key="ccm.error.028"/>','HH:mm:ss'));
				return false;
			}
		}
		
		//推送类型中  放量和房态只能二选一 
		var pushRavls = document.getElementsByName("channel.pushRavl");
		var pushRstus = document.getElementsByName("channel.pushRstu");
		if(pushRavls[0].checked&&pushRstus[0].checked){
			alert('<fmt:message key="ccm.error.029"/>.');
			return false;
		}
		
		if ($("#channelForm").valid()) {
			document.channelForm.action = '/channel_save.do';
			document.channelForm.submit();
			//禁止重复提交
			 $('.f_save').addClass('no_ald');
			 $('.f_save').attr("disabled","disabled");	
		}
	}
	
	function push(thi){
		if($(thi).is(':checked')){
			$("#isPushDiv").show();
			$("#maxPushDay").addClass("required");
			$("#pushAddress").addClass("required");
		}else{ 
			$("#maxPushDay").removeClass("required");
			$("#pushAddress").removeClass("required");
			$("#isPushDiv :input[type=text]").val("");
			$("#isPushDiv :input[type=checkbox]").prop("checked","");
			$("#isPushDiv").hide();
		}
	}
	push($("#isPush"));
	
	function receive(thi){
		if($(thi).is(':checked')){
			$("#isReceiveDiv").show();
			$("#maxPushDay").addClass("required");
			$("#receiveAddress").addClass("required");
		}else{ 
			$("#receiveAddress").removeClass("required");
			$("#isReceiveDiv :input[type=text]").val("");
			$("#isReceiveDiv :input[type=checkbox]").prop("checked","");
			$("#isReceiveDiv").hide();
		}
	}
	receive($("#isReceive"));
	
	function pushRate(){
		if ($('#channelForm_channel_pushRateContent').is(":checked")) {
			$('#channelForm_channel_pushRateUrl').addClass("required");
		}else{
			$('#channelForm_channel_pushRateUrl').removeClass("required");
		}
	}
</script>