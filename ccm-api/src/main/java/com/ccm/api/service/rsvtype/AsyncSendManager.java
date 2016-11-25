package com.ccm.api.service.rsvtype;

import com.ccm.api.model.rsvtype.Rsvtype;

public interface AsyncSendManager {
	/**异步发送房量、房态消息*/
	void sendRoomStatusMsgToJms(Rsvtype rsvtype,boolean isHandSend);
}
