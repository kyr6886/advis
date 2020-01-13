package kr.dis.std.lawext.dao;

import java.util.List;

import com.noaa.base.meta.dao.MetaLawDTO;

public interface IMetaLawExtDao {
	public List<MetaLawDTO> listAllSidoCodes();	
}