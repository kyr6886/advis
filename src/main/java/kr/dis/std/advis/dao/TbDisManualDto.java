package kr.dis.std.advis.dao;

import java.io.Serializable;

public class TbDisManualDto implements Serializable {
	
	private int seq;
	private String use_yn;
	private String ctg_id;
	private String grp_code;
	private String sys_code;
	private String manual_title;
	private String manual_contents;
	
	private String sys_title;
	private String ctg_title;
	
	private int total_cnt;
	
	
	public int getTotal_cnt() {
		return total_cnt;
	}
	public void setTotal_cnt(int total_cnt) {
		this.total_cnt = total_cnt;
	}
	public String getCtg_title() {
		return ctg_title;
	}
	public void setCtg_title(String ctg_title) {
		this.ctg_title = ctg_title;
	}
	public String getSys_title() {
		return sys_title;
	}
	public void setSys_title(String sys_title) {
		this.sys_title = sys_title;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getCtg_id() {
		return ctg_id;
	}
	public void setCtg_id(String ctg_id) {
		this.ctg_id = ctg_id;
	}
	public String getGrp_code() {
		return grp_code;
	}
	public void setGrp_code(String grp_code) {
		this.grp_code = grp_code;
	}
	public String getSys_code() {
		return sys_code;
	}
	public void setSys_code(String sys_code) {
		this.sys_code = sys_code;
	}
	public String getManual_title() {
		return manual_title;
	}
	public void setManual_title(String manual_title) {
		this.manual_title = manual_title;
	}
	public String getManual_contents() {
		return manual_contents;
	}
	public void setManual_contents(String manual_contents) {
		this.manual_contents = manual_contents;
	}
	
	

}
