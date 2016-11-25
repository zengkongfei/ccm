package com.ccm.api.dao.master.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.master.MasterChangesLogDao;
import com.ccm.api.model.order.MasterChangesLog;

@Repository("masterChangesLogDao")
public class MasterChangesLogDaoibatis extends GenericDaoiBatis<MasterChangesLog, String> implements MasterChangesLogDao {

	public MasterChangesLogDaoibatis() {
		super(MasterChangesLog.class);
	}

	/**
	 * 根据订单号查询订单变更记录
	 */
	@SuppressWarnings("unchecked")
	public List<MasterChangesLog> getMasterChangesLogByMasterId(String masterId) {
		return getSqlMapClientTemplate().queryForList("getMasterChangesLogByMasterId", masterId);
	}

}
