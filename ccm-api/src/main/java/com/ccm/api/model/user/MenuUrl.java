/**
 * 
 */
package com.ccm.api.model.user;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny Liao for spring security
 */
public class MenuUrl extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4067208456903339384L;

	private String urlId;
	private String menuId;
	private String url;
	private String menuName;
	private String masterFlag;

	private String recordLog;

	/**
	 * 
	 */
	public String getId() {
		return urlId;
	}

	public Boolean saveOrUpdate() {
		if (urlId != null && !urlId.trim().isEmpty()) {
			return false;// update
		} else {
			return true;// save
		}
	}

	/**
	 * @return the urlId
	 */
	public String getUrlId() {
		return urlId;
	}

	/**
	 * @param urlId
	 *            the urlId to set
	 */
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 *            the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMasterFlag() {
		return masterFlag;
	}

	public void setMasterFlag(String masterFlag) {
		this.masterFlag = masterFlag;
	}

	public String getRecordLog() {
		return recordLog;
	}

	public void setRecordLog(String recordLog) {
		this.recordLog = recordLog;
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

	@Override
	public String toString() {
		return "MenuUrl [urlId=" + urlId + ", menuId=" + menuId + ", url=" + url + ", menuName=" + menuName + ", masterFlag=" + masterFlag + ", recordLog=" + recordLog + "]";
	}

}
