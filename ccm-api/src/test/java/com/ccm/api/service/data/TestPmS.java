package com.ccm.api.service.data;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.service.common.DictCodeManager;

public class TestPmS extends BaseManagerTestCase {

	@Autowired
	private DictCodeManager dictCodeManager;

	@Test
	@Rollback(false)
	public void testPMS() throws Exception {

		String pmsRoomCode = "";
		String hotelCode = "TEAZJ";
		Map<String, String> codeMap = dictCodeManager.searchCodeMapByChannelHotel(OXIConstant.orderStatus, "44a605dc-f201-11e3-b373-a6124581", null, true);

		String roomCode = codeMap.get(pmsRoomCode);
		System.out.println(StringUtils.isBlank(roomCode) ? pmsRoomCode : roomCode);
	}
}
