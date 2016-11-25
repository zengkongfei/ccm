/**
 * 
 */
package com.ccm.api.service.system.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.opentravel.ota._2003._05.BasicPropertyInfoType;
import org.opentravel.ota._2003._05.CustomerType;
import org.opentravel.ota._2003._05.CustomerType.Telephone;
import org.opentravel.ota._2003._05.DocumentType;
import org.opentravel.ota._2003._05.HotelResModifyRequestType;
import org.opentravel.ota._2003._05.HotelResModifyType;
import org.opentravel.ota._2003._05.HotelResModifyType.HotelResModify;
import org.opentravel.ota._2003._05.HotelReservationIDsType;
import org.opentravel.ota._2003._05.HotelReservationIDsType.HotelReservationID;
import org.opentravel.ota._2003._05.HotelReservationType.ResGlobalInfo;
import org.opentravel.ota._2003._05.ObjectFactory;
import org.opentravel.ota._2003._05.PersonNameType;
import org.opentravel.ota._2003._05.ProfileType;
import org.opentravel.ota._2003._05.ProfilesType;
import org.opentravel.ota._2003._05.ProfilesType.ProfileInfo;
import org.opentravel.ota._2003._05.ResGuestType;
import org.opentravel.ota._2003._05.ResGuestsType;
import org.opentravel.ota._2003._05.UniqueIDType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccm.api.dao.ads.AdsMessageDao;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.channel.mongodb.ChannelHotelConfigDaoMongo;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.ratePlan.RateGuaranteeRelationshipDao;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelHotelConfig;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.enume.DocumentTypeEnum;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.ratePlan.RateGuaranteeRelationship;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.system.AbstractPush2ChannelProtocolService;
import com.ccm.api.service.system.OTAHoterlProductNotifyRSManager;
import com.ccm.api.service.system.Push2ChannelManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.HttpclientUtil;
import com.ccm.api.util.PushDataUtil;
import com.ccm.api.util.XMLUtil;

/**
 * @author Jenny
 * 
 */
@Service("push2ChannelManager")
public class Push2ChannelManagerImpl implements Push2ChannelManager {

	private Log log = LogFactory.getLog(Push2ChannelManagerImpl.class);

	private Map<Boolean, Object> isOTA = new HashMap<Boolean, Object>();

	@Resource
	private RateplanDao rateplanDao;
	@Resource
	private ChannelgoodsDao channelgoodsDao;
	@Resource
	private HotelDao hotelDao;
	@Resource
	private AdsMessageDao adsMessageDao;
	@Resource
	private RateGuaranteeRelationshipDao rateGuaranteeRelationshipDao;
	@Resource
	private RoomTypeDao roomTypeDao;
	@Resource
	private ChannelManager channelManager;
	@Resource
	private AdsMessageDao adsMessageErrDao;
	@Resource
	private ChannelDao channelDao;
	@Resource
	private VelocityEngine velocityEngine;
	
	@Resource
	private ChannelHotelConfigDaoMongo channelHotelConfigDao;
	@Resource
	private OTAHoterlProductNotifyRSManager otaHoterlProductNotifyRSManager;

	public void pushRateContent(String language, List<ChannelGoodsVO> rateChannelMap) {
		Map<String,ChannelHotelConfig> channelHotelConfigMap = new HashMap<String, ChannelHotelConfig>();
		
		List<String> channelHotelRateId = new ArrayList<String>();
		for (ChannelGoodsVO cgvo : rateChannelMap) {
			String key = cgvo.getChannelId() + cgvo.getHotelId() + cgvo.getRatePlanId();
			if (channelHotelRateId.contains(key)) {
				continue;
			}
			channelHotelRateId.add(key);
			// 获取发送到tb2应用的绑定关系
			String pushUrl = getPushRateUrl(cgvo);
			if (!StringUtils.hasText(pushUrl)) {
				continue;
			}
			cgvo.setPushRateUrl(pushUrl);
			String channelId = cgvo.getChannelId();
			ChannelHotelConfig channelHotelConfig = channelHotelConfigMap.get(channelId);
			if(channelHotelConfig == null){
				channelHotelConfig = channelHotelConfigDao.getChannelHotelConfigByChannelId(channelId);
				channelHotelConfigMap.put(channelId, channelHotelConfig);
			}
			/** 未配置组合推送*/
			if(channelHotelConfig != null && channelHotelConfig.getPushMethod()==2){
				continue;
			}
			push2ChannelRate(cgvo, language);
		}
		
	}
	
	public void pushRateStaticContent(String language, List<ChannelGoodsVO> rateChannelMap) {

		List<String> channelHotelRateId = new ArrayList<String>();
		for (ChannelGoodsVO cgvo : rateChannelMap) {
			String key = cgvo.getChannelId() + cgvo.getHotelId() + cgvo.getRatePlanId();
			if (channelHotelRateId.contains(key)) {
				continue;
			}
			channelHotelRateId.add(key);
			// 获取发送到tb2应用的绑定关系
			String pushUrl = getPushRateUrl(cgvo);
			if (!StringUtils.hasText(pushUrl)) {
				continue;
			}
			cgvo.setPushRateUrl(pushUrl);
			push2ChannelRate(cgvo, language);
		}

	}

	
	public void pushRateContentOfEditRate(String hotelId, String ratePlanId, String language) {

		List<ChannelGoodsVO> cgList = channelgoodsDao.getPushChannelRelationByHotelRateId(hotelId, ratePlanId, null);
		List<String> channelHotelRateId = new ArrayList<String>();
		for (ChannelGoodsVO cgvo : cgList) {
			String key = cgvo.getChannelId() + cgvo.getHotelId() + cgvo.getRatePlanId();
			if (channelHotelRateId.contains(key)) {
				continue;
			}
			channelHotelRateId.add(key);
			String pushUrl = getPushRateUrl(cgvo);
			if (!StringUtils.hasText(pushUrl)) {
				continue;
			}
			cgvo.setPushRateUrl(pushUrl);
			push2ChannelRate(cgvo, language);
		}

	}

	@Async
	public void pushRateOnOff(String language, ChannelGoodsVO cgvo) {

		String pushUrl = getPushRateUrl(cgvo);
		if (!StringUtils.hasText(pushUrl)) {
			return;
		}
		cgvo.setPushRateUrl(pushUrl);

		AdsMessage adsMsg = new AdsMessage();
		adsMsg.setRoomTypeCode("");
		adsMsg.setMsgType(AdsMessage.MSGTYPE_PUSH);
		adsMsg.setAdsType(AdsMessage.ADSTYPE_RATECODE);
		adsMsg.setStatus(AdsMessage.EXEC_INIT_STATUS);
		adsMsg.setTargetGDS(cgvo.getChannelCode());
		String echoToken = CommonUtil.generateSequenceNo();
		adsMsg.setEchoToken(echoToken);

		try {
			RatePlanVO rpvo = rateplanDao.getRateChainHotelByRateplanIdHotelId(cgvo.getHotelId(), language, cgvo.getRatePlanId());
			if (rpvo == null) {
				log.info("rateplanISNotFound");
				return;
			}
			// 构建日志
			adsMsg.setChainCode(rpvo.getChainCode());
			String hotelCode = rpvo.getRp().getHotelCode();
			adsMsg.setHotelCode(hotelCode);
			String rateCode = rpvo.getRp().getRatePlanCode();
			adsMsg.setRatePlanCode(rateCode);

			AbstractPush2ChannelProtocolService push2channel = getPush2ChannelProtocolService(cgvo.getIsOTA());
			Object rp = push2channel.buildRateOnOff(rpvo.getChainCode(), hotelCode, rateCode, cgvo.getStatus());

			String xml = XMLUtil.JAXBParseToXml(rp);
			adsMsg.setContent(xml);
			if (cgvo.getIsChannelPushUrl()) {
				String hotelCodeE = replacePointEncodeParam(hotelCode);
				String rateCodeE = replacePointEncodeParam(rateCode);
				cgvo.setPushRateUrl(CommonUtil.getHttpReq(cgvo.getPushRateUrl()) + "/api/hotels/" + hotelCodeE + "/rateplans/" + rateCodeE);
				String response = HttpclientUtil.doPut(CommonUtil.getHttpReq(cgvo.getPushRateUrl()), xml);
				adsMsg.setActionValue(response);
			} else {
				cgvo.setPushRateUrl(cgvo.getPushRateUrl().replace("{}", "pushRatePlan"));
				String response = PushDataUtil.postDataResponse(CommonUtil.getHttpReq(cgvo.getPushRateUrl() ), xml);
				adsMsg.setActionValue(response);
			}
			adsMsg.setStatus(AdsMessage.EXEC_END_STATUS);
		} catch (Exception e) {
			adsMsg.setStatus(AdsMessage.EXEC_ERROR_STATUS);
			adsMsg.setErrMsg(CommonUtil.getExceptionMsg(e, "ccm"));
		}

		saveAdsMessage(adsMsg);
	}

	public void pushHotelRoomContent(String language, String hotelId, List<String> channelIds, List<String> roomTypeIds) {

		HotelVO hvo = hotelDao.getPushHotelInfoByHotelId(hotelId, language);
		if (hvo == null) {
			log.info("hotelISNotFound:" + hotelId);
			return;
		}

		// 用于判断重复
		List<String> channelHotelId = new ArrayList<String>();
		List<String> channelHotelRoomId = new ArrayList<String>();

		for (String channelId : channelIds) {

			Channel c = null;
			try {
				c = channelManager.get(channelId);
			} catch (Exception e) {
				log.info("ChannelISFouncException:" + channelId);
				continue;
			}
			if (c == null) {
				log.info("ChannelISNotFound:" + channelId);
				continue;
			}
			ChannelHotelConfig channelHotelConfig = channelHotelConfigDao.getChannelHotelConfigByChannelId(c.getChannelId());
			//单独推送
			if (channelHotelConfig!=null && channelHotelConfig.getPushMethod() == 2) {
				continue;
			}
			String pushHotelUrl = c.getPushRateUrl();
			String pushRoomUrl = c.getPushRateUrl();
			String keyH = channelId + hotelId;
			if (!channelHotelId.contains(keyH) && c.getPushHotel() != null && c.getPushHotel()) {
				channelHotelId.add(keyH);
				if (!c.getIsChannelPushUrl()) {
					pushHotelUrl = hvo.getHotelPushUrl();
				}
				if (StringUtils.hasText(pushHotelUrl)) {
					// 获取发送到tb2应用的酒店绑定关系
					c.setPushRateUrl(pushHotelUrl);
					pushHotelInfo(hvo, c);
				}
			}
			// 获取发送到tb2应用的房型绑定关系
			if (c.getPushRoom() != null && c.getPushRoom()) {
				if (!c.getIsChannelPushUrl()) {
					pushRoomUrl = hvo.getHotelPushUrl();
				}
				if (StringUtils.hasText(pushRoomUrl)) {
					for (String roomTypeId : roomTypeIds) {
						String key = c.getChannelId() + hvo.getHotelId() + roomTypeId;
						if (channelHotelRoomId.contains(key)) {
							continue;
						}
						channelHotelRoomId.add(key);
						c.setPushRateUrl(pushRoomUrl);
						pushRoomContent(hvo, c, roomTypeId);
					}
				}
			}
		}

	}

	public void pushReservation(Master order) {
		AdsMessage adsMsg = null;
		try {
			Channel channel = channelManager.getChannelByChannelCode(order.getChannel());
			if (channel == null || CommonUtil.isEmpty(channel.getPushRateUrl())) {
				log.warn("PushHotelReservationUrl is null");
				return;
			}
			// 构建日志
			adsMsg = new AdsMessage();
			adsMsg.setRoomTypeCode("");
			adsMsg.setRatePlanCode("");
			adsMsg.setMsgType(AdsMessage.MSGTYPE_PUSH);
			adsMsg.setAdsType(AdsMessage.ADSTYPE_OTAHOTERRESMODIFY);
			adsMsg.setStatus(AdsMessage.EXEC_INIT_STATUS);
			adsMsg.setTargetGDS(channel.getChannelCode());
			String echoToken = CommonUtil.generateSequenceNo();
			adsMsg.setEchoToken(echoToken);
			adsMsg.setChainCode(order.getChainCode());
			adsMsg.setHotelCode(order.getHotelCode());

			// UniqueID
			UniqueIDType uniqueIDType = new UniqueIDType();
			uniqueIDType.setType("40");
			uniqueIDType.setID(order.getCrsno());
			// PersonName
			PersonNameType personNameType = new PersonNameType();
			personNameType.setLanguage(order.getLang());
			personNameType.setSurname(order.getName2());
			// Telephone
			Telephone telephone = new Telephone();
			telephone.setPhoneNumber(order.getMobile());
			telephone.setPhoneTechType("5");
			// Document
			DocumentType documentType = new DocumentType();
			try {
				documentType.setDocType(DocumentTypeEnum.valueOf(order.getIdcls()).getValue());
			} catch (Exception e) {
				log.error(CommonUtil.getExceptionMsg(e, "ccm"));
			}
			documentType.setDocID(order.getIdent());
			// Customer
			CustomerType customerType = new CustomerType();
			customerType.getPersonName().add(personNameType);
			customerType.getTelephone().add(telephone);
			customerType.getDocument().add(documentType);
			// Profile
			ProfileType profileType = new ProfileType();
			profileType.setCustomer(customerType);
			// ProfileInfo
			ProfileInfo profileInfo = new ProfileInfo();
			profileInfo.setProfile(profileType);
			// Profiles
			ProfilesType profilesType = new ProfilesType();
			profilesType.getProfileInfo().add(profileInfo);
			// ResGuest
			ResGuestType resGuestType = new ResGuestType();
			resGuestType.setProfiles(profilesType);
			// ResGuests
			ResGuestsType resGuestsType = new ResGuestsType();
			resGuestsType.getResGuest().add(resGuestType);
			// HotelReservationID
			HotelReservationID hotelReservationID = new HotelReservationID();
			hotelReservationID.setResIDType("40");
			hotelReservationID.setResIDValue(order.getPmsId());
			// HotelReservationIDs
			HotelReservationIDsType hotelReservationIDsType = new HotelReservationIDsType();
			hotelReservationIDsType.getHotelReservationID().add(hotelReservationID);
			// BasicPropertyInfo
			BasicPropertyInfoType basicPropertyInfoType = new BasicPropertyInfoType();
			basicPropertyInfoType.setChainCode(order.getChainCode());
			basicPropertyInfoType.setHotelCode(order.getHotelCode());
			// ResGlobalInfo
			ResGlobalInfo resGlobalInfo = new ResGlobalInfo();
			resGlobalInfo.setHotelReservationIDs(hotelReservationIDsType);
			resGlobalInfo.setBasicPropertyInfo(basicPropertyInfoType);
			// HotelResModify
			HotelResModify hotelResModify = new HotelResModify();
			hotelResModify.getUniqueID().add(uniqueIDType);
			hotelResModify.setResGuests(resGuestsType);
			hotelResModify.setResGlobalInfo(resGlobalInfo);
			// HotelResModifies
			HotelResModifyType hotelResModifyType = new HotelResModifyType();
			hotelResModifyType.getHotelResModify().add(hotelResModify);
			// OTA_HotelResModifyNotifRQ
			HotelResModifyRequestType hotelResModifyRequestType = new HotelResModifyRequestType();
			hotelResModifyRequestType.setHotelResModifies(hotelResModifyType);
			JAXBElement<HotelResModifyRequestType> jaxb = new ObjectFactory().createOTAHotelResModifyNotifRQ(hotelResModifyRequestType);
			String xml = XMLUtil.JAXBParseToXmlNoXmlRoot(hotelResModifyRequestType, jaxb);
			adsMsg.setContent(xml);
			String response = PushDataUtil.postDataResponse( CommonUtil.getHttpReq(channel.getPushRateUrl()) + "/api/hotels/reservation", xml);
			adsMsg.setActionValue(response);
			adsMsg.setStatus(AdsMessage.EXEC_END_STATUS);
		} catch (Exception e) {
			adsMsg.setStatus(AdsMessage.EXEC_ERROR_STATUS);
			adsMsg.setErrMsg(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		saveAdsMessage(adsMsg);
	}

	@Async
	public void pushHotelInfo(HotelVO hvo, Channel c) {

		AdsMessage adsMsg = new AdsMessage();
		try {
			// 构建日志
			adsMsg.setRoomTypeCode("");
			adsMsg.setRatePlanCode("");
			adsMsg.setMsgType(AdsMessage.MSGTYPE_PUSH);
			adsMsg.setAdsType(AdsMessage.ADSTYPE_HOTELCODE);
			adsMsg.setStatus(AdsMessage.EXEC_INIT_STATUS);
			adsMsg.setTargetGDS(c.getChannelCode());
			String echoToken = CommonUtil.generateSequenceNo();
			adsMsg.setEchoToken(echoToken);
			adsMsg.setChainCode(hvo.getChainCode());
			adsMsg.setHotelCode(hvo.getHotelCode());

			AbstractPush2ChannelProtocolService push2channel = getPush2ChannelProtocolService(c.getIsOTA());
			Object hInfo = push2channel.buildHotelMsg(hvo);

			String xml = XMLUtil.JAXBParseToXml(hInfo);
			adsMsg.setContent(xml);
			if (c.getIsChannelPushUrl()) {
				String hotelCodeE = replacePointEncodeParam(hvo.getHotelCode());
				c.setPushRateUrl(c.getPushRateUrl() + "/api/hotels/" + hotelCodeE);
			} else {
				c.setPushRateUrl(c.getPushRateUrl().replace("{}", "pushHotelInfo"));
			}
			String response = PushDataUtil.postDataResponse(CommonUtil.getHttpReq(c.getPushRateUrl()), xml);
			adsMsg.setActionValue(response);
			adsMsg.setStatus(AdsMessage.EXEC_END_STATUS);
		} catch (Exception e) {
			adsMsg.setStatus(AdsMessage.EXEC_ERROR_STATUS);
			adsMsg.setErrMsg(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		saveAdsMessage(adsMsg);
	}

	@Async
	public void pushRoomContent(HotelVO hvo, Channel c, String roomTypeId) {

		// 获取房型
		RoomTypeVO rtvo = roomTypeDao.getRoomTypeById(roomTypeId, hvo.getLanguageCode());
		if (rtvo == null) {
			log.info("roomtypevoISNotFound:" + roomTypeId);
			return;
		}

		// 构建日志
		AdsMessage adsMsg = new AdsMessage();
		try {
			adsMsg.setChainCode(hvo.getChainCode());
			adsMsg.setHotelCode(hvo.getHotelCode());
			adsMsg.setMsgType(AdsMessage.MSGTYPE_PUSH);
			adsMsg.setAdsType(AdsMessage.ADSTYPE_ROOMCODE);
			adsMsg.setStatus(AdsMessage.EXEC_INIT_STATUS);
			adsMsg.setTargetGDS(c.getChannelCode());
			String echoToken = CommonUtil.generateSequenceNo();
			adsMsg.setEchoToken(echoToken);
			adsMsg.setRatePlanCode("");
			adsMsg.setRoomTypeCode(rtvo.getRoomTypeCode());

			AbstractPush2ChannelProtocolService push2channel = getPush2ChannelProtocolService(c.getIsOTA());
			rtvo.setHotelCode(hvo.getHotelCode());
			Object rt = push2channel.buildRoomMsg(hvo.getChainCode(), rtvo, c.getChannelId());

			String xml = XMLUtil.JAXBParseToXml(rt);
			adsMsg.setContent(xml);
			if (c.getIsChannelPushUrl()) {
				String hotelCodeE = replacePointEncodeParam(hvo.getHotelCode());
				String roomCodeE = replacePointEncodeParam(rtvo.getRoomTypeCode());
				c.setPushRateUrl(c.getPushRateUrl() + "/api/hotels/" + hotelCodeE + "/roomtypes/" + roomCodeE);
			} else {
				c.setPushRateUrl(c.getPushRateUrl().replace("{}", "pushRoomInfo"));
			}
			String response = PushDataUtil.postDataResponse( CommonUtil.getHttpReq(c.getPushRateUrl()), xml);
			adsMsg.setActionValue(response);
			adsMsg.setStatus(AdsMessage.EXEC_END_STATUS);
		} catch (Exception e) {
			adsMsg.setStatus(AdsMessage.EXEC_ERROR_STATUS);
			adsMsg.setErrMsg(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		saveAdsMessage(adsMsg);

	}

	@Async
	public void push2ChannelRate(ChannelGoodsVO cgvo, String language) {

		AdsMessage adsMsg = new AdsMessage();
		adsMsg.setRoomTypeCode("");
		adsMsg.setMsgType(AdsMessage.MSGTYPE_PUSH);
		adsMsg.setAdsType(AdsMessage.ADSTYPE_RATECODE);
		adsMsg.setStatus(AdsMessage.EXEC_INIT_STATUS);
		adsMsg.setTargetGDS(cgvo.getChannelCode());
		String echoToken = CommonUtil.generateSequenceNo();
		adsMsg.setEchoToken(echoToken);

		try {
			RatePlanVO rpvo = rateplanDao.getRateChainHotelByRateplanIdHotelId(cgvo.getHotelId(), language, cgvo.getRatePlanId());
			if (rpvo == null) {
				log.info("rateplanISNotFound");
				return;
			}
			// 构建日志
			adsMsg.setChainCode(rpvo.getChainCode());
			adsMsg.setHotelCode(rpvo.getRp().getHotelCode());
			adsMsg.setRatePlanCode(rpvo.getRp().getRatePlanCode());

			AbstractPush2ChannelProtocolService push2channel = getPush2ChannelProtocolService(cgvo.getIsOTA());
			Object rp = push2channel.buildRateMsg(rpvo, cgvo.getStatus(), language, channelDao.getChannelByChannelCode(cgvo.getChannelCode()).getChannelId());

			String xml = XMLUtil.JAXBParseToXml(rp);
			adsMsg.setContent(xml);
			if (cgvo.getIsChannelPushUrl()) {
				String hotelCodeE = replacePointEncodeParam(rpvo.getRp().getHotelCode());
				String rateCodeE = replacePointEncodeParam(rpvo.getRp().getRatePlanCode());
				cgvo.setPushRateUrl(cgvo.getPushRateUrl() + "/api/hotels/" + hotelCodeE + "/rateplans/" + rateCodeE);
			} else {
				cgvo.setPushRateUrl(cgvo.getPushRateUrl().replace("{}", "pushRatePlan"));
			}
			String response = PushDataUtil.postDataResponse(CommonUtil.getHttpReq(cgvo.getPushRateUrl()), xml);
			adsMsg.setActionValue(response);
			adsMsg.setStatus(AdsMessage.EXEC_END_STATUS);
		} catch (Exception e) {
			adsMsg.setStatus(AdsMessage.EXEC_ERROR_STATUS);
			adsMsg.setErrMsg(CommonUtil.getExceptionMsg(e, "ccm"));
		}

		saveAdsMessage(adsMsg);
	}

	private void saveAdsMessage(AdsMessage adsMsg) {
		try {
			if (AdsMessage.EXEC_ERROR_STATUS.equals(adsMsg.getStatus())) {
				adsMessageErrDao.save(adsMsg);
			} else {
				adsMessageDao.save(adsMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private AbstractPush2ChannelProtocolService getPush2ChannelProtocolService(Boolean isOta) {
		if (isOta == null) {
			isOta = true;
		}
		return (AbstractPush2ChannelProtocolService) isOTA.get(isOta);
	}

	private String getPushRateUrl(ChannelGoodsVO cgvo) {
		if (cgvo.getPushRateContent() != null && cgvo.getPushRateContent()) {
			if (cgvo.getIsChannelPushUrl() && StringUtils.hasText(cgvo.getPushRateUrl())) {
				return cgvo.getPushRateUrl();
			}
			if (!cgvo.getIsChannelPushUrl()) {
				Hotel h = hotelDao.getHotel(cgvo.getHotelId(), "hotelPushUrl");
				if (h != null && StringUtils.hasText(h.getHotelPushUrl())) {
					return h.getHotelPushUrl();
				}
			}
		}
		return null;
	}

	private String replacePointEncodeParam(String value) {
		try {
			return URLEncoder.encode(value.replace(".", "&#46"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		return value;
	}

	public Map<Boolean, Object> getIsOTA() {
		return isOTA;
	}

	public void setIsOTA(Map<Boolean, Object> isOTA) {
		this.isOTA = isOTA;
	}

	
	/**
	 * 推送一个渠道数据
	 * @param c
	 * @param hotelId
	 * @param language
	 */
	@Async
	public void push2ChannelProduct(Channel c,HotelVO hvo, String language,ChannelHotelConfig channelHotelConfig) {
		ChannelGoodsVO channelGoodsVO = new ChannelGoodsVO();
		channelGoodsVO.setChannelId(c.getChannelId());
		channelGoodsVO.setHotelId(hvo.getHotelId());
		//一个渠道的绑定关系
		List<ChannelGoodsVO> cgList = channelgoodsDao.getChannelGoodsVOyChannelGoods(channelGoodsVO);
		
		Map<String,Object> map = buildHotelProductMsg(cgList,hvo,channelHotelConfig);
		

		// 构建日志
		AdsMessage adsMsg = new AdsMessage();
		try {
			adsMsg.setChainCode(hvo.getChainCode());
			adsMsg.setHotelCode(hvo.getHotelCode());
			adsMsg.setMsgType(AdsMessage.MSGTYPE_PUSH);
			adsMsg.setAdsType(AdsMessage.ADSTYPE_HOTELPRODUCT);
			adsMsg.setStatus(AdsMessage.EXEC_INIT_STATUS);
			adsMsg.setTargetGDS(c.getChannelCode());
			String echoToken = CommonUtil.generateSequenceNo();
			adsMsg.setEchoToken(echoToken);
			adsMsg.setRatePlanCode("");
			adsMsg.setRoomTypeCode("");
			AbstractPush2ChannelProtocolService push2channel = getPush2ChannelProtocolService(c.getIsOTA());
			String xml = push2channel.buildHotelProductMsg(map);
			if(xml==null){
				return;
			}
			adsMsg.setContent(xml);
//			if (c.getIsChannelPushUrl()) {
//				String hotelCodeE = replacePointEncodeParam(hvo.getHotelCode());
//				c.setPushRateUrl(c.getPushRateUrl() + "/api/hotels/" + hotelCodeE );
//			} else {
//				c.setPushRateUrl(c.getPushRateUrl().replace("{}", "pushRoomInfo"));
//			}
			String response = PushDataUtil.postDataResponse(CommonUtil.getHttpReq(c.getPushRateUrl()), xml);
			adsMsg.setActionValue(response);
			otaHoterlProductNotifyRSManager.analyzeResponse(adsMsg);
			adsMsg.setStatus(AdsMessage.EXEC_END_STATUS);
		} catch (Exception e) {
			adsMsg.setStatus(AdsMessage.EXEC_ERROR_STATUS);
			adsMsg.setErrMsg(CommonUtil.getExceptionMsg(e, "ccm"));
		}
		saveAdsMessage(adsMsg);
	}

	
	@SuppressWarnings("unchecked")
	private Map<String,Object> buildHotelProductMsg(List<ChannelGoodsVO> cgList,HotelVO hvo,ChannelHotelConfig channelHotelConfig){
		Map<String,Object> map =new HashMap<String, Object>();
		
		map.put("hotelCode", hvo.getHotelCode());
		map.put("hotelName", hvo.getHotelName()==null?"":hvo.getHotelName());
		map.put("chainCode", hvo.getChainCode());
		map.put("telephone", hvo.getTelephone()==null?"":hvo.getTelephone());
		map.put("address", hvo.getAddress()==null?"":hvo.getAddress());
		map.put("email", hvo.getEmail()==null?"":hvo.getEmail());
		map.put("city", hvo.getCityName()==null?"":hvo.getCityName());
		map.put("provinceStr", hvo.getPrivinceName()==null?"":hvo.getPrivinceName());
		
		Map<String,List<RateGuaranteeRelationship>> rgrMap = new HashMap<String,List<RateGuaranteeRelationship>>();
		List<Map<String,Object>> hotelProducts = new ArrayList<Map<String,Object>>();
		map.put("hotelProducts", hotelProducts);
		String channelCode="";
		for(ChannelGoodsVO cgvo:cgList){
			channelCode = cgvo.getChannelCode();
			Map<String,Object> hotelProduct = new HashMap<String, Object>();
			hotelProducts.add(hotelProduct);
			List<RateGuaranteeRelationship> rgrList =  rgrMap.get(cgvo.getRatePlanId());
			if(rgrList==null){
				rgrList = rateGuaranteeRelationshipDao.getRateGuaranteeRelationshipByRatePlanId(cgvo.getRatePlanId());
				rgrMap.put(cgvo.getRatePlanId(), rgrList);
			}
			
			// 发布
			if (ChannelGoodsStatus.OFF.equals(cgvo.getStatus())) {
				hotelProduct.put("productStatusType", "Deactiviated");
			}else{
				hotelProduct.put("productStatusType", "Active");
			}
			hotelProduct.put("roomTypeCode", cgvo.getRoomTypeCode());
			hotelProduct.put("roomTypeName", cgvo.getRoomTypeName()==null?"":cgvo.getRoomTypeName());
			hotelProduct.put("ratePlanCode", cgvo.getRatePlanCode());
			hotelProduct.put("ratePlanName", cgvo.getRatePlanName()==null?"":cgvo.getRatePlanName());
			String guaranteeJsonArr="";
			for(RateGuaranteeRelationship r:rgrList){
				String policyName = r.getPolicyName();
				if(!"JWPAY".equalsIgnoreCase(policyName)){
					guaranteeJsonArr = policyName;
					break;
				}
			}
			if(!StringUtils.hasText(guaranteeJsonArr)){
				return null;
			}
			hotelProduct.put("guaranteeJsonArr", guaranteeJsonArr);
		}
		
		String channelHotelConfigStr = channelHotelConfig.getChannelHotelConfig();//酒店配置
		String channelRoomTypeConfigStr = channelHotelConfig.getChannelRoomTypeConfig();//房型配置
		String channelRateConfigStr = channelHotelConfig.getChannelRateConfig();//房价配置
		
		List<String> list = new ArrayList<String>();
		if(StringUtils.hasText(channelHotelConfigStr)){
			JSONObject channelHotelConfigJson = JSONObject.parseObject(channelHotelConfigStr);
			Set<String> keyset1 = channelHotelConfigJson.keySet();
			list.addAll(keyset1);
		}
		if(StringUtils.hasText(channelRoomTypeConfigStr)){
			JSONObject channelRoomTypeConfigJson = JSONObject.parseObject(channelRoomTypeConfigStr);
			Set<String> keyset2 = channelRoomTypeConfigJson.keySet();
			list.addAll(keyset2);
		}
		if(StringUtils.hasText(channelRateConfigStr)){
			JSONObject channelRateConfigJson = JSONObject.parseObject(channelRateConfigStr);
			Set<String> keyset3 = channelRateConfigJson.keySet();
			list.addAll(keyset3);
		}
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			if(key.equalsIgnoreCase("hotelCode")){
				continue;
			}
			Object value = entry.getValue();// list<Map<String,Object>>
			if(value instanceof ArrayList){
				ArrayList<HashMap<String,Object>> mapList = (ArrayList<HashMap<String, Object>>) value;
				for(int i=0;i<mapList.size();i++){
					HashMap<String,Object> mapchild = mapList.get(i);
					Iterator<Entry<String, Object>> itchild = mapchild.entrySet().iterator();
					while(itchild.hasNext()){
						Map.Entry<String, Object> entrychild = itchild.next();
						String keychild = entrychild.getKey();
						if(keychild.equalsIgnoreCase("roomTypeCode")){
							continue;
						}
						if(keychild.equalsIgnoreCase("ratePlanCode")){
							continue;
						}
						
						if(!list.contains(keychild)){
							mapchild.put(keychild, "");
						}
					}
				}
			}else if(!list.contains(key)){
				map.put(key, "");
			}
		}
		map.put("channelCode", channelCode);
		return map;
	}
	

}
