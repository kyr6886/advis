package com.noaa.nema.viewer.year.dme.dao;

import com.noaa.nema.viewer.asos.dao.ObsAsosDto;

public class YearDmeDto {
	private String damage_sido;
	private String damage_info;
	private String damage_code;
	private String sido_code;
	private String sigungu_code;
	private String sido;
	private String sigungu;
	private String beg_year;
	private String beg_month;
	private String beg_day;
	private String end_year;
	private String end_month;
	private String end_day;
	private int cov_1;
	private int cov_2;
	private int cov_3;
	private int cov_4;
	private int cov_5;
	private int cov_dme_1;
	private int cov_dme_2;
	private int com_1;
	private int com_2;
	private int com_3;
	private int com_4;
	private int com_dme;
	private int coc_1;
	private int coc_2;
	private int coc_3;
	private int coc_4;
	private int coc_5;
	private int coc_dme;
	private int cob_1;
	private int cob_2;
	private int cob_3;
	private int cob_4;
	private int cob_5;
	private int cob_6;
	private int cob_7;
	private int cob_dme;
	private double cob_tot;
	private int cos_1;
	private double cos_2;
	private int cos_3;
	private double cos_4;
	private int cos_5;
	private double cos_6;
	private int cos_7;
	private double cos_8;
	private int cos_dme_1;
	private double cos_dme_2;
	private double cos_tot;
	private double cof_1;
	private double cof_2;
	private double cof_dme;
	private double cof_tot;
	private double cow_1;
	private double cow_2;
	private double cow_3;
	private double cow_dme;
	private double cow_tot;
	private int pba_1;
	private int pba_2;
	private int pba_3;
	private int pba_4;
	private double pba_tot;
	private int pbb_1;
	private double pbb_2;
	private double pbb_tot;
	private int pbr_1;
	private double pbr_2;
	private double pbr_tot;
	private int pbc_1;
	private double pbc_2;
	private double pbc_tot;
	private int pbd_1;
	private double pbd_tot;
	private int pbf_1;
	private double pbf_tot;
	private int pbg_1;
	private double pbg_tot;
	private int pbh_1;
	private int pbh_2;
	private double pbh_tot;
	private int pbi_1;
	private double pbi_2;
	private double pbi_tot;
	private int pbj_1;
	private int pbj_2;
	private double pbj_3;
	private double pbj_tot;
	private int pbk_1;
	private double pbk_2;
	private double pbk_3;
	private int pbk_4;
	private double pbk_5;
	private double pbk_6;
	private int pbk_7;
	private double pbk_8;
	private double pbk_9;
	private double pbk_tot;
	private int pbl_1;
	private double pbl_tot;
	private int pbm_1;
	private double pbm_tot;
	private int pbn_1;
	private double pbn_tot;
	private int pbo_1;
	private double pbo_tot;
	private int pra_1;
	private double pra_tot;
	private int prb_1;
	private double prb_tot;
	private int prc_1;
	private double prc_tot;
	private int prd_1;
	private double prd_tot;
	private int pre_1;
	private double pre_tot;
	private int prf_1;
	private double prf_tot;
	private String prg_1;
	private int prg_tot;
	private int etc_1;
	private int etc_2;
	private int etc_3;
	private double etc_tot;
	private double com_dem;
	private double com_total;
	private double rn_day;
	private double rn_10m_max;
	private double rn_60m_max;
	private String damage_name;
	private double pub_total;
	private double pri_total;
	private double total_damage;
	private String beg_date;
	private String end_date;
	private int count;
	private double wd_ins;
	private String inform_nm;
	private String damage_cause;
	private String damage_result;
	private String age_sperate;
	private String gender;
	private double total_com_dme;
	private double all_total_damage;
	private String dme_code;
	private YearDmeConditionDto dmeCondition;

	private double com_dme_max;
	private double total_damage_max;
	private ObsAsosDto asosInfo;
	private String dis_category;
	
	
	public String getDamage_info() {
		return damage_info;
	}
	public void setDamage_info(String damage_info) {
		this.damage_info = damage_info;
	}
	public String getDamage_sido() {
		return damage_sido;
	}
	public void setDamage_sido(String damage_sido) {
		this.damage_sido = damage_sido;
	}
	public String getDamage_code() {
		return damage_code;
	}
	public void setDamage_code(String damage_code) {
		this.damage_code = damage_code;
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
	public int getCov_1() {
		return cov_1;
	}
	public void setCov_1(int cov_1) {
		this.cov_1 = cov_1;
	}
	public int getCov_2() {
		return cov_2;
	}
	public void setCov_2(int cov_2) {
		this.cov_2 = cov_2;
	}
	public int getCov_3() {
		return cov_3;
	}
	public void setCov_3(int cov_3) {
		this.cov_3 = cov_3;
	}
	public int getCov_4() {
		return cov_4;
	}
	public void setCov_4(int cov_4) {
		this.cov_4 = cov_4;
	}
	public int getCov_5() {
		return cov_5;
	}
	public void setCov_5(int cov_5) {
		this.cov_5 = cov_5;
	}
	public int getCov_dme_1() {
		return cov_dme_1;
	}
	public void setCov_dme_1(int cov_dme_1) {
		this.cov_dme_1 = cov_dme_1;
	}
	public int getCov_dme_2() {
		return cov_dme_2;
	}
	public void setCov_dme_2(int cov_dme_2) {
		this.cov_dme_2 = cov_dme_2;
	}
	public int getCom_1() {
		return com_1;
	}
	public void setCom_1(int com_1) {
		this.com_1 = com_1;
	}
	public int getCom_2() {
		return com_2;
	}
	public void setCom_2(int com_2) {
		this.com_2 = com_2;
	}
	public int getCom_3() {
		return com_3;
	}
	public void setCom_3(int com_3) {
		this.com_3 = com_3;
	}
	public int getCom_4() {
		return com_4;
	}
	public void setCom_4(int com_4) {
		this.com_4 = com_4;
	}
	public int getCom_dme() {
		return com_dme;
	}
	public void setCom_dme(int com_dme) {
		this.com_dme = com_dme;
	}
	public int getCoc_1() {
		return coc_1;
	}
	public void setCoc_1(int coc_1) {
		this.coc_1 = coc_1;
	}
	public int getCoc_2() {
		return coc_2;
	}
	public void setCoc_2(int coc_2) {
		this.coc_2 = coc_2;
	}
	public int getCoc_3() {
		return coc_3;
	}
	public void setCoc_3(int coc_3) {
		this.coc_3 = coc_3;
	}
	public int getCoc_4() {
		return coc_4;
	}
	public void setCoc_4(int coc_4) {
		this.coc_4 = coc_4;
	}
	public int getCoc_5() {
		return coc_5;
	}
	public void setCoc_5(int coc_5) {
		this.coc_5 = coc_5;
	}
	public int getCoc_dme() {
		return coc_dme;
	}
	public void setCoc_dme(int coc_dme) {
		this.coc_dme = coc_dme;
	}
	public int getCob_1() {
		return cob_1;
	}
	public void setCob_1(int cob_1) {
		this.cob_1 = cob_1;
	}
	public int getCob_2() {
		return cob_2;
	}
	public void setCob_2(int cob_2) {
		this.cob_2 = cob_2;
	}
	public int getCob_3() {
		return cob_3;
	}
	public void setCob_3(int cob_3) {
		this.cob_3 = cob_3;
	}
	public int getCob_4() {
		return cob_4;
	}
	public void setCob_4(int cob_4) {
		this.cob_4 = cob_4;
	}
	public int getCob_5() {
		return cob_5;
	}
	public void setCob_5(int cob_5) {
		this.cob_5 = cob_5;
	}
	public int getCob_6() {
		return cob_6;
	}
	public void setCob_6(int cob_6) {
		this.cob_6 = cob_6;
	}
	public int getCob_7() {
		return cob_7;
	}
	public void setCob_7(int cob_7) {
		this.cob_7 = cob_7;
	}
	public int getCob_dme() {
		return cob_dme;
	}
	public void setCob_dme(int cob_dme) {
		this.cob_dme = cob_dme;
	}
	public double getCob_tot() {
		return cob_tot;
	}
	public void setCob_tot(double cob_tot) {
		this.cob_tot = cob_tot;
	}
	public int getCos_1() {
		return cos_1;
	}
	public void setCos_1(int cos_1) {
		this.cos_1 = cos_1;
	}
	public double getCos_2() {
		return cos_2;
	}
	public void setCos_2(double cos_2) {
		this.cos_2 = cos_2;
	}
	public int getCos_3() {
		return cos_3;
	}
	public void setCos_3(int cos_3) {
		this.cos_3 = cos_3;
	}
	public double getCos_4() {
		return cos_4;
	}
	public void setCos_4(double cos_4) {
		this.cos_4 = cos_4;
	}
	public int getCos_5() {
		return cos_5;
	}
	public void setCos_5(int cos_5) {
		this.cos_5 = cos_5;
	}
	public double getCos_6() {
		return cos_6;
	}
	public void setCos_6(double cos_6) {
		this.cos_6 = cos_6;
	}
	public int getCos_7() {
		return cos_7;
	}
	public void setCos_7(int cos_7) {
		this.cos_7 = cos_7;
	}
	public double getCos_8() {
		return cos_8;
	}
	public void setCos_8(double cos_8) {
		this.cos_8 = cos_8;
	}
	public int getCos_dme_1() {
		return cos_dme_1;
	}
	public void setCos_dme_1(int cos_dme_1) {
		this.cos_dme_1 = cos_dme_1;
	}
	public double getCos_dme_2() {
		return cos_dme_2;
	}
	public void setCos_dme_2(double cos_dme_2) {
		this.cos_dme_2 = cos_dme_2;
	}
	public double getCos_tot() {
		return cos_tot;
	}
	public void setCos_tot(double cos_tot) {
		this.cos_tot = cos_tot;
	}
	public double getCof_1() {
		return cof_1;
	}
	public void setCof_1(double cof_1) {
		this.cof_1 = cof_1;
	}
	public double getCof_2() {
		return cof_2;
	}
	public void setCof_2(double cof_2) {
		this.cof_2 = cof_2;
	}
	public double getCof_dme() {
		return cof_dme;
	}
	public void setCof_dme(double cof_dme) {
		this.cof_dme = cof_dme;
	}
	public double getCof_tot() {
		return cof_tot;
	}
	public void setCof_tot(double cof_tot) {
		this.cof_tot = cof_tot;
	}
	public double getCow_1() {
		return cow_1;
	}
	public void setCow_1(double cow_1) {
		this.cow_1 = cow_1;
	}
	public double getCow_2() {
		return cow_2;
	}
	public void setCow_2(double cow_2) {
		this.cow_2 = cow_2;
	}
	public double getCow_3() {
		return cow_3;
	}
	public void setCow_3(double cow_3) {
		this.cow_3 = cow_3;
	}
	public double getCow_dme() {
		return cow_dme;
	}
	public void setCow_dme(double cow_dme) {
		this.cow_dme = cow_dme;
	}
	public double getCow_tot() {
		return cow_tot;
	}
	public void setCow_tot(double cow_tot) {
		this.cow_tot = cow_tot;
	}
	public int getPba_1() {
		return pba_1;
	}
	public void setPba_1(int pba_1) {
		this.pba_1 = pba_1;
	}
	public int getPba_2() {
		return pba_2;
	}
	public void setPba_2(int pba_2) {
		this.pba_2 = pba_2;
	}
	public int getPba_3() {
		return pba_3;
	}
	public void setPba_3(int pba_3) {
		this.pba_3 = pba_3;
	}
	public int getPba_4() {
		return pba_4;
	}
	public void setPba_4(int pba_4) {
		this.pba_4 = pba_4;
	}
	public double getPba_tot() {
		return pba_tot;
	}
	public void setPba_tot(double pba_tot) {
		this.pba_tot = pba_tot;
	}
	public int getPbb_1() {
		return pbb_1;
	}
	public void setPbb_1(int pbb_1) {
		this.pbb_1 = pbb_1;
	}
	public double getPbb_2() {
		return pbb_2;
	}
	public void setPbb_2(double pbb_2) {
		this.pbb_2 = pbb_2;
	}
	public double getPbb_tot() {
		return pbb_tot;
	}
	public void setPbb_tot(double pbb_tot) {
		this.pbb_tot = pbb_tot;
	}
	public int getPbr_1() {
		return pbr_1;
	}
	public void setPbr_1(int pbr_1) {
		this.pbr_1 = pbr_1;
	}
	public double getPbr_2() {
		return pbr_2;
	}
	public void setPbr_2(double pbr_2) {
		this.pbr_2 = pbr_2;
	}
	public double getPbr_tot() {
		return pbr_tot;
	}
	public void setPbr_tot(double pbr_tot) {
		this.pbr_tot = pbr_tot;
	}
	public int getPbc_1() {
		return pbc_1;
	}
	public void setPbc_1(int pbc_1) {
		this.pbc_1 = pbc_1;
	}
	public double getPbc_2() {
		return pbc_2;
	}
	public void setPbc_2(double pbc_2) {
		this.pbc_2 = pbc_2;
	}
	public double getPbc_tot() {
		return pbc_tot;
	}
	public void setPbc_tot(double pbc_tot) {
		this.pbc_tot = pbc_tot;
	}
	public int getPbd_1() {
		return pbd_1;
	}
	public void setPbd_1(int pbd_1) {
		this.pbd_1 = pbd_1;
	}
	public double getPbd_tot() {
		return pbd_tot;
	}
	public void setPbd_tot(double pbd_tot) {
		this.pbd_tot = pbd_tot;
	}
	public int getPbf_1() {
		return pbf_1;
	}
	public void setPbf_1(int pbf_1) {
		this.pbf_1 = pbf_1;
	}
	public double getPbf_tot() {
		return pbf_tot;
	}
	public void setPbf_tot(double pbf_tot) {
		this.pbf_tot = pbf_tot;
	}
	public int getPbg_1() {
		return pbg_1;
	}
	public void setPbg_1(int pbg_1) {
		this.pbg_1 = pbg_1;
	}
	public double getPbg_tot() {
		return pbg_tot;
	}
	public void setPbg_tot(double pbg_tot) {
		this.pbg_tot = pbg_tot;
	}
	public int getPbh_1() {
		return pbh_1;
	}
	public void setPbh_1(int pbh_1) {
		this.pbh_1 = pbh_1;
	}
	public int getPbh_2() {
		return pbh_2;
	}
	public void setPbh_2(int pbh_2) {
		this.pbh_2 = pbh_2;
	}
	public double getPbh_tot() {
		return pbh_tot;
	}
	public void setPbh_tot(double pbh_tot) {
		this.pbh_tot = pbh_tot;
	}
	public int getPbi_1() {
		return pbi_1;
	}
	public void setPbi_1(int pbi_1) {
		this.pbi_1 = pbi_1;
	}
	public double getPbi_2() {
		return pbi_2;
	}
	public void setPbi_2(double pbi_2) {
		this.pbi_2 = pbi_2;
	}
	public double getPbi_tot() {
		return pbi_tot;
	}
	public void setPbi_tot(double pbi_tot) {
		this.pbi_tot = pbi_tot;
	}
	public int getPbj_1() {
		return pbj_1;
	}
	public void setPbj_1(int pbj_1) {
		this.pbj_1 = pbj_1;
	}
	public int getPbj_2() {
		return pbj_2;
	}
	public void setPbj_2(int pbj_2) {
		this.pbj_2 = pbj_2;
	}
	public double getPbj_3() {
		return pbj_3;
	}
	public void setPbj_3(double pbj_3) {
		this.pbj_3 = pbj_3;
	}
	public double getPbj_tot() {
		return pbj_tot;
	}
	public void setPbj_tot(double pbj_tot) {
		this.pbj_tot = pbj_tot;
	}
	public int getPbk_1() {
		return pbk_1;
	}
	public void setPbk_1(int pbk_1) {
		this.pbk_1 = pbk_1;
	}
	public double getPbk_2() {
		return pbk_2;
	}
	public void setPbk_2(double pbk_2) {
		this.pbk_2 = pbk_2;
	}
	public double getPbk_3() {
		return pbk_3;
	}
	public void setPbk_3(double pbk_3) {
		this.pbk_3 = pbk_3;
	}
	public int getPbk_4() {
		return pbk_4;
	}
	public void setPbk_4(int pbk_4) {
		this.pbk_4 = pbk_4;
	}
	public double getPbk_5() {
		return pbk_5;
	}
	public void setPbk_5(double pbk_5) {
		this.pbk_5 = pbk_5;
	}
	public double getPbk_6() {
		return pbk_6;
	}
	public void setPbk_6(double pbk_6) {
		this.pbk_6 = pbk_6;
	}
	public int getPbk_7() {
		return pbk_7;
	}
	public void setPbk_7(int pbk_7) {
		this.pbk_7 = pbk_7;
	}
	public double getPbk_8() {
		return pbk_8;
	}
	public void setPbk_8(double pbk_8) {
		this.pbk_8 = pbk_8;
	}
	public double getPbk_9() {
		return pbk_9;
	}
	public void setPbk_9(double pbk_9) {
		this.pbk_9 = pbk_9;
	}
	public double getPbk_tot() {
		return pbk_tot;
	}
	public void setPbk_tot(double pbk_tot) {
		this.pbk_tot = pbk_tot;
	}
	public int getPbl_1() {
		return pbl_1;
	}
	public void setPbl_1(int pbl_1) {
		this.pbl_1 = pbl_1;
	}
	public double getPbl_tot() {
		return pbl_tot;
	}
	public void setPbl_tot(double pbl_tot) {
		this.pbl_tot = pbl_tot;
	}
	public int getPbm_1() {
		return pbm_1;
	}
	public void setPbm_1(int pbm_1) {
		this.pbm_1 = pbm_1;
	}
	public double getPbm_tot() {
		return pbm_tot;
	}
	public void setPbm_tot(double pbm_tot) {
		this.pbm_tot = pbm_tot;
	}
	public int getPbn_1() {
		return pbn_1;
	}
	public void setPbn_1(int pbn_1) {
		this.pbn_1 = pbn_1;
	}
	public double getPbn_tot() {
		return pbn_tot;
	}
	public void setPbn_tot(double pbn_tot) {
		this.pbn_tot = pbn_tot;
	}
	public int getPbo_1() {
		return pbo_1;
	}
	public void setPbo_1(int pbo_1) {
		this.pbo_1 = pbo_1;
	}
	public double getPbo_tot() {
		return pbo_tot;
	}
	public void setPbo_tot(double pbo_tot) {
		this.pbo_tot = pbo_tot;
	}
	public int getPra_1() {
		return pra_1;
	}
	public void setPra_1(int pra_1) {
		this.pra_1 = pra_1;
	}
	public double getPra_tot() {
		return pra_tot;
	}
	public void setPra_tot(double pra_tot) {
		this.pra_tot = pra_tot;
	}
	public int getPrb_1() {
		return prb_1;
	}
	public void setPrb_1(int prb_1) {
		this.prb_1 = prb_1;
	}
	public double getPrb_tot() {
		return prb_tot;
	}
	public void setPrb_tot(double prb_tot) {
		this.prb_tot = prb_tot;
	}
	public int getPrc_1() {
		return prc_1;
	}
	public void setPrc_1(int prc_1) {
		this.prc_1 = prc_1;
	}
	public double getPrc_tot() {
		return prc_tot;
	}
	public void setPrc_tot(double prc_tot) {
		this.prc_tot = prc_tot;
	}
	public int getPrd_1() {
		return prd_1;
	}
	public void setPrd_1(int prd_1) {
		this.prd_1 = prd_1;
	}
	public double getPrd_tot() {
		return prd_tot;
	}
	public void setPrd_tot(double prd_tot) {
		this.prd_tot = prd_tot;
	}
	public int getPre_1() {
		return pre_1;
	}
	public void setPre_1(int pre_1) {
		this.pre_1 = pre_1;
	}
	public double getPre_tot() {
		return pre_tot;
	}
	public void setPre_tot(double pre_tot) {
		this.pre_tot = pre_tot;
	}
	public int getPrf_1() {
		return prf_1;
	}
	public void setPrf_1(int prf_1) {
		this.prf_1 = prf_1;
	}
	public double getPrf_tot() {
		return prf_tot;
	}
	public void setPrf_tot(double prf_tot) {
		this.prf_tot = prf_tot;
	}
	public String getPrg_1() {
		return prg_1;
	}
	public void setPrg_1(String prg_1) {
		this.prg_1 = prg_1;
	}
	public int getPrg_tot() {
		return prg_tot;
	}
	public void setPrg_tot(int prg_tot) {
		this.prg_tot = prg_tot;
	}
	public int getEtc_1() {
		return etc_1;
	}
	public void setEtc_1(int etc_1) {
		this.etc_1 = etc_1;
	}
	public int getEtc_2() {
		return etc_2;
	}
	public void setEtc_2(int etc_2) {
		this.etc_2 = etc_2;
	}
	public int getEtc_3() {
		return etc_3;
	}
	public void setEtc_3(int etc_3) {
		this.etc_3 = etc_3;
	}
	public double getEtc_tot() {
		return etc_tot;
	}
	public void setEtc_tot(double etc_tot) {
		this.etc_tot = etc_tot;
	}
	public double getCom_dem() {
		return com_dem;
	}
	public void setCom_dem(double com_dem) {
		this.com_dem = com_dem;
	}
	public double getCom_total() {
		return com_total;
	}
	public void setCom_total(double com_total) {
		this.com_total = com_total;
	}
	public double getRn_day() {
		return rn_day;
	}
	public void setRn_day(double rn_day) {
		this.rn_day = rn_day;
	}
	public double getRn_10m_max() {
		return rn_10m_max;
	}
	public void setRn_10m_max(double rn_10m_max) {
		this.rn_10m_max = rn_10m_max;
	}
	public double getRn_60m_max() {
		return rn_60m_max;
	}
	public void setRn_60m_max(double rn_60m_max) {
		this.rn_60m_max = rn_60m_max;
	}
	public String getDamage_name() {
		return damage_name;
	}
	public void setDamage_name(String damage_name) {
		this.damage_name = damage_name;
	}
	public double getPub_total() {
		return pub_total;
	}
	public void setPub_total(double pub_total) {
		this.pub_total = pub_total;
	}
	public double getPri_total() {
		return pri_total;
	}
	public void setPri_total(double pri_total) {
		this.pri_total = pri_total;
	}
	public double getTotal_damage() {
		return total_damage;
	}
	public void setTotal_damage(double total_damage) {
		this.total_damage = total_damage;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getWd_ins() {
		return wd_ins;
	}
	public void setWd_ins(double wd_ins) {
		this.wd_ins = wd_ins;
	}
	public String getInform_nm() {
		return inform_nm;
	}
	public void setInform_nm(String inform_nm) {
		this.inform_nm = inform_nm;
	}
	public String getDamage_cause() {
		return damage_cause;
	}
	public void setDamage_cause(String damage_cause) {
		this.damage_cause = damage_cause;
	}
	public String getDamage_result() {
		return damage_result;
	}
	public void setDamage_result(String damage_result) {
		this.damage_result = damage_result;
	}
	public String getAge_sperate() {
		return age_sperate;
	}
	public void setAge_sperate(String age_sperate) {
		this.age_sperate = age_sperate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getTotal_com_dme() {
		return total_com_dme;
	}
	public void setTotal_com_dme(double total_com_dme) {
		this.total_com_dme = total_com_dme;
	}
	public double getAll_total_damage() {
		return all_total_damage;
	}
	public void setAll_total_damage(double all_total_damage) {
		this.all_total_damage = all_total_damage;
	}
	public String getDme_code() {
		return dme_code;
	}
	public void setDme_code(String dme_code) {
		this.dme_code = dme_code;
	}
	public YearDmeConditionDto getDmeCondition() {
		return dmeCondition;
	}
	public void setDmeCondition(YearDmeConditionDto dmeCondition) {
		this.dmeCondition = dmeCondition;
	}
	public double getCom_dme_max() {
		return com_dme_max;
	}
	public void setCom_dme_max(double com_dme_max) {
		this.com_dme_max = com_dme_max;
	}
	public double getTotal_damage_max() {
		return total_damage_max;
	}
	public void setTotal_damage_max(double total_damage_max) {
		this.total_damage_max = total_damage_max;
	}
	public ObsAsosDto getAsosInfo() {
		return asosInfo;
	}
	public void setAsosInfo(ObsAsosDto asosInfo) {
		this.asosInfo = asosInfo;
	}
	public String getDis_category() {
		return dis_category;
	}
	public void setDis_category(String dis_category) {
		this.dis_category = dis_category;
	}
	
}
