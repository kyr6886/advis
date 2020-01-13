package com.noaa.nema.viewer.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeSummaryDto;


@Repository("yearDmeDao")
public class YearDmeDaoImpl extends BaseDao implements IYearDmeDao {


	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmeDto> listSidoTot(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listSidoTot", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmeDto> listGunguTot(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listGunguTot", paramMap);
	}

	@Override
	public List<YearDmeDto> listDamageByYear(HashMap<String, Object> paramMap) {
		
		return selectList("yearDme.listDamageByYear", paramMap);
	}
	@Override
	public YearDmeDto damageItemSum(HashMap<String, Object> paramMap) {
		
		return (YearDmeDto)selectOne("yearDme.damageItemSum", paramMap);
	}

	@Override
	public List<YearDmeDto> listComDmeGunguTop10(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listComDmeGunguTop10", paramMap);
	}
	
	@Override
	public List<YearDmeDto> listComDmeRainSidoJoinByGungu(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listComDmeRainSidoJoinByGungu", paramMap);
	}

	@Override
	public List<YearDmeDto> listTotalDmeTop10(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listTotalDmeGunguTop10", paramMap);
	}

	@Override
	public List<YearDmeDto> listTotalDmeRainSidoJoinByGungu(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listTotalDmeRainSidoJoinByGungu", paramMap);
	}

	@Override
	public List<YearDmeDto> listTotalDmeChartByYear(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listTotalDmeChartByYear", paramMap);
	}

	@Override
	public List<YearDmeDto> listComDmeAndTotalDmeByYear(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listComDmeAndTotalDmeByYear", paramMap);
	}


	@Override
	public List<YearDmeDto> listDisasterMonthlyCount(HashMap<String,Object> paramMap) {
		return selectList("yearDme.listDisMonthlyCount",paramMap);
	}
	
	@Override
	public YearDmeDto maxDisasterByMonth(HashMap<String, Object> paramMap) {
		return (YearDmeDto)selectOne("yearDme.maxDisasterByMonth",paramMap);
	} 

	@Override
	public List<YearDmeDto>  listAreaDamageSum(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listAreaDamageSum",paramMap);
	}

	@Override
	public List<YearDmeDto> listYearCount(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listDisEachYearCount",paramMap);
	}

	@Override
	public List<YearDmeDto> listAllSigunguDamageAndRain(HashMap<String, Object> paramMap) {
		return selectList("commonData.listAllSigunguDamageAndRain",paramMap);
	} 
	
	@Override
	public List<YearDmeDto> listAllSidoDamageAndRain(HashMap<String, Object> paramMap) {
		return selectList("commonData.listAllSidoDamageAndRain",paramMap);
	}

	@Override
	public int insertSummeryList(YearDmeSummaryDto paramDto) {
		return (int)insert("commonData.insertSummeryList", paramDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmeDto> listTyphoonDmeList(HashMap<String, Object> paramMap) {
		return selectList("yearDme.listTyphoonDmeList",paramMap);
	}

	@Override
	public List<YearDmeDto> listYearDme(HashMap<String, Object> paramMap) {
		return selectList("yearDmeMapper.listYearDme",paramMap);
	}
}