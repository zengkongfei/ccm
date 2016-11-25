/**
 * 
 */
package com.ccm.api.service.log;

import java.util.Map;

import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.log.vo.UpDownPmsLogCriteria;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;
import com.ccm.api.service.base.GenericManager;
import com.ccm.oxi.result.Success;

/**
 * @author Jenny
 * 
 */
public interface SendMsgLogManager extends GenericManager<SendMsgLog, String> {

	/**
	 * 更新订单队列状态并移除MAP中的值
	 * 
	 * @param sendMsgLog
	 */
	void saveLogRemoveOrderQueue(SendMsgLog sendMsgLog);

	void saveSendUpdateReceiveMsgLog(Map<String, String> mapReq, Success status, String resultMessage, String action);

	public void saveSendMsgLog(Map<String, String> mapReq, Success status, String resultMessage, String action);

	/**
	 * 创建或更新下传消息表时同时更新订单表的downPmsLogStatus字段，此字段主要用于查询订单报表使用
	 * 
	 * @param sml
	 */
	void saveSendMsgLogUpdateOrder(SendMsgLog sml);

	SendMsgLog getSendMsgLogByCondit(SendMsgLog cond);

	/**
	 * 根据条件查询订单队列
	 * 
	 * @param cond
	 * @return
	 */
	SendMsgLog getOrderQueueByCondition(SendMsgLog cond);

	SendMsgLog getSendMsgLogById(String sendMsgLogId, String hotelCode);

	/**
	 * 获取消息序列号
	 * 
	 * @return
	 */
	String getMsgId(String hotelCode);

	UpDownPmsLogResult searchList(UpDownPmsLogCriteria criteria);

}
