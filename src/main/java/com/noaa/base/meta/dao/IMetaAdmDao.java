package com.noaa.base.meta.dao;

import java.util.HashMap;
import java.util.List;

public interface IMetaAdmDao {
	
	public int createTable();
	public int createIndex();
	
	/**
	 * prepare 사용
	 * @param paramList
	 * @return
	 */
	public int createData(List<MetaAdmDTO> paramList);
	
	public MetaAdmDTO detail(HashMap<String, Object> paramMap);
	
	
	public List<MetaAdmDTO> listSido();
	public List<MetaAdmDTO> listGungu(HashMap<String,Object> paramMap);
	public List<MetaAdmDTO> listDong(HashMap<String,Object> paramMap);
	
	
}
