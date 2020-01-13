package com.noaa.nema.viewer.service;

import java.util.List;

import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;
import com.noaa.nema.viewer.year.dme.dao.*;
/**
 * 재해연보 관련 서비스
 * @author dev
 *
 */
public interface IYearDmeService {

	

	/**
	 * 시도의 공공, 사유시설 총피해액, 사유시설&공공시설 피해액 
	 * @param paramSidoCode, paramStDate, paramEndDate
	 * @return
	 */
	public List<YearDmeDto> listDmeSido(String paramSido, String paramStDate, String paramEndDate);
	
	/**
	 * 시도의 공공, 사유시설 총피해액, 사유시설&공공시설 피해액 
	 * @param paramSidoCode, paramStDate, paramEndDate
	 * @return
	 */
	public List<YearDmeDto> listDmeGungu(String paramSido, String paramStDate, String paramEndDate);
	/**
	 * 년도별 통계
	 * @param paramSidoCode, paramStYear, paramEndYear, paramDmeCode
	 * @return
	 */
	public List<YearDmeDto> listDmeByYear(String paramGungu, String paramStYear, String paramEndYear);
	
	public YearDmeDto dmeItemSum(String paramGungu, String paramDmeCode, String paramStYear, String paramEndYear);
	
	/**
	 * 년도별 인명피해 top 10
	 * @param paramStYear, paramEndYear
	 * @return
	 */
	
	public List<YearDmeDto> listComDmeTop10ByYear(String paramStYear, String paramEndYear);
	
	public List<YearDmeDto> listTotalDmeTop10ByYear(String paramStYear, String paramEndYear, String paramGungu);

	public List<YearDmeDto> listWholeAreaDmeChartByYear(String paramStYear, String paramEndYear);
	
	public List<YearDmeDto> listComDmeAndTotalDmeByYear(String paramStYear, String paramEndYear, String paramDmeCode, int paramOffset, String paramGungu);
	
	public YearDmeConditionDto detailCondition(String paramStDate,String paramEndDate,String paramDmeCode);
	
	public List<YearDmeImagesDto> listYearDmeImages(String paramStDate, String paramEndDate);
	
	public List<YearDmeCodeDto> listDmeCode();
	
	public List<YearDmeCodeDto> listDmeGunguAndDateCode(String paramYear, String paramStDate, String paramDmeCode );
	
	public List<YearDmeCodeDto> listDmeSidoCode(String paramYear, String paramStDate, String paramDmeCode );
	
	public List<YearDmeCodeDto> listDmeGunguCode(String paramYear, String paramStDate, String paramDmeCode, String paramSidoCode );
	
	public List<YearDmeCodeDto> listDmeTyphoonNameCode(String paramYear, String paramDmeCode);
	
	public List<YearDmeCodeDto> listTyphoonDmeSidoCode(String paramYear, int paramTypSeq, String paramDmeCode );
	
	public int registCondition(int paramSeq, String paramStDate, String paramEndDate, String paramDescription, String paramDamageType, String paramCauses);
	
	public int updateCondition(int paramSeq, String paramDescription);
	
	public int registImages(String paramStDate, String paramEndDate, String paramFileName, String paramId);
	
	public int deleteImages(int paramSeq);

	public int insertSummerySidoList(String paramStDate, String paramEndDate);
	public int insertSummerySigunguList(String paramStDate, String paramEndDate);
	public List<YearDmeDto> listAllSidoDamageAndRain(String paramStDate, String paramEndDate);
	public List<YearDmeDto> listAllSigunguDamageAndRain(String paramStDate, String paramEndDate);
	public List<YearDmeDto> listTyphoonDmeList(String paramStDate, String paramEndDate, String typhoonName);
	
	public List<YearDmeDto> listYearDme(String paramStDate, String paramEndDate, String paramDmeCode);
	
}