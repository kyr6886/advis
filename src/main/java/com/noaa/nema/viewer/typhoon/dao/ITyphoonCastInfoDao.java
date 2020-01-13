package com.noaa.nema.viewer.typhoon.dao;

import java.util.HashMap;
import java.util.List;

public interface ITyphoonCastInfoDao {
	
	public List<TyphoonCastInfoDto> listTyphoonByCurrentTime(HashMap<String, Object> paramMap);
	public List<TyphoonCastInfoDto> listPastTyphoonByPastTime(HashMap<String, Object> paramMap);
	public List<TyphoonCastInfoDto> listCurrentSimialarTyphoonByPeriod(HashMap<String, Object> paramMap);
	public List<TyphoonCastInfoDto> listPastSimialarTyphoonByPeriod(HashMap<String, Object> paramMap);
	public List<TyphoonCastInfoDto> listTyphoonName(HashMap<String, Object> paramMap);
	public TyphoonCastInfoDto last();
	public List<TyphoonCastInfoDto> listTyphoonInTargetArea(HashMap<String, Object> paramMap);
	public List<TyphoonCastInfoDto> listForcast(HashMap<String, Object> paramMap);
	public List<TyphoonCastInfoDto> listTyphoonInTargetAreaByForcast(HashMap<String, Object> paramMap);
	public List<TyphoonCastInfoDto> listCurrentTyphoonInfos(HashMap<String,Object> paramMap);
	public TyphoonCastInfoDto detailInfo(HashMap<String, Object> paramMap) ;
	public List<TyphoonCastInfoDto> listSearchTyphoonsByNameYear(HashMap<String, Object> paramMap);
	public List<TyphoonCastInfoDto> listLat30Info();
}
