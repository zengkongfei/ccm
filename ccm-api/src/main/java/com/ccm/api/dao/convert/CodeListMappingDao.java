/**
 * 
 */
package com.ccm.api.dao.convert;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.convert.CodeListMapping;

/**
 * @author Jenny
 * 
 */
public interface CodeListMappingDao extends GenericDao<CodeListMapping, String> {

	void saveCodeListMappingBatch(List<CodeListMapping> codeMapList, String curHotelId, String curUserId);

	void deleteCodeListMappingByHotelId(String hotelId);

	List<CodeListMapping> getCodeMapNoRoomRateByHotelId(String hotelId);

	/**
	 * 根据条件查询映射关系
	 * 
	 * @param codeMap
	 * @return
	 */
	List<CodeListMapping> getCodeMapByCodeId(CodeListMapping codeMap);

	/**
	 * 根据channelId,codeName,codeId查询映射
	 * 
	 * @param codeMap
	 * @return
	 */
	List<CodeListMapping> getCodeListMappingByCond(CodeListMapping codeMap);

}
