package com.ccm.api.service.sell;

import java.util.List;

import com.ccm.api.model.sell.Source;
import com.ccm.api.model.sell.SourceI18n;
import com.ccm.api.model.sell.vo.SourceCreteria;
import com.ccm.api.model.sell.vo.SourceResult;
import com.ccm.api.model.sell.vo.SourceVO;
import com.ccm.api.service.base.GenericManager;

public interface SourceManager extends GenericManager<Source, String> {

	/**
	 * 新增来源
	 */
	void saveSource(SourceVO vo);
	
	/**
	 * 修改来源
	 */
	void updateSource(SourceVO vo);
	
	/**
	 * 删除来源
	 */
	void deleteSource(SourceVO vo);
	
	/**
	 * 根据来源ID取来源信息
	 */
	SourceVO getSourceById(String sourceId);
	
	/**
	 * 根据来源CODE取来源信息
	 */
	SourceVO getSourceByCode(String sourceCode);
	
	/**
	 * 根据来源CODE和语言取来源信息
	 */
	SourceVO getSourceByCode(String sourceCode, String language);
	
	/**
	 * 根据条件取来源信息
	 */
	SourceResult searchSource(SourceCreteria creteria);

	void deleteSourceI18nBySourceId(String sourceId);

	/**
	 * 根据sourceID和语言获取来源
	 * @param sourceId
	 * @param language
	 * @return
	 */
	SourceVO getSourceById(String sourceId, String language);

	List<SourceI18n> getSourceI18ns(String sourceId);

	SourceI18n getDefaultLanguageI18n(SourceVO vo);
}
