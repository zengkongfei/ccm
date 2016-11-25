package com.ccm.api.service.ratePlan;

import java.util.List;

import com.ccm.api.model.ratePlan.GuaranteePolicy;
import com.ccm.api.model.ratePlan.GuaranteePolicyI18n;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyCriteria;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyResult;
import com.ccm.api.model.ratePlan.vo.GuaranteePolicyVO;
import com.ccm.api.service.base.GenericManager;

public interface GuaranteePolicyManager extends GenericManager<GuaranteePolicy, String> {

	/**
	 * 新增担保规则
	 */
	void saveGuaranteePolicy(GuaranteePolicyVO vo);

	/**
	 * 修改担保规则
	 */
	void updateGuaranteePolicy(GuaranteePolicyVO vo);

	/**
	 * 删除担保规则
	 */
	void deleteGuaranteePolicy(GuaranteePolicyVO vo);

	/**
	 * 根据担保规则代码取担保规则信息
	 */
	GuaranteePolicyVO getGuaranteePolicyByCode(String guaranteeCode);

	/**
	 * 根据担保规则代码和语言代码取担保规则信息
	 */
	GuaranteePolicyVO getGuaranteePolicyByCode(String guaranteeCode, String language);

	/**
	 * 根据担保规则ID取担保规则信息
	 */
	GuaranteePolicyVO getGuaranteePolicyById(String guaranteeId);

	/**
	 * 获取担保规则
	 * 
	 * @param guaranteeId
	 * @param language
	 * @return
	 */
	GuaranteePolicyVO getGuaranteePolicyById(String guaranteeId, String language);

	/**
	 * 根据条件取担保规则信息
	 */
	GuaranteePolicyResult searchGuaranteePolicy(GuaranteePolicyCriteria criteria);

	/**
	 * 删除担保规则多语言
	 * 
	 * @param guaranteeId
	 */
	void deleteGuaranteePolicyI18nByGuaranteeId(String guaranteeId);

	/**
	 * 获取规则多语言列表
	 * 
	 * @param guaranteeId
	 * @return
	 */
	List<GuaranteePolicyI18n> getGuaranteePolicyI18ns(String guaranteeId);

	GuaranteePolicyI18n getDefaultLanguageI18n(GuaranteePolicyVO vo);

	List<GuaranteePolicyVO> getAllGuaranteePolicys();

	List<GuaranteePolicyVO> getAllGuaranteePolicys(String language);

	List<GuaranteePolicyVO> getGuaranteePolicyByRatePlanId(String ratePlanId, String language);

}
