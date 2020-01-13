package kr.dis.std.event.dao;

import com.noaa.base.BaseDTO;

public class EventLocationDto extends BaseDTO{
	private String dme_type;
	 private String  evt_date;
	 private String evt_time;
	 private String evt_lon;
	 private String evt_lat;
	 private String evt_power;
	 private String evt_z;
	 private String contents;
	 private String evt_group;
	 private String addr;
	public String getDme_type() {
		return dme_type;
	}
	public void setDme_type(String dme_type) {
		this.dme_type = dme_type;
	}
	public String getEvt_date() {
		return evt_date;
	}
	public void setEvt_date(String evt_date) {
		this.evt_date = evt_date;
	}
	public String getEvt_time() {
		return evt_time;
	}
	public void setEvt_time(String evt_time) {
		this.evt_time = evt_time;
	}
	public String getEvt_lon() {
		return evt_lon;
	}
	public void setEvt_lon(String evt_lon) {
		this.evt_lon = evt_lon;
	}
	public String getEvt_lat() {
		return evt_lat;
	}
	public void setEvt_lat(String evt_lat) {
		this.evt_lat = evt_lat;
	}
	public String getEvt_power() {
		return evt_power;
	}
	public void setEvt_power(String evt_power) {
		this.evt_power = evt_power;
	}
	public String getEvt_z() {
		return evt_z;
	}
	public void setEvt_z(String evt_z) {
		this.evt_z = evt_z;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getEvt_group() {
		return evt_group;
	}
	public void setEvt_group(String evt_group) {
		this.evt_group = evt_group;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	 
}
