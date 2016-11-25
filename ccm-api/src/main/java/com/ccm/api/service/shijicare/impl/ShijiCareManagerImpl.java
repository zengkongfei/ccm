package com.ccm.api.service.shijicare.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.shijiCare.ShijiCareDao;
import com.ccm.api.model.shijicare.ShijiCare;
import com.ccm.api.model.shijicare.vo.ShijicareCriteria;
import com.ccm.api.model.shijicare.vo.ShijicareSearchResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.shijicare.ShijiCareManager;

@Service("shijiCareManager")
public class ShijiCareManagerImpl extends GenericManagerImpl<ShijiCare, String> implements ShijiCareManager {

	@Autowired
	private ShijiCareDao shijiCareDao;
	
	@Override
	public ShijicareSearchResult searchShijiCareVO(ShijicareCriteria sc) {
		ShijicareSearchResult result = new ShijicareSearchResult();
		if(sc.isExcelFlag()){
			result.setResultList(shijiCareDao.getExcelList(sc));
		}else{
			result.setTotalCount(shijiCareDao.getCount(sc));
			result.setResultList(shijiCareDao.getList(sc));
		}
		return result;
	}

	

}
