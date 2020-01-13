package com.noaa.nema.viewer.typhoon.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.typhoon.dao.IYearDmeTyphoonDao;
import com.noaa.nema.viewer.typhoon.dao.YearDmeTyphoonDto;
@Repository("yearDmeTyphoonDao")
public class YearDmeTyphoonDaoImpl extends BaseDao implements IYearDmeTyphoonDao {

	@Override
	public List<YearDmeTyphoonDto> listAll() {
		
		return selectList("yearDmeTyphoon.listAll",null);
	}

	@Override
	public int create(YearDmeTyphoonDto paramBean) {
		
		return 0;
	}

	@Override
	public int update() {
		return update("yearDmeTyphoon.updateEndDate",null);
	}
 
	@Override
	public int create() {
		return update("yearDmeTyphoon.create",null);
	}

}
