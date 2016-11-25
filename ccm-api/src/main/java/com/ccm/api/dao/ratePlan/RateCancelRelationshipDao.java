package com.ccm.api.dao.ratePlan;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ratePlan.RateCancelRelationship;

public interface RateCancelRelationshipDao extends GenericDao<RateCancelRelationship, String> {

	/**
	 * 保存
	 */
	RateCancelRelationship addRateCancelRelationship(RateCancelRelationship rateGuaranteeRelationship);

	/**
	 * 根据房价ID删除
	 */
	void deleteRateCancelRelationshipByRatePlanId(String ratePlanId);

	/**
	 * 根据房价ID查找
	 * 
	 * @param ratePlanId
	 * @param language
	 * @return
	 */
	List<RateCancelRelationship> getRateCancelRelationshipByRatePlanId(String ratePlanId);

	List<RateCancelRelationship> getRateCancelPolicyByRatePlanId(String ratePlanId);

	List<RateCancelRelationship> getRateCancelPolicyI18nByRatePlanId(String hotelId, String ratePlanId, String language);

	RateCancelRelationship get180DayRateCancel(String hotelId,
			String ratePlanCode, String day);

}
