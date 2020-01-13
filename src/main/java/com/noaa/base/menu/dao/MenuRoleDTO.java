package com.noaa.base.menu.dao;

import com.noaa.base.BaseDTO;

public class MenuRoleDTO extends BaseDTO {
	private String menu_id;  
	private String role_id;
	private String write_yn;
	
	
	public String getWrite_yn() {
		return write_yn;
	}
	public void setWrite_yn(String write_yn) {
		this.write_yn = write_yn;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
}
