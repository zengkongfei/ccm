package com.ccm.api.dao.fax;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.fax.FaxSendTime;

public interface FaxSendTimeDao extends GenericDao<FaxSendTime, String>{
	
	/**
	 * 查询传真时间集合
	 * @param faxSendTime
	 * @return
	 */
	List<FaxSendTime> searchFaxSendTimeList(FaxSendTime faxSendTime);
}
