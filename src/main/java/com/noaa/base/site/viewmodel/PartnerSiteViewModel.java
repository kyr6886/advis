package com.noaa.base.site.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.base.site.dao.PartnerSiteDto;

public class PartnerSiteViewModel extends BaseViewModel{
	private PartnerSiteDto detail;
	
	
	private List<PartnerSiteDto> list;
	private String paramSiteUrl;
	private String paramSiteTitle;
	private String paramFileGrpId;
	
	public String getParamFileGrpId() {
		return paramFileGrpId;
	}
	public void setParamFileGrpId(String paramFileGrpId) {
		this.paramFileGrpId = paramFileGrpId;
	}
	public String getParamSiteTitle() {
		return paramSiteTitle;
	}
	public void setParamSiteTitle(String paramSiteTitle) {
		this.paramSiteTitle = paramSiteTitle;
	}
	public PartnerSiteDto getDetail() {
		return detail;
	}
	public void setDetail(PartnerSiteDto detail) {
		this.detail = detail;
	}
	public List<PartnerSiteDto> getList() {
		return list;
	}
	public void setList(List<PartnerSiteDto> list) {
		this.list = list;
	}
	public String getParamSiteUrl() {
		return paramSiteUrl;
	}
	public void setParamSiteUrl(String paramSiteUrl) {
		this.paramSiteUrl = paramSiteUrl;
	}
	
	
}
