package com.ccm.api.dao.fax.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.fax.FaxSendTimeDao;
import com.ccm.api.model.fax.FaxSendTime;

@Repository("faxSendTimeDao")
public class FaxSendTimeDaoiBatis extends GenericDaoiBatis<FaxSendTime, String> implements FaxSendTimeDao {

	public FaxSendTimeDaoiBatis() {
		super(FaxSendTime.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaxSendTime> searchFaxSendTimeList(FaxSendTime faxSendTime) {
		return getSqlMapClientTemplate().queryForList("searchFaxSendTimeList", faxSendTime);
	}

	

}
