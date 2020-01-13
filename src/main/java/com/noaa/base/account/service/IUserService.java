package com.noaa.base.account.service;

public interface IUserService {
	
	/** 사용자 count
	  * @Method Name : totalCount
	  * @작성일 : 2016. 11. 9.
	  * @작성자 : isyan79
	  * @param paramUsrId 아이디
	  * @param paramUserName 이름
	  * @param paramTypeCode 권한
	  * @param paramStatusCode 상태
	  * @param paramStDate 가입일 시작 YYYYMMDD
	  * @param paramEnDate 가입일 종료 YYYYMMDD
	  * @return
	  */
	public int totalCount(String paramUsrId,String paramUserName,String paramTypeCode,String paramStatusCode,String paramStDate,String paramEnDate);
}
