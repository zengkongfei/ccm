package com.ccm.api.service.system.impl;

import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.opentravel.ota._2003._05.AvailabilityStatusType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.ccm.api.dao.ads.AdsMessageDao;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.hotel.ChainDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.ratePlan.PriceCalcDao;
import com.ccm.api.dao.ratePlan.mongodb.RoomMsgDaoMongo;
import com.ccm.api.dao.redis.RedisDao;
import com.ccm.api.dao.rsvtype.RoomQtyDao;
import com.ccm.api.dao.rsvtype.RsvchanBlockDao;
import com.ccm.api.jms.RoomJmsManager;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.ads.vo.AdsLogMessageCriteria;
import com.ccm.api.model.channel.AdsGoods;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.constant.CharacterSet;
import com.ccm.api.model.constant.OrderStatus;
import com.ccm.api.model.email.vo.EmailVO;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.email.EmailManager;
import com.ccm.api.service.email.EmailProperties;
import com.ccm.api.service.ratePlan.RateAmountManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.service.system.Push2ChannelStaticMsgManager;
import com.ccm.api.service.system.PushManage;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.DateUtils;
import com.ccm.api.util.PushDataUtil;

@Service("pushManage")
public class PushManageImpl implements PushManage {
	protected final Log log = LogFactory.getLog(getClass());
	@Resource
	private ChannelManager channelManager;
	@Resource
	private HotelDao hotelDao;
	@Resource
	private RoomJmsManager roomJmsManager;
	private String userId = "COL$Expedia";
	private String password = "H57w3aRgy6";
	@Resource
	private ChainDao chainDao;
	@Resource
	private PriceCalcDao priceCalcDao;
	@Resource
	private AdsMessageDao adsMessageDao;
	@Resource
	private AdsMessageDao adsMessageErrDao;
	@Resource
	private ChannelDao channelDao;
	@Resource
	private ChannelgoodsDao channelgoodsDao;
	@Resource
	private RsvtypeChannelManager rsvtypeChannelManager;
	@Resource
	private ChannelgoodsManager channelgoodsManager;

	private HashMap<String, String> staMap;
	@Resource
	private RoomMsgDaoMongo roomMsgDaoMongo;
	@Resource
	private RoomTypeManager roomTypeManager;
	@Resource
	private EmailManager emailManager;
	@Resource
	private RsvchanBlockDao rsvchanBlockDao;
	@Resource
	private RedisDao redisDao;
	@Resource
	private RoomQtyDao roomQtyDao;
	@Resource
	private RateAmountManager rateAmountManager;
	@Resource
	private VelocityEngine velocityEngine;
	@Resource
	private Push2ChannelStaticMsgManager push2ChannelStaticMsgManager;

	/** 房量 **/
	@Override
	@Async
	public void pushAvailability(RoomMsg rm, Channel c) {
		String channelCode = rm.getChannelCode();
		if (c == null) {
			c = channelManager.getChannelByChannelCode(channelCode);
		}
		if (CommonUtil.isEmpty(c.getPushAddress())) {
			log.warn("PushAddress is null");
			return;
		}
		if (c.getPushRavl() != null && !c.getPushRavl()) {
			log.warn("PushRavl or PushRavl is null or date err");
			return;
		}
		try {
			Date date = DateUtil.convertStringToDate(rm.getStartDate());
			if (!c.isPush(DateUtil.addDays(date, -1))) {
				return;
			}
			if (DateUtil.convertStringToDate(rm.getStartDate()).before(DateUtil.cleanTimeDate(new Date()))) {
				return;
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String POST_URL = c.getHttpPushAddress();
		String tmpUrl = "pushXmlTemplate/FIDELIO_AvailabilityStatusRQ.xml";
		String chainCode = rm.getChainCode();
		String hotelCode = rm.getHotelCode(); // 酒店代码 上海嘉豪淮海国际豪生酒店

		String rtCode = rm.getRoomTypeCode(); // 房型代码 高级大床房
		String roomsAvailable = "9999";
		String echoToken = CommonUtil.generateSequenceNo();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("echoToken", echoToken);
		model.put("password", password);
		model.put("userID", userId);
		model.put("profile", chainCode);
		model.put("destination", POST_URL);
		model.put("userAgent", chainCode);

		model.put("chainCode", chainCode);
		model.put("hotelCode", hotelCode);
		model.put("channelCode", channelCode);
		model.put("startDate", rm.getStartDate());
		model.put("roomsAvailable", roomsAvailable);
		model.put("roomSegmentAvailable", rm.getAmount());
		model.put("roomTypeCode", rtCode);

		String status = AdsMessage.EXEC_ERROR_STATUS;
		String errMsg = "";
		String avaiStr = "";
		try {
			avaiStr = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tmpUrl, CharacterSet.defaultCode, model);
			if (PushDataUtil.postDataIsOK(POST_URL, avaiStr)) {
				status = AdsMessage.EXEC_END_STATUS;
			}
		} catch (Exception e1) {
			errMsg = CommonUtil.getExceptionMsg(e1, "ccm");
			log.error("!!!" + errMsg);
		}
		StringBuffer actionValue = new StringBuffer();
		actionValue.append("date=" + model.get("startDate")).append(" available=" + model.get("roomSegmentAvailable"));
		saveAdsMessage(errMsg, status, echoToken, channelCode, chainCode, hotelCode, rtCode, "", avaiStr, AdsMessage.ADSTYPE_AVAILABILITY, actionValue.toString(), rm.getStartDate());
	}

	private AdsMessage saveAdsMessage(String errMsg, String status, String echoToken, String channelCode, String chainCode, String hotelCode, String rtCode, String ratePlanCode, String content, String adsType, String actionValue, String actionDate) {
		AdsMessage adsMsg = new AdsMessage();
		adsMsg.setErrMsg(errMsg);
		adsMsg.setStatus(status);
		adsMsg.setEchoToken(echoToken);
		adsMsg.setChainCode(chainCode);
		adsMsg.setTargetGDS(channelCode);
		adsMsg.setHotelCode(hotelCode);
		adsMsg.setRoomTypeCode(rtCode);
		adsMsg.setRatePlanCode(ratePlanCode);
		adsMsg.setContent(content);
		adsMsg.setMsgType(AdsMessage.MSGTYPE_PUSH);
		adsMsg.setAdsType(adsType);
		adsMsg.setActionValue(actionValue);
		try {
			if (actionDate != null) {
				adsMsg.setActionDate(DateUtil.convertStringToDate(actionDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (AdsMessage.EXEC_ERROR_STATUS.equals(status)) {
			return adsMessageErrDao.save(adsMsg);
		}
		return adsMessageDao.save(adsMsg);
	}

	/**
	 * 开关房老协议格式
	 * */
	private void pushAvailUpdateNotif_ads(RoomMsg rm, Channel channel) {

		String channelCode = rm.getChannelCode();
		String post_url = channel.getHttpPushAddress();
		String chainCode = rm.getChainCode();
		String hotelCode = rm.getHotelCode();
		String rtCode = rm.getRoomTypeCode();
		String rateCode = rm.getRateCode();
		String echoToken = CommonUtil.generateSequenceNo();
		String profile = chainCode;
		String destination = post_url;
		String version = "1.0";

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userId", userId);
		model.put("password", password);
		model.put("type", "AvailabilityRestrictionMessage");
		model.put("profile", profile);
		model.put("destination", destination);
		model.put("echoToken", echoToken);
		model.put("timeStamp", DateUtils.dateToXmlDate(new Date()));
		model.put("version", version);
		model.put("chainCode", chainCode);
		model.put("hotelCode", hotelCode);
		model.put("roomTypeCode", rtCode);
		if (rm.getOnOff()) {
			model.put("actionCode", "Open"); //
		} else {
			model.put("actionCode", "Close"); //
		}
		if (CommonUtil.isNotEmpty(rateCode)) {
			model.put("rateCode", rateCode);
			model.put("inventory", false);
		} else {
			model.put("allRateCode", true);
			model.put("inventory", true);
		}

		model.put("targetGDS", channelCode);
		String status = AdsMessage.EXEC_ERROR_STATUS;
		String errMsg = "";
		String xmlStr = "";
		try {
			model.put("start", DateUtils.dateToXmlDate(DateUtil.convertStringToDate(rm.getStartDate())));
			model.put("end", DateUtils.dateToXmlDate(DateUtil.convertStringToDate(rm.getStartDate())));
			model.put("duration", DateUtil.dateDiff(rm.getStartDate(), rm.getStartDate(), DateUtil.getDatePattern()).intValue());

			String tmpUrl = "pushXmlTemplate/FIDELIO_AvailUpdateNotifRQ.xml";
			xmlStr = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tmpUrl, CharacterSet.defaultCode, model);

			if (PushDataUtil.postDataIsOK(post_url, xmlStr)) {
				status = AdsMessage.EXEC_END_STATUS;
			}
		} catch (Exception e) {
			errMsg = CommonUtil.getExceptionMsg(e, "ccm");
			log.error("!!!" + errMsg);
		}
		StringBuffer actionValue = new StringBuffer();
		actionValue.append("date=" + rm.getStartDate()).append(" actionCode=" + model.get("actionCode"));
		saveAdsMessage(errMsg, status, echoToken, channelCode, chainCode, hotelCode, rtCode, rateCode, xmlStr, AdsMessage.ADSTYPE_AVAILUPDATE, actionValue.toString(), rm.getStartDate());
		if (status.equals(AdsMessage.EXEC_END_STATUS)) {
			String switchKey = null;
			if (CommonUtil.isNotEmpty(rateCode)) {
				switchKey = "RtavSwitch-" + channelCode + "-" + hotelCode + "-" + rtCode + "-" + rateCode + "-" + rm.getStartDate();
			} else {
				switchKey = "RstuSwitch-" + channelCode + "-" + hotelCode + "-" + rtCode + "-" + rm.getStartDate();
			}
			Long diffdays = DateUtil.dateDiff(DateUtil.getDate(new Date()), rm.getStartDate());
			if (diffdays.intValue() >= 0)
				redisDao.save(switchKey, rm.getOnOff(), diffdays + 1, TimeUnit.DAYS);
		}
	}

	/** 开关房OTA **/
	@Override
	public void pushAvailUpdateNotif(RoomMsg rm, Channel channel) {
		String channelCode = rm.getChannelCode();
		if (channel == null) {
			channel = channelManager.getChannelByChannelCode(channelCode);
		}

		if (CommonUtil.isEmpty(channel.getPushAddress())) {
			return;
		}

		try {
			Date date = DateUtil.convertStringToDate(rm.getStartDate());
			if (!channel.isPush(DateUtil.addDays(date, -1))) {
				return;
			}
			if (DateUtil.convertStringToDate(rm.getStartDate()).before(DateUtil.cleanTimeDate(new Date()))) {
				return;
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if ((channel.getPushRtav() != null && channel.getPushRtav()) || (channel.getPushRstu() != null && channel.getPushRstu())) {

			String post_url = channel.getHttpPushAddress();
			String chainCode = rm.getChainCode();
			String hotelCode = rm.getHotelCode(); // 酒店代码
			String rtCode = rm.getRoomTypeCode(); // 房型代码
			String rateCode = rm.getRateCode();
			String echoToken = CommonUtil.generateSequenceNo();
			String version = "1.0";
			String currencyCode = "CNY";

			if (CommonUtil.isEmpty(rateCode)) {
				this.pushAvailUpdateNotif_ads(rm, channel);
				return;
			}
			String tmpUrl = "pushXmlTemplate/OTA_HotelRatePlanNotifRQ.xml";

			Map<String, Object> modelMap = new HashMap<String, Object>();
			// OTA_HotelRatePlanNotifRQ
			modelMap.put("echoToken", echoToken);
			modelMap.put("timeStamp", DateUtils.dateToXmlDate(new Date()));
			modelMap.put("version", new BigDecimal(version));
			// RatePlans
			modelMap.put("hotelCode", hotelCode);
			modelMap.put("chainCode", chainCode);
			// RatePlan
			modelMap.put("currencyCode", currencyCode);
			modelMap.put("rateCode", rateCode);
			// DestinationSystemsCode
			modelMap.put("channelCode", channelCode);
			// Rate
			modelMap.put("rtCode", rtCode);
			modelMap.put("start", rm.getStartDate());
			modelMap.put("end", rm.getStartDate());

			if (rm.getOnOff()) {
				modelMap.put("status", AvailabilityStatusType.OPEN.value()); //
			} else {
				modelMap.put("status", AvailabilityStatusType.CLOSE.value()); //
			}
			// if(CommonUtil.isNotEmpty(rateCode)){
			// aus.setRateCode(rateCode);
			// aus.setInventory(false);
			// }else{
			// aus.setAllRateCode(true);
			// aus.setInventory(true);
			// }

			String status = AdsMessage.EXEC_ERROR_STATUS;
			String errMsg = "";
			String xmlStr = "";
			try {
				modelMap.put("rtav", 1);
				xmlStr = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tmpUrl, CharacterSet.defaultCode, modelMap);

				if (PushDataUtil.postDataIsOK(post_url, xmlStr)) {
					status = AdsMessage.EXEC_END_STATUS;
				}
			} catch (Exception e) {
				errMsg = CommonUtil.getExceptionMsg(e, "ccm");
				log.error("!!!" + errMsg);
			}
			StringBuffer actionValue = new StringBuffer();
			actionValue.append("date=" + rm.getStartDate()).append(" actionCode=" + modelMap.get("status"));
			saveAdsMessage(errMsg, status, echoToken, channelCode, chainCode, hotelCode, rtCode, rateCode, xmlStr, AdsMessage.ADSTYPE_AVAILUPDATE, actionValue.toString(), rm.getStartDate());
			if (status.equals(AdsMessage.EXEC_END_STATUS)) {
				String switchKey = null;
				if (CommonUtil.isNotEmpty(rateCode)) {
					switchKey = "RtavSwitch-" + channelCode + "-" + hotelCode + "-" + rtCode + "-" + rateCode + "-" + rm.getStartDate();
				} else {
					switchKey = "RstuSwitch-" + channelCode + "-" + hotelCode + "-" + rtCode + "-" + rm.getStartDate();
				}
				Long diffdays = DateUtil.dateDiff(DateUtil.getDate(new Date()), rm.getStartDate());
				if (diffdays.intValue() >= 0)
					redisDao.save(switchKey, rm.getOnOff(), diffdays + 1, TimeUnit.DAYS);
			}
		}
	}

	/** 房价 */
	@Override
	public void pushRateUpdateNotif(PriceCalc pc) {
		String channelCode = pc.getChannelCode();
		Channel channel = channelManager.getChannelByChannelCode(channelCode);
		if (channel != null && channel.getPushRate() != null && !channel.getPushRate()) {
			return;
		}
		if (channel == null || CommonUtil.isEmpty(channel.getPushAddress())) {
			return;
		}
		try {
			Date date = DateUtil.convertStringToDate(pc.getDate());
			if (!channel.isPush(DateUtil.addDays(date, -1))) {
				return;
			}
			if (DateUtil.convertStringToDate(pc.getDate()).before(DateUtil.cleanTimeDate(new Date()))) {
				return;
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String post_url = channel.getHttpPushAddress();
		String chainCode = pc.getChainCode();
		String hotelCode = pc.getHotelCode(); // 酒店代码 上海嘉豪淮海国际豪生酒店

		String rtCode = pc.getRoomTypeCode(); // 房型代码 高级大床房
		String rateCode = pc.getRatePlanCode();
		String echoToken = CommonUtil.generateSequenceNo();

		String currencyCode = "CNY";
		if (CommonUtil.isNotEmpty(pc.getCurrencyCode())) {
			currencyCode = pc.getCurrencyCode();
		} else {
			log.error("currencyCode is null! hotelCode:" + hotelCode);
		}
		String start = pc.getDate();
		String end = pc.getDate();

		String tmpUrl = "pushXmlTemplate/OTA_HotelRatePlanNotifRQ.xml";

		Map<String, Object> modelMap = new HashMap<String, Object>();
		// OTA_HotelRatePlanNotifRQ
		modelMap.put("echoToken", echoToken);
		modelMap.put("timeStamp", DateUtils.dateToXmlDate(new Date()));
		modelMap.put("version", new BigDecimal("1.0"));
		// RatePlans
		modelMap.put("hotelCode", hotelCode);
		modelMap.put("chainCode", chainCode);
		// RatePlan
		modelMap.put("currencyCode", currencyCode);
		modelMap.put("rateCode", rateCode);
		// DestinationSystemsCode
		modelMap.put("channelCode", channelCode);
		// Rate
		modelMap.put("rtCode", rtCode);
		modelMap.put("start", start);
		modelMap.put("end", end);
		// BaseByGuestAmts
		List<com.ccm.api.model.ratePlan.RateAmount> rateList = pc.getRateAmountList();
		String rateAmountStr = "";
		List<com.ccm.api.model.ratePlan.RateAmount> additionalGuestAmounts = new ArrayList<com.ccm.api.model.ratePlan.RateAmount>();
		List<com.ccm.api.model.ratePlan.RateAmount> baseByGuestAmts = new ArrayList<com.ccm.api.model.ratePlan.RateAmount>();
		for (com.ccm.api.model.ratePlan.RateAmount rateAmount2 : rateList) { // 人数
			if (rateAmount2.getNumberOfUnits().intValue() == 101) {
				additionalGuestAmounts.add(rateAmount2);
				continue;
			} else if (rateAmount2.getNumberOfUnits().intValue() == 102) {
				continue;
			}
			baseByGuestAmts.add(rateAmount2);
			rateAmountStr += rateAmount2.getNumberOfUnits() + "=" + rateAmount2.getBaseAmount() + "-" + rateAmount2.getAmountAfterTax() + ";";
		}
		modelMap.put("baseByGuestAmts", baseByGuestAmts);
		modelMap.put("additionalGuestAmounts", additionalGuestAmounts);
		String status = AdsMessage.EXEC_ERROR_STATUS;
		String errMsg = "";
		String xmlStr = "";
		try {
			modelMap.put("rtav", 0);
			xmlStr = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tmpUrl, CharacterSet.defaultCode, modelMap);

			if (PushDataUtil.postDataIsOK(post_url, xmlStr)) {
				status = AdsMessage.EXEC_END_STATUS;
			}
		} catch (Exception e) {
			errMsg = CommonUtil.getExceptionMsg(e, "ccm");
			log.error("!!!" + errMsg);
		}
		StringBuffer actionValue = new StringBuffer();
		actionValue.append("date=" + start).append(" rateAmount=[" + rateAmountStr + "]");
		saveAdsMessage(errMsg, status, echoToken, channelCode, chainCode, hotelCode, rtCode, rateCode, xmlStr, AdsMessage.ADSTYPE_RATE, actionValue.toString(), start);
	}

	@Override
	public void sendByDate(Date startDate, Date endDate, String sendChannelCode) {
		// 获取渠道列表
		System.out.println("sendByDate  " + startDate + sendChannelCode);

		log.info("manualSend start...");
		Date pushDate = startDate;
		// 获取渠道列表
		try {
			List<Channel> channelList = channelDao.getAllDistinct();
			HashMap<String, Object> sendMap = new HashMap<String, Object>();
			HashMap<String, Object> sendAvaiMap = new HashMap<String, Object>();
			for (Channel channel : channelList) {
				// push block list for channel
				List<RsvchanBlock> finalChannelRsvchanBlockWithRatesList = new ArrayList<RsvchanBlock>();
				String channelCode = channel.getChannelCode();
				if (CommonUtil.isEmpty(channel.getMaxPushDay())) {
					continue;
				}
				if (CommonUtil.isEmpty(channel.getPushAddress()) || CommonUtil.isEmpty(channelCode)) {
					continue;
				}
				List<RoomMsg> rmList = new ArrayList<RoomMsg>();
				ChannelGoodsVO channelGoodsVO = new ChannelGoodsVO();
				channelGoodsVO.setChannelId(channel.getChannelId());
				channelGoodsVO.setExpireDate(pushDate);
				channelGoodsVO.setStatus(ChannelGoodsStatus.publish);// 查询已发布的关系
				channelGoodsVO.setCreatedTime(new Date());
				channelGoodsVO.setIsBind(true);
				List<ChannelGoodsVO> cgvoList = channelgoodsDao.getChannelGoodsVORoomTypeByChannelGoods(channelGoodsVO);//
				if (CommonUtil.isEmpty(cgvoList)) {
					continue;
				}

				for (ChannelGoodsVO cgvo : cgvoList) {
					if (cgvo == null) {
						log.warn(channelGoodsVO + " cgvo is null");
						continue;
					}
					Hotel h = hotelDao.getHotel(cgvo.getHotelId());
					if (h == null) {
						log.warn(channelGoodsVO + " h is null");
						continue;
					}
					Chain chain = chainDao.getChainById(h.getChainId());
					if (chain == null) {
						log.warn(channelGoodsVO + "chain is null");
						continue;
					}

					String chainCode = chain.getChainCode();
					String hotelCode = h.getHotelCode();
					String roomTypeCode = cgvo.getRoomTypeCode();
					String ratePlanCode = cgvo.getRatePlanCode();

					// block
					String pushDateStr = DateUtil.convertDateToString(pushDate);
					List<RsvchanBlock> rsvchanBlockList = rsvchanBlockDao.getHandSendRsvchanBlock(hotelCode, channelCode, pushDateStr, roomTypeCode);
					if (CommonUtil.isEmpty(rsvchanBlockList) == false) {
						for (RsvchanBlock rsvchanBlock : rsvchanBlockList) {
							if (rsvchanBlock.getRatePlanCodes() != null) {
								if (rsvchanBlock.getRatePlanCodes().contains(ratePlanCode))
									finalChannelRsvchanBlockWithRatesList.add(rsvchanBlock);
							}
						}
					}

					// 查询价格detail
					PriceCalc priceCalc = new PriceCalc();
					priceCalc.setHotelId(h.getHotelId());
					priceCalc.setStartDate(DateUtil.convertDateToString(pushDate));
					priceCalc.setEndDate(DateUtil.convertDateToString(pushDate));

					List<String> rtCodeList = new ArrayList<String>();
					rtCodeList.add(roomTypeCode);
					priceCalc.setRoomTypeCodeList(rtCodeList);

					List<String> rateCodeList = new ArrayList<String>();
					rateCodeList.add(ratePlanCode);
					priceCalc.setRatePlanCodeList(rateCodeList);

					String roomAvaiKey = channelCode + chainCode + hotelCode + roomTypeCode;

					if (!sendAvaiMap.containsKey(roomAvaiKey)) {
						// 推送房量/开关
						RsvtypeChannel rt = new RsvtypeChannel();
						rt.setChannelId(cgvo.getChannelId());
						rt.setHotelCode(hotelCode);
						rt.setType(roomTypeCode);
						rt.setDate(pushDate);
						rt.setHotelid(h.getHotelId());
						rt.setIsDailyDay(true);
						List<RsvtypeChannel> rcList = rsvtypeChannelManager.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, rt.getDate(), DateUtil.addDays(rt.getDate(), 1), channelCode);
						if (CommonUtil.isEmpty(rcList)) {
							rcList = new ArrayList<RsvtypeChannel>();
							rcList.add(rt);
						}
						for (RsvtypeChannel rc : rcList) {
							int sendAvai = 0;
							// 获取该天该ob量
							rc.setChannelCode(channelCode);
							int obAvailable = rsvtypeChannelManager.getObAvailable(rc);
							int totalObSold = CommonUtil.getInt(rc.getTotalOBSold());
							if (CommonUtil.getInt(rc.getAvailable()) >= 0) {
								if (rc.getFreeSell() == null || rc.getFreeSell()) {
									int roomTypePhysicalRoom = 0;
									if (null == rc.getAvailable()) {
										roomTypePhysicalRoom = roomTypeManager.getRoomTypePhysicalRoom(rc.getType(), rc.getHotelCode());
									}
									String key = hotelCode + "|" + roomTypeCode + "|" + DateUtil.getMonthOfYear(pushDate);
									Map<String, Long> redisRoomQtyMap = roomQtyDao.readLongValueFromMap(key);
									Long redisSoldQty = new Long(0);
									if (redisRoomQtyMap != null) {
										if (redisRoomQtyMap.get(DateUtil.getDate(rc.getDate())) != null) {
											redisSoldQty = redisRoomQtyMap.get(DateUtil.getDate(rc.getDate()));
										}
									}
									sendAvai = roomTypePhysicalRoom + CommonUtil.getInt(rc.getAvailable()) + (obAvailable - totalObSold) - redisSoldQty.intValue();
								} else {
									sendAvai = obAvailable - totalObSold;
								}
							}
							rc.setSendAvailable(sendAvai);
							rc.setChannelCode(channelCode);
							rc.setChannel(channelCode);
							rc.setInvSnapInvoke(true);
							rc.setIsDailyDay(true);
							log.info("pushManage.dailySend->" + AdsMessage.ADSTYPE_AVAILUPDATE);
							// 推送房量
							if ((channel.getPushRavl() == null ? false : channel.getPushRavl()))
								roomJmsManager.sendRoomStatusMsgToJms(rc, true);

							if ((channel.getPushRstu() == null ? false : channel.getPushRstu())) {
								// 推房态
								RsvtypeChannel rcSwitch = rc.clone();
								rcSwitch.setForceSend(false);
								rcSwitch.setRateCode(null);
								roomJmsManager.sendRoomStatusMsgToJms(rcSwitch, true);
							}
							if ((channel.getPushRtav() == null ? false : channel.getPushRtav())) {
								// 推送开关
								RsvtypeChannel rcSwitch = rc.clone();
								rcSwitch.setForceSend(true);
								rcSwitch.setRateCode(null);
								roomJmsManager.sendRoomStatusMsgToJms(rcSwitch, true);
							}
							sendAvaiMap.put(roomAvaiKey, null);
						}
					}

					String ratekey = roomAvaiKey + ratePlanCode;
					if (sendMap.containsKey(ratekey)) {
						continue;
					}

					// 从rateDetail中推送房价
					PriceCalc pc = new PriceCalc();
					pc.setHotelId(h.getHotelId());
					pc.setChannelId(cgvo.getChannelId());
					pc.setRoomTypeCode(cgvo.getRoomTypeCode());
					StringBuffer roomTypeIds = new StringBuffer();
					if (CommonUtil.isNotEmpty(cgvo.getRoomTypeId())) {
						roomTypeIds.append(" and roomTypeId = '" + cgvo.getRoomTypeId() + "'");
					}
					pc.setRoomTypeIdsql(roomTypeIds.toString());
					pc.setRatePlanCode(cgvo.getRatePlanCode());
					pc.setStartDate(DateUtil.convertDateToString(pushDate));
					pc.setEndDate(DateUtil.convertDateToString(pushDate));
					List<PriceCalc> pcList = priceCalcDao.getPriceFromRateDetail(pc);
					if (CommonUtil.isEmpty(pcList)) {
						continue;
					}
					List<com.ccm.api.model.ratePlan.RateAmount> rateAmountList = new ArrayList<com.ccm.api.model.ratePlan.RateAmount>();
					for (PriceCalc pCalc : pcList) {
						com.ccm.api.model.ratePlan.RateAmount ra = new com.ccm.api.model.ratePlan.RateAmount();
						ra.setBaseAmount(pCalc.getAmount());
						ra.setNumberOfUnits(pCalc.getNumberOfUnits());
						rateAmountList.add(ra);
					}
					RoomMsg rm = new RoomMsg();
					rm.setAdsType(AdsMessage.ADSTYPE_RATE);
					rm.setChainCode(chainCode);
					rm.setChannelCode(channelCode);
					rm.setHotelCode(hotelCode);
					rm.setRoomTypeCode(pcList.get(0).getRoomTypeCode());
					rm.setRateCode(pcList.get(0).getRatePlanCode());
					rm.setStartDate(DateUtil.convertDateToString(pushDate));
					rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
					rm.setCreatedTime(new Date());
					rm.setRateAmountList(rateAmountList);
					rm.setCurrencyCode(h.getCurrencyCode());
					rmList.add(rm);

					sendMap.put(ratekey, null);
				}
				// 设置AmountAfterTax值
				rateAmountManager.setAfterAmountWithTaxForRoomMsg(rmList);
				roomMsgDaoMongo.batchSave(rmList);

				// 推送block
				rsvtypeChannelManager.handleOTABlocks(null, "NEW", finalChannelRsvchanBlockWithRatesList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendMail(pushDate, pushDate, CommonUtil.getExceptionMsg(e, "ccm"));
		}
		this.sendMail(startDate, endDate, "sendByDate finished");
	}

	public void sendMail(Date startDate, Date endDate, String content) {
		EmailVO emailVO = new EmailVO();
		String to = EmailProperties.getExceptionMonitorTo();
		emailVO.setToArray(to.split(","));
		// emailVO.setTo("davin.deng@shijinet.com.cn");
		emailVO.setContent(content);
		emailVO.setSubject("pushManage " + DateUtil.convertDateToString(startDate) + DateUtil.convertDateToString(endDate));
		emailManager.sendExceptionText(emailVO);
	}

	private void manualSendAllMsgs(Date startDate, Date endDate, String channelCode, String hotelCode, String roomTypeCode, String ratePlanCode) throws Exception {
		// 获取渠道列表

		log.info("manualSend start...");

		List<Hotel> hotelList = hotelDao.getHotelByHotelCode(hotelCode);
		Hotel hotel = CommonUtil.isNotEmpty(hotelList) ? hotelList.get(0) : new Hotel();
		Chain chain = chainDao.getChainById(hotel.getChainId());
		if (chain == null) {
			log.warn("chain is null");
			return;
		}

		String chainCode = chain.getChainCode();
		Channel channel = channelDao.getChannelByChannelCode(channelCode);
		Date maxPushDate = null;
		List<RsvchanBlock> finalChannelRsvchanBlockWithRatesList = new ArrayList<RsvchanBlock>();
		List<RoomMsg> rateMsgList = new ArrayList<RoomMsg>();
		if (channel == null) {
			return;
		}
		// 获取渠道列表
		if (CommonUtil.isEmpty(channel.getMaxPushDay())) {
			return;
		}
		Integer maxPushDay = channel.getMaxPushDay();
		if (maxPushDay != null && maxPushDay > 0) {
			maxPushDate = DateUtil.addDays(DateUtil.currentDate(), maxPushDay);
		} else {
			maxPushDate = DateUtil.addDays(DateUtil.currentDate(), 89);
		}
		if (CommonUtil.isEmpty(channel.getPushAddress()) || CommonUtil.isEmpty(channelCode)) {
			return;
		}
		List<Date> dayList = DateUtil.getDayList(startDate, endDate);
		for (Date pushDate : dayList) {
			Set<String> sendAvaiSet = new HashSet<String>();
			Set<String> sendRateSet = new HashSet<String>();
			// push block list for channel
			ChannelGoodsVO channelGoodsVO = new ChannelGoodsVO();
			channelGoodsVO.setChannelId(channel.getChannelId());
			channelGoodsVO.setChannelCode(channelCode);
			channelGoodsVO.setHotelId(hotel.getHotelId());
			if (CommonUtil.isNotEmpty(ratePlanCode)) {
				channelGoodsVO.setRatePlanCode(ratePlanCode);
			}
			if (CommonUtil.isNotEmpty(roomTypeCode)) {
				channelGoodsVO.setRoomTypeCode(roomTypeCode);
			}
			channelGoodsVO.setExpireDate(pushDate);
			channelGoodsVO.setStatus(ChannelGoodsStatus.publish);// 查询已发布的关系
			channelGoodsVO.setCreatedTime(new Date());
			channelGoodsVO.setIsBind(true);
			List<ChannelGoodsVO> cgvoList = channelgoodsDao.getChannelGoodsVORoomTypeByChannelGoods(channelGoodsVO);//
			if (CommonUtil.isEmpty(cgvoList)) {
				return;
			}

			for (ChannelGoodsVO cgvo : cgvoList) {
				if (cgvo == null) {
					log.warn(channelGoodsVO + " cgvo is null");
					continue;
				}
				Hotel h = hotelDao.getHotel(cgvo.getHotelId());
				if (h == null) {
					log.warn(channelGoodsVO + " h is null");
					continue;
				}

				String enabledRoomTypeCode = cgvo.getRoomTypeCode();
				String enabledRatePlanCode = cgvo.getRatePlanCode();

				// block
				String pushDateStr = DateUtil.convertDateToString(pushDate);
				List<RsvchanBlock> rsvchanBlockList = rsvchanBlockDao.getHandSendRsvchanBlock(hotelCode, channelCode, pushDateStr, enabledRoomTypeCode);
				if (CommonUtil.isEmpty(rsvchanBlockList) == false) {
					for (RsvchanBlock rsvchanBlock : rsvchanBlockList) {
						if (rsvchanBlock.getRatePlanCodes() != null) {
							if (rsvchanBlock.getRatePlanCodes().contains(enabledRatePlanCode))
								finalChannelRsvchanBlockWithRatesList.add(rsvchanBlock);
						}
					}
				}

				String roomAvaiKey = channelCode + chainCode + hotelCode + enabledRoomTypeCode;

				if (!sendAvaiSet.contains(roomAvaiKey)) {
					// 推送房量/开关
					RsvtypeChannel rt = new RsvtypeChannel();
					rt.setChannelId(cgvo.getChannelId());
					rt.setHotelCode(hotelCode);
					rt.setType(enabledRoomTypeCode);
					rt.setDate(pushDate);
					rt.setHotelid(h.getHotelId());
					rt.setIsDailyDay(true);
					List<RsvtypeChannel> rcList = rsvtypeChannelManager.getRsvtypeChannelAvailable(enabledRoomTypeCode, hotelCode, rt.getDate(), DateUtil.addDays(rt.getDate(), 1), channelCode);
					if (CommonUtil.isEmpty(rcList)) {
						rcList = new ArrayList<RsvtypeChannel>();
						rcList.add(rt);
					}
					for (RsvtypeChannel rc : rcList) {
						int sendAvai = 0;
						// 获取该天该ob量
						rc.setChannelCode(channelCode);
						int obAvailable = rsvtypeChannelManager.getObAvailable(rc);
						int totalObSold = CommonUtil.getInt(rc.getTotalOBSold());
						if (CommonUtil.getInt(rc.getAvailable()) >= 0) {
							if (rc.getFreeSell() == null || rc.getFreeSell()) {
								int roomTypePhysicalRoom = 0;
								if (null == rc.getAvailable()) {
									roomTypePhysicalRoom = roomTypeManager.getRoomTypePhysicalRoom(rc.getType(), rc.getHotelCode());
								}
								String key = hotelCode + "|" + enabledRoomTypeCode + "|" + DateUtil.getMonthOfYear(pushDate);
								Map<String, Long> redisRoomQtyMap = roomQtyDao.readLongValueFromMap(key);
								Long redisSoldQty = new Long(0);
								if (redisRoomQtyMap != null) {
									if (redisRoomQtyMap.get(DateUtil.getDate(rc.getDate())) != null) {
										redisSoldQty = redisRoomQtyMap.get(DateUtil.getDate(rc.getDate()));
									}
								}
								sendAvai = roomTypePhysicalRoom + CommonUtil.getInt(rc.getAvailable()) + (obAvailable - totalObSold) - redisSoldQty.intValue();
							} else {
								sendAvai = obAvailable - totalObSold;
							}
						}
						rc.setSendAvailable(sendAvai);
						rc.setChannelCode(channelCode);
						rc.setChannel(channelCode);
						rc.setInvSnapInvoke(true);
						rc.setIsDailyDay(true);
						log.info("pushManage.dailySend->" + AdsMessage.ADSTYPE_AVAILUPDATE);
						// 推送房量
						if ((channel.getPushRavl() == null ? false : channel.getPushRavl()))
							roomJmsManager.sendRoomStatusMsgToJms(rc, true);

						if ((channel.getPushRstu() == null ? false : channel.getPushRstu())) {
							// 推房态
							RsvtypeChannel rcSwitch = rc.clone();
							rcSwitch.setForceSend(false);
							rcSwitch.setRateCode(null);
							roomJmsManager.sendRoomStatusMsgToJms(rcSwitch, true);
						}
						if ((channel.getPushRtav() == null ? false : channel.getPushRtav())) {
							// 推送开关
							RsvtypeChannel rcSwitch = rc.clone();
							rcSwitch.setForceSend(true);
							rcSwitch.setRateCode(channelGoodsVO.getRatePlanCode());
							roomJmsManager.sendRoomStatusMsgToJms(rcSwitch, true);
						}
						sendAvaiSet.add(roomAvaiKey);
					}
				}
				
					String ratekey = roomAvaiKey + enabledRatePlanCode;
					if (sendRateSet.contains(ratekey)) {
						continue;
					}

					// 从rateDetail中推送房价
					PriceCalc pc = new PriceCalc();
					pc.setHotelId(hotel.getHotelId());
					pc.setChannelId(cgvo.getChannelId());
					pc.setRoomTypeCode(cgvo.getRoomTypeCode());
					StringBuffer roomTypeIds = new StringBuffer();
					if (CommonUtil.isNotEmpty(cgvo.getRoomTypeId())) {
						roomTypeIds.append(" and roomTypeId = '" + cgvo.getRoomTypeId() + "'");
					}
					pc.setRoomTypeIdsql(roomTypeIds.toString());
					pc.setRatePlanCode(cgvo.getRatePlanCode());
					pc.setStartDate(DateUtil.convertDateToString(pushDate));
					pc.setEndDate(DateUtil.convertDateToString(pushDate));
					List<PriceCalc> pcList = priceCalcDao.getPriceFromRateDetail(pc);
					if (CommonUtil.isEmpty(pcList)) {
						continue;
					}
					List<com.ccm.api.model.ratePlan.RateAmount> rateAmountList = new ArrayList<com.ccm.api.model.ratePlan.RateAmount>();
					for (PriceCalc pCalc : pcList) {
						com.ccm.api.model.ratePlan.RateAmount ra = new com.ccm.api.model.ratePlan.RateAmount();
						ra.setBaseAmount(pCalc.getAmount());
						ra.setNumberOfUnits(pCalc.getNumberOfUnits());
						rateAmountList.add(ra);
					}
					RoomMsg rm = new RoomMsg();
					rm.setAdsType(AdsMessage.ADSTYPE_RATE);
					rm.setChainCode(chainCode);
					rm.setChannelCode(channelCode);
					rm.setHotelCode(hotelCode);
					rm.setRoomTypeCode(pcList.get(0).getRoomTypeCode());
					rm.setRateCode(pcList.get(0).getRatePlanCode());
					rm.setStartDate(DateUtil.convertDateToString(pushDate));
					rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
					rm.setCreatedTime(new Date());
					rm.setRateAmountList(rateAmountList);
					rm.setCurrencyCode(hotel.getCurrencyCode());
					rateMsgList.add(rm);
					sendRateSet.add(ratekey);
				
			}
		}

		// 推送block
		rsvtypeChannelManager.handleOTABlocks(null, "NEW", finalChannelRsvchanBlockWithRatesList);
		// 设置AmountAfterTax值
		rateAmountManager.setAfterAmountWithTaxForRoomMsg(rateMsgList);
		roomMsgDaoMongo.batchSave(rateMsgList);
	}

	@Override
	public void dailySend() {
		log.info("dailySend start...");
		Date pushDate = null;
		// 获取渠道列表
		try {
			List<Channel> channelList = channelDao.getAllDistinct();
			HashMap<String, Object> sendMap = new HashMap<String, Object>();
			HashMap<String, Object> sendAvaiMap = new HashMap<String, Object>();
			for (Channel channel : channelList) {
				// push block list for channel
				List<RsvchanBlock> finalChannelRsvchanBlockWithRatesList = new ArrayList<RsvchanBlock>();
				String channelCode = channel.getChannelCode();
				if (CommonUtil.isEmpty(channel.getMaxPushDay())) {
					continue;
				}
				if (CommonUtil.isEmpty(channel.getPushAddress()) || CommonUtil.isEmpty(channelCode)) {
					continue;
				}
				List<RoomMsg> rmList = new ArrayList<RoomMsg>();
				ChannelGoodsVO channelGoodsVO = new ChannelGoodsVO();
				channelGoodsVO.setChannelId(channel.getChannelId());
				pushDate = DateUtil.addDays(DateUtil.currentDate(), channel.getMaxPushDay() + 1);
				channelGoodsVO.setExpireDate(pushDate);
				channelGoodsVO.setStatus(ChannelGoodsStatus.publish);// 查询已发布的关系
				channelGoodsVO.setCreatedTime(new Date());
				channelGoodsVO.setIsBind(true);
				List<ChannelGoodsVO> cgvoList = channelgoodsDao.getChannelGoodsVORoomTypeByChannelGoods(channelGoodsVO);//
				if (CommonUtil.isEmpty(cgvoList)) {
					continue;
				}

				for (ChannelGoodsVO cgvo : cgvoList) {
					if (cgvo == null) {
						log.warn(channelGoodsVO + " cgvo is null");
						continue;
					}
					Hotel h = hotelDao.getHotel(cgvo.getHotelId());
					if (h == null) {
						log.warn(channelGoodsVO + " h is null");
						continue;
					}
					Chain chain = chainDao.getChainById(h.getChainId());
					if (chain == null) {
						log.warn(channelGoodsVO + "chain is null");
						continue;
					}

					String chainCode = chain.getChainCode();
					String hotelCode = h.getHotelCode();
					String roomTypeCode = cgvo.getRoomTypeCode();
					String ratePlanCode = cgvo.getRatePlanCode();

					// block
					String pushDateStr = DateUtil.convertDateToString(pushDate);
					List<RsvchanBlock> rsvchanBlockList = rsvchanBlockDao.getHandSendRsvchanBlock(hotelCode, channelCode, pushDateStr, roomTypeCode);
					if (CommonUtil.isEmpty(rsvchanBlockList) == false) {
						for (RsvchanBlock rsvchanBlock : rsvchanBlockList) {
							if (rsvchanBlock.getRatePlanCodes() != null) {
								if (rsvchanBlock.getRatePlanCodes().contains(ratePlanCode))
									finalChannelRsvchanBlockWithRatesList.add(rsvchanBlock);
							}
						}
					}

					// 查询价格detail
					PriceCalc priceCalc = new PriceCalc();
					priceCalc.setHotelId(h.getHotelId());
					priceCalc.setStartDate(DateUtil.convertDateToString(pushDate));
					priceCalc.setEndDate(DateUtil.convertDateToString(pushDate));

					List<String> rtCodeList = new ArrayList<String>();
					rtCodeList.add(roomTypeCode);
					priceCalc.setRoomTypeCodeList(rtCodeList);

					List<String> rateCodeList = new ArrayList<String>();
					rateCodeList.add(ratePlanCode);
					priceCalc.setRatePlanCodeList(rateCodeList);

					String roomAvaiKey = channelCode + chainCode + hotelCode + roomTypeCode;

					if (!sendAvaiMap.containsKey(roomAvaiKey)) {
						// 推送房量/开关
						RsvtypeChannel rt = new RsvtypeChannel();
						rt.setChannelId(cgvo.getChannelId());
						rt.setHotelCode(hotelCode);
						rt.setType(roomTypeCode);
						rt.setDate(pushDate);
						rt.setHotelid(h.getHotelId());
						rt.setIsDailyDay(true);
						List<RsvtypeChannel> rcList = rsvtypeChannelManager.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, rt.getDate(), DateUtil.addDays(rt.getDate(), 1), channelCode);
						if (CommonUtil.isEmpty(rcList)) {
							rcList = new ArrayList<RsvtypeChannel>();
							rcList.add(rt);
						}
						for (RsvtypeChannel rc : rcList) {
							int sendAvai = 0;
							// 获取该天该ob量
							rc.setChannelCode(channelCode);
							int obAvailable = rsvtypeChannelManager.getObAvailable(rc);
							int totalObSold = CommonUtil.getInt(rc.getTotalOBSold());
							if (CommonUtil.getInt(rc.getAvailable()) >= 0) {
								if (rc.getFreeSell() == null || rc.getFreeSell()) {
									int roomTypePhysicalRoom = 0;
									if (null == rc.getAvailable()) {
										roomTypePhysicalRoom = roomTypeManager.getRoomTypePhysicalRoom(rc.getType(), rc.getHotelCode());
									}
									String key = hotelCode + "|" + roomTypeCode + "|" + DateUtil.getMonthOfYear(pushDate);
									Map<String, Long> redisRoomQtyMap = roomQtyDao.readLongValueFromMap(key);
									Long redisSoldQty = new Long(0);
									if (redisRoomQtyMap != null) {
										if (redisRoomQtyMap.get(DateUtil.getDate(rc.getDate())) != null) {
											redisSoldQty = redisRoomQtyMap.get(DateUtil.getDate(rc.getDate()));
										}
									}
									sendAvai = roomTypePhysicalRoom + CommonUtil.getInt(rc.getAvailable()) + (obAvailable - totalObSold) - redisSoldQty.intValue();
								} else {
									sendAvai = obAvailable - totalObSold;
								}
							}
							rc.setSendAvailable(sendAvai);
							rc.setChannelCode(channelCode);
							rc.setChannel(channelCode);
							rc.setInvSnapInvoke(true);
							rc.setIsDailyDay(true);
							log.info("pushManage.dailySend->" + AdsMessage.ADSTYPE_AVAILUPDATE);
							// 推送房量
							if ((channel.getPushRavl() == null ? false : channel.getPushRavl()))
								roomJmsManager.sendRoomStatusMsgToJms(rc, true);

							if ((channel.getPushRstu() == null ? false : channel.getPushRstu())) {
								// 推房态
								RsvtypeChannel rcSwitch = rc.clone();
								rcSwitch.setForceSend(false);
								rcSwitch.setRateCode(null);
								roomJmsManager.sendRoomStatusMsgToJms(rcSwitch, true);
							}
							if ((channel.getPushRtav() == null ? false : channel.getPushRtav())) {
								// 推送开关
								RsvtypeChannel rcSwitch = rc.clone();
								rcSwitch.setForceSend(true);
								rcSwitch.setRateCode(null);
								roomJmsManager.sendRoomStatusMsgToJms(rcSwitch, true);
							}
							sendAvaiMap.put(roomAvaiKey, null);
						}
					}

					String ratekey = roomAvaiKey + ratePlanCode;
					if (sendMap.containsKey(ratekey)) {
						continue;
					}
					// 推送开关
					/*
					 * List<RsvtypeChannel> rcList =
					 * rsvtypeChannelManager.getRsvtypeChannelAvailable
					 * (roomTypeCode, hotelCode,pushDate,
					 * DateUtil.addDays(pushDate,1), channelCode); ProductionVO
					 * product = new ProductionVO();
					 * product.setChainCode(chainCode);
					 * product.setHotelCode(hotelCode);
					 * product.setChannelCode(channelCode);
					 * product.setRatePlanCode(ratePlanCode);
					 * product.setRoomTypeCode(roomTypeCode);
					 * product.setStartDate(pushDate);
					 * product.setEndDate(pushDate); List<RestrictionCalc>
					 * restrictionCalcList =
					 * restrictionCalcDao.searchRestrictionCalcOnOffForPush
					 * (product); for (RestrictionCalc restrictionCalc :
					 * restrictionCalcList) {
					 * if(CommonUtil.isNotEmpty(roomRateDateSet) &&
					 * roomRateDateSet
					 * .contains(roomTypeCode+ratePlanCode+DateUtil
					 * .convertDateToString(pushDate))){ RoomMsg rm = new
					 * RoomMsg();
					 * rm.setOnOff(restrictionCalc.getOnOff().intValue()==1);
					 * if(CommonUtil.isNotEmpty(rcList)){
					 * if((rcList.get(0).getFreeSell()!=null)){
					 * if(rcList.get(0).getFreeSell().booleanValue()==false)
					 * rm.setOnOff(false); } } String
					 * switchKey="RtavSwitch-"+channelCode
					 * +"-"+hotelCode+"-"+roomTypeCode
					 * +"-"+ratePlanCode+"-"+DateUtil
					 * .convertDateToString(pushDate); Boolean
					 * rateSwitch=redisDao
					 * .readObject(switchKey)==null?null:(Boolean)
					 * redisDao.readObject(switchKey);
					 * if(rateSwitch==null||!(rateSwitch
					 * .equals(rm.getOnOff()))){ // no data or the different
					 * data in the redis needs to push
					 * rm.setChannelCode(product.getChannelCode());
					 * rm.setChainCode(product.getChainCode());
					 * rm.setHotelCode(product.getHotelCode());
					 * rm.setRoomTypeCode(product.getRoomTypeCode());
					 * rm.setRateCode(product.getRatePlanCode());
					 * rm.setStartDate
					 * (DateUtil.convertDateToString(restrictionCalc
					 * .getDate()));
					 * rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
					 * rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
					 * rm.setCreatedTime(new Date()); rmList.add(rm); } } }
					 */
					// 从rateDetail中推送房价
					PriceCalc pc = new PriceCalc();
					pc.setHotelId(h.getHotelId());
					pc.setChannelId(cgvo.getChannelId());
					pc.setRoomTypeCode(cgvo.getRoomTypeCode());
					StringBuffer roomTypeIds = new StringBuffer();
					if (CommonUtil.isNotEmpty(cgvo.getRoomTypeId())) {
						roomTypeIds.append(" and roomTypeId = '" + cgvo.getRoomTypeId() + "'");
					}
					pc.setRoomTypeIdsql(roomTypeIds.toString());
					pc.setRatePlanCode(cgvo.getRatePlanCode());
					pc.setStartDate(DateUtil.convertDateToString(pushDate));
					pc.setEndDate(DateUtil.convertDateToString(pushDate));
					List<PriceCalc> pcList = priceCalcDao.getPriceFromRateDetail(pc);
					if (CommonUtil.isEmpty(pcList)) {
						continue;
					}
					List<com.ccm.api.model.ratePlan.RateAmount> rateAmountList = new ArrayList<com.ccm.api.model.ratePlan.RateAmount>();
					for (PriceCalc pCalc : pcList) {
						com.ccm.api.model.ratePlan.RateAmount ra = new com.ccm.api.model.ratePlan.RateAmount();
						ra.setBaseAmount(pCalc.getAmount());
						ra.setNumberOfUnits(pCalc.getNumberOfUnits());
						rateAmountList.add(ra);
					}
					RoomMsg rm = new RoomMsg();
					rm.setAdsType(AdsMessage.ADSTYPE_RATE);
					rm.setChainCode(chainCode);
					rm.setChannelCode(channelCode);
					rm.setHotelCode(hotelCode);
					rm.setRoomTypeCode(pcList.get(0).getRoomTypeCode());
					rm.setRateCode(pcList.get(0).getRatePlanCode());
					rm.setStartDate(DateUtil.convertDateToString(pushDate));
					rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
					rm.setCreatedTime(new Date());
					rm.setRateAmountList(rateAmountList);
					rm.setCurrencyCode(h.getCurrencyCode());
					rmList.add(rm);

					// pc.setChannelCode(channelCode);
					// pc.setChainCode(chainCode);
					// pc.setHotelCode(hotelCode);
					// pc.setRatePlanCode(pcList.get(0).getRatePlanCode());
					// pc.setRoomTypeCode(pcList.get(0).getRoomTypeCode());
					// pc.setRateAmountList(rateAmountList);
					// pc.setDate(DateUtil.convertDateToString(pushDate));
					// roomJmsManager.sendRoomRateUpdateMsg(pc);
					sendMap.put(ratekey, null);
				}
				// 设置AmountAfterTax值
				rateAmountManager.setAfterAmountWithTaxForRoomMsg(rmList);
				roomMsgDaoMongo.batchSave(rmList);

				// 推送block
				rsvtypeChannelManager.handleOTABlocks(null, "NEW", finalChannelRsvchanBlockWithRatesList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendMail(pushDate, pushDate, "n+1 " + CommonUtil.getExceptionMsg(e, "ccm"));
		}
		System.out.println("pushDate:" + pushDate + " dateTime:" + new Date());
	}

	@Override
	public boolean sendByChannelGoods(AdsGoods adsGoods){
		// 获取渠道列表
		
		log.info("sendByChannelGoods start...");
		String adsPushKey="ADSGOODS-"+adsGoods.getChannelCode()+"-"+adsGoods.getHotelCode()+"-"+adsGoods.getRatePlanCode()+"-"+adsGoods.getRoomTypeCode();
		if(adsGoods.getForcePush()==false){
			Boolean hasAdsPush = redisDao.readObject(adsPushKey) == null ? false : (Boolean) redisDao.readObject(adsPushKey);
			if(hasAdsPush)
				return true;
		}
		Date startDate=DateUtil.currentDate();
		try{
		// 获取渠道列表
			Channel channel	= channelDao.getChannelByChannelCode(adsGoods.getChannelCode());
				// push block list for channel
				String channelCode = channel.getChannelCode();
				if (CommonUtil.isEmpty(channel.getMaxPushDay())){
					return false;
				}
				if (CommonUtil.isEmpty(channel.getPushAddress()) || CommonUtil.isEmpty(channelCode)) {
					return false;
				}
				Date overDate=DateUtil.addDays(startDate,channel.getMaxPushDay());
				List<Date> dayList = DateUtil.getDayList(startDate, overDate);
				Hotel hotel=hotelDao.getHotel(adsGoods.getHotelId());
				List<RoomMsg> rmList = new ArrayList<RoomMsg>();
				List<RsvchanBlock> finalChannelRsvchanBlockWithRatesList = new ArrayList<RsvchanBlock>();
					for (Date pushDate : dayList) {
					Set<String> sendAvaiSet = new HashSet<String>();
					Set<String> sendRateSet = new HashSet<String>();
					ChannelGoodsVO channelGoodsVO = new ChannelGoodsVO();
					channelGoodsVO.setChannelId(channel.getChannelId());
					channelGoodsVO.setExpireDate(pushDate);
					channelGoodsVO.setStatus(ChannelGoodsStatus.publish);// 查询已发布的关系
					channelGoodsVO.setIsBind(true);
					channelGoodsVO.setCreatedTime(new Date());
					channelGoodsVO.setHotelId(adsGoods.getHotelId());
					channelGoodsVO.setRatePlanCode(adsGoods.getRatePlanCode());
					channelGoodsVO.setRoomTypeCode(adsGoods.getRoomTypeCode());
					List<ChannelGoodsVO> cgvoList = channelgoodsDao.getChannelGoodsVORoomTypeByChannelGoods(channelGoodsVO);//
					if (CommonUtil.isEmpty(cgvoList)) {
						continue;
					}
	
					for (ChannelGoodsVO cgvo : cgvoList) {
						if (cgvo == null) {
							log.warn(channelGoodsVO + " cgvo is null");
							continue;
						}
						
						Chain chain = chainDao.getChainById(hotel.getChainId());
						if (chain == null) {
							log.warn(channelGoodsVO + "chain is null");
							continue;
						}
	
						String chainCode = chain.getChainCode();
						String hotelCode = hotel.getHotelCode();
						String roomTypeCode = cgvo.getRoomTypeCode();
						String ratePlanCode = cgvo.getRatePlanCode();
						
						// block
						String pushDateStr = DateUtil.convertDateToString(pushDate);
						List<RsvchanBlock> rsvchanBlockList = rsvchanBlockDao.getHandSendRsvchanBlock(hotelCode, channelCode, pushDateStr, roomTypeCode);
						if (CommonUtil.isEmpty(rsvchanBlockList) == false) {
							for (RsvchanBlock rsvchanBlock : rsvchanBlockList) {
								if (rsvchanBlock.getRatePlanCodes() != null) {
									if (rsvchanBlock.getRatePlanCodes().contains(ratePlanCode))
										finalChannelRsvchanBlockWithRatesList.add(rsvchanBlock);
								}
							}
						}
						
						String roomAvaiKey = channelCode + chainCode + hotelCode + roomTypeCode;
						
						if (!sendAvaiSet.contains(roomAvaiKey)) {
							// 推送房量/开关
							RsvtypeChannel rt = new RsvtypeChannel();
							rt.setChannelId(cgvo.getChannelId());
							rt.setHotelCode(hotelCode);
							rt.setType(roomTypeCode);
							rt.setDate(pushDate);
							rt.setHotelid(hotel.getHotelId());
							rt.setIsDailyDay(true);
							List<RsvtypeChannel> rcList = rsvtypeChannelManager.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, rt.getDate(), DateUtil.addDays(rt.getDate(), 1), channelCode);
							if (CommonUtil.isEmpty(rcList)) {
								rcList = new ArrayList<RsvtypeChannel>();
								rcList.add(rt);
							}
							for (RsvtypeChannel rc : rcList) {
								int sendAvai = 0;
								// 获取该天该ob量
								rc.setChannelCode(channelCode);
								int obAvailable = rsvtypeChannelManager.getObAvailable(rc);
								int totalObSold = CommonUtil.getInt(rc.getTotalOBSold());
								if (CommonUtil.getInt(rc.getAvailable()) >= 0) {
									if (rc.getFreeSell() == null || rc.getFreeSell()) {
										int roomTypePhysicalRoom = 0;
										if (null == rc.getAvailable()) {
											roomTypePhysicalRoom = roomTypeManager.getRoomTypePhysicalRoom(rc.getType(), rc.getHotelCode());
										}
										String key = hotelCode + "|" + roomTypeCode + "|" + DateUtil.getMonthOfYear(pushDate);
										Map<String, Long> redisRoomQtyMap = roomQtyDao.readLongValueFromMap(key);
										Long redisSoldQty = new Long(0);
										if (redisRoomQtyMap != null) {
											if (redisRoomQtyMap.get(DateUtil.getDate(rc.getDate())) != null) {
												redisSoldQty = redisRoomQtyMap.get(DateUtil.getDate(rc.getDate()));
											}
										}
										sendAvai = roomTypePhysicalRoom + CommonUtil.getInt(rc.getAvailable()) + (obAvailable - totalObSold) - redisSoldQty.intValue();
									} else {
										sendAvai = obAvailable - totalObSold;
									}
								}
								rc.setSendAvailable(sendAvai);
								rc.setChannelCode(channelCode);
								rc.setChannel(channelCode);
								rc.setInvSnapInvoke(true);
								rc.setIsDailyDay(true);
								//推送房量
								if((channel.getPushRavl()==null?false:channel.getPushRavl()))
									roomJmsManager.sendRoomStatusMsgToJms(rc, true);
	
								if((channel.getPushRstu()==null?false:channel.getPushRstu())){
									//推房态
									RsvtypeChannel rcSwitch =rc.clone();
									rcSwitch.setForceSend(false);
									rcSwitch.setRateCode(null);
									roomJmsManager.sendRoomStatusMsgToJms(rcSwitch, true);
								}
								if((channel.getPushRtav()==null?false:channel.getPushRtav())){
									// 推送开关
									RsvtypeChannel rcSwitch = rc.clone();
									rcSwitch.setForceSend(true);
									rcSwitch.setRateCode(ratePlanCode);
									roomJmsManager.sendRoomStatusMsgToJms(rcSwitch, true);
								}
								sendAvaiSet.add(roomAvaiKey);
							}
						}
							
						String ratekey = roomAvaiKey + ratePlanCode;
						if (sendRateSet.contains(ratekey)) {
							continue;
						}
	
						// 从rateDetail中推送房价
						PriceCalc pc = new PriceCalc();
						pc.setHotelId(hotel.getHotelId());
						pc.setChannelId(cgvo.getChannelId());
						pc.setRoomTypeCode(cgvo.getRoomTypeCode());
						StringBuffer roomTypeIds = new StringBuffer();
						if (CommonUtil.isNotEmpty(cgvo.getRoomTypeId())) {
							roomTypeIds.append(" and roomTypeId = '" + cgvo.getRoomTypeId() + "'");
						}
						pc.setRoomTypeIdsql(roomTypeIds.toString());
						pc.setRatePlanCode(cgvo.getRatePlanCode());
						pc.setStartDate(DateUtil.convertDateToString(pushDate));
						pc.setEndDate(DateUtil.convertDateToString(pushDate));
						List<PriceCalc> pcList = priceCalcDao.getPriceFromRateDetail(pc);
						if (CommonUtil.isEmpty(pcList)) {
							continue;
						}
						List<com.ccm.api.model.ratePlan.RateAmount> rateAmountList = new ArrayList<com.ccm.api.model.ratePlan.RateAmount>();
						for (PriceCalc pCalc : pcList) {
							com.ccm.api.model.ratePlan.RateAmount ra = new com.ccm.api.model.ratePlan.RateAmount();
							ra.setBaseAmount(pCalc.getAmount());
							ra.setNumberOfUnits(pCalc.getNumberOfUnits());
							rateAmountList.add(ra);
						}
						RoomMsg rm = new RoomMsg();
						rm.setAdsType(AdsMessage.ADSTYPE_RATE);
						rm.setChainCode(chainCode);
						rm.setChannelCode(channelCode);
						rm.setHotelCode(hotelCode);
						rm.setRoomTypeCode(pcList.get(0).getRoomTypeCode());
						rm.setRateCode(pcList.get(0).getRatePlanCode());
						rm.setStartDate(DateUtil.convertDateToString(pushDate));
						rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
						rm.setCreatedTime(new Date());
						rm.setRateAmountList(rateAmountList);
						rm.setCurrencyCode(hotel.getCurrencyCode());
						rmList.add(rm);
						
						sendRateSet.add(ratekey);
					}
				}
				// 设置AmountAfterTax值
				rateAmountManager.setAfterAmountWithTaxForRoomMsg(rmList);
				roomMsgDaoMongo.batchSave(rmList);
				// 推送block
				rsvtypeChannelManager.handleOTABlocks(null, "NEW", finalChannelRsvchanBlockWithRatesList);
				
				redisDao.save(adsPushKey,true);
				log.info("sendByChannelGoods end...");
				}catch(Exception ex){
					redisDao.delete(adsPushKey);
					sendMail(startDate, null,CommonUtil.getExceptionMsg(ex, "ccm")+"msg="+adsGoods.toString());
					return false;
				}
		return true;
	}
	
	/**
	 * 手动推送房量房价开关
	 * 
	 * @throws Exception
	 */
	@Override
	public boolean handSend(AdsLogMessageCriteria amc, HotelVO hotelvo, String language) throws Exception {
		if (AdsMessage.ADSTYPE_HOTELPRODUCT.equals(amc.getAdsType())) {
			Channel channel = channelManager.getChannelByChannelCode(amc.getTargetGDS());
			push2ChannelStaticMsgManager.pushAllStaticMsg(hotelvo.getHotelId(), channel.getChannelId(), true, language);
			return true;
		}
		boolean res = false;
		
		String channelCode = amc.getTargetGDS();
		String chainCode = hotelvo.getChainCode();
		String hotelCode = hotelvo.getHotelCode();
		String roomTypeCode = amc.getRoomTypeCode();
		String ratePlanCode = amc.getRatePlanCode();

		Date startDate = amc.getStartDate();
		Date endDate = amc.getEndDate();
		if (AdsMessage.FIDELIO_AllMsgRQ.equals(amc.getAdsType())) {
			manualSendAllMsgs(startDate, endDate, channelCode, hotelCode, roomTypeCode, ratePlanCode);
			return true;
		}
		Channel channel = channelDao.getChannelByChannelCode(channelCode);
		if (channel == null) {
			return res;
		}
		Integer maxPushDay = channel.getMaxPushDay();
		Date maxPushDate = null;
		if (maxPushDay != null && maxPushDay > 0) {
			maxPushDate = DateUtil.addDays(DateUtil.currentDate(), maxPushDay);
		} else {
			maxPushDate = DateUtil.addDays(DateUtil.currentDate(), 89);
		}
		List<Hotel> hotelList = hotelDao.getHotelByHotelCode(hotelCode);
		Hotel hotel = CommonUtil.isNotEmpty(hotelList) ? hotelList.get(0) : new Hotel();
		if (channel.getMaxPushDay() == null) {
			log.info("最大推送日期为空");
			return res;
		}

		List<Date> dayList = DateUtil.getDayList(startDate, endDate);
		List<RoomMsg> rateMsgList = new ArrayList<RoomMsg>();
		// push block list
		List<RsvchanBlock> finalAddRsvchanBlockWithRatesList = new ArrayList<RsvchanBlock>();
		for (Date pushDate : dayList) {
			HashSet<String> sendAvaiSet = new HashSet<String>();
			HashSet<String> sendRateSet = new HashSet<String>();
			
			if (!channel.isPush(pushDate)) {
				log.info(DateUtil.convertDateToString(pushDate) + " 日期超过最大推送天数");
				continue;
			}
			ChannelGoodsVO channelGoodsVO = new ChannelGoodsVO();
			channelGoodsVO.setChannelCode(channelCode);
			channelGoodsVO.setExpireDate(pushDate);
			channelGoodsVO.setHotelId(hotel.getHotelId());
			if (CommonUtil.isNotEmpty(ratePlanCode)) {
				channelGoodsVO.setRatePlanCode(ratePlanCode);
			}
			if (CommonUtil.isNotEmpty(roomTypeCode)) {
				channelGoodsVO.setRoomTypeCode(roomTypeCode);
			}
			channelGoodsVO.setIsBind(true);
			if (AdsMessage.ADSTYPE_RATE.equals(amc.getAdsType())||AdsMessage.ADSTYPE_AVAILABILITY.equals(amc.getAdsType()) || AdsMessage.ADSTYPE_AVAILSTATUS.equals(amc.getAdsType()) || AdsMessage.ADSTYPE_AVAILUPDATE.equals(amc.getAdsType()) || AdsMessage.ADSTYPE_BLOCK.equals(amc.getAdsType())) {
				List<ChannelGoodsVO> cgvoList = channelgoodsManager.getEnabledChannelGoods(channelGoodsVO);
				if (CommonUtil.isEmpty(cgvoList)) {
					continue;
				}
				for (ChannelGoodsVO cgvo : cgvoList) {

					String enabledRoomTypeCode = cgvo.getRoomTypeCode();
					String enableRateplanCode = cgvo.getRatePlanCode();

					String roomAvaiKey = cgvo.getChannelCode() + chainCode + hotelCode + enabledRoomTypeCode;
					if (AdsMessage.ADSTYPE_AVAILABILITY.equals(amc.getAdsType()) || AdsMessage.ADSTYPE_AVAILSTATUS.equals(amc.getAdsType()) || AdsMessage.ADSTYPE_AVAILUPDATE.equals(amc.getAdsType())) { // 房量
						// 推送房量/房态/开关
						if (!sendAvaiSet.contains(roomAvaiKey)) {
							RsvtypeChannel rt = new RsvtypeChannel();
							rt.setChannelId(cgvo.getChannelId());
							rt.setChannel(cgvo.getChannelCode());
							rt.setHotelCode(hotelCode);
							rt.setType(enabledRoomTypeCode);
							rt.setDate(pushDate);
							rt.setHotelid(hotel.getHotelId());
							List<RsvtypeChannel> rcList = rsvtypeChannelManager.getRsvtypeChannelAvailable(enabledRoomTypeCode, hotelCode, rt.getDate(), DateUtil.addDays(rt.getDate(), 1), channelCode);
							if (CommonUtil.isEmpty(rcList)) {
								rcList = new ArrayList<RsvtypeChannel>();
								rcList.add(rt);
							}
							for (RsvtypeChannel rc : rcList) {
								int sendAvai = 0;
								// 获取该天该ob量
								rc.setChannelCode(channelCode);
								int obAvailable = rsvtypeChannelManager.getObAvailable(rc);
								int totalObSold = CommonUtil.getInt(rc.getTotalOBSold());
								if (CommonUtil.getInt(rc.getAvailable()) >= 0) {
									if (rc.getFreeSell() == null || rc.getFreeSell()) {
										int roomTypePhysicalRoom = 0;
										if (null == rc.getAvailable()) {
											roomTypePhysicalRoom = roomTypeManager.getRoomTypePhysicalRoom(rc.getType(), rc.getHotelCode());
										}
										String key = hotelCode + "|" + enabledRoomTypeCode + "|" + DateUtil.getMonthOfYear(pushDate);
										Map<String, Long> redisRoomQtyMap = roomQtyDao.readLongValueFromMap(key);
										Long redisSoldQty = new Long(0);
										if (redisRoomQtyMap != null) {
											if (redisRoomQtyMap.get(DateUtil.getDate(rc.getDate())) != null) {
												redisSoldQty = redisRoomQtyMap.get(DateUtil.getDate(rc.getDate()));
											}
										}
										sendAvai = roomTypePhysicalRoom + CommonUtil.getInt(rc.getAvailable()) + (obAvailable - totalObSold) - redisSoldQty.intValue();
									} else {
										sendAvai = obAvailable - totalObSold;
									}
								}
								rc.setSendAvailable(sendAvai);
								rc.setChannel(channelCode);
								rc.setIsDailyDay(true);
								if (AdsMessage.ADSTYPE_AVAILUPDATE.equals(amc.getAdsType())) {
									// 开关房
									rc.setForceSend(true);
									rc.setRateCode(channelGoodsVO.getRatePlanCode());
								}
								roomJmsManager.sendRoomStatusMsgToJms(rc, true);
							}
							sendAvaiSet.add(roomAvaiKey);
						}
					}else if(AdsMessage.ADSTYPE_RATE.equals(amc.getAdsType())){
						String ratekey = roomAvaiKey + enableRateplanCode;
						if (sendRateSet.contains(ratekey)) {
							continue;
						}
	
						// 从rateDetail中推送房价
						PriceCalc pc = new PriceCalc();
						pc.setHotelId(hotel.getHotelId());
						pc.setChannelId(cgvo.getChannelId());
						pc.setRoomTypeCode(cgvo.getRoomTypeCode());
						StringBuffer roomTypeIds = new StringBuffer();
						if (CommonUtil.isNotEmpty(cgvo.getRoomTypeId())) {
							roomTypeIds.append(" and roomTypeId = '" + cgvo.getRoomTypeId() + "'");
						}
						pc.setRoomTypeIdsql(roomTypeIds.toString());
						pc.setRatePlanCode(cgvo.getRatePlanCode());
						pc.setStartDate(DateUtil.convertDateToString(pushDate));
						pc.setEndDate(DateUtil.convertDateToString(pushDate));
						List<PriceCalc> pcList = priceCalcDao.getPriceFromRateDetail(pc);
						if (CommonUtil.isEmpty(pcList)) {
							continue;
						}
						List<com.ccm.api.model.ratePlan.RateAmount> rateAmountList = new ArrayList<com.ccm.api.model.ratePlan.RateAmount>();
						for (PriceCalc pCalc : pcList) {
							com.ccm.api.model.ratePlan.RateAmount ra = new com.ccm.api.model.ratePlan.RateAmount();
							ra.setBaseAmount(pCalc.getAmount());
							ra.setNumberOfUnits(pCalc.getNumberOfUnits());
							rateAmountList.add(ra);
						}
						RoomMsg rm = new RoomMsg();
						rm.setAdsType(AdsMessage.ADSTYPE_RATE);
						rm.setChainCode(chainCode);
						rm.setChannelCode(channelCode);
						rm.setHotelCode(hotelCode);
						rm.setRoomTypeCode(pcList.get(0).getRoomTypeCode());
						rm.setRateCode(pcList.get(0).getRatePlanCode());
						rm.setStartDate(DateUtil.convertDateToString(pushDate));
						rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
						rm.setCreatedTime(new Date());
						rm.setRateAmountList(rateAmountList);
						rm.setCurrencyCode(hotel.getCurrencyCode());
						rateMsgList.add(rm);
						sendRateSet.add(ratekey);
					} else {
						// block
						String pushDateStr = DateUtil.convertDateToString(pushDate);
						List<RsvchanBlock> rsvchanBlockList = rsvchanBlockDao.getHandSendRsvchanBlock(hotelCode, channelCode, pushDateStr, enabledRoomTypeCode);
						if (CommonUtil.isEmpty(rsvchanBlockList) == false) {
							for (RsvchanBlock rsvchanBlock : rsvchanBlockList) {
								if (rsvchanBlock.getRatePlanCodes() != null) {
									if (rsvchanBlock.getRatePlanCodes().contains(enableRateplanCode))
										finalAddRsvchanBlockWithRatesList.add(rsvchanBlock);
								}
							}
						}

					}
				}
			}
		}
		// 设置AmountAfterTax值
		rateAmountManager.setAfterAmountWithTaxForRoomMsg(rateMsgList);
		roomMsgDaoMongo.batchSave(rateMsgList);
		// 推送block
		rsvtypeChannelManager.handleOTABlocks(null, "NEW", finalAddRsvchanBlockWithRatesList);
		return true;
	}

	@Override
	public void stayHistoryNotification(Master order) {
		if (staMap == null) {
			staMap = new HashMap<String, String>();
			staMap.put(OrderStatus.CHECKIN, "CHECKEDIN");
			staMap.put(OrderStatus.CHECKOUT, "CHECKEDOUT");
			staMap.put(OrderStatus.CANCEL, "CANCELLED");
			staMap.put(OrderStatus.HARDCANCEL, "CANCELLED");
			staMap.put(OrderStatus.NOSHOW, "NOSHOW");
			if(order.getIsMC()){
				staMap.put(OrderStatus.RESERVED, "RESERVED");
			}
		}

		String sta = order.getSta();

		if (staMap.containsKey(sta)) {
			order.setSta(staMap.get(sta));
		} else {
			log.info(sta + " 该状态无需推送 " + order.getCrsno());
			return;
		}

		String status = AdsMessage.EXEC_ERROR_STATUS;
		String errMsg = "";
		String xml = "";
		try {
			Channel channel = channelDao.getChannelByChannelCode(order.getChannel());
			if (CommonUtil.isEmpty(channel.getPushStayHistoryAddress())) {
				log.warn("PushStayHistoryAddress is null");
				return;
			}

			order.setCheckInDate(DateUtil.convertDateToString(order.getArr()) + "T00:00:00");
			order.setCheckOutDate(DateUtil.convertDateToString(order.getDep()) + "T00:00:00");
			order.setCreatedDate(DateUtil.convertDateToString(order.getChanged()) + "T00:00:00");
			String tmpUrl = "pushXmlTemplate/Switch_StayHistoryNotificationRQ.xml";
			xml = PushDataUtil.getVelocityStr(tmpUrl, order);
			log.info(xml);

			String POST_URL = channel.getHttpPushStayHistoryAddress();
			log.info("stayHistoryNotification，POST_URL="+POST_URL);
			String resXml = PushDataUtil.postData(POST_URL, xml);
			log.info("stayHistoryNotification，返回的xml，resXml="+resXml);
			SAXReader saxReader = new SAXReader();
			if (CommonUtil.isNotEmpty(resXml) && resXml.contains("StayRequestResult")) {
				Document document = saxReader.read(new StringReader(resXml));
				Node node = (Node) document.selectObject("/StayRequestResult/*");
				Element resEle = (Element) node;
				String resFlag = resEle.attributeValue("resultStatusFlag");
				if ("FAIL".equalsIgnoreCase(resFlag.trim())) {
					errMsg = resEle.getTextTrim();
					log.error("request stayhistory error! " + resXml);
				} else {
					status = AdsMessage.EXEC_END_STATUS;
				}
			} else {
				errMsg = resXml;
				status = AdsMessage.EXEC_ERROR_STATUS;
				log.error("response stayhistory error! " + resXml);
			}
		} catch (Exception e1) {
			status = AdsMessage.EXEC_ERROR_STATUS;
			errMsg += CommonUtil.getExceptionMsg(e1, "ccm");
			e1.printStackTrace();
			log.error("!!!" + errMsg);
		} finally {
			saveAdsMessage(errMsg, status, CommonUtil.generateSequenceNo(), order.getChannel(), order.getChainCode(), order.getHotelCode(), order.getType(), order.getRatePlanCode(), xml, AdsMessage.STAYHISTORYNOTIFICATION, "", null);
		}
	}

	public Boolean getBoolean(Boolean bool) {
		return bool == null ? false : bool;

	}
}
