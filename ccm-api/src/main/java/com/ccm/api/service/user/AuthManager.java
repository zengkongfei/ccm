package com.ccm.api.service.user;

import java.util.List;
import java.util.Map;

import com.ccm.api.model.base.vo.MenuTreeVO;
import com.ccm.api.model.common.Menu;
import com.ccm.api.model.role.RoleCriteria;
import com.ccm.api.model.role.RoleSearchResult;
import com.ccm.api.model.user.AuthRole;
import com.ccm.api.model.user.AuthUser;
import com.ccm.api.model.user.Authority;
import com.ccm.api.model.user.Role;
import com.ccm.api.model.user.RoleChannel;
import com.ccm.api.model.user.RoleMenu;
import com.ccm.api.model.user.criteria.B2BUserCriteria;
import com.ccm.api.model.user.vo.B2BUserSearchResult;
import com.ccm.api.service.base.GenericManager;

public interface AuthManager extends GenericManager<Authority, String> {
	//功能权限
	List<Authority> getAllAuth();
	
	void saveAuthRole(AuthRole ar);	
	AuthRole getAuthRole(String id);
	void removeAuthRole(String id);
	
	void saveAuthUser(AuthUser au);	
	AuthUser getAuthUser(String id);
	
	//获取某用户的所有功能权限
	List<String> getUserAuth(String id);
	
	//
	Map<String, List<Authority>> getAuthMap();
}
