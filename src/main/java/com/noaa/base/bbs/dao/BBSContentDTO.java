package com.noaa.base.bbs.dao;

import java.util.List;

import com.noaa.base.BaseDTO;
import com.noaa.base.file.dao.AttachFileDTO;

public class BBSContentDTO extends BaseDTO {
	private int bbs_seq;
	private String bbs_id;
	private String bbs_cont;
	private String bbs_cont_title;
	private int bbs_group;
	private int  bbs_sort;
	private int  bbs_parent;
	private int bbs_level;
	private int bbs_read;
	private String file_grp_id;
	private int category_seq;
	private String create_ip;
	private String category_title;
	private int fileSeq;
	private String fixed_yn;
	private String createUserID;
	private String bbs_title;
	private String edit_yn;
	private String read_yn;
	
	
	
	public String getRead_yn() {
		return read_yn;
	}
	public void setRead_yn(String read_yn) {
		this.read_yn = read_yn;
	}
	public String getEdit_yn() {
		return edit_yn;
	}
	public void setEdit_yn(String edit_yn) {
		this.edit_yn = edit_yn;
	}
	public String getBbs_title() {
		return bbs_title;
	}
	public void setBbs_title(String bbs_title) {
		this.bbs_title = bbs_title;
	}
	public String getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
	public String getFixed_yn() {
		return fixed_yn;
	}
	public void setFixed_yn(String fixed_yn) {
		this.fixed_yn = fixed_yn;
	}
	private List<AttachFileDTO> listFiles;
	
	public List<AttachFileDTO> getListFiles() {
		return listFiles;
	}
	public void setListFiles(List<AttachFileDTO> listFiles) {
		this.listFiles = listFiles;
	}
	public int getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}
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
	public int getBbs_read() {
		return bbs_read;
	}
	public void setBbs_read(int bbs_read) {
		this.bbs_read = bbs_read;
	}
	public String getCreate_ip() {
		return create_ip;
	}
	public void setCreate_ip(String create_ip) {
		this.create_ip = create_ip;
	}
	
	public String getBbs_cont_title() {
		return bbs_cont_title;
	}
	public void setBbs_cont_title(String bbs_cont_title) {
		this.bbs_cont_title = bbs_cont_title;
	}
	public int getBbs_seq() {
		return bbs_seq;
	}
	public void setBbs_seq(int bbs_seq) {
		this.bbs_seq = bbs_seq;
	}
	public String getBbs_id() {
		return bbs_id;
	}
	public void setBbs_id(String bbs_id) {
		this.bbs_id = bbs_id;
	}
	public String getBbs_cont() {
		return bbs_cont;
	}
	public void setBbs_cont(String bbs_cont) {
		this.bbs_cont = bbs_cont;
	}
	public int getBbs_group() {
		return bbs_group;
	}
	public void setBbs_group(int bbs_group) {
		this.bbs_group = bbs_group;
	}
	public int getBbs_sort() {
		return bbs_sort;
	}
	public void setBbs_sort(int bbs_sort) {
		this.bbs_sort = bbs_sort;
	}
	public int getBbs_parent() {
		return bbs_parent;
	}
	public void setBbs_parent(int bbs_parent) {
		this.bbs_parent = bbs_parent;
	}
	public int getBbs_level() {
		return bbs_level;
	}
	public void setBbs_level(int bbs_level) {
		this.bbs_level = bbs_level;
	}
	public String getFile_grp_id() {
		return file_grp_id;
	}
	public void setFile_grp_id(String file_grp_id) {
		this.file_grp_id = file_grp_id;
	}

	
}
