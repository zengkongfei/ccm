/**
 * 
 */
package com.ccm.api.service.order.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.order.OrderPayDao;
import com.ccm.api.model.constant.GuaranteeCode;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.enume.OperUserType;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterPay;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.order.OrderChangesLogManager;
import com.ccm.api.service.order.OrderManager;
import com.ccm.api.service.order.OrderPayManager;
import com.ccm.api.service.order.ReservationService;
import com.ccm.api.util.RSAEncrypt;

/**
 * 
 * 
 * @author Jenny.Liao
 * 
 */
@Service("orderPayManager")
public class OrderPayManagerImpl extends GenericManagerImpl<MasterPay, String> implements OrderPayManager {

	private Log logPayRefund = LogFactory.getLog("payRefund");

	@Resource
	private OrderPayDao orderPayDao;
	@Resource
	private OrderManager orderManager;

	@Resource
	private ReservationService reservationService;
	@Resource
	private OrderChangesLogManager orderChangesLogManager;

	@Autowired
	public OrderPayManagerImpl(OrderPayDao orderPayDao) {
		super(orderPayDao);
		this.orderPayDao = orderPayDao;
	}

	public MasterPay getOrderPayByOrderId(String orderId) {
		return orderPayDao.getOrderPayByOrderId(orderId);
	}

	public Map<String, String> completePayCallback(MasterPay op) {
		logPayRefund.info("支付订单：" + op.getMasterId());
		Map<String, String> resultMap = new HashMap<String, String>();
		MasterPay orderPay = this.get(op.getOrderPayId());
		if (orderPay != null && "1".equals(orderPay.getStatus())) {
			resultMap.put("Y", "支付id：" + op.getOrderPayId() + "已经更新。");
			logPayRefund.info("支付订单号：" + orderPay.getMasterId() + "支付状态：" + "1".equals(orderPay.getStatus()));
			return resultMap;
		}

		// 更新支付表
		MasterPay pay = this.save(op);
		logPayRefund.info("支付订单号：" + pay.getMasterId() + "支付状态：" + "1".equals(pay.getStatus()));
		resultMap.put("Y", "支付订单号：" + pay.getMasterId() + "更新支付状态" + "1".equals(pay.getStatus()));

		// 更新总订单表
		Master m = orderManager.getOrderByOrderNo(op.getMasterId());
		if (m != null) {
			m.setSta(OrderStatus.PAID);
			m.setPayment(GuaranteeCode.DPGTD);
			m.setUpdatedBy(OperUserType.WBE.value());
			orderManager.updateMasterDealRestype(m);
		}
		// 更新子订单表，并发送给PMS
		List<Master> childList = orderManager.getOrderChildByOrderId(op.getMasterId());
		for (Master child : childList) {
			child.setSta(OrderStatus.PAID);
			child.setPayment(GuaranteeCode.DPGTD);
			child.setUpdatedBy(OperUserType.WBE.value());
			orderManager.updateMasterDealRestype(child);
			orderChangesLogManager.saveMasterChangesLog(child);
			child.setCardNumber(RSAEncrypt.decrypt(child.getCardNumber()));
			reservationService.buildWBEOrder(child);
		}
		logPayRefund.info("支付订单号：" + op.getMasterId() + "订单表更新支付状态完成");

		return resultMap;
	}

}
