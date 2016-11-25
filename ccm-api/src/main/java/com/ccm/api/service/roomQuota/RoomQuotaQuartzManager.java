package com.ccm.api.service.roomQuota;

import java.util.List;

import com.ccm.api.model.roomQuota.RoomQuota;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.service.base.GenericManager;


public interface RoomQuotaQuartzManager extends GenericManager<RoomQuota, String> {
	/**
	 * 房态发布定时任务
	 */
	public void roomQuotaQuartz();
	
	/**
	 * 按照酒店ID和房型代码拆分房价日历表
	 * @param rList
	 */
	public List<List<Rsvtype>> getSubListList(List<Rsvtype> rList);
}
