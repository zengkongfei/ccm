package com.ccm.api.model.ads.vo;

import java.util.Date;
/**
 * 接收ads推送消息类 
 */
public class AdsMessageVo{
	private static final long serialVersionUID = 2241610909495825852L;
	private String adsId;
    private String adsContent;
    private Date   adsCreatedTime;
    private String adsStatus;
    private String adsMsgType; 
    private String adsEchoToken;
    private String adsErrMsg;
    private String rquestTbData;
	
    public String getAdsId() {
        return adsId;
    }
    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }
    public String getAdsContent() {
        return adsContent;
    }
    public void setAdsContent(String adsContent) {
        this.adsContent = adsContent;
    }
    public Date getAdsCreatedTime() {
        return adsCreatedTime;
    }
    public void setAdsCreatedTime(Date adsCreatedTime) {
        this.adsCreatedTime = adsCreatedTime;
    }
    public String getAdsStatus() {
        return adsStatus;
    }
    public void setAdsStatus(String adsStatus) {
        this.adsStatus = adsStatus;
    }
    
    public String getAdsMsgType() {
        return adsMsgType;
    }
    public void setAdsMsgType(String adsMsgType) {
        this.adsMsgType = adsMsgType;
    }
    public String getAdsEchoToken() {
        return adsEchoToken;
    }
    public void setAdsEchoToken(String adsEchoToken) {
        this.adsEchoToken = adsEchoToken;
    }
    public String getAdsErrMsg() {
        return adsErrMsg;
    }
    public void setAdsErrMsg(String adsErrMsg) {
        this.adsErrMsg = adsErrMsg;
    }
    public String getRquestTbData() {
        return rquestTbData;
    }
    public void setRquestTbData(String rquestTbData) {
        this.rquestTbData = rquestTbData;
    }
	
}
