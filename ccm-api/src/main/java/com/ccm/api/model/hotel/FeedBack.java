/**
 * 
 */
package com.ccm.api.model.hotel;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class FeedBack extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1175646623758773888L;

	private String feedBackId;// 序号
	private String hotelId;//
	private String companyName;
	private String name; //
	private String mobile;//
	private String email;//
	private String message;
	private Integer status;

	public String getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(String feedBackId) {
		this.feedBackId = feedBackId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FeedBack [feedBackId=" + feedBackId + ", hotelId=" + hotelId + ", companyName=" + companyName + ", name=" + name + ", mobile=" + mobile + ", email=" + email + ", message=" + message + ", status=" + status + "]";
	}

}
