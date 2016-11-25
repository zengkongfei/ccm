package com.ccm.oxi.rate_6;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateAdapter extends XmlAdapter<String, XMLGregorianCalendar> {

	 private SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	 private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");  
	
	 @Override  
	 public XMLGregorianCalendar unmarshal(String v) throws Exception { 
		 if(StringUtils.isBlank(v)){
			 return null;  
		 }
		 //如果只有年月日
		 String yyyyMMDDReg = "[0-9]{4}[-/][0-9]{1,2}[-/][0-9]{1,2}";
		 if(v.matches(yyyyMMDDReg)){
			 return this.dateToXmlDate(yyyyMMdd.parse(v));
		 }
		 v = v.replace("T", " ");
		 //如果是年月日时分秒
		 String yyyyMMDDHHmmssReg = "[0-9]{4}[-/][0-9]{1,2}[-/][0-9]{1,2} [0-9]{1,2}[:/][0-9]{1,2}[:/][0-9]{1,2}"; 
		 if(v.matches(yyyyMMDDHHmmssReg)){
			 return this.dateToXmlDate(yyyyMMddHHmmss.parse(v));
		 }

	     return null;
	 }  
	  
	 @Override  
	 public String marshal(XMLGregorianCalendar v) throws Exception {  
	     return yyyyMMddHHmmss.format(this.xmlDate2Date(v));
	 }  
	 
	 /**
	  * 将Date类转换为XMLGregorianCalendar
	  * 
	  * @param date
	  * @return
	  */
	 public XMLGregorianCalendar dateToXmlDate(Date date) {
		 if (date == null) {
			 return null;
		 }
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 DatatypeFactory dtf = null;
		 try {
			 dtf = DatatypeFactory.newInstance();
		 } catch (DatatypeConfigurationException e) {
		 }
		 XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
		 dateType.setYear(cal.get(Calendar.YEAR));
		 // 由于Calendar.MONTH取值范围为0~11,需要加1
		 dateType.setMonth(cal.get(Calendar.MONTH) + 1);
		 dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
		 dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
		 dateType.setMinute(cal.get(Calendar.MINUTE));
		 dateType.setSecond(cal.get(Calendar.SECOND));
		 return dateType;
	 }		 
		
	 /**
	  * 将XMLGregorianCalendar转换为Date
	  * 
	  * @param cal
	  * @return
	  */
	 public Date xmlDate2Date(XMLGregorianCalendar cal) {
		 return cal.toGregorianCalendar().getTime();
	 }	

}
