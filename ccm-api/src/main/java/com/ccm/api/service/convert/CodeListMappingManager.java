/**
 * 
 */
package com.ccm.api.service.convert;

import java.util.List;

import com.ccm.api.model.convert.CodeListMapping;
import com.ccm.api.service.base.GenericManager;

/**
 * @author Jenny
 * 
 */
public interface CodeListMappingManager extends GenericManager<CodeListMapping, String> {

	void copyCodeMapByHotelId(String hotelId, String curHotelId, String curUserId);

	/**
	 * 根据酒店代码，字典ID查询映射关系
	 * 
	 * @param dictId
	 * @param hotelId
	 * @return
	 */
	List<CodeListMapping> getCodeMapByCodeId(String dictId, String hotelId);

	/**
	 * 查询映射
	 * 
	 * @param codeMap
	 * @return
	 */
	CodeListMapping getCodeListMappingByCond(CodeListMapping codeMap);

}
