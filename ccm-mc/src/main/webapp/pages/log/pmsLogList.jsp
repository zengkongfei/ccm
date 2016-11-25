<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<div class="title_wp">
	<div class="bt">
		<!--  <a href="0HOTEL-2.html" class="btn_2 blue">新建</a>-->
	</div>
	<fmt:message key="ccm.PropertyInterfaceMonitor" />
</div>
<form id="searchForm" name="searchForm" action="" method="post">
	<div class="c_whitebg">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_2">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode"/></span>
					</div>
					<div class="i_input" style="position: relative;">
						<select id="soc_hotelId" name="soc.hoteCodes" style="display: none;" class="fxt w120" multiple="multiple" >
							<c:forEach items="${hotelList}" var="hotel">
									<option value="${hotel.hotelCode}"
									 	${fn:contains(soc.hoteCodes, hotel.hotelCode)?"selected":""}
									>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.PropertyInterfaceMonitor.logStatus"/></span>
					</div>
					<div class="i_input">
						<s:select list="#request.logStatus" key="soc.status" cssClass="fxt w120"></s:select>
					</div>
				</li>
			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<!--  <a class="out greylink" href="#" id="exportCustom">导出订单</a>(每次最多只能导出1000条记录)&nbsp;-->
				<button type="button" class="btn_2 black mgR12" onclick="javascript:searchOrder();"><fmt:message key="common.button.searchSelect"/></button>
				<button type="button" class="btn_2 white" onclick="javascript:clearForm(this.form);"><fmt:message key="common.button.Reset"/></button>
				<button type="button" class="btn_2 white" onclick="doExport();"><fmt:message key="common.button.File"/></button>
				<input id="isMonitor" type="text" value="${isMonitor}" style="display:none"/>
				<button id="showChart" type="button" class="btn_2 white mgR12" style="display:none"><fmt:message key="ccm.report.ShowDataGraph"/></button>
			</div>
		</div>
	</div>
</form>
<div id="show" class="ccm-popup width900 zoom-anim-dialog mfp-hide"></div>
<div class="c_whitebg">
	<div class="bt_wp">
		<form id="pmslog" name="pmslog" method="post" action="">
			<display:table excludedParams="roomTypeChk" form="pmslog" name="receivePmsLogResult.resultList" id="pmslog" class="ccm_table1" requestURI="" pagesize="20" size="receivePmsLogResult.totalCount" partialList="true">
				<display:column property="hotelCode" sortable="true" titleKey="ccm.ReservationMonitorReport.PropertyCode" headerClass="sorted" />
				<display:column sortable="true" titleKey="ccm.hotel.MonitorPMSHeartBeat" headerClass="sorted">
					<c:if test="${pmslog.isPMSHeartBeat=='true'}"><fmt:message key="common.Yes"/></c:if>
					<c:if test="${pmslog.isPMSHeartBeat=='false'}"><fmt:message key="common.Not"/></c:if>
				</display:column>
				<display:column sortable="true" titleKey="ccm.ReservationsManagment.Status" headerClass="sorted">
					<c:if test="${pmslog.statusResult=='on'}"><fmt:message key="common.Active"/></c:if>
					<c:if test="${pmslog.statusResult=='off'}"><fmt:message key="common.Stop"/></c:if>
				</display:column>
				<display:column sortable="true"  titleKey="ccm.PropertyInterfaceMonitor.UploadMessageTimeInterval" headerClass="sorted">
					${pmslog.spaceSec}<fmt:message key="common.Seconds"/>
				</display:column>
			</display:table>
		</form>
	</div>
</div>

<!-- 内容区域 end-->
<script type="text/javascript">
	function searchOrder() {
		//alert($('#soc_hotelId').val());
		document.searchForm.action = "/pmsLog_list.do";
		$('#searchForm').submit();
	}
	
	function showChart() {
		document.searchForm.action = "/main.do";
		$('#searchForm').submit();
	}
	
	function doExport(){
		$("#searchForm").attr("action", "/pmsLog_export.do");
		$("#searchForm").submit();
	}
   $(document).ready(function() {
		
	   $('#showChart').hide();
	   
	   var isMonitor=$('#isMonitor').val();
	  
	   if('true'==isMonitor){
		   
		   $('#showChart').show();
		   $("#showChart").click(function(){
			   showChart();
			});
	   }else{
		   $('#showChart').hide();
	   }
	  
	   
	   $('#soc_hotelId').parent().append(headHtml);
	   setTimeout(function () { 
		 //初始化
		  $('#soc_hotelId').multiselect({
			dropRight: true,
	        enableCaseInsensitiveFiltering: true,
			includeSelectAllOption: true,
			maxHeight: 300,
			maxWidth:192,
			buttonWidth: '194px',
			allSelectedText:'<fmt:message key="common.select.selectAll"/>',
			selectAllText:'<fmt:message key="common.select.selectAll"/>',
			dSelectAllText: '<fmt:message key="common.select.Unselect"/>',
			nonSelectedText: '<fmt:message key="common.select.plesesSelect"/>',
	    });
	   }, 50);
		$('#soc_hotelId').next().remove();
	});
		
</script>