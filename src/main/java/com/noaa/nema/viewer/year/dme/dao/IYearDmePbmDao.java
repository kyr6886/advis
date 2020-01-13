package com.noaa.nema.viewer.year.dme.dao;

import java.util.HashMap;
import java.util.List;



public interface IYearDmePbmDao {
	/**
	 * 사유시설 지역 전체 재해 목록 
	 * 시작일 종료일 재해구분 함께 표출
	 * @param paramMap
	 * @return
	 */

	public List<YearDmePbmDto> listPbm(HashMap<String, Object> paramMap);
	public List<YearDmePbmDto> detailPbm(HashMap<String, Object> paramMap);
	public List<YearDmePbmDto> listSidoPbmDme(HashMap<String, Object> paramMap);
	public List<YearDmePbmDto> listGunguPbmDme(HashMap<String, Object> paramMap);

	/**
	 * 시도별 기간 피해 sum
	 * @param paramMap
	 * @return
	 */
	public List<YearDmePbmDto> listSidoPbmDmeSum(HashMap<String, Object> paramMap);
	/**
	 * 시군구별기간 피해 sum
	 * @param paramMap
	 * @return
	 */
	public List<YearDmePbmDto> listGunguPbmDmeSum(HashMap<String, Object> paramMap);
	public List<YearDmePbmDto> listGunguPbmDmeSumByYear(HashMap<String, Object> paramMap);
	

}
