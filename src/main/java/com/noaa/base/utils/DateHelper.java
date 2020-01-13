package com.noaa.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public  class DateHelper {
	public static String getCurrentYear(){
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy"); 
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return formatter.format(c.getTime());
	}
	public static String getCurrentYYYYMMDD(){
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd"); 
		  Calendar c = Calendar.getInstance();
		  c.setTime(new Date());
		return formatter.format(c.getTime());
	}

	public static String getNextYYYYMMDD(int n){
		 Calendar cal = new GregorianCalendar(Locale.KOREA);
		 cal.setTime(new Date());
		 cal.add(Calendar.DAY_OF_YEAR, n);
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd"); 
		return formatter.format(cal.getTime());
	}

	public static String getNextYYYYMMDDHHmmssSSS(){
		 Calendar cal = new GregorianCalendar(Locale.KOREA);
		 cal.setTime(new Date());
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMddHHmmss.SSS"); 
		return formatter.format(cal.getTime());
	}
	
	//n달전 날짜
	public static String getNextMonthYYYYMMDD(int n){
		 Calendar cal = new GregorianCalendar(Locale.KOREA);
		 cal.setTime(new Date());
		 cal.add(Calendar.MONTH, n);
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd"); 
		return formatter.format(cal.getTime());
	}
	//해당월 1일 초기화
	public static String getFirstDayYYYYMMDD(){
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd"); 
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
		return formatter.format(c.getTime()).substring(0,6)+"01";
	}
	//n분전(후) 시간을 가져온다.
	public static String getYYYYMMDDHH24MI(int n){
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, n);
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMddkkmm"); 
		return formatter.format(cal.getTime());
	}
	
	
	/** 요청한 날짜의 전달/뒷달
	 * @param n 1,-1...
	 * @param paramDate YYYYMMDD
	 * @return
	 */
	public static String getNextMonthYYYYMMDD(int n,String paramDate){
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd");
		Date date = null;
		try{
			date = formatter.parse(paramDate);		
		}catch(ParseException e){
			
		}
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return formatter.format(cal.getTime());
	}
	//n초전(후) 시간을 가져온다.
	public static String getYYYYMMDDHH24MISS(int n){
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(new Date());
		cal.add(Calendar.SECOND, n);
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMddkkmmss"); 
		return formatter.format(cal.getTime());
	}
	
}
