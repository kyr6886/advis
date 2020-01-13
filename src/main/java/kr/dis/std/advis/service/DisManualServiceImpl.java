package kr.dis.std.advis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.base.BaseService;

import kr.dis.std.advis.dao.DisManualDaoImpl;
import kr.dis.std.advis.dao.TbDisManualDto;

@Service("disManualService")
public class DisManualServiceImpl extends BaseService implements IDisManualService{

	@Autowired
	private DisManualDaoImpl disManualDaoImpl;
	
	@Override
	public List<TbDisManualDto> listDisManual(TbDisManualDto paramBean) {
		if(paramBean.getCtg_id().equals("")) paramBean.setCtg_id(null);
		return disManualDaoImpl.listDisManual(paramBean);
	}

	@Override
	public int deleteDisManual(int paramSeq) {
		TbDisManualDto paramBean = new TbDisManualDto();
		paramBean.setSeq(paramSeq);
		return disManualDaoImpl.deleteDisManual(paramBean);
	}

	@Override
	public int insertDisManual(TbDisManualDto paramBean) {
		return disManualDaoImpl.insertDisManual(paramBean);
	}

	@Override
	public List<TbDisManualDto> selectListGroupByDisManual(TbDisManualDto paramBean) {
		if(paramBean.getCtg_id().equals("")) paramBean.setCtg_id(null);
		return disManualDaoImpl.selectListGroupByDisManual(paramBean);
	}
	

}
