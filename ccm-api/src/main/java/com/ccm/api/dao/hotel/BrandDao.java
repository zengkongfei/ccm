package com.ccm.api.dao.hotel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.Brand;
import com.ccm.api.model.hotel.BrandI18n;
import com.ccm.api.model.hotel.vo.BrandCreteria;
import com.ccm.api.model.hotel.vo.BrandVO;

public interface BrandDao extends GenericDao<Brand, String> {

	Brand addBrand(Brand brand);
	
	BrandI18n addBrandI18n(BrandI18n brandI18n);
	
	void updateBrand(Brand brand);
	
	void updateBrandI18n(BrandI18n brandI18n);
	
	void deleteBrand(Brand brand);
	
	void deleteBrandI18n(BrandI18n brandI18n);
	
	BrandVO getBrandByCode(String brandCode);
	
	BrandVO getBrandByCode(String brandCode, String language);
	
	BrandVO getBrandById(String brandId);
	
	BrandVO getBrandById(String brandId, String language);
	
	List<BrandVO> searchBrand(BrandCreteria creteria);
	
	Integer searchBrandCount(BrandCreteria creteria);
	
	List<BrandVO> getAllBrandByChainId(String chainId);
	
	List<BrandVO> getAllBrandByChainId(String chainId, String language);

	void deleteBrandI18nByBrandId(String brandId);

	List<BrandI18n> getBrandI18ns(String brandId);
	
}
