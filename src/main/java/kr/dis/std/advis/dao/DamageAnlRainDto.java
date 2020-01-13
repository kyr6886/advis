package kr.dis.std.advis.dao;

import com.noaa.base.BaseDTO;

public class DamageAnlRainDto extends BaseDTO {
	
	//tb_ob_asos
	 private int stn_id;
	 private String tm;
	 private int wd_max;
	 private int ws_max;
	 private int ws_max_tm;
	 private int wd_ins;
	 private int ws_ins;
	 private int ws_ins_tm;
	 private int wr_day;
	 private int ta;
	 private int ta_max;
	 private int ta_max_tm;
	 private int ta_min;
	 private int ta_min_tm;
	 private int hm;
	 private int hm_min;
	 private int hm_min_tm;
	 private int pa;
	 private int ps;
	 private int ps_max;
	 private int ps_max_tm;
	 private int ps_min;
	 private int ps_min_tm;
	 private int rn_day;
	 private int rn_d99;
	 private int rn_dur;
	 private int rn_60m_max;
	 private int rn_60m_max_tm;
	 private int rn_10m_max;
	 private int rn_10m_max_tm;
	 private int sd_new;
	 private int sd_new_tm;
	 private int sd_max;
	 private int sd_max_tm;
	 private int ev_s;
	 private int ev_l;
	 private int fg_dur;
	 private int ss_day;
	 private int ss_dur;
	 private int ss_cmb;
	 private int si_day;
	 private int si_60m_max;
	 private int si_60m_max_tm;
	 private int tg_min;
	 private int tg_min_tm;
	 private int ww_01;
	 private int ww_02;
	 private int ww_03;
	 private int ww_04;
	 private int ww_05;
	 private int ww_06;
	 private int ww_07;
	 private int ww_08;
	 private int ww_09;
	 private int ww_10;
	 private int ww_11;
	 private int ww_12;
	 private int ws;
	 private int td;
	 private int pv;
	 private int vs;
	 private int ca_tot;
	 private int ts;
	 private int te_005;
	 private int te_01;
	 private int te_02;
	 private int te_03;
	 private int te_05;
	 private int te_10;
	 private int te_15;
	 private int te_30;
	 private int te_50;
	 private String dt_regt;
	 
	 //tb_ob_st
	 private String obs_id;
	 private String resources_code;
	 private String obs_name;
	 private String position_x;
	 private String position_y;
	 private String above_see_level;
	 private String obs_barometer;
	 private String obs_management;
	 private String obs_thermometer;
	 private String obs_windspeed;
	 private String obs_rainfallmeter;
	 private String start_date;
	 private String delete_date;
	 
	 private String stDate;
	 private String endDate;
	 
	 
	public String getStDate() {
		return stDate;
	}
	public void setStDate(String stDate) {
		this.stDate = stDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getStn_id() {
		return stn_id;
	}
	public void setStn_id(int stn_id) {
		this.stn_id = stn_id;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public int getWd_max() {
		return wd_max;
	}
	public void setWd_max(int wd_max) {
		this.wd_max = wd_max;
	}
	public int getWs_max() {
		return ws_max;
	}
	public void setWs_max(int ws_max) {
		this.ws_max = ws_max;
	}
	public int getWs_max_tm() {
		return ws_max_tm;
	}
	public void setWs_max_tm(int ws_max_tm) {
		this.ws_max_tm = ws_max_tm;
	}
	public int getWd_ins() {
		return wd_ins;
	}
	public void setWd_ins(int wd_ins) {
		this.wd_ins = wd_ins;
	}
	public int getWs_ins() {
		return ws_ins;
	}
	public void setWs_ins(int ws_ins) {
		this.ws_ins = ws_ins;
	}
	public int getWs_ins_tm() {
		return ws_ins_tm;
	}
	public void setWs_ins_tm(int ws_ins_tm) {
		this.ws_ins_tm = ws_ins_tm;
	}
	public int getWr_day() {
		return wr_day;
	}
	public void setWr_day(int wr_day) {
		this.wr_day = wr_day;
	}
	public int getTa() {
		return ta;
	}
	public void setTa(int ta) {
		this.ta = ta;
	}
	public int getTa_max() {
		return ta_max;
	}
	public void setTa_max(int ta_max) {
		this.ta_max = ta_max;
	}
	public int getTa_max_tm() {
		return ta_max_tm;
	}
	public void setTa_max_tm(int ta_max_tm) {
		this.ta_max_tm = ta_max_tm;
	}
	public int getTa_min() {
		return ta_min;
	}
	public void setTa_min(int ta_min) {
		this.ta_min = ta_min;
	}
	public int getTa_min_tm() {
		return ta_min_tm;
	}
	public void setTa_min_tm(int ta_min_tm) {
		this.ta_min_tm = ta_min_tm;
	}
	public int getHm() {
		return hm;
	}
	public void setHm(int hm) {
		this.hm = hm;
	}
	public int getHm_min() {
		return hm_min;
	}
	public void setHm_min(int hm_min) {
		this.hm_min = hm_min;
	}
	public int getHm_min_tm() {
		return hm_min_tm;
	}
	public void setHm_min_tm(int hm_min_tm) {
		this.hm_min_tm = hm_min_tm;
	}
	public int getPa() {
		return pa;
	}
	public void setPa(int pa) {
		this.pa = pa;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public int getPs_max() {
		return ps_max;
	}
	public void setPs_max(int ps_max) {
		this.ps_max = ps_max;
	}
	public int getPs_max_tm() {
		return ps_max_tm;
	}
	public void setPs_max_tm(int ps_max_tm) {
		this.ps_max_tm = ps_max_tm;
	}
	public int getPs_min() {
		return ps_min;
	}
	public void setPs_min(int ps_min) {
		this.ps_min = ps_min;
	}
	public int getPs_min_tm() {
		return ps_min_tm;
	}
	public void setPs_min_tm(int ps_min_tm) {
		this.ps_min_tm = ps_min_tm;
	}
	public int getRn_day() {
		return rn_day;
	}
	public void setRn_day(int rn_day) {
		this.rn_day = rn_day;
	}
	public int getRn_d99() {
		return rn_d99;
	}
	public void setRn_d99(int rn_d99) {
		this.rn_d99 = rn_d99;
	}
	public int getRn_dur() {
		return rn_dur;
	}
	public void setRn_dur(int rn_dur) {
		this.rn_dur = rn_dur;
	}
	public int getRn_60m_max() {
		return rn_60m_max;
	}
	public void setRn_60m_max(int rn_60m_max) {
		this.rn_60m_max = rn_60m_max;
	}
	public int getRn_60m_max_tm() {
		return rn_60m_max_tm;
	}
	public void setRn_60m_max_tm(int rn_60m_max_tm) {
		this.rn_60m_max_tm = rn_60m_max_tm;
	}
	public int getRn_10m_max() {
		return rn_10m_max;
	}
	public void setRn_10m_max(int rn_10m_max) {
		this.rn_10m_max = rn_10m_max;
	}
	public int getRn_10m_max_tm() {
		return rn_10m_max_tm;
	}
	public void setRn_10m_max_tm(int rn_10m_max_tm) {
		this.rn_10m_max_tm = rn_10m_max_tm;
	}
	public int getSd_new() {
		return sd_new;
	}
	public void setSd_new(int sd_new) {
		this.sd_new = sd_new;
	}
	public int getSd_new_tm() {
		return sd_new_tm;
	}
	public void setSd_new_tm(int sd_new_tm) {
		this.sd_new_tm = sd_new_tm;
	}
	public int getSd_max() {
		return sd_max;
	}
	public void setSd_max(int sd_max) {
		this.sd_max = sd_max;
	}
	public int getSd_max_tm() {
		return sd_max_tm;
	}
	public void setSd_max_tm(int sd_max_tm) {
		this.sd_max_tm = sd_max_tm;
	}
	public int getEv_s() {
		return ev_s;
	}
	public void setEv_s(int ev_s) {
		this.ev_s = ev_s;
	}
	public int getEv_l() {
		return ev_l;
	}
	public void setEv_l(int ev_l) {
		this.ev_l = ev_l;
	}
	public int getFg_dur() {
		return fg_dur;
	}
	public void setFg_dur(int fg_dur) {
		this.fg_dur = fg_dur;
	}
	public int getSs_day() {
		return ss_day;
	}
	public void setSs_day(int ss_day) {
		this.ss_day = ss_day;
	}
	public int getSs_dur() {
		return ss_dur;
	}
	public void setSs_dur(int ss_dur) {
		this.ss_dur = ss_dur;
	}
	public int getSs_cmb() {
		return ss_cmb;
	}
	public void setSs_cmb(int ss_cmb) {
		this.ss_cmb = ss_cmb;
	}
	public int getSi_day() {
		return si_day;
	}
	public void setSi_day(int si_day) {
		this.si_day = si_day;
	}
	public int getSi_60m_max() {
		return si_60m_max;
	}
	public void setSi_60m_max(int si_60m_max) {
		this.si_60m_max = si_60m_max;
	}
	public int getSi_60m_max_tm() {
		return si_60m_max_tm;
	}
	public void setSi_60m_max_tm(int si_60m_max_tm) {
		this.si_60m_max_tm = si_60m_max_tm;
	}
	public int getTg_min() {
		return tg_min;
	}
	public void setTg_min(int tg_min) {
		this.tg_min = tg_min;
	}
	public int getTg_min_tm() {
		return tg_min_tm;
	}
	public void setTg_min_tm(int tg_min_tm) {
		this.tg_min_tm = tg_min_tm;
	}
	public int getWw_01() {
		return ww_01;
	}
	public void setWw_01(int ww_01) {
		this.ww_01 = ww_01;
	}
	public int getWw_02() {
		return ww_02;
	}
	public void setWw_02(int ww_02) {
		this.ww_02 = ww_02;
	}
	public int getWw_03() {
		return ww_03;
	}
	public void setWw_03(int ww_03) {
		this.ww_03 = ww_03;
	}
	public int getWw_04() {
		return ww_04;
	}
	public void setWw_04(int ww_04) {
		this.ww_04 = ww_04;
	}
	public int getWw_05() {
		return ww_05;
	}
	public void setWw_05(int ww_05) {
		this.ww_05 = ww_05;
	}
	public int getWw_06() {
		return ww_06;
	}
	public void setWw_06(int ww_06) {
		this.ww_06 = ww_06;
	}
	public int getWw_07() {
		return ww_07;
	}
	public void setWw_07(int ww_07) {
		this.ww_07 = ww_07;
	}
	public int getWw_08() {
		return ww_08;
	}
	public void setWw_08(int ww_08) {
		this.ww_08 = ww_08;
	}
	public int getWw_09() {
		return ww_09;
	}
	public void setWw_09(int ww_09) {
		this.ww_09 = ww_09;
	}
	public int getWw_10() {
		return ww_10;
	}
	public void setWw_10(int ww_10) {
		this.ww_10 = ww_10;
	}
	public int getWw_11() {
		return ww_11;
	}
	public void setWw_11(int ww_11) {
		this.ww_11 = ww_11;
	}
	public int getWw_12() {
		return ww_12;
	}
	public void setWw_12(int ww_12) {
		this.ww_12 = ww_12;
	}
	public int getWs() {
		return ws;
	}
	public void setWs(int ws) {
		this.ws = ws;
	}
	public int getTd() {
		return td;
	}
	public void setTd(int td) {
		this.td = td;
	}
	public int getPv() {
		return pv;
	}
	public void setPv(int pv) {
		this.pv = pv;
	}
	public int getVs() {
		return vs;
	}
	public void setVs(int vs) {
		this.vs = vs;
	}
	public int getCa_tot() {
		return ca_tot;
	}
	public void setCa_tot(int ca_tot) {
		this.ca_tot = ca_tot;
	}
	public int getTs() {
		return ts;
	}
	public void setTs(int ts) {
		this.ts = ts;
	}
	public int getTe_005() {
		return te_005;
	}
	public void setTe_005(int te_005) {
		this.te_005 = te_005;
	}
	public int getTe_01() {
		return te_01;
	}
	public void setTe_01(int te_01) {
		this.te_01 = te_01;
	}
	public int getTe_02() {
		return te_02;
	}
	public void setTe_02(int te_02) {
		this.te_02 = te_02;
	}
	public int getTe_03() {
		return te_03;
	}
	public void setTe_03(int te_03) {
		this.te_03 = te_03;
	}
	public int getTe_05() {
		return te_05;
	}
	public void setTe_05(int te_05) {
		this.te_05 = te_05;
	}
	public int getTe_10() {
		return te_10;
	}
	public void setTe_10(int te_10) {
		this.te_10 = te_10;
	}
	public int getTe_15() {
		return te_15;
	}
	public void setTe_15(int te_15) {
		this.te_15 = te_15;
	}
	public int getTe_30() {
		return te_30;
	}
	public void setTe_30(int te_30) {
		this.te_30 = te_30;
	}
	public int getTe_50() {
		return te_50;
	}
	public void setTe_50(int te_50) {
		this.te_50 = te_50;
	}
	public String getDt_regt() {
		return dt_regt;
	}
	public void setDt_regt(String dt_regt) {
		this.dt_regt = dt_regt;
	}
	public String getObs_id() {
		return obs_id;
	}
	public void setObs_id(String obs_id) {
		this.obs_id = obs_id;
	}
	public String getResources_code() {
		return resources_code;
	}
	public void setResources_code(String resources_code) {
		this.resources_code = resources_code;
	}
	public String getObs_name() {
		return obs_name;
	}
	public void setObs_name(String obs_name) {
		this.obs_name = obs_name;
	}
	public String getPosition_x() {
		return position_x;
	}
	public void setPosition_x(String position_x) {
		this.position_x = position_x;
	}
	public String getPosition_y() {
		return position_y;
	}
	public void setPosition_y(String position_y) {
		this.position_y = position_y;
	}
	public String getAbove_see_level() {
		return above_see_level;
	}
	public void setAbove_see_level(String above_see_level) {
		this.above_see_level = above_see_level;
	}
	public String getObs_barometer() {
		return obs_barometer;
	}
	public void setObs_barometer(String obs_barometer) {
		this.obs_barometer = obs_barometer;
	}
	public String getObs_management() {
		return obs_management;
	}
	public void setObs_management(String obs_management) {
		this.obs_management = obs_management;
	}
	public String getObs_thermometer() {
		return obs_thermometer;
	}
	public void setObs_thermometer(String obs_thermometer) {
		this.obs_thermometer = obs_thermometer;
	}
	public String getObs_windspeed() {
		return obs_windspeed;
	}
	public void setObs_windspeed(String obs_windspeed) {
		this.obs_windspeed = obs_windspeed;
	}
	public String getObs_rainfallmeter() {
		return obs_rainfallmeter;
	}
	public void setObs_rainfallmeter(String obs_rainfallmeter) {
		this.obs_rainfallmeter = obs_rainfallmeter;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getDelete_date() {
		return delete_date;
	}
	public void setDelete_date(String delete_date) {
		this.delete_date = delete_date;
	}
	 
	 
}
