package com.ccm.api.service.fax;

import java.util.Date;
import java.util.List;

import com.ccm.api.model.fax.FaxSend;
import com.ccm.api.model.fax.vo.FaxSendCriteria;
import com.ccm.api.model.fax.vo.FaxSendSearchResult;
import com.ccm.api.service.base.GenericManager;

public interface FaxSendManager extends GenericManager<FaxSend, String>{
	FaxSendSearchResult searchFaxSendVO(FaxSendCriteria fsc);
	/**
	 * 发送时间大于sendTime 的fax
	 * @param sendTime
	 * @return
	 */
	public List<FaxSend> getFaxSendByTime(Date sendTime);
}
