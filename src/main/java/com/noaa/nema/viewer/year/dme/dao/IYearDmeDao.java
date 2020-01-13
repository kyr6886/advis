package com.noaa.nema.viewer.year.dme.dao;

import java.util.HashMap;
import java.util.List;

public interface IYearDmeDao {
	
	/**
	 * 공공시설 및 사유시설 일별 시도 전체 재해 목록 
	 * 시작일 종료일 재해구분 표출
	 * @param paramMap
	 * @return
	 */

	public List<YearDmeDto> listSidoTot(HashMap<String, Object> paramMap);
	
	
	/**
	 * 공공시설 및 사유시설 일별 시도 전체 재해 목록 
	 * 시작일 종료일 재해구분 표출
	 * @param paramMap
	 * @return
	 */
	public List<YearDmeDto> listGunguTot(HashMap<String, Object> paramMap);
	public List<YearDmeDto> listDamageByYear(HashMap<String, Object> paramMap);
	public YearDmeDto damageItemSum(HashMap<String, Object> paramMap);
	
	
	/**
	 * 인명피해별 시군구 top 10
	 * 시작일 종료일 총인명피해, 재해발생일인명피해 ,피해금액, 최대일강우, 최대시강우
	 * @param paramMap
	 * @return
	 */
	
	public List<YearDmeDto> listComDmeGunguTop10(HashMap<String, Object> paramMap);
	
	/**
	 * 인명피해별 시군구 top 10에서 시도부분 join
	 * 시작일 종료일 총인명피해, 재해발생일인명피해 ,피해금액, 최대일강우, 최대시강우
	 * @param paramMap
	 * @return
	 */
	
	
	public List<YearDmeDto> listComDmeRainSidoJoinByGungu(HashMap<String, Object> paramMap);

	
	/**
	 * 피해액별 시군구 top 10
	 * 시작일 종료일 총인명피해, 재해발생일인명피해 ,피해금액, 최대일강우
	 * @return
	 */
	
	public List<YearDmeDto> listTotalDmeTop10(HashMap<String, Object> paramMap);
	
	/**
	 * 피해액별 시군구 top 10에서 시도부분 join
	 * 시작일 종료일 총인명피해, 재해발생일인명피해 ,피해금액, 최대일강우, 최대시강우
	 * @param paramMap
	 * @return
	 */
	
	public List<YearDmeDto> listTotalDmeRainSidoJoinByGungu(HashMap<String, Object> paramMap);

	/**
	 * 피해액별 시군구 top 10에서 시도부분 join
	 * 시작일 종료일 총인명피해, 재해발생일인명피해 ,피해금액, 최대일강우, 최대시강우
	 * @param paramMap
	 * @return
	 */
	
	
	public List<YearDmeDto> listTotalDmeChartByYear(HashMap<String, Object> paramMap);
	
	
	/**
	 * 인명피해별 및 피해액 날짜별 전체 리스트
	 * 재난코드, 재난명, 시작일 종료일  해당일인명피해 , 해당일피해금액
	 * @param paramMap
	 * @return
	 */
	
	public List<YearDmeDto> listComDmeAndTotalDmeByYear(HashMap<String, Object> paramMap);
	
	
	
	public List<YearDmeDto> listDisasterMonthlyCount(HashMap<String,Object> paramMap) ;
	
	
	public YearDmeDto maxDisasterByMonth(HashMap<String, Object> paramMap) ;
	
	
	public List<YearDmeDto>  listAreaDamageSum(HashMap<String, Object> paramMap) ;
	
	
	public List<YearDmeDto> listYearCount(HashMap<String, Object> paramMap) ;
	
	public List<YearDmeDto> listAllSigunguDamageAndRain(HashMap<String, Object> paramMap);
	public List<YearDmeDto> listAllSidoDamageAndRain(HashMap<String, Object> paramMap);
	public int insertSummeryList(YearDmeSummaryDto paramDto);


	public List<YearDmeDto> listTyphoonDmeList(HashMap<String, Object> paramMap);
	public List<YearDmeDto> listYearDme(HashMap<String, Object> paramMap);
}