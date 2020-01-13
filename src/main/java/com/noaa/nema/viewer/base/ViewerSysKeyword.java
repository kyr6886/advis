package com.noaa.nema.viewer.base;

public class ViewerSysKeyword {
	//태풍 테이블 기준시점(이전:tob_kma_typhoon 이후:tob_kma_typhoon_5ds)
	public static String TYPOON_ST_DATE="20120522";
	public static int RAIN_SECTION_END_1=49;
	public static int RAIN_SECTION_END_2=2000;
	public static int RAIN_SECTION_ST_1=1;
	public static int RAIN_SECTION_ST_2=50;
	public static int RAIN_SECTION_ST_3=80;
	public static int RAIN_SECTION_ST_4=150;
	public static String DME_CODE_RAIN="HZD006";
	public static String DME_CODE_TYPHOON="HZD002";
	
	public static String DME_CODE_SNOW="HZD008";
	public static String DME_CODE_EARTHQUAKE="HZD017";
	public static String DME_CODE_TYPHOON_RAIN="HZD007";
	public static String DME_FIRST_YEAR="1985";
	
	

	
	public static String KMA_RPT_CLEAR="해제";
	public static String KMA_RPT_TYPE_WAVE="풍랑";
	public static String KMA_RPT_TYPE_RAIN="호우";
	public static String KMA_RPT_TYPE_TYPHOON="태풍";
	public static String KMA_RPT_TYPE_RED="경보";
	public static String KMA_RPT_TYPE_BROD="발표";
	public static String KMA_RPT_TYPE_YELLOW="주의";
	public static String KMA_RPT_TYPE_DELETE="제외";

	public static int TYPHOON_PAST_DATE_ST = 201204;
	public static String TYPHOON_SMIMILAR_DATE_ST = "1951";
	public static String TYPHOON_SMIMILAR_DATE_END = "2012";
	
	public static String SEARCH_SIDO_JEJU="제주";
	public static String SEARCH_SIDO_SEJONG="세종";
	public static String SEARCH_SIDO_JEJU_CODE="50000";
	public static String SEARCH_SIDO_SEJONG_CODE="36110";
	public static String SEARCH_HISTORY_TOP_DME = "COM_DME";
	
	public static String RAIN_JEJU_SIDO_AREA = "50";
	public static String RAIN_JEJU_GUNGU_AREA = "50000";
	
	public static String FILE_PATH_SEPERATE_NAME = "images";
	public static String FILE_PATH_REPRORT_NAME = "report";
	public static final String EXT_IMG="png,jpeg,jpg,gif,bmp";


}
