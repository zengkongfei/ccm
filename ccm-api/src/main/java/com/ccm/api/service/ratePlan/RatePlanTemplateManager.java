package com.ccm.api.service.ratePlan;

import java.util.List;

import com.ccm.api.model.ratePlan.RatePlanTemplate;
import com.ccm.api.model.ratePlan.RatePlanTemplateI18n;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateCreteria;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateResult;
import com.ccm.api.model.ratePlan.vo.RatePlanTemplateVO;
import com.ccm.api.service.base.GenericManager;

public interface RatePlanTemplateManager extends GenericManager<RatePlanTemplate, String> {

	RatePlanTemplateVO saveRatePlanTemplate(RatePlanTemplateVO vo);

	void updateRoomTypeTempldate(RatePlanTemplateVO vo);

	void deleteRoomTypeTempldate(RatePlanTemplateVO vo);
	
	RatePlanTemplateVO getRatePlanTemplateById(String roomTypeTempId);
	
	RatePlanTemplateVO getRatePlanTemplateByCode(String ratePlanTemplateCode);

	RatePlanTemplateVO getRatePlanTemplateById(String ratePlanTemplateId,
			String language);

	RatePlanTemplateVO getRatePlanTemplateByCode(String ratePlanTemplateCode,
			String language);

	List<RatePlanTemplateI18n> getRatePlanTemplateI18ns(
			String ratePlanTemplateId);
	
	List<RatePlanTemplateI18n> getRatePlanTemplateI18ns2(
			String ratePlanTemplateCode);

	List<RatePlanTemplateVO> getAllRatePlanTemplate(String language);
	
	List<RatePlanTemplateVO> getAllRatePlanTemplate();

	RatePlanTemplateResult searchRatePlanTemplate(
			RatePlanTemplateCreteria creteria);

	RatePlanTemplateI18n getDefaultLanguageI18n(RatePlanTemplateVO vo);
	
	List<RatePlanTemplateVO> getDontUseRatePlanTemplate(String hotelId);

	List<RatePlanTemplateVO> getDontUseRatePlanTemplate(String hotelId,
			String language);

}
