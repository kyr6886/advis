package com.noaa.nema.viewer.year.dme.dao;

import java.util.List;

import com.noaa.nema.viewer.asos.dao.ObsAsosDto;

public class YearDmeCodeDto {

	private String code;
	private String code_name_dme;
	private String group_code;
	private String code_name_grp;
	

	private String sido;
	private String sido_code;
	private String beg_year;
	private String beg_month;
	private String beg_day;
	private String beg_date;

	private String end_date;
	private String typ_seq;

	private String typ_name;

	private String damage_data_code;
	private List<YearDmePbmDto> listPbm;
	private List<YearDmePubDto> listPub;
	private List<ObsAsosDto> listRain;


	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode_name_dme() {
		return code_name_dme;
	}
	public void setCode_name_dme(String code_name_dme) {
		this.code_name_dme = code_name_dme;
	}
	public String getGroup_code() {
		return group_code;
	}
	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}
	public String getCode_name_grp() {
		return code_name_grp;
	}
	public void setCode_name_grp(String code_name_grp) {
		this.code_name_grp = code_name_grp;
	}
	
	public String getDamage_data_code() {
		return damage_data_code;
	}
	public void setDamage_data_code(String damage_data_code) {
		this.damage_data_code = damage_data_code;
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
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSido_code() {
		return sido_code;
	}
	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}
	public String getBeg_year() {
		return beg_year;
	}
	public void setBeg_year(String beg_year) {
		this.beg_year = beg_year;
	}
	public String getBeg_month() {
		return beg_month;
	}
	public void setBeg_month(String beg_month) {
		this.beg_month = beg_month;
	}
	public String getBeg_day() {
		return beg_day;
	}
	public void setBeg_day(String beg_day) {
		this.beg_day = beg_day;
	}
	public String getBeg_date() {
		return beg_date;
	}
	public void setBeg_date(String beg_date) {
		this.beg_date = beg_date;
	}
	public String getTyp_seq() {
		return typ_seq;
	}
	public void setTyp_seq(String typ_seq) {
		this.typ_seq = typ_seq;
	}
	public String getTyp_name() {
		return typ_name;
	}
	public void setTyp_name(String typ_name) {
		this.typ_name = typ_name;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}
