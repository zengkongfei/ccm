package com.ccm.api.service.hotel.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.hotel.HotelCancelDao;
import com.ccm.api.model.channel.Rateplan;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.HotelCancel;
import com.ccm.api.model.hotel.HotelCancelI18n;
import com.ccm.api.model.hotel.vo.HotelCancelCreteria;
import com.ccm.api.model.hotel.vo.HotelCancelResult;
import com.ccm.api.model.hotel.vo.HotelCancelVO;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.HotelCancelManager;

@Service("hotelCancelManager")
public class HotelCancelManagerImpl extends GenericManagerImpl<HotelCancel, String> implements HotelCancelManager{
	
	@Autowired
	private HotelCancelDao hotelCancelDao;
	
	@Override
	public HotelCancelVO saveHotelCancel(HotelCancelVO vo) {
		HotelCancel vo1 = new HotelCancel();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hotelCancelDao.addHotelCancel(vo1);
		vo.setHotelCancelId(vo1.getHotelCancelId());
		
		if(vo.getHotelCancelI18nList() == null 
				|| vo.getHotelCancelI18nList().size() == 0){
			List<HotelCancelI18n> i18nList = new ArrayList<HotelCancelI18n>();
			HotelCancelI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setHotelCancelI18nList(i18nList);
		}
		
		//循环添加多语言数据
		for (HotelCancelI18n hotelCancelI18n : vo.getHotelCancelI18nList()) {
			HotelCancelI18n i18n = new HotelCancelI18n();
			i18n.setHotelCancelId(vo.getHotelCancelId());
			i18n.setLanguage(hotelCancelI18n.getLanguage());
			i18n.setPolicyName(hotelCancelI18n.getPolicyName());
			i18n.setDescription(hotelCancelI18n.getDescription());
			hotelCancelDao.addHotelCancelI18n(i18n);
		}
		return vo;
	}

	@Override
	public void updateHotelCancel(HotelCancelVO vo) {
		HotelCancel vo1 = new HotelCancel();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hotelCancelDao.updaetHotelCancel(vo1);
		
		if(vo.getHotelCancelI18nList()!=null){
			hotelCancelDao.deleteHotelCancelI18nById(vo.getHotelCancelId());
			//循环添加多语言数据
			for (HotelCancelI18n hotelCancelI18n : vo.getHotelCancelI18nList()) {
				HotelCancelI18n i18n = new HotelCancelI18n();
				i18n.setHotelCancelId(vo.getHotelCancelId());
				i18n.setLanguage(hotelCancelI18n.getLanguage());
				i18n.setPolicyName(hotelCancelI18n.getPolicyName());
				i18n.setDescription(hotelCancelI18n.getDescription());
				hotelCancelDao.addHotelCancelI18n(i18n);
			}
		}else{
			HotelCancelI18n hotelCancelI18n = new HotelCancelI18n();
			hotelCancelI18n.setHotelCancelMId(vo.getHotelCancelMId());
			hotelCancelI18n.setHotelCancelId(vo.getHotelCancelId());
			hotelCancelI18n.setPolicyName(vo.getPolicyName());
			hotelCancelI18n.setDescription(vo.getDescription());
			hotelCancelI18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			hotelCancelDao.updateHotelCancelI18n(hotelCancelI18n);
		}
	}

	@Override
	public void deleteHotelCancelByHotelId(String hotelId) {
		hotelCancelDao.deleteHotelCancelByHotelId(hotelId);
	}

	@Override
	public void deleteHotelCancelById(HotelCancelVO vo) {
		hotelCancelDao.deleteHotelCancelById(vo);
	}

	@Override
	public HotelCancelVO getHotelCancelById(String hotelCancelId) {
		return hotelCancelDao.getHotelCancelById(hotelCancelId);
	}
	
	@Override
	public HotelCancelVO getHotelCancelById(String hotelCancelId,
			String language) {
		return hotelCancelDao.getHotelCancelById(hotelCancelId,language);
	}
	
	@Override
	public List<HotelCancelVO> getHotelCancelByObj(HotelCancelVO vo) {
		return hotelCancelDao.getHotelCancelByObj(vo);
	}

	@Override
	public HotelCancelResult searchHotelCancel(HotelCancelCreteria creteria) {
		HotelCancelResult result = new HotelCancelResult();
		List<HotelCancelVO> resultList = hotelCancelDao.searchHotelCancel(creteria);
		Integer totalCount = hotelCancelDao.searchHotelCancelcount(creteria);
		result.setResultList(resultList);
		result.setTotalCount(totalCount);
		return result;
	}

	@Override
	public HotelCancelVO getHotelCancelByHotelId(String hotelId) {
		return hotelCancelDao.getHotelCancelByHotelId(hotelId);
	}

	@Override
	public List<CancelPolicyVO> getDontUseHotelCancel(String hotelId) {
		return hotelCancelDao.getDontUseHotelCancel(hotelId);
	}

	@Override
	public List<CancelPolicyVO> getDontUseHotelCancel(String hotelId,
			String language) {
		return hotelCancelDao.getDontUseHotelCancel(hotelId, language);
	}

	@Override
	public List<Rateplan> getUseRateCancel(String hotelId, String cancelId) {
		return hotelCancelDao.getUseRateCancel(hotelId, cancelId);
	}

	@Override
	public HotelCancelI18n addHotelCancelI18n(HotelCancelI18n i18n) {
		return hotelCancelDao.addHotelCancelI18n(i18n);
	}

	@Override
	public void deleteHotelCancelI18nById(String hotelCancelId) {
		hotelCancelDao.deleteHotelCancelI18nById(hotelCancelId);
		
	}

	@Override
	public List<HotelCancelI18n> getHotelCancelI18ns(String hotelCancelId) {
		return hotelCancelDao.getHotelCancelI18ns(hotelCancelId);
	}

	@Override
	public HotelCancelI18n getDefaultLanguageI18n(HotelCancelVO vo) {
		HotelCancelI18n i18n = new HotelCancelI18n();
		i18n.setPolicyName(vo.getPolicyName());
		if(vo.getLanguage()==null){
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			i18n.setLanguage(vo.getLanguage());
		}
		i18n.setDescription(vo.getDescription());
		i18n.setHotelCancelId(vo.getHotelCancelId());
		return i18n;
	}

	

}
