/**
 * 
 */
package com.ccm.api.dao.channel.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelCreteria;

/**
 * 
 */
@Repository("channelDao")
public class ChannelDaoibatis extends GenericDaoiBatis<Channel, String> implements ChannelDao {

	public ChannelDaoibatis() {
		super(Channel.class);
	}

	public void deleteChannel(Channel c) {
		getSqlMapClientTemplate().update("deleteChannel", c);
	}

	@Override
	public Channel getChannelByChannelCode(String channelCode) {
		return (Channel) getSqlMapClientTemplate().queryForObject("getChannelByChannelCode", channelCode);
	}

	@SuppressWarnings("unchecked")
	public List<Channel> searchChannel(ChannelCreteria creteria) {
		return getSqlMapClientTemplate().queryForList("searchChannel", creteria);
	}

	@Override
	public Integer searchChannelCount(ChannelCreteria creteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("searchChannelCount", creteria);
	}

	@SuppressWarnings("unchecked")
	public String getEnabledChannel(Channel c) {
		List<String> idList = getSqlMapClientTemplate().queryForList("getEnabledChannel", c);
		if (idList != null && !idList.isEmpty()) {
			return idList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Channel> getAllChannel() {
		return getSqlMapClientTemplate().queryForList("getAllChannel");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Channel> getChannelInfoChainByUserId(String userId) {
		return getSqlMapClientTemplate().queryForList("getChannelInfoChainByUserId",userId);
	}

	@Override
	public Channel getChannel(String channelId) {
		return (Channel) getSqlMapClientTemplate().queryForObject("getChannel",channelId);
	}

}
