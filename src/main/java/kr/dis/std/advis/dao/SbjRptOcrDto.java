package kr.dis.std.advis.dao;

import com.noaa.base.BaseDTO;

public class SbjRptOcrDto extends BaseDTO  {
	private int sbj_seq;
	private int rpt_seq;
	private String sbj_nm;
	private String sbj_cont;
	public int getSbj_seq() {
		return sbj_seq;
	}
	public void setSbj_seq(int sbj_seq) {
		this.sbj_seq = sbj_seq;
	}
	public int getRpt_seq() {
		return rpt_seq;
	}
	public void setRpt_seq(int rpt_seq) {
		this.rpt_seq = rpt_seq;
	}
	public String getSbj_nm() {
		return sbj_nm;
	}
	public void setSbj_nm(String sbj_nm) {
		this.sbj_nm = sbj_nm;
	}
	public String getSbj_cont() {
		return sbj_cont;
	}
	public void setSbj_cont(String sbj_cont) {
		this.sbj_cont = sbj_cont;
	}
	
	
}
