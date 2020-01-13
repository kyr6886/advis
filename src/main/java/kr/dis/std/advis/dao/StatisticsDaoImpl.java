package kr.dis.std.advis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;

import kr.dis.std.history.dao.AccidentSocDto;

@Repository("statisticsDao")
public class StatisticsDaoImpl extends BaseDao implements IStatisticsDao {

	@Override
	public List<AccidentSocDto> listAccidentSocGroup(AccidentSocDto paramBean) {
		return selectList("statisticsMapper.listAccidentSocGroup",paramBean);
	}

	@Override
	public List<AccidentSocDto> listAccidentSocAll(AccidentSocDto paramBean) {
		return selectList("statisticsMapper.listAccidentSocAll", paramBean);
	}

	@Override
	public List<AccidentSocDto> searchListAccidentSocGroup(AccidentSocDto paramBean) {
		return selectList("statisticsMapper.searchListAccidentSocGroup", paramBean);
	}

	@Override
	public List<AccidentSocDto> listAccidentSocCountByMonthly(AccidentSocDto paramBean) {
		return selectList("statisticsMapper.listAccidentSocCountByMonthly", paramBean);
	}

	@Override
	public List<AccidentSocDto> searchListAccidentSocCountByMonthly(AccidentSocDto paramBean) {
		return selectList("statisticsMapper.searchListAccidentSocCountByMonthly", paramBean);
	}
	
	


}
