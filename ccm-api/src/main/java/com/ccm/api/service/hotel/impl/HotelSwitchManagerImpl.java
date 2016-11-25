/**
 * 
 */
package com.ccm.api.service.hotel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.hotel.HotelSwitchDao;

import com.ccm.api.model.hotel.HotelSwitch;

import com.ccm.api.model.hotel.vo.HotelSwitchCriteria;
import com.ccm.api.model.hotel.vo.HotelSwitchResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.HotelSwitchManager;


/**
 * @author Water
 * 
 */
@Service("hotelSwitchManager")
public class HotelSwitchManagerImpl extends GenericManagerImpl<HotelSwitch, String> implements HotelSwitchManager {
	@Autowired
	private HotelSwitchDao hotelSwitchDao;
	@Autowired
	public HotelSwitchManagerImpl(HotelSwitchDao hotelSwitchDao) {
		super(hotelSwitchDao);
		this.hotelSwitchDao = hotelSwitchDao;
	}
	@Override
	public void addHotelSwitch(HotelSwitch hs) {
		hotelSwitchDao.addHotelSwitch(hs);
	}
	@Override
	public void updateHotelSwitch(HotelSwitch hotelSwitch) {
		hotelSwitchDao.updateHotelSwitch(hotelSwitch);	
	}
	@Override
	public void updateByHotelId(HotelSwitch hotelSwitch) {
		hotelSwitchDao.updateByHotelId(hotelSwitch);
	}
	/**
	 * 
	 */
	@Override
	public HotelSwitchResult getHotelSwitch(HotelSwitchCriteria criteria) {
		return hotelSwitchDao.getHotelSwitch(criteria);
	}
	
	@Override
	public HotelSwitch getByHotelId(String hotelId) {
		return hotelSwitchDao.getByHotelId(hotelId);
	}

}
