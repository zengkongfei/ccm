package com.ccm.api.service.hotel;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.base.BaseManagerTestCase;

public class HotelManagerTest extends BaseManagerTestCase {

	@Autowired
	private HotelManager hm;

	@Autowired
	private RateCustomRelationshipDao rrd;
	
	//测试获取所有直连酒店
	@Test
	public void testPMSHotel() throws Exception {
		List<HotelVO> hotelList = new ArrayList<HotelVO>();
		hotelList=hm.getAllDirectPmsHotel("zh_CN", true);
		for(HotelVO h:hotelList){
			System.out.println(h.getHotelCode()+h.getIsDirectPms());
		}
	}

	/**
	 * this test case has been refined.
	 * 
	 * @throws Exception
	 */
	@Test
	@Rollback(true)
	public void testSaveHotel() throws Exception {

//		HotelVO hotel = new HotelVO();
//		hotel.setHotelName("北京石基信息");
//		hotel.setAreaCode(110101l);
//		hotel.setLevel("A");
//		hotel.setOrientation("B");
//
//		B2BUser user = new B2BUser();
//		user.setUserId("testjenny");
//
//		Hotel hre = hm.saveOrUpdateHotelInfo(hotel);
//
//		assertNotNull(hre.getHotelId());

	}

	@Test
	@Rollback(false)
	public void testRateCustom() throws Exception {
//		Custom c = new Custom();
//		c.setCorpIATANo(null);
//		c.setHotelId("ca73fbf20c9a11e48ca076ff40eec093");
//		c.setType("TRAVEL_AGENT");
//		c.setAccessCode("ELONG");
//		Custom s = rrd.getCustomIdByRateCustom(c, "d995410859164d30957e7a85bfaa33fe");
//		if (s != null) {
//			System.out.println(s.getCorpIATANo());
//			System.out.println(s.getName());
//			System.out.println("!null");
//		} else {
//			System.out.println("n");
//		}

	}
}
