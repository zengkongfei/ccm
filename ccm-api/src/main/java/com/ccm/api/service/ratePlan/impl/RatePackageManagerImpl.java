package com.ccm.api.service.ratePlan.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccm.api.dao.ratePlan.RatePackageDao;
import com.ccm.api.model.ratePlan.RatePackage;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.ratePlan.RatePackageManager;

@Service("ratePackageManager")
public class RatePackageManagerImpl extends GenericManagerImpl<RatePackage, String> implements RatePackageManager{

	@Resource
	private RatePackageDao ratePackageDao;
	
	@Override
	public RatePackage addRatePackage(RatePackage ratePackage) {
		return ratePackageDao.addRatePackage(ratePackage);
	}

	@Override
	public void deleteRatePackageByRatePlanId(String ratePlanId) {
		ratePackageDao.deleteRatePackageByRatePlanId(ratePlanId);
	}

	@Override
	public List<RatePackage> getRatePackageByRatePlanId(String ratePlanId) {
		return ratePackageDao.getRatePackageByRatePlanId(ratePlanId);
	}

	@Override
	public List<RatePackage> getRatePackageByRatePlanId(String ratePlanId,
			String language) {
		return ratePackageDao.getRatePackageByRatePlanId(ratePlanId, language);
	}

}
