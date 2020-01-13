package com.noaa.base.bbs.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("bbsLogDao")
public class BBSLogDaoImpl extends BaseDao implements IBBSLogDao {

	@Override
	public List<BBSLogDTO> list(HashMap<String, Object> paramMap) {
		
		return selectList("bbsLog.list",paramMap);
	}

	@Override
	public BBSLogDTO detail(HashMap<String, Object> paramMap) {
		
		return (BBSLogDTO)selectOne("bbsLog.detail",paramMap);
	}

	@Override
	public int create(BBSLogDTO paramBean) {
		
		return (Integer)insert("bbsLog.create",paramBean);
	}

}
