package com.ccm.api.model.user;

import java.util.Date;

/**
 * 用户密码
 * @author chay
 *
 */
public class UserPassword extends B2BUser {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2219582571974645963L;
	
	private String password1;//历史密码1
	private String password2;//历史密码2
	private String password3;//历史密码3
	private String password4;//历史密码4
	private int passwordFailNumber;//密码输入错误次数
	private Date passwordlastModifyTime;//最新更新密码时间
	private Date passwordFaillastModifyTime;//最近登入输入密码错误时间
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getPassword3() {
		return password3;
	}
	public void setPassword3(String password3) {
		this.password3 = password3;
	}
	public String getPassword4() {
		return password4;
	}
	public void setPassword4(String password4) {
		this.password4 = password4;
	}
	public int getPasswordFailNumber() {
		return passwordFailNumber;
	}
	public void setPasswordFailNumber(int passwordFailNumber) {
		this.passwordFailNumber = passwordFailNumber;
	}
	public Date getPasswordlastModifyTime() {
		return passwordlastModifyTime;
	}
	public void setPasswordlastModifyTime(Date passwordlastModifyTime) {
		this.passwordlastModifyTime = passwordlastModifyTime;
	}
	public Date getPasswordFaillastModifyTime() {
		return passwordFaillastModifyTime;
	}
	public void setPasswordFaillastModifyTime(Date passwordFaillastModifyTime) {
		this.passwordFaillastModifyTime = passwordFaillastModifyTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "UserPassword [ password1=" + password1
				+ ", password2=" + password2 + ", password3=" + password3
				+ ", password4=" + password4 + ", passwordFailNumber="
				+ passwordFailNumber + ", passwordlastModifyTime="
				+ passwordlastModifyTime + ", passwordFaillastModifyTime="
				+ passwordFaillastModifyTime + "]";
	}
	
	
	
}
