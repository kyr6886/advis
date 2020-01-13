package com.noaa.base.menu.dao;

import com.noaa.base.BaseDTO;

public class MenuLogDTO extends BaseDTO{
	private String menu_id;
	private String menu_title;
	private String sessid;
	private String conn_day;
	private int dur_time;
	private float hit_count;
	private String device;
	private String isMobleYN;
	private String ip_addr;
	private int visitCount;
	
	public String getMenu_title() {
		return menu_title;
	}
	public void setMenu_title(String menu_title) {
		this.menu_title = menu_title;
	}
	public String getIsMobleYN() {
		return isMobleYN;
	}
	public void setIsMobleYN(String isMobleYN) {
		this.isMobleYN = isMobleYN;
	}
	public int getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getSessid() {
		return sessid;
	}
	public void setSessid(String sessid) {
		this.sessid = sessid;
	}
	public String getConn_day() {
		return conn_day;
	}
	public void setConn_day(String conn_day) {
		this.conn_day = conn_day;
	}
	public int getDur_time() {
		return dur_time;
	}
	public void setDur_time(int dur_time) {
		this.dur_time = dur_time;
	}
	public float getHit_count() {
		return hit_count;
	}
	public void setHit_count(float hit_count) {
		this.hit_count = hit_count;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	
}
