<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
ul.inq_wp li .i_title{
	width:57px;
	float:left;
	text-align:right;
	padding:5px 6px 4px 6px;
}
.w240{
	width: 156px;
}

</style>
<!-- 内容区域-->
<div class="CCMmainConter" style="width: :960px;">
	<div class="title_wp"><fmt:message key="ccm.ReservationsManagment.ReservationDetail"/></div>
	<!--左右两列-->
	<div class="ccm_2wp clearfix">
		<div  style="width: 960px;">
			<div class="step_wp">
				<ul class="step3">
					<li ><span>1.</span> <fmt:message key="ccm.Reservations.QueryInformation"/></li>
					<li><span>2.</span> <fmt:message key="ccm.Reservations.RateQuery"/></li>
					<li class="active"><span>3.</span> <fmt:message key="ccm.Reservations.NewRSVN"/></li>
				</ul>
			</div>
		</div>
		<%@ include file="/common/messages.jsp"%>
	</div>
</div>
<!-- 内容区域 end-->
<script type="text/javascript">
</script>