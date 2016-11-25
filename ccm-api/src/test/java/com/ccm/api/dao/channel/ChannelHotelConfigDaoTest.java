package com.ccm.api.dao.channel;

import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.dao.channel.mongodb.ChannelHotelConfigDaoMongo;
import com.ccm.api.dao.log.mongodb.DeleteLogDaoMongo;
import com.ccm.api.model.channel.ChannelHotelConfig;
import com.ccm.api.model.log.DeleteLog;

public class ChannelHotelConfigDaoTest extends BaseDaoTestCase {
	
	@Autowired
	private ChannelHotelConfigDaoMongo channelHotelConfigDao;
	
	@Test
	public void getChannelTest() throws Exception{
		
		ChannelHotelConfig channelHotelConfig=new ChannelHotelConfig();
		channelHotelConfig.setChannelId("qd1");//渠道id
		channelHotelConfig.setPushMethod(1);//推送方法：单独推送=1  全量推送=2    页面单选  默认1 
		//酒店配置,默认为true,不勾选的则不在字段中存
		channelHotelConfig.setChannelHotelConfig(
		"'address':'true','chainCode':'true','city':'true','cityName':'true','countryCode':'true','email':'true','fax':'true','hotelCode':'true','hotelName':'true','languageCode':'true','postCode':'true','tbShopName':'true','telephone':'true'"
		);
		//房型配置,默认为true,不勾选的则不在字段中存
		channelHotelConfig.setChannelRoomTypeConfig(
		"'bed_type':'true','description':'true','maxOccupancy':'true','roomTypeCode':'true','roomTypeName':'true'"		
		);
		channelHotelConfig.setChannelHotelConfigId(UUID.randomUUID().toString().replace("-", ""));//设置主键
		
		channelHotelConfigDao.addChannelHotelConfig(channelHotelConfig);
		//channelHotelConfigDao.getChannelHotelConfigByChannelId("qd1");
		
		System.out.println("test over");
	}
	
}
