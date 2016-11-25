package com.ccm.api.dao.fax;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.fax.FaxSendTime;
import com.ccm.api.util.DateUtil;

public class FaxSendTimeDaoTest extends BaseDaoTestCase {
	
	@Resource
	private FaxSendTimeDao dao;
	
	@Test
	@Rollback(value=false)
	public void saveTest(){
		try {
			String hotelId="65ee23f171e1451a83c0d8ad5060ef48";
			FaxSendTime faxSendTime = new FaxSendTime();
			faxSendTime.setHotelId(hotelId);
			faxSendTime.setFaxNumber("456");
			faxSendTime.setBeginTime(DateUtil.convertStringToDate("HH:mm","10:00"));
			faxSendTime.setEndTime(DateUtil.convertStringToDate("HH:mm","16:00"));
			dao.save(faxSendTime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(value=false)
	public void searchFaxSendTimeListTest(){
		try {
			Date time =   DateUtil.convertDateToDate("HH:mm", new Date());
			String hotelId="65ee23f171e1451a83c0d8ad5060ef48";
			FaxSendTime faxSendTime = new FaxSendTime();
			faxSendTime.setHotelId(hotelId);
			faxSendTime.setTime(time);
			List<FaxSendTime> list = dao.searchFaxSendTimeList(faxSendTime);
			for(FaxSendTime f: list){
				System.out.println(f);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(value=false)
	public void getAllTest(){
		try {
			List<FaxSendTime> list = dao.getAll();
			for(FaxSendTime f: list){
				System.out.println(f);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
