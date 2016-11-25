package com.ccm.api.dao.user.mongodb;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.model.user.AuthRole;
import com.ccm.api.util.CommonUtil;

@Repository("authRoleDao")
public class AuthRoleDaoMongo extends MongodbBaseDao<AuthRole> {
	protected Class<AuthRole> getEntityClass(){
		return AuthRole.class;
	}
	
	public void remove(String id){
		Query query=new Query();
		if(CommonUtil.isNotEmpty(id)){
			query.addCriteria(Criteria.where("id").in(id));
			this.remove(query);
		}
	}
}
