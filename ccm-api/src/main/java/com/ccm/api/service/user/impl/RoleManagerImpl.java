package com.ccm.api.service.user.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.common.MenuDao;
import com.ccm.api.dao.user.RoleDao;
import com.ccm.api.model.base.vo.MenuTreeVO;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.constant.RoleUsage;
import com.ccm.api.model.role.RoleCriteria;
import com.ccm.api.model.role.RoleSearchResult;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.RoleChannel;
import com.ccm.api.model.user.RoleMenu;
import com.ccm.api.model.user.UserRoleChannel;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.user.RoleManager;
import com.ccm.api.util.CommonUtil;

/**
 * Implementation of RoleManager interface.
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Service("roleManager")
public class RoleManagerImpl extends GenericManagerImpl<Role, String> implements RoleManager {
	@Resource
	private RoleDao roleDao;
	@Autowired
	public RoleManagerImpl(RoleDao roleDao) {
		super(roleDao);
		this.roleDao = roleDao;
	}

	private MenuDao menuDao;

	@Autowired
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Role> getRoles() {
		return dao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public Role getRole(String rolename) {
		return roleDao.getRoleByName(rolename);
	}

	/**
	 * {@inheritDoc}
	 */
	public Role saveRole(Role role) {
		return dao.save(role);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeRoleByName(String rolename) {
		roleDao.removeRoleByName(rolename);
		
	}

	
	@Override
	public void removeRoleById(String roleId) {
		// TODO Auto-generated method stub
		roleDao.remove(roleId);
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<MenuTreeVO>> getAllAuthedMenu(String userId,String language) {
		
		// 取一级菜单
		List<Menu> topMenuList = menuDao.getTopMenuList(language);
		Map<String, Menu> topMap = new HashMap<String, Menu>();
		for (Menu topmenu : topMenuList) {
			topMap.put(topmenu.getMenuId(), topmenu);
		}
		
		// 取可授权的二级权限菜单
		List<Menu> menuList = menuDao.getCanAuthedMenus(userId,language);
		Map<String, MenuTreeVO> authMap = new HashMap<String, MenuTreeVO>();
		for (Menu menu : menuList) {
			
			if (menu.getMenuType().equals("2")) {
				if (authMap.containsKey(menu.getParentId())) {
					authMap.get(menu.getParentId()).addMenu(menu);
				}
			} else {
				MenuTreeVO tmpMenuTreeVO = new MenuTreeVO();
				tmpMenuTreeVO.setMenu(menu);
				authMap.put(menu.getMenuId(), tmpMenuTreeVO);
			}
			
		}
		
		// 组装权限列表
		Map<String, List<MenuTreeVO>> retmap = new HashMap<String, List<MenuTreeVO>>();
		
		for (String menuId : authMap.keySet()) {
			
			MenuTreeVO menuTreeVO = authMap.get(menuId);
			Menu tmpMenu = menuTreeVO.getMenu();
			
			Menu topMenu = topMap.get(tmpMenu.getMenuId());
			
			if (topMenu != null) {
				if (retmap.containsKey(topMenu.getMenuName())) {
					retmap.get(topMenu.getMenuName()).add(menuTreeVO);
				} else {
					List<MenuTreeVO> list = new ArrayList<MenuTreeVO>();
					list.add(menuTreeVO);
					retmap.put(topMenu.getMenuName(), list);
				}
			}
		}
		return retmap;
	}
	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<MenuTreeVO>> getAllAuthedMenu(String userId) {

		// 取一级菜单
		List<Menu> topMenuList = menuDao.getTopMenuList();
		Map<String, Menu> topMap = new HashMap<String, Menu>();
		for (Menu topmenu : topMenuList) {
			topMap.put(topmenu.getMenuId(), topmenu);
		}

		// 取可授权的二级权限菜单
		List<Menu> menuList = menuDao.getCanAuthedMenus(userId);
		Map<String, MenuTreeVO> authMap = new HashMap<String, MenuTreeVO>();
		for (Menu menu : menuList) {

			if (menu.getMenuType().equals("2")) {
				if (authMap.containsKey(menu.getParentId())) {
					authMap.get(menu.getParentId()).addMenu(menu);
				}
			} else {
				MenuTreeVO tmpMenuTreeVO = new MenuTreeVO();
				tmpMenuTreeVO.setMenu(menu);
				authMap.put(menu.getMenuId(), tmpMenuTreeVO);
			}

		}

		// 组装权限列表
		Map<String, List<MenuTreeVO>> retmap = new HashMap<String, List<MenuTreeVO>>();

		for (String menuId : authMap.keySet()) {

			MenuTreeVO menuTreeVO = authMap.get(menuId);
			Menu tmpMenu = menuTreeVO.getMenu();

			Menu topMenu = topMap.get(tmpMenu.getMenuId());

			if (topMenu != null) {
				if (retmap.containsKey(topMenu.getMenuName())) {
					retmap.get(topMenu.getMenuName()).add(menuTreeVO);
				} else {
					List<MenuTreeVO> list = new ArrayList<MenuTreeVO>();
					list.add(menuTreeVO);
					retmap.put(topMenu.getMenuName(), list);
				}
			}
		}
		return retmap;
	}

	/**
	 * 为商家管理员自动授权，取所有权限
	 * 
	 * @param useScope
	 * @return
	 */
	public List<Menu> getAllAuthMenuForAdmin(String useScope) {
		String[] usescopes = getUsescopes(useScope);

		List<Menu> menuList = menuDao.getAllAuthMenuListByUsescope(usescopes);

		return menuList;
	}

	private String[] getUsescopes(String useScope) {
		String[] usescopes = null;

		if (RoleUsage.PLAT.equals(useScope)) {

			usescopes = new String[] { RoleUsage.PLAT };

		} else if (RoleUsage.CHAIN.equals(useScope)) {

			usescopes = new String[] { RoleUsage.CHAIN, RoleUsage.HOTEL_OR_CHAIN };

		} else if (RoleUsage.HOTEL.equals(useScope)) {

			usescopes = new String[] { RoleUsage.HOTEL, RoleUsage.HOTEL_OR_CHAIN };

		} else if (RoleUsage.AGENTS.equals(useScope)) {
			usescopes = new String[] { RoleUsage.AGENTS };
		}
		return usescopes;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Menu> getChainAuthSubMenus() {
		return menuDao.getChainAuthSubMenus();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Menu> getPlatAuthSubMenus() {
		return menuDao.getPlatAuthSubMenus();
	}

	@Override
	public RoleSearchResult search(RoleCriteria roleCriteria) {
		// TODO Auto-generated method stub
		return roleDao.searchRole(roleCriteria);
	}
	
	@Override
	public Map<String, List<MenuTreeVO>> getAllMenu(String language){
		// retrieve all authorization 
		List<Menu> allMenuList = menuDao.getAllMenu(language);
		Map<String, MenuTreeVO> authMap = new HashMap<String, MenuTreeVO>();
		for (Menu menu : allMenuList) {
			if (menu.getMenuType().equals("2")) {
				if (authMap.containsKey(menu.getParentId())) {
					authMap.get(menu.getParentId()).addMenu(menu);
				}
			} else {
				MenuTreeVO tmpMenuTreeVO = new MenuTreeVO();
				tmpMenuTreeVO.setMenu(menu);
				authMap.put(menu.getMenuId(), tmpMenuTreeVO);
			}

		}

		// 组装权限列表
		Map<String, List<MenuTreeVO>> retmap = new HashMap<String, List<MenuTreeVO>>();

		for (String menuId : authMap.keySet()) {
			MenuTreeVO menuTreeVO = authMap.get(menuId);
			if (menuTreeVO.getMenu() != null) {
				if (retmap.containsKey(menuTreeVO.getMenu().getMenuName())) {
					retmap.get(menuTreeVO.getMenu().getMenuName()).add(menuTreeVO);
				} else {
					List<MenuTreeVO> list = new ArrayList<MenuTreeVO>();
					list.add(menuTreeVO);
					retmap.put(menuTreeVO.getMenu().getMenuName(), list);
				}
			}
		}
		return retmap;
	}
	
	@Override
	public List<Menu> getMenuByRoleId(String roleId,String language){
		return 	menuDao.getMenuByRoleId(roleId, language);
	}
	
	@Override
	public RoleMenu addRoleMenu(RoleMenu roleMenu){
		return roleDao.addRoleMenu(roleMenu);
	}
	
	@Override
	public Integer removeRoleMenuByRoleId(String roleId){
		return roleDao.removeRoleMenuByRoleId(roleId);
	}
	
	@Override
	public RoleChannel addRoleChannel(RoleChannel roleChannel) {
		// TODO Auto-generated method stub
		return roleDao.addRoleChannel(roleChannel);
	}

	@Override
	public Integer removeRoleChannelByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return roleDao.removeRoleChannelByRoleId(roleId);
	}

	@Override
	public List<RoleChannel> getRoleChannelByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return roleDao.getRoleChannelByRoleId(roleId);
	}

	@Override
	public List<String> getChannelIdByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return roleDao.getChannelIdByRoleId(roleId);
	}
	
	@Override
	public List<Role> getAllShowedRoles(){
		return roleDao.getAllShowedRoles();
	}
	
	@Override
	public List<String> getRoleIdsByUserId(String userId){
		return roleDao.getRoleIdsByUserId(userId);
	}
	
	@Override
	public List<RoleChannel> getRoleChannelByRoles(String[] roleIds){
		return roleDao.getRoleChannelByRoles(roleIds);
	}
	
	@Override
	public void setRoleOfRoleChannel(String roleId, List<RoleChannel> chosenRoleChannels) {
		List<RoleChannel> dbRoleChannels = this.getRoleChannelByRoleId(roleId);
		
		List<RoleChannel> toBeDeleted = new ArrayList<RoleChannel>();
		List<RoleChannel> toBeAdded = new ArrayList<RoleChannel>();

		Set<RoleChannel> addRoleChannelSet=new HashSet<RoleChannel>(chosenRoleChannels);
		// 找出不变的和需要删除的
		for (RoleChannel dbRoleChannel : dbRoleChannels) {
			Boolean isFind = false;
			for (RoleChannel chosenRoleChannel : chosenRoleChannels) {
					if (dbRoleChannel.getChannelId().equals(chosenRoleChannel.getChannelId())) {
						// find it,when old data is the same as new data ,we remove chosen data from the new List
						addRoleChannelSet.remove(chosenRoleChannel);
						isFind = true;
						break;
					}
			}
			if (isFind==false) {
				toBeDeleted.add(dbRoleChannel);
			}
		}
		
		toBeAdded.addAll(addRoleChannelSet);
		// 数据库操作
		for (RoleChannel removeRoleChannel : toBeDeleted) {
			roleDao.removeRoleChannelById(removeRoleChannel);
		}
		for (RoleChannel newRoleChannel : toBeAdded) {
			this.addRoleChannel(newRoleChannel);
		}
	}
	
	@Override
	public Boolean isExistedRoleTemplateByRoleCode(String roleCode){
		Integer rCount=roleDao.getRoleCountByName(roleCode);
		if(rCount>0){
			return true;
		}else{
			return false;
		}
	}
}