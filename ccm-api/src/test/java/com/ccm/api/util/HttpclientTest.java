package com.ccm.api.util;

import static com.ccm.api.util.HttpclientUtil.doPost;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class HttpclientTest {
    
	
    public void testRtavclient() {
    	String urlStr = "http://localhost/ccm-ws/oxi_genputdata.do";
    	
    	String filePath = "E:/畅连CCM/ocm/OXI_Toolkit/XML 2.0/6_Sample_Messages/InvSnap/PMStoCRS/InvSnapOperaToExternal3.xml";
    	String xmlStr = readFile(filePath);
    	
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("Message", xmlStr);
    	
    	try {
			doPost(urlStr, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public void testRavlclient() {
    	String urlStr = "http://localhost/ccm-ws/oxi_genputdata.do";
    	
    	String filePath = "E:/畅连CCM/ocm/OXI_Toolkit/XML 2.0/6_Sample_Messages/Restrictions/PMStoCRS/Rate_Restriction_New.xml";
    	String xmlStr = readFile(filePath);
    	
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("Message", xmlStr);
    	
    	try {
			doPost(urlStr, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void testRateclient() {
    	String urlStr = "http://localhost/ccm-ws/oxi_genputdata.do";
    	
    	String filePath = "E:/畅连CCM/ocm/OXI_Toolkit/XML Updated Version/6_Sample_Messages/Rate/Rate_Set.xml";
    	String xmlStr = readFile(filePath);
    	
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("Message", xmlStr);
    	
    	try {
			doPost(urlStr, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static String readFile(String filePath){
         File file = new File(filePath);
         String line = null;
         StringBuffer sbf = new StringBuffer();
         try {
        	 BufferedReader in = new BufferedReader(new FileReader(file));
             while ((line = in.readLine()) != null) {
            	 sbf.append(line);
             }
             in.close();
         } 
         catch (IOException e) {
             e.getStackTrace();
         }
         return sbf.toString();

    }
}
