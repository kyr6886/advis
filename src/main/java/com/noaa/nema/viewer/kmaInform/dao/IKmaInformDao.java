package com.noaa.nema.viewer.kmaInform.dao;

import java.util.HashMap;
import java.util.List;

public interface IKmaInformDao {
	public KmaInformDto detailCurrent(HashMap<String,Object> paramMap);
	public List<KmaInformDto> list(HashMap<String,Object> paramMap);
	public List<KmaLocDto> listKmaLocation(HashMap<String,Object> paramMap);
	public int updateKmaRainType(HashMap<String,Object> paramMap);
	public int updateKmaRainTypeInit(HashMap<String,Object> paramMap);
	public int updateKmaTyphoonType(HashMap<String,Object> paramMap);
	public int updateKmaTyphoonTypeInit(HashMap<String,Object> paramMap);
	
}
