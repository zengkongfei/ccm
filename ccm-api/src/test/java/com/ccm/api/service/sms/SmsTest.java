package com.ccm.api.service.sms;

import java.util.HashMap;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.service.base.BaseManagerTestCase;

public class SmsTest extends BaseManagerTestCase{

	@Autowired
	private SmsManager2 sms2;
	
	@Autowired
	private SmsManager smsManager;
	
	@Test
	@Rollback(true)
	public void testSmsSend() throws Exception {
		
		
		
		
//		sms2.smsSend("SINFON", "zh_CN", "NewReservation", "13524374816", new HashMap<String, Object>());
		
		smsManager.smsSendI18n("SMSREMIND", "18217754568", new HashMap<String, Object>(),"zh_CN",null);
		
	}
}
