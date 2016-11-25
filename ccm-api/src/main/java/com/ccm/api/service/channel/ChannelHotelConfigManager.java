/**
 * 
 */
package com.ccm.api.service.channel;

import com.ccm.api.model.channel.ChannelHotelConfig;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Water
 * 
 */
public interface ChannelHotelConfigManager extends GenericManager<ChannelHotelConfig, String> {

	
	ChannelHotelConfig getChannelHotelConfigByChannelId(String channelId);
	
	void updateChannelHotelConfig(ChannelHotelConfig chc);
	
	void addChannelHotelConfig(ChannelHotelConfig chc);
}
