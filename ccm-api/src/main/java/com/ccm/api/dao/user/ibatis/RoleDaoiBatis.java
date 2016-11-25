package com.ccm.api.dao.user.ibatis;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.user.RoleDao;
import com.ccm.api.model.role.RoleCriteria;
import com.ccm.api.model.role.RoleSearchResult;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.RoleChannel;
import com.ccm.api.model.user.RoleMenu;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.model.user.vo.B2BUserVO;
import com.ccm.api.model.user.vo.UserRoleVO;

/**
 * This class interacts with iBatis's SQL Maps to save/delete and retrieve Role
 * objects.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Repository
public class RoleDaoiBatis extends GenericDaoiBatis<Role, String> implements RoleDao {

	/**
	 * Constructor to create a Generics-based version using Role as the entity
	 */
	public RoleDaoiBatis() {
		super(Role.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Role> getAll() {
		return getSqlMapClientTemplate().queryForList("getRoles", null);
	}

	/**
	 * {@inheritDoc}
	 */
	public Role getRoleByName(String name) {
		return (Role) getSqlMapClientTemplate().queryForObject("getRoleByName", name);
	}

	/**
	 * {@inheritDoc}
	 */

	/**
	 * {@inheritDoc}
	 */
	public void removeRoleByName(String rolename) {
		getSqlMapClientTemplate().update("removeRoleByName", rolename);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleVO> getAuthedRoles(String userId) {
		return getSqlMapClientTemplate().queryForList("getAuthedRoles", userId);
	}

	@Override
	public RoleSearchResult searchRole(RoleCriteria roleCriteria) {
		// TODO Auto-generated method stub
		RoleSearchResult searchResult = new RoleSearchResult();
		int count = (Integer) getSqlMapClientTemplate().queryForObject("searchRolesCount", roleCriteria);
		searchResult.setTotalCount(count);
		List<Role> roleList = getSqlMapClientTemplate().queryForList("searchRoles", roleCriteria);
		searchResult.setResultList(roleList);

		return searchResult;
	}
	
	@Override
	public RoleMenu addRoleMenu(RoleMenu roleMenu){
		getSqlMapClientTemplate().insert("addRoleMenu", roleMenu);
		return roleMenu;
	}
	
	@Override
	public Integer removeRoleMenuByRoleId(String roleId){
		return getSqlMapClientTemplate().delete("removeRoleMenuByRoleId",roleId);
	}

	@Override
	public RoleChannel addRoleChannel(RoleChannel roleChannel) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("addRoleChannel", roleChannel);
		return roleChannel;
	}

	@Override
	public Integer removeRoleChannelByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().delete("removeRoleChannelByRoleId",roleId);
	}
	

	@Override
	public List<RoleChannel> getRoleChannelByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("getRoleChannelByRoleId", roleId);
	}

	@Override
	public List<RoleChannel> getRoleChannelByRoles(String[] roleIds) {
		// TODO Auto-generated method stub
//		List<String>roleIdList=Arrays.asList(roleIds);
		return getSqlMapClientTemplate().queryForList("getRoleChannelByRoles", roleIds);
	}
	
	@Override
	public List<String> getChannelIdByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("getChannelIdByRoleId", roleId);
	}
	
	@Override
	public List<Role> getAllShowedRoles(){
		return getSqlMapClientTemplate().queryForList("getAllShowedRoles");
	}
	
	@Override
	public List<String>getRoleIdsByUserId(String userId){
		return getSqlMapClientTemplate().queryForList("getRoleIdsByUserId",userId);
	}
	
	@Override
	public Integer removeRoleChannelById(RoleChannel roleChannel){
		return getSqlMapClientTemplate().delete("removeRoleChannelById",roleChannel);
	}
	
	@Override
	public Integer getRoleCountByName(String name){
		return (Integer)getSqlMapClientTemplate().queryForObject("getRoleCountByName", name);
		
	}
}
