package com.ccm.api.dao.ratePlan;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.RateGuaranteeRelationship;

public interface RateGuaranteeRelationshipDao extends GenericDao<RateGuaranteeRelationship, String> {

	/**
	 * 保存
	 */
	RateGuaranteeRelationship addRateGuaranteeRelationship(RateGuaranteeRelationship rateGuaranteeRelationship);

	/**
	 * 根据房价ID删除
	 */
	void deleteRateGuaranteeRelationshipByRatePlanId(String ratePlanId);

	/**
	 * 根据房价ID,担保ID查询房价担保关系
	 * 
	 * @param rgrs
	 * @return
	 */
	boolean getRateGuarRealByRatePlanIdGuaranteeId(RateGuaranteeRelationship rgrs);

	List<RateGuaranteeRelationship> getRateGuaranteeRelationshipByRatePlanId(String ratePlanId);

	List<GuaranteePolicy> getEffectiveRateGuaranteeByRatePlanId(RateGuaranteeRelationship rgrs);
}
