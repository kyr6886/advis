package com.noaa.base.meta.dao;

import com.noaa.base.BaseDTO;

public class MetaLawDTO extends BaseDTO {
	private String law_code;
	private String law_sido;
	private String law_sigungu;
	private String law_dong;
	private String law_ri;
	private String code_create_day;
	private String code_delete_day;
	
	public String getLaw_code() {
		return law_code;
	}
	public void setLaw_code(String law_code) {
		this.law_code = law_code;
	}
	public String getLaw_sido() {
		return law_sido;
	}
	public void setLaw_sido(String law_sido) {
		this.law_sido = law_sido;
	}
	public String getLaw_sigungu() {
		return law_sigungu;
	}
	public void setLaw_sigungu(String law_sigungu) {
		this.law_sigungu = law_sigungu;
	}
	public String getLaw_dong() {
		return law_dong;
	}
	public void setLaw_dong(String law_dong) {
		this.law_dong = law_dong;
	}
	public String getLaw_ri() {
		return law_ri;
	}
	public void setLaw_ri(String law_ri) {
		this.law_ri = law_ri;
	}
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
	
	
	
}
