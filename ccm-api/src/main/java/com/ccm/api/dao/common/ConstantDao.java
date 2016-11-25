/**
 * 
 */
package com.ccm.api.dao.common;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.common.Constant;

/**
 * @author Jenny Liao
 * 
 */
public interface ConstantDao extends GenericDao<Constant, String> {
	
	void updateConstantFlagById(String id, int flag);

	/**
	 * 根据名称找具体值
	 * 
	 * @param name
	 * @return
	 */
	List<Constant> getConstantByName(String name);

	List<Constant> getConstantByObj(Constant c);

	List<Constant> getConstantByHotelCode();

	void updateFlagConstant(Constant c);
}
