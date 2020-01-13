package kr.dis.std.web.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;

import kr.dis.std.data.dao.TobKmaInformDTO;
import kr.dis.std.data.dao.TobKmaTyphoon5dsDTO;

public class DataManageViewModel extends BaseViewModel {
	private String stn_id;
	private String tm_fc;
	private String typ_seq;
	private String typ_name;
	private String typ_en;
	private String typ_lat;
	private String typ_lon;
	private String typ_ws;
	private String typ_ps;
	private String typ_15;
	private String ft1_lat;
	private String ft1_lon;
	private String ft1_ws;
	private String ft1_ps;
	private String ft2_lat;
	private String ft2_lon;
	private String ft2_ws;
	private String ft2_ps;
	private String ft3_lat;
	private String ft3_lon;
	private String ft3_ws;
	private String ft3_ps;
	
	private String cd_stn;
	private String dt_tm_fc;
	private String no_tm_seq;
	private String nm_man_fc;
	private String cd_war_fc;
	private String etc_ttl;
	private String sect_area_txt;
	private String dt_tm_ef_txt;
	private String stat_ctnt;
	private String dt_war_tm;
	private String stat_tm_ef;
	private String stat_pw_vl;
	private String etc_ref;
	private String dt_regt;

	private List<TobKmaTyphoon5dsDTO> typhoonList;
	private List<TobKmaInformDTO> informList;
	
	public List<TobKmaTyphoon5dsDTO> getTyphoonList() {
		return typhoonList;
	}
	public void setTyphoonList(List<TobKmaTyphoon5dsDTO> typhoonList) {
		this.typhoonList = typhoonList;
	}
	public List<TobKmaInformDTO> getInformList() {
		return informList;
	}
	public void setInformList(List<TobKmaInformDTO> informList) {
		this.informList = informList;
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
	public String getTyp_ws() {
		return typ_ws;
	}
	public void setTyp_ws(String typ_ws) {
		this.typ_ws = typ_ws;
	}
	public String getTyp_ps() {
		return typ_ps;
	}
	public void setTyp_ps(String typ_ps) {
		this.typ_ps = typ_ps;
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
	public String getFt1_ws() {
		return ft1_ws;
	}
	public void setFt1_ws(String ft1_ws) {
		this.ft1_ws = ft1_ws;
	}
	public String getFt1_ps() {
		return ft1_ps;
	}
	public void setFt1_ps(String ft1_ps) {
		this.ft1_ps = ft1_ps;
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
	public String getFt2_ws() {
		return ft2_ws;
	}
	public void setFt2_ws(String ft2_ws) {
		this.ft2_ws = ft2_ws;
	}
	public String getFt2_ps() {
		return ft2_ps;
	}
	public void setFt2_ps(String ft2_ps) {
		this.ft2_ps = ft2_ps;
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
	public String getFt3_ws() {
		return ft3_ws;
	}
	public void setFt3_ws(String ft3_ws) {
		this.ft3_ws = ft3_ws;
	}
	public String getFt3_ps() {
		return ft3_ps;
	}
	public void setFt3_ps(String ft3_ps) {
		this.ft3_ps = ft3_ps;
	}
	public String getCd_stn() {
		return cd_stn;
	}
	public void setCd_stn(String cd_stn) {
		this.cd_stn = cd_stn;
	}
	public String getDt_tm_fc() {
		return dt_tm_fc;
	}
	public void setDt_tm_fc(String dt_tm_fc) {
		this.dt_tm_fc = dt_tm_fc;
	}
	public String getNo_tm_seq() {
		return no_tm_seq;
	}
	public void setNo_tm_seq(String no_tm_seq) {
		this.no_tm_seq = no_tm_seq;
	}
	public String getNm_man_fc() {
		return nm_man_fc;
	}
	public void setNm_man_fc(String nm_man_fc) {
		this.nm_man_fc = nm_man_fc;
	}
	public String getCd_war_fc() {
		return cd_war_fc;
	}
	public void setCd_war_fc(String cd_war_fc) {
		this.cd_war_fc = cd_war_fc;
	}
	public String getEtc_ttl() {
		return etc_ttl;
	}
	public void setEtc_ttl(String etc_ttl) {
		this.etc_ttl = etc_ttl;
	}
	public String getSect_area_txt() {
		return sect_area_txt;
	}
	public void setSect_area_txt(String sect_area_txt) {
		this.sect_area_txt = sect_area_txt;
	}
	public String getDt_tm_ef_txt() {
		return dt_tm_ef_txt;
	}
	public void setDt_tm_ef_txt(String dt_tm_ef_txt) {
		this.dt_tm_ef_txt = dt_tm_ef_txt;
	}
	public String getStat_ctnt() {
		return stat_ctnt;
	}
	public void setStat_ctnt(String stat_ctnt) {
		this.stat_ctnt = stat_ctnt;
	}
	public String getDt_war_tm() {
		return dt_war_tm;
	}
	public void setDt_war_tm(String dt_war_tm) {
		this.dt_war_tm = dt_war_tm;
	}
	public String getStat_tm_ef() {
		return stat_tm_ef;
	}
	public void setStat_tm_ef(String stat_tm_ef) {
		this.stat_tm_ef = stat_tm_ef;
	}
	public String getStat_pw_vl() {
		return stat_pw_vl;
	}
	public void setStat_pw_vl(String stat_pw_vl) {
		this.stat_pw_vl = stat_pw_vl;
	}
	public String getEtc_ref() {
		return etc_ref;
	}
	public void setEtc_ref(String etc_ref) {
		this.etc_ref = etc_ref;
	}
	public String getDt_regt() {
		return dt_regt;
	}
	public void setDt_regt(String dt_regt) {
		this.dt_regt = dt_regt;
	}
	public String getTyp_15() {
		return typ_15;
	}
	public void setTyp_15(String typ_15) {
		this.typ_15 = typ_15;
	}
}