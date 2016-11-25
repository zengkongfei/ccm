/**
 * 
 */
package com.ccm.api.dao.common.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.common.DictCodeDao;
import com.ccm.api.model.common.DictCode;
import com.ccm.api.model.common.Dictionary;
import com.ccm.api.model.common.vo.DictCodeChannelCodeNameVO;
import com.ccm.api.model.constant.LanguageCode;

/**
 * @author Jenny Liao
 * 
 */
@Repository("dictCodeDao")
public class DictCodeDaoImpl extends GenericDaoiBatis<DictCode, String> implements DictCodeDao {

	/**
	 * Constructor that sets the entity to User.class.
	 */
	public DictCodeDaoImpl() {
		super(DictCode.class);
	}

	/**
	 * 根据字典ID找字典表
	 */
	public Dictionary getDictionaryByDictId(String dictId) {
		if (!StringUtils.hasText(dictId)) {
			return null;
		}

		return (Dictionary) getSqlMapClientTemplate().queryForObject("getDictionaryByDictId", dictId);
	}

	/**
	 * 根据字典类型找字典代码列表
	 */
	@SuppressWarnings("unchecked")
	public List<Dictionary> getDictionaryByDictType(List<String> dictTypes,String language) {
		if (dictTypes == null || dictTypes.isEmpty()) {
			return null;
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dictTypes", dictTypes);
		param.put("language", language);
		return getSqlMapClientTemplate().queryForList("getDictionaryByDictType", param);
	}

	/**
	 * 根据字典ID找系统值
	 */
	@SuppressWarnings("unchecked")
	public List<DictCode> getDictCodeByDictId(String dictId, String hotelId,String language) {
		if (!StringUtils.hasText(dictId)) {
			return null;
		}
		Dictionary d = getDictionaryByDictId(dictId);
		if (d != null) {
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("dataTable", d.getDataTable());
			if (StringUtils.hasText(d.getQuerySql())) {
				paraMap.put("querySql", d.getQuerySql().replace("hotelId=#hotelId#", "hotelId=\"" + hotelId + "\""));
//				d.setQuerySql(d.getQuerySql().replace("hotelId=#hotelId#", "hotelId=\"" + hotelId + "\""));
			}
			paraMap.put("lableFeild", d.getLableFeild());
			paraMap.put("dictId", dictId);
			paraMap.put("hotelId", hotelId);
			paraMap.put("language", language);
			return getSqlMapClientTemplate().queryForList("getDictCodeByDictId", paraMap);
		}
		return null;
	}

	/**
	 * 根据字典名称找字典代码列表
	 */
	@SuppressWarnings("unchecked")
	public List<DictCode> searchByDictName(String dictName) {
		if (!StringUtils.hasText(dictName)) {
			return null;
		}
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("dictName", dictName);
		paraMap.put("language", LanguageCode.MAIN_LANGUAGE_CODE);
		return getSqlMapClientTemplate().queryForList("searchByDictName", paraMap);
	}
	/**
	 * 根据字典名称找字典代码列表
	 */
	@SuppressWarnings("unchecked")
	public List<DictCode> searchByDictName(String dictName,String language) {
		if (!StringUtils.hasText(dictName)) {
			return null;
		}
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("dictName", dictName);
		paraMap.put("language", language);
		return getSqlMapClientTemplate().queryForList("searchByDictName", paraMap);
	}

	/**
	 * 根据字典名称与代码找具体字典代码
	 */
	public DictCode searchByCodeNo(String dictName, String codeNo) {
		if (!StringUtils.hasText(dictName)) {
			return null;
		}
		if (!StringUtils.hasText(codeNo)) {
			return null;
		}

		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("dictName", dictName);
		paraMap.put("codeNo", codeNo);
		DictCode dictCode = (DictCode) getSqlMapClientTemplate().queryForObject("searchByCodeNo", paraMap);

		return dictCode;

	}

	/**
	 * 根据字典名称与渠道代码名称查询此渠道对应的所有设施
	 */
	@SuppressWarnings("unchecked")
	public List<DictCodeChannelCodeNameVO> searchCodeByChannelDictName(String dictName, String channelCode) {
		if (!StringUtils.hasText(dictName)) {
			return null;
		}
		if (!StringUtils.hasText(channelCode)) {
			return null;
		}

		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("dictName", dictName);
		paraMap.put("channelCode", channelCode);
		return getSqlMapClientTemplate().queryForList("searchCodeByChannelDictName", paraMap);

	}

	/**
	 * 根据字典名称与渠道代码名称查询此渠道对应的所有设施。 key:渠道的字段名称（codeListMapping中的codeName），
	 * value:系统中真实的值(dictCode中的codeNo)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> searchChannelCodeNoByChannel(String dictName, String channelCode, Boolean codeName) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("dictName", dictName);
		paraMap.put("channelCode", channelCode);
		if (codeName) {
			return getSqlMapClientTemplate().queryForMap("searchChannelCodeNoByChannel", paraMap, "codeName", "codeNo");
		} else {
			return getSqlMapClientTemplate().queryForMap("searchChannelCodeNoByChannel", paraMap, "codeNo", "codeName");
		}

	}

	/**
	 * key:系统中的值，value:pms的值
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> searchCodeMapByChannelHotel(String dictName, String channelId, String hotelId, Boolean kvf) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("dictName", dictName);
		paraMap.put("channelId", channelId);
		paraMap.put("hotelId", hotelId);

		// 如字典是单独表维护时
		List<Dictionary> dl = getSqlMapClientTemplate().queryForList("getDictionaryByDictName", dictName);
		if (dl != null && !dl.isEmpty()) {
			Dictionary d = dl.get(0);
			paraMap.put("dataTable", d.getDataTable());
			paraMap.put("lableFeild", d.getLableFeild());
			if (StringUtils.hasText(d.getQuerySql())) {
				if (StringUtils.hasText(hotelId)) {
					paraMap.put("querySql", d.getQuerySql().replace("hotelId=#hotelId#", "hotelId=\"" + hotelId + "\""));
				} else {
					paraMap.put("querySql", d.getQuerySql().replace(" and hotelId=#hotelId#", ""));
				}
			}
		}

		if (kvf) {
			paraMap.put("ccm2external", "1");
			return getSqlMapClientTemplate().queryForMap("searchCodeMapByPms", paraMap, "codeNo", "codeName");
		} else {
			paraMap.put("external2ccm", "1");
			return getSqlMapClientTemplate().queryForMap("searchCodeMapByPms", paraMap, "codeName", "codeNo");
		}

	}

	/**
	 * key:系统中的值，value:pms的值
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> searchRoomCodeMapByHotel(String hotelId, Boolean kvf) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("hotelId", hotelId);
		if (kvf) {
			paraMap.put("ccm2external", "1");
			return getSqlMapClientTemplate().queryForMap("searchRoomCodeMapByPms", paraMap, "codeNo", "codeName");
		} else {
			paraMap.put("external2ccm", "1");
			return getSqlMapClientTemplate().queryForMap("searchRoomCodeMapByPms", paraMap, "codeName", "codeNo");
		}

	}

}
