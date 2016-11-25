package com.ccm.api.service.ads;

import com.ccm.api.model.ads.AdsOrderMessage;
import com.ccm.api.service.base.GenericManager;

public interface AdsOrderManager extends GenericManager<AdsOrderMessage, String>{

	/**	
     * 保存adsOrder
     */
	public AdsOrderMessage createAdsMessage(AdsOrderMessage adsOrderMsg);
	
}
