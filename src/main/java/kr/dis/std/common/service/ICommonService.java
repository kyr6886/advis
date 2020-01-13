package kr.dis.std.common.service;

import java.util.List;

import kr.dis.std.advis.dao.DamageTyphoonDto;
import kr.dis.std.advis.resultmodel.StaticsMonthlyModel;
import kr.dis.std.advis.resultmodel.StatisticsModel;

public interface ICommonService {
	public List<StaticsMonthlyModel> listDisRateMonthly(String paramSidoCode,String paramBegMonthMM, List<String> paramListSidoCodes);
	public List<StaticsMonthlyModel> listDisSidoMonthly(String paramSidoCode,String paramBegMonthMM,String paramDamageCode);
	public List<StatisticsModel> listBuildDamges(List<String> paramDamageCode,String paramBegDate,String paramEndDate);
	public List<StatisticsModel> listBuildDamges(List<String> paramDamageCode,String paramBegDate,String paramEndDate,String paramDamgeName);
	public DamageTyphoonDto detailDamageTyphoon(String paramYear,String paramName);
	public List<DamageTyphoonDto> listDamageTyphoon(double paramStDamageMoney,double paramEndDamageMoney,double paramStDamagePerson,double paramEndDamagePerson);
}
