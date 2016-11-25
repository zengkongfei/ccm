/**
 * 
 */
package com.ccm.api.model.order;

import java.math.BigDecimal;
import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class MasterPackage extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 607202642464631853L;
	private String masterPackageId;
	private String masterId;// 订单号
	private String packageId;
	private String packageCode;//
	private BigDecimal amount;// 总金额
	private Integer quantity;// 数量
	private Date date;
	private Boolean isDynamic = false;
	private String packageDesc;
	private String packageName;

	private Integer rooms = 1;// 房间数量

	public String getMasterPackageId() {
		return masterPackageId;
	}

	public void setMasterPackageId(String masterPackageId) {
		this.masterPackageId = masterPackageId;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getIsDynamic() {
		return isDynamic;
	}

	public void setIsDynamic(Boolean isDynamic) {
		this.isDynamic = isDynamic;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPackageDesc() {
		return packageDesc;
	}

	public void setPackageDesc(String packageDesc) {
		this.packageDesc = packageDesc;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "MasterPackage [masterPackageId=" + masterPackageId + ", masterId=" + masterId + ", packageId=" + packageId + ", packageCode=" + packageCode + ", amount=" + amount + ", quantity=" + quantity + ", date=" + date + ", isDynamic=" + isDynamic + ", packageDesc=" + packageDesc + ", packageName=" + packageName + ", rooms=" + rooms + "]";
	}

}
