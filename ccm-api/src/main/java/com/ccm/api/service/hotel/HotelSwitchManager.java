/**
 * 
 */
package com.ccm.api.service.hotel;

import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.hotel.vo.HotelSwitchCriteria;
import com.ccm.api.model.hotel.vo.HotelSwitchResult;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Water
 * 
 */
public interface HotelSwitchManager extends GenericManager<HotelSwitch, String> {
	/**
	 * 添加HotelSwitch
	 * @param hotelSwitch
	 */
	void addHotelSwitch(HotelSwitch hotelSwitch);	
	/**
	 * 更新HotelSwitch
	 * @param hotelSwitch
	 */
	void updateHotelSwitch(HotelSwitch hotelSwitch);	
	
	void updateByHotelId(HotelSwitch hotelSwitch);
	
	/**
	 * 获取getHotelSwitch
	 * @return
	 */
	HotelSwitchResult getHotelSwitch(HotelSwitchCriteria criteria);
	
	/**
	 * get HotelSwitch by hotelId
	 * @param hotelId
	 * @return
	 */
	HotelSwitch getByHotelId(String hotelId);
}
