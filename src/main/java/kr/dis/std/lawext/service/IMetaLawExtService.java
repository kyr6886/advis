package kr.dis.std.lawext.service;

import java.util.List;

import com.noaa.base.meta.dao.MetaLawDTO;

public interface IMetaLawExtService {
	List<MetaLawDTO> listAllSidoCodes();
}