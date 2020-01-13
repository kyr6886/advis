package com.noaa.base.bbs.dao;

import java.util.Date;

import com.noaa.base.BaseDTO;

public class BBSUserDTO extends BaseDTO {
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_phone;
	private String user_email;
	private String user_type_code;
	private String user_status_code;
	private String use_yn;
	private String user_sex_code;
	
	private String user_roles;
	private String user_status_title;
	private String user_type_title;
	private String user_key;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_type_code() {
		return user_type_code;
	}
	public void setUser_type_code(String user_type_code) {
		this.user_type_code = user_type_code;
	}
	public String getUser_status_code() {
		return user_status_code;
	}
	public void setUser_status_code(String user_status_code) {
		this.user_status_code = user_status_code;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getUser_sex_code() {
		return user_sex_code;
	}
	public void setUser_sex_code(String user_sex_code) {
		this.user_sex_code = user_sex_code;
	}
	public String getUser_roles() {
		return user_roles;
	}
	public void setUser_roles(String user_roles) {
		this.user_roles = user_roles;
	}
	public String getUser_status_title() {
		return user_status_title;
	}
	public void setUser_status_title(String user_status_title) {
		this.user_status_title = user_status_title;
	}
	public String getUser_type_title() {
		return user_type_title;
	}
	public void setUser_type_title(String user_type_title) {
		this.user_type_title = user_type_title;
	}
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	
	
}
