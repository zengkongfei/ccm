package com.ccm.api.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelCreteria;
import com.ccm.api.model.channel.vo.ChannelResult;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.util.CommonUtil;

@Service("channelManager")
public class ChannelManagerImpl extends GenericManagerImpl<Channel, String> implements ChannelManager {

	private ChannelDao ChannelDao;

	@Autowired
	public void setChannelDao(ChannelDao channelDao) {
		ChannelDao = channelDao;
		this.dao = channelDao;
	}

	public void deleteChannel(Channel c) {
		ChannelDao.deleteChannel(c);
	}

	@Override
	public Channel getChannelByChannelCode(String channelCode) {
		return ChannelDao.getChannelByChannelCode(channelCode);
	}

	public ChannelResult searchChannel(ChannelCreteria creteria) {
		ChannelResult result = new ChannelResult();
		List<Channel> voList = ChannelDao.searchChannel(creteria);
		Integer count = ChannelDao.searchChannelCount(creteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}

	@Override
	public List<Channel> getAllChannel() {
		return ChannelDao.getAllChannel();
	}

	@Override
	public List<Channel> getChannelInfoChainByUserId(String userId) {
		return ChannelDao.getChannelInfoChainByUserId(userId);
	}

	@Override
	public List<Channel> getEnabledChannel(List<Channel> list) {
		if(list==null){
			return null;
		}
		B2BUser user = SecurityHolder.getB2BUser();
		if(user.getCompanyId().equals(CompanyType.CHANNEL)==false)
			return list;
		List<Channel> channelList = user.getChannels();
		List<Channel> newList = new ArrayList<Channel>();
		if(CommonUtil.isEmpty(channelList)){
			return newList;
		}
		
		for(Channel channel : list){
			for(Channel ableCHannel:channelList){
				if(channel.getChannelId().equalsIgnoreCase(ableCHannel.getChannelId())){//list 中的channel 在权限当中
					newList.add(channel);
				}
			}
		}
		return newList;
	}

}
