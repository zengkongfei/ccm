package com.ccm.api.dao.sell;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.sell.OverbookingGroup;

public interface OverbookingGroupDao extends GenericDao<OverbookingGroup, String> {

	List<OverbookingGroup> getObGroupByHotelId(String hotelId);

    OverbookingGroup searchOverbookingChannelHotelGroup(String channelId,
            String hotelCode);

	
}
