package com.ccm.api.service.ratePlan;

import java.util.List;

import com.ccm.api.model.ratePlan.RoomRate;
import com.ccm.api.model.ratePlan.vo.RoomRateVO;
import com.ccm.api.service.base.GenericManager;

public interface RoomRateManager extends GenericManager<RoomRate, String> {

    /***
     * 根据房价id，房型id  找到房价房型
     * @param ratePlanId
     * @param roomTypeId
     * @return
     */
    List<RoomRate> getRoomRateByRatePlanIdAndRoomTypeId(String ratePlanId,
            String roomTypeId);
    
    
    /**
	 * 根据房价明细ID删除房型房价
	 */
	void deleteRoomRateByRateDetailId(String rateDetailId);
	
	/**
	 * 根据rateDetailId获取对应的roomRate列表
	 * @param rateDetailId
	 * @return
	 */
	public List<RoomRateVO> getRoomRateVOByRateDetailId(String rateDetailId);
}
