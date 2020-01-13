package com.noaa.nema.viewer.typhoon.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.typhoon.dao.ITyphoonWorldInfoDao;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;


@Repository("typhoonWorldInfoDao")
public class TyphoonWordInfoDaoImpl extends BaseDao implements ITyphoonWorldInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TyphoonCastInfoDto> worldTyphoonHistoryList(HashMap<String, Object> paramMap) {
		return selectList("typhoonWorldInfo.worldTyphoonHistoryList", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TyphoonCastInfoDto> worldTyphoonForecastList(HashMap<String, Object> paramMap) {
		return selectList("typhoonWorldInfo.worldTyphoonForecastList", paramMap);
	}

	@Override
	public List<TyphoonCastInfoDto> worldTyphoonForecastGroupByName(HashMap<String, Object> paramMap) {
		return selectList("typhoonWorldInfo.worldTyphoonForecastGroupByName", paramMap);	
	}

}
