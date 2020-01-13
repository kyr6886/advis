package kr.dis.std.advis.service;

import java.util.ArrayList;
import java.util.List;

import com.noaa.nema.viewer.kmaInform.dao.KmaInformDto;

import kr.dis.std.advis.dao.TbAccidentSocDto;
import kr.dis.std.advis.dao.YearDmeStaticsDto;
import kr.dis.std.advis.resultmodel.StatisticsModel;
import kr.dis.std.history.dao.AccidentSocDto;

public interface IStatisticsService {
	/**
	 * 1depth에 해당하는 재난카테고리의 값+ 재해연보 월별 재난 카운트
	 * @return
	 */
	public List<StatisticsModel> listDisMonthlyCount();
	public List<StatisticsModel> listDisMonthlyCount(List<String> paramMonth,String paramStYear,String paramEndYear);
	public List<StatisticsModel> listAreaDamageSum(String paramStYear,String paramEndYear,String paramDamageCode);
	public List<StatisticsModel> listEachYearCount(String paramStYear,String paramEndYear);
	
	public StatisticsModel maxDisasterInfoByMonth(String paramMonthMM);
	public StatisticsModel maxDisasterInfoByTyphoon(KmaInformDto dto);
	
	public List<AccidentSocDto> listAccidentSocGroup(AccidentSocDto paramBean);
	public List<AccidentSocDto> listAccidentSocAll(AccidentSocDto paramBean);
	public List<AccidentSocDto> searchListAccidentSocGroup(String paramMonth, String paramSidoCode, String paramText);
	public List<AccidentSocDto> listAccidentSocCountByMonthly(AccidentSocDto paramBean);
	public List<AccidentSocDto> searchListAccidentSocCountByMonthly(AccidentSocDto paramBean);
	public List<YearDmeStaticsDto> listYearDmeStatics(String paramDamageType,String paramStDate,String paramEndDate,String paramStTotalDamageMoney,String paramEndTotalDamageMoney,String paramStComDmeCount,String paramEndComDmeCount,String paramStRain,String paramEndRain);
	public List<YearDmeStaticsDto> listYearDmeStatics(String paramDamageType,String paramStDate,String paramEndDate,String paramStTotalDamageMoney,String paramEndTotalDamageMoney,String paramStComDmeCount,String paramEndComDmeCount,String paramStRain,String paramEndRain,String paramSidoCode);
	
	public List<YearDmeStaticsDto> listSidoDmeRain(ArrayList<String> paramListObsId, String paramRnDay);
}
