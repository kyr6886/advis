package com.noaa.base.meta.service;

import java.util.List;

import com.noaa.base.meta.dao.MetaAdmDTO;

public interface IMetaAdmService {
	
	public int creataData();
	
	public List<MetaAdmDTO> listSido();
	public List<MetaAdmDTO> listGungu(String paramSido);
	public List<MetaAdmDTO> listDong(String paramGungu);
}
