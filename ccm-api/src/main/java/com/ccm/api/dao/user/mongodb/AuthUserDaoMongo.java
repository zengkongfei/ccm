package com.ccm.api.dao.user.mongodb;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ccm.api.dao.base.mongodb.MongodbBaseDao;
import com.ccm.api.model.user.AuthRole;
import com.ccm.api.model.user.AuthUser;
import com.ccm.api.model.user.Authority;
import com.ccm.api.util.CommonUtil;

@Repository("authUserDao")
public class AuthUserDaoMongo extends MongodbBaseDao<AuthUser> {
	protected Class<AuthUser> getEntityClass(){
		return AuthUser.class;
	}
	
}
