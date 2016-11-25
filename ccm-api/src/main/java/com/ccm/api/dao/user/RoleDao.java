package com.ccm.api.dao.user;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.role.RoleCriteria;
import com.ccm.api.model.role.RoleSearchResult;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.RoleChannel;
import com.ccm.api.model.user.RoleMenu;
import com.ccm.api.model.user.vo.UserRoleVO;

/**
 * Role Data Access Object (DAO) interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface RoleDao extends GenericDao<Role, String> {
	/**
	 * Gets role information based on rolename
	 * 
	 * @param rolename
	 *            the rolename
	 * @return populated role object
	 */
	Role getRoleByName(String rolename);

	/**
	 * Removes a role from the database by name
	 * 
	 * @param rolename
	 *            the role's rolename
	 */
	void removeRoleByName(String rolename);

	/**
	 * 取用户已有权限
	 * 
	 * @param userId
	 * @return
	 */
	List<UserRoleVO> getAuthedRoles(String userId);

	RoleSearchResult searchRole(RoleCriteria roleCriteria);

	RoleMenu addRoleMenu(RoleMenu roleMenu);

	Integer removeRoleMenuByRoleId(String roleId);

	RoleChannel addRoleChannel(RoleChannel roleChannel);
	
	Integer removeRoleChannelByRoleId(String roleId);
	
	List<RoleChannel>getRoleChannelByRoleId(String roleId);
	
	List <String>getChannelIdByRoleId(String roleId);

	List<Role> getAllShowedRoles();

	List<String> getRoleIdsByUserId(String userId);

	List<RoleChannel> getRoleChannelByRoles(String[] roleIds);

	Integer removeRoleChannelById(RoleChannel roleChannel);

	Integer getRoleCountByName(String name);
}
