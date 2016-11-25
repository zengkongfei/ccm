package com.ccm.api.model.rsvtype.vo;

public class RoomStatusJsonVO {

	private String date;//日期
	private Integer physicalRooms;//物理房量
	private Integer unavailable;//总占房量
	private Integer allotment;//渠道保留房
	private Integer roomTypeOverbook;//超卖总数
	private Integer overbookAmount;//超卖房量
	private Boolean freeSell;//开关
	private Integer outoforder;
    private Integer totalOBSold;//已超卖总房量
    private Integer allotmentSold;//已卖保留房
    private Integer obSold;    //已售超预定
    private Integer sold;      //已售房量
    private Integer available; //可用房量
	private Integer overBooking;//超卖房量额
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getPhysicalRooms() {
		return physicalRooms;
	}
	public void setPhysicalRooms(Integer physicalRooms) {
		this.physicalRooms = physicalRooms;
	}
	public Integer getAllotment() {
		return allotment;
	}
	public void setAllotment(Integer allotment) {
		this.allotment = allotment;
	}
	public Integer getRoomTypeOverbook() {
		return roomTypeOverbook;
	}
	public void setRoomTypeOverbook(Integer roomTypeOverbook) {
		this.roomTypeOverbook = roomTypeOverbook;
	}
	public Integer getOverbookAmount() {
		return overbookAmount;
	}
	public void setOverbookAmount(Integer overbookAmount) {
		this.overbookAmount = overbookAmount;
	}
	public Boolean getFreeSell() {
		return freeSell;
	}
	public void setFreeSell(Boolean freeSell) {
		this.freeSell = freeSell;
	}
	public Integer getUnavailable() {
		return unavailable;
	}
	public void setUnavailable(Integer unavailable) {
		this.unavailable = unavailable;
	}
	public Integer getOutoforder() {
		return outoforder;
	}
	public void setOutoforder(Integer outoforder) {
		this.outoforder = outoforder;
	}
	public Integer getOverBooking() {
        return overBooking;
    }
    public void setOverBooking(Integer overBooking) {
        this.overBooking = overBooking;
    }
    public Integer getTotalOBSold() {
        return totalOBSold;
    }
    public void setTotalOBSold(Integer totalOBSold) {
        this.totalOBSold = totalOBSold;
    }
    public Integer getAllotmentSold() {
        return allotmentSold;
    }
    public void setAllotmentSold(Integer allotmentSold) {
        this.allotmentSold = allotmentSold;
    }
    public Integer getObSold() {
        return obSold;
    }
    public void setObSold(Integer obSold) {
        this.obSold = obSold;
    }
    public Integer getSold() {
        return sold;
    }
    public void setSold(Integer sold) {
        this.sold = sold;
    }
    public Integer getAvailable() {
        return available;
    }
    public void setAvailable(Integer available) {
        this.available = available;
    }
}
