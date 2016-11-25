package com.ccm.api.service.rsvtype;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ccm.api.model.rsvtype.RsvChangeInfo;
import com.ccm.api.model.rsvtype.RsvchanBlock;
import com.ccm.api.model.rsvtype.Rsvtype;
import com.ccm.api.model.rsvtype.RsvtypeChannel;
import com.ccm.api.model.rsvtype.vo.RoomStatusSetVO;
import com.ccm.api.model.rsvtype.vo.RoomStatusVO;
import com.ccm.api.service.base.GenericManager;

public interface RsvtypeChannelManager extends GenericManager<RsvtypeChannel, String> {

    RsvtypeChannel setAllotmentAndFreeSell(RoomStatusVO roomStatusVO, Boolean isPush);

    Map<String,List<Rsvtype>> getRsvtypeByChannelIdsRoomTypeCodes(RoomStatusVO vo);
    /**验证房量*/
    boolean validataRsvtypeChannelAilable(RsvChangeInfo rsvChangeInfo);

    /**确认房量，并进行房量扣减*/
    boolean confirmRsvtypeChannelAilable(RsvChangeInfo rsvChangeInfo)
    throws Exception;
    
     /**已作废*/
    boolean confirmRsvtypeChannelAilable(String roomTypeCode, String hotelCode,String rateplanCode,
            Date checkinDate, Date checkoutDate, String channelCode, int roomNum)
            throws Exception;
    
 
    
    
    /**增加or扣减 REDIS里酒店房型的房量*/
    void modifyRedisResvtypeToRedis(String hotelCode,
			String roomTypeCode, Date startDate, int roomQty);
    
	/**
	 * 将可卖数量存入渠道已售总房数
	 * 
	 * @param roomTypeCode
	 * @param hotelCode
	 * @param checkinDate
	 * @param checkoutDate
	 * @param channelId
	 * @param channelCode
	 * @param roomNum
	 * @return
	 * @throws Exception
	 */
	boolean updateRsvtypeChannelSold(String roomTypeCode, String hotelCode, Date checkinDate, Date checkoutDate, String channelId, String channelCode, int roomNum) throws Exception;

    /**取消房量，并进行房量取消*/
    boolean cancelRsvtypeChannelAilable(String roomTypeCode, String hotelCode,
            Date checkinDate, Date checkoutDate, String channelCode, int roomNum,String orderRateCode,Date orderCreatedDate);
    
    /**获取发送到渠道的房量
     *  OB配额 - TotalOBSold >0   关       OB配额 - TotalOBSold
        OB配额 - TotalOBSold >0   开       CCM可卖房+（OB配额 - TotalOBSold）
        OB配额 -TotalOBSold=0     X    CCM可卖房
     * */
    int calcSendChannelAvailable(RsvtypeChannel rsvtypeChannel) throws Exception;
    
    /**取到每一天的房量,【注】：checkoutDate 这一天不包含 */
    List<RsvtypeChannel> getRsvtypeChannelAvailable(String roomTypeCode,
            String hotelCode, Date checkinDate, Date checkoutDate,
            String channelCode);

    /**获取BLOCK每一天的房量,【注】：checkoutDate 这一天不包含 */
    Map<String,List<RsvchanBlock>> getBlockChannelAvailable(List<RsvtypeChannel> rcList);
    
    int getObAvailable(RsvtypeChannel rc);
    
    /***获取该段时间最小房量*/
    int getRsvtypeChannelMinAvailable(String roomTypeCode, String hotelCode,
            Date checkinDate, Date checkoutDate, String channelCode,String ratePlanCode);

    /***获取该天房量,包括酒店，渠道房量*/
    int getRsvtypeChannelAvailableByDate(String roomTypeCode, String hotelCode,
            Date date,String channelCode,String ratePlanCode);
    /***批量添加渠道房量*/
	void addBatchRsvtypeChannel(List<RsvtypeChannel> rsvList);

	void sendChangeSellDataMsg(RsvtypeChannel rsvtypeChannel);
	
    boolean confirmSoldableCondition(String hotelCode, String rateplanCode,
            Date checkinDate, Date checkoutDate, int roomNum) throws Exception;
    
    /**根据房型id，渠道id找到渠道数据
     * 不包含Rsvtype数据*/
	List<RsvtypeChannel> getRsvtypeChannelAvailable(String rsvtypeId,
			Set<String> channelIdSet);

	int queryRsvtypeChannelMinAvailable(String roomTypeCode, String hotelCode,
			Date checkinDate, Date checkoutDate, String channelCode,String rateplanCode);

	int queryRsvtypeChannelAvailableByDate(String roomTypeCode,
			String hotelCode, Date date, String channelCode,String rateplanCode);
	
	public List<RsvtypeChannel>  saveBatchRsvtypeChannel(RoomStatusSetVO setvo) throws Exception;
	
	public RsvchanBlock saveNewRsvchanBlock(String channelId,String channelCode,
			String invBlockGroupingCode, String hotelCode, String roomType,
			Date inventoryDate,Integer cutOffDays,Integer allotmentQty, Integer allotmentSold,
			Date cutOffDate,String ratePlanCodes,String rsvtypeId,String rsvtypeChannelId,Boolean isSendToPMS);
	
	public Integer	updateExistedRsvchanBlock(RsvchanBlock rsvchanBlock,Integer allotmentQty,
			Integer allotmentSold,Date cutOffDate,Integer cutOffDays,String ratePlanCodes,Boolean isSendToPMS);
	
	public boolean removeRsvchanBlockByBlockCode(String hotelCode,String channelCode,
			Date inventoryDate, String roomType, String invBlockGroupingCode);
	
	public void handleOTABlocks(String oxiTrxId,String actionType,List <RsvchanBlock> rsvchanBlockList);
	
	public void sendAvai(String hotelCode,String roomTypeCode,RsvtypeChannel rc);
	
	public Map<String,Map<Date,Integer>> getRoomTypeAllInventForWBE(String inventoryType,String hotelCode,String channelCode,String roomTypeCode, Date checkinDate, Date checkoutDate,List<String>rateplanCodeList);

	int getSendAvai(RsvtypeChannel rc);
}
