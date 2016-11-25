<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<div class="title_wp">
	<fmt:message key="ccm.CustomerFeedback" />
	<div class="bt"></div>
</div>
<div class="c_whitebg">

	<!--列表样式-->
	<div class="bt_wp">
		<display:table name="result.resultList" id="feedBack" class="ccm_table1" requestURI="" pagesize="20" size="result.totalCount" partialList="true">
			<display:column property="companyName"  titleKey="ccm.CustomerFeedback.CompanyName" />
			<display:column property="name" titleKey="ccm.ReservationsManagment.GuestName" />
			<display:column property="mobile" titleKey="ccm.CustomerFeedback.MobilePhone"/>
			<display:column property="email"  titleKey="ccm.CustomerFeedback.Email" />
			<display:column property="createdTime" titleKey="ccm.ReservationMonitorReport.CreatedTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
			<display:column  titleKey="ccm.UserActivityLog.Action">
				<a href="/feedBack_view.do?feedBackId=${feedBack.feedBackId}" class="link mgR12"><fmt:message key="common.View"/></a>
			</display:column>
		</display:table>
	</div>
</div>
