package com.noaa.nema.viewer.service;

import java.util.List;

import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;
import com.noaa.nema.viewer.typhoon.dao.TyphoonDamageDto;
import com.noaa.nema.viewer.typhoon.dao.YearDmeTyphoonDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;

public interface ITyphoonCastInfoService {
	
	
	/**
	 * 2012년 5월 이후에 일어난 태풍 목록(과거태풍)
	 * @param paramSidoCode, paramStYearMonth, paramEndYearMonth
	 * @return
	 */
	public List<TyphoonCastInfoDto> currentTyphoonInfoList(String paramYear, String paramMonth, String paramSeq);
	
	/**
	 * 2012년 5월 이전에 일어난 과거 태풍 목록(과거태풍)
	 * @param paramSidoCode, paramStYearMonth, paramEndYearMonth
	 * @return
	 */
	
	public List<TyphoonCastInfoDto> pastTyphoonInfoList(String paramYear, String paramMonth, String paramSeq);

	/**
	 * 태풍이 일어난 기간(월단위)에 일어난 시도의 공공, 사유시설 총피해액, 사유시설&공공시설 피해액 
	 * @param  paramYearMonth
	 * @return
	 */
	public List<YearDmeDto> listTyphoonCastDmeArea(String paramStDate, String paramEndDate);
	
	
	/**
	 *  선택된 과거태풍과 유사 기간 태풍(유사태풍)을 가져온다
	 * @param  paramStDate, paramEndDate, paramSeq
	 * @return
	 */
	public List<TyphoonCastInfoDto> currentSimilarTyphoonListByPeriod(String paramStDate, String paramEndDate, String paramSeq);
	
	/**
	 *  선택된 과거태풍과 유사 기간 태풍(유사태풍)을 가져온다
	 * @param  paramStDate, paramEndDate, paramSeq
	 * @return
	 */
	public List<TyphoonCastInfoDto> pastSimilarTyphoonListByPeriod(String paramStDate, String paramEndDate, String paramSeq);
	
	/**
	 *  재해연보 자료현황 중 태풍 목록에 태풍명을 더한다.
	 * @param  paramStYear, paramEndYear
	 * @return
	 */

	public List<TyphoonCastInfoDto> listTyphoonName(String paramStYear, String paramEndYear);
	public TyphoonCastInfoDto detailCurrentTyphoon();
	public List<TyphoonCastInfoDto> listSimilarTyphoonByPosition(String paramYear,String paramMonth,int paramTyphoonSeq);
	public List<TyphoonCastInfoDto> listSimilarTyphoonByForcast(String paramYear,String paramMonth,int paramTyphoonSeq,int paramTyphoonTmSeq);
	
	/**
	 *  각국 태풍관측정보를 가져온다 
	 * @param  paramStDate, paramEndDate, paramNatinalWeatherName, paramTyphoonName
	 * @return
	 */
	
	public List<TyphoonCastInfoDto> worldTyphoonHistoryList(String paramStDate, String paramEndDate, String paramNatinalWeatherName, String paramTyphoonName);
	
	/**
	 *  각국 태풍예측정보를 가져온다 
	 * @param  paramStDate, paramEndDate, paramNatinalWeatherName, paramTyphoonName
	 * @return
	 */
	
	public List<TyphoonCastInfoDto> worldTyphoonForecastList(String paramStDate, String paramEndDate, String paramNatinalWeatherName, String paramTyphoonName);
	public List<TyphoonCastInfoDto> worldTyphoonForecastByPeriod(String paramStDate, String paramEndDate);
	public List<TyphoonCastInfoDto> listTyphoonCurrentSummaryInfos(String paramStDate, String paramEndDate);
	
	public YearDmeTyphoonDto typhoonDateMapping(List<TyphoonCastInfoDto> paramTargetPosition,List<YearDmeTyphoonDto> paramInfos);
	public void typhoonDamageMapping(TyphoonCastInfoDto paramTarget);
	public List<YearDmeTyphoonDto> listAllYearDmeTyphoon();
	public void createCurrentTyphoonInfo();

	public List<YearDmeDto> listDamageTyphoon(String stDate, String endDate);
	public List<TyphoonDamageDto> listDamageTyphoon(double paramStDamageMoney,double paramEndDamageMoney,double paramStDamagePerson,double paramEndDamagePerson);
	
	public TyphoonCastInfoDto detailLastPosition(String paramYear, String paramMonth, String paramName);
	public List<TyphoonCastInfoDto> listTyphoonByPeriodAction(String paramStDate);
}
