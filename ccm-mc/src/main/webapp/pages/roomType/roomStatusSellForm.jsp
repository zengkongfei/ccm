<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--设置销售数据-->
<div id="SetSalesData" class="ccm-popup width750 zoom-anim-dialog mfp-hide">
<s:form id="roomStatusSellForm" action="roomStatus_ajaxSaveSell.do" method="post">
<s:hidden id="sell_roonTypeCodes" name="setvo.roonTypeCodes"/>
<s:hidden id="sell_channelIds" name="setvo.channelIds"/>
<s:hidden id="sell_weeks" name="setvo.weeks"/>
	<div class="pp_main">
		<div class="t_title">设置销售数据</div>
		<div class="pdA24">
			<ul class="list_input">
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text">房型：</span>
					</div>
					<div class="i_input sell_roomType">
						<div class="fm_bwp inline w240">
							<div class="sel_ele">
								<span class="select_all">全选</span>
								<span class="select_inverse">反选</span>
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
						<span class="text">渠道：</span>
					</div>
					<div class="i_input sell_channel">
						<div class="fm_bwp inline w240">
							<div class="sel_ele">
								<span class="select_all">全选</span>
								<span class="select_inverse">反选</span>
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
						<span class="text">开始时间：</span>
					</div>
					<div class="i_input">
						<s:textfield id="sell_fromDate" cssClass="fxt w120" name="setvo.fromDate" ></s:textfield>
					</div>
					<div class="date_abs sell_weeks">
						<div class="dateweek">
							<div class="d_wp">
								<span>周日</span> 
								<input type="checkbox" value="0" checked>
							</div>
							<div class="d_wp">
								<span>周一</span> 
								<input type="checkbox" value="1" checked>
							</div>
							<div class="d_wp">
								<span>周二</span> 
								<input type="checkbox" value="2" checked>
							</div>
							<div class="d_wp">
								<span>周三</span> 
								<input type="checkbox" value="3" checked>
							</div>
							<div class="d_wp">
								<span>周四</span> 
								<input type="checkbox" value="4" checked>
							</div>
							<div class="d_wp">
								<span>周五</span> 
								<input type="checkbox" value="5" checked>
							</div>
							<div class="d_wp">
								<span>周六</span> 
								<input type="checkbox" value="6" checked>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text">结束时间：</span>
					</div>
					<div class="i_input">
						<s:textfield id="sell_toDate" cssClass="fxt w120" name="setvo.toDate" ></s:textfield>
					</div>
				</li>
				<li>
					<div class="i_title">
						<span class=""></span>
						<span class="text">保留房：</span>
					</div>
					<div class="i_input">
						<input id="sell_allotment" type="text" class="fxt w30" name="setvo.allotment">
					</div>
				</li>
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
						<span class="text">酒店可售房开关：</span>
					</div>
					<div class="i_input">
						<label class="radio inline"> 
							<input type="radio" name="setvo.freeSell" value="true" checked>
							<span class="">开</span>
						</label>
						<label class="radio inline"> 
							<input type="radio" name="setvo.freeSell" value="false"> 
							<span class="">关</span> 
						</label>
					</div>
				</li>
			</ul>
		</div>
		<div class="b_crl">
			<label class="checkbox inline" id="label_obPush">
				<input type="checkbox" id="sellPush" checked="checked">推送
			</label>
			<button type="button" class="btn_2 green mgR6 sell_save">保存</button>
			<button type="button" class="btn_2 white popup-close">关闭</button>
		</div>
	</div>
</s:form>
</div>