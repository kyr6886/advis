package com.noaa.nema.viewer.area.dao.impl;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.nema.viewer.area.dao.IAreaCodeDao;
import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;
import com.noaa.base.BaseDao;
@Repository("areaCodeDao")
public class AreaCodeDaoImpl extends BaseDao implements IAreaCodeDao {

	@Override
	public List<YearAreaCodeDto> listSido(HashMap<String, Object> paramMap) {
		
		return selectList("yearAreaCode.listSido",paramMap);
	}

	@Override
	public List<YearAreaCodeDto> listSiGungu(HashMap<String, Object> paramMap) {
		
		return selectList("yearAreaCode.listGungu",paramMap);
	}

}
