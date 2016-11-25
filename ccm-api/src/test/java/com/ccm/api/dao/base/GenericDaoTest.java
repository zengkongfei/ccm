package com.ccm.api.dao.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;

public class GenericDaoTest extends BaseDaoTestCase {
    Log log = LogFactory.getLog(GenericDaoTest.class);
    public GenericDao<User, Long> genericDao;
    @Autowired
    public SqlMapClient sqlMapClient;

    @Before
    public void setUp() {
        genericDao = new GenericDaoiBatis<User, Long>(User.class, sqlMapClient);
    }
    
    @Test
    public void dumpTest() {
    	
    }

//    @Test
    public void getUser() {
        User user = genericDao.get(-1L);
        assertNotNull(user);
        assertEquals("user", user.getUsername());
    }
}