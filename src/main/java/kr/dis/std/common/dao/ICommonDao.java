package kr.dis.std.common.dao;

import java.util.HashMap;
import java.util.List;

import kr.dis.std.advis.resultmodel.StaticsMonthlyModel;
import kr.dis.std.advis.resultmodel.StatisticsModel;

public interface ICommonDao {
	public List<StaticsMonthlyModel> listDisRateMonthly(HashMap<String,Object> paramMap);
	public List<StaticsMonthlyModel> listDisSidoMonthly(HashMap<String,Object> paramMap);
	public List<StatisticsModel> listDisBuildType(HashMap<String,Object> paramMap);
}
