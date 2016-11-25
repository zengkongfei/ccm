package com.ccm.api.dao.pms.form;

public class AccountForm {
	private String accnt;
	private double privilege = 0.00d;//优惠金额
	private String billType; 
	private String payType;
	private double price;//入账的金额
	private String crsno="";//渠道订单号
	private String ismany="false";
	private String aliPayNo = "";
	
	public String getAccnt() {
		return accnt;
	}
	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}
	public double getPrivilege() {
		return privilege;
	}
	public void setPrivilege(double privilege) {
		this.privilege = privilege;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCrsno() {
		return crsno;
	}
	public void setCrsno(String crsno) {
		this.crsno = crsno;
	}
	public String getIsmany() {
		return ismany;
	}
	public void setIsmany(String ismany) {
		this.ismany = ismany;
	}
	public String getAliPayNo() {
		return aliPayNo;
	}
	public void setAliPayNo(String aliPayNo) {
		this.aliPayNo = aliPayNo;
	}
	
}
