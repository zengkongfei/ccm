package com.ccm.api.model.user;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;

import com.ccm.api.model.base.BaseObject;

public class Role extends BaseObject implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 3690197650654049848L;

	// 必须按字符串排序
	public static final String[] DEFAULT_ROLES = { "1", "10", "2", "3" };

	private String roleId; // 角色ID

	private String name; // 角色名（必须英文关键字）

	private String cnName; // 角色中文名

	private String description;

	private String companyId; // 各商家自定义角色时候需要,系统默认包含useage=2的,companyId为空给商家，系统默认不能删除。

	private String usescope; // 参见RoleUsage.java

	private Boolean isShow=true;
	/**
	 * Default constructor - creates a new instance with no values set.
	 */
	public Role() {
	}

	/**
	 * Create a new instance and set the name.
	 * 
	 * @param name
	 *            name of the role.
	 */
	public Role(final String name) {
		this.name = name;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getCnName() {
		return cnName;
	}

	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @return the name property (getAuthority required by Acegi's
	 *         GrantedAuthority interface)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */

	public String getAuthority() {
		return getName();
	}

	public String getName() {
		return this.name;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUsescope() {
		return usescope;
	}

	public void setUsescope(String usescope) {
		this.usescope = usescope;
	}

	public String getDescription() {
		return this.description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Role)) {
			return false;
		}

		final Role role = (Role) o;

		return !(name != null ? !name.equals(role.name) : role.name != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (name != null ? name.hashCode() : 0);
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + ", cnName="
				+ cnName + ", description=" + description + ", companyId="
				+ companyId + ", usescope=" + usescope + ", isShow=" + isShow
				+ "]";
	}

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(Object o) {
		return (equals(o) ? 0 : -1);
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}
	
	
}
