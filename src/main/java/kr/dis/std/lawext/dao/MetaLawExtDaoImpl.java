package kr.dis.std.lawext.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.base.meta.dao.MetaLawDTO;

@Repository("metaLawExtDao")
public class MetaLawExtDaoImpl extends BaseDao implements IMetaLawExtDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<MetaLawDTO> listAllSidoCodes() {
		return selectList("metaLawExtMapper.listAllSidoCodes");
	}
}