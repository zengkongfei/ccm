/**
 * 
 */
package com.ccm.api.model.common;

import com.ccm.api.model.base.BaseObject;

/**
 * @author Jenny Liao 菜单，可与Spring Security结使用
 */
public class Menu extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5622061279327852895L;

	private String menuId;

	private String menuName; // 菜单名称

	private String displayName; // 显示名称

	private String target;

	private String url;

	private String picPath;

	private String parentId;

	private String menuType; // '1 菜单树 2 普通功能
								// 对有些功能需要在树菜单中显示，而有些菜单不需要显示，但是又要在分配权限的时候分配。

	private String authFlag; // '1 可授权 0 不可授权 对有一些系统菜单在分配权限的时候不需要分配给其他角色

	private String usageScope; // '1 商家，2 平台 ',

	public String getId() {
		return menuId;
	}

	public Boolean saveOrUpdate() {
		if (menuId != null && !menuId.trim().isEmpty()) {
			return false;// update
		} else {
			return true;// save
		}
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

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName
	 *            the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag(String authFlag) {
		this.authFlag = authFlag;
	}

	public String getUsageScope() {
		return usageScope;
	}

	public void setUsageScope(String usageScope) {
		this.usageScope = usageScope;
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
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", displayName=" + displayName + ", target=" + target + ", url=" + url + ", picPath=" + picPath + ", parentId=" + parentId + ", menuType=" + menuType + ", authFlag=" + authFlag + ", usageScope=" + usageScope + "]";
	}

}
