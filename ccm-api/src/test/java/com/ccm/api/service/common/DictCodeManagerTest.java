/**
 * 
 */
package com.ccm.api.service.common;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccm.api.model.common.DictCode;
import com.ccm.api.service.base.BaseManagerTestCase;

/**
 * @author Jenny Liao
 *
 */
public class DictCodeManagerTest extends BaseManagerTestCase {
	

//    private Log log = LogFactory.getLog(DictCodeManagerTest.class);
    
    @Autowired
    private DictCodeManager mgr;
    
    @Test
    public void testSearchByDictName() throws Exception {
    	List<DictCode> codes = mgr.searchByDictName("companyType");
    	assertEquals(2, codes.size());
    }

}
