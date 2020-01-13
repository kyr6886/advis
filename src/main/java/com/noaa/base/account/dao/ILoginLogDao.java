package com.noaa.base.account.dao;

import java.util.HashMap;
import java.util.List;

public interface ILoginLogDao {
	public int update(HashMap<String,Object> paramMap);
	public int create(LoginLogDTO paramBean);
	public int count(HashMap<String,Object> paramMap);
	public int totalCount(HashMap<String,Object> paramMap);
	public int detailTotalCount(HashMap<String,Object> paramMap);
	public List<LoginLogDTO> list(HashMap<String,Object> paramMap);
	public List<LoginLogDTO> detail(HashMap<String,Object> paramMap);
	public int detailConnTotalCount(HashMap<String,Object> paramMap);
	public LoginLogDTO curTime();
}
