<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
	.tdValue{width:300px;}
	.tdValue2{width:650px;}
</style>
<div class="pp_main">
    <div style="height:500px;overflow-y:scroll;">
    	<div style="text-align:left;padding-right:20px;">
    		<strong >予約確認番号：${order.masterId}</strong> &nbsp;&nbsp;&nbsp;&nbsp;
    		<strong >ステータス：${order.sta}</strong>
    	</div>
    	<div>
    		<table>
    			<tr>
    				<td>ホテル：</td>
    				<td class="tdValue">${order.hotelName}</td>
    				<td>日：</td>
    				<td class="tdValue"><s:date name="order.changed" format="yyyy-MM-dd HH:mm:ss" /></td>
    			</tr>
    			<tr>
    				<td>に：</td>
    				<td class="tdValue">${order.name4}</td>
    				<td>電話番号：</td>
    				<td class="tdValue">${order.telephone}</td>
    			</tr>
    			<tr>
    				<td>予約部：</td>
    				<td class="tdValue">预订部</td>
    				<td>ファックス：</td>
    				<td class="tdValue">${order.fax}</td>
    			</tr>
    			<tr>
    				<td>チャンネル：</td>
    				<td class="tdValue">${order.channelCode}</td>
    				<td>ホテルのメール：</td>
    				<td class="tdValue">${order.email}</td>
    			</tr>
    		</table>
    	</div>
    	<div style="border:1px solid #ddd;">
			${order.hotelName}
		</div>
		<div>
    		<table>
    			<tr>
    				<td>客の名前：</td>
    				<td class="tdValue2">${order.name4}</td>
    			</tr>
    			<tr>
    				<td>Mobile：</td>
    				<td class="tdValue2">${order.mobile}</td>
    			</tr>
    			    			<tr>
    				<td>Email：</td>
    				<td class="tdValue2">${order.masterEmail}</td>
    			</tr>
    			    			<tr>
    				<td>Address：</td>
    				<td class="tdValue2">${order.addressLine}</td>
    			</tr>
    			<tr>
    				<td>チェック-イン日：</td>
    				<td class="tdValue2"><s:date name="order.arr" format="yyyy-MM-dd" /></td>
    			</tr>
    			<tr>
    				<td>チェック-アウト日：</td>
    				<td class="tdValue2"><s:date name="order.dep" format="yyyy-MM-dd" /></td>
    			</tr>
    			<tr>
    				<td>ルームタイプ：</td>
    				<td class="tdValue2">${order.roomTypeName}</td>
    			</tr>
    			<tr>
    				<td>产品：</td>
    				<td class="tdValue2">${order.ratePlanDesc}</td>
    			</tr>
    			<tr>
    				<td>Guarantee Type：</td>
    				<td class="tdValue2">${order.payment}</td>
    			</tr>
    			<tr>
    				<td>大人：</td>
    				<td class="tdValue2">${order.gstno}</td>
    			</tr>
    			<tr>
    				<td>Number Of Rooms：</td>
    				<td class="tdValue2">${order.numberOfUnits}</td>
    			</tr>
    			<tr>
    				<td>1泊の適用料金：</td>
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
    				<td>部屋料金の選択：</td>
    				<td class="tdValue2">
    					${order.currencyCode} ${order.charge}
					</td>
    			</tr>
    			<tr>
					<td>注釈：</td>
					<td class="tdValue2">${order.srqs}</td>
				</tr>
				<tr>
					<td>注釈：</td>
					<td class="tdValue2">${fn:replace(order.ref, order.srqs, "")}</td>
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
					<td>—予約のキャンセルについて：</td>
					<td class="tdValue2">${order.cancelPolicyDesc}</td>
				</tr>
    		</table>
    	</div>
    	<hr>
    	<div>
			<div>
				<span>住所：${order.address}</span>
				| <span> 電話：${order.telephone}</span>
				| <br><span> ファックス：${order.fax}</span>
				| <span> 郵便番号：${order.postCode}</span>
			</div>
			<div class="copyright">Powered by CHINAonline</div>
		</div>
	</div>
	<div class="b_crl" style="text-align: center;">
		<button type="button" class="btn_2 white popup-close">工場を閉鎖し</button>
	</div>
</div>
<script src="<c:url value='/js/main.js'/>"></script>