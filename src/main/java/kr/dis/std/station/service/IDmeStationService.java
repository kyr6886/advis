package kr.dis.std.station.service;

import java.util.List;

import kr.dis.std.station.dao.DisDmeStationDto;

public interface IDmeStationService {
	List<DisDmeStationDto> list(String paramArea, String paramType);

	List<DisDmeStationDto> listTypeDmeStation();
}