package com.noaa.nema.viewer.dao.impl;

import java.util.HashMap;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeDateDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDateDto;


@Repository("yearDmeDateDao")
public class YearDmeDateDaoImpl extends BaseDao implements IYearDmeDateDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmeDateDto> list(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return selectList("yearDmeDate.listDate", paramMap);
	}

	@Override
	public YearDmeDateDto detail(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return (YearDmeDateDto) selectList("yearDmeDate.detailDate", paramMap);
	}

}
