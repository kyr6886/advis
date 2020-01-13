package kr.dis.std.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;

@Repository("dataManageDao")
public class DataManageDaoImpl extends BaseDao implements IDataManageDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TobKmaTyphoon5dsDTO> listTyphoon(TobKmaTyphoon5dsDTO dto) {
		return selectList("kmaTyphoon.listTyphoon", dto);
	}

	@Override
	public int countTyphoon(TobKmaTyphoon5dsDTO dto) {
		return (Integer)selectOne("kmaTyphoon.countTyphoon", dto);
	}
	
	@Override
	public int countMaxTmSeq(TobKmaTyphoon5dsDTO dto) {
		Object result = selectOne("kmaTyphoon.countMaxTyphoonTmSeq", dto);
		return result != null ? (Integer)result : 0;
	}
	
	@Override
	public int insertTyphoon(TobKmaTyphoon5dsDTO dto) {
		return update("kmaTyphoon.insertTyphoon", dto);
	}
	
	@Override
	public int deleteTyphoon(TobKmaTyphoon5dsDTO dto) {
		return update("kmaTyphoon.deleteTyphoon", dto);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TobKmaInformDTO> listInform(TobKmaInformDTO dto) {
		return selectList("kmaInform.listInform", dto);
	}
	
	@Override
	public int countInform(TobKmaInformDTO dto) {
		return (Integer)selectOne("kmaInform.countInform", dto);
	}
	
	@Override
	public int insertInform(TobKmaInformDTO dto) {
		return update("kmaInform.insertInform", dto);
	}

	@Override
	public int deleteInform(TobKmaInformDTO dto) {
		return update("kmaInform.deleteInform", dto);
	}
}