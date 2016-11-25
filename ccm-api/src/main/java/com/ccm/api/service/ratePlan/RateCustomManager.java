package com.ccm.api.service.ratePlan;

import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.ratePlan.RateCustomRelationship;
import com.ccm.api.service.base.GenericManager;

public interface RateCustomManager extends GenericManager<RateCustomRelationship, String> {

	/**
	 * 新增修改房价时保存房价客户关系
	 * 
	 * @param savedRatePlanId
	 * @param vo
	 */
	void saveOrUpdateRateCustomRelationship(String savedRatePlanId, RatePlanVO vo);

	void addCustomRelationship(String savedRatePlanId, RatePlanVO vo);
	
	public void delCustomRelationship(String savedRatePlanId, RatePlanVO vo);
}
