package com.ccm.api.model.role;

import com.ccm.api.model.base.criteria.SearchCriteria;

/**
 * 角色查询条件类
 */
public class RoleCriteria extends SearchCriteria {


    /**
	 * 
	 */
	private static final long serialVersionUID = -3259258545167825443L;

	private String roleId;

    private String roleName; // role name

    private String roleCode; // role code
    
    private String language;

   

    public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


 

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
