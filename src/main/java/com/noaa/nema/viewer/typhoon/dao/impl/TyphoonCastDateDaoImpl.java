package com.noaa.nema.viewer.typhoon.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastDateDto;
import com.noaa.nema.viewer.typhoon.dao.ITyphoonCastDateDao;

@Repository("typCastDataDao")
public class TyphoonCastDateDaoImpl extends BaseDao implements ITyphoonCastDateDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TyphoonCastDateDto> listCastTyphoonYear() {
		return selectList("typCastDate.listCastTyphoonYear");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TyphoonCastDateDto> listCastTyphoonMonth(HashMap<String, Object> paramMap) {
		return selectList("typCastDate.listCastTyphoonMonth",paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TyphoonCastDateDto> listCastTyphoonName(HashMap<String, Object> paramMap) {
		return selectList("typCastDate.listCastTyphoonName",paramMap);
	}


}
