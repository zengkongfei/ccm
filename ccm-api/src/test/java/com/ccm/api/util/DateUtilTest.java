package com.ccm.api.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateUtilTest extends TestCase {
    //~ Instance fields ========================================================

    private final Log log = LogFactory.getLog(DateUtilTest.class);

    //~ Constructors ===========================================================

    public DateUtilTest(String name) {
        super(name);
    }

    public void testGetInternationalDatePattern() {
        LocaleContextHolder.setLocale(new Locale("nl"));
        assertEquals("dd-MMM-yyyy", DateUtil.getDatePattern());
       
        LocaleContextHolder.setLocale(Locale.FRANCE);
        assertEquals("dd/MM/yyyy", DateUtil.getDatePattern());
        
        LocaleContextHolder.setLocale(Locale.GERMANY);
        assertEquals("dd.MM.yyyy", DateUtil.getDatePattern());
        
        // non-existant bundle should default to default locale
        LocaleContextHolder.setLocale(new Locale("fi"));
        String fiPattern = DateUtil.getDatePattern();
        LocaleContextHolder.setLocale(Locale.getDefault());
        String defaultPattern = DateUtil.getDatePattern();
        
        assertEquals(defaultPattern, fiPattern);
    }

    public void testGetDate() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("db date to convert: " + new Date());
        }

        String date = DateUtil.getDate(new Date());

        if (log.isDebugEnabled()) {
            log.debug("converted ui date: " + date);
        }

        assertTrue(date != null);
    }
    
    public void testGetDateTime() {
        if (log.isDebugEnabled()) {
            log.debug("entered 'testGetDateTime' method");
        }
        String now = DateUtil.getTimeNow(new Date());
        assertTrue(now != null);
        log.debug(now);
    }
    public void testGetDateWithNull() {
        final String date = DateUtil.getDate(null);
        assertEquals("", date);
    }

    public void testGetDateTimeWithNull() {
        final String date = DateUtil.getDateTime(null, null);
        assertEquals("", date);
    }

    public void testGetToday() {
        assertNotNull(DateUtil.getToday());
    }
    
    @Test
    public void testConvertStringToDate() throws Exception {
        DateUtil.convertStringToDate("2012-11-11 08:23:00", "yyyy-MM-dd HH:mm:ss");
    }
    
    
    @Test
    public void testGetDay() throws Exception {
        String[][] expected = new String[][]{
                {"2012-01-01", "2012-02-04"},//1
                {"2012-01-29", "2012-03-03"},//2
                {"2012-02-26", "2012-03-31"},//3
                {"2012-04-01", "2012-05-05"},//4
                {"2012-04-29", "2012-06-02"},//5
                {"2012-05-27", "2012-06-30"},//6
                {"2012-07-01", "2012-08-04"},//7
                {"2012-07-29", "2012-09-01"},//8
                {"2012-08-26", "2012-10-06"},//9
                {"2012-09-30", "2012-11-03"},//10
                {"2012-10-28", "2012-12-01"},//11
                {"2012-11-25", "2013-01-05"}//12
        };
        for (int i = 1; i <=12; i ++ ) {
            Date d = DateUtil.convertStringToDate("2012-0" + i + "-01");
            
            
            System.out.println(DateUtil.getCalendar(d)[0].toString() + DateUtil.getCalendar(d)[1].toString());
            
            assertEquals(expected[i-1][0], DateUtil.convertDateToString(DateUtil.getCalendar(d)[0]));
            assertEquals(expected[i-1][1], DateUtil.convertDateToString(DateUtil.getCalendar(d)[1]));
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            System.out.print(c.get(Calendar.DAY_OF_WEEK) + " ");
            System.out.println(DateUtil.getDay(2012, i));
        }
    }
    
    @Test
    public void testGetDayOfYear() throws Exception  {
    	Date d = DateUtil.convertStringToDate("2012-01-01");
    	for(int i = 1;i <= 365;i ++) {
    		assertEquals(i, DateUtil.getDayOfYear(d));
    		d = DateUtil.nextDay(d);
    		log.info(DateUtil.convertDateToString(d));
    	}
    }
    
    @Test
    public void testGetMonthWeekDay() throws Exception {
        List<String> days = DateUtil.getMonthWeekDay("2012-12-11", new int[] {Calendar.SUNDAY - 1});
        assertEquals("2012-12-02", days.get(0));
        assertEquals("2012-12-09", days.get(1));
        assertEquals("2012-12-16", days.get(2));
        assertEquals("2012-12-23", days.get(3));
        assertEquals("2012-12-30", days.get(4));
        
    }
    
    @Test
    public void testDaysBegin() throws Exception {
        Date date = DateUtil.convertStringToDate("2013-01-07");
        assertEquals("2013-01-07T00:00:00", DateUtil.getDateTime("yyyy-MM-dd'T'HH:mm:ss", DateUtil.daysBegin(date)));
        assertEquals("2013-01-07T23:59:59",DateUtil.getDateTime("yyyy-MM-dd'T'HH:mm:ss",  DateUtil.daysEnd(date)));
    }
}
