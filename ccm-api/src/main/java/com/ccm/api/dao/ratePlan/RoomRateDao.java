package com.ccm.api.dao.ratePlan;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ratePlan.RoomRate;
import com.ccm.api.model.ratePlan.vo.RoomRateVO;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.util.CommonUtil;

public interface RoomRateDao extends GenericDao<RoomRate, String> {

	/**
	 * 保存房型房价
	 */
	RoomRate addRoomRate(RoomRate roomRate);
	
	/**
	 * 根据房价明细ID删除房型房价
	 */
	void deleteRoomRateByRateDetailId(String rateDetailId);
	
	int deleteRoomRateById(String roomRateId);
	
	int deleteRoomRateByRoomTypeId(String rateDetailId,String roomTypeId);
	/**
	 * 根据房价明细ID查找房型房价
	 */
	List<RoomRate> getRoomRateByRateDetailId(String rateDetailId);
	
	/***
	 * 保存关联房型 房型打包
	 * @param roomRateVO
	 */
    void addRoomRateVO(RoomRateVO roomRateVO);

    List<RoomRateVO> getRoomRateVOByRateDetailId(String rateDetailId);

    void updateRoomRateVO(RoomRateVO roomRateVO);

    List<RoomRate> getRoomRateByRatePlanIdAndRoomTypeId(String ratePlanId,String roomTypeId);

    /**
	 * 根据房价明细ID和语言查找房型房价
	 */
    List<RoomRateVO> getRoomRateVOByRateDetailId(String rateDetailId,String language);
    
	RoomRateVO getRoomRateVOByRateDetailIdAndRoomTYpeCode(String rateDetailId,String roomTYpeCode,String language);
	
	void addBatchRoomRates(List<RoomRateVO> roomRateList);
	
	/**
	 * 删除不存在detail中的房型,返回删除操作影响的记录条数
	 * @return
	 */
	int deleteRoomRateNotExistsInDetail();
	
}
