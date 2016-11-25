/**
 * 
 */
package com.ccm.api.dao.log.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.log.ReceivePmsLogDao;
import com.ccm.api.model.log.ReceivePmsLog;
import com.ccm.api.model.log.vo.ReceivePmsLogCriteria;
import com.ccm.api.model.log.vo.ReceivePmsLogResult;

/**
 * 接收PMS最后的热点请求时间
 * 
 * @author Jenny
 * 
 */
@Repository("receivePmsLogDao")
public class ReceivePmsLogDaoiBatis extends GenericDaoiBatis<ReceivePmsLog, String> implements ReceivePmsLogDao {

	public ReceivePmsLogDaoiBatis() {
		super(ReceivePmsLog.class);
	}

	@SuppressWarnings("unchecked")
	public List<ReceivePmsLog> getReceivePmsLogByConf(ReceivePmsLog rpl) {
		return getSqlMapClientTemplate().queryForList("getReceivePmsLogByConf", rpl);
	}

	@SuppressWarnings("unchecked")
	public ReceivePmsLogResult searchList(ReceivePmsLogCriteria criteria) {
		ReceivePmsLogResult searchResult = new ReceivePmsLogResult();

		if (criteria.isNeedPage()) {
			int count = (Integer) getSqlMapClientTemplate().queryForObject("countReceivePmsLog", criteria);
			searchResult.setTotalCount(count);

			List<ReceivePmsLog> list = getSqlMapClientTemplate().queryForList("searchReceivePmsLog", criteria);
			searchResult.setResultList(list);
		} else {
			List<ReceivePmsLog> list = getSqlMapClientTemplate().queryForList("searchReceivePmsLog", criteria);
			searchResult.setResultList(list);
		}

		return searchResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceivePmsLog> getOffReceivePmsLog() {
		List<ReceivePmsLog> list = getSqlMapClientTemplate().queryForList("getOffReceivePmsLog");
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReceivePmsLog> getOnReceivePmsLog() {
		List<ReceivePmsLog> list = getSqlMapClientTemplate().queryForList("getOnReceivePmsLog");
		return list;
	}

}
