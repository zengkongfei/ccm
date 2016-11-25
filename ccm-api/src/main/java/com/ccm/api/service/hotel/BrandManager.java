package com.ccm.api.service.hotel;

import java.util.List;

import com.ccm.api.model.hotel.Brand;
import com.ccm.api.model.hotel.BrandI18n;
import com.ccm.api.model.hotel.vo.BrandCreteria;
import com.ccm.api.model.hotel.vo.BrandResult;
import com.ccm.api.model.hotel.vo.BrandVO;
import com.ccm.api.service.base.GenericManager;

public interface BrandManager extends GenericManager<Brand, String> {

	/**
	 * 新增品牌
	 */
	void saveBrand(BrandVO vo);
	
	/**
	 * 修改品牌
	 */
	void updateBrand(BrandVO vo);
	
	/**
	 * 删除品牌
	 */
	void deleteBrand(BrandVO vo);
	
	/**
	 * 根据品牌代码取品牌信息
	 */
	BrandVO getBrandByCode(String brandCode);
	
	/**
	 * 根据品牌代码和语言种类来获取品牌信息
	 * @param brandCode
	 * @param language
	 * @return
	 */
	BrandVO getBrandByCode(String brandCode,String language);
	
	/**
	 * 根据品牌ID取品牌信息
	 */
	BrandVO getBrandById(String brandId);
	
	/**
	 * 根据条件取品牌信息
	 */
	BrandResult searchBrand(BrandCreteria creteria);
	
	/**
	 * 查询所有品牌根据集团ID
	 */
	List<BrandVO> getAllBrandByChainId(String chainId);
	
	/**
	 * 查询所有品牌根据集团ID
	 */
	List<BrandVO> getAllBrandByChainId(String chainId, String language);
	
	/**
	 * 获取一个品牌(根据语言和ID)
	 * @param brandId
	 * @param language
	 * @return
	 */
	BrandVO getBrandById(String brandId, String language);

	/**
	 * 删除一个品牌下的所有多语言记录
	 * @param brandId
	 */
	void deleteBrandI18nByBrandId(String brandId);

	/**
	 * 得到品牌多语言列表
	 * @param brandId
	 * @return
	 */
	List<BrandI18n> getBrandI18ns(String brandId);

	/**
	 * 获取默认语种下的多语言对象
	 * @param vo
	 * @return
	 */
	BrandI18n getDefaultLanguageI18n(BrandVO vo);

	
}
