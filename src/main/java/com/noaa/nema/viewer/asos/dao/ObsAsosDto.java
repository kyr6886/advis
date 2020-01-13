package com.noaa.nema.viewer.asos.dao;

public class ObsAsosDto {
	private String obs_name;
	private String stn_id		;
	private String tm           ;
	private double wd_max       ;
	private double ws_max       ;
	private double ws_max_tm    ;
	private double wd_ins       ;
	private double ws_ins       ;
	private double ws_ins_tm    ;
	private double wr_day       ;
	private double ta           ;
	private double ta_max       ;
	private double ta_max_tm    ;
	private double ta_min       ;
	private double ta_min_tm    ;
	private double hm           ;
	private double hm_min       ;
	private double hm_min_tm    ;
	private double pa           ;
	private double ps           ;
	private double ps_max       ;
	private double ps_max_tm    ;
	private double ps_min       ;
	private double ps_min_tm    ;
	private double rn_day       ;
	private double rn_d99       ;
	public String getObs_name() {
		return obs_name;
	}
	public void setObs_name(String obs_name) {
		this.obs_name = obs_name;
	}
	private double rn_dur       ;
	private double rn_60m_max   ;
	private double rn_60m_max_tm;
	private double rn_10m_max   ;
	private double rn_10m_max_tm;
	private double sd_new       ;
	private double sd_new_tm    ;
	private double sd_max       ;
	private double sd_max_tm    ;
	private double ev_s         ;
	private double ev_l         ;
	private double fg_dur       ;
	private double ss_day       ;
	private double ss_dur       ;
	private double ss_cmb       ;
	private double si_day       ;
	private double si_60m_max   ;
	private double si_60m_max_tm;
	private double tg_min       ;
	private double tg_min_tm    ;
	private double ww_01        ;
	private double ww_02        ;
	private double ww_03        ;
	private double ww_04        ;
	private double ww_05        ;
	private double ww_06        ;
	private double ww_07        ;
	private double ww_08        ;
	private double ww_09        ;
	private double ww_10        ;
	private double ww_11        ;
	private double ww_12        ;
	private double ws           ;
	private double td           ;
	private double pv           ;
	private double vs           ;
	private double ca_tot       ;
	private double ts           ;
	private double te_005       ;
	private double te_01        ;
	private double te_02        ;
	private double te_03        ;
	private double te_05        ;
	private double te_10        ;
	private double te_15        ;
	private double te_30        ;
	private double te_50        ;
	private String dt_regt      ;
	
	private String law_code;
	private int sido_count;
	private int gungu_count;
	private double rn_day_max;

	private String man_count;
	private String pbm_count;
	private String pub_count;

	private int man_total_cnt;
	private int pbm_total_cnt;
	private int pub_total_cnt;
	

	private double rn_day_plus;
	private double rn_10m_max_plus;
	private double rn_60m_max_plus;

	private String damage_data_code;
	
	
	public String getStn_id() {
		return stn_id;
	}
	public void setStn_id(String stn_id) {
		this.stn_id = stn_id;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public double getWd_max() {
		return wd_max;
	}
	public void setWd_max(double wd_max) {
		this.wd_max = wd_max;
	}
	public double getWs_max() {
		return ws_max;
	}
	public void setWs_max(double ws_max) {
		this.ws_max = ws_max;
	}
	public double getWs_max_tm() {
		return ws_max_tm;
	}
	public void setWs_max_tm(double ws_max_tm) {
		this.ws_max_tm = ws_max_tm;
	}
	public double getWd_ins() {
		return wd_ins;
	}
	public void setWd_ins(double wd_ins) {
		this.wd_ins = wd_ins;
	}
	public double getWs_ins() {
		return ws_ins;
	}
	public void setWs_ins(double ws_ins) {
		this.ws_ins = ws_ins;
	}
	public double getWs_ins_tm() {
		return ws_ins_tm;
	}
	public void setWs_ins_tm(double ws_ins_tm) {
		this.ws_ins_tm = ws_ins_tm;
	}
	public double getWr_day() {
		return wr_day;
	}
	public void setWr_day(double wr_day) {
		this.wr_day = wr_day;
	}
	public double getTa() {
		return ta;
	}
	public void setTa(double ta) {
		this.ta = ta;
	}
	public double getTa_max() {
		return ta_max;
	}
	public void setTa_max(double ta_max) {
		this.ta_max = ta_max;
	}
	public double getTa_max_tm() {
		return ta_max_tm;
	}
	public void setTa_max_tm(double ta_max_tm) {
		this.ta_max_tm = ta_max_tm;
	}
	public double getTa_min() {
		return ta_min;
	}
	public void setTa_min(double ta_min) {
		this.ta_min = ta_min;
	}
	public double getTa_min_tm() {
		return ta_min_tm;
	}
	public void setTa_min_tm(double ta_min_tm) {
		this.ta_min_tm = ta_min_tm;
	}
	public double getHm() {
		return hm;
	}
	public void setHm(double hm) {
		this.hm = hm;
	}
	public double getHm_min() {
		return hm_min;
	}
	public void setHm_min(double hm_min) {
		this.hm_min = hm_min;
	}
	public double getHm_min_tm() {
		return hm_min_tm;
	}
	public void setHm_min_tm(double hm_min_tm) {
		this.hm_min_tm = hm_min_tm;
	}
	public double getPa() {
		return pa;
	}
	public void setPa(double pa) {
		this.pa = pa;
	}
	public double getPs() {
		return ps;
	}
	public void setPs(double ps) {
		this.ps = ps;
	}
	public double getPs_max() {
		return ps_max;
	}
	public void setPs_max(double ps_max) {
		this.ps_max = ps_max;
	}
	public double getPs_max_tm() {
		return ps_max_tm;
	}
	public void setPs_max_tm(double ps_max_tm) {
		this.ps_max_tm = ps_max_tm;
	}
	public double getPs_min() {
		return ps_min;
	}
	public void setPs_min(double ps_min) {
		this.ps_min = ps_min;
	}
	public double getPs_min_tm() {
		return ps_min_tm;
	}
	public void setPs_min_tm(double ps_min_tm) {
		this.ps_min_tm = ps_min_tm;
	}
	public double getRn_day() {
		return rn_day;
	}
	public void setRn_day(double rn_day) {
		this.rn_day = rn_day;
	}
	public double getRn_d99() {
		return rn_d99;
	}
	public void setRn_d99(double rn_d99) {
		this.rn_d99 = rn_d99;
	}
	public double getRn_dur() {
		return rn_dur;
	}
	public void setRn_dur(double rn_dur) {
		this.rn_dur = rn_dur;
	}
	public double getRn_60m_max() {
		return rn_60m_max;
	}
	public void setRn_60m_max(double rn_60m_max) {
		this.rn_60m_max = rn_60m_max;
	}
	public double getRn_60m_max_tm() {
		return rn_60m_max_tm;
	}
	public void setRn_60m_max_tm(double rn_60m_max_tm) {
		this.rn_60m_max_tm = rn_60m_max_tm;
	}
	public double getRn_10m_max() {
		return rn_10m_max;
	}
	public void setRn_10m_max(double rn_10m_max) {
		this.rn_10m_max = rn_10m_max;
	}
	public double getRn_10m_max_tm() {
		return rn_10m_max_tm;
	}
	public void setRn_10m_max_tm(double rn_10m_max_tm) {
		this.rn_10m_max_tm = rn_10m_max_tm;
	}
	public double getSd_new() {
		return sd_new;
	}
	public void setSd_new(double sd_new) {
		this.sd_new = sd_new;
	}
	public double getSd_new_tm() {
		return sd_new_tm;
	}
	public void setSd_new_tm(double sd_new_tm) {
		this.sd_new_tm = sd_new_tm;
	}
	public double getSd_max() {
		return sd_max;
	}
	public void setSd_max(double sd_max) {
		this.sd_max = sd_max;
	}
	public double getSd_max_tm() {
		return sd_max_tm;
	}
	public void setSd_max_tm(double sd_max_tm) {
		this.sd_max_tm = sd_max_tm;
	}
	public double getEv_s() {
		return ev_s;
	}
	public void setEv_s(double ev_s) {
		this.ev_s = ev_s;
	}
	public double getEv_l() {
		return ev_l;
	}
	public void setEv_l(double ev_l) {
		this.ev_l = ev_l;
	}
	public double getFg_dur() {
		return fg_dur;
	}
	public void setFg_dur(double fg_dur) {
		this.fg_dur = fg_dur;
	}
	public double getSs_day() {
		return ss_day;
	}
	public void setSs_day(double ss_day) {
		this.ss_day = ss_day;
	}
	public double getSs_dur() {
		return ss_dur;
	}
	public void setSs_dur(double ss_dur) {
		this.ss_dur = ss_dur;
	}
	public double getSs_cmb() {
		return ss_cmb;
	}
	public void setSs_cmb(double ss_cmb) {
		this.ss_cmb = ss_cmb;
	}
	public double getSi_day() {
		return si_day;
	}
	public void setSi_day(double si_day) {
		this.si_day = si_day;
	}
	public double getSi_60m_max() {
		return si_60m_max;
	}
	public void setSi_60m_max(double si_60m_max) {
		this.si_60m_max = si_60m_max;
	}
	public double getSi_60m_max_tm() {
		return si_60m_max_tm;
	}
	public void setSi_60m_max_tm(double si_60m_max_tm) {
		this.si_60m_max_tm = si_60m_max_tm;
	}
	public double getTg_min() {
		return tg_min;
	}
	public void setTg_min(double tg_min) {
		this.tg_min = tg_min;
	}
	public double getTg_min_tm() {
		return tg_min_tm;
	}
	public void setTg_min_tm(double tg_min_tm) {
		this.tg_min_tm = tg_min_tm;
	}
	public double getWw_01() {
		return ww_01;
	}
	public void setWw_01(double ww_01) {
		this.ww_01 = ww_01;
	}
	public double getWw_02() {
		return ww_02;
	}
	public void setWw_02(double ww_02) {
		this.ww_02 = ww_02;
	}
	public double getWw_03() {
		return ww_03;
	}
	public void setWw_03(double ww_03) {
		this.ww_03 = ww_03;
	}
	public double getWw_04() {
		return ww_04;
	}
	public void setWw_04(double ww_04) {
		this.ww_04 = ww_04;
	}
	public double getWw_05() {
		return ww_05;
	}
	public void setWw_05(double ww_05) {
		this.ww_05 = ww_05;
	}
	public double getWw_06() {
		return ww_06;
	}
	public void setWw_06(double ww_06) {
		this.ww_06 = ww_06;
	}
	public double getWw_07() {
		return ww_07;
	}
	public void setWw_07(double ww_07) {
		this.ww_07 = ww_07;
	}
	public double getWw_08() {
		return ww_08;
	}
	public void setWw_08(double ww_08) {
		this.ww_08 = ww_08;
	}
	public double getWw_09() {
		return ww_09;
	}
	public void setWw_09(double ww_09) {
		this.ww_09 = ww_09;
	}
	public double getWw_10() {
		return ww_10;
	}
	public void setWw_10(double ww_10) {
		this.ww_10 = ww_10;
	}
	public double getWw_11() {
		return ww_11;
	}
	public void setWw_11(double ww_11) {
		this.ww_11 = ww_11;
	}
	public double getWw_12() {
		return ww_12;
	}
	public void setWw_12(double ww_12) {
		this.ww_12 = ww_12;
	}
	public double getWs() {
		return ws;
	}
	public void setWs(double ws) {
		this.ws = ws;
	}
	public double getTd() {
		return td;
	}
	public void setTd(double td) {
		this.td = td;
	}
	public double getPv() {
		return pv;
	}
	public void setPv(double pv) {
		this.pv = pv;
	}
	public double getVs() {
		return vs;
	}
	public void setVs(double vs) {
		this.vs = vs;
	}
	public double getCa_tot() {
		return ca_tot;
	}
	public void setCa_tot(double ca_tot) {
		this.ca_tot = ca_tot;
	}
	public double getTs() {
		return ts;
	}
	public void setTs(double ts) {
		this.ts = ts;
	}
	public double getTe_005() {
		return te_005;
	}
	public void setTe_005(double te_005) {
		this.te_005 = te_005;
	}
	public double getTe_01() {
		return te_01;
	}
	public void setTe_01(double te_01) {
		this.te_01 = te_01;
	}
	public double getTe_02() {
		return te_02;
	}
	public void setTe_02(double te_02) {
		this.te_02 = te_02;
	}
	public double getTe_03() {
		return te_03;
	}
	public void setTe_03(double te_03) {
		this.te_03 = te_03;
	}
	public double getTe_05() {
		return te_05;
	}
	public void setTe_05(double te_05) {
		this.te_05 = te_05;
	}
	public double getTe_10() {
		return te_10;
	}
	public void setTe_10(double te_10) {
		this.te_10 = te_10;
	}
	public double getTe_15() {
		return te_15;
	}
	public void setTe_15(double te_15) {
		this.te_15 = te_15;
	}
	public double getTe_30() {
		return te_30;
	}
	public void setTe_30(double te_30) {
		this.te_30 = te_30;
	}
	public double getTe_50() {
		return te_50;
	}
	public void setTe_50(double te_50) {
		this.te_50 = te_50;
	}
	public String getDt_regt() {
		return dt_regt;
	}
	public void setDt_regt(String dt_regt) {
		this.dt_regt = dt_regt;
	}

	public String getLaw_code() {
		return law_code;
	}
	public void setLaw_code(String law_code) {
		this.law_code = law_code;
	}
	public int getSido_count() {
		return sido_count;
	}
	public void setSido_count(int sido_count) {
		this.sido_count = sido_count;
	}
	public int getGungu_count() {
		return gungu_count;
	}
	public void setGungu_count(int gungu_count) {
		this.gungu_count = gungu_count;
	}
	public double getRn_day_max() {
		return rn_day_max;
	}
	public void setRn_day_max(double rn_day_max) {
		this.rn_day_max = rn_day_max;
	}
	
	
	
	public String getMan_count() {
		return man_count;
	}
	public void setMan_count(String man_count) {
		this.man_count = man_count;
	}
	public String getPbm_count() {
		return pbm_count;
	}
	public void setPbm_count(String pbm_count) {
		this.pbm_count = pbm_count;
	}
	public String getPub_count() {
		return pub_count;
	}
	public void setPub_count(String pub_count) {
		this.pub_count = pub_count;
	}
	public int getMan_total_cnt() {
		return man_total_cnt;
	}
	public void setMan_total_cnt(int man_total_cnt) {
		this.man_total_cnt = man_total_cnt;
	}
	public int getPbm_total_cnt() {
		return pbm_total_cnt;
	}
	public void setPbm_total_cnt(int pbm_total_cnt) {
		this.pbm_total_cnt = pbm_total_cnt;
	}
	public int getPub_total_cnt() {
		return pub_total_cnt;
	}
	public void setPub_total_cnt(int pub_total_cnt) {
		this.pub_total_cnt = pub_total_cnt;
	}
	
	public double getRn_day_plus() {
		return rn_day_plus;
	}
	public void setRn_day_plus(double rn_day_plus) {
		this.rn_day_plus = rn_day_plus;
	}
	public double getRn_10m_max_plus() {
		return rn_10m_max_plus;
	}
	public void setRn_10m_max_plus(double rn_10m_max_plus) {
		this.rn_10m_max_plus = rn_10m_max_plus;
	}
	public double getRn_60m_max_plus() {
		return rn_60m_max_plus;
	}
	public void setRn_60m_max_plus(double rn_60m_max_plus) {
		this.rn_60m_max_plus = rn_60m_max_plus;
	}

	public String getDamage_data_code() {
		return damage_data_code;
	}
	public void setDamage_data_code(String damage_data_code) {
		this.damage_data_code = damage_data_code;
	}
}
