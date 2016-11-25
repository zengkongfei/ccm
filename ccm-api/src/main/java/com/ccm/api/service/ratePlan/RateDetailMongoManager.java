package com.ccm.api.service.ratePlan;

import com.ccm.api.model.ratePlan.PriceCalc;

public interface RateDetailMongoManager {
	/**查询mongodb中RateDetail是否存在，存在返回true*/
	boolean isExistsRateDetailMongo(PriceCalc pc);
}
