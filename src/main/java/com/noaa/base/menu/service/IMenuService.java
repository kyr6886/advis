package com.noaa.base.menu.service;


import java.util.List;

import com.noaa.base.CommonParams;
import com.noaa.base.menu.dao.MenuDTO;
import com.noaa.base.menu.dao.MenuLogDTO;
import com.noaa.base.menu.dao.MenuRoleDTO;


public interface IMenuService {
	public List<MenuDTO> list(String paramUseYN);
	public int create(MenuDTO paramDTO);
	public int create(MenuDTO paramDTO,CommonParams common);
	public int createRole(String paramMenuID,List<MenuRoleDTO> paramRoles);
	public int createRole(String paramMenuID,String[] paramRoles);
	public int createRole(String paramMenuID, String[] paramRoles,String[] paramWriteRoles) ;
	public int drop(String paramMenuID);
	public int update(MenuDTO paramDTO);
	public int createLog(CommonParams common,String paramPrgID,int paramDurSecond);
	public int totalCountLog(String paramDateS,String paramDataE);
	public List<MenuLogDTO> listLog(String paramStDate,String paramEnDate, int pageNo, int pageSize);
	public List<MenuLogDTO> listVisitLog(String paramStDate, String paramEnDate);
	
	/** 접속장비별 접속수/퍼센트
	  * @Method Name : devicePrecent
	  * @작성일 : 2016. 11. 10.
	  * @작성자 : isyan79
	  * @param paramStDate 검색 시작일 YYYYMMDD
	  * @param paramEnDate 검색 종료일 YYYYMMDD
	  * @return
	  */
	public MenuDTO devicePrecent(String paramStDate,String paramEnDate);
	public int countTotalUser();
	public int averageUseTime(String paramDateS,String paramDataE);
	public int countCurrentUser(int paramTime);
}
