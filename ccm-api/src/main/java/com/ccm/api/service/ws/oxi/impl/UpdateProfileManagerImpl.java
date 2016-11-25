package com.ccm.api.service.ws.oxi.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import _0._2.fidelio.profile.Profile;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.common.PessimisticLockDao;
import com.ccm.api.model.channel.ChannelGoods;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.RatePlanVO;
import com.ccm.api.model.common.PessimisticLock;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.model.constant.UpdateProfileConstant;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.hotel.HotelSwitch;
import com.ccm.api.model.log.ReceiveMsgLog;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.hotel.CustomManager;
import com.ccm.api.service.hotel.HotelManager;
import com.ccm.api.service.hotel.HotelSwitchManager;
import com.ccm.api.service.log.ReceiveMsgLogManager;
import com.ccm.api.service.ratePlan.RateCustomManager;
import com.ccm.api.service.system.Push2ChannelManager;
import com.ccm.api.service.ws.oxi.UpdateProfileManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.OwsOxiUtil;

@Service("updateProfileManager")
public class UpdateProfileManagerImpl implements UpdateProfileManager {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ChannelgoodsDao channelgoodsDao;
	
	@Resource
	private HotelManager hotelManager;
	@Resource
	private CustomManager customManager;
	@Resource
	private HotelSwitchManager hotelSwitchManager;
	@Resource
	private RatePlanManager ratePlanManager;
	@Resource
	private RateCustomManager rateCustomManager;
	@Resource
	private ReceiveMsgLogManager receiveMsgLogManager;
	@Autowired
	private ChannelgoodsManager channelgoodsManager;
	@Resource
	private Push2ChannelManager push2ChannelManager;
	
	@Resource
	private PessimisticLockDao pessimisticLockDao;

	// 自动发布渠道
	public void runAutoPublishing(Profile profile, String propertyName,List<String> rateList) {
		// 获取hotelId
		List<Hotel> hotels = hotelManager.getHotelByHotelCode(propertyName);
		String hotelId = hotels.get(0).getHotelId();
		if(null==hotelId){
			//酒店不存在报错
			throw new BizException("Error: hotel does not exist");
		}
		
		//如果profile内的所有房价，在ccm中一个对应也没有，则报错Error: rate code does not exist
		boolean flag=true;
		for (String rateCode : rateList) {	
			if (null != rateCode) {
				Rateplan rateplan = ratePlanManager.getRateplanByRateplanCode(rateCode, propertyName);
				if((null != rateplan) && (UpdateProfileConstant.COL.equals(rateplan.getSourceCode()))) {
					flag=false;
				}
			}
		}
		if(flag){
			//房价不存在报错
			throw new BizException("Error: rate code does not exist");
		}
		
		for (String rateCode : rateList) {	
			if (null != rateCode) {
				Rateplan rateplan = ratePlanManager.getRateplanByRateplanCode(rateCode, propertyName);
				/*
				if(null==rateplan){
					//房价不存在报错
					throw new BizException("Error: rate code does not exist");
				}
				*/
				
				if ((null != rateplan) && (UpdateProfileConstant.COL.equals(rateplan.getSourceCode()))) 
				{
					ChannelGoods channelgoods = new ChannelGoods();
					channelgoods.setHotelId(hotelId);
					channelgoods.setRateplanid(rateplan.getRatePlanId());
					channelgoods.setEffectiveDate(new Date());
					channelgoods.setExpireDate(null);
					channelgoods.setStatus(ChannelGoodsStatus.DEFAULT);
					channelgoods.setIsActiveImmediately(true);

					List<RoomTypeVO> roomTypes = channelgoodsManager
							.getRoomTypeByRatePlanId(rateplan.getRatePlanId(),
									UpdateProfileConstant.EN_US);
					List<String> roomTypeIds = new ArrayList<String>();
					for (RoomTypeVO rt : roomTypes) {
						roomTypeIds.add(rt.getRoomTypeId());
					}

					Custom cus = null;
					// 获取该酒店的客户custom对象
					List<Custom> customs = customManager
							.getCustomByHotelId(hotelId);
					// 获取XML的mfResortProfileID
					String mfResortProfileID = profile.getMfResortProfileID();
					// 根据ProfileID获取accessCode
					for (Custom custom : customs) 
					{
						if ((null != mfResortProfileID)
								&& (mfResortProfileID.equals(custom
										.getProfileID()))
								&& ((UpdateProfileConstant.TRAVEL_AGENT
										.equals(custom.getType())) || (UpdateProfileConstant.CORPORATE
										.equals(custom.getType())))
								&& (custom.getType().equals(profile
										.getProfileType()))) 
						{
							cus = custom;
						}
					}
					
					if(null==cus){
						//对应客户不存在报错
						throw new BizException("Error: CCM customer profile ID does not exist");
					}
					
					List<String> channelIds = new ArrayList<String>();
					channelIds.add(cus.getChannelId());
					
					// 查询绑定关系
					List<ChannelGoodsVO> result = channelgoodsDao.searchChannelGoodsByRatePlanId(hotelId,rateplan.getRatePlanId(),cus.getChannelId());
					//已存在绑定关系报错
					if ( (null!=result) && (result.size()>0) ) {
						throw new BizException("Error: channelgoods already exist");
					}else{
			
						// 渠道绑定该房价以及该房价下的所有房型
						bind(channelgoods, roomTypeIds, channelIds,
								UpdateProfileConstant.EN_US, hotelId);
						
						// 发布该房价
						publish(hotelId,rateplan.getRatePlanId(),cus.getChannelId());	
					}
					
				}
			}
		}
		
	}

	/**
	 * 自动绑定渠道和房价：模仿ccm-mc的渠道管理菜单下渠道绑定并发布功能，相当于房型全选，选择发布即激活
	 * @param channelgoods
	 * @param roomTypeIds
	 * @param channelIds
	 * @param language
	 * @param hotelId
	 */
	public void bind(ChannelGoods channelgoods, List<String> roomTypeIds,
			List<String> channelIds, String language, String hotelId) {
		PessimisticLock pl = new PessimisticLock();
		pl.setBizId(CommonUtil.generatePrimaryKey());
		pl.setBizType("saveOrUpdateChannelGoods");
		pl.setCreatedTime(new Date());
		try {
			pessimisticLockDao.savePessimisticLock(pl);
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
			
			channelgoodsManager.saveOrUpdateChannelGoodsForProfile(channelgoods, roomTypeIds, channelIds,hotelId);
			
			push2ChannelManager.pushHotelRoomContent(language, hotelId,channelIds, roomTypeIds);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pessimisticLockDao.deletePessimisticLock(pl);
		}
	}
	/**
	 * 发布绑定的房价
	 * @param hotelId
	 */
	public void publish(String hotelId,String ratePlanId,String channelId) {

		// 防并发，使用数据库表锁
		PessimisticLock pl = new PessimisticLock();
		pl.setBizId(CommonUtil.generatePrimaryKey());
		pl.setBizType("publishChannelGoods");
		pl.setCreatedTime(new Date());
		try {
			pessimisticLockDao.savePessimisticLock(pl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 发布绑定关系先更新相应状态为发布进行中，再更新房价日历
		try {
			channelgoodsManager.publishChannelGoodsForProfile(hotelId,
					UpdateProfileConstant.EN_US,ratePlanId,channelId);
		} catch (BizException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pessimisticLockDao.deletePessimisticLock(pl);
		}
	}

	/**
	 * rateCode绑定accessCode
	 */
	@Override
	public void updateRateCode(Profile profile, String hotelCode,String message,List<String> rateList) {
		String customId = null;
		Custom customMessage=new Custom();
		customMessage.setProfileMessage(message);
		// String accessCode=null;
		// 获取hotelId
		List<Hotel> hotels = hotelManager.getHotelByHotelCode(hotelCode);
		String hotelId = hotels.get(0).getHotelId();

		if(null==hotelId){
			//酒店不存在报错
			throw new BizException("Error: hotel does not exist");
		}
		
		// 如果isUploadRateHeader FROM `hotel_switch` 为true
		HotelSwitch hotelSwitch = hotelSwitchManager.getByHotelId(hotelId);
		if ((null != hotelSwitch) && (hotelSwitch.getIsUploadRateHeader())) 
		{
			// 获取该酒店的客户custom对象
			List<Custom> customs = customManager.getCustomByHotelId(hotelId);
			// 获取XML的mfResortProfileID
			String mfResortProfileID = profile.getMfResortProfileID();
			// 根据ProfileID获取accessCode
			for (Custom custom : customs) {
				if ((null != mfResortProfileID)
						&& (mfResortProfileID.equals(custom.getProfileID()))
						&& ((UpdateProfileConstant.TRAVEL_AGENT.equals(custom
								.getType())) || (UpdateProfileConstant.CORPORATE
								.equals(custom.getType())))
						&& (custom.getType().equals(profile.getProfileType()))) {
					// accessCode=custom.getAddress();
					customId = custom.getCustomId();
				}
			}
			if(null==customId){
				//对应客户不存在报错
				throw new BizException("Error: CCM customer profile ID does not exist");
			}
			customMessage.setCustomId(customId);
			// 将该酒店所有符合要求的rateCode绑定accessCode
			// if(null!=accessCode){
			if (null != customId) {
				
				for (String rateCode : rateList) {
					if (null != rateCode) {
						Rateplan rateplan = ratePlanManager
								.getRateplanByRateplanCode(rateCode, hotelCode);
						
//						if(!UpdateProfileConstant.COL.equals(rateplan.getSourceCode())){
//							//不满足SourceCode为COL保存
//							throw new BizException("SourceCode is COL error");
//						}
						
						if ((null != rateplan) && (UpdateProfileConstant.COL.equals(rateplan.getSourceCode()))) {
							// rateplan.setAccessCode(accessCode);
							// ratePlanManager.updateAccessCode(rateplan);

							List<String> customList = new ArrayList<String>();
							customList.add(customId);

							RatePlanVO ratePlanVO = new RatePlanVO();
							ratePlanVO.setCustomList(customList);
							
							// 把customId和ratePlanId这两个字段保存到rate_custom_relationship表
							rateCustomManager.addCustomRelationship(rateplan.getRatePlanId(),ratePlanVO);
							//把update的profile保存到对应的custom（两者以唯一的ProfileID对应）
							customManager.updateProfileMessage(customMessage);
						}
					}
				}
			}

		}else{
			//isUploadRateHeader FROM `hotel_switch` 不为true 报错
			throw new BizException("Error: UploadRateHeader control button is not TRUE");
		}

	}

	// 保存PMS上传消息的日志
	public void savePmsMsg(Map<String, String> receive) {
		ReceiveMsgLog rml = new ReceiveMsgLog();
		rml.setMessage(receive.get("Message"));
		rml.setMessageType(receive.get("MessageType"));
		rml.setHotelCode(receive.get("propertyName"));
		rml.setBeginMsgId(receive.get("transactionId"));// PMS的交易号
		rml.setStatus(receive.get("status"));
		rml.setReceiveMsgLogId(receive.get("receiveMsgLogId")); // 外部生成的UUID,作为主键
		rml.setInterfaceId(OXIConstant.Interface);
		rml.setExtMsgId(OwsOxiUtil.getAtomicCounter18().toString());// 系统产生的交易号
		receiveMsgLogManager.addReceiveMsgLog(rml);
	}

}
