package com.noaa.base.bbs.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("bbsMasterDao")
public class BBSMasterDaoImpl extends BaseDao implements IBBSMasterDao {

	@Override
	public BBSMasterDTO detail(HashMap<String, Object> paramMap) {
		
		return (BBSMasterDTO)selectOne("bbsMaster.detail",paramMap);
	}

	@Override
	public List<BBSMasterDTO> list(HashMap<String, Object> paramMap) {
		
		return selectList("bbsMaster.list",paramMap);
	}
	@Override
	public int create(BBSMasterDTO paramBean) {

		return update("bbsMaster.create",paramBean);
	}
	@Override
	public int update(BBSMasterDTO paramBean) {

		return update("bbsMaster.update",paramBean);
	}
	@Override
	public List<BBSCategoryDTO> listCategory(HashMap<String, Object> paramMap) {
		
		return selectList("bbsCategory.list",paramMap);
	}

	@Override
	public int createCategory(BBSCategoryDTO paramBean) {
		
		return update("bbsCategory.create",paramBean);
	}

	@Override
	public int dropCategory(HashMap<String, Object> paramMap) {
	
		return delete("bbsCategory.drop", paramMap);
	}

}
