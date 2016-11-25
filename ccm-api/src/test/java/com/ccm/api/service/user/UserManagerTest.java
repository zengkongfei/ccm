package com.ccm.api.service.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ccm.api.model.hotel.Company;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.criteria.B2BUserCriteria;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.model.user.vo.UserRoleVO;
import com.ccm.api.service.base.BaseManagerTestCase;
import org.springframework.test.annotation.Rollback;

public class UserManagerTest extends BaseManagerTestCase {
	// private Log log = LogFactory.getLog(UserManagerTest.class);
	@Autowired
	private UserManager mgr;

	// @Autowired
	// private RoleManager roleManager;
	// private B2BUser user;
	//
	// @Test
	// public void testGetUser() throws Exception {
	// Map<String, List<UserRoleVO>> map =
	// mgr.getRoleOfUser("ecba5e39b6774e409f04a4e9228af63d");
	// map.keySet();
	// }

	/**
	 * this test case has been refined.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSaveUser() throws Exception {

		B2BUser user = new B2BUser();
		user.setUsername("testUserName");
		user.setPassword("testPassword");

		user = mgr.createUserInfo(user);

		B2BUser newUser = mgr.getB2bUser(user.getUserId());

		assertEquals("testUserName", newUser.getUsername());
		// assertEquals(new ShaPasswordEncoder().encodePassword("testPassword",
		// null), newUser.getPassword());
		assertNotNull(newUser.getUserId());

	}

	/**
	 * 测试运营用户权限
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserRoleForPlatform() throws Exception {
		B2BUser user = new B2BUser();
		user.setUsername("testUserName");
		user.setPassword("testPassword");

		user = mgr.createUserInfo(user);

		// 运营用户
		Map<String, List<UserRoleVO>> roleMap = mgr.getRoleOfUser(user.getUserId());
		Assert.assertNotSame(0, roleMap.keySet().size());
		for (String categoryName : roleMap.keySet()) {
			if (categoryName.equals("财务管理")) {
				List<UserRoleVO> voList = roleMap.get(categoryName);
				for (UserRoleVO vo : voList) {
					assertNotNull(vo.getRoleId());
					assertNotNull(vo.getRoleCnName());
					assertNotNull(vo.getUserId());
					vo.setChecked(true);
				}
			}
		}

		// 选中财务管理
		List<UserRoleVO> voList = new ArrayList<UserRoleVO>();
		for (String categoryName : roleMap.keySet()) {
			voList.addAll(roleMap.get(categoryName));
		}

		mgr.setRoleOfUser(user.getUserId(), voList);

		// 重新取出看是否选中
		Map<String, List<UserRoleVO>> newRoleMap = mgr.getRoleOfUser(user.getUserId());
		Assert.assertNotSame(0, newRoleMap.keySet().size());
		for (String categoryName : newRoleMap.keySet()) {
			if (categoryName.equals("财务管理")) {
				List<UserRoleVO> newVoList = newRoleMap.get(categoryName);
				for (UserRoleVO vo : newVoList) {
					assertEquals(Boolean.TRUE, vo.getChecked());
					vo.setChecked(false);
				}
			}
		}

		// 然后不选财务管理
		voList = new ArrayList<UserRoleVO>();
		for (String categoryName : newRoleMap.keySet()) {
			voList.addAll(newRoleMap.get(categoryName));
		}

		mgr.setRoleOfUser(user.getUserId(), voList);

		// 重新取出看是否不选
		newRoleMap = mgr.getRoleOfUser(user.getUserId());
		Assert.assertNotSame(0, newRoleMap.keySet().size());
		for (String categoryName : newRoleMap.keySet()) {
			if (categoryName.equals("财务管理")) {
				List<UserRoleVO> newVoList = newRoleMap.get(categoryName);
				for (UserRoleVO vo : newVoList) {
					assertEquals(Boolean.FALSE, vo.getChecked());
				}
			}
		}
	}

	/**
	 * 测试集团用户权限
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserRoleForChain() throws Exception {

		// firstly, connect chain and company
		Company chainCompany = new Company();
		chainCompany.setCompanyName("testCompanyName1");
		chainCompany.setBizRegNo("testBizRegNo1");
		chainCompany.setAccountBank("testAccountBank1");
		chainCompany.setAccountName("testAccountName1");
		chainCompany.setAccountNo("testAccountNo1");

		// then, create chain

		// secondly, connect hotel and company
		Company company = new Company();
		company.setCompanyName("testCompanyName");
		company.setBizRegNo("testBizRegNo");
		company.setAccountBank("testAccountBank");
		company.setAccountName("testAccountName");
		company.setAccountNo("testAccountNo");

		// then, create hotel

		B2BUser user = new B2BUser();
		user.setUsername("testUserName");
		user.setPassword("testPassword");
		user.setCompanyId(chainCompany.getCompanyId());

		user = mgr.createUserInfo(user);
		assertEquals(chainCompany.getCompanyId(), user.getCompanyId());

		// 集团用户
		Boolean hasHotel = false;
		Map<String, List<UserRoleVO>> roleMap = mgr.getRoleOfUser(user.getUserId());
		Assert.assertNotSame(0, roleMap.keySet().size());
		for (String categoryName : roleMap.keySet()) {
			if (categoryName.equals("集团权限")) {
				List<UserRoleVO> voList = roleMap.get(categoryName);
				for (UserRoleVO vo : voList) {
					assertNotNull(vo.getRoleId());
					assertNotNull(vo.getRoleCnName());
					assertNotNull(vo.getUserId());
					vo.setChecked(true);
				}
			}
		}
		assertEquals(Boolean.TRUE, hasHotel);

		// 选中"集团权限"
		List<UserRoleVO> voList = new ArrayList<UserRoleVO>();
		for (String categoryName : roleMap.keySet()) {
			voList.addAll(roleMap.get(categoryName));
		}

		mgr.setRoleOfUser(user.getUserId(), voList);

		// 重新取出看是否选中
		Map<String, List<UserRoleVO>> newRoleMap = mgr.getRoleOfUser(user.getUserId());
		Assert.assertNotSame(0, newRoleMap.keySet().size());
		for (String categoryName : newRoleMap.keySet()) {
			if (categoryName.equals("集团权限")) {
				List<UserRoleVO> newVoList = newRoleMap.get(categoryName);
				for (UserRoleVO vo : newVoList) {
					assertEquals(Boolean.TRUE, vo.getChecked());
					vo.setChecked(false);
				}
			}
		}

		// 然后不选财务管理
		voList = new ArrayList<UserRoleVO>();
		for (String categoryName : newRoleMap.keySet()) {
			voList.addAll(newRoleMap.get(categoryName));
		}

		mgr.setRoleOfUser(user.getUserId(), voList);

		// 重新取出看是否不选
		newRoleMap = mgr.getRoleOfUser(user.getUserId());
		Assert.assertNotSame(0, newRoleMap.keySet().size());
		for (String categoryName : newRoleMap.keySet()) {
			if (categoryName.equals("集团权限")) {
				List<UserRoleVO> newVoList = newRoleMap.get(categoryName);
				for (UserRoleVO vo : newVoList) {
					assertEquals(Boolean.FALSE, vo.getChecked());
				}
			}
		}
	}

	/**
	 * 测试商家用户权限
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserRoleForHotel() throws Exception {

		Company hotelCompany = new Company();
		hotelCompany.setCompanyName("testCompanyName");

		B2BUser user = new B2BUser();
		user.setUsername("testUserName");
		user.setPassword("testPassword");
		user.setCompanyId(hotelCompany.getCompanyId());

		user = mgr.createUserInfo(user);
		assertEquals(hotelCompany.getCompanyId(), user.getCompanyId());

		// 酒店用户
		Map<String, List<UserRoleVO>> roleMap = mgr.getRoleOfUser(user.getUserId());
		Assert.assertNotSame(0, roleMap.keySet().size());
		for (String categoryName : roleMap.keySet()) {
			if (categoryName.equals("财务管理")) {
				List<UserRoleVO> voList = roleMap.get(categoryName);
				for (UserRoleVO vo : voList) {
					assertNotNull(vo.getRoleId());
					assertNotNull(vo.getRoleCnName());
					assertNotNull(vo.getUserId());
					vo.setChecked(true);
				}
			}
		}

		// 选中财务管理
		List<UserRoleVO> voList = new ArrayList<UserRoleVO>();
		for (String categoryName : roleMap.keySet()) {
			voList.addAll(roleMap.get(categoryName));
		}

		mgr.setRoleOfUser(user.getUserId(), voList);

		// 重新取出看是否选中
		Map<String, List<UserRoleVO>> newRoleMap = mgr.getRoleOfUser(user.getUserId());
		Assert.assertNotSame(0, newRoleMap.keySet().size());
		for (String categoryName : newRoleMap.keySet()) {
			if (categoryName.equals("财务管理")) {
				List<UserRoleVO> newVoList = newRoleMap.get(categoryName);
				for (UserRoleVO vo : newVoList) {
					assertEquals(Boolean.TRUE, vo.getChecked());
					vo.setChecked(false);
				}
			}
		}

		// 然后不选财务管理
		voList = new ArrayList<UserRoleVO>();
		for (String categoryName : newRoleMap.keySet()) {
			voList.addAll(newRoleMap.get(categoryName));
		}

		mgr.setRoleOfUser(user.getUserId(), voList);

		// 重新取出看是否不选
		newRoleMap = mgr.getRoleOfUser(user.getUserId());
		Assert.assertNotSame(0, newRoleMap.keySet().size());
		for (String categoryName : newRoleMap.keySet()) {
			if (categoryName.equals("财务管理")) {
				List<UserRoleVO> newVoList = newRoleMap.get(categoryName);
				for (UserRoleVO vo : newVoList) {
					assertEquals(Boolean.FALSE, vo.getChecked());
				}
			}
		}
	}

	@Test
	public void testResetPassword() {

		B2BUser user = new B2BUser();
		user.setUsername("testUserName");
		user.setPassword("testPassword");

		user = mgr.createUserInfo(user);

		mgr.resetPassword(user.getUserId(), "testPassword1");

		B2BUser newUser = mgr.getUserByUsername(user.getUsername());

		assertEquals(new ShaPasswordEncoder().encodePassword("testPassword1", null), newUser.getPassword());
	}

	@Test
	public void testChangePassword() {

		B2BUser user = new B2BUser();
		user.setUsername("testUserName");
		user.setPassword("testPassword");

		user = mgr.createUserInfo(user);

		mgr.changePassword(user.getUserId(), "testPassword", "12345");

		B2BUser newUser = mgr.getUserByUsername(user.getUsername());

		assertEquals(new ShaPasswordEncoder().encodePassword("12345", null), newUser.getPassword());

	}

	@Test
	public void testChangeEmail() {

		B2BUser user = new B2BUser();
		user.setUsername("testUserName");
		user.setPassword("testPassword");

		user = mgr.createUserInfo(user);

		mgr.changeEmail(user.getUserId(), "test1@test.com");

	}

	@Test
	public void testDeleteUser() {

		B2BUser user = new B2BUser();
		user.setUsername("testUserName");
		user.setPassword("testPassword");

		user = mgr.createUserInfo(user);
		B2BUser newUser = mgr.getUserByUsername(user.getUsername());
		Assert.assertNotNull(newUser);

		mgr.removeUser(user.getUserId());

		Boolean hasException = false;
		try {
			mgr.getUserByUsername(user.getUsername());
		} catch (UsernameNotFoundException e) {
			hasException = true;
		}

		assertEquals(Boolean.TRUE, hasException);
	}

	// @Test
	// public void testAddAndRemoveUser() throws Exception {
	// user = new B2BUser();
	//
	// // call populate method in super class to populate test data
	// // from a properties file matching this class name
	// user = (B2BUser) populate(user);
	//
	// user.addRole(roleManager.getRole(Constants.USER_ROLE));
	//
	// user = mgr.saveUser(user);
	// assertEquals("john", user.getUsername());
	// assertEquals(1, user.getRoles().size());
	//
	// log.debug("removing user...");
	//
	// mgr.removeUser(user.getUserId());
	//
	// try {
	// user = mgr.getUserByUsername("john");
	// fail("Expected 'Exception' not thrown");
	// } catch (Exception e) {
	// log.debug(e);
	// assertNotNull(e);
	// }
	// }

	@Test
	public void testSearchUser() {

		B2BUser user = new B2BUser();
		user.setUsername("testUserName");
		user.setPassword("testPassword");

		mgr.save(user);

		B2BUserCriteria userCriteria = new B2BUserCriteria();
		userCriteria.setPageNum(1);
		userCriteria.setPageSize(10);
		userCriteria.setUsername("testUserName");
		B2BUserSearchResult result = mgr.search(userCriteria);
		assertEquals(new Integer(1), new Integer(result.getTotalCount()));

		mgr.remove(user.getUserId());
	}

	@Rollback(false)
	@Test
	public void resetPasswordTest() {
		String userName = "ccm_admin";
		B2BUser b2bUser = mgr.getUserByUsername(userName);
		mgr.resetPassword(b2bUser.getUserId(), "123456");
		assertEquals(userName, b2bUser.getUsername());
		b2bUser = mgr.get(b2bUser.getUserId());

	}
}
