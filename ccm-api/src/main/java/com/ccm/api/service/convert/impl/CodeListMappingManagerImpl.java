/**
 * 
 */
package com.ccm.api.service.convert.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.common.DictCodeDao;
import com.ccm.api.dao.convert.CodeListMappingDao;
import com.ccm.api.model.common.Dictionary;
import com.ccm.api.model.convert.CodeListMapping;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.convert.CodeListMappingManager;

/**
 * @author Jenny
 * 
 */
@Service("codeListMappingManager")
public class CodeListMappingManagerImpl extends GenericManagerImpl<CodeListMapping, String> implements CodeListMappingManager {

	private CodeListMappingDao codeListMappingDao;

	@Autowired
	private DictCodeDao dictCodeDao;

	@Autowired
	public CodeListMappingManagerImpl(CodeListMappingDao codeListMappingDao) {
		super(codeListMappingDao);
		this.codeListMappingDao = codeListMappingDao;
	}

	public void copyCodeMapByHotelId(String hotelId, String curHotelId, String curUserId) {
		// 先删除目标酒店原来的转换代码（房价和房型除外）
		codeListMappingDao.deleteCodeListMappingByHotelId(curHotelId);
		// 复制用户选择的酒店转换代码到目标（当前酒店）酒店上。
		List<CodeListMapping> codeMapList = codeListMappingDao.getCodeMapNoRoomRateByHotelId(hotelId);
		if (!codeMapList.isEmpty()) {
			codeListMappingDao.saveCodeListMappingBatch(codeMapList, curHotelId, curUserId);
		}
	}

	/**
	 * 根据酒店ID，字典ID查询映射关系
	 */
	public List<CodeListMapping> getCodeMapByCodeId(String dictId, String hotelId) {
		CodeListMapping codeMap = new CodeListMapping();
		codeMap.setDictId(dictId);
		codeMap.setHotelId(hotelId);
		Dictionary d = dictCodeDao.getDictionaryByDictId(dictId);
		if (d != null) {
			codeMap.setDataTable(d.getDataTable());
			codeMap.setLableField(d.getLableFeild());
			if (StringUtils.hasText(d.getQuerySql())) {
				codeMap.setQuerySql(d.getQuerySql().replace("hotelId=#hotelId#", "hotelId=\"" + hotelId + "\""));
			}
		}
		return codeListMappingDao.getCodeMapByCodeId(codeMap);
	}

	/**
	 * 根据channelId或hotelid,codeName,codeId查询映射
	 */
	public CodeListMapping getCodeListMappingByCond(CodeListMapping codeMap) {
		List<CodeListMapping> clmList = codeListMappingDao.getCodeListMappingByCond(codeMap);
		if (clmList != null && clmList.size() > 0) {
			return clmList.get(0);
		}
		return null;

	}
}
