package com.ccm.api.service.fax.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.fax.FaxSendDao;
import com.ccm.api.model.fax.FaxSend;
import com.ccm.api.model.fax.vo.FaxSendCriteria;
import com.ccm.api.model.fax.vo.FaxSendSearchResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.fax.FaxSendManager;

@Service("faxSendManager")
public class FaxSendManagerImpl extends GenericManagerImpl<FaxSend, String> implements FaxSendManager {
	@Autowired
	private FaxSendDao faxSendDao;
	
	@Override
	public FaxSendSearchResult searchFaxSendVO(FaxSendCriteria fsc) {
		FaxSendSearchResult result = new FaxSendSearchResult();
		if(fsc.isExcelFlag()){
			result.setResultList(faxSendDao.getExcelList(fsc));
		}else{
			result.setTotalCount(faxSendDao.getCount(fsc));
			result.setResultList(faxSendDao.getList(fsc));
		}
		return result;
	}

	@Override
	public List<FaxSend> getFaxSendByTime(Date sendTime) {
		return faxSendDao.getFaxSendByTime(sendTime);
	}
	
}
