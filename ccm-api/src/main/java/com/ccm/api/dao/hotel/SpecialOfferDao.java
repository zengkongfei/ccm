package com.ccm.api.dao.hotel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.SpecialOffer;
import com.ccm.api.model.hotel.SpecialOfferI18n;
import com.ccm.api.model.hotel.vo.SpecialOfferCreteria;
import com.ccm.api.model.hotel.vo.SpecialOfferVO;

public interface SpecialOfferDao extends GenericDao<SpecialOffer, String>{

	/**
	 * 新增优惠信息
	 * @param specialOffer
	 * @return
	 */
	SpecialOffer addSpecialOffer(SpecialOffer specialOffer);
	
	/**
	 * 新增多语言记录
	 * @param specialOfferI18n
	 * @return
	 */
	SpecialOfferI18n addSpecialOfferI18n(SpecialOfferI18n specialOfferI18n);
	
	/**
	 * 修改优惠信息
	 * @param specialOffer
	 */
	void updateSpecialOffer(SpecialOffer specialOffer);
	
	/**
	 * 修改多语言记录
	 * @param specialOfferI18n
	 */
	void updateSpecialOfferI18n(SpecialOfferI18n specialOfferI18n);
	
	/**
	 * 软删除优惠信息
	 * @param specialOffer
	 */
	void deleteSpecialOffer(SpecialOffer specialOffer);
	
	/**
	 * 软删除优惠多语言信息
	 * @param specialOfferI18n
	 */
	void deleteSpecialOfferI18n(SpecialOfferI18n specialOfferI18n);
	
	/**
	 * 根据specialOfferId删除所有的多语言记录
	 * @param specialOfferId
	 */
	void deleteSpecialOfferI18nBySpecialOfferId(String specialOfferId);
	
	/**
	 * 获取优惠信息
	 * @param specialOfferId
	 * @return
	 */
	SpecialOffer getSpecialOffer(String specialOfferId);
	
	/**
	 * 获取vo对象
	 * @param specialOfferId
	 * @return
	 */
	SpecialOfferVO getSpecialOfferById(String specialOfferId);
	
	/**
	 * 获取vo对象
	 * @param specialOfferId
	 * @return
	 */
	SpecialOfferVO getSpecialOfferById(String specialOfferId,String language);
	
	
	/**
	 * 优惠信息查询对象
	 * @param creteria
	 * @return
	 */
	List<SpecialOfferVO> searchSpecialOffer(SpecialOfferCreteria creteria);
	
	/**
	 * 获取总条数
	 * @param creteria
	 * @return
	 */
	Integer searchSpecialOfferCount(SpecialOfferCreteria creteria);
	
	/**
	 * 根据酒店ID获取所有的优惠信息（默认语言）
	 * @param hotelId
	 * @return
	 */
	List<SpecialOfferVO> getAllSpecialOfferByHotelId(String hotelId);
	
	
	/**
	 * 根据酒店ID获取所有的优惠信息（指定语言）
	 * @param hotelId
	 * @return
	 */
	List<SpecialOfferVO> getAllSpecialOfferByHotelId(String hotelId,String language);
	
	/**
	 * 根据specialOfferId获取对应所有的多语言记录
	 * @param specialOfferId
	 * @return
	 */
	List<SpecialOfferI18n> getSpecialOfferI18ns(String specialOfferId);
}
