package com.ccm.api.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccm.api.dao.user.mongodb.AuthRoleDaoMongo;
import com.ccm.api.dao.user.mongodb.AuthUserDaoMongo;
import com.ccm.api.dao.user.mongodb.AuthorityDaoMongo;
import com.ccm.api.model.user.AuthRole;
import com.ccm.api.model.user.AuthUser;
import com.ccm.api.model.user.Authority;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.user.AuthManager;
import com.ccm.api.util.CommonUtil;

/**
 * 功能权限管理类
 */
@Service("authManager")
public class AuthManagerImpl extends GenericManagerImpl<Authority, String> implements AuthManager {

	@Autowired
	private AuthorityDaoMongo authorityDao;
	@Autowired
	private AuthRoleDaoMongo authRoleDao;
	@Autowired
	private AuthUserDaoMongo authUserDao;
	
	@Autowired
	public void setAuthorityDao(AuthorityDaoMongo authorityDao) {
		this.authorityDao = authorityDao;
		this.dao = authorityDao;
	}

	/**
	 * 获取所有功能权限
	 * @return
	 */
	@Override
	public List<Authority> getAllAuth() {
		return authorityDao.getAll();
	}
	
	/**
	 * 保存角色功能权限
	 */
	@Override
	public void saveAuthRole(AuthRole ar) {
		if(null!=ar&&ar.getId()!=null){
			if(authRoleDao.exists(ar.getId())){
				authRoleDao.updateById(ar);
			}else{
				authRoleDao.save(ar);
			}
		}
	}

	@Override
	public void removeAuthRole(String id) {
		authRoleDao.remove(id);
	}
	
	/**
	 * 根据roleId获取其所有权限
	 */
	@Override
	public AuthRole getAuthRole(String id) {	
		return authRoleDao.get(id);
	}

	/**
	 * 保存用户功能权限
	 */
	@Override
	public void saveAuthUser(AuthUser au) {
		if(null!=au&&au.getId()!=null){
			if(authUserDao.exists(au.getId())){
				authUserDao.updateById(au);
			}else{
				authUserDao.save(au);
			}
		}
	}

	@Override
	public AuthUser getAuthUser(String id) {
		return authUserDao.get(id);
	}

	/**
	 * 用户功能权限
	 */
	@Override
	public List<String> getUserAuth(String id) {
		AuthUser au = authUserDao.get(id);
		List<String> auths=new ArrayList<String>();
		if(null!=au&&au.getRoleIds()!=null){
			//获取该用户的所有角色的所有功能权限
			List<String> rids=au.getRoleIds();
			List<String> rAuth=new ArrayList<String>();
			for (String rid : rids) {
				AuthRole ar=authRoleDao.get(rid);
				if(null!=ar){
					rAuth.addAll(ar.getAuthIds());
				}
			}
			//获取用户的所有功能权限
			List<String> uAuth=au.getAuthIds();
			//遍历用户的功能权限，看是否在角色的功能权限存在，
			for (String ua : uAuth) {
				if(rAuth.contains(ua)){
					auths.add(ua);
				}
			}	
		}
		return auths;
	}

	@Override
	public Map<String, List<Authority>> getAuthMap() {
		Map<String, List<Authority>> authMap = new HashMap<String, List<Authority>>();
		List<AuthRole> arList=authRoleDao.getAll();
		for(AuthRole ar:arList){
			if(CommonUtil.isEmpty(authMap.get(ar.getId()))){
				authMap.put(ar.getId(), new ArrayList<Authority>());
			}
			List<String> auids=ar.getAuthIds();
			for(String auid:auids){
				Authority authr=authorityDao.get(auid);
				if(null!=authr){
					authMap.get(ar.getId()).add(authr);
				}
			}
			
		}
		return authMap;
	}

	
	
	
}
