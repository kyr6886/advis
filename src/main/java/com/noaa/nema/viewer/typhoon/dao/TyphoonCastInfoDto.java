package com.noaa.nema.viewer.typhoon.dao;

import java.util.List;

import com.noaa.nema.viewer.year.dme.dao.YearDmeConditionDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeImagesDto;
public class TyphoonCastInfoDto {
	private int rowNum;
	private String stn_id;
	private String tm_fc;
	private String typ_seq;
	private String tm_seq;
	private String typ_name;
	private String typ_en;
	private String man_fc;
	private String typ_tm;
	private String typ_lat;
	private String typ_lon;
	private String typ_dir;
	private String typ_sp;
	private String typ_ps;
	private String typ_ws;
	private String typ_tp;
	private String typ_25;
	private String typ_25ed;
	private String typ_25er;
	private String typ_15;
	private String typ_15ed;
	private String typ_15er;
	private String ft1_tm;
	private String ft1_lat;
	private String ft1_lon;
	private String ft1_ps;
	private String ft1_ws;
	private String ft1_rad;
	private String ft1_15;
	private String ft1_15ed;
	private String ft1_15er;
	private String ft1_dir;
	private String ft1_sp;
	private String ft2_tm;
	private String ft2_lat;
	private String ft2_lon;
	private String ft2_ps;
	private String ft2_ws;
	private String ft2_rad;
	private String ft2_15;
	private String ft2_15ed;
	private String ft2_15er;
	private String ft2_dir;
	private String ft2_sp;
	private String ft3_tm;
	private String ft3_lat;
	private String ft3_lon;
	private String ft3_ps;
	private String ft3_ws;
	private String ft3_rad;
	private String ft3_15;
	private String ft3_15ed;
	private String ft3_15er;
	private String ft3_dir;
	private String ft3_sp;
	private String ft4_tm;
	private String ft4_lat;
	private String ft4_lon;
	private String ft4_ps;
	private String ft4_ws;
	private String ft4_rad;
	private String ft4_15;
	private String ft4_15ed;
	private String ft4_15er;
	private String ft4_dir;
	private String ft4_sp;
	private String ft5_tm;
	private String ft5_lat;
	private String ft5_lon;
	private String ft5_ps;
	private String ft5_ws;
	private String ft5_rad;
	private String ft5_15;
	private String ft5_15ed;
	private String ft5_15er;
	private String ft5_dir;
	private String ft5_sp;
	
	private String ft6_tm;
	private String ft6_lat;
	private String ft6_lon;
	private String ft6_ps;
	private String ft6_ws;
	private String ft6_rad;
	private String ft6_15;
	private String ft6_15ed;
	private String ft6_15er;
	private String ft6_dir;
	private String ft6_sp;
	
	private String ft7_tm;
	private String ft7_lat;
	private String ft7_lon;
	private String ft7_ps;
	private String ft7_ws;
	private String ft7_rad;
	private String ft7_15;
	private String ft7_15ed;
	private String ft7_15er;
	private String ft7_dir;
	private String ft7_sp;
	
	private String ft8_tm;
	private String ft8_lat;
	private String ft8_lon;
	private String ft8_ps;
	private String ft8_ws;
	private String ft8_rad;
	private String ft8_15;
	private String ft8_15ed;
	private String ft8_15er;
	private String ft8_dir;
	private String ft8_sp;
	
	private String typ_ws_kor;
	private String typ_15_kor;
	private String typ_dir_kor;
	private String typ_ws_time;
	private String beg_year;
	
	private String beg_date;
	private String end_date;
	private String impact_yn;
	private int countPosition;
	private String com_dem;
	private String total_damage;
	private String typ_size_kor;
	private String pw_kor;
	private List<YearDmeDto>  personDamage;
	private List<YearDmeDto>  moneyDamage;
	private List<TyphoonCastInfoDto> listPosition;
	private YearDmeConditionDto detailCondition;
	private YearDmeDto detailSummary;
	private List<YearDmeImagesDto> listImages;
	private YearDmeTyphoonDto yearDmeTyphoon;
	private float distance;
	

	
	
	public String getFt6_tm() {
		return ft6_tm;
	}
	public void setFt6_tm(String ft6_tm) {
		this.ft6_tm = ft6_tm;
	}
	public String getFt6_lat() {
		return ft6_lat;
	}
	public void setFt6_lat(String ft6_lat) {
		this.ft6_lat = ft6_lat;
	}
	public String getFt6_lon() {
		return ft6_lon;
	}
	public void setFt6_lon(String ft6_lon) {
		this.ft6_lon = ft6_lon;
	}
	public String getFt6_ps() {
		return ft6_ps;
	}
	public void setFt6_ps(String ft6_ps) {
		this.ft6_ps = ft6_ps;
	}
	public String getFt6_ws() {
		return ft6_ws;
	}
	public void setFt6_ws(String ft6_ws) {
		this.ft6_ws = ft6_ws;
	}
	public String getFt6_rad() {
		return ft6_rad;
	}
	public void setFt6_rad(String ft6_rad) {
		this.ft6_rad = ft6_rad;
	}
	public String getFt6_15() {
		return ft6_15;
	}
	public void setFt6_15(String ft6_15) {
		this.ft6_15 = ft6_15;
	}
	public String getFt6_15ed() {
		return ft6_15ed;
	}
	public void setFt6_15ed(String ft6_15ed) {
		this.ft6_15ed = ft6_15ed;
	}
	public String getFt6_15er() {
		return ft6_15er;
	}
	public void setFt6_15er(String ft6_15er) {
		this.ft6_15er = ft6_15er;
	}
	public String getFt6_dir() {
		return ft6_dir;
	}
	public void setFt6_dir(String ft6_dir) {
		this.ft6_dir = ft6_dir;
	}
	public String getFt6_sp() {
		return ft6_sp;
	}
	public void setFt6_sp(String ft6_sp) {
		this.ft6_sp = ft6_sp;
	}
	public String getFt7_tm() {
		return ft7_tm;
	}
	public void setFt7_tm(String ft7_tm) {
		this.ft7_tm = ft7_tm;
	}
	public String getFt7_lat() {
		return ft7_lat;
	}
	public void setFt7_lat(String ft7_lat) {
		this.ft7_lat = ft7_lat;
	}
	public String getFt7_lon() {
		return ft7_lon;
	}
	public void setFt7_lon(String ft7_lon) {
		this.ft7_lon = ft7_lon;
	}
	public String getFt7_ps() {
		return ft7_ps;
	}
	public void setFt7_ps(String ft7_ps) {
		this.ft7_ps = ft7_ps;
	}
	public String getFt7_ws() {
		return ft7_ws;
	}
	public void setFt7_ws(String ft7_ws) {
		this.ft7_ws = ft7_ws;
	}
	public String getFt7_rad() {
		return ft7_rad;
	}
	public void setFt7_rad(String ft7_rad) {
		this.ft7_rad = ft7_rad;
	}
	public String getFt7_15() {
		return ft7_15;
	}
	public void setFt7_15(String ft7_15) {
		this.ft7_15 = ft7_15;
	}
	public String getFt7_15ed() {
		return ft7_15ed;
	}
	public void setFt7_15ed(String ft7_15ed) {
		this.ft7_15ed = ft7_15ed;
	}
	public String getFt7_15er() {
		return ft7_15er;
	}
	public void setFt7_15er(String ft7_15er) {
		this.ft7_15er = ft7_15er;
	}
	public String getFt7_dir() {
		return ft7_dir;
	}
	public void setFt7_dir(String ft7_dir) {
		this.ft7_dir = ft7_dir;
	}
	public String getFt7_sp() {
		return ft7_sp;
	}
	public void setFt7_sp(String ft7_sp) {
		this.ft7_sp = ft7_sp;
	}
	public String getFt8_tm() {
		return ft8_tm;
	}
	public void setFt8_tm(String ft8_tm) {
		this.ft8_tm = ft8_tm;
	}
	public String getFt8_lat() {
		return ft8_lat;
	}
	public void setFt8_lat(String ft8_lat) {
		this.ft8_lat = ft8_lat;
	}
	public String getFt8_lon() {
		return ft8_lon;
	}
	public void setFt8_lon(String ft8_lon) {
		this.ft8_lon = ft8_lon;
	}
	public String getFt8_ps() {
		return ft8_ps;
	}
	public void setFt8_ps(String ft8_ps) {
		this.ft8_ps = ft8_ps;
	}
	public String getFt8_ws() {
		return ft8_ws;
	}
	public void setFt8_ws(String ft8_ws) {
		this.ft8_ws = ft8_ws;
	}
	public String getFt8_rad() {
		return ft8_rad;
	}
	public void setFt8_rad(String ft8_rad) {
		this.ft8_rad = ft8_rad;
	}
	public String getFt8_15() {
		return ft8_15;
	}
	public void setFt8_15(String ft8_15) {
		this.ft8_15 = ft8_15;
	}
	public String getFt8_15ed() {
		return ft8_15ed;
	}
	public void setFt8_15ed(String ft8_15ed) {
		this.ft8_15ed = ft8_15ed;
	}
	public String getFt8_15er() {
		return ft8_15er;
	}
	public void setFt8_15er(String ft8_15er) {
		this.ft8_15er = ft8_15er;
	}
	public String getFt8_dir() {
		return ft8_dir;
	}
	public void setFt8_dir(String ft8_dir) {
		this.ft8_dir = ft8_dir;
	}
	public String getFt8_sp() {
		return ft8_sp;
	}
	public void setFt8_sp(String ft8_sp) {
		this.ft8_sp = ft8_sp;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public String getTyp_size_kor() {
		return typ_size_kor;
	}
	public void setTyp_size_kor(String typ_size_kor) {
		this.typ_size_kor = typ_size_kor;
	}
	public String getPw_kor() {
		return pw_kor;
	}
	public void setPw_kor(String pw_kor) {
		this.pw_kor = pw_kor;
	}
	public String getCom_dem() {
		return com_dem;
	}
	public void setCom_dem(String com_dem) {
		this.com_dem = com_dem;
	}
	public String getTotal_damage() {
		return total_damage;
	}
	public void setTotal_damage(String total_damage) {
		this.total_damage = total_damage;
	}
	public YearDmeTyphoonDto getYearDmeTyphoon() {
		return yearDmeTyphoon;
	}
	public void setYearDmeTyphoon(YearDmeTyphoonDto yearDmeTyphoon) {
		this.yearDmeTyphoon = yearDmeTyphoon;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public List<YearDmeImagesDto> getListImages() {
		return listImages;
	}
	public void setListImages(List<YearDmeImagesDto> listImages) {
		this.listImages = listImages;
	}
	public YearDmeDto getDetailSummary() {
		return detailSummary;
	}
	public void setDetailSummary(YearDmeDto detailSummary) {
		this.detailSummary = detailSummary;
	}
	public YearDmeConditionDto getDetailCondition() {
		return detailCondition;
	}
	public void setDetailCondition(YearDmeConditionDto detailCondition) {
		this.detailCondition = detailCondition;
	}
	public List<TyphoonCastInfoDto> getListPosition() {
		return listPosition;
	}
	public void setListPosition(List<TyphoonCastInfoDto> listPosition) {
		this.listPosition = listPosition;
	}
	public List<YearDmeDto> getPersonDamage() {
		return personDamage;
	}
	public void setPersonDamage(List<YearDmeDto> personDamage) {
		this.personDamage = personDamage;
	}
	public List<YearDmeDto> getMoneyDamage() {
		return moneyDamage;
	}
	public void setMoneyDamage(List<YearDmeDto> moneyDamage) {
		this.moneyDamage = moneyDamage;
	}

	public int getCountPosition() {
		return countPosition;
	}
	public void setCountPosition(int countPosition) {
		this.countPosition = countPosition;
	}
	public String getBeg_year() {
		return beg_year;
	}
	public void setBeg_year(String beg_year) {
		this.beg_year = beg_year;
	}
	public String getStn_id() {
		return stn_id;
	}
	public void setStn_id(String stn_id) {
		this.stn_id = stn_id;
	}
	public String getTm_fc() {
		return tm_fc;
	}
	public void setTm_fc(String tm_fc) {
		this.tm_fc = tm_fc;
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
	public String getTyp_name() {
		return typ_name;
	}
	public void setTyp_name(String typ_name) {
		this.typ_name = typ_name;
	}
	public String getTyp_en() {
		return typ_en;
	}
	public void setTyp_en(String typ_en) {
		this.typ_en = typ_en;
	}
	public String getMan_fc() {
		return man_fc;
	}
	public void setMan_fc(String man_fc) {
		this.man_fc = man_fc;
	}
	public String getTyp_tm() {
		return typ_tm;
	}
	public void setTyp_tm(String typ_tm) {
		this.typ_tm = typ_tm;
	}
	public String getTyp_lat() {
		return typ_lat;
	}
	public void setTyp_lat(String typ_lat) {
		this.typ_lat = typ_lat;
	}
	public String getTyp_lon() {
		return typ_lon;
	}
	public void setTyp_lon(String typ_lon) {
		this.typ_lon = typ_lon;
	}
	public String getTyp_dir() {
		return typ_dir;
	}
	public void setTyp_dir(String typ_dir) {
		this.typ_dir = typ_dir;
	}
	public String getTyp_sp() {
		return typ_sp;
	}
	public void setTyp_sp(String typ_sp) {
		this.typ_sp = typ_sp;
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
	public String getTyp_tp() {
		return typ_tp;
	}
	public void setTyp_tp(String typ_tp) {
		this.typ_tp = typ_tp;
	}
	public String getTyp_25() {
		return typ_25;
	}
	public void setTyp_25(String typ_25) {
		this.typ_25 = typ_25;
	}
	public String getTyp_25ed() {
		return typ_25ed;
	}
	public void setTyp_25ed(String typ_25ed) {
		this.typ_25ed = typ_25ed;
	}
	public String getTyp_25er() {
		return typ_25er;
	}
	public void setTyp_25er(String typ_25er) {
		this.typ_25er = typ_25er;
	}
	public String getTyp_15() {
		return typ_15;
	}
	public void setTyp_15(String typ_15) {
		this.typ_15 = typ_15;
	}
	public String getTyp_15ed() {
		return typ_15ed;
	}
	public void setTyp_15ed(String typ_15ed) {
		this.typ_15ed = typ_15ed;
	}
	public String getTyp_15er() {
		return typ_15er;
	}
	public void setTyp_15er(String typ_15er) {
		this.typ_15er = typ_15er;
	}
	public String getFt1_tm() {
		return ft1_tm;
	}
	public void setFt1_tm(String ft1_tm) {
		this.ft1_tm = ft1_tm;
	}
	public String getFt1_lat() {
		return ft1_lat;
	}
	public void setFt1_lat(String ft1_lat) {
		this.ft1_lat = ft1_lat;
	}
	public String getFt1_lon() {
		return ft1_lon;
	}
	public void setFt1_lon(String ft1_lon) {
		this.ft1_lon = ft1_lon;
	}
	public String getFt1_ps() {
		return ft1_ps;
	}
	public void setFt1_ps(String ft1_ps) {
		this.ft1_ps = ft1_ps;
	}
	public String getFt1_ws() {
		return ft1_ws;
	}
	public void setFt1_ws(String ft1_ws) {
		this.ft1_ws = ft1_ws;
	}
	public String getFt1_rad() {
		return ft1_rad;
	}
	public void setFt1_rad(String ft1_rad) {
		this.ft1_rad = ft1_rad;
	}
	public String getFt1_15() {
		return ft1_15;
	}
	public void setFt1_15(String ft1_15) {
		this.ft1_15 = ft1_15;
	}
	public String getFt1_15ed() {
		return ft1_15ed;
	}
	public void setFt1_15ed(String ft1_15ed) {
		this.ft1_15ed = ft1_15ed;
	}
	public String getFt1_15er() {
		return ft1_15er;
	}
	public void setFt1_15er(String ft1_15er) {
		this.ft1_15er = ft1_15er;
	}
	public String getFt1_dir() {
		return ft1_dir;
	}
	public void setFt1_dir(String ft1_dir) {
		this.ft1_dir = ft1_dir;
	}
	public String getFt1_sp() {
		return ft1_sp;
	}
	public void setFt1_sp(String ft1_sp) {
		this.ft1_sp = ft1_sp;
	}
	public String getFt2_tm() {
		return ft2_tm;
	}
	public void setFt2_tm(String ft2_tm) {
		this.ft2_tm = ft2_tm;
	}
	public String getFt2_lat() {
		return ft2_lat;
	}
	public void setFt2_lat(String ft2_lat) {
		this.ft2_lat = ft2_lat;
	}
	public String getFt2_lon() {
		return ft2_lon;
	}
	public void setFt2_lon(String ft2_lon) {
		this.ft2_lon = ft2_lon;
	}
	public String getFt2_ps() {
		return ft2_ps;
	}
	public void setFt2_ps(String ft2_ps) {
		this.ft2_ps = ft2_ps;
	}
	public String getFt2_ws() {
		return ft2_ws;
	}
	public void setFt2_ws(String ft2_ws) {
		this.ft2_ws = ft2_ws;
	}
	public String getFt2_rad() {
		return ft2_rad;
	}
	public void setFt2_rad(String ft2_rad) {
		this.ft2_rad = ft2_rad;
	}
	public String getFt2_15() {
		return ft2_15;
	}
	public void setFt2_15(String ft2_15) {
		this.ft2_15 = ft2_15;
	}
	public String getFt2_15ed() {
		return ft2_15ed;
	}
	public void setFt2_15ed(String ft2_15ed) {
		this.ft2_15ed = ft2_15ed;
	}
	public String getFt2_15er() {
		return ft2_15er;
	}
	public void setFt2_15er(String ft2_15er) {
		this.ft2_15er = ft2_15er;
	}
	public String getFt2_dir() {
		return ft2_dir;
	}
	public void setFt2_dir(String ft2_dir) {
		this.ft2_dir = ft2_dir;
	}
	public String getFt2_sp() {
		return ft2_sp;
	}
	public void setFt2_sp(String ft2_sp) {
		this.ft2_sp = ft2_sp;
	}
	public String getFt3_tm() {
		return ft3_tm;
	}
	public void setFt3_tm(String ft3_tm) {
		this.ft3_tm = ft3_tm;
	}
	public String getFt3_lat() {
		return ft3_lat;
	}
	public void setFt3_lat(String ft3_lat) {
		this.ft3_lat = ft3_lat;
	}
	public String getFt3_lon() {
		return ft3_lon;
	}
	public void setFt3_lon(String ft3_lon) {
		this.ft3_lon = ft3_lon;
	}
	public String getFt3_ps() {
		return ft3_ps;
	}
	public void setFt3_ps(String ft3_ps) {
		this.ft3_ps = ft3_ps;
	}
	public String getFt3_ws() {
		return ft3_ws;
	}
	public void setFt3_ws(String ft3_ws) {
		this.ft3_ws = ft3_ws;
	}
	public String getFt3_rad() {
		return ft3_rad;
	}
	public void setFt3_rad(String ft3_rad) {
		this.ft3_rad = ft3_rad;
	}
	public String getFt3_15() {
		return ft3_15;
	}
	public void setFt3_15(String ft3_15) {
		this.ft3_15 = ft3_15;
	}
	public String getFt3_15ed() {
		return ft3_15ed;
	}
	public void setFt3_15ed(String ft3_15ed) {
		this.ft3_15ed = ft3_15ed;
	}
	public String getFt3_15er() {
		return ft3_15er;
	}
	public void setFt3_15er(String ft3_15er) {
		this.ft3_15er = ft3_15er;
	}
	public String getFt3_dir() {
		return ft3_dir;
	}
	public void setFt3_dir(String ft3_dir) {
		this.ft3_dir = ft3_dir;
	}
	public String getFt3_sp() {
		return ft3_sp;
	}
	public void setFt3_sp(String ft3_sp) {
		this.ft3_sp = ft3_sp;
	}
	public String getFt4_tm() {
		return ft4_tm;
	}
	public void setFt4_tm(String ft4_tm) {
		this.ft4_tm = ft4_tm;
	}
	public String getFt4_lat() {
		return ft4_lat;
	}
	public void setFt4_lat(String ft4_lat) {
		this.ft4_lat = ft4_lat;
	}
	public String getFt4_lon() {
		return ft4_lon;
	}
	public void setFt4_lon(String ft4_lon) {
		this.ft4_lon = ft4_lon;
	}
	public String getFt4_ps() {
		return ft4_ps;
	}
	public void setFt4_ps(String ft4_ps) {
		this.ft4_ps = ft4_ps;
	}
	public String getFt4_ws() {
		return ft4_ws;
	}
	public void setFt4_ws(String ft4_ws) {
		this.ft4_ws = ft4_ws;
	}
	public String getFt4_rad() {
		return ft4_rad;
	}
	public void setFt4_rad(String ft4_rad) {
		this.ft4_rad = ft4_rad;
	}
	public String getFt4_15() {
		return ft4_15;
	}
	public void setFt4_15(String ft4_15) {
		this.ft4_15 = ft4_15;
	}
	public String getFt4_15ed() {
		return ft4_15ed;
	}
	public void setFt4_15ed(String ft4_15ed) {
		this.ft4_15ed = ft4_15ed;
	}
	public String getFt4_15er() {
		return ft4_15er;
	}
	public void setFt4_15er(String ft4_15er) {
		this.ft4_15er = ft4_15er;
	}
	public String getFt4_dir() {
		return ft4_dir;
	}
	public void setFt4_dir(String ft4_dir) {
		this.ft4_dir = ft4_dir;
	}
	public String getFt4_sp() {
		return ft4_sp;
	}
	public void setFt4_sp(String ft4_sp) {
		this.ft4_sp = ft4_sp;
	}
	public String getFt5_tm() {
		return ft5_tm;
	}
	public void setFt5_tm(String ft5_tm) {
		this.ft5_tm = ft5_tm;
	}
	public String getFt5_lat() {
		return ft5_lat;
	}
	public void setFt5_lat(String ft5_lat) {
		this.ft5_lat = ft5_lat;
	}
	public String getFt5_lon() {
		return ft5_lon;
	}
	public void setFt5_lon(String ft5_lon) {
		this.ft5_lon = ft5_lon;
	}
	public String getFt5_ps() {
		return ft5_ps;
	}
	public void setFt5_ps(String ft5_ps) {
		this.ft5_ps = ft5_ps;
	}
	public String getFt5_ws() {
		return ft5_ws;
	}
	public void setFt5_ws(String ft5_ws) {
		this.ft5_ws = ft5_ws;
	}
	public String getFt5_rad() {
		return ft5_rad;
	}
	public void setFt5_rad(String ft5_rad) {
		this.ft5_rad = ft5_rad;
	}
	public String getFt5_15() {
		return ft5_15;
	}
	public void setFt5_15(String ft5_15) {
		this.ft5_15 = ft5_15;
	}
	public String getFt5_15ed() {
		return ft5_15ed;
	}
	public void setFt5_15ed(String ft5_15ed) {
		this.ft5_15ed = ft5_15ed;
	}
	public String getFt5_15er() {
		return ft5_15er;
	}
	public void setFt5_15er(String ft5_15er) {
		this.ft5_15er = ft5_15er;
	}
	public String getFt5_dir() {
		return ft5_dir;
	}
	public void setFt5_dir(String ft5_dir) {
		this.ft5_dir = ft5_dir;
	}
	public String getFt5_sp() {
		return ft5_sp;
	}
	public void setFt5_sp(String ft5_sp) {
		this.ft5_sp = ft5_sp;
	}
	public String getTyp_ws_kor() {
		return typ_ws_kor;
	}
	public void setTyp_ws_kor(String typ_ws_kor) {
		this.typ_ws_kor = typ_ws_kor;
	}
	public String getTyp_15_kor() {
		return typ_15_kor;
	}
	public void setTyp_15_kor(String typ_15_kor) {
		this.typ_15_kor = typ_15_kor;
	}
	public String getTyp_dir_kor() {
		return typ_dir_kor;
	}
	public void setTyp_dir_kor(String typ_dir_kor) {
		this.typ_dir_kor = typ_dir_kor;
	}
	public String getTyp_ws_time() {
		return typ_ws_time;
	}
	public void setTyp_ws_time(String typ_ws_time) {
		this.typ_ws_time = typ_ws_time;
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
	public String getImpact_yn() {
		return impact_yn;
	}
	public void setImpact_yn(String impact_yn) {
		this.impact_yn = impact_yn;
	}


}
