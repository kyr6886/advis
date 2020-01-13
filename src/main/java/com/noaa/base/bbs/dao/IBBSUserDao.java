package com.noaa.base.bbs.dao;

import java.util.HashMap;

public interface IBBSUserDao {
	public int create(BBSUserDTO paramBean);
	public BBSUserDTO detail(HashMap<String,Object> paramMap);
	public int createUserRole(BBSUserRoleDTO paramBean);
	
}
