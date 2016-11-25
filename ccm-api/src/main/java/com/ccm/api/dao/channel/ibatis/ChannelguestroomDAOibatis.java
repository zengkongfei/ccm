package com.ccm.api.dao.channel.ibatis;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.channel.ChannelguestroomDAO;
import com.ccm.api.model.channel.Channelguestroom;

@Repository("channelguestroomDAO")
public class ChannelguestroomDAOibatis extends GenericDaoiBatis<Channelguestroom, String> implements ChannelguestroomDAO {

	public ChannelguestroomDAOibatis() {
		super(Channelguestroom.class);
	}

	@Override
	public Channelguestroom getChannelguestroomByGRID(String guestRoomId) {
		return (Channelguestroom) getSqlMapClientTemplate().queryForObject("getChannelguestroomByGRID", guestRoomId);
	}

	public Channelguestroom getChannelGuestRoomByRidOuterId(Channelguestroom c) {
		return (Channelguestroom) getSqlMapClientTemplate().queryForObject("getChannelGuestRoomByRidOuterId", c);
	}
}