package com.ccm.api.service.ads.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.ads.ChainannelRateIdMapperDao;
import com.ccm.api.model.ads.ChainannelRateIdMapper;
import com.ccm.api.service.ads.ChainannelRateIdMapperManager;
import com.ccm.api.service.base.impl.GenericManagerImpl;
@Repository("chainannelRateIdMapperManager")
public class ChainannelRateIdMapperManagerImpl extends GenericManagerImpl<ChainannelRateIdMapper, String> implements ChainannelRateIdMapperManager{
    
    private ChainannelRateIdMapperDao chainannelRateIdMapperDao ;
    
    @Resource
    public void setChainannelRateIdMapperDao(ChainannelRateIdMapperDao c) {
        chainannelRateIdMapperDao = c;
        dao = c;
    }

    @Override
    public String getChainannelProductCode(ChainannelRateIdMapper cm) {
        List<ChainannelRateIdMapper> cmList = chainannelRateIdMapperDao.searchChainannelRateIdMapper(cm);
        if(cmList != null && cmList.size() > 0){
            return cmList.get(0).getChainannelProductCode();
        }
        return null;
    }

    @Override
    public ChainannelRateIdMapper getChainannelCodeByProductCode(String productCode) {
        ChainannelRateIdMapper cm = new ChainannelRateIdMapper();
        cm.setChainannelProductCode(productCode);
        List<ChainannelRateIdMapper> cmList = chainannelRateIdMapperDao.searchChainannelRateIdMapper(cm);
        if(cmList != null && cmList.size() > 0){
            return cmList.get(0);
        }
        return null;
    }

    @Override
    public List<ChainannelRateIdMapper> searchChainannelRateIdMapper(
            ChainannelRateIdMapper cm) {
        return chainannelRateIdMapperDao.searchChainannelRateIdMapper(cm);
    }
    
    
}
