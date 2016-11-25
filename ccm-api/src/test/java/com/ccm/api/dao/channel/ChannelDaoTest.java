package com.ccm.api.dao.channel;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.util.DateUtil;

public class ChannelDaoTest extends BaseDaoTestCase {
	@Autowired
	private ChannelDao channelDao;
	
	@Test
	public void getChannelTest() throws Exception{
//		String id = "36ac46679baf42ab8f62115bba61f5c9";
//		Channel channel = channelDao.get(id);
//		System.out.println(channel);
		// 排除监控时间  开始时间
		Date invalidTimeBegin = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss","1970-01-01 08:30:30");
		// 排除监控时间  结束时间
		Date invalidTimeEnd = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss","1970-01-01 15:30:30");
		
		Date creataTime =DateUtil.convertDateToDate("HH:mm:ss", new Date());
		System.out.println(DateUtil.convertDateTimeToString(creataTime));
		System.out.println(DateUtil.convertDateTimeToString(invalidTimeBegin));
		System.out.println(DateUtil.convertDateTimeToString(invalidTimeEnd));
		System.out.println(creataTime.after(invalidTimeBegin));
		System.out.println(creataTime.before(invalidTimeEnd));
		
	}
}
