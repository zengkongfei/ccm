package com.ccm.api.service.security;

import java.util.Collection;

import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 决策类 当请求访问时，判断用户是否具有访问所需的所有权限
 */

public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {
	org.apache.commons.logging.Log log = LogFactory.getLog(AccessDecisionManager.class);

	/**
	 * authentication用户认证后 存有用户的所有权限 configAttributes访问所需要的权限 若无权则抛出异常
	 */
	public void decide(Authentication authentication, Object arg1, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

		if (null == configAttributes) {
			return;
		}
		for(GrantedAuthority gAuthority : authentication.getAuthorities()){  
            if("ROLE_ADMIN".equals(gAuthority.getAuthority().trim()) ){  
                return;  
            }
        } 
		

		for (ConfigAttribute configAttribute : configAttributes) {
			for (GrantedAuthority gAuthority : authentication.getAuthorities()) {
				if (configAttribute.getAttribute().trim().equals(gAuthority.getAuthority().trim())) {
					return;
				}
			}
		}
		// 无权限抛出拒绝异常
		throw new AccessDeniedException("");
	}

	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

}
