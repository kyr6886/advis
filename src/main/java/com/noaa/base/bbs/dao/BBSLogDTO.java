package com.noaa.base.bbs.dao;

import com.noaa.base.BaseDTO;

public class BBSLogDTO extends BaseDTO{
	private int bbs_seq;
	private String log_target_code;
	public int getBbs_seq() {
		return bbs_seq;
	}
	public void setBbs_seq(int bbs_seq) {
		this.bbs_seq = bbs_seq;
	}
	public String getLog_target_code() {
		return log_target_code;
	}
	public void setLog_target_code(String log_target_code) {
		this.log_target_code = log_target_code;
	}
	
}
