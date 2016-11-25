/**
 * 
 */
package com.ccm.api.dao.convert.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.convert.CodeListMappingDao;
import com.ccm.api.model.convert.CodeListMapping;

/**
 * @author Jenny
 * 
 */
@Repository("codeListMappingDao")
public class CodeListMappingDaoiBatis extends GenericDaoiBatis<CodeListMapping, String> implements CodeListMappingDao {

	public CodeListMappingDaoiBatis() {
		super(CodeListMapping.class);
	}

	public void saveCodeListMappingBatch(List<CodeListMapping> codeMapList, String curHotelId, String curUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("codeMapList", codeMapList);
		param.put("curHotelId", curHotelId);
		param.put("createdBy", curUserId);
		getSqlMapClientTemplate().insert("addCodeMapList", param);
	}

	public void deleteCodeListMappingByHotelId(String hotelId) {
		getSqlMapClientTemplate().delete("deleteCodeListMappingByHotelId", hotelId);
	}

	@SuppressWarnings("unchecked")
	public List<CodeListMapping> getCodeMapNoRoomRateByHotelId(String hotelId) {
		return getSqlMapClientTemplate().queryForList("getCodeMapNoRoomRateByHotelId", hotelId);
	}

	/**
	 * 根据条件查询映射关系
	 */
	@SuppressWarnings("unchecked")
	public List<CodeListMapping> getCodeMapByCodeId(CodeListMapping codeMap) {
		return getSqlMapClientTemplate().queryForList("getCodeMapByCodeId", codeMap);
	}

	/**
	 * 根据channelId,codeName,codeId查询映射
	 */
	@SuppressWarnings("unchecked")
	public List<CodeListMapping> getCodeListMappingByCond(CodeListMapping codeMap) {
		return getSqlMapClientTemplate().queryForList("getCodeListMappingByCond", codeMap);
	}

}
