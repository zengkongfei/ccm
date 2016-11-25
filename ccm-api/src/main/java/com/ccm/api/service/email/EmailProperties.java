/**
 * 
 */
package com.ccm.api.service.email;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author Jenny Liao
 * 
 */
public class EmailProperties {

	private transient static Logger log = Logger.getLogger(EmailProperties.class);

	private static ResourceBundle resource = ResourceBundle.getBundle("mail");

	/**
	 * 
	 * @param key
	 * @return
	 */
	static public String getProperty(String key) {
		return resource.getString(key);
	}

	public static String getExceptionMonitorTo() {
		return getProperty("exceptionMonitorEmail.to");
	}

	public static String getExceptionMonitorAppSubject() {
		return getProperty("exceptionMonitorApp") + "系统异常错误通知";
	}

}
