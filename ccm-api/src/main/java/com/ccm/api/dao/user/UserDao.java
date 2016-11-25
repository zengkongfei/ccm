package com.ccm.api.dao.user;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ccm.api.dao.base.GenericDao;
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
 * User Data Access Object (GenericDao) interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface UserDao extends GenericDao<B2BUser, String> {

	/************* 以下是整理过的 *****************/

	/**
	 * 添加某用户特定酒店的特定权限
	 * 
	 * @param roleId
	 * @param userId
	 * @param hotelId
	 */
	void saveHotelUserRole(String roleId, String userId, String hotelId);

	/**
	 * 复制选择的用户角色到当前用户下，不包括默认Role.DEFAULT_ROLES的角色
	 * 
	 * @param orgUserId
	 * @param desUserId
	 */
	void saveUserRoleBatch(String orgUserId, String desUserId);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	public B2BUser createUser(final B2BUser user);

	/**
	 * 删除用户角色
	 * 
	 * @param userId
	 */
	public void deleteUserRoles(final String userId);

	/**
	 * 修改用户授权sessionKey与密码
	 * 
	 * @param user
	 */
	void updateUserSessionKeyPwd(B2BUser user);

	/**
	 * 修改b2buser初始化状态
	 * 
	 * @param userId
	 * @return
	 */
	void updateStatus(String userId, int initStatus);

	/**
	 * 更新用户的账务设置
	 * 
	 * @param user
	 */
	public void changeUserAccount(B2BUser user);

	/**
	 * 根据用户获取酒店代码
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getHotelCodeOfUser(String userId);

	/**
	 * 用户获取酒店ID
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getHotelIdOfUser(String userId);

	/**
	 * 根据用户ID查询所有角色
	 * 
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRoles(String userId);

	/**
	 * 根据用户取用户,如果不存在则返回null
	 * 
	 * @param username
	 * @return
	 */
	B2BUser getUserByUsername(String username);

	/**
	 * 求出所有用户的授权sessionKey
	 * 
	 * @return
	 */
	List<B2BUser> getAllUserSessionKey();

	/**
	 * 根据父用户ID查询所有子用户
	 * 
	 * @param companyId
	 * @return
	 */
	public List<B2BUser> getB2BUserByCompanyId(String companyId);

	/**
	 * 根据ID求用户信息，如果不存在则返回null而不是抛异常
	 * 
	 * @param userId
	 * @return
	 */
	B2BUser getUserById(String userId);

	/**
	 * 查询用户
	 * 
	 * @param userCriteria
	 * @return
	 */
	B2BUserSearchResult searchUser(B2BUserCriteria userCriteria);

	/**
	 * MC应用中得到所有以选中的权限
	 * 
	 * @param userId
	 * @return
	 */
	List<UserRoleVO> getChecekedRoleOfUser(String userId);

	/**
	 * MC应用中删除某用户特定酒店的特定权限
	 * 
	 * @param roleId
	 * @param userId
	 * @param hotelId
	 */
	void deleteHotelUserRole(String roleId, String userId, String hotelId);

	/**
	 * 删除原来用户的角色，但不包括Role.DEFAULT_ROLES的默认角色
	 * 
	 * @param userId
	 */
	void deleteUserRoleByURID(String userId);

	/************* 以上是整理过的 *****************/

	/**
	 * Gets a list of users ordered by the uppercase version of their username.
	 * 
	 * @return List populated list of users
	 */
	List<B2BUser> getUsers();

	/**
	 * Retrieves the password in DB for a user
	 * 
	 * @param username
	 *            the user's username
	 * @return the password in DB, if the user is already persisted
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	String getUserPassword(String username);

	/**
	 * 保存用户信息,同时保存角色信息
	 */
	B2BUser saveUser(B2BUser user);

	/**
	 * 得到一个用户的RoleUsage
	 * 
	 * @param userId
	 * @return
	 */
	String getRoleUsageOfUser(String userId);

	/**
	 * 显示所选权限
	 * 
	 * @param userId
	 * @param usageList
	 * @return
	 */
	List<UserRoleVO> getChecekedRoleOfUser(String userId, List<String> usageList);

	/**
	 * 得到特定用户特定酒店的所有权限
	 * 
	 * @param userId
	 * @param hotelUsageList
	 * @param hotelId
	 * @param hotelName
	 * @return
	 */
	List<UserRoleVO> getChecekedRoleOfUser(String userId, List<String> hotelUsageList, String hotelId, String hotelName);

	/**
	 * 得到该用户是否具备某特定酒店的权限
	 * 
	 * @param roleId
	 * @param userId
	 * @param hotelId
	 * @return
	 */
	Boolean getHotelUserRole(String roleId, String userId, String hotelId);

	/**
	 * 更改密码
	 * 
	 * @param userId
	 * @param encodePassword
	 */
	void changePassword(String userId, String encodePassword);

	public List<B2BUser> getUserByHotelId(String hotelId);

	/**
	 * 查询SessionKey根据channelCode
	 * 
	 * @param channelCode渠道编码
	 */
	public String getSessionKeyByChainCode(String channelCode);

	List<B2BUserVO> getAllB2BUser();
	
	/**
	 * 添加某用户特定渠道的特定权限
	 * @param roleId
	 * @param userId
	 * @param channelId
	 */
	public void saveCHannelUserRole(String roleId, String userId, String channelId);
	/**
	 * MC应用中删除某用户特定渠道的特定权限
	 * @param roleId
	 * @param userId
	 * @param channelId
	 */
	public void deleteCHannelUserRole(String roleId, String userId, String channelId);
	
	/**
	 * MC应用中得到所有以选中的渠道权限
	 * 
	 * @param userId
	 * @return
	 */
	List<UserRoleChannel> getChecekedChannelRoleOfUser(String userId);

	/**
	 * 根据用户ID获取渠道ID
	 * @param userId
	 * @return
	 */
	List<String> getChannelIdListByUserId(String userId);
	
	/**
	 * 删除用户渠道权限
	 * 
	 * @param userId
	 */
	public void deleteUserRoleChannels(String userId);

	void updateIsLive(B2BUser b2bUser);
	
	/**
	 * 取用户所有已有权限
	 * 
	 * @param userId
	 * @return
	 */
	List<UserRole> getUserRolesByUserId(String userId);
	
	
	List<Hotel> getHotelsByUserId(String userId);
	/**
	 * 用户历史密码信息
	 * @param userId
	 * @return
	 */
	UserPassword findUserPassword(String userId);
	
	/**
	 * 用户信息，包括用户密码情况
	 * @param userid
	 * @return
	 */
	UserPassword getUserInfo(String userid);
	
	/**
	 * 添加用户密码信息
	 * @param userPassword
	 */
	void addUserPassword(UserPassword userPassword);
	
	/**
	 * 用户密码信息，用户锁定情况
	 * @param userid
	 * @return
	 */
	UserPassword getUserPasswordInfo(String userid);
	
	/**
	 * 修改用户历史密码数据
	 * @param UserPassword
	 */
	void updateUserPassword(UserPassword userPassword);
	
	/**
	 * 修改用户锁定，false:解除锁定，true:锁定
	 * @param userId
	 * @param islocak
	 */
	void updateLocak(int islocak,String userId);
	
	/**
	 * 修改是否是新建用户
	 * @param passwordIsInit
	 * @param userId
	 */
	void updatePasswordIsInit(int passwordIsInit,String userId);
	
	/**
	 * 修改输入密码错误次数
	 * @param number
	 * @param userId
	 */
	void updatePassFailNumber(int number,String userId);
	/**
	 * 通过用户名精确查找用户
	 * @param username
	 * @return
	 */
	public B2BUserVO getUserByLoginName(String username);

	Integer removeUserRoleChannelByRoleId(String roleId);

	Integer removeUserRoleChannelByUserId(String userId);

	List<String> getUserIdByRoleId(String roleId);

	List<UserRoleChannel> getCheckedChannelRoleByUserRole(UserRole userRole);
}
