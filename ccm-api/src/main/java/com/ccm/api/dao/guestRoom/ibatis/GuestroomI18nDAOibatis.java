package com.ccm.api.dao.guestRoom.ibatis;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.guestRoom.GuestroomI18nDAO;
import com.ccm.api.model.guestroom.GuestroomI18n;

@Repository("guestroomI18nDAO")
public class GuestroomI18nDAOibatis extends GenericDaoiBatis<GuestroomI18n, String> implements GuestroomI18nDAO {

	public GuestroomI18nDAOibatis() {
		super(GuestroomI18n.class);
	}

	@Override
	public GuestroomI18n getGuestroomI18nByGRID(String guestRoomId) {
		return (GuestroomI18n) getSqlMapClientTemplate().queryForObject("getGuestroomI18nByGRID", guestRoomId);
	}

	@Override
	public String getGuestRoomNameByRoomTypeCode(String roomTypeCode) {
		return (String) getSqlMapClientTemplate().queryForObject("getGuestRoomNameByRoomTypeCode", roomTypeCode);
	}
}