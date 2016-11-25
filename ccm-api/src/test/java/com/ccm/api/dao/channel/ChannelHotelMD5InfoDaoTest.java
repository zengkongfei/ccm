package com.ccm.api.dao.channel;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.channel.ChannelHotelMD5Info;

public class ChannelHotelMD5InfoDaoTest extends BaseDaoTestCase{
	
	@Resource
	private ChannelHotelMD5InfoDao channelHotelMD5InfoDao;
	
	@Test
	@Rollback(value=false)
	public void saveTest(){
		/** sihji  ctrip*/
		ChannelHotelMD5Info channelHotelMD5Info = new ChannelHotelMD5Info();
		channelHotelMD5Info.setHotelId("af1567e1248a49cc8f87500dfa249a15");
		channelHotelMD5Info.setChannelId("49a9c1e749614471ad8822b83cff6753");
		
		channelHotelMD5InfoDao.save(channelHotelMD5Info);
	}
	
	@Test
	@Rollback(value=false)
	public void getTest(){
		/** sihji  ctrip*/
		ChannelHotelMD5Info channelHotelMD5Info = channelHotelMD5InfoDao.get("643f81ed2b0342499fb6f716ef540094");
		System.out.println(channelHotelMD5Info);
	}
	
	@Test
	@Rollback(value=false)
	public void getChannelHotelMD5InfoByParamTest(){
		/** sihji  ctrip*/
		String channelId="49a9c1e749614471ad8822b83cff6753";
		String type="1";
		String id="af1567e1248a49cc8f87500dfa249a15";
		ChannelHotelMD5Info channelHotelMD5Info = channelHotelMD5InfoDao.getChannelHotelMD5InfoByParam(channelId, type, id);
		System.out.println(channelHotelMD5Info);
	}
	
}
