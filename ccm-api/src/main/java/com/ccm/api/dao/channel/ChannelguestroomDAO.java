package com.ccm.api.dao.channel;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.Channelguestroom;

public interface ChannelguestroomDAO extends GenericDao<Channelguestroom, String> {

	Channelguestroom getChannelguestroomByGRID(String guestRoomId);

	Channelguestroom getChannelGuestRoomByRidOuterId(Channelguestroom c);
}