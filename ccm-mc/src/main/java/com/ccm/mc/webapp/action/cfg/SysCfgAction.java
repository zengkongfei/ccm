package com.ccm.mc.webapp.action.cfg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ccm.api.model.cfg.SysCfg;
import com.ccm.api.service.cfg.SysCfgManager;
import com.ccm.api.util.IncrementService;
import com.ccm.mc.webapp.action.base.BaseAction;

/**
 * 
 * 系统配置
 */
public class SysCfgAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3763070066876730483L;

	@Autowired
	private SysCfgManager sysCfgManager;

	private SysCfg sysCfg=new SysCfg();

	public String list() {
		List<SysCfg> all=sysCfgManager.getAll();
		if(null!=all&&all.size()>0){
			sysCfg=all.get(0);
		}
		
		return "list";
	}

	public String saveSuccess() {
		List<SysCfg> all=sysCfgManager.getAll();
		
		SysCfg temp=new SysCfg();
		if(null!=all&&all.size()>0){
			temp=all.get(0);
			if(temp!=null && StringUtils.hasText(temp.getCcmSysCfgId())){
				sysCfg.setCcmSysCfgId(temp.getCcmSysCfgId());
			}
		}
		
		sysCfgManager.save(sysCfg);
		if(sysCfg.getIsInterfaceListener()!=temp.getIsInterfaceListener()
				||sysCfg.getIsMasterLister()!=temp.getIsMasterLister()
				||sysCfg.getMasterListener()!=temp.getMasterListener()
				||sysCfg.getInterfaceListener()!=temp.getInterfaceListener()
				||sysCfg.getMasterDealTime()!=temp.getMasterDealTime()
				||sysCfg.getTimeArr()!=temp.getTimeArr()
		){
			IncrementService.refreshJob();
		}
		
		
		return "list";
	}

	public SysCfgManager getSysCfgManager() {
		return sysCfgManager;
	}

	public void setSysCfgManager(SysCfgManager sysCfgManager) {
		this.sysCfgManager = sysCfgManager;
	}

	public SysCfg getSysCfg() {
		return sysCfg;
	}

	public void setSysCfg(SysCfg sysCfg) {
		this.sysCfg = sysCfg;
	}

}
