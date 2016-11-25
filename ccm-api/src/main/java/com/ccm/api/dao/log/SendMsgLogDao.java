/**
 * 
 */
package com.ccm.api.dao.log;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.log.SendMsgLog;
import com.ccm.api.model.log.vo.UpDownPmsLogCriteria;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;

/**
 * @author Jenny
 * 
 */
public interface SendMsgLogDao extends GenericDao<SendMsgLog, String> {

	void createSendMsgLogTable(String hotelCode);

	/**
	 * 根据动态条件查询发送到PMS的队列
	 * 
	 * @param condition
	 * @return
	 */
	List<SendMsgLog> getSendMsgLogByCondit(SendMsgLog condition);

	/**
	 * 获取消息序列号
	 * 
	 * @return
	 */
	String getMsgId(String hotelCode);

	SendMsgLog getSendMsgLogById(String sendMsgLogId, String hotelCode);

	UpDownPmsLogResult searchList(UpDownPmsLogCriteria criteria);

	UpDownPmsLogResult searchListNoCount(UpDownPmsLogCriteria criteria);

	/**
	 * 根据日期与酒店编号删除sendmsglog 表的数据并返回影响记录的条数
	 * 
	 * @param date
	 * @param hotelcode
	 * @return
	 */
	int deleteSendMsgLogByCreatedTimeAndHotelCode(String createdTime, String hotelcode);

}
