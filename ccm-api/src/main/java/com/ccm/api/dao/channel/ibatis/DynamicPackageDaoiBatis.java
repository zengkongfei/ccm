/**
 * 
 */
package com.ccm.api.dao.channel.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.channel.DynamicPackageDao;
import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.channel.vo.DynamicPackageCreteria;

/**
 * @author Jenny
 * 
 */
@Repository("dynamicPackageDao")
public class DynamicPackageDaoiBatis extends GenericDaoiBatis<DynamicPackage, String> implements DynamicPackageDao {

	public DynamicPackageDaoiBatis() {
		super(DynamicPackage.class);
	}

	@SuppressWarnings("unchecked")
	public List<DynamicPackage> searchDynamicPackage(DynamicPackageCreteria creteria) {
		return getSqlMapClientTemplate().queryForList("searchDynamicPackage", creteria, creteria.getStart(), creteria.getPageSize());
	}

	public Integer countDynamicPackage(DynamicPackageCreteria creteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("countDynamicPackage", creteria);
	}

	public DynamicPackage getDynamicPackagesById(String hotelId, String channelId, String packageId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("hotelId", hotelId);
		param.put("channelId", channelId);
		param.put("packageId", packageId);
		return (DynamicPackage) getSqlMapClientTemplate().queryForObject("getDynamicPackagesByCPId", param);
	}

}
