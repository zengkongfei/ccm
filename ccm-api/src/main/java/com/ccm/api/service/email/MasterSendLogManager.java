package com.ccm.api.service.email;

import com.ccm.api.model.email.MasterSendLog;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.sms.SmsSend;
import com.ccm.api.service.base.GenericManager;

public interface MasterSendLogManager extends GenericManager<MasterSendLog, String> {

	public void saveMasterSendLog(MasterSendLog masterSendLog);

	public MasterSendLog getMasterSendLogById(String masterSendLogId);

	public MasterSendLog getMasterSendLogBySmsId(String smsSendId);

	/**
	 * OWS,WBE第一次发送邮件确认函
	 * 
	 * @param email
	 * @param hotelName
	 * @param masterId
	 * @param lang
	 * @param sta
	 */
	void recordSendEmail(String email, String hotelName, String masterId, String lang, String sta);

	/**
	 * 重发订单确认函（数据取自表:mastersendlog）
	 * 
	 * @param email
	 * @param msl
	 * @param lang
	 * @return
	 */
	SmsSend reSendEmail(String email, MasterSendLog msl, String lang);

	/**
	 * 每5分执行一次:WBE订单发送未发送成功的邮件
	 */
	void retrySendEmail();

	/**
	 * 每1分执行一次:淘宝新建订单30分钟后，如PMS下传消息的“消息状态”为IGNORE，就不向酒店发送邮件确认函。
	 */
	void sendEmailOfTBADD();

	/**
	 * 记录淘宝新建订单的邮件确认函但不发送
	 * 
	 * @param email
	 * @param masterId
	 * @param lang
	 * @param sta
	 */
	void recordSendEmailOfTBADD(String email, String masterId, String lang, String sta);

	/**
	 * 保存订单确认函快照
	 * 
	 * @param order
	 * @param smssendId
	 * 
	 * @param lang
	 * @return
	 */
	MasterSendLog saveSendEmailLog(Master order, String smssendId, String lang);

}
