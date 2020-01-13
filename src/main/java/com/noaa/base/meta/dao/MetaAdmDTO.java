package com.noaa.base.meta.dao;

import com.noaa.base.BaseDTO;

public class MetaAdmDTO extends BaseDTO {
	private String adm_code;
	private String adm_sido;
	private String adm_sigungu;
	private String adm_dong;
	private String code_create_day;
	private String code_delete_day;
	
	
	public String getCode_create_day() {
		return code_create_day;
	}
	public void setCode_create_day(String code_create_day) {
		this.code_create_day = code_create_day;
	}

	public String getCode_delete_day() {
		return code_delete_day;
	}
	public void setCode_delete_day(String code_delete_day) {
		this.code_delete_day = code_delete_day;
	}
	public String getAdm_code() {
		return adm_code;
	}
	public void setAdm_code(String adm_code) {
		this.adm_code = adm_code;
	}
	public String getAdm_sido() {
		return adm_sido;
	}
	public void setAdm_sido(String adm_sido) {
		this.adm_sido = adm_sido;
	}
	public String getAdm_sigungu() {
		return adm_sigungu;
	}
	public void setAdm_sigungu(String adm_sigungu) {
		this.adm_sigungu = adm_sigungu;
	}
	public String getAdm_dong() {
		return adm_dong;
	}
	public void setAdm_dong(String adm_dong) {
		this.adm_dong = adm_dong;
	}	
	
}
