/**
 * 
 */
package com.ccm.api.dao.log;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.log.ReceiveReqResLog;
import com.ccm.api.model.log.vo.OrderLogSearchResult;
import com.ccm.api.model.log.vo.SearchOrderLogCriteria;

/**
 * @author Jenny
 * 
 */
public interface ReceiveReqResDao extends GenericDao<ReceiveReqResLog, String> {

	void saveReceiveReqResLog(ReceiveReqResLog receiveLog);

	List<ReceiveReqResLog> getReceiveReqResByObj(ReceiveReqResLog obj);

	OrderLogSearchResult searchList(SearchOrderLogCriteria criteria);

}
