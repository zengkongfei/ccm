package com.ccm.api.service.allotment.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.opentravel.ota._2003._05.InvBlockRoomType;
import org.opentravel.ota._2003._05.InvBlockType;
import org.opentravel.ota._2003._05.OTAHotelInvBlockNotifRQ;
import org.opentravel.ota._2003._05.OTAHotelInvBlockNotifRS;
import org.opentravel.ota._2003._05.TransactionActionType;
import org.opentravel.ota._2003._05.InvBlockType.RoomTypes;
import org.springframework.scheduling.annotation.Async;

import com.ccm.api.jms.OTAJmsManager;
import com.ccm.api.model.log.SendOTALog;
import com.ccm.api.service.log.SendOTALogManager;
import com.ccm.api.util.PushDataUtil;
import com.ccm.api.util.XMLUtil;

public class SendOTABLockThread implements Runnable {

	private List<Map<String, String>> invBlockList;
	private String oxiTrxId;
	private String messageType;
	private String action;
	private String hotelCode;
	private String chainCode;
	private String target;
	
	// 项目配置属性
	private Properties projectProperties;

	private SendOTALogManager sendOTALogManager;
	
	private OTAJmsManager otaJmsManager;
	

	public SendOTABLockThread(String oxiTrxId, String messageType,
			String action, String hotelCode, String chainCode,String target,
			List<Map<String, String>> invBlockList,SendOTALogManager sendOTALogManager,OTAJmsManager otaJmsManager,Properties projectProperties) {
		this.oxiTrxId = oxiTrxId;
		this.messageType = messageType;
		this.action = action;
		this.hotelCode = hotelCode;
		this.chainCode = chainCode;
		this.target=target;
		this.invBlockList = invBlockList;
		this.sendOTALogManager=sendOTALogManager;
		this.otaJmsManager=otaJmsManager;
		this.projectProperties = projectProperties;
	}

	public SendOTALog convertOTABlockToSendOTALog() {
		SendOTALog sendOTALog = new SendOTALog();
		sendOTALog.setAction(action);
		sendOTALog.setChainCode(chainCode);
		sendOTALog.setChannelCode(target);
		sendOTALog.setCreatedTime(new Date());
		sendOTALog.setHotelCode(hotelCode);
		sendOTALog.setMessageType(messageType);
		sendOTALog.setOxiTrxId(oxiTrxId);
		sendOTALog.setCreatedTime(new Date());
		sendOTALog.setSendOTALogId(UUID.randomUUID().toString()
				.replace("-", ""));
		try {
			String otaXML = XMLUtil.JAXBParseToXml(SendOTABLockThread
					.createBlockNotification(target,invBlockList));
			sendOTALog.setMessage(otaXML);
			sendOTALog.setTransformStatus("SUCCESS");

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			sendOTALog.setTransformStatus("FAILED");
			e.printStackTrace();
		}
		sendOTALogManager.addOTAlog(sendOTALog);
		return sendOTALog;
	}

	public static OTAHotelInvBlockNotifRQ createBlockNotification(
			String target,List<Map<String, String>> invBlockList) {

		OTAHotelInvBlockNotifRQ otaHotelInvBlockNotifRQ = new OTAHotelInvBlockNotifRQ();
		otaHotelInvBlockNotifRQ.setTarget(target);
		otaHotelInvBlockNotifRQ.setVersion(new BigDecimal(2));
		OTAHotelInvBlockNotifRQ.InvBlocks invBlocks = new OTAHotelInvBlockNotifRQ.InvBlocks();
		otaHotelInvBlockNotifRQ.setInvBlocks(invBlocks);
		for (Map<String, String> blockMap : invBlockList) {

			OTAHotelInvBlockNotifRQ.InvBlocks.InvBlock invBlock = new OTAHotelInvBlockNotifRQ.InvBlocks.InvBlock();
			invBlocks.getInvBlock().add(invBlock);
			if (blockMap.get("actionType").equalsIgnoreCase("SYNC")) {
				invBlock.setTransactionAction(TransactionActionType.INITIATE);
			} else if (blockMap.get("actionType").equalsIgnoreCase("NEW")) {
				invBlock.setTransactionAction(TransactionActionType.INITIATE);
			} else if (blockMap.get("actionType").equalsIgnoreCase("CHANGE")) {
				invBlock.setTransactionAction(TransactionActionType.MODIFY);
			} else if (blockMap.get("actionType").equalsIgnoreCase("DELETE")) {
				invBlock.setTransactionAction(TransactionActionType.CANCEL);
			} else if (blockMap.get("actionType").equalsIgnoreCase("NA")) {
				invBlock.setTransactionAction(TransactionActionType.IGNORE);
			}

			invBlock.setBookingStatus("OPEN");
			invBlock.setInvBlockCode(blockMap.get("invBlockCode"));
			invBlock.setInvBlockGroupingCode(blockMap.get("invBlockCode"));

			org.opentravel.ota._2003._05.InvBlockType.HotelRef hotelRef = new org.opentravel.ota._2003._05.InvBlockType.HotelRef();
			hotelRef.setHotelCode(blockMap.get("hotelCode"));
			hotelRef.setChainCode(blockMap.get("chainCode"));
			invBlock.setHotelRef(hotelRef);

			InvBlockType.InvBlockDates invBlockDates = new InvBlockType.InvBlockDates();
			invBlockDates.setStart(blockMap.get("startDate"));
			invBlockDates.setEnd(blockMap.get("startDate"));
			invBlockDates.setAbsoluteCutoff(blockMap.get("cutOffDate"));
			invBlock.setInvBlockDates(invBlockDates);
			
			
			/*zhongyao*/
			InvBlockRoomType invBlockRoomType = new InvBlockRoomType();
			
			String ratePlanCodes=blockMap.get("ratePlanCodes");
			invBlockRoomType.setRatePlans(new InvBlockRoomType.RatePlans());
			List<InvBlockRoomType.RatePlans.RatePlan> ratePlanList=invBlockRoomType.getRatePlans().getRatePlan();
			for(String ratePlanCode:ratePlanCodes.split(",")){
				InvBlockRoomType.RatePlans.RatePlan ratePlan=new InvBlockRoomType.RatePlans.RatePlan();
				ratePlan.setRatePlanCode(ratePlanCode);
				ratePlanList.add(ratePlan);
			}
			
			
			invBlockRoomType.setRoomTypeCode(blockMap.get("roomTypeCode"));
			InvBlockType.RoomTypes roomTypes = new RoomTypes();
			roomTypes.getRoomType().add(invBlockRoomType);
			invBlock.setRoomTypes(roomTypes);

			InvBlockRoomType.RoomTypeAllocations.RoomTypeAllocation roomTypeAllocation = new InvBlockRoomType.RoomTypeAllocations.RoomTypeAllocation();
			roomTypeAllocation.setStart(blockMap.get("startDate"));
			roomTypeAllocation.setEnd(blockMap.get("startDate"));
			roomTypeAllocation.setNumberOfUnits(new BigInteger(blockMap
					.get("numberOfUnits")));
			roomTypeAllocation.setDuration("P1D");

			InvBlockRoomType.RoomTypeAllocations roomTypeAllocations = new InvBlockRoomType.RoomTypeAllocations();
			roomTypeAllocations.getRoomTypeAllocation().add(roomTypeAllocation);
			invBlockRoomType.getRoomTypeAllocations().add(roomTypeAllocations);
		}
		return otaHotelInvBlockNotifRQ;
	}

	public static void handleResp(String otaRespXml) throws JAXBException {
		OTAHotelInvBlockNotifRS otaHotelInvBlockNotifRS=(OTAHotelInvBlockNotifRS)XMLUtil.JAXBParseToBean(otaRespXml, OTAHotelInvBlockNotifRS.class,
				"UTF-8");
		
	}
	
	public void done(){
		// TODO Auto-generated method stub
		SendOTALog sendOTALog = convertOTABlockToSendOTALog();
			if (sendOTALog.getTransformStatus().equalsIgnoreCase("SUCCESS")) {
				String otaRespXml=null;
				try {
					otaRespXml= PushDataUtil.postData(
							projectProperties.getProperty("otaUrlContext"),
							sendOTALog.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					try{
						otaJmsManager.sendOTAmsg(sendOTALog);
					}catch(Exception ex){
						Map paramMap=new HashMap();
						paramMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
						paramMap.put("status","FAIL");
						paramMap.put("comments","jms server is down");
						paramMap.put("lastModifyTime", new Date());
						sendOTALogManager.updateStatusForOTALog(paramMap);
					}
					return;
				}
				try {
					handleResp(otaRespXml);
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					try{
						otaJmsManager.sendOTAmsg(sendOTALog);
					}catch(Exception ex){
						Map paramMap=new HashMap();
						paramMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
						paramMap.put("status","FAIL");
						paramMap.put("comments","jms server is down");
						paramMap.put("lastModifyTime", new Date());
						sendOTALogManager.updateStatusForOTALog(paramMap);
					}
					return;
				}
				Map paramMap=new HashMap();
				paramMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
				paramMap.put("status","SUCCESS");
				paramMap.put("comments","no problem");
				paramMap.put("lastModifyTime", new Date());
				sendOTALogManager.updateStatusForOTALog(paramMap);
			}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		SendOTALog sendOTALog = convertOTABlockToSendOTALog();
		if (sendOTALog.getTransformStatus().equalsIgnoreCase("SUCCESS")) {
			String otaRespXml=null;
			try {
				otaRespXml= PushDataUtil.postData(
						projectProperties.getProperty("otaUrlContext"),
						sendOTALog.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try{
					otaJmsManager.sendOTAmsg(sendOTALog);
				}catch(Exception ex){
					Map paramMap=new HashMap();
					paramMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
					paramMap.put("status","FAIL");
					paramMap.put("comments","jms server is down");
					paramMap.put("lastModifyTime", new Date());
					sendOTALogManager.updateStatusForOTALog(paramMap);
				}
				return;
			}
			try {
				handleResp(otaRespXml);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				try{
					otaJmsManager.sendOTAmsg(sendOTALog);
				}catch(Exception ex){
					Map paramMap=new HashMap();
					paramMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
					paramMap.put("status","FAIL");
					paramMap.put("comments","jms server is down");
					paramMap.put("lastModifyTime", new Date());
					sendOTALogManager.updateStatusForOTALog(paramMap);
				}
				return;
			}
			Map paramMap=new HashMap();
			paramMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
			paramMap.put("status","SUCCESS");
			paramMap.put("comments","no problem");
			paramMap.put("lastModifyTime", new Date());
			sendOTALogManager.updateStatusForOTALog(paramMap);
		}
	}
}
