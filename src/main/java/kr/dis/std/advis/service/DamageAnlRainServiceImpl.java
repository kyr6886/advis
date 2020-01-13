package kr.dis.std.advis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.base.BaseService;

import kr.dis.std.advis.dao.DamageAnlRainDaoImpl;
import kr.dis.std.advis.dao.DamageAnlRainDto;

@Service("damageAnlRainService")
public class DamageAnlRainServiceImpl extends BaseService implements IDamageAnlRainService {
	
	@Autowired
	private DamageAnlRainDaoImpl damageAnlRainDaoImpl;

	@Override
	public List<DamageAnlRainDto> searchListDailyTotalRainByStn(String paramStDate, String paramEndDate) {
		DamageAnlRainDto paramBean = new DamageAnlRainDto();
		paramBean.setStDate(paramStDate);
		paramBean.setEndDate(paramEndDate);
		
		return damageAnlRainDaoImpl.searchListDailyTotalRainByStn(paramBean);
	}

	@Override
	public List<DamageAnlRainDto> searchListDailyMaxRain(String paramStDate, String paramEndDate) {
		DamageAnlRainDto paramBean = new DamageAnlRainDto();
		paramBean.setStDate(paramStDate);
		paramBean.setEndDate(paramEndDate);
		
		return damageAnlRainDaoImpl.searchListDailyMaxRain(paramBean);
	}

}
