package com.ccm.api;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccm.api.util.PropertiesUtil;

public class AppConfig {

    private static final Log log = LogFactory.getLog(AppConfig.class);

    public static String getApiName() {
    	
        return "api";
    }

    public static String getB2bName() {
        return "b2b";
    }

    public static String getB2cName() {
        return "b2c";
    }
    
    public static boolean isEnabledMemCache() {
        if (isDev()) {
            return false;
        }
        return Boolean.valueOf(getString("cache.isuse"));
    }

    public static boolean isDev() {
        return Boolean.valueOf(getString("ccm.dev"));
    }

    public static boolean isQA() {
        return Boolean.valueOf(getString("ccm.qa"));
    }

    public static boolean isProduct() {
        return Boolean.valueOf(getString("ccm.product"));
    }

    public static boolean isEanble(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        return Boolean.valueOf(System.getProperty(key));
    }

    public static String getB2cBaseUrl() {
        if (isDev()) {
            return "/";
        } else if (isQA()) {
            return "http://wwwtest.ccm.com";
        } else if (isProduct()) {
            return "http://www.ccm.com";
        }
        return "/";
    }

    public static String getString(String key) {
        if (PropertiesUtil.getProperty(key) == null) {
            return String.valueOf(System.getProperty(key, key));
        } else {
            return PropertiesUtil.getProperty(key);
        }
    }
    
    public static void setString(String key, String value) {
        if (PropertiesUtil.getProperty(key) == null) {
            System.setProperty(key, value);
        } else {
            PropertiesUtil.setProperty(key, value);
        } 
    }
    
    public static String getString(String key, String defaultValue) {
        if (PropertiesUtil.getProperty(key) == null) {
            return String.valueOf(System.getProperty(key, defaultValue));
        } else {
            return PropertiesUtil.getProperty(key);
        }
    }
    
    public static Integer getInteger(String key, Integer defaultValue) {
        return Integer.valueOf(getString(key, String.valueOf(defaultValue)));
    }
}
