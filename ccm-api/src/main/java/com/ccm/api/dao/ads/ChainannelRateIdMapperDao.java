package com.ccm.api.dao.ads;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ads.ChainannelRateIdMapper;


public interface ChainannelRateIdMapperDao extends GenericDao<ChainannelRateIdMapper, String> {

    List<ChainannelRateIdMapper> searchChainannelRateIdMapper(ChainannelRateIdMapper cm);

}
