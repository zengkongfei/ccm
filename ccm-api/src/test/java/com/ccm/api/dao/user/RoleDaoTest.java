package com.ccm.api.dao.user;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.dao.channel.ChannelDao;
import com.ccm.api.model.channel.Channel;

public class RoleDaoTest extends BaseDaoTestCase {
	@Autowired
	private RoleDao dao;
	@Autowired
	private ChannelDao cdao;

	@Test
	public void dumpTest() {

	}

	@Test
	public void testGetRoleInvalid() throws Exception {
		Channel c = new Channel();
//		c.setChannelCode("aaa");
		String channelId = cdao.getEnabledChannel(c);
		System.out.println(channelId);
	}
	// // @Test
	// public void testGetRoleInvalid() throws Exception {
	// Role role = dao.getRoleByName("badrolename");
	// assertNull(role);
	// }
	//
	// // @Test
	// public void testGetRole() throws Exception {
	// Role role = dao.getRoleByName(Constants.USER_ROLE);
	// assertNotNull(role);
	// }
	//
	// // @Test
	// public void testUpdateRole() throws Exception {
	// Role role = dao.getRoleByName("ROLE_USER");
	// log.debug(role);
	// role.setDescription("test descr");
	//
	// dao.save(role);
	//
	// role = dao.getRoleByName("ROLE_USER");
	// assertEquals(role.getDescription(), "test descr");
	// }

	// // @Test
	// public void testAddAndRemoveRole() throws Exception {
	// Role role = new Role("testrole");
	// role.setDescription("new role descr");
	// dao.save(role);
	// //setComplete(); // change behavior from rollback to commit
	// //endTransaction();
	//
	// //startNewTransaction();
	// role = dao.getRoleByName("testrole");
	// assertNotNull(role.getDescription());
	//
	// dao.removeRole("testrole");
	// //setComplete();
	// //endTransaction(); // deletes role from database
	//
	// role = dao.getRoleByName("testrole");
	// assertNull(role);
	// }
}
