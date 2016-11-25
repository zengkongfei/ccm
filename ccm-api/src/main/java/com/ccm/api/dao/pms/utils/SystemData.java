package com.ccm.api.dao.pms.utils;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.hotel.vo.HotelVO;

public class SystemData {
	private String hotelid;
	private String userid;
	private String userName;
	private Date systemDate;
	private String sessionKey="";
	private List<HotelVO> hotelids;
	private String hotelidSqlList="";
	private String companyid;
	
	private List<Object[]> hotelidInfo = null;
	
	public List<Object[]> getHotelidInfo() {
		return hotelidInfo;
	}
	public void setHotelidInfo(List<Object[]> list) {
		this.hotelidInfo = list;
	}
	public String getHotelid() {
		return hotelid;
	}
	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getSystemDate() {
		return systemDate;
	}
	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public List<HotelVO> getHotelids() {
		return hotelids;
	}
	public void setHotelids(List<HotelVO> hotelids) {
		this.hotelids = hotelids;
	}
	public String getHotelidSqlList() {
		return hotelidSqlList;
	}

	public void setHotelidSqlList(List<HotelVO> hotelids) {
		this.hotelidSqlList = FoxSystemUtils.hotelVOToStrList(hotelids);
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	
}
