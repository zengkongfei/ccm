package com.ccm.api.dao.sell;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.sell.Source;
import com.ccm.api.model.sell.SourceI18n;
import com.ccm.api.model.sell.vo.SourceCreteria;
import com.ccm.api.model.sell.vo.SourceVO;

public interface SourceDao extends GenericDao<Source, String> {

	Source addSource(Source source);
	
	SourceI18n addSourceI18n(SourceI18n sourceI18n);
	
	void updateSource(Source source);
	
	void updateSourceI18n(SourceI18n sourceI18n);
	
	void deleteSource(Source source);
	
	void deleteSourceI18n(SourceI18n sourceI18n);
	
	SourceVO getSourceByCode(String sourceCode);
	
	SourceVO getSourceById(String sourceId);
	
	List<SourceVO> searchSource(SourceCreteria creteria);
	
	Integer searchSourceCount(SourceCreteria creteria);

	SourceVO getSourceById(String sourceId, String language);

	void deleteSourceI18nBySourceId(String sourceId);

	List<SourceI18n> getSourceI18ns(String sourceId);

	SourceVO getSourceByCode(String sourceCode, String language);
}
