<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
 <div class="title_wp"><fmt:message key="ccm.HotelPromotions.HotelPromotionsList"/>
          <div class="bt"> 
          	<a href="/specialOffer_toEdit.do" class="btn_2 blue"><fmt:message key="common.button.New"/></a> 
          </div>
        </div>
        <div class="c_whitebg"> 
          <!--搜索项-->
          <s:form action="/specialOffer_list.do" method="post">
          <div class="nm_box "> 
          	<ul class="inq_wp frow">
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="common.time.BeginDate"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_beginTime" cssClass="fxt w150" name="creteria.beginTime" ></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="common.time.EndDate"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_endTime" cssClass="fxt w150" name="creteria.endTime" ></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.HotelPromotions.Overview"/>：</span></div>
	                <div class="i_input">
	                   <s:textfield id="l_summary" name="creteria.summary" cssClass="fxt w180 required" ></s:textfield>
	                </div>
                </li>
            </ul>
            <hr class="solided notopMargin">
            <div class="center">
	            <button type="submit" class="btn_2 black mgR12" onclick="javascript:return query();"><fmt:message key="common.button.searchSelect"/>	</button>
	            <button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
            </div>
          </div>
          </s:form>
          
          <!--列表样式-->
          <div class="bt_wp">
			<display:table name="result.resultList" id="specialOfferList" class="ccm_table1" requestURI="" pagesize="20" size="result.totalCount" partialList="true">
				<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
				<display:column property="beginTime" sortable="true" titleKey="common.time.BeginDate" format="{0,date,yyyy-MM-dd}" headerClass="sorted"/>
				<display:column property="endTime" sortable="true" titleKey="common.time.EndDate" format="{0,date,yyyy-MM-dd}" headerClass="sorted"/>
				<display:column property="summary" sortable="true" titleKey="ccm.HotelPromotions.Overview" headerClass="sorted" style="width:400px;"/>
				<display:column titleKey="common.button.Activity">
					<a href="/specialOffer_toEdit.do?specialOfferVO.specialOfferId=${specialOfferList.specialOfferId}" class="link mgR12"><fmt:message key="common.button.edit"/></a>
		            <a href="javascript:;" specialOfferId=${specialOfferList.specialOfferId } class="link del_ifself"><fmt:message key="common.button.delete"/></a>
				</display:column>
			</display:table>
	 	  </div>
      </div>

<script>

$(document).ready(function() {
	
	/*是否删除本条 setHotelIdForHref */
	$('.del_ifself').bind('click',function(){
		var specialOfferId = $(this).attr('specialOfferId');
		var r=confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>?");
		if (r==true){
			window.location.href=setHotelIdForHref(
					"/specialOffer_delete.do?specialOfferVO.specialOfferId="+specialOfferId);
		}
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
			              ]
		}
	var start = $("#l_beginTime");
	var end = $("#l_endTime");
	start.datepicker($.extend(dpconfig, {
		onClose : function(v) {
			end.datepicker("option", "minDate", v);
		},
		onSelect : function(selectedDateTime) {
			end.datetimepicker('option', 'minDate', start.datetimepicker('getDate'));
		}
	}));

	end.datepicker($.extend(dpconfig, {
		onClose : function(v) {
			start.datepicker("option", "maxDate", v);
		},
		onSelect : function(selectedDateTime) {
			start.datetimepicker('option', 'maxDate', end.datetimepicker('getDate'));
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
	//开始和结束时间校验
	var beginTime = $("#l_beginTime");
	var end = $("#l_endTime");
	
	if(!strIsNull(beginTime)){
		var beginTimeCode = validateYYYYMMDD(beginTime);
		if(beginTimeCode!='success'){
			alert(getErrorMsg(beginTimeCode,'<fmt:message key="common.time.BeginDate"/>','yyyy-MM-DD'));
			return false;
		}
	}
	
	
	//下线时间校验
	var endTime = $("#endTime").val();
	var endTimeCode = validateYYYYMMDD(endTime);
	if(endTimeCode!='success'){
		alert(getErrorMsg(endTimeCode,'<fmt:message key="common.time.EndDate"/>','yyyy-MM-DD'));
		return false;
	}else if(isMorethanNow(endTime)=='false'){
		alert('结束时间不能小于当前时间');
		return false;
	}
	return true;
}
</script>