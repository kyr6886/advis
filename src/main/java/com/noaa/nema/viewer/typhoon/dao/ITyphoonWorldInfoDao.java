package com.noaa.nema.viewer.typhoon.dao;

import java.util.HashMap;
import java.util.List;

public interface ITyphoonWorldInfoDao {
	
	public List<TyphoonCastInfoDto> worldTyphoonHistoryList(HashMap<String , Object> paramMap);
	public List<TyphoonCastInfoDto> worldTyphoonForecastList(HashMap<String , Object> paramMap);
	public List<TyphoonCastInfoDto> worldTyphoonForecastGroupByName(HashMap<String , Object> paramMap);
	

}
