package com.ccm.api.model.ratePlan;

import com.ccm.api.model.base.BaseObject;

/**
 * 房价(多语言)
 * 
 * @author carr
 * 
 */
public class RatePlanI18n extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7771820125696530102L;

	private String ratePlanMId;// 序号
	private String ratePlanId;// 房价序号
	private String language;// 语言
	private String ratePlanName;// 房价名称
	private String description;// 描述
	private String ratePlanNameEn;// 房价名称
	private String descriptionEn;// 描述
	private String englishName;// 英文名称

	public String getRatePlanNameEn() {
		return ratePlanNameEn;
	}

	public void setRatePlanNameEn(String ratePlanNameEn) {
		this.ratePlanNameEn = ratePlanNameEn;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getRatePlanMId() {
		return ratePlanMId;
	}

	public void setRatePlanMId(String ratePlanMId) {
		this.ratePlanMId = ratePlanMId;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRatePlanName() {
		return ratePlanName;
	}

	public void setRatePlanName(String ratePlanName) {
		this.ratePlanName = ratePlanName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@Override
	public String toString() {
		return "RatePlanI18n [ratePlanMId=" + ratePlanMId + ", ratePlanId=" + ratePlanId + ", language=" + language + ", ratePlanName=" + ratePlanName + ", description=" + description + ", englishName=" + englishName + "]";
	}
}
