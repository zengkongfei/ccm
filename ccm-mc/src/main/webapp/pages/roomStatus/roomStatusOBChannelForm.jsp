<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--设置OB渠道分类-->
<%
	String[] obChannelGroup = new String[]{"A","B","C","D","E"};
	request.setAttribute("obChannelGroup",obChannelGroup);
%>
<div id="SetOB" class="ccm-popup width900 zoom-anim-dialog mfp-hide">
	<div class="pp_main">
		<div class="t_title"><fmt:message key="ccm.InventoryManagement.OverbookingClass"/> </div>
		<div class="pdA24">
			<div class="ob_ch">
				<div class="title"><fmt:message key="ccm.InventoryManagement.ChannelsGrouped"/></div>
				<div class="box" id="unGroup">
				<s:iterator value="cgList" id="cg">
					<label class="checkbox inline" id="label_${channelCode }">
						<input type="checkbox" id="${channelId }" value="${channelCode }">
						<span class="">${channelCode }</span>
					</label>
				</s:iterator>
				</div>
			</div>
			<c:forEach items="${obChannelGroup }" var="obcg" varStatus="idx">
			<div class="ob_wp inline ${fn:length(obChannelGroup)-1 eq idx.index ?'noRmargin':''} " id="${obcg }">
				<div class="center pdA12">
					<button type="button" class="btn_3 blue mgR6" onclick="addChannel('${obcg }');"><fmt:message key="common.button.add"/></button>
					<button type="button" class="btn_3 red" onclick="removeChannel('${obcg }');"><fmt:message key="common.button.remove"/></button>
				</div>
				<div class="ob_ch">
					<div class="title">${obcg }</div>
					<div class="box" id="groupBox_${obcg }">
						<c:forEach items="${obGroupList }" var="obGroup">
							<c:if test="${obGroup.groupCode eq obcg }">
								<input id="groupId_${obcg }" type="hidden" value="${obGroup.groupId}"/>
								<c:forEach items="${obGroup.channelHotelList }" var="ch">
									<label class="checkbox inline" id="label_${ch.channelCode }">
										<input type="checkbox" id="${ch.channelId }" value="${ch.channelCode }">
										<span class="">${ch.channelCode }</span>
									</label>
								</c:forEach>
								<c:set value="${obGroup.percent }" var="percent"></c:set>
							</c:if>
						</c:forEach>
					</div>
					<hr class="dashed notopMargin">
					<div class="center mgB12">
						<input class="fxt w30" id="percent_${obcg }" type="text" value="${percent }">%
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		<div class="b_crl">
			<label class="checkbox inline" id="label_obPush">
				<input type="checkbox" id="obPush" value="1" checked="checked"><fmt:message key="ccm.InventoryManagement.Distribute"/>
			</label>
			<button type="button" class="btn_2 green mgR6" onclick="saveChannelGroup();"><fmt:message key="common.button.save"/></button>
			<button type="button" class="btn_2 white popup-close"><fmt:message key="common.button.close"/></button>
		</div>
	</div>
</div>
<jsp:include page="oBChannelGroup.jsp" flush="false"></jsp:include>
