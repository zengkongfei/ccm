/**
 * 
 */
package com.ccm.api.dao.cfg.iBatis;

import org.springframework.stereotype.Repository;

import com.ccm.api.dao.base.ibatis.GenericDaoiBatis;
import com.ccm.api.dao.cfg.SysCfgDao;
import com.ccm.api.model.cfg.SysCfg;

/**
 * 
 */
@Repository("sysCfgDao")
public class SysCfgDaoibatis extends GenericDaoiBatis<SysCfg, String> implements SysCfgDao {

	public SysCfgDaoibatis() {
		super(SysCfg.class);
	}
}
