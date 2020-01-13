package kr.dis.std.advis.dao;

import java.util.List;

import kr.dis.std.history.dao.AccidentSocDto;

public interface IStatisticsDao {
	
	public List<AccidentSocDto> listAccidentSocGroup(AccidentSocDto paramBean);
	public List<AccidentSocDto> listAccidentSocAll(AccidentSocDto paramBean);
	public List<AccidentSocDto> searchListAccidentSocGroup(AccidentSocDto paramBean);
	public List<AccidentSocDto> listAccidentSocCountByMonthly(AccidentSocDto paramBean);
	public List<AccidentSocDto> searchListAccidentSocCountByMonthly(AccidentSocDto paramBean);
	

}
