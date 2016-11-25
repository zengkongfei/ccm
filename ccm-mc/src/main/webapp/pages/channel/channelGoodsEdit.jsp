<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<script src="<c:url value='/js/main.js'/>"></script>
<s:if test='#request.flag=="no"'><%@ include file="/common/messages.jsp"%>
<div style="text-align: center;"><button type="button" style="vertical-align: middle;" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button></div></s:if>
<s:else>
<div class="pp_main">
	<s:form id="channelGoodsForm" method="post">
		<s:hidden key="channelgoods.channelGoodsId"></s:hidden>
		<s:hidden key="channelgoods.status"></s:hidden>
		<div class="t_title"><fmt:message key="ccm.Channel.BindChannel"/></div>
		<div class="pdA24">
			<div class="mgB18">
				<div class="mgB6">
					<div class="fm_bwp inline w240">
						<div class="title yahei"><fmt:message key="ccm.InventoryManagement.Channels"/></div>
					</div>
					<div class="fm_bwp inline w240">
						<div class="title yahei"><fmt:message key="ccm.RestrictionsManagement.RateCodeDescription"/></div>
					</div>
					<div class="fm_bwp inline w240">
						<div class="title yahei"><fmt:message key="ccm.InventoryManagement.RoomTypes"/></div>
					</div>
				</div>
				<div class="fm_bwp inline w240" style="vertical-align: top;">
					<div class="sel_ele">
						<span class="select_all"><fmt:message key="common.select.selectAll"/></span><span class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
					</div>
					<div class="fm_box">
						<s:iterator value="#request.cList">
							<label class="checkbox"> <input type="checkbox" class="tempcls" <s:if test="channelId==channelgoods.channelId">checked="checked"</s:if> value="${channelId}" name="channelIds"><span class="">${channelCode}</span> </label>
						</s:iterator>
					</div>
				</div>
				<div class="fm_bwp inline w240" style="vertical-align:top;">
					<div class="sel_ele hidden">
						<span class="select_all"><fmt:message key="common.select.selectAll"/></span><span class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
					</div>
					<div class="fm_box">
						<s:iterator value="#request.ratePan">
							<label class="checkbox"><input type="radio" name="rateplanid" <s:if test="ratePlanId==channelgoods.rateplanid">checked="checked"</s:if> class="tempcls" value="${ratePlanId}"><span>${ratePlanCode}&nbsp;${ratePlanName}</span> </label>
						</s:iterator>
					</div>
				</div>
				<div class="fm_bwp inline w240" style="vertical-align:top;">
					<div class="sel_ele">
						<span class="select_all"><fmt:message key="common.select.selectAll"/></span><span class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
					</div>
					<div class="fm_box" id="rtList"><jsp:include page="channelRoomType.jsp"/></div>
				</div>
			</div>

			<div class="pdT18 pdB18">
				<hr class="dashed">
			</div>

			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class=""></span><span class="text"><fmt:message key="ccm.Channel.ActiveTime"/>：</span>
					</div>
					<div class="i_input">
						<label class="radio inline"> 
						<input type="radio" name="isActiveImmediately" id="optionsRadios_a1" value="true" <s:if test="channelgoods.isActiveImmediately">checked="checked"</s:if> <s:if test="channelGoodsId==null">checked="checked"</s:if>> 
						<span class="checked"><fmt:message key="ccm.Channel.Active"/></span> </label> <label class="radio inline"> 
						<input type="radio" name="isActiveImmediately" id="optionsRadios_a2" <s:if test="!channelgoods.isActiveImmediately">checked="checked"</s:if> value="false"> 
						<span class=""><fmt:message key="common.month.monthCustom"/></span> </label> <span class="customTime" style="display: none;"> 
						<input class="fxt w110" name="channelgoods.effectiveDate" type="text" value="<s:date name="channelgoods.effectiveDate" format="yyyy-MM-dd HH:mm"/>" id="start" placeholder="<fmt:message key="common.time.BeginDate"/>"> 
						<span>-</span> 
						<input class="fxt w110" id="end" type="text" value="<s:date name="channelgoods.expireDate" format="yyyy-MM-dd HH:mm"/>" name="channelgoods.expireDate" placeholder="<fmt:message key="common.time.EndDate"/>"> </span>
					</div>
				</li>
			</ul>
		</div>
		<div class="b_crl">
			<button type="button" class="btn_2 green mgR6" id="sm" onclick="dosubmit(this)"><fmt:message key="common.button.save"/>	</button>
			<button type="button" class="btn_2 white popup-close" onclick="refreshC();"><fmt:message key="common.button.close"/></button>
		</div>
	</s:form>
</div>
<script type="text/javascript" src="/js/datepicker.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				
				if ($("#optionsRadios_a2").is(':checked')) {
					$('.customTime').show();
				} 

				$('#optionsRadios_a1').change(function() {
					if ($(this).is(':checked')) {
						$('.customTime').hide();
					}
				});

				$('#optionsRadios_a2').change(function() {
					if ($(this).is(':checked')) {
						$('.customTime').show();
					}
				});

				$('input[type=radio][name=rateplanid]').change(
						function() {
							$('.changeFromRate').removeClass('invisible');
							$('#rtList').load(
									'channelGoods_channelRoomType.do?channelgoods.rateplanid='
											+ $(this).val());
						});

				RadioCheckedName('isActiveImmediately');
				RadioCheckedName('rateplanid');

				$('#One_click').bind('click', function() {
					$('#One_show').slideDown();
				});
				$('#Two_click').bind('click', function() {
					$('#Two_show').slideDown();
				});

				$('#One_show .closethis').bind('click', function() {
					$('#One_show').hide();
				});
				$('#Two_show .closethis').bind('click', function() {
					$('#Two_show').hide();
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
										'<fmt:message key="month.january"/>', 
										'<fmt:message key="month.february"/>', 
										'<fmt:message key="month.march"/>', 
										'<fmt:message key="month.april"/>', 
										'<fmt:message key="month.may"/>', 
										'<fmt:message key="month.june"/>', 
										'<fmt:message key="month.july"/>', 
										'<fmt:message key="month.august"/>',
										'<fmt:message key="month.september"/>', 
										'<fmt:message key="month.october"/>', 
										'<fmt:message key="month.november"/>', 
										'<fmt:message key="month.december"/>' 
						              ],
					hourText:'<fmt:message key="time.Hour"/>',
					minuteText:'<fmt:message key="time.Minute"/>',
					timeText:'<fmt:message key="time.Time"/>',
					currentText:'<fmt:message key="time.Present"/>',
					closeText:'<fmt:message key="common.button.close"/>'
				}
				var start=$("#start");
				var end=$("#end");
				start.datetimepicker($.extend(dpconfig,{
					minDate:new Date(),
					onClose: function(dateText, inst) {
						if (end.val() != '') {
							var testStartDate = start.datetimepicker('getDate');
							var testEndDate = end.datetimepicker('getDate');
							if (testStartDate > testEndDate)
								end.datetimepicker('setDate', testStartDate);
						}
						else {
							end.val(dateText);
						}
					},
					onSelect: function (selectedDateTime){
						end.datetimepicker('option', 'minDate', start.datetimepicker('getDate') );
					}
				}));

				end.datetimepicker($.extend(dpconfig,{
					minDate:new Date(),
					onClose: function(dateText, inst) {
						if (start.val() != '') {
							var testStartDate = start.datetimepicker('getDate');
							var testEndDate = end.datetimepicker('getDate');
							if (testStartDate > testEndDate)
								start.datetimepicker('setDate', testEndDate);
						}
						else {
							start.val(dateText);
						}
					},
					onSelect: function (selectedDateTime){
						start.datetimepicker('option', 'maxDate', end.datetimepicker('getDate') );
					}
				}));
				
			});
	
	var v;
	function dosubmit(t) {
		if (!$("[name='channelIds']").is(':checked')
				|| !$("[name='rateplanid']").is(':checked')
				|| !$("[name='roomTypeIds']").is(':checked')) {
			alert('<fmt:message key="ccm.Channel.message.BindingMessage"/>');
			return false;
		} else if (!$("[name='isActiveImmediately']").is(':checked')) {
			alert('<fmt:message key="ccm.Channel.message.PleaseActiveMessage"/>');
			return false;
		} else if ($("[name='isActiveImmediately']:checked").val()=='false' && ($('#start').val()=='' || $('#end').val()=='')) {
			alert('<fmt:message key="ccm.Channel.message.CustomTime"/>');
			return false;
		}else if ($("[name='isActiveImmediately']:checked").val()=='false') {
			
			//校验自定义激活开始时间
			var start = $('#start').val();
			var startCode = validateYYYYMMDDHHmm(start);
			if(startCode!='success'){
				alert(getErrorMsg(startCode,'<fmt:message key="ccm.Channel.message.ActiveBeginDate"/>','yyyy-MM-DD HH:mm'));
				return false;
			}else if(isMorethanNow(start)=='false'){
				alert('<fmt:message key="ccm.CurrentTimeError001"/>');
				return false;
			}
			
			//校验自定义激活结束时间
			var end = $('#end').val();
			var endCode = validateYYYYMMDDHHmm(end);
			if(endCode!='success'){
				alert(getErrorMsg(endCode,'<fmt:message key="ccm.ActivationEndTime"/>','yyyy-MM-DD HH:mm'));
				return false;
			}else if(isMorethanNow(end)=='false'){
				alert('<fmt:message key="ccm.CurrentTimeError002"/>');
				return false;
			}
		} 
		
		//保存	    
		$.ajax({
			url : '/channelGoods_save.do',
			beforeSend : function() {
				$('#sm').addClass('no_ald');
				$('#sm').attr('disabled', 'disabled');
			},
			cache : false,
			data : $('#channelGoodsForm').serialize(),
			dataType : "text",
			success : function(data) {
				if(data=="success"){
					v = 1;
					refreshC();
				}
				else if(data=="error"){
					alert('<fmt:message key="ccm.Channel.message.RepeatedBinding"/>');
					$('#sm').removeClass('no_ald');
					$('#sm').removeAttr('disabled');
				}else if(data=="priceCal"){
					alert('<fmt:message key="ccm.Channel.message.RateUpdateFailure"/>');
					$('#sm').removeClass('no_ald');
					$('#sm').removeAttr('disabled');
				}else{
					alert(data);
					$('#sm').removeClass('no_ald');
					$('#sm').removeAttr('disabled');
				}
			}
		});	   
	}
	function refreshC(){
		if(v == 1){
			location.reload();
		}
	}
</script>
</s:else>