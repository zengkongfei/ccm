/**
 * 
 */
package com.ccm.api.service.log;

import java.util.List;

import com.ccm.api.model.log.ReceiveReqResLog;
import com.ccm.api.model.log.vo.OrderLogSearchResult;
import com.ccm.api.model.log.vo.SearchOrderLogCriteria;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface ReceiveReqResManager extends GenericManager<ReceiveReqResLog, String> {

	void saveOrUpdateReceiveLogOfOrder(ReceiveReqResLog receiveLog);

	List<ReceiveReqResLog> getReceiveReqResByObj(ReceiveReqResLog conf);

	OrderLogSearchResult searchList(SearchOrderLogCriteria criteria);

	void saveOrUpdateReceiveReqRes(ReceiveReqResLog receiveReqResLog);

}
