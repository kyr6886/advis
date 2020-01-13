package com.noaa.nema.viewer.year.dme.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.year.dme.dao.ICommonDataDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmePbmDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmePubDto;
@Repository("commonDataDao")
public class CommonDataDaoImpl extends BaseDao implements ICommonDataDao {

	@Override
	public int countThissen(HashMap<String, Object> paramMap) {

		return (Integer)selectOne("commonData.countThissens", paramMap);
	}

	@Override
	public List<YearDmeDto> listDmeWithGungu(HashMap<String, Object> paramMap) {

		return selectList("commonData.listDmeWithGungu",paramMap);
	}

	@Override
	public List<YearDmeDto> listDmeWithGunguByArea(HashMap<String, Object> paramMap) {
		return selectList("commonData.listDmeWithGunguByArea",paramMap);
	}

	@Override
	public List<YearDmeDto> listDmeHisWithGungu(HashMap<String, Object> paramMap) {
		return selectList("commonData.listDmeHisWithGungu",paramMap);
	}

	@Override
	public List<YearDmeDto> listDmeHisWithGunguByArea(HashMap<String, Object> paramMap) {
		return selectList("commonData.listDmeHisWithGunguByArea",paramMap);
	}

	@Override
	public YearDmeDto totalDamage(HashMap<String, Object> paramMap) {
		return (YearDmeDto)selectOne("commonData.totalDamageByPeriod",paramMap);
	}

	@Override
	public String lookingLawArea(HashMap<String, Object> paramMap) {
		
		return (String) selectOne("commonData.lookingLawArea", paramMap) ;
	}

	@Override
	public YearDmeDto maxDamagePersonByYear(HashMap<String, Object> paramMap) {
		return (YearDmeDto)selectOne("commonData.maxDamagePersonByYear",paramMap);
	}

	@Override
	public YearDmeDto maxDamageMoneyByYear(HashMap<String, Object> paramMap) {
		return (YearDmeDto)selectOne("commonData.maxDamageMoneyByYear",paramMap);
	}
	@Override
	public List<YearDmeDto> sumDamageByGungu(HashMap<String, Object> paramMap) {
		return selectList("commonData.sumDamageByGungu",paramMap);
	}

	@Override
	public List<YearDmeDto> listDamagePersonTop10(HashMap<String, Object> paramMap) {
		return selectList("commonData.listDamagePersonTop10",paramMap);
	}

	@Override
	public List<YearDmeDto> listDamageMoneyTop10(HashMap<String, Object> paramMap) {
		return selectList("commonData.listDamageMoneyTop10",paramMap);
	}

	@Override
	public List<YearDmeDto> listGunguDmeCause(HashMap<String, Object> paramMap) {
		return selectList("commonData.listGunguDmeCause",paramMap);
	}
	@Override
	public YearDmeDto detailSummary(HashMap<String, Object> paramMap) {
		return (YearDmeDto)selectOne("commonData.detailSummary",paramMap);
	}

	@Override
	public List<YearDmeDto> listGunguDmeCauseResultSum(HashMap<String, Object> paramMap) {
		return selectList("commonData.listGunguDmeCauseResultSum",paramMap);
	}

}