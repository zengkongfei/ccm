/**
 * 
 */
package com.ccm.api.service.order.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.StringUtils;

import com.ccm.api.model.constant.CharacterSet;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.constant.UpdateProfileConstant;
import com.ccm.api.model.enume.OXIStatusEnum;
import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.order.Master;
import com.ccm.api.service.log.SendMsgLogManager;
import com.ccm.api.service.order.OXIReservationNodesService;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtils;
import com.ccm.api.util.IncrementService;
import com.ccm.oxi.reservation.ReservationActionType;
import com.ccm.oxi.reservation.ReservationAlert;
import com.ccm.oxi.reservation.ReservationAlerts;
import com.ccm.oxi.reservation.ReservationTrace;
import com.ccm.oxi.reservation.ReservationTraces;

/**
 * @author Jenny
 * 
 */
@Service("oXIReservationNodesService")
public class OXIReservationNodesServiceImpl implements OXIReservationNodesService {

	@Resource
	private SendMsgLogManager sendMsgLogManager;
	@Resource
	private VelocityEngine velocityEngine;

	public ReservationAlerts buildReservationAlerts(ReservationActionType reservationActionType, String alerts) {
		ReservationAlert reservationAlert = new ReservationAlert();
		reservationAlert.setReservationActionType(reservationActionType);
		reservationAlert.setAlertCode(UpdateProfileConstant.COL);
		reservationAlert.setAlertDescription(alerts);
		reservationAlert.setAlertArea(MessageType.RESERVATION);
		ReservationAlerts result = new ReservationAlerts();
		result.getReservationAlert().add(reservationAlert);
		return result;
	}

	public ReservationTraces buildReservationTraces(String traceDept, String traces) {
		ReservationTrace reservationTrace = new ReservationTrace();
		reservationTrace.setTraceDepartment(traceDept);
		reservationTrace.setTraceDate(DateUtils.dateToXmlDate(new Date()));
		reservationTrace.setTraceText(traces);
		ReservationTraces result = new ReservationTraces();
		result.getReservationTrace().add(reservationTrace);
		return result;
	}

	public void buildCancelFintrx(Master master) {
		/**
		 * 当有”入帐信息“时，且订单无罚金时，生成FINTRX消息，消息中金额为负数的订单总额，客户列表中的“入帐代码”。
		 * FINTRX消息成功后，生成取消订单请求
		 */
		BigDecimal deduct = master.getDeduct();
		if (!StringUtils.hasText(master.getAccountMsgId()) || (deduct != null && deduct.compareTo(BigDecimal.ZERO) != 0)) {
			return;
		}
		buildFintrxMsg(master, "-" + master.getCharge(), "");
	}

	public String buildFintrxMsg(Master master, String total, String beginMsgId) {
		// 生成FINTRX消息
		String msgId = IncrementService.msgId(master.getHotelCode());
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("hotelCode", master.getHotelCode());
		modelMap.put("msgId", msgId);
		modelMap.put("masterId", master.getMasterId());
		String pmsId = master.getPmsId();
		if (pmsId.endsWith(".1")) {
			pmsId = pmsId.substring(0, pmsId.lastIndexOf(".1"));
		}
		modelMap.put("pmsId", pmsId);
		modelMap.put("amount", total);
		modelMap.put("currencyCode", master.getCurrencyCode());
		modelMap.put("transactionCode", master.getTransactionCode());
		String transactionId = "12345678";
		if (StringUtils.hasText(master.getPaymentTransaction())) {
			transactionId = master.getPaymentTransaction();
		}
		if (total != null && total.startsWith("-")) {
			transactionId = transactionId + ".1";
		}
		modelMap.put("transactionId", transactionId);
		String tmpUrl = "pushXmlTemplate/Fintrx.xml";
		String messageF;
		try {
			messageF = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tmpUrl, CharacterSet.defaultCode, modelMap);
		} catch (VelocityException e) {
			messageF = CommonUtil.getExceptionMsg(e, "");
		}
		SendMsgLog log = new SendMsgLog();
		log.setInterfaceId(OXIConstant.Interface);
		log.setHotelCode(master.getHotelCode());
		log.setMessageType(MessageType.FINTRX);
		log.setMsgId(msgId);
		log.setStatus(OXIStatusEnum.SEND_READY.getValue());
		log.setMessage(messageF);
		log.setLinkedMsgId(beginMsgId);
		log.setAction(MessageType.RESERVATION);
		log.setOrderId(master.getMasterId());
		sendMsgLogManager.saveSendMsgLogUpdateOrder(log);
		return msgId;
	}
}
