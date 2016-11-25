package com.ccm.api.dao.pms.form;

public class RateForm {
	private String type;// guestroom表中的roomTypeCode字段
	private String gid;// 淘宝的房型ID
	private String start;
	private String end;
	private String rate;
	private String rateweekend;
	private String hotelid;
	private String channel;
	private String[] types;

	private String flag;// 用于区别调整价格还是设置价格标志
	
	private String lockNum;

	public String getLockNum() {
		return lockNum;
	}

	public void setLockNum(String lockNum) {
		this.lockNum = lockNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getRateweekend() {
		return rateweekend;
	}

	public void setRateweekend(String rateweekend) {
		this.rateweekend = rateweekend;
	}

	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
