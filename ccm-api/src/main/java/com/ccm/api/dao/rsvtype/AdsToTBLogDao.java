package com.ccm.api.dao.rsvtype;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.rsvtype.AdsToTBLog;

public interface AdsToTBLogDao extends GenericDao<AdsToTBLog, String> {

	/**
	 * 修改AdsToTBLog
	 */
	public void updateAdsToTBLog(AdsToTBLog log);
}
