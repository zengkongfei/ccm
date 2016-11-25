/**
 * 
 */
package com.ccm.api.model.common.vo;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny Liao 菜单，可与Spring Security结使用
 */
public class MenuVO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5622061279327852895L;

	private String menuId;

	private String pmenuId;// 父菜单

	private String menuUrlName; // 菜单名称

	private String urlId;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getPmenuId() {
		return pmenuId;
	}

	public void setPmenuId(String pmenuId) {
		this.pmenuId = pmenuId;
	}

	public String getMenuUrlName() {
		return menuUrlName;
	}

	public void setMenuUrlName(String menuUrlName) {
		this.menuUrlName = menuUrlName;
	}

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	@Override
	public String toString() {
		return "MenuVO [menuId=" + menuId + ", pmenuId=" + pmenuId + ", menuUrlName=" + menuUrlName + ", urlId=" + urlId + "]";
	}

}
