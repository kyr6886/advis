package com.noaa.base.meta.dao;

import java.util.HashMap;
import java.util.List;

public interface IMetaDao {
	
	
	/**
	 * @param paramMap null
	 * @return TC_META 전체 리스트
	 */
	public List<MetaDTO> list(HashMap<String, Object> paramMap);
	
}
