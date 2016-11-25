package com.ccm.api.management;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.ccm.api.AppConfig;

@ManagedResource(objectName="bean:name=ConfigManagement")
public class ConfigManagement {
	
	public final transient Log log = LogFactory.getLog(getClass());
	
	@ManagedOperation
	public void setProperty(String key, String value) {
		log.info("key:" + key + " value:" + value);
		AppConfig.setString(key, value);
	}
	
	@ManagedOperation
	public String getProperty(String key) {
		String value = AppConfig.getString(key);
		log.info("key:" + key + " value:" + value);
		return value;
	}
}
