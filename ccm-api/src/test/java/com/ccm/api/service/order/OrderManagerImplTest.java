package com.ccm.api.service.order;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.dao.redis.RedisDao;
import com.ccm.api.service.base.BaseManagerTestCase;

public class OrderManagerImplTest extends BaseManagerTestCase{
	@Autowired
	private OrderManager orderManager;
	@Autowired
	private RedisDao redisDao;
	@Test
	public void isReservationTest(){
//		System.out.println("============"+orderManager.isReservation("0575807682b64e0bbe142635e0b7afde", "0575807682b64e0bbe142635e0b7afde"));
//		orderManager.deleReverationKey("0575807682b64e0bbe142635e0b7afde");
//		orderManager.addReverationTime("0575807682b64e0bbe142635e0b7afde");
//		orderManager.addReverationTime("0575807682b64e0bbe142635e0b7afde");
//		orderManager.addReverationTime("0575807682b64e0bbe142635e0b7afde");
//		orderManager.addReverationTime("0575807682b64e0bbe142635e0b7afde");
//		orderManager.deleReverationKey("0575807682b64e0bbe142635e0b7afde");
//		orderManager.deleReverationKey("0575807682b64e0bbe142635e0b7afde");
//		orderManager.addReverationTime("0575807682b64e0bbe142635e0b7afde");
		orderManager.addRedisDealOrder("0575807682b64e0bbe142635e0b7afde", true, 120);
//		orderManager.addRedisDealOrder("0575807682b64e0bbe142635e0b7afde", true, 120);
//		Serializable redis = redisDao.readObject("0575807682b64e0bbe142635e0b7afde");
//		Boolean b = (Boolean)redis;
//		System.out.println(redis);
//		System.out.println(b);
	}
}
