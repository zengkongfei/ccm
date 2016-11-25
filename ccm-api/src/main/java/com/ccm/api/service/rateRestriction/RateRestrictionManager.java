package com.ccm.api.service.rateRestriction;

import java.util.List;

import com.ccm.api.model.rateRestriction.RateRestriction;
import com.ccm.api.service.base.GenericManager;

public interface RateRestrictionManager extends GenericManager<RateRestriction, String> {
	
	/**
	 * 根据房价代码，酒店代码，日期查询房价限制记录
	 */
	public List<RateRestriction> getRateRestrictionByDate(RateRestriction rateRestriction);
	
	/**
	 * 根据房价代码，酒店代码，日期修改房价限制记录
	 */
	public void updateRateRestriction(RateRestriction rateRestriction);
}
