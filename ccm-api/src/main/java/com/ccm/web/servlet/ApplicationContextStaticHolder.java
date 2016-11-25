package com.ccm.web.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.context.ApplicationContext;

/**
 * @author Jenny Liao
 *
 */
public class ApplicationContextStaticHolder {
    
    private static ApplicationContext ctx = null;    
    
    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (applicationContext == null) throw new RuntimeException("ctx is null");
        ctx = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
    
    public static  <T>T getBean(Class<T> cls) {
        if (ctx == null) throw new RuntimeException("ctx not initialized");
        return (T)ctx.getBean(cls);
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException{
    	System.out.println(new String("ddd你好".getBytes()));
    	System.out.println(new String("ddd你好".getBytes("ISO8859-1"), "GBK"));
    	System.out.println(URLEncoder.encode("ddd好","UTF-8"));
    	System.out.println(URLEncoder.encode("ddd好","GBK"));
    }
    

}
