package com.ccm.api.model.base.vo;

import java.util.HashSet;
import java.util.Set;

import com.ccm.api.model.common.Menu;

/**
 * 
 * 菜单树vo
 *
 */
public class MenuTreeVO {
   
	private Menu menu;
	
	private Set<Menu>  menuset;

	public void addMenu(Menu menu){
		if(menuset== null){
			menuset = new HashSet<Menu>();
		}
		menuset.add(menu);
	}
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Set<Menu> getMenuset() {
		return menuset;
	}

	public void setMenuset(Set<Menu> menuset) {
		this.menuset = menuset;
	}
	
	
	
	
}
