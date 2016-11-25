package com.ccm.api.dao.hotel;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.dao.log.CustomLogDao;
import com.ccm.api.model.hotel.Custom;
import com.ccm.api.model.log.CustomLog;

public class CustomLogDaoTest extends BaseDaoTestCase {
	@Resource
	private CustomLogDao customLogDao;

	@Autowired
	private CustomDao customDao;
	
	@Test
	@Rollback(value=false)
	public void testSaveCustomLog() throws Exception {
		String customId="0a1e01c1334611e48ad476ff40eec093";
		Custom custom = customDao.get(customId);
		CustomLog cl = new CustomLog();
		cl.setCustomId(custom.getCustomId());
		cl.setHotelCode("MHLHA");
		cl.setName(custom.getName());
		cl.setType(custom.getType());
		cl.setCorpIATANo(custom.getCorpIATANo());
		cl.setAccessCode(custom.getAccessCode());
		cl.setBusiness(custom.getBusiness());
		cl.setMobile(custom.getMobile());
		cl.setFax(custom.getFax());
		cl.setAddress(custom.getAddress());
		cl.setEmail(custom.getEmail());
		cl.setOriginalCreditLimit(custom.getOriginalCreditLimit());
		cl.setTotalRoomRev("133.30");
		cl.setIncome(custom.getIncome());
		cl.setMinLimit(custom.getMinLimit());
		customLogDao.saveCustomLog(cl);
	}
}
