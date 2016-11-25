<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--remove 销售数据-->
<div id="RemoveSalesData" class="ccm-popup width750 zoom-anim-dialog mfp-hide">
<s:form id="roomStatusRemoveForm" action="roomStatus_ajaxRemoveSell.do" method="post">
<s:hidden id="sell_roonTypeCodes2" name="setvo.roonTypeCodes"/>
<s:hidden id="sell_channelIds2" name="setvo.channelIds"/>
<s:hidden id="sell_weeks2" name="setvo.weeks"/>
	<div class="pp_main">
		<div class="t_title"><fmt:message key="ccm.InventoryManagement.RemoveAllotment"/></div>
		<div class="pdA24">
			<ul class="list_input" id="saleFields">
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="ccm.InventoryManagement.RoomTypes"/>：</span>
					</div>
					<div class="i_input sell_roomType2">
						<div class="fm_bwp inline w240">
							<div class="sel_ele">
								<span class="select_all"><fmt:message key="common.select.selectAll"/></span>
								<span class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
							</div>
							<div class="fm_box">
								<c:forEach items="${roomTypeList }" var="rl" varStatus="idx">
									<label class="checkbox"> 
										<input type="checkbox" id="sell_roomType_${idx.index }" value="${rl.roomTypeCode }">
										<span class="checked">${rl.roomTypeCode}&nbsp;${rl.roomTypeName }
											<c:if test="${!empty rl.pmsCode}">_${rl.pmsCode}</c:if>
										</span> 
									</label>
								</c:forEach>
							</div>
						</div>
					</div>
				</li>
				<li>
				   <div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="ccm.InventoryManagement.Channels"/>：</span>
					</div>
					<div class="i_input sell_channel2">
						<div class="fm_bwp inline w240">
							<div class="sel_ele">
							</div>
							<div class="fm_box" style="height:80px;">
								<c:forEach items="${cgAllList}" var="rl" varStatus="idx">
									<label class="checkbox"> 
										<input type="radio" name="removeSell_channelId" id="sell_channel_${idx.index }" value="${rl.channelId }">
										<span class="checked">${rl.channelCode}</span> 
									</label>
								</c:forEach>
							</div>
						</div>
					</div>
				</li>
				<li class="c_rel">
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="common.time.BeginDate"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="sell_fromDate2" cssClass="fxt w120" name="setvo.fromDate"></s:textfield>
					</div>
					<div class="date_abs sell_weeks2">
						<div class="dateweek">
							<div class="d_wp">
								<span><fmt:message key="common.week.sunday"/></span> 
								<input name ="weekCB"  type="checkbox" value="0" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.monday"/></span> 
								<input name ="weekCB"  type="checkbox" value="1" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.tuesday"/></span> 
								<input name ="weekCB"  type="checkbox" value="2" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.wednesday"/></span> 
								<input name ="weekCB"  type="checkbox" value="3" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.thursday"/></span> 
								<input name ="weekCB"  type="checkbox" value="4" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.friday"/></span> 
								<input name ="weekCB"  type="checkbox" value="5" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.saturday"/></span> 
								<input name ="weekCB"  type="checkbox" value="6" checked>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="common.time.EndDate"/>：</span>
					</div>
					<div class="i_input">
						<s:textfield id="sell_toDate2" cssClass="fxt w120" name="setvo.toDate"></s:textfield>
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="ccm.InventoryManagement.ChannelBlockCode"/>：</span>
					</div>
					<div class="i_input">
						<input id="sell_blockcode2" type="text" class="fxt w120" name="setvo.blockCode">
					</div>
				</li>
				
			</ul>
			<hr class="dashed">
		</div>
		<div class="b_crl">
			<label class="checkbox inline" id="label_obPush">
				<input type="checkbox" id="sellPush" checked="checked"><fmt:message key="ccm.InventoryManagement.Distribute"/>
			</label>
			<button type="button" class="btn_2 green mgR6 sell_save"><fmt:message key="common.button.save"/></button>
			<button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
		</div>
	</div>
</s:form>
</div>
