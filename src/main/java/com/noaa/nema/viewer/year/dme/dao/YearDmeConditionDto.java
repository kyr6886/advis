package com.noaa.nema.viewer.year.dme.dao;

import com.noaa.nema.viewer.asos.dao.ObsAsosDto;

public class YearDmeConditionDto {
	private int seq;
	private String beg_year;
	private String beg_month;
	
	private String beg_day;
	private String end_year;
	private String end_month;
	private String end_day;
	private String damage_type;
	private String description;
	private String damages;
	private String causes;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public String getEnd_year() {
		return end_year;
	}
	public void setEnd_year(String end_year) {
		this.end_year = end_year;
	}
	public String getEnd_month() {
		return end_month;
	}
	public void setEnd_month(String end_month) {
		this.end_month = end_month;
	}
	public String getEnd_day() {
		return end_day;
	}
	public void setEnd_day(String end_day) {
		this.end_day = end_day;
	}
	public String getDamage_type() {
		return damage_type;
	}
	public void setDamage_type(String damage_type) {
		this.damage_type = damage_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDamages() {
		return damages;
	}
	public void setDamages(String damages) {
		this.damages = damages;
	}
	public String getCauses() {
		return causes;
	}
	public void setCauses(String causes) {
		this.causes = causes;
	}
	
}
