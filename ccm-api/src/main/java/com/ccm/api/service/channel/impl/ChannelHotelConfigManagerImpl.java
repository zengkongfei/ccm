/**
 * 
 */
package com.ccm.api.service.channel.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.channel.mongodb.ChannelHotelConfigDaoMongo;
import com.ccm.api.model.channel.ChannelHotelConfig;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelHotelConfigManager;

/**
 * @author Water
 * 
 */
@Service("channelHotelConfigManager")
public class ChannelHotelConfigManagerImpl extends GenericManagerImpl<ChannelHotelConfig, String> implements ChannelHotelConfigManager {
	
	@Autowired
	private ChannelHotelConfigDaoMongo channelHotelConfigDao;

	@Override
	public ChannelHotelConfig getChannelHotelConfigByChannelId(String channelId) {
		return channelHotelConfigDao.getChannelHotelConfigByChannelId(channelId);
	}

	@Override
	public void updateChannelHotelConfig(ChannelHotelConfig channelHotelConfig) {
		channelHotelConfigDao.updateChannelHotelConfig(channelHotelConfig);
	}

	@Override
	public void addChannelHotelConfig(ChannelHotelConfig channelHotelConfig) {
		channelHotelConfig.setChannelHotelConfigId(UUID.randomUUID().toString().replace("-", ""));//设置主键
		channelHotelConfigDao.addChannelHotelConfig(channelHotelConfig);
	}

}
