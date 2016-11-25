<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
 <div class="title_wp"><fmt:message key="ccm.Channel.ChannelList"/>
          <div class="bt"> 
          	<a href="/channel_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          
          <!--搜索项-->
          <s:form action="/channel_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow">
            	<li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.Channel.ChannelCode"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_sourceCode" name="creteria.channelCode" cssClass="fxt w120"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.Channel.OnlineTime"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_effectiveDate" cssClass="fxt w120" name="creteria.addTime" ></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.Channel.OfflineTime"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_expireDate" cssClass="fxt w120" name="creteria.delTime" ></s:textfield>
	                </div>
                </li>
            </ul>
            <hr class="solided notopMargin">
            <div class="center">
	            <button type="submit" class="btn_2 black mgR12" onclick="javascript:return query();"><fmt:message key="common.button.searchSelect"/></button>
	            <button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
            </div>
          </div>
          </s:form>
          
          <!--列表样式-->
          <div class="bt_wp">
		<display:table name="result.resultList" id="channelList" class="ccm_table1" requestURI="" pagesize="20" size="result.totalCount" partialList="true">

				<display:column property="channelCode" sortable="true" titleKey="ccm.Channel.ChannelCode" headerClass="sorted"/>
				<display:column property="addTime" sortable="true" titleKey="ccm.Channel.OnlineTime" format="{0,date,yyyy-MM-dd HH:mm}" headerClass="sorted"/>
				<display:column property="delTime" sortable="true" titleKey="ccm.Channel.OfflineTime" format="{0,date,yyyy-MM-dd HH:mm}" headerClass="sorted"/>
				<display:column sortable="true" titleKey="common.AvailableorNot" headerClass="sorted">
					<c:if test="${channelList.isEnabled==true}">
						<fmt:message key="common.Yes"/>
					</c:if>
					<c:if test="${channelList.isEnabled==false}">
						<fmt:message key="common.Not"/>
					</c:if>
				</display:column>
				<display:column titleKey="ccm.channelHotelConfig.ChannelHotelConfig">
					<a href="/channel_channelHotelConfig.do?channel.channelId=${channelList.channelId}" class="link mgR12"><fmt:message key="common.button.edit"/>	
					</a>
				</display:column>
				<display:column titleKey="ccm.UserActivityLog.Action">
					<a href="/channel_toEdit.do?channel.channelId=${channelList.channelId}" class="link mgR12"><fmt:message key="common.button.edit"/>	</a>
		            <a href="javascript:;" channelId=${channelList.channelId} class="link del_ifself"><fmt:message key="common.button.delete"/></a>
				</display:column>
			</display:table>
	 </div>
        </div>

<script>
$(document).ready(function() {
	
	/*是否删除本条 setHotelIdForHref */
	$('.del_ifself').bind('click',function(){
		var channelId = $(this).attr('channelId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
		if (r==true){
			window.location.href=setHotelIdForHref("/channel_delete.do?channel.channelId="+channelId);
		}
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
	var start=$("#l_effectiveDate");
	var end=$("#l_expireDate");
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


/*是否删除本条*/
/*
function delUser(url) {
	var r = confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
	if (r == true) {
		location.href = url;
	}
	
}
*/

//查询
function query(){
	//上线时间
	var effectiveDate = $('#l_effectiveDate').val();
	if(!strIsNull(effectiveDate)){
		var effectiveCode = validateYYYYMMDDHHmm(effectiveDate);
		if(effectiveCode!='success'){
			alert(getErrorMsg(effectiveCode,'<fmt:message key="ccm.Channel.OnlineTime"/>','yyyy-MM-DD HH:mm'));
			return false;
		}else if(isMorethanNow(effectiveDate)=='false'){
			alert('<fmt:message key="ccm.Channel.ErrorMessage.onlineTimeLessPresent"/>');
			return false;
		}
	}

	//下线时间
	var expireDate = $('#l_expireDate').val();
	if(!strIsNull(expireDate)){
		var expireCode = validateYYYYMMDDHHmm(expireDate);
		if(expireCode!='success'){
			alert(getErrorMsg(expireCode,'<fmt:message key="ccm.Channel.OnlineTime"/>','yyyy-MM-DD HH:mm'));
			return false;
		}else if(isMorethanNow(expireDate)=='false'){
			alert('<fmt:message key="ccm.Channel.ErrorMessage.offlineTimeLessPresent"/>');
			return false;
		}
	}
	
	return true;
}
</script>
