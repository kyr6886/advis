package com.noaa.nema.viewer.kamInform.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.kmaInform.dao.IKmaAwsDao;
import com.noaa.nema.viewer.kmaInform.dao.KmaAwsDto;
@Repository("kmaAwsDao")
public class KmaAwsDaoImpl extends BaseDao implements IKmaAwsDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<KmaAwsDto> list(HashMap<String, Object> paramMap) {
		return selectList("kmaAws.awsList", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KmaAwsDto> awsGunguList(HashMap<String, Object> paramMap) {
		return selectList("kmaAws.awsGunguHourAndDay", paramMap);
	}

	@Override
	public String awsMaxYmdh(HashMap<String, Object> paramMap) {
		return (String) selectOne("kmaAws.awsMaxYmdh", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public KmaAwsDto obsWaterAwsDataList(HashMap<String, Object> paramMap) {
		return (KmaAwsDto) selectOne("kmaAws.obsWaterAwsDataList", paramMap);
	}



}
