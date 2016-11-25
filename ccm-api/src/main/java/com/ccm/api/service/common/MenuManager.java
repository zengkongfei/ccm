/**
 * 
 */
package com.ccm.api.service.common;

import java.util.List;

import com.ccm.api.model.common.Menu;
import com.ccm.api.model.common.vo.MenuCriteria;
import com.ccm.api.model.common.vo.MenuVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.base.GenericManager;

public interface MenuManager extends GenericManager<Menu, String> {

	/**
	 * 取用户制已分配的一级菜单
	 * 
	 * @param b2bUser
	 * @param language 
	 * @return
	 */
	public List<Menu> getAuthedTopMenu(B2BUser b2bUser, String language);
	
	List<Menu> getAuthedTopMenusByUserIds(MenuCriteria menuCriteria);

	/**
	 * 取用户已分配的二级子菜单
	 * 
	 * @param parentId
	 * @param userId
	 * @param language 
	 * @return
	 */
	public List<Menu> getAuthedTwoMenus(String parentId, String userId, String language);
	
	public List<Menu> getTwoMenusByParentIds_i18n(MenuCriteria menuCriteria);
	
	/**
	 * 根据menuId和parentId查找菜单
	 */
	Menu getMenuByParentId(String menuId, String parentId);

	/**
	 * 取一级菜单
	 * 
	 * @return
	 */
	List<Menu> getTopMenuList();
	public List<Menu> getTopMenuList(String language);

	/**
	 * 通过一级菜单id获取二级菜单李彪
	 * @param parentId
	 * @return
	 */
	List<Menu> getTwoMenus(String parentId);

	MenuVO findRecordLogMenuByUrl(String url);
	MenuVO findRecordLogMenuByUrl(String url,String language);

	MenuVO getParentSubMenuIdByUrl(String userId, String url);

	void cleanHashMap();

	/**
	 * 通过url取用户已分配的二级子菜单
	 * @param userId
	 * @param url
	 * @return
	 */
	List<Menu> getAuthedTwoMenusByUrl(String userId,String url,String language);
	
	/**
	 * 通过url获取主菜单
	 * @param url
	 * @param language
	 * @return
	 */
	Menu getMenuByUrl(String url,String language);
	
	Menu getMenuById(String menuId,String language);
	
	/**
	 * 通过二级菜单id获取一级菜单
	 * @param childId
	 * @param language
	 * @return
	 */
	Menu getMenuByChildId(String childId,String language);

	/**
	 * 通过一级菜单id获取二级菜单
	 * @param parentId
	 * @return
	 */
	public List<Menu> getTwoMenus(String parentId, String language);

	List<Menu> getAllMenu(String language);

	List<Menu> getMenuByRoleId(String roleId, String language);

}