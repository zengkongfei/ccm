package com.ccm.api.model.sell;

import com.ccm.api.model.base.BaseObject;

/**
 * 来源（多语言）
 * @author carr
 *
 */
public class SourceI18n extends BaseObject {

	private static final long serialVersionUID = -268963255998582553L;

	private String	sourceMId	;//	序号
	private String	sourceId	;//	来源序号
	private String	language	;//	语言
	private String	description	;//	描述
	
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
