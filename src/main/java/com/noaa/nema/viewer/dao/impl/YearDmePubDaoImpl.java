package com.noaa.nema.viewer.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmePubDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmePbmDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmePubDto;

@Repository("yearDmePubDao")
public class YearDmePubDaoImpl extends BaseDao implements IYearDmePubDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePubDto> listPub(HashMap<String, Object> paramMap) {
		return selectList("yearDmePub.listPub", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePubDto> detailPub(HashMap<String, Object> paramMap) {
		return selectList("yearDmePub.detailPub", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePubDto> listSidoPubDme(HashMap<String, Object> paramMap) {
		return selectList("yearDmePub.listSidoPubDme", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePubDto> listGunguPubDme(HashMap<String, Object> paramMap) {
		return selectList("yearDmePub.listGunguPubDme", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePubDto> listSidoPubDmeSum(HashMap<String, Object> paramMap) {
		return selectList("yearDmePub.listSidoPubDmeSum", paramMap);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePubDto> listGunguPubDmeSum(HashMap<String, Object> paramMap) {
		return selectList("yearDmePub.listGunguPubDmeSum", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmePubDto> listGunguPubDmeSumByYear(HashMap<String, Object> paramMap) {
		return selectList("yearDmePub.listGunguPubDmeSumByYear", paramMap);
	}


}
