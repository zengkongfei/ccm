package com.ccm.api.dao.master.ibatis;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.master.MasterPmsDao;
import com.ccm.api.model.order.MasterPms;

@Repository("masterPmsDao")
public class MasterPmsDaoibatis extends GenericDaoiBatis<MasterPms, String> implements MasterPmsDao {

	public MasterPmsDaoibatis() {
		super(MasterPms.class);
	}
	

	@Override
	public void deleteMasterPms(String masterId) {
		getSqlMapClientTemplate().delete("deleteMasterPms2",masterId);
	}


	@Override
	public void saveMasterPms2(MasterPms m) {
		m.setCreatedTime(new Date());
		getSqlMapClientTemplate().insert("saveMasterPms2",m);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MasterPms> getAllMasterPms() {
		return getSqlMapClientTemplate().queryForList("getAllMasterPms2");
	}
}
