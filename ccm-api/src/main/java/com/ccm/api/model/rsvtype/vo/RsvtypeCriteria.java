package com.ccm.api.model.rsvtype.vo;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import com.ccm.api.model.base.criteria.SearchCriteria;

public class RsvtypeCriteria extends SearchCriteria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5912421348846295588L;

	private String chainCode;
	/**酒店ID*/
	private String hotelid;
	/**日期*/
	private Date date;
	/**房型ID*/
	private String type;
	private String status;
	private String hotelCode;
	private Date startDate;
    private Date endDate;
    private Collection<String> types;
    
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	public String getHotelid() {
		return hotelid;
	}
	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
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
	public Collection<String> getTypes() {
		return types;
	}
	public void setTypes(Collection<String> types) {
		this.types = types;
	}
	
    
    
}
