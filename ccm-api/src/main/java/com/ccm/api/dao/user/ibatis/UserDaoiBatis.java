package com.ccm.api.dao.user.ibatis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.base.ibatis.IBatisDaoUtils;
import com.ccm.api.dao.user.UserDao;
import com.ccm.api.model.hotel.Company;
import com.ccm.api.model.hotel.Hotel;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.UserPassword;
import com.ccm.api.model.user.UserRole;
import com.ccm.api.model.user.UserRoleChannel;
import com.ccm.api.model.user.criteria.B2BUserCriteria;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.model.user.vo.B2BUserVO;
import com.ccm.api.model.user.vo.UserRoleVO;

/**
 * This class interacts with iBatis's SQL Maps to save and retrieve User related objects.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Repository("userDao")
public class UserDaoiBatis extends GenericDaoiBatis<B2BUser, String> implements UserDao {

	/**
	 * Constructor that sets the entity to User.class.
	 */
	public UserDaoiBatis() {
		super(B2BUser.class);
	}

	/************* 以下是整理过的 *****************/

	@Override
	public void saveHotelUserRole(String roleId, String userId, String hotelId) {
		UserRole ur = new UserRole();
		ur.setUserId(userId);
		ur.setRoleId(roleId);
		ur.setHotelId(hotelId);
		createBaseObject(ur);
		getSqlMapClientTemplate().insert("addUserRole", ur);
	}

	/**
	 * 复制选择的用户角色到当前用户下，不包括默认Role.DEFAULT_ROLES的角色
	 */
	public void saveUserRoleBatch(String orgUserId, String desUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orgUserId", orgUserId);
		param.put("desUserId", desUserId);
		param.put("roleIds", Role.DEFAULT_ROLES);
		getSqlMapClientTemplate().insert("addUserRoleList", param);
	}

	/**
	 * {@inheritDoc}
	 */
	public B2BUser createUser(final B2BUser user) {

		IBatisDaoUtils.prepareObjectForSaveOrUpdate(user);
		createBaseObject(user);
		getSqlMapClientTemplate().insert("addB2BUser", user);

		addUserRoles(user);

		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	public B2BUser saveUser(final B2BUser user) {

		if (user.getUserId() == null || "".equals(user.getUserId())) {
			save(user);
			addUserRoles(user);
		} else {
			save(user);
			deleteUserRoles(user.getUserId());
			addUserRoles(user);
		}
		return user;
	}

	/**
	 * Convenience method to delete roles
	 * 
	 * @param userId
	 *            the id of the user to delete
	 */
	public void deleteUserRoles(final String userId) {
		getSqlMapClientTemplate().update("deleteUserRoles", userId);
	}

	@Override
	public void updateUserSessionKeyPwd(B2BUser user) {
		updateBaseObject(user);
		getSqlMapClientTemplate().update("updateUserSessionKeyPwd", user);

	}

	@Override
	public void updateStatus(String userId, int initStatus) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("initStatus", initStatus);
		getSqlMapClientTemplate().update("updateB2bUserStatus", param);
	}

	/**
	 * 更新用户的账务设置
	 * 
	 * @param user
	 */
	public void changeUserAccount(B2BUser user) {
		getSqlMapClientTemplate().update("updateUserAccountSet", user);
	}

	@SuppressWarnings("unchecked")
	public List<String> getHotelCodeOfUser(String userId) {
		return getSqlMapClientTemplate().queryForList("getHotelCodeOfUser", userId);

	}

	/**
	 * 
	 * @param userId
	 * @param hotelId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getHotelIdOfUser(String userId) {
		return getSqlMapClientTemplate().queryForList("getHotelIdOfUser", userId);

	}

	/**
	 * 根据用户ID查询所有角色
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getUserRoles(String userId) {
		return getSqlMapClientTemplate().queryForList("getUserRoles", userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public B2BUser getUserByUsername(String username) {

		return (B2BUser) getSqlMapClientTemplate().queryForObject("getUserByUsername", username);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<B2BUser> getAllUserSessionKey() {

		return getSqlMapClientTemplate().queryForList("getUserSessionKey");

	}

	/**
	 * 获取b2buser表信息
	 */
	public B2BUser getUserById(String userId) {
		return (B2BUser) getSqlMapClientTemplate().queryForObject("getB2BUser", userId);
	}

	/**
	 * 根据父用户ID查询所有子用户
	 */
	@SuppressWarnings("unchecked")
	public List<B2BUser> getB2BUserByCompanyId(String companyId) {
		return getSqlMapClientTemplate().queryForList("getB2BUserByCompanyId", companyId);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public B2BUserSearchResult searchUser(B2BUserCriteria criteria) {

		B2BUserSearchResult searchResult = new B2BUserSearchResult();

		// 如果是第一页,系统计算该条件下总共多少条记录
		// if (criteria.getPageNum() == 1) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("searchB2BUserCount", criteria);
		searchResult.setTotalCount(count);
		// }

		List<B2BUserVO> list = getSqlMapClientTemplate().queryForList("searchB2BUsers", criteria);

		searchResult.setResultList(list);

		return searchResult;
	}

	private void addUserRoles(final B2BUser user) {
		if (user.getRoles() != null) {
			for (Role role : user.getRoles()) {
				saveHotelUserRole(role.getRoleId(), user.getUserId(), null);
			}
		}
	}

	/**
	 * MC应用中得到所有以选中的权限
	 */
	@SuppressWarnings("unchecked")
	public List<UserRoleVO> getChecekedRoleOfUser(String userId) {

		List<UserRoleVO> result = (List<UserRoleVO>) getSqlMapClientTemplate().queryForList("getAllCheckedHotelRoleOfUser", userId);

		return result;
	}

	/**
	 * MC应用中删除用户角色
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteHotelUserRole(String roleId, String userId, String hotelId) {
		Map param = new HashMap();
		param.put("roleId", roleId);
		param.put("userId", userId);
		param.put("hotelId", hotelId);
		getSqlMapClientTemplate().delete("deleteHotelUserRole", param);
	}

	/**
	 * 删除原来用户的角色，但不包括Role.DEFAULT_ROLES的默认角色
	 */
	public void deleteUserRoleByURID(String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleIds", Role.DEFAULT_ROLES);
		param.put("userId", userId);
		getSqlMapClientTemplate().delete("deleteUserRoleByURID", param);
	}

	/************* 以上是整理过的 *****************/

	/**
	 * Get user by id.
	 * 
	 * @param userId
	 *            the user's id
	 * @return a populated user object
	 */
	@Override
	public B2BUser get(String userId) {

		B2BUser user = getUserById(userId);

		if (user == null) {
			log.warn("uh oh, user not found...");
			throw new ObjectRetrievalFailureException(B2BUser.class, userId);
		} else {
			List<Role> roles = getUserRoles(user.getUserId());
			user.setRoles(new HashSet<Role>(roles));
		}

		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<B2BUser> getUsers() {
		List users = getSqlMapClientTemplate().queryForList("getB2BUsers", null);

		// get the roles for each user
		for (int i = 0; i < users.size(); i++) {
			B2BUser user = (B2BUser) users.get(i);

			user = get(user.getUserId());
			users.set(i, user);
		}

		return users;
	}

	/**
	 * 删除用户，角色
	 */
	@Override
	public void remove(String userId) {
		deleteUserRoles(userId);
		deleteUserRoleChannels(userId);
		getSqlMapClientTemplate().update("deleteB2BUser", userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getUserPassword(String username) {
		return (String) getSqlMapClientTemplate().queryForObject("getUserPassword", username);
	}

	@Override
	public String getRoleUsageOfUser(String userId) {

		Company company = (Company) getSqlMapClientTemplate().queryForObject("getCompanyOfUser", userId);
		if (company != null) {
			// if (CompanyType.PLAT_COMPANY_ID.equals(company.getCompanyId())) {
			// return RoleUsage.PLAT; // 运营
			// } else if (CompanyType.HOTEL.equals(company.getCompanyType())) {
			// return RoleUsage.HOTEL; // 酒店
			// }
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleVO> getChecekedRoleOfUser(String userId, List<String> usageList) {
		Map param = new HashMap();
		param.put("userId", userId);
		param.put("usageList", usageList);
		List<UserRoleVO> result = (List<UserRoleVO>) getSqlMapClientTemplate().queryForList("getCheckedRoleOfUser", param);

		for (UserRoleVO roleVO : result) {
			roleVO.setUserId(userId);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleVO> getChecekedRoleOfUser(String userId, List<String> hotelUsageList, String hotelId, String hotelName) {
		Map param = new HashMap();
		param.put("userId", userId);
		param.put("usageList", hotelUsageList);
		param.put("hotelId", hotelId);
		List<UserRoleVO> result = (List<UserRoleVO>) getSqlMapClientTemplate().queryForList("getHotelRoleOfUser", param);

		for (UserRoleVO roleVO : result) {
			roleVO.setUserId(userId);
			roleVO.setHotelId(hotelId);
			roleVO.setHotelName(hotelName);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean getHotelUserRole(String roleId, String userId, String hotelId) {
		Map param = new HashMap();
		param.put("roleId", roleId);
		param.put("userId", userId);
		param.put("hotelId", hotelId);
		String hotelRoleId = (String) getSqlMapClientTemplate().queryForObject("getHotelUserRole", param);
		if (hotelRoleId != null) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void changePassword(String userId, String encodePassword) {

		Map param = new HashMap();
		param.put("userId", userId);
		param.put("password", encodePassword);

		getSqlMapClientTemplate().update("changePassword", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<B2BUser> getUserByHotelId(String hotelId) {
		return getSqlMapClientTemplate().queryForList("getUserByHotelId", hotelId);
	}

	@Override
	public String getSessionKeyByChainCode(String channelCode) {
		return (String) getSqlMapClientTemplate().queryForObject("getSessionKeyByChainCode", channelCode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<B2BUserVO> getAllB2BUser() {
		return getSqlMapClientTemplate().queryForList("getAllB2BUser");
	}

	@Override
	public void saveCHannelUserRole(String roleId, String userId, String channelId) {
		UserRoleChannel urc = new UserRoleChannel();
		urc.setUserId(userId);
		urc.setRoleId(roleId);
		urc.setChannelId(channelId);
		createBaseObject(urc);
		getSqlMapClientTemplate().insert("saveCHannelUserRole", urc);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void deleteCHannelUserRole(String roleId, String userId, String channelId) {
		Map param = new HashMap();
		param.put("roleId", roleId);
		param.put("userId", userId);
		param.put("channelId", channelId);
		getSqlMapClientTemplate().delete("deleteCHannelUserRole", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleChannel> getChecekedChannelRoleOfUser(String userId) {
		List<UserRoleChannel> result = (List<UserRoleChannel>) getSqlMapClientTemplate().queryForList("getChecekedChannelRoleOfUser", userId);

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleChannel> getCheckedChannelRoleByUserRole(UserRole userRole) {
		List<UserRoleChannel> result = (List<UserRoleChannel>) getSqlMapClientTemplate().queryForList("getCheckedChannelRoleByUserRole", userRole);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getChannelIdListByUserId(String userId) {
		return getSqlMapClientTemplate().queryForList("getChannelIdListByUserId", userId);
	}

	@Override
	public void deleteUserRoleChannels(String userId) {
		getSqlMapClientTemplate().update("deleteUserRoleChannels", userId);
		
	}

	@Override
	public void updateIsLive(B2BUser b2bUser) {
		getSqlMapClientTemplate().update("updateIsLive", b2bUser);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getUserRolesByUserId(String userId) {
		return getSqlMapClientTemplate().queryForList("getUserRolesByUserId", userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> getHotelsByUserId(String userId){
		return getSqlMapClientTemplate().queryForList("getHotelsByUserId", userId);
	}

	@Override
	public UserPassword findUserPassword(String userId) {
		return (UserPassword) getSqlMapClientTemplate().queryForObject("findUserPassword", userId);
	}

	@Override
	public UserPassword getUserInfo(String userid) {
		return (UserPassword) getSqlMapClientTemplate().queryForObject("getUserInfo", userid);
	}

	@Override
	public UserPassword getUserPasswordInfo(String userid) {
		return (UserPassword) getSqlMapClientTemplate().queryForObject("getUserPasswordInfo", userid);
	}

	@Override
	public void updateUserPassword(UserPassword userPassword) {
		getSqlMapClientTemplate().update("updateUserPassword", userPassword);
	}

	@Override
	public void updateLocak(int islocak,String userId) {
		Map param = new HashMap();
		param.put("islocak", islocak);
		param.put("userId", userId);
		getSqlMapClientTemplate().update("updateLocak", param);
		
	}

	@Override
	public void addUserPassword(UserPassword userPassword) {
		getSqlMapClientTemplate().insert("addUserPassword", userPassword);
	}

	@Override
	public void updatePassFailNumber(int number, String userId) {
		Map param = new HashMap();
		param.put("passwordFailNumber", number);
		param.put("userId", userId);
		getSqlMapClientTemplate().update("updatePassFailNumber", param);
	}

	@Override
	public void updatePasswordIsInit(int passwordIsInit, String userId) {
		Map param = new HashMap();
		param.put("passwordIsInit", passwordIsInit);
		param.put("userId", userId);
		getSqlMapClientTemplate().update("updatePasswordIsInit", param);
		
	}

	@Override
	public B2BUserVO getUserByLoginName(String username) {
		return (B2BUserVO) getSqlMapClientTemplate().queryForObject("getUserByLoginName", username);
	}

	@Override
	public Integer removeUserRoleChannelByRoleId(String roleId){
		return getSqlMapClientTemplate().delete("removeUserRoleChannelByRoleId", roleId);
	}
	
	@Override
	public Integer removeUserRoleChannelByUserId(String userId){
		return getSqlMapClientTemplate().delete("removeUserRoleChannelByUserId", userId);
	}
	
	@Override
	public List<String> getUserIdByRoleId(String roleId){
		return getSqlMapClientTemplate().queryForList("getUserIdByRoleId", roleId);
	}
	
}
