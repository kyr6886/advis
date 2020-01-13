package com.noaa.base.site.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.noaa.base.BaseDTO;
import com.noaa.base.file.dao.AttachFileDTO;

public class PartnerSiteDto extends BaseDTO {
	private String site_url;
	private String site_title;
	private String file_grp_id;
	private String siteUrlBefore;
	
	private AttachFileDTO fileInfo;
	
	public AttachFileDTO getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(AttachFileDTO fileInfo) {
		this.fileInfo = fileInfo;
	}
	public String getSite_url() {
		return site_url;
	}
	public void setSite_url(String site_url) {
		this.site_url = site_url;
	}
	public String getSite_title() {
		return site_title;
	}
	public void setSite_title(String site_title) {
		this.site_title = site_title;
	}
	public String getFile_grp_id() {
		return file_grp_id;
	}
	public void setFile_grp_id(String file_grp_id) {
		this.file_grp_id = file_grp_id;
	}
	public String getSiteUrlBefore() {
		return siteUrlBefore;
	}
	public void setSiteUrlBefore(String siteUrlBefore) {
		this.siteUrlBefore = siteUrlBefore;
	}
	
	
	 
	
}
