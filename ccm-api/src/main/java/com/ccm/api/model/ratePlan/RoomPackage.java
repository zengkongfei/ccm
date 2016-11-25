package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 房型打包
 * @author carr
 *
 */
public class RoomPackage extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6359502351097423041L;
	
	private String roomPackageId;//序号     
	private String roomRateId;//房型房价序号
	private String packageId;//打包服务序号 
	private String packageName;
	
	public String getRoomPackageId() {
		return roomPackageId;
	}
	public void setRoomPackageId(String roomPackageId) {
		this.roomPackageId = roomPackageId;
	}
	public String getRoomRateId() {
		return roomRateId;
	}
	public void setRoomRateId(String roomRateId) {
		this.roomRateId = roomRateId;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
	public String getPackageName() {
        return packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    @Override
	public String toString() {
		return "RoomPackage [roomPackageId=" + roomPackageId + ", roomRateId="
				+ roomRateId + ", packageId=" + packageId + "]";
	}
}
