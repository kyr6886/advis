package com.noaa.nema.viewer.year.dme.dao;

import java.util.HashMap;
import java.util.List;



public interface IYearDmePubDao {
	/**
	 * 공공시설 지역 전체 재해 목록 
	 * 시작일 종료일 재해구분 함께 표출
	 * @param paramMap
	 * @return
	 */
	public List<YearDmePubDto> listPub(HashMap<String, Object> paramMap);
	public List<YearDmePubDto> detailPub(HashMap<String, Object> paramMap);
	public List<YearDmePubDto> listSidoPubDme(HashMap<String, Object> paramMap);
	public List<YearDmePubDto> listGunguPubDme(HashMap<String, Object> paramMap);
	
	public List<YearDmePubDto> listSidoPubDmeSum(HashMap<String, Object> paramMap);
	public List<YearDmePubDto> listGunguPubDmeSum(HashMap<String, Object> paramMap);
	public List<YearDmePubDto> listGunguPubDmeSumByYear(HashMap<String, Object> paramMap);
	
	

}
