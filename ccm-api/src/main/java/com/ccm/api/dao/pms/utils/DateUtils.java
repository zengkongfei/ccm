package com.ccm.api.dao.pms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 据说是相当可怕的日期工具类
 *
 * @author foxhis 2013-5-24
 * @see
 * @since 1.0
 */ 
public class DateUtils {
	public static String YYYYMMDD = "yyyy-MM-dd";
	public static String YMDHSM = "yyyy-MM-dd hh:mm:ss";
	
	public static final int YEAR = Calendar.DAY_OF_YEAR;
	public static final int MONTH = Calendar.DAY_OF_MONTH;
	public static final int WEEK = Calendar.DAY_OF_WEEK;
	public static final int DAY = Calendar.DATE;
	public static final int HOUR = Calendar.HOUR;
	public static final int MINUTE = Calendar.MINUTE;
	public static final int SECOND = Calendar.SECOND;
	
	private static final long SECOND_TIME = 1000;
	private static final long MINUTE_TIME = SECOND_TIME * 60;
	private static final long HOUR_TIME = MINUTE_TIME * 60;
	private static final long DAY_TIME = HOUR_TIME * 24;
	private static final long WEEK_TIME = DAY_TIME * 7;
	
	private DateUtils() {
	}
	
	public static String format(Date date, String patten) {
		return new SimpleDateFormat(patten).format(date);
	}
	
	public static String format(Date date) {
		return format(date,YYYYMMDD);
	}
	
	public static Date parse(String date, String patten) {
		try {
			return new SimpleDateFormat(patten).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date parse(String date) {
		return parse(date, YYYYMMDD);
	}
	
	public static Date add(Date date, int type, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(type, num);
		return c.getTime();
	}
	
	public static void addSelf(Date date, int type, int num) {
		Date newDate = add(date, type, num);
		date.setTime(newDate.getTime());
	}
	
	/**
	 * 比较两个时间，根据类型返回相差的时间值
	 * @param d1
	 * @param d2
	 * @param type
	 * @return
	 */ 
	public static long compareTo(Date d1, Date d2, int type) {
		long time1 = d1.getTime();
		long time2 = d2.getTime();
		long diff = time2 - time1;
		switch (type) {
		case MINUTE:
			return diff / MINUTE_TIME;
		case HOUR:
			return diff / HOUR_TIME;
		case DAY:
			return diff / DAY_TIME;
		case WEEK:
			return diff / WEEK_TIME;
		case YEAR:
			return d2.getYear() - d1.getYear();
		default:
			return diff;
		}
	}
	
	/**
	 * 判断两天是否是同一天
	 * @param date1
	 * @param date2
	 * @return
	 */ 
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		} else {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			return isSameDay(cal1, cal2);
		}
	}

	/**
	 * 判断两天是否是同一天
	 * @param date1
	 * @param date2
	 * @return
	 */ 
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null)
			throw new IllegalArgumentException("The date must not be null");
		else
			return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
					&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
					&& cal1.get(Calendar.DAY_OF_YEAR) == cal2
							.get(Calendar.DAY_OF_YEAR);
	}
	
}
