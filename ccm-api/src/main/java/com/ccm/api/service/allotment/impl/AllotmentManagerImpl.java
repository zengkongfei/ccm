package com.ccm.api.service.allotment.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import _0._2.fidelio.allotment.AssociatedProfiles;
import _0._2.fidelio.allotment.InventoryBlock;
import _0._2.fidelio.allotment.InventoryBlockNotification;
import _0._2.fidelio.allotment.InventoryBlockStatusType;
import _0._2.fidelio.allotment.InventoryBlocks;
import _0._2.fidelio.profile.Profile;

import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.dao.channel.RateplanDao;
import com.ccm.api.dao.hotel.ChainDao;
import com.ccm.api.dao.hotel.CustomDao;
import com.ccm.api.dao.hotel.HotelDao;
import com.ccm.api.dao.ratePlan.RateCustomRelationshipDao;
import com.ccm.api.dao.redis.RedisDao;
import com.ccm.api.dao.roomType.RoomTypeDao;
import com.ccm.api.dao.rsvtype.RoomQtyDao;
import com.ccm.api.dao.rsvtype.RsvchanBlockDao;
import com.ccm.api.dao.rsvtype.RsvtypeChannelDao;
import com.ccm.api.dao.rsvtype.RsvtypeDao;
import com.ccm.api.jms.OTAJmsManager;
import com.ccm.api.jms.RoomJmsManager;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.hotel.Chain;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.model.roomType.vo.RoomTypeVO;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.service.log.SendMsgLogManager;
import com.ccm.api.service.log.SendOTALogManager;
import com.ccm.api.service.roomType.RoomTypeManager;
import com.ccm.api.service.rsvtype.RsvtypeChannelManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.DateUtils;
import com.ccm.api.util.XMLUtil;
import com.ccm.oxi.result.Success;
import com.ccm.api.service.allotment.AllotmentManager;
import com.ccm.api.service.allotment.SendBlockManager;
import com.ccm.api.service.common.DictCodeManager;

@Service("allotmentManager")
public class AllotmentManagerImpl implements AllotmentManager {

	@Resource
	private RoomQtyDao roomQtyDao;
	@Resource
	private RsvtypeDao rsvtypeDao;
	@Resource
	private RsvtypeChannelDao rsvtypeChannelDao;
	@Resource
	private ChannelDao channelDao;
	@Resource
	private SendMsgLogManager sendMsgLogManager;
	@Resource
	private ChainDao chainDao;
	@Resource
	private HotelDao hotelDao;
	@Resource
	private RsvchanBlockDao rsvchanBlockDao;
	// 项目配置属性
	private Properties projectProperties;
	@Resource
	private RateplanDao rateplanDao;
	@Resource
	private RoomTypeDao roomTypeDao;
	@Resource
	private CustomDao customDao;
	@Resource
	private SendBlockManager sendBlockManager;
	@Resource
	private RsvtypeChannelManager rsvtypeChannelManager;
	@Resource
	RateCustomRelationshipDao rateCustomRelationshipDao;
	@Resource
	private DictCodeManager dictCodeManager;
	@Resource
	public void setProjectProperties(Properties projectProperties) {
		this.projectProperties = projectProperties;
	}
	
	public boolean proxyHandleAllotment(Map<String, String> mapReq) {
		return handleAllotment(mapReq);
	}

	public boolean handleAllotment(Map<String, String> mapReq) {
		// TODO Auto-generated method stub
		try {
			InventoryBlockNotification invBlockNotification = (InventoryBlockNotification) XMLUtil
					.JAXBParseToBean(mapReq.get("Message"),
							InventoryBlockNotification.class,
							mapReq.get("charsetName"));
			final String oxiTrxId=mapReq.get("transactionId");
			// mfBlockMessageType
			String blockMessageType = invBlockNotification
					.getMfBlockMessageType().value();

			String inventoryBlockStatusType=invBlockNotification.getInventoryBlockStatusType().value();
			// hotelCode
			String hotelCode = invBlockNotification.getHotelReference()
					.getHotelCode();
			String hotelId=null;
			String chainCode = invBlockNotification.getHotelReference().getChainCode();
				List <Hotel> hotelList=hotelDao.getHotelByHotelCode(hotelCode);
				if(hotelList.size()>0){
					String chainId=hotelList.get(0).getChainId();
					hotelId=hotelList.get(0).getHotelId();
					Chain chain=chainDao.get(chainId);
					chainCode=chain.getChainCode();
					}else{
						sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
								Success.FAIL, "There is no the hotel in the ccm",
								MessageType.ALLOTMENT);
						return false;
					}
				
			//rateCode
				String ratePlanCodes=null;
				List <String>rateplanCodeList=invBlockNotification.getRatePlanCodes();
				if(rateplanCodeList.size()==0){
					ratePlanCodes=null;
				}else{
					ratePlanCodes="";
				}
				
				List<Rateplan>ratePlanList=new ArrayList<Rateplan>();
				for(String ratePlanCode:rateplanCodeList){
					Rateplan rp=rateplanDao.getRateplanByRateplanCode(ratePlanCode, hotelCode);
					if(rp==null){
						sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
								Success.FAIL, "There is not the ratecode["+ratePlanCode+"] in the ccm",
								MessageType.ALLOTMENT);
						return false;
					}else{
						ratePlanList.add(rp);
					}
					ratePlanCodes+=ratePlanCode+",";
				}
				if(ratePlanCodes!=null){
					ratePlanCodes=ratePlanCodes.substring(0,ratePlanCodes.length()-1);
				}
				
			// inventoryBlockGroupingCode
			String invBlockGroupingCode = invBlockNotification
					.getInventoryBlockGroupingCode();
			String [] partOfBlockCode=invBlockGroupingCode.split("_");
			if(partOfBlockCode.length==1){
				sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
						Success.FAIL, "The format of block code is wrong",
						MessageType.ALLOTMENT);
				return false;
			}
			String channelCodeOfPerfix=partOfBlockCode[0];
		
			// channelId
			Channel channel = channelDao.getChannelByChannelCode(channelCodeOfPerfix);
			if (channel == null) {
				// it will push failed result to PMS;
				sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
						Success.FAIL, "There is not the channelCode["
								+ channelCodeOfPerfix + "] in CCM",
						MessageType.ALLOTMENT);
				return false;
			}
			
			String profileType = "TRAVEL_AGENT";

			if(blockMessageType.equalsIgnoreCase("DETAIL")&&ratePlanCodes==null){
				List<RsvchanBlock> rsvchanBlockList=rsvchanBlockDao.getRsvchanBlocksByChannelAndBlock(hotelCode,channel.getChannelCode(),invBlockGroupingCode);
				if(rsvchanBlockList.size()>0){
					ratePlanCodes=rsvchanBlockList.get(0).getRatePlanCodes();
				}else{
					String redisRateCodes=(String)roomQtyDao.getFieldValueForHash("BLOCK_"+hotelCode,invBlockGroupingCode);
					if(redisRateCodes!=null){
						if(redisRateCodes.equalsIgnoreCase("ALL")){
							ratePlanCodes=null;
						}else{
							ratePlanCodes=redisRateCodes;
						}
					}else{
						sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
								Success.FAIL, "Can not find the ratecode of the block["
										+ invBlockGroupingCode + "] in CCM",
								MessageType.ALLOTMENT);
						return false;
					}
				}
			}
			
			List<Map<String, String>> otaInvBlockList = new Vector<Map<String, String>>();

			Map <String,String>roomCodeMap=new HashMap<String,String>();
			
			List<RsvchanBlock>removedRsvchanBlockWithRatesList=new ArrayList<RsvchanBlock>();
			
			List<RsvchanBlock>changedRsvchanBlockWithRatesList=new ArrayList<RsvchanBlock>();
			
			List<RsvtypeChannel> sendAvaiRsvtypeChannelList=new ArrayList<RsvtypeChannel>();
			
			if (blockMessageType.equalsIgnoreCase("HEADER")) {
				List<RsvchanBlock> existedRsvchanBlockList=rsvchanBlockDao.getRsvchanBlocksByChannelAndBlock(hotelCode,channel.getChannelCode(),invBlockGroupingCode);
				
				AssociatedProfiles associatedProfiles = invBlockNotification.getAssociatedProfiles();
					List<Profile> associatedProfileList = associatedProfiles.getProfile();
					for (Profile profile : associatedProfileList) {
						if(profile.getProfileType().equalsIgnoreCase(profileType)){
			
							// mfNameCode->IATA CODE
							String IATACode=profile.getMfNameCode();
							Custom c=new Custom();
							c.setHotelId(hotelId);
							c.setCorpIATANo(IATACode);
							c.setType(profileType);
							List <Custom> customList=customDao.getCustomByObj(c);
							if(customList==null||customList.size()==0){
								sendMsgLogManager.saveSendUpdateReceiveMsgLog(
										mapReq, Success.FAIL,
										"There is not the IATACode["+IATACode+"] in the ccm",
										MessageType.ALLOTMENT);
								return false;
							}else{
								c=customList.get(0);
								for(Rateplan rp:ratePlanList){
										if(rateCustomRelationshipDao.getCustomIdByRateCustom(c, rp.getRatePlanId())==null){
											sendMsgLogManager.saveSendUpdateReceiveMsgLog(
													mapReq, Success.FAIL,
													"The accesscode does not bind the Negotiated ratecode in the ccm",
													MessageType.ALLOTMENT);
											return false;
										}
								}
							}
						}
					}
				
				
				if(existedRsvchanBlockList.size()==0){
					// 只有header无法处理
					String redisRateCodes="ALL";
					if(ratePlanCodes!=null){
						redisRateCodes=ratePlanCodes;
					}
					roomQtyDao.hsetForHash("BLOCK_"+hotelCode,invBlockGroupingCode,redisRateCodes);
					sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
							Success.FAIL, "Can not find the matching blocks in CCM",
							MessageType.ALLOTMENT);
					return false;
				}
				if(inventoryBlockStatusType.equalsIgnoreCase("DEACTIVATED")||inventoryBlockStatusType.equalsIgnoreCase("CANCEL")){
					//remove block
					for(RsvchanBlock rb:existedRsvchanBlockList){
						rb.setBlockNum(0);
						rb.setBlockSold(0);
						rsvchanBlockDao.removeRsvchanBlockByRsvchannelIdAndBlockCode(rb.getRsvtypeChannelId(),rb.getBlockCode());
						if(rb.getRatePlanCodes()!=null){
							removedRsvchanBlockWithRatesList.add(rb);
						}else{
							Rsvtype pmsRsvtype=rsvtypeDao.get(rb.getRsvtypeId());
							RsvtypeChannel rc=rsvtypeChannelDao.getRsvtypeChannelByRsvIdAndChannelId(rb.getRsvtypeId(),channel.getChannelId());
							rc.setRsvtypeId(pmsRsvtype.getRsvtypeId());
							rc.setOverBooking(pmsRsvtype.getOverBooking());
							rc.setHotelCode(pmsRsvtype.getHotelCode());
							rc.setType(pmsRsvtype.getType());
							rc.setAvailable(pmsRsvtype.getAvailable());
							rc.setTotalOBSold(pmsRsvtype.getTotalOBSold());
							rc.setDate(pmsRsvtype.getDate());
							sendAvaiRsvtypeChannelList.add(rc);
						}
					}
				}else{
					//update rate
					for(RsvchanBlock rb:existedRsvchanBlockList){
						if(rb.getRatePlanCodes()!=null&&ratePlanCodes==null){
							try {
								RsvchanBlock removeRB = rb.clone();
								removeRB.setBlockNum(0);
								removeRB.setBlockSold(0);
								removedRsvchanBlockWithRatesList.add(removeRB);
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						rb.setRatePlanCodes(ratePlanCodes);
						rsvchanBlockDao.updateRsvchanBlock(rb);
						if(ratePlanCodes!=null){
							changedRsvchanBlockWithRatesList.add(rb);
						}
							Rsvtype pmsRsvtype=rsvtypeDao.get(rb.getRsvtypeId());
							RsvtypeChannel rc=rsvtypeChannelDao.getRsvtypeChannelByRsvIdAndChannelId(rb.getRsvtypeId(),channel.getChannelId());
							rc.setRsvtypeId(pmsRsvtype.getRsvtypeId());
							rc.setOverBooking(pmsRsvtype.getOverBooking());
							rc.setHotelCode(pmsRsvtype.getHotelCode());
							rc.setType(pmsRsvtype.getType());
							rc.setAvailable(pmsRsvtype.getAvailable());
							rc.setTotalOBSold(pmsRsvtype.getTotalOBSold());
							rc.setDate(pmsRsvtype.getDate());
							sendAvaiRsvtypeChannelList.add(rc);
					}
				}
				sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
						Success.SUCCESS,
						"Finish the allotment to CCM successfully",
						MessageType.ALLOTMENT);
			} else if (blockMessageType.equalsIgnoreCase("DETAIL")) {
				String actionType = null;
				InventoryBlocks inventoryBlocks = invBlockNotification
						.getInventoryBlocks();
				List<InventoryBlock> InventoryBlockList = inventoryBlocks
						.getInventoryBlock();
				int pmsAllotInvBlockNums = InventoryBlockList.size();
				int syncAllotInvblockNums = 0;
				
				Set<String>pmsRoomCodeSet=new HashSet<String>();
				Map <String,String> codeMap=dictCodeManager.searchCodeMapByHotelCode3(hotelId, false);
				if(codeMap!=null){
					for (InventoryBlock inventoryBlock : InventoryBlockList) {
						pmsRoomCodeSet.add(inventoryBlock.getInventoryCode());
						String ccmRoomCode=codeMap.get(inventoryBlock.getInventoryCode());
						if(ccmRoomCode!=null)
							roomCodeMap.put(inventoryBlock.getInventoryCode(),ccmRoomCode);
					}
				}
					if(codeMap==null||pmsRoomCodeSet.size()>roomCodeMap.keySet().size()){
						sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
								Success.FAIL, "There is not the pms room type code in CCM",
								MessageType.ALLOTMENT);
						return false;
					}
				
				
				for (InventoryBlock inventoryBlock : InventoryBlockList) {
					String actionTypeValue = inventoryBlock.getActionType()
							.value();
					// invetoryDate
					actionType=actionTypeValue;
					Date inventoryDate = DateUtils.xmlDate2Date(inventoryBlock
							.getMfInventoryDate());
					// inventoryCode -> roomType
					String roomType = roomCodeMap.get(inventoryBlock.getInventoryCode());
					// cutOfDate convert to cutOfDays
					Date cutOffDate = DateUtils.xmlDate2Date(inventoryBlock
							.getMfCutoffDate());

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String invDateStr = dateFormat.format(inventoryDate);

					// numberToBlock
					Integer allotmentQty = inventoryBlock.getNumberToBlock();
					// sold
					Integer allotmentSold = inventoryBlock.getMfNumberSold()==null?0:inventoryBlock.getMfNumberSold();
					if(inventoryBlock.getMfNumberSold()==null){
						Map<String,Object> oneParamMap=new HashMap<String,Object>();
						oneParamMap.put("channelCode", channel.getChannelCode());
						oneParamMap.put("hotelCode", hotelCode);
						oneParamMap.put("type", roomType);
						oneParamMap.put("date", inventoryDate);
						oneParamMap.put("blockCode",invBlockGroupingCode);
						RsvchanBlock exRvchanBlock=rsvchanBlockDao.getRsvchanBlockByParam(oneParamMap);
						if(CommonUtil.isNotEmpty(exRvchanBlock)){
							allotmentSold=exRvchanBlock.getBlockSold()==null?0:exRvchanBlock.getBlockSold();
						}
					}
					Map<String, String> otaBlockMap = new HashMap<String, String>();
					otaBlockMap.put("actionType", actionTypeValue);
					otaBlockMap.put("hotelCode", hotelCode);
					otaBlockMap.put("chainCode", chainCode);
					otaBlockMap.put("invBlockCode", invBlockGroupingCode);
					otaBlockMap.put("invBlockGroupingCode",
							invBlockGroupingCode);
					otaBlockMap.put("startDate",
							DateUtil.convertDateToString(inventoryDate));
					otaBlockMap.put("endDate",
							DateUtil.convertDateToString(inventoryDate));
					otaBlockMap.put("cutOffDate",
							DateUtil.convertDateToString(cutOffDate));
					otaBlockMap.put("roomTypeCode", roomType);
					otaBlockMap.put("numberOfUnits",
							String.valueOf(allotmentQty-allotmentSold));
					otaBlockMap.put("ratePlanCodes", ratePlanCodes);
					if (actionTypeValue.equalsIgnoreCase("NEW")) {
						// add
						if (createAndUpdateForWholeAllotment(channel.getChannelId(),channel.getChannelCode(),
								invBlockGroupingCode, hotelCode, roomType,
								inventoryDate, allotmentQty, inventoryBlock.getMfNumberSold(),
								cutOffDate,ratePlanCodes,removedRsvchanBlockWithRatesList,sendAvaiRsvtypeChannelList)) {
							otaInvBlockList.add(otaBlockMap);
							syncAllotInvblockNums++;
						} else {
							sendMsgLogManager.saveSendUpdateReceiveMsgLog(
									mapReq, Success.FAIL,
									"Add the allotment detail[room type:"
											+ roomType + "inventry date:"
											+ invDateStr + "] failed",
									MessageType.ALLOTMENT);
						}
					} else if (actionTypeValue.equalsIgnoreCase("CHANGE")) {
						// modify
						if (createAndUpdateForWholeAllotment(channel.getChannelId(),channel.getChannelCode(),invBlockGroupingCode,
								hotelCode, roomType, inventoryDate,
								allotmentQty, inventoryBlock.getMfNumberSold(), cutOffDate,ratePlanCodes,removedRsvchanBlockWithRatesList,sendAvaiRsvtypeChannelList)) {
							otaInvBlockList.add(otaBlockMap);
							syncAllotInvblockNums++;
						} else {
							sendMsgLogManager.saveSendUpdateReceiveMsgLog(
									mapReq, Success.FAIL,
									"Update the allotment detail[room type:"
											+ roomType + "inventry date:"
											+ invDateStr + "] failed",
									MessageType.ALLOTMENT);
						}
					} else if (actionTypeValue.equalsIgnoreCase("DELETE")) {
						// remove
						if (removeRsvchanBlockByBlockCode(hotelCode,channel.getChannelCode(),
								inventoryDate, roomType, invBlockGroupingCode,removedRsvchanBlockWithRatesList,sendAvaiRsvtypeChannelList)) {
							otaInvBlockList.add(otaBlockMap);
							syncAllotInvblockNums++;
						} else {
							sendMsgLogManager.saveSendUpdateReceiveMsgLog(
									mapReq, Success.FAIL,
									"Remove the allotment detail[room type:"
											+ roomType + "inventry date:"
											+ invDateStr + "] failed",
									MessageType.ALLOTMENT);
						}

					}
				}
				if (pmsAllotInvBlockNums == syncAllotInvblockNums) {
					sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
							Success.SUCCESS,
							"Finish the allotment to CCM successfully",
							MessageType.ALLOTMENT);
				}
				if(ratePlanCodes!=null)
					handleSendOTALog(mapReq.get("transactionId"),
						mapReq.get("MessageType"), actionType, hotelCode,chainCode,
						channel.getChannelCode(), otaInvBlockList);

			} else if (blockMessageType.equalsIgnoreCase("HEADERWITHDETAIL")) {
				// resync
				// 1.add Or modify directly
				AssociatedProfiles associatedProfiles = invBlockNotification.getAssociatedProfiles();
					List<Profile> associatedProfileList = associatedProfiles.getProfile();
					for (Profile profile : associatedProfileList) {
						if(profile.getProfileType().equalsIgnoreCase(profileType)){
			
							// mfNameCode->IATA CODE
							String IATACode=profile.getMfNameCode();
							Custom c=new Custom();
							c.setHotelId(hotelId);
							c.setCorpIATANo(IATACode);
							c.setType(profileType);
							List <Custom> customList=customDao.getCustomByObj(c);
							if(customList==null||customList.size()==0){
								sendMsgLogManager.saveSendUpdateReceiveMsgLog(
										mapReq, Success.FAIL,
										"There is not the IATACode["+IATACode+"] in the ccm",
										MessageType.ALLOTMENT);
								return false;
							}else{
								c=customList.get(0);
								for(Rateplan rp:ratePlanList){
										if(rateCustomRelationshipDao.getCustomIdByRateCustom(c, rp.getRatePlanId())==null){
											sendMsgLogManager.saveSendUpdateReceiveMsgLog(
													mapReq, Success.FAIL,
													"The accesscode does not bind the Negotiated ratecode in the ccm",
													MessageType.ALLOTMENT);
											return false;
										}
								}
							}
						}
					}
				InventoryBlocks inventoryBlocks = invBlockNotification
						.getInventoryBlocks();
				List<InventoryBlock> InventoryBlockList = inventoryBlocks
						.getInventoryBlock();

				int pmsAllotInvBlockNums = InventoryBlockList.size();
				int syncAllotInvblockNums = 0;
				
				String actionType = null;
				Set<String>pmsRoomCodeSet=new HashSet<String>();
				Map <String,String> codeMap=dictCodeManager.searchCodeMapByHotelCode3(hotelId, false);
				if(codeMap!=null){
					for (InventoryBlock inventoryBlock : InventoryBlockList) {
						pmsRoomCodeSet.add(inventoryBlock.getInventoryCode());
						String ccmRoomCode=codeMap.get(inventoryBlock.getInventoryCode());
						if(ccmRoomCode!=null)
							roomCodeMap.put(inventoryBlock.getInventoryCode(),ccmRoomCode);
					}
				}
					if(codeMap==null||pmsRoomCodeSet.size()>roomCodeMap.keySet().size()){
						sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
								Success.FAIL, "There is not the pms room type code in CCM",
								MessageType.ALLOTMENT);
						return false;
					}
				
				for (InventoryBlock inventoryBlock : InventoryBlockList) {
					/**
					 * actionValue determined to how to operate the
					 * rsv_channel,for example NEW New CHANGE Change DELETE
					 * Cancel or delete SYNC Used in case a block is
					 * synchronized
					 */
					String actionTypeValue = inventoryBlock.getActionType()
							.value();
					actionType = actionTypeValue;
					// invetoryDate
					Date inventoryDate = DateUtils.xmlDate2Date(inventoryBlock
							.getMfInventoryDate());
					// inventoryCode -> roomType
					String roomType = roomCodeMap.get(inventoryBlock.getInventoryCode());
					// cutOffDate convert to cutOffDays
					Date cutOffDate = DateUtils.xmlDate2Date(inventoryBlock
							.getMfCutoffDate());

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String invDateStr = dateFormat.format(inventoryDate);
					// numberToBlock
					Integer allotmentQty = inventoryBlock.getNumberToBlock();
					// sold
					Integer allotmentSold = inventoryBlock.getMfNumberSold()==null?0: inventoryBlock.getMfNumberSold();
					if(inventoryBlock.getMfNumberSold()==null){
						Map<String,Object> oneParamMap=new HashMap<String,Object>();
						oneParamMap.put("channelCode", channel.getChannelCode());
						oneParamMap.put("hotelCode", hotelCode);
						oneParamMap.put("type", roomType);
						oneParamMap.put("date", inventoryDate);
						oneParamMap.put("blockCode",invBlockGroupingCode);
						RsvchanBlock exRvchanBlock=rsvchanBlockDao.getRsvchanBlockByParam(oneParamMap);
						if(CommonUtil.isNotEmpty(exRvchanBlock)){
							allotmentSold=exRvchanBlock.getBlockSold()==null?0:exRvchanBlock.getBlockSold();
						}
					}
					Map<String, String> otaBlockMap = new HashMap<String, String>();
					otaBlockMap.put("actionType", actionTypeValue);
					otaBlockMap.put("hotelCode", hotelCode);
					otaBlockMap.put("chainCode", chainCode);
					otaBlockMap.put("invBlockCode", invBlockGroupingCode);
					otaBlockMap.put("invBlockGroupingCode",
							invBlockGroupingCode);
					otaBlockMap.put("startDate",
							DateUtil.convertDateToString(inventoryDate));
					otaBlockMap.put("endDate",
							DateUtil.convertDateToString(inventoryDate));
					otaBlockMap.put("cutOffDate",
							DateUtil.convertDateToString(cutOffDate));
					otaBlockMap.put("roomTypeCode", roomType);
					otaBlockMap.put("numberOfUnits",
							String.valueOf(allotmentQty-allotmentSold));
					otaBlockMap.put("ratePlanCodes", ratePlanCodes);
					
					if (actionTypeValue.equalsIgnoreCase("SYNC")
							|| actionTypeValue.equalsIgnoreCase("NEW")
							|| actionTypeValue.equalsIgnoreCase("CHANGE")) {
						if (createAndUpdateForWholeAllotment(channel.getChannelId(),channel.getChannelCode(),
								invBlockGroupingCode, hotelCode, roomType,
								inventoryDate, allotmentQty, inventoryBlock.getMfNumberSold(),
								cutOffDate,ratePlanCodes,removedRsvchanBlockWithRatesList,sendAvaiRsvtypeChannelList)) {
							otaInvBlockList.add(otaBlockMap);
							syncAllotInvblockNums++;
						} else {
							sendMsgLogManager.saveSendUpdateReceiveMsgLog(
									mapReq, Success.FAIL,
									"Update the allotment detail[room type:"
											+ roomType + "inventry date:"
											+ invDateStr + "] failed",
									MessageType.ALLOTMENT);
						}
					} else if (actionTypeValue.equalsIgnoreCase("DELETE")) {
						// remove
						if (removeRsvchanBlockByBlockCode(hotelCode,channel.getChannelCode(),
								inventoryDate, roomType, invBlockGroupingCode,removedRsvchanBlockWithRatesList,sendAvaiRsvtypeChannelList)) {
							otaInvBlockList.add(otaBlockMap);
							syncAllotInvblockNums++;
						} else {
							sendMsgLogManager.saveSendUpdateReceiveMsgLog(
									mapReq, Success.FAIL,
									"Remove the allotment detail[room type:"
											+ roomType + "inventry date:"
											+ invDateStr + "] failed",
									MessageType.ALLOTMENT);
						}
					}

				}
				if (pmsAllotInvBlockNums == syncAllotInvblockNums) {
					sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq,
							Success.SUCCESS,
							"Finish the allotment to CCM successfully",
							MessageType.ALLOTMENT);
				}
				if(ratePlanCodes!=null)
					handleSendOTALog(mapReq.get("transactionId"),
						mapReq.get("MessageType"), actionType, hotelCode,chainCode,
						channel.getChannelCode(), otaInvBlockList);
			}
			final List<RsvchanBlock>finalremovedRsvchanBlockWithRatesList =removedRsvchanBlockWithRatesList;
			if(finalremovedRsvchanBlockWithRatesList.size()>0){
	            Thread thread2 = new Thread(new Runnable() {
	        		public void run() {
	        			rsvtypeChannelManager.handleOTABlocks(oxiTrxId,"DELETE",finalremovedRsvchanBlockWithRatesList);
	        		}
	        	});
	            thread2.start();
            }
			final List<RsvchanBlock>finalchangedRsvchanBlockWithRatesList =changedRsvchanBlockWithRatesList;
			if(finalchangedRsvchanBlockWithRatesList.size()>0){
	            Thread thread3 = new Thread(new Runnable() {
	        		public void run() {
	        			rsvtypeChannelManager.handleOTABlocks(oxiTrxId,"CHANGE",finalchangedRsvchanBlockWithRatesList);
	        		}
	        	});
	            thread3.start();
            }
			final List<RsvtypeChannel>finalsendAvaiRsvtypeChannelList =sendAvaiRsvtypeChannelList;
			if(finalsendAvaiRsvtypeChannelList.size()>0){
	            Thread thread4 = new Thread(new Runnable() {
	        		public void run() {
	        			for(RsvtypeChannel rc:finalsendAvaiRsvtypeChannelList){
	        				rsvtypeChannelManager.sendAvai(rc.getHotelCode(), rc.getType(), rc);
	        			}
	        		}
	        	});
	            thread4.start();
            }
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			sendMsgLogManager.saveSendUpdateReceiveMsgLog(mapReq, Success.FAIL,
					"The block xml can not be transform to JAVA",
					MessageType.ALLOTMENT);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* update Detail */
	@Override
	public boolean modifyAllotmentForDetail(String channelId,String channelCode,
			String invBlockGroupingCode, String hotelCode, String roomType,
			Date inventoryDate, Integer allotmentQty, Integer allotmentSold,
			Date cutOffDate,String ratePlanCodes,List<RsvchanBlock>removedRsvchanBlockWithRatesList,List<RsvtypeChannel>sendAvaiRsvtypeChannelList) {
		Integer cutOffDays = DateUtil.dateDiff(cutOffDate, inventoryDate);
		// find rsvtype
		Rsvtype pmsRsvtype = new Rsvtype();
		pmsRsvtype.setDate(inventoryDate);
		pmsRsvtype.setHotelCode(hotelCode);
		pmsRsvtype.setType(roomType);
		pmsRsvtype.setCreatedTime(new Date());
		Rsvtype persistedRsvtype = rsvtypeDao.getRsvtype(pmsRsvtype);
		String rsvtypeId = null;
		if (persistedRsvtype != null) {
			rsvtypeId = persistedRsvtype.getRsvtypeId();
			pmsRsvtype=persistedRsvtype;
		} else {
			pmsRsvtype.setRsvtypeId(UUID.randomUUID().toString()
					.replace("-", ""));
			rsvtypeDao.saveRsvtype(pmsRsvtype);
			rsvtypeId = pmsRsvtype.getRsvtypeId();
		}

		List<RsvtypeChannel> rsvChannelList = rsvtypeChannelDao
				.getRsvChannelListByHeaderDetail(hotelCode, channelId,
						roomType, inventoryDate);
		RsvtypeChannel rsvtypeChannel=null;
		if (rsvChannelList.size() > 0) {
			/* update */
			rsvtypeChannel = rsvChannelList.get(0);
			rsvtypeChannel.setAllotment(null);
			rsvtypeChannel.setAllotmentSold(null);
			rsvtypeChannel.setHasBlock(1);
			rsvtypeChannel.setCutOffDays(null);
			rsvtypeChannel.setRatePlanCodes(null);
			
			if(ratePlanCodes==null||ratePlanCodes.trim().length()==0){
				// empty rate
				/*search block data*/
        		List<RsvchanBlock> existedRsvchanBlockList=rsvchanBlockDao.getRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
        		if(existedRsvchanBlockList.size()>0){
        			for(RsvchanBlock rb:existedRsvchanBlockList){
        				rb.setBlockNum(0);
    					rb.setBlockSold(0);
        				if(rb.getRatePlanCodes()==null||rb.getRatePlanCodes().trim().length()==0){
        				}else{
        					removedRsvchanBlockWithRatesList.add(rb);
        				}
        			}
        		}
        		/*remove block data*/
				rsvchanBlockDao.removeRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
			}else if(rsvchanBlockDao.getBlockCountWithoutRates(rsvtypeChannel.getRsvtypeChannelId())>0){
				//has rate
				rsvchanBlockDao.removeRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
			}
			
			if (rsvtypeChannelDao
					.updateAllotmentToRsvtypeChannel(rsvtypeChannel) > 0) {
				RsvchanBlock rsvchanBlock=rsvchanBlockDao.getBlockListByRsvChanIdAndBlockCode(rsvtypeChannel.getRsvtypeChannelId(),invBlockGroupingCode);
				if(rsvchanBlock!=null){
					//update
					updateExistedRsvchanBlock(rsvchanBlock,allotmentQty,allotmentSold,cutOffDate,cutOffDays,ratePlanCodes);
				}else{
					//new
					saveNewRsvchanBlock(channelId,channelCode,invBlockGroupingCode,hotelCode,roomType,inventoryDate,cutOffDays,
							allotmentQty,allotmentSold,cutOffDate, ratePlanCodes, rsvtypeId, rsvtypeChannel.getRsvtypeChannelId());
				}
			}else{
				return false;
			} 
		}else {
			/* insert */
			rsvtypeChannel= saveRsvtypeChannel(rsvtypeId,channelId);
			saveNewRsvchanBlock(channelId,channelCode,invBlockGroupingCode,hotelCode,roomType,inventoryDate,cutOffDays,
					allotmentQty,allotmentSold,cutOffDate, ratePlanCodes, rsvtypeId, rsvtypeChannel.getRsvtypeChannelId());
		}
		rsvtypeChannel.setRsvtypeId(pmsRsvtype.getRsvtypeId());
		rsvtypeChannel.setOverBooking(pmsRsvtype.getOverBooking());
		rsvtypeChannel.setHotelCode(pmsRsvtype.getHotelCode());
		rsvtypeChannel.setType(pmsRsvtype.getType());
		rsvtypeChannel.setAvailable(pmsRsvtype.getAvailable());
		rsvtypeChannel.setTotalOBSold(pmsRsvtype.getTotalOBSold());
		rsvtypeChannel.setDate(pmsRsvtype.getDate());
		sendAvaiRsvtypeChannelList.add(rsvtypeChannel);
		return true;
	}
	
	@Override
	public boolean createAndUpdateForWholeAllotment(String channelId,String channelCode,
			String invBlockGroupingCode, String hotelCode, String roomType,
			Date inventoryDate, Integer allotmentQty, Integer allotmentSold,
			Date cutOffDate,String ratePlanCodes,List<RsvchanBlock>removedRsvchanBlockWithRatesList,List<RsvtypeChannel>sendAvaiRsvtypeChannelList) {

		Integer cutOffDays = DateUtil.dateDiff(cutOffDate, inventoryDate);
		// find rsvtype
		Rsvtype pmsRsvtype = new Rsvtype();
		pmsRsvtype.setDate(inventoryDate);
		pmsRsvtype.setHotelCode(hotelCode);
		pmsRsvtype.setType(roomType);
		pmsRsvtype.setCreatedTime(new Date());
		Rsvtype persistedRsvtype = rsvtypeDao.getRsvtype(pmsRsvtype);
		String rsvtypeId = null;
		if (persistedRsvtype != null) {
			rsvtypeId = persistedRsvtype.getRsvtypeId();
			pmsRsvtype=persistedRsvtype;
		} else {
			pmsRsvtype.setRsvtypeId(UUID.randomUUID().toString()
					.replace("-", ""));
			rsvtypeDao.saveRsvtype(pmsRsvtype);
			rsvtypeId = pmsRsvtype.getRsvtypeId();
		}

		List<RsvtypeChannel> rsvChannelList = rsvtypeChannelDao
				.getRsvChannelListByHeaderDetail(hotelCode, channelId,
						roomType, inventoryDate);
		
		RsvtypeChannel rsvtypeChannel=null;
		if (rsvChannelList.size() > 0) {
			/* update */
			rsvtypeChannel = rsvChannelList.get(0);
			rsvtypeChannel.setAllotment(null);
			rsvtypeChannel.setAllotmentSold(null);
			rsvtypeChannel.setHasBlock(1);
			rsvtypeChannel.setCutOffDays(null);
			rsvtypeChannel.setRatePlanCodes(null);
			
			if(ratePlanCodes==null||ratePlanCodes.trim().length()==0){
				// empty rate
				/*search block data*/
        		List<RsvchanBlock> existedRsvchanBlockList=rsvchanBlockDao.getRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
        		if(existedRsvchanBlockList.size()>0){
        			for(RsvchanBlock rb:existedRsvchanBlockList){
        				rb.setBlockNum(0);
    					rb.setBlockSold(0);
        				if(rb.getRatePlanCodes()==null||rb.getRatePlanCodes().trim().length()==0){
        				}else{
        					removedRsvchanBlockWithRatesList.add(rb);
        				}
        			}
        		}
        		/*remove block data*/
				rsvchanBlockDao.removeRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
			}else if(rsvchanBlockDao.getBlockCountWithoutRates(rsvtypeChannel.getRsvtypeChannelId())>0){
				//has rate
				rsvchanBlockDao.removeRsvchanBlocksByRsvchanelId(rsvtypeChannel.getRsvtypeChannelId());
			}
			
			if (rsvtypeChannelDao
					.updateAllotmentToRsvtypeChannel(rsvtypeChannel) > 0) {
				RsvchanBlock rsvchanBlock=rsvchanBlockDao.getBlockListByRsvChanIdAndBlockCode(rsvtypeChannel.getRsvtypeChannelId(),invBlockGroupingCode);
				if(rsvchanBlock!=null){
					//update
					updateExistedRsvchanBlock(rsvchanBlock,allotmentQty,allotmentSold,cutOffDate,cutOffDays,ratePlanCodes);
				}else{
					//new
					saveNewRsvchanBlock(channelId,channelCode,invBlockGroupingCode,hotelCode,roomType,inventoryDate,cutOffDays,
							allotmentQty,allotmentSold,cutOffDate, ratePlanCodes, rsvtypeId, rsvtypeChannel.getRsvtypeChannelId());
				}
			}else{
				return false;
			} 
		} else {
			/* insert */
			rsvtypeChannel= saveRsvtypeChannel(rsvtypeId,channelId);
			saveNewRsvchanBlock(channelId,channelCode,invBlockGroupingCode,hotelCode,roomType,inventoryDate,cutOffDays,
					allotmentQty,allotmentSold,cutOffDate, ratePlanCodes, rsvtypeId, rsvtypeChannel.getRsvtypeChannelId());
		}
			rsvtypeChannel.setRsvtypeId(pmsRsvtype.getRsvtypeId());
			rsvtypeChannel.setOverBooking(pmsRsvtype.getOverBooking());
			rsvtypeChannel.setHotelCode(pmsRsvtype.getHotelCode());
			rsvtypeChannel.setType(pmsRsvtype.getType());
			rsvtypeChannel.setAvailable(pmsRsvtype.getAvailable());
			rsvtypeChannel.setTotalOBSold(pmsRsvtype.getTotalOBSold());
			rsvtypeChannel.setDate(pmsRsvtype.getDate());
			sendAvaiRsvtypeChannelList.add(rsvtypeChannel);
		return true;
	}

	private RsvtypeChannel saveRsvtypeChannel(String rsvtypeId,String channelId){
		RsvtypeChannel rsvtypeChannel = new RsvtypeChannel();
		rsvtypeChannel.setRsvtypeChannelId(CommonUtil.generatePrimaryKey());
		rsvtypeChannel.setRsvtypeId(rsvtypeId);
		rsvtypeChannel.setChannelId(channelId);
		rsvtypeChannel.setFreeSell(true);
		rsvtypeChannel.setHasBlock(1);
		rsvtypeChannelDao.addRsvtypeChannel(rsvtypeChannel);
		return rsvtypeChannel;
	}
	
	private Integer	updateExistedRsvchanBlock(RsvchanBlock rsvchanBlock,Integer allotmentQty,Integer allotmentSold,Date cutOffDate,Integer cutOffDays,String ratePlanCodes){
		rsvchanBlock.setBlockNum(allotmentQty);
		rsvchanBlock.setBlockSold(allotmentSold);
		rsvchanBlock.setLastModifyTime(new Date());
		rsvchanBlock.setCutOffDate(cutOffDate);
		rsvchanBlock.setCutOffDays(cutOffDays);
		rsvchanBlock.setRatePlanCodes(ratePlanCodes);
		rsvchanBlock.setIsSendToPMS(true);
		return rsvchanBlockDao.updateRsvchanBlock(rsvchanBlock);
	}
	private RsvchanBlock saveNewRsvchanBlock(String channelId,String channelCode,
			String invBlockGroupingCode, String hotelCode, String roomType,
			Date inventoryDate,Integer cutOffDays,Integer allotmentQty, Integer allotmentSold,
			Date cutOffDate,String ratePlanCodes,String rsvtypeId,String rsvtypeChannelId){
		RsvchanBlock rsvchanBlock=new RsvchanBlock();
		rsvchanBlock.setRsvchanblockId(CommonUtil.generatePrimaryKey());
		rsvchanBlock.setRsvtypeChannelId(rsvtypeChannelId);
		rsvchanBlock.setRsvtypeId(rsvtypeId);
		rsvchanBlock.setChannelCode(channelCode);
		rsvchanBlock.setChannelId(channelId);
		rsvchanBlock.setType(roomType);
		rsvchanBlock.setBlockCode(invBlockGroupingCode);
		rsvchanBlock.setDate(inventoryDate);
		rsvchanBlock.setCutOffDate(cutOffDate);
		rsvchanBlock.setCutOffDays(cutOffDays);
		rsvchanBlock.setCreatedTime(new Date());
		rsvchanBlock.setBlockNum(allotmentQty);
		rsvchanBlock.setBlockSold(allotmentSold==null?0:allotmentSold);
		rsvchanBlock.setRatePlanCodes(ratePlanCodes);
		rsvchanBlock.setHotelCode(hotelCode);
		rsvchanBlock.setIsSendToPMS(true);
		rsvchanBlockDao.saveRsvchanBlock(rsvchanBlock);
		return rsvchanBlock;
	}
	/* no logical with header alone */
	public void createAllotmentForHeader(String channelId,
			String invBlockGroupingCode, String hotelCode) {

	}

	/* no logical with header alone */
	public void modifyAllotmentForHeader(
			InventoryBlockNotification invBlockNotification) {
		// TODO Auto-generated method stub

	}

	
	/* remove Detail */
	@Override
	public boolean removeRsvchanBlockByBlockCode(String hotelCode,String channelCode,
			Date inventoryDate, String roomType, String invBlockGroupingCode,List<RsvchanBlock>removedRsvchanBlockWithRatesList,List<RsvtypeChannel>sendAvaiRsvtypeChannelList) {
			Map<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("channelCode", channelCode);
			paramMap.put("hotelCode", hotelCode);
			paramMap.put("type", roomType);
			paramMap.put("date", inventoryDate);
			paramMap.put("blockCode",invBlockGroupingCode);
			RsvchanBlock existedRsvchanBlock=rsvchanBlockDao.getRsvchanBlockByParam(paramMap);
    		if(existedRsvchanBlock!=null){
    			existedRsvchanBlock.setBlockNum(0);
    			existedRsvchanBlock.setBlockSold(0);
    				if(existedRsvchanBlock.getRatePlanCodes()==null||existedRsvchanBlock.getRatePlanCodes().trim().length()==0){
    				}else{
    					removedRsvchanBlockWithRatesList.add(existedRsvchanBlock);
    				}
    				rsvchanBlockDao.removeRsvchanBlock(hotelCode,channelCode,
    	    				inventoryDate, roomType, invBlockGroupingCode);
    				Rsvtype pmsRsvtype=rsvtypeDao.get(existedRsvchanBlock.getRsvtypeId());
    				RsvtypeChannel rc=rsvtypeChannelDao.getRsvtypeChannelByRsvIdAndChannelId(existedRsvchanBlock.getRsvtypeId(),existedRsvchanBlock.getChannelId());
    				rc.setRsvtypeId(pmsRsvtype.getRsvtypeId());
    				rc.setOverBooking(pmsRsvtype.getOverBooking());
    				rc.setHotelCode(pmsRsvtype.getHotelCode());
    				rc.setType(pmsRsvtype.getType());
    				rc.setAvailable(pmsRsvtype.getAvailable());
    				rc.setTotalOBSold(pmsRsvtype.getTotalOBSold());
    				rc.setDate(pmsRsvtype.getDate());
    				sendAvaiRsvtypeChannelList.add(rc);
    		}
    	return true;
	}
	
	@Override
	public void handleSendOTALog(String oxiTrxId, String messageType,
			String actionType, String hotelCode, String chainCode,String target,
			List<Map<String, String>> otaInvBlockList) {
		Map<String,Object>blockParamMap=new HashMap<String,Object>();
		blockParamMap.put("oxiTrxId",oxiTrxId);
		blockParamMap.put("messageType",MessageType.ALLOTMENT);
		blockParamMap.put("action",actionType);
		blockParamMap.put("hotelCode",hotelCode);
		blockParamMap.put("chainCode",chainCode);
		blockParamMap.put("target",target);
		blockParamMap.put("invBlockList",otaInvBlockList);
		final Map<String,Object>finalBlockParamMap=blockParamMap;
		Thread thread = new Thread(new Runnable() {
    		public void run() {
    			sendBlockManager.done(finalBlockParamMap);
    		}
	});
		thread.start();
	}
}