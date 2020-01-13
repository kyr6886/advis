package kr.dis.std.lawext.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noaa.base.BaseService;
import com.noaa.base.meta.dao.MetaLawDTO;

import kr.dis.std.lawext.dao.IMetaLawExtDao;

@Service("metaLawExtService")
public class MetaLawExtServiceImpl extends BaseService implements IMetaLawExtService {

	@Autowired
	private IMetaLawExtDao metaLawExtDao;

	@Override
	public List<MetaLawDTO> listAllSidoCodes() {
		return metaLawExtDao.listAllSidoCodes();
	}
}