package com.ccm.api.dao.master;

import java.util.List;

import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.order.MasterPms;

public interface MasterPmsDao extends GenericDao<MasterPms, String> {

	public void saveMasterPms2(MasterPms m);
	public void deleteMasterPms(String masterId);
	public List<MasterPms> getAllMasterPms();
}
