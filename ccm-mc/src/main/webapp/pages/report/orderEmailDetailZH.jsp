<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
	.tdValue{width:300px;}
	.tdValue2{width:650px;}
</style>
<div class="pp_main">
    <div style="height:500px;overflow-y:scroll;">	
    	<div>
    		<table>
    			<tr>
    				<td>订单号：</td>
    				<td class="tdValue">${order.masterId}</td>
    				<td>订单状态：</td>
    				<td class="tdValue">${order.sta}</td>
    			</tr>
    			<tr>
    				<td>酒店：</td>
    				<td class="tdValue">${order.hotelName}</td>
    				<td>日期：</td>
    				<td class="tdValue"><s:date name="order.changed" format="yyyy-MM-dd HH:mm:ss" /></td>
    			</tr>
    			<tr>
    				<td>致：</td>
    				<td class="tdValue">${order.name4}</td>
    				<td>电话：</td>
    				<td class="tdValue">${order.telephone}</td>
    			</tr>
    			<tr>
    				<td>自：</td>
    				<td class="tdValue">预订部</td>
    				<td>传真：</td>
    				<td class="tdValue">${order.fax}</td>
    			</tr>
    			<tr>
    				<td>渠道：</td>
    				<td class="tdValue">${order.channelCode}</td>
    				<td>酒店邮箱：</td>
    				<td class="tdValue">${order.email}</td>
    			</tr>
    		</table>
    	</div>
    	<div style="border:1px solid #ddd;">
    		<br/>
			感谢您在${order.hotelName}预订房间，并且确认以下预订信息。
			<br/><br/>
		</div>
		<div>
    		<table>
    			<tr>
    				<td>客人姓名：</td>
    				<td class="tdValue2">${order.name4}</td>
    			</tr>
    			<tr>
    				<td>手机：</td>
    				<td class="tdValue2">${order.mobile}</td>
    			</tr>
    			    			<tr>
    				<td>邮箱：</td>
    				<td class="tdValue2">${order.masterEmail}</td>
    			</tr>
    			    			<tr>
    				<td>地址：</td>
    				<td class="tdValue2">${order.addressLine}</td>
    			</tr>
    			<tr>
    				<td>入住日期：</td>
    				<td class="tdValue2"><s:date name="order.arr" format="yyyy-MM-dd" /></td>
    			</tr>
    			<tr>
    				<td>退房日期：</td>
    				<td class="tdValue2"><s:date name="order.dep" format="yyyy-MM-dd" /></td>
    			</tr>
    			<tr>
    				<td>房型：</td>
    				<td class="tdValue2">${order.roomTypeName}</td>
    			</tr>
    			<tr>
    				<td>产品：</td>
    				<td class="tdValue2">${order.ratePlanDesc}</td>
    			</tr>
    			<tr>
    				<td>担保方式：</td>
    				<td class="tdValue2">${order.payment}</td>
    			</tr>
    			<tr>
    				<td>人数：</td>
    				<td class="tdValue2">${order.gstno}</td>
    			</tr>
    			<tr>
    				<td>房间数：</td>
    				<td class="tdValue2">${order.numberOfUnits}</td>
    			</tr>
    			<tr>
    				<td>日   房   价：</td>
    				<td class="tdValue2">
    					<s:iterator value="order.mrList">
							<span class="nowp">
								<s:date name="date" format="yyyy-MM-dd" />：${order.currencyCode}<s:property value="setrate" />
							</span>
							<span>&nbsp;</span>
						</s:iterator>
					</td>
    			</tr>
    			<tr>
    				<td>套餐：</td>
    				<td class="tdValue2">
    					<s:iterator value="order.mpList">
							<s:if test="isDynamic">
								<s:if test="#ii>1">;</s:if>
								<s:property value="packageName" />
								<s:property value="quantity" />份:
								共<s:property value="amount" />元;
								<s:set value="#ii+1" name="ii">
								</s:set>
							</s:if>
						</s:iterator>
					</td>
    			</tr>
    			<tr>
    				<td>税费：</td>
    				<td class="tdValue2">
    					<s:set value="1" name="jj"></s:set>
						<s:iterator value="order.mpList">
						<s:if test="!isDynamic">
							<s:if test="#jj>1">;</s:if>
							${packageName}:共<s:property value="amount"/>元
							<s:set value="#jj+1" name="jj"></s:set>
						</s:if>
						</s:iterator>
					</td>
    			</tr>
    			<tr>
    				<td>总房价：</td>
    				<td class="tdValue2">
    					${order.currencyCode} ${order.charge}
					</td>
    			</tr>
    			<tr>
					<td>特 殊  需 求：</td>
					<td class="tdValue2">${order.srqs}</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td class="tdValue2">${fn:replace(order.ref, order.srqs, "")}</td>
				</tr>
				
				<tr>
					<td colspan="2"><hr/></td>
				</tr>
				
				<tr>
					<td>—入住时间描述：</td>
					<td class="tdValue2">${order.checkInTimeDesc}</td>
				</tr>
				<tr>
					<td>—退房时间描述：</td>
					<td class="tdValue2">${order.checkOutTimeDesc}</td>
				</tr>
				<tr>
					<td>—取消政策描述：</td>
					<td class="tdValue2">${order.cancelPolicyDesc}</td>
				</tr>
    		</table>
    	</div>
    	<hr>
    	<div>
			<div align="center">
				<span>地址：${order.address}</span>
				| <span> 电话：${order.telephone}</span>
				| <br><span> 传真：${order.fax}</span>
				| <span> 邮编：${order.postCode}</span>
			</div>
			<div class="copyright" align="center">Powered by CHINAonline</div>
		</div>
	</div>
	<div class="b_crl" style="text-align: center;">
		<button type="button" class="btn_2 white popup-close">关闭</button>
	</div>
</div>
<script src="<c:url value='/js/main.js'/>"></script>