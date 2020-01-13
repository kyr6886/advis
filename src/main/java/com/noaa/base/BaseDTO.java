package com.noaa.base;

import java.util.Date;

public class BaseDTO {
	protected Date create_date;
	protected String use_yn;
	protected String create_user_id;
	protected String create_user_name;
	protected Date update_date;
	protected String update_user_id;
	
	//dsi 관련재난
	private String disaster_type ;
	
	
	public String getDisaster_type() {
		return disaster_type;
	}

	public void setDisaster_type(String disaster_type) {
		this.disaster_type = disaster_type;
	}

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
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

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public String getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}

}
