package com.noaa.nema.viewer.year.dme.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeConditionDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmeCodeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeConditionDto;
@Repository("yearDmeConditionDao")
public class YearDmeConditionDaoImpl extends BaseDao  implements IYearDmeConditionDao {

	@Override
	public YearDmeConditionDto detail(HashMap<String, Object> paraMap) {
		
		return (YearDmeConditionDto)selectOne("yearDmeCondition.detail",paraMap);
	}

	@Override
	public int registCondition(YearDmeConditionDto paramDto) {
		return (int) insert("yearDmeCondition.registCondition", paramDto);
	}

	@Override
	public int updateCondition(YearDmeConditionDto paramDto) {
		return (int) update("yearDmeCondition.updateCondition", paramDto);
	}

}
