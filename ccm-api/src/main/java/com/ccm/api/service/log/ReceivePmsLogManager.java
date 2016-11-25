/**
 * 
 */
package com.ccm.api.service.log;

import com.ccm.api.model.log.ReceivePmsLog;
import com.ccm.api.model.log.vo.ReceivePmsLogCriteria;
import com.ccm.api.model.log.vo.ReceivePmsLogResult;
import com.ccm.api.service.base.GenericManager;

/**
 * 接收PMS最后的热点请求时间
 * 
 * @author Jenny
 * 
 */
public interface ReceivePmsLogManager extends GenericManager<ReceivePmsLog, String> {

	void saveOrupdateReceivePmsLog(ReceivePmsLog rpl);

	ReceivePmsLogResult searchList(ReceivePmsLogCriteria criteria);

}
