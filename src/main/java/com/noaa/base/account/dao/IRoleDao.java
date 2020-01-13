package com.noaa.base.account.dao;

import java.util.HashMap;
import java.util.List;

public interface IRoleDao {
	public int create(RoleDTO paramBean);
	public int delete(HashMap<String,Object> paramMap);
	public List<RoleDTO> list();
}
