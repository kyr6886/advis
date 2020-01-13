package com.noaa.nema.viewer.year.dme.dao;

import java.util.HashMap;
import java.util.List;

public interface ICommonDataDao {
	public int countThissen(HashMap<String,Object> paramMap);
	
	public YearDmeDto totalDamage(HashMap<String,Object> paramMap);
	public List<YearDmeDto> listDmeWithGungu(HashMap<String,Object> paramMap);
	public List<YearDmeDto> listDmeWithGunguByArea(HashMap<String,Object> paramMap);
	public List<YearDmeDto> listDmeHisWithGungu(HashMap<String,Object> paramMap);
	public List<YearDmeDto> listDmeHisWithGunguByArea(HashMap<String,Object> paramMap);
	public String lookingLawArea(HashMap<String,Object> paramMap);
	public YearDmeDto maxDamagePersonByYear(HashMap<String,Object> paramMap);
	public YearDmeDto maxDamageMoneyByYear(HashMap<String,Object> paramMap);
	public List<YearDmeDto>  sumDamageByGungu(HashMap<String,Object> paramMap);
	public List<YearDmeDto>  listDamagePersonTop10(HashMap<String,Object> paramMap);
	public List<YearDmeDto>  listDamageMoneyTop10(HashMap<String,Object> paramMap);
	public List<YearDmeDto>  listGunguDmeCause(HashMap<String,Object> paramMap);
	public YearDmeDto detailSummary(HashMap<String, Object> paramMap);
	public List<YearDmeDto> listGunguDmeCauseResultSum(HashMap<String,Object> paramMap);
}
