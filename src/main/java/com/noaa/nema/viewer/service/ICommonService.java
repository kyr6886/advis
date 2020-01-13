package com.noaa.nema.viewer.service;

import java.util.HashMap;
import java.util.List;

import com.noaa.nema.viewer.area.dao.SectorDamageModel;
import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;


public interface ICommonService {
public int countThissen(String paramSiguguCode);
	
	/**
	 * 시군구별 기간 sum
	 * @param paramSidoCode
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public List<YearDmeDto> listDmeWithGungu(String paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode);
	public List<YearDmeDto> listDmeWithGungu(String paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode,String paramDamageName);
	public List<YearDmeDto> listDmeWithGungu(String paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode,String paramStRainValure,String paramEndRainValue);
	public List<YearDmeDto> listDmeWithGungu(String paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode,String paramStRainValure,String paramEndRainValue,String paramDamageName);
	/**
	 * 시군구별 기간 sum (in query)
	 * 티세 시군구 미제공
	 * @param paramGunguCode
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public List<YearDmeDto> listDmeWithGungu(List<String> paramGunguCode,String paramStDate,String paramEndDate,String paramDemCode,String paramDamageName);
	public List<YearDmeDto> listDmeWithGungu(List<String> paramGunguCode,String paramStDate,String paramEndDate,String paramDemCode,String paramStRainValure,String paramEndRainValue,String paramDamageName);
	public List<YearDmeDto> listDmeWithGungu(List<String> paramGunguCode,String paramStDate,String paramEndDate,String paramDemCode);
	public List<YearDmeDto> listDmeWithGungu(List<String> paramGunguCode,String paramStDate,String paramEndDate,String paramDemCode,String paramStRainValure,String paramEndRainValue);
	
	/**
	 * 시군구별 기간 sum
	 * @param paramSidoCode
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public List<YearDmeDto> listDmeWithGunguByArea(String paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode);
	public List<YearDmeDto> listDmeWithGunguByArea(String paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode,String paramDamageName);
	public List<YearDmeDto> listDmeWithGunguByArea(String paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode,String paramStRainValure,String paramEndRainValue);
	public List<YearDmeDto> listDmeWithGunguByArea(String paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode,String paramStRainValure,String paramEndRainValue,String paramDamageName);
	/**
	 * 시군구별 기간 sum (in query)
	 * @param paramSidoCode
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public List<YearDmeDto> listDmeWithGunguByArea(List<String> paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode);
	public List<YearDmeDto> listDmeWithGunguByArea(List<String> paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode,String paramDamageName);
	public List<YearDmeDto> listDmeWithGunguByArea(List<String> paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode,String paramStRainValure,String paramEndRainValue);
	public List<YearDmeDto> listDmeWithGunguByArea(List<String> paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode,String paramStRainValure,String paramEndRainValue,String paramDamageName);

	/**
	 * 시군구 재해이력정보
	 * 티세 시군구 미제공
	 * @param paramSidoCode
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public List<YearDmeDto> listDmeHisWithGungu(String paramGunguCode,String paramStDate,String paramEndDate,String paramDemCode);
	/**
	 * 시군구 재해이력정보
	 * @param paramSidoCode
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public List<YearDmeDto> listDmeHisWithGunguByArea(String paramSidoCode,String paramStDate,String paramEndDate,String paramDemCode);
	

	/**
	 * 해당 시군구 기간 총 피해액
	 * @param paramSigungu
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public YearDmeDto totalDamage(String paramSigungu,String paramStDate,String paramEndDate,String paramDemCode);
	
	
	/**
	 * 강우 구간별 시군구 재해
	 * @param paramList
	 * @return
	 */
	public List<SectorDamageModel> listRainDemSector(List<YearDmeDto> paramList);
	public String thissenLawAreaYn(String paramGungu);
	/**
	 * 최대 인명피해
	 * @param paramSigungu
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public YearDmeDto maxDamagePerson(String paramSigungu,String paramStDate,String paramEndDate,String paramDemCode);
	
	/**
	 * 최대 재산피해
	 * @param paramSigungu
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public YearDmeDto maxDamageMoney(String paramSigungu,String paramStDate,String paramEndDate,String paramDemCode);
	
	/**
	 * 최대 인명피해 top 10
	 * @param paramSido
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public List<YearDmeDto> listDamagePersonTop10(String paramSido,String paramStDate,String paramEndDate,String paramDemCode);
	public List<YearDmeDto> listDamagePersonTop10(String paramSido,String paramStDate,String paramEndDate,String paramDemCode,String paramDamageName);
	/**
	 * 최대 재산피해 top 10
	 * @param paramSido
	 * @param paramStDate
	 * @param paramEndDate
	 * @param paramDemCode
	 * @return
	 */
	public List<YearDmeDto> listDamageMoneyTop10(String paramSido,String paramStDate,String paramEndDate,String paramDemCode);
	public List<YearDmeDto> listDamageMoneyTop10(String paramSido,String paramStDate,String paramEndDate,String paramDemCode,String paramDamageName);
	public List<YearDmeDto> listDamageMoneyTop10(String paramSido,String paramStDate,String paramEndDate,String paramDemCode,String paramStRainValue,String paramEndRainValue);
	public List<YearDmeDto> listDamageMoneyTop10(String paramSido,String paramStDate,String paramEndDate,String paramDemCode,String paramStRainValue,String paramEndRainValue,String paramDamageName);
	/**
	 * 시군구별  재해 원인 조회(세부정보 모두 조회)
	 * @param paramSidoCode
	 * @param paramStDate
	 * @param paramEndDate
	 * @return
	 */
	public List<YearDmeDto> listGunguDamageCause(String paramSido,String paramStDate,String paramEndDate);
	public YearDmeDto detailSummary(String paramStDate,String paramEndDate,String paramDmeCode);
	
	
	/**
	 * 시군구별  재해 원인 조회(재해 원인 겹칠 시 카운트) 
	 * @param paramSidoCode
	 * @param paramStDate
	 * @param paramEndDate
	 * @return
	 */
	public List<YearDmeDto> listGunguDamageCauseResultSum(String paramSido,String paramStDate,String paramEndDate);

}

