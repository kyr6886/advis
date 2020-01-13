package com.noaa.nema.viewer.kmaInform.dao;

public class KmaLocDto {
	private String loc_code;
	private String loc_name;
	private String adm_code;
	private String loc_type;
	private String short_name;
	
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getLoc_code() {
		return loc_code;
	}
	public void setLoc_code(String loc_code) {
		this.loc_code = loc_code;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public String getAdm_code() {
		return adm_code;
	}
	public void setAdm_code(String adm_code) {
		this.adm_code = adm_code;
	}
	public String getLoc_type() {
		return loc_type;
	}
	public void setLoc_type(String loc_type) {
		this.loc_type = loc_type;
	}
	
}
