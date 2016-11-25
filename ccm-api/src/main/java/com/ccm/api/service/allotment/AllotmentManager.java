package com.ccm.api.service.allotment;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.RsvtypeChannel;

public interface AllotmentManager {
	boolean proxyHandleAllotment(Map<String, String> mapReq);

	public boolean createAndUpdateForWholeAllotment(String channelId,String channelCode,
			String invBlockGroupingCode, String hotelCode, String roomType,
			Date inventoryDate, Integer allotmentQty, Integer allotmentSold,
			Date cutOffDate,String ratePlanCodes,List<RsvchanBlock>removedRsvchanBlockWithRatesList,List<RsvtypeChannel>sendAvaiRsvtypeChannelList);

	public boolean removeRsvchanBlockByBlockCode(String hotelCode,String channelCode,
			Date inventoryDate, String roomType, String invBlockGroupingCode,List<RsvchanBlock>removedRsvchanBlockWithRatesList,List<RsvtypeChannel>sendAvaiRsvtypeChannelList);
	
	public void handleSendOTALog(String oxiTrxId, String messageType,
			String actionType, String hotelCode, String chainCode,String target,
			List<Map<String, String>> otaInvBlockList) ;
	
	public boolean modifyAllotmentForDetail(String channelId,String channelCode,
			String invBlockGroupingCode, String hotelCode, String roomType,
			Date inventoryDate, Integer allotmentQty, Integer allotmentSold,
			Date cutOffDate,String ratePlanCodes,List<RsvchanBlock>removedRsvchanBlockWithRatesList,List<RsvtypeChannel>sendAvaiRsvtypeChannelList);
}
