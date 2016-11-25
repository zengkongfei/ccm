package com.ccm.api.dao.cfg;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.cfg.SysCfg;

import com.ccm.api.service.cfg.SysCfgManager;

public class SysCfgDaoTest extends BaseDaoTestCase {
	
	@Autowired
	private SysCfgDao sysCfgDao;

	//@Autowired
	//private SysCfgManager sysCfgManager;
	
	@Test
	@Rollback(value=false)
	public void testMasterCardNo() throws Exception {
		//SysCfg s=new SysCfg();
		//s.setCcmSysCfgId("1");
		//s.setPendingMsgCount(24444);
		
		//sysCfgDao.addSysCfg(s);
		
		//sysCfgDao.updateSysCfg(s);
		
		//s=sysCfgDao.getSysCfg("1");
		//System.out.println(s);
		
		//sysCfgDao.deleteSysCfg("1");
		//sysCfgManager.addSysCfg(s);
		//s=sysCfgManager.getSysCfg("111111");
		//System.out.println(s);
		
		//sysCfgManager.addSysCfg(s);
		
		//sysCfgManager.deleteSysCfg("");
		
		System.out.println(sysCfgDao.getAll().get(0));
		
		System.out.println("run over");
	}

}
