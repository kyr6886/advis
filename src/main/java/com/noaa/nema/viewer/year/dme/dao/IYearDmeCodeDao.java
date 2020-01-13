package com.noaa.nema.viewer.year.dme.dao;

import java.util.HashMap;
import java.util.List;



public interface IYearDmeCodeDao {
	/**
	 * 코드 그룹에 속하는 코드 리스트
	 * @param paramMap
	 * @return
	 */
	public List<YearDmeCodeDto> list(HashMap<String,Object> paramMap);
	public List<YearDmeCodeDto> listCode(HashMap<String,Object> paramMap);
	/**
	 * 재해연보 내 코드 리스트
	 * @param paramMap
	 * @return
	 */
	public List<YearDmeCodeDto> listDmeCode(HashMap<String, Object> paramMap);

	public List<YearDmeCodeDto> listDmeSidoCode(HashMap<String, Object> paramMap);
	
	public List<YearDmeCodeDto> listDmeGunguCode(HashMap<String, Object> paramMap);
	
	public List<YearDmeCodeDto> listTyphoonDmeSidoCode(HashMap<String, Object> paramMap);

	

}
