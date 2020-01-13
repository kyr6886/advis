package com.noaa.nema.viewer.year.dme.dao;

import java.util.HashMap;

public interface IYearDmeConditionDao {
	public YearDmeConditionDto detail(HashMap<String,Object> paraMap);
	public int registCondition(YearDmeConditionDto paramDto);
	public int updateCondition(YearDmeConditionDto paramDto);

}
