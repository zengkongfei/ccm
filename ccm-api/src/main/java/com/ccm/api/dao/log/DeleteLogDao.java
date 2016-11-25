package com.ccm.api.dao.log;

import com.ccm.api.model.log.DeleteLog;

public interface DeleteLogDao {
	void saveDeleteLog(DeleteLog deleteLog);
}
