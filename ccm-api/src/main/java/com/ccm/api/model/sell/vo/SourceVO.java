package com.ccm.api.model.sell.vo;

import java.util.List;

import com.ccm.api.model.sell.Source;
import com.ccm.api.model.sell.SourceI18n;

public class SourceVO extends Source{

	private static final long serialVersionUID = -2219457550991149048L;
	
	private String	sourceMId	;//	序号
	private String	sourceId	;//	市场序号
	private String	language	;//	语言
	private String	description	;//	描述
	private List<SourceI18n> sourceI18nList; //多语言列表
	
	public List<SourceI18n> getSourceI18nList() {
		return sourceI18nList;
	}
	public void setSourceI18nList(List<SourceI18n> sourceI18nList) {
		this.sourceI18nList = sourceI18nList;
	}
	public String getSourceMId() {
		return sourceMId;
	}
	public void setSourceMId(String sourceMId) {
		this.sourceMId = sourceMId;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "SourceI18n [sourceMId=" + sourceMId + ", sourceId=" + sourceId
				+ ", language=" + language + ", description=" + description
				+ "]";
	}
}
