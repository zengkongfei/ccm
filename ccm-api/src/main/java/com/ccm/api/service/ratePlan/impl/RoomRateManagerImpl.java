package com.ccm.api.service.ratePlan.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccm.api.dao.ratePlan.RoomRateDao;
import com.ccm.api.model.ratePlan.RoomRate;
import com.ccm.api.model.ratePlan.vo.RoomRateVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.ratePlan.RoomRateManager;
@Service("roomRateManager")
public class RoomRateManagerImpl extends GenericManagerImpl<RoomRate, String> implements RoomRateManager {

    
    @Resource
    private RoomRateDao roomRateDao;
    
    @Override
    public List<RoomRate> getRoomRateByRatePlanIdAndRoomTypeId(
            String ratePlanId, String roomTypeId) {
        return roomRateDao.getRoomRateByRatePlanIdAndRoomTypeId(ratePlanId,roomTypeId);
    }

	@Override
	public void deleteRoomRateByRateDetailId(String rateDetailId) {
		roomRateDao.deleteRoomRateByRateDetailId(rateDetailId);
	}

	@Override
	public List<RoomRateVO> getRoomRateVOByRateDetailId(String rateDetailId) {
		return roomRateDao.getRoomRateVOByRateDetailId(rateDetailId);
	}

}
