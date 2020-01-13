package com.noaa.base;

public class BaseSysKeyword {
	
	public static final String COMMON_PARAM_USE_Y="Y";
	public static final String COMMON_PARAM_USE_N="N";
	
	public static final String SESSION_USER_ID="USERID";
	public static final String SESSION_USER_NAME="USERNAME";
	public static final String SESSION_USER_EMAIL="USEREMAIL";
	public static final String SESSION_USER_ROLE="USEREROLE";
	public static final String SESSION_ACC_TYPE="ACC_TYPE";
	public static final String SESSION_ACC_TITLE="ACC_TITLE";
	public static final String CODE_GROUP_ACC_TYPE="ACC_TYPE";
	public static final String CODE_GROUP_ACC_STATUS="ACC_STUS";
	
	
	public static final String CODE_USERTYPE_ADMIN="ACC_0000";
	public static final String CODE_USERTYPE_PUBLICK="ACC_0001";
	public static final String CODE_USERTYPE_SYSTEM="ACC_0002";
	public static final String CODE_USERTYPE_PARTNER="ACC_0003";
	
	public static final String CODE_GROUP_ACC_STATUS_DEFAULT="ACC_ST01";
	public static final String CODE_GROUP_ACC_STATUS_LOCK="ACC_ST03";
	public static final int CODE_GROUP_ACC_STATUS_LOCK_CNT=5;
	public static final String[] META_KOR_ADM={"tc_adm","행정동 코드"};
	public static final String[] META_KOR_LAW={"tc_law","법정동 코드"};
	public static final String[] META_HRFCO_OBS={"tc_rainfall_obs","홍수통제소 우량/수위 측정소"};
	public static final String[] META_KWATER_OBS={"tc_dam_info","홍수통제소 우량/수위 측정소"};
	public static final String[] META_TABLE_SPACES={"TC_ADM","TC_LAW"};
	
	public static final String COOKIE_NAME_KEEP="KEEP";
	public static final String COOKIE_VALUE_KEEP="Y";
	public static final String COOKIE_NAME_USERID="USERID";
	public static final String[] ACCOUNT_FIND_OPTIONS={"user_name","user_id"};
	public static final String PRIVATE_KEY="rsa_private_key";
	public static final String TABLESPACE_NAME="winsplus";
	
}
