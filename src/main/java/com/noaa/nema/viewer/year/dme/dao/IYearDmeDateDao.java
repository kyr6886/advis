package com.noaa.nema.viewer.year.dme.dao;

import java.util.HashMap;
import java.util.List;



public interface IYearDmeDateDao {
	/**
	 * 파라미터로 전달 받은 날짜가 속하는 재해일 
	 * @param paramMap
	 * @return
	 */
	public List<YearDmeDateDto> list(HashMap<String,Object> paramMap);
	/**
	 * 상세
	 * @param paramMap
	 * @return
	 */
	public YearDmeDateDto detail(HashMap<String,Object> paramMap);
}
