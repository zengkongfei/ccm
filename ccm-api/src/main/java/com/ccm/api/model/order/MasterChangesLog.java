/**
 * 
 */
package com.ccm.api.model.order;

import java.util.Date;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny
 * 
 */
public class MasterChangesLog extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5622275989372860020L;

	private String masterChangesLogId;// 主键
	private String masterId;// 订单号
	private String user;// 更改操作人
	private String pmsId;// PMS确认号
	private String lastName = "";// 姓
	private String firstName = ""; // 名
	private String chineseName = "";// 中文名
	private Date arrivalDate;// 抵店日期
	private Date departureDate;// 离店日期
	private Date masterCreateTime;// 预定创建时间
	private Date masterCancelTime;// 预定取消时间
	private Date masterModifyTime;// 预定修改时间
	private String status;// 状态
	private String restype;// reservationStatusType
	private String rateCode;// 房价代码
	private String roomCode;// 房型代码
	private String source;// 来源
	private String ext;// 扩展字段
	private String ext2;// 扩展字段2

	public String getMasterChangesLogId() {
		return masterChangesLogId;
	}

	public void setMasterChangesLogId(String masterChangesLogId) {
		this.masterChangesLogId = masterChangesLogId;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPmsId() {
		return pmsId;
	}

	public void setPmsId(String pmsId) {
		this.pmsId = pmsId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getMasterCreateTime() {
		return masterCreateTime;
	}

	public void setMasterCreateTime(Date masterCreateTime) {
		this.masterCreateTime = masterCreateTime;
	}

	public Date getMasterCancelTime() {
		return masterCancelTime;
	}

	public void setMasterCancelTime(Date masterCancelTime) {
		this.masterCancelTime = masterCancelTime;
	}

	public Date getMasterModifyTime() {
		return masterModifyTime;
	}

	public void setMasterModifyTime(Date masterModifyTime) {
		this.masterModifyTime = masterModifyTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRestype() {
		return restype;
	}

	public void setRestype(String restype) {
		this.restype = restype;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

}
