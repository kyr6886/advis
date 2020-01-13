package com.noaa.base.account.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("loginLogDao")
public class LoginLogDaoImpl extends BaseDao implements ILoginLogDao {

	@Override
	public int update(HashMap<String, Object> paramMap) {
		
		return update("loginLog.update",paramMap);
	}

	@Override
	public int create(LoginLogDTO paramMap) {

		return update("loginLog.create",paramMap);
	}

	@Override
	public int count(HashMap<String, Object> paramMap) {
		//Object obj=selectOne("loginLog.count",paramMap);
		return (Integer)selectOne("loginLog.count",paramMap);
	}

	@Override
	public List<LoginLogDTO> list(HashMap<String, Object> paramMap) {
		return selectList("loginLog.list",paramMap);
	}

	@Override
	public List<LoginLogDTO> detail(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return selectList("loginLog.detail",paramMap);
	}

	@Override
	public LoginLogDTO curTime() {
		return (LoginLogDTO) selectOne("loginLog.curTime");
	}

	@Override
	public int totalCount(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return (Integer)selectOne("loginLog.totalCount",paramMap);
	}

	@Override
	public int detailTotalCount(HashMap<String, Object> paramMap) {
		return (Integer)selectOne("loginLog.detailTotalCount",paramMap);
	}

	@Override
	public int detailConnTotalCount(HashMap<String, Object> paramMap) {
		return (Integer)selectOne("loginLog.detailConnTotalCount",paramMap);
	}
	
}
