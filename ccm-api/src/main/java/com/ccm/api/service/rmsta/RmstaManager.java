package com.ccm.api.service.rmsta;

import com.ccm.api.service.base.GenericManager;

public interface RmstaManager extends GenericManager<Object, String> {
	
	/**
	 * 查询房间表房号
	 * return : "1001,1002,1003,1004"
	 */
	public String queryRoomNo(String hotelId, String roomTypeCode,String gid);
	
	/**
	 * 删除房号
	 */
	public String deleteRmstaRoomNo(String hotelId,String roomNo,String roomTypeCode,String gid);
	
	/**
	 * 保存房号
	 */
	public void saveRmstaRoomNo(String hotelId,String roomNos,String roomTypeCode,String gid);
}
