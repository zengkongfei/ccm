package com.ccm.api.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.channel.ChannelHotelMD5InfoDao;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.channel.mongodb.ChannelHotelConfigDaoMongo;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelHotelConfig;
import com.ccm.api.model.channel.ChannelHotelMD5Info;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.system.Push2ChannelManager;
import com.ccm.api.service.system.Push2ChannelStaticMsgManager;
import com.ccm.api.util.CommonUtil;

@Service("push2ChannelStaticMsgManager")
public class Push2ChannelStaticMsgManagerImpl implements Push2ChannelStaticMsgManager {
	private Log log = LogFactory.getLog(Push2ChannelStaticMsgManagerImpl.class);

	@Resource
	private ChannelgoodsDao channelgoodsDao;
	@Resource
	private HotelDao hotelDao;

	@Resource
	private ChannelHotelMD5InfoDao channelHotelMD5InfoDao;

	@Resource
	private ChannelHotelConfigDaoMongo channelHotelConfigDao;

	@Resource
	private RoomTypeDao roomTypeDao;

	@Resource
	private RatePlanManager ratePlanManager;

	@Resource
	private Push2ChannelManager push2ChannelManager;
	
	@Override
	public void pushStaticMsgHotelInfo(String hotelId, String language,
			Boolean bool) {
		/** 不需要推送 */
		if (!bool) {
			return;
		}

		HotelVO hvo = hotelDao.getPushHotelInfoByHotelId(hotelId, language);
		if (hvo == null) {
			log.info("hotelISNotFound:" + hotelId);
			return;
		}

		/**
		 * 酒店绑定关系
		 */
		List<ChannelGoodsVO> cgList = channelgoodsDao.getPushChannelRelationByHotelRateId(hotelId, null, null);
		List<String> channelHotelId = new ArrayList<String>();
		for (ChannelGoodsVO cgvo : cgList) {
			String key = cgvo.getChannelId() + cgvo.getHotelId();
			if (channelHotelId.contains(key)) {
				continue;
			}
			channelHotelId.add(key);
			if (cgvo.getPushHotel() == null || !cgvo.getPushHotel()) {
				continue;
			}
			if (!cgvo.getIsChannelPushUrl()) {
				cgvo.setPushRateUrl(hvo.getHotelPushUrl());
			}
			if (!StringUtils.hasText(cgvo.getPushRateUrl())) {
				continue;
			}

			Channel c = new Channel();
			c.setChannelId(cgvo.getChannelId());
			c.setChannelCode(cgvo.getChannelCode());
			c.setIsOTA(cgvo.getIsOTA());
			c.setPushRateUrl(cgvo.getPushRateUrl());
			c.setIsChannelPushUrl(cgvo.getIsChannelPushUrl());
			
			ChannelHotelConfig channelHotelConfig = channelHotelConfigDao.getChannelHotelConfigByChannelId(cgvo.getChannelId());
			//单独推送
			if (channelHotelConfig==null || channelHotelConfig.getPushMethod() == 1) {
				push2ChannelManager.pushHotelInfo(hvo, c);
			} else {//组合推送
				if (cgvo.getPushHotel() == null || !cgvo.getPushHotel()
						|| cgvo.getPushRateContent()==null || !cgvo.getPushRateContent()
						|| cgvo.getPushRoom()==null || !cgvo.getPushRoom()
						) {
					continue;
				}
				
				
				List<String> channelIdMap = new ArrayList<String>();
				if(!channelIdMap.contains(cgvo.getChannelId())){
					channelIdMap.add(cgvo.getChannelId());
					/** 修改对象的上一次md5 */
					ChannelHotelMD5Info channelHotelMD5Info = channelHotelMD5InfoDao.getChannelHotelMD5InfoByParam(cgvo.getChannelId(),ChannelHotelMD5Info.TYPE_HOTEL, hotelId);
	
					String md5new = "";
					try {
						if(StringUtils.hasText(channelHotelConfig.getChannelHotelConfig())){
							md5new = CommonUtil.objectMd5(channelHotelConfig.getChannelHotelConfig(), hvo);// 酒店这次的md5
						}
					} catch (Exception e) {
						md5new = "";
						log.error(e);
					}
	
					/** 无md5,不需要推送 */
					if (!StringUtils.hasText(md5new)) {
						continue;
					}
					/** 原来无md5 ，这次有md5，需要推送 */
					if ((channelHotelMD5Info==null||!StringUtils.hasText(channelHotelMD5Info.getHotelMd5()))&& StringUtils.hasText(md5new)) {
						if(channelHotelMD5Info==null){
							channelHotelMD5Info = new ChannelHotelMD5Info();
							channelHotelMD5Info.setChannelId(cgvo.getChannelId());
							channelHotelMD5Info.setHotelId(hotelId);
						}
						channelHotelMD5Info.setHotelMd5(md5new);
						channelHotelMD5InfoDao.save(channelHotelMD5Info);
						pushHotelInfoContent(c, hvo, language,channelHotelConfig);
						continue;
					}
					/** 原来的md5 与这次不一样，需要推送 */
					if (!channelHotelMD5Info.getHotelMd5().equalsIgnoreCase(md5new)) {
						channelHotelMD5Info.setHotelMd5(md5new);
						channelHotelMD5InfoDao.save(channelHotelMD5Info);
						pushHotelInfoContent(c, hvo, language,channelHotelConfig);
						continue;
					}
				}
			}
		}
	}


	@Override
	public void pushStaticMsgRoomType(String hotelId, String roomtypeId,
			String language, Boolean bool) {
		if(!bool){
			return;
		}
		HotelVO hvo = hotelDao.getPushHotelInfoByHotelId(hotelId, language);
		if (hvo == null) {
			log.info("hotelISNotFound:" + hotelId);
			return;
		}

		List<ChannelGoodsVO> cgList = channelgoodsDao.getPushChannelRelationByHotelRateId(hotelId, null, roomtypeId);
		List<String> channelHotelRoomId = new ArrayList<String>();
		List<String> channelIdMap = new ArrayList<String>();
		for (ChannelGoodsVO cgvo : cgList) {
			String key = cgvo.getChannelId() + cgvo.getHotelId() + cgvo.getRoomTypeId();
			if (channelHotelRoomId.contains(key)) {
				continue;
			}
			channelHotelRoomId.add(key);
			if (cgvo.getPushRoom() == null || !cgvo.getPushRoom()) {
				continue;
			}
			if (!cgvo.getIsChannelPushUrl()) {
				cgvo.setPushRateUrl(hvo.getHotelPushUrl());
			}
			if (!StringUtils.hasText(cgvo.getPushRateUrl())) {
				continue;
			}
			
			Channel c = new Channel();
			c.setChannelId(cgvo.getChannelId());
			c.setChannelCode(cgvo.getChannelCode());
			c.setIsOTA(cgvo.getIsOTA());
			c.setPushRateUrl(cgvo.getPushRateUrl());
			c.setIsChannelPushUrl(cgvo.getIsChannelPushUrl());
			c.setChannelId(cgvo.getChannelId());
			
			ChannelHotelConfig channelHotelConfig = channelHotelConfigDao.getChannelHotelConfigByChannelId(cgvo.getChannelId());
			//单独推送
			if (channelHotelConfig==null || channelHotelConfig.getPushMethod() == 1) {
				push2ChannelManager.pushRoomContent(hvo, c, roomtypeId);
			} else {//组合推送
				if (cgvo.getPushHotel() == null || !cgvo.getPushHotel()
						|| cgvo.getPushRateContent()==null || !cgvo.getPushRateContent()
						|| cgvo.getPushRoom()==null || !cgvo.getPushRoom()
						) {
					continue;
				}
				if(!channelIdMap.contains(cgvo.getChannelId())){
					channelIdMap.add(cgvo.getChannelId());
					
					RoomTypeVO roomTypeVO = roomTypeDao.getRoomTypeById(roomtypeId);
					if(roomTypeVO==null){
						continue;
					}
					/** 修改对象的上一次md5 */
					ChannelHotelMD5Info channelHotelMD5Info = channelHotelMD5InfoDao.getChannelHotelMD5InfoByParam(cgvo.getChannelId(),ChannelHotelMD5Info.TYPE_ROONTYPE, roomtypeId);
					
					String md5new = "";
					try {
						if(StringUtils.hasText(channelHotelConfig.getChannelRoomTypeConfig())){
							md5new = CommonUtil.objectMd5(channelHotelConfig.getChannelRoomTypeConfig(),roomTypeVO);// 房型这次的md5
						}
					} catch (Exception e) {
						md5new = "";
						log.error(e);
					}
					
					/** 无md5,不需要推送 */
					if (!StringUtils.hasText(md5new)) {
						continue;
					}
					/** 原来无md5 ，这次有md5，需要推送 */
					if ((channelHotelMD5Info==null||!StringUtils.hasText(channelHotelMD5Info.getRoomTypeMd5())) && StringUtils.hasText(md5new)) {
						if(channelHotelMD5Info==null){
							channelHotelMD5Info = new ChannelHotelMD5Info();
							channelHotelMD5Info.setChannelId(cgvo.getChannelId());
							channelHotelMD5Info.setRoomTypeId(roomtypeId);
						}
						channelHotelMD5Info.setRoomTypeMd5(md5new);
						channelHotelMD5InfoDao.save(channelHotelMD5Info);
						pushHotelInfoContent(c, hvo, language,channelHotelConfig);
						continue;
					}
					/** 原来的md5 与这次不一样，需要推送 */
					if (!channelHotelMD5Info.getRoomTypeMd5().equalsIgnoreCase(md5new)) {
						channelHotelMD5Info.setRoomTypeMd5(md5new);
						channelHotelMD5InfoDao.save(channelHotelMD5Info);
						pushHotelInfoContent(c, hvo, language,channelHotelConfig);
						continue;
					}
				}
				
			}
		}

	}
	
	
	@Override
	public void pushStaticMsgRatePaln(String hotelId, String ratePlanId,String language, Boolean bool) {
		if(!bool){
			return;
		}
		HotelVO hvo = hotelDao.getPushHotelInfoByHotelId(hotelId, language);
		if (hvo == null) {
			log.info("hotelISNotFound:" + hotelId);
			return;
		}
		List<ChannelGoodsVO> cgList = channelgoodsDao.getPushChannelRelationByHotelRateId(hotelId, ratePlanId, null);
		
		List<String> channelHotelRateId = new ArrayList<String>();
		List<String> channelIdMap = new ArrayList<String>();
		
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
			ChannelHotelConfig channelHotelConfig = channelHotelConfigDao.getChannelHotelConfigByChannelId(cgvo.getChannelId());
			//单独推送
			if (channelHotelConfig==null ||channelHotelConfig.getPushMethod() == 1) {
				push2ChannelManager.push2ChannelRate(cgvo, language);
			} else {
				if (cgvo.getPushHotel() == null || !cgvo.getPushHotel()
						|| cgvo.getPushRateContent()==null || !cgvo.getPushRateContent()
						|| cgvo.getPushRoom()==null || !cgvo.getPushRoom()
						) {
					continue;
				}
				Channel c = new Channel();
				c.setChannelId(cgvo.getChannelId());
				c.setChannelCode(cgvo.getChannelCode());
				c.setIsOTA(cgvo.getIsOTA());
				c.setPushRateUrl(cgvo.getPushRateUrl());
				c.setIsChannelPushUrl(cgvo.getIsChannelPushUrl());
				
				channelIdMap.add(cgvo.getChannelId());
				boolean flag=false;
				//比较一个房价的md5
				if(StringUtils.hasText(ratePlanId)){
					/** 修改对象的上一次md5 */
					ChannelHotelMD5Info channelHotelMD5Info = channelHotelMD5InfoDao.getChannelHotelMD5InfoByParam(cgvo.getChannelId(),ChannelHotelMD5Info.TYPE_RATEPLAN, ratePlanId);
					RatePlanVO ratePlanVO = ratePlanManager.getRatePlanVO(ratePlanId, true,language);
					if(ratePlanVO==null){
						continue;
					}
					ratePlanVO.setProductStatusType(cgvo.getStatus());
					String md5new="";
					try {
						if(StringUtils.hasText(channelHotelConfig.getChannelRateConfig())){
							md5new = CommonUtil.objectMd5(channelHotelConfig.getChannelRateConfig(),ratePlanVO);// 房价这次的md5
						}
					} catch (Exception e) {
						md5new = "";
						log.error(e);
					}
					/** 无md5,不需要推送 */
					if (!StringUtils.hasText(md5new)) {
						flag=false;
					}else if ((channelHotelMD5Info==null||!StringUtils.hasText(channelHotelMD5Info.getRatePlanMd5())) && StringUtils.hasText(md5new)) {/** 原来无md5 ，这次有md5，需要推送 */
						if(channelHotelMD5Info==null){
							channelHotelMD5Info = new ChannelHotelMD5Info();
							channelHotelMD5Info.setChannelId(cgvo.getChannelId());
							channelHotelMD5Info.setRatePlanId(ratePlanId);
						}
						channelHotelMD5Info.setRatePlanMd5(md5new);
						channelHotelMD5InfoDao.save(channelHotelMD5Info);
						flag=true;
					}else  if (!channelHotelMD5Info.getRatePlanMd5().equalsIgnoreCase(md5new)) {/** 原来的md5 与这次不一样，需要推送 */
						channelHotelMD5Info.setRatePlanMd5(md5new);
						channelHotelMD5InfoDao.save(channelHotelMD5Info);
						flag=true;
					}
				}
				if(flag){
					pushHotelInfoContent(c, hvo, language,channelHotelConfig);
					continue;
				}
			}
		}
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


	@Override
	public void pushAllStaticMsg(String hotelId, String channelId, Boolean bool,String language) {
		HotelVO hvo = hotelDao.getPushHotelInfoByHotelId(hotelId, language);
		if (hvo == null) {
			log.info("hotelISNotFound:" + hotelId);
			return;
		}
		
		List<ChannelGoodsVO> cgList = channelgoodsDao.getPushChannelRelationByHotelRateId(hvo.getHotelId(), null, null);
		/** key=channelId value = ChannelHotelConfig */
		Map<String,ChannelHotelConfig> channelHotelConfigMap = new HashMap<String, ChannelHotelConfig>();
		/** 
		 * key=channelId value=ChannelHotelMD5Info  酒店的md5
		 * key=channelId+ratePlanId value=ChannelHotelMD5Info  房价的md5
		 * 
		 * */
		Map<String,ChannelHotelMD5Info> hotelMD5InfoMap = new HashMap<String, ChannelHotelMD5Info>();
		List<String> channelHotelId = new ArrayList<String>();
		for(ChannelGoodsVO cgvo:cgList){
			String key = cgvo.getChannelId() + cgvo.getHotelId();
			if (channelHotelId.contains(key)) {
				continue;
			}
			channelHotelId.add(key);
			/** 三种类型推送 */
			if (cgvo.getPushHotel() == null || !cgvo.getPushHotel() || 
					cgvo.getPushRateContent()==null || !cgvo.getPushRateContent() ||
					cgvo.getPushRoom()==null || !cgvo.getPushRoom()
					) {
				continue;
			}
			if (!cgvo.getIsChannelPushUrl()) {
				cgvo.setPushRateUrl(hvo.getHotelPushUrl());
			}
			if (!StringUtils.hasText(cgvo.getPushRateUrl())) {
				continue;
			}
			String channelIdKey = cgvo.getChannelId();
			
			ChannelHotelConfig channelHotelConfig = channelHotelConfigMap.get(channelIdKey);
			if(channelHotelConfig == null){
				channelHotelConfig = channelHotelConfigDao.getChannelHotelConfigByChannelId(channelIdKey);
				channelHotelConfigMap.put(channelIdKey, channelHotelConfig);
			}
			/** 未配置组合推送*/
			if(channelHotelConfig == null || channelHotelConfig.getPushMethod()==1){
				continue;
			}
			
			ChannelHotelMD5Info hotelMD5Info = hotelMD5InfoMap.get(channelIdKey);
			/** 酒店，按照渠道，只处理一次 */
			if(hotelMD5Info==null){
				hotelMD5Info = channelHotelMD5InfoDao.getChannelHotelMD5InfoByParam(channelIdKey,ChannelHotelMD5Info.TYPE_HOTEL, hotelId);
				String hotelMd5 = "";
				try {
					if(StringUtils.hasText(channelHotelConfig.getChannelHotelConfig())){
						hotelMd5 = CommonUtil.objectMd5(channelHotelConfig.getChannelHotelConfig(), hvo);// 酒店这次的md5
					}
				} catch (Exception e) {
					hotelMd5 = "";
					log.error(e);
				}
				
				if(hotelMD5Info==null){
					hotelMD5Info = new ChannelHotelMD5Info();
					hotelMD5Info.setChannelId(channelIdKey);
					hotelMD5Info.setHotelId(hotelId);
				}
				hotelMD5Info.setHotelMd5(hotelMd5);
				channelHotelMD5InfoDao.save(hotelMD5Info);
				hotelMD5InfoMap.put(channelIdKey, hotelMD5Info);
			}
			
			/** 房价，按照渠道，只处理一次*/
			String rateplanId = cgvo.getRatePlanId();
			ChannelHotelMD5Info ratePlanMD5Info = hotelMD5InfoMap.get(channelIdKey+rateplanId);
			if(ratePlanMD5Info==null){
				ratePlanMD5Info = channelHotelMD5InfoDao.getChannelHotelMD5InfoByParam(channelIdKey,ChannelHotelMD5Info.TYPE_RATEPLAN, rateplanId);
				RatePlanVO ratePlanVO = ratePlanManager.getRatePlanVO(rateplanId, true,language);
				if(ratePlanVO==null){
					continue;
				}
				ratePlanVO.setProductStatusType(cgvo.getStatus());
				String ratePlanMd5 = "";
				try {
					if(StringUtils.hasText(channelHotelConfig.getChannelRateConfig())){
						ratePlanMd5 = CommonUtil.objectMd5(channelHotelConfig.getChannelRateConfig(), ratePlanVO);// 酒店这次的md5
					}
				} catch (Exception e) {
					ratePlanMd5 = "";
					log.error(e);
				}
				if(ratePlanMD5Info==null){
					ratePlanMD5Info = new ChannelHotelMD5Info();
					ratePlanMD5Info.setChannelId(channelIdKey);
					ratePlanMD5Info.setRatePlanId(rateplanId);
				}
				ratePlanMD5Info.setHotelMd5(ratePlanMd5);
				channelHotelMD5InfoDao.save(ratePlanMD5Info);
				hotelMD5InfoMap.put(channelIdKey+rateplanId, ratePlanMD5Info);
			}
			
			String roomTypeId = cgvo.getRoomTypeId();
			/**房型md5 */
			ChannelHotelMD5Info channelHotelMD5Info = channelHotelMD5InfoDao.getChannelHotelMD5InfoByParam(channelIdKey,ChannelHotelMD5Info.TYPE_ROONTYPE, roomTypeId);
			RoomTypeVO roomTypeVO = roomTypeDao.getRoomTypeById(roomTypeId);
			if(roomTypeVO==null){
				continue;
			}
			String md5new = "";
			try {
				if(StringUtils.hasText(channelHotelConfig.getChannelRoomTypeConfig())){
					md5new = CommonUtil.objectMd5(channelHotelConfig.getChannelRoomTypeConfig(),roomTypeVO);// 房型这次的md5
				}
			} catch (Exception e) {
				md5new = "";
				log.error(e);
			}
			if(channelHotelMD5Info==null){
				channelHotelMD5Info = new ChannelHotelMD5Info();
				channelHotelMD5Info.setChannelId(channelIdKey);
				channelHotelMD5Info.setRoomTypeId(roomTypeId);
			}
			channelHotelMD5Info.setRoomTypeMd5(md5new);
			channelHotelMD5InfoDao.save(channelHotelMD5Info);
			
			Channel channel = new Channel();
			channel.setChannelId(cgvo.getChannelId());
			channel.setChannelCode(cgvo.getChannelCode());
			channel.setIsOTA(cgvo.getIsOTA());
			channel.setPushRateUrl(cgvo.getPushRateUrl());
			channel.setIsChannelPushUrl(cgvo.getIsChannelPushUrl());
			
			pushHotelInfoContent(channel, hvo, language,channelHotelConfig);
		}	
	}
	
	public void pushHotelInfoContent(Channel c, HotelVO hvo, String language,ChannelHotelConfig channelHotelConfig) {
		//推送全部渠道
		if(c==null){
			List<ChannelGoodsVO> cgList = channelgoodsDao.getPushChannelRelationByHotelRateId(hvo.getHotelId(), null, null);
			List<String> channelHotelId = new ArrayList<String>();
			for (ChannelGoodsVO cgvo : cgList) {
				String key = cgvo.getChannelId() + cgvo.getHotelId();
				if (channelHotelId.contains(key)) {
					continue;
				}
				channelHotelId.add(key);
				if (!cgvo.getIsChannelPushUrl()) {
					cgvo.setPushRateUrl(hvo.getHotelPushUrl());
				}
				if (!StringUtils.hasText(cgvo.getPushRateUrl())) {
					continue;
				}
				
				if (cgvo.getPushHotel() == null || !cgvo.getPushHotel() || 
						cgvo.getPushRateContent()==null || !cgvo.getPushRateContent() ||
						cgvo.getPushRoom()==null || !cgvo.getPushRoom()
						) {
					continue;
				}
				
				Channel channel = new Channel();
				channel.setChannelId(cgvo.getChannelId());
				channel.setChannelCode(cgvo.getChannelCode());
				channel.setIsOTA(cgvo.getIsOTA());
				channel.setPushRateUrl(cgvo.getPushRateUrl());
				channel.setIsChannelPushUrl(cgvo.getIsChannelPushUrl());
				push2ChannelManager.push2ChannelProduct(channel,hvo,language,channelHotelConfig);
			}
			
		}else{//推送一个渠道
			push2ChannelManager.push2ChannelProduct(c,hvo,language,channelHotelConfig);
			
		}
	}

	@Override
	public void pushStaticMsgOff(ChannelGoodsVO cgvo, String language) {
		ChannelHotelConfig channelHotelConfig = channelHotelConfigDao.getChannelHotelConfigByChannelId(cgvo.getChannelId());
		// 单独推送
		if (channelHotelConfig == null || channelHotelConfig.getPushMethod() == 1) {
			push2ChannelManager.pushRateOnOff(language, cgvo);
		} else {
			if (cgvo.getPushHotel() == null || !cgvo.getPushHotel() || cgvo.getPushRateContent() == null || !cgvo.getPushRateContent() || cgvo.getPushRoom() == null || !cgvo.getPushRoom()) {
				return;
			}
			String hotelId = cgvo.getHotelId();
			HotelVO hvo = hotelDao.getPushHotelInfoByHotelId(hotelId, language);
			if (hvo == null) {
				log.info("hotelISNotFound:" + hotelId);
				return;
			}
			Channel channel = new Channel();
			channel.setChannelId(cgvo.getChannelId());
			channel.setChannelCode(cgvo.getChannelCode());
			channel.setIsOTA(cgvo.getIsOTA());
			channel.setPushRateUrl(cgvo.getPushRateUrl());
			channel.setIsChannelPushUrl(cgvo.getIsChannelPushUrl());
			pushHotelInfoContent(channel, hvo, language, channelHotelConfig);
		}
	}
}
