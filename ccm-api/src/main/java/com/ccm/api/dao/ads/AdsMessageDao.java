package com.ccm.api.dao.ads;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.ads.vo.AdsLogMessageCriteria;
import com.ccm.api.model.ads.vo.AdsMessageResult;
import com.ccm.api.model.bdp.AdsPushErrorCount;
import com.ccm.api.model.rsvtype.AdsToTBLog;


public interface AdsMessageDao extends GenericDao<AdsMessage, String> {
	

    AdsMessageResult searchAdsLog(AdsLogMessageCriteria amc);

    List<AdsToTBLog> getTbLog(AdsToTBLog tbLog);

    String getAdsMessageFieldValue(HashMap<String, String> map);

    String getAdsToTBLogFieldValue(HashMap<String, String> map);

    AdsMessage getMaxReqDateAdsMessage();

    List<AdsMessage> getParamCode();
    
    /**跟据echoToken，adsType 获取最近一条消息 */
    AdsMessage getAdsMessageByEchoTokenAndAdsType(String echoToken, String adsType);

    List<Map<String, Object>> getInventoryByCode(String hotelCode,
            String roomTypeCode, String ratePlanCode, Date startDate, Date endDate);

    Integer searchAdsLogCount(AdsLogMessageCriteria amc);

    List<AdsMessage> searchAdsLogList(AdsLogMessageCriteria amc);

    void updateAdsMessageStatus(String adsId, String execEndStatus, String errMsg,String hotelCode);

    List<AdsMessage> getParamCode(String msgtypePush);

	AdsMessage getLastMsg(AdsLogMessageCriteria adsmsg);

	/**
	 * 根据日期与酒店编号删除adsmessage表的数据并返回影响记录的条数
	 * @param date
	 * @param hotelcode
	 * @return
	 */
	int deleteAdsMesssageByCreatedTimeAndHotelCode(String createdTime,String hotelcode);
	
	List<AdsPushErrorCount> getAdsErrorMsgLastDate(AdsPushErrorCount adsPushErrorCount);
}
