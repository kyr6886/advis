package kr.dis.std.log.service;

import java.util.List;

import kr.dis.std.log.dao.LogDisSearchDto;

public interface ILogDisSearchService {
	public List<LogDisSearchDto> list(String paramStDate,String paramEndDate);
}
