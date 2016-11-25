package com.ccm.api.service.sell;

import java.util.List;

import com.ccm.api.model.sell.OverbookingGroup;
import com.ccm.api.service.base.GenericManager;

public interface OverbookingGroupManager extends GenericManager<OverbookingGroup, String> {

	boolean saveChannelGroup(String channelGroupStr, boolean isPush);

	List<OverbookingGroup> getObGroupByHotelId(String hotelId);

	
}
