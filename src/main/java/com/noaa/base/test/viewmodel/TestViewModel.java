package com.noaa.base.test.viewmodel;

import com.noaa.base.BaseViewModel;

public class TestViewModel extends BaseViewModel {
	private String base64Str;
	private String fileName;
	
	public String getBase64Str() {
		return base64Str;
	}

	public void setBase64Str(String base64Str) {
		this.base64Str = base64Str;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
