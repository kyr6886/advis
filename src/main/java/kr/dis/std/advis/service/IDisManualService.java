package kr.dis.std.advis.service;

import java.util.List;

import kr.dis.std.advis.dao.TbDisManualDto;

public interface IDisManualService {

	public List<TbDisManualDto> listDisManual(TbDisManualDto paramBean);
	public List<TbDisManualDto> selectListGroupByDisManual(TbDisManualDto paramBean);
	public int deleteDisManual(int paramSeq);
	public int insertDisManual(TbDisManualDto paramBean);
}
