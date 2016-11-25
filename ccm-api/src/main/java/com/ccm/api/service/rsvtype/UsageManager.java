package com.ccm.api.service.rsvtype;

import java.util.List;

import com.ccm.api.model.rsvtype.Usage;
import com.ccm.api.model.rsvtype.vo.UsageCriteria;
import com.ccm.api.model.rsvtype.vo.UsageResult;
import com.ccm.api.service.base.GenericManager;

public interface UsageManager extends GenericManager<Usage, String> {

	/**
	 * 获取查询结果
	 * @param usageCriteria
	 * @return
	 */
    public UsageResult getUsages(UsageCriteria usageCriteria);
     
}
