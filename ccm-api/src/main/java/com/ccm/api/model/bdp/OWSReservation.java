package com.ccm.api.model.bdp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OWSReservation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8746979877597851903L;
	
	private String id;
	private String fromSource;
	private String resortCode;
	private String resortName;
	private String confirmationNo;
	private String chainCode;
	private String chainName;
	private String channelCode;
	private String channelDes;
	private Date arrivalDate;
	private Date departureDate;
	private String roomType;
	private String rateCode;
	private BigDecimal rate;
	private String guestFirstName;
	private String guestLastName;
	private Integer numOfRooms;
	private Integer roomNights;
	private String comments;
	private Date originBookingDate;
	private Date updateBookingDate;
	private String status;
	private String channelUniqueResID;
	private BigDecimal totalRate;
	private String guaranteeType;
	private BigDecimal deduct;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFromSource() {
		return fromSource;
	}
	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
	}
	public String getResortCode() {
		return resortCode;
	}
	public void setResortCode(String resortCode) {
		this.resortCode = resortCode;
	}
	public String getResortName() {
		return resortName;
	}
	public void setResortName(String resortName) {
		this.resortName = resortName;
	}
	public String getConfirmationNo() {
		return confirmationNo;
	}
	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	public String getChainName() {
		return chainName;
	}
	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getChannelDes() {
		return channelDes;
	}
	public void setChannelDes(String channelDes) {
		this.channelDes = channelDes;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getGuestFirstName() {
		return guestFirstName;
	}
	public void setGuestFirstName(String guestFirstName) {
		this.guestFirstName = guestFirstName;
	}
	public String getGuestLastName() {
		return guestLastName;
	}
	public void setGuestLastName(String guestLastName) {
		this.guestLastName = guestLastName;
	}
	public Integer getNumOfRooms() {
		return numOfRooms;
	}
	public void setNumOfRooms(Integer numOfRooms) {
		this.numOfRooms = numOfRooms;
	}
	public Integer getRoomNights() {
		return roomNights;
	}
	public void setRoomNights(Integer roomNights) {
		this.roomNights = roomNights;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getOriginBookingDate() {
		return originBookingDate;
	}
	public void setOriginBookingDate(Date originBookingDate) {
		this.originBookingDate = originBookingDate;
	}
	public Date getUpdateBookingDate() {
		return updateBookingDate;
	}
	public void setUpdateBookingDate(Date updateBookingDate) {
		this.updateBookingDate = updateBookingDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChannelUniqueResID() {
		return channelUniqueResID;
	}
	public void setChannelUniqueResID(String channelUniqueResID) {
		this.channelUniqueResID = channelUniqueResID;
	}
	public BigDecimal getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(BigDecimal totalRate) {
		this.totalRate = totalRate;
	}
	public String getGuaranteeType() {
		return guaranteeType;
	}
	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	public BigDecimal getDeduct() {
		return deduct;
	}
	public void setDeduct(BigDecimal deduct) {
		this.deduct = deduct;
	}
}
