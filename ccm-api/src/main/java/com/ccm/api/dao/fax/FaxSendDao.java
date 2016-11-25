package com.ccm.api.dao.fax;

import java.util.Date;
import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.fax.FaxSend;
import com.ccm.api.model.fax.vo.FaxSendCriteria;
import com.ccm.api.model.fax.vo.FaxSendVO;

public interface FaxSendDao extends GenericDao<FaxSend, String>{
	public void saveFaxSend(FaxSend faxSend);
	
	public Integer getCount(FaxSendCriteria fsc);
	
	public List<FaxSendVO> getList(FaxSendCriteria fsc);

	public List<FaxSendVO> getExcelList(FaxSendCriteria fsc);

	/**
	 * 发送时间大于sendTime 的fax
	 * @param sendTime
	 * @return
	 */
	public List<FaxSend> getFaxSendByTime(Date sendTime);
}
