
package com.ccm.api.service.security;

import java.text.ParseException;
import java.util.Date;

import org.springframework.security.authentication.encoding.BaseDigestPasswordEncoder;

import com.ccm.api.util.DateUtil;
import com.ccm.api.util.SHA256Util;


public class DoubleMD5AndSaltPasswordEncoder extends BaseDigestPasswordEncoder {


    public String encodePassword(String rawPass, Object salt) {
    	
    	
    	
    	
//    	String md5  = MD5Util.encode(rawPass);
//		md5 =  md5+ salt;
//		String resultP=  MD5Util.encode(md5.toUpperCase());
		
		return SHA256Util.SHA256Encrypt(rawPass, (String)salt);
       
//    	return resultP.toUpperCase();
    }

  
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        String pass1 = "" + encPass;
        String pass2 = encodePassword(rawPass, salt);
        return pass1.equals(pass2);
    }

    public String getAlgorithm() {
        return "DoubleMD5AndSalt";
    }
    
    public static void main(String [] args) throws ParseException{
    	String pass = "123456";
    	
    	Date date = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", "2012-12-10 16:56:39");
    	String  salt =   DateUtil.getDateTime("yyyyMMddHHmmss", date);
		System.out.println(salt);
    	String enPass = new DoubleMD5AndSaltPasswordEncoder().encodePassword(pass, salt); 
    	System.out.println(enPass);
    	
    }
}
