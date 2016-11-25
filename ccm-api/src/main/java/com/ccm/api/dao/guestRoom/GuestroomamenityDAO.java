package com.ccm.api.dao.guestRoom;

import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.guestroom.Guestroomamenity;

public interface GuestroomamenityDAO  extends GenericDao<Guestroomamenity,String>{

    Map<String, String> getGuestRoomAmenity(String guestRoomId);

}