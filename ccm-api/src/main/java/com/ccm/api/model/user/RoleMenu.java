package com.ccm.api.model.user;

import java.io.Serializable;

import com.ccm.api.model.base.BaseObject;

public class RoleMenu extends BaseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6742427101316410530L;
	private String menuId;
	private String roleId;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
