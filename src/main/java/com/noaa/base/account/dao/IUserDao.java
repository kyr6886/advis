package com.noaa.base.account.dao;

import java.util.HashMap;
import java.util.List;

public interface IUserDao {
	public int create(UserDTO paramBean);
	public int countTotalUser();
	public UserDTO detail(HashMap<String,Object> paramMap);
	public int totalCount(HashMap<String,Object> paramMap);
	public int update(UserDTO paramBean);
	public int updateUserType(HashMap<String,Object> paramMap);
	public int updateRole(HashMap<String,Object> paramMap);
	public int delete(HashMap<String,Object> paramMap);
	public int changePassword(HashMap<String,Object> paramMap);
	public List<UserDTO> list(HashMap<String,Object> paramMap);
	public int createRole(HashMap<String,Object> paramMap);
	public int dropRole(HashMap<String,Object> paramMap);
	public int countRole(HashMap<String,Object> paramMap);
	public int updateFailCnt(UserDTO paramBean);
	public int updateReFailCnt(UserDTO paramBean);
}
