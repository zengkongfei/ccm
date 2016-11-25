package com.ccm.api.model.ads;

import java.util.Date;
import java.util.HashMap;

import com.ccm.api.common.Column;
import com.ccm.api.model.base.BaseObject;

/**
 * 接收ads推送消息类
 */
public class AdsMessage extends BaseObject {

    private static final long serialVersionUID = 2241610909495825852L;

    private String adsId;
    
    @Column(title="接收状态")
    private String acceptStatus;
    @Column(title="渠道代码")
    private String targetGDS;
    @Column(title="集团代码")
    private String chainCode;
    @Column(title="酒店代码")
    private String hotelCode;
    @Column(title="房型代码")
    private String roomTypeCode;
    @Column(title="协议类型")
    private String adsType;
    @Column(title="EchoToken")
    private String echoToken;
    @Column(title="消息记录时间")
    private Date createdTime;
    @Column(title="信息详情")
    private String actionValue;
    
    private String content;
    private String status;
    private String errMsg;
    @Column(title="rquestTbData")
    private String rquestTbData;
    private Date lastRequestDate;
    
    private Date actionDate;
    
    private String ratePlanCode;
    private String tbStatus; //SEND_ERROR_TBSTATUS
    public String msgType; //消息类型，推送，接受
    
    
    public static final String MSGTYPE_RECEIVE="1";  //接收
    public static final String MSGTYPE_PUSH="2"; //推送
    
    public static final String EXEC_INIT_STATUS = "0";// 初始为 0 为执行 ，pending
    public static final String EXEC_END_STATUS = "1";// 1 为 已执行     ，success
    public static final String EXEC_ERROR_STATUS = "9";// 9 执行错误
    
    public static final String SEND_ERROR_TBSTATUS = "0";// 发送失败
    public static final String SEND_SUCCESS_TBSTATUS = "1";// 1 发送成功
    
    public static final String FIDELIO_AllMsgRQ = "FIDELIO_AllMsgRQ";					//所有
    public static final String ADSTYPE_AVAILABILITY = "FIDELIO_AvailabilityStatusRQ";	//房量
    public static final String ADSTYPE_RATE = "FIDELIO_RateUpdateNotifRQ";				//房价
    public static final String ADSTYPE_AVAILUPDATE = "FIDELIO_AvailUpdateNotifRQ";		//开关
    public static final String ADSTYPE_AVAILSTATUS = "FIDELIO_AvailStatusNotifRQ";		//房态
    public static final String ADSTYPE_BLOCK="FIDELIO_BlockStatusNotifRQ";				//BLOCK
    public static final String ADSTYPE_HOTELPRODUCT="FIDELIO_HotelProductNotifRQ";		//酒店全量静态消息
    
    public static final String ADSTYPE_HOTELCODE = "HOTELCODE";		//酒店代码类型
    public static final String ADSTYPE_ROOMCODE = "ROOMCODE";		//房型代码类型
    public static final String ADSTYPE_RATECODE = "RATECODE";		//房价代码类型
	public static final String ADSTYPE_OTAHOTERRESMODIFY = "OTAHotelResModify"; // OTA订单身份证上传类型
    
    public static final String STAYHISTORYNOTIFICATION = "Switch_StayHistoryNotificationRQ";
    
    private static HashMap<String,HashMap<String,String>> chainMap = new HashMap<String, HashMap<String,String>>();
    
    
    
    public String getAcceptStatus() {
        if(status!=null){
            if(status.equals("0") ){
                acceptStatus="未执行";
            }else if(status.equals("1") ){
                acceptStatus="接收成功";
            }
            else if(status.equals("9") ){
                acceptStatus="接收异常";
            }
        }
        return acceptStatus;
    }

    public static HashMap<String,String> getHotelByChainCode(String chainCode){
        return chainMap.get(chainCode);
    }
    
    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public String getRquestTbData() {
        return rquestTbData;
    }

    public void setRquestTbData(String rquestTbData) {
        this.rquestTbData = rquestTbData;
    }

    public String getAdsId() {
        return adsId;
    }

    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdsType() {
        return adsType;
    }

    public void setAdsType(String adsType) {
        this.adsType = adsType;
    }

    public String getEchoToken() {
        return echoToken;
    }

    public void setEchoToken(String echoToken) {
        this.echoToken = echoToken;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getChainCode() {
        return chainCode;
    }

    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getTargetGDS() {
        return targetGDS;
    }

    public void setTargetGDS(String targetGDS) {
        this.targetGDS = targetGDS;
    }

    public Date getLastRequestDate() {
        return lastRequestDate;
    }

    public void setLastRequestDate(Date lastRequestDate) {
        this.lastRequestDate = lastRequestDate;
    }

    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    public String getTbStatus() {
        return tbStatus;
    }

    public void setTbStatus(String tbStatus) {
        this.tbStatus = tbStatus;
    }

    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
    
}
