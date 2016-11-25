/**
 * 
 */
package com.ccm.api.dao.log.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.log.ReceiveReqResDao;
import com.ccm.api.model.log.ReceiveReqResLog;
import com.ccm.api.model.log.vo.OrderLogSearchResult;
import com.ccm.api.model.log.vo.SearchOrderLogCriteria;
import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
@Repository("receiveReqResDao")
public class ReceiveReqResDaoiBatis extends GenericDaoiBatis<ReceiveReqResLog, String> implements ReceiveReqResDao {

	public ReceiveReqResDaoiBatis() {
		super(ReceiveReqResLog.class);
	}

	public void saveReceiveReqResLog(ReceiveReqResLog receiveLog) {
		if (receiveLog.getCreatedTime() == null) {
			receiveLog.setCreatedTime(DateUtil.currentDateTime());
		}
		getSqlMapClientTemplate().insert("addReceiveReqResLog", receiveLog);
	}

	@SuppressWarnings("unchecked")
	public List<ReceiveReqResLog> getReceiveReqResByObj(ReceiveReqResLog obj) {

		return getSqlMapClientTemplate().queryForList("getReceiveReqResByObj", obj);
	}

	@SuppressWarnings("unchecked")
	public OrderLogSearchResult searchList(SearchOrderLogCriteria criteria) {

		OrderLogSearchResult searchResult = new OrderLogSearchResult();
		int count = (Integer) getSqlMapClientTemplate().queryForObject("countReceiveReqResByConf", criteria);
		searchResult.setTotalCount(count);

		List<ReceiveReqResLog> list = getSqlMapClientTemplate().queryForList("getReceiveReqResByConf", criteria);
		searchResult.setResultList(list);

		return searchResult;
	}

}
