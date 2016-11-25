package com.ccm.api.dao.ads.ibatis;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.ads.AdsOrderMessageDao;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.model.ads.AdsOrderMessage;

@Repository("adsOrderMessageDao")
public class AdsOrderMessageDaoiBatis extends GenericDaoiBatis<AdsOrderMessage, String> implements AdsOrderMessageDao{

	public AdsOrderMessageDaoiBatis() {
		super(AdsOrderMessage.class);
	}

}
