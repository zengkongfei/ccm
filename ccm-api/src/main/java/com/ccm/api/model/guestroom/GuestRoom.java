package com.ccm.api.model.guestroom;

import java.util.Date;
import java.util.Map;


public class GuestRoom {
	/**
	* guestRoomId	CHAR(32)
	*/
	private String guestRoomId;
	/**
	* hotelId	CHAR(32)
	*/
	private String hotelId;
	/**
	* roomTypeCode	VARCHAR(16)
	*/
	private String roomTypeCode;
	/**
	* standardOccupancy	INT(11)
	*/
	private Integer standardOccupancy;
	/**
	* maxOccupancy	INT(11)
	*/
	private Integer maxOccupancy;
	/**
	* maxAdultOccupancy	INT(11)
	*/
	private Integer maxAdultOccupancy;
	/**
	* maxChildOccupancy	INT(11)
	*/
	private Integer maxChildOccupancy;
	/**
	* standardNumBeds	INT(11)
	*/
	private Integer standardNumBeds;
	/**
	* bedTypeCode	INT(11)
	*/
	private Integer bedTypeCode;
	/**
	* breakfast	INT(11)
	*/
	private Integer breakfast;
	
	private Boolean delFlag;
	/**
	* status	INT(11)
	*/
	private Integer status;
	/**
	* size	FLOAT(12)
	*/
	private Float size;

    private Date createdTime;
    
    private Date lastModifiedTime;
    
    private Map<String, String> serviceMap;
    
	public String getGuestRoomId() {
		return guestRoomId;	
	}
	public void setGuestRoomId(String guestRoomId) {
		this.guestRoomId = guestRoomId;
	}
	public String getHotelId() {
		return hotelId;	
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getRoomTypeCode() {
		return roomTypeCode;	
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public Integer getStandardOccupancy() {
		return standardOccupancy;	
	}
	public void setStandardOccupancy(Integer standardOccupancy) {
		this.standardOccupancy = standardOccupancy;
	}
	public Integer getMaxOccupancy() {
		return maxOccupancy;	
	}
	public void setMaxOccupancy(Integer maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public Integer getMaxAdultOccupancy() {
		return maxAdultOccupancy;	
	}
	public void setMaxAdultOccupancy(Integer maxAdultOccupancy) {
		this.maxAdultOccupancy = maxAdultOccupancy;
	}
	public Integer getMaxChildOccupancy() {
		return maxChildOccupancy;	
	}
	public void setMaxChildOccupancy(Integer maxChildOccupancy) {
		this.maxChildOccupancy = maxChildOccupancy;
	}
	public Integer getStandardNumBeds() {
		return standardNumBeds;	
	}
	public void setStandardNumBeds(Integer standardNumBeds) {
		this.standardNumBeds = standardNumBeds;
	}
	public Integer getBedTypeCode() {
		return bedTypeCode;	
	}
	public void setBedTypeCode(Integer bedTypeCode) {
		this.bedTypeCode = bedTypeCode;
	}
	public Integer getBreakfast() {
		return breakfast;	
	}
	public void setBreakfast(Integer breakfast) {
		this.breakfast = breakfast;
	}
	public Integer getStatus() {
		return status;	
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Float getSize() {
		return size;	
	}
	public void setSize(Float size) {
		this.size = size;
	}
    public Date getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
    public Map<String, String> getServiceMap() {
        return serviceMap;
    }
    public void setServiceMap(Map<String, String> serviceMap) {
        this.serviceMap = serviceMap;
    }
	public Boolean getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	
	
}
