package com.ccm.api.service.block;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.opentravel.ota._2003._05.OTAHotelInvBlockNotifRQ;

import com.ccm.api.model.constant.MessageType;
import com.ccm.api.service.log.SendOTALogManager;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.XMLUtil;

public class OTAAllotmentTransformer{
	public static void  testOTA(){
		/*
		OTAHotelInvBlockNotifRQ otaHotelInvBlockNotifRQ=readOTABlock("D:/test/OTA_HotelInvBlockNotifRQ.xml");
		try {
			String handledXML = XMLUtil
			.JAXBParseToXml(otaHotelInvBlockNotifRQ);
			writeByNIO(handledXML,"D:/test/CONVERT_HotelInvBlockNotifRQ.xml");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		String invBlockGroupingCode = "1234567";
		String hotelCode = "TESTCCM";
		String roomType = "1BSTS";

		Date inventoryDate = null;
		Date cutOffDate = null;
		try {
			inventoryDate = df.parse("2015-10-17");
			cutOffDate = df.parse("2015-10-16");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Integer allotmentQty = 9;
		Integer allotmentSold = 2;
		
		List<Map<String, String>> invBlockList=new Vector<Map<String, String>> ();
		Map<String, String> otaBlockMap = new HashMap<String, String>();
		otaBlockMap.put("actionType", "NEW");
		otaBlockMap.put("hotelCode", hotelCode);
		otaBlockMap.put("chainCode",
				"TAOBAO");
		otaBlockMap.put("invBlockCode", invBlockGroupingCode);
		otaBlockMap.put("invBlockGroupingCode",
				invBlockGroupingCode);
		otaBlockMap.put("startDate",
				DateUtil.convertDateToString(inventoryDate));
		otaBlockMap.put("endDate",
				DateUtil.convertDateToString(inventoryDate));
		otaBlockMap.put("cutOffDate",
				DateUtil.convertDateToString(cutOffDate));
		otaBlockMap.put("roomTypeCode",roomType);
		otaBlockMap.put("numberOfUnits",String.valueOf(allotmentQty-allotmentSold));
		invBlockList.add(otaBlockMap);
	}
	public static void main(String ...args){
		System.out.println( new Date().getTime());
	}
}
