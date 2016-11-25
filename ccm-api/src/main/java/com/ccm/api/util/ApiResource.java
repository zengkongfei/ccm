/**
 * 
 */
package com.ccm.api.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author Jenny Liao
 *
 */
public class ApiResource {
	
    private transient static Logger log = Logger.getLogger(ApiResource.class);
	
    private static ResourceBundle resource = ResourceBundle.getBundle("apiResource", Locale.SIMPLIFIED_CHINESE);
   
    /**
     * 
     * @param key
     * @return
     */
    static public String getProperty(String key) {
    	return resource.getString(key);
    }
}
