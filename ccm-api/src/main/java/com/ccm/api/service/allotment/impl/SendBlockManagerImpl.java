package com.ccm.api.service.allotment.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;

import javax.annotation.Resource;
import javax.imageio.metadata.IIOMetadataNode;
import javax.xml.bind.JAXBException;

import org.opentravel.ota._2003._05.InvBlockRoomType;
import org.opentravel.ota._2003._05.InvBlockType;
import org.opentravel.ota._2003._05.OTAHotelInvBlockNotifRQ;
import org.opentravel.ota._2003._05.OTAHotelInvBlockNotifRS;
import org.opentravel.ota._2003._05.TPAExtensionsType;
import org.opentravel.ota._2003._05.TransactionActionType;
import org.opentravel.ota._2003._05.InvBlockType.RoomTypes;
import org.springframework.stereotype.Service;

import com.ccm.api.jms.OTAJmsManager;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.log.SendOTALog;
import com.ccm.api.service.allotment.SendBlockManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.log.SendOTALogManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;
import com.ccm.api.util.PushDataUtil;
import com.ccm.api.util.XMLUtil;

@Service("sendBlockManager")
public class SendBlockManagerImpl implements SendBlockManager {
	
	@Resource
	private Properties projectProperties;
	@Resource
	private SendOTALogManager sendOTALogManager;
	@Resource
	private OTAJmsManager otaJmsManager;
	@Resource
	private ChannelManager channelManager;
	@Override
	public void done(Map<String,Object>paramMap){
		List<SendOTALog>  sendOTALogList=generateAndSendOTABlockMsg(paramMap);
		for(SendOTALog sendOTALog:sendOTALogList){
			send2ndTimeForFailedOTABlockMsg(sendOTALog);
		}
	}
	public List<SendOTALog> generateAndSendOTABlockMsg(Map<String,Object>paramMap){
		String oxiTrxId=(String)paramMap.get("oxiTrxId");
		String messageType=(String)paramMap.get("messageType");
		String action=(String)paramMap.get("action");
		String hotelCode = (String)paramMap.get("hotelCode");
		String chainCode = (String)paramMap.get("chainCode");
		String target = (String)paramMap.get("target");
		List<Map<String, String>> invBlockList=(List<Map<String, String>>)paramMap.get("invBlockList");
		List<SendOTALog> sendOTALogList=new Vector<SendOTALog>();
		for(int i=0;i<invBlockList.size();i++){
		List<Map<String, String>> sendBlockList=new ArrayList<Map<String,String>>();
		sendBlockList.add(invBlockList.get(i));
		Map<String, String>sendBlock=sendBlockList.get(0);
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
		sendOTALog.setStatus("READY");
		try {
			sendOTALog.setStartDate(DateUtil.convertStringToDate(sendBlock.get("startDate")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sendOTALog.setRatePlanCode(sendBlock.get("ratePlanCodes"));
		sendOTALog.setRoomTypeCode(sendBlock.get("roomTypeCode"));
		try {
			String otaXML = XMLUtil.JAXBParseToXml(
					createBlockNotification(target,sendBlockList));
			otaXML=otaXML.replaceAll("xmlns=\"\"","");
			otaXML=otaXML.replaceAll("xmlns:ns2=\"http://www.opentravel.org/OTA/2003/05\"", "");
			sendOTALog.setMessage(otaXML);
			sendOTALog.setTransformStatus("SUCCESS");

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			sendOTALog.setTransformStatus("FAILED");
			e.printStackTrace();
		}
		sendOTALogManager.addOTAlog(sendOTALog);
		sendOTALogList.add(sendOTALog);
		}
		return sendOTALogList;
	}
	public void send2ndTimeForFailedOTABlockMsg(SendOTALog sendOTALog){
		if (sendOTALog.getTransformStatus().equalsIgnoreCase("SUCCESS")) {
			String otaRespXml=null;
			try {
				Channel channel=channelManager.getChannelByChannelCode(sendOTALog.getChannelCode());
				if(channel==null||channel.getReceiveBlock()==null||channel.getReceiveBlock()==false){
					otaRespXml= PushDataUtil.postData(
							projectProperties.getProperty("otaUrlContext"),
							sendOTALog.getMessage());
				}else{
					otaRespXml= PushDataUtil.postData(
							CommonUtil.getHttpReq(channel.getReceiveAddress().trim()),
							sendOTALog.getMessage());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Map <String,Object>logMap=new HashMap<String,Object>();
				logMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
				logMap.put("status","FAIL");
				logMap.put("comments",e.getClass().getName());
				logMap.put("lastModifyTime", new Date());
				sendOTALogManager.updateStatusForOTALog(logMap);
				try{
					otaJmsManager.sendOTAmsg(sendOTALog);
				}catch(Exception ex){
					Map <String,Object>logParamMap=new HashMap<String,Object>();
					logParamMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
					logParamMap.put("status","FAIL");
					logParamMap.put("comments",ex.getClass().getName());
					logParamMap.put("lastModifyTime", new Date());
					sendOTALogManager.updateStatusForOTALog(logParamMap);
				}
				return;
			}
			boolean respResult=false;
			try {
				respResult=handleResp(otaRespXml);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				Map <String,Object>logMap=new HashMap<String,Object>();
				logMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
				logMap.put("status","FAIL");
				logMap.put("comments",e.getClass().getName());
				logMap.put("lastModifyTime", new Date());
				sendOTALogManager.updateStatusForOTALog(logMap);
				try{
					otaJmsManager.sendOTAmsg(sendOTALog);
				}catch(Exception ex){
					Map<String,Object>logParamMap=new HashMap<String,Object>();
					logParamMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
					logParamMap.put("status","FAIL");
					logParamMap.put("comments",ex.getClass().getName());
					logParamMap.put("lastModifyTime", new Date());
					sendOTALogManager.updateStatusForOTALog(logParamMap);
				}
				return;
			}
			Map <String,Object>logParamMap=new HashMap<String,Object>();
			logParamMap.put("sendOTALogId", sendOTALog.getSendOTALogId());
			if(respResult){
				logParamMap.put("status","SUCCESS");
				logParamMap.put("comments",null);
			}else{
				logParamMap.put("status","FAIL");
				logParamMap.put("comments","The result of switch is failed");
			}
			logParamMap.put("lastModifyTime", new Date());
			sendOTALogManager.updateStatusForOTALog(logParamMap);
		}
	}
	@Override
	public boolean handleResp(String otaRespXml) throws JAXBException {
		if(CommonUtil.isEmpty(otaRespXml))
			return false;
		OTAHotelInvBlockNotifRS otaHotelInvBlockNotifRS=(OTAHotelInvBlockNotifRS)XMLUtil.JAXBParseToBean(otaRespXml, OTAHotelInvBlockNotifRS.class,
				"UTF-8");
		if(otaHotelInvBlockNotifRS.getSuccess()!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public OTAHotelInvBlockNotifRQ createBlockNotification(
			String target,List<Map<String, String>> invBlockList) {

		OTAHotelInvBlockNotifRQ otaHotelInvBlockNotifRQ = new OTAHotelInvBlockNotifRQ();
		otaHotelInvBlockNotifRQ.setTarget(projectProperties.getProperty("otaInfType"));
		otaHotelInvBlockNotifRQ.setVersion(new BigDecimal(2));
		OTAHotelInvBlockNotifRQ.InvBlocks invBlocks = new OTAHotelInvBlockNotifRQ.InvBlocks();
		otaHotelInvBlockNotifRQ.setInvBlocks(invBlocks);
		TPAExtensionsType tpaExtensionsType=new TPAExtensionsType();
		IIOMetadataNode channelNode=new IIOMetadataNode("Channel");
		channelNode.setAttribute("ChannelCode", target);
		tpaExtensionsType.getAny().add(channelNode);
		otaHotelInvBlockNotifRQ.setTPAExtensions(tpaExtensionsType);
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
}
