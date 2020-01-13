package kr.dis.std.history.service;

import java.util.List;

import kr.dis.std.history.dao.AccidentSocDto;

public interface IAccidentSocService {
	public List<AccidentSocDto> listAccidentSoc(String paramMonth, List<String> paramList);
	public int countAccidentSoc(String paramMonth, List<String> paramList);
	public List<AccidentSocDto> listAccidentGroupCount(String paramMonth, List<String> paramList);
	public void createCheckedArea(String user_id, List<AccidentSocDto> list);
	public List<AccidentSocDto> listCheckedArea(String user_id);
}