package com.noaa.nema.viewer.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.year.dme.dao.*;

@Repository("yearDmeCodeDao")
public class YearDmeCodeDaoImpl extends BaseDao  implements IYearDmeCodeDao {

	@Override
	public List<YearDmeCodeDto> list(HashMap<String, Object> paramMap) {
		List<YearDmeCodeDto> rs=null;
		rs=selectList("yearDmeCode.list",paramMap);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YearDmeCodeDto> listCode(HashMap<String, Object> paramMap) {
		return selectList("yearDmeCode.listCode",paramMap);
	}

	@Override
	public List<YearDmeCodeDto> listDmeCode(HashMap<String, Object> paramMap) {
		return selectList("yearDmeCode.selectDmeCodeList",paramMap);
	}

	@Override
	public List<YearDmeCodeDto> listDmeSidoCode(HashMap<String, Object> paramMap) {
		return selectList("yearDmeCode.selectDmeSidoCodeList",paramMap);
	}

	@Override
	public List<YearDmeCodeDto> listDmeGunguCode(HashMap<String, Object> paramMap) {
		return selectList("yearDmeCode.selectDmeGunguCodeList",paramMap);
	}

	@Override
	public List<YearDmeCodeDto> listTyphoonDmeSidoCode(HashMap<String, Object> paramMap) {
		return selectList("yearDmeCode.selectTypoonDmeSidoCodeList",paramMap);
	}


}
