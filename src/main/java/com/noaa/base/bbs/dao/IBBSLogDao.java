package com.noaa.base.bbs.dao;

import java.util.HashMap;
import java.util.List;

public interface IBBSLogDao  {
	public List<BBSLogDTO> list(HashMap<String,Object> paramMap);
	public BBSLogDTO detail(HashMap<String,Object> paramMap);
	public int create(BBSLogDTO paramBean);
}
