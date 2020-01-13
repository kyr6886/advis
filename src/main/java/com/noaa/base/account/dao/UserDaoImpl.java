package com.noaa.base.account.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements IUserDao {

	@Override
	public int create(UserDTO paramBean) {

		return update("user.create",paramBean);
	}

	@Override
	public UserDTO detail(HashMap<String, Object> paramMap) {
		Object obj=selectOne("user.detail",paramMap);
		return (UserDTO)obj;
	}

	@Override
	public int update(UserDTO paramBean) {

		return update("user.update",paramBean);
	}

	@Override
	public int delete(HashMap<String, Object> paramMap) {

		return update("user.delete",paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> list(HashMap<String, Object> paramMap) {
		
		return selectList("user.list",paramMap);
	}

	@Override
	public int changePassword(HashMap<String, Object> paramMap) {
		
		return update("user.changePassword",paramMap);
	}

	
	@Override
	public int updateRole(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalCount(HashMap<String, Object> paramMap) {
		
		return (Integer)selectOne("user.totalCount",paramMap);
	}

	@Override
	public int updateUserType(HashMap<String, Object> paramMap) {
		
		return update("user.updateUserType",paramMap);
	}

	@Override
	public int createRole(HashMap<String, Object> paramMap) {
		
		return update("userRole.create",paramMap);
	}

	@Override
	public int dropRole(HashMap<String, Object> paramMap) {

		return delete("userRole.drop",paramMap);
	}

	@Override
	public int countRole(HashMap<String, Object> paramMap) {
		
		return (Integer)selectOne("user.countRole",paramMap);
	}

	@Override
	public int countTotalUser() {
	
		return (Integer)selectOne("user.countTotalUser",null);
	}

	@Override
	public int updateFailCnt(UserDTO paramBean) {
		return update("user.updateFailCnt",paramBean);
	}

	@Override
	public int updateReFailCnt(UserDTO paramBean) {
		return update("user.updateReFailCnt",paramBean);
	}

	
}
