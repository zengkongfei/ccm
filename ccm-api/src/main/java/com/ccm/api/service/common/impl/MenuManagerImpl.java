/**
 * 
 */
package com.ccm.api.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.common.MenuDao;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.common.vo.MenuCriteria;
import com.ccm.api.model.common.vo.MenuVO;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.common.MenuManager;

@Service("menuManager")
public class MenuManagerImpl extends GenericManagerImpl<Menu, String> implements MenuManager {
	
	private MenuDao menuDao;

	private Map<String, MenuVO> menuUrlMap = new HashMap<String, MenuVO>();
	private Map<String, MenuVO> parentSubMenuIdMap = new HashMap<String, MenuVO>();

	@Autowired
	public MenuManagerImpl(MenuDao menuDao) {
		super(menuDao);
		this.menuDao = menuDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Menu> getAuthedTopMenu(B2BUser b2bUser,String language) {

		// 取授权的一级菜单
		String userId = b2bUser.getUserId();
		return menuDao.getAuthedTopMenus(userId,language);

	}
	
	/**
	 * 根据父菜单取用户拥有的二级菜单 
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> getTwoMenusByParentIds_i18n(MenuCriteria menuCriteria) {
		return menuDao.getTwoMenusByParentIds_i18n(menuCriteria);
	}
	
	@Override
	public List<Menu> getAuthedTopMenusByUserIds(MenuCriteria menuCriteria) {
		return menuDao.getAuthedTopMenusByUserIds(menuCriteria);
	}
	
	public List<Menu> getAuthedTwoMenus(String parentId, String userId,String language) {
		return menuDao.getAuthedTwoMenus(parentId, userId,language);
	}

	@Override
	public Menu getMenuByParentId(String menuId, String parentId) {
		return menuDao.getMenuByParentId(menuId, parentId);
	}

	@Override
	public List<Menu> getTopMenuList() {
		return menuDao.getTopMenuList();
	}
	@Override
	public List<Menu> getTopMenuList(String language) {
		return menuDao.getTopMenuList(language);
	}

	@Override
	public List<Menu> getTwoMenus(String parentId) {
		return menuDao.getTwoMenus(parentId);
	}

	public MenuVO findRecordLogMenuByUrl(String url) {
		
		if (menuUrlMap.get(url) == null) {
			List<MenuVO> mvoList = menuDao.findRecordLogMenuByUrl(url);
			if (mvoList.size() > 0) {
				menuUrlMap.put(url, mvoList.get(0));
			} else {
				menuUrlMap.put(url, new MenuVO());
			}
		}
		return menuUrlMap.get(url);
	}
	
	public MenuVO findRecordLogMenuByUrl(String url,String langauge) {

		if (menuUrlMap.get(url+langauge) == null) {
			List<MenuVO> mvoList = menuDao.findRecordLogMenuByUrl(url,langauge);
			if (mvoList.size() > 0) {
				menuUrlMap.put(url+langauge, mvoList.get(0));
			} else {
				menuUrlMap.put(url+langauge, new MenuVO());
			}
		}
		return menuUrlMap.get(url+langauge);
	}

	public MenuVO getParentSubMenuIdByUrl(String userId, String url) {
		if (!StringUtils.hasText(userId)) {
			return null;
		}
		String key = userId + url;
		if (parentSubMenuIdMap.get(key) == null) {
			List<MenuVO> mvoList = menuDao.getParentSubMenuIdByUrl(userId, url);
			if (mvoList.size() > 0) {
				parentSubMenuIdMap.put(key, mvoList.get(0));
			} else {
				parentSubMenuIdMap.put(key, new MenuVO());
			}
		}
		return parentSubMenuIdMap.get(key);
	}

	public void cleanHashMap() {
		menuUrlMap.clear();
		parentSubMenuIdMap.clear();
	}

	@Override
	public List<Menu> getAuthedTwoMenusByUrl(String userId, String url,String language) {
		return menuDao.getAuthedTwoMenusByUrl(userId, url,language);
	}

	@Override
	public Menu getMenuByUrl(String url,String language) {
		return menuDao.getMenuByUrl(url,language);
	}

	@Override
	public Menu getMenuById(String menuId, String language) {
		return menuDao.getMenuById(menuId, language);
	}
	
	@Override
	public Menu getMenuByChildId(String childId,String language) {
		return menuDao.getMenuById(childId, language);
	}

	@Override
	public List<Menu> getTwoMenus(String parentId, String language) {
		return menuDao.getTwoMenus(parentId,language);
	}

	@Override
	public List<Menu> getAllMenu(String language) {
		// TODO Auto-generated method stub
		return menuDao.getAllMenu(language);
	}

	@Override
	public List<Menu> getMenuByRoleId(String roleId, String language) {
		// TODO Auto-generated method stub
		return menuDao.getMenuByRoleId(roleId,language);
	}
	
}
