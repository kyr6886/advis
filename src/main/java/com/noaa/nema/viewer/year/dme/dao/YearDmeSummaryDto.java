package com.noaa.nema.viewer.year.dme.dao;

public class YearDmeSummaryDto {
	
	private String sigungu;
	private String damage_code;
	private String beg_date;
	private String end_date;
	private double person_dmage;
	private double com_damage;
	private double pri_damage;
	private double public_damage;
	private double rain_day;
	private double rain_60;
	private double rain_10;
	private int seq;
	private String sido_yn; //티센시도영역여부
	private String damage_info; //재해명
	
	
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getDamage_code() {
		return damage_code;
	}
	public void setDamage_code(String damage_code) {
		this.damage_code = damage_code;
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
	public double getPerson_dmage() {
		return person_dmage;
	}
	public void setPerson_dmage(double person_dmage) {
		this.person_dmage = person_dmage;
	}
	public double getCom_damage() {
		return com_damage;
	}
	public void setCom_damage(double com_damage) {
		this.com_damage = com_damage;
	}
	
	public double getPri_damage() {
		return pri_damage;
	}
	public void setPri_damage(double pri_damage) {
		this.pri_damage = pri_damage;
	}
	public double getPublic_damage() {
		return public_damage;
	}
	public void setPublic_damage(double public_damage) {
		this.public_damage = public_damage;
	}
	public double getRain_day() {
		return rain_day;
	}
	public void setRain_day(double rain_day) {
		this.rain_day = rain_day;
	}
	public double getRain_60() {
		return rain_60;
	}
	public void setRain_60(double rain_60) {
		this.rain_60 = rain_60;
	}
	public double getRain_10() {
		return rain_10;
	}
	public void setRain_10(double rain_10) {
		this.rain_10 = rain_10;
	}
	public void setRain_10(int rain_10) {
		this.rain_10 = rain_10;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSido_yn() {
		return sido_yn;
	}
	public void setSido_yn(String sido_yn) {
		this.sido_yn = sido_yn;
	}
	public String getDamage_info() {
		return damage_info;
	}
	public void setDamage_info(String damage_info) {
		this.damage_info = damage_info;
	}
	
	
}
