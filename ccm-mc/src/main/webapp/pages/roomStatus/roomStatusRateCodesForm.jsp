<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--设置销售数据-->
<div id="SetRateCodes" class="ccm-popup zoom-anim-dialog mfp-hide" style="width:350px">
<s:form id="roomRateCodesForm" action="roomStatus_ajaxAllotmentSet.do" method="post">
<s:hidden id="sell_channelcodeperdate" name="roomStatusVO.channelCode"/>
<s:hidden id="sell_blockcodeperdate" name="roomStatusVO.blockCode"/>
<s:hidden id="sell_y" name="year"/>
<s:hidden id="sell_m" name="month"/>
<s:hidden id="sell_d" name="day"/>
<s:hidden  name="roomStatusVO.isModifyRates" value="y"/>
<s:hidden id="sell_type" name="roomStatusVO.type"/>
	<div class="pp_main">
		<div class="t_title"><fmt:message key="ccm.InventoryManagement.AllotmentAndFreeSell"/></div>
		<div class="pdA24">
			<ul class="list_input" id="rateFields">
										
					<li style="display:block">
						<div class="i_title">
							
							<span style="color: #1E50A2;font-weight: bold;" id="roomTypeCodeSpan">
							</span> 
							<span class=""><fmt:message key="ccm.InventoryManagement.ChannelAllotmentRateCodes"/></span>
						</div>
						<br>
						<br>
						<div class="i_input sell_rateCodes">
								<div class="fm_bwp inline w240">
									<div class="sel_ele">
										<span class="select_all"><fmt:message key="common.select.selectAll"/></span>
										<span class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
									</div>
									<div class="fm_box" id="rateCodeSetPerDate">
									</div>
								</div>
							</div>
						
				
					</li>
			</ul>
		
		</div>
		<div class="b_crl">
			<button type="button" class="btn_2 green mgR6 rate_save"><fmt:message key="common.button.save"/></button>
			<button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
		</div>
	</div>
</s:form>

</div>
