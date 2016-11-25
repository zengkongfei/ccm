/**
 * 
 */
package com.ccm.api.service.channel;

import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.channel.vo.DynamicPackageCreteria;
import com.ccm.api.model.channel.vo.DynamicPackageResult;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface DynamicPackageManager extends GenericManager<DynamicPackage, String> {

	DynamicPackageResult searchList(DynamicPackageCreteria creteria);

	DynamicPackage getDynamicPackagesById(String hotelId, String channelId, String packageId);
}
