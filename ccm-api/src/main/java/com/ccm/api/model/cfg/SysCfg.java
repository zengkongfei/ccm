package com.ccm.api.model.cfg;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Water 系统配置
 */
public class SysCfg extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8320045767965789338L;

	private String ccmSysCfgId;
	private Integer pendingMsgCount;// 等待消息警示值
	private Long masterListener;// 订单监控轮询间隔
	private Long interfaceListener;// 酒店接口监控轮询间隔
	private Boolean isMasterLister = Boolean.TRUE;// 系统是否开启订单监控
	private Boolean isInterfaceListener = Boolean.TRUE;// 系统是否开启接口监控
	private Integer masterDealTime;// 订单监控，订单多久无pmsid 或者cancelpmsid 处理时间
	
	private String timeArr;//接口固定时间监控配置，以分号隔开

	private String announcementEn;// 系统公告 英文
	private String announcementCn;// 系统公告 中文

	private Boolean isAnnounce = false;// 是否开启系统公告

	public Boolean getIsAnnounce() {
		return isAnnounce;
	}

	public void setIsAnnounce(Boolean isAnnounce) {
		this.isAnnounce = isAnnounce;
	}

	public String getAnnouncementEn() {
		return announcementEn;
	}

	public void setAnnouncementEn(String announcementEn) {
		this.announcementEn = announcementEn;
	}

	public String getAnnouncementCn() {
		return announcementCn;
	}

	public void setAnnouncementCn(String announcementCn) {
		this.announcementCn = announcementCn;
	}

	public String getCcmSysCfgId() {
		return ccmSysCfgId;
	}

	public void setCcmSysCfgId(String ccmSysCfgId) {
		this.ccmSysCfgId = ccmSysCfgId;
	}

	public Integer getPendingMsgCount() {
		return pendingMsgCount;
	}

	public void setPendingMsgCount(Integer pendingMsgCount) {
		this.pendingMsgCount = pendingMsgCount;
	}

	public Long getMasterListener() {
		return masterListener;
	}

	public void setMasterListener(Long masterListener) {
		this.masterListener = masterListener;
	}

	public Long getInterfaceListener() {
		return interfaceListener;
	}

	public void setInterfaceListener(Long interfaceListener) {
		this.interfaceListener = interfaceListener;
	}

	public Boolean getIsMasterLister() {
		return isMasterLister;
	}

	public void setIsMasterLister(Boolean isMasterLister) {
		this.isMasterLister = isMasterLister;
	}

	public Boolean getIsInterfaceListener() {
		return isInterfaceListener;
	}

	public void setIsInterfaceListener(Boolean isInterfaceListener) {
		this.isInterfaceListener = isInterfaceListener;
	}

	public Integer getMasterDealTime() {
		return masterDealTime;
	}

	public void setMasterDealTime(Integer masterDealTime) {
		this.masterDealTime = masterDealTime;
	}

	public String getTimeArr() {
		return timeArr;
	}

	public void setTimeArr(String timeArr) {
		this.timeArr = timeArr;
	}

	@Override
	public String toString() {
		return "SysCfg [ccmSysCfgId=" + ccmSysCfgId + ", pendingMsgCount="
				+ pendingMsgCount + ", masterListener=" + masterListener
				+ ", interfaceListener=" + interfaceListener
				+ ", isMasterLister=" + isMasterLister
				+ ", isInterfaceListener=" + isInterfaceListener
				+ ", masterDealTime=" + masterDealTime + ", timeArr=" + timeArr
				+ ", announcementEn=" + announcementEn + ", announcementCn="
				+ announcementCn + ", isAnnounce=" + isAnnounce + "]";
	}


}
