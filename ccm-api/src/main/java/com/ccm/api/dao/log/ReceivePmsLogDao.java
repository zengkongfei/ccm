/**
 * 
 */
package com.ccm.api.dao.log;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.log.ReceivePmsLog;
import com.ccm.api.model.log.vo.ReceivePmsLogCriteria;
import com.ccm.api.model.log.vo.ReceivePmsLogResult;

/**
 * 接收PMS最后的热点请求时间
 * 
 * @author Jenny
 * 
 */
public interface ReceivePmsLogDao extends GenericDao<ReceivePmsLog, String> {

	List<ReceivePmsLog> getReceivePmsLogByConf(ReceivePmsLog rpl);

	ReceivePmsLogResult searchList(ReceivePmsLogCriteria criteria);
	
	/**
	 * 所有断开的酒店
	 * @return
	 */
	List<ReceivePmsLog> getOffReceivePmsLog();
	
	/**
	 * 所有活跃的酒店
	 * @return
	 */
	List<ReceivePmsLog> getOnReceivePmsLog();

}
