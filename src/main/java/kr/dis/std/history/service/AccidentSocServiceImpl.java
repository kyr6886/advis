package kr.dis.std.history.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dis.std.history.dao.AccidentSocDto;
import kr.dis.std.history.dao.IAccidentSocDao;

@Service("accidentSocService")
public class AccidentSocServiceImpl implements IAccidentSocService{
	
	@Autowired
	private IAccidentSocDao accidentSocDao;

	@Override
	public List<AccidentSocDto> listAccidentSoc(String paramMonth, List<String> paramList) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paramMonth", paramMonth);
		paramMap.put("paramList", paramList);
		
		return accidentSocDao.listAccidentSoc(paramMap);
	}

	@Override
	public int countAccidentSoc(String paramMonth, List<String> paramList) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paramMonth", paramMonth);
		paramMap.put("paramList", paramList);
		
		return accidentSocDao.countAccidentSoc(paramMap);
	}

	@Override
	public List<AccidentSocDto> listAccidentGroupCount(String paramMonth, List<String> paramList) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paramMonth", paramMonth);
		paramMap.put("paramList", paramList);
		
		return accidentSocDao.listAccidentGroupCount(paramMap);
	}

	@Override
	public void createCheckedArea(String user_id, List<AccidentSocDto> paramList) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("user_id", user_id);
		
		accidentSocDao.deleteCheckedArea(paramMap);
		
		for(AccidentSocDto dto : paramList){
			paramMap.put("paramSido", dto.getLaw_code());
			
			accidentSocDao.createCheckedArea(paramMap);
		}
	}

	@Override
	public List<AccidentSocDto> listCheckedArea(String user_id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("user_id", user_id);
		
		return accidentSocDao.listCheckedArea(paramMap);
	}
}