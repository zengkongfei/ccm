package com.ccm.api.service.hotel.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.hotel.SpecialOfferDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.SpecialOffer;
import com.ccm.api.model.hotel.SpecialOfferI18n;
import com.ccm.api.model.hotel.vo.SpecialOfferCreteria;
import com.ccm.api.model.hotel.vo.SpecialOfferResult;
import com.ccm.api.model.hotel.vo.SpecialOfferVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.HotelMCManager;
import com.ccm.api.service.hotel.SpecialOfferManager;

@Service("specialOfferManager")
public class SpecialOfferManagerImpl extends GenericManagerImpl<SpecialOffer, String> implements SpecialOfferManager{

	@Autowired
	private SpecialOfferDao specialOfferDao;
	
	@Autowired 
	private HotelMCManager hotelMCManager;
	
	@Override
	public SpecialOfferVO saveSpecialOffer(SpecialOfferVO vo) {
		SpecialOffer vo1 = new SpecialOffer();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = specialOfferDao.addSpecialOffer(vo1);
		vo.setSpecialOfferId(vo1.getSpecialOfferId());
		
		if(vo.getSpecialOfferI18nList() == null 
				|| vo.getSpecialOfferI18nList().size() == 0){
			List<SpecialOfferI18n> i18nList = new ArrayList<SpecialOfferI18n>();
			SpecialOfferI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setSpecialOfferI18nList(i18nList);
		}
		
		//循环添加多语言数据
		for (SpecialOfferI18n specialOfferI18n : vo.getSpecialOfferI18nList()) {
			SpecialOfferI18n i18n = new SpecialOfferI18n();
			i18n.setSpecialOfferId(vo.getSpecialOfferId());
			i18n.setSummary(specialOfferI18n.getSummary());
			i18n.setDetail(specialOfferI18n.getDetail());
			i18n.setLanguage(specialOfferI18n.getLanguage());
			specialOfferDao.addSpecialOfferI18n(i18n);
		}
		return vo;
	}

	@Override
	public void updateSpecialOffer(SpecialOfferVO vo) {
		SpecialOffer vo1 = new SpecialOffer();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		specialOfferDao.updateSpecialOffer(vo1);
		
		if(vo.getSpecialOfferI18nList()!=null){
			specialOfferDao.deleteSpecialOfferI18nBySpecialOfferId(vo.getSpecialOfferId());
			
			//循环添加多语言数据
			for (SpecialOfferI18n specialOfferI18n : vo.getSpecialOfferI18nList()) {
				SpecialOfferI18n i18n = new SpecialOfferI18n();
				i18n.setSpecialOfferId(vo.getSpecialOfferId());
				i18n.setSummary(specialOfferI18n.getSummary());
				i18n.setDetail(specialOfferI18n.getDetail());
				i18n.setLanguage(specialOfferI18n.getLanguage());
				specialOfferDao.addSpecialOfferI18n(i18n);
			}
		}else{
			SpecialOfferI18n specialOfferI18n = new SpecialOfferI18n();
			specialOfferI18n.setSpecialOfferMId(vo.getSpecialOfferMId());
			specialOfferI18n.setSpecialOfferId(vo.getSpecialOfferId());
			specialOfferI18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			specialOfferI18n.setSummary(vo.getSummary());
			specialOfferI18n.setDetail(vo.getDetail());
			specialOfferDao.updateSpecialOfferI18n(specialOfferI18n);
			
		}
	}

	@Override
	public void deleteSpecialOffer(SpecialOfferVO vo) {
		SpecialOffer vo1 = new SpecialOffer();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		specialOfferDao.deleteSpecialOffer(vo1);
		specialOfferDao.deleteSpecialOfferI18nBySpecialOfferId(vo.getSpecialOfferId());
		
	}

	@Override
	public SpecialOfferVO getSpecialOfferById(String specialOfferId) {
		return specialOfferDao.getSpecialOfferById(specialOfferId);
	}

	@Override
	public SpecialOfferVO getSpecialOfferById(String specialOfferId,String language) {
		return specialOfferDao.getSpecialOfferById(specialOfferId, language);
	}

	@Override
	public List<SpecialOfferI18n> getSpecialOfferI18ns(String specialOfferId) {
		return specialOfferDao.getSpecialOfferI18ns(specialOfferId);
	}

	@Override
	public SpecialOfferResult searchSpecialOffer(SpecialOfferCreteria creteria) {
		SpecialOfferResult result = new SpecialOfferResult();
		List<SpecialOfferVO> resultList = specialOfferDao.searchSpecialOffer(creteria);
		//设置酒店Code
		for (SpecialOfferVO vo : resultList) {
			vo.setHotelCode(hotelMCManager.getHotelByIdMC(vo.getHotelId()).getHotelCode());
		}
		
		Integer totalCount = specialOfferDao.searchSpecialOfferCount(creteria);
		result.setResultList(resultList);
		result.setTotalCount(totalCount);
		return result;
	}

	@Override
	public SpecialOfferI18n getDefaultLanguageI18n(SpecialOfferVO vo) {
		SpecialOfferI18n i18n = new SpecialOfferI18n();
		i18n.setSpecialOfferId(vo.getSpecialOfferId());
		if(vo.getLanguage()==null){
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			i18n.setLanguage(vo.getLanguage());
		}
		i18n.setSummary(vo.getSummary());
		i18n.setDetail(vo.getDetail());
		return i18n;
	}

	@Override
	public List<SpecialOfferVO> getAllSpecialOfferByHotelId(String hotelId) {
		return specialOfferDao.getAllSpecialOfferByHotelId(hotelId);
	}

	@Override
	public List<SpecialOfferVO> getAllSpecialOfferByHotelId(String hotelId,
			String language) {
		return specialOfferDao.getAllSpecialOfferByHotelId(hotelId, language);
	}

}
