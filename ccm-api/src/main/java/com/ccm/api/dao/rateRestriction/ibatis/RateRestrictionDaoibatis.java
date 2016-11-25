package com.ccm.api.dao.rateRestriction.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.rateRestriction.RateRestrictionDao;
import com.ccm.api.model.rateRestriction.RateRestriction;

@Repository("rateRestrictionDao")
public class RateRestrictionDaoibatis extends GenericDaoiBatis<RateRestriction, String> implements RateRestrictionDao {

	public RateRestrictionDaoibatis() {
		super(RateRestriction.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RateRestriction> getRateRestrictionByDate(RateRestriction rateRestriction) {
		return getSqlMapClientTemplate().queryForList("getRateRestrictionByDate", rateRestriction);
	}

	@Override
	public void updateRateRestriction(RateRestriction rateRestriction) {
		getSqlMapClientTemplate().update("updateRateRestriction", rateRestriction);
	}
}
