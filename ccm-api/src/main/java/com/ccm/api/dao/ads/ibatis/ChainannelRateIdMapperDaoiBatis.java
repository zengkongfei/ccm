/**
 * 
 */
package com.ccm.api.dao.ads.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.ads.ChainannelRateIdMapperDao;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.model.ads.ChainannelRateIdMapper;

@Repository("chainannelRateIdMapperDao")
public class ChainannelRateIdMapperDaoiBatis extends GenericDaoiBatis<ChainannelRateIdMapper, String> implements ChainannelRateIdMapperDao{

	public ChainannelRateIdMapperDaoiBatis() {
		super(ChainannelRateIdMapper.class);
	}

    @Override
    public List<ChainannelRateIdMapper> searchChainannelRateIdMapper(ChainannelRateIdMapper cm) {
        List<ChainannelRateIdMapper> cmList = getSqlMapClientTemplate().queryForList("searchChainannelRateIdMapper", cm);
        return cmList;
    }
}
