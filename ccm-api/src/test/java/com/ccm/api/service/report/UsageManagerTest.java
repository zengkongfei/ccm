package com.ccm.api.service.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.rsvtype.RoomQtyDao;
import com.ccm.api.dao.rsvtype.UsageDao;
import com.ccm.api.model.rsvtype.Usage;
import com.ccm.api.model.rsvtype.vo.RedisRsvtype;
import com.ccm.api.model.rsvtype.vo.UsageCriteria;
import com.ccm.api.model.rsvtype.vo.UsageResult;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.service.rsvtype.UsageManager;
import com.ccm.api.util.DateUtil;

public class UsageManagerTest extends BaseManagerTestCase {

	@Autowired           
	private UsageManager usageManager;

	@Autowired
	private UsageDao usageDao;
	@Resource
	private RoomQtyDao roomQtyDao;
	

	//@Test
	@Rollback(false)
	public void testSave() throws Exception {
		RedisRsvtype  redisRsvtype=new RedisRsvtype("TESTCCM","1BSTS",DateUtil.convertStringToDate("2016-06-30"),12);
		roomQtyDao.hincrebyForHash(redisRsvtype.getKey(),redisRsvtype.getDate(),redisRsvtype.getQty());
		
		redisRsvtype=new RedisRsvtype("ABRJL","1KBUS",DateUtil.convertStringToDate("2016-06-30"),2);
		roomQtyDao.hincrebyForHash(redisRsvtype.getKey(),redisRsvtype.getDate(),redisRsvtype.getQty());
		
	}
	
	@Test
	@Rollback(false)
	public void testUsage() throws Exception {
		
		List<String> hotelIdList=new ArrayList<String>();
		hotelIdList.add("167686c78ea311e4afbb76ff40eec093");
		hotelIdList.add("91b2b568803a4b0699fb2d6462f0fa84");
		hotelIdList.add("e0eb74a41a264095941df1be1ce3bc9a");	
		hotelIdList.add("e38c50fb76d14a8e8b9f6b28a0217ddf");
		hotelIdList.add("af1567e1248a49cc8f87500dfa249a15");
		hotelIdList.add("8424341deacd4451ada393aaf51d3f6b");
		
		List<String> channelIdList=new ArrayList<String>();
		channelIdList.add("622341fb7d5642848ffd3a78c3b6877b");
		
		UsageCriteria c=new UsageCriteria();
		
		c.setChannelIdList(channelIdList);
		
		c.setHotelIdList(hotelIdList);
		
		//c.setResvDate(DateUtil.convertStringToDate("2016-06-30"));
		c.setResvDateBegin(DateUtil.convertStringToDate("2011-01-14"));
		c.setResvDateEnd(DateUtil.convertStringToDate("2017-07-14"));
		
		//导出
		c.setNeedPage(false);
		
		//int count=usageDao.getUsagesCount(c);
		//System.out.println("count="+count);
		
		//Usage usage = usageDao.getUsagesSum(c);
		//System.out.println("sum="+usage);
	
		UsageResult r=usageManager.getUsages(c);
		
		//System.out.println("结果总数："+r.getTotalCount());
		
		//System.out.println("AvailableSum="+r.getAvailableSum());
		//System.out.println("AllotmentSum="+r.getAllotmentSum());
		//sSystem.out.println("AllotmentRemainSum="+r.getAllotmentRemainSum());
		
		List<Usage> li=r.getResultList();
		for (Usage u : li) {
			System.out.println(u);
			//System.out.println(DateUtil.convertDateTimeToString(u.getResvDate()));
		}
	
	}
}
