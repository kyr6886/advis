package kr.dis.std.log.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dis.std.log.dao.ILogDisSearchDao;
import kr.dis.std.log.dao.LogDisSearchDto;

@Service("logDisSearchService")
public class LogDisSearchServiceImpl implements ILogDisSearchService {

	@Autowired
	private ILogDisSearchDao logDisSearchDao;
	@Override
	public List<LogDisSearchDto> list(String paramStDate, String paramEndDate) {
		List<LogDisSearchDto> rs=new ArrayList<LogDisSearchDto>();
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("beg_date", paramStDate);
		paramMap.put("end_date", paramEndDate);
		rs=logDisSearchDao.list(paramMap);
		return rs;
	}
	
}
