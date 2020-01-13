package kr.dis.std.log.dao;

import java.util.HashMap;
import java.util.List;

import kr.dis.std.category.dao.CategoryDTO;

public interface ILogDisSearchDao {
	public List<LogDisSearchDto> list(HashMap<String,Object> paramMap);
	public int insert(LogDisSearchDto paramBean);
}
