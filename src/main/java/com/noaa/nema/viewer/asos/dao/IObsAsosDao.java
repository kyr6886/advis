package com.noaa.nema.viewer.asos.dao;

import java.util.HashMap;
import java.util.List;

public interface IObsAsosDao {
	
	public List<ObsAsosDto> countGunguAsos(HashMap<String,Object> paramMap);
	public List<ObsAsosDto> countSidoAsos(HashMap<String,Object> paramMap);
	public List<ObsAsosStDto> obsList();
	public List<ObsAsosDto> listRainSido(HashMap<String,Object> paramMap);
	public List<ObsAsosDto> listRainGungu(HashMap<String,Object> paramMap);
	public List<ObsAsosDto> maxSidoAsos(HashMap<String,Object> paramMap);
	public List<ObsAsosDto> maxGunguAsos(HashMap<String,Object> paramMap);
	public ObsAsosDto maxObsRnDay(HashMap<String,Object> paramMap);
	public List<ObsAsosDto> listObsRnDay(HashMap<String,Object> paramMap);
	
}
