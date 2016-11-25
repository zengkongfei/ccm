package com.ccm.api.model.user;

import java.io.Serializable;

import com.ccm.api.model.base.BaseObject;
/**
 * 功能权限
 * @author Water
 *
 */
public class Authority extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2385644799254901906L;

	private String id; // authId 权限Id

	private String authNameEN; //权限名（英文）

	private String authNameCN; //权限名（中文）

	private Boolean isShow=true;//是否显示此权限，默认显示
	
	private String menu; //权限所属的菜单

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthNameEN() {
		return authNameEN;
	}

	public void setAuthNameEN(String authNameEN) {
		this.authNameEN = authNameEN;
	}

	public String getAuthNameCN() {
		return authNameCN;
	}

	public void setAuthNameCN(String authNameCN) {
		this.authNameCN = authNameCN;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", authNameEN=" + authNameEN + ", authNameCN=" + authNameCN + ", isShow="
				+ isShow + ", menu=" + menu + "]";
	}

}
