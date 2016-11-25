package com.ccm.api.jms.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.ccm.api.dao.hotel.ChainDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.ratePlan.RestrictionCalcDao;
import com.ccm.api.dao.ratePlan.mongodb.RoomMsgDaoMongo;
import com.ccm.api.dao.redis.RedisDao;
import com.ccm.api.dao.rsvtype.RsvchanBlockDao;
import com.ccm.api.jms.RoomJmsManager;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.vo.ChannelGoodsVO;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.jmsMsg.RoomMsg;
import com.ccm.api.model.ratePlan.RestrictionCalc;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.sell.vo.ProductionVO;
import com.ccm.api.service.allotment.SendBlockManager;
import com.ccm.api.service.channel.ChannelgoodsManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Service("roomJmsManager")
public class RoomJmsManagerImpl implements RoomJmsManager {
	protected final Log log = LogFactory.getLog(getClass());
	@Resource
	private JmsTemplate jmsTemplate;
	@Resource
	private ChannelManager channelManager;
	@Resource
	private ChannelgoodsManager channelgoodsManager;
	@Resource
	private ChainDao chainDao;
	@Resource
	private HotelDao hotelDao;
	@Resource
	private RsvtypeChannelManager rsvtypeChannelManager;
	@Resource
	private Destination invSnapChannelRoomAvaiQueue;
	@Resource
	private RoomMsgDaoMongo roomMsgDaoMongo;
	@Resource
	private RsvchanBlockDao rsvchanBlockDao;
	@Resource
	private RedisDao redisDao;
	@Resource
	private RestrictionCalcDao restrictionCalcDao;
	@Resource
	private RoomTypeManager roomTypeManager;
	@Resource
	private SendBlockManager sendBlockManager;
	@Override
	public void sendRoomStatusMsg2ToJms(Rsvtype rt, boolean isHandSend) throws Exception {

		Hotel h = null;
		if (CommonUtil.isNotEmpty(rt.getHotelid())) {
			h = hotelDao.getHotel(rt.getHotelid());
		} else {
			h = hotelDao.getHotelByHotelCode(rt.getHotelCode()).get(0);
		}
		Chain chain = chainDao.getChainById(h.getChainId());
		String chainCode = chain.getChainCode();
		String hotelCode = rt.getHotelCode();
		String roomTypeCode = rt.getType();

		List<ChannelGoodsVO> crpList = new ArrayList<ChannelGoodsVO>();
		List<RoomMsg> rmList = new ArrayList<RoomMsg>();

		ChannelGoodsVO cgvo = new ChannelGoodsVO();
		cgvo.setRoomTypeCode(rt.getType());
		cgvo.setRatePlanCode(rt.getRateCode());
		cgvo.setHotelId(h.getHotelId());
		cgvo.setIsBind(true);
		crpList = channelgoodsManager.getEnabledChannelGoods(cgvo);
		if (CommonUtil.isEmpty(crpList)) {
			log.error(rt.getType() + "该房型没有对应的渠道");
			crpList = new ArrayList<ChannelGoodsVO>();
		}

		HashMap<String, Object> sendAvaiMap = new HashMap<String, Object>();
		HashMap<String, Object> switchAvaiMap = new HashMap<String, Object>();
		for (ChannelGoodsVO channelGoodsVO : crpList) { // 渠道
			Channel channel = channelManager.getChannelByChannelCode(channelGoodsVO.getChannelCode());

			String channelCode = channel.getChannelCode();
			String ratePlanCode = channelGoodsVO.getRatePlanCode();

			String roomAvaiKey = channelCode + chainCode + hotelCode + roomTypeCode;
			String rateAvaiKey = channelCode + chainCode + hotelCode + roomTypeCode + ratePlanCode;
			List<RsvtypeChannel> rcList = rsvtypeChannelManager.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, rt.getDate(), DateUtil.addDays(rt.getDate(), 1), channelCode);
			Integer amount = 0;
			if (CommonUtil.isNotEmpty(rcList)) {
				RsvtypeChannel rc = rcList.get(0);
				rc.setChannelId(channel.getChannelId());
				amount = rsvtypeChannelManager.getSendAvai(rc);
			} else {
				RsvtypeChannel rc = new RsvtypeChannel();
				rc.setChannelId(channel.getChannelId());
				rc.setHotelCode(hotelCode);
				rc.setOverBooking(rt.getOverBooking());
				int obAvailable = rsvtypeChannelManager.getObAvailable(rc);
				if (rt.getAvailable() == null) {
					rt.setAvailable(roomTypeManager.getRoomTypePhysicalRoom(roomTypeCode, hotelCode));
				}
				int avaiOb = obAvailable - getInt(rt.getTotalOBSold());
				amount = rt.getAvailable() + avaiOb;
			}
			RsvchanBlock rb = rsvchanBlockDao.getRsvchanBlockNum(channelCode, roomTypeCode, hotelCode, rt.getDate());
			RoomMsg rm = new RoomMsg();
			rm.setChainCode(chainCode);
			rm.setHotelCode(hotelCode);
			rm.setRoomTypeCode(roomTypeCode);
			int blockAvai = 0;
			if (rb != null) {
				blockAvai = (rb.getBlockNum() - rb.getBlockSold() > 0 ? rb.getBlockNum() - rb.getBlockSold() : 0);
				rm.setAmount(amount + (rb.getBlockNum() - rb.getBlockSold() > 0 ? rb.getBlockNum() - rb.getBlockSold() : 0));
				log.info("inventory avai:" + amount + "+,block avai:" + (rb.getBlockNum() - rb.getBlockSold()));
			} else {
				rm.setAmount(amount);
			}
			rm.setStartDate(DateUtil.convertDateToString(rt.getDate()));
			rm.setChannelCode(channelGoodsVO.getChannelCode());
			if (channel != null) {
				if (channel.isPush(rt.getDate()) || rt.getIsDailyDay()) {
					if (channel.getPushRavl() == null ? false : channel.getPushRavl() && rt.isForceSend() == false) { // 房量
						if (sendAvaiMap.containsKey(roomAvaiKey)) {
							continue;
						}
						// freesell开关切换
						if (CommonUtil.isNotEmpty(rcList)) {
							if ((rcList.get(0).getFreeSell() == null) || rcList.get(0).getFreeSell() == true) {
								// open
							} else {
								// close
								if (rm.getAmount().intValue() - blockAvai > 0) {
									if (rt.isNeedSendRtav()) {
										// 忽略，不生成消息
										continue;
									} else {
										rm.setAmount(rm.getAmount().intValue() - blockAvai);
									}
								} else {
									rm.setAmount(0);
								}
							}
						}
						rm.setAdsType(AdsMessage.ADSTYPE_AVAILABILITY);
						rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
						rm.setCreatedTime(new Date());
						rmList.add(rm);
					} else if (channel.getPushRstu() == null ? false : channel.getPushRstu() && rt.isForceSend() == false) {// 房态
						if (sendAvaiMap.containsKey(roomAvaiKey)) {
							continue;
						}
						rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
						rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
						rm.setCreatedTime(new Date());

						ProductionVO product = new ProductionVO();
						product.setChainCode(chainCode);
						product.setHotelCode(hotelCode);
						product.setChannelCode(channelCode);
						product.setRoomTypeCode(roomTypeCode);
						product.setStartDate(rt.getDate());
						product.setEndDate(rt.getDate());
						
							if (rm.getAmount().intValue() > 0) {
								// 推送开
								rm.setOnOff(RoomMsg.ROOM_OPEN);
							} else {
								// 推送关
								rm.setOnOff(RoomMsg.ROOM_CLOSE);
							}

						// freesell开关切换
						if (CommonUtil.isNotEmpty(rcList)) {
							if ((rcList.get(0).getFreeSell() == null) || rcList.get(0).getFreeSell() == true) {
								// open
								if (rm.getAmount().intValue() > 0) {
									if (rt.isNeedSendRtav()) {
										// 忽略，不生成消息
									}
								}
							} else {
								// close
								if (rm.getAmount().intValue() - blockAvai > 0) {
									if (rt.isNeedSendRtav()) {
										// 忽略，不生成消息
										continue;
									}
								} else {
									rm.setOnOff(RoomMsg.ROOM_CLOSE);
								}
							}
						} else {
							// open
							if (rm.getAmount().intValue() > 0) {
								if (rt.isNeedSendRtav()) {
									// 忽略，不生成消息
								}
							}
						}

						// valid whether the same switch data has been sent by
						// redis
						String switchKey = "RstuSwitch-" + channelCode + "-" + hotelCode + "-" + roomTypeCode + "-" + DateUtil.convertDateToString(rt.getDate());
						Boolean rateSwitch = redisDao.readObject(switchKey) == null ? null : (Boolean) redisDao.readObject(switchKey);
						if (rateSwitch == null || !rateSwitch.equals(rm.getOnOff())) {
							// push different
							rmList.add(rm);
						}
					} else if (rt.isForceSend() || (channel.getPushRtav() == null ? false : channel.getPushRtav())) {// 开关
						if (switchAvaiMap.containsKey(rateAvaiKey)) {
							continue;
						}
						log.info("roomJmsManager.sendRoomStatusMsg2ToJms->" + AdsMessage.ADSTYPE_AVAILUPDATE);
						// 推送开关
						ProductionVO product = new ProductionVO();
						product.setChainCode(chainCode);
						product.setHotelCode(hotelCode);
						product.setChannelCode(channelCode);
						product.setRoomTypeCode(roomTypeCode);
						product.setRatePlanCode(ratePlanCode);
						product.setStartDate(rt.getDate());
						product.setEndDate(rt.getDate());
						List<RestrictionCalc> restrictionCalcList = restrictionCalcDao.searchRestrictionCalcOnOffForPush(product);
						for (RestrictionCalc restrictionCalc : restrictionCalcList) {
							if ((restrictionCalc.getOnOff().intValue() == 1)) {
								// restriction is open
								rm.setOnOff(RoomMsg.ROOM_OPEN);
							} else {
								// restriction is close
								rm.setOnOff(RoomMsg.ROOM_CLOSE);
							}

							String switchKey = "RtavSwitch-" + channelCode + "-" + hotelCode + "-" + roomTypeCode + "-" + product.getRatePlanCode() + "-" + DateUtil.convertDateToString(rt.getDate());
							Boolean rateSwitch = redisDao.readObject(switchKey) == null ? null : (Boolean) redisDao.readObject(switchKey);
							if (rt.isForceSend() || rateSwitch == null || !(rateSwitch.equals(rm.getOnOff()))) {
								rm.setChannelCode(channelCode);
								rm.setChainCode(chainCode);
								rm.setHotelCode(hotelCode);
								rm.setRoomTypeCode(roomTypeCode);
								rm.setRateCode(product.getRatePlanCode());
								rm.setStartDate(DateUtil.convertDateToString(rt.getDate()));
								rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
								rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
								rm.setCreatedTime(new Date());
								rmList.add(rm);
							}
						}
					}
					sendAvaiMap.put(roomAvaiKey, null);
					switchAvaiMap.put(rateAvaiKey, null);
				}

			}
		}
		roomMsgDaoMongo.batchSave(rmList);
	}

	@Override
	public void sendRoomStatusMsgToJms(Rsvtype rt, boolean isHandSend) throws Exception {
		// 获取房量
		// Integer amount = rsvtypeManager.getRsvtypeAilable(rt.getType(),
		// rt.getHotelCode(), rt.getDate(), DateUtil.addDays(rt.getDate(),
		// 1),rt.getChannel());
		Integer amount = rt.getSendAvailable() >= 0 ? rt.getSendAvailable() : 0;

		Hotel h = null;
		if (CommonUtil.isNotEmpty(rt.getHotelid())) {
			h = hotelDao.getHotel(rt.getHotelid());
		} else {
			h = hotelDao.getHotelByHotelCode(rt.getHotelCode()).get(0);
		}
		Chain chain = chainDao.getChainById(h.getChainId());
		String chainCode = chain.getChainCode();
		String hotelCode = rt.getHotelCode();
		String roomTypeCode = rt.getType();

		List<ChannelGoodsVO> crpList = new ArrayList<ChannelGoodsVO>();
		List<RoomMsg> rmList = new ArrayList<RoomMsg>();

		// log.info("get channel start...");
		ChannelGoodsVO cgvo = new ChannelGoodsVO();
		if (CommonUtil.isNotEmpty(rt.getChannel())) {
			cgvo.setChannelCode(rt.getChannel());
		}
			cgvo.setRoomTypeCode(rt.getType());
			cgvo.setHotelId(h.getHotelId());
			cgvo.setRatePlanCode(rt.getRateCode());
			cgvo.setIsBind(true);
			crpList = channelgoodsManager.getEnabledChannelGoods(cgvo);
		if (CommonUtil.isEmpty(crpList)) {
			log.warn(rt.getType() + "该房型没有对应的渠道");
			crpList = new ArrayList<ChannelGoodsVO>();
		}
		// log.info("get channel end...");
		HashMap<String, Object> sendAvaiMap = new HashMap<String, Object>();
		HashMap<String, Object> switchAvaiMap = new HashMap<String, Object>();
		for (ChannelGoodsVO channelGoodsVO : crpList) { // 渠道
			Channel channel = channelManager.getChannelByChannelCode(channelGoodsVO.getChannelCode());
			String channelCode = channel.getChannelCode();
			String ratePlanCode = channelGoodsVO.getRatePlanCode();
			String roomAvaiKey = channelCode + chainCode + hotelCode + roomTypeCode;
			String rateAvaiKey = channelCode + chainCode + hotelCode + roomTypeCode + ratePlanCode;
			List<RsvtypeChannel> rcList = rsvtypeChannelManager.getRsvtypeChannelAvailable(roomTypeCode, hotelCode, rt.getDate(), DateUtil.addDays(rt.getDate(), 1), channelCode);
			RsvchanBlock rb = rsvchanBlockDao.getRsvchanBlockNum(channelCode, roomTypeCode, hotelCode, rt.getDate());
			RoomMsg rm = new RoomMsg();
			rm.setChainCode(chainCode);
			rm.setHotelCode(hotelCode);
			rm.setRoomTypeCode(roomTypeCode);
			int blockAvai = 0;
			if (rb != null) {
				blockAvai = (rb.getBlockNum() - rb.getBlockSold() > 0 ? rb.getBlockNum() - rb.getBlockSold() : 0);
				rm.setAmount(amount + (rb.getBlockNum() - rb.getBlockSold() > 0 ? rb.getBlockNum() - rb.getBlockSold() : 0));
				log.info("inventory avai:" + amount + "+,block avai:" + (rb.getBlockNum() - rb.getBlockSold()));
			} else {
				rm.setAmount(amount);
			}
			rm.setStartDate(DateUtil.convertDateToString(rt.getDate()));
			rm.setChannelCode(channelGoodsVO.getChannelCode());
			if (channel != null) {
				if (channel.isPush(rt.getDate()) || rt.getIsDailyDay()) {
					if (channel.getPushRavl() == null ? false : channel.getPushRavl() && rt.isForceSend() == false) { // 房量
						if (sendAvaiMap.containsKey(roomAvaiKey)) {
							continue;
						}
						// freesell开关切换
						if (CommonUtil.isNotEmpty(rcList)) {
							if ((rcList.get(0).getFreeSell() == null) || rcList.get(0).getFreeSell() == true) {
								// open
							} else {
								// close
								if (rm.getAmount().intValue() - blockAvai > 0) {
									if (rt.isNeedSendRtav()) {
										// 忽略，不生成消息
										continue;
									} else {
										rm.setAmount(rm.getAmount().intValue() - blockAvai);
									}
								} else {
									rm.setAmount(0);
								}
							}
						}
						rm.setAdsType(AdsMessage.ADSTYPE_AVAILABILITY);
						rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
						rm.setCreatedTime(new Date());
						rmList.add(rm);
					} else if (channel.getPushRstu() == null ? false : channel.getPushRstu() && rt.isForceSend() == false) {// 房态
						if (sendAvaiMap.containsKey(roomAvaiKey)) {
							continue;
						}
						rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
						rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
						rm.setCreatedTime(new Date());

						ProductionVO product = new ProductionVO();
						product.setChainCode(chainCode);
						product.setHotelCode(hotelCode);
						product.setChannelCode(channelCode);
						product.setRoomTypeCode(roomTypeCode);
						product.setStartDate(rt.getDate());
						product.setEndDate(rt.getDate());
						
							if (rm.getAmount().intValue() > 0) {
								// 推送开
								rm.setOnOff(RoomMsg.ROOM_OPEN);
							} else {
								// 推送关
								rm.setOnOff(RoomMsg.ROOM_CLOSE);
							}

						// freesell开关切换
						if (CommonUtil.isNotEmpty(rcList)) {
							if ((rcList.get(0).getFreeSell() == null) || rcList.get(0).getFreeSell() == true) {
								// open
								if (rm.getAmount().intValue() > 0) {
									if (rt.isNeedSendRtav()) {
										// 忽略，不生成消息
									}
								}
							} else {
								// close
								if (rm.getAmount().intValue() - blockAvai > 0) {
									if (rt.isNeedSendRtav()) {
										// 忽略，不生成消息
										continue;
									}
								} else {
									rm.setOnOff(RoomMsg.ROOM_CLOSE);
								}
							}
						} else {
							// open
							if (rm.getAmount().intValue() > 0) {
								if (rt.isNeedSendRtav()) {
									// 忽略，不生成消息
								}
							}
						}
						// valid whether the same switch data has been sent by
						// redis
						String switchKey = "RstuSwitch-" + channelCode + "-" + hotelCode + "-" + roomTypeCode + "-" + DateUtil.convertDateToString(rt.getDate());
						Boolean rateSwitch = redisDao.readObject(switchKey) == null ? null : (Boolean) redisDao.readObject(switchKey);
						if (isHandSend || rateSwitch == null || !(rateSwitch.equals(rm.getOnOff()))) {
							// push different
							rmList.add(rm);
						}
					} else if (rt.isForceSend() || (channel.getPushRtav() == null ? false : channel.getPushRtav())) {//开关
						if (switchAvaiMap.containsKey(rateAvaiKey)) {
							continue;
						}
						log.info("roomJmsManager.sendRoomStatusMsgToJms->" + AdsMessage.ADSTYPE_AVAILUPDATE);
						// 推送开关
						ProductionVO product = new ProductionVO();
						product.setChainCode(chainCode);
						product.setHotelCode(hotelCode);
						product.setChannelCode(channelCode);
						product.setRoomTypeCode(roomTypeCode);
						product.setRatePlanCode(ratePlanCode);
						product.setStartDate(rt.getDate());
						product.setEndDate(rt.getDate());
						List<RestrictionCalc> restrictionCalcList = restrictionCalcDao.searchRestrictionCalcOnOffForPush(product);
						for (RestrictionCalc restrictionCalc : restrictionCalcList) {
							if ((restrictionCalc.getOnOff().intValue() == 1)) {
								// restriction is open
								rm.setOnOff(RoomMsg.ROOM_OPEN);
							} else {
								// restriction is close
								rm.setOnOff(RoomMsg.ROOM_CLOSE);
							}
							String switchKey = "RtavSwitch-" + channelCode + "-" + hotelCode + "-" + roomTypeCode + "-" + product.getRatePlanCode() + "-" + DateUtil.convertDateToString(rt.getDate());
							Boolean rateSwitch = redisDao.readObject(switchKey) == null ? null : (Boolean) redisDao.readObject(switchKey);
							if (isHandSend || rt.isForceSend() || rateSwitch == null || !(rateSwitch.equals(rm.getOnOff()))) {
								rm.setChannelCode(channelCode);
								rm.setChainCode(chainCode);
								rm.setHotelCode(hotelCode);
								rm.setRoomTypeCode(roomTypeCode);
								rm.setRateCode(product.getRatePlanCode());
								rm.setStartDate(DateUtil.convertDateToString(rt.getDate()));
								rm.setAdsType(AdsMessage.ADSTYPE_AVAILUPDATE);
								rm.setSendStatus(AdsMessage.EXEC_INIT_STATUS);
								rm.setCreatedTime(new Date());
								rmList.add(rm);
							}
						}

					}
					sendAvaiMap.put(roomAvaiKey, null);
					switchAvaiMap.put(rateAvaiKey, null);
				}
			}
		}
		roomMsgDaoMongo.batchSave(rmList);
	}

	@Override
	public void sendChannelRoomAvai(final Map<String, String> rsvMap) {
		this.jmsTemplate.send(this.invSnapChannelRoomAvaiQueue, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objMsg = session.createObjectMessage();
				objMsg.setObjectProperty("rsvMap", rsvMap);
				return objMsg;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void invSnapChannelRoomAvai(Map<String, String> rsvMap) {
		String rsvJson = rsvMap.get("rsvJson");
		String roomtype = rsvMap.get("roomTypeJson");
		String threadName = rsvMap.get("threadName");
		List<Rsvtype> rsvtypeList = JSONArray.parseArray(rsvJson, Rsvtype.class);

		HashSet<String> roomTypeSet = JSONArray.parseObject(roomtype, HashSet.class);
		HashMap<String, List<ChannelGoodsVO>> roomTypeChannelMap = null;
		String hotelId = "";
		if (CommonUtil.isNotEmpty(rsvtypeList)) {
			Rsvtype rsv = rsvtypeList.get(0);
			List<Hotel> hList = hotelDao.getHotelByHotelCode(rsv.getHotelCode());
			if (CommonUtil.isNotEmpty(hList)) {
				hotelId = hList.get(0).getHotelId();
				roomTypeChannelMap = new HashMap<String, List<ChannelGoodsVO>>();
				for (String roomType : roomTypeSet) {
					if (CommonUtil.isNotEmpty(hotelId)) {
						ChannelGoodsVO cgvo = new ChannelGoodsVO();
						cgvo.setRoomTypeCode(roomType);
						cgvo.setHotelId(hotelId);
						List<ChannelGoodsVO> crpList = channelgoodsManager.getEnabledChannelGoods(cgvo);
						roomTypeChannelMap.put(roomType, crpList);
					}
				}
			}
		} else {
			return;
		}

		for (Rsvtype rsv : rsvtypeList) {
			/** 酒店房量变更发送消息逻辑 */
			try {
				String roomTypeCode = rsv.getType();
				List<ChannelGoodsVO> crpList = roomTypeChannelMap.get(roomTypeCode);
				if (CommonUtil.isEmpty(crpList)) {
					log.warn("该房型没有对应的渠道");
					continue;
				} else {
					HashMap<String, String> roomtChannelMap = new HashMap<String, String>();
					for (ChannelGoodsVO channelGoodsVO : crpList) {
						if (CommonUtil.isNotEmpty(channelGoodsVO.getChannelId())) {
							roomtChannelMap.put(channelGoodsVO.getChannelId(), channelGoodsVO.getChannelCode());
						}
					}
					List<RsvtypeChannel> rcList = rsvtypeChannelManager.getRsvtypeChannelAvailable(rsv.getRsvtypeId(), roomtChannelMap.keySet());
					if (CommonUtil.isEmpty(rcList)) {
						rcList = new ArrayList<RsvtypeChannel>();
						RsvtypeChannel rc = new RsvtypeChannel();
						BeanUtils.copyProperties(rsv, rc);
						rcList.add(rc);
					}
					for (RsvtypeChannel rc : rcList) {
						int sendAvai = 0;
						// 获取该天该ob量
						BeanUtils.copyProperties(rsv, rc);
						int obAvailable = rsvtypeChannelManager.getObAvailable(rc);
						int totalObSold = getInt(rc.getTotalOBSold());
						int hotelAvailable = rsv.getAvailable();
						if (hotelAvailable >= 0) {
							if (rc.getFreeSell() == null || rc.getFreeSell()) {
								sendAvai = hotelAvailable + (obAvailable - totalObSold);
							} else {
								sendAvai = obAvailable - totalObSold;
							}
						}
						rc.setChannel(roomtChannelMap.get(rc.getChannelId()));
						rc.setSendAvailable(sendAvai);
						rc.setInvSnapInvoke(true);
						rc.setHotelid(hotelId);
						sendRoomStatusMsgToJms(rc, false);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("#################" + threadName);
	}

	private int getInt(Object obj) {
		return obj != null ? Integer.parseInt(obj.toString()) : 0;
	}
	
	@Override
	public void sendDeductOTABlocks(String oxiTrxId,String actionType,List <RsvchanBlock> rsvchanBlockList){
		String chainCode=null;
		if(rsvchanBlockList.size()==0){
			return ;
		}
		List <Hotel> hotelList=hotelDao.getHotelByHotelCode(rsvchanBlockList.get(0).getHotelCode());
		if(hotelList.size()>0){
			String chainId=hotelList.get(0).getChainId();
			Chain chain=chainDao.get(chainId);
			chainCode=chain.getChainCode();
			}else{
				return;
			}
		//key is channel code
		Map <String,List<Map<String, String>>> otaInvBlockListMap=new HashMap<String,List<Map<String, String>>>();
		for(RsvchanBlock rsvchanBlock:rsvchanBlockList){
			if(otaInvBlockListMap.get(rsvchanBlock.getChannelCode())==null){
				List<Map<String, String>> otaInvBlockList = new Vector<Map<String, String>>();
				otaInvBlockListMap.put(rsvchanBlock.getChannelCode(),otaInvBlockList);
			}
		Integer deduct=rsvchanBlock.getBlockSold()!=null?rsvchanBlock.getBlockSold():0;
		rsvchanBlock=rsvchanBlockDao.get(rsvchanBlock.getRsvchanblockId());
		if(CommonUtil.isEmpty(rsvchanBlock))
			continue;
		
		List<Map<String, String>> otaInvBlockList=otaInvBlockListMap.get(rsvchanBlock.getChannelCode());
		Map<String, String> otaBlockMap = new HashMap<String, String>();
		otaBlockMap.put("actionType", actionType);
		otaBlockMap.put("hotelCode",  rsvchanBlock.getHotelCode());
		otaBlockMap.put("chainCode", chainCode);
		otaBlockMap.put("invBlockCode", rsvchanBlock.getBlockCode());
		otaBlockMap.put("invBlockGroupingCode",
				rsvchanBlock.getBlockCode());
		otaBlockMap.put("startDate",
				DateUtil.convertDateToString(rsvchanBlock.getDate()));
		otaBlockMap.put("endDate",
				DateUtil.convertDateToString(rsvchanBlock.getDate()));
		if(rsvchanBlock.getCutOffDate()==null){
			otaBlockMap.put("cutOffDate",
					DateUtil.convertDateToString(rsvchanBlock.getDate()));
		}else{
			otaBlockMap.put("cutOffDate",
					DateUtil.convertDateToString(rsvchanBlock.getCutOffDate()));
		}
		otaBlockMap.put("roomTypeCode", rsvchanBlock.getType());
		otaBlockMap.put("numberOfUnits",
				String.valueOf(getInt(rsvchanBlock.getBlockNum())-getInt(rsvchanBlock.getBlockSold())-deduct));
		otaBlockMap.put("ratePlanCodes", rsvchanBlock.getRatePlanCodes());
	
		otaInvBlockList.add(otaBlockMap);
		}
		
		for(Map.Entry<String,List<Map<String, String>>> entry:otaInvBlockListMap.entrySet()){
			List<Map<String, String>> otaInvBlockList =entry.getValue();
			String channelCode=entry.getKey();
			Map<String,Object>blockParamMap=new HashMap<String,Object>();
			blockParamMap.put("oxiTrxId",oxiTrxId);
			blockParamMap.put("messageType",com.ccm.api.model.constant.MessageType.ALLOTMENT);
			blockParamMap.put("action",actionType);
			blockParamMap.put("hotelCode",otaInvBlockList.get(0).get("hotelCode"));
			blockParamMap.put("chainCode",chainCode);
			blockParamMap.put("target",channelCode);
			blockParamMap.put("invBlockList",otaInvBlockList);
			sendBlockManager.done(blockParamMap);
		}
	
	}
}
