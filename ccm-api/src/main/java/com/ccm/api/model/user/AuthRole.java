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
public class AuthRole extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2385644799254901906L;

	//private String authRoleId;
	private String id; // roleId
	
	private List<String> authIds; // 权限Id

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
		return "AuthRole [id=" + id + ", authIds=" + authIds + "]";
	}

	
}
