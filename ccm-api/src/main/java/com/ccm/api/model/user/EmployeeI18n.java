package com.ccm.api.model.user;

import com.ccm.api.model.base.BaseObject;

/**
 * 
 * @author Chay.huang
 *
 */
public class EmployeeI18n extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -585285475243676194L;
	private String employeeId;
	private String employeeI18n_MId;
	private String dept;//部门
	private String title;//职位
	private String name;//姓名
	private String language;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeI18n_MId() {
		return employeeI18n_MId;
	}
	public void setEmployeeI18n_MId(String employeeI18n_MId) {
		this.employeeI18n_MId = employeeI18n_MId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "EmployeeI18n [employeeI18n_MId=" + employeeI18n_MId + ", employeeId=" + employeeId
				+ ", language=" + language + ", dept=" + dept +", title=" + title +", name=" + name + "]";
	}
}
