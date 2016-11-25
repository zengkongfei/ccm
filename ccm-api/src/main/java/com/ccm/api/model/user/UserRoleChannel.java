package com.ccm.api.model.user;

import java.io.Serializable;

import com.ccm.api.model.base.BaseObject;

/**
 * 
 * @author chay
 *
 */
public class UserRoleChannel extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8762481187487633456L;
	
	private String MId;//主键
	private String userId;
	private String roleId;
	private String channelId;
	public String getMId() {
		return MId;
	}
	public void setMId(String mId) {
		MId = mId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
}
