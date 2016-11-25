
/**
 * 
 */
package com.ccm.api.service.channel.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.ccm.api.SecurityHolder;
import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.ads.AdsMessageDao;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.ChannelHotelDao;
import com.ccm.api.dao.channel.ChannelgoodsDao;
import com.ccm.api.dao.channel.mongodb.ChannelHotelConfigDaoMongo;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.ratePlan.mongodb.RoomMsgDaoMongo;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.ChannelGoods;
import com.ccm.api.model.channel.ChannelHotel;
import com.ccm.api.model.channel.ChannelHotelConfig;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.channel.vo.GoodsVO;
import com.ccm.api.model.common.vo.InvokeResponse;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.constant.CompanyType;
import com.ccm.api.model.constant.UserInitStatus;
import com.ccm.api.model.enume.ChannelCodeEnum;
import com.ccm.api.model.hotel.vo.HotelVO;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.vo.ChannelRateplanVO;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.ChannelHotelManager;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.channel.RatePlanManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.log.ReceiveReqResManager;
import com.ccm.api.service.ratePlan.PriceCalcManager;
import com.ccm.api.service.system.Push2ChannelManager;
import com.ccm.api.service.system.Push2ChannelStaticMsgManager;
import com.ccm.api.service.taobaoAPI2.TaobaoApiManager;
import com.ccm.api.service.user.UserManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
@Service("channelgoodsManager")
public class ChannelgoodsManagerImpl extends GenericManagerImpl<ChannelGoods, String> implements ChannelgoodsManager {

	@Autowired
	private ChannelgoodsDao channelgoodsDao;

	@Autowired
	private RoomTypeDao roomTypeDao;

	@Autowired
	private UserManager userManager;

	@Autowired
	private ChannelHotelManager channelHotelManager;

	@Autowired
	private PriceCalcManager priceCalcManager;

	@Autowired
	private ChannelManager channelManager;

	@Autowired
	private RatePlanManager ratePlanManager;

	@Resource
	private TaobaoApiManager taobaoApiManager;
	@Resource
	private ReceiveReqResManager receiveReqResManager;
	@Resource
	private Push2ChannelManager push2ChannelManager;
	@Resource
	private AdsMessageDao adsMessageDao;
	@Resource
	private HotelDao hotelDao;
	private HashMap<String, HashSet<String>> channelRatePlanMap;
	private HashMap<String, HashSet<String>> ratePlanRoomTypeMap;
	@Resource
	private ChannelHotelDao channelHotelDao;
	@Resource
	private ChannelDao channelDao;
	@Resource
	private RoomMsgDaoMongo roomMsgDaoMongo;
	
	@Resource
	private Push2ChannelStaticMsgManager push2ChannelStaticMsgManager;
	
	@Resource
	private ChannelHotelConfigDaoMongo channelHotelConfigDao;
	
	@Autowired
	public void setChannelgoodsDao(ChannelgoodsDao channelgoodsDao) {
		this.channelgoodsDao = channelgoodsDao;
		this.dao = channelgoodsDao;
	}

	@Override
	public ChannelGoods getChannelgoodsbyGid(String gid) {
		return channelgoodsDao.getChannelgoodsbyGid(gid);
	}

	/**
	 * 保存渠道绑定关系
	 */
	public void saveOrUpdateChannelGoods(ChannelGoods cg, List<String> rtList, List<String> channelIds) {

		if (rtList == null || rtList.isEmpty() || channelIds == null || channelIds.isEmpty()) {
			return;
		}

		List<ChannelGoods> cgList = new ArrayList<ChannelGoods>();
		String createdBy = SecurityHolder.getUserName();

		// 存在重复记录
		if (existsChannelGoods(cg, channelIds)) {
			throw new BizException("error");
		}

		// 更新,删除渠道绑定关系与渠道酒店关系，再添加
		if (StringUtils.hasText(cg.getChannelGoodsId())) {
			deleteChannelGoods(cg.getChannelGoodsId(), cg.getHotelId());
		}

		List<String> channelCodes = new ArrayList<String>();
		for (String channelId : channelIds) {

			String channelCode = channelManager.get(channelId).getChannelCode();
			channelCodes.add(channelCode);
			ChannelHotelConfig channelHotelConfig = channelHotelConfigDao.getChannelHotelConfigByChannelId(channelId);
			for (String roomTypeId : rtList) {
				ChannelGoods cgnew = new ChannelGoods();
				BeanUtils.copyProperties(cg, cgnew);
				cgnew.setRoomTypeId(roomTypeId);
				cgnew.setChannelGoodsId(CommonUtil.generatePrimaryKey());
				cgnew.setCreatedBy(createdBy);
				cgnew.setCreatedTime(new Date());
				cgnew.setChannelId(channelId);
				if(null!=channelHotelConfig){
					cgnew.setIsBind(channelHotelConfig.getAriSwitch());
				}
				cgList.add(cgnew);
			}

			// 保存渠道关系
			ChannelHotel ch = new ChannelHotel();
			ch.setChannelId(channelId);
			ch.setHotelId(cg.getHotelId());
			ch.setChannelHotelCode(channelCode);
			ch.setAddTime(cg.getEffectiveDate());
			ch.setDelTime(cg.getExpireDate());
			ch.setCreatedBy(createdBy);
			channelHotelManager.save(ch);
		}

		channelgoodsDao.saveChannelGoods(cgList);

		ChannelRateplanVO pc = new ChannelRateplanVO();
		pc.setChannelCodeList(channelCodes);

		HotelVO hvo = SecurityHolder.getB2BUser().getHotelvo();
		pc.setChainCode(hvo.getChainCode());
		pc.setHotelCode(hvo.getHotelCode());

		pc.setRoomTypeIdList(rtList);
		pc.setStartDate(DateUtil.getCleanDate(cg.getEffectiveDate()));
		pc.setEndDate(DateUtil.getCleanDate(cg.getExpireDate()));
		Rateplan rp = ratePlanManager.get(cg.getRateplanid());
		pc.setRatePlanCode(rp.getRatePlanCode());
		List<ChannelRateplanVO> crvoList = new ArrayList<ChannelRateplanVO>();
		crvoList.add(pc);
		log.info("binding updatePriceCalcByChannelRateplanVOList start!");
		priceCalcManager.updatePriceCalcByChannelRateplanVOList(crvoList, hvo.getHotelId(), ChannelGoods.HANDLETYPE_BINDING);
		log.info("binding updatePriceCalcByChannelRateplanVOList end!");
	}

	/**
	 * 保存渠道绑定关系
	 */
	@Override
	public void saveOrUpdateChannelGoodsForProfile(ChannelGoods cg, List<String> rtList, List<String> channelIds,String hotelId) {

		if (rtList == null || rtList.isEmpty() || channelIds == null || channelIds.isEmpty()) {
			return;
		}

		List<ChannelGoods> cgList = new ArrayList<ChannelGoods>();
		//String createdBy = SecurityHolder.getUserName();

		// 存在重复记录
		if (existsChannelGoods(cg, channelIds)) {
			throw new BizException("error");
		}

		// 更新,删除渠道绑定关系与渠道酒店关系，再添加
		if (StringUtils.hasText(cg.getChannelGoodsId())) {
			deleteChannelGoods(cg.getChannelGoodsId(), cg.getHotelId());
		}

		List<String> channelCodes = new ArrayList<String>();
		for (String channelId : channelIds) {

			String channelCode = channelManager.get(channelId).getChannelCode();
			channelCodes.add(channelCode);

			for (String roomTypeId : rtList) {
				ChannelGoods cgnew = new ChannelGoods();
				BeanUtils.copyProperties(cg, cgnew);
				cgnew.setRoomTypeId(roomTypeId);
				cgnew.setChannelGoodsId(CommonUtil.generatePrimaryKey());
				//cgnew.setCreatedBy(createdBy);
				cgnew.setCreatedTime(new Date());
				cgnew.setChannelId(channelId);
				cgList.add(cgnew);
			}

			// 保存渠道关系
			ChannelHotel ch = new ChannelHotel();
			ch.setChannelId(channelId);
			ch.setHotelId(cg.getHotelId());
			ch.setChannelHotelCode(channelCode);
			ch.setAddTime(cg.getEffectiveDate());
			ch.setDelTime(cg.getExpireDate());
			//ch.setCreatedBy(createdBy);
			channelHotelManager.save(ch);
		}

		channelgoodsDao.saveChannelGoods(cgList);

		ChannelRateplanVO pc = new ChannelRateplanVO();
		pc.setChannelCodeList(channelCodes);

//		HotelVO hvo = SecurityHolder.getB2BUser().getHotelvo();
//		pc.setChainCode(hvo.getChainCode());
//		pc.setHotelCode(hvo.getHotelCode());

		pc.setRoomTypeIdList(rtList);
		pc.setStartDate(DateUtil.getCleanDate(cg.getEffectiveDate()));
		pc.setEndDate(DateUtil.getCleanDate(cg.getExpireDate()));
		Rateplan rp = ratePlanManager.get(cg.getRateplanid());
		pc.setRatePlanCode(rp.getRatePlanCode());
		List<ChannelRateplanVO> crvoList = new ArrayList<ChannelRateplanVO>();
		crvoList.add(pc);
		log.info("binding updatePriceCalcByChannelRateplanVOList start!");
		priceCalcManager.updatePriceCalcByChannelRateplanVOList(crvoList, hotelId, ChannelGoods.HANDLETYPE_BINDING);
		log.info("binding updatePriceCalcByChannelRateplanVOList end!");
	}

	/**
	 * 开通或关闭渠道绑定关系
	 */
	public void changeChannelGoodsStatus(ChannelGoods cg, String language) {
		log.info("onOff:" + cg.getHotelId() + cg.getChannelGoodsId() + cg.getStatus() + cg.getUpdatedBy());
		ChannelGoods cgdb = channelgoodsDao.get(cg.getChannelGoodsId());
		cgdb.setHotelId(cg.getHotelId());

		List<ChannelGoods> cgList = channelgoodsDao.getChannelGoodsByHotelIdChannelIdRatePlanId(cgdb);
		if (cgList == null || cgList.isEmpty()) {
			return;
		}
		for (ChannelGoods cgs : cgList) {
			cgs.setStatus(cg.getStatus());
			cgs.setUpdatedBy(cg.getUpdatedBy());
			cgs.setLastModifyTime(new Date());
			channelgoodsDao.save(cgs);
		}
		// 绑定关系关闭时推房价内容到渠道
		if (ChannelGoodsStatus.OFF.equalsIgnoreCase(cg.getStatus())) {
			Channel c = channelManager.get(cgdb.getChannelId());
			if (c == null) {
				return;
			}
			ChannelGoodsVO cgvo = new ChannelGoodsVO();
			cgvo.setHotelId(cg.getHotelId());
			cgvo.setRatePlanId(cgdb.getRateplanid());
			cgvo.setStatus(cg.getStatus());
			cgvo.setPushRateContent(c.getPushRateContent());
			cgvo.setPushRateUrl(c.getPushRateUrl());
			cgvo.setChannelCode(c.getChannelCode());
			cgvo.setChannelId(c.getChannelId());
			cgvo.setIsChannelPushUrl(c.getIsChannelPushUrl());
			cgvo.setIsOTA(c.getIsOTA());
			cgvo.setPushHotel(c.getPushHotel());
			cgvo.setPushRoom(c.getPushRoom());
			push2ChannelStaticMsgManager.pushStaticMsgOff(cgvo, language);
			
			// 关闭渠道最近3月内产品
			if (c.getIsPushClose() != null && c.getIsPushClose()) {
				log.info("channelGoodsManager.changeChannelGoodsStatus->" + AdsMessage.ADSTYPE_AVAILUPDATE);
				HotelVO hvo = SecurityHolder.getB2BUser().getHotelvo();
				ChannelGoods channelGood = cgList.get(0);
				Rateplan rp = ratePlanManager.get(channelGood.getRateplanid());
				if (rp != null) {
					String rateplanCode = rp.getRatePlanCode();

					Set<String> rtSet = new HashSet<String>();
					for (ChannelGoods cgs : cgList) {
						RoomType rt = roomTypeDao.get(cgs.getRoomTypeId());
						if (rt != null)
							rtSet.add(rt.getRoomTypeCode());
					}
					List<RoomMsg> rmList = new ArrayList<RoomMsg>();
					int pushDay = 0;
					if (c.getMaxPushDay() != null) {
						pushDay = c.getMaxPushDay() < 89 ? c.getMaxPushDay() : 89;
					}

					List<String> dateList = DateUtil.getDays(new Date(), DateUtil.addDays(new Date(), pushDay));
					for (String rtCode : rtSet) {
						for (String date : dateList) {
							RoomMsg rm = new RoomMsg();
							rm.setOnOff(Boolean.FALSE);
							rm.setChannelCode(c.getChannelCode());
							rm.setChainCode(hvo.getChainCode());
							rm.setHotelCode(hvo.getHotelCode());
							rm.setRoomTypeCode(rtCode);
							rm.setRateCode(rateplanCode);
							rm.setStartDate(date);
							rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
							rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
							rm.setCreatedTime(new Date());
							rmList.add(rm);
						}
					}
					roomMsgDaoMongo.batchSave(rmList);
				}
			}
		}

	}

	/**
	 * 逻辑删除绑定关系
	 * 
	 * @param channelgoodsId
	 */
	public void deleteChannelGoods(String channelgoodsId, String hotelId) {
		try {
			// 删除渠道绑定关系
			ChannelGoods cgs = channelgoodsDao.get(channelgoodsId);
			cgs.setUpdatedBy(SecurityHolder.getUserId());
			cgs.setLastModifyTime(new Date());
			cgs.setDelFlag(true);
			channelgoodsDao.deleteChannelGoods(cgs);
			// 删除渠道酒店关系
			channelHotelManager.logicDeleteChannelHotel(hotelId, cgs.getChannelId(), cgs.getEffectiveDate(), cgs.getExpireDate());

			/** 判定ob配额逻辑 */
			ChannelGoodsVO cgvo = new ChannelGoodsVO();
			cgvo.setChannelId(cgs.getChannelId());
			cgvo.setHotelId(hotelId);
			List<ChannelGoodsVO> crpList = getEnabledChannelGoods(cgvo);
			if (CommonUtil.isEmpty(crpList)) {
				// 从ob配额中删除该渠道
				ChannelHotel ch = new ChannelHotel();
				ch.setChannelId(cgvo.getChannelId());
				ch.setHotelId(hotelId);
				List<ChannelHotel> chList = channelHotelDao.getChannelHotelByHotelIdChannelId(ch);
				for (ChannelHotel channelHotel : chList) {
					if (CommonUtil.isNotEmpty(channelHotel.getGroupId())) {
						channelHotelDao.deleteCHByGroupIdAndHotelId(channelHotel.getGroupId(), hotelId);
						log.warn(" ob配额 channelId:" + cgvo.getChannelId() + "已删除");
					}
				}
			}
		} catch (Exception e) {
			log.info(e);
		}
	}

	/**
	 * 根据酒店发布绑定关系
	 */
	public void publishChannelGoods(String hotelId, String language) {
		try {
			// 查询绑定关系
			List<ChannelGoodsVO> result = channelgoodsDao.searchChannelGoods(hotelId);
			if (result == null || result.isEmpty()) {
				return;
			}
			// 更新发布状态
			updateChannelGoodsStatus(hotelId, ChannelGoodsStatus.publish);
			
			push2ChannelManager.pushRateContent(language, result);
			
			
			push2ChannelStaticMsgManager.pushAllStaticMsg(hotelId, null, true, language);
			
			
		} catch (Exception e) {
			log.info(e);
			throw new BizException("更新房价日历失败");
		}
	}

	/**
	 * 根据酒店发布绑定关系
	 */
	@Override
	public void publishChannelGoodsForProfile(String hotelId, String language,String ratePlanId,String channelId) {
		try {
			// 查询绑定关系
			List<ChannelGoodsVO> result = channelgoodsDao.searchChannelGoodsByRatePlanId(hotelId,ratePlanId,channelId);
			if (result == null || result.isEmpty()) {
				return;
			}
			// 更新发布状态
			updateChannelGoodsStatusForProfile(hotelId, ChannelGoodsStatus.publish,ratePlanId);
			push2ChannelManager.pushRateContent(language, result);
		} catch (Exception e) {
			log.info(e);
			throw new BizException("更新房价日历失败");
		}
	}
	
	/**
	 * 批量发布绑定关系与房价日历
	 */
	public boolean publishChannelGoodsBatch(String hotelId) {

		// 更新房价日历
		List<ChannelGoodsVO> result = channelgoodsDao.searchChannelGoods(hotelId);
		if (result == null || result.isEmpty()) {
			return false;
		}
		List<ChannelRateplanVO> crvoList = new ArrayList<ChannelRateplanVO>();
		for (ChannelGoodsVO cgvo : result) {

			if (!ChannelGoodsStatus.DEFAULT.equals(cgvo.getStatus()) && !ChannelGoodsStatus.fail.equals(cgvo.getStatus())) {
				continue;
			}

			List<ChannelGoods> cgList = getChannelGoodsByHotelIdChannelIdRatePlanId(hotelId, cgvo.getChannelId(), cgvo.getRatePlanId(),null,null);
			
			if (cgList == null || cgList.isEmpty()) {
				continue;
			}
			for (ChannelGoods cg : cgList) {
				cgvo.getRoomTypeIds().add(cg.getRoomTypeId());
			}

			ChannelRateplanVO pc = new ChannelRateplanVO();
			List<String> channelCodes = new ArrayList<String>();
			channelCodes.add(cgvo.getChannelCode());
			pc.setChannelCodeList(channelCodes);
			pc.setRoomTypeIdList(cgvo.getRoomTypeIds());
			pc.setStartDate(DateUtil.getCleanDate(cgvo.getEffectiveDate()));
			pc.setEndDate(DateUtil.getCleanDate(cgvo.getExpireDate()));
			pc.setRatePlanCode(cgvo.getRatePlanCode());
			crvoList.add(pc);
		}

		try {
			// 更新发布状态
			updateChannelGoodsStatus(hotelId, ChannelGoodsStatus.process);
			// push2ChannelManager.pushRateContent(LanguageCode.MAIN_LANGUAGE_CODE,
			// result);
			log.info("publish updatePriceCalcByChannelRateplanVOList start!" + hotelId);
			priceCalcManager.updatePriceCalcByChannelRateplanVOList(crvoList, hotelId, ChannelGoods.HANDLETYPE_PUBLISH);
			log.info("publish updatePriceCalcByChannelRateplanVOList end!");
			return true;
		} catch (Exception e) {
			log.info(e);
			throw new BizException("更新房价日历失败");
		}
	}

	public void updateChannelGoodsStatus(String hotelId, String status) {
		ChannelGoodsVO cgv = new ChannelGoodsVO();
		cgv.setHotelId(hotelId);
		cgv.setStatus(status);
		cgv.setUpdatedBy(SecurityHolder.getUserId());
		cgv.setLastModifyTime(new Date());
		log.info("changeChannelGoodsStatus" + status);
		if (ChannelGoodsStatus.process.equals(status)) {
			cgv.setSrcStatus(ChannelGoodsStatus.DEFAULT);
			channelgoodsDao.publishChannelGoods(cgv);
			cgv.setSrcStatus(ChannelGoodsStatus.fail);
			channelgoodsDao.publishChannelGoods(cgv);
		} else if (ChannelGoodsStatus.publish.equals(status)) {
			cgv.setSrcStatus(ChannelGoodsStatus.DEFAULT);
			channelgoodsDao.publishChannelGoods(cgv);
			cgv.setSrcStatus(ChannelGoodsStatus.process);
			channelgoodsDao.publishChannelGoods(cgv);
			cgv.setSrcStatus(ChannelGoodsStatus.fail);
			channelgoodsDao.publishChannelGoods(cgv);
		} else if (ChannelGoodsStatus.fail.equals(status)) {
			cgv.setSrcStatus(ChannelGoodsStatus.DEFAULT);
			channelgoodsDao.publishChannelGoods(cgv);
			cgv.setSrcStatus(ChannelGoodsStatus.process);
			channelgoodsDao.publishChannelGoods(cgv);
		}
	}
   
	public void updateChannelGoodsStatusForProfile(String hotelId, String status,String ratePlanId) {
		ChannelGoodsVO cgv = new ChannelGoodsVO();
		cgv.setHotelId(hotelId);
		cgv.setStatus(status);
		cgv.setRatePlanId(ratePlanId);
		cgv.setLastModifyTime(new Date());
		log.info("changeChannelGoodsStatus" + status);
		if (ChannelGoodsStatus.process.equals(status)) {
			cgv.setSrcStatus(ChannelGoodsStatus.DEFAULT);
			channelgoodsDao.publishChannelGoodsByRatePlanId(cgv);
			cgv.setSrcStatus(ChannelGoodsStatus.fail);
			channelgoodsDao.publishChannelGoodsByRatePlanId(cgv);
		} else if (ChannelGoodsStatus.publish.equals(status)) {
			cgv.setSrcStatus(ChannelGoodsStatus.DEFAULT);
			channelgoodsDao.publishChannelGoodsByRatePlanId(cgv);
			cgv.setSrcStatus(ChannelGoodsStatus.process);
			channelgoodsDao.publishChannelGoodsByRatePlanId(cgv);
			cgv.setSrcStatus(ChannelGoodsStatus.fail);
			channelgoodsDao.publishChannelGoodsByRatePlanId(cgv);
		} else if (ChannelGoodsStatus.fail.equals(status)) {
			cgv.setSrcStatus(ChannelGoodsStatus.DEFAULT);
			channelgoodsDao.publishChannelGoodsByRatePlanId(cgv);
			cgv.setSrcStatus(ChannelGoodsStatus.process);
			channelgoodsDao.publishChannelGoodsByRatePlanId(cgv);
		}
	}
	
	/**
	 * 根据酒店ID或(房价代码,房型代码,渠道IDS)查询渠道代码,房价ID,房价代码,房价描述(房价代码不重复)
	 */
	public List<ChannelGoodsVO> getChannelRatePlanByChannelGoods(ChannelGoodsVO cgvo) {
		Map<String, String> map = new HashMap<String, String>();
		List<ChannelGoodsVO> ChannelGoodsVOList = new ArrayList<ChannelGoodsVO>();
		List<ChannelGoodsVO> list = getChannelGoodsVORoomTypeByChannelGoods(cgvo);
		for (ChannelGoodsVO crp : list) {
			if (map.get(crp.getRatePlanCode()) == null) {
				map.put(crp.getRatePlanCode(), crp.getHotelId());
				ChannelGoodsVOList.add(crp);
			}
		}
		return ChannelGoodsVOList;
	}

	/**
	 * 根据酒店ID或(渠道IDS,房价IDS)查询房型ID,房型代码,房价名称(房型代码不重复)
	 */
	public List<ChannelGoodsVO> getChannelRoomTypeByChannelGoods(ChannelGoodsVO cgvo) {
		Map<String, String> map = new HashMap<String, String>();
		List<ChannelGoodsVO> ChannelGoodsVOList = new ArrayList<ChannelGoodsVO>();
		List<ChannelGoodsVO> list = getChannelGoodsVORoomTypeByChannelGoods(cgvo);
		for (ChannelGoodsVO crp : list) {
			if (map.get(crp.getRoomTypeCode()) == null) {
				map.put(crp.getRoomTypeCode(), crp.getHotelId());
				ChannelGoodsVOList.add(crp);
			}
		}
		return ChannelGoodsVOList;
	}

	/**
	 * 根据酒店ID或(房价代码,房型代码,渠道代码,生效与失效时间)查询酒店ID,房型代码,房价ID,房价代码,生效时间,失效时间,渠道档案ID,
	 * 验证房价类型..已发布的绑定关系
	 */
	public List<ChannelGoodsVO> getEnabledChannelGoods(ChannelGoodsVO cgvo) {

		// 判断渠道绑定关系是否已发布和在有效时间
		cgvo.setStatus(ChannelGoodsStatus.publish);// 查询已发布的关系
		return getEnabledChannelgoods(cgvo);
	}

	public List<ChannelGoodsVO> getEnabledChannelGoods2(ChannelGoodsVO cgvo) {
		// 判断渠道绑定关系是否已发布和在有效时间
		cgvo.setCreatedTime(new Date());
		return channelgoodsDao.getChannelCodeByChannelGoods2(cgvo);
	}

	/**
	 * 根据酒店ID或(房价代码,房型代码,渠道代码,生效与失效时间)查询酒店ID,房型代码,房价ID,房价代码,生效时间,失效时间,渠道档案ID,
	 * 验证房价类型..未发布与已发布的绑定关系
	 */
	public List<ChannelGoodsVO> getEnabledUnOrPublishChannelGoods(ChannelGoodsVO cgvo) {
		// 判断渠道绑定关系是否(未发布或已发布)和在有效时间
		List<String> statusList = new ArrayList<String>();
		statusList.add(ChannelGoodsStatus.DEFAULT);
		statusList.add(ChannelGoodsStatus.publish);
		cgvo.setStatusList(statusList);
		return getEnabledChannelgoods(cgvo);
	}

	/**
	 * 根据酒店，渠道，房价ID查询绑定关系
	 */
	public List<ChannelGoods> getChannelGoodsByHotelIdChannelIdRatePlanId(String hotelId, String channelId, String ratePlanId,Boolean isBind,Date  effectiveDate) {
		ChannelGoods cg = new ChannelGoods();
		cg.setHotelId(hotelId);
		cg.setRateplanid(ratePlanId);
		cg.setChannelId(channelId);
		cg.setIsBind(isBind);
		cg.setEffectiveDate(effectiveDate);
		return channelgoodsDao.getChannelGoodsByHotelIdChannelIdRatePlanId(cg);
	}

	/**
	 * 查询某酒店下的渠道绑定房价列表
	 */
	public List<ChannelGoodsVO> searchChannelGoods(String hotelId, String language) {
		List<ChannelGoodsVO> result = channelgoodsDao.getChannelgoodsByBind(hotelId);
		result = this.packChannelGoodsVo(result, hotelId, language);
		result = getEnabledChannelGoodsVO(result);
		return result;
	}

	/**
	 * 查询某酒店和渠道下的渠道绑定房价列表
	 */
	public List<ChannelGoodsVO> searchChannelgoodsByChannelHotel(ChannelGoodsVO cgvo) {
		List<ChannelGoodsVO> result = channelgoodsDao.searchChannelgoodsByChannelHotel(cgvo);
		result = this.packChannelGoodsVo(result, cgvo.getHotelId(), cgvo.getLanguage());
		return result;
	}

	/**
	 * 获取渠道商品VO列表后设置值
	 * 
	 * @param result
	 * @param hotelId
	 * @return
	 */
	public List<ChannelGoodsVO> packChannelGoodsVo(List<ChannelGoodsVO> result, String hotelId, String language) {
		SecurityHolder.getSession().setAttribute("channelGoods", false);
		SecurityHolder.getSession().setAttribute("enableOper", true);
		if (result == null || result.isEmpty()) {
			return null;
		}
		for (ChannelGoodsVO cgvo : result) {
			List<ChannelGoods> cgList = getChannelGoodsByHotelIdChannelIdRatePlanId(hotelId, cgvo.getChannelId(), cgvo.getRatePlanId(),cgvo.getIsBind(),cgvo.getEffectiveDate());
			if (cgList == null || cgList.isEmpty()) {
				continue;
			}

			// 获取房型代码
			List<String> roomTypeIds = new ArrayList<String>();
			for (ChannelGoods cg : cgList) {
				roomTypeIds.add(cg.getRoomTypeId());
			}
			cgvo.setRoomTypeVoList(roomTypeDao.getRoomTypeInfosByIds(roomTypeIds, language));

			if (ChannelGoodsStatus.DEFAULT.equals(cgvo.getStatus()) || ChannelGoodsStatus.fail.equals(cgvo.getStatus())) {
				SecurityHolder.getSession().setAttribute("channelGoods", true);
			}
			// 用于限制绑定关系正在发布中时，不允许操作其它操作
			else if (ChannelGoodsStatus.process.equals(cgvo.getStatus())) {
				SecurityHolder.getSession().setAttribute("enableOper", false);
			}

			// 计算同步与不同步状态
			if (ChannelGoodsStatus.publish.equals(cgvo.getStatus())) {
				// 在有效日期范围内
				if (!new Date().before(cgvo.getEffectiveDate()) && (cgvo.getExpireDate() == null || (cgvo.getExpireDate() != null && !new Date().after(cgvo.getExpireDate())))) {
					cgvo.setStatus(ChannelGoodsStatus.synch);
				} else {
					cgvo.setStatus(ChannelGoodsStatus.asynch);
				}
			}

		}
		return result;
	}

	/**
	 * 根据房价定义查询房型
	 */
	public List<RoomTypeVO> getRoomTypeByRatePlanId(String ratePlanId, String language) {
		return roomTypeDao.getRoomTypeVOByRatePlanIdLang(ratePlanId, language);
	}

	/**
	 * 根据条件判断绑定关系是否存在
	 */
	public boolean existsChannelGoods(ChannelGoods cg, List<String> channelIds) {
		if (channelIds == null) {
			return false;
		}

		ChannelGoodsVO cgvo = new ChannelGoodsVO();
		cgvo.setChannelIds(channelIds);
		cgvo.setRatePlanId(cg.getRateplanid());
		cgvo.setEffectiveDate(cg.getEffectiveDate());
		cgvo.setExpireDate(cg.getExpireDate());

		List<String> result = channelgoodsDao.existsChannelGoods(cgvo);

		if (result != null && !result.isEmpty()) {
			if (StringUtils.hasText(cg.getChannelGoodsId()) && result.contains(cg.getChannelGoodsId())) {
				for (String cgId : result) {
					if (!cgId.equals(cg.getChannelGoodsId())) {
						return true;
					}
				}
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 根据酒店ID或(房价代码,房价IDS,房型代码,渠道代码,渠道IDS,生效与失效时间,状态)查询
	 * 渠道代码,房价ID,房价代码,房价描述,房型ID,房型代码,房型名称,酒店ID,生效时间,失效时间,渠道档案ID,验证房价类型
	 */
	private List<ChannelGoodsVO> getChannelGoodsVORoomTypeByChannelGoods(ChannelGoodsVO cgvo) {

		String hotelId = cgvo.getHotelId();

		// 针对登录用户查询
		if (!StringUtils.hasText(hotelId)) {
			B2BUser user = SecurityHolder.getB2BUser();
			if (user != null) {
				hotelId = user.getHotelvo().getHotelId();
				cgvo.setHotelId(hotelId);
			}
		}

		return channelgoodsDao.getChannelGoodsVORoomTypeByChannelGoods(cgvo);

	}

	/**
	 * 根据条件查询宝贝
	 */
	public List<GoodsVO> searchGoodsByCreteria(GoodsVO gvo) {
		return channelgoodsDao.searchGoodsByCreteria(gvo);
	}

	/**
	 * 根据主键查询宝贝
	 */
	// public GoodsVO getGoodsByChanelGoodsId(String channelGoodsId) {
	// return channelgoodsDao.getGoodsByChanelGoodsId(channelGoodsId);
	// }

	public String checkGoodsName(String goodsName) {
		return channelgoodsDao.checkGoodsName(goodsName);
	}

	@Override
	public GoodsVO getGoodsVOById(String channelGoodsId) {
		if (CommonUtil.isNotEmpty(channelGoodsId)) {
			GoodsVO goodsVO = new GoodsVO();
			goodsVO.setChannelGoodsId(channelGoodsId);
			goodsVO.setType("");
			goodsVO.setInterfaceId("");
			B2BUser user = SecurityHolder.getB2BUser();
			if (user.checkIsAdmin()) {
				goodsVO.setCreatedBy(user.getUserId());
			} else {
				List<String> hotelIdList = userManager.getHotelIdListByUserId(user.getUserId());
				goodsVO.setHotelIdList(hotelIdList);
			}
			List<GoodsVO> goodsVOList = searchGoodsByCreteria(goodsVO);
			if (CommonUtil.isNotEmpty(goodsVOList) && goodsVOList.size() > 0) {
				return goodsVOList.get(0);
			}
		}
		return null;
	}

	@Override
	public void saveOrUpdateChannelGoods(ChannelGoods cg) {
		// 添加时修改用户状态
		if (!StringUtils.hasText(cg.getChannelGoodsId())) {
			userManager.updateStatus(UserInitStatus.ProductCreated);
			cg.setTbSyncStatus(ChannelGoods.TBSYNCSTATUS_SYNC);
		}

		cg.setChannelGoodsCode(getChannelGoodsCodeByRTid(cg.getRoomTypeId()));
		cg = save(cg);
	}

	@Override
	public String getChannelGoodsCodeByRTid(String roomTypeId) {
		ChannelGoods cg = channelgoodsDao.getChannelgoodsByChannelIdAndRoomTypeId(channelManager.getChannelByChannelCode(ChannelCodeEnum.TAOBAO.getName()).getChannelId(), roomTypeId);
		if (CommonUtil.isNotEmpty(cg)) {
			return cg.getChannelGoodsCode();
		}
		return null;
	}

	/*** 获取到淘宝房量房价map **/
	@Override
	public Map<String, Object> getInventoryPrice(List<Map<String, Object>> inventoryList) {
		Map<String, Object> inventoryMap = new HashMap<String, Object>();
		List<inventoryPrice> inventoryPriceList = new ArrayList<inventoryPrice>();

		if (CommonUtil.isNotEmpty(inventoryList)) {
			for (Map<String, Object> dayMap : inventoryList) {
				String date = dayMap.get("date") + "";
				inventoryPrice ip = new inventoryPrice();
				ip.date = date;
				Object amountObj = dayMap.get("amount");
				if (amountObj != null) {
					BigDecimal amount = new BigDecimal(amountObj.toString());
					Object onOff = dayMap.get("onOff");
					if (onOff == null || onOff.toString().equals("true")) {
						if (amount.floatValue() > 0) {
							ip.price = (int) (amount.floatValue() * 100);
							if (dayMap.get("physicalRooms") != null) {
								ip.quota = Integer.parseInt(dayMap.get("physicalRooms") + "");
							} else {
								ip.quota = 0;
							}
							inventoryPriceList.add(ip);
						}
					}
				}
			}
		}

		// List<String> dayList = DateUtil.getDays(new Date(),
		// DateUtil.addDays(new Date(), 89));
		// for (String day : dayList) {
		// inventoryPrice ip = new inventoryPrice();
		// ip.date = day;
		// ip.price = 300;
		// ip.quota = 10;
		//
		// if(CommonUtil.isNotEmpty(inventoryList)){
		// for (Map<String,Object> dayMap : inventoryList) {
		// String date = dayMap.get("date")+"";
		// if(day.equals(date)){
		// BigDecimal amount = new BigDecimal(dayMap.get("amount")+"");
		// Object onOff = dayMap.get("onOff");
		// if(onOff == null || onOff.toString().equals("true")){
		// if( amount.floatValue() > 0){
		// ip.price = ((int) (amount.floatValue() * 100));
		// }
		// if(dayMap.get("physicalRooms") != null){
		// ip.quota = Integer.parseInt(dayMap.get("physicalRooms")+"");
		// }
		// }
		// break;
		// }
		// }
		// }
		// inventoryPriceList.add(ip);
		// }

		System.out.println("size:" + inventoryPriceList.size());
		inventoryMap.put("use_room_inventory", false);
		inventoryMap.put("inventory_price", inventoryPriceList);
		return inventoryMap;
	}

	/** 有效返回true **/
	@Override
	public boolean checkGoods(ChannelGoods cg) {
		GoodsVO goodsVO = new GoodsVO();
		goodsVO.setRoomTypeId(cg.getRoomTypeId());
		goodsVO.setRateplanid(cg.getRateplanid());
		B2BUser user = SecurityHolder.getB2BUser();
		if (user.checkIsAdmin()) {
			goodsVO.setCreatedBy(user.getUserId());
		} else {
			List<String> hotelIdList = userManager.getHotelIdListByUserId(user.getUserId());
			goodsVO.setHotelIdList(hotelIdList);
		}
		List<GoodsVO> goodsVOList = searchGoodsByCreteria(goodsVO);
		if (CommonUtil.isNotEmpty(goodsVOList)) {
			GoodsVO gsvo = goodsVOList.get(0);
			if (!gsvo.getChannelGoodsId().equals(cg.getChannelGoodsId())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<ChannelGoodsVO> searchRateplansByChannelGoods(ChannelGoodsVO channelGoodsVO) {
		return channelgoodsDao.searchRateplansByChannelGoods(channelGoodsVO);
	}

	@Override
	public List<ChannelGoodsVO> searchChannelgoodsByChannelHotelGroupByRoomType(ChannelGoodsVO cgvo) {
		return channelgoodsDao.searchChannelgoodsByChannelHotelGroupByRoomType(cgvo);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean stopSale(List<String> cgs) throws Exception {
		boolean isSuccess = false;
		if (cgs != null) {
			List rateList = new ArrayList();
			for (String cg : cgs) {
				String[] gr = cg.split("-");
				HashMap<String, Object> rateMap = new HashMap<String, Object>();
				rateMap.put("gid", gr[0]);
				rateMap.put("rp_id", gr[1]);
				Map<String, Object> inventoryMap = new HashMap<String, Object>();
				inventoryMap.put("use_room_inventory", false);
				inventoryMap.put("inventory_price", new ArrayList());
				rateMap.put("data", inventoryMap);
				rateList.add(rateMap);
			}
			String rate_inventory_price_map = JSON.toJSONString(rateList);
			InvokeResponse res = taobaoApiManager.ratesUpdate(rate_inventory_price_map);
			if (res.getResObj() != null) {
				List<String> grList = (List<String>) res.getResObj();
				if (grList.size() == cgs.size()) {
					isSuccess = true;
					log.info("下架成功：" + CommonUtil.join(grList, ","));
				}
			} else {
				res.setErrMsg("下架失败 :" + res.getErrMsg());
				log.error(res.getErrMsg());
			}
		}
		return isSuccess;
	}

	@Override
	public GoodsVO getGoodsByChanelGoodsId(String channelGoodsId) {
		return channelgoodsDao.getGoodsByChanelGoodsId(channelGoodsId);
	}

	@Override
	public GoodsVO getGoodsByChanelGoodsId(String channelGoodsId, String language) {
		return channelgoodsDao.getGoodsByChanelGoodsId(channelGoodsId, language);
	}

	/** 获取该酒店可用渠道，房价，房型code **/
	@Override
	public HashMap<String, String> getEnabledChannelCodeMap() {
		channelRatePlanMap = new HashMap<String, HashSet<String>>();
		ratePlanRoomTypeMap = new HashMap<String, HashSet<String>>();

		HotelVO hotelvo = SecurityHolder.getB2BUser().getHotelvo();
		ChannelGoodsVO channelGoodsVO = new ChannelGoodsVO();
		channelGoodsVO.setHotelId(hotelvo.getHotelId());

		List<ChannelGoodsVO> cgvoList = getEnabledChannelGoods(channelGoodsVO);
		HashMap<String, String> channelMap = new HashMap<String, String>(); // 包含最大推送日期
		if (cgvoList != null) {
			for (ChannelGoodsVO cgvo : cgvoList) {
				String channelCode = cgvo.getChannelCode();
				String ratePlanCode = cgvo.getRatePlanCode();
				String rtCode = cgvo.getRoomTypeCode();

				if (!channelMap.containsKey(channelCode)) {
					Channel c = channelDao.getChannelByChannelCode(channelCode);
					Integer maxPushDay = c.getMaxPushDay();
					if (c != null && maxPushDay != null && maxPushDay > 0) {
						Date pushDate = DateUtil.addDays(DateUtil.currentDate(), maxPushDay);
						channelMap.put(channelCode, DateUtil.convertDateToString(pushDate));
					}
				}

				if (channelRatePlanMap.containsKey(channelCode)) {
					HashSet<String> rateSet = channelRatePlanMap.get(channelCode);
					rateSet.add(ratePlanCode);
				} else {
					HashSet<String> rateSet = new HashSet<String>();
					rateSet.add(ratePlanCode);
					channelRatePlanMap.put(channelCode, rateSet);
				}

				if (ratePlanRoomTypeMap.containsKey(channelCode + ratePlanCode)) {
					HashSet<String> rtSet = ratePlanRoomTypeMap.get(channelCode + ratePlanCode);
					rtSet.add(rtCode);
				} else {
					HashSet<String> rtSet = new HashSet<String>();
					rtSet.add(rtCode);
					ratePlanRoomTypeMap.put(channelCode + ratePlanCode, rtSet);
				}
			}
		}

		
		B2BUser user = SecurityHolder.getB2BUser();
		List<Channel> channelList = user.getChannels();
		
		if(!channelList.isEmpty()){
			Iterator<Entry<String, String>> it = channelMap.entrySet().iterator();
			HashMap<String, String> channelMap_ = new HashMap<String, String>(); // 包含最大推送日期
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				String key = entry.getKey();
				String value = entry.getValue();
				for(Channel c :channelList){
					if(c.getChannelCode().equalsIgnoreCase(key)){
						channelMap_.put(key, value);
					}
				}
			}
			return channelMap_;
		}
		
		return channelMap;
	}

	@Override
	public HashMap<String, HashSet<String>> getChannelRatePlanMap() {
		return channelRatePlanMap;
	}

	@Override
	public HashMap<String, HashSet<String>> getRatePlanRoomTypeMap() {
		return ratePlanRoomTypeMap;
	}

	private List<ChannelGoodsVO> getEnabledChannelgoods(ChannelGoodsVO cgvo) {
		cgvo.setCreatedTime(new Date());
		// expireDate传入的是日期
		List<ChannelGoodsVO> result = getChannelGoodsVORoomTypeByChannelGoods(cgvo);
		if (result == null || result.isEmpty()) {
			return null;
		}

		return result;
	}

	@Override
	public List<ChannelGoodsVO> getChannelGoodsVORoomTypeListByChannelGoods(
			ChannelGoodsVO cgvo) {
		return channelgoodsDao.getChannelGoodsVORoomTypeByChannelGoods2(cgvo);
	}

	@Override
	public List<ChannelGoodsVO> getEnabledChannelGoodsVO(List<ChannelGoodsVO> list) {
		if(list==null){
			return null;
		}
		B2BUser user = SecurityHolder.getB2BUser();
		if(user.getCompanyId().equals(CompanyType.CHANNEL)){
			List<Channel> channelList = user.getChannels();
			List<ChannelGoodsVO> newList = new ArrayList<ChannelGoodsVO>();
			if(CommonUtil.isEmpty(channelList)){
				return newList;
			}
			
			for(ChannelGoodsVO channelGoodsVO : list){
				for(Channel ableCHannel:channelList){
					if(channelGoodsVO.getChannelId().equalsIgnoreCase(ableCHannel.getChannelId())){//list 中的ChannelGoodsVO 在权限当中
						newList.add(channelGoodsVO);
					}
				}
			}
			
			list = newList ;
		}
		return list;
	}
}

class inventoryPrice {
	public String date;
	public Integer quota;
	public Integer price;
}

