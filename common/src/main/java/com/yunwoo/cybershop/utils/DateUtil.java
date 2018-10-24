package com.yunwoo.cybershop.utils;

import org.joda.time.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间操作工具类
 *
 */
public class DateUtil extends DateTimeUtils {
	/**
	 * 短日期格式
	 */
	public final static String SHORTDATEFORMATER = "yyyy-MM-dd";

	public final static String SHORTTIMEFORMATER = "HH:mm:ss";

	public final static String LONGDATEFORMATER = "yyyy-MM-dd HH:mm:ss";
	
	public final static String LONGDATEFORMATER_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	
	public final static String LONGDATEFORMATER_YYYYMMDDHHMM_DOT = "yyyy.MM.dd HH:mm";
	/**
	 * Solr中使用的日期格式
	 */
	public static final String SOLR_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:sss'Z'";
	
	public static final long DAYTIMESTAMP = 24 * 60 * 60 * 1000;
	
	private static Logger log = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * @param datePattern 
	 *            格式
	 * @return 返回日期格式字符串（yyyy-mm-dd HH:mm:ss）
	 */
	public static final String getDateByDatePattern(String datePattern) {
		return convertDateToString(datePattern, new Date());
	}

	/**
	 * 把时间装成指定的字符串
	 * 
	 * @param datePattern
	 *            转换格式
	 * @param date
	 *            日期
	 * @return 时间字符串
	 */
	public static final String convertDateToString(String datePattern, Date date) {
		String returnValue = null;
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(datePattern);
			returnValue = df.format(date);
		}
		return (returnValue);
	}

	/**
	 * 把时间装成指定的字符串 (yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 *            日期
	 * @return 时间字符串
	 */
	public static final String convertDateToString(Date date) {
		return convertDateToString(LONGDATEFORMATER, date);
	}

	/**
	 * 把时间字符串转成指定格式的时间
	 * 
	 * @param datePattern
	 *            转换格式
	 * @param date
	 *            时间
	 * @return 时间Date
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String datePattern,String dateStr) {
		if( StringUtils.isBlank(dateStr) ){
			return null;
		}
		
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(datePattern);
		try {
			date = df.parse(dateStr);
		} catch (ParseException pe) {
			log.info("{}",pe);
			return null;
		}
		return (date);
	}
	
	public static final Date convertStringToDate(String dateStr) {
		if( StringUtils.isBlank(dateStr) ){
			return null;
		}
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(LONGDATEFORMATER);
		try {
			date = df.parse(dateStr);
		} catch (ParseException pe) {
			log.info("{}",pe);
			return null;
		}
		return (date);
	}

	/**
	 * 把时间字符串装成日期
	 * 
	 * @param date
	 *            可传（yyyy-mm-dd HH:mm:ss）或者（yyyy-mm-dd）
	 * @return
	 */
	public static final Date getDateTime(String date) {
		return convertStringToDate(LONGDATEFORMATER, date);
	}
	
	/**
	 * 判断给定日期是否未过期
	 * @param givenDate 给定日期
	 * @return
	 */
	public static final boolean isNonExpired(Date givenDate){
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(calendarNow.getTime());
		Calendar calendarGiven = Calendar.getInstance();
		calendarGiven.setTime(givenDate);
		return calendarNow.before(calendarGiven);
	}
	
	/**
	 * 判断给定日期是否过期
	 * @param givenDate 给定日期
	 * @return
	 */
	public static final boolean isExpired(Date givenDate){
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(calendarNow.getTime());
		Calendar calendarGiven = Calendar.getInstance();
		calendarGiven.setTime(givenDate);
		return calendarNow.after(calendarGiven);
	}
	
	public static final Date getDate(){
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return calendarNow.getTime();
	}
	
	/**
	 * 将{@link Date}类型转换为Solr可以使用的字符串
	 * @param date 要转换的日期
	 * @return
	 */
	public static String formatSolrDateString(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SOLR_DATE_FORMAT);
		return simpleDateFormat.format(date);
	}
	
	public static Date DateMinus(Date date,int month){
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.MONTH, -month);
		return calendar.getTime();
	}
	
	/**
	 * 获取指定日期之前指定天,包含传入的那一天
	 * @param date 指定的日期
	 * @param days 指定的天数
	 * @return
	 */
	public static String getDaysBefore(Date date, int days) {
		Date td = new Date(date.getTime() - DAYTIMESTAMP * days);
		return DateUtil.convertDateToString(SHORTDATEFORMATER, td);
	}
	
	/**
	 * 获取指定日期之前指定天,包含传入的那一天
	 * @param dateStr 指定的日期的字符串格式
	 * @param days 指定的天数
	 * @return
	 */
	public static String getDaysBefore(String dateStr, int days) {
		Date date = convertStringToDate(SHORTDATEFORMATER, dateStr);
		return DateUtil.getDaysBefore(date, days);
	}
	
	/**
	 * 获取指定日期之前指定天的数组,包含传入的那一天
	 * @param date 指定的日期
	 * @param days 指定的天数
	 * @return
	 */
	public static List<String> getDaysBeforeArray(Date date, int days){
		List<String> resultList = new ArrayList<String>();
		for (int i = days-1; i >= 0; i--) {
			resultList.add(getDaysBefore(date, i));
		}
		return resultList;
	}
	
	/**
	 * 获取指定日期之前指定天的数组,包含传入的那一天
	 * @param dateStr 指定的日期的字符串格式
	 * @param days 指定的天数
	 * @return
	 */
	public static List<String> getDaysBeforeArray(String dateStr, int days){
		List<String> resultList = new ArrayList<String>();
		for (int i = days-1; i >= 0; i--) {
			resultList.add(getDaysBefore(dateStr, i));
		}
		return resultList;
	}
	
	/**
	 * 获取指定日期之前指定天,包含传入的那一天
	 * @param date 指定的日期
	 * @param days 指定的天数
	 * @return
	 */
	public static String getDaysAfter(Date date, int days) {
		Date td = new Date(date.getTime() + DAYTIMESTAMP * days);
		return DateUtil.convertDateToString(SHORTDATEFORMATER, td);
	}
	
	/**
	 * 获取指定日期之前指定天,包含传入的那一天
	 * @param dateStr 指定的日期的字符串格式
	 * @param days 指定的天数
	 * @return
	 */
	public static String getDaysAfter(String dateStr, int days) {
		Date date = convertStringToDate(SHORTDATEFORMATER, dateStr);
		return DateUtil.getDaysAfter(date, days);
	}
	
	
	/**
	 * 获取指定日期之后指定天的数组,包含传入的那一天
	 * @param dateStr 指定的日期的字符串格式
	 * @param days 指定的天数
	 * @return
	 */
	public static List<String> getDaysAfterArray(Date date, int days){
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < days; i++) {
			resultList.add(getDaysAfter(date, i));
		}
		return resultList;
	}
	
	/**
	 * 获取指定日期之后指定天的数组,包含传入的那一天
	 * @param dateStr 指定的日期的字符串格式
	 * @param days 指定的天数
	 * @return
	 */
	public static List<String> getDaysAfterArray(String dateStr, int days){
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < days; i++) {
			resultList.add(getDaysAfter(dateStr, i));
		}
		return resultList;
	}
	
 
}
