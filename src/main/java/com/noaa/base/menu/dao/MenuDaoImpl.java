package com.noaa.base.menu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDao  implements IMenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuDTO> list(Map<String, Object> paramMap) {
		return selectList("menu.list",paramMap);
	}

	@Override
	public int create(MenuDTO paramDTO) {
		return update("menu.create",paramDTO);
	}

	@Override
	public int delete(Map<String, Object> paramMap) {
		return delete("menu.drop",paramMap);
	}

	@Override
	public int update(MenuDTO paramDTO) {
		return update("menu.update",paramDTO);
	}

	@Override
	public int createRole(MenuRoleDTO paramRole) {
		
		return update("menuRole.create",paramRole);
	}
	@Override
	public int dropRole(HashMap<String,Object> paramMap){
		return delete("menuRole.drop",paramMap);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuRoleDTO> listRole(HashMap<String,Object> paramMap){
		return selectList("menuRole.list",paramMap);
	}

	@Override
	public int createLog(MenuLogDTO paramBean) {
		
		return update("menu.createLog",paramBean);
	}
	@Override
	public MenuLogDTO detailLog(HashMap<String,Object> paramMap){
		return (MenuLogDTO)selectOne("menu.detailLog",paramMap);
		 
	}
	@Override
	public int totalCountLog(HashMap<String, Object> paramMap) {
		return (Integer) selectOne("menu.totalCountLog",paramMap);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuLogDTO> listLog(HashMap<String,Object> paramMap) {
		
		return selectList("menu.listLog",paramMap);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuLogDTO> listVisitLog(HashMap<String,Object> paramMap) {
		
		return selectList("menu.listConnectCount",paramMap);
	}
	@Override
	public int updateLog(MenuLogDTO paramBean) {
		
		return update("menu.updateLog",paramBean);
	}

	@Override
	public MenuDTO devicePrecent(HashMap<String, Object> paramMap) {
		return (MenuDTO)selectOne("menu.devicePrecent",paramMap);
	}

	@Override
	public int averageUseTime(HashMap<String, Object> paramMap) {
		return (Integer) selectOne("menu.averageUseTime",paramMap);
	}

	@Override
	public int countCurrentUser(HashMap<String, Object> paramMap) {
		return (Integer) selectOne("menu.countCurrentUser",paramMap);
	}

	@Override
	public int countTotalUser() {
		return (Integer) selectOne("menu.countTotalUser");
	}

	
}
