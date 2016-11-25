package com.ccm.api.dao.rsvtype;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.util.DateUtil;

public class RsvtypeChannelDaoTest extends BaseDaoTestCase {
	@Resource
	private RsvtypeChannelDao rsvtypeChannelDao;

	@Test
	public void dumpTest() {

	}

	@Test
	public void testgetRsvtypeChannelAilable() throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("channelCode", "ELONG");
		paramMap.put("hotelCode", "SWHBJ");
		paramMap.put("roomTypeCode", "2BETS");
		paramMap.put("startDate", DateUtil.convertStringToDate("2014-11-16"));
		paramMap.put("endDate", DateUtil.convertStringToDate("2015-12-31"));
		List<RsvtypeChannel> rcList = rsvtypeChannelDao.getRsvtypeChannelAilable(paramMap);
		for (RsvtypeChannel rc : rcList) {
			System.out.println(rc.getRsvtypeChannelId());
			System.out.println(rc.getRsvtypeId());
			System.out.println(rc.getDate());
			System.out.println(rc.getHotelCode());
		}
	}

}
