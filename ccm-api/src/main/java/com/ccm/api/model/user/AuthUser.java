package com.ccm.api.model.user;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;

import com.ccm.api.model.base.BaseObject;
/**
 * 功能权限
 * @author Water
 *
 */
public class AuthUser extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2385644799254901906L;

	//private String authUserId;
	private String id; //userId
	private List<String> roleIds; // 角色Id
	private List<String> authIds; // 权限Id

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getAuthIds() {
		return authIds;
	}

	public void setAuthIds(List<String> authIds) {
		this.authIds = authIds;
	}

	@Override
	public String toString() {
		return "AuthUser [id=" + id + ", authIds=" + authIds + "]";
	}
	
}
