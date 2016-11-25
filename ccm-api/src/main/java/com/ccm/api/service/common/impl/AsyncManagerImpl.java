/**
 * 
 */
package com.ccm.api.service.common.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.log.ReceiveMsgLogDao;
import com.ccm.api.dao.log.SendMsgLogDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.hotel.CreditLimit;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.log.ReceivePmsLog;
import com.ccm.api.model.order.Master;
import com.ccm.api.service.common.AsyncManager;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.email.MasterSendLogManager;
import com.ccm.api.service.log.ReceivePmsLogManager;

/**
 * @author Jenny
 * 
 */
@Service("asyncManager")
public class AsyncManagerImpl implements AsyncManager {

	private Log log = LogFactory.getLog(AsyncManagerImpl.class);

	@Resource
	private SendMsgLogDao sendMsgLogDao;
	@Resource
	private ReceiveMsgLogDao receiveMsgLogDao;
	@Resource
	private HotelDao hotelDao;

	@Resource
	private ReceivePmsLogManager receivePmsLogManager;
	@Resource
	private MasterSendLogManager masterSendLogManager;
	@Resource
	private EmailManager emailManager;
	
	@Async
	public void createSendReceivePmsLogTable(String hotelCode) {
		try {
			sendMsgLogDao.createSendMsgLogTable(hotelCode);
			receiveMsgLogDao.createReceiveMsgLogTable(hotelCode);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Async
	public void changeReceivePmsLog(ReceiveMsgLog receiveMsgLog) {
		ReceivePmsLog rpl = new ReceivePmsLog();
		rpl.setInterfaceId(receiveMsgLog.getInterfaceId());
		rpl.setHotelCode(receiveMsgLog.getHotelCode());
		rpl.setReceiveMsgLogLastTime(new Date());
		receivePmsLogManager.saveOrupdateReceivePmsLog(rpl);
	}

	/**
	 * 发送邮件给酒店
	 */
	@Async
	public void owsOrder2pmsSendEmail(Master order) {
		String lang = LanguageCode.MAIN_LANGUAGE_CODE;
		HotelVO hvo = hotelDao.getHotelVoByHotelId(order.getHotelId(), lang);
		if (hvo != null && StringUtils.hasText(hvo.getEmail())) {
			// 淘宝新建订单30分钟后再决定是否发送邮件
			if ("TAOBAO".equals(order.getChannel()) && OrderStatus.ADD.equals(order.getSta())) {
				masterSendLogManager.recordSendEmailOfTBADD(hvo.getEmail(), order.getMasterId(), lang, order.getSta());
			} else {
				masterSendLogManager.recordSendEmail(hvo.getEmail(), hvo.getHotelName(), order.getMasterId(), lang, order.getSta());
			}
		}
	}
	
	@Override
	@Async
	public void sendCreditLimitEmail(String hotelId,String channelCode,String balance,String typename,CreditLimit cl){
		emailManager.sendCreditLimitEmail(hotelId,channelCode,balance,typename,cl);
	}
	@Override
	@Async
	public void sendAllotNotificationEmail(String hotelId,String channelCode,String orderId){
		emailManager.sendAllotNotificationEmail(hotelId,channelCode,orderId);
	}
}
