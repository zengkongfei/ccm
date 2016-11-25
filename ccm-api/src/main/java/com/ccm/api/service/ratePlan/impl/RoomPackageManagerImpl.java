package com.ccm.api.service.ratePlan.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccm.api.dao.ratePlan.RoomPackageDao;
import com.ccm.api.model.ratePlan.RoomPackage;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.ratePlan.RoomPackageManager;

@Service("roomPackageManager")
public class RoomPackageManagerImpl extends GenericManagerImpl<RoomPackage, String> implements RoomPackageManager{

	@Resource
	private RoomPackageDao roomPackageDao;
	
	@Override
	public RoomPackage addRoomPackage(RoomPackage roomPackage) {
		return roomPackageDao.addRoomPackage(roomPackage);
	}

	@Override
	public void deleteRoomPackageByRoomRateId(String roomRateId) {
		roomPackageDao.deleteRoomPackageByRoomRateId(roomRateId);
	}

	@Override
	public List<RoomPackage> getRoomPackageByRoomRateId(String roomRateId) {
		return roomPackageDao.getRoomPackageByRoomRateId(roomRateId);
	}

	@Override
	public List<RoomPackage> getRoomPackageByRoomRateId(String roomRateId,
			String language) {
		return roomPackageDao.getRoomPackageByRoomRateId(roomRateId, language);
	}

}
