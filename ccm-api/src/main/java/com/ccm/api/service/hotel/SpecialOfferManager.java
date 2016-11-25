package com.ccm.api.service.hotel;

import java.util.List;
import com.ccm.api.model.hotel.SpecialOffer;
import com.ccm.api.model.hotel.SpecialOfferI18n;
import com.ccm.api.model.hotel.vo.SpecialOfferCreteria;
import com.ccm.api.model.hotel.vo.SpecialOfferResult;
import com.ccm.api.model.hotel.vo.SpecialOfferVO;
import com.ccm.api.service.base.GenericManager;

public interface SpecialOfferManager extends GenericManager<SpecialOffer, String> {

	SpecialOfferVO saveSpecialOffer(SpecialOfferVO vo);

	void updateSpecialOffer(SpecialOfferVO vo);

	void deleteSpecialOffer(SpecialOfferVO vo);
	
	SpecialOfferVO getSpecialOfferById(String specialOfferId);

	SpecialOfferVO getSpecialOfferById(String specialOfferId,String language);

	List<SpecialOfferI18n> getSpecialOfferI18ns(String specialOfferId);

	SpecialOfferResult searchSpecialOffer(SpecialOfferCreteria creteria);

	SpecialOfferI18n getDefaultLanguageI18n(SpecialOfferVO vo);

	List<SpecialOfferVO> getAllSpecialOfferByHotelId(String hotelId);

	List<SpecialOfferVO> getAllSpecialOfferByHotelId(String hotelId,String language);

}
