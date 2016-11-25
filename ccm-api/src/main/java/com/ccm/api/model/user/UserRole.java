/**
 * 
 */
package com.ccm.api.model.user;

import java.io.Serializable;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny Liao
 * 
 */
public class UserRole extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8891126618043860382L;

	private String userId;

	private String roleId;

	private String hotelId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the hotelId
	 */
	public String getHotelId() {
		return hotelId;
	}

	/**
	 * @param hotelId
	 *            the hotelId to set
	 */
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

}
