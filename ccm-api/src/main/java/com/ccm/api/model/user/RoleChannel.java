package com.ccm.api.model.user;

import java.io.Serializable;

import com.ccm.api.model.base.BaseObject;

public class RoleChannel extends BaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6698804236981852588L;
	private String roleId;
	private String channelId;
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
