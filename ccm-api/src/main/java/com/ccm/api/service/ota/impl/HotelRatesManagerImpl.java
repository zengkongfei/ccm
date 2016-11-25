/**
 * 
 */
package com.ccm.api.service.ota.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opentravel.ota._2003._05.AvailabilityStatusType;
import org.opentravel.ota._2003._05.ErrorType;
import org.opentravel.ota._2003._05.ErrorsType;
import org.opentravel.ota._2003._05.GuestRoomType;
import org.opentravel.ota._2003._05.GuestRoomType.Room;
import org.opentravel.ota._2003._05.HotelRatePlanType;
import org.opentravel.ota._2003._05.HotelRatePlanType.Rates.Rate;
import org.opentravel.ota._2003._05.OTAHotelRatePlanRQ;
import org.opentravel.ota._2003._05.OTAHotelRatePlanRQ.RatePlans.RatePlan.HotelRef;
import org.opentravel.ota._2003._05.OTAHotelRatePlanRS;
import org.opentravel.ota._2003._05.RateUploadType;
import org.opentravel.ota._2003._05.RateUploadType.BaseByGuestAmts.BaseByGuestAmt;
import org.opentravel.ota._2003._05.SellableProductsType;
import org.opentravel.ota._2003._05.SellableProductsType.SellableProduct;
import org.opentravel.ota._2003._05.SourceType.RequestorID;
import org.opentravel.ota._2003._05.SuccessType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ccm.api.common.exception.BizException;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.ratePlan.PriceCalcDao;
import com.ccm.api.dao.ratePlan.RestrictionCalcDao;
import com.ccm.api.dao.rsvtype.RoomQtyDao;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.constant.ChannelGoodsStatus;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.PriceCalc;
import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.model.ratePlan.RestrictionCalc;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.sell.vo.ProductionVO;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.ota.HotelRatesManager;
import com.ccm.api.service.ratePlan.RateAmountManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
@Service("hotelRatesManager")
public class HotelRatesManagerImpl implements HotelRatesManager {

	private Log log = LogFactory.getLog(HotelRatesManagerImpl.class);

	@Resource
	private RoomQtyDao roomQtyDao;
	@Resource
	private RestrictionCalcDao restrictionCalcDao;
	@Resource
	private PriceCalcDao priceCalcDao;
	@Resource
	private ChannelDao channelDao;

	@Resource
	private ChannelgoodsManager channelgoodsManager;
	@Resource
	private RsvtypeChannelManager rsvtypeChannelManager;
	@Resource
	private RoomTypeManager roomTypeManager;
	@Resource
	private HotelMCManager hotelMCManager;
	@Resource
	private RateAmountManager rateAmountManager;

	@SuppressWarnings("unchecked")
	public OTAHotelRatePlanRS buildHotelRatePlanRS(OTAHotelRatePlanRQ rq) {
		long startMili = System.currentTimeMillis();
		OTAHotelRatePlanRS oTAHotelRatePlanRS = new OTAHotelRatePlanRS();
		OTAHotelRatePlanRS.RatePlans ratePlans = new OTAHotelRatePlanRS.RatePlans();
		String msg = null;
		try {
			// 渠道代码
			RequestorID rid = rq.getPOS().getSource().get(0).getRequestorID();
			String channelCode = null;
			if (rid != null && "5".equals(rid.getType())) {
				channelCode = rid.getID();
			}
			// 集团与酒店代码
			HotelRef hotelRef = rq.getRatePlans().getRatePlan().get(0).getHotelRef();
			if (hotelRef == null) {
				msg = "HotelCodeIsNotEmpty";
				oTAHotelRatePlanRS = setResponseMsg(oTAHotelRatePlanRS, msg);
				log.info("buildHotelRatePlanRS-HotelCodeIsNotEmpty：" + (System.currentTimeMillis() - startMili) + "ms");
				return null;
			}
			String chainCode = hotelRef.getChainCode();
			String hotelCode = hotelRef.getHotelCode();

			Map<String, Object> roomMap = new HashMap<String, Object>();
			Map<String, Object> rateMap = queryHotelRatePlan(channelCode, chainCode, hotelCode, roomMap);
			// 房价，开关，房量或房态
			for (Map.Entry<String, Object> entry : rateMap.entrySet()) {
				String key = entry.getKey();
				HotelRatePlanType rp = new HotelRatePlanType();
				rp.setRatePlanCode(key);
				// Rates
				HotelRatePlanType.Rates rates = new HotelRatePlanType.Rates();
				Map<String, RoomMsg> value = (Map<String, RoomMsg>) entry.getValue();
				for (Map.Entry<String, RoomMsg> rateRoom : value.entrySet()) {
					Rate rate = new Rate();
					RoomMsg rm = rateRoom.getValue();
					rate.setInvCode(rm.getRoomTypeCode());
					rate.setStart(rm.getStartDate());
					rate.setEnd(rm.getStartDate());
					rate.setCurrencyCode(rm.getCurrencyCode());
					// 无房价时开关状态返回关
					String status = "Close";
					if (rm.getRateAmountList() != null) {
						RateUploadType.BaseByGuestAmts baseByGuestAmts = new RateUploadType.BaseByGuestAmts();
						for (RateAmount ra : rm.getRateAmountList()) {
							BaseByGuestAmt baseByGuestAmt = new BaseByGuestAmt();
							baseByGuestAmt.setAmountBeforeTax(ra.getBaseAmount());
							baseByGuestAmt.setAmountAfterTax(ra.getAmountAfterTax());
							baseByGuestAmt.setNumberOfGuests(ra.getNumberOfUnits());
							baseByGuestAmts.getBaseByGuestAmt().add(baseByGuestAmt);
						}
						rate.setBaseByGuestAmts(baseByGuestAmts);
						status = rm.getOnOff() == null || rm.getOnOff() ? "Open" : "Close";
					}
					rate.setStatus(AvailabilityStatusType.fromValue(status));
					rates.getRate().add(rate);
				}
				rp.setRates(rates);
				// SellableProducts
				if (roomMap.get(key) != null) {
					rp.setSellableProducts(dealSellabelProducts(roomMap, key));
					roomMap.remove(key);
				}
				ratePlans.getRatePlan().add(rp);
			}
			// SellableProducts,处理没有房价有房量或房态的情况
			for (Map.Entry<String, Object> entry : roomMap.entrySet()) {
				String key = entry.getKey();
				HotelRatePlanType rp = new HotelRatePlanType();
				rp.setRatePlanCode(key);
				rp.setSellableProducts(dealSellabelProducts(roomMap, key));
				ratePlans.getRatePlan().add(rp);
			}
		} catch (Exception e) {
			msg = CommonUtil.getExceptionMsg(e, "ccm");
		}
		// OTAHotelRatePlanRS
		oTAHotelRatePlanRS = setResponseMsg(oTAHotelRatePlanRS, msg);
		oTAHotelRatePlanRS.setRatePlans(ratePlans);
		log.info(msg + "buildHotelRatePlanRS：" + (System.currentTimeMillis() - startMili) + "ms");
		return oTAHotelRatePlanRS;
	}

	/**
	 * 查询7天的全量数据(开关，房价，房量或房态)
	 * 
	 * @param channelCode
	 * @param chainCode
	 * @param hotelCode
	 * @param roomMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> queryHotelRatePlan(String channelCode, String chainCode, String hotelCode, Map<String, Object> roomMap) {

		ChannelGoodsVO channelGoodsVO = new ChannelGoodsVO();
		channelGoodsVO.setChannelCode(channelCode);
		// 查询７天内数据
		Date start = DateUtil.currentDate();
		Date end = DateUtil.addDays(start, 6);
		List<Date> dayList = DateUtil.getDayList(start, end);

		// 获取酒店ID
		Hotel hotel = hotelMCManager.getHotelByCodeMC3(chainCode, hotelCode);
		if (hotel == null) {
			throw new BizException("HotelIsNotFound");
		}

		Map<String, Object> rateMap = new HashMap<String, Object>();

		RoomMsg param = new RoomMsg();
		param.setChannelCode(channelCode);
		param.setChainCode(chainCode);
		param.setHotelCode(hotelCode);
		param.setCurrencyCode(hotel.getCurrencyCode());
		Channel channel = channelDao.getChannelByChannelCode(channelCode);
		if (channel == null) {
			throw new BizException("ChannelIsNotFound");
		}
		param.setChannelId(channel.getChannelId());
		List<String> roomTypeIdList = new ArrayList<String>();
		StringBuffer roomTypeIds = new StringBuffer();
		List<String> rateCodeList = new ArrayList<String>();
		List<String> roomList = new ArrayList<String>();
		for (Date pushDate : dayList) {
			channelGoodsVO.setExpireDate(pushDate);
			channelGoodsVO.setHotelId(hotel.getHotelId());
			channelGoodsVO.setStatus(ChannelGoodsStatus.publish);// 查询已发布的关系
			List<ChannelGoodsVO> cgvoList = channelgoodsManager.getEnabledChannelGoods2(channelGoodsVO);
			if (CommonUtil.isEmpty(cgvoList)) {
				continue;
			}
			Date dateAdd1 = DateUtil.addDays(pushDate, 1);
			for (ChannelGoodsVO cgvo : cgvoList) {
				if (!roomTypeIdList.contains(cgvo.getRoomTypeId())) {
					roomTypeIdList.add(cgvo.getRoomTypeId());
					roomTypeIds.append(",'").append(cgvo.getRoomTypeId()).append("'");
				}
				String roomTypeCode = cgvo.getRoomTypeCode();
				String ratePlanCode = cgvo.getRatePlanCode();
				if (!rateCodeList.contains(ratePlanCode)) {
					rateCodeList.add(ratePlanCode);
				}
				param.setRoomTypeCode(roomTypeCode);
				param.setRateCode(ratePlanCode);
				param.setDate(pushDate);
				param.setStartDate(DateUtil.convertDateToString(pushDate));

				// 排除同天重复房价房型代码
				String key = buildKey(new String[] { ratePlanCode, roomTypeCode, param.getStartDate() });
				if (roomList.contains(key)) {
					continue;
				}
				roomList.add(key);

				// 查询产品开关
				rateMap = queryProductSwith(param, rateMap);

				// 查询房量或房态
				List<RsvtypeChannel> rcList = rsvtypeChannelManager.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, pushDate, dateAdd1, channelCode);
				if (CommonUtil.isEmpty(rcList)) {
					RsvtypeChannel rc = new RsvtypeChannel();
					roomMap = queryRoomStatus(param, rc, channel, roomMap);
				} else {
					for (RsvtypeChannel rc : rcList) {
						roomMap = queryRoomStatus(param, rc, channel, roomMap);
					}
				}

			}
		}
		String roomIds = "";
		if (roomTypeIds.length() > 1) {
			roomIds = " and roomTypeId in (" + roomTypeIds.substring(1) + ")";
		}
		// 查询房价
		PriceCalc pc = new PriceCalc();
		pc.setChannelCode(channelCode);
		pc.setChainCode(chainCode);
		pc.setHotelCode(hotelCode);
		pc.setCurrencyCode(hotel.getCurrencyCode());
		pc.setHotelId(hotel.getHotelId());
		pc.setChannelId(param.getChannelId());
		pc.setStartDate(DateUtil.convertDateToString(start));
		pc.setEndDate(DateUtil.convertDateToString(end));
		pc.setRoomTypeIdsql(roomIds);
		pc.setRatePlanCodeList(rateCodeList);
		List<RoomMsg> rateList = queryRates(pc);
		for (RoomMsg rate : rateList) {
			String rateKey = rate.getRateCode();
			String rateRoomKey = buildKey(new String[] { rate.getRoomTypeCode(), rate.getStartDate() });
			if (rateMap.get(rateKey) != null) {
				Map<String, RoomMsg> rateRoomMapSrc = (Map<String, RoomMsg>) rateMap.get(rateKey);
				if (rateRoomMapSrc.containsKey(rateRoomKey)) {
					RoomMsg rm = rateRoomMapSrc.get(rateRoomKey);
					Boolean onOff = rm.getOnOff();
					BeanUtils.copyProperties(rate, rm);
					rm.setOnOff(onOff);
				} else {
					rateRoomMapSrc.put(rateRoomKey, rate);
				}
			} else {
				Map<String, RoomMsg> rateRoomMap = new HashMap<String, RoomMsg>();
				rateRoomMap.put(rateRoomKey, rate);
				rateMap.put(rateKey, rateRoomMap);
			}
		}
		return rateMap;
	}

	/**
	 * 查询房量或房态
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> queryRoomStatus(RoomMsg param, RsvtypeChannel rc, Channel channel, Map<String, Object> roomMap) {

		Date date = param.getDate();
		String channelCode = param.getChannelCode();
		String hotelCode = param.getHotelCode();
		String roomTypeCode = param.getRoomTypeCode();

		RoomMsg rm = new RoomMsg();
		BeanUtils.copyProperties(param, rm);
		int sendAvai = 0;
		// 获取该天该ob量
		rc.setChannelId(param.getChannelId());
		rc.setChannelCode(channelCode);
		rc.setHotelCode(hotelCode);
		rc.setType(roomTypeCode);
		rc.setDate(date);
		int obAvailable = rsvtypeChannelManager.getObAvailable(rc);
		int totalObSold = CommonUtil.getInt(rc.getTotalOBSold());
		boolean freeSell;
		if (rc.getFreeSell() == null || rc.getFreeSell()) {
			freeSell = true;
		} else {
			freeSell = false;
		}
		if (CommonUtil.getInt(rc.getAvailable()) >= 0) {
			if (freeSell) {
				int roomTypePhysicalRoom = 0;
				if (null == rc.getAvailable()) {
					roomTypePhysicalRoom = roomTypeManager.getRoomTypePhysicalRoom(rc.getType(), rc.getHotelCode());
				}
				String key = hotelCode + "|" + roomTypeCode + "|" + DateUtil.getMonthOfYear(date);
				Map<String, Long> redisRoomQtyMap = roomQtyDao.readLongValueFromMap(key);
				Long redisSoldQty = new Long(0);
				if (redisRoomQtyMap != null) {
					String rcDate = DateUtil.getDate(rc.getDate());
					if (redisRoomQtyMap.get(rcDate) != null) {
						redisSoldQty = redisRoomQtyMap.get(rcDate);
					}
				}
				sendAvai = roomTypePhysicalRoom + CommonUtil.getInt(rc.getAvailable()) + (obAvailable - totalObSold) - redisSoldQty.intValue();
			} else {
				sendAvai = obAvailable - totalObSold;
			}
		}
		if (channel.getPushRavl() == null ? false : channel.getPushRavl()) { // 房量
			if (freeSell) {
				if (sendAvai > 0) {
					rm.setAmount(sendAvai);
					rm.setOnOff(null);
				} else {
					rm.setAmount(null);
					rm.setOnOff(RoomMsg.ROOM_CLOSE);
				}
			} else {
				if (obAvailable > 0) {
					rm.setAmount(sendAvai);
					rm.setOnOff(null);
				} else {
					rm.setAmount(null);
					rm.setOnOff(RoomMsg.ROOM_CLOSE);
				}
			}
		} else if (channel.getPushRstu() == null ? false : channel.getPushRstu()) {// 房态
			if (rm.getAmount().intValue() > 0) {
				// 推送开
				rm.setOnOff(RoomMsg.ROOM_OPEN);
			} else {
				// 推送关
				rm.setOnOff(RoomMsg.ROOM_CLOSE);
			}
			rm.setAmount(null);
		}
		String rateKey = param.getRateCode();
		String rateRoomKey = buildKey(new String[] { roomTypeCode, param.getStartDate() });
		if (roomMap.get(rateKey) != null) {
			Map<String, RoomMsg> rateRoomMapSrc = (Map<String, RoomMsg>) roomMap.get(rateKey);
			if (!rateRoomMapSrc.containsKey(rateRoomKey)) {
				rateRoomMapSrc.put(rateRoomKey, rm);
			}
		} else {
			Map<String, RoomMsg> rateRoomMap = new HashMap<String, RoomMsg>();
			rateRoomMap.put(rateRoomKey, rm);
			roomMap.put(rateKey, rateRoomMap);
		}
		return roomMap;
	}

	/**
	 * 查询房价
	 * 
	 * @param pc
	 * @param roomIds
	 * @return
	 * @throws Exception
	 */
	private List<RoomMsg> queryRates(PriceCalc pc) {

		// 房价消息的list
		List<RoomMsg> rateList = new ArrayList<RoomMsg>();
		// 获取房价
		List<PriceCalc> pcList = priceCalcDao.getPriceFromRateDetail(pc);
		Map<String, List<com.ccm.api.model.ratePlan.RateAmount>> rateAmountMap = new HashMap<String, List<com.ccm.api.model.ratePlan.RateAmount>>();

		for (PriceCalc priceCalc : pcList) {
			// 查询有渠道绑定关系的房价
			if (pc.getRatePlanCodeList().contains(priceCalc.getRatePlanCode())) {
				String rateKey = priceCalc.getChainCode() + priceCalc.getChannelCode() + priceCalc.getHotelCode() + priceCalc.getRoomTypeCode() + priceCalc.getRatePlanCode() + priceCalc.getDate();
				List<com.ccm.api.model.ratePlan.RateAmount> rateAmountList = rateAmountMap.get(rateKey);

				if (rateAmountList != null) {
					rateAmountList = rateAmountMap.get(rateKey);
					com.ccm.api.model.ratePlan.RateAmount ra = new com.ccm.api.model.ratePlan.RateAmount();
					ra.setBaseAmount(priceCalc.getAmount());
					ra.setNumberOfUnits(priceCalc.getNumberOfUnits());
					rateAmountList.add(ra);
				} else {
					rateAmountList = new ArrayList<com.ccm.api.model.ratePlan.RateAmount>();
					com.ccm.api.model.ratePlan.RateAmount ra = new com.ccm.api.model.ratePlan.RateAmount();
					ra.setBaseAmount(priceCalc.getAmount());
					ra.setNumberOfUnits(priceCalc.getNumberOfUnits());
					rateAmountList.add(ra);

					RoomMsg rm = new RoomMsg();
					rm.setChainCode(pc.getChainCode());
					rm.setChannelCode(pc.getChannelCode());
					rm.setHotelCode(pc.getHotelCode());
					rm.setRoomTypeCode(priceCalc.getRoomTypeCode());
					rm.setRateCode(priceCalc.getRatePlanCode());
					rm.setStartDate(priceCalc.getDate());
					rm.setRateAmountList(rateAmountList);
					rm.setCurrencyCode(pc.getCurrencyCode());
					rateList.add(rm);
					rateAmountMap.put(rateKey, rateAmountList);
				}
			}
		}
		// 设置AmountAfterTax值
		rateAmountManager.setAfterAmountWithTaxForRoomMsg(rateList);
		return rateList;
	}

	/**
	 * 查询产品开关
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> queryProductSwith(RoomMsg param, Map<String, Object> rateMap) {
		String channelCode = param.getChannelCode();
		String chainCode = param.getChainCode();
		String hotelCode = param.getHotelCode();
		String roomTypeCode = param.getRoomTypeCode();
		String ratePlanCode = param.getRateCode();
		ProductionVO product = new ProductionVO();
		product.setChainCode(chainCode);
		product.setHotelCode(hotelCode);
		product.setChannelCode(channelCode);
		product.setRoomTypeCode(roomTypeCode);
		product.setRatePlanCode(ratePlanCode);
		product.setStartDate(param.getDate());
		product.setEndDate(param.getDate());
		List<RestrictionCalc> restrictionCalcList = restrictionCalcDao.searchRestrictionCalcOnOffForPush(product);
		for (RestrictionCalc restrictionCalc : restrictionCalcList) {
			Boolean onOff = null;
			if ((restrictionCalc.getOnOff().intValue() == 1)) {
				onOff = RoomMsg.ROOM_OPEN;
			} else {
				onOff = RoomMsg.ROOM_CLOSE;
			}
			String rateKey = ratePlanCode;
			String rateRoomKey = buildKey(new String[] { roomTypeCode, param.getStartDate() });
			if (rateMap.get(rateKey) != null) {
				Map<String, RoomMsg> rateRoomMapSrc = (Map<String, RoomMsg>) rateMap.get(rateKey);
				if (!rateRoomMapSrc.containsKey(rateRoomKey)) {
					RoomMsg rm = new RoomMsg();
					BeanUtils.copyProperties(param, rm);
					rm.setOnOff(onOff);
					rateRoomMapSrc.put(rateRoomKey, rm);
				}
			} else {
				RoomMsg rm = new RoomMsg();
				BeanUtils.copyProperties(param, rm);
				rm.setOnOff(onOff);
				Map<String, RoomMsg> rateRoomMap = new HashMap<String, RoomMsg>();
				rateRoomMap.put(rateRoomKey, rm);
				rateMap.put(rateKey, rateRoomMap);
			}
		}
		return rateMap;
	}

	private String buildKey(String[] str) {
		StringBuffer sb = new StringBuffer();
		for (String s : str) {
			sb.append(":").append(s);
		}
		return sb.toString().substring(1);
	}

	@SuppressWarnings("unchecked")
	/**
	 * 构建SellableProducts
	 */
	private SellableProductsType dealSellabelProducts(Map<String, Object> roomMap, String key) {
		SellableProductsType sellableProductsType = new SellableProductsType();
		Map<String, RoomMsg> roomDateMap = (Map<String, RoomMsg>) roomMap.get(key);
		for (Map.Entry<String, RoomMsg> room : roomDateMap.entrySet()) {
			RoomMsg rm = room.getValue();
			SellableProduct sellableProduct = new SellableProduct();
			sellableProduct.setInvCode(rm.getRoomTypeCode());
			sellableProduct.setStart(rm.getStartDate());
			sellableProduct.setEnd(rm.getStartDate());
			if (rm.getOnOff() != null) {
				sellableProduct.setInvStatusType(rm.getOnOff() ? "Active" : "Deactivated");
			}
			if (rm.getAmount() != null) {
				Room gRoom = new Room();
				gRoom.setQuantity(rm.getAmount());
				GuestRoomType guestRoomType = new GuestRoomType();
				guestRoomType.setRoom(gRoom);
				sellableProduct.setGuestRoom(guestRoomType);
			}
			sellableProductsType.getSellableProduct().add(sellableProduct);
		}
		return sellableProductsType;
	}

	private OTAHotelRatePlanRS setResponseMsg(OTAHotelRatePlanRS oTAHotelRatePlanRS, String msg) {
		if (msg != null) {
			ErrorType errorType = new ErrorType();
			errorType.setType("99");
			errorType.setValue(msg);
			ErrorsType errorsType = new ErrorsType();
			errorsType.getError().add(errorType);
			oTAHotelRatePlanRS.setErrors(errorsType);
		} else {
			oTAHotelRatePlanRS.setSuccess(new SuccessType());
		}
		return oTAHotelRatePlanRS;
	}
}
