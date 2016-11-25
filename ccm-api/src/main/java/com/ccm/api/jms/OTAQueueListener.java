package com.ccm.api.jms;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccm.api.aop.ExceptionPointcut;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.constant.MessageType;
import com.ccm.api.model.log.SendOTALog;
import com.ccm.api.service.allotment.SendBlockManager;
import com.ccm.api.service.common.ChannelManager;
import com.ccm.api.service.log.SendOTALogManager;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.PushDataUtil;

public class OTAQueueListener implements MessageListener {

	protected final Log log = LogFactory.getLog("OTAlistener");
	@Resource
	private SendOTALogManager sendOTALogManager;
	@Resource
	private SendBlockManager sendBlockManager;
	@Resource
	private ChannelManager channelManager;
	// 项目配置属性
	private Properties projectProperties;


	@Resource
	public void setProjectProperties(Properties projectProperties) {
		this.projectProperties = projectProperties;
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionPointcut
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		String sendOTALogId=null;
		if (message instanceof ObjectMessage) {
			try {
				ObjectMessage oMsg=(ObjectMessage)message;
				SendOTALog sendOTALog=(SendOTALog)oMsg.getObject();
				sendOTALogId=sendOTALog.getSendOTALogId();
				String otaRespXml=null;
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
				boolean respResult=false;
				if(sendOTALog.getMessageType().equalsIgnoreCase(MessageType.ALLOTMENT)&&sendOTALog.getStatus().equalsIgnoreCase("READY")){
					respResult=sendBlockManager.handleResp(otaRespXml);
				}
				Map<String,Object> param=new HashMap<String,Object>();
				param.put("sendOTALogId",sendOTALogId);
				if(respResult){
					param.put("status", "SUCCESS");
					param.put("comments",null);
				}else{
					param.put("status","FAIL");
					param.put("comments","The result of PMS is failed");
				}
				param.put("lastModifyTime", new Date());
				sendOTALogManager.updateStatusForOTALog(param);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if(sendOTALogId!=null){
					Map<String,Object>param=new HashMap<String,Object>();
					param.put("sendOTALogId",sendOTALogId);
					param.put("status", "FAIL");
					param.put("lastModifyTime", new Date());
					param.put("comments",e.getClass().getName());
					sendOTALogManager.updateStatusForOTALog(param);
				}
			}
			} else {
				log.error("receiveMsgTypeIsFalse");
			}
		

	}
}
