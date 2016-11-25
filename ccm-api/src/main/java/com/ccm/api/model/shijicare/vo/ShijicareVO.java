package com.ccm.api.model.shijicare.vo;

import java.util.Date;

import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.util.DateUtil;

public class ShijicareVO extends ShijiCare{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2021734345364373679L;
	private String interfaceStatus;//酒店接口状态
	private String spaceSec;
	private Date receivePmsLogCreatedTime;
	private Date receivePmsLogLastModifyTime;
	private String caseStatus;//新建成功/新建失败/关闭成功/关闭失败  对应状态分别是 1，2，3，4
	
	public String getInterfaceStatus() {
		return interfaceStatus;
	}
	public void setInterfaceStatus(String interfaceStatus) {
		this.interfaceStatus = interfaceStatus;
	}
	public String getStatusResult() {
		try {
			if (DateUtil.addMinutes(getReceivePmsLogLastModifyTime(), 5).before(getReceivePmsLogCreatedTime()) || Integer.valueOf(getSpaceSec()) / 60 <= 5) {
				return "on";
			} else {
				return "off";
			}
		} catch (Exception e) {
			return "off";
		}
	}
	public String getSpaceSec() {
		return spaceSec;
	}
	public void setSpaceSec(String spaceSec) {
		this.spaceSec = spaceSec;
	}
	public Date getReceivePmsLogCreatedTime() {
		return receivePmsLogCreatedTime;
	}
	public void setReceivePmsLogCreatedTime(Date receivePmsLogCreatedTime) {
		this.receivePmsLogCreatedTime = receivePmsLogCreatedTime;
	}
	public Date getReceivePmsLogLastModifyTime() {
		return receivePmsLogLastModifyTime;
	}
	public void setReceivePmsLogLastModifyTime(Date receivePmsLogLastModifyTime) {
		this.receivePmsLogLastModifyTime = receivePmsLogLastModifyTime;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	@Override
	public String toString() {
		return "ShijicareVO [interfaceStatus=" + interfaceStatus + ", spaceSec=" + spaceSec + ", receivePmsLogCreatedTime=" + receivePmsLogCreatedTime + ", receivePmsLogLastModifyTime=" + receivePmsLogLastModifyTime + ", caseStatus=" + caseStatus + "]";
	}
	
}
