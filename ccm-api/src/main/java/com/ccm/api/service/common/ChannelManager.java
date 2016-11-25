/**
 * 
 */
package com.ccm.api.service.common;

import java.util.List;

import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelCreteria;
import com.ccm.api.model.channel.vo.ChannelResult;
import com.ccm.api.service.base.GenericManager;

public interface ChannelManager extends GenericManager<Channel, String> {

	/**
	 * 逻辑删除
	 * 
	 * @param c
	 */
	void deleteChannel(Channel c);

	Channel getChannelByChannelCode(String string);

	/**
	 * 根据条件取渠道信息
	 */
	ChannelResult searchChannel(ChannelCreteria creteria);
	
	List<Channel> getAllChannel();

	/**
	 * 根据用户ID获取渠道
	 * @param userId
	 * @return
	 */
	List<Channel> getChannelInfoChainByUserId(String userId);
	
	/**
	 * 根据当前用户渠道权限赛选渠道
	 * @param list
	 * @return
	 */
	List<Channel> getEnabledChannel(List<Channel> list);

}