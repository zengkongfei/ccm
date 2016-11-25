package com.ccm.api.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class PinyinUtil {
    
	/**
	 * 转换字符串中汉字为拼音,除汉字外的原样输出
	 * @param cnName
	 */
	public static String covertCnNameToPinyin(String cnName){
		
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		
		
		String ret="";
		if(cnName != null && cnName.length()>0){
			  
			  for(int i = 0 ;i<cnName.length();i++){
				      String[] pinyinArray = null;
		              try
		              {
		                   char idxcnNameChar = cnName.charAt(i);
		            	   pinyinArray = PinyinHelper.toHanyuPinyinStringArray(idxcnNameChar, outputFormat);
		            	   if(pinyinArray == null){
		            	       pinyinArray = new String[]{String.valueOf(idxcnNameChar)};
		            	       String outputString = concatPinyinStringArray(pinyinArray);
                               ret = ret + outputString;
		            	   }else{
		            	       String outputString = concatPinyinStringArray(pinyinArray);
		                       if(i==0)ret = outputString+" ";
		                       else ret = ret + outputString;
		            	   }
		              } catch (BadHanyuPinyinOutputFormatCombination e1)
		              {
		                  e1.printStackTrace();
		              }
		              
			  }
             
			
		}
		 
		return ret;
		
    }
	/**
     * 转换字符串中汉字为拼音,除汉字外的原样输出
     * @param cnName
     */
    public static String covertCnNameToPinyin(String cnName,String delimiter){
        
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        
        
        String ret="";
        if(cnName != null && cnName.length()>0){
              
              for(int i = 0 ;i<cnName.length();i++){
                      String[] pinyinArray = null;
                      try
                      {
                           char idxcnNameChar = cnName.charAt(i);
                           pinyinArray = PinyinHelper.toHanyuPinyinStringArray(idxcnNameChar, outputFormat);
                           if(pinyinArray == null){
                               pinyinArray = new String[]{String.valueOf(idxcnNameChar)};
                               String outputString = concatPinyinStringArray(pinyinArray);
                               ret = ret + outputString;
                           }else{
                               String outputString = concatPinyinStringArray(pinyinArray);
                               if(i==0)ret = outputString+delimiter;
                               else ret = ret + outputString;
                           }
                      } catch (BadHanyuPinyinOutputFormatCombination e1)
                      {
                          e1.printStackTrace();
                      }
                      
              }
             
            
        }
         
        return ret;
        
    }
	/**
	 * 转换汉字为拼音
	 * @param cnName
	 */
	public static String covertCnStringToPinyin(String cnName){
		
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		
		
		String ret="";
		if(cnName != null && cnName.length()>0){
			  
			  for(int i = 0 ;i<cnName.length();i++){
				      String[] pinyinArray = null;
		              try
		              {
		            	   pinyinArray = PinyinHelper.toHanyuPinyinStringArray(cnName.charAt(i), outputFormat);
		              } catch (BadHanyuPinyinOutputFormatCombination e1)
		              {
		                  e1.printStackTrace();
		              }
		              String outputString = concatPinyinStringArray(pinyinArray);
		              
		              
		             ret = ret + outputString;
			  }
             
			
		}
		 
		return ret;
		
    }
	
	 @SuppressWarnings("unused")
	private static String concatPinyinStringArray(String[] pinyinArray)
     {
         StringBuffer pinyinStrBuf = new StringBuffer();

         if ((null != pinyinArray) && (pinyinArray.length > 0))
         {
             for (int i = 0; i < pinyinArray.length; i++)
             {
                 pinyinStrBuf.append(pinyinArray[i]);
                 break;
                // pinyinStrBuf.append(System.getProperty("line.separator"));
             }
         }
         String outputString = pinyinStrBuf.toString();
         return outputString;
     }
	 
	 public static void main(String[] args) {
		 
		 System.out.println("wwwwwwwwww");
		 String outputString = PinyinUtil.covertCnNameToPinyin("纪少杰");
		 System.out.println("wwwwwwwwww"+outputString);
		 outputString = PinyinUtil.covertCnNameToPinyin("only纪eng$lish");
		 System.out.println("wwwwwwwwww"+outputString);
	 }
	 
}
