package com.ccm.api.dao.ratePlan;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ratePlan.RatePlanTemplate;
import com.ccm.api.model.ratePlan.RatePlanTemplateI18n;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateCreteria;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateVO;


public interface RatePlanTemplateDao extends GenericDao<RatePlanTemplate, String>{

	RatePlanTemplate addRatePlanTemplate(RatePlanTemplate ratePlanTemp);
	
	RatePlanTemplateI18n addRatePlanTemplateI18n(RatePlanTemplateI18n ratePlanTempI18n);
	
	void deleteRatePlanTemplate(RatePlanTemplate ratePlanTemp);
	
	RatePlanTemplateVO getRatePlanTemplateById(String ratePlanTempId);
	
	List<RatePlanTemplateVO> searchRatePlanTemplate(RatePlanTemplateCreteria creteria);

	List<RatePlanTemplateVO> getAllRatePlanTemplate(String language);

	void updateRatePlanTemplate(RatePlanTemplate ratePlanTemp);

	void updateRatePlanTemplateI18n(RatePlanTemplateI18n ratePlanTempI18n);

	void deleteRatePlanTemplateI18n(RatePlanTemplateI18n ratePlanTempI18n);

	void deleteRatePlanTemplateI18nByTempId(String ratePlanTempId);

	RatePlanTemplateVO getRatePlanTemplateByCode(String ratePlanTemplateCode);

	RatePlanTemplateVO getRatePlanTemplateById(String ratePlanTemplateId,
			String language);

	RatePlanTemplateVO getRatePlanTemplateByCode(String ratePlanTemplateCode,
			String language);

	Integer searchRatePlanTemplateCount(RatePlanTemplateCreteria creteria);

	List<RatePlanTemplateI18n> getRatePlanTemplateI18ns(
			String ratePlanTemplateId);
	
	List<RatePlanTemplateI18n> getRatePlanTemplateI18ns2(
			String ratePlanTemplateCode);

	List<RatePlanTemplateVO> getAllRatePlanTemplate();

	List<RatePlanTemplateVO> getDontUseRatePlanTemplate(String hotelId);

	List<RatePlanTemplateVO> getDontUseRatePlanTemplate(String hotelId,
			String language);
	
}
