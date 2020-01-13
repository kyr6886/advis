package com.noaa.base.site.dao;

import java.util.HashMap;
import java.util.List;

public interface IPartnerSiteDao  {
	public int create(PartnerSiteDto paramBean);
	public List<PartnerSiteDto> list();
	public List<PartnerSiteDto> list(HashMap<String, Object> paramMap);
	public List<PartnerSiteDto> detail(HashMap<String, Object> paramMap); 
	public int update(PartnerSiteDto paramBean);
	public int updateUseYN(PartnerSiteDto paramBean);
	public int drop(HashMap<String,Object> paramMap);
	public int deleteSiteImage(PartnerSiteDto paramBean);
	
}
