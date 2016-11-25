/**
 * 
 */
package com.ccm.api.service.log.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.aop.ExceptionPointcut;
import com.ccm.api.dao.log.SendMsgLogDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.log.vo.UpDownPmsLogCriteria;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.log.ReceiveMsgLogManager;
import com.ccm.api.service.log.SendMsgLogManager;
import com.ccm.api.util.DateUtils;
import com.ccm.api.util.OwsOxiUtil;
import com.ccm.api.util.XMLUtil;
import com.ccm.oxi.result.RESULT;
import com.ccm.oxi.result.Success;

/**
 * @author Jenny
 * 
 */
@Service("sendMsgLogManager")
public class SendMsgLogManagerImpl extends GenericManagerImpl<SendMsgLog, String> implements SendMsgLogManager {

	private SendMsgLogDao sendMsgLogDao;

	private Map<String, String> orderQueue = new HashMap<String, String>();

	private final Log log1 = LogFactory.getLog(SendMsgLogManagerImpl.class);

	@Autowired
	public SendMsgLogManagerImpl(SendMsgLogDao sendMsgLogDao) {
		super(sendMsgLogDao);
		this.sendMsgLogDao = sendMsgLogDao;
	}

	@Resource
	private MasterDao masterDao;

	@Resource
	private ReceiveMsgLogManager receiveMsgLogManager;

	public void saveLogRemoveOrderQueue(SendMsgLog sendMsgLog) {
		saveSendMsgLogUpdateOrder(sendMsgLog);
		if (orderQueue.get(sendMsgLog.getSendMsgLogId()) != null) {
			orderQueue.remove(sendMsgLog.getSendMsgLogId());
		}
	}

	/**
	 * 保存发送PMS日志信息并更新接收日志状态
	 */
	public void saveSendUpdateReceiveMsgLog(Map<String, String> mapReq, Success status, String resultMessage, String action) {
		long s = System.currentTimeMillis();
		String hotelCode = mapReq.get("propertyName");
		String linkedMsgId = mapReq.get("transactionId");
		String receiveMsgLogId = mapReq.get("receiveMsgLogId");

		String msgId = "";
		RESULT result = new RESULT();
		result.setResortId(hotelCode);
		result.setResultMessage(resultMessage);
		result.setSuccess(status);
		result.setTimeStamp(DateUtils.dateToXmlDate(new Date()));

		try {
			String message = XMLUtil.JAXBParseToXmlNoSA(result, OwsOxiUtil.getReservationLabel(hotelCode, linkedMsgId, action));
			long e1 = System.currentTimeMillis();
			log1.info("jaxbSendMsg:" + (e1 - s) + "ms");
			SendMsgLog log = new SendMsgLog();
			log.setInterfaceId(OXIConstant.Interface);
			log.setHotelCode(hotelCode);
			log.setMessageType(MessageType.RESULT);
			log.setAction(action);
			log.setMsgId(msgId);
			log.setStatus(status.name());
			log.setMessage(message);
			log.setLinkedMsgId(linkedMsgId);
			saveSendMsgLogUpdateOrder(log);
			long e2 = System.currentTimeMillis();
			log1.info("saveSendMsgLog:" + (e2 - e1) + "ms");

			ReceiveMsgLog rml = new ReceiveMsgLog();
			rml.setReceiveMsgLogId(receiveMsgLogId);
			rml.setHotelCode(hotelCode);
			rml.setStatus(log.getStatus());
			receiveMsgLogManager.updateReceiveMsgLogStatus(rml);
			long e3 = System.currentTimeMillis();
			log1.info("updateReceiveMsgLogStatus:" + (e3 - e2) + "ms");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存发送PMS日志信息并更新接收日志状态
	 */
	@Override
	public void saveSendMsgLog(Map<String, String> mapReq, Success status, String resultMessage, String action) {

		String hotelCode = mapReq.get("propertyName");
		String linkedMsgId = mapReq.get("transactionId");

		String receiveMsgLogId = mapReq.get("receiveMsgLogId");
		ReceiveMsgLog receiveMsgLog = receiveMsgLogManager.getReceiveMsgLog(receiveMsgLogId, hotelCode);

		RESULT result = new RESULT();
		result.setResortId(hotelCode);
		result.setResultMessage(resultMessage);
		result.setSuccess(status);
		result.setTimeStamp(DateUtils.dateToXmlDate(new Date()));

		try {
			String message = XMLUtil.JAXBParseToXmlNoSA(result, OwsOxiUtil.getReservationLabel(hotelCode, linkedMsgId, action));

			SendMsgLog log = new SendMsgLog();
			log.setInterfaceId(OXIConstant.Interface);
			log.setHotelCode(hotelCode);
			log.setMessageType(MessageType.RESULT);
			log.setAction(action);
			log.setStatus(status.name());
			log.setMessage(message);
			log.setLinkedMsgId(linkedMsgId);
			// CCM消息编号
			log.setMsgId(receiveMsgLog.getExtMsgId());

			sendMsgLogDao.save(log);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建或更新下传消息表时同时更新订单表的downPmsLogStatus字段，此字段主要用于查询订单报表使用
	 */
	public void saveSendMsgLogUpdateOrder(SendMsgLog sml) {
		save(sml);

		if (MessageType.RESERVATION.equals(sml.getMessageType())) {
			masterDao.updateDownPmsMsgStatusByMasterId(sml.getOrderId(), sml.getStatus());
		}
	}

	public SendMsgLog getSendMsgLogByCondit(SendMsgLog cond) {
		List<SendMsgLog> sendList = sendMsgLogDao.getSendMsgLogByCondit(cond);
		if (sendList != null && !sendList.isEmpty()) {
			return sendList.get(0);
		}
		return null;
	}

	public SendMsgLog getOrderQueueByCondition(SendMsgLog cond) {
		List<SendMsgLog> sendList = sendMsgLogDao.getSendMsgLogByCondit(cond);
		if (sendList != null && !sendList.isEmpty()) {
			for (SendMsgLog sml : sendList) {
				if (orderQueue.get(sml.getSendMsgLogId()) != null) {
					continue;
				} else {
					orderQueue.put(sml.getSendMsgLogId(), sml.getSendMsgLogId());
					return sml;
				}
			}
		}
		return null;
	}

	public SendMsgLog getSendMsgLogById(String sendMsgLogId, String hotelCode) {
		return sendMsgLogDao.getSendMsgLogById(sendMsgLogId, hotelCode);
	}

	@ExceptionPointcut
	public String getMsgId(String hotelCode) {
		return sendMsgLogDao.getMsgId(hotelCode);
	}

	public UpDownPmsLogResult searchList(UpDownPmsLogCriteria criteria) {
		return sendMsgLogDao.searchList(criteria);
	}

}
