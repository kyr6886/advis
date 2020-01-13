package kr.dis.std.history.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;

@Repository("accidentSocDao")
public class AccidentSocDaoImpl extends BaseDao implements IAccidentSocDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentSocDto> listAccidentSoc(Map<String, Object> paramMap) {
		return selectList("accidentSocMapper.listAccidentSoc", paramMap);
	}

	@Override
	public int countAccidentSoc(Map<String, Object> paramMap) {
		return (Integer)selectOne("accidentSocMapper.countAccidentSoc", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentSocDto> listAccidentGroupCount(Map<String, Object> paramMap) {
		return selectList("accidentSocMapper.listAccidentGroupCount", paramMap);
	}

	@Override
	public int createCheckedArea(Map<String, Object> paramMap) {
		return update("accidentSocMapper.createCheckedArea", paramMap);
	}

	@Override
	public int deleteCheckedArea(Map<String, Object> paramMap) {
		return update("accidentSocMapper.deleteCheckedArea", paramMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentSocDto> listCheckedArea(Map<String, Object> paramMap) {
		return selectList("accidentSocMapper.listCheckedArea", paramMap);
	}
}