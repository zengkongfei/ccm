<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 内容区域-->
<body style="background:#fff;">
<form id="orderForm" name="orderForm" action="/order_detail.action" method="post">
<c:set value="${order.orderRulePaymentType eq '03'}" var="isHotelPay"></c:set>
	<div class="keycontent">
		<div class="fright"></div>
		<div class="backwp dingdanxq printc1 printtext">
			<div class="wps">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ddxq">
					<tr>
						<td width="180" style="text-align: right; padding-right: 36px;">
							<img src="/img/lesukelogo-black.png">
							<p style="font-size: 22px; font-family: '微软雅黑', 'YaHei', '黑体', 'Hei';">
								<s:if test="order.status=='2805' || order.status=='2806' || order.status=='2808' || order.status=='2809'">
									酒店取消单
								</s:if>
								<s:else>酒店预订单</s:else>
							</p>
						</td>
						<td style="width: 245px;">
							<s:if test="order.companyType==\"3\"">
								<p class="printtwo">
									${order.supplierName}${order.contractCity}
								</p>
								<p>
									联系人：${order.supplierContactName}
								</p>
								<p>
									联系电话：${order.supplierContactTel}
								</p>
								<p>
									传真号码：${order.supplierContactFax}
								</p>
							</s:if>
							<s:else>
								<p class="printtwo">
									${order.companyName }${order.contractCity}
								</p>
								<p>
									联系人：${order.hotelContactName }
								</p>
								<p>
									联系电话：${order.hotelContactTel }
								</p>
								<p>
									传真号码：${order.hotelContactFax }
								</p>
							</s:else>
						</td>
						<td style="padding-left: 36px;">
							<p class="printtwo">
								乐宿客
							</p>
							<p>
								联系电话：400-021-1960
							</p>
							<p>
								传真号码：021-33250563
							</p>
						</td>
					</tr>
				</table>
			</div>
			<div class="linef print" style="text-align: left;">
				<span class="ls">订单编码:${order.orderNo }</span>
				<span class="ls"> 订单时间：<s:date name="order.createDate" format="yyyy-MM-dd （HH:mm:ss）"/></span>
				<span class="ls"> 订单总额：${order.totalamount }元</span>
				<span class="ls"> 订单状态：<appfuse:label dictKey="orderStatuss" codeNo="${order.status}" style="margin-right:5px;"></appfuse:label>| <appfuse:label dictKey="confirmStatus" codeNo="${order.confirmStatus}"></appfuse:label><s:if test="order.status=='2805' || order.status=='2806' || order.status=='2809' || order.status=='2807'"><label style="margin-left: -25px;">取消</label></s:if></span>
			</div>
			<div class="backsectitle printtitle">
				客人入住信息
			</div>
			<div class="wps">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ddxq">
					<tr>
						<td class="chd">
							客人姓名：
						</td>
						<td>
							<s:iterator value="guestList">
								${guestName } &nbsp; &nbsp;
							</s:iterator> （联系人：${order.b2cUserName }&nbsp;&nbsp;&nbsp;电话：${order.b2cUserTel }）
						</td>
					</tr>
					<tr>
						<td class="chd">
							住店日期：
						</td>
						<td>
							<s:date name="order.checkinDate" format="yyyy-MM-dd"/>至<s:date name="order.checkoutDate" format="yyyy-MM-dd"/>（${order.days }晚）
						</td>
					</tr>
					<tr>
						<td class="chd">
							入住房型：
						</td>
						<td>
							${order.hotelName }/${order.roomName }（${order.roomNum }间）
						</td>
					</tr>
					<s:if test="order.bedType!=null && order.bedType!=\"\"">
					<tr>
	                    <td class="chd">床型：</td>
	                    <td>${order.bedType}</td>
                  	</tr>
                  	</s:if>
					<tr>
						<td class="chd">
							房价信息：
						</td>
						<td>
							<p>
								${order.rateDefineName}(<appfuse:label dictKey="breakfastType" codeNo="${order.breakfastType}" style="margin-right:0px;vertical-align: baseline;"></appfuse:label>)
							</p>
							<p>
								<ul class="priceli">
									<s:iterator value="priceDetail" status="st">
			                        <li class="print">
			                          <div class="date"><s:date name="priceDate" format="MM月dd日" /></div>
			                          <div class="pinf">
			                          	<div>
			                          		<span class="pricename">卖价：</span>
			                          		<span class="price">${price}</span>
			                          	</div>
			                          	<c:if test="${!isHotelPay }">
			                        	<div>
			                        		<span class="pricename">结算价：</span>
                          					<span class="price">${costPrice==null?'0.0':costPrice}</span>
                            			</div> 
                            			</c:if>
                          			  </div>
                          			 </li>
			                        </s:iterator>
			                    </ul>
							</p>
						</td>
					</tr>
				</table>
			</div>
			<div class="backsectitle printtitle">
				订单支付信息
			</div>
			<div class="wps">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ddxq">
					<tr>
						<td class="chd">
							支付方式：
						</td>
						<td>
							<appfuse:label dictKey="paymentType" codeNo="${order.orderRulePaymentType}"></appfuse:label>
						</td>
					</tr>
					<tr>
						<td class="chd">
							订单总额：
						</td>
						<td>
							${order.totalamount }元
						</td>
					</tr>
				<c:if test="${!isHotelPay }">
					<!-- 开票金额：根据发票规则，如果是乐宿客开发票，不显示。如果是酒店前台开具，显示 -->
					<s:if test="!order.invoiceType">
								<tr>
									<td class="chd">开票金额：</td>
									<td><span class="list">
									<s:if test="order.eventType==4">
										${order.invoiceAmount==null ? orderPay.status==1 ? order.totalamount : "0" : order.invoiceAmount}
									</s:if>
									<s:else>
										${order.invoiceAmount==null ? orderPay.status==1 ? orderPay.cost : "0" : order.invoiceAmount}
									</s:else>元</span></td>
								</tr>
							</s:if>
	                <tr>
	                    <td class="chd">结算价总额：</td>
	                    <td><span class="list">${order.settleAmount!=null?order.settleAmount:0.0}元</span> </td>
	                </tr>
	             </c:if>
					<tr>
						<td class="chd">
							本单取消规则：
						</td>
						<td>
						<c:if test="${!isHotelPay }">
							${order.cancelRuleName }
						</c:if>
						<c:if test="${isHotelPay }">	
							客人可在入住日期18:00前，登陆乐宿客网站取消订单
						</c:if>
						</td>
					</tr>
				</table>
			</div>
			<div class="backsectitle printtitle">
				其他信息
			</div>
			<div class="wps">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="ddxq">
					<tr>
						<td class="chd">
							客人特别要求：
						</td>
						<td>
							${order.remark }&nbsp;
						</td>
					</tr>
					<tr>
						<td class="chd">
							乐宿客备注：
						</td>
						<td>
							<s:if test="order.invoiceType">客人发票由乐宿客提供</s:if><s:else>请按订单开票金额为客人开具发票</s:else>&nbsp;&nbsp;${order.promotionOrderMark}&nbsp;
						</td>
					</tr>
				</table>
			</div>
			<div class="wps">
				<div class="info2 prints">
					<p>
						<strong>注意：</strong>
					</p>
					<p>
						1、 感谢贵酒店的支持，请根据订单要求为客人确认并安排房间。
					</p>
					<p>
						2、 此单房费由乐宿客支付，其他杂费由客人自理。
					</p>
					<p>
						3、如对订单有任何疑问，可直接联系客人确认订单信息，或拨打乐宿客客服电话。
					</p>
				</div>
			</div>
			<!-- 签字-->
            <div class="blancket20"></div>
           	<div class="mrd12">
           	<div class="mpt6 alright">酒店确认人&nbsp;_____________________ &nbsp;&nbsp;&nbsp;&nbsp;确认时间&nbsp;______________________________</div>
           	<div class="blancket20"></div>
           	<!-- 签字 end--> 
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="backwp dingdanxq">
			<div class="printbt">
				<div class="bt">
					<a href="#" id="print"><img src="/img/print.png">
					</a>
				</div>
			</div>
		</div>
	</div>
</form>
<!-- 内容区域 end-->
<script type="text/javascript" src="/js/jquery.PrintArea.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#print').click(function() {
			$("div.keycontent").printArea();
			$(this).hide();
		});
	});
</script>
</body>