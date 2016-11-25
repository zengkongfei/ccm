package com.ccm.api.service.report;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.service.log.ChannelOrderManager;

public class ChannelOrderManagerTest extends BaseManagerTestCase {

	@Autowired
	private ChannelOrderManager channelOrderManager;

	@Test
	@Rollback(false)
	public void testPMS() throws Exception {

		//String changed="2015-09-14"; 
		String changed="2015-08-24"; 
		String languageCode="zh_CN";
		//String hotelId="e38c50fb76d14a8e8b9f6b28a0217ddf";//
		String hotelId="16764a598ea311e4afbb76ff40eec093";
		System.out.println("run=================================================");
//       List<ChannelOrder> channelOrders=channelOrderManager.getChannelOrder(changed, hotelId, languageCode);
//       
//        for (ChannelOrder channelOrder : channelOrders) {
//			System.out.println(channelOrder);
//		}
	}
}
