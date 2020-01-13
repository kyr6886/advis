package com.noaa.nema.viewer.typhoon.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.typhoon.dao.ITyphoonCastInfoDao;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastDateDto;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;

@Repository("typhoonCastInfoDao")
public class TyphoonCastInfoDaoImpl extends BaseDao implements ITyphoonCastInfoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TyphoonCastInfoDto> listTyphoonByCurrentTime(HashMap<String, Object> paramMap) {
		return selectList("typhoonCastInfo.listTyphoonInfoByCurrentTime", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TyphoonCastInfoDto> listPastTyphoonByPastTime(HashMap<String, Object> paramMap) {
		return selectList("typhoonCastInfo.listTyphoonInfoByPastTime", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TyphoonCastInfoDto> listCurrentSimialarTyphoonByPeriod(HashMap<String, Object> paramMap) {
		return selectList("typhoonCastInfo.listCurrentSimilarTyphoonByPeriod", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TyphoonCastInfoDto> listPastSimialarTyphoonByPeriod(HashMap<String, Object> paramMap) {
		return selectList("typhoonCastInfo.listPastSimilarTyphoonByPeriod", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TyphoonCastInfoDto> listTyphoonName(HashMap<String, Object> paramMap) {
		return selectList("typhoonCastInfo.typhoonNameList", paramMap);
	}

	@Override
	public TyphoonCastInfoDto last() {
		return (TyphoonCastInfoDto)selectOne("typhoonCastInfo.last", null);
	}

	@Override
	public List<TyphoonCastInfoDto> listTyphoonInTargetArea(HashMap<String, Object> paramMap) {
		return selectList("typhoonCastInfo.listTyphoonInTargetArea", paramMap);
	}
	@Override
	public List<TyphoonCastInfoDto> listTyphoonInTargetAreaByForcast(HashMap<String, Object> paramMap) {
		return selectList("typhoonCastInfo.listTyphoonInTargetAreaByForcast", paramMap);
	}

	@Override
	public List<TyphoonCastInfoDto> listForcast(HashMap<String, Object> paramMap) {
		return selectList("typhoonCastInfo.listForcast", paramMap);
	}

	@Override
	public List<TyphoonCastInfoDto> listCurrentTyphoonInfos(HashMap<String, Object> paramMap) {
		
		return selectList("typhoonCastInfo.currentTyphoonInfos",paramMap);
	}

	@Override
	public TyphoonCastInfoDto detailInfo(HashMap<String, Object> paramMap) {
		return (TyphoonCastInfoDto)selectOne("typhoonCastInfo.detailInfo",paramMap);
	}
	@Override
	public List<TyphoonCastInfoDto> listLat30Info() {
		return selectList("typhoonCastInfo.listLat30Info",null);
	}
	@Override
	public List<TyphoonCastInfoDto> listSearchTyphoonsByNameYear(HashMap<String, Object> paramMap) {
		return selectList("typhoonCastInfo.listSearchTyphoonsByNameYear",paramMap);
	}
}
