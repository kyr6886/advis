package com.noaa.base.file.dao;

import com.noaa.base.BaseDTO;

public class DBInfoDTO extends BaseDTO {
	private String tableSpace;
	private double total_mb;
	private double used_mb;
	private double used;
	public String getTableSpace() {
		return tableSpace;
	}
	public void setTableSpace(String tableSpace) {
		this.tableSpace = tableSpace;
	}
	public double getTotal_mb() {
		return total_mb;
	}
	public void setTotal_mb(double total_mb) {
		this.total_mb = total_mb;
	}
	public double getUsed_mb() {
		return used_mb;
	}
	public void setUsed_mb(double used_mb) {
		this.used_mb = used_mb;
	}
	public double getUsed() {
		return used;
	}
	public void setUsed(double used) {
		this.used = used;
	}
	
	
}
