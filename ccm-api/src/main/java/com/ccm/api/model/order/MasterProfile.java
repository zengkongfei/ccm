/**
 * 
 */
package com.ccm.api.model.order;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class MasterProfile extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6091219629053730099L;
	/**
	 * 
	 */
	private String masterProfileId;
	private String masterId;// 订单号
	private String firstName;
	private String lastName;
	private String chinaName;
	private String sex;
	private String nameTitle;

	public String getMasterProfileId() {
		return masterProfileId;
	}

	public void setMasterProfileId(String masterProfileId) {
		this.masterProfileId = masterProfileId;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getChinaName() {
		return chinaName;
	}

	public void setChinaName(String chinaName) {
		this.chinaName = chinaName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNameTitle() {
		return nameTitle;
	}

	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}

	@Override
	public String toString() {
		return "MasterProfile [masterProfileId=" + masterProfileId + ", masterId=" + masterId + ", firstName=" + firstName + ", lastName=" + lastName + ", chinaName=" + chinaName + ", sex=" + sex + ", nameTitle=" + nameTitle + "]";
	}

}
