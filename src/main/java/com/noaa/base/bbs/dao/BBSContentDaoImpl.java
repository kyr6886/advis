package com.noaa.base.bbs.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("bbsContentDao")
public class BBSContentDaoImpl extends BaseDao implements IBBSContentDao {

	@Override
	public int create(BBSContentDTO paramBean) {
		
		return update("bbsContent.create", paramBean);
	}

	@Override
	public BBSContentDTO detail(HashMap<String, Object> paramMap) {

		return (BBSContentDTO)selectOne("bbsContent.detail",paramMap);
	}

	@Override
	public List<BBSContentDTO> list(HashMap<String, Object> paramMap) {
		
		return selectList("bbsContent.list",paramMap);
	}

	@Override
	public int updateSortNoBBSContent(HashMap<String, Object> paramMap) {
		
		return update("bbsContent.updateSort",paramMap);
	}

	@Override
	public int update(BBSContentDTO paramBean) {

		return update("bbsContent.update",paramBean);
	}
	@Override
	public int updateFile(HashMap<String, Object> paramMap) {
		return update("bbsContent.updateFile",paramMap);
	}
	@Override
	public int delete(HashMap<String, Object> paramMap) {

		return update("bbsContent.delete",paramMap);
	}

	@Override
	public int maxSort(HashMap<String, Object> paramMap) {
		
		return (Integer)selectOne("bbsContent.maxSort",paramMap);
	}

	@Override
	public int totalCount(HashMap<String, Object> paramMap) {
		
		return (Integer)selectOne("bbsContent.totalCount",paramMap);
	}

	@Override
	public List<BBSContentDTO> listRecent(HashMap<String, Object> paramMap) {
		
		return selectList("bbsContent.listRecent",paramMap);
	}

	

}
