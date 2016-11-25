package com.ccm.api.dao.user.mongodb;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.model.user.Authority;
import com.ccm.api.util.CommonUtil;

@Repository("authorityDao")
public class AuthorityDaoMongo extends MongodbBaseDao<Authority> {
	protected Class<Authority> getEntityClass(){
		return Authority.class;
	}
	
	public List<Authority> getAuthByIds(List<String> ids){
		Query query = new Query();
		if(CommonUtil.isNotEmpty(ids)){
			query.addCriteria(Criteria.where("id").in(ids));
		}	
		return find(query);
	}
}
