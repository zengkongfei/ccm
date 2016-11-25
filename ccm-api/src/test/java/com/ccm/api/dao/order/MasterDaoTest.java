package com.ccm.api.dao.order;

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

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.dao.master.MasterDao;
import com.ccm.api.dao.user.RoleDao;
import com.ccm.api.model.order.Master;
import com.ccm.api.model.order.MasterPms;
import com.ccm.api.model.user.B2BUser;
import com.ccm.api.util.RSAEncrypt;

public class MasterDaoTest extends BaseDaoTestCase {
	
	@Autowired
	private MasterDao dao;

	@Test
	@Rollback(value=false)
	public void testMasterCardNo() throws Exception {
//		Master m = new Master();
//		m.setMasterId("testsa");
//		m.setCardNumber("111111");
//		System.out.println(m.getCardNumber());
//		dao.saveMasterOrder(m);
//		System.out.println(m.getCardNumber());
//		m.setCardNumber(RSAEncrypt.decrypt(m.getCardNumber()));
//		System.out.println(m.getCardNumber());
//		dao.get("testsa");
		List<String> list = dao.getMasterPmsListBYCreatedTime(new Date());
		System.out.println(list);
	}

}
