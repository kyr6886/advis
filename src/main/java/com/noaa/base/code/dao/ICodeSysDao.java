package com.noaa.base.code.dao;

import java.util.HashMap;
import java.util.List;

public interface ICodeSysDao {
	public List<CodeSysDTO> list(HashMap<String,Object> paramObject);
	public List<CodeGrpDTO> listGrp(HashMap<String,Object> paramObject);
	
	public CodeGrpDTO detailGrp(HashMap<String,Object> paramObject);
	public CodeSysDTO detailSys(HashMap<String,Object> paramObject);
	
	public int createGrp(CodeGrpDTO paramBean);
	public int createSys(CodeSysDTO paramBean);
	public int dropGrp(HashMap<String,Object> paramMap);
	public int dropSys(HashMap<String,Object> paramMap);
	public int updateGrp(HashMap<String,Object> paramMap);
	public int updateSys(HashMap<String,Object> paramMap);
}
