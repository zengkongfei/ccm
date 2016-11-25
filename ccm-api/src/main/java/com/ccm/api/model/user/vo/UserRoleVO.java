/**
 * 
 */
package com.ccm.api.model.user.vo;

import java.io.Serializable;

/**
 * @author Jenny Liao
 * 
 */
public class UserRoleVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8891126618043860382L;

	private String roleId;

	private String userId;

	private Boolean checked;

	private String roleCnName; // read-only

	private String roleCategory;// read-only

	private String hotelId;

	private String hotelName;// read-only

	private String name;

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

	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * @param hotelName
	 *            the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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
	 * @return the roleCnName
	 */
	public String getRoleCnName() {
		return roleCnName;
	}

	/**
	 * @param roleCnName
	 *            the roleCnName to set
	 */
	public void setRoleCnName(String roleCnName) {
		this.roleCnName = roleCnName;
	}

	/**
	 * @return the checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * @param checked
	 *            the checked to set
	 */
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

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
	 * @return the roleCategory
	 */
	public String getRoleCategory() {
		return roleCategory;
	}

	/**
	 * @param roleCategory
	 *            the roleCategory to set
	 */
	public void setRoleCategory(String roleCategory) {
		this.roleCategory = roleCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
