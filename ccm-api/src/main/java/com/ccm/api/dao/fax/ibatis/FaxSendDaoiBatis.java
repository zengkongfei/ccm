package com.ccm.api.dao.fax.ibatis;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.fax.FaxSendDao;
import com.ccm.api.model.fax.FaxSend;
import com.ccm.api.model.fax.vo.FaxSendCriteria;
import com.ccm.api.model.fax.vo.FaxSendVO;

@Repository("faxSendDao")
public class FaxSendDaoiBatis extends GenericDaoiBatis<FaxSend, String> implements FaxSendDao {

	public FaxSendDaoiBatis() {
		super(FaxSend.class);
	}

	@Override
	public void saveFaxSend(FaxSend faxSend) {
		getSqlMapClientTemplate().insert("saveFaxSend",faxSend);
	}

	@Override
	public Integer getCount(FaxSendCriteria fsc) {
		int count = (Integer) getSqlMapClientTemplate().queryForObject("getFaxSendCount", fsc);
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaxSendVO> getList(FaxSendCriteria fsc) {
		List<FaxSendVO> list = getSqlMapClientTemplate().queryForList("getFaxSendList", fsc);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaxSendVO> getExcelList(FaxSendCriteria fsc) {
		List<FaxSendVO> list = getSqlMapClientTemplate().queryForList("getFaxSendExcelList", fsc);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaxSend> getFaxSendByTime(Date sendTime) {
		return getSqlMapClientTemplate().queryForList("getFaxSendByTime", sendTime);
	}

}
