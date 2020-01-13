package com.noaa.base.bbs.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("bbsUserDao")
public class BBSUserDaoImpl extends BaseDao implements IBBSUserDao {

	@Override
	public int create(BBSUserDTO paramBean) {
		
		return update("bbsUser.create", paramBean);
	}

	@Override
	public BBSUserDTO detail(HashMap<String, Object> paramMap) {
		
		return (BBSUserDTO)selectOne("bbsUser.detail",paramMap);
	}

	@Override
	public int createUserRole(BBSUserRoleDTO paramBean) {
		
		return (Integer)insert("bbsUserRole.create",paramBean);
	}
	
}
