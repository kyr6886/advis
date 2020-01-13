package kr.dis.std.history.dao;

import java.util.List;
import java.util.Map;

public interface IAccidentSocDao {
	List<AccidentSocDto> listAccidentSoc(Map<String, Object> paramMap);
	int countAccidentSoc(Map<String, Object> paramMap);
	List<AccidentSocDto> listAccidentGroupCount(Map<String, Object> paramMap);
	int createCheckedArea(Map<String, Object> paramMap);
	int deleteCheckedArea(Map<String, Object> paramMap);
	List<AccidentSocDto> listCheckedArea(Map<String, Object> paramMap);
}