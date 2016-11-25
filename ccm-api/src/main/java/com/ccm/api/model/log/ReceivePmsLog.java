/**
 * 
 */
package com.ccm.api.model.log;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;
import com.ccm.api.util.DateUtil;

/**
 * @author Jenny
 * 
 */
public class ReceivePmsLog extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4040166497815621385L;

	private String receivepmslogId;

	private String interfaceId;// 接口ID

	private String hotelCode;// 酒店代码

	private Date receiveMsgLogLastTime;// 收到PMS上传消息的最后一条时间

	// 用于返回结果集
	private String spaceSec;
	private String status;// 状态
	private String hotelName;
	private String pmsType;
	private String isCreditOnlineHotel;
	private String isDisplacementInterface;
	private Boolean isPMSHeartBeat ;//是否监控PMS心跳
	private String hotelId;
	
	public Boolean getIsPMSHeartBeat() {
		return isPMSHeartBeat;
	}

	public void setIsPMSHeartBeat(Boolean isPMSHeartBeat) {
		this.isPMSHeartBeat = isPMSHeartBeat;
	}

	public String getIsCreditOnlineHotel() {
		return isCreditOnlineHotel;
	}

	public void setIsCreditOnlineHotel(String isCreditOnlineHotel) {
		this.isCreditOnlineHotel = isCreditOnlineHotel;
	}

	public String getIsDisplacementInterface() {
		return isDisplacementInterface;
	}

	public void setIsDisplacementInterface(String isDisplacementInterface) {
		this.isDisplacementInterface = isDisplacementInterface;
	}

	public String getReceivepmslogId() {
		return receivepmslogId;
	}

	public void setReceivepmslogId(String receivepmslogId) {
		this.receivepmslogId = receivepmslogId;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public Date getReceiveMsgLogLastTime() {
		return receiveMsgLogLastTime;
	}

	public void setReceiveMsgLogLastTime(Date receiveMsgLogLastTime) {
		this.receiveMsgLogLastTime = receiveMsgLogLastTime;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusResult() {
		try {
			if (!DateUtil.addMinutes(getLastModifyTime(), 5).before(getCreatedTime()) || Integer.valueOf(getSpaceSec()) / 60 <= 5) {
				return "on";
			} else {
				return "off";
			}
		} catch (Exception e) {
			return "off";
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpaceSec() {
		return spaceSec;
	}

	public void setSpaceSec(String spaceSec) {
		this.spaceSec = spaceSec;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getPmsType() {
		return pmsType;
	}

	public void setPmsType(String pmsType) {
		this.pmsType = pmsType;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	@Override
	public String toString() {
		return "ReceivePmsLog [receivepmslogId=" + receivepmslogId + ", interfaceId=" + interfaceId + ", hotelCode=" + hotelCode + ", receiveMsgLogLastTime=" + receiveMsgLogLastTime + ", spaceSec=" + spaceSec + ", status=" + status + ", hotelName=" + hotelName + ", pmsType=" + pmsType + ", isCreditOnlineHotel=" + isCreditOnlineHotel + ", isDisplacementInterface=" + isDisplacementInterface + ", isPMSHeartBeat=" + isPMSHeartBeat + ", hotelId=" + hotelId + "]";
	}


}
