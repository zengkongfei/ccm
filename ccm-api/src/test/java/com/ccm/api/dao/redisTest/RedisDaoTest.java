package com.ccm.api.dao.redisTest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.junit.Test;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.dao.redis.RedisDao;
import com.ccm.api.dao.rsvtype.RoomQtyDao;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.rsvtype.vo.RedisRsvtype;
import com.ccm.api.util.DateUtil;
/**
 * @author Luanjm
 * 
 */
public class RedisDaoTest extends BaseDaoTestCase {

	@Resource
	private RoomQtyDao roomQtyDao;

	@Resource
	private RedisDao redisDao;

	 List<Hotel> hotelList = new ArrayList<Hotel>();
	 
	@Test
	public void testRedisDao() throws Exception{
		
		String firstDayOfMonth="2015-08-11";
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date=df.parse(firstDayOfMonth);
		
		String key="TESTCCM"+"|"+"1BSBA"+"|"+DateUtil.getMonthOfYear(date);
		
		Map<String, Long> map=redisDao.readLongValueFromMap(key);
		Set<String> ks=map.keySet();
		Iterator<String> it=ks.iterator();
		while(it.hasNext()){
			String k=it.next();
			System.out.println(k+"====="+map.get(k));
		}
		
		
	}
	
	//@Test
	public void testOrder() throws Exception {
//		Date date=DateUtil.getFirstDayOfWeek(new Date());
		String firstDayOfMonth="2015-08-11";
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date=df.parse(firstDayOfMonth);
		RedisRsvtype  redisRsvtype=new RedisRsvtype("GAL","HJF",date,null);
//		RedisRsvtype  redisRsvtype2=new RedisRsvtype("GAL","HJF",date,new Long(10));
//		RedisRsvtype  redisRsvtype3=new RedisRsvtype("TAR","HJF",date,new Long(-5));
//		roomQtyDao.hincrebyForHash(redisRsvtype.getKey(),redisRsvtype.getDate(),redisRsvtype.getQty());
//		roomQtyDao.hincrebyForHash(redisRsvtype2.getKey(),redisRsvtype2.getDate(),redisRsvtype2.getQty());
//		roomQtyDao.hincrebyForHash(redisRsvtype.getKey(),redisRsvtype.getDate(),redisRsvtype.getQty());
//		Map<String,Long>resultMap=roomQtyDao.readLongValueFromMap(redisRsvtype.getKey());
		
		roomQtyDao.deleteFieldForHash(redisRsvtype.getKey(),DateUtil.getDate(date));
	}
	
}
