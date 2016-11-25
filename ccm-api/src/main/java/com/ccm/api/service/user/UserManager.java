package com.ccm.api.service.user;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.ccm.api.model.base.vo.MenuTreeVO;
import com.ccm.api.model.user.Authority;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.EmployeeI18n;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.UserPassword;
import com.ccm.api.model.user.UserRole;
import com.ccm.api.model.user.UserRoleChannel;
import com.ccm.api.model.user.criteria.B2BUserCriteria;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.model.user.vo.B2BUserVO;
import com.ccm.api.model.user.vo.UserRoleVO;
import com.ccm.api.service.base.GenericManager;

/**
 * 用户管理,现在系统中存在 用户 可分为 平台运营人员，商家人员.
 */
public interface UserManager extends GenericManager<B2BUser, String> {

	
	/**
	 * Gets users information based on login name.
	 * 
	 * @param username
	 *            the user's username
	 * @return userDetails populated userDetails object
	 * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
	 *             thrown when user not found in database
	 */
	@Transactional
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	/**
	 * 从淘宝那里取出用户数据保存到数据库中
	 * 
	 * @param sessionId
	 * @return
	 */
	public String saveUserFromTaobao(String sessionId);

	/**
	 * 添加酒店时保存用户与酒店关系
	 * 
	 * @param userId
	 * @param hotelId
	 */
	public void saveUserHotel(String userId, String hotelId, Set<Role> rSet);

	/**
	 * Finds a user by their username.
	 * 
	 * @param username
	 *            the user's username used to login
	 * @return User a populated user object
	 * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
	 *             exception thrown when user not found
	 */
	B2BUser getUserByUsername(String username) throws UsernameNotFoundException;

	/**
	 * Retrieves a list of all users.
	 * 
	 * @return List
	 */
	List<B2BUser> getUsers();

	/**
	 * 根据父用户ID查询所有子用户
	 * 
	 * @param companyId
	 * @return
	 */
	public List<B2BUser> getB2BUserByCompanyId(String companyId);

	/**
	 * 根据用户ID查询父及兄弟用户
	 * 
	 * @param userId
	 * @return
	 */
	List<String> getUserIdByCurUserId(String userId);

	/**
	 * Removes a user from the database by their userId 删除用户，雇员，角色
	 * 
	 * @param userId
	 */
	void removeUser(String userId);

	/**
	 * 重新设置用户信息及获取权限信息
	 * 
	 * @param user
	 * @param credentials
	 */
	public void setUserDetail(B2BUser user, Object credentials);

	/**
	 * 更新用户sessionKey与更新时间
	 * 
	 * @param user
	 */
	public void updateUserSessionKey(B2BUser user);

	/**
	 * 从淘宝登录成功后获取淘宝用户的店铺地址
	 * 
	 * @param user
	 * @return
	 */
	public B2BUser getUserTaobaoShop(B2BUser user);

	/**
	 * 淘宝设置用户权限
	 * 
	 * @param
	 * @return
	 */
	public void setRoleOfUser(String userId, List<UserRoleVO> roleRoles);

	/**
	 * 创建用户
	 * 
	 * @param b2bUser
	 * @return
	 */
	public B2BUser createUserInfo(B2BUser b2bUser);

	/**
	 * 更新用户
	 * 
	 * @param b2bUser
	 * @return
	 */
	public B2BUser updateUserInfo(B2BUser b2bUser);

	/**
	 * mc应用分配用户权限
	 * 
	 * @param userId
	 * @param userRoles
	 */
	public void setRoleOfUserOfMc(String userId, List<UserRoleVO> userRoles);

	/**
	 * 先删除当前用户下默认Role.DEFAULT_ROLES之外的角色，再 复制选择的用户下默认Role.DEFAULT_ROLES之外的角色到当前用户下
	 * 
	 * @param userId
	 * @param curUserId
	 */
	void copyUserRoleByUserId(String userId, String curUserId);

	/**
	 * 修改b2buser初始化状态
	 * 
	 * @param initStatus
	 */
	void updateStatus(int initStatus);

	/************* 以上是整理过的 *****************/

	/**
	 * 取用户,包含雇员信息
	 */
	B2BUser getB2bUser(String userId);

	/***
	 * 商家注册
	 * 
	 * @param comUser
	 * @return
	 */
	public B2BUser comUserRegist(B2BUser b2bUser);

	/***
	 * 将用户与商家管理
	 * 
	 * @param userId
	 * @param companyId
	 */
	public void relateWithCompany(String userId, String companyId);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public void changePassword(String userId, String oldPassword, String newPassword);

	/**
	 * 修改邮箱
	 * 
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public void changeEmail(String userId, String newEmail);

	/**
	 * 密码重置
	 * 
	 * @param userId
	 */
	public void resetPassword(String userId, String newPassword);

	/**
	 * 查询用户
	 * 
	 * @param userId
	 */
	public B2BUserSearchResult search(B2BUserCriteria criteria);

	/**
	 * 权限设置页面：Key可以为RoleCateogry, 或者 hotelName 对于运营和集团来说，Key是RoleCategory 对于酒店来说，Key是HotelName
	 * 
	 * @param groupUserId
	 * @return
	 */
	public Map<String, List<UserRoleVO>> getRoleOfUser(String userId);

	/**
	 * 根据用户ID获取酒店ID
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getHotelIdListByUserId(String userId);

	/**
	 * 求用户的sessionKey
	 * 
	 * @param userId
	 * @return
	 */
	String getUserSessionKey(String userId);

	/**
	 * 返回所有用户的sessionKey
	 * 
	 * @return
	 */
	Map<String, String> getAllUserSession();

	/**
	 * 清用户session缓存
	 */
	void cleanSessionCache();

	/**
	 * 更新用户的账务设置
	 * 
	 * @param user
	 */
	public void changeUserAccount(B2BUser user);

	/**
	 * 根据酒店id取User
	 * 
	 * @param hotelId
	 * @return
	 */
	public B2BUser getUserByHotelId(String hotelId);

	/**
	 * 根据用户获取酒店代码
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getHoteCodeListByUserId(String userId);

	List<B2BUserVO> getAllB2BUser();

	// 根据集团获取该用户sessionKey
	String getChainUserSessionKey(String chainCode);

	EmployeeI18n getDefaultLanguageI18n(B2BUser b2bUser, String language);
	
	B2BUser getB2bUser(String userId,String language);
	
	/**
	 * mc应用分配用户渠道权限 
	 * 
	 * @param userId
	 * @param userRoles
	 */
	public void setRoleOfUserOfMcChannel(String userId,String roleId,List<UserRoleChannel> userRoleChannels);
	
	/**
	 * 根据用户ID获取渠道ID
	 * @param userId
	 * @return
	 */
	List<String> getChannelIdListByUserId(String userId);
	
	/**
	 * 取用户所有已有权限
	 * 
	 * @param userId
	 * @return
	 */
	List<UserRole> getUserRolesByUserId(String userId);
	
	/**
	 * 用户是否锁定
	 * true :锁定，false:未锁定
	 * @param userid
	 * @return 
	 */
	public boolean isLock(String userid);
	
	/**
	 * 修改是否锁定
	 * 0：未锁定  false
	 * 1：锁定  true
	 */
	public void updateLock(String userid,int lock);
	
	/**
	 * 用户密码信息，用户锁定情况
	 * @param userid
	 * @return
	 */
	UserPassword getUserPasswordInfo(String userid);
	
	/**
	 * 修改输入密码错误次数
	 * @param number
	 * @param userId
	 */
	void updatePassFailNumber(int number,String userId);
	
	/**
	 * 修改用户历史密码
	 * @param password  最新密码
	 * @param userId  
	 * 
	 */
	public void updateUserPassword(String userId,String password);
	/**
	 * 登入密码校验
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean checkUserPassowrd(B2BUser user,String password);
	
	/**
	 * 解锁用户
	 */
	public void unlockUser(String userId);
	
	/**
	 * 是否允许修改为此密码
	 * @param userid
	 * @param password
	 * @return
	 */
	public boolean passwordAllowEidt(String userid,String password);
	
	/**
	 * 当前输入密码错误次数
	 * @param user
	 * @param password
	 * @return
	 */
	public int inputFailPasswordNumber(B2BUser user, String password);
	
	/**
	 * 通过用户名精确查找用户
	 * @param username
	 * @return
	 */
	public B2BUserVO getUserByLoginName(String username);
	
	public void resetForgetPassword(String userid,String password);

	Integer removeUserRoleChannelByUserId(String userId);

	Integer removeUserRoleChannelByRoleId(String roleId);

	List<String> getUserIdByRoleId(String roleId);

	void saveUserRoleChannel(String roleId, String userId, String channelId);

}
