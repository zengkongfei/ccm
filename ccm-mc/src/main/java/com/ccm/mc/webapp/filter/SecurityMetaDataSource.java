package com.ccm.mc.webapp.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.StringUtils;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.common.MenuDao;
import com.ccm.api.model.common.vo.MenuVO;
import com.ccm.api.model.user.Role;
import com.ccm.api.service.common.MenuManager;

/**
 * 资源与权限建立管理 在服务器启动时就加载所有访问URL所需的权限，存入resourceMap集合中。
 * Spring在设置完一个bean所有的合作者后，会检查bean是否实现了InitializingBean接口，
 * 如果实现就调用bean的afterPropertiesSet方法。 但这样便造成bean和spring的耦合， 最好用init-method
 * 
 */
public class SecurityMetaDataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

	org.apache.commons.logging.Log log = LogFactory.getLog(SecurityMetadataSource.class);

	private MenuDao menuDao;

	@Resource
	private MenuManager menuManager;

	/**
	 * 构造方法中建立请求url(key)与权限(value)的关系集合
	 */
	public void afterPropertiesSet() throws Exception {

	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;

	}

	/**
	 * 根据请求的url从集合中查询出所需权限
	 */
	public Collection<ConfigAttribute> getAttributes(Object filter) throws IllegalArgumentException {

		String url = ((FilterInvocation) filter).getRequestUrl();

		int position = url.indexOf("?");

		if (-1 != position) {
			url = url.substring(0, position);
		}

		if (url.startsWith("/")) {
			url = url.substring(1);
		}

		List<Role> auths = menuDao.findRoleNameByUrl(url);
		if (auths != null && auths.size() > 0) {
			Collection<ConfigAttribute> cAttributes = new ArrayList<ConfigAttribute>();

			for (Role role : auths) {
				ConfigAttribute ca = new SecurityConfig(role.getName());
				cAttributes.add(ca);
			}

			MenuVO mvo = menuManager.getParentSubMenuIdByUrl(SecurityHolder.getUserId(), url);
			
			if (mvo != null) {
				if (!StringUtils.hasLength(mvo.getPmenuId())) {
					SecurityHolder.getSession().setAttribute("menuId", mvo.getMenuId());
					SecurityHolder.getSession().setAttribute("tmenuId", "");
				} else {
					SecurityHolder.getSession().setAttribute("menuId", mvo.getPmenuId());
					SecurityHolder.getSession().setAttribute("tmenuId", mvo.getMenuId());
				}
			}
			return cAttributes;
		} else {

			Collection<ConfigAttribute> defaultConfAttrs = new ArrayList<ConfigAttribute>();

			return defaultConfAttrs;

		}

	}

	/**
	 * 返回false则报错 SecurityMetadataSource does not support secure object class:
	 * class org.springframework.security.web.FilterInvocation
	 */
	public boolean supports(Class<?> arg0) {
		return true;
	}

	@Resource
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public MenuDao getMenuDao() {
		return menuDao;
	}

}
