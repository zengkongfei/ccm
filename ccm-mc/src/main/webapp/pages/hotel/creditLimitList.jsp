<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="title_wp">
	<fmt:message key="common.button.searchSelect" />
</div>
<div class="c_whitebg">

	<!--搜索项-->
	<s:form action="/creditLimit_list.do" method="post" id="search">
		<div class="nm_box">
			<ul class="inq_wp frow">
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.creditlimit.groupname" />：</span>
					</div>
					<div class="i_input">
						<s:textfield id="l_hotelName" name="creteria.groupName" cssClass="fxt w120"></s:textfield>
					</div></li>
				<li class="col3_1 numLi">
					<div class="i_title">
						<span style="color:red">*</span><span class="text"><fmt:message key="ccm.Channel.ChannelCode" />：</span>
					</div>
					<div class="i_input">
						<select class="fxt w120 required" name="creteria.channelId">
							<option value="">
								<fmt:message key="common.select.plesesSelect" />
							</option>
							<c:forEach items="${channelList }" var="channel">
								<option value="${channel.channelId }" <c:if test="${creteria.channelId==channel.channelId }">selected</c:if>>${channel.channelCode }</option>
							</c:forEach>
						</select>
					</div></li>
				<li class="col3_1">
					<div class="i_title">
						<span class="text"><fmt:message key="ccm.ReservationMonitorReport.PropertyCode" />：</span>
					</div>
					<div class="i_input" style="position: relative;">
						<select id="soc_hotelId" name="creteria.hotelId" class="fxt w120">
							<option value="">
								<fmt:message key="common.select.plesesSelect" />
							</option>
							<c:forEach items="${hotelList}" var="hotel">
								<option value="${hotel.hotelId}" <c:if test="${creteria.hotelId==hotel.hotelId }">selected</c:if>>${hotel.hotelCode}</option>
							</c:forEach>
						</select>
					</div></li>

			</ul>
			<hr class="solided notopMargin">
			<div class="center">
				<button onclick="javascript:searchForm();" type="button" class="btn_2 black mgR12">
					<fmt:message key="common.button.searchSelect" />
				</button>
				<button type="button" class="btn_2 white l_reset" onclick="javascript:clearForm(this.form);">
					<fmt:message key="common.button.Reset" />
				</button>
			</div>
		</div>
	</s:form>
	<!--列表样式-->
	<div class="bt_wp">
		<display:table name="result.resultList" id="creditLimitList" class="ccm_table1" requestURI="" pagesize="20" size="result.totalCount" partialList="true">
			<display:column property="groupName" titleKey="ccm.creditlimit.groupname" />
			<display:column property="channelCode" titleKey="ccm.Channel.ChannelCode" />
			<display:column property="hotelCodes" titleKey="ccm.ReservationMonitorReport.PropertyCode" />
			<display:column property="minLimit" titleKey="ccm.BookingDepositReport.MinCredit" />
			<display:column  titleKey="ccm.ProfileList.ProfileBalance" >${creditLimitList.originalCreditLimit- creditLimitList.minLimit+creditLimitList.income-creditLimitList.totalRoomRev }</display:column>
			<display:column titleKey="ccm.UserActivityLog.Action">
				<c:forEach items="${SPRING_SECURITY_CONTEXT.authentication.principal.roles }" var="role">
					<c:if test="${role.roleId=='1302' }">
						<a href="/creditLimit_edit.do?creditLimitId=${creditLimitList.creditLimitId }" class="link mgR12"><fmt:message key="common.button.edit" /> </a>
						<a href="javascript:;" onclick="delRecord('${creditLimitList.creditLimitId }')" class="link del_ifself"><fmt:message key="common.button.delete"/></a>
					</c:if>
				</c:forEach>
			</display:column>
		</display:table>
	</div>
</div>
<script>
function searchForm(){
	if ($("#search").valid()) {
		$("#search").submit();
	}
}


/*是否删除本条*/
function delRecord(creditLimitId){
	var r = confirm("<fmt:message key='ccm.Channel.message.DeleteMessage'/>？");
	if (r == true) {
		location.href =setHotelIdForHref("/creditLimit_delete.do?creditLimitId=" + creditLimitId);
	}
}

	$(document)
			.ready(
					function() {
						$('#soc_hotelId').parent().append(headHtml);
						setTimeout(
								function() {
									//初始化
									$('#soc_hotelId')
											.multiselect(
													{
														dropRight : true,
														enableCaseInsensitiveFiltering : true,
														includeSelectAllOption : true,
														maxHeight : 300,
														maxWidth : 192,
														buttonWidth : '194px',
														allSelectedText : '<fmt:message key="common.select.selectAll"/>',
														selectAllText : '<fmt:message key="common.select.selectAll"/>',
														dSelectAllText : '<fmt:message key="common.select.Unselect"/>',
														nonSelectedText : '<fmt:message key="common.select.plesesSelect"/>',
													});
								}, 50);
						$('#soc_hotelId').next().remove();
					});
</script>
