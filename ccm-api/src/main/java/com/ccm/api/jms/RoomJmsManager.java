package com.ccm.api.jms;

import java.util.List;
import java.util.Map;

import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.Rsvtype;

public interface RoomJmsManager {

	void sendRoomStatusMsgToJms(Rsvtype rt, boolean isHandSend) throws Exception;

	void sendChannelRoomAvai(final Map<String, String> rsvMap);

	void invSnapChannelRoomAvai(final Map<String, String> rsvMap);

	void sendRoomStatusMsg2ToJms(Rsvtype rt, boolean isHandSend) throws Exception;

	void sendDeductOTABlocks(String oxiTrxId, String actionType,
			List<RsvchanBlock> rsvchanBlockList);
}
