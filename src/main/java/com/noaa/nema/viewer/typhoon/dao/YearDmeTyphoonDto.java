package com.noaa.nema.viewer.typhoon.dao;

public class YearDmeTyphoonDto {
	private int seq;
	private String beg_date;
	private String end_date;
	private String typ_name;
	private String typ_en;
	
	private double total_damage;
	private double com_dme;
	
	
	public String getTyp_en() {
		return typ_en;
	}
	public void setTyp_en(String typ_en) {
		this.typ_en = typ_en;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public String getTyp_name() {
		return typ_name;
	}
	public void setTyp_name(String typ_name) {
		this.typ_name = typ_name;
	}
	public double getTotal_damage() {
		return total_damage;
	}
	public void setTotal_damage(double total_damage) {
		this.total_damage = total_damage;
	}
	public double getCom_dme() {
		return com_dme;
	}
	public void setCom_dme(double com_dme) {
		this.com_dme = com_dme;
	}
	
	
	
}
