package kr.dis.std.advis.dao;

import java.util.HashMap;
import java.util.List;

public interface IYearDmeStaticsDao {
	public List<YearDmeStaticsDto> list(HashMap<String,Object> paramMap);
	public List<YearDmeStaticsDto> listSidoDmeRain(HashMap<String,Object> paramMap);
	
	
}
