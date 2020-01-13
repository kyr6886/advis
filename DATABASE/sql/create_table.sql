
-- Drop table

-- DROP TABLE public.ids;

CREATE TABLE public.ids (
	table_name varchar(20) NOT NULL,
	next_id numeric NOT NULL DEFAULT 0
);

-- Drop table

-- DROP TABLE public.lettccmmnclcode;

CREATE TABLE public.lettccmmnclcode (
	cl_code bpchar(3) NOT NULL,
	cl_code_nm varchar(60) NULL,
	cl_code_dc varchar(200) NULL,
	use_at bpchar(1) NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	CONSTRAINT lettccmmnclcode_pkey PRIMARY KEY (cl_code)
);

-- Drop table

-- DROP TABLE public.lettccmmncode;

CREATE TABLE public.lettccmmncode (
	code_id varchar(6) NOT NULL,
	code_id_nm varchar(60) NULL,
	code_id_dc varchar(200) NULL,
	use_at bpchar(1) NULL,
	cl_code bpchar(3) NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	CONSTRAINT lettccmmncode_pkey PRIMARY KEY (code_id)
);

-- Drop table

-- DROP TABLE public.lettccmmndetailcode;

CREATE TABLE public.lettccmmndetailcode (
	code_id varchar(6) NOT NULL,
	code varchar(15) NOT NULL,
	code_nm varchar(60) NULL,
	code_dc varchar(200) NULL,
	use_at bpchar(1) NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	CONSTRAINT lettccmmndetailcode_pk PRIMARY KEY (code_id, code)
);

-- Drop table

-- DROP TABLE public.lettczip;

CREATE TABLE public.lettczip (
	zip varchar(6) NOT NULL,
	sn numeric NOT NULL DEFAULT 0::numeric,
	ctprvn_nm varchar(20) NULL,
	signgu_nm varchar(20) NULL,
	emd_nm varchar(60) NULL,
	li_buld_nm varchar(60) NULL,
	lnbr_dong_ho varchar(20) NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	CONSTRAINT lettczip_pk PRIMARY KEY (zip, sn)
);

-- Drop table

-- DROP TABLE public.lettnauthorgroupinfo;

CREATE TABLE public.lettnauthorgroupinfo (
	group_id bpchar(20) NOT NULL,
	group_nm varchar(60) NOT NULL,
	group_creat_de bpchar(20) NOT NULL,
	group_dc varchar(100) NULL,
	CONSTRAINT lettnauthorgroupinfo_pkey PRIMARY KEY (group_id)
);

-- Drop table

-- DROP TABLE public.lettnauthorinfo;

CREATE TABLE public.lettnauthorinfo (
	author_code varchar(30) NOT NULL,
	author_nm varchar(60) NOT NULL,
	author_dc varchar(200) NULL,
	author_creat_de bpchar(20) NOT NULL,
	CONSTRAINT lettnauthorinfo_pkey PRIMARY KEY (author_code)
);

-- Drop table

-- DROP TABLE public.lettnauthorrolerelate;

CREATE TABLE public.lettnauthorrolerelate (
	author_code varchar(30) NOT NULL,
	role_code varchar(50) NOT NULL,
	creat_dt date NULL,
	CONSTRAINT lettnauthorrolerelate_pk PRIMARY KEY (author_code, role_code)
);

-- Drop table

-- DROP TABLE public.lettnbanner;

CREATE TABLE public.lettnbanner (
	banner_id bpchar(20) NOT NULL,
	banner_nm varchar(60) NOT NULL,
	link_url varchar(255) NOT NULL,
	banner_image varchar(60) NOT NULL,
	banner_dc varchar(200) NULL,
	reflct_at bpchar(1) NOT NULL,
	frst_register_id varchar(20) NULL,
	frst_regist_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	banner_image_file varchar(60) NULL,
	sort_ordr numeric NULL,
	CONSTRAINT lettnbanner_pkey PRIMARY KEY (banner_id)
);

-- Drop table

-- DROP TABLE public.lettnbbs;

CREATE TABLE public.lettnbbs (
	ntt_id numeric NOT NULL,
	bbs_id bpchar(20) NOT NULL,
	ntt_no numeric NULL,
	ntt_sj varchar(2000) NULL,
	ntt_cn text NULL,
	answer_at bpchar(1) NULL,
	parntsctt_no numeric NULL,
	answer_lc numeric NULL,
	sort_ordr numeric NULL,
	rdcnt numeric NULL,
	use_at bpchar(1) NOT NULL,
	ntce_bgnde bpchar(20) NULL,
	ntce_endde bpchar(20) NULL,
	ntcr_id varchar(20) NULL,
	ntcr_nm varchar(20) NULL,
	"password" varchar(200) NULL,
	atch_file_id bpchar(20) NULL,
	frst_regist_pnttm date NOT NULL,
	frst_register_id varchar(20) NOT NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	CONSTRAINT lettnbbs_pk PRIMARY KEY (ntt_id, bbs_id)
);

-- Drop table

-- DROP TABLE public.lettnbbsmaster;

CREATE TABLE public.lettnbbsmaster (
	bbs_id bpchar(20) NOT NULL,
	bbs_nm varchar(255) NOT NULL,
	bbs_intrcn varchar(2400) NULL,
	bbs_ty_code bpchar(6) NOT NULL,
	bbs_attrb_code bpchar(6) NOT NULL,
	reply_posbl_at bpchar(1) NULL,
	file_atch_posbl_at bpchar(1) NOT NULL,
	atch_posbl_file_numeric numeric NOT NULL,
	atch_posbl_file_size numeric NULL,
	use_at bpchar(1) NOT NULL,
	tmplat_id bpchar(20) NULL,
	frst_register_id varchar(20) NOT NULL,
	frst_regist_pnttm date NOT NULL,
	last_updusr_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	CONSTRAINT lettnbbsmaster_pkey PRIMARY KEY (bbs_id)
);

-- Drop table

-- DROP TABLE public.lettnbbsmasteroptn;

CREATE TABLE public.lettnbbsmasteroptn (
	bbs_id bpchar(20) NOT NULL,
	answer_at bpchar(1) NOT NULL DEFAULT ''::bpchar,
	stsfdg_at bpchar(1) NOT NULL DEFAULT ''::bpchar,
	frst_regist_pnttm date NOT NULL DEFAULT now(),
	last_updt_pnttm date NULL,
	frst_register_id varchar(20) NOT NULL DEFAULT ''::character varying,
	last_updusr_id varchar(20) NULL,
	CONSTRAINT lettnbbsmasteroptn_pkey PRIMARY KEY (bbs_id)
);

-- Drop table

-- DROP TABLE public.lettnbbsuse;

CREATE TABLE public.lettnbbsuse (
	bbs_id bpchar(20) NOT NULL,
	trget_id bpchar(20) NOT NULL DEFAULT ''::bpchar,
	use_at bpchar(1) NOT NULL,
	regist_se_code bpchar(6) NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NOT NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	CONSTRAINT lettnbbsuse_pk PRIMARY KEY (bbs_id, trget_id)
);

-- Drop table

-- DROP TABLE public.lettnemplyrinfo;

CREATE TABLE public.lettnemplyrinfo (
	emplyr_id varchar(20) NOT NULL,
	orgnzt_id bpchar(20) NULL,
	user_nm varchar(60) NOT NULL,
	"password" varchar(200) NOT NULL,
	empl_no varchar(20) NULL,
	ihidnum varchar(200) NULL,
	sexdstn_code bpchar(1) NULL,
	brthdy bpchar(20) NULL,
	fxnum varchar(20) NULL,
	house_adres varchar(100) NULL,
	password_hint varchar(100) NOT NULL,
	password_cnsr varchar(100) NOT NULL,
	house_end_telno varchar(4) NULL,
	area_no varchar(4) NULL,
	detail_adres varchar(100) NULL,
	zip varchar(6) NULL,
	offm_telno varchar(20) NULL,
	mbtlnum varchar(20) NULL,
	email_adres varchar(50) NULL,
	ofcps_nm varchar(60) NULL,
	house_middle_telno varchar(4) NULL,
	group_id bpchar(20) NULL,
	pstinst_code bpchar(8) NULL,
	emplyr_sttus_code varchar(15) NOT NULL,
	esntl_id bpchar(20) NOT NULL,
	crtfc_dn_value varchar(20) NULL,
	sbscrb_de date NULL,
	CONSTRAINT lettnemplyrinfo_pkey PRIMARY KEY (emplyr_id)
);

-- Drop table

-- DROP TABLE public.lettnemplyrscrtyestbs;

CREATE TABLE public.lettnemplyrscrtyestbs (
	scrty_dtrmn_trget_id varchar(20) NOT NULL,
	mber_ty_code varchar(15) NULL,
	author_code varchar(30) NOT NULL,
	CONSTRAINT lettnemplyrscrtyestbs_pkey PRIMARY KEY (scrty_dtrmn_trget_id)
);

-- Drop table

-- DROP TABLE public.lettnentrprsmber;

CREATE TABLE public.lettnentrprsmber (
	entrprs_mber_id varchar(20) NOT NULL,
	entrprs_se_code bpchar(15) NULL,
	bizrno varchar(10) NULL,
	jurirno varchar(13) NULL,
	cmpny_nm varchar(60) NOT NULL,
	cxfc varchar(50) NULL,
	zip varchar(6) NULL,
	adres varchar(100) NULL,
	entrprs_middle_telno varchar(4) NULL,
	fxnum varchar(20) NULL,
	induty_code bpchar(15) NULL,
	applcnt_nm varchar(50) NULL,
	applcnt_ihidnum varchar(200) NULL,
	sbscrb_de date NULL,
	entrprs_mber_sttus varchar(15) NULL,
	entrprs_mber_password varchar(200) NOT NULL,
	entrprs_mber_password_hint varchar(100) NOT NULL,
	entrprs_mber_password_cnsr varchar(100) NOT NULL,
	group_id bpchar(20) NULL,
	detail_adres varchar(100) NULL,
	entrprs_end_telno varchar(4) NULL,
	area_no varchar(4) NULL,
	applcnt_email_adres varchar(50) NULL,
	esntl_id bpchar(20) NOT NULL,
	CONSTRAINT lettnentrprsmber_pkey PRIMARY KEY (entrprs_mber_id)
);

-- Drop table

-- DROP TABLE public.lettnfaqinfo;

CREATE TABLE public.lettnfaqinfo (
	faq_id bpchar(20) NOT NULL,
	qestn_sj varchar(255) NULL,
	qestn_cn varchar(2500) NULL,
	answer_cn varchar(2500) NULL,
	rdcnt numeric NULL,
	frst_regist_pnttm date NOT NULL,
	frst_register_id varchar(20) NOT NULL,
	last_updt_pnttm date NOT NULL,
	last_updusr_id varchar(20) NOT NULL,
	atch_file_id bpchar(20) NULL,
	qna_process_sttus_code bpchar(1) NULL,
	CONSTRAINT lettnfaqinfo_pkey PRIMARY KEY (faq_id)
);

-- Drop table

-- DROP TABLE public.lettnfile;

CREATE TABLE public.lettnfile (
	atch_file_id bpchar(20) NOT NULL,
	creat_dt date NOT NULL,
	use_at bpchar(1) NULL,
	CONSTRAINT lettnfile_pkey PRIMARY KEY (atch_file_id)
);

-- Drop table

-- DROP TABLE public.lettnfiledetail;

CREATE TABLE public.lettnfiledetail (
	atch_file_id bpchar(20) NOT NULL,
	file_sn numeric NOT NULL,
	file_stre_cours varchar(2000) NOT NULL,
	stre_file_nm varchar(255) NOT NULL,
	orignl_file_nm varchar(255) NULL,
	file_extsn varchar(20) NOT NULL,
	file_cn text NULL,
	file_size numeric NULL,
	CONSTRAINT lettnfiledetail_pk PRIMARY KEY (atch_file_id, file_sn)
);

-- Drop table

-- DROP TABLE public.lettngnrlmber;

CREATE TABLE public.lettngnrlmber (
	mber_id varchar(20) NOT NULL,
	"password" varchar(200) NOT NULL,
	password_hint varchar(100) NOT NULL,
	password_cnsr varchar(100) NOT NULL,
	ihidnum varchar(200) NULL,
	mber_nm varchar(50) NOT NULL,
	zip varchar(6) NULL,
	adres varchar(100) NULL,
	area_no varchar(4) NULL,
	mber_sttus varchar(15) NULL,
	detail_adres varchar(100) NULL,
	end_telno varchar(4) NULL,
	mbtlnum varchar(20) NULL,
	group_id bpchar(20) NULL,
	mber_fxnum varchar(20) NULL,
	mber_email_adres varchar(50) NULL,
	middle_telno varchar(4) NULL,
	sbscrb_de date NULL,
	sexdstn_code bpchar(1) NULL,
	esntl_id bpchar(20) NOT NULL,
	CONSTRAINT lettngnrlmber_pkey PRIMARY KEY (mber_id)
);

-- Drop table

-- DROP TABLE public.lettnindvdlinfopolicy;

CREATE TABLE public.lettnindvdlinfopolicy (
	indvdl_info_policy_id bpchar(20) NOT NULL,
	indvdl_info_policy_cn varchar(2500) NULL,
	indvdl_info_policy_agre_at bpchar(1) NULL,
	frst_register_id varchar(20) NULL,
	frst_regist_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	indvdl_info_policy_nm varchar(255) NULL,
	CONSTRAINT lettnindvdlinfopolicy_pkey PRIMARY KEY (indvdl_info_policy_id)
);

-- Drop table

-- DROP TABLE public.lettnmenucreatdtls;

CREATE TABLE public.lettnmenucreatdtls (
	menu_no numeric NOT NULL,
	author_code varchar(30) NOT NULL,
	mapng_creat_id varchar(30) NULL,
	CONSTRAINT lettnmenucreatdtls_pk PRIMARY KEY (menu_no, author_code)
);

-- Drop table

-- DROP TABLE public.lettnmenuinfo;

CREATE TABLE public.lettnmenuinfo (
	menu_nm varchar(60) NOT NULL,
	progrm_file_nm varchar(60) NOT NULL,
	menu_no numeric NOT NULL,
	upper_menu_no numeric NULL,
	menu_ordr numeric NOT NULL,
	menu_dc varchar(250) NULL,
	relate_image_path varchar(100) NULL,
	relate_image_nm varchar(60) NULL,
	CONSTRAINT lettnmenuinfo_pkey PRIMARY KEY (menu_no)
);

-- Drop table

-- DROP TABLE public.lettnorgnztinfo;

CREATE TABLE public.lettnorgnztinfo (
	orgnzt_id bpchar(20) NOT NULL,
	orgnzt_nm varchar(20) NOT NULL,
	orgnzt_dc varchar(100) NULL,
	CONSTRAINT lettnorgnztinfo_pkey PRIMARY KEY (orgnzt_id)
);

-- Drop table

-- DROP TABLE public.lettnprogrmlist;

CREATE TABLE public.lettnprogrmlist (
	progrm_file_nm varchar(60) NOT NULL,
	progrm_stre_path varchar(100) NOT NULL,
	progrm_korean_nm varchar(60) NULL,
	progrm_dc varchar(200) NULL,
	url varchar(100) NOT NULL,
	CONSTRAINT lettnprogrmlist_pkey PRIMARY KEY (progrm_file_nm)
);

-- Drop table

-- DROP TABLE public.lettnqainfo;

CREATE TABLE public.lettnqainfo (
	qa_id bpchar(20) NOT NULL,
	qestn_sj varchar(255) NULL,
	qestn_cn varchar(2500) NULL,
	writng_de bpchar(20) NULL,
	rdcnt numeric NULL,
	email_adres varchar(50) NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	qna_process_sttus_code bpchar(1) NULL,
	wrter_nm varchar(20) NULL,
	answer_cn varchar(2500) NULL,
	writng_password varchar(20) NULL,
	answer_de bpchar(20) NULL,
	email_answer_at bpchar(1) NULL,
	area_no varchar(4) NULL,
	middle_telno varchar(4) NULL,
	end_telno varchar(4) NULL,
	CONSTRAINT lettnqainfo_pkey PRIMARY KEY (qa_id)
);

-- Drop table

-- DROP TABLE public.lettnqustnriem;

CREATE TABLE public.lettnqustnriem (
	qustnr_tmplat_id bpchar(20) NOT NULL,
	qestnr_id bpchar(20) NOT NULL,
	qustnr_qesitm_id bpchar(20) NOT NULL,
	qustnr_iem_id varchar(20) NOT NULL,
	iem_sn numeric NULL,
	iem_cn varchar(1000) NULL,
	etc_answer_at bpchar(1) NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	CONSTRAINT lettnqustnriem_pk PRIMARY KEY (qustnr_tmplat_id, qestnr_id, qustnr_qesitm_id, qustnr_iem_id)
);

-- Drop table

-- DROP TABLE public.lettnqustnrqesitm;

CREATE TABLE public.lettnqustnrqesitm (
	qestnr_id bpchar(20) NOT NULL,
	qustnr_qesitm_id bpchar(20) NOT NULL,
	qustnr_tmplat_id bpchar(20) NOT NULL,
	qestn_sn numeric NULL,
	qestn_ty_code bpchar(1) NULL,
	qestn_cn varchar(2500) NULL,
	mxmm_choise_co numeric NULL,
	frst_regist_pnttm date NOT NULL,
	frst_register_id varchar(20) NOT NULL,
	last_updt_pnttm date NOT NULL,
	last_updusr_id varchar(20) NOT NULL,
	CONSTRAINT lettnqustnrqesitm_pk PRIMARY KEY (qestnr_id, qustnr_qesitm_id, qustnr_tmplat_id)
);

-- Drop table

-- DROP TABLE public.lettnqustnrrespondinfo;

CREATE TABLE public.lettnqustnrrespondinfo (
	qustnr_tmplat_id bpchar(20) NOT NULL,
	qestnr_id bpchar(20) NOT NULL,
	qustnr_respond_id bpchar(20) NOT NULL,
	sexdstn_code bpchar(1) NULL,
	occp_ty_code varchar(1) NULL,
	respond_nm varchar(50) NULL,
	brthdy bpchar(20) NULL,
	area_no varchar(4) NULL,
	middle_telno varchar(4) NULL,
	end_telno varchar(4) NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	CONSTRAINT lettnqustnrrespondinfo_pk PRIMARY KEY (qustnr_tmplat_id, qestnr_id, qustnr_respond_id)
);

-- Drop table

-- DROP TABLE public.lettnqustnrrspnsresult;

CREATE TABLE public.lettnqustnrrspnsresult (
	qustnr_rspns_result_id bpchar(20) NOT NULL,
	qestnr_id bpchar(20) NOT NULL,
	qustnr_qesitm_id bpchar(20) NOT NULL,
	qustnr_tmplat_id bpchar(20) NOT NULL,
	respond_answer_cn varchar(1000) NULL,
	etc_answer_cn varchar(1000) NULL,
	respond_nm varchar(50) NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	qustnr_iem_id varchar(20) NULL,
	CONSTRAINT lettnqustnrrspnsresult_pk PRIMARY KEY (qustnr_rspns_result_id, qestnr_id, qustnr_qesitm_id, qustnr_tmplat_id)
);

-- Drop table

-- DROP TABLE public.lettnqustnrtmplat;

CREATE TABLE public.lettnqustnrtmplat (
	qustnr_tmplat_id bpchar(20) NOT NULL,
	qustnr_tmplat_ty varchar(100) NULL,
	qustnr_tmplat_dc varchar(2000) NULL,
	qustnr_tmplat_path_nm varchar(100) NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	qustnr_tmplat_image_info text NULL,
	CONSTRAINT lettnqustnrtmplat_pkey PRIMARY KEY (qustnr_tmplat_id)
);

-- Drop table

-- DROP TABLE public.lettnroleinfo;

CREATE TABLE public.lettnroleinfo (
	role_code varchar(50) NOT NULL,
	role_nm varchar(60) NOT NULL,
	role_pttrn varchar(300) NULL,
	role_dc varchar(200) NULL,
	role_ty varchar(80) NULL,
	role_sort varchar(10) NULL,
	role_creat_de bpchar(20) NOT NULL,
	CONSTRAINT lettnroleinfo_pkey PRIMARY KEY (role_code)
);

-- Drop table

-- DROP TABLE public.lettnroles_hierarchy;

CREATE TABLE public.lettnroles_hierarchy (
	parnts_role varchar(30) NOT NULL,
	chldrn_role varchar(30) NOT NULL,
	CONSTRAINT lettnroles_hierarchy_pk PRIMARY KEY (parnts_role, chldrn_role)
);

-- Drop table

-- DROP TABLE public.lettnstplatinfo;

CREATE TABLE public.lettnstplatinfo (
	use_stplat_id bpchar(20) NOT NULL,
	use_stplat_nm varchar(100) NULL,
	use_stplat_cn text NULL,
	info_provd_agre_cn text NULL,
	frst_regist_pnttm date NULL,
	frst_register_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	CONSTRAINT lettnstplatinfo_pkey PRIMARY KEY (use_stplat_id)
);

-- Drop table

-- DROP TABLE public.lettntmplatinfo;

CREATE TABLE public.lettntmplatinfo (
	tmplat_id bpchar(20) NOT NULL,
	tmplat_nm varchar(255) NULL,
	tmplat_cours varchar(2000) NULL,
	use_at bpchar(1) NULL,
	tmplat_se_code bpchar(6) NULL,
	frst_register_id varchar(20) NULL,
	frst_regist_pnttm date NULL,
	last_updusr_id varchar(20) NULL,
	last_updt_pnttm date NULL,
	CONSTRAINT lettntmplatinfo_pkey PRIMARY KEY (tmplat_id)
);

-- Drop table

-- DROP TABLE public.spatial_ref_sys;

CREATE TABLE public.spatial_ref_sys (
	srid int4 NOT NULL,
	auth_name varchar(256) NULL,
	auth_srid int4 NULL,
	srtext varchar(2048) NULL,
	proj4text varchar(2048) NULL,
	CONSTRAINT spatial_ref_sys_pkey PRIMARY KEY (srid),
	CONSTRAINT spatial_ref_sys_srid_check CHECK (((srid > 0) AND (srid <= 998999)))
);

-- Drop table

-- DROP TABLE public.tb_accident_soc;

CREATE TABLE public.tb_accident_soc (
	di_inf_id varchar(20) NULL,
	di_ty_id int4 NULL,
	di_name bpchar(10) NULL,
	di_dtl_id bpchar(10) NULL,
	di_dtl_name varchar(50) NULL,
	item_ty varchar(10) NULL,
	di_dtl_etc varchar(50) NULL,
	di_dtl_clt varchar(50) NULL,
	di_inf_id_clt varchar(10) NULL,
	di_inf_name varchar(500) NULL,
	dis_content varchar(3000) NULL,
	obz_dt bpchar(20) NULL,
	occr_st_date timestamptz NULL,
	occr_ed_date timestamptz NULL,
	occr_place varchar(500) NULL,
	addr_code varchar(10) NULL,
	hm_dmg_killed int4 NULL,
	hm_dmg_missing int4 NULL,
	hm_dmg_injser int4 NULL,
	hm_dmg_injslt int4 NULL,
	hm_dmg_etc varchar(500) NULL,
	mt_dmg_fac varchar(500) NULL,
	mt_dmg_bud varchar(500) NULL,
	mt_dmg_anm varchar(500) NULL,
	mt_dmg_plt varchar(500) NULL,
	mt_dmg_etc varchar(500) NULL,
	dmg_total varchar(500) NULL,
	dmg_amnt numeric NULL,
	dmg_amnt_movasset numeric NULL,
	dmg_amnt_rlest numeric NULL,
	cause_first varchar(3000) NULL,
	cause_second varchar(3000) NULL,
	cause_third varchar(3000) NULL,
	cause_etc varchar(3000) NULL,
	action_prob varchar(3000) NULL,
	action_imprv varchar(3000) NULL,
	action_exec varchar(3000) NULL,
	action_content varchar(3000) NULL,
	instt_super varchar(30) NULL,
	instt_resp varchar(30) NULL,
	instt_cause_inv varchar(30) NULL,
	instt_collect varchar(30) NULL,
	rel_legislation varchar(50) NULL,
	rel_cause_inv_data varchar(1000) NULL,
	rel_action_exec varchar(1000) NULL,
	weather varchar(10) NULL,
	temperature numeric NULL,
	humidity numeric NULL,
	cause_self_first varchar(30) NULL,
	cause_self_second varchar(30) NULL,
	cause_self_third varchar(30) NULL,
	cause_self_etc varchar(30) NULL,
	cord_lat numeric NULL,
	cord_lon numeric NULL,
	cord_ht numeric NULL,
	ctg_id varchar(30) NULL
);

-- Drop table

-- DROP TABLE public.tb_accident_soc_group;

CREATE TABLE public.tb_accident_soc_group (
	di_inf_id varchar(20) NULL,
	di_ty_id int4 NULL,
	di_name bpchar(10) NULL,
	di_dtl_id bpchar(10) NULL,
	di_dtl_name varchar(50) NULL,
	item_ty varchar(10) NULL,
	di_dtl_etc varchar(50) NULL,
	di_dtl_clt varchar(50) NULL,
	di_inf_id_clt varchar(10) NULL,
	di_inf_name varchar(500) NULL,
	dis_content varchar(3000) NULL,
	obz_dt bpchar(20) NULL,
	occr_st_date timestamptz NULL,
	occr_ed_date timestamptz NULL,
	occr_place varchar(500) NULL,
	addr_code varchar(10) NULL,
	hm_dmg_killed int4 NULL,
	hm_dmg_missing int4 NULL,
	hm_dmg_injser int4 NULL,
	hm_dmg_injslt int4 NULL,
	hm_dmg_etc varchar(500) NULL,
	mt_dmg_fac varchar(500) NULL,
	mt_dmg_bud varchar(500) NULL,
	mt_dmg_anm varchar(500) NULL,
	mt_dmg_plt varchar(500) NULL,
	mt_dmg_etc varchar(500) NULL,
	dmg_total varchar(500) NULL,
	dmg_amnt numeric NULL,
	dmg_amnt_movasset numeric NULL,
	dmg_amnt_rlest numeric NULL,
	cause_first varchar(3000) NULL,
	cause_second varchar(3000) NULL,
	cause_third varchar(3000) NULL,
	cause_etc varchar(3000) NULL,
	action_prob varchar(3000) NULL,
	action_imprv varchar(3000) NULL,
	action_exec varchar(3000) NULL,
	action_content varchar(3000) NULL,
	instt_super varchar(30) NULL,
	instt_resp varchar(30) NULL,
	instt_cause_inv varchar(30) NULL,
	instt_collect varchar(30) NULL,
	rel_legislation varchar(50) NULL,
	rel_cause_inv_data varchar(1000) NULL,
	rel_action_exec varchar(1000) NULL,
	weather varchar(10) NULL,
	temperature numeric NULL,
	humidity numeric NULL,
	cause_self_first varchar(30) NULL,
	cause_self_second varchar(30) NULL,
	cause_self_third varchar(30) NULL,
	cause_self_etc varchar(30) NULL,
	cord_lat numeric NULL,
	cord_lon numeric NULL,
	cord_ht numeric NULL,
	ctg_id varchar(30) NULL
);

-- Drop table

-- DROP TABLE public.tb_attach_file;

CREATE TABLE public.tb_attach_file (
	file_seq numeric NOT NULL,
	file_grp_id varchar(100) NULL,
	file_org_name varchar(200) NULL,
	file_new_name varchar(100) NULL,
	file_path varchar(100) NULL,
	file_size numeric NULL,
	file_ext varchar(10) NULL,
	create_date date NULL,
	update_date date NULL,
	update_user_id varchar(256) NULL,
	create_user_id varchar(256) NULL,
	use_yn bpchar(1) NULL,
	file_title varchar(200) NULL,
	CONSTRAINT tb_attach_file_pkey PRIMARY KEY (file_seq)
);

-- Drop table

-- DROP TABLE public.tb_bbs_category;

CREATE TABLE public.tb_bbs_category (
	category_seq numeric NOT NULL,
	category_title varchar(20) NULL,
	bbs_id varchar(8) NULL,
	CONSTRAINT tb_bbs_category_pkey PRIMARY KEY (category_seq)
);

-- Drop table

-- DROP TABLE public.tb_bbs_content;

CREATE TABLE public.tb_bbs_content (
	bbs_seq numeric NOT NULL,
	bbs_cont varchar(4000) NULL,
	bbs_group int4 NULL,
	bbs_sort int4 NULL,
	bbs_parent int4 NULL,
	bbs_level int4 NULL,
	file_grp_id varchar(250) NULL,
	bbs_cont_title varchar(200) NULL,
	bbs_read int4 NULL,
	category_seq int4 NULL,
	update_date date NULL,
	create_date date NULL,
	update_user_id varchar(256) NULL,
	create_user_id varchar(256) NULL,
	create_ip varchar(20) NULL,
	use_yn bpchar(1) NULL,
	bbs_id varchar(8) NULL,
	fixed_yn varchar(1) NULL,
	CONSTRAINT tb_bbs_content_pkey PRIMARY KEY (bbs_seq)
);

-- Drop table

-- DROP TABLE public.tb_damage_typhoon;

CREATE TABLE public.tb_damage_typhoon (
	seq serial NOT NULL,
	beg_date varchar(10) NULL,
	end_date varchar(10) NULL,
	typ_name varchar(10) NULL,
	impact_yn bpchar(1) NULL,
	typ_eng_name varchar(30) NULL,
	typ_seq varchar(2) NULL,
	rn_day numeric(6,1) NULL,
	rn_day_area varchar(10) NULL,
	com_dme numeric(6) NULL,
	com_dme_sep varchar(30) NULL,
	property_dme numeric(8) NULL,
	typ_ps numeric(6) NULL,
	typ_ws numeric(6) NULL,
	typ_15 numeric(6) NULL,
	com_area varchar(100) NULL,
	typ_feature varchar(3000) NULL,
	CONSTRAINT tb_damage_typhoon_pk PRIMARY KEY (seq)
);

-- Drop table

-- DROP TABLE public.tb_dis_dme_station;

CREATE TABLE public.tb_dis_dme_station (
	dis_dme_id numeric NULL,
	dis_dme_nm varchar NULL,
	dis_dme_area varchar NULL,
	dis_dme_area_detail varchar NULL,
	dis_dme_typ varchar NULL,
	dis_dme_class varchar NULL,
	dis_facility_nm varchar NULL,
	dis_facility_ty varchar NULL,
	dis_water_nm varchar NULL,
	dis_app_date varchar NULL,
	dis_release_date varchar NULL,
	info_update_date varchar NULL,
	app_reason varchar NULL,
	release_reason varchar NULL,
	progress_txt varchar NULL,
	dis_dme_factor varchar NULL,
	dis_app_area numeric NULL,
	dis_app_org_nm varchar NULL,
	dis_app_org_number varchar NULL,
	dis_standard_date varchar NULL,
	provider_code varchar NULL,
	provider_nm varchar NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_event_action;

CREATE TABLE public.tb_dis_event_action (
	ctg_id varchar(20) NOT NULL,
	evt_group varchar(20) NOT NULL,
	"year" varchar(4) NOT NULL,
	"month" varchar(2) NOT NULL,
	"day" varchar(2) NOT NULL,
	"hour" varchar(2) NOT NULL,
	sort int4 NOT NULL,
	dis_org varchar NULL,
	dis_level_code varchar NULL,
	dis_act_lv1 varchar NULL,
	dis_act_lv2 varchar NULL,
	dis_act_lv3 varchar NULL,
	dis_act_lv4 varchar NULL,
	dis_act_lv5 varchar NULL,
	dis_act_lv6 varchar NULL,
	dis_act_date varchar NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_event_dept;

CREATE TABLE public.tb_dis_event_dept (
	seq int4 NOT NULL,
	evt_id varchar(20) NOT NULL,
	evt_num int4 NOT NULL,
	act_date varchar(20) NOT NULL,
	org_code varchar(2) NOT NULL,
	contents varchar(2000) NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_event_detail;

CREATE TABLE public.tb_dis_event_detail (
	seq int4 NOT NULL,
	evt_id varchar(20) NOT NULL,
	evt_num int4 NOT NULL,
	ctg_id varchar(20) NOT NULL,
	contents varchar(2000) NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_event_file;

CREATE TABLE public.tb_dis_event_file (
	seq int4 NOT NULL,
	file_grp_id varchar(100) NOT NULL,
	evt_id varchar(20) NOT NULL,
	ctg_id varchar(20) NOT NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_event_img;

CREATE TABLE public.tb_dis_event_img (
	seq serial NOT NULL,
	file_grp_id varchar(100) NOT NULL,
	evt_id varchar(20) NOT NULL,
	ctg_id varchar(20) NOT NULL,
	title varchar(20) NOT NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL,
	CONSTRAINT pk_img PRIMARY KEY (seq)
);

-- Drop table

-- DROP TABLE public.tb_dis_event_item;

CREATE TABLE public.tb_dis_event_item (
	seq int4 NOT NULL,
	evt_id varchar(20) NOT NULL,
	ctg_id varchar(20) NOT NULL,
	evt_num int4 NOT NULL,
	contents varchar(3000) NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL,
	item_group varchar(20) NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_event_items;

CREATE TABLE public.tb_dis_event_items (
	seq int4 NOT NULL,
	evt_id varchar(20) NOT NULL,
	ctg_id varchar(20) NOT NULL,
	evt_num int4 NOT NULL,
	contents varchar(3000) NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL,
	item_group varchar(10) NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_event_ocr;

CREATE TABLE public.tb_dis_event_ocr (
	seq int4 NOT NULL,
	evt_id varchar(20) NOT NULL,
	evt_num int4 NOT NULL,
	ctg_id varchar(20) NOT NULL,
	contents varchar(2000) NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_event_status;

CREATE TABLE public.tb_dis_event_status (
	seq int4 NOT NULL,
	evt_id varchar(20) NOT NULL,
	evt_num int4 NOT NULL,
	ctg_id varchar(20) NOT NULL,
	contents varchar(2000) NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_event_type;

CREATE TABLE public.tb_dis_event_type (
	ctg_id varchar(20) NOT NULL,
	evt_id varchar(20) NOT NULL,
	evt_num int4 NOT NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_manual;

CREATE TABLE public.tb_dis_manual (
	ctg_id varchar NOT NULL,
	grp_code varchar NULL,
	sys_code varchar NULL,
	manual_title varchar NULL,
	manual_contents varchar NULL,
	seq int4 NOT NULL,
	use_yn varchar(1) NOT NULL
);

-- Drop table

-- DROP TABLE public.tb_dis_month;

CREATE TABLE public.tb_dis_month (
	sido_code varchar(10) NOT NULL,
	damage_money numeric NULL,
	damage_name varchar(20) NOT NULL,
	damage_code varchar(10) NOT NULL,
	damage_month varchar(2) NOT NULL,
	CONSTRAINT tb_dis_month_pk PRIMARY KEY (sido_code, damage_code, damage_month, damage_name)
);

-- Drop table

-- DROP TABLE public.tb_event_location;

CREATE TABLE public.tb_event_location (
	dme_type varchar(10) NULL,
	evt_date varchar(10) NULL,
	evt_time varchar(10) NULL,
	evt_lon varchar(20) NULL,
	evt_lat varchar(20) NULL,
	evt_power varchar(10) NULL,
	evt_z varchar NULL,
	contents varchar(1000) NULL,
	evt_group varchar(20) NULL,
	addr varchar(100) NULL
);

-- Drop table

-- DROP TABLE public.tb_menu_role;

CREATE TABLE public.tb_menu_role (
	role_id varchar(8) NOT NULL,
	menu_id varchar(8) NOT NULL,
	write_yn varchar(1) NULL,
	CONSTRAINT pk_tb_menu_role PRIMARY KEY (role_id, menu_id)
);

-- Drop table

-- DROP TABLE public.tb_obs_asos;

CREATE TABLE public.tb_obs_asos (
	stn_id numeric(5) NOT NULL,
	tm varchar(12) NOT NULL,
	wd_max numeric(6) NULL,
	ws_max numeric(6) NULL,
	ws_max_tm numeric(4) NULL,
	wd_ins numeric(6) NULL,
	ws_ins numeric(6) NULL,
	ws_ins_tm numeric(4) NULL,
	wr_day numeric(6) NULL,
	ta numeric(6) NULL,
	ta_max numeric(6) NULL,
	ta_max_tm numeric(4) NULL,
	ta_min numeric(6) NULL,
	ta_min_tm numeric(4) NULL,
	hm numeric(6) NULL,
	hm_min numeric(6) NULL,
	hm_min_tm numeric(4) NULL,
	pa numeric(6) NULL,
	ps numeric(6) NULL,
	ps_max numeric(6) NULL,
	ps_max_tm numeric(4) NULL,
	ps_min numeric(6) NULL,
	ps_min_tm numeric(4) NULL,
	rn_day numeric(6) NULL,
	rn_d99 numeric(6) NULL,
	rn_dur numeric(6) NULL,
	rn_60m_max numeric(6) NULL,
	rn_60m_max_tm numeric(4) NULL,
	rn_10m_max numeric(6) NULL,
	rn_10m_max_tm numeric(4) NULL,
	sd_new numeric(6) NULL,
	sd_new_tm numeric(4) NULL,
	sd_max numeric(6) NULL,
	sd_max_tm numeric(4) NULL,
	ev_s numeric(6) NULL,
	ev_l numeric(6) NULL,
	fg_dur numeric(6) NULL,
	ss_day numeric(6) NULL,
	ss_dur numeric(6) NULL,
	ss_cmb numeric(6) NULL,
	si_day numeric(6) NULL,
	si_60m_max numeric(6) NULL,
	si_60m_max_tm numeric(4) NULL,
	tg_min numeric(6) NULL,
	tg_min_tm numeric(4) NULL,
	ww_01 numeric(6) NULL,
	ww_02 numeric(6) NULL,
	ww_03 numeric(6) NULL,
	ww_04 numeric(6) NULL,
	ww_05 numeric(6) NULL,
	ww_06 numeric(6) NULL,
	ww_07 numeric(6) NULL,
	ww_08 numeric(6) NULL,
	ww_09 numeric(6) NULL,
	ww_10 numeric(6) NULL,
	ww_11 numeric(6) NULL,
	ww_12 numeric(6) NULL,
	ws numeric(6) NULL,
	td numeric(6) NULL,
	pv numeric(6) NULL,
	vs numeric(6) NULL,
	ca_tot numeric(6) NULL,
	ts numeric(6) NULL,
	te_005 numeric(6) NULL,
	te_01 numeric(6) NULL,
	te_02 numeric(6) NULL,
	te_03 numeric(6) NULL,
	te_05 numeric(6) NULL,
	te_10 numeric(6) NULL,
	te_15 numeric(6) NULL,
	te_30 numeric(6) NULL,
	te_50 numeric(6) NULL,
	dt_regt timestamp NULL,
	CONSTRAINT tb_obs_asos_pk PRIMARY KEY (stn_id, tm)
);
CREATE INDEX tb_obs_asos_tm_stn_id_idx ON public.tb_obs_asos USING btree (tm, stn_id);

-- Drop table

-- DROP TABLE public.tb_obs_st;

CREATE TABLE public.tb_obs_st (
	obs_id varchar(10) NULL,
	resources_code varchar(10) NULL,
	obs_name varchar(20) NULL,
	position_x varchar(30) NULL,
	position_y varchar(30) NULL,
	above_see_level varchar(30) NULL,
	obs_barometer varchar(30) NULL,
	obs_management varchar(40) NULL,
	obs_thermometer varchar(30) NULL,
	obs_windspeed varchar(30) NULL,
	obs_rainfallmeter varchar(30) NULL,
	start_date varchar(30) NULL,
	delete_date varchar(30) NULL
);

-- Drop table

-- DROP TABLE public.tb_obs_tsen;

CREATE TABLE public.tb_obs_tsen (
	law_code varchar(10) NOT NULL,
	obs_id varchar(10) NOT NULL,
	law_area varchar(30) NULL,
	law_area_per varchar(30) NULL,
	CONSTRAINT tb_obs_tsen_pkey PRIMARY KEY (law_code, obs_id)
);

-- Drop table

-- DROP TABLE public.tb_ocr_file;

CREATE TABLE public.tb_ocr_file (
	ctg_id varchar(30) NOT NULL,
	evt_id varchar(30) NOT NULL,
	title varchar(100) NULL,
	file_org_name varchar(100) NULL,
	file_new_name varchar(100) NULL,
	CONSTRAINT tb_ocr_file_pk PRIMARY KEY (ctg_id, evt_id)
);

-- Drop table

-- DROP TABLE public.tb_partner_site;

CREATE TABLE public.tb_partner_site (
	site_url varchar(500) NOT NULL,
	site_title varchar(500) NULL,
	file_grp_id varchar(100) NULL,
	use_yn bpchar(1) NULL,
	CONSTRAINT tb_partner_site_pkey PRIMARY KEY (site_url)
);

-- Drop table

-- DROP TABLE public.tb_rain_damage;

CREATE TABLE public.tb_rain_damage (
	beg_date varchar(30) NULL,
	end_date varchar(30) NULL,
	com_dme varchar(30) NULL,
	com_total varchar(50) NULL,
	pub_total varchar(50) NULL,
	pri_total varchar(50) NULL,
	total_damage varchar(50) NULL,
	stn_id varchar(30) NULL,
	rn_day varchar(30) NULL,
	rn_day_max varchar(30) NULL,
	rn_60m_max varchar(30) NULL
);

-- Drop table

-- DROP TABLE public.tb_typhoon_damage;

CREATE TABLE public.tb_typhoon_damage (
	rownum int4 NULL,
	typ_name varchar(50) NULL,
	typ_ps varchar(50) NULL,
	typ_ws varchar(50) NULL,
	typ_en varchar(50) NULL,
	typ_sp varchar(50) NULL,
	beg_date varchar(50) NULL,
	typ_seq varchar(50) NULL,
	tm_seq varchar(50) NULL,
	end_date varchar(50) NULL,
	com_dme varchar(50) NULL,
	total_damage varchar(50) NULL
);

-- Drop table

-- DROP TABLE public.tb_user;

CREATE TABLE public.tb_user (
	user_id varchar(256) NOT NULL,
	user_pwd varchar(256) NULL,
	user_name varchar(256) NULL,
	user_phone varchar(256) NULL,
	user_email varchar(256) NULL,
	user_type_code varchar(20) NULL,
	user_status_code varchar(20) NULL,
	update_date timestamp NULL,
	create_date timestamp NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn bpchar(1) NULL,
	user_key varchar(32) NULL,
	user_adm_code varchar(20) NULL,
	user_dept varchar(100) NULL,
	user_phone_office varchar(20) NULL,
	user_position varchar(100) NULL,
	user_work_cont varchar(200) NULL,
	user_major varchar(100) NULL,
	user_addr varchar(100) NULL,
	user_fail_count numeric NULL,
	CONSTRAINT tb_user_pkey PRIMARY KEY (user_id)
);

-- Drop table

-- DROP TABLE public.tb_user_itrs;

CREATE TABLE public.tb_user_itrs (
	user_id varchar(256) NULL,
	sido bpchar(2) NULL
);

-- Drop table

-- DROP TABLE public.tb_user_msg;

CREATE TABLE public.tb_user_msg (
	seq numeric NOT NULL,
	user_id varchar(256) NULL,
	msg_content varchar(400) NULL,
	create_date date NULL,
	create_user_id varchar(256) NULL,
	msg_type_code bpchar(18) NULL,
	CONSTRAINT tb_user_msg_pkey PRIMARY KEY (seq)
);

-- Drop table

-- DROP TABLE public.tb_user_role;

CREATE TABLE public.tb_user_role (
	user_id varchar(256) NOT NULL,
	role_id varchar(8) NOT NULL,
	CONSTRAINT pk_tb_user_role PRIMARY KEY (user_id, role_id)
);

-- Drop table

-- DROP TABLE public.tb_year_area_code;

CREATE TABLE public.tb_year_area_code (
	code varchar(20) NULL,
	group_cd varchar(20) NULL,
	sido varchar(20) NULL,
	sigungu varchar(20) NULL,
	code_level varchar(20) NULL,
	short_name varchar(20) NULL
);

-- Drop table

-- DROP TABLE public.tb_year_dme;

CREATE TABLE public.tb_year_dme (
	damage_code varchar(10) NOT NULL,
	sido_code varchar(10) NOT NULL,
	sigungu_code varchar(20) NOT NULL,
	sido varchar(10) NULL,
	sigungu varchar(20) NULL,
	beg_year varchar(30) NOT NULL,
	beg_month varchar(30) NOT NULL,
	beg_day varchar(30) NOT NULL,
	end_year varchar(30) NOT NULL,
	end_month varchar(30) NOT NULL,
	end_day varchar(30) NOT NULL,
	cov_1 varchar(30) NULL,
	cov_2 varchar(30) NULL,
	cov_3 varchar(30) NULL,
	cov_4 varchar(30) NULL,
	cov_5 varchar(30) NULL,
	cov_dme_1 varchar(30) NULL,
	cov_dme_2 varchar(30) NULL,
	com_1 varchar(30) NULL,
	com_2 varchar(30) NULL,
	com_3 varchar(30) NULL,
	com_4 varchar(30) NULL,
	com_dme varchar(30) NULL,
	coc_1 varchar(30) NULL,
	coc_2 varchar(30) NULL,
	coc_3 varchar(30) NULL,
	coc_4 varchar(30) NULL,
	coc_5 varchar(30) NULL,
	coc_dme varchar(30) NULL,
	cob_1 varchar(30) NULL,
	cob_2 varchar(30) NULL,
	cob_3 varchar(30) NULL,
	cob_4 varchar(30) NULL,
	cob_5 varchar(30) NULL,
	cob_6 varchar(30) NULL,
	cob_7 varchar(30) NULL,
	cob_dme varchar(30) NULL,
	cob_tot varchar(30) NULL,
	cos_1 varchar(30) NULL,
	cos_2 varchar(30) NULL,
	cos_3 varchar(30) NULL,
	cos_4 varchar(30) NULL,
	cos_5 varchar(30) NULL,
	cos_6 varchar(30) NULL,
	cos_7 varchar(30) NULL,
	cos_8 varchar(30) NULL,
	cos_dme_1 varchar(30) NULL,
	cos_dme_2 varchar(30) NULL,
	cos_tot varchar(30) NULL,
	cof_1 varchar(30) NULL,
	cof_2 varchar(30) NULL,
	cof_dme varchar(30) NULL,
	cof_tot varchar(30) NULL,
	cow_1 varchar(30) NULL,
	cow_2 varchar(30) NULL,
	cow_3 varchar(30) NULL,
	cow_dme varchar(30) NULL,
	cow_tot varchar(30) NULL,
	pba_1 varchar(30) NULL,
	pba_2 varchar(30) NULL,
	pba_3 varchar(30) NULL,
	pba_4 varchar(30) NULL,
	pba_tot varchar(30) NULL,
	pbb_1 varchar(30) NULL,
	pbb_2 varchar(30) NULL,
	pbb_tot varchar(30) NULL,
	pbr_1 varchar(30) NULL,
	pbr_2 varchar(30) NULL,
	pbr_tot varchar(30) NULL,
	pbc_1 varchar(30) NULL,
	pbc_2 varchar(30) NULL,
	pbc_tot varchar(30) NULL,
	pbd_1 varchar(30) NULL,
	pbd_tot varchar(30) NULL,
	pbf_1 varchar(30) NULL,
	pbf_tot varchar(30) NULL,
	pbg_1 varchar(30) NULL,
	pbg_tot varchar(30) NULL,
	pbh_1 varchar(30) NULL,
	pbh_2 varchar(30) NULL,
	pbh_tot varchar(30) NULL,
	pbi_1 varchar(30) NULL,
	pbi_2 varchar(30) NULL,
	pbi_tot varchar(30) NULL,
	pbj_1 varchar(30) NULL,
	pbj_2 varchar(30) NULL,
	pbj_3 varchar(30) NULL,
	pbj_tot varchar(30) NULL,
	pbk_1 varchar(30) NULL,
	pbk_2 varchar(30) NULL,
	pbk_3 varchar(30) NULL,
	pbk_4 varchar(30) NULL,
	pbk_5 varchar(30) NULL,
	pbk_6 varchar(30) NULL,
	pbk_7 varchar(30) NULL,
	pbk_8 varchar(30) NULL,
	pbk_9 varchar(30) NULL,
	pbk_tot varchar(30) NULL,
	pbl_1 varchar(30) NULL,
	pbl_tot varchar(30) NULL,
	pbm_1 varchar(30) NULL,
	pbm_tot varchar(30) NULL,
	pbn_1 varchar(30) NULL,
	pbn_tot varchar(30) NULL,
	pbo_1 varchar(30) NULL,
	pbo_tot varchar(30) NULL,
	pra_1 varchar(30) NULL,
	pra_tot varchar(30) NULL,
	prb_1 varchar(30) NULL,
	prb_tot varchar(30) NULL,
	prc_1 varchar(30) NULL,
	prc_tot varchar(30) NULL,
	prd_1 varchar(30) NULL,
	prd_tot varchar(30) NULL,
	pre_1 varchar(30) NULL,
	pre_tot varchar(30) NULL,
	prf_1 varchar(30) NULL,
	prf_tot varchar(30) NULL,
	prg_1 varchar(30) NULL,
	prg_tot varchar(30) NULL,
	etc_1 varchar(30) NULL,
	etc_2 varchar(30) NULL,
	etc_3 varchar(30) NULL,
	etc_tot varchar(30) NULL,
	seq numeric NOT NULL,
	damage_info varchar(50) NULL,
	CONSTRAINT tb_year_dme_pk PRIMARY KEY (seq)
);
CREATE INDEX tb_year_dme_damage_date_code_sido_code_sigungu_code_beg_yea_idx ON public.tb_year_dme USING btree (damage_code, sido_code, sigungu_code, beg_year, beg_month, beg_day, end_year, end_month, end_day);

-- Drop table

-- DROP TABLE public.tb_year_dme_cause;

CREATE TABLE public.tb_year_dme_cause (
	beg_year varchar(30) NULL,
	beg_month varchar(30) NULL,
	beg_day varchar(30) NULL,
	sido varchar(10) NULL,
	sigungu varchar(20) NULL,
	emd_addr varchar(300) NULL,
	sigungu_code varchar(20) NULL,
	damage_cause varchar(1000) NULL,
	damage_result varchar(10) NULL,
	age_sperate varchar(20) NULL,
	inform_nm varchar(20) NULL,
	gender varchar(10) NULL,
	com_1 varchar(30) NULL,
	com2 varchar(30) NULL,
	com_dme varchar(30) NULL,
	woe_1 varchar(30) NULL,
	woe_2 varchar(30) NULL,
	woe_3 varchar(30) NULL,
	woe_4 varchar(30) NULL,
	woe_5 varchar(30) NULL,
	woe_6 varchar(30) NULL,
	woe_7 varchar(30) NULL,
	woe_8 varchar(30) NULL,
	woe_9 varchar(30) NULL,
	woe_10 varchar(30) NULL,
	woe_11 varchar(30) NULL,
	woe_12 varchar(30) NULL,
	etc_note varchar(1000) NULL
);

-- Drop table

-- DROP TABLE public.tb_year_dme_code;

CREATE TABLE public.tb_year_dme_code (
	code varchar(10) NOT NULL,
	code_name varchar(30) NULL,
	group_code varchar(10) NULL,
	dis_category varchar(30) NULL,
	CONSTRAINT tb_year_dme_code_pkey PRIMARY KEY (code)
);

-- Drop table

-- DROP TABLE public.tb_year_dme_code_grp;

CREATE TABLE public.tb_year_dme_code_grp (
	group_code varchar(10) NOT NULL,
	code_name varchar(30) NULL,
	CONSTRAINT tb_year_dme_code_grp_pkey PRIMARY KEY (group_code)
);

-- Drop table

-- DROP TABLE public.tb_year_dme_condition;

CREATE TABLE public.tb_year_dme_condition (
	seq numeric NULL,
	beg_month varchar(3) NULL,
	beg_day varchar(3) NULL,
	end_year varchar(4) NULL,
	end_month varchar(3) NULL,
	end_day varchar(3) NULL,
	damage_type varchar(8) NULL,
	description varchar(2000) NULL,
	damages varchar(2000) NULL,
	causes varchar(2000) NULL,
	beg_year varchar(4) NULL
);

-- Drop table

-- DROP TABLE public.tb_year_dme_images;

CREATE TABLE public.tb_year_dme_images (
	seq int4 NOT NULL,
	beg_date varchar(10) NULL,
	end_date varchar(10) NULL,
	file_path varchar(100) NULL,
	file_name varchar(50) NULL,
	regist_user varchar(10) NULL,
	regist_dt time NULL
);

-- Drop table

-- DROP TABLE public.tb_year_dme_sido_monthly;

CREATE TABLE public.tb_year_dme_sido_monthly (
	sido_code varchar(10) NOT NULL,
	damage_code varchar(10) NOT NULL,
	damage_month varchar(2) NULL,
	damage_money varchar(20) NULL,
	damage_name varchar(30) NULL
);

-- Drop table

-- DROP TABLE public.tb_year_dme_statics;

CREATE TABLE public.tb_year_dme_statics (
	beg_date varchar(30) NULL,
	end_date varchar(30) NULL,
	com_dme varchar(30) NULL,
	com_total varchar(50) NULL,
	pub_total varchar(50) NULL,
	pri_total varchar(50) NULL,
	total_damage varchar(50) NULL,
	stn_id varchar(30) NULL,
	rn_day varchar(30) NULL,
	rn_day_max varchar(30) NULL,
	rn_60m_max varchar(30) NULL
);

-- Drop table

-- DROP TABLE public.tb_year_dme_summary;

CREATE TABLE public.tb_year_dme_summary (
	sigungu varchar(10) NOT NULL,
	damage_code varchar(10) NOT NULL,
	beg_date varchar(8) NOT NULL,
	end_date varchar(8) NOT NULL,
	person_dmage numeric NULL,
	com_damage numeric NULL,
	pri_damage numeric NULL,
	public_damage numeric NULL,
	rain_day numeric NULL,
	rain_60 numeric NULL,
	rain_10 numeric NULL,
	seq numeric NOT NULL,
	sido_yn varchar(1) NULL,
	damage_info varchar(100) NULL
);

-- Drop table

-- DROP TABLE public.tb_year_dme_typhoon;

CREATE TABLE public.tb_year_dme_typhoon (
	seq int4 NOT NULL,
	beg_date varchar(10) NULL,
	end_date varchar(10) NULL,
	typ_name varchar(10) NULL,
	impact_yn bpchar(1) NULL,
	CONSTRAINT tb_year_dme_typhoon_pk PRIMARY KEY (seq)
);

-- Drop table

-- DROP TABLE public.tbo_dmi_typhoonforecast;

CREATE TABLE public.tbo_dmi_typhoonforecast (
	idx numeric(22) NULL,
	obs_id varchar(10) NULL,
	typhoon_nm varchar(100) NULL,
	kt numeric(22,7) NULL,
	obs_time timestamp NULL,
	lon numeric(22,12) NULL,
	lat numeric(22,12) NULL,
	data_time timestamp NULL,
	dt_regt timestamp NULL,
	esb_state varchar(1) NULL,
	esb_prcss_dttm varchar(30) NULL,
	esb_msg_id varchar(256) NULL,
	esb_err_log varchar(300) NULL,
	esb_tran_id varchar(50) NULL
);

-- Drop table

-- DROP TABLE public.tbo_dmi_typhoonhistory;

CREATE TABLE public.tbo_dmi_typhoonhistory (
	idx numeric(22) NULL,
	obs_id varchar(20) NULL,
	typhoon_nm varchar(100) NULL,
	kt varchar(5) NULL,
	obs_time timestamp NULL,
	lon numeric(22,12) NULL,
	lat numeric(22,12) NULL,
	data_time timestamp NULL,
	dt_regt timestamp NULL,
	esb_state bpchar(1) NULL,
	esb_prcss_dttm varchar(30) NULL,
	esb_msg_id varchar(256) NULL,
	esb_err_log varchar(300) NULL,
	esb_tran_id varchar(50) NULL
);

-- Drop table

-- DROP TABLE public.tc_adm;

CREATE TABLE public.tc_adm (
	adm_code varchar(10) NOT NULL,
	adm_sido varchar(50) NULL,
	adm_sigungu varchar(50) NULL,
	adm_dong varchar(50) NULL,
	code_create_day varchar(10) NULL,
	code_delete_day varchar(10) NULL,
	CONSTRAINT tc_adm_pkey PRIMARY KEY (adm_code)
);
CREATE INDEX tc_adm_idx_salary ON public.tc_adm USING btree (adm_code, adm_sido);

-- Drop table

-- DROP TABLE public.tc_code_grp;

CREATE TABLE public.tc_code_grp (
	grp_code varchar(8) NOT NULL,
	grp_title varchar(40) NULL,
	update_date timestamp NULL,
	create_date timestamp NULL,
	update_user_id varchar(256) NULL,
	create_user_id varchar(256) NULL,
	use_yn bpchar(1) NULL
);

-- Drop table

-- DROP TABLE public.tc_code_sys;

CREATE TABLE public.tc_code_sys (
	sys_code varchar(8) NOT NULL,
	sys_title varchar(40) NULL,
	grp_code varchar(8) NOT NULL,
	update_date timestamp NULL,
	create_date timestamp NULL,
	update_user_id varchar(256) NULL,
	create_user_id varchar(256) NULL,
	use_yn bpchar(1) NULL
);

-- Drop table

-- DROP TABLE public.tc_dis_category;

CREATE TABLE public.tc_dis_category (
	ctg_id varchar(30) NOT NULL,
	title varchar(100) NOT NULL,
	contents varchar(100) NULL,
	"depth" int4 NOT NULL,
	sort int4 NOT NULL,
	parent_ctg_id varchar(30) NULL,
	parent_ctg_ids varchar(100) NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL,
	print_id varchar(20) NULL,
	ctg_group varchar(20) NULL,
	CONSTRAINT tc_dis_category_pk_ctg_id PRIMARY KEY (ctg_id)
);

-- Drop table

-- DROP TABLE public.tc_dis_org;

CREATE TABLE public.tc_dis_org (
	org_code varchar(2) NOT NULL,
	title varchar(50) NOT NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL
);

-- Drop table

-- DROP TABLE public.tc_kma_loc;

CREATE TABLE public.tc_kma_loc (
	loc_code varchar(20) NULL,
	loc_name varchar(50) NULL,
	adm_code varchar(200) NULL,
	loc_type int4 NULL,
	short_name varchar(20) NULL
);

-- Drop table

-- DROP TABLE public.tc_law;

CREATE TABLE public.tc_law (
	law_code varchar(10) NOT NULL,
	law_sido varchar(50) NULL,
	law_sigungu varchar(50) NULL,
	law_dong varchar(50) NULL,
	law_ri varchar(50) NULL,
	code_create_day varchar(10) NULL,
	code_delete_day varchar(10) NULL,
	CONSTRAINT tc_law_pk PRIMARY KEY (law_code)
);
CREATE INDEX tc_law_idx_salary ON public.tc_law USING btree (law_code, law_sido);

-- Drop table

-- DROP TABLE public.tc_role;

CREATE TABLE public.tc_role (
	role_id varchar(8) NOT NULL,
	role_title varchar(20) NULL
);

-- Drop table

-- DROP TABLE public.th_bbs_log;

CREATE TABLE public.th_bbs_log (
	bbs_log_seq numeric NOT NULL,
	bbs_seq numeric NULL,
	log_target_code bpchar(18) NULL,
	create_user_id varchar(256) NULL,
	create_date timestamp NULL
);

-- Drop table

-- DROP TABLE public.th_dis_search;

CREATE TABLE public.th_dis_search (
	category_id varchar(20) NOT NULL,
	create_date timestamp NULL,
	session_id varchar(265) NULL
);

-- Drop table

-- DROP TABLE public.th_login_log;

CREATE TABLE public.th_login_log (
	user_id varchar(256) NOT NULL,
	create_date timestamp NOT NULL,
	session_id varchar(256) NOT NULL,
	update_date timestamp NULL,
	hit_count numeric NULL
);

-- Drop table

-- DROP TABLE public.th_menu_log;

CREATE TABLE public.th_menu_log (
	menu_id varchar(20) NOT NULL,
	sessid varchar(100) NOT NULL,
	create_date timestamp NOT NULL,
	conn_day varchar(8) NOT NULL,
	dur_time varchar(20) NULL,
	hit_count numeric NULL,
	device varchar(200) NULL,
	ip_addr varchar(20) NULL,
	mobile_yn bpchar(1) NULL
);

-- Drop table

-- DROP TABLE public.tm_bbs;

CREATE TABLE public.tm_bbs (
	bbs_id varchar(8) NOT NULL,
	bbs_title varchar(100) NULL,
	bbs_list_view varchar(20) NULL,
	bbs_write_view varchar(20) NULL,
	bbs_update_view varchar(20) NULL,
	bbs_pub_yn bpchar(1) NULL,
	bbs_read_view varchar(20) NULL,
	read_role_id varchar(100) NULL,
	write_role_id varchar(100) NULL,
	bbs_img_path bpchar(18) NULL,
	bbs_info_html varchar(1000) NULL,
	update_date date NULL,
	create_date date NULL,
	update_user_id varchar(256) NULL,
	create_user_id varchar(256) NULL,
	use_yn bpchar(1) NULL
);

-- Drop table

-- DROP TABLE public.tm_dis_event;

CREATE TABLE public.tm_dis_event (
	ctg_id varchar(20) NOT NULL,
	evt_id varchar(20) NOT NULL,
	title varchar(100) NULL,
	evt_date varchar(12) NULL,
	evt_num int4 NOT NULL,
	sort int4 NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL,
	evt_group varchar(20) NULL,
	com_1 varchar(10) NULL,
	com_2 varchar(10) NULL,
	com_3 varchar(10) NULL,
	latitude varchar(20) NULL,
	longitude varchar(20) NULL,
	area_codes varchar(200) NULL,
	is_root_yn varchar(1) NULL,
	is_cs_yn varchar(1) NULL,
	dis_level_code varchar(20) NULL
);

-- Drop table

-- DROP TABLE public.tm_dis_events;

CREATE TABLE public.tm_dis_events (
	ctg_id varchar(20) NOT NULL,
	evt_id varchar(20) NOT NULL,
	title varchar(100) NULL,
	evt_date varchar(12) NULL,
	evt_num int4 NOT NULL,
	sort int4 NULL,
	create_date date NULL,
	update_date date NULL,
	create_user_id varchar(256) NULL,
	update_user_id varchar(256) NULL,
	use_yn varchar(1) NOT NULL,
	evt_group varchar(20) NULL
);

-- Drop table

-- DROP TABLE public.tm_menu;

CREATE TABLE public.tm_menu (
	menu_id varchar(8) NOT NULL,
	menu_title varchar(40) NULL,
	menu_group varchar(8) NULL,
	menu_level numeric NULL,
	menu_sort numeric NULL,
	menu_uri varchar(100) NULL,
	menu_type_code varchar(20) NULL,
	menu_css varchar(20) NULL,
	menu_img varchar(20) NULL,
	update_date timestamp NULL,
	create_date timestamp NULL,
	update_user_id varchar(256) NULL,
	create_user_id varchar(256) NULL,
	use_yn bpchar(1) NULL
);

-- Drop table

-- DROP TABLE public.tob_kma_inform;

CREATE TABLE public.tob_kma_inform (
	cd_stn numeric(22) NULL,
	dt_tm_fc varchar(14) NULL,
	no_tm_seq numeric(22) NULL,
	nm_man_fc varchar(20) NULL,
	cd_war_fc bpchar(1) NULL,
	etc_ttl varchar(250) NULL,
	sect_area_txt varchar(4000) NULL,
	dt_tm_ef_txt varchar(4000) NULL,
	stat_ctnt varchar(4000) NULL,
	dt_war_tm timestamp NULL,
	stat_tm_ef varchar(4000) NULL,
	stat_pw_vl varchar(4000) NULL,
	etc_ref varchar(1000) NULL,
	dt_regt timestamp NULL,
	esb_state varchar(1) NULL,
	esb_prcss_dttm varchar(30) NULL,
	esb_msg_id varchar(256) NULL,
	esb_err_log varchar(250) NULL,
	esb_tran_id varchar(50) NULL
);

-- Drop table

-- DROP TABLE public.tob_kma_typhoon;

CREATE TABLE public.tob_kma_typhoon (
	cd_stn numeric(22,5) NOT NULL,
	dt_tm_fc varchar(14) NOT NULL,
	no_seq numeric(22,3) NOT NULL,
	no_tm_seq numeric(22,3) NOT NULL,
	nm_kor varchar(20) NULL,
	nm_eng varchar(20) NULL,
	nm_man_fc varchar(20) NULL,
	dt_typ_tm varchar(14) NULL,
	loc_lati varchar(10) NULL,
	loc_lngi varchar(10) NULL,
	loc_ctnt varchar(100) NULL,
	cus_dir varchar(3) NULL,
	wv_sp numeric(6,1) NULL,
	at_ps numeric(6,1) NULL,
	wv_ws numeric(6,1) NULL,
	cat_lvl varchar(3) NULL,
	loc_25rad numeric(6,1) NULL,
	cus_25ex_dir varchar(3) NULL,
	loc_25ex_rad numeric(6,1) NULL,
	loc_15rad numeric(6,1) NULL,
	cus_15ex_dir varchar(5) NULL,
	loc_15ex_rad numeric(6,1) NULL,
	dt_est1 varchar(14) NULL,
	loc_est_lati1 varchar(10) NULL,
	loc_est_lngi1 varchar(10) NULL,
	loc_est_loc1 varchar(100) NULL,
	at_est_ps1 numeric(6,1) NULL,
	wv_est_ws1 numeric(6,1) NULL,
	loc_est_rad1 numeric(6,1) NULL,
	loc_est_15rad1 numeric(6,1) NULL,
	cus_est_15ex_dir1 varchar(3) NULL,
	loc_est_15ex_rad1 numeric(6,1) NULL,
	cus_est_dir1 varchar(3) NULL,
	wv_est_sp1 numeric(6,1) NULL,
	dt_est2 varchar(14) NULL,
	loc_est_lati2 varchar(10) NULL,
	loc_est_lngi2 varchar(10) NULL,
	loc_est_loc2 varchar(100) NULL,
	at_est_ps2 numeric(6,1) NULL,
	wv_est_ws2 numeric(6,1) NULL,
	loc_est_rad2 numeric(6,1) NULL,
	loc_est_15rad2 numeric(6,1) NULL,
	cus_est_15ex_dir2 varchar(3) NULL,
	loc_est_15ex_rad2 numeric(6,1) NULL,
	cus_est_dir2 varchar(3) NULL,
	wv_est_sp2 numeric(6,1) NULL,
	dt_est3 varchar(14) NULL,
	loc_est_lati3 varchar(10) NULL,
	loc_est_lngi3 varchar(10) NULL,
	loc_est_loc3 varchar(100) NULL,
	at_est_ps3 numeric(6,1) NULL,
	wv_est_ws3 numeric(6,1) NULL,
	loc_est_rad3 numeric(6,1) NULL,
	loc_est_15rad3 numeric(6,1) NULL,
	cus_est_15ex_dir3 varchar(3) NULL,
	loc_est_15ex_rad3 numeric(6,1) NULL,
	cus_est_dir3 varchar(3) NULL,
	wv_est_sp3 numeric(6,1) NULL,
	dt_est4 varchar(14) NULL,
	loc_est_lati4 varchar(10) NULL,
	loc_est_lngi4 varchar(10) NULL,
	loc_est_loc4 varchar(100) NULL,
	at_est_ps4 numeric(6,1) NULL,
	wv_est_ws4 numeric(6,1) NULL,
	loc_est_rad4 numeric(6,1) NULL,
	loc_est_15rad4 numeric(6,1) NULL,
	cus_est_15ex_dir4 varchar(3) NULL,
	loc_est_15ex_rad4 numeric(6,1) NULL,
	cus_est_dir4 varchar(3) NULL,
	wv_est_sp4 numeric(6,1) NULL,
	dt_est5 varchar(14) NULL,
	loc_est_lati5 varchar(10) NULL,
	loc_est_lngi5 varchar(10) NULL,
	loc_est_loc5 varchar(100) NULL,
	at_est_ps5 numeric(6,1) NULL,
	wv_est_ws5 numeric(6,1) NULL,
	loc_est_rad5 numeric(6,1) NULL,
	loc_est_15rad5 numeric(6,1) NULL,
	cus_est_15ex_dir5 varchar(3) NULL,
	loc_est_15ex_rad5 numeric(6,1) NULL,
	cus_est_dir5 varchar(3) NULL,
	wv_est_sp5 numeric(6,1) NULL,
	stat_bndy varchar(1000) NULL,
	dt_war_tm varchar(14) NULL,
	stat_tm_ef varchar(1000) NULL,
	stat_tp_vl varchar(1000) NULL,
	etc_ref varchar(1000) NULL,
	dt_regt varchar(14) NULL,
	no_regt_seq numeric(10) NULL,
	CONSTRAINT tob_kma_typhoon_pk PRIMARY KEY (cd_stn, dt_tm_fc, no_seq, no_tm_seq)
);

-- Drop table

-- DROP TABLE public.tob_kma_typhoon_5ds;

CREATE TABLE public.tob_kma_typhoon_5ds (
	stn_id numeric(22,5) NOT NULL,
	tm_fc varchar(12) NOT NULL,
	typ_seq numeric(22,3) NOT NULL,
	tm_seq numeric(22,3) NOT NULL,
	typ_name varchar(20) NULL,
	typ_en varchar(20) NULL,
	man_fc varchar(20) NULL,
	typ_tm varchar(12) NULL,
	typ_lat numeric(22,5) NULL,
	typ_lon numeric(22,5) NULL,
	typ_loc varchar(100) NULL,
	typ_dir varchar(3) NULL,
	typ_sp numeric(22,5) NULL,
	typ_ps numeric(22,5) NULL,
	typ_ws numeric(22,5) NULL,
	typ_tp varchar(3) NULL,
	typ_25 numeric(22,5) NULL,
	typ_25ed varchar(3) NULL,
	typ_25er numeric(22,5) NULL,
	typ_15 numeric(22,5) NULL,
	typ_15ed varchar(5) NULL,
	typ_15er numeric(22,5) NULL,
	ft1_tm varchar(12) NULL,
	ft1_lat numeric(22,5) NULL,
	ft1_lon numeric(22,5) NULL,
	ft1_loc varchar(100) NULL,
	ft1_ps numeric(22,5) NULL,
	ft1_ws numeric(22,5) NULL,
	ft1_rad numeric(22,5) NULL,
	ft1_15 numeric(22,5) NULL,
	ft1_15ed varchar(3) NULL,
	ft1_15er numeric(22,5) NULL,
	ft1_dir varchar(3) NULL,
	ft1_sp numeric(22,5) NULL,
	ft2_tm varchar(12) NULL,
	ft2_lat numeric(22,5) NULL,
	ft2_lon numeric(22,5) NULL,
	ft2_loc varchar(100) NULL,
	ft2_ps numeric(22,5) NULL,
	ft2_ws numeric(22,5) NULL,
	ft2_rad numeric(22,5) NULL,
	ft2_15 numeric(22,5) NULL,
	ft2_15ed varchar(3) NULL,
	ft2_15er numeric(22,5) NULL,
	ft2_dir varchar(3) NULL,
	ft2_sp numeric(22,5) NULL,
	ft3_tm varchar(12) NULL,
	ft3_lat numeric(22,5) NULL,
	ft3_lon numeric(22,5) NULL,
	ft3_loc varchar(100) NULL,
	ft3_ps numeric(22,5) NULL,
	ft3_ws numeric(22,5) NULL,
	ft3_rad numeric(22,5) NULL,
	ft3_15 numeric(22,5) NULL,
	ft3_15ed varchar(3) NULL,
	ft3_15er numeric(22,5) NULL,
	ft3_dir varchar(3) NULL,
	ft3_sp numeric(22,5) NULL,
	ft4_tm varchar(12) NULL,
	ft4_lat numeric(22,5) NULL,
	ft4_lon numeric(22,5) NULL,
	ft4_loc varchar(100) NULL,
	ft4_ps numeric(22,5) NULL,
	ft4_ws numeric(22,5) NULL,
	ft4_rad numeric(22,5) NULL,
	ft4_15 numeric(22,5) NULL,
	ft4_15ed varchar(3) NULL,
	ft4_15er numeric(22,5) NULL,
	ft4_dir varchar(3) NULL,
	ft4_sp numeric(22,5) NULL,
	ft5_tm varchar(12) NULL,
	ft5_lat numeric(22,5) NULL,
	ft5_lon numeric(22,5) NULL,
	ft5_loc varchar(100) NULL,
	ft5_ps numeric(22,5) NULL,
	ft5_ws numeric(22,5) NULL,
	ft5_rad numeric(22,5) NULL,
	ft5_15 numeric(22,5) NULL,
	ft5_15ed varchar(3) NULL,
	ft5_15er numeric(22,5) NULL,
	ft5_dir varchar(3) NULL,
	ft5_sp numeric(22,5) NULL,
	ft6_tm varchar(12) NULL,
	ft6_lat numeric(22,5) NULL,
	ft6_lon numeric(22,5) NULL,
	ft6_loc varchar(100) NULL,
	ft6_ps numeric(22,5) NULL,
	ft6_ws numeric(22,5) NULL,
	ft6_rad numeric(22,5) NULL,
	ft6_15 numeric(22,5) NULL,
	ft6_15ed varchar(3) NULL,
	ft6_15er numeric(22,5) NULL,
	ft6_dir varchar(3) NULL,
	ft6_sp numeric(22,5) NULL,
	ft7_tm varchar(12) NULL,
	ft7_lat numeric(22,5) NULL,
	ft7_lon numeric(22,5) NULL,
	ft7_loc varchar(100) NULL,
	ft7_ps numeric(22,5) NULL,
	ft7_ws numeric(22,5) NULL,
	ft7_rad numeric(22,5) NULL,
	ft7_15 numeric(22,5) NULL,
	ft7_15ed varchar(3) NULL,
	ft7_15er numeric(22,5) NULL,
	ft7_dir varchar(3) NULL,
	ft7_sp numeric(22,5) NULL,
	ft8_tm varchar(12) NULL,
	ft8_lat numeric(22,5) NULL,
	ft8_lon numeric(22,5) NULL,
	ft8_loc varchar(100) NULL,
	ft8_ps numeric(22,5) NULL,
	ft8_ws numeric(22,5) NULL,
	ft8_rad numeric(22,5) NULL,
	ft8_15 numeric(22,5) NULL,
	ft8_15ed varchar(3) NULL,
	ft8_15er numeric(22,5) NULL,
	ft8_dir varchar(3) NULL,
	ft8_sp numeric(22,5) NULL,
	ft9_tm varchar(12) NULL,
	ft9_lat numeric(22,5) NULL,
	ft9_lon numeric(22,5) NULL,
	ft9_loc varchar(100) NULL,
	ft9_ps numeric(22,5) NULL,
	ft9_ws numeric(22,5) NULL,
	ft9_rad numeric(22,5) NULL,
	ft9_15 numeric(22,5) NULL,
	ft9_15ed varchar(3) NULL,
	ft9_15er numeric(22,5) NULL,
	ft9_dir varchar(3) NULL,
	ft9_sp numeric(22,5) NULL,
	ft10_tm varchar(12) NULL,
	ft10_lat numeric(22,5) NULL,
	ft10_lon numeric(22,5) NULL,
	ft10_loc varchar(100) NULL,
	ft10_ps numeric(22,5) NULL,
	ft10_ws numeric(22,5) NULL,
	ft10_rad numeric(22,5) NULL,
	ft10_15 numeric(22,5) NULL,
	ft10_15ed varchar(3) NULL,
	ft10_15er numeric(22,5) NULL,
	ft10_dir varchar(3) NULL,
	ft10_sp numeric(22,5) NULL,
	typ_ref varchar(255) NULL,
	typ_rem varchar(255) NULL,
	dt_regt timestamp NULL,
	esb_state varchar(1) NULL,
	esb_prcss_dttm varchar(30) NULL,
	esb_msg_id varchar(256) NULL,
	esb_err_log varchar(250) NULL,
	esb_tran_id varchar(50) NULL,
	CONSTRAINT tob_kma_typhoon_5ds_pk PRIMARY KEY (stn_id, tm_fc, typ_seq, tm_seq)
);

-- Drop table

-- DROP TABLE public.ts_kma_gungu;

CREATE TABLE public.ts_kma_gungu (
	gid serial NOT NULL,
	objectid int4 NULL,
	join_count int4 NULL,
	target_fid int4 NULL,
	sig_cd varchar(5) NULL,
	sig_eng_nm varchar(40) NULL,
	sig_kor_nm varchar(40) NULL,
	ctprvn_cd varchar(2) NULL,
	ctp_eng_nm varchar(40) NULL,
	ctp_kor_nm varchar(40) NULL,
	shape_leng numeric NULL,
	shape_area numeric NULL,
	ccd varchar(4) NULL,
	cccd varchar(5) NULL,
	geom geometry(MULTIPOLYGON, 5181) NULL,
	CONSTRAINT ts_gungu_pkey PRIMARY KEY (gid)
);
CREATE INDEX ts_gungu_idx ON public.ts_kma_gungu USING gist (geom);

-- Drop table

-- DROP TABLE public.ts_kma_loc;

CREATE TABLE public.ts_kma_loc (
	gid int4 NOT NULL,
	objectid int8 NULL,
	zone_cd varchar(254) NULL,
	zone_nm varchar(50) NULL,
	shape_area numeric NULL,
	shape_len numeric NULL,
	geom geometry NULL,
	CONSTRAINT ts_kma_loc_pkey PRIMARY KEY (gid)
);
