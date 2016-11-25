package com.ccm.api.service.ratePlan;

import java.util.List;

import com.ccm.api.model.ratePlan.RateDetail;
import com.ccm.api.model.ratePlan.vo.PriceValidSearchResult;
import com.ccm.api.model.ratePlan.vo.RateDetailVO;
import com.ccm.api.model.ratePlan.vo.SearchPriceValidCriteria;
import com.ccm.api.service.base.GenericManager;

public interface RateDetailManager extends GenericManager<RateDetail, String> {

	/**
	 * 保存房价明细
	 */
	RateDetail addRateDetail(RateDetail detail);
	
	/**
	 * 根据房价ID删除房价明细
	 */
	void deleteRateDetailByRatePlanId(String ratePlanId);
	
	/**
	 * 根据房价ID查找房价明细
	 */
	List<RateDetailVO> getRateDetailVOByRatePlanId(String ratePlanId);

    boolean delRateDetail(String rateDetailId);
    
    PriceValidSearchResult searchPriceValidTimes(
			SearchPriceValidCriteria criteria);
}
