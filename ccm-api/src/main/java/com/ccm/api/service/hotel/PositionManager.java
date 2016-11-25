package com.ccm.api.service.hotel;

import java.util.List;

import com.ccm.api.model.hotel.Position;
import com.ccm.api.model.hotel.PositionI18n;
import com.ccm.api.model.hotel.vo.PositionCreteria;
import com.ccm.api.model.hotel.vo.PositionResult;
import com.ccm.api.model.hotel.vo.PositionVO;
import com.ccm.api.service.base.GenericManager;

public interface PositionManager extends GenericManager<Position, String> {

	/**
	 * 新增地标
	 */
	void savePosition(PositionVO vo);
	
	/**
	 * 修改地标
	 */
	void updatePosition(PositionVO vo);
	
	/**
	 * 删除地标
	 */
	void deletePosition(PositionVO vo);
	
	/**
	 * 根据地标ID取地标信息
	 */
	PositionVO getPositionById(String positionId);
	
	/**
	 * 根据条件取地标信息
	 */
	PositionResult searchPosition(PositionCreteria creteria);
	
	/**
	 * 查询所有品牌
	 */
	List<PositionVO> getAllPosition(String hotelId);
	
	/**
	 * 查询对应语言下的所有地标
	 */
	List<PositionVO> getAllPosition(String hotelId,String language);
	
	/**
	 * 获取地表信息
	 * @param positionId
	 * @param languageCode
	 * @return
	 */
	PositionVO getPositionById(String positionId, String languageCode);

	/**
	 * 删除地表多语言列表
	 * @param positionId
	 */
	void deletePositionI18nByPositionId(String positionId);

	/**
	 * 获取地表列表
	 * @param positionId
	 * @return
	 */
	List<PositionI18n> getPositionI18ns(String positionId);

	PositionI18n getDefaultLanguageI18n(PositionVO vo);
}
