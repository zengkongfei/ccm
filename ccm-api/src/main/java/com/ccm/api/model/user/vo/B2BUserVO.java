package com.ccm.api.model.user.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ccm.api.model.hotel.vo.HotelVO;

/**
 * 用户VO,用在查询列表返回和 获取详细信息时候,方便页面显示.
 */
public class B2BUserVO implements Serializable {

	private static final long serialVersionUID = 3832626162173359411L;

	private String userId;

	private String username;
	private String password;

	private Date lastLoginTime;

	/*** 公司信息 ***/
	private String companyId; // 公司ID

	private String companyName; // 公司ID

	/** 雇员信息 **/
	private String employeeId; // 雇员ID

	private String name;// 用户昵称,真实姓名

	private String telNo;// 联系电话

	private String mobile;// 发送短信的手机号码
	private Boolean sendMess = Boolean.FALSE;// 是否发送短信标志

	private String email;

	private String adminFlag;

	private String qq;
	private String dept;// 部门
	private String title;// 职位

	private Date createdTime;
	private List<HotelVO> hotels = new ArrayList<HotelVO>();// 用户分配的酒店

	private Boolean isLive;// 账号激活
	private Boolean passwordIsInit;// 是否是初始密码
	private Boolean islocak;// 是否锁定
	private Boolean isAdmin;// 是否为Admin,数据库无此字段，仅作前台标识

	private Boolean ishotelBlackList;
	
	public Boolean getIshotelBlackList() {
		return ishotelBlackList;
	}

	public void setIshotelBlackList(Boolean ishotelBlackList) {
		this.ishotelBlackList = ishotelBlackList;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	public Boolean getPasswordIsInit() {
		return passwordIsInit;
	}

	public void setPasswordIsInit(Boolean passwordIsInit) {
		this.passwordIsInit = passwordIsInit;
	}

	public Boolean getIslocak() {
		return islocak;
	}

	public void setIslocak(Boolean islocak) {
		this.islocak = islocak;
	}

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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
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

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public List<HotelVO> getHotels() {
		return hotels;
	}

	public void setHotels(List<HotelVO> hotels) {
		this.hotels = hotels;
	}

	public Boolean getSendMess() {
		return sendMess;
	}

	public void setSendMess(Boolean sendMess) {
		this.sendMess = sendMess;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
