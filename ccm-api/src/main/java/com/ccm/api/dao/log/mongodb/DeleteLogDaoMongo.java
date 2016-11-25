package com.ccm.api.dao.log.mongodb;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.dao.log.DeleteLogDao;
import com.ccm.api.model.log.DeleteLog;

@Repository("deleteLogDaoMongo")
public class DeleteLogDaoMongo extends MongodbBaseDao<DeleteLog> implements DeleteLogDao {


	@Override
	public void saveDeleteLog(DeleteLog deleteLog) {
		
		this.save(deleteLog);
		
	}
	
}
