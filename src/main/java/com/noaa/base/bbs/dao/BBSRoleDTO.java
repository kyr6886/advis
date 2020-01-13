package com.noaa.base.bbs.dao;

import com.noaa.base.BaseDTO;

public class BBSRoleDTO extends BaseDTO {
	
	private String role_id;
	private String role_title;
	private int role_step;
	
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_title() {
		return role_title;
	}
	public void setRole_title(String role_title) {
		this.role_title = role_title;
	}
	public int getRole_step() {
		return role_step;
	}
	public void setRole_step(int role_step) {
		this.role_step = role_step;
	}
	
	
}
