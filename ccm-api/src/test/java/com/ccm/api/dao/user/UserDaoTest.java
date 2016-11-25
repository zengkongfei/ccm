package com.ccm.api.dao.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.compass.core.CompassCallbackWithoutResult;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;
import org.compass.core.CompassTemplate;
import org.compass.gps.CompassGps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.Constants;
import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.UserPassword;
import com.ccm.api.model.user.criteria.B2BUserCriteria;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.model.user.vo.B2BUserVO;


public class UserDaoTest extends BaseDaoTestCase {
    @Autowired
    private UserDao dao;
//    @Autowired
//    private RoleDao rdao;
//    @Autowired
//    private CompassTemplate compassTemplate;
//    @Autowired
//    private CompassGps compassGps;

    @Test
    public void dumpTest() {
    	
    }
    /**
     * 测试查询用户
     */
    @Test
    public void testsearchUser(){
    	B2BUserCriteria userCriteria=new B2BUserCriteria();
    	userCriteria.setUserId("002061b772174373808bd68e18139bab");
    	
    	B2BUserSearchResult b2bUserSearchResult= dao.searchUser(userCriteria);
    	List<B2BUserVO> uList=b2bUserSearchResult.getResultList();
		for (B2BUserVO b2bUserVO : uList) {
			System.out.println(b2bUserVO.getUserId()+"========"+b2bUserVO.getIslocak());	
		}
    	System.out.println();
    	
    }
//    
//    /**
//	 * 用户密码
//	 * @param userId
//	 * @return
//	 */
//    @Test
//    public void findUserPassword(){
//    	UserPassword userPassword = dao.findUserPassword("01bd2b8d64b24e27942f6c69b192cc6a");
//    	System.out.println(userPassword);
//    }
//	
//	/**
//	 * 用户信息，包括用户密码情况
//	 * @param userid
//	 * @return
//	 */
//    @Test
//    public void getUserInfo(){
//    	UserPassword userPassword = dao.getUserInfo("01bd2b8d64b24e27942f6c69b192cc6a");
//    	System.out.println(userPassword.getIslocak());
//    	System.out.println(userPassword);
//    }
//	
//	/**
//	 * 添加用户密码信息
//	 * @param userPassword
//	 */
//    @Test
//    @Rollback(false)
//    public void addUserPassword(){
//    	UserPassword userPassword = new UserPassword();
//    	userPassword.setUserId("01bd2b8d64b24e27942f6c69b192cc6a");
//    	userPassword.setPassword1("1");
//    	userPassword.setPassword2("2");
//    	userPassword.setPassword3("3");
//    	userPassword.setPassword4("4");
//    	userPassword.setPasswordFailNumber(1);
//    	userPassword.setPasswordlastModifyTime(new Date());
//    	userPassword.setPasswordFaillastModifyTime(new Date());
//    	
//    	dao.addUserPassword(userPassword);
//    }
//	
//	/**
//	 * 用户密码信息，用户锁定情况
//	 * @param userid
//	 * @return
//	 */
//    @Test
//    public void getUserPasswordInfo(){
//    	UserPassword userPassword = dao.getUserPasswordInfo("01bd2b8d64b24e27942f6c69b192cc6a");
//    	System.out.println(userPassword);
//	}
//	
//	/**
//	 * 修改用户历史密码数据
//	 * @param UserPassword
//	 */
//    @Test
//    @Rollback(false)
//	public void updateUserPassword(){
//		UserPassword userPassword = new UserPassword();
//    	userPassword.setUserId("01bd2b8d64b24e27942f6c69b192cc6a");
//    	userPassword.setPassword1("11");
//    	userPassword.setPassword2("21");
//    	userPassword.setPassword3("31");
//    	userPassword.setPassword4("41");
//    	userPassword.setPasswordFailNumber(1);
//    	userPassword.setPasswordlastModifyTime(new Date());
//    	userPassword.setPasswordFaillastModifyTime(new Date());
//    	
//    	dao.updateUserPassword(userPassword);
//	}
//	
//	/**
//	 * 修改用户锁定，false:解除锁定，true:锁定
//	 * @param bool
//	 */
//    @Test
//    @Rollback(false)
//	public void updateLocak(){
//		dao.updateLocak(1,"01bd2b8d64b24e27942f6c69b192cc6a");
//	}
//	
//	/**
//	 * 修改输入密码错误次数
//	 * @param number
//	 * @param userId
//	 */
//    @Test
//    @Rollback(false)
//	public void updatePassFailNumber(){
//		dao.updatePassFailNumber(5,"01bd2b8d64b24e27942f6c69b192cc6a");
//	}
//    
////    @Test
//    @ExpectedException(DataAccessException.class)
//    public void testGetUserInvalid() throws Exception {
//        dao.get("1000");
//    }
//
////    @Test
//    public void testGetUser() throws Exception {
//    	B2BUser user = dao.get("-1");
//
//        assertNotNull(user);
//        assertEquals(1, user.getRoles().size());
//        assertTrue(user.isEnabled());
//    }
//
////    @Test
//    public void testGetUserPassword() throws Exception {
//    	B2BUser user = dao.get("-1");
//        String password = dao.getUserPassword(user.getUsername());
//        assertNotNull(password);
//    }
//
////    @Test
//    @ExpectedException(DataIntegrityViolationException.class)
//    public void testUpdateUser() throws Exception {
//    	B2BUser user = dao.get("-1");
//
//       
//       
//       
//        // verify that violation occurs when adding new user with same username
//        user.setUserId(null);
//
//        //endTransaction();
//
//    }
//
////    @Test
//    public void testAddUserRole() throws Exception {
////    	B2BUser user = dao.get("-1");
////        assertEquals(1, user.getRoles().size());
////
////        Role role = rdao.getRoleByName(Constants.ADMIN_ROLE);
////        user.addRole(role);
////     
////        assertEquals(2, user.getRoles().size());
////
////        //add the same role twice - should result in no additional role
////        user.addRole(role);
////      
////        assertEquals("more than 2 roles", 2, user.getRoles().size());
////
////        user.getRoles().remove(role);
////     
////        assertEquals(1, user.getRoles().size());
//    }
//
////    @Test
//    @ExpectedException(DataAccessException.class)
//    public void testAddAndRemoveUser() throws Exception {
////    	B2BUser user = new B2BUser("testuser");
////        user.setPassword("testpass");
////      
//////        user.setEmail("testuser@appfuse.org");
////       
////        Role role = rdao.getRoleByName(Constants.USER_ROLE);
////        assertNotNull(role.getRoleId());
////        user.addRole(role);
////
////      
////        assertNotNull(user.getUserId());
////        assertEquals("testpass", user.getPassword());
////
////        dao.remove(user.getUserId());
////
////        // should throw DataAccessException
////        dao.get(user.getUserId());
//    }
//    
//    public void testUserExists() throws Exception {
//        boolean b = dao.exists("-1");
//        assertTrue(b);
//    }
//    
//    public void testUserNotExists() throws Exception {
//        boolean b = dao.exists("-1");
//        assertFalse(b);
//    }
//
////    @Test
//    public void testUserSearch() throws Exception {
//        // reindex all the data
//        compassGps.index();
//
//        B2BUser user = compassTemplate.get(B2BUser.class, -2);
//        assertNotNull(user);
//    
//        compassTemplate.execute(new CompassCallbackWithoutResult() {
//            @Override
//            protected void doInCompassWithoutResult(CompassSession compassSession) throws CompassException {
//                CompassHits hits = compassSession.find("Matt");
//                assertEquals(1, hits.length());
//                assertEquals("Matt", hits.resource(0).getValue("firstName"));
//            }
//        });
//
//        // test mirroring
//        user = dao.get("-2");
//     
//        // now verify it is reflected in the index
//        user = compassTemplate.get(B2BUser.class, -2);
//        assertNotNull(user);
//     
//        compassTemplate.execute(new CompassCallbackWithoutResult() {
//            @Override
//            protected void doInCompassWithoutResult(CompassSession compassSession) throws CompassException {
//                CompassHits hits = compassSession.find("MattX");
//                assertEquals(1, hits.length());
//               }
//        });
//    }
}
