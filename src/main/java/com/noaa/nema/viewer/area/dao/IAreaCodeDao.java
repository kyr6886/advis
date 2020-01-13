package com.noaa.nema.viewer.area.dao;

import java.util.HashMap;
import java.util.List;

public interface IAreaCodeDao {
	public List<YearAreaCodeDto> listSido(HashMap<String,Object> paramMap);
	public List<YearAreaCodeDto> listSiGungu(HashMap<String,Object> paramMap);
}
