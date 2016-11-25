<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<div class="title_wp"><fmt:message key="ccm.UserActivityLog" /></div>
        <div class="c_whitebg"> 
          <!--搜索项-->
          <s:form action="/log_list.do" method="post">
          <div class="nm_box"> 
          	<ul class="inq_wp frow"><%@ include file="/common/messages.jsp"%>
          	
            	<li class="col3_1">
	                <div class="i_title">
	                	<span class="text"><fmt:message key="ccm.UserActivityLog.UserName"/>：</span>
	                </div>
	                <div id="operaterIds_show" class="i_input" style="position:relative;">
					  <select id="l_operaterId" class="fxt w120" name="criteria.operaterIds" multiple="multiple">
					  	<c:forEach items="${userList}" var="user">
						  	<option value="${user.userId}"  	  
						  		${fn:contains(criteria.operaterIds, user.userId)?"selected":""}
						  	>${user.username}</option>
					  	</c:forEach>
					  </select>
	                </div>
                </li>
                <!-- 一级菜单 -->
                <li class="col3_1">
	                <div class="i_title">
	                	<span class="text"><fmt:message key="common.First-LevelMenu"/>：</span>
	                </div>
	                <div id="businessIds_show" class="i_input" style="position:relative;">
					  <select id="l_businessId" class="fxt w120" onchange="changeBusinessId();" name="criteria.businessIds" multiple="multiple">
					  	<c:forEach items="${topMenuList }" var="menu">
						  	<option value="${menu.menuId}"  
						  		<c:if test="${menu.menuId==criteria.businessId}">checked="checked"</c:if>
						  	>${menu.displayName}</option>
					  	</c:forEach>
					  </select>
	                </div>
                </li>
                <!-- 二级菜单 -->
                <li class="col3_1">
	                <div class="i_title">
	                	<span class="text"><fmt:message key="common.Second-LevelMenu"/>：</span>
	                </div>
	                <div id="functionIds_show" class="i_input" style="position:relative;">
					  <select id="l_functionId" name="criteria.functionIds" functionId="${criteria.functionId}" class="fxt w120" multiple="multiple">
							
					  </select>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/>：</span></div>
	                <div class="i_input" style="position:relative;">
	                	<select id="soc_hotelId" name="criteria.hotelIds" class="fxt w120" multiple="multiple">
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelId}"
									 ${fn:contains(criteria.hotelIds, hotel.hotelId)?"selected":""}
								>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.UserActivityLog.ActionStartTime"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_operateTimeBegin" cssClass="fxt w120" key="criteria.operateTimeBegin" readonly="true"></s:textfield>
	                </div>
                </li>
                <li class="col3_1">
	                <div class="i_title"><span class="text"><fmt:message key="ccm.UserActivityLog.ActionEndTime"/>：</span></div>
	                <div class="i_input">
	                  <s:textfield id="l_operateTimeEnd" cssClass="fxt w120" key="criteria.operateTimeEnd" readonly="true"></s:textfield>
	                </div>
                </li>
            </ul>
            <hr class="solided notopMargin">
            <div class="center">
	            <button type="submit" class="btn_2 black mgR12"><fmt:message key="common.button.searchSelect"/></button>
	            <button type="button" class="btn_2 white l_reset"><fmt:message key="common.button.Reset"/></button>
            </div>
          </div>
          </s:form>
          
          <!--列表样式-->
          <div class="bt_wp">
          <form id="displayForm" name="displayForm" method="post" action="">
	          <display:table name="logInfoResult.resultList" id="logInfoList" class="ccm_table1" requestURI=""  form="displayForm"
	        	pagesize="20"  size="logInfoResult.totalCount" partialList="true">
		        <display:column property="operaterName" titleKey="ccm.UserActivityLog.UserName" sortable="true"/>
		        <display:column property="hotelName" titleKey="ccm.UserActivityLog.PropertyName" sortable="true"/>
		        <display:column titleKey="ccm.UserActivityLog.ActionTime" sortable="true">
		        	<fmt:formatDate type="date" value="${logInfoList.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		        </display:column>
		        <display:column property="businessName" titleKey="common.First-LevelMenu" sortable="true"/>
		        <display:column property="functionName" titleKey="common.Second-LevelMenu" sortable="true"/>
		        <display:column titleKey="ccm.UserActivityLog.ActionType" sortable="true">
		        	<c:choose>
		        		<c:when test="${logInfoList.operateType=='1' }"><fmt:message key="common.button.New"/></c:when>
		        		<c:when test="${logInfoList.operateType=='2' }"><fmt:message key="common.button.edit"/></c:when>
		        		<c:when test="${logInfoList.operateType=='3' }"><fmt:message key="common.button.delete"/></c:when>
		        		<c:otherwise>${logInfoList.operateType}</c:otherwise>
		        	</c:choose>
		        </display:column>
		        <display:column  titleKey="ccm.UserActivityLog.Action">
		        	<a href="/log_logDetailSearch.do?logId=${logInfoList.logId }" class="link mgR12"><fmt:message key="common.Details"/></a>
		        </display:column>
		      </display:table>
		      </form>
          </div>
        </div>
<script>
$(document).ready(function() {
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
							secondText:'<fmt:message key="time.Second"/>',
							timeText:'<fmt:message key="time.Time"/>',
							currentText:'<fmt:message key="time.Present"/>',
							closeText:'<fmt:message key="common.button.close"/>'
		}
	//日期显示
	$("#l_operateTimeBegin").datetimepicker($.extend(dpconfig, {
		timeFormat: 'HH:mm:ss',
		onClose : function(dateText, inst) {
			if ($("#l_operateTimeEnd").val() != '') {
				var testStartDate = $("#l_operateTimeBegin").datetimepicker('getDate');
				var testEndDate = $("#l_operateTimeEnd").datetimepicker('getDate');
				if (testStartDate > testEndDate)
					$("#l_operateTimeEnd").datetimepicker('setDate', testStartDate);
			} else {
				$("#l_operateTimeEnd").val(dateText);
			}
		},
		onSelect : function(selectedDateTime) {
			$("#l_operateTimeEnd").datetimepicker('option', 'minDate', $("#l_operateTimeBegin").datetimepicker('getDate'));
		}
	}));

	$("#l_operateTimeEnd").datetimepicker($.extend(dpconfig, {
		timeFormat: 'HH:mm:ss',
		onClose : function(dateText, inst) {
			if ($("#l_operateTimeBegin").val() != '') {
				var testStartDate = $("#l_operateTimeBegin").datetimepicker('getDate');
				var testEndDate = $("#l_operateTimeEnd").datetimepicker('getDate');
				if (testStartDate > testEndDate)
					$("#l_operateTimeBegin").datetimepicker('setDate', testEndDate);
			} else {
				$("#l_operateTimeBegin").val(dateText);
			}
		},
		onSelect : function(selectedDateTime) {
			$("#l_operateTimeBegin").datetimepicker('option', 'maxDate', $("#l_operateTimeEnd").datetimepicker('getDate'));
		}
	}));
	// 重置
	$('.l_reset').click(function(){
		$('#l_operaterId').val("");
		$('#l_businessId').val("");
		$('#l_functionId').val("");
		$('#l_operateTimeBegin').val("");
		$('#l_operateTimeEnd').val("");
		$('#l_operateType').val("");
	});
	//初始化    
	$('#l_functionId').multiselect({
		numberDisplayed: 2,
		dropRight: true,
        enableCaseInsensitiveFiltering: true,
		includeSelectAllOption: true,
		maxHeight: 300,
		maxWidth:192,
		buttonWidth: '194px',
		allSelectedText:'<fmt:message key="common.select.selectAll"/>',
		selectAllText:'<fmt:message key="common.select.selectAll"/>',
		dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
		nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
    });
	$('#l_businessId').multiselect({
		numberDisplayed: 2,
		dropRight: true,
        enableCaseInsensitiveFiltering: true,
		includeSelectAllOption: true,
		maxHeight: 300,
		maxWidth:192,
		buttonWidth: '194px',
		allSelectedText:'<fmt:message key="common.select.selectAll"/>',
		selectAllText:'<fmt:message key="common.select.selectAll"/>',
		dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
		nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
    });
	$('#l_operaterId').multiselect({
		numberDisplayed: 2,
		dropRight: true,
        enableCaseInsensitiveFiltering: true,
		includeSelectAllOption: true,
		maxHeight: 300,
		maxWidth:192,
		buttonWidth: '194px',
		allSelectedText:'<fmt:message key="common.select.selectAll"/>',
		selectAllText:'<fmt:message key="common.select.selectAll"/>',
		dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
		nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
    });
	$('#soc_hotelId').multiselect({
		numberDisplayed: 2,
		dropRight: true,
        enableCaseInsensitiveFiltering: true,
		includeSelectAllOption: true,
		maxHeight: 300,
		maxWidth:192,
		buttonWidth: '194px',
		allSelectedText:'<fmt:message key="common.select.selectAll"/>',
		selectAllText:'<fmt:message key="common.select.selectAll"/>',
		dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
		nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
    });
	
	//默认选择第一个
	/*
	if($('#l_operaterId option').length>0){
    	$("#l_operaterId option:first").prop("selected", 'selected');
    	$('#l_operaterId').multiselect('rebuild');
    	changeUser();
	}
	if($('#l_businessId option').length>0){
    	$("#l_businessId option:first").prop("selected", 'selected');
    	$('#l_businessId').multiselect('rebuild');
    	changeBusinessId();
	}
	if($('#l_functionId option').length>0){
    	$("#l_functionId option:first").prop("selected", 'selected');
    	$('#l_functionId').multiselect('rebuild');
	}
	if($('#soc_hotelId option').length>0){
    	$("#soc_hotelId option:first").prop("selected", 'selected');
    	$('#soc_hotelId').multiselect('rebuild');
	}
	*/

});

//动态加载
/*
$('#operaterIds_show').bind("change",function(){
	changeUser();
});
*/

$('#businessIds_show').bind("change",function(){
	changeBusinessId();
});	

//用户选择与酒店和一级菜单级联
/*
function changeUser(){
	$('#l_businessId').empty();
	$('#l_businessId').multiselect('rebuild');
	var userIds = '';
	$('#l_operaterId option:checked').each(function(){ 
		userIds += $(this).val() + ',';
	});
	userIds = userIds.substr(0,userIds.lastIndexOf(','));
	$.ajax({
	   	 type:"POST",
	   	 async:false,
	     dataType : "json",
	   	 url:"/log_ajaxGetAllTopMenuByUserId.do",
	   	 data:{"userIds":userIds},
		 success:function(data){
			  if(data.length > 0){
				  for(var i =0 ; i < data.length ; i++)
				  {  
					  var menu = data[i] ;
					  $('#l_businessId').append('<option value="'+menu.menuId+'">'+menu.displayName+'</option>');
				  }
				  $('#l_businessId').multiselect('rebuild');
			  }
	     }
    });
}
*/

//一级菜单选择与二级菜单级联
function changeBusinessId(){
	
	$('#l_functionId').empty();
	$('#l_functionId').multiselect('rebuild');
	var businessIds = '';
	$('#l_businessId option:checked').each(function(){ 
		businessIds += $(this).val() + ',';
	});
	businessIds = businessIds.substr(0,businessIds.lastIndexOf(','));
	$.ajax({
	   	 type:"POST",
	   	 async:false,
	     dataType : "json",
	   	 url:"/log_ajaxGetAllTwoMenuByUserId.do",
	   	 data:{"businessIds":businessIds},
		 success:function(data){
			  if(data.length > 0){
				  for(var i =0 ; i < data.length ; i++)
				  {  
					  var menu = data[i] ;
					  $('#l_functionId').append('<option value="'+menu.menuId+'">'+menu.displayName+'</option>');
				  }
				  $('#l_functionId').multiselect('rebuild');
			  }
	     }
    });
	
}

</script>
