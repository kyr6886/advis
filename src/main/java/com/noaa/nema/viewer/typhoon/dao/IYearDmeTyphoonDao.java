package com.noaa.nema.viewer.typhoon.dao;

import java.util.List;

public interface IYearDmeTyphoonDao {
	public List<YearDmeTyphoonDto> listAll();
	public int create();
	public int create(YearDmeTyphoonDto paramBean);
	public int update();
}
