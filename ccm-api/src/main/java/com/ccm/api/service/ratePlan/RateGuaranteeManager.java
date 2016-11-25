package com.ccm.api.service.ratePlan;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.ratePlan.RateGuaranteeRelationship;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.service.base.GenericManager;

public interface RateGuaranteeManager extends GenericManager<RateGuaranteeRelationship, String> {

	/**
	 * switch下单时验证担保规则
	 * 
	 * @param guaranteeCode
	 * @param ratePlanId
	 * @param checkinDate
	 * @return
	 */
	boolean validGuaranteePolicy(String guaranteeCode, String ratePlanId, Date checkinDate);

	List<GuaranteePolicyVO> getEnabledGuaranteePolicy(String ratePlanId, String language, Date checkinDate);

}
