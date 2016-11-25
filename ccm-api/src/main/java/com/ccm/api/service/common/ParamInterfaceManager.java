/**
 * 
 */
package com.ccm.api.service.common;

import com.ccm.api.model.common.ParamInterface;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface ParamInterfaceManager extends GenericManager<ParamInterface, String> {

	ParamInterface getParamInterfaceByObj(ParamInterface pi);
	
	/**
	 * 多语言
	 * @param pi
	 * @param language
	 * @return
	 */
	ParamInterface getParamInterfaceByObj(ParamInterface pi,String language);

	String getCode(String sql, String param);

	String getPrimaryKeyByCond(String urlId);

}
