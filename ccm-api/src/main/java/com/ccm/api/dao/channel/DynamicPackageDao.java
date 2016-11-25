/**
 * 
 */
package com.ccm.api.dao.channel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.channel.vo.DynamicPackageCreteria;

/**
 * @author Jenny
 * 
 */
public interface DynamicPackageDao extends GenericDao<DynamicPackage, String> {

	List<DynamicPackage> searchDynamicPackage(DynamicPackageCreteria creteria);

	Integer countDynamicPackage(DynamicPackageCreteria creteria);

	DynamicPackage getDynamicPackagesById(String hotelId, String channelId, String packageId);

}
