CREATE SEQUENCE tb_attach_file_seq START 1;
CREATE SEQUENCE tb_bbs_content_bbs_seq START 1;
CREATE SEQUENCE th_bbs_log_bbs_seq START 1;



CREATE TABLE "public"."ids"
(
   table_name varchar(20) NOT NULL,
   next_id numeric DEFAULT 0 NOT NULL
)
;
CREATE TABLE "public"."lettccmmnclcode"
(
   cl_code char(3) PRIMARY KEY NOT NULL,
   cl_code_nm varchar(60),
   cl_code_dc varchar(200),
   use_at char(1),
   frst_regist_pnttm date,
   frst_register_id varchar(20),
   last_updt_pnttm date,
   last_updusr_id varchar(20)
)
;
CREATE TABLE "public"."lettccmmncode"
(
   code_id varchar(6) PRIMARY KEY NOT NULL,
   code_id_nm varchar(60),
   code_id_dc varchar(200),
   use_at char(1),
   cl_code char(3),
   frst_regist_pnttm date,
   frst_register_id varchar(20),
   last_updt_pnttm date,
   last_updusr_id varchar(20)
)
;
CREATE TABLE "public"."lettccmmndetailcode"
(
   code_id varchar(6) NOT NULL,
   code varchar(15) NOT NULL,
   code_nm varchar(60),
   code_dc varchar(200),
   use_at char(1),
   frst_regist_pnttm date,
   frst_register_id varchar(20),
   last_updt_pnttm date,
   last_updusr_id varchar(20),
   CONSTRAINT lettccmmndetailcode_pk PRIMARY KEY (code_id,code)
)
;
CREATE TABLE "public"."lettczip"
(
   zip varchar(6) NOT NULL,
   sn numeric DEFAULT 0::numeric NOT NULL,
   ctprvn_nm varchar(20),
   signgu_nm varchar(20),
   emd_nm varchar(60),
   li_buld_nm varchar(60),
   lnbr_dong_ho varchar(20),
   frst_regist_pnttm date,
   frst_register_id varchar(20),
   last_updt_pnttm date,
   last_updusr_id varchar(20),
   CONSTRAINT lettczip_pk PRIMARY KEY (zip,sn)
)
;
CREATE TABLE "public"."lettnauthorgroupinfo"
(
   group_id char(20) PRIMARY KEY NOT NULL,
   group_nm varchar(60) NOT NULL,
   group_creat_de char(20) NOT NULL,
   group_dc varchar(100)
)
;
CREATE TABLE "public"."lettnauthorinfo"
(
   author_code varchar(30) PRIMARY KEY NOT NULL,
   author_nm varchar(60) NOT NULL,
   author_dc varchar(200),
   author_creat_de char(20) NOT NULL
)
;
CREATE TABLE "public"."lettnauthorrolerelate"
(
   author_code varchar(30) NOT NULL,
   role_code varchar(50) NOT NULL,
   creat_dt date,
   CONSTRAINT lettnauthorrolerelate_pk PRIMARY KEY (author_code,role_code)
)
;
CREATE TABLE "public"."lettnbanner"
(
   banner_id char(20) PRIMARY KEY NOT NULL,
   banner_nm varchar(60) NOT NULL,
   link_url varchar(255) NOT NULL,
   banner_image varchar(60) NOT NULL,
   banner_dc varchar(200),
   reflct_at char(1) NOT NULL,
   frst_register_id varchar(20),
   frst_regist_pnttm date,
   last_updusr_id varchar(20),
   last_updt_pnttm date,
   banner_image_file varchar(60),
   sort_ordr numeric
)
;
CREATE TABLE "public"."lettnbbs"
(
   ntt_id numeric NOT NULL,
   bbs_id char(20) NOT NULL,
   ntt_no numeric,
   ntt_sj varchar(2000),
   ntt_cn text,
   answer_at char(1),
   parntsctt_no numeric,
   answer_lc numeric,
   sort_ordr numeric,
   rdcnt numeric,
   use_at char(1) NOT NULL,
   ntce_bgnde char(20),
   ntce_endde char(20),
   ntcr_id varchar(20),
   ntcr_nm varchar(20),
   password varchar(200),
   atch_file_id char(20),
   frst_regist_pnttm date NOT NULL,
   frst_register_id varchar(20) NOT NULL,
   last_updt_pnttm date,
   last_updusr_id varchar(20),
   CONSTRAINT lettnbbs_pk PRIMARY KEY (ntt_id,bbs_id)
)
;
CREATE TABLE "public"."lettnbbsmaster"
(
   bbs_id char(20) PRIMARY KEY NOT NULL,
   bbs_nm varchar(255) NOT NULL,
   bbs_intrcn varchar(2400),
   bbs_ty_code char(6) NOT NULL,
   bbs_attrb_code char(6) NOT NULL,
   reply_posbl_at char(1),
   file_atch_posbl_at char(1) NOT NULL,
   atch_posbl_file_numeric numeric NOT NULL,
   atch_posbl_file_size numeric,
   use_at char(1) NOT NULL,
   tmplat_id char(20),
   frst_register_id varchar(20) NOT NULL,
   frst_regist_pnttm date NOT NULL,
   last_updusr_id varchar(20),
   last_updt_pnttm date
)
;
CREATE TABLE "public"."lettnbbsmasteroptn"
(
   bbs_id char(20) PRIMARY KEY NOT NULL,
   answer_at char(1) DEFAULT ''::bpchar NOT NULL,
   stsfdg_at char(1) DEFAULT ''::bpchar NOT NULL,
   frst_regist_pnttm date DEFAULT now() NOT NULL,
   last_updt_pnttm date,
   frst_register_id varchar(20) DEFAULT ''::character varying NOT NULL,
   last_updusr_id varchar(20)
)
;
CREATE TABLE "public"."lettnbbsuse"
(
   bbs_id char(20) NOT NULL,
   trget_id char(20) DEFAULT ''::bpchar NOT NULL,
   use_at char(1) NOT NULL,
   regist_se_code char(6),
   frst_regist_pnttm date,
   frst_register_id varchar(20) NOT NULL,
   last_updt_pnttm date,
   last_updusr_id varchar(20),
   CONSTRAINT lettnbbsuse_pk PRIMARY KEY (bbs_id,trget_id)
)
;
CREATE TABLE "public"."lettnemplyrinfo"
(
   emplyr_id varchar(20) PRIMARY KEY NOT NULL,
   orgnzt_id char(20),
   user_nm varchar(60) NOT NULL,
   password varchar(200) NOT NULL,
   empl_no varchar(20),
   ihidnum varchar(200),
   sexdstn_code char(1),
   brthdy char(20),
   fxnum varchar(20),
   house_adres varchar(100),
   password_hint varchar(100) NOT NULL,
   password_cnsr varchar(100) NOT NULL,
   house_end_telno varchar(4),
   area_no varchar(4),
   detail_adres varchar(100),
   zip varchar(6),
   offm_telno varchar(20),
   mbtlnum varchar(20),
   email_adres varchar(50),
   ofcps_nm varchar(60),
   house_middle_telno varchar(4),
   group_id char(20),
   pstinst_code char(8),
   emplyr_sttus_code varchar(15) NOT NULL,
   esntl_id char(20) NOT NULL,
   crtfc_dn_value varchar(20),
   sbscrb_de date
)
;
CREATE TABLE "public"."lettnemplyrscrtyestbs"
(
   scrty_dtrmn_trget_id varchar(20) PRIMARY KEY NOT NULL,
   mber_ty_code varchar(15),
   author_code varchar(30) NOT NULL
)
;
CREATE TABLE "public"."lettnentrprsmber"
(
   entrprs_mber_id varchar(20) PRIMARY KEY NOT NULL,
   entrprs_se_code char(15),
   bizrno varchar(10),
   jurirno varchar(13),
   cmpny_nm varchar(60) NOT NULL,
   cxfc varchar(50),
   zip varchar(6),
   adres varchar(100),
   entrprs_middle_telno varchar(4),
   fxnum varchar(20),
   induty_code char(15),
   applcnt_nm varchar(50),
   applcnt_ihidnum varchar(200),
   sbscrb_de date,
   entrprs_mber_sttus varchar(15),
   entrprs_mber_password varchar(200) NOT NULL,
   entrprs_mber_password_hint varchar(100) NOT NULL,
   entrprs_mber_password_cnsr varchar(100) NOT NULL,
   group_id char(20),
   detail_adres varchar(100),
   entrprs_end_telno varchar(4),
   area_no varchar(4),
   applcnt_email_adres varchar(50),
   esntl_id char(20) NOT NULL
)
;
CREATE TABLE "public"."lettnfaqinfo"
(
   faq_id char(20) PRIMARY KEY NOT NULL,
   qestn_sj varchar(255),
   qestn_cn varchar(2500),
   answer_cn varchar(2500),
   rdcnt numeric,
   frst_regist_pnttm date NOT NULL,
   frst_register_id varchar(20) NOT NULL,
   last_updt_pnttm date NOT NULL,
   last_updusr_id varchar(20) NOT NULL,
   atch_file_id char(20),
   qna_process_sttus_code char(1)
)
;
CREATE TABLE "public"."lettnfile"
(
   atch_file_id char(20) PRIMARY KEY NOT NULL,
   creat_dt date NOT NULL,
   use_at char(1)
)
;
CREATE TABLE "public"."lettnfiledetail"
(
   atch_file_id char(20) NOT NULL,
   file_sn numeric NOT NULL,
   file_stre_cours varchar(2000) NOT NULL,
   stre_file_nm varchar(255) NOT NULL,
   orignl_file_nm varchar(255),
   file_extsn varchar(20) NOT NULL,
   file_cn text,
   file_size numeric,
   CONSTRAINT lettnfiledetail_pk PRIMARY KEY (atch_file_id,file_sn)
)
;
CREATE TABLE "public"."lettngnrlmber"
(
   mber_id varchar(20) PRIMARY KEY NOT NULL,
   password varchar(200) NOT NULL,
   password_hint varchar(100) NOT NULL,
   password_cnsr varchar(100) NOT NULL,
   ihidnum varchar(200),
   mber_nm varchar(50) NOT NULL,
   zip varchar(6),
   adres varchar(100),
   area_no varchar(4),
   mber_sttus varchar(15),
   detail_adres varchar(100),
   end_telno varchar(4),
   mbtlnum varchar(20),
   group_id char(20),
   mber_fxnum varchar(20),
   mber_email_adres varchar(50),
   middle_telno varchar(4),
   sbscrb_de date,
   sexdstn_code char(1),
   esntl_id char(20) NOT NULL
)
;
CREATE TABLE "public"."lettnindvdlinfopolicy"
(
   indvdl_info_policy_id char(20) PRIMARY KEY NOT NULL,
   indvdl_info_policy_cn varchar(2500),
   indvdl_info_policy_agre_at char(1),
   frst_register_id varchar(20),
   frst_regist_pnttm date,
   last_updusr_id varchar(20),
   last_updt_pnttm date,
   indvdl_info_policy_nm varchar(255)
)
;
CREATE TABLE "public"."lettnmenucreatdtls"
(
   menu_no numeric NOT NULL,
   author_code varchar(30) NOT NULL,
   mapng_creat_id varchar(30),
   CONSTRAINT lettnmenucreatdtls_pk PRIMARY KEY (menu_no,author_code)
)
;
CREATE TABLE "public"."lettnmenuinfo"
(
   menu_nm varchar(60) NOT NULL,
   progrm_file_nm varchar(60) NOT NULL,
   menu_no numeric PRIMARY KEY NOT NULL,
   upper_menu_no numeric,
   menu_ordr numeric NOT NULL,
   menu_dc varchar(250),
   relate_image_path varchar(100),
   relate_image_nm varchar(60)
)
;
CREATE TABLE "public"."lettnorgnztinfo"
(
   orgnzt_id char(20) PRIMARY KEY NOT NULL,
   orgnzt_nm varchar(20) NOT NULL,
   orgnzt_dc varchar(100)
)
;
CREATE TABLE "public"."lettnprogrmlist"
(
   progrm_file_nm varchar(60) PRIMARY KEY NOT NULL,
   progrm_stre_path varchar(100) NOT NULL,
   progrm_korean_nm varchar(60),
   progrm_dc varchar(200),
   url varchar(100) NOT NULL
)
;
CREATE TABLE "public"."lettnqainfo"
(
   qa_id char(20) PRIMARY KEY NOT NULL,
   qestn_sj varchar(255),
   qestn_cn varchar(2500),
   writng_de char(20),
   rdcnt numeric,
   email_adres varchar(50),
   frst_regist_pnttm date,
   frst_register_id varchar(20),
   last_updt_pnttm date,
   last_updusr_id varchar(20),
   qna_process_sttus_code char(1),
   wrter_nm varchar(20),
   answer_cn varchar(2500),
   writng_password varchar(20),
   answer_de char(20),
   email_answer_at char(1),
   area_no varchar(4),
   middle_telno varchar(4),
   end_telno varchar(4)
)
;
CREATE TABLE "public"."lettnqustnriem"
(
   qustnr_tmplat_id char(20) NOT NULL,
   qestnr_id char(20) NOT NULL,
   qustnr_qesitm_id char(20) NOT NULL,
   qustnr_iem_id varchar(20) NOT NULL,
   iem_sn numeric,
   iem_cn varchar(1000),
   etc_answer_at char(1),
   frst_regist_pnttm date,
   frst_register_id varchar(20),
   last_updt_pnttm date,
   last_updusr_id varchar(20),
   CONSTRAINT lettnqustnriem_pk PRIMARY KEY (qustnr_tmplat_id,qestnr_id,qustnr_qesitm_id,qustnr_iem_id)
)
;
CREATE TABLE "public"."lettnqustnrqesitm"
(
   qestnr_id char(20) NOT NULL,
   qustnr_qesitm_id char(20) NOT NULL,
   qustnr_tmplat_id char(20) NOT NULL,
   qestn_sn numeric,
   qestn_ty_code char(1),
   qestn_cn varchar(2500),
   mxmm_choise_co numeric,
   frst_regist_pnttm date NOT NULL,
   frst_register_id varchar(20) NOT NULL,
   last_updt_pnttm date NOT NULL,
   last_updusr_id varchar(20) NOT NULL,
   CONSTRAINT lettnqustnrqesitm_pk PRIMARY KEY (qestnr_id,qustnr_qesitm_id,qustnr_tmplat_id)
)
;
CREATE TABLE "public"."lettnqustnrrespondinfo"
(
   qustnr_tmplat_id char(20) NOT NULL,
   qestnr_id char(20) NOT NULL,
   qustnr_respond_id char(20) NOT NULL,
   sexdstn_code char(1),
   occp_ty_code varchar(1),
   respond_nm varchar(50),
   brthdy char(20),
   area_no varchar(4),
   middle_telno varchar(4),
   end_telno varchar(4),
   frst_regist_pnttm date,
   frst_register_id varchar(20),
   last_updt_pnttm date,
   last_updusr_id varchar(20),
   CONSTRAINT lettnqustnrrespondinfo_pk PRIMARY KEY (qustnr_tmplat_id,qestnr_id,qustnr_respond_id)
)
;
CREATE TABLE "public"."lettnqustnrrspnsresult"
(
   qustnr_rspns_result_id char(20) NOT NULL,
   qestnr_id char(20) NOT NULL,
   qustnr_qesitm_id char(20) NOT NULL,
   qustnr_tmplat_id char(20) NOT NULL,
   respond_answer_cn varchar(1000),
   etc_answer_cn varchar(1000),
   respond_nm varchar(50),
   frst_regist_pnttm date,
   frst_register_id varchar(20),
   last_updt_pnttm date,
   last_updusr_id varchar(20),
   qustnr_iem_id varchar(20),
   CONSTRAINT lettnqustnrrspnsresult_pk PRIMARY KEY (qustnr_rspns_result_id,qestnr_id,qustnr_qesitm_id,qustnr_tmplat_id)
)
;
CREATE TABLE "public"."lettnqustnrtmplat"
(
   qustnr_tmplat_id char(20) PRIMARY KEY NOT NULL,
   qustnr_tmplat_ty varchar(100),
   qustnr_tmplat_dc varchar(2000),
   qustnr_tmplat_path_nm varchar(100),
   frst_regist_pnttm date,
   frst_register_id varchar(20),
   last_updt_pnttm date,
   last_updusr_id varchar(20),
   qustnr_tmplat_image_info text
)
;
CREATE TABLE "public"."lettnroleinfo"
(
   role_code varchar(50) PRIMARY KEY NOT NULL,
   role_nm varchar(60) NOT NULL,
   role_pttrn varchar(300),
   role_dc varchar(200),
   role_ty varchar(80),
   role_sort varchar(10),
   role_creat_de char(20) NOT NULL
)
;
CREATE TABLE "public"."lettnroles_hierarchy"
(
   parnts_role varchar(30) NOT NULL,
   chldrn_role varchar(30) NOT NULL,
   CONSTRAINT lettnroles_hierarchy_pk PRIMARY KEY (parnts_role,chldrn_role)
)
;
CREATE TABLE "public"."lettnstplatinfo"
(
   use_stplat_id char(20) PRIMARY KEY NOT NULL,
   use_stplat_nm varchar(100),
   use_stplat_cn text,
   info_provd_agre_cn text,
   frst_regist_pnttm date,
   frst_register_id varchar(20),
   last_updt_pnttm date,
   last_updusr_id varchar(20)
)
;
CREATE TABLE "public"."lettntmplatinfo"
(
   tmplat_id char(20) PRIMARY KEY NOT NULL,
   tmplat_nm varchar(255),
   tmplat_cours varchar(2000),
   use_at char(1),
   tmplat_se_code char(6),
   frst_register_id varchar(20),
   frst_regist_pnttm date,
   last_updusr_id varchar(20),
   last_updt_pnttm date
)
;
CREATE TABLE "public"."tb_attach_file"
(
   file_seq numeric PRIMARY KEY NOT NULL,
   file_grp_id varchar(100),
   file_org_name varchar(200),
   file_new_name varchar(100),
   file_path varchar(100),
   file_size numeric,
   file_ext varchar(10),
   create_date date,
   update_date date,
   update_user_id varchar(256),
   create_user_id varchar(256),
   use_yn char(1),
   file_title varchar(200)
)
;
CREATE TABLE "public"."tb_bbs_category"
(
   category_seq numeric PRIMARY KEY NOT NULL,
   category_title varchar(20),
   bbs_id varchar(8)
)
;
CREATE TABLE "public"."tb_bbs_content"
(
   bbs_seq numeric PRIMARY KEY NOT NULL,
   bbs_cont varchar(4000),
   bbs_group int,
   bbs_sort int,
   bbs_parent int,
   bbs_level int,
   file_grp_id varchar(250),
   bbs_cont_title varchar(200),
   bbs_read int,
   category_seq int,
   update_date date,
   create_date date,
   update_user_id varchar(256),
   create_user_id varchar(256),
   create_ip varchar(20),
   use_yn char(1),
   bbs_id varchar(8),
   fixed_yn varchar(1)
)
;
CREATE TABLE "public"."tb_menu_role"
(
   role_id varchar(8) NOT NULL,
   menu_id varchar(8) NOT NULL,
   write_yn varchar(1),
   CONSTRAINT PK_TB_MENU_ROLE PRIMARY KEY (role_id,menu_id)
)
;
CREATE TABLE "public"."tb_partner_site"
(
   site_url varchar(500) PRIMARY KEY NOT NULL,
   site_title varchar(500),
   file_grp_id varchar(100),
   use_yn char(1)
)
;
CREATE TABLE "public"."tb_user"
(
   user_id varchar(256) PRIMARY KEY NOT NULL,
   user_pwd varchar(256),
   user_name varchar(256),
   user_phone varchar(256),
   user_email varchar(256),
   user_type_code varchar(20),
   user_status_code varchar(20),
   update_date timestamp,
   create_date timestamp,
   create_user_id varchar(256),
   update_user_id varchar(256),
   use_yn char(1),
   user_key varchar(32),
   user_adm_code varchar(20),
   user_dept varchar(100),
   user_phone_office varchar(20),
   user_position varchar(100),
   user_work_cont varchar(200),
   user_major varchar(100),
   user_addr varchar(100),
   user_fail_count numeric
)
;
CREATE TABLE "public"."tb_user_msg"
(
   seq numeric PRIMARY KEY NOT NULL,
   user_id varchar(256),
   msg_content varchar(400),
   create_date date,
   create_user_id varchar(256),
   msg_type_code char(18)
)
;
CREATE TABLE "public"."tb_user_role"
(
   user_id varchar(256) NOT NULL,
   role_id varchar(8) NOT NULL,
   CONSTRAINT PK_TB_USER_ROLE PRIMARY KEY (user_id,role_id)
)
;
CREATE TABLE "public"."tc_adm"
(
   adm_code varchar(10) PRIMARY KEY NOT NULL,
   adm_sido varchar(50),
   adm_sigungu varchar(50),
   adm_dong varchar(50),
   code_create_day varchar(10),
   code_delete_day varchar(10)
)
;
CREATE TABLE "public"."tc_code_grp"
(
   grp_code varchar(8) NOT NULL,
   grp_title varchar(40),
   update_date timestamp,
   create_date timestamp,
   update_user_id varchar(256),
   create_user_id varchar(256),
   use_yn char(1)
)
;
CREATE TABLE "public"."tc_code_sys"
(
   sys_code varchar(8) NOT NULL,
   sys_title varchar(40),
   grp_code varchar(8) NOT NULL,
   update_date timestamp,
   create_date timestamp,
   update_user_id varchar(256),
   create_user_id varchar(256),
   use_yn char(1)
)
;
CREATE TABLE "public"."tc_role"
(
   role_id varchar(8) NOT NULL,
   role_title varchar(20)
)
;
CREATE TABLE "public"."th_bbs_log"
(
   bbs_log_seq numeric NOT NULL,
   bbs_seq numeric,
   log_target_code char(18),
   create_user_id varchar(256),
   create_date timestamp
)
;
CREATE TABLE "public"."th_login_log"
(
   user_id varchar(256) NOT NULL,
   create_date timestamp NOT NULL,
   session_id varchar(256) NOT NULL,
   update_date timestamp,
   hit_count numeric
)
;
CREATE TABLE "public"."th_menu_log"
(
   menu_id varchar(20) NOT NULL,
   sessid varchar(100) NOT NULL,
   create_date timestamp NOT NULL,
   conn_day varchar(8) NOT NULL,
   dur_time varchar(20),
   hit_count numeric,
   device varchar(200),
   ip_addr varchar(20),
   mobile_yn char(1)
)
;
CREATE TABLE "public"."tm_bbs"
(
   bbs_id varchar(8) NOT NULL,
   bbs_title varchar(100),
   bbs_list_view varchar(20),
   bbs_write_view varchar(20),
   bbs_update_view varchar(20),
   bbs_pub_yn char(1),
   bbs_read_view varchar(20),
   read_role_id varchar(100),
   write_role_id varchar(100),
   bbs_img_path char(18),
   bbs_info_html varchar(1000),
   update_date date,
   create_date date,
   update_user_id varchar(256),
   create_user_id varchar(256),
   use_yn char(1)
)
;
CREATE TABLE "public"."tm_menu"
(
   menu_id varchar(8) NOT NULL,
   menu_title varchar(40),
   menu_group varchar(8),
   menu_level numeric,
   menu_sort numeric,
   menu_uri varchar(100),
   menu_type_code varchar(20),
   menu_css varchar(20),
   menu_img varchar(20),
   update_date timestamp,
   create_date timestamp,
   update_user_id varchar(256),
   create_user_id varchar(256),
   use_yn char(1)
)
;
CREATE INDEX tc_adm_idx_salary ON "public"."tc_adm"
(
  adm_code,
  adm_sido
)
;
CREATE VIEW COMVNUSERMASTER AS
	SELECT ESNTL_ID, MBER_ID as USER_ID,PASSWORD,MBER_NM  as USER_NM,ZIP as USER_ZIP,ADRES as USER_ADRES,MBER_EMAIL_ADRES as USER_EMAIL,' ' AS GROUP_ID,'GNR' AS USER_SE, ' ' AS  ORGNZT_ID
        FROM LETTNGNRLMBER
    UNION ALL
        SELECT ESNTL_ID,EMPLYR_ID,PASSWORD,USER_NM,ZIP,HOUSE_ADRES,EMAIL_ADRES,GROUP_ID ,'USR' AS USER_SE, ORGNZT_ID
        FROM LETTNEMPLYRINFO
    UNION ALL
        SELECT ESNTL_ID,ENTRPRS_MBER_ID,ENTRPRS_MBER_PASSWORD,CMPNY_NM,ZIP,ADRES,APPLCNT_EMAIL_ADRES,' ' ,'ENT' AS USER_SE, ' ' ORGNZT_ID
        FROM LETTNENTRPRSMBER
;
