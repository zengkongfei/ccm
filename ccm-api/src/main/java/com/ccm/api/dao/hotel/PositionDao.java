package com.ccm.api.dao.hotel;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.hotel.Position;
import com.ccm.api.model.hotel.PositionI18n;
import com.ccm.api.model.hotel.vo.PositionCreteria;
import com.ccm.api.model.hotel.vo.PositionVO;

public interface PositionDao extends GenericDao<Position, String> {

	Position addPosition(Position position);
	
	PositionI18n addPositionI18n(PositionI18n positionI18n);
	
	void updatePosition(Position position);
	
	void updatePositionI18n(PositionI18n positionI18n);
	
	void deletePosition(Position position);
	
	void deletePositionI18n(PositionI18n positionI18n);
	
	PositionVO getPositionById(String positionId);
	
	List<PositionVO> searchPosition(PositionCreteria creteria);
	
	Integer searchPositionCount(PositionCreteria creteria);
	
	List<PositionVO> getAllPosition(String hotelId);

	PositionVO getPositionById(String positionId, String languageCode);

	void deletePositionI18nByPositionId(String positionId);

	List<PositionI18n> getPositionI18ns(String positionId);

	List<PositionVO> getAllPosition(String hotelId,String languageCode);
}
