package com.ccm.api.service.user;

import java.util.List;
import java.util.Map;

import com.ccm.api.model.base.vo.MenuTreeVO;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.role.RoleCriteria;
import com.ccm.api.model.role.RoleSearchResult;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.RoleChannel;
import com.ccm.api.model.user.RoleMenu;
import com.ccm.api.model.user.criteria.B2BUserCriteria;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.service.base.GenericManager;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface RoleManager extends GenericManager<Role, String> {
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	List getRoles();

	/**
	 * {@inheritDoc}
	 */
	Role getRole(String rolename);

	/**
	 * {@inheritDoc}
	 */
	Role saveRole(Role role);

	/**
	 * {@inheritDoc}
	 */
	void removeRoleByName(String rolename);
	
	void removeRoleById(String roleId);
	/**
	 * 根据授权使用范围找所有可授权的权限
	 * 
	 * @return
	 * @param userId
	 * @return String 为权限分类，即菜单大类，MenuTreeVO未权限菜单，可能包含子权限
	 */
	public Map<String, List<MenuTreeVO>> getAllAuthedMenu(String userId);

	/**
	 * 为商家管理员自动授权，取所有权限
	 * 
	 * @param useScope
	 * @return
	 */
	public List<Menu> getAllAuthMenuForAdmin(String useScope);

	/**
	 * 死代码，获取集团商家 中 酒店列表 中所有操作列权限
	 * 
	 * @param useScope
	 * @return
	 */
	public List<Menu> getChainAuthSubMenus();

	/**
	 * 死代码，获取运营 酒店列表 中所有操作列权限
	 * 
	 * @param useScope
	 * @return
	 */
	public List<Menu> getPlatAuthSubMenus();

	Map<String, List<MenuTreeVO>> getAllAuthedMenu(String userId,
			String language);

	public RoleSearchResult search(RoleCriteria roleCriteria);

	Map<String, List<MenuTreeVO>> getAllMenu(
			String language);

	List<Menu> getMenuByRoleId(String roleId, String language);

	RoleMenu addRoleMenu(RoleMenu roleMenu);

	Integer removeRoleMenuByRoleId(String roleId);

	RoleChannel addRoleChannel(RoleChannel roleChannel);

	Integer removeRoleChannelByRoleId(String roleId);
	
	List<RoleChannel>getRoleChannelByRoleId(String roleId);
	
	List <String>getChannelIdByRoleId(String roleId);

	List<Role> getAllShowedRoles();

	List<String> getRoleIdsByUserId(String userId);

	List<RoleChannel> getRoleChannelByRoles(String[] roleIds);

	void setRoleOfRoleChannel(String roleId,
			List<RoleChannel> chosenRoleChannels);

	Boolean isExistedRoleTemplateByRoleCode(String roleCode);
	
}
