package com.ccm.api.service.hotel.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.hotel.PositionDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Position;
import com.ccm.api.model.hotel.PositionI18n;
import com.ccm.api.model.hotel.vo.PositionCreteria;
import com.ccm.api.model.hotel.vo.PositionResult;
import com.ccm.api.model.hotel.vo.PositionVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.hotel.PositionManager;

@Service("positionManager")
public class PositionManagerImpl extends GenericManagerImpl<Position, String> implements PositionManager {
	
	@Autowired
	private PositionDao positionDao;

	@Override
	public void savePosition(PositionVO vo) {
		Position vo1 = new Position();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = positionDao.addPosition(vo1);
		vo.setPositionId(vo1.getPositionId());
		
		if(vo.getPositionI18nList()==null||vo.getPositionI18nList().size()==0){
			List<PositionI18n> i18nList = new ArrayList<PositionI18n>();
			PositionI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setPositionI18nList(i18nList);
		}
		
		//循环添加多语言数据
		for (PositionI18n positionI18n : vo.getPositionI18nList()) {
			PositionI18n i18n = new PositionI18n();
			i18n.setPositionId(vo.getPositionId());
			i18n.setLanguageCode(positionI18n.getLanguageCode());
			i18n.setName(positionI18n.getName());
			i18n.setDescription(positionI18n.getDescription());
			positionDao.addPositionI18n(i18n);
		}
	}

	@Override
	public void updatePosition(PositionVO vo) {
		Position vo1 = new Position();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		positionDao.updatePosition(vo1);
		
		if(vo.getPositionI18nList()!=null){
			
			positionDao.deletePositionI18nByPositionId(vo.getPositionId());
			
			//循环添加多语言数据
			for (PositionI18n positionI18n : vo.getPositionI18nList()) {
				PositionI18n i18n = new PositionI18n();
				i18n.setPositionId(vo.getPositionId());
				i18n.setLanguageCode(positionI18n.getLanguageCode());
				i18n.setName(positionI18n.getName());
				i18n.setDescription(positionI18n.getDescription());
				positionDao.addPositionI18n(i18n);
			}
		}else{
			PositionI18n i18n = new PositionI18n();
			i18n.setRelativePositionMId(vo.getRelativePositionMId());
			i18n.setPositionId(vo.getPositionId());
			i18n.setLanguageCode(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setName(vo.getName());
			i18n.setDescription(vo.getDescription());
			positionDao.updatePositionI18n(i18n);
		}

	}

	@Override
	public void deletePosition(PositionVO vo) {
		Position vo1 = new Position();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		positionDao.deletePosition(vo1);
		positionDao.deletePositionI18nByPositionId(vo.getPositionId());
	}
	
	@Override
	public PositionI18n getDefaultLanguageI18n(PositionVO vo) {
		PositionI18n i18n = new PositionI18n();
		if(vo.getLanguageCode()==null){
			i18n.setLanguageCode(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			i18n.setLanguageCode(vo.getLanguageCode());
		}
		i18n.setName(vo.getName());
		i18n.setDescription(vo.getDescription());
		return i18n;
	}

	@Override
	public PositionVO getPositionById(String positionId) {
		return positionDao.getPositionById(positionId);
	}

	@Override
	public PositionResult searchPosition(PositionCreteria creteria) {
		PositionResult result = new PositionResult();
		List<PositionVO> voList = positionDao.searchPosition(creteria);
		Integer count = positionDao.searchPositionCount(creteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}
	
	@Override
	public List<PositionVO> getAllPosition(String hotelId) {
		return positionDao.getAllPosition(hotelId);
	}

	@Override
	public PositionVO getPositionById(String positionId, String languageCode) {
		return positionDao.getPositionById(positionId, languageCode);
	}

	@Override
	public void deletePositionI18nByPositionId(String positionId) {
		positionDao.deletePositionI18nByPositionId(positionId);
	}

	@Override
	public List<PositionI18n> getPositionI18ns(String positionId) {
		return positionDao.getPositionI18ns(positionId);
	}

	@Override
	public List<PositionVO> getAllPosition(String hotelId,String languageCode) {
		return positionDao.getAllPosition(hotelId,languageCode);
	}
	
	
}
