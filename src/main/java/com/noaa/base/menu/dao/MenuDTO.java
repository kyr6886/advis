package com.noaa.base.menu.dao;

import java.util.List;

import com.noaa.base.BaseDTO;

public class MenuDTO extends BaseDTO {
	//
	private String menu_id;
	//
	private String menu_title;
	//
	private String menu_group;
	//
	private int menu_level;
	//
	private int menu_sort;
	//
	private String menu_uri;
	//
	private String menu_type_code;
	//
	private String menu_role;
	private String write_yn;
	private String menu_css;
	private String menu_img;
	private String roleIds;
	private List<MenuRoleDTO> roles;
	
	private String stDate;
	private String enDate;
	private int mobile_cnt;
	private double mobile_percent;
	private int desk_cnt;
	private double desk_percent;
	
	
	
	public String getWrite_yn() {
		return write_yn;
	}
	public void setWrite_yn(String write_yn) {
		this.write_yn = write_yn;
	}
	public String getStDate() {
		return stDate;
	}
	public void setStDate(String stDate) {
		this.stDate = stDate;
	}
	public String getEnDate() {
		return enDate;
	}
	public void setEnDate(String enDate) {
		this.enDate = enDate;
	}
	public int getMobile_cnt() {
		return mobile_cnt;
	}
	public void setMobile_cnt(int mobile_cnt) {
		this.mobile_cnt = mobile_cnt;
	}
	public double getMobile_percent() {
		return mobile_percent;
	}
	public void setMobile_percent(double mobile_percent) {
		this.mobile_percent = mobile_percent;
	}
	public int getDesk_cnt() {
		return desk_cnt;
	}
	public void setDesk_cnt(int desk_cnt) {
		this.desk_cnt = desk_cnt;
	}
	public double getDesk_percent() {
		return desk_percent;
	}
	public void setDesk_percent(double desk_percent) {
		this.desk_percent = desk_percent;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public List<MenuRoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(List<MenuRoleDTO> roles) {
		this.roles = roles;
	}
	public String getMenu_type_code() {
		return menu_type_code;
	}
	public void setMenu_type_code(String menu_type_code) {
		this.menu_type_code = menu_type_code;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_title() {
		return menu_title;
	}
	public void setMenu_title(String menu_title) {
		this.menu_title = menu_title;
	}
	public String getMenu_group() {
		return menu_group;
	}
	public void setMenu_group(String menu_group) {
		this.menu_group = menu_group;
	}
	public int getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(int menu_level) {
		this.menu_level = menu_level;
	}
	public int getMenu_sort() {
		return menu_sort;
	}
	public void setMenu_sort(int menu_sort) {
		this.menu_sort = menu_sort;
	}
	public String getMenu_uri() {
		return menu_uri;
	}
	public void setMenu_uri(String menu_uri) {
		this.menu_uri = menu_uri;
	}
	
	public String getMenu_role() {
		return menu_role;
	}
	public void setMenu_role(String menu_role) {
		this.menu_role = menu_role;
	}
	public String getMenu_css() {
		return menu_css;
	}
	public void setMenu_css(String menu_css) {
		this.menu_css = menu_css;
	}
	public String getMenu_img() {
		return menu_img;
	}
	public void setMenu_img(String menu_img) {
		this.menu_img = menu_img;
	}
	

}
