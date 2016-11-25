package com.ccm.api.service.hotel.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.hotel.HotelPackageDao;
import com.ccm.api.model.channel.DynamicPackage;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.HotelPackage;
import com.ccm.api.model.hotel.HotelPackageI18n;
import com.ccm.api.model.hotel.vo.HotelPackageCreteria;
import com.ccm.api.model.hotel.vo.HotelPackageResult;
import com.ccm.api.model.hotel.vo.HotelPackageVO;
import com.ccm.api.model.ratePlan.vo.PackageVO;
import com.ccm.api.model.roomType.RoomType;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.HotelPackageManager;

@Service("hotelPackageManager")
public class HotelPackageManagerImpl extends GenericManagerImpl<HotelPackage, String> implements HotelPackageManager{

	@Autowired
	private HotelPackageDao hotelPackageDao;
	
	@Override
	public HotelPackageVO saveHotelPackage(HotelPackageVO vo) {
		HotelPackage vo1 = new HotelPackage();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hotelPackageDao.addHotelPackage(vo1);
		vo.setHotelPackageId(vo1.getHotelPackageId());
		
		
		if(vo.getHotelPackageI18nList() == null 
				|| vo.getHotelPackageI18nList().size() == 0){
			List<HotelPackageI18n> i18nList = new ArrayList<HotelPackageI18n>();
			HotelPackageI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setHotelPackageI18nList(i18nList);
		}
		
		//循环添加多语言数据
		for (HotelPackageI18n hotelPackageI18n : vo.getHotelPackageI18nList()) {
			HotelPackageI18n i18n = new HotelPackageI18n();
			i18n.setHotelPackageId(vo.getHotelPackageId());
			i18n.setLanguage(hotelPackageI18n.getLanguage());
			i18n.setPackageName(hotelPackageI18n.getPackageName());
			i18n.setDescription(hotelPackageI18n.getDescription());
			hotelPackageDao.addHotelPackageI18n(i18n);
		}
		return vo;
	}
	
	public void updateHotelPackage(HotelPackageVO vo){
		HotelPackage vo1 = new HotelPackage();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hotelPackageDao.updaetHotelPackage(vo1);
		
		if(vo.getHotelPackageI18nList()!=null){
			hotelPackageDao.deleteHotelPackageI18nById(vo.getHotelPackageId());
			//循环添加多语言数据
			for (HotelPackageI18n hotelPackageI18n : vo.getHotelPackageI18nList()) {
				HotelPackageI18n i18n = new HotelPackageI18n();
				i18n.setHotelPackageId(vo.getHotelPackageId());
				i18n.setLanguage(hotelPackageI18n.getLanguage());
				i18n.setPackageName(hotelPackageI18n.getPackageName());
				i18n.setDescription(hotelPackageI18n.getDescription());
				hotelPackageDao.addHotelPackageI18n(i18n);
			}
		}else{
			HotelPackageI18n hotelPackageI18n = new HotelPackageI18n();
			hotelPackageI18n.setHotelPackageMId(vo.getHotelPackageMId());
			hotelPackageI18n.setHotelPackageId(vo.getHotelPackageId());
			hotelPackageI18n.setPackageName(vo.getPackageName());
			hotelPackageI18n.setDescription(vo.getDescription());
			hotelPackageI18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			hotelPackageDao.updateHotelPackageI18n(hotelPackageI18n);
		}
	}

	@Override
	public void deleteHotelPackageByHotelId(String hotelId) {
		hotelPackageDao.deleteHotelPackageByHotelId(hotelId);
	}

	@Override
	public HotelPackageVO getHotelPackageById(String hotelPackageId) {
		return hotelPackageDao.getHotelPackageById(hotelPackageId);
	}

	@Override
	public List<HotelPackageVO> getHotelPackageByObj(HotelPackageVO vo) {
		return hotelPackageDao.getHotelPackageByObj(vo);
	}

	@Override
	public HotelPackageResult searchHotelPackage(HotelPackageCreteria creteria) {
		HotelPackageResult result = new HotelPackageResult();
		List<HotelPackageVO> resultList = hotelPackageDao.searchHotelPackage(creteria);
		Integer totalCount = hotelPackageDao.searchHotelPackagecount(creteria);
		result.setResultList(resultList);
		result.setTotalCount(totalCount);
		return result;
	}

	@Override
	public HotelPackageVO getHotelPackageByHotelId(String hotelId) {
		return hotelPackageDao.getHotelPackageByHotelId(hotelId);
	}

	@Override
	public void deleteHotelPackageById(HotelPackageVO vo) {
		hotelPackageDao.deleteHotelPackageById(vo);
	}

	@Override
	public List<PackageVO> getDontUseHotelPackage(String hotelId) {
		return hotelPackageDao.getDontUseHotelPackage(hotelId);
	}

	@Override
	public List<PackageVO> getDontUseHotelPackage(String hotelId,
			String language) {
		return hotelPackageDao.getDontUseHotelPackage(hotelId, language);
	}

	@Override
	public List<Rateplan> getUseRatePackage(String hotelId, String packageId) {
		return hotelPackageDao.getUseRatePackage(hotelId, packageId);
	}

	@Override
	public List<RoomType> getUseRoomPackage(String hotelId, String packageId) {
		return hotelPackageDao.getUseRoomPackage(hotelId, packageId);
	}

	@Override
	public List<DynamicPackage> getUseDynamicPackage(String hotelId,
			String packageId) {
		return hotelPackageDao.getUseDynamicPackage(hotelId, packageId);
	}

	@Override
	public HotelPackageI18n addHotelPackageI18n(HotelPackageI18n i18n) {
		return hotelPackageDao.addHotelPackageI18n(i18n);
	}

	@Override
	public void deleteHotelPackageI18nById(String hotelPackageId) {
		hotelPackageDao.deleteHotelPackageI18nById(hotelPackageId);
	}

	@Override
	public HotelPackageVO getHotelPackageById(String hotelPackageId,String language) {
		return hotelPackageDao.getHotelPackageById(hotelPackageId, language);
	}

	@Override
	public HotelPackageVO getHotelPackageByHotelId(String hotelId,String language) {
		return hotelPackageDao.getHotelPackageByHotelId(hotelId, language);
	}

	@Override
	public List<HotelPackageI18n> getHotelPackageI18ns(String hotelPackageId) {
		return hotelPackageDao.getHotelPackageI18ns(hotelPackageId);
	}

	@Override
	public HotelPackageI18n getDefaultLanguageI18n(HotelPackageVO vo) {
		HotelPackageI18n i18n = new HotelPackageI18n();
		i18n.setHotelPackageId(vo.getHotelPackageId());
		if(vo.getLanguage()==null){
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			i18n.setLanguage(vo.getLanguage());
		}
		i18n.setPackageName(vo.getPackageName());
		i18n.setDescription(vo.getDescription());
		return i18n;
	}

	

}
