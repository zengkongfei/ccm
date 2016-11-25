package com.ccm.api.service.ratePlan;

import java.util.List;

import com.ccm.api.model.ratePlan.RoomPackage;
import com.ccm.api.service.base.GenericManager;

public interface RoomPackageManager extends GenericManager<RoomPackage, String> {

	/**
	 * 保存房型打包
	 */
	RoomPackage addRoomPackage(RoomPackage roomPackage);
	
	/**
	 * 根据房型房价ID删除房型打包
	 */
	void deleteRoomPackageByRoomRateId(String roomRateId);
	
	/**
	 * 根据房型房价ID查找房型打包
	 */
	List<RoomPackage> getRoomPackageByRoomRateId(String roomRateId);
	
	/**
	 * 根据房型ID和语言查询房型打包数据
	 * @param roomRateId
	 * @param language
	 * @return
	 */
	List<RoomPackage> getRoomPackageByRoomRateId(String roomRateId,String language);
}
