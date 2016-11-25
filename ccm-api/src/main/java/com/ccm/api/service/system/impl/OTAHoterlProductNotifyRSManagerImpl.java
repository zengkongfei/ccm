
/**
 * 
 */
package com.ccm.api.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.channel.AdsGoods;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.service.system.OTAHoterlProductNotifyRSManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.PropertiesUtil;
import com.ccm.api.util.PushDataUtil;
import com.ccm.api.util.XMLUtil;

/**
 * @author Jenny
 *
 */
@Service("otaHoterlProductNotifyRSManager")
public class OTAHoterlProductNotifyRSManagerImpl implements OTAHoterlProductNotifyRSManager {

	private Log log = LogFactory.getLog(OTAHoterlProductNotifyRSManagerImpl.class);

	private static final String ARI_URL = PropertiesUtil.getProperty("msgUrl");

	@Resource
	private HotelDao hotelDao;
	@Resource
	private ChannelgoodsDao channelgoodsDao;

	@SuppressWarnings({ "unchecked" })
	@Override
	public void analyzeResponse(AdsMessage ads) {
		long startMili = System.currentTimeMillis();
		log.info("analyzeResponse start......");

		Document d = XMLUtil.getXmlDocumentXpath(ads.getActionValue(), "http://www.opentravel.org/OTA/2003/05");
		if (d == null) {
			ads.setStatus(AdsMessage.SEND_ERROR_TBSTATUS);
			log.info("analyzeResponse end" + (System.currentTimeMillis() - startMili) + "ms dIsNull");
			return;
		}
		List<Element> pmsHotelMappingResults = d.selectNodes("/OTA_HotelProductNotifRS/xmlns:TPA_Extensions/xmlns:PMSHotelMappingResults/xmlns:PMSHotelMappingResult");
		StringBuffer sb = new StringBuffer();
		Map<String, String> hMap = new HashMap<String, String>();
		for (Element pmsHotelMappingResult : pmsHotelMappingResults) {
			String channel = pmsHotelMappingResult.elementText("Channel");
			String hotelCode = pmsHotelMappingResult.elementText("HotelCode");
			String roomTypeCode = pmsHotelMappingResult.elementText("RoomTypeCode");
			String ratePlanCode = pmsHotelMappingResult.elementText("RatePlanCode");
			String isSuccess = pmsHotelMappingResult.elementText("IsSuccess");
			// 101产品已经存在 102酒店创建失败 103价格代码不存在 104房型创建失败 109其它错误
			String errorCode = pmsHotelMappingResult.elementText("ErrorCode");
			// 处理酒店代码重复情况
			if (!hMap.containsKey(hotelCode)) {
				List<Hotel> hList = hotelDao.getHotelByHotelCode(hotelCode);
				if (hList == null || hList.isEmpty()) {
					hMap.put(hotelCode, null);
					sb.append("hotelIsNotFound,");
					continue;
				}
				hMap.put(hotelCode, hList.get(0).getHotelId());
			} else if (hMap.get(hotelCode) == null) {
				continue;
			}
			AdsGoods adsGoods = new AdsGoods();
			adsGoods.setHotelId(hMap.get(hotelCode));
			adsGoods.setHotelCode(hotelCode);
			adsGoods.setChannelCode(channel);
			adsGoods.setRatePlanCode(ratePlanCode);
			adsGoods.setRoomTypeCode(roomTypeCode);
			if ("true".equalsIgnoreCase(isSuccess)) {
				adsGoods.setForcePush(true);
				dealARIMsg(adsGoods, sb);
			} else if ("false".equalsIgnoreCase(isSuccess) && "101".equalsIgnoreCase(errorCode)) {
				adsGoods.setForcePush(false);
				dealARIMsg(adsGoods, sb);
			} else {
				channelgoodsDao.updateChannelGoodsIsBind(adsGoods, Boolean.FALSE);
			}
		}
		ads.setRquestTbData(sb.toString());
		log.info("analyzeResponse end" + (System.currentTimeMillis() - startMili) + "ms");
	}

	private void dealARIMsg(AdsGoods adsGoods, StringBuffer sb) {
		channelgoodsDao.updateChannelGoodsIsBind(adsGoods, Boolean.TRUE);
		String json = JSONObject.toJSONString(adsGoods);
		try {
			String response = PushDataUtil.postDataResponse(ARI_URL, json);
			sb.append(json).append(response).append(",");
		} catch (Exception e) {
			sb.append(json).append("postARIException,");
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
	}

}

