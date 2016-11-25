/**
 * 
 */
package com.ccm.api.model.hotel;

import java.util.Date;

import com.ccm.api.model.base.Address;
import com.ccm.api.model.base.BaseObject;


/**
 * @author Jenny Liao 即商家
 */
public class Company extends BaseObject {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6296213664780760508L;

    private String companyId;
    private String companyName;
    private String certifyStatus;// 比如CompanyCertifiedStatus.NOT_CERTIFIED,即DictCode.codeNo
    private String bizRegNo;// 工商注册号:
    private String accountBank;
    private String accountName;
    private String accountNo;

    private Date applyDate;// 申请认证时间，为商家点击申请认证按钮之后

    private Date authDate; // 认证时间，为运行批准之后

    private Date createdTime;// 创建时间

    private Boolean delFlag;//

    private String companyType; // 比如CompanyType.CHIAN

    private String parentId;// 集团公司Id,也是companyId

    private Address address;
    private String settlement;

    private Boolean invoice;// 用于保存订单快照时的临时值，true-畅联渠道开具，false-酒店开具

    private String premiumrateIdenticalFlag;
    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     *            the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the certifyStatus
     */
    public String getCertifyStatus() {
        return certifyStatus;
    }

    /**
     * @param certifyStatus
     *            the certifyStatus to set
     */
    public void setCertifyStatus(String certifyStatus) {
        this.certifyStatus = certifyStatus;
    }

    /**
     * @return the bizRegNo
     */
    public String getBizRegNo() {
        return bizRegNo;
    }

    /**
     * @param bizRegNo
     *            the bizRegNo to set
     */
    public void setBizRegNo(String bizRegNo) {
        this.bizRegNo = bizRegNo;
    }

    /**
     * @return the accountBank
     */
    public String getAccountBank() {
        return accountBank;
    }

    /**
     * @param accountBank
     *            the accountBank to set
     */
    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     *            the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo
     *            the accountNo to set
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @return the applyDate
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * @param applyDate
     *            the applyDate to set
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * @return the authDate
     */
    public Date getAuthDate() {
        return authDate;
    }

    /**
     * @param authDate
     *            the authDate to set
     */
    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }

    /**
     * @return the delFlag
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     *            the delFlag to set
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return the companyType
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * @param companyType
     *            the companyType to set
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     *            the parentId to set
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     *            the createdTime to set
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return the companyId
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     *            the companyId to set
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "company:" + companyId;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public Boolean getInvoice() {
        return invoice;
    }

    public void setInvoice(Boolean invoice) {
        this.invoice = invoice;
    }

	public void setPremiumrateIdenticalFlag(String premiumrateIdenticalFlag) {
		this.premiumrateIdenticalFlag = premiumrateIdenticalFlag;
	}

	public String getPremiumrateIdenticalFlag() {
		return premiumrateIdenticalFlag;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
