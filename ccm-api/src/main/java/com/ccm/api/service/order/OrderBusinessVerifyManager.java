/**
 * 
 */
package com.ccm.api.service.order;

import com.ccm.api.model.order.Master;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.reservation.RoomStay;

/**
 * @author Jenny
 * 
 */
public interface OrderBusinessVerifyManager {

	/**
	 * OWS订单验证房价并获取房价与包价
	 * 
	 * @param pc
	 * @param m
	 * @param rs
	 * @param validRoomPrice
	 * @return
	 */
	double verifyRoomRate(PriceCalc pc, Master m, RoomStay rs, int validRoomPrice);

	/**
	 * WBE订单验证房价并获取房价与包价
	 * 
	 * @param pc
	 * @param master
	 */
	void verifyRoomRateWbe(PriceCalc pc, Master master);

}
