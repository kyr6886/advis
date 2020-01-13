package com.noaa.base.account.dao;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7475514549323314794L;
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_phone;
	private String user_email;
	private String user_type_code;
	private String user_status_code;
	private Date update_date;
	private String update_user_id;
	private String use_yn;
	private Date create_date;
	private String create_user_id;
	private String user_sex_code;
	private String user_addr;
	private String user_roles;
	private String user_rolenames;
	private String user_status_title;
	private String user_type_title;
	private String user_key;
	private int user_fail_count;
	
	private String user_adm_code;
	private String user_dept;
	private String user_phone_office;
	private String user_position;
	private String user_work_cont;
	private String user_major;
	private String user_major_title;
	private String[] userAdm;
	
	private String dis_code;
	private String encUserID;
	private String encUserPwd;
	
	
	public String getEncUserID() {
		return encUserID;
	}

	public void setEncUserID(String encUserID) {
		this.encUserID = encUserID;
	}

	public String getEncUserPwd() {
		return encUserPwd;
	}

	public void setEncUserPwd(String encUserPwd) {
		this.encUserPwd = encUserPwd;
	}

	public int getUser_fail_count() {
		return user_fail_count;
	}

	public void setUser_fail_count(int user_fail_count) {
		this.user_fail_count = user_fail_count;
	}

	public String[] getUserAdm() {
		return userAdm;
	}

	public void setUserAdm(String[] userAdm) {
		this.userAdm = userAdm;
	}

	public String getUser_rolenames() {
		return user_rolenames;
	}

	public void setUser_rolenames(String user_rolenames) {
		this.user_rolenames = user_rolenames;
	}

	public String getDis_code() {
		return dis_code;
	}

	public void setDis_code(String dis_code) {
		this.dis_code = dis_code;
	}

	public String getUser_addr() {
		return user_addr;
	}

	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}

	public String getUser_major() {
		return user_major;
	}

	public void setUser_major(String user_major) {
		this.user_major = user_major;
	}

	public String getUser_major_title() {
		return user_major_title;
	}

	public void setUser_major_title(String user_major_title) {
		this.user_major_title = user_major_title;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUser_adm_code() {
		return user_adm_code;
	}

	public void setUser_adm_code(String user_adm_code) {
		this.user_adm_code = user_adm_code;
	}

	public String getUser_dept() {
		return user_dept;
	}

	public void setUser_dept(String user_dept) {
		this.user_dept = user_dept;
	}

	public String getUser_phone_office() {
		return user_phone_office;
	}

	public void setUser_phone_office(String user_phone_office) {
		this.user_phone_office = user_phone_office;
	}

	public String getUser_position() {
		return user_position;
	}

	public void setUser_position(String user_position) {
		this.user_position = user_position;
	}

	public String getUser_work_cont() {
		return user_work_cont;
	}

	public void setUser_work_cont(String user_work_cont) {
		this.user_work_cont = user_work_cont;
	}

	public String getUser_key() {
		return user_key;
	}

	public void setUser_key(String user_key) {
		this.user_key = user_key;
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

	public String getUser_status_code() {
		return user_status_code;
	}

	public void setUser_status_code(String user_status_code) {
		this.user_status_code = user_status_code;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(String update_user_id) {
		this.update_user_id = update_user_id;
	}

	public String getUser_sex_code() {
		return user_sex_code;
	}

	public void setUser_sex_code(String user_sex_code) {
		this.user_sex_code = user_sex_code;
	}

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

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

}
