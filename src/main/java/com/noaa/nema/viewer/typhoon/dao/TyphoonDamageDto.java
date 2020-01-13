package com.noaa.nema.viewer.typhoon.dao;

public class TyphoonDamageDto {
	private int rownum ;
	private String typ_name;
	private String typ_ps;
	private String typ_ws;
	private String typ_en;
	private String typ_sp;
	private String beg_date;
	private String typ_seq;
	private String tm_seq;
	private String end_date;
	private String com_dme;
	private String total_damage;
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public String getTyp_name() {
		return typ_name;
	}
	public void setTyp_name(String typ_name) {
		this.typ_name = typ_name;
	}
	public String getTyp_ps() {
		return typ_ps;
	}
	public void setTyp_ps(String typ_ps) {
		this.typ_ps = typ_ps;
	}
	public String getTyp_ws() {
		return typ_ws;
	}
	public void setTyp_ws(String typ_ws) {
		this.typ_ws = typ_ws;
	}
	public String getTyp_en() {
		return typ_en;
	}
	public void setTyp_en(String typ_en) {
		this.typ_en = typ_en;
	}
	public String getTyp_sp() {
		return typ_sp;
	}
	public void setTyp_sp(String typ_sp) {
		this.typ_sp = typ_sp;
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
	public String getTm_seq() {
		return tm_seq;
	}
	public void setTm_seq(String tm_seq) {
		this.tm_seq = tm_seq;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getCom_dme() {
		return com_dme;
	}
	public void setCom_dme(String com_dme) {
		this.com_dme = com_dme;
	}
	public String getTotal_damage() {
		return total_damage;
	}
	public void setTotal_damage(String total_damage) {
		this.total_damage = total_damage;
	}
	
}
