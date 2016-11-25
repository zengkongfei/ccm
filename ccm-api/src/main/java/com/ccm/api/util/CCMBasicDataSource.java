package com.ccm.api.util;

import org.apache.commons.dbcp.BasicDataSource;


public class CCMBasicDataSource extends BasicDataSource {
	@Override
	public void setPassword(String password) {
		String exceptionMonitorApp = PropertiesUtil.getProperty("exceptionMonitorApp");
		if("CcmMC".equals(exceptionMonitorApp)
    			|| "CcmLocalMC".equals(exceptionMonitorApp)
    			|| "CcmTestMC".equals(exceptionMonitorApp)){
			super.setPassword(AesEncrypt.decrypt(password));
    	}else{
    		super.setPassword(password);
    	}
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	
}
