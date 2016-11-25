/**
 * 
 */
package com.ccm.api.service.channel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.channel.DynamicPackageDao;
import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.channel.vo.DynamicPackageCreteria;
import com.ccm.api.model.channel.vo.DynamicPackageResult;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.channel.DynamicPackageManager;

/**
 * @author Jenny
 * 
 */
@Service("dynamicPackageManager")
public class DynamicPackageManagerImpl extends GenericManagerImpl<DynamicPackage, String> implements DynamicPackageManager {

	private DynamicPackageDao dynamicPackageDao;

	@Autowired
	public DynamicPackageManagerImpl(DynamicPackageDao dynamicPackageDao) {
		super(dynamicPackageDao);
		this.dynamicPackageDao = dynamicPackageDao;
	}

	public DynamicPackageResult searchList(DynamicPackageCreteria creteria) {
		DynamicPackageResult result = new DynamicPackageResult();
		int count = dynamicPackageDao.countDynamicPackage(creteria);
		result.setTotalCount(count);

		List<DynamicPackage> list = dynamicPackageDao.searchDynamicPackage(creteria);
		result.setResultList(list);

		return result;
	}

	public DynamicPackage getDynamicPackagesById(String hotelId, String channelId, String packageId) {
		return dynamicPackageDao.getDynamicPackagesById(hotelId, channelId, packageId);
	}

}
