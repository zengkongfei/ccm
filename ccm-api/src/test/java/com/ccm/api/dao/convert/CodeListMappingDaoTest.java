package com.ccm.api.dao.convert;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.model.convert.CodeListMapping;

public class CodeListMappingDaoTest extends BaseDaoTestCase {

	@Resource
	private CodeListMappingDao codeListMappingDao;

	@Test
	@Rollback(false)
	public void testCopyCodeMap() throws Exception {
		List<CodeListMapping> codeMapList = codeListMappingDao.getCodeMapNoRoomRateByHotelId("ca73fbf20c9a11e48ca076ff40eec09");
		if (!codeMapList.isEmpty()) {
			codeListMappingDao.saveCodeListMappingBatch(codeMapList, "8815ceb13f904cdc99ced0a86d5dadd3", "testImport");
		}
	}

}
