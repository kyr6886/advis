package kr.dis.std.station.dao;

import java.util.List;
import java.util.Map;

public interface IDmeStationDao {
	public List<DisDmeStationDto> list(Map<String, Object> paramMap);

	public List<DisDmeStationDto> listTypeDmeStation();
}