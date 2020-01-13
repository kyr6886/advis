package kr.dis.std.data.dao;

import java.util.List;

public interface IDataManageDao {
	List<TobKmaTyphoon5dsDTO> listTyphoon(TobKmaTyphoon5dsDTO dto);
	List<TobKmaInformDTO> listInform(TobKmaInformDTO dto);
	int countTyphoon(TobKmaTyphoon5dsDTO dto);
	int countInform(TobKmaInformDTO dto);
	int countMaxTmSeq(TobKmaTyphoon5dsDTO dto);
	int insertTyphoon(TobKmaTyphoon5dsDTO dto);
	int insertInform(TobKmaInformDTO dto);
	int deleteTyphoon(TobKmaTyphoon5dsDTO dto);
	int deleteInform(TobKmaInformDTO dto);
}