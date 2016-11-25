/**
 * 
 */
package com.ccm.api.dao.common;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.common.ParamInterface;

/**
 * @author Jenny
 * 
 */
public interface ParamInterfaceDao extends GenericDao<ParamInterface, String> {

	List<ParamInterface> getParamInterfaceByObj(ParamInterface pi);
	
	/**
	 * 多语言
	 * @param pi
	 * @param language
	 * @return
	 */
	List<ParamInterface> getParamInterfaceByObj(ParamInterface pi,String language);

	List<String> getCode(String sql, String param);

}
