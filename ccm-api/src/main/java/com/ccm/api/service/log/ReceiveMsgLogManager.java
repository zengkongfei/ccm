/**
 * 
 */
package com.ccm.api.service.log;

import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.log.vo.UpDownPmsLogCriteria;
import com.ccm.api.model.log.vo.UpDownPmsLogResult;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface ReceiveMsgLogManager extends GenericManager<ReceiveMsgLog, String> {

	ReceiveMsgLog addReceiveMsgLog(ReceiveMsgLog receiveMsgLog);

	void updateReceiveMsgLog(ReceiveMsgLog receiveMsgLog);
	
	void updateReceiveMsgLogStatus(ReceiveMsgLog receiveMsgLog);

	ReceiveMsgLog getRMLByHotelCodeMsgId(String hotelCode, String msgId);

	ReceiveMsgLog getReceiveMsgLog(String receiveMsgLogId, String hotelCode);

	UpDownPmsLogResult searchList(UpDownPmsLogCriteria criteria);

}
