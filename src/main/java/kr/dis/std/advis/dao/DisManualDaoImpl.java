package kr.dis.std.advis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;

@Repository("disManualDao")
public class DisManualDaoImpl extends BaseDao implements IDisManualDao{

	@Override
	public List<TbDisManualDto> listDisManual(TbDisManualDto paramBean) {
		return selectList("disManualMapper.listDisManual",paramBean);
	}

	@Override
	public int deleteDisManual(TbDisManualDto paramBean) {
		return update("disManualMapper.deleteDisManual",paramBean);
	}

	@Override
	public int insertDisManual(TbDisManualDto paramBean) {
		return update("disManualMapper.insertDisManual",paramBean);
	}

	@Override
	public List<TbDisManualDto> selectListGroupByDisManual(TbDisManualDto paramBean) {
		return selectList("disManualMapper.selectListGroupByDisManual",paramBean);
	}

	


}
