package com.ccm.api.model.rsvtype;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * ads接口发送到淘宝日志
 * @author carr
 *
 */
public class AdsToTBLog extends BaseObject {

	private static final long serialVersionUID = 2154959428034886840L;
	
	private String adsToTBLogId;//	ID
	private String echoToken;//	ads接口标识
	private String adsType;//	接口名称
	private String content;//	传输数据
	private Integer status;//	状态
	private Date createdTime;//	创建时间
	private String errMsg;
	
	public String getAdsToTBLogId() {
		return adsToTBLogId;
	}
	public void setAdsToTBLogId(String adsToTBLogId) {
		this.adsToTBLogId = adsToTBLogId;
	}
	public String getEchoToken() {
		return echoToken;
	}
	public void setEchoToken(String echoToken) {
		this.echoToken = echoToken;
	}
	public String getAdsType() {
		return adsType;
	}
	public void setAdsType(String adsType) {
		this.adsType = adsType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public String getErrMsg() {
        return errMsg;
    }
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    @Override
	public String toString() {
		return "AdsToTBLog [adsToTBLogId=" + adsToTBLogId + ", echoToken="
				+ echoToken + ", adsType=" + adsType + ", content=" + content
				+ ", status=" + status + ", createdTime=" + createdTime + "]";
	}
    
}
