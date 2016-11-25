package com.ccm.api.service.user.impl;

import org.junit.Before;
import org.junit.Test;

import com.ccm.api.dao.user.LookupDao;
import com.ccm.api.service.base.impl.BaseManagerMockTestCase;


public class LookupManagerImplTest extends BaseManagerMockTestCase {
    private LookupManagerImpl mgr = new LookupManagerImpl();
    private LookupDao lookupDao;

    @Test
    public void dumpTest() {
    	
    }
    
    @Before
    public void setUp() throws Exception {
        lookupDao = context.mock(LookupDao.class);
        mgr.dao = lookupDao;
    }

//    @Test
    public void testGetAllRoles() {
        log.debug("entered 'testGetAllRoles' method");

//        // set expected behavior on dao
//        Role role = new Role(Constants.ADMIN_ROLE);
//        final List<Role> testData = new ArrayList<Role>();
//        testData.add(role);
//        context.checking(new Expectations() {{
//            one(lookupDao).getRoles();
//            will(returnValue(testData));
//        }});
//
//        List<LabelValue> roles = mgr.getAllRoles();
//        assertTrue(roles.size() > 0);
    }
}
