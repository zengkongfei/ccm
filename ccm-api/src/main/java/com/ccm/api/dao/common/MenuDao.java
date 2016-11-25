/**
 * 
 */
package com.ccm.api.dao.common;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.common.vo.MenuCriteria;
import com.ccm.api.model.common.vo.MenuVO;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.RoleMenu;

public interface MenuDao extends GenericDao<Menu, String> {

	/**
	 * 取用户拥有的一级菜单
	 * 
	 * @param userId
	 * @param language 
	 * @return
	 */
	List<Menu> getAuthedTopMenus(String userId, String language);

	List<Menu> getAuthedTopMenusByUserIds(MenuCriteria menuCriteria);
	
	/**
	 * 取一级菜单
	 * 
	 * @return
	 */
	List<Menu> getTopMenuList();

	/**
	 * 取可分配权限的菜单
	 * 
	 * @param userId
	 * @return
	 */
	List<Menu> getCanAuthedMenus(String userId);

	/**
	 * 取用户已分配的二级子菜单
	 * 
	 * @param parentId
	 * @param language 
	 * @return
	 */
	List<Menu> getAuthedTwoMenus(String parentId, String userId, String language);

	/**
	 * 根据当前URl获取需日志的父子菜单
	 * 
	 * @param url
	 * @return
	 */
	public List<MenuVO> findRecordLogMenuByUrl(String url);

	/**
	 * 取所有需要secrutity拦截的URL
	 * 
	 * @return
	 */
	List<Menu> getAllSecurityUrl();

	/**
	 * 根据url，取有该URL访问的角色名称
	 * 
	 * @param url
	 * @return
	 */
	List<Role> findRoleNameByUrl(String url);

	/**
	 * 根据menuId取menu
	 * 
	 * @param string
	 * @return
	 */
	Menu getMenuById(String string);

	/**
	 * 根据使用范围找所有授权权限菜单
	 * 
	 * @param userScopes
	 * @return
	 */
	List<Menu> getAllAuthMenuListByUsescope(String[] userScopes);

	/**
	 * 死代码，获取集团商家 中 酒店列表 中所有操作列权限
	 * 
	 * @return
	 */
	List<Menu> getChainAuthSubMenus();

	/**
	 * 死代码，获取运营 酒店列表 中所有操作列权限
	 * 
	 * @param useScope
	 * @return
	 */
	List<Menu> getPlatAuthSubMenus();

	/**
	 * 根据menuId和parentId查找菜单
	 */
	Menu getMenuByParentId(String menuId, String parentId);

	Menu getMenu(String menuId);

	List<Menu> getTwoMenus(String parentId);

	List<MenuVO> getParentSubMenuIdByUrl(String userId, String url);

	/**
	 * 通过url取用户已分配的二级子菜单
	 * @param userId
	 * @param url
	 * @return
	 */
	List<Menu> getAuthedTwoMenusByUrl(String userId, String url, String language);

	/**
	 * 通过url获取菜单
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
	 * 取可分配权限的菜单
	 * 
	 * @param userId
	 * @param language
	 * @return
	 */
	List<Menu> getCanAuthedMenus(String userId, String language);

	List<Menu> getTopMenuList(String language);

	List<Menu> getTwoMenus(String parentId, String language);

	public List<Menu> getTwoMenusByParentIds_i18n(MenuCriteria menuCriteria);
	
	List<MenuVO> findRecordLogMenuByUrl(String url, String langauge);

	List<Menu> getMenuByRoleId(String roleId, String language);

	List<Menu> getAllMenu(String language);

	List<RoleMenu> getMenuByShowRoles(String language);

}