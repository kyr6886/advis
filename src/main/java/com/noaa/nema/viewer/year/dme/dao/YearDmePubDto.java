package com.noaa.nema.viewer.year.dme.dao;

import java.util.List;

import com.noaa.nema.viewer.asos.dao.ObsAsosDto;

public class YearDmePubDto {

	private String damage_date_code;
	private String sido_code ;
	private String sigungu_code ;
	private String sido ;
	private String sigungu ;
	private String beg_year ;
	private String beg_month ;
	private String beg_day ;
	private String end_year ;
	private String end_month ;
	private String end_day ;
	private String pub_lon ;
	private String pub_lom ;
	private String pub_brn ;
	private String pub_brm ;
	private String pub_lbn_dme ;
	private String pub_lev_n ;
	private String pub_lev_m ;
	private String pub_lev_dme ;
	private String pub_rin ;
	private String pub_rim ;
	private String pub_rin_dme ;
	private String pub_srn ;
	private String pub_srm ;
	private String pub_srn_dme ;
	private String pub_wan ;
	private String pub_wan_dme ;
	private String pub_han ;
	private String pub_han_dme ;
	private String pub_pon ;
	private String pub_pon_dme ;
	private String pub_scn ;
	private String pub_subcn ;
	private String pub_scn_dme ;
	private String pub_ran ;
	private String pub_ram ;
	private String pub_ran_dme ;
	private String pub_irn ;
	private String pub_emn ;
	private String pub_ien_dme ;
	private String pub_sbn ;
	private String pub_sbh ;
	private String pub_sb_dme ;
	private String pub_lmn ;
	private String pub_lmh ;
	private String pub_lm_dme ;
	private String pub_sln_dme ;
	private String pub_net_n ;
	private String pub_net_dme ;
	private String pub_smn ;
	private String pub_smn_dme ;
	private String pub_etc ;
	private String pub_etc_dme ;
	private String pub_arn ;
	private String pub_arn_dme ;
	


	private double pub_total_dme;
	
	
	private String disaster_code;
	private String disaster_name;
	
	private String beg_date;
	private String end_date;
	

	private double sumRain;
	private double maxWind;
	
	private ObsAsosDto detailRain;
	
	public double getPub_total_dme() {
		return pub_total_dme;
	}
	public void setPub_total_dme(double pub_total_dme) {
		this.pub_total_dme = pub_total_dme;
	}
	 
    public String getDamage_date_code() {
		return damage_date_code;
	}
	public void setDamage_date_code(String damage_date_code) {
		this.damage_date_code = damage_date_code;
	}
	public String getSido_code() {
		return sido_code;
	}
	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}
	public String getSigungu_code() {
		return sigungu_code;
	}
	public void setSigungu_code(String sigungu_code) {
		this.sigungu_code = sigungu_code;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
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
	public String getPub_lon() {
		return pub_lon;
	}
	public void setPub_lon(String pub_lon) {
		this.pub_lon = pub_lon;
	}
	public String getPub_lom() {
		return pub_lom;
	}
	public void setPub_lom(String pub_lom) {
		this.pub_lom = pub_lom;
	}
	public String getPub_brn() {
		return pub_brn;
	}
	public void setPub_brn(String pub_brn) {
		this.pub_brn = pub_brn;
	}
	public String getPub_brm() {
		return pub_brm;
	}
	public void setPub_brm(String pub_brm) {
		this.pub_brm = pub_brm;
	}
	public String getPub_lbn_dme() {
		return pub_lbn_dme;
	}
	public void setPub_lbn_dme(String pub_lbn_dme) {
		this.pub_lbn_dme = pub_lbn_dme;
	}
	public String getPub_lev_n() {
		return pub_lev_n;
	}
	public void setPub_lev_n(String pub_lev_n) {
		this.pub_lev_n = pub_lev_n;
	}
	public String getPub_lev_m() {
		return pub_lev_m;
	}
	public void setPub_lev_m(String pub_lev_m) {
		this.pub_lev_m = pub_lev_m;
	}
	public String getPub_lev_dme() {
		return pub_lev_dme;
	}
	public void setPub_lev_dme(String pub_lev_dme) {
		this.pub_lev_dme = pub_lev_dme;
	}
	public String getPub_rin() {
		return pub_rin;
	}
	public void setPub_rin(String pub_rin) {
		this.pub_rin = pub_rin;
	}
	public String getPub_rim() {
		return pub_rim;
	}
	public void setPub_rim(String pub_rim) {
		this.pub_rim = pub_rim;
	}
	public String getPub_rin_dme() {
		return pub_rin_dme;
	}
	public void setPub_rin_dme(String pub_rin_dme) {
		this.pub_rin_dme = pub_rin_dme;
	}
	public String getPub_srn() {
		return pub_srn;
	}
	public void setPub_srn(String pub_srn) {
		this.pub_srn = pub_srn;
	}
	public String getPub_srm() {
		return pub_srm;
	}
	public void setPub_srm(String pub_srm) {
		this.pub_srm = pub_srm;
	}
	public String getPub_srn_dme() {
		return pub_srn_dme;
	}
	public void setPub_srn_dme(String pub_srn_dme) {
		this.pub_srn_dme = pub_srn_dme;
	}
	public String getPub_wan() {
		return pub_wan;
	}
	public void setPub_wan(String pub_wan) {
		this.pub_wan = pub_wan;
	}
	public String getPub_wan_dme() {
		return pub_wan_dme;
	}
	public void setPub_wan_dme(String pub_wan_dme) {
		this.pub_wan_dme = pub_wan_dme;
	}
	public String getPub_han() {
		return pub_han;
	}
	public void setPub_han(String pub_han) {
		this.pub_han = pub_han;
	}
	public String getPub_han_dme() {
		return pub_han_dme;
	}
	public void setPub_han_dme(String pub_han_dme) {
		this.pub_han_dme = pub_han_dme;
	}
	public String getPub_pon() {
		return pub_pon;
	}
	public void setPub_pon(String pub_pon) {
		this.pub_pon = pub_pon;
	}
	public String getPub_pon_dme() {
		return pub_pon_dme;
	}
	public void setPub_pon_dme(String pub_pon_dme) {
		this.pub_pon_dme = pub_pon_dme;
	}
	public String getPub_scn() {
		return pub_scn;
	}
	public void setPub_scn(String pub_scn) {
		this.pub_scn = pub_scn;
	}
	public String getPub_subcn() {
		return pub_subcn;
	}
	public void setPub_subcn(String pub_subcn) {
		this.pub_subcn = pub_subcn;
	}
	public String getPub_scn_dme() {
		return pub_scn_dme;
	}
	public void setPub_scn_dme(String pub_scn_dme) {
		this.pub_scn_dme = pub_scn_dme;
	}
	public String getPub_ran() {
		return pub_ran;
	}
	public void setPub_ran(String pub_ran) {
		this.pub_ran = pub_ran;
	}
	public String getPub_ram() {
		return pub_ram;
	}
	public void setPub_ram(String pub_ram) {
		this.pub_ram = pub_ram;
	}
	public String getPub_ran_dme() {
		return pub_ran_dme;
	}
	public void setPub_ran_dme(String pub_ran_dme) {
		this.pub_ran_dme = pub_ran_dme;
	}
	public String getPub_irn() {
		return pub_irn;
	}
	public void setPub_irn(String pub_irn) {
		this.pub_irn = pub_irn;
	}
	public String getPub_emn() {
		return pub_emn;
	}
	public void setPub_emn(String pub_emn) {
		this.pub_emn = pub_emn;
	}
	public String getPub_ien_dme() {
		return pub_ien_dme;
	}
	public void setPub_ien_dme(String pub_ien_dme) {
		this.pub_ien_dme = pub_ien_dme;
	}
	public String getPub_sbn() {
		return pub_sbn;
	}
	public void setPub_sbn(String pub_sbn) {
		this.pub_sbn = pub_sbn;
	}
	public String getPub_sbh() {
		return pub_sbh;
	}
	public void setPub_sbh(String pub_sbh) {
		this.pub_sbh = pub_sbh;
	}
	public String getPub_sb_dme() {
		return pub_sb_dme;
	}
	public void setPub_sb_dme(String pub_sb_dme) {
		this.pub_sb_dme = pub_sb_dme;
	}
	public String getPub_lmn() {
		return pub_lmn;
	}
	public void setPub_lmn(String pub_lmn) {
		this.pub_lmn = pub_lmn;
	}
	public String getPub_lmh() {
		return pub_lmh;
	}
	public void setPub_lmh(String pub_lmh) {
		this.pub_lmh = pub_lmh;
	}
	public String getPub_lm_dme() {
		return pub_lm_dme;
	}
	public void setPub_lm_dme(String pub_lm_dme) {
		this.pub_lm_dme = pub_lm_dme;
	}
	public String getPub_sln_dme() {
		return pub_sln_dme;
	}
	public void setPub_sln_dme(String pub_sln_dme) {
		this.pub_sln_dme = pub_sln_dme;
	}
	public String getPub_net_n() {
		return pub_net_n;
	}
	public void setPub_net_n(String pub_net_n) {
		this.pub_net_n = pub_net_n;
	}
	public String getPub_net_dme() {
		return pub_net_dme;
	}
	public void setPub_net_dme(String pub_net_dme) {
		this.pub_net_dme = pub_net_dme;
	}
	public String getPub_smn() {
		return pub_smn;
	}
	public void setPub_smn(String pub_smn) {
		this.pub_smn = pub_smn;
	}
	public String getPub_smn_dme() {
		return pub_smn_dme;
	}
	public void setPub_smn_dme(String pub_smn_dme) {
		this.pub_smn_dme = pub_smn_dme;
	}
	public String getPub_etc() {
		return pub_etc;
	}
	public void setPub_etc(String pub_etc) {
		this.pub_etc = pub_etc;
	}
	public String getPub_etc_dme() {
		return pub_etc_dme;
	}
	public void setPub_etc_dme(String pub_etc_dme) {
		this.pub_etc_dme = pub_etc_dme;
	}
	public String getPub_arn() {
		return pub_arn;
	}
	public void setPub_arn(String pub_arn) {
		this.pub_arn = pub_arn;
	}
	public String getPub_arn_dme() {
		return pub_arn_dme;
	}
	public void setPub_arn_dme(String pub_arn_dme) {
		this.pub_arn_dme = pub_arn_dme;
	}
	public String getDisaster_code() {
		return disaster_code;
	}
	public void setDisaster_code(String disaster_code) {
		this.disaster_code = disaster_code;
	}
	public String getDisaster_name() {
		return disaster_name;
	}
	public void setDisaster_name(String disaster_name) {
		this.disaster_name = disaster_name;
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
	public ObsAsosDto getDetailRain() {
		return detailRain;
	}
	public void setDetailRain(ObsAsosDto detailRain) {
		this.detailRain = detailRain;
	}
	public double getSumRain() {
		return sumRain;
	}
	public void setSumRain(double sumRain) {
		this.sumRain = sumRain;
	}
	public double getMaxWind() {
		return maxWind;
	}
	public void setMaxWind(double maxWind) {
		this.maxWind = maxWind;
	}

}
