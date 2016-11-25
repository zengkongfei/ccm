<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<div class="CCMmainConter w1200">
	<div class="title_wp">
		<!-- <div class="bt"> <a href="0HOTEL-2.html" class="btn_2 blue">新建</a> </div>-->
		<fmt:message key="ccm.Rates.RatesManagment"/>
	</div>

	<!--左右两列-->
	<div class="ccm_2wp clearfix">
		<div class="ccm_left" style="position: relative;">
			<div class="menulayerMask"></div>
			<div class="lt_menu2">
				<ul class="mlist">
				</ul>
				<div class="newwp">
					<button type="button" class="btn_2 blue"><fmt:message key="ccm.Rates.RateSetupNew"/></button>
					<a href="#" class="ccm-popup-click"></a>
				</div>
			</div>
		</div>

		<div class="ccm_2col">
			<div class="ccm_right">
				<div class="step_wp">
					<ul class="step3">
						<li><span>1.</span><fmt:message key="ccm.Rates.RateHeader"/></li>
						<li><span>2.</span><fmt:message key="ccm.Rates.RateDetail"/></li>
						<li class="active"><span>3.</span><fmt:message key="ccm.Rates.OK"/></li>
					</ul>
				</div>
				<div class="ccm_right">
					<div style="padding: 36px 48px;">
						<div class="smilewp">
							<div class="b_info"><fmt:message key="ccm.Rates.message.RateCodeSetupFinished"/>！</div>
							<p>
								<a class="link" href="/roomRate_list.do?ratePlanId=${ratePlanId }"><fmt:message key="ccm.Rates.message.ManuallyEdit"/></a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
