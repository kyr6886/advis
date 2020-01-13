package com.noaa.nema.viewer.kamInform.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.kmaInform.dao.IKmaInformDao;
import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;
import com.noaa.nema.viewer.kmaInform.dao.KmaLocDto;
@Repository("kmaInformDao")
public class KmaInformDaoImpl extends BaseDao  implements IKmaInformDao {

	@Override
	public KmaInformDto detailCurrent(HashMap<String,Object> paramMap) {
	
		return (KmaInformDto)selectOne("kmaInform.current",paramMap);
	}

	@Override
	public List<KmaInformDto> list(HashMap<String, Object> paramMap) {
		return selectList("kmaInform.list",paramMap);
	}
	@Override
	public List<KmaLocDto> listKmaLocation(HashMap<String, Object> paramMap) {
		return selectList("kmaInform.listKmaLocCode",paramMap);
	}

	@Override
	public int updateKmaRainType(HashMap<String, Object> paramMap) {
		return update("kmaInform.updateKmaRainType",paramMap);
	}

	@Override
	public int updateKmaRainTypeInit(HashMap<String, Object> paramMap) {
		return update("kmaInform.updateKmaRainTypeInit",paramMap);
	}
	
	@Override
	public int updateKmaTyphoonType(HashMap<String, Object> paramMap) {
		return update("kmaInform.updateKmaTyphoonType",paramMap);
	}

	@Override
	public int updateKmaTyphoonTypeInit(HashMap<String, Object> paramMap) {
		return update("kmaInform.updateKmaTyphoonTypeInit",paramMap);
	}
}
