package com.noaa.nema.viewer.area.service;

import java.util.List;

import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;

public interface IAreaCodeService {
	public List<YearAreaCodeDto> listSido();
	public List<YearAreaCodeDto> listGungu(String paramSido);

}
