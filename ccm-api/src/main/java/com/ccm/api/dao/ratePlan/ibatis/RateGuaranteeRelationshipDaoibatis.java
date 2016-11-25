package com.ccm.api.dao.ratePlan.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.ratePlan.RateGuaranteeRelationshipDao;
import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.RateGuaranteeRelationship;

@Repository("rateGuaranteeRelationshipDao")
public class RateGuaranteeRelationshipDaoibatis extends GenericDaoiBatis<RateGuaranteeRelationship, String> implements RateGuaranteeRelationshipDao {

	public RateGuaranteeRelationshipDaoibatis() {
		super(RateGuaranteeRelationship.class);
	}

	@Override
	public RateGuaranteeRelationship addRateGuaranteeRelationship(RateGuaranteeRelationship rateGuaranteeRelationship) {
		return (RateGuaranteeRelationship) this.getSqlMapClientTemplate().insert("addRateGuaranteeRelationship", rateGuaranteeRelationship);
	}

	@Override
	public void deleteRateGuaranteeRelationshipByRatePlanId(String ratePlanId) {
		getSqlMapClientTemplate().delete("deleteRateGuaranteeRelationshipByRatePlanId", ratePlanId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RateGuaranteeRelationship> getRateGuaranteeRelationshipByRatePlanId(String ratePlanId) {
		return getSqlMapClientTemplate().queryForList("getRateGuaranteeRelationshipByRatePlanId", ratePlanId);
	}

	@SuppressWarnings("unchecked")
	public boolean getRateGuarRealByRatePlanIdGuaranteeId(RateGuaranteeRelationship rgrs) {
		List<RateGuaranteeRelationship> rgrList = getSqlMapClientTemplate().queryForList("getRateGuarRealByRatePlanIdGuaranteeId", rgrs);
		if (rgrList != null && !rgrList.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public List<GuaranteePolicy> getEffectiveRateGuaranteeByRatePlanId(RateGuaranteeRelationship rgrs){
		return getSqlMapClientTemplate().queryForList("getEffectiveRateGuaranteeByRatePlanId", rgrs);
	}
}
