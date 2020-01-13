package kr.dis.std.advis.service;

import java.util.List;

import kr.dis.std.advis.dao.DamageAnlRainDto;

public interface IDamageAnlRainService {
	
	public List<DamageAnlRainDto> searchListDailyTotalRainByStn(String paramStDate, String paramEndDate);
	public List<DamageAnlRainDto> searchListDailyMaxRain(String paramStDate, String paramEndDate);

}
