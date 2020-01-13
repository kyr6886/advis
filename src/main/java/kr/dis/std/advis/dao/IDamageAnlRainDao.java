package kr.dis.std.advis.dao;

import java.util.List;

public interface IDamageAnlRainDao {
	
	public List<DamageAnlRainDto> searchListDailyTotalRainByStn(DamageAnlRainDto paramBean);
	public List<DamageAnlRainDto> searchListDailyMaxRain(DamageAnlRainDto paramBean);

}
