/**
 * 
 */
package com.ccm.api.dao.ads.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.ads.AdsMessageDao;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.ads.vo.AdsLogMessageCriteria;
import com.ccm.api.model.ads.vo.AdsMessageResult;
import com.ccm.api.model.bdp.AdsPushErrorCount;
import com.ccm.api.model.rsvtype.AdsToTBLog;
import com.ccm.api.util.CommonUtil;
import com.ccm.api.util.DateUtil;

@Repository("adsMessageDao")
public class AdsMessageDaoiBatis extends GenericDaoiBatis<AdsMessage, String> implements AdsMessageDao{


	public AdsMessageDaoiBatis() {
		super(AdsMessage.class);
	}

    @Override
    public AdsMessageResult searchAdsLog(AdsLogMessageCriteria amc) {
        AdsMessageResult adsVoResult = new AdsMessageResult();
        List<AdsMessage> resultList = searchAdsLogList(amc);
        adsVoResult.setResultList(resultList);
        adsVoResult.setTotalCount(searchAdsLogCount(amc));
        return adsVoResult;
    }
    
    @Override
    public List<AdsMessage> searchAdsLogList(AdsLogMessageCriteria amc) { 
        List<AdsMessage> resultList = getSqlMapClientTemplate().queryForList("searchAdsMessage",amc);
        return resultList;
    }
    
    @Override
	public Integer searchAdsLogCount(AdsLogMessageCriteria amc){
    	List<Integer> countList = getSqlMapClientTemplate().queryForList("searchAdsMessageCount",amc);
    	int count=0;
    	for (Integer integer : countList) {
    		count +=integer;
		}
	    return count;
	}

    @Override
    public List<AdsToTBLog> getTbLog(AdsToTBLog tbLog) {
        return getSqlMapClientTemplate().queryForList("searchAdsToTBLog",tbLog);
    }

    @Override
    public String getAdsMessageFieldValue(HashMap<String, String> map) {
    	Object obj = getSqlMapClientTemplate().queryForObject("getAdsMessageFieldValue",map);
        return obj!=null ? obj.toString() : "";
    }

    @Override
    public String getAdsToTBLogFieldValue(HashMap<String, String> map) {
    	Object obj = getSqlMapClientTemplate().queryForObject("getAdsToTBLogFieldValue",map);
        return obj!=null ? obj.toString() : "";
    }

    @Override
    public AdsMessage getMaxReqDateAdsMessage() {
        return (AdsMessage) getSqlMapClientTemplate().queryForObject("getMaxReqDateAdsMessage");
    }

    @Override
    public List<AdsMessage> getParamCode() {
        return getSqlMapClientTemplate().queryForList("getParamCode");
    }

    @Override
    public AdsMessage getAdsMessageByEchoTokenAndAdsType(String echoToken,
            String adsType) {
        HashMap<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("echoToken", echoToken);
        paramMap.put("adsType", adsType);
        return (AdsMessage)getSqlMapClientTemplate().queryForObject("getAdsMessageByEchoTokenAndAdsType",paramMap);
    }

    @Override
    public List<Map<String, Object>> getInventoryByCode(String hotelCode,
            String roomTypeCode, String ratePlanCode,Date startDate,Date endDate) {
        HashMap<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("hotelCode", hotelCode);
        paramMap.put("roomTypeCode",roomTypeCode);
        paramMap.put("ratePlanCode", ratePlanCode);
        paramMap.put("startDate", DateUtil.convertDateToString(startDate));
        paramMap.put("endDate", DateUtil.convertDateToString(endDate));
        return getSqlMapClientTemplate().queryForList("getInventoryByCode",paramMap);
    }

    @Override
    public void updateAdsMessageStatus(String adsId, String status,String errMsg,String hotelCode) {
        HashMap<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("adsId", adsId);
        paramMap.put("hotelCode", hotelCode);
        paramMap.put("status", status);
        paramMap.put("errMsg", errMsg);
        getSqlMapClientTemplate().update("updateAdsMessageStatus",paramMap);
    }

    @Override
    public List<AdsMessage> getParamCode(
            String msgtypePush) {
        return getSqlMapClientTemplate().queryForList("getParamCode",msgtypePush);
    }

	@Override
	public AdsMessage getLastMsg(AdsLogMessageCriteria adsmsg) {
		List<AdsMessage> resultList = getSqlMapClientTemplate().queryForList("searchAdsMessage",adsmsg,0,1);
		if(CommonUtil.isNotEmpty(resultList)){
			return resultList.get(0);
		}
		return null;
	}
	
	 /**
	 * 根据日期与酒店编号删除adsmessage表的数据并返回影响记录的条数
	 * @param date
	 * @param hotelcode
	 * @return
	 */
	
	@Override
	public int deleteAdsMesssageByCreatedTimeAndHotelCode(String createdTime, String hotelcode) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
		params.put("createdTime",createdTime);
		params.put("hotelcode", hotelcode);
		
		return getSqlMapClientTemplate().delete("deleteAdsMesssageByCreatedTimeAndHotelCode", params);
		
	}

	@Override
	public List<AdsPushErrorCount> getAdsErrorMsgLastDate(
			AdsPushErrorCount adsPushErrorCount) {
		// TODO Auto-generated method stub
		return null;
	}
}
