package com.noaa.nema.viewer.typhoon.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.typhoon.dao.ITyphoonDamageDao;
import com.noaa.nema.viewer.typhoon.dao.TyphoonDamageDto;

@Repository("typhoonDamageDao")
public class TyphoonDamageDaoImpl extends BaseDao implements ITyphoonDamageDao{

	@Override
	public List<TyphoonDamageDto> list(HashMap<String, Object> paramMap) {
		
		return selectList("commonData.listTyphoonWithDamage",paramMap);
	}

}
