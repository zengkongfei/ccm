package com.ccm.api.service.ads;

import java.util.List;

import com.ccm.api.model.ads.ChainannelRateIdMapper;
import com.ccm.api.service.base.GenericManager;

/**
 * 
 */
public interface ChainannelRateIdMapperManager extends GenericManager<ChainannelRateIdMapper, String> {
    
	List<ChainannelRateIdMapper> searchChainannelRateIdMapper(ChainannelRateIdMapper cm);

    String getChainannelProductCode(ChainannelRateIdMapper cm);

    ChainannelRateIdMapper getChainannelCodeByProductCode(String productCode);
}