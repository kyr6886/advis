package kr.dis.std.advis.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("yearDmeStaticsDao")
public class YearDmeStaticsDaoImpl extends BaseDao implements IYearDmeStaticsDao {

	@Override
	public List<YearDmeStaticsDto> list(HashMap<String, Object> paramMap) {
		return selectList("yearDmeStatics.list",paramMap);
	}
	
	@Override
	public List<YearDmeStaticsDto> listSidoDmeRain(HashMap<String, Object> paramMap) {
		return selectList("yearDmeStatics.listSidoDmeRain",paramMap);
	}
}
