package com.ccm.api.dao.ratePlan;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.ratePlan.Package;
import com.ccm.api.model.ratePlan.PackageI18n;
import com.ccm.api.model.ratePlan.vo.PackageCriteria;
import com.ccm.api.model.ratePlan.vo.PackageVO;

public interface PackageDao extends GenericDao<Package, String> {

	Package addPackage(Package p);

	PackageI18n addPackageI18n(PackageI18n packageI18n);

	void updatePackage(Package p);

	void updatePackageI18n(PackageI18n packageI18n);

	void deletePackage(Package p);

	void deletePackageI18n(PackageI18n packageI18n);

	PackageVO getPackageByCode(String packageCode);

	PackageVO getPackageById(String packageId);

	List<PackageVO> searchPackage(PackageCriteria criteria);

	Integer searchPackageCount(PackageCriteria criteria);

	/**
	 * 根据房价idList查找关联房价的package
	 */
	List<PackageVO> getRatePlanPackageByRatePlanId(List<String> ratePlanIds);

	/**
	 * 根据房价idList和语言查找关联房价的package
	 */
	List<PackageVO> getRatePlanPackageByRatePlanId(List<String> ratePlanIds, String language);

	/**
	 * 根据房价rateDetailId查找关联房型的package
	 */
	List<PackageVO> getRoomTypePackageByRatePlanId(String rateDetailId);

	/**
	 * 根据房价iD和语言查找打包服务
	 * 
	 * @param ratePlanId
	 * @param language
	 * @return
	 */
	List<PackageVO> getRoomTypePackageByRatePlanId(String rateDetailId, String language);

	PackageVO getPackageById(String packageId, String language);

	void deletePackageI18nByPackageId(String packageId);

	List<PackageI18n> getPackageI18ns(String packageId);

	PackageVO getPackageByCode(String packageCode, String language);

	List<PackageVO> getAllPackages();

	List<PackageVO> getAllPackages(String language);


	List<PackageVO> getDynamicPackagesByObj(DynamicPackage obj);

	List<PackageVO> getDynamicPackagesByObjLang(DynamicPackage obj, String language);


}
