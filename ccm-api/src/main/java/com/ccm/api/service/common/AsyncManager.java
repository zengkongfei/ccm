/**
 * 
 */
package com.ccm.api.service.common;

import com.ccm.api.model.hotel.CreditLimit;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.order.Master;

/**
 * @author Jenny
 * 
 */
public interface AsyncManager {

	void createSendReceivePmsLogTable(String hotelCode);

	void changeReceivePmsLog(ReceiveMsgLog receiveMsgLog);

	/**
	 * 发送邮件给酒店
	 * 
	 * @param order
	 */
	void owsOrder2pmsSendEmail(Master order);

	void sendCreditLimitEmail(String hotelId, String channelCode,
			String balance, String typename, CreditLimit cl);

	void sendAllotNotificationEmail(String hotelId, String channelCode,
			String orderId);

}
