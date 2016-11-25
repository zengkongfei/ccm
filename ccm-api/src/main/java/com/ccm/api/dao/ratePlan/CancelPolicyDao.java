package com.ccm.api.dao.ratePlan;

import java.util.List;
import java.util.Map;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.ratePlan.CancelPolicy;
import com.ccm.api.model.ratePlan.CancelPolicyI18n;
import com.ccm.api.model.ratePlan.vo.CancelPolicyCriteria;
import com.ccm.api.model.ratePlan.vo.CancelPolicyVO;

public interface CancelPolicyDao extends GenericDao<CancelPolicy, String> {

	CancelPolicy addCancelPolicy(CancelPolicy cancelPolicy);

	CancelPolicyI18n addCancelPolicyI18n(CancelPolicyI18n cancelPolicyI18n);

	void updateCancelPolicy(CancelPolicy cancelPolicy);

	void updateCancelPolicyI18n(CancelPolicyI18n cancelPolicyI18n);

	void deleteCancelPolicy(CancelPolicy cancelPolicy);

	void deleteCancelPolicyI18n(CancelPolicyI18n cancelPolicyI18n);

	CancelPolicyVO getCancelPolicyByCode(String cancelPolicyCode);

	CancelPolicyVO getCancelPolicyByCode(String cancelPolicyCode, String language);

	CancelPolicyVO getCancelPolicyById(String cancelId);

	List<CancelPolicyVO> searchCancelPolicy(CancelPolicyCriteria criteria);

	Integer searchCancelPolicyCount(CancelPolicyCriteria criteria);

	CancelPolicyVO getCancelPolicyById(String cancelId, String language);

	void deleteCancelPolicyI18nByCancelId(String cancelId);

	List<CancelPolicyI18n> getCancelPolicyI18ns(String cancelId);

	List<CancelPolicyVO> getAllCancelPolicys();

	List<CancelPolicyVO> getAllCancelPolicys(String language);

	List<CancelPolicy> getRateCancelByRatePlanId(String ratePlanId);

	List<CancelPolicyVO> getRateCancelI18nByRatePlanId(String ratePlanId, String language);

	List<CancelPolicyVO> getRateCancelI18nByCondition(Map<String,Object>params);
}
