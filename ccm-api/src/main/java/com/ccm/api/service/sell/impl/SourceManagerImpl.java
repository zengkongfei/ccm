package com.ccm.api.service.sell.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.sell.SourceDao;
import com.ccm.api.model.constant.LanguageCode;
import com.ccm.api.model.sell.Source;
import com.ccm.api.model.sell.SourceI18n;
import com.ccm.api.model.sell.vo.SourceCreteria;
import com.ccm.api.model.sell.vo.SourceResult;
import com.ccm.api.model.sell.vo.SourceVO;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.sell.SourceManager;

@Service("sourceManager")
public class SourceManagerImpl extends GenericManagerImpl<Source, String> implements SourceManager {
	
	@Autowired
	private SourceDao sourceDao;

	@Override
	public void saveSource(SourceVO vo) {
		Source vo1 = new Source();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo1 = sourceDao.addSource(vo1);
		vo.setSourceId(vo1.getSourceId());
		
		if(vo.getSourceI18nList()==null||vo.getSourceI18nList().size()==0){
			List<SourceI18n> i18nList = new ArrayList<SourceI18n>();
			SourceI18n i18n = this.getDefaultLanguageI18n(vo);
			i18nList.add(i18n);
			vo.setSourceI18nList(i18nList);
		}
		
		//循环添加多语言数据
		for (SourceI18n sourceI18n : vo.getSourceI18nList()) {
			SourceI18n i18n = new SourceI18n();
			i18n.setSourceId(vo.getSourceId());
			i18n.setLanguage(sourceI18n.getLanguage());
			i18n.setDescription(sourceI18n.getDescription());
			sourceDao.addSourceI18n(i18n);
		}
	}

	@Override
	public void updateSource(SourceVO vo) {
		Source vo1 = new Source();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sourceDao.updateSource(vo1);
		
		//CCM系统设置中 更改来源多语言列表
		if(vo.getSourceI18nList()!=null){
			//批量删除多语言记录
			sourceDao.deleteSourceI18nBySourceId(vo.getSourceId());
			
			//循环添加多语言数据
			for (SourceI18n sourceI18n : vo.getSourceI18nList()) {
				SourceI18n i18n = new SourceI18n();
				i18n.setSourceId(vo.getSourceId());
				i18n.setLanguage(sourceI18n.getLanguage());
				i18n.setDescription(sourceI18n.getDescription());
				sourceDao.addSourceI18n(i18n);
			}
		}else{
			SourceI18n i18n = new SourceI18n();
			i18n.setSourceMId(vo.getSourceMId());
			i18n.setSourceId(vo.getSourceId());
			i18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
			i18n.setDescription(vo.getDescription());
			sourceDao.updateSourceI18n(i18n);
		}
	}

	@Override
	public void deleteSource(SourceVO vo) {
		Source vo1 = new Source();
		try {
			PropertyUtils.copyProperties(vo1, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sourceDao.deleteSource(vo1);
		
		sourceDao.deleteSourceI18nBySourceId(vo.getSourceId());
	}

	@Override
	public SourceVO getSourceById(String sourceId) {
		return sourceDao.getSourceById(sourceId);
	}
	
	@Override
	public SourceVO getSourceByCode(String sourceCode) {
		return sourceDao.getSourceByCode(sourceCode);
	}

	@Override
	public SourceResult searchSource(SourceCreteria creteria) {
		SourceResult result = new SourceResult();
		List<SourceVO> voList = sourceDao.searchSource(creteria);
		Integer count = sourceDao.searchSourceCount(creteria);
		result.setResultList(voList);
		result.setTotalCount(count);
		return result;
	}

	
	@Override
	public SourceI18n getDefaultLanguageI18n(SourceVO vo) {
		SourceI18n sourceI18n = new SourceI18n();
		if(vo.getLanguage()==null){
			sourceI18n.setLanguage(LanguageCode.MAIN_LANGUAGE_CODE);
		}else{
			sourceI18n.setLanguage(vo.getLanguage());
		}
		sourceI18n.setDescription(vo.getDescription());
		return sourceI18n;
	}
	
	@Override
	public void deleteSourceI18nBySourceId(String sourceId) {
		sourceDao.deleteSourceI18nBySourceId(sourceId);
	}

	@Override
	public SourceVO getSourceById(String sourceId, String language) {
		return sourceDao.getSourceById(sourceId, language);
	}

	@Override
	public List<SourceI18n> getSourceI18ns(String sourceId) {
		return sourceDao.getSourceI18ns(sourceId);
	}

	@Override
	public SourceVO getSourceByCode(String sourceCode, String language) {
		return sourceDao.getSourceByCode(sourceCode, language);
	}
}
