package com.noaa.nema.viewer.year.dme.dao;

import java.util.List;

import com.noaa.nema.viewer.asos.dao.ObsAsosDto;

public class YearDmeDateDto {

	private String damage_date_code;
	private String beg_date;
	private String end_date;
	private String code;
	private String code_name;

	private List<YearDmePbmDto> listPbm;
	private List<YearDmePubDto> listPub;
	private List<ObsAsosDto> listRain;
	
	public String getDamage_date_code() {
		return damage_date_code;
	}
	public void setDamage_date_code(String damage_date_code) {
		this.damage_date_code = damage_date_code;
	}
	public String getBeg_date() {
		return beg_date;
	}
	public void setBeg_date(String beg_date) {
		this.beg_date = beg_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public List<YearDmePbmDto> getListPbm() {
		return listPbm;
	}
	public void setListPbm(List<YearDmePbmDto> listPbm) {
		this.listPbm = listPbm;
	}
	public List<YearDmePubDto> getListPub() {
		return listPub;
	}
	public void setListPub(List<YearDmePubDto> listPub) {
		this.listPub = listPub;
	}
	public List<ObsAsosDto> getListRain() {
		return listRain;
	}
	public void setListRain(List<ObsAsosDto> listRain) {
		this.listRain = listRain;
	}



	
}
