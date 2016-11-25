package com.ccm.api.service.redis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.dao.redis.RedisDao;
import com.ccm.api.service.base.BaseManagerTestCase;

public class RedisTest extends BaseManagerTestCase {
	@Autowired
	private RedisDao redisDao;

//	@Test
//	public void hincrebyForHashTest() {
//		final String channelId = "test111111";
//		final int a = 1;
//		List<Thread> l = new ArrayList<Thread>();
//		for (int i = 0; i < 5; i++) {
//			Thread thread = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					Map<String, Long> s = redisDao.readLongValueFromMap(channelId);
//					if (s != null && !s.isEmpty()) {
//						redisDao.hincrebyForHash(channelId, channelId, a);
//					} else {
//						redisDao.hincrebyForHash(channelId, channelId, a, 60, TimeUnit.SECONDS);
//					}
//				}
//			});
//			l.add(thread);
//		}
//
//		for (Thread t : l) {
//			t.start();
//		}
//	}

	@Test
	public void timeout() {
		final String channelId = "test111111";
		final int a = 1;
		Map<String, Long> s = redisDao.readLongValueFromMap(channelId);
		if (s != null && !s.isEmpty()) {
			redisDao.hincrebyForHash(channelId, channelId, a);
		} else {
			redisDao.hincrebyForHash(channelId, channelId, a, 60, TimeUnit.SECONDS);
		}
	}
	@Test
	public void save() {
		String channelId = "test111111";
		redisDao.save(channelId, true, 60, TimeUnit.SECONDS);
	}
}
