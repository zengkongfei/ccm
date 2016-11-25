package com.ccm.api.model.ratePlan.vo;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.ccm.api.model.ratePlan.RateAmount;
import com.ccm.api.model.ratePlan.RateDetail;

public class RateDetailVO extends RateDetail{

    private static final long serialVersionUID = 2037922326114974427L;
    @Id
    private String rateDetailVOId;
    private String channelCode;
    private String chainCode;// 集团代码
	private String hotelCode;// 酒店代码
	private String ratePlanCode;// 房价代码
	private String status;
	private String errMsg;
    private List<RoomRateVO> roomRateList;
    private List<RateAmount> rateAmountList;
    private String uuidSign;
    private String rateType;
    private String customerId;
    public static final String MONGO_STATUS_INIT = "0";
    public static final String MONGO_STATUS_END = "1";
    public static final String MONGO_STATUS_ERR = "9";
    public static final String RATETYPE_CCM="CCM";
    public static final String RATETYPE_PMS="PMS";
    public static final String RATETYPE_CCM_SUB_RATE="CCM_SUB_RATE";
    public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public List<RoomRateVO> getRoomRateList() {
        return roomRateList;
    }
    public void setRoomRateList(List<RoomRateVO> roomRateList) {
        this.roomRateList = roomRateList;
    }
    public List<RateAmount> getRateAmountList() {
        return rateAmountList;
    }
    public void setRateAmountList(List<RateAmount> rateAmountList) {
        this.rateAmountList = rateAmountList;
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
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	public String getRateDetailVOId() {
		return rateDetailVOId;
	}
	public void setRateDetailVOId(String rateDetailVOId) {
		this.rateDetailVOId = rateDetailVOId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getUuidSign() {
		return uuidSign;
	}
	public void setUuidSign(String uuidSign) {
		this.uuidSign = uuidSign;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
    
}
