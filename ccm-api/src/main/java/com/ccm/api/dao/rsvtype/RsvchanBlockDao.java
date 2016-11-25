package com.ccm.api.dao.rsvtype;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.RsvtypeChannel;

public interface RsvchanBlockDao extends GenericDao <RsvchanBlock, String> {

	RsvchanBlock saveRsvchanBlock(RsvchanBlock rsvchanBlock);
	Integer updateRsvchanBlock(RsvchanBlock rsvchanBlock);
	Integer updateRsvchanBlockSold(RsvchanBlock rsvchanBlock);
	RsvchanBlock getRsvchanBlockByParam(Map<String,Object> paramMap);
	List<RsvchanBlock> getRsvchanBlocksByRsvchanelIds(Map<String,Object> paramMap);
	List<RsvchanBlock> getRsvchanBlocksByChannelAndBlock(String hotelCode,String channelCode,String blockCode);
	List<RsvchanBlock> getRsvchanBlocksByRsvchanelId(String rsvchanelId);
	Map<String,List<RsvchanBlock>> getBlockChannelAilable(List<RsvtypeChannel> rcList);
	RsvchanBlock getBlockListByRsvChanIdAndBlockCode(String rsvtypeChannelId,String blockCode);
	Integer removeRsvchanBlock(String hotelCode,String channelCode,Date inventoryDate,String roomType,String invBlockGroupingCode);
	Integer removeRsvchanBlocksByRsvchanelId(String rsvchannelId);
	Integer removeRsvchanBlockByRsvchannelIdAndBlockCode(String rsvchannelId,String blockCode);
	/**
	 * 下传到pms需要带block Code
	 * @param hotelCode
	 * @param channelCode
	 * @param date
	 * @param roomType
	 * @return
	 */
	List<RsvchanBlock> getIsSendToPMSRsvchanBlock(String hotelCode,String channelCode,List<String> dateList,String roomType);
	List<RsvchanBlock> getHandSendRsvchanBlock(String hotelCode,String channelCode, String date, String roomType);
	List<RsvchanBlock> getRsvchanBlocksByOrder(String hotelCode,String channelCode,List<String> dateList,String roomType);
	
	/**
	 * 查找block房量
	 * @param channelCode
	 * @param type
	 * @param hotelCode
	 * @param date
	 * @return
	 */
	RsvchanBlock getRsvchanBlockNum(String channelCode,String type,String hotelCode,Date date);
	
	Integer getBlockCountWithoutRates(String rsvtypeChannelId);
}
