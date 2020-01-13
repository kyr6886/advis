package kr.dis.std.station.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;

@Repository("dmeStationDao")
public class DmeStationDaoImpl extends BaseDao implements IDmeStationDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<DisDmeStationDto> list(Map<String, Object> paramMap) {
		return selectList("disDmeStationMapper.list", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DisDmeStationDto> listTypeDmeStation() {
		return selectList("disDmeStationMapper.listType");
	}
}