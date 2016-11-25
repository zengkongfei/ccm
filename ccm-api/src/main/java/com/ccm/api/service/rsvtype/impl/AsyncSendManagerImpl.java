package com.ccm.api.service.rsvtype.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ccm.api.jms.RoomJmsManager;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.service.rsvtype.AsyncSendManager;
@Service("asyncSendManager")
public class AsyncSendManagerImpl implements AsyncSendManager{
	
	@Resource
	private RoomJmsManager roomJmsManager;
	
	@Async
	public void sendRoomStatusMsgToJms(Rsvtype rsvtype, boolean isHandSend) {
		try {
//			System.out.println("async start sendRoomStatusMsgToJms...");
			roomJmsManager.sendRoomStatusMsgToJms(rsvtype,isHandSend);
//			System.out.println("sendRoomStatusMsgToJms :"+rsvtype.getChannel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
