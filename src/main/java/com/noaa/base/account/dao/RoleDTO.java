package com.noaa.base.account.dao;

import com.noaa.base.BaseDTO;

public class RoleDTO extends BaseDTO{
	private String role_id; 
	private String role_title;
	
	public String getRole_title() {
		return role_title;
	}
	public void setRole_title(String role_title) {
		this.role_title = role_title;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	
	
}
