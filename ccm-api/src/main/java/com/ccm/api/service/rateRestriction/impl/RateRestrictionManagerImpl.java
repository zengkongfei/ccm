package com.ccm.api.service.rateRestriction.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.rateRestriction.RateRestrictionDao;
import com.ccm.api.model.rateRestriction.RateRestriction;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.rateRestriction.RateRestrictionManager;

@Service("rateRestrictionManager")
public class RateRestrictionManagerImpl extends GenericManagerImpl<RateRestriction, String> implements RateRestrictionManager {

	@Autowired
	private RateRestrictionDao rateRestrictionDao;
	
	@Autowired
    public void setRateRestrictionDao(RateRestrictionDao rateRestrictionDao) {
        this.rateRestrictionDao = rateRestrictionDao;
        this.dao = rateRestrictionDao;
    }

	@Override
	public List<RateRestriction> getRateRestrictionByDate(RateRestriction rateRestriction) {
		return rateRestrictionDao.getRateRestrictionByDate(rateRestriction);
	}

	@Override
	public void updateRateRestriction(RateRestriction rateRestriction) {
		rateRestrictionDao.updateRateRestriction(rateRestriction);
	}

}
