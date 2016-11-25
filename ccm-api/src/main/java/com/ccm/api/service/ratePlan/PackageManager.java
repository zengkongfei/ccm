package com.ccm.api.service.ratePlan;

import java.util.List;

import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.ratePlan.Package;
import com.ccm.api.model.ratePlan.PackageI18n;
import com.ccm.api.model.ratePlan.vo.PackageCriteria;
import com.ccm.api.model.ratePlan.vo.PackageResult;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.service.base.GenericManager;

public interface PackageManager extends GenericManager<Package, String> {

	/**
	 * 新增打包服务
	 */
	PackageVO savePackage(PackageVO vo);

	/**
	 * 修改打包服务
	 */
	void updatePackage(PackageVO vo);

	/**
	 * 删除打包服务
	 */
	void deletePackage(PackageVO vo);

	/**
	 * 根据打包服务代码取打包服务信息
	 */
	PackageVO getPackageByCode(String packageCode);

	/**
	 * 根据打包服务代码和语言代码取打包服务信息
	 */
	PackageVO getPackageByCode(String packageCode, String language);

	/**
	 * 根据打包服务ID取打包服务信息
	 */
	PackageVO getPackageById(String packageId);

	/**
	 * 根据条件取打包服务信息
	 */
	PackageResult searchPackage(PackageCriteria criteria);

	/**
	 * 根据房价id查找package
	 */
	List<PackageVO> getPackageByRatePlan(List<String> ratePlanIds, String language);

	/**
	 * 获取打包信息
	 * 
	 * @param packageId
	 * @param language
	 * @return
	 */
	PackageVO getPackageById(String packageId, String language);

	/**
	 * 删除打包多语言列表
	 * 
	 * @param packageId
	 */
	void deletePackageI18nByPackageId(String packageId);

	/**
	 * 获得打包列表
	 * 
	 * @param packageId
	 * @return
	 */
	List<PackageI18n> getPackageI18ns(String packageId);

	PackageI18n getDefaultLanguageI18n(PackageVO vo);

	List<PackageVO> getAllPackages();

	List<PackageVO> getAllPackages(String language);

	/**
	 * 根据房价idList查找关联房价的package
	 */
	List<PackageVO> getRoomTypePackageByRatePlanId(String rateDetailId, String language);

	List<PackageVO> getDynamicPackagesByObj(DynamicPackage obj, String language);
}
