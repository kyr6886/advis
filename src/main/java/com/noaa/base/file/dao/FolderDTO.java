package com.noaa.base.file.dao;

import com.noaa.base.BaseDTO;

public class FolderDTO extends BaseDTO {
	private double folder_size;
	private String folder_ext;
	
	public double getFolder_size() {
		return folder_size;
	}
	public void setFolder_size(double folder_size) {
		this.folder_size = folder_size;
	}
	public String getFolder_ext() {
		return folder_ext;
	}
	public void setFolder_ext(String folder_ext) {
		this.folder_ext = folder_ext;
	}
	
	
}
