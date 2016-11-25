package com.ccm.api.dao.rsvtype;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.rsvtype.Usage;
import com.ccm.api.model.rsvtype.vo.UsageCriteria;

public interface UsageDao extends GenericDao<Usage, String> {

	/**
	 * 获取查询结果
	 * @param usageCriteria
	 * @return
	 */
    public List<Usage> getUsages(UsageCriteria usageCriteria);
    
    /**
	 * 获取查询结果数
	 * @param usageCriteria
	 * @return
	 */
    public Integer getUsagesCount(UsageCriteria usageCriteria);
    
    /**
	 * 获取记录和
	 * @param usageCriteria
	 * @return
	 */
    public Usage getUsagesSum(UsageCriteria usageCriteria);
    
}
