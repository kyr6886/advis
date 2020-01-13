package com.noaa.base.code.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("codeSysDao")
public class CodeSysDaoImpl extends BaseDao implements ICodeSysDao  {

	@Override
	public List<CodeSysDTO> list(HashMap<String, Object> paramMap) {
	
		return selectList("codeSys.list", paramMap);
	}

	@Override
	public List<CodeGrpDTO> listGrp(HashMap<String, Object> paramMap) {
		return selectList("codeSys.listGrp", paramMap);
	}

	@Override
	public int createGrp(CodeGrpDTO paramBean) {
		
		return update("codeSys.createGrp", paramBean);
	}

	@Override
	public int createSys(CodeSysDTO paramBean) {
		
		return update("codeSys.createSys", paramBean);
	}

	@Override
	public int dropGrp(HashMap<String, Object> paramMap) {
		
		return update("codeSys.dropGrp", paramMap);
	}

	@Override
	public int dropSys(HashMap<String, Object> paramMap) {
		
		return update("codeSys.dropSys", paramMap);
	}

	@Override
	public int updateGrp(HashMap<String, Object> paramMap) {
		return update("codeSys.updateGrp", paramMap);
	}

	@Override
	public int updateSys(HashMap<String, Object> paramMap) {
		return update("codeSys.updateSys", paramMap);
	}

	@Override
	public CodeGrpDTO detailGrp(HashMap<String, Object> paramMap) {
		
		return (CodeGrpDTO)selectOne("codeSys.detailGrp",paramMap);
	}

	@Override
	public CodeSysDTO detailSys(HashMap<String, Object> paramMap) {
		return (CodeSysDTO)selectOne("codeSys.detailSys",paramMap);
	}
	
	

}
