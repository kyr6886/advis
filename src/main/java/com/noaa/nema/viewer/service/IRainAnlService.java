package com.noaa.nema.viewer.service;

import java.util.List;

import com.noaa.nema.viewer.asos.dao.ObsAsosDto;
import com.noaa.nema.viewer.asos.dao.ObsAsosStDto;

public interface IRainAnlService {
	
	/**
	 * asos 해당군구의 paramRain 이상  비가 온 횟수
	 * @param paramGungu
	 * @param paramRain
	 * @return
	 */
	public List<ObsAsosDto> countGunguAsos(String paramGungu,int paramRain);
	
	/**
	 * asos 해당시도의 paramRain 이상  비가 온 횟수
	 * @param paramGungu
	 * @param paramRain
	 * @return
	 */
	public List<ObsAsosDto> countSidoAsos(String paramGungu,int paramRain);
	
	/**
	 * asos 지점정보
	 * @return
	 */
	public List<ObsAsosStDto> listAsosStation();
	
	
	/**
	 * 시작일 ~ 종료일에 해당하는 시도 관측 목록(티센보정)
	 * @param paramSido
	 * @param paramStDate
	 * @param paramEndDate
	 * @return
	 */
	public List<ObsAsosDto> listSidoAsos(String paramSido,String paramStDate,String paramEndDate);
	/**시작일 ~ 종료일에 해당하는 군구 관측 목록(티센보정)
	 * @param paramGungu
	 * @param paramStDate
	 * @param paramEndDate
	 * @return
	 */
	public List<ObsAsosDto> listGunguAsos(String paramGungu,String paramStDate,String paramEndDate);
	
	/**
	 * 기간 시도 최대 강우
	 * @param paramGungu
	 * @param paramStDate
	 * @param paramEndDate
	 * @return
	 */
	public double maxSidoAsos(String paramGungu,String paramStDate,String paramEndDate);
	/**
	 * 기간 시군구 최대 강우
	 * @param paramGungu
	 * @param paramStDate
	 * @param paramEndDate
	 * @return
	 */
	public double maxGunguAsos(String paramGungu,String paramStDate,String paramEndDate);
	/**
	 * 10년 시도 최대 강우
	 * @param paramGungu
	 * @return
	 */
	public double max10YearSidoAsos(String paramGungu);
	/**
	 * 10년 시군구 최대 강우
	 * @param paramGungu
	 * @return
	 */
	public double max10YearGunguAsos(String paramGungu);
	
	/**
	 * 강우구간에 해당하는 시군구 목록
	 * @param paramSido
	 * @param paramStRain
	 * @param paramEndRain
	 * @return
	 */
	public List<ObsAsosDto> listGunguSectorAsos(String paramSido,int paramStRain,int paramEndRain);
	
	/**
	 * 강우구간에 해당하는 시군구 및 재해연보 
	 * @param paramSido
	 * @param paramStRain
	 * @param paramEndRain
	 * @return
	 */
	public List<ObsAsosDto> listGunguSectorDamage(String paramSido, String paramStYear, String paramEndYear);
	
	public List<ObsAsosDto> listObsRnDay(String paramStDate, String paramRnDay);
}
