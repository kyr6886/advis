package com.noaa.nema.viewer.typhoon.dao;

import java.util.HashMap;
import java.util.List;

public interface ITyphoonCastDateDao {

	
	public List<TyphoonCastDateDto> listCastTyphoonYear();
	public List<TyphoonCastDateDto> listCastTyphoonMonth(HashMap<String, Object> paramMap);
	public List<TyphoonCastDateDto> listCastTyphoonName(HashMap<String, Object> paramMap);


}
