package com.noaa.nema.viewer.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.nema.viewer.service.ITyphoonCastDateService;
import com.noaa.nema.viewer.typhoon.dao.ITyphoonCastDateDao;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastDateDto;


@Service("typCastDateService")
public class TyphoonCastDateServiceImpl implements ITyphoonCastDateService{
	@Autowired
	private ITyphoonCastDateDao typCastDateDao;
	
	@Override
	public List<TyphoonCastDateDto> listCastTyphoonYear() {
		List<TyphoonCastDateDto> rs = null;
		 
		rs = typCastDateDao.listCastTyphoonYear();
		 
		return rs;
	}

	@Override
	public List<TyphoonCastDateDto> listCastTyphoonMonth(String paramYear) {
		List<TyphoonCastDateDto> rs = null;		
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beg_year", paramYear);
		
		rs = typCastDateDao.listCastTyphoonMonth(paramMap);
		
		return rs;
	}

	@Override
	public List<TyphoonCastDateDto> listCastTyphoonName(String paramYear, String paramMonth) {
		List<TyphoonCastDateDto> rs = null;	
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beg_year", paramYear);
		paramMap.put("beg_month", paramMonth);
	
		rs = typCastDateDao.listCastTyphoonName(paramMap);
		
		return rs;
	}

	

}
