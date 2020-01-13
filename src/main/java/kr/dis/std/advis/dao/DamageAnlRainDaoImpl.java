package kr.dis.std.advis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("damageAnlRainDao")
public class DamageAnlRainDaoImpl extends BaseDao implements IDamageAnlRainDao{

	@Override
	public List<DamageAnlRainDto> searchListDailyTotalRainByStn(DamageAnlRainDto paramBean) {
		return selectList("damageAnlRainMapper.searchListTotalRainByStn",paramBean);
	}

	@Override
	public List<DamageAnlRainDto> searchListDailyMaxRain(DamageAnlRainDto paramBean) {
		return selectList("damageAnlRainMapper.searchListDailyMaxRain",paramBean);
	}

}
