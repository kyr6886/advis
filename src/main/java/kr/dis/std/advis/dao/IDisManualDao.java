package kr.dis.std.advis.dao;

import java.util.List;

public interface IDisManualDao {

	public List<TbDisManualDto> listDisManual(TbDisManualDto paramBean);
	public List<TbDisManualDto> selectListGroupByDisManual(TbDisManualDto paramBean);
	public int deleteDisManual(TbDisManualDto paramBean);
	public int insertDisManual(TbDisManualDto paramBean);
}
