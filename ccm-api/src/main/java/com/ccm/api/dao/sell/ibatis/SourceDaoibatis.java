package com.ccm.api.dao.sell.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.ccm.api.SecurityHolder;
import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.sell.SourceDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.sell.Source;
import com.ccm.api.model.sell.SourceI18n;
import com.ccm.api.model.sell.vo.SourceCreteria;
import com.ccm.api.model.sell.vo.SourceVO;

@Repository("sourceDao")
public class SourceDaoibatis extends GenericDaoiBatis<Source, String> implements SourceDao {

	public SourceDaoibatis() {
		super(Source.class);
	}

	@Override
	public Source addSource(Source source) {
		source.setSourceId(UUID.randomUUID().toString().replace("-", ""));
		source.setCreatedBy(SecurityHolder.getUserId());
		source.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addSource",source);
		return source;
	}

	@Override
	public SourceI18n addSourceI18n(SourceI18n sourceI18n) {
		sourceI18n.setSourceMId(UUID.randomUUID().toString().replace("-", ""));
		sourceI18n.setCreatedBy(SecurityHolder.getUserId());
		sourceI18n.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("addSourceI18n",sourceI18n);
		return sourceI18n;
	}

	@Override
	public void updateSource(Source source) {
		source.setUpdatedBy(SecurityHolder.getUserId());
		source.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateSource",source);
	}
	
	@Override
	public void updateSourceI18n(SourceI18n sourceI18n) {
		sourceI18n.setUpdatedBy(SecurityHolder.getUserId());
		sourceI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("updateSourceI18n",sourceI18n);
	}

	@Override
	public void deleteSource(Source source) {
		source.setUpdatedBy(SecurityHolder.getUserId());
		source.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteSource",source);
	}

	@Override
	public void deleteSourceI18n(SourceI18n sourceI18n) {
		sourceI18n.setUpdatedBy(SecurityHolder.getUserId());
		sourceI18n.setLastModifyTime(new Date());
		getSqlMapClientTemplate().update("deleteSourceI18n",sourceI18n);
	}

	@Override
	public SourceVO getSourceByCode(String sourceCode) {
		return this.getSourceByCode(sourceCode, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public SourceVO getSourceByCode(String sourceCode,String language) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("sourceCode", sourceCode);
		params.put("language", language);
		return (SourceVO) getSqlMapClientTemplate().queryForObject("getSourceByCode",params);
	}
	
	@Override
	public SourceVO getSourceById(String sourceId) {
		return this.getSourceById(sourceId, LanguageCode.MAIN_LANGUAGE_CODE);
	}
	
	@Override
	public SourceVO getSourceById(String sourceId,String language) {
		Map<String,String> params = new HashMap<String, String>();
		params.put("sourceId", sourceId);
		params.put("language", language);
		return (SourceVO) getSqlMapClientTemplate().queryForObject("getSourceById",params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SourceVO> searchSource(SourceCreteria creteria) {
		//如果没有知道语言种类
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return getSqlMapClientTemplate().queryForList("searchSource",creteria);
	}

	@Override
	public Integer searchSourceCount(SourceCreteria creteria) {
		//如果没有知道语言种类
		if(creteria!=null&&StringUtils.isBlank(creteria.getLanguage())){
			creteria.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}
		return (Integer) getSqlMapClientTemplate().queryForObject("searchSourceCount",creteria);
	}
	
	@Override
	public void deleteSourceI18nBySourceId(String sourceId){
		getSqlMapClientTemplate().update("deleteSourceI18nBySourceId",sourceId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SourceI18n> getSourceI18ns(String sourceId){
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("sourceId", sourceId);
		return getSqlMapClientTemplate().queryForList("getSourceI18ns",params);
	}
}
