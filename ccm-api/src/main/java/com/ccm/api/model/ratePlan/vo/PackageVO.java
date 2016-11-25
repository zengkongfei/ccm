package com.ccm.api.model.ratePlan.vo;

import java.util.List;

import com.ccm.api.model.ratePlan.Package;
import com.ccm.api.model.ratePlan.PackageI18n;

public class PackageVO extends Package {

	private static final long serialVersionUID = -1845853508412282458L;

	private String packageMId;// 序号
	private String language;// 语言
	private String packageName;// 服务名称
	private String description;// 描述
	private String message;// 提醒

	private String ratePlanId;// 房价ID
	private String roomTypeId;// 房型ID
	private List<String> ratePlanIds;// 房价IDs
	private String packageAmount;// 计算后金额
	private List<PackageI18n> packageI18nList;

	// 用于协议
	private Integer quantity;
	private Boolean isDynamic = false;
	// 用于WBE动态包价
	private Integer rooms = 1;// 房间数量

	public String getPackageMId() {
		return packageMId;
	}

	public void setPackageMId(String packageMId) {
		this.packageMId = packageMId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public String getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public List<String> getRatePlanIds() {
		return ratePlanIds;
	}

	public void setRatePlanIds(List<String> ratePlanIds) {
		this.ratePlanIds = ratePlanIds;
	}

	public String getPackageAmount() {
		return packageAmount;
	}

	public void setPackageAmount(String packageAmount) {
		this.packageAmount = packageAmount;
	}

	public List<PackageI18n> getPackageI18nList() {
		return packageI18nList;
	}

	public void setPackageI18nList(List<PackageI18n> packageI18nList) {
		this.packageI18nList = packageI18nList;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsDynamic() {
		return isDynamic;
	}

	public void setIsDynamic(Boolean isDynamic) {
		this.isDynamic = isDynamic;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "PackageVO [packageMId=" + packageMId + ", language=" + language + ", packageName=" + packageName + ", description=" + description + ", message=" + message + ", ratePlanId=" + ratePlanId + ", roomTypeId=" + roomTypeId + ", ratePlanIds=" + ratePlanIds + ", packageAmount=" + packageAmount + ", packageI18nList=" + packageI18nList + ", quantity=" + quantity + ", isDynamic=" + isDynamic + ", rooms=" + rooms + "]";
	}

}
