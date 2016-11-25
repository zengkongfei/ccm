package com.ccm.api.dao.rsvtype.ibatis;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.rsvtype.RatepmsDao;
import com.ccm.api.model.rsvtype.Ratepms;

@Repository("ratepmsDao")
public class RatepmsDaoibatis extends GenericDaoiBatis<Ratepms, String> implements RatepmsDao {

	public RatepmsDaoibatis() {
		super(Ratepms.class);
	}

	@Override
	public void addRatepms(Ratepms ratepms) {
		String primaryKey = UUID.randomUUID().toString().replace("-", "");
		ratepms.setRatepmsId(primaryKey);
		getSqlMapClientTemplate().insert("addRatepms", ratepms);
	}

	@Override
	public void updateRatepmsStatus(String ratepmsId) {
		getSqlMapClientTemplate().update("updateRatepmsStatus", ratepmsId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ratepms> getRatepms() {
		return getSqlMapClientTemplate().queryForList("getRatepms");
	}
}
