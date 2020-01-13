package com.noaa.base.account.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.noaa.base.CommonParams;
import com.noaa.base.account.dao.LoginLogDTO;
import com.noaa.base.account.dao.RoleDTO;
import com.noaa.base.account.dao.UserDTO;

public interface IAccountService {
	public UserDTO detail(String paramUserID);
	public UserDTO detail(String paramUserID,String paramPwd);
	public UserDTO detailByNameEmail(String paramUserName,String paramUserEmail);
	public UserDTO detailByIDEmail(String paramUserID,String paramUserEmail);
	public int countUserRoles(String paramRoleID);
	public int countTotalUser();
	public List<UserDTO> list(String paramUserType,int paramPageNo,int paramRowSize);
	public List<UserDTO> list(String paramUserType,String paramUserStatus,int paramPageNo,int paramRowSize);
	public List<UserDTO> list(String paramUserType,String paramUserStatus,String paramUserName,int paramPageNo,int paramRowSize);
	public List<UserDTO> list(String paramUserType,String paramUserStatus,String paramSearchOption,String paramSearchTxt,String paramStDate,String paramEnDate,String paramAdmCode,int paramPageNo,int paramRowSize);
	
	public List<RoleDTO> listRoles();
	
	public int totalCount(String paramUserType,String paramUserStatus,String paramSearchOption,String paramSearchTxt,String paramStDate,String paramEnDate,String paramAdmCode);
	public int create(UserDTO paramBean);
	public int createAdmin(UserDTO paramBean);
	
	public int update(UserDTO paramBean,CommonParams comm);
	public int update(String paramUserID,String paramTypeCode,String paramStatusCode);
	public int updateRole(String paramUserID,String[] paramRole);
	public int delete(String paramUserID,CommonParams comm);
	
	public int createLoginLog(CommonParams commons,String paramSessionID);
	public int updateLoginLog(CommonParams commons,String paramSessionID);
	public int countLoginLog(CommonParams commons,String paramSessionID);
	public int changePassword(String paramUserID,String orgPassword,String paramPassword);
	public String findUserID(String paramUserName,String paramUserEmail);
	public String findUserPwd(String paramUserID,String paramUserEmail);
	public int updateFailCnt(UserDTO paramBean);
	public int updateReFailCnt(UserDTO paramBean);
	public int updateStatus(UserDTO paramBean);
	
}
