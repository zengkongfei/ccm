/**
 * 
 */
package com.ccm.api.dao.log;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.log.vo.UpDownPmsLogCriteria;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;

/**
 * @author Jenny
 * 
 */
public interface ReceiveMsgLogDao extends GenericDao<ReceiveMsgLog, String> {

	void createReceiveMsgLogTable(String hotelCode);

	ReceiveMsgLog addReceiveMsgLog(ReceiveMsgLog receiveMsgLog);
	
	void updateReceiveMsgLogStatus(ReceiveMsgLog receiveMsgLog);

	List<ReceiveMsgLog> getRMLByHotelCodeMsgId(ReceiveMsgLog obj);

	ReceiveMsgLog getReceiveMsgLog(String receiveMsgLogId, String hotelCode);

	UpDownPmsLogResult searchList(UpDownPmsLogCriteria criteria);
	
	/**
	 * 根据日期与酒店编号删除receivemsglog表的数据并返回影响记录的条数
	 * @param date
	 * @param hotelcode
	 * @return
	 */
	int deleteReceiveMsgLogByCreatedTimeAndHotelCode(String createdTime,String hotelcode);

	ReceiveMsgLog getPendingReceiveMsgLog(ReceiveMsgLog conditionMsgLog);

}
