<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- scroll -->
<link rel="stylesheet" href="/css/scroll.css">
<script src="<c:url value='/js/scroll.js'/>"></script>
<!-- 内容区域-->
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="title_wp">
		<div class="bt">
			<!--  <a href="0HOTEL-2.html" class="btn_2 blue">新建</a>-->
		</div>
		<fmt:message key="ccm.ReservationProductionDetailReport"/>
	</div>
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
						
				<li class="col3_1">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.Channel.ChannelCode"/>
						</span>
					</div>
					<div id="channels_show" class="i_input" style="position:relative;">
						<input id="channelCodeHi" value="${soc.channelCodeList }" type="hidden"/>
						<select id="soc_channelCode" name="soc.channelCodeList" class="fxt w120" multiple="multiple">
							<c:forEach items="${channelList}" var="channel">
								<option value="${channel.channelCode}"
								 	${fn:contains(soc.channelCodeList, channel.channelCode)?"selected":""}
								>${channel.channelCode}</option>	
							</c:forEach>
						</select>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.ArrivalStartDate"/></span>
					</div>
					<div class="i_input">
						<input id="checkinStart" name="soc.checkinStart" class="fxt w120 calendar datetip001" 
							value="<fmt:formatDate value='${soc.checkinStart }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.ArrivalEndDate"/></span>
					</div>
					<div class="i_input">
						<input id="checkinEnd" name="soc.checkinEnd" class="fxt w120 calendar datetip001" 
							value="<fmt:formatDate value='${soc.checkinEnd }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.BasicConfiguration.ChainCode"/></span>
					</div>
					<div id="chains_show" class="i_input" style="position:relative;">
						<select id="soc_chainCode" name="soc.chainCodeList" class="fxt w120" multiple="multiple">
							<c:forEach items="${chainList}" var="rl">
								<option value="${rl.chainCode}"
								 	${fn:contains(soc.chainCodeList, rl.chainCode)?"selected":""}
								>${rl.chainCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.DepartureStartDate"/></span>
					</div>
					<div class="i_input">
						<input id="checkoutStart" name="soc.checkoutStart" class="fxt w120 calendar datetip001" 
							value="<fmt:formatDate value='${soc.checkoutStart }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationsManagment.DepartureEndDate"/></span>
					</div>
					<div class="i_input">
						<input id="checkoutEnd" name="soc.checkoutEnd" class="fxt w120 calendar datetip001" 
							value="<fmt:formatDate value='${soc.checkoutEnd }' pattern='yyyy-MM-dd'/>">
					</div>
				</li>
				
				<li class="col3_1" id="hotelIdListClick">
					<div class="i_title">
						<span class="text"><span style="color:red">*</span><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/></span>
					</div>
					<div id="hotelIdList_show" class="i_input" style="position:relative;">
						<select id="soc_hotelId" name="soc.hotelIdList" class="fxt w120" multiple="multiple">
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelId}"
								 	${fn:contains(soc.hotelIdList, hotel.hotelId)?"selected":""}
								>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.ResvCreatedStartDate"/></span>
					</div>
					<div class="i_input">
						<input id="createStart" name="soc.createStart" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.createStart }' pattern='yyyy-MM-dd HH:mm'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.ResvCreatedEndDate"/></span>
					</div>
					<div class="i_input">
						<input id="createEnd" name="soc.createEnd" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.createEnd }' pattern='yyyy-MM-dd HH:mm'/>">
					</div>
				</li>
				<!-- 订单状态 -->
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.ReservationStatus"/></span>
					</div>
					<div id="orderStatus_show" class="i_input" style="position:relative;">
						<select id="soc_orderStatus" name="soc.statusList" class="fxt w120" multiple="multiple">
							<c:forEach items="${orderStatus}" var="rl">
								<option value="${rl.key}"
								 	${fn:contains(soc.statusList,rl.key)?"selected":""}
								>${rl.value}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<!-- 预订更新1-->
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key='ccm.ReservationUpdateStartDate'/></span>
					</div>
					<div class="i_input">
						<input id="lastModifyStart" name="soc.lastModifyStart" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.lastModifyStart}' pattern='yyyy-MM-dd HH:mm'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationUpdateEndDate"/></span>
					</div>
					<div class="i_input">
						<input id="lastModifyEnd" name="soc.lastModifyEnd" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.lastModifyEnd}' pattern='yyyy-MM-dd HH:mm'/>">
					</div>
				</li>
				
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.CRSNo"/></span>
					</div>
					<div class="i_input">
						<s:textfield id="orderNo" key="soc.orderNo" cssClass="fxt w120"/>
					</div>
				</li>
				
				<!-- 订单取消日期 -->
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key='ccm.report.CacellationStartDate'/></span>
					</div>
					<div class="i_input">
						<input id="cancelTimeStart" name="soc.cancelTimeStart" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.cancelTimeStart}' pattern='yyyy-MM-dd HH:mm'/>">
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.report.CacellationEndDate"/></span>
					</div>
					<div class="i_input">
						<input id="cancelTimeEnd" name="soc.cancelTimeEnd" class="fxt w120 calendar" 
							value="<fmt:formatDate value='${soc.cancelTimeEnd}' pattern='yyyy-MM-dd HH:mm'/>">
					</div>
				</li>
				
				<!-- 渠道订单号 -->
				<li class="col3_1">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.Channel.ChannelReservationNumber"/></span>
					</div>
					<div class="i_input">
						<s:textfield id="crsno" key="soc.crsno" cssClass="fxt w120"/>
					</div>
				</li>
				
				<!-- 订单下传消息状态 -->
				<li class="col3_1" id="downPmsMsgStatusChkLi">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key="ccm.ReservationDownloadMessageStatus"/></span>
					</div>
					<div id="downPmsMsgStatus_show" class="i_input" style="position:relative;">		
						<select id="soc_downPmsMsgStatus" name="soc.downPmsMsgStatusList" class="fxt w120" multiple="multiple">
							<c:forEach items="${downPmsMsgStatus}" var="rl">
								<option value="${rl.key}"
								 	${fn:contains(soc.downPmsMsgStatusList,rl.key)?"selected":""}
								>${rl.value}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<!--消息类型1-->
				<li class="col3_1" id="actionListChkLi">
					<div class="i_title" style="width: 96px;">
						<span class="text"><fmt:message key='ccm.Channel.MessageType'/></span>
					</div>
					<div id="actionList_show" class="i_input" style="position:relative;">		
						<select id="soc_actionList" name="soc.actionList" class="fxt w120" multiple="multiple">
							<c:forEach items="${actionList}" var="rl">
								<option value="${rl.key}"
								 	${fn:contains(soc.actionList,rl.key)?"selected":""}
								>${rl.value}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				
				<!-- 市场代码 -->
				<li class="col3_1" id="">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.report.MarketCode"/>
						</span>
					</div>
					<div id="market_show" class="i_input" style="position:relative;">
						<select id="soc_market" name="soc.marketList" class="fxt w120" multiple="multiple">
							<c:forEach var="m" items="${marketList}">		
							
								<option value="${m }" 
									${fn:contains(soc.marketList, m)?"selected":""} 
								>${m }</option>	
							
							</c:forEach>
						</select>
					</div>
				</li>
				<!-- 房价代码 -->
				<li class="col3_1" id="">
					<div class="i_title">
						<span class="text">
							<fmt:message key="ccm.RestrictionsManagement.RateCode"/>
						</span>
					</div>
					<div id="ratePlan_show" class="i_input" style="position:relative;">
						<select id="soc_ratePlan" name="soc.ratePlan" class="fxt w120" multiple="multiple">
						<!-- 
							<c:forEach var="m" items="${market}">
								<option value="${m }" <c:if test="${m==soc.market }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						 -->
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
						<select id="soc_roomType" name="soc.roomType" class="fxt w120" multiple="multiple">
						<!-- 
							<c:forEach var="m" items="${market}">
								<option value="${m }" <c:if test="${m==soc.market }">selected="selected"</c:if>  >${m }</option>
							</c:forEach>
						 -->
						</select>
					</div>
				</li>
				
			</ul>
			<div class="i_title" style="width: 500px;">
				<span class="text"><fmt:message key="ccm.tip.SearchByCRSNo"/></span>
			</div>
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
		<div class="bt_wp" >
			<form id="orderSearchForm" name="orderSearchForm" method="post" action="" style="position:relative;width:100%;overflow:auto;overflow-x:auto;
			overflow-y:scroll;">
				<div class="scroll-pane">
					<div class="scroll-bar-wrap">
	    				<div class="scroll-bar"></div>
	  				</div>
					<display:table name="orderSearchResult.resultList" id="orderProductDetail" 
						class="ccm_table1 scroll-content" requestURI="" pagesize="20" size="orderSearchResult.totalCount" 
						partialList="true"  form="orderSearchForm" >
						<display:column property="channel" sortable="true"  titleKey="ccm.Channel.ChannelCode" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="market" sortable="true" titleKey="ccm.report.MarketCode" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="masterId" sortable="true"  titleKey="ccm.ReservationsManagment.CRSNo" headerClass="sorted" class="scroll-content-item"/>
						
						<display:column property="crsno" sortable="true"  titleKey="ccm.Channel.ChannelReservationNumber" headerClass="sorted" class="scroll-content-item"/>
						
						<display:column property="pmsId" sortable="true"  titleKey="ccm.report.PMSAltID" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="name2" sortable="true"  titleKey="ccm.ReservationsManagment.EnglishSurname" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="name" sortable="true" titleKey="ccm.ReservationsManagment.EnglishName" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="name4" sortable="true"  titleKey="ccm.ReservationsManagment.ChineseName" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="arr" sortable="true" titleKey="ccm.ReservationsManagment.ArrivalDate" format="{0,date,yyyy-MM-dd}" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="dep" sortable="true" titleKey="ccm.ReservationsManagment.DepartureDate" format="{0,date,yyyy-MM-dd}" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="changed" sortable="true" titleKey="ccm.ReservationProductionDetailReport.ResvCreatedDate" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="lastModifyTime" sortable="true" titleKey="ccm.ReservationUpdateDate" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="cancelTime" sortable="true" titleKey="ccm.report.CacellationDate" format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="days" sortable="true" titleKey="ccm.ReservationProductionDetailReport.Nights" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="numberOfUnits" sortable="true" titleKey="ccm.ReservationProductionDetailReport.Rooms" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="ratePlanCode" sortable="true" titleKey="ccm.RestrictionsManagement.RateCode" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="type" sortable="true"  titleKey="ccm.Channel.RoomTypeCode" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="firstDateRate" sortable="true" titleKey="ccm.ReservationProductionDetailReport.1stDayRoomRate" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="deduct" sortable="true" titleKey="ccm.ReservationProductionDetailReport.DeductedCXLFee" headerClass="sorted" class="scroll-content-item"/>
				        <display:column property="charge" sortable="true" titleKey="ccm.ReservationProductionDetailReport.TotalRoomFee" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="payment" sortable="true" titleKey="ccm.GuaranteeRules.GuaranteeType" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="action" sortable="true" titleKey="ccm.Channel.MessageType" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="sta" sortable="true"  titleKey="ccm.ReservationMonitorReport.ReservationStatus" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="downPmsMsgStatus" sortable="true" titleKey="ccm.ReservationDownloadMessageStatus" headerClass="sorted" class="scroll-content-item"/>
						<display:column property="createBY" sortable="true" titleKey="ccm.reports.CREATEBY" headerClass="sorted" class="scroll-content-item"/>
					</display:table>
				</div>
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
	function searchOrder() {
		
		//酒店代码单选复选切换 
		if( ( ($('#actionList_show input:checked').length>0)||($('#downPmsMsgStatus_show input:checked').length>0)) &&
				($('#hotelIdList_show option:checked').length>1)){
			alert('当选择订单下传消息状态或消息类型中的任意一个的话，酒店代码只能选择一项！');
			return false;
		}
		
		if($('input[name="soc.channelCodeList"]').val() == ""){
			alert('<fmt:message key="ccm.RestrictionsManagement.error.channelNull"/>');
			return false;
		}
		
		//请选择酒店
		if($('#soc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
	
		//验证中央预定号
		var orderNo = $('#orderNo').val();
		
		//验证市场代码 soc_market
		var marketCode = $('#soc_market').val();
	
		if( strIsNull(orderNo) && (marketCode==null || marketCode.length<1) ){
	     
		 //验证日期
		var checkinStart = $('#checkinStart').val();
		if(!strIsNull(checkinStart)){
			var code = validateYYYYMMDD(checkinStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.ArrivalStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var checkinEnd = $('#checkinEnd').val();
		if(!strIsNull(checkinEnd)){
			var code = validateYYYYMMDD(checkinEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.ArrivalEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		var checkoutStart = $('#checkoutStart').val();
		if(!strIsNull(checkoutStart)){
			var code = validateYYYYMMDD(checkoutStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.DepartureStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var checkoutEnd = $('#checkoutEnd').val();
		if(!strIsNull(checkoutEnd)){
			var code = validateYYYYMMDD(checkoutEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.DepartureEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		var createStart = $('#createStart').val();
		if(!strIsNull(createStart)){
			var code = validateYYYYMMDDHHmm(createStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationMonitorReport.ResvCreatedStartDate"/>','YYYYMMDDHHmm'));
				return false;
			}
		}
		var createEnd = $('#createEnd').val();
		if(!strIsNull(createEnd)){
			var code = validateYYYYMMDDHHmm(createEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationMonitorReport.ResvCreatedEndDate"/>','YYYYMMDDHHmm'));
				return false;
			}
		}
		
		var lastModifyStart = $('#lastModifyStart').val();
			if(!strIsNull(lastModifyStart)){
				var code = validateYYYYMMDDHHmm(lastModifyStart);
				if(code!='success'){
					alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationUpdateStartDate"/>','YYYYMMDDHHmm'));
					return false;
				}
			}
		var lastModifyEnd = $('#lastModifyEnd').val();
			if(!strIsNull(lastModifyEnd)){
				var code = validateYYYYMMDDHHmm(lastModifyEnd);
				if(code!='success'){
					alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationUpdateEndDate"/>','YYYYMMDDHHmm'));
					return false;
				}
			}
			
		if((strIsNull(checkinStart)||strIsNull(checkinEnd))
				&&(strIsNull(checkoutStart)||strIsNull(checkoutEnd))
				&&(strIsNull(createStart)||strIsNull(createEnd))
				&&(strIsNull(lastModifyStart)||strIsNull(lastModifyEnd))
				){
			alert('<fmt:message key="ccm.ReservationProductionDetailReport.ErrorMessage.4DateNUll"/>!');
			return false;
		}
		if($('#searchForm').valid()){
			
			//如果市场代码为全选,则设空
			//已选市场代码 
			var marketCodeSelected = $('#soc_market').val();
			//所有市场代码
			var optionNum=0;
			$("#soc_market option").each(function () {
				optionNum++;
	        });
			if(marketCodeSelected!=null&&(marketCodeSelected.length==optionNum)){
				$('#soc_market').val(null);
			}
			
			document.searchForm.action = "/orderProduct_detailQuery.do";
			$('#searchForm').submit();
		}
	}
	if($('#searchForm').valid()){
		
		//如果市场代码为全选,则设空
		//已选市场代码 
		var marketCodeSelected = $('#soc_market').val();
		//所有市场代码
		var optionNum=0;
		$("#soc_market option").each(function () {
			optionNum++;
        });
		if(marketCodeSelected!=null&&(marketCodeSelected.length==optionNum)){
			$('#soc_market').val(null);
		}
		
		document.searchForm.action = "/orderProduct_detailQuery.do";
		$('#searchForm').submit();
	}
}
	
	//导出excel
	function exportOrder() {
		//酒店代码单选复选切换 
		if((($('#actionList_show input:checked').length>0)||($('#downPmsMsgStatus_show input:checked').length>0)) &&
				($('#hotelIdList_show option:checked').length>1)){
			alert('当选择订单下传消息状态或消息类型中的任意一个的话，酒店代码只能选择一项！');
			return false;
		}
		if($('input[name="soc.channelCodeList"]').val() == ""){
			alert('<fmt:message key="ccm.RestrictionsManagement.error.channelNull"/>');
			return false;
		}
		
		//请选择酒店
		if($('#soc_hotelId option:selected').length == 0){
			alert('<fmt:message key="ccm.ReservationMonitorReport.PleaseSelectTheHotel"/>');
			return false;
		}
		
		//验证中央预定号
		var orderNo = $('#orderNo').val();
		//验证市场代码 soc_market
		var marketCode = $('#soc_market').val();
	
	    if( strIsNull(orderNo) && (marketCode==null || marketCode.length<1) ){
		//验证日期
		var checkinStart = $('#checkinStart').val();
		if(!strIsNull(checkinStart)){
			var code = validateYYYYMMDD(checkinStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.ArrivalStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var checkinEnd = $('#checkinEnd').val();
		if(!strIsNull(checkinEnd)){
			var code = validateYYYYMMDD(checkinEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.ArrivalEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		var checkoutStart = $('#checkoutStart').val();
		if(!strIsNull(checkoutStart)){
			var code = validateYYYYMMDD(checkoutStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.DepartureStartDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		var checkoutEnd = $('#checkoutEnd').val();
		if(!strIsNull(checkoutEnd)){
			var code = validateYYYYMMDD(checkoutEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationsManagment.DepartureEndDate"/>','yyyy-MM-dd'));
				return false;
			}
		}
		
		var createStart = $('#createStart').val();
		if(!strIsNull(createStart)){
			var code = validateYYYYMMDDHHmm(createStart);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationMonitorReport.ResvCreatedStartDate"/>','YYYYMMDDHHmm'));
				return false;
			}
		}
		var createEnd = $('#createEnd').val();
		if(!strIsNull(createEnd)){
			var code = validateYYYYMMDDHHmm(createEnd);
			if(code!='success'){
				alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationMonitorReport.ResvCreatedEndDate"/>','YYYYMMDDHHmm'));
				return false;
			}
		}
		var lastModifyStart = $('#lastModifyStart').val();
			if(!strIsNull(lastModifyStart)){
				var code = validateYYYYMMDDHHmm(lastModifyStart);
				if(code!='success'){
					alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationUpdateStartDate"/>','YYYYMMDDHHmm'));
					return false;
				}
			}
			var lastModifyEnd = $('#lastModifyEnd').val();
			if(!strIsNull(lastModifyEnd)){
				var code = validateYYYYMMDDHHmm(lastModifyEnd);
				if(code!='success'){
					alert(getErrorMsg(code,'<fmt:message key="ccm.ReservationUpdateEndDate"/>','YYYYMMDDHHmm'));
					return false;
				}
			}
			
		if((strIsNull(checkinStart)||strIsNull(checkinEnd))
			&&(strIsNull(checkoutStart)||strIsNull(checkoutEnd))
			&&(strIsNull(createStart)||strIsNull(createEnd))
			&&(strIsNull(lastModifyStart)||strIsNull(lastModifyEnd))
				){
			alert('<fmt:message key="ccm.ReservationProductionDetailReport.ErrorMessage.4DateNUll"/>!');
			return false;
		}
		
		//如果通过校验
		if($('#searchForm').valid()){
			
			//如果市场代码为全选,则设空
			//已选市场代码 
			var marketCodeSelected = $('#soc_market').val();
			//所有市场代码
			var optionNum=0;
			$("#soc_market option").each(function () {
				optionNum++;
	        });
			if(marketCodeSelected!=null&&(marketCodeSelected.length==optionNum)){
				$('#soc_market').val(null);
			}
			
			document.searchForm.action = "/orderProduct_detailExport.do";
			document.searchForm.submit();
		}
	}
	//如果通过校验
	if($('#searchForm').valid()){
		
		//如果市场代码为全选,则设空
		//已选市场代码 
		var marketCodeSelected = $('#soc_market').val();
		//所有市场代码
		var optionNum=0;
		$("#soc_market option").each(function () {
			optionNum++;
        });
		if(marketCodeSelected!=null&&(marketCodeSelected.length==optionNum)){
			$('#soc_market').val(null);
		}
		
		document.searchForm.action = "/orderProduct_detailExport.do";
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
			              ],
							hourText:'<fmt:message key="time.Hour"/>',
							minuteText:'<fmt:message key="time.Minute"/>',
							timeText:'<fmt:message key="time.Time"/>',
							currentText:'<fmt:message key="time.Present"/>',
							closeText:'<fmt:message key="common.button.close"/>'
	}
	
	//到店时间
	var cs = $("#checkinStart");
	var es = $("#checkinEnd");
	cs.datepicker($.extend(dpconfig,{
		onClose : function(dateText, inst) {
			if (es.val() != '') {
				var testStartDate = cs.datepicker('getDate');
				var testEndDate = es.datepicker('getDate');
				if (testStartDate > testEndDate){
					var startDate = new Date(testStartDate);
					startDate.setDate(startDate.getDate()+1);
					es.datepicker('setDate', startDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var startDate = new Date(cs.datepicker('getDate'));
			startDate.setDate(startDate.getDate()+1);
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
					endDate.setDate(endDate.getDate()-1);
					cs.datepicker('setDate', endDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var endDate = new Date(es.datepicker('getDate'));
			endDate.setDate(endDate.getDate()-1);
			cs.datepicker('option', 'maxDate', endDate);
		}
	}));
	
	
	//离店时间
	var os = $("#checkoutStart");
	var oe = $("#checkoutEnd");
	os.datepicker($.extend(dpconfig,{
		onClose : function(dateText, inst) {
			if (oe.val() != '') {
				var testStartDate = os.datepicker('getDate');
				var testEndDate = oe.datepicker('getDate');
				if (testStartDate > testEndDate){
					var startDate = new Date(testStartDate);
					startDate.setDate(startDate.getDate()+1);
					oe.datepicker('setDate', startDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var startDate = new Date(os.datepicker('getDate'));
			startDate.setDate(startDate.getDate()+1);
			oe.datepicker('option', 'minDate', startDate);
		}
	}));

	oe.datepicker($.extend(dpconfig,{
		hour:23,
		minute:59,
		onClose : function(dateText, inst) {
			if (oe.val() != '') {
				var testStartDate = os.datepicker('getDate');
				var testEndDate = oe.datepicker('getDate');
				if (testStartDate > testEndDate){
					var endDate = new Date(testEndDate);
					endDate.setDate(endDate.getDate()-1);
					os.datepicker('setDate', endDate);
				}
			}
		},
		onSelect : function(selectedDateTime) {
			var endDate = new Date(oe.datepicker('getDate'));
			endDate.setDate(endDate.getDate()-1);
			os.datepicker('option', 'maxDate', endDate);
		}
	}));
	

	var start = $("#createStart");
	var end = $("#createEnd");
	start.datetimepicker($.extend(dpconfig,{
		hour:0,
		minute:0,
		onClose : function(dateText, inst) {
			if (end.val() != '') {
				var testStartDate = start.datetimepicker('getDate');
				var testEndDate = end.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					end.datetimepicker('setDate', testStartDate);
			}
		},
		onSelect : function(selectedDateTime) {
			//end.datetimepicker('option', 'minDate', start.datetimepicker('getDate'));
			if (end.val() != '') {
				var testStartDate = start.datetimepicker('getDate');
				var testEndDate = end.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					end.datetimepicker('setDate', testStartDate);
			}
		}
	}));
	end.datetimepicker($.extend(dpconfig,{
		hour:23,
		minute:59,
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
			//start.datetimepicker('option', 'maxDate', end.datetimepicker('getDate'));
			if (start.val() != '') {
				var testStartDate = start.datetimepicker('getDate');
				var testEndDate = end.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					start.datetimepicker('setDate', testEndDate);
			} else {
				start.val(dateText);
			}
		}
	}));
	
	//订单取消日期
	var cstart = $("#cancelTimeStart");
	var cend = $("#cancelTimeEnd");
	cstart.datetimepicker($.extend(dpconfig,{
		hour:0,
		minute:0,
		onClose : function(dateText, inst) {
			if (cend.val() != '') {
				var testStartDate = cstart.datetimepicker('getDate');
				var testEndDate = cend.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					cend.datetimepicker('setDate', testStartDate);
			}
		},
		onSelect : function(selectedDateTime) {
			//end.datetimepicker('option', 'minDate', start.datetimepicker('getDate'));
			if (cend.val() != '') {
				var testStartDate = cstart.datetimepicker('getDate');
				var testEndDate = cend.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					cend.datetimepicker('setDate', testStartDate);
			}
		}
	}));
	cend.datetimepicker($.extend(dpconfig,{
		hour:23,
		minute:59,
		onClose : function(dateText, inst) {
			if (cstart.val() != '') {
				var testStartDate = cstart.datetimepicker('getDate');
				var testEndDate = cend.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					cstart.datetimepicker('setDate', testEndDate);
			} else {
				cstart.val(dateText);
			}
		},
		onSelect : function(selectedDateTime) {
			//start.datetimepicker('option', 'maxDate', end.datetimepicker('getDate'));
			if (start.val() != '') {
				var testStartDate = cstart.datetimepicker('getDate');
				var testEndDate = cend.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					cstart.datetimepicker('setDate', testEndDate);
			} else {
				cstart.val(dateText);
			}
		}
	}));
	
	<!-- 预订更新 -->
	var mstart = $("#lastModifyStart");
	var mend = $("#lastModifyEnd");
	mstart.datetimepicker($.extend(dpconfig,{
		hour:0,
		minute:0,
		onClose : function(dateText, inst) {
			if (mend.val() != '') {
				var testStartDate = mstart.datetimepicker('getDate');
				var testEndDate = mend.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					mend.datetimepicker('setDate', testStartDate);
			}
		},
		onSelect : function(selectedDateTime) {
			//mend.datetimepicker('option', 'minDate', mstart.datetimepicker('getDate'));
			if (mend.val() != '') {
				var testStartDate = mstart.datetimepicker('getDate');
				var testEndDate = mend.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					mend.datetimepicker('setDate', testStartDate);
			}
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
			//mstart.datetimepicker('option', 'maxDate', mend.datetimepicker('getDate'));
			if (mstart.val() != '') {
				var testStartDate = mstart.datetimepicker('getDate');
				var testEndDate = mend.datetimepicker('getDate');
				if (testStartDate > testEndDate)
					mstart.datetimepicker('setDate', testEndDate);
			} else {
				mstart.val(dateText);
			}
		}
	}));
	
	//酒店代码单选复选切换
	$("#actionListChkLi").click(function(){
		if( ( ($('#actionList_show input:checked').length>0)||($('#downPmsMsgStatus_show input:checked').length>0)) &&
				($('#hotelIdList_show option:checked').length>1)){
			alert('当选择订单下传消息状态或消息类型中的任意一个的话，酒店代码只能选择一项！');
			return false;
		}
		if( ($('#actionList_show input:checked').length>0)||($('#downPmsMsgStatus_show input:checked').length>0) ){
			$('#soc_hotelId').removeAttr('multiple');
		}else{
			$('#soc_hotelId').attr('multiple','multiple');	
		}
		// $('#soc_hotelId').multiselect('rebuild');
	});	
	$("#downPmsMsgStatusChkLi").click(function(){
		if( ( ($('#actionList_show input:checked').length>0)||($('#downPmsMsgStatus_show input:checked').length>0)) &&
				($('#hotelIdList_show option:checked').length>1)){
			alert('当选择订单下传消息状态或消息类型中的任意一个的话，酒店代码只能选择一项！');
			return false;
		}
		if( ($('#actionList_show input:checked').length>0)||($('#downPmsMsgStatus_show input:checked').length>0) ){
			$('#soc_hotelId').removeAttr('multiple');
		}else{
			$('#soc_hotelId').attr('multiple','multiple');	
		}
		// $('#soc_hotelId').multiselect('rebuild');
	});
	$("#hotelIdListClick").click(function(){
		if( ($('#actionList_show input:checked').length>0)||($('#downPmsMsgStatus_show input:checked').length>0) ){
			$('#soc_hotelId').removeAttr('multiple');
		}else{
			$('#soc_hotelId').attr('multiple','multiple');	
		}
		 $('#soc_hotelId').multiselect('rebuild');
	});
	
	$(document).ready(function() {

		 //如果没有选中任何渠道,则默认选中第一个渠道
	    if($('#soc_channelCode option:selected').length==0){
	    	$("#soc_channelCode option:first").prop("selected", 'selected');
	    }
	    
	    //如果没有选中任何集团,则默认选中第一个集团
	    if($('#soc_chainCode option:selected').length==0){
	    	$("#soc_chainCode option:first").prop("selected", 'selected');
	    }
	    
	    //动态加载酒店代码
		$('#chains_show').bind("change",function(){
			$('#soc_hotelId').empty();
			showChainCode();
		});	

		//加载房价代码
		function ajaxGetRatePlan(ms){
			
			$('#soc_ratePlan').empty();
			var hotelIds = '';
			$('#hotelIdList_show input:checked').each(function(){ 
				hotelIds += $(this).val() + ',';
			});
			hotelIds = hotelIds.substr(0,hotelIds.lastIndexOf(','));
			if($('#hotelIdList_show input:checked').length >= 1){
				$.ajax({
				   	 type:"POST",
				   	 async:false,
				     dataType : "json",
				   	 url:"/orderProduct_ajaxGetRatePlan.do",
				   	 data:{"hotelIds":hotelIds},
					 success:function(data){
						  if(data.length > 0){	  
							  
							  if(null==ms || ms.length < 1){
								 for(var i =0 ; i < data.length ; i++)
								  {
									  var rateCode = data[i] ;	  
									  $('#soc_ratePlan').append('<option value="'+rateCode.rateCode+'" selected>'+rateCode.rateCode+'</option>');  
								  }
							  }else{
								for(var i =0 ; i < data.length ; i++)
								{
								  var rateCode = data[i] ;
								  if(ms.indexOf(rateCode.rateCode) > 0){
									  $('#soc_ratePlan').append('<option value="'+rateCode.rateCode+'" selected>'+rateCode.rateCode+'</option>');	  
								  }else{
									  $('#soc_ratePlan').append('<option value="'+rateCode.rateCode+'">'+rateCode.rateCode+'</option>');  
								  } 
								}
							  }
							  $('#soc_ratePlan').multiselect('rebuild');
						  }
						  
				     }
			    });
			}	
		}
		
		//加载房型代码
		function ajaxGetRoomType(ms){
			
			$('#soc_roomType').empty();
			var hotelIds = '';
			$('#hotelIdList_show input:checked').each(function(){ 
				hotelIds += $(this).val() + ',';
			});
			hotelIds = hotelIds.substr(0,hotelIds.lastIndexOf(','));
			
			if($('#hotelIdList_show input:checked').length >= 1){
				$.ajax({
				   	 type:"POST",
				   	 async:false,
				     dataType : "json",
				   	 url:"/orderProduct_ajaxGetRoomType.do",
				   	 data:{"hotelIds":hotelIds},
					 success:function(data){
						   
						  if(data.length > 0){	
							  if(null==ms || ms.length < 1){
									 for(var i =0 ; i < data.length ; i++)
									  {
										  var roomTypes = data[i] ;	  
										  $('#soc_roomType').append('<option value="'+roomTypes.roomTypes+'" selected>'+roomTypes.roomTypes+'</option>');  
									  }
								 }else{
									 for(var i =0 ; i < data.length ; i++)
									 {
										  var roomTypes = data[i] ;
										
										  if(ms.indexOf(roomTypes.roomTypes) > 0){
											  $('#soc_roomType').append('<option value="'+roomTypes.roomTypes+'" selected>'+roomTypes.roomTypes+'</option>');	  
										  }else{
											  $('#soc_roomType').append('<option value="'+roomTypes.roomTypes+'">'+roomTypes.roomTypes+'</option>');	  
										  }
										 
									 }
							  }
							  
							 $('#soc_roomType').multiselect('rebuild');
						  }
				     }
			    });
			}
		}
		//加载市场代码
		function ajaxGetMarkets(ms){
			$('#soc_market').empty();
			var hotelIds = '';
			$('#hotelIdList_show input:checked').each(function(){ 
				hotelIds += $(this).val() + ',';
			});
			hotelIds = hotelIds.substr(0,hotelIds.lastIndexOf(','));
			if($('#hotelIdList_show input:checked').length >= 1){
				//加载市场代码
				$.ajax({
				   	 type:"POST",
				   	 async:false,
				     dataType : "json",
				   	 url:"/orderProduct_ajaxGetMarkets.do",
				   	 data:{"hotelIds":hotelIds},
					 success:function(data){
						 
						  if(data.length > 0){	
							 if(null==ms || ms.length < 1){
								 for(var i =0 ; i < data.length ; i++)
								  {
									  var market = data[i] ;	  
									  $('#soc_market').append('<option value="'+market.market+'" selected>'+market.market+'</option>');	  
								  }
							 }else{
								 for(var i =0 ; i < data.length ; i++)
								 {
									  var market = data[i] ;
									
									  if(ms.indexOf(market.market) > 0){
										  $('#soc_market').append('<option value="'+market.market+'" selected>'+market.market+'</option>');	  
									  }else{
										  $('#soc_market').append('<option value="'+market.market+'">'+market.market+'</option>');	  
									  }
									 
								 }
							 }
							 
							 $('#soc_market').multiselect('rebuild');
						  }
					
				     }
			    });
			}	
		}
		
		$('#hotelIdList_show').bind('change',function(){
			ajaxGetRatePlan(null);
			ajaxGetRoomType(null);
			ajaxGetMarkets(null);
		});
		//初始化市场代码
		$('#soc_market').multiselect({
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
		//消息类型
		$('#soc_actionList').multiselect({
			numberDisplayed: 2,
			dropRight: true,
            enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:200,
			buttonWidth: '202px',
			selectedList: 1,
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
        });
		//订单下传消息状态 初始化  
		$('#soc_downPmsMsgStatus').multiselect({
			numberDisplayed: 1,
			dropRight: true,
            enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:200,
			buttonWidth: '202px',
			selectedList: 1,
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>'
        });
		
		//订单状态初始化
		$('#soc_orderStatus').multiselect({
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
		
		//初始化房价代码
		$('#soc_ratePlan').multiselect({
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

		//初始化房型代码
		$('#soc_roomType').multiselect({
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
		$('#soc_hotelId').multiselect({
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
		$('#soc_channelCode').multiselect({
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
		//集团代码初始化 soc_chainCode
		$('#soc_chainCode').multiselect({
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
		
		//如果是最开始跳入
		if('${isInit}' == '1'){
			//加载酒店代码
			$('#soc_hotelId').empty();
			showChainCode();
			
			//消息类型全选
			/*
			if($('#soc_actionList option').length>0){
		    	$("#soc_actionList option").each(function(){ 		
					$(this).attr("selected", true);
				});
		    	$('#soc_actionList').multiselect('rebuild');
			}
			*/
			//订单下传消息状态全选
			/*
			if($('#soc_downPmsMsgStatus option').length>0){
		    	$("#soc_downPmsMsgStatus option").each(function(){ 	
					$(this).attr("selected", true);
				});
		    	$('#soc_downPmsMsgStatus').multiselect('rebuild');
			}
			*/
			
			//订单状态全选
			if($('#soc_orderStatus option').length>0){
		    	$("#soc_orderStatus option").each(function(){ 
		    		
					$(this).attr("selected", true);
				});
		    	$('#soc_orderStatus').multiselect('rebuild');
			}
			
		}	
		
		//加载房型代码
		ajaxGetRoomType('${soc.roomType}');
		//加载房价代码
		ajaxGetRatePlan('${soc.ratePlan}');
		//加载市场代码
		ajaxGetMarkets('${soc.marketList}');
		
	});
	
	function showChainCode(){
		var chainCodeName='';
		$('#chains_show input:checked').next('span').each(function(){ 
			chainCodeName += $(this).find("span.span_chainCode").text()+",";
		});
		
		var chainCodes = '';
		$('#chains_show input:checked').each(function(){ 
			$('#chains_click .typeCode').append(
					'<input type="text" name="soc.chainCodeList" value="'+$(this).val()+'">');
			chainCodes += $(this).val() + ',';
		});
		$('#chains_click .typeName').text(chainCodeName.substr(0,chainCodeName.lastIndexOf(',')));
		chainCodes = chainCodes.substr(0,chainCodes.lastIndexOf(','));
		
		$('#soc_hotelId').next().find('button').html('<fmt:message key="common.select.plesesSelect"/> <b class="caret"></b>');
		$('option', $('#soc_hotelId')).remove();
	
		if($('#chains_show input:checked').length >= 1){
			$.ajax({
			   	 type:"POST",
			   	 async:false,
			     dataType : "json",
			   	 url:"/orderProduct_ajaxGetHotels.do",
			   	 data:{"chainCodes":chainCodes},
				 success:function(data){
					  if(data.length > 0){
						  for(var i =0 ; i < data.length ; i++)
						  {
							  var hotel = data[i] ;
							  
							  //初始化,默认选中第一项
							  if(i==0){
								  $('#soc_hotelId').append('<option value="'+hotel.hotelId+'" selected>'+hotel.hotelCode+'</option>');  
							  }else{
								  $('#soc_hotelId').append('<option value="'+hotel.hotelId+'">'+hotel.hotelCode+'</option>');  
							  }
							 
						  }
						  $('#soc_hotelId').next().find('button').html('<fmt:message key="common.select.selectAll"/> <b class="caret"></b>');
						  $('#soc_hotelId').multiselect('rebuild');
					  }
			     }
		    });
			 
		}
		
	}
	
	function showChannelCode(){
		var channelCodeName='';
		$('#channels_show input:checked').next('span').each(function(){ 
			channelCodeName += $(this).find("span.span_channelCode").text()+",";
		});
		$('#channels_click .typeName').text(channelCodeName.substr(0,channelCodeName.lastIndexOf(',')));
		$('#channels_show input:checked').each(function(){ 
			$('#channels_click .typeCode').append('<input type="text" name="soc.channelCodeList" value="'+$(this).val()+'">');
		});
	}

</script>

<script type="text/javascript">
//已选中的值
var optionVal=$("#soc_channelCode").val()+'';
var optionArr = new Array();
optionArr=optionVal.split(",");
//实际传到后台的值  <input id="channelCodeHi" value="${soc.channelCodeList }" type="hidden"/>
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
		$("#soc_channelCode").find("option[value="+vo+"]").removeAttr("selected");
	}
}  
</script>