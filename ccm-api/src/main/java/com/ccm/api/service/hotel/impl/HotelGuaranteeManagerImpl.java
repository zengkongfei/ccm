package com.ccm.api.service.hotel.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.hotel.HotelGuaranteeDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.HotelGuarantee;
import com.ccm.api.model.hotel.HotelGuaranteeI18n;
import com.ccm.api.model.hotel.vo.HotelGuaranteeCreteria;
import com.ccm.api.model.hotel.vo.HotelGuaranteeResult;
import com.ccm.api.model.hotel.vo.HotelGuaranteeVO;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.HotelGuaranteeManager;

@Service("hotelGuaranteeManager")
public class HotelGuaranteeManagerImpl extends GenericManagerImpl<HotelGuarantee, String> implements HotelGuaranteeManager{

	@Autowired
	private HotelGuaranteeDao hotelGuaranteeDao;
	
	@Override
	public HotelGuaranteeVO saveHotelGuarantee(HotelGuaranteeVO vo) {
		HotelGuarantee vo1 = new HotelGuarantee();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hotelGuaranteeDao.addHotelGuarantee(vo1);
		vo.setHotelGuaranteeId(vo1.getHotelGuaranteeId());
		
		if(vo.getHotelGuaranteeI18nList() == null 
				|| vo.getHotelGuaranteeI18nList().size() == 0){
			List<HotelGuaranteeI18n> i18nList = new ArrayList<HotelGuaranteeI18n>();
			HotelGuaranteeI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setHotelGuaranteeI18nList(i18nList);
		}
		
		//循环添加多语言数据
		for (HotelGuaranteeI18n hotelGuaranteeI18n : vo.getHotelGuaranteeI18nList()) {
			HotelGuaranteeI18n i18n = new HotelGuaranteeI18n();
			i18n.setHotelGuaranteeId(vo.getHotelGuaranteeId());
			i18n.setLanguage(hotelGuaranteeI18n.getLanguage());
			i18n.setPolicyName(hotelGuaranteeI18n.getPolicyName());
			i18n.setDescription(hotelGuaranteeI18n.getDescription());
			hotelGuaranteeDao.addHotelGuaranteeI18n(i18n);
		}
		return vo;
	}

	@Override
	public void updateHotelGuarantee(HotelGuaranteeVO vo) {
		HotelGuarantee vo1 = new HotelGuarantee();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hotelGuaranteeDao.updaetHotelGuarantee(vo1);
		
		if(vo.getHotelGuaranteeI18nList()!=null){
			hotelGuaranteeDao.deleteHotelGuaranteeI18nById(vo.getHotelGuaranteeId());
			//循环添加多语言数据
			for (HotelGuaranteeI18n hotelGuaranteeI18n : vo.getHotelGuaranteeI18nList()) {
				HotelGuaranteeI18n i18n = new HotelGuaranteeI18n();
				i18n.setHotelGuaranteeId(vo.getHotelGuaranteeId());
				i18n.setLanguage(hotelGuaranteeI18n.getLanguage());
				i18n.setPolicyName(hotelGuaranteeI18n.getPolicyName());
				i18n.setDescription(hotelGuaranteeI18n.getDescription());
				hotelGuaranteeDao.addHotelGuaranteeI18n(i18n);
			}
		}else{
			HotelGuaranteeI18n hotelGuaranteeI18n = new HotelGuaranteeI18n();
			hotelGuaranteeI18n.setHotelGuaranteeMId(vo.getHotelGuaranteeMId());
			hotelGuaranteeI18n.setHotelGuaranteeId(vo.getHotelGuaranteeId());
			hotelGuaranteeI18n.setPolicyName(vo.getPolicyName());
			hotelGuaranteeI18n.setDescription(vo.getDescription());
			hotelGuaranteeI18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			hotelGuaranteeDao.updateHotelGuaranteeI18n(hotelGuaranteeI18n);
		}
	}

	@Override
	public void deleteHotelGuaranteeByHotelId(String hotelId) {
		hotelGuaranteeDao.deleteHotelGuaranteeByHotelId(hotelId);
	}

	@Override
	public void deleteHotelGuaranteeById(HotelGuaranteeVO vo) {
		hotelGuaranteeDao.deleteHotelGuaranteeById(vo);
	}

	@Override
	public HotelGuaranteeVO getHotelGuaranteeById(String hotelGuaranteeId) {
		return hotelGuaranteeDao.getHotelGuaranteeById(hotelGuaranteeId);
	}
	
	@Override
	public HotelGuaranteeVO getHotelGuaranteeById(String hotelGuaranteeId,String language) {
		return hotelGuaranteeDao.getHotelGuaranteeById(hotelGuaranteeId,language);
	}

	@Override
	public List<HotelGuaranteeVO> getHotelGuaranteeByObj(HotelGuaranteeVO vo) {
		return hotelGuaranteeDao.getHotelGuaranteeByObj(vo);
	}

	@Override
	public HotelGuaranteeResult searchHotelGuarantee(
			HotelGuaranteeCreteria creteria) {
		HotelGuaranteeResult result = new HotelGuaranteeResult();
		List<HotelGuaranteeVO> resultList = hotelGuaranteeDao.searchHotelGuarantee(creteria);
		Integer totalCount = hotelGuaranteeDao.searchHotelGuaranteecount(creteria);
		result.setResultList(resultList);
		result.setTotalCount(totalCount);
		return result;
	}

	@Override
	public HotelGuaranteeVO getHotelGuaranteeByHotelId(String hotelId) {
		return hotelGuaranteeDao.getHotelGuaranteeByHotelId(hotelId);
	}
	
	public List<HotelGuaranteeVO> getHotelGuaranteeCodeByHotelId(String hotelId) {
		return hotelGuaranteeDao.getHotelGuaranteeCodeByHotelId(hotelId);
	}

	@Override
	public List<GuaranteePolicyVO> getDontUseHotelGuarantee(String hotelId) {
		return hotelGuaranteeDao.getDontUseHotelGuarantee(hotelId);
	}

	@Override
	public List<GuaranteePolicyVO> getDontUseHotelGuarantee(String hotelId,
			String language) {
		return hotelGuaranteeDao.getDontUseHotelGuarantee(hotelId, language);
	}

	@Override
	public List<Rateplan> getUseRateGuarantee(String hotelId, String guaranteeId) {
		return hotelGuaranteeDao.getUseRateGuarantee(hotelId, guaranteeId);
	}

	@Override
	public HotelGuaranteeI18n addHotelGuaranteeI18n(HotelGuaranteeI18n i18n) {	
		return hotelGuaranteeDao.addHotelGuaranteeI18n(i18n);
	}

	@Override
	public void deleteHotelGuaranteeI18nById(String hotelGuaranteeId) {
		hotelGuaranteeDao.deleteHotelGuaranteeI18nById(hotelGuaranteeId);
	}

	@Override
	public List<HotelGuaranteeI18n> getHotelGuaranteeI18ns(
			String hotelGuaranteeId) {
		return hotelGuaranteeDao.getHotelGuaranteeI18ns(hotelGuaranteeId);
	}

	@Override
	public HotelGuaranteeI18n getDefaultLanguageI18n(HotelGuaranteeVO vo) {
		HotelGuaranteeI18n i18n = new HotelGuaranteeI18n();
		i18n.setHotelGuaranteeId(vo.getHotelGuaranteeId());
		i18n.setPolicyName(vo.getPolicyName());
		i18n.setDescription(vo.getDescription());
		if(vo.getLanguage()==null){
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			i18n.setLanguage(vo.getLanguage());
		}
		return i18n;
	}

}
