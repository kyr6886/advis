package com.noaa.nema.viewer.year.dme.dao;

import java.util.List;

import com.noaa.nema.viewer.asos.dao.ObsAsosDto;

public class YearDmePbmDto {

	private String  damage_date_code;
	private String  sido_code;
	private String  sigungu_code;
	private String  sido;
	private String  sigungu;
	private String  beg_year;
	private String  beg_month ;
	private String  beg_day ;
	private String  end_year ;
	private String  end_month ;
	private String  end_day ;
	private String  vts_hue_b_typ_b ;
	private String  vts_hue_e_typ_b ;
	private String  vts_hue ;
	private String  vts_hue_b_typ_a ;
	private String  vts_hue_e_typ_a ;
	private String  vts_cnt ;
	private String  man_dah ;
	private String  man_dpr ;
	private String  man_ije ;
	private String  man_e_typ_c ;
	private String  man_dme ;
	private String  fld_fmd ;
	private String  fld_cty ;
	private String  fld_fmd_j_typ_a ;
	private String  fld_fmd_d_typ_a ;
	private String  fld_fmd_e_typ_a ;
	private String  fld_dme ;
	private String  bug_are_u_typ_a ;
	private String  bug_are_a_typ_a ;
	private String  bug_are ;
	private String  bug_hre ;
	private String  bug_are_typ_a ;
	private String  bug_fld ;
	private String  bug_are_sub_typ_a ;
	private String  bug_dme ;
	private String  shp_amn ;
	private String  shp_amt ;
	private String  shp_hmn ;
	private String  shp_hmt ;
	private String  shp_apn ;
	private String  shp_apt ;
	private String  shp_hpn ;
	private String  shp_hpt ;
	private String  shp_dme ;
	private String  fmd_fmd ;
	private String  fmd_fed ;
	private String  fmd_dme ;
	private String  hvt_fmd ;
	private String  hvt_fed ;
	private String  hvt_etc ;
	private String  hvt_dme ;
	private String  hvt_type_a_dme ;
	private String  pbm_wln ;
	private String  pbm_wln_dme ;
	private String  pbm_aln ;
	private String  pbm_aln_dme ;
	private String  pbm_adn ;
	private String  pbm_adn_dme ;
	private String  pbm_scn ;
	private String  pbm_scn_dme ;
	private String  pbm_fgn ;
	private String  pbm_fgn_dme ;
	private String  pbm_vhn ;
	private String  pbm_vhn_dme ;
	private String  pbm_etc ;
	private String  pbm_etc_dme ;
	

	private String disaster_code;

	private String disaster_name;

	private String beg_date;
	private String end_date;
	private float pbm_total_dme;
	private float man_total_dme;
	private float pub_total_dme;

	private int damageCount;
	private double sumRain;
	private double maxWind;
	private ObsAsosDto detailRain;
	
	public int getDamageCount() {
		return damageCount;
	}
	public void setDamageCount(int damageCount) {
		this.damageCount = damageCount;
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
	public String getVts_hue_b_typ_b() {
		return vts_hue_b_typ_b;
	}
	public void setVts_hue_b_typ_b(String vts_hue_b_typ_b) {
		this.vts_hue_b_typ_b = vts_hue_b_typ_b;
	}
	public String getVts_hue_e_typ_b() {
		return vts_hue_e_typ_b;
	}
	public void setVts_hue_e_typ_b(String vts_hue_e_typ_b) {
		this.vts_hue_e_typ_b = vts_hue_e_typ_b;
	}
	public String getVts_hue() {
		return vts_hue;
	}
	public void setVts_hue(String vts_hue) {
		this.vts_hue = vts_hue;
	}
	public String getVts_hue_b_typ_a() {
		return vts_hue_b_typ_a;
	}
	public void setVts_hue_b_typ_a(String vts_hue_b_typ_a) {
		this.vts_hue_b_typ_a = vts_hue_b_typ_a;
	}
	public String getVts_hue_e_typ_a() {
		return vts_hue_e_typ_a;
	}
	public void setVts_hue_e_typ_a(String vts_hue_e_typ_a) {
		this.vts_hue_e_typ_a = vts_hue_e_typ_a;
	}
	public String getVts_cnt() {
		return vts_cnt;
	}
	public void setVts_cnt(String vts_cnt) {
		this.vts_cnt = vts_cnt;
	}
	public String getMan_dah() {
		return man_dah;
	}
	public void setMan_dah(String man_dah) {
		this.man_dah = man_dah;
	}
	public String getMan_dpr() {
		return man_dpr;
	}
	public void setMan_dpr(String man_dpr) {
		this.man_dpr = man_dpr;
	}
	public String getMan_ije() {
		return man_ije;
	}
	public void setMan_ije(String man_ije) {
		this.man_ije = man_ije;
	}
	public String getMan_e_typ_c() {
		return man_e_typ_c;
	}
	public void setMan_e_typ_c(String man_e_typ_c) {
		this.man_e_typ_c = man_e_typ_c;
	}
	public String getMan_dme() {
		return man_dme;
	}
	public void setMan_dme(String man_dme) {
		this.man_dme = man_dme;
	}
	public String getFld_fmd() {
		return fld_fmd;
	}
	public void setFld_fmd(String fld_fmd) {
		this.fld_fmd = fld_fmd;
	}
	public String getFld_cty() {
		return fld_cty;
	}
	public void setFld_cty(String fld_cty) {
		this.fld_cty = fld_cty;
	}
	public String getFld_fmd_j_typ_a() {
		return fld_fmd_j_typ_a;
	}
	public void setFld_fmd_j_typ_a(String fld_fmd_j_typ_a) {
		this.fld_fmd_j_typ_a = fld_fmd_j_typ_a;
	}
	public String getFld_fmd_d_typ_a() {
		return fld_fmd_d_typ_a;
	}
	public void setFld_fmd_d_typ_a(String fld_fmd_d_typ_a) {
		this.fld_fmd_d_typ_a = fld_fmd_d_typ_a;
	}
	public String getFld_fmd_e_typ_a() {
		return fld_fmd_e_typ_a;
	}
	public void setFld_fmd_e_typ_a(String fld_fmd_e_typ_a) {
		this.fld_fmd_e_typ_a = fld_fmd_e_typ_a;
	}
	public String getFld_dme() {
		return fld_dme;
	}
	public void setFld_dme(String fld_dme) {
		this.fld_dme = fld_dme;
	}
	public String getBug_are_u_typ_a() {
		return bug_are_u_typ_a;
	}
	public void setBug_are_u_typ_a(String bug_are_u_typ_a) {
		this.bug_are_u_typ_a = bug_are_u_typ_a;
	}
	public String getBug_are_a_typ_a() {
		return bug_are_a_typ_a;
	}
	public void setBug_are_a_typ_a(String bug_are_a_typ_a) {
		this.bug_are_a_typ_a = bug_are_a_typ_a;
	}
	public String getBug_are() {
		return bug_are;
	}
	public void setBug_are(String bug_are) {
		this.bug_are = bug_are;
	}
	public String getBug_hre() {
		return bug_hre;
	}
	public void setBug_hre(String bug_hre) {
		this.bug_hre = bug_hre;
	}
	public String getBug_are_typ_a() {
		return bug_are_typ_a;
	}
	public void setBug_are_typ_a(String bug_are_typ_a) {
		this.bug_are_typ_a = bug_are_typ_a;
	}
	public String getBug_fld() {
		return bug_fld;
	}
	public void setBug_fld(String bug_fld) {
		this.bug_fld = bug_fld;
	}
	public String getBug_are_sub_typ_a() {
		return bug_are_sub_typ_a;
	}
	public void setBug_are_sub_typ_a(String bug_are_sub_typ_a) {
		this.bug_are_sub_typ_a = bug_are_sub_typ_a;
	}
	public String getBug_dme() {
		return bug_dme;
	}
	public void setBug_dme(String bug_dme) {
		this.bug_dme = bug_dme;
	}
	public String getShp_amn() {
		return shp_amn;
	}
	public void setShp_amn(String shp_amn) {
		this.shp_amn = shp_amn;
	}
	public String getShp_amt() {
		return shp_amt;
	}
	public void setShp_amt(String shp_amt) {
		this.shp_amt = shp_amt;
	}
	public String getShp_hmn() {
		return shp_hmn;
	}
	public void setShp_hmn(String shp_hmn) {
		this.shp_hmn = shp_hmn;
	}
	public String getShp_hmt() {
		return shp_hmt;
	}
	public void setShp_hmt(String shp_hmt) {
		this.shp_hmt = shp_hmt;
	}
	public String getShp_apn() {
		return shp_apn;
	}
	public void setShp_apn(String shp_apn) {
		this.shp_apn = shp_apn;
	}
	public String getShp_apt() {
		return shp_apt;
	}
	public void setShp_apt(String shp_apt) {
		this.shp_apt = shp_apt;
	}
	public String getShp_hpn() {
		return shp_hpn;
	}
	public void setShp_hpn(String shp_hpn) {
		this.shp_hpn = shp_hpn;
	}
	public String getShp_hpt() {
		return shp_hpt;
	}
	public void setShp_hpt(String shp_hpt) {
		this.shp_hpt = shp_hpt;
	}
	public String getShp_dme() {
		return shp_dme;
	}
	public void setShp_dme(String shp_dme) {
		this.shp_dme = shp_dme;
	}
	public String getFmd_fmd() {
		return fmd_fmd;
	}
	public void setFmd_fmd(String fmd_fmd) {
		this.fmd_fmd = fmd_fmd;
	}
	public String getFmd_fed() {
		return fmd_fed;
	}
	public void setFmd_fed(String fmd_fed) {
		this.fmd_fed = fmd_fed;
	}
	public String getFmd_dme() {
		return fmd_dme;
	}
	public void setFmd_dme(String fmd_dme) {
		this.fmd_dme = fmd_dme;
	}
	public String getHvt_fmd() {
		return hvt_fmd;
	}
	public void setHvt_fmd(String hvt_fmd) {
		this.hvt_fmd = hvt_fmd;
	}
	public String getHvt_fed() {
		return hvt_fed;
	}
	public void setHvt_fed(String hvt_fed) {
		this.hvt_fed = hvt_fed;
	}
	public String getHvt_etc() {
		return hvt_etc;
	}
	public void setHvt_etc(String hvt_etc) {
		this.hvt_etc = hvt_etc;
	}
	public String getHvt_dme() {
		return hvt_dme;
	}
	public void setHvt_dme(String hvt_dme) {
		this.hvt_dme = hvt_dme;
	}
	public String getHvt_type_a_dme() {
		return hvt_type_a_dme;
	}
	public void setHvt_type_a_dme(String hvt_type_a_dme) {
		this.hvt_type_a_dme = hvt_type_a_dme;
	}
	public String getPbm_wln() {
		return pbm_wln;
	}
	public void setPbm_wln(String pbm_wln) {
		this.pbm_wln = pbm_wln;
	}
	public String getPbm_wln_dme() {
		return pbm_wln_dme;
	}
	public void setPbm_wln_dme(String pbm_wln_dme) {
		this.pbm_wln_dme = pbm_wln_dme;
	}
	public String getPbm_aln() {
		return pbm_aln;
	}
	public void setPbm_aln(String pbm_aln) {
		this.pbm_aln = pbm_aln;
	}
	public String getPbm_aln_dme() {
		return pbm_aln_dme;
	}
	public void setPbm_aln_dme(String pbm_aln_dme) {
		this.pbm_aln_dme = pbm_aln_dme;
	}
	public String getPbm_adn() {
		return pbm_adn;
	}
	public void setPbm_adn(String pbm_adn) {
		this.pbm_adn = pbm_adn;
	}
	public String getPbm_adn_dme() {
		return pbm_adn_dme;
	}
	public void setPbm_adn_dme(String pbm_adn_dme) {
		this.pbm_adn_dme = pbm_adn_dme;
	}
	public String getPbm_scn() {
		return pbm_scn;
	}
	public void setPbm_scn(String pbm_scn) {
		this.pbm_scn = pbm_scn;
	}
	public String getPbm_scn_dme() {
		return pbm_scn_dme;
	}
	public void setPbm_scn_dme(String pbm_scn_dme) {
		this.pbm_scn_dme = pbm_scn_dme;
	}
	public String getPbm_fgn() {
		return pbm_fgn;
	}
	public void setPbm_fgn(String pbm_fgn) {
		this.pbm_fgn = pbm_fgn;
	}
	public String getPbm_fgn_dme() {
		return pbm_fgn_dme;
	}
	public void setPbm_fgn_dme(String pbm_fgn_dme) {
		this.pbm_fgn_dme = pbm_fgn_dme;
	}
	public String getPbm_vhn() {
		return pbm_vhn;
	}
	public void setPbm_vhn(String pbm_vhn) {
		this.pbm_vhn = pbm_vhn;
	}
	public String getPbm_vhn_dme() {
		return pbm_vhn_dme;
	}
	public void setPbm_vhn_dme(String pbm_vhn_dme) {
		this.pbm_vhn_dme = pbm_vhn_dme;
	}
	public String getPbm_etc() {
		return pbm_etc;
	}
	public void setPbm_etc(String pbm_etc) {
		this.pbm_etc = pbm_etc;
	}
	public String getPbm_etc_dme() {
		return pbm_etc_dme;
	}
	public void setPbm_etc_dme(String pbm_etc_dme) {
		this.pbm_etc_dme = pbm_etc_dme;
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
	public float getPbm_total_dme() {
		return pbm_total_dme;
	}
	public void setPbm_total_dme(float pbm_total_dme) {
		this.pbm_total_dme = pbm_total_dme;
	}
	public float getMan_total_dme() {
		return man_total_dme;
	}
	public void setMan_total_dme(float man_total_dme) {
		this.man_total_dme = man_total_dme;
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
	public float getPub_total_dme() {
		return pub_total_dme;
	}
	public void setPub_total_dme(float pub_total_dme) {
		this.pub_total_dme = pub_total_dme;
	}
	
}
