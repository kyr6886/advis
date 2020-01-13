package com.noaa.base.code.dao;

import com.noaa.base.BaseDTO;

public class CodeSysDTO extends BaseDTO{
	private String sys_code;
	private String sys_title;
	private String grp_code;
	
	public String getSys_code() {
		return sys_code;
	}
	public void setSys_code(String sys_code) {
		this.sys_code = sys_code;
	}
	public String getSys_title() {
		return sys_title;
	}
	public void setSys_title(String sys_title) {
		this.sys_title = sys_title;
	}
	public String getGrp_code() {
		return grp_code;
	}
	public void setGrp_code(String grp_code) {
		this.grp_code = grp_code;
	}
	
}
