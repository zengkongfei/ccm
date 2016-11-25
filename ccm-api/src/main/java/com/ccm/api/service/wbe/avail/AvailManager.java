package com.ccm.api.service.wbe.avail;

import java.util.List;

import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.wbe.vo.query.AvailRoomVO;
import com.ccm.api.service.base.GenericManager;

public interface AvailManager extends GenericManager<AvailRoomVO, String>{

	/**
	 * 获取房价日历
	 */
	List<PriceCalc> getPriceCalcList(AvailRoomVO availRoomVO) throws Exception;

	
	/**
	 * 查询房型列表
	 * @param availRoomVO
	 * @return
	 * @throws Exception 
	 */
	List<RoomTypeVO> queryRoomTypeVoList(List<PriceCalc> priceCalcList, AvailRoomVO availRoomVO) throws Exception;
	
	/**
	 * 查询房价列表
	 * @param availRoomVO
	 * @return
	 */
	List<RatePlanVO> queryRatePlanVoList(List<PriceCalc> priceCalcList, AvailRoomVO availRoomVO) throws Exception;
	
	
	
}
