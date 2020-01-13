package com.noaa.nema.viewer.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.asos.dao.IObsAsosDao;
import com.noaa.nema.viewer.asos.dao.ObsAsosDto;
import com.noaa.nema.viewer.asos.dao.ObsAsosStDto;



@Repository("obsAsosDao")
public class ObsAsosDaoImpl extends BaseDao implements IObsAsosDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ObsAsosStDto> obsList() {
		// TODO Auto-generated method stub
		return selectList("obsAsosData.listObs");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ObsAsosDto> listRainSido(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return selectList("obsAsosData.listRainSido", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<ObsAsosDto> countGunguAsos(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return  selectList("obsAsosData.countGunguAsosOverLap", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public  List<ObsAsosDto> countSidoAsos(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return  selectList("obsAsosData.countSidoAsosOverLap", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObsAsosDto> listRainGungu(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return selectList("obsAsosData.listRainGungu", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObsAsosDto> maxSidoAsos(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return selectList("obsAsosData.maxSidoAsos", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObsAsosDto> maxGunguAsos(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return selectList("obsAsosData.maxGunguAsos", paramMap);
	}

	@Override
	public ObsAsosDto maxObsRnDay(HashMap<String, Object> paramMap) {
		return (ObsAsosDto)selectOne("obsAsosData.maxObsRnDay", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObsAsosDto> listObsRnDay(HashMap<String, Object> paramMap) {
		return selectList("obsAsosData.listObsRnDay", paramMap);
	}



}
