package com.ccm.api.dao.rsvtype.ibatis;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.rsvtype.AdsToTBLogDao;
import com.ccm.api.model.rsvtype.AdsToTBLog;

@Repository("adsToTBLogDao")
public class AdsToTBLogDaoibatis extends GenericDaoiBatis<AdsToTBLog, String> implements AdsToTBLogDao {

	public AdsToTBLogDaoibatis() {
		super(AdsToTBLog.class);
	}

	@Override
	public void updateAdsToTBLog(AdsToTBLog log) {
		getSqlMapClientTemplate().update("updateAdsToTBLog", log);
	}
}
