/**
 * 
 */
package com.ccm.api.service.cfg.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccm.api.dao.cfg.SysCfgDao;
import com.ccm.api.model.cfg.SysCfg;
import com.ccm.api.service.base.impl.GenericManagerImpl;
import com.ccm.api.service.cfg.SysCfgManager;


/**
 * @author Water
 * 
 */
@Service("sysCfgManager")
public class SysCfgManagerImpl extends GenericManagerImpl<SysCfg, String> implements SysCfgManager {
	@Resource
	private SysCfgDao sysCfgDao;
	@Resource
	public void setSysCfgDao(SysCfgDao sysCfgDao) {
		this.sysCfgDao = sysCfgDao;
		this.dao = sysCfgDao;
	}
}

	

	
