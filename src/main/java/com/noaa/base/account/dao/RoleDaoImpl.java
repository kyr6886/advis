package com.noaa.base.account.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("roleDao")
public class RoleDaoImpl extends BaseDao implements IRoleDao {

	@Override
	public int create(RoleDTO paramBean) {
		
		return update("role.create",paramBean);
	}

	@Override
	public int delete(HashMap<String, Object> paramMap) {
		
		return delete("role.delete",paramMap);
	}

	@Override
	public List<RoleDTO> list() {
		
		return selectList("role.list",null);
	}

}
