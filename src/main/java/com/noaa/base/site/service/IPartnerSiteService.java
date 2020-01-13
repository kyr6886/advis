package com.noaa.base.site.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.noaa.base.CommonParams;
import com.noaa.base.site.dao.PartnerSiteDto;

public interface IPartnerSiteService {
	public List<PartnerSiteDto> list();
	public List<PartnerSiteDto> list(String paramUseYn);
	public List<PartnerSiteDto> detail(String paramUrl);
	public int create(PartnerSiteDto paramBean,CommonParams CommonParams, List<MultipartFile> siteThumb);
	public int update(PartnerSiteDto paramBean,CommonParams CommonParams, List<MultipartFile> siteThumb);
	public int updateUseYN(PartnerSiteDto paramBean);
	public int drop(String paramUrl);
	public int deleteSiteImage(PartnerSiteDto paramBean);
	public int deleteSiteImage(String paramUrl,String paramFileId);
}
