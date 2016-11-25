/**
 * 
 */
package com.ccm.api.dao.common.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.common.MenuDao;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.common.vo.MenuCriteria;
import com.ccm.api.model.common.vo.MenuVO;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.RoleMenu;

@Repository("menuDao")
public class MenuDaoiBatis extends GenericDaoiBatis<Menu, String> implements MenuDao {

	/**
	 * Constructor that sets the entity to User.class.
	 */
	public MenuDaoiBatis() {
		super(Menu.class);
	}

	/**
	 * 取用户拥有的一级菜单
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> getAuthedTopMenus(String userId,String language) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		param.put("language", language);
		List<Menu> menus = getSqlMapClientTemplate().queryForList("getAuthedTopMenus", param);
		return menus;
	}

	/**
	 * 取用户拥有的一级菜单 getTwoMenusByParentIds_i18n
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> getAuthedTopMenusByUserIds(MenuCriteria menuCriteria) {
		return getSqlMapClientTemplate().queryForList("getAuthedTopMenusByUserIds", menuCriteria);
	}
	
	/**
	 * 根据父菜单取用户拥有的二级菜单 
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> getTwoMenusByParentIds_i18n(MenuCriteria menuCriteria) {
		return getSqlMapClientTemplate().queryForList("getTwoMenusByParentIds_i18n", menuCriteria);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> getTopMenuList() {
		List<Menu> menus = getSqlMapClientTemplate().queryForList("getTopMenuList", null);
		return menus;
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> getTopMenuList(String language) {
		List<Menu> menus = getSqlMapClientTemplate().queryForList("getTopMenuList_i18n", language);
		return menus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getCanAuthedMenus(String userId) {
		return getSqlMapClientTemplate().queryForList("getCanAuthedMenus", userId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getCanAuthedMenus(String userId,String language) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		param.put("language", language);
		return getSqlMapClientTemplate().queryForList("getCanAuthedMenus_i18n", param);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getAuthedTwoMenus(String parentId, String userId,String language) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("parentId", parentId);
		param.put("userId", userId);
		param.put("language", language);
		return getSqlMapClientTemplate().queryForList("getAuthedTwoMenus", param);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<MenuVO> findRecordLogMenuByUrl(String url) {
		
		Map paraMap = new HashMap();
		paraMap.put("url", url);
		List<MenuVO> menuVO = getSqlMapClientTemplate().queryForList("findRecordLogMenuByUrl", paraMap);
		
		return menuVO;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<MenuVO> findRecordLogMenuByUrl(String url,String langauge) {

		Map paraMap = new HashMap();
		paraMap.put("url", url);
		paraMap.put("langauge", langauge);
		List<MenuVO> menuVO = getSqlMapClientTemplate().queryForList("findRecordLogMenuByUrl_i18n", paraMap);

		return menuVO;
	}

	/**
	 * 根据URl获取角色
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Role> findRoleNameByUrl(String url) {

		List<Role> roles = getSqlMapClientTemplate().queryForList("findRoleNameByUrl", url);

		return roles;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getAllSecurityUrl() {
		List<Menu> menus = getSqlMapClientTemplate().queryForList("getAllSecurityUrl", null);
		return menus;
	}

	/**
	 * {@inheritDoc}
	 */
	public Menu getMenuById(String menuId) {
		Menu menu = (Menu) getSqlMapClientTemplate().queryForObject("getMenuByMenuId", menuId);
		return menu;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> getAllAuthMenuListByUsescope(String[] userScopes) {
		String str = "";
		for (int i = 0; i < userScopes.length; i++) {
			if (i == 0)
				str = "'" + userScopes[i] + "'";
			else
				str = str + ",'" + userScopes[i] + "'";

		}
		List<Menu> menus = getSqlMapClientTemplate().queryForList("getAllAuthMenuListByUsescope", str);
		return menus;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> getChainAuthSubMenus() {
		List<Menu> menus = getSqlMapClientTemplate().queryForList("getChainAuthSubMenus");
		return menus;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> getPlatAuthSubMenus() {
		List<Menu> menus = getSqlMapClientTemplate().queryForList("getPlatAuthSubMenus");
		return menus;
	}

	@Override
	public Menu getMenuByParentId(String menuId, String parentId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("menuId", menuId);
		map.put("parentId", parentId);
		return (Menu) getSqlMapClientTemplate().queryForObject("getMenuByParentId", map);
	}

	@Override
	public Menu getMenu(String menuId) {
		return (Menu) getSqlMapClientTemplate().queryForObject("getMenu", menuId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getTwoMenus(String parentId) {
		return getSqlMapClientTemplate().queryForList("getTwoMenus", parentId);
	}

	@SuppressWarnings("unchecked")
	public List<MenuVO> getParentSubMenuIdByUrl(String userId, String url) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("url", url);
		return getSqlMapClientTemplate().queryForList("getParentSubMenuIdByUrl", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getAuthedTwoMenusByUrl(String userId, String url,String language) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("url", url);
		param.put("userId", userId);
		param.put("language", language);
		return getSqlMapClientTemplate().queryForList("getAuthedTwoMenusByUrl", param);
	}

	@Override
	public Menu getMenuByUrl(String url,String language) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("url", url);
		param.put("language", language);
		return (Menu) getSqlMapClientTemplate().queryForObject("getMenuByUrl", param);
	}

	@Override
	public Menu getMenuById(String menuId,String language) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("menuId", menuId);
		param.put("language", language);
		return (Menu) getSqlMapClientTemplate().queryForObject("getMenuById", param);
	}
	
	@Override
	public Menu getMenuByChildId(String childId,String language) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("childId", childId);
		param.put("language", language);
		return (Menu) getSqlMapClientTemplate().queryForObject("getMenuByChildId", param);
	}

	@Override
	public List<Menu> getTwoMenus(String parentId, String language) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("parentId", parentId);
		param.put("language", language);
		return getSqlMapClientTemplate().queryForList("getTwoMenus_i18n", param);
	}
	
	@Override 
	public List<Menu> getAllMenu(String language){
		return getSqlMapClientTemplate().queryForList("getAllMenu", language);
	}
	
	@Override
	public List<Menu> getMenuByRoleId(String roleId,String language){
		Map<String, String> param = new HashMap<String, String>();
		param.put("roleId", roleId);
		param.put("language", language);
		return getSqlMapClientTemplate().queryForList("getMenuByRoleId", param);
	}
	
	@Override
	public List<RoleMenu> getMenuByShowRoles(String language){
		return getSqlMapClientTemplate().queryForList("getMenuByShowRoles",language);
	}
}
