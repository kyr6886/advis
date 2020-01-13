package com.noaa.base.meta.service;

import java.util.List;

import com.noaa.base.meta.dao.MetaLawDTO;

public interface IMetaLawService {
	
	public int creataData();
	
	public List<MetaLawDTO> listSido();
	public List<MetaLawDTO> listGungu(String paramSido);
	public List<MetaLawDTO> listDong(String paramGungu);
	public List<MetaLawDTO> listRi(String paramDong);
}
