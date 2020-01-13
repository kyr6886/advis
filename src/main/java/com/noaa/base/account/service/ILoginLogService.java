package com.noaa.base.account.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.noaa.base.CommonParams;
import com.noaa.base.account.dao.LoginLogDTO;
import com.noaa.base.account.dao.RoleDTO;
import com.noaa.base.account.dao.UserDTO;

public interface ILoginLogService {
	
	/** 접속자 로그
	  * @Method Name : listLoginLog
	  * @작성일 : 2016. 11. 9.
	  * @작성자 : isyan79
	  * @param paramDateS 조회 시작일 YYYYMMDD
	  * @param paramDataE 조회 종료일 YYYYMMDD
	  * @param userId 접속자 ID
	  * @return
	  */
	public int listLoginLog(String paramDateS,String paramDataE,String userId);
	/** 접속자 로그
	  * @Method Name : listLoginLog
	  * @작성일 : 2016. 11. 9.
	  * @작성자 : isyan79
	  * @param paramDateS 조회 시작일 YYYYMMDD
	  * @param paramDataE 조회 종료일 YYYYMMDD
	  * @param userId 접속자 ID
	  * @param userId 접속자 Name
	  * @return
	  */
	public LoginLogDTO curTime();
	public int count(String paramDateS,String paramDataE,String userId,String userName);
	public int totalCount(String paramDateS,String paramDataE,String userId,String userName);
	public List<LoginLogDTO> list(String paramDateS,String paramDataE,String userId,String userName, int pageNo, int pageSize);
	public List<LoginLogDTO> list(String paramDateS,String paramDataE,String userId, int pageNo, int pageSize);
	public List<LoginLogDTO> detail(String paramDateS,String paramDataE);
	public List<LoginLogDTO> detail(String paramDateS,String paramDataE,String paramUserId, int pageNo, int pageSize);
	public List<LoginLogDTO> detail(String paramDateS,String paramDataE,String paramUserId);
	public int detailConnTotalCount(String paramDateS,String paramDataE,String paramUserId);
	public int detailTotalCount(String paramDateS,String paramDataE,String userId,String userName);
	
}
