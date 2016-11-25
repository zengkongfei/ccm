package com.ccm.api.dao.ratePlan;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ratePlan.RatePackage;
import com.ccm.api.model.ratePlan.Package;
public interface RatePackageDao extends GenericDao<RatePackage, String> {

	/**
	 * 保存房价打包
	 */
	RatePackage addRatePackage(RatePackage ratePackage);
	
	/**
	 * 根据房价ID删除房价打包
	 */
	void deleteRatePackageByRatePlanId(String ratePlanId);
	
	/**
	 * 根据房价ID查找房价打包
	 */
	List<RatePackage> getRatePackageByRatePlanId(String ratePlanId);

    void saveRatePackageList(List<RatePackage> ratePackageList);

    /**
     * 根据房价ID和语言获取房价打包列表
     * @param ratePlanId
     * @param language
     * @return
     */
	List<RatePackage> getRatePackageByRatePlanId(String ratePlanId,String language);
	
	List<Package> getSvcTaxPackageByRatePlanId(String ratePlanId);
	
	List<Package> getSvcOrTaxFromRateDetail(Map paramMap);
}
