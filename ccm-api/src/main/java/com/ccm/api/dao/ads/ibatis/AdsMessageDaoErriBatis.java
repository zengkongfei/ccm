/**
 * 
 */
package com.ccm.api.dao.ads.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.ads.AdsMessageDao;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.ads.vo.AdsLogMessageCriteria;
import com.ccm.api.model.ads.vo.AdsMessageResult;
import com.ccm.api.model.bdp.AdsPushErrorCount;
import com.ccm.api.model.rsvtype.AdsToTBLog;

@Repository("adsMessageErrDao")
public class AdsMessageDaoErriBatis extends GenericDaoiBatis<AdsMessage, String> implements AdsMessageDao{


	public AdsMessageDaoErriBatis() {
		super(AdsMessage.class);
	}

	public AdsMessage save(AdsMessage ads){
	  ads.setAdsId(UUID.randomUUID().toString().replace("-", ""));
	  ads.setCreatedTime(new Date());
      return  (AdsMessage) getSqlMapClientTemplate().insert("addAdsMessageErr", ads);
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
        Integer skipResults = (amc.getPageNum() - 1)* amc.getPageSize();
        Integer maxResults = amc.getPageSize();
        List<AdsMessage> resultList = getSqlMapClientTemplate().queryForList("searchAdsMessageErr",amc,skipResults,maxResults);
        return resultList;
    }
    
    @Override
	public Integer searchAdsLogCount(AdsLogMessageCriteria amc){
    	List<Integer> countList = getSqlMapClientTemplate().queryForList("searchAdsMessageErrCount",amc);
    	int count=0;
    	for (Integer integer : countList) {
    		count +=integer;
		}
	    return count;
	}

    @Override
    public String getAdsMessageFieldValue(HashMap<String, String> map) {
    	Object obj = getSqlMapClientTemplate().queryForObject("getAdsMessageErrFieldValue",map);
        return obj!=null ? obj.toString() : "";
    }


    @Override
    public AdsMessage getAdsMessageByEchoTokenAndAdsType(String echoToken,
            String adsType) {
        HashMap<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("echoToken", echoToken);
        paramMap.put("adsType", adsType);
        return (AdsMessage)getSqlMapClientTemplate().queryForObject("getAdsMessageErrByEchoTokenAndAdsType",paramMap);
    }

    @Override
    public void updateAdsMessageStatus(String adsId, String status,String errMsg,String hotelCode) {
        HashMap<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("adsId", adsId);
        paramMap.put("hotelCode", hotelCode);
        paramMap.put("status", status);
        paramMap.put("errMsg", errMsg);
        getSqlMapClientTemplate().update("updateAdsMessageErr",paramMap);
    }

	@Override
	public AdsMessage getLastMsg(AdsLogMessageCriteria adsmsg) {
		return null;
	}

    @Override
    public List<AdsToTBLog> getTbLog(AdsToTBLog tbLog) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Map<String, Object>> getInventoryByCode(String hotelCode,
            String roomTypeCode, String ratePlanCode, Date startDate,
            Date endDate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AdsMessage> getParamCode(String msgtypePush) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getAdsToTBLogFieldValue(HashMap<String, String> map) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AdsMessage getMaxReqDateAdsMessage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AdsMessage> getParamCode() {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public int deleteAdsMesssageByCreatedTimeAndHotelCode(String createdTime,
			String hotelcode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AdsPushErrorCount> getAdsErrorMsgLastDate(
			AdsPushErrorCount adsPushErrorCount) {
		// TODO Auto-generated method stub
		return  getSqlMapClientTemplate().queryForList("getAdsErrorForBdp",adsPushErrorCount);
	}

    
}
