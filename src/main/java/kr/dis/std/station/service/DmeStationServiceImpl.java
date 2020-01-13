package kr.dis.std.station.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.base.BaseService;

import kr.dis.std.station.dao.DisDmeStationDto;
import kr.dis.std.station.dao.IDmeStationDao;

@Service("dmeStationService")
public class DmeStationServiceImpl extends BaseService implements IDmeStationService {

	@Autowired
	private IDmeStationDao dmeStationDao;

	@Override
	public List<DisDmeStationDto> list(String paramArea, String paramType) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paramArea", paramArea);
		paramMap.put("paramType", paramType);
		
		return dmeStationDao.list(paramMap);
	}

	@Override
	public List<DisDmeStationDto> listTypeDmeStation() {
		return dmeStationDao.listTypeDmeStation();
	}
}