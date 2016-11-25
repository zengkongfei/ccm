package com.ccm.api.service.fax;

import java.util.List;

import com.ccm.api.model.fax.FaxSendTime;
import com.ccm.api.service.base.GenericManager;

public interface FaxSendTimeManager extends GenericManager<FaxSendTime, String>{
	/**
	 * 查询传真时间集合
	 * @param faxSendTime
	 * @return
	 */
	List<FaxSendTime> searchFaxSendTimeList(FaxSendTime faxSendTime);
	
	public void saveOrUpdateFaxSendTime(List<FaxSendTime> list);
}
