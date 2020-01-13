package kr.dis.std.log.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
@Repository("logDisSearchDao")
public class LogDisSearchDaoImpl extends BaseDao implements ILogDisSearchDao {
	@Override
	public List<LogDisSearchDto> list(HashMap<String, Object> paramMap) {
		
		return selectList("logDisSearch.list",paramMap);
	}

	@Override
	public int insert(LogDisSearchDto paramBean) {
		return update("logDisSearch.insert", paramBean);
	}
	
	

}
