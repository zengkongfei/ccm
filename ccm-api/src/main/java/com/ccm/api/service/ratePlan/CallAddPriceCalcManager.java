package com.ccm.api.service.ratePlan;

import com.ccm.api.model.channel.Rateplan;

public interface CallAddPriceCalcManager {
	/***pms 上传使用*/
	void startAddPmsPriceCalc(String uuidSign, Rateplan rateplan);
	
}
