package com.noaa.nema.viewer.area.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.nema.viewer.area.dao.IAreaCodeDao;
import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;
import com.noaa.nema.viewer.area.service.IAreaCodeService;
@Service("areaCodeService")
public class AreaCodeServiceImpl implements IAreaCodeService {

	@Autowired
	private IAreaCodeDao areaCodeDao;
	@Override
	public List<YearAreaCodeDto> listSido() {
		List<YearAreaCodeDto> rs=null;
		rs=areaCodeDao.listSido(null);
		
		return rs;
	}

	@Override
	public List<YearAreaCodeDto> listGungu(String paramSido) {
		List<YearAreaCodeDto> rs=null;
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		if(paramSido!=null){
		paramMap.put("sido",paramSido.substring(0,2));
		}
		rs=areaCodeDao.listSiGungu(paramMap);
		
		return rs;
	}
	
	
}
