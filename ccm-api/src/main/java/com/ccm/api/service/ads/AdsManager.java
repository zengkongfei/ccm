package com.ccm.api.service.ads;

import java.util.HashMap;
import java.util.List;

import com.ccm.api.model.ads.AdsMessage;
import com.ccm.api.model.ads.vo.AdsLogMessageCriteria;
import com.ccm.api.model.ads.vo.AdsMessageResult;
import com.ccm.api.model.ads.vo.AdsVO;
import com.ccm.api.model.rsvtype.AdsToTBLog;
import com.ccm.api.service.base.GenericManager;

/**
 * 
 * Ads 相关业务方法
 *
 */
public interface AdsManager extends GenericManager<AdsMessage, String> {
    
	/**	
     * 保存ads
     */
	public AdsMessage createAdsMessage(AdsMessage adsMsg);
	

	/**
	 * 保存直连房量数据
	 * @return 
	 */
	public List<HashMap<String, String>> saveAvailablilityStatus(List<AdsVO> availList);
	
	
	/**
	 * 更新直连房量数据
	 * @return 
	 */
	public List<HashMap<String, String>> updateAvailablilityStatus(List<AdsVO> availList);
	
	
	/**
	 * 更新直连房价数据
	 */
	public List<HashMap<String,String>> saveRateUpdate(List<AdsVO> rateUpdateList);


    public AdsMessageResult searchAdsLog(AdsLogMessageCriteria amc);


    public List<AdsToTBLog> getTbLog(AdsToTBLog tbLog);


    public String getAdsMessageFieldValue(String field, String adsId,String hotelCode, String status);


    public String getAdsToTBLogFieldValue(String field, String adsToTBLogId);


    public AdsToTBLog getAdsToTBLog(String adsToTBLogId);


    public boolean pushToTaobao(AdsMessage adsMsg) throws Exception;


    public AdsMessage getMaxReqDateAdsMessage();


    public HashMap<String, HashMap<String, String>> getParamCode();


    public HashMap<String, String> getHotelByChainId(String string);


    public HashMap<String, String> getRoomByHotelId(String hotelId);


    public void sendRoomChangeToTB(HashMap<String, String> map, String adsType, AdsMessage adsMsg) throws Exception;


    public List<AdsMessage> exportAdsLog(AdsLogMessageCriteria amc);


	AdsToTBLog saveAdsToTBLog(String echoToken, String adsType, Integer status,
			String content, String errMsg);

}