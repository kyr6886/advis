package com.noaa.base.bbs.dao;

import java.util.HashMap;
import java.util.List;

public interface IBBSContentDao {
	public int create(BBSContentDTO paramBean);
	public BBSContentDTO detail(HashMap<String,Object> paramMap);
	public List<BBSContentDTO> list(HashMap<String,Object> paramMap);
	public List<BBSContentDTO> listRecent(HashMap<String,Object> paramMap);
	public int updateSortNoBBSContent(HashMap<String,Object> paramMap);
	public int update(BBSContentDTO paramBean);
	public int delete(HashMap<String,Object> paramMap);
	public int maxSort(HashMap<String,Object> paramMap);
	public int totalCount(HashMap<String,Object> paramMap);
	public int updateFile(HashMap<String,Object> paramMap);
}
