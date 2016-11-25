/**
 * 
 */
package com.ccm.api.dao.hotel;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.hotel.vo.HotelSwitchCriteria;
import com.ccm.api.model.hotel.vo.HotelSwitchResult;

/**
 * @author Water
 * 
 */
public interface HotelSwitchDao extends GenericDao<HotelSwitch, String> {
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
	
	HotelSwitch getByChainAndHotel(String chainCode, String hotelCode);
}
