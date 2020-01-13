package com.noaa.base.menu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public interface IMenuDao {
	public List<MenuDTO> list(Map<String,Object> paramMap );
	public int create(MenuDTO paramDTO);
	public int createRole(MenuRoleDTO paramRole);
	public int dropRole(HashMap<String,Object> paramMap);
	public int delete(Map<String,Object> paramMap);
	public int update(MenuDTO paramDTO);
	public List<MenuRoleDTO> listRole(HashMap<String,Object> paramMap);
	public int createLog(MenuLogDTO paramBean);
	public MenuLogDTO detailLog(HashMap<String,Object> paramMap); 
	public int totalCountLog(HashMap<String,Object> paramMap);
	public List<MenuLogDTO> listLog(HashMap<String,Object> paramMap);
	public int updateLog(MenuLogDTO paramBean);
	public List<MenuLogDTO> listVisitLog(HashMap<String,Object> paramMap);
	
	public MenuDTO devicePrecent(HashMap<String, Object> paramMap);
	
	public int countTotalUser();
	
	public int averageUseTime(HashMap<String, Object> paramMap);
	
	public int countCurrentUser(HashMap<String, Object> paramMap);
	
}
