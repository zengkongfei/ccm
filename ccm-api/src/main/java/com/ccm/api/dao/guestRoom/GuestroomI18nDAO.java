package com.ccm.api.dao.guestRoom;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.guestroom.GuestroomI18n;

public interface GuestroomI18nDAO extends GenericDao<GuestroomI18n, String> {

	GuestroomI18n getGuestroomI18nByGRID(String guestRoomId);

	String getGuestRoomNameByRoomTypeCode(String roomTypeCode);
}