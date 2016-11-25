package com.ccm.api.dao.hotel.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.hotel.PositionDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.hotel.Position;
import com.ccm.api.model.hotel.PositionI18n;
import com.ccm.api.model.hotel.vo.PositionCreteria;
import com.ccm.api.model.hotel.vo.PositionVO;

@Repository("positionDao")
public class PositionDaoibatis extends GenericDaoiBatis<Position, String> implements PositionDao {

	public PositionDaoibatis() {
		super(Position.class);
	}

	@Override
	public Position addPosition(Position vo) {
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		vo.setPositionId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addPosition",vo);
		return vo;
	}

	@Override
	public PositionI18n addPositionI18n(PositionI18n vo) {
		vo.setCreatedBy(SecurityHolder.getUserId());
		vo.setCreatedTime(new Date());
		vo.setRelativePositionMId(UUID.randomUUID().toString().replace("-", ""));
		getSqlMapClientTemplate().insert("addPositionI18n",vo);
		return vo;
	}

	@Override
	public void updatePosition(Position vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updatePosition",vo);
	}
	
	@Override
	public void updatePositionI18n(PositionI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updatePositionI18n",vo);
	}

	@Override
	public void deletePosition(Position vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deletePosition",vo);
	}

	@Override
	public void deletePositionI18n(PositionI18n vo) {
		vo.setUpdatedBy(SecurityHolder.getUserId());
		vo.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deletePositionI18n",vo);
	}

	@Override
	public PositionVO getPositionById(String positionId) {
		return this.getPositionById(positionId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public PositionVO getPositionById(String positionId,String languageCode) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("positionId", positionId);
		params.put("languageCode", languageCode);
		return (PositionVO) getSqlMapClientTemplate().queryForObject("getPositionById",params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PositionVO> searchPosition(PositionCreteria creteria) {
		//如果没有知道语言种类
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguageCode())){
			creteria.setLanguageCode(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchPosition",creteria);
	}

	@Override
	public Integer searchPositionCount(PositionCreteria creteria) {
		//如果没有知道语言种类
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguageCode())){
			creteria.setLanguageCode(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchPositionCount",creteria);
	}
	
	@Override
	public List<PositionVO> getAllPosition(String hotelId) {
		return this.getAllPosition(hotelId,LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PositionVO> getAllPosition(String hotelId,String languageCode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("hotelId", hotelId);
		params.put("languageCode", languageCode);
		return getSqlMapClientTemplate().queryForList("getAllPosition",params);
	}
	
	@Override
	public void deletePositionI18nByPositionId(String positionId){
		getSqlMapClientTemplate().update("deletePositionI18nByPositionId",positionId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PositionI18n> getPositionI18ns(String positionId){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("positionId", positionId);
		return getSqlMapClientTemplate().queryForList("getPositionI18ns",params);
	}
	
}
