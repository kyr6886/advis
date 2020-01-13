package kr.dis.std.common.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;

import kr.dis.std.advis.resultmodel.StaticsMonthlyModel;
import kr.dis.std.advis.resultmodel.StatisticsModel;
@Repository("stdCommonDao")
public class CommonDaoImpl extends BaseDao implements ICommonDao {

	@Override
	public List<StaticsMonthlyModel> listDisRateMonthly(HashMap<String, Object> paramMap) {
		
		return selectList("stdCommon.disMonthlySum",paramMap);
	}

	@Override
	public List<StaticsMonthlyModel> listDisSidoMonthly(HashMap<String, Object> paramMap) {
		return selectList("stdCommon.sidoMonthlyDamage",paramMap);
	}
	@Override
	public List<StatisticsModel> listDisBuildType(HashMap<String,Object> paramMap){
		return selectList("stdCommon.listDamageBuildType",paramMap);
	}
}
