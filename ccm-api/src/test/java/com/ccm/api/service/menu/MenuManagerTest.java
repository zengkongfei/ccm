package com.ccm.api.service.menu;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.dao.common.MenuDao;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.common.vo.MenuCriteria;
import com.ccm.api.service.base.BaseManagerTestCase;
import com.ccm.api.service.common.MenuManager;

public class MenuManagerTest extends BaseManagerTestCase {

	@Autowired
	private MenuManager mm;
	@Autowired
	private MenuDao md;

	@Test
	public void getTwoMenusByParentIds_i18nTest() throws Exception {
		List<String> ps = new ArrayList<String>();
		ps.add("12");
		//ps.add("72");
		ps.add("4");
		MenuCriteria menuCriteria = new MenuCriteria();
		menuCriteria.setLanguage("zh_CN");
		menuCriteria.setParentIds(ps);
		
		//List<Menu> ms =md.getTwoMenusByParentIds_i18n(menuCriteria);
		List<Menu> ms =mm.getTwoMenusByParentIds_i18n(menuCriteria);
		
		for (Menu menu : ms) {
			System.out.println(menu.getMenuId());
		}
	}

	// @Test
	public void getAuthedTopMenusByUserIdsTest() throws Exception {

		List<String> us = new ArrayList<String>();
		us.add("1");
		us.add("2");

		MenuCriteria menuCriteria = new MenuCriteria();

		menuCriteria.setLanguage("zh_CN");
		menuCriteria.setUserIds(us);

		List<Menu> ms = mm.getAuthedTopMenusByUserIds(menuCriteria);
		for (Menu menu : ms) {
			System.out.println(menu.getMenuId());
		}

	}
}
