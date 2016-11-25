/**
 * 
 */
package com.ccm.api.dao.log;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.log.SendReqResLog;

/**
 * @author Jenny
 * 
 */
public interface SendReqResDao extends GenericDao<SendReqResLog, String> {

	/**
	 * 根据动态条件查询请求返回日志
	 * 
	 * @param condition
	 * @return
	 */
	public List<SendReqResLog> searchSendReqRes(SendReqResLog condition);

}
