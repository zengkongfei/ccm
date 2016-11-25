package com.ccm.api.dao.ratePlan;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.GuaranteePolicyI18n;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyCriteria;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;

public interface GuaranteePolicyDao extends GenericDao<GuaranteePolicy, String> {

	GuaranteePolicy addGuaranteePolicy(GuaranteePolicy guaranteePolicy);

	GuaranteePolicyI18n addGuaranteePolicyI18n(GuaranteePolicyI18n guaranteePolicyI18n);

	void updateGuaranteePolicy(GuaranteePolicy guaranteePolicy);

	void updateGuaranteePolicyI18n(GuaranteePolicyI18n guaranteePolicyI18n);

	void deleteGuaranteePolicy(GuaranteePolicy guaranteePolicy);

	void deleteGuaranteePolicyI18n(GuaranteePolicyI18n guaranteePolicyI18n);

	GuaranteePolicyVO getGuaranteePolicyByCode(String guaranteeCode);

	GuaranteePolicyVO getGuaranteePolicyById(String guaranteeId);

	List<GuaranteePolicyVO> searchGuaranteePolicy(GuaranteePolicyCriteria criteria);

	Integer searchGuaranteePolicyCount(GuaranteePolicyCriteria criteria);

	GuaranteePolicyVO getGuaranteePolicyById(String guaranteeId, String language);

	void deleteGuaranteePolicyI18nByGuaranteeId(String guaranteeId);

	GuaranteePolicy getGuaranteeByCode(String guaranteeCode);

	List<GuaranteePolicyI18n> getGuaranteePolicyI18ns(String guaranteeId);

	GuaranteePolicyVO getGuaranteePolicyByCode(String guaranteeCode, String language);

	/**
	 * 根据房价IDS查询担保规则表
	 * 
	 * @param ratePlanIds
	 * @return
	 */
	List<GuaranteePolicy> getGuaranteePolicyByRatePlanIds(List<String> ratePlanIds);

	List<GuaranteePolicy> getGuaranteePolicyByRatePlanId(String ratePlanId);

	List<GuaranteePolicyVO> getAllGuaranteePolicys();

	List<GuaranteePolicyVO> getAllGuaranteePolicys(String language);

	List<GuaranteePolicyVO> getGuaranteePolicyI18nByRatePlanId(String ratePlanId, String language);

}
