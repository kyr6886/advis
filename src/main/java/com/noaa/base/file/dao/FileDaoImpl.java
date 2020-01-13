package com.noaa.base.file.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("fileDao")
public class FileDaoImpl extends BaseDao implements IFileDao {
	@Override
	public int create(AttachFileDTO paramBean) {
		
		return update("file.create", paramBean);
	}

	@Override
	public int drop(HashMap<String, Object> paramMap) {
		
		return delete("file.drop",paramMap);
	}

	@Override
	public List<AttachFileDTO> list(HashMap<String, Object> paramMap) {

		return selectList("file.list",paramMap);
	}

	@Override
	public AttachFileDTO detail(HashMap<String, Object> paramMap) {
		
		return (AttachFileDTO)selectOne("file.detail",paramMap);
	}

	@Override
	public List<DBInfoDTO> dbList() {
		return selectList("system.dbList");
	}

	@Override
	public int update(AttachFileDTO paramBean) {
		
		return update("file.update",paramBean);
	}

}
