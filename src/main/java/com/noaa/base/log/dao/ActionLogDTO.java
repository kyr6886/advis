package com.noaa.base.log.dao;

import com.noaa.base.BaseDTO;

public class ActionLogDTO extends BaseDTO {
	private String tb_name;
    private String logCont;
    private String logTarget;
	public String getTb_name() {
		return tb_name;
	}
	public void setTb_name(String tb_name) {
		this.tb_name = tb_name;
	}
	public String getLogCont() {
		return logCont;
	}
	public void setLogCont(String logCont) {
		this.logCont = logCont;
	}
	public String getLogTarget() {
		return logTarget;
	}
	public void setLogTarget(String logTarget) {
		this.logTarget = logTarget;
	}
  
}
