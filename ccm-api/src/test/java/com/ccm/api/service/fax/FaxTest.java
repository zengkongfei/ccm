package com.ccm.api.service.fax;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.dao.fax.FaxSendDao;
import com.ccm.api.dao.hotel.HotelMCDao;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.constant.SmsType;
import com.ccm.api.model.fax.FaxSend;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.order.Master;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

public class FaxTest extends BaseManagerTestCase{

	@Autowired
	private HotelMCDao hotelMCDao;
	@Autowired
	private MasterDao masterDao;
	@Autowired
	private FaxManager faxManager;
	@Autowired
	private FaxSendDao faxSendDao;
	
//	@Test
//	@Rollback(true)
//	public void testSend(){
//		String hotelId="af1567e1248a49cc8f87500dfa249a15";
//		String masterId="99221409";
//		HotelVO hotel = hotelMCDao.gethotelRemindInfo(hotelId);
//		//配置了Efax提醒
//		if(StringUtils.hasLength(hotel.getRemindEfax())){
//			Master m = masterDao.getMasterOrderByOrderId(masterId);
//			sendEfax2HotelOff(m,hotel);
//		}
//	}
	
	@Test
	public void queryTask() {
		/*
		 * 07-08 16:05
		 */
		String msgid="interface-chinaonline@gfax.cn-1468464908755";
//		String msgid="interface-chinaonline@gfax.cn-1467687840243";
		String result = faxManager.queryTask(msgid);
		System.out.println(result);
//		/*
//		 * 07-08 16:05
//		 */
//		String msgid1="interface-chinaonline@gfax.cn-1467965101953";
//		String result1 = faxManager.queryTask(msgid1);
//		System.out.println(result1);
//		/*
//		 * 07-08 15:20
//		 */
//		String msgid2="interface-chinaonline@gfax.cn-1467962400950";
//		String result2 = faxManager.queryTask(msgid2);
//		System.out.println(result2);
//		/*
//		 * 07-08 15:20
//		 */
//		String msgid3="interface-chinaonline@gfax.cn-1467962402897";
//		String result3 = faxManager.queryTask(msgid3);
//		System.out.println(result3);
//		
//		/*
//		 * 2016-07-05 11:04:00
//		 */
//		String msgid4="interface-chinaonline@gfax.cn-1467687840243";
//		String result4 = faxManager.queryTask(msgid4);
//		System.out.println(result4);
	}
	
	/**
	 * 发送提醒fax
	 * @param Master m
	 * @param HotelVO hotel
	 * @return
	 */
	private String sendEfax2HotelOff(Master m,HotelVO hotel) {
		Date now = new Date();
		FaxSend faxSend = new FaxSend();
		faxSend.setFaxNumber( hotel.getRemindEfax());
		faxSend.setFaxType(SmsType.SMS_TYPE_EMAIL);
		faxSend.setVerifyCode(LanguageCode.MAIN_LANGUAGE_CODE);
		faxSend.setBizId(m.getMasterId());
		faxSend.setSendTime(now);
		String resultCode = null;
		try {
			String date = DateUtil.convertDateToString(now);
			String time = DateUtil.getTimeNow(now);
			Map<String,String> map = new HashMap<String, String>();
			map.put("tel", !StringUtils.hasText(hotel.getTelephone())?"-":hotel.getTelephone());
			map.put("fax", hotel.getRemindEfax());
			map.put("date", date);
			map.put("time", time);
//			String result = faxManager.sendFax(map, hotel.getRemindEfax());
			String result ="0";
			JSONObject json = JSONObject.parseObject(result);
			resultCode = json.getString("result");
			String msgid = json.getString("msgid");
			faxSend.setMsgId(msgid);
			faxSend.setResultCode(resultCode);
			if("-1".equalsIgnoreCase(msgid)){
				faxSend.setResultMsg("传真提交失败");
			}else if("0".equalsIgnoreCase(msgid)){
				faxSend.setResultMsg("传真提交成功");
			}else if("-3".equalsIgnoreCase(msgid)){
				faxSend.setResultMsg("身份认证失败");
			}
		} catch (Exception e) {
			String msg = CommonUtil.getExceptionMsg(e, new String[] { "ccm" });
			if (msg.length() > 200) {
				msg = msg.substring(0, 200);
			}
			resultCode = "-99";
			faxSend.setResultCode(resultCode);
			faxSend.setResultMsg(msg);
		}
		faxSendDao.save(faxSend);
		return resultCode;
	}
}
