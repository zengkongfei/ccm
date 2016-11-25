package com.ccm.api;

import org.apache.commons.lang.StringUtils;

import com.ccm.api.common.exception.BizException;


public class ValidationChecker {
    
    public static void notEmpty(String... values) throws RuntimeException {
        for (String value : values) {
            if (StringUtils.isEmpty(value)) {
                throw new BizException("Parameter is empty");
            }
        }
    }
    
    public static void notNull(Object... values) throws RuntimeException {
        for (Object value : values) {
            if (value == null) {
                throw new BizException("Parameter is null");
            }
        }
    }

}
