package com.ccm.api.service.taobaoRoom;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.service.taobaoAPI.TaobaoRoomManager;

public class TaobaoRoomManagerTest extends BaseManagerTestCase {

	@Autowired
	private TaobaoRoomManager taobaoRoomManager;

	
	@Test
	@Rollback(false)
	public void onlineToTaobaoTest() throws Exception {
		@SuppressWarnings("deprecation")
		Date date = new Date(2013-1900, 11, 8);
		taobaoRoomManager.onlineToTaobao("COL", "3648555", date, "1234", "1234");
	}
}
