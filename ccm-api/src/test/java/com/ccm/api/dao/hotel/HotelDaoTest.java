/**
 * 
 */
package com.ccm.api.dao.hotel;

import javax.annotation.Resource;

import org.junit.Test;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.hotel.Hotel;

/**
 * @author Jenny
 * 
 */
public class HotelDaoTest extends BaseDaoTestCase {

	@Resource
	private HotelDao hotelDao;

	@Test
	public void testGetUser() throws Exception {
		Hotel h = hotelDao.getHotel("0a0f5423334611e48ad476ff40eec093");
		System.out.println(h.getHotelCode());
		Hotel h2 = hotelDao.getHotel("0a0f5423334611e48ad476ff40eec093", "hotelCode,merchantid,merchant_tid,securekey");
		System.out.println(h2.getHotelCode());
		Hotel h3 = hotelDao.getHotel("0a0f5423334611e48ad476ff40eec093", "hotelCode,merchantid,securekey");
		System.out.println(h3.getHotelCode());
	}
	
}
