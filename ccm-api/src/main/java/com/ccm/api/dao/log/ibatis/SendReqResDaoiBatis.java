/**
 * 
 */
package com.ccm.api.dao.log.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.log.SendReqResDao;
import com.ccm.api.model.log.SendReqResLog;

/**
 * @author Jenny
 * 
 */
@Repository("sendReqResDao")
public class SendReqResDaoiBatis extends GenericDaoiBatis<SendReqResLog, String> implements SendReqResDao {

	public SendReqResDaoiBatis() {
		super(SendReqResLog.class);
	}

	@SuppressWarnings("unchecked")
	public List<SendReqResLog> searchSendReqRes(SendReqResLog condition) {
		return getSqlMapClientTemplate().queryForList("searchSendReqResByConf", condition);
	}

}
