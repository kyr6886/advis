package kr.dis.std.advis.dao;

import java.io.Serializable;

public class TbAccidentSocDto implements Serializable {
	
	private String di_inf_id;
	private int di_ty_id;
	private String di_name;
	private String di_dtl_id;
	private String di_dtl_name;
	private String item_ty;
	private String di_dtl_etc;
	private String di_dtl_clt;
	private String di_inf_id_clt;
	private String di_inf_name;
	private String dis_content;
	private String obz_dt;
	private String occr_st_date;
	private String occr_ed_date;
	private String occr_place;
	private String addr_code;
	private int hm_dmg_killed;
	private int hm_dmg_missing;
	private int hm_dmg_injser;
	private int hm_dmg_injslt;
	private String hm_dmg_etc;
	private String mt_dmg_fac;
	private String mt_dmg_bud;
	private String mt_dmg_anm;
	private String mt_dmg_plt;
	private String mt_dmg_etc;
	private String dmg_total;
	private int dmg_amnt;
	private int dmg_amnt_movasset;
	private int dmg_amnt_rlest;
	private String cause_first;
	private String cause_second;
	private String cause_third;
	private String cause_etc;
	private String action_prob;
	private String action_imprv;
	private String action_exec;
	private String action_content;
	private String instt_super;
	private String instt_resp;
	private String instt_cause_inv;
	private String instt_collect;
	private String rel_legislation;
	private String rel_cause_inv_data;
	private String rel_action_exec;
	private String weather;
	private int temperature;
	private int humidity;
	private String cause_self_first;
	private String cause_self_second;
	private String cause_self_third;
	private String cause_self_etc;
	private double cord_lat;
	private double cord_lon;
	private double cord_ht;
	private String ctg_id;
	
	private String title;
	private String sido;
	private String sigungu;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getDi_inf_id() {
		return di_inf_id;
	}
	public void setDi_inf_id(String di_inf_id) {
		this.di_inf_id = di_inf_id;
	}
	public int getDi_ty_id() {
		return di_ty_id;
	}
	public void setDi_ty_id(int di_ty_id) {
		this.di_ty_id = di_ty_id;
	}
	public String getDi_name() {
		return di_name;
	}
	public void setDi_name(String di_name) {
		this.di_name = di_name;
	}
	public String getDi_dtl_id() {
		return di_dtl_id;
	}
	public void setDi_dtl_id(String di_dtl_id) {
		this.di_dtl_id = di_dtl_id;
	}
	public String getDi_dtl_name() {
		return di_dtl_name;
	}
	public void setDi_dtl_name(String di_dtl_name) {
		this.di_dtl_name = di_dtl_name;
	}
	public String getItem_ty() {
		return item_ty;
	}
	public void setItem_ty(String item_ty) {
		this.item_ty = item_ty;
	}
	public String getDi_dtl_etc() {
		return di_dtl_etc;
	}
	public void setDi_dtl_etc(String di_dtl_etc) {
		this.di_dtl_etc = di_dtl_etc;
	}
	public String getDi_dtl_clt() {
		return di_dtl_clt;
	}
	public void setDi_dtl_clt(String di_dtl_clt) {
		this.di_dtl_clt = di_dtl_clt;
	}
	public String getDi_inf_id_clt() {
		return di_inf_id_clt;
	}
	public void setDi_inf_id_clt(String di_inf_id_clt) {
		this.di_inf_id_clt = di_inf_id_clt;
	}
	public String getDi_inf_name() {
		return di_inf_name;
	}
	public void setDi_inf_name(String di_inf_name) {
		this.di_inf_name = di_inf_name;
	}
	public String getDis_content() {
		return dis_content;
	}
	public void setDis_content(String dis_content) {
		this.dis_content = dis_content;
	}
	public String getObz_dt() {
		return obz_dt;
	}
	public void setObz_dt(String obz_dt) {
		this.obz_dt = obz_dt;
	}
	public String getOccr_st_date() {
		return occr_st_date;
	}
	public void setOccr_st_date(String occr_st_date) {
		this.occr_st_date = occr_st_date;
	}
	public String getOccr_ed_date() {
		return occr_ed_date;
	}
	public void setOccr_ed_date(String occr_ed_date) {
		this.occr_ed_date = occr_ed_date;
	}
	public String getOccr_place() {
		return occr_place;
	}
	public void setOccr_place(String occr_place) {
		this.occr_place = occr_place;
	}
	public String getAddr_code() {
		return addr_code;
	}
	public void setAddr_code(String addr_code) {
		this.addr_code = addr_code;
	}
	public int getHm_dmg_killed() {
		return hm_dmg_killed;
	}
	public void setHm_dmg_killed(int hm_dmg_killed) {
		this.hm_dmg_killed = hm_dmg_killed;
	}
	public int getHm_dmg_missing() {
		return hm_dmg_missing;
	}
	public void setHm_dmg_missing(int hm_dmg_missing) {
		this.hm_dmg_missing = hm_dmg_missing;
	}
	public int getHm_dmg_injser() {
		return hm_dmg_injser;
	}
	public void setHm_dmg_injser(int hm_dmg_injser) {
		this.hm_dmg_injser = hm_dmg_injser;
	}
	public int getHm_dmg_injslt() {
		return hm_dmg_injslt;
	}
	public void setHm_dmg_injslt(int hm_dmg_injslt) {
		this.hm_dmg_injslt = hm_dmg_injslt;
	}
	public String getHm_dmg_etc() {
		return hm_dmg_etc;
	}
	public void setHm_dmg_etc(String hm_dmg_etc) {
		this.hm_dmg_etc = hm_dmg_etc;
	}
	public String getMt_dmg_fac() {
		return mt_dmg_fac;
	}
	public void setMt_dmg_fac(String mt_dmg_fac) {
		this.mt_dmg_fac = mt_dmg_fac;
	}
	public String getMt_dmg_bud() {
		return mt_dmg_bud;
	}
	public void setMt_dmg_bud(String mt_dmg_bud) {
		this.mt_dmg_bud = mt_dmg_bud;
	}
	public String getMt_dmg_anm() {
		return mt_dmg_anm;
	}
	public void setMt_dmg_anm(String mt_dmg_anm) {
		this.mt_dmg_anm = mt_dmg_anm;
	}
	public String getMt_dmg_plt() {
		return mt_dmg_plt;
	}
	public void setMt_dmg_plt(String mt_dmg_plt) {
		this.mt_dmg_plt = mt_dmg_plt;
	}
	public String getMt_dmg_etc() {
		return mt_dmg_etc;
	}
	public void setMt_dmg_etc(String mt_dmg_etc) {
		this.mt_dmg_etc = mt_dmg_etc;
	}
	public String getDmg_total() {
		return dmg_total;
	}
	public void setDmg_total(String dmg_total) {
		this.dmg_total = dmg_total;
	}
	public int getDmg_amnt() {
		return dmg_amnt;
	}
	public void setDmg_amnt(int dmg_amnt) {
		this.dmg_amnt = dmg_amnt;
	}
	public int getDmg_amnt_movasset() {
		return dmg_amnt_movasset;
	}
	public void setDmg_amnt_movasset(int dmg_amnt_movasset) {
		this.dmg_amnt_movasset = dmg_amnt_movasset;
	}
	public int getDmg_amnt_rlest() {
		return dmg_amnt_rlest;
	}
	public void setDmg_amnt_rlest(int dmg_amnt_rlest) {
		this.dmg_amnt_rlest = dmg_amnt_rlest;
	}
	public String getCause_first() {
		return cause_first;
	}
	public void setCause_first(String cause_first) {
		this.cause_first = cause_first;
	}
	public String getCause_second() {
		return cause_second;
	}
	public void setCause_second(String cause_second) {
		this.cause_second = cause_second;
	}
	public String getCause_third() {
		return cause_third;
	}
	public void setCause_third(String cause_third) {
		this.cause_third = cause_third;
	}
	public String getCause_etc() {
		return cause_etc;
	}
	public void setCause_etc(String cause_etc) {
		this.cause_etc = cause_etc;
	}
	public String getAction_prob() {
		return action_prob;
	}
	public void setAction_prob(String action_prob) {
		this.action_prob = action_prob;
	}
	public String getAction_imprv() {
		return action_imprv;
	}
	public void setAction_imprv(String action_imprv) {
		this.action_imprv = action_imprv;
	}
	public String getAction_exec() {
		return action_exec;
	}
	public void setAction_exec(String action_exec) {
		this.action_exec = action_exec;
	}
	public String getAction_content() {
		return action_content;
	}
	public void setAction_content(String action_content) {
		this.action_content = action_content;
	}
	public String getInstt_super() {
		return instt_super;
	}
	public void setInstt_super(String instt_super) {
		this.instt_super = instt_super;
	}
	public String getInstt_resp() {
		return instt_resp;
	}
	public void setInstt_resp(String instt_resp) {
		this.instt_resp = instt_resp;
	}
	public String getInstt_cause_inv() {
		return instt_cause_inv;
	}
	public void setInstt_cause_inv(String instt_cause_inv) {
		this.instt_cause_inv = instt_cause_inv;
	}
	public String getInstt_collect() {
		return instt_collect;
	}
	public void setInstt_collect(String instt_collect) {
		this.instt_collect = instt_collect;
	}
	public String getRel_legislation() {
		return rel_legislation;
	}
	public void setRel_legislation(String rel_legislation) {
		this.rel_legislation = rel_legislation;
	}
	public String getRel_cause_inv_data() {
		return rel_cause_inv_data;
	}
	public void setRel_cause_inv_data(String rel_cause_inv_data) {
		this.rel_cause_inv_data = rel_cause_inv_data;
	}
	public String getRel_action_exec() {
		return rel_action_exec;
	}
	public void setRel_action_exec(String rel_action_exec) {
		this.rel_action_exec = rel_action_exec;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public String getCause_self_first() {
		return cause_self_first;
	}
	public void setCause_self_first(String cause_self_first) {
		this.cause_self_first = cause_self_first;
	}
	public String getCause_self_second() {
		return cause_self_second;
	}
	public void setCause_self_second(String cause_self_second) {
		this.cause_self_second = cause_self_second;
	}
	public String getCause_self_third() {
		return cause_self_third;
	}
	public void setCause_self_third(String cause_self_third) {
		this.cause_self_third = cause_self_third;
	}
	public String getCause_self_etc() {
		return cause_self_etc;
	}
	public void setCause_self_etc(String cause_self_etc) {
		this.cause_self_etc = cause_self_etc;
	}
	public double getCord_lat() {
		return cord_lat;
	}
	public void setCord_lat(double cord_lat) {
		this.cord_lat = cord_lat;
	}
	public double getCord_lon() {
		return cord_lon;
	}
	public void setCord_lon(double cord_lon) {
		this.cord_lon = cord_lon;
	}
	public double getCord_ht() {
		return cord_ht;
	}
	public void setCord_ht(double cord_ht) {
		this.cord_ht = cord_ht;
	}
	public String getCtg_id() {
		return ctg_id;
	}
	public void setCtg_id(String ctg_id) {
		this.ctg_id = ctg_id;
	}
	
	


}
