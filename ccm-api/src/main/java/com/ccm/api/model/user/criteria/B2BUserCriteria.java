package com.ccm.api.model.user.criteria;

import com.ccm.api.model.base.criteria.SearchCriteria;

/**
 * 用户查询条件类
 */
public class B2BUserCriteria extends SearchCriteria {

    private static final long serialVersionUID = -6080445635681508644L;

    private String userId;

    private String username; // 用户名

    private String name; // 姓名

    private String companyId; // 公司ID

    private String companyName; // 公司名

    private String mobile;// 手机
    private String dept;// 部门
    private String title;// 职位
    
    private String language;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
