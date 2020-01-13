package com.noaa.nema.viewer.service;

import java.util.List;

import com.noaa.nema.viewer.typhoon.dao.TyphoonCastDateDto;

public interface ITyphoonCastDateService {
	
	
	/**
	 * 태풍 발생 selectbox 년도 조회
	 * @param 
	 * @return
	 */
	public List<TyphoonCastDateDto> listCastTyphoonYear();
	
	/**
	 * 태풍 발생 selectbox 월별 조회
	 * @param paramYear
	 * @return
	 */
	public List<TyphoonCastDateDto> listCastTyphoonMonth(String paramYear);
	
	/**
	 * 태풍 발생 selectbox 태풍이름 조회
	 * @param paramYear, paramMonth
	 * @return
	 */

	public List<TyphoonCastDateDto> listCastTyphoonName(String paramYear, String paramMonth);
	

}
