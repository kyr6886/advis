package com.noaa.base.code.dao;

import com.noaa.base.BaseDTO;

public class CodeGrpDTO extends BaseDTO {
	private String grp_code;
	private String grp_title;
	public String getGrp_code() {
		return grp_code;
	}
	public void setGrp_code(String grp_code) {
		this.grp_code = grp_code;
	}
	public String getGrp_title() {
		return grp_title;
	}
	public void setGrp_title(String grp_title) {
		this.grp_title = grp_title;
	}
	
}
