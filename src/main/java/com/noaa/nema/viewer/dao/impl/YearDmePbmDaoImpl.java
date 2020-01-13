package com.noaa.nema.viewer.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmePbmDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmePbmDto;

@Repository("yearDmePbmDao")
public class YearDmePbmDaoImpl extends BaseDao implements IYearDmePbmDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePbmDto> listPbm(HashMap<String, Object> paramMap) {
		return selectList("yearDmePbm.listPbm" , paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePbmDto> detailPbm(HashMap<String, Object> paramMap) {
		return selectList("yearDmePbm.detailPbm" , paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePbmDto> listSidoPbmDme(HashMap<String, Object> paramMap) {
		return selectList("yearDmePbm.listSidoPbmDme" , paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePbmDto> listGunguPbmDme(HashMap<String, Object> paramMap) {
		return selectList("yearDmePbm.listGunguPbmDme" , paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePbmDto> listSidoPbmDmeSum(HashMap<String, Object> paramMap) {
		return selectList("yearDmePbm.listSidoPbmDmeSum" , paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePbmDto> listGunguPbmDmeSum(HashMap<String, Object> paramMap) {
		return selectList("yearDmePbm.listGunguPbmDmeSum" , paramMap);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePbmDto> listGunguPbmDmeSumByYear(HashMap<String, Object> paramMap) {
		return selectList("yearDmePbm.listGunguPbmDmeSumByYear" , paramMap);
	}

}
