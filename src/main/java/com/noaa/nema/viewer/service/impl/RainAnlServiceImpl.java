package com.noaa.nema.viewer.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.nema.viewer.asos.dao.IObsAsosDao;
import com.noaa.nema.viewer.asos.dao.ObsAsosDto;
import com.noaa.nema.viewer.asos.dao.ObsAsosStDto;
import com.noaa.nema.viewer.service.IRainAnlService;
import com.noaa.nema.viewer.year.dme.dao.IYearDmePbmDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmePubDao;
import com.noaa.nema.viewer.base.ViewerSysKeyword;



@Service("rainAnlService")
public class RainAnlServiceImpl implements IRainAnlService {

	@Autowired
	private IObsAsosDao obsAsosDao;
	
	@Autowired
	private IYearDmePbmDao yearDmePbmDao;
	
	@Autowired
	private IYearDmePubDao yearDmePubDao;
	
	@Override
	public List<ObsAsosDto> countGunguAsos(String paramGungu, int paramRain) {
		
		List<ObsAsosDto> rainList = null;

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramGungu);
		paramMap.put("rn_day", paramRain);
		
		rainList = obsAsosDao.countGunguAsos(paramMap);
		
		return rainList;
	}

	@Override
	public List<ObsAsosDto> countSidoAsos(String paramGungu, int paramRain) {
		
		List<ObsAsosDto> rainList = null;
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramGungu);
		paramMap.put("rn_day", paramRain);

		rainList = obsAsosDao.countGunguAsos(paramMap);
		
		return rainList;
	}

	@Override
	public List<ObsAsosStDto> listAsosStation() {
		
		List<ObsAsosStDto> rainList = null;
		rainList = obsAsosDao.obsList();

		return rainList;
	}

	@Override
	public List<ObsAsosDto> listSidoAsos(String paramSido, String paramStDate, String paramEndDate) {

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido);
		paramMap.put("beg_date", paramStDate.replaceAll("-", ""));
		paramMap.put("end_date", paramEndDate.replaceAll("-", ""));

		List<ObsAsosDto> rainList = null;
		rainList = obsAsosDao.listRainSido(paramMap);
		
		
		

		return rainList;
	}

	@Override
	public List<ObsAsosDto> listGunguAsos(String paramGungu, String paramStDate, String paramEndDate) {

		
	
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramGungu);
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);

		List<ObsAsosDto> rainList = null;
		rainList = obsAsosDao.listRainGungu(paramMap);
		

		return rainList;
	}

	@Override
	public double maxSidoAsos(String paramGungu, String paramStDate, String paramEndDate) {

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramGungu);
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		
		double result_cnt = 0;
		List<ObsAsosDto> rainList = null;
		rainList = obsAsosDao.maxSidoAsos(paramMap);
		
		for(ObsAsosDto list : rainList){
			if(paramGungu.equals(list.getLaw_code())){
				result_cnt = list.getRn_day_max();
			}
		}
	
		return result_cnt;
	}

	@Override
	public double maxGunguAsos(String paramGungu, String paramStDate, String paramEndDate) {

		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramGungu);
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		
		double result_cnt = 0;
		List<ObsAsosDto> rainList = null;
		rainList = obsAsosDao.maxSidoAsos(paramMap);
		
		for(ObsAsosDto list : rainList){
			if(paramGungu.equals(list.getLaw_code())){
				result_cnt = list.getRn_day_max();
			}
		}
	
		return result_cnt;
	}

	@Override
	public double max10YearSidoAsos(String paramGungu) {
	
		SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
		String paramStDate = "";
		String paramEndDate = "";
		
		
		Calendar past = Calendar.getInstance();
		past.add(past.YEAR, -10);
		Calendar today = Calendar.getInstance();

		paramStDate = endDate.format(past.getTime());
		paramEndDate = 	endDate.format(today.getTime());
		
	
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramGungu);
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		
		double result_cnt = 0;
		List<ObsAsosDto> rainList = null;
		rainList = obsAsosDao.maxSidoAsos(paramMap);
		
		for(ObsAsosDto list : rainList){
			if(paramGungu.equals(list.getLaw_code())){
				result_cnt = list.getRn_day_max();
			}
		}
	
		return result_cnt;
	}

	@Override
	public double max10YearGunguAsos(String paramGungu) {
		SimpleDateFormat endDate = new SimpleDateFormat("yyyyMMdd");
		String paramStDate = "";
		String paramEndDate = "";
		
		
		Calendar past = Calendar.getInstance();
		past.add(past.YEAR, -10);
		Calendar today = Calendar.getInstance();

		paramStDate = endDate.format(past.getTime());
		paramEndDate = 	endDate.format(today.getTime());
		
	
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramGungu);
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		
		double result_cnt = 0;
		List<ObsAsosDto> rainList = null;
		rainList = obsAsosDao.maxSidoAsos(paramMap);
		
		for(ObsAsosDto list : rainList){
			if(paramGungu.equals(list.getLaw_code())){
				result_cnt = list.getRn_day_max();
			}
		}
	
		return result_cnt;
	}

	@Override
	public List<ObsAsosDto> listGunguSectorAsos(String paramSido, int paramStRain, int paramEndRain) {
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("law_code", paramSido);
		
		if(paramStRain > 0 && paramEndRain < ViewerSysKeyword.RAIN_SECTION_END_1){
			
			paramStRain = 0;
			paramEndRain = ViewerSysKeyword.RAIN_SECTION_END_1;
			paramMap.put("pre_rn_day", paramStRain);
			paramMap.put("end_rn_day", paramEndRain);
			
		}else if(paramStRain > ViewerSysKeyword.RAIN_SECTION_END_1 && paramEndRain < ViewerSysKeyword.RAIN_SECTION_ST_2){
			
			paramStRain = ViewerSysKeyword.RAIN_SECTION_END_1;
			paramEndRain = ViewerSysKeyword.RAIN_SECTION_ST_2;
			paramMap.put("pre_rn_day", paramStRain);
			paramMap.put("end_rn_day", paramEndRain);
				
		}else if(paramStRain > ViewerSysKeyword.RAIN_SECTION_ST_2 && paramEndRain < ViewerSysKeyword.RAIN_SECTION_ST_3){
			
			paramStRain =  ViewerSysKeyword.RAIN_SECTION_ST_2;
			paramEndRain = ViewerSysKeyword.RAIN_SECTION_ST_3;
			paramMap.put("pre_rn_day", paramStRain);
			paramMap.put("end_rn_day", paramEndRain);
		
		}else{
			paramStRain =  ViewerSysKeyword.RAIN_SECTION_ST_3;
			paramEndRain = ViewerSysKeyword.RAIN_SECTION_END_2;
			paramMap.put("pre_rn_day", paramStRain);
			paramMap.put("end_rn_day", paramEndRain);
		}
		
		List<ObsAsosDto> rainList = null;
		rainList = obsAsosDao.listRainSido(paramMap);
		
		
		return rainList;
	}

	@Override
	public List<ObsAsosDto> listGunguSectorDamage(String paramSido, String paramStYear, String paramEndYear) {
		List<ObsAsosDto> rs=new ArrayList<ObsAsosDto>();
		HashMap<String, Object> paramMap = new HashMap<>();
		paramStYear=paramStYear.substring(0,4)+"0101";
		paramEndYear=paramEndYear.substring(0,4)+"1231";
		paramMap.put("law_code", paramSido);
		paramMap.put("beg_date", paramStYear);
		paramMap.put("end_date", paramEndYear);
		paramMap.put("pre_rn_day",10);
		paramMap.put("end_rn_day", ViewerSysKeyword.RAIN_SECTION_END_1);
		rs.addAll(obsAsosDao.listRainGungu(paramMap));	
		paramMap.put("pre_rn_day", ViewerSysKeyword.RAIN_SECTION_ST_2);
		paramMap.put("end_rn_day", ViewerSysKeyword.RAIN_SECTION_ST_3);
		rs.addAll(obsAsosDao.listRainGungu(paramMap));
		paramMap.put("pre_rn_day", ViewerSysKeyword.RAIN_SECTION_ST_3);
		paramMap.put("end_rn_day", ViewerSysKeyword.RAIN_SECTION_ST_4);
		rs.addAll(obsAsosDao.listRainGungu(paramMap));
		paramMap.put("pre_rn_day", ViewerSysKeyword.RAIN_SECTION_ST_4);
		paramMap.put("end_rn_day", ViewerSysKeyword.RAIN_SECTION_END_2);
		rs.addAll(obsAsosDao.listRainGungu(paramMap));
		return null;
	}
	
	@Override
	public List<ObsAsosDto> listObsRnDay(String paramStDate, String paramRnDay){
		List<ObsAsosDto> rs = new ArrayList<ObsAsosDto>();
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("rn_day",paramRnDay);
		rs = obsAsosDao.listObsRnDay(paramMap);
		return rs;
	}

}
