package com.noaa.base.meta.dao;

import com.noaa.base.BaseDTO;

public class MetaDTO extends BaseDTO {
	private String meta_id;
	private String meta_table;
	private String state;
	private String desc;
	
	public String getMeta_id() {
		return meta_id;
	}
	public void setMeta_id(String meta_id) {
		this.meta_id = meta_id;
	}
	public String getMeta_table() {
		return meta_table;
	}
	public void setMeta_table(String meta_table) {
		this.meta_table = meta_table;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	

	
	
	
	
	
}
