package com.ccm.api.dao.channel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelCreteria;

public interface ChannelDao extends GenericDao<Channel, String> {

	/**
	 * 逻辑删除
	 * 
	 * @param c
	 */
	void deleteChannel(Channel c);

	Channel getChannelByChannelCode(String channelCode);

	List<Channel> searchChannel(ChannelCreteria creteria);

	Integer searchChannelCount(ChannelCreteria creteria);

	String getEnabledChannel(Channel c);

	List<Channel> getAllChannel();

	/**
	 * 根据用户ID获取渠道
	 * @param userId
	 * @return
	 */
	List<Channel> getChannelInfoChainByUserId(String userId);
	
	public Channel getChannel(String channelId);

}
