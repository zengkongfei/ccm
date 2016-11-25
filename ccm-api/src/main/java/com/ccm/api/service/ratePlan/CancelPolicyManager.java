package com.ccm.api.service.ratePlan;

import java.util.List;

import com.ccm.api.model.ratePlan.CancelPolicy;
import com.ccm.api.model.ratePlan.CancelPolicyI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyCriteria;
import com.ccm.api.model.ratePlan.vo.CancelPolicyResult;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;
import com.ccm.api.service.base.GenericManager;

public interface CancelPolicyManager extends GenericManager<CancelPolicy, String> {

	/**
	 * 新增取消规则
	 */
	void saveCancelPolicy(CancelPolicyVO vo);

	/**
	 * 修改取消规则
	 */
	void updateCancelPolicy(CancelPolicyVO vo);

	/**
	 * 删除取消规则
	 */
	void deleteCancelPolicy(CancelPolicyVO vo);

	/**
	 * 根据取消规则代码取取消规则信息
	 */
	CancelPolicyVO getCancelPolicyByCode(String cancelPolicyCode);

	/**
	 * 根据取消规则代码和语种取取消规则信息
	 */
	CancelPolicyVO getCancelPolicyByCode(String cancelPolicyCode, String language);

	/**
	 * 根据取消规则ID取取消规则信息
	 */
	CancelPolicyVO getCancelPolicyById(String cancelId);

	/**
	 * 根据取消规则ID和语种获取取取消规则信息
	 */
	CancelPolicyVO getCancelPolicyById(String cancelId, String language);

	/**
	 * 根据条件取取消规则信息
	 */
	CancelPolicyResult searchCancelPolicy(CancelPolicyCriteria criteria);

	/**
	 * 删除取消规则多语言列表
	 * 
	 * @param cancelId
	 */
	void deleteCancelPolicyI18nByCancelId(String cancelId);

	/**
	 * 获取取消规则列表
	 * 
	 * @param cancelId
	 * @return
	 */
	List<CancelPolicyI18n> getCancelPolicyI18ns(String cancelId);

	/**
	 * 获取默认语言的取消规则对象
	 * 
	 * @param vo
	 * @return
	 */
	CancelPolicyI18n getDefaultLanguageI18n(CancelPolicyVO vo);

	List<CancelPolicyVO> getAllCancelPolicys();

	List<CancelPolicyVO> getAllCancelPolicys(String language);

	List<CancelPolicy> getRateCancelByRatePlanId(String ratePlanId);

	List<CancelPolicyVO> getRateCancelI18nByRatePlanId(String ratePlanId, String language);

}
