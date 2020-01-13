package com.noaa.base.bbs.dao;

import java.util.List;

import com.noaa.base.BaseDTO;

public class BBSMasterDTO extends BaseDTO {
	private String bbs_id;
	private String bbs_title;
	
	private String read_role_id;
	private String write_role_id;
	private String bbs_list_view;
	private String bbs_write_view;
	private String bbs_update_view;
	private String bbs_read_view;
	private String bbs_img_path;
	private String bbs_info_html;
	private String bbs_pub_yn;
	private String category_seqs;
	private String category_titles;
    private String enableReadYN;
    private String enableWriteYN;
    
    	
	public String getEnableWriteYN() {
		return enableWriteYN;
	}

	public void setEnableWriteYN(String enableWriteYN) {
		this.enableWriteYN = enableWriteYN;
	}

	public String getEnableReadYN() {
		return enableReadYN;
	}

	public void setEnableReadYN(String enableReadYN) {
		this.enableReadYN = enableReadYN;
	}

	private List<BBSCategoryDTO> listCategory;

	public String getBbs_id() {
		return bbs_id;
	}

	public void setBbs_id(String bbs_id) {
		this.bbs_id = bbs_id;
	}

	public String getBbs_title() {
		return bbs_title;
	}

	public void setBbs_title(String bbs_title) {
		this.bbs_title = bbs_title;
	}

	public String getRead_role_id() {
		return read_role_id;
	}

	public void setRead_role_id(String read_role_id) {
		this.read_role_id = read_role_id;
	}

	public String getWrite_role_id() {
		return write_role_id;
	}

	public void setWrite_role_id(String write_role_id) {
		this.write_role_id = write_role_id;
	}

	public String getBbs_list_view() {
		return bbs_list_view;
	}

	public void setBbs_list_view(String bbs_list_view) {
		this.bbs_list_view = bbs_list_view;
	}

	public String getBbs_write_view() {
		return bbs_write_view;
	}

	public void setBbs_write_view(String bbs_write_view) {
		this.bbs_write_view = bbs_write_view;
	}

	public String getBbs_update_view() {
		return bbs_update_view;
	}

	public void setBbs_update_view(String bbs_update_view) {
		this.bbs_update_view = bbs_update_view;
	}

	public String getBbs_read_view() {
		return bbs_read_view;
	}

	public void setBbs_read_view(String bbs_read_view) {
		this.bbs_read_view = bbs_read_view;
	}

	public String getBbs_img_path() {
		return bbs_img_path;
	}

	public void setBbs_img_path(String bbs_img_path) {
		this.bbs_img_path = bbs_img_path;
	}

	public String getBbs_info_html() {
		return bbs_info_html;
	}

	public void setBbs_info_html(String bbs_info_html) {
		this.bbs_info_html = bbs_info_html;
	}

	public String getBbs_pub_yn() {
		return bbs_pub_yn;
	}

	public void setBbs_pub_yn(String bbs_pub_yn) {
		this.bbs_pub_yn = bbs_pub_yn;
	}

	public String getCategory_seqs() {
		return category_seqs;
	}

	public void setCategory_seqs(String category_seqs) {
		this.category_seqs = category_seqs;
	}

	public String getCategory_titles() {
		return category_titles;
	}

	public void setCategory_titles(String category_titles) {
		this.category_titles = category_titles;
	}

	public List<BBSCategoryDTO> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<BBSCategoryDTO> listCategory) {
		this.listCategory = listCategory;
	}
	
	
}
