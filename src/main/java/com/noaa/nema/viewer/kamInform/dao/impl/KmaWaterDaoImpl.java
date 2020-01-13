package com.noaa.nema.viewer.kamInform.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.kmaInform.dao.IKmaWaterDao;
import com.noaa.nema.viewer.kmaInform.dao.KmaWaterDto;
@Repository("kmaWaterDao")
public class KmaWaterDaoImpl extends BaseDao implements IKmaWaterDao {

	@Override
	public List<KmaWaterDto> kmaWaterObsList(HashMap<String, Object> paramMap) {
		return selectList("kmaWater.kmaWaterObsList", paramMap);
	}
	

	@Override
	public KmaWaterDto kmaWaterDamList(HashMap<String, Object> paramMap) {
		return (KmaWaterDto) selectOne("kmaWater.kmaWaterDamList", paramMap);
	}
	
	@Override
	public String obsWaterMaxYmdh(HashMap<String, Object> paramMap) {
		return (String) selectOne("kmaWater.maxWaterObsDate", paramMap);
	}
	

	@Override
	public String obsDamMaxYmdh(HashMap<String, Object> paramMap) {
		return (String) selectOne("kmaWater.maxWaterDamDate", paramMap);
	}

}
