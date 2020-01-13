package com.noaa.base.bbs.dao;

import java.util.HashMap;
import java.util.List;

public interface IBBSMasterDao {
	public BBSMasterDTO detail(HashMap<String,Object> paramMap);
	public int create(BBSMasterDTO paramBean);
	public int update(BBSMasterDTO paramBean);
	public List<BBSMasterDTO> list(HashMap<String,Object> paramMap);
	public List<BBSCategoryDTO> listCategory(HashMap<String,Object> paramMap);
	public int dropCategory(HashMap<String,Object> paramMap);
	public int createCategory(BBSCategoryDTO paramBean);
}
