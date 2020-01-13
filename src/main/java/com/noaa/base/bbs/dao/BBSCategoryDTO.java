package com.noaa.base.bbs.dao;

import com.noaa.base.BaseDTO;

public class BBSCategoryDTO extends BaseDTO {
	 private int category_seq; 
	 private String category_title;
	 private String bbs_id;
	 
	public String getCategory_title() {
		return category_title;
	}
	public void setCategory_title(String category_title) {
		this.category_title = category_title;
	}
	public int getCategory_seq() {
		return category_seq;
	}
	public void setCategory_seq(int category_seq) {
		this.category_seq = category_seq;
	}
	
	public String getBbs_id() {
		return bbs_id;
	}
	public void setBbs_id(String bbs_id) {
		this.bbs_id = bbs_id;
	} 
	 
}
