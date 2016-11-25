package com.ccm.api.dao.hotel;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.hotel.HotelSwitch;

/**
 * 
 * @author hyh
 *
 */
public class HotelSwitchDaoTest extends BaseDaoTestCase {
	@Autowired
	private HotelSwitchDao hotelSwitchDao;
	
	
	@Test
	@Rollback(value=false)
	public void addHotelSwitchTest(){
		HotelSwitch hotelSwitch = new HotelSwitch();
		hotelSwitch.setHotelId("65ee23f171e1451a83c0d8ad5060ef48");
		hotelSwitch.setHotelCode("AHHAN");
		hotelSwitch.setChainId("44ab3928fd744659b0a59fcd59f41ce8");
		hotelSwitch.setChainCode("CCM");
		hotelSwitch.setIsGenerates(true);
		hotelSwitch.setIsHardCancel(true);
		hotelSwitch.setIsUploadRateHeader(true);
		hotelSwitch.setCreatedTime(new Date());
		hotelSwitch.setCreatedBy("1");
		hotelSwitchDao.addHotelSwitch(hotelSwitch);
	}
	
	@Test
	@Rollback(value=false)
	public void getByHotelIdTest(){
		String hotelId="65ee23f171e1451a83c0d8ad5060ef48";
		HotelSwitch hotelSwitch = hotelSwitchDao.getByHotelId(hotelId);
		System.out.println(hotelSwitch);
	}
}
