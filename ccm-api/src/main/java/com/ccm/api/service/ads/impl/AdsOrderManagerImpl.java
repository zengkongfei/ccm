package com.ccm.api.service.ads.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.ads.AdsOrderMessageDao;
import com.ccm.api.model.ads.AdsOrderMessage;
import com.ccm.api.service.ads.AdsOrderManager;
import com.ccm.api.service.base.impl.GenericManagerImpl;

@Repository("adsOrderManager")
public class AdsOrderManagerImpl extends GenericManagerImpl<AdsOrderMessage, String> implements AdsOrderManager{
	
	@Resource
	private AdsOrderMessageDao adsOrderMessageDao;
	
	@Override
	public AdsOrderMessage createAdsMessage(AdsOrderMessage adsOrderMsg) {
		AdsOrderMessage saveAdsOrderMsg = adsOrderMessageDao.save(adsOrderMsg);
		return saveAdsOrderMsg;
	}

}
