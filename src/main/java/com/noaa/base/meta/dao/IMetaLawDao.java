package com.noaa.base.meta.dao;

import java.util.HashMap;
import java.util.List;

public interface IMetaLawDao {
	
	public int createTable();
	public int createIndex();
	
	/**
	 * prepare 사용
	 * @param paramList
	 * @return
	 */
	public int createData(List<MetaLawDTO> paramList);
	
	public MetaLawDTO detail(HashMap<String, Object> paramMap);
	
	public List<MetaLawDTO> listSido();
	public List<MetaLawDTO> listGungu(HashMap<String,Object> paramMap);
	public List<MetaLawDTO> listDong(HashMap<String,Object> paramMap);
	public List<MetaLawDTO> listRi(HashMap<String,Object> paramMap);
}
