package com.ccm.api.model.ratePlan.vo;

import java.util.Date;
import java.util.List;
import com.ccm.api.model.base.criteria.SearchCriteria;

public class RoomStatusAndProductSwitchCriteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8661904893253241717L;

	private String specialist;  //酒店维护专员
	private Date startDate;     //开始日期
	private Date endDate;       //结束日期
	private String type;        //类型(1:房态,2:产品)
	
	private Boolean includeRoomStatus; //包含房态记录
	private Boolean includeProduct;    //包含产品记录
	private Boolean isUnion;           //是否要串联起来
	
	private List<String> hotelCodes;
	private List<String> channelCodes;
	private List<String> roomTypeCodes;
	private List<String> ratePlanCodes;
	
	private boolean excelFlag = false;// 导出标志
	
	public String getSpecialist() {
		return specialist;
	}
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getIncludeRoomStatus() {
		return includeRoomStatus;
	}
	public void setIncludeRoomStatus(Boolean includeRoomStatus) {
		this.includeRoomStatus = includeRoomStatus;
	}
	public Boolean getIncludeProduct() {
		return includeProduct;
	}
	public void setIncludeProduct(Boolean includeProduct) {
		this.includeProduct = includeProduct;
	}
	public Boolean getIsUnion() {
		return isUnion;
	}
	public void setIsUnion(Boolean isUnion) {
		this.isUnion = isUnion;
	}
	public List<String> getHotelCodes() {
		return hotelCodes;
	}
	public void setHotelCodes(List<String> hotelCodes) {
		this.hotelCodes = hotelCodes;
	}
	public List<String> getChannelCodes() {
		return channelCodes;
	}
	public void setChannelCodes(List<String> channelCodes) {
		this.channelCodes = channelCodes;
	}
	public List<String> getRoomTypeCodes() {
		return roomTypeCodes;
	}
	public void setRoomTypeCodes(List<String> roomTypeCodes) {
		this.roomTypeCodes = roomTypeCodes;
	}
	public List<String> getRatePlanCodes() {
		return ratePlanCodes;
	}
	public void setRatePlanCodes(List<String> ratePlanCodes) {
		this.ratePlanCodes = ratePlanCodes;
	}
	public boolean isExcelFlag() {
		return excelFlag;
	}
	public void setExcelFlag(boolean excelFlag) {
		this.excelFlag = excelFlag;
	}
	@Override
	public String toString() {
		return "RoomStatusAndProductSwitchCriteria [specialist=" + specialist
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", type=" + type + ", includeRoomStatus=" + includeRoomStatus
				+ ", includeProduct=" + includeProduct + ", isUnion=" + isUnion
				+ ", hotelCodes=" + hotelCodes + ", channelCodes="
				+ channelCodes + ", roomTypeCodes=" + roomTypeCodes
				+ ", ratePlanCodes=" + ratePlanCodes + ", excelFlag="
				+ excelFlag + "]";
	}
	
}
