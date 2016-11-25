<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--设置销售数据-->
<div id="SetSalesData" class="ccm-popup width750 zoom-anim-dialog mfp-hide">
<s:form id="roomStatusSellForm" action="roomStatus_ajaxSaveSell.do" method="post">
<s:hidden id="sell_roonTypeCodes" name="setvo.roonTypeCodes"/>
<s:hidden id="sell_channelIds" name="setvo.channelIds"/>
<s:hidden id="sell_weeks" name="setvo.weeks"/>
<s:hidden id="sell_rateCodes" name="setvo.jsonRateCodesWithBlock" value=""/>
	<div class="pp_main">
		<div class="t_title"><fmt:message key="ccm.InventoryManagement.AllotmentAndFreeSell"/></div>
		<div class="pdA24">
			<ul class="list_input" id="saleFields">
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="ccm.InventoryManagement.RoomTypes"/>：</span>
					</div>
					<div class="i_input sell_roomType">
						<div class="fm_bwp inline w240">
							<div class="sel_ele">
								<span class="select_all" onclick="addBlockRateCodesConfigForAllRoomTypes();"><fmt:message key="common.select.selectAll"/></span>
								<span class="select_inverse" onclick="inverseBlockRateCodesConfigForAllRoomTypes();"><fmt:message key="common.select.Unselect"/></span>
							</div>
							<div class="fm_box">
								<c:forEach items="${roomTypeList }" var="rl" varStatus="idx">
									<label class="checkbox"> 
										<input type="checkbox" name ="roomTypeCodeCB" onclick="addBlockRateCodesConfigForOneRoomType(this,'${rl.roomTypeCode}');" id="sell_roomType_${idx.index }" value="${rl.roomTypeCode }">
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
					<div class="i_input sell_channel">
						<div class="fm_bwp inline w240">
							<div class="sel_ele">
								<span class="select_all"><fmt:message key="common.select.selectAll"/></span>
								<span class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
							</div>
							<div class="fm_box" style="height:80px;">
								<c:forEach items="${cgAllList}" var="rl" varStatus="idx">
									<label class="checkbox"> 
										<input type="checkbox" id="sell_channel_${idx.index }" value="${rl.channelId }">
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
						<s:textfield id="sell_fromDate" cssClass="fxt w120" name="setvo.fromDate"  onchange="getRateCodes();"></s:textfield>
					</div>
					<div class="date_abs sell_weeks">
						<div class="dateweek">
							<div class="d_wp">
								<span><fmt:message key="common.week.sunday"/></span> 
								<input name ="weekCB" onchange="getRateCodes();" type="checkbox" value="0" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.monday"/></span> 
								<input name ="weekCB" onchange="getRateCodes();" type="checkbox" value="1" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.tuesday"/></span> 
								<input name ="weekCB" onchange="getRateCodes();" type="checkbox" value="2" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.wednesday"/></span> 
								<input name ="weekCB" onchange="getRateCodes();" type="checkbox" value="3" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.thursday"/></span> 
								<input name ="weekCB" onchange="getRateCodes();" type="checkbox" value="4" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.friday"/></span> 
								<input name ="weekCB" onchange="getRateCodes();" type="checkbox" value="5" checked>
							</div>
							<div class="d_wp">
								<span><fmt:message key="common.week.saturday"/></span> 
								<input name ="weekCB" onchange="getRateCodes();" type="checkbox" value="6" checked>
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
						<s:textfield id="sell_toDate" cssClass="fxt w120" name="setvo.toDate" onchange="getRateCodes();"></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="ccm.InventoryManagement.Allotment"/>：</span>
					</div>
					<div class="i_input">
						<input id="sell_allotment" type="text" class="fxt w30" name="setvo.allotment">
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="ccm.InventoryManagement.ChannelCutOffDays"/>：</span>
					</div>
					<div class="i_input">
						<input id="sell_cutOffDays" type="text" class="fxt w30" name="setvo.cutOffDays">
					</div>
				</li>
				
										
				<c:forEach items="${roomTypeList}" var="rl" varStatus="idx">					
					<li id="${rl.roomTypeCode}_rateCodeContainer" name="rateCodeContainer" style="display:none">
						<div class="i_title">
							
							<span style="color: #1E50A2;font-weight: bold;">${rl.roomTypeCode}
													
							</span> 
							<span class=""><fmt:message key="ccm.InventoryManagement.ChannelRateCodesSetup"/></span>
						</div>
						<div class="i_input sell_rateCodes">
								<div class="fm_bwp inline w240">
									<div class="sel_ele">
										<span class="select_all" onclick="getChosenRateCodeToBlock();"><fmt:message key="common.select.selectAll"/></span>
										<span class="select_inverse"><fmt:message key="common.select.Unselect"/></span>
									</div>
									<div class="fm_box" id="rateCodeSet_${rl.roomTypeCode}" name="rateCodeSet">
										
									</div>
								</div>
							</div>
						
				
					</li>
				</c:forEach>
			
				
				<!-- 
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text">有效时间：</span>
					</div>
					<div class="i_input">
						<s:textfield id="sell_effectiveTime" cssClass="fxt w90" name="setvo.effectiveTime" readonly="true"></s:textfield>
						<span>-</span>
						<s:textfield id="sell_expireTime" cssClass="fxt w90" name="setvo.expireTime" readonly="true"></s:textfield>
					</div>
				</li>
				 -->
			</ul>
			<hr class="dashed">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="ccm.InventoryManagement.FreeSell"/>：</span>
					</div>
					<div class="i_input">
						<label class="radio inline"> 
							<input type="radio" name="setvo.freeSell" value="true" checked>
							<span class=""><fmt:message key="ccm.InventoryManagement.Open"/></span>
						</label>
						<label class="radio inline"> 
							<input type="radio" name="setvo.freeSell" value="false"> 
							<span class=""><fmt:message key="ccm.InventoryManagement.Close"/></span> 
						</label>
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="ccm.InventoryManagement.ChannelBlockCode"/>：</span>
					</div>
					<div class="i_input">
						<input id="sell_blockcode" type="text" class="fxt w120" name="setvo.blockCode">
					</div>
				</li>
				
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text"><fmt:message key="ccm.InventoryManagement.ChannelBlockCodeToPMS"/>：</span>
					</div>
					<div class="i_input">
						<label class="radio inline"> 
							<input type="radio" name="setvo.isSendToPMS" value="true" checked>
							<span class=""><fmt:message key="ccm.InventoryManagement.Open"/></span>
						</label>
						<label class="radio inline"> 
							<input type="radio" name="setvo.isSendToPMS" value="false"> 
							<span class=""><fmt:message key="ccm.InventoryManagement.Close"/></span> 
						</label>
					</div>
				</li>
				
			</ul>
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
