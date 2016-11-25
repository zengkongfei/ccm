package com.ccm.api.dao.user;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.ccm.api.dao.base.BaseDaoTestCase;
import com.ccm.api.dao.user.mongodb.AuthRoleDaoMongo;
import com.ccm.api.dao.user.mongodb.AuthUserDaoMongo;
import com.ccm.api.dao.user.mongodb.AuthorityDaoMongo;
import com.ccm.api.model.user.AuthRole;
import com.ccm.api.model.user.Authority;
import com.ccm.api.service.user.AuthManager;
import com.ccm.api.service.user.UserManager;

public class AuthDaoTest extends BaseDaoTestCase {
	@Autowired
	private AuthorityDaoMongo authorityDao;
	@Autowired
	private AuthRoleDaoMongo authRoleDao;
	@Autowired
	private AuthUserDaoMongo authUserDao;
	@Autowired
	private UserManager userManager;
	@Autowired
	private AuthManager authManager;
	@Test
	public void test() throws Exception {
		//================================================================
		Authority auth=new Authority();
		auth.setId("101");
		auth.setAuthNameCN("订单编辑");
		auth.setAuthNameEN("Reservation Authority");
		auth.setMenu("71");
		//authManager.save(auth);
		//List<Authority> auths=authorityDao.getAll();
		List<Authority> auths=authManager.getAllAuth();
		if(null!=auths){
			for (Authority authority : auths) {
				System.out.println(authority);
			}
		}
		
		//================================================================
		
		//================================================================
		AuthRole ar= new AuthRole();
	    ar.setId("2");
	    List<String> authIds=new ArrayList<>();
	    authIds.add("220");
	    authIds.add("230");
	    ar.setAuthIds(authIds);
	    
		//authRoleDao.save(ar);
		
		//System.out.println(authRoleDao.findById("2", AuthRole.class));

		//================================================================
		Authority au=new Authority();
		au.setId("2");
		au.setAuthNameCN("n4");
		//更新或新增
		//authorityDao.saveOrUpdateById(au);
		//获取所有
		List<Authority> aus=authorityDao.getAll();
		for (Authority authority : aus) {
			//System.out.println(authority);
		}
		//authorityDao.remove(query);
		//authorityDao.find(new Query())
		List<String> ids=new ArrayList<>();
		ids.add("2");
		aus=authorityDao.getAuthByIds(ids);
		for (Authority authority : aus) {
			//System.out.println(authority);
		}
	}

}
