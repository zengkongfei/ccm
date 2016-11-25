/**
 * 
 */
package com.ccm.api.service.log;

import java.util.List;

import com.ccm.api.model.log.SendReqResLog;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface SendReqResManager extends GenericManager<SendReqResLog, String> {

	/**
	 * 根据条件查询请求返回
	 * 
	 * @param cond
	 * @return
	 */
	List<SendReqResLog> searchSendReqRes(SendReqResLog cond);

}
