package com.noaa.nema.viewer.kmaInform.dao;

public class KmaWaterDto {

	private String dt_obsdh;
	private String cd_wlobs;
	private String nm_obs;
	private String law_code;
	private double qty_hrwl;
	private double qty_flw;
	private String cord_lon;
	private String cord_lat;
	private double qty_mxdokcnt;
	private double swl;
	private double lwl;
	private double pre_qty_hrwl;
	private String addr;
	private String dt_opn;
	private double srcdis;
	private double tototf;

	private String cd_dam;
	
	public String getDt_obsdh() {
		return dt_obsdh;
	}
	public void setDt_obsdh(String dt_obsdh) {
		this.dt_obsdh = dt_obsdh;
	}
	public String getCd_wlobs() {
		return cd_wlobs;
	}
	public void setCd_wlobs(String cd_wlobs) {
		this.cd_wlobs = cd_wlobs;
	}
	public String getNm_obs() {
		return nm_obs;
	}
	public void setNm_obs(String nm_obs) {
		this.nm_obs = nm_obs;
	}
	public double getQty_hrwl() {
		return qty_hrwl;
	}
	public void setQty_hrwl(double qty_hrwl) {
		this.qty_hrwl = qty_hrwl;
	}
	public double getQty_flw() {
		return qty_flw;
	}
	public void setQty_flw(double qty_flw) {
		this.qty_flw = qty_flw;
	}
	public String getCord_lon() {
		return cord_lon;
	}
	public void setCord_lon(String cord_lon) {
		this.cord_lon = cord_lon;
	}
	public String getCord_lat() {
		return cord_lat;
	}
	public void setCord_lat(String cord_lat) {
		this.cord_lat = cord_lat;
	}
	public double getQty_mxdokcnt() {
		return qty_mxdokcnt;
	}
	public void setQty_mxdokcnt(double qty_mxdokcnt) {
		this.qty_mxdokcnt = qty_mxdokcnt;
	}
	public double getSwl() {
		return swl;
	}
	public void setSwl(double swl) {
		this.swl = swl;
	}
	public double getLwl() {
		return lwl;
	}
	public void setLwl(double lwl) {
		this.lwl = lwl;
	}
	public double getPre_qty_hrwl() {
		return pre_qty_hrwl;
	}
	public void setPre_qty_hrwl(double pre_qty_hrwl) {
		this.pre_qty_hrwl = pre_qty_hrwl;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDt_opn() {
		return dt_opn;
	}
	public void setDt_opn(String dt_opn) {
		this.dt_opn = dt_opn;
	}
	public double getSrcdis() {
		return srcdis;
	}
	public void setSrcdis(double srcdis) {
		this.srcdis = srcdis;
	}
	public double getTototf() {
		return tototf;
	}
	public void setTototf(double tototf) {
		this.tototf = tototf;
	}
	public String getCd_dam() {
		return cd_dam;
	}
	public void setCd_dam(String cd_dam) {
		this.cd_dam = cd_dam;
	}
	public String getLaw_code() {
		return law_code;
	}
	public void setLaw_code(String law_code) {
		this.law_code = law_code;
	}
}
