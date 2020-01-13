package com.noaa.nema.viewer.asos.dao;

/**
 * @author dev
 * asos 관측지점
 */
public class ObsAsosStDto {
	
	private String obs_id;
	private String resources_code;
	private String obs_name;
	private String position_x;
	private String position_y;
	private String above_see_level;
	private String obs_barometer;
	private String obs_management;
	private String obs_thermometer;
	private String obs_windspeed;
	private String obs_rainfallmeter;
	private String start_date;
	private String delete_date;
	private String stn_id;
	
	public String getStn_id() {
		return stn_id;
	}
	public void setStn_id(String stn_id) {
		this.stn_id = stn_id;
	}
	public String getObs_id() {
		return obs_id;
	}
	public void setObs_id(String obs_id) {
		this.obs_id = obs_id;
	}
	public String getResources_code() {
		return resources_code;
	}
	public void setResources_code(String resources_code) {
		this.resources_code = resources_code;
	}
	public String getObs_name() {
		return obs_name;
	}
	public void setObs_name(String obs_name) {
		this.obs_name = obs_name;
	}
	public String getPosition_x() {
		return position_x;
	}
	public void setPosition_x(String position_x) {
		this.position_x = position_x;
	}
	public String getPosition_y() {
		return position_y;
	}
	public void setPosition_y(String position_y) {
		this.position_y = position_y;
	}
	public String getAbove_see_level() {
		return above_see_level;
	}
	public void setAbove_see_level(String above_see_level) {
		this.above_see_level = above_see_level;
	}
	public String getObs_barometer() {
		return obs_barometer;
	}
	public void setObs_barometer(String obs_barometer) {
		this.obs_barometer = obs_barometer;
	}
	public String getObs_management() {
		return obs_management;
	}
	public void setObs_management(String obs_management) {
		this.obs_management = obs_management;
	}
	public String getObs_thermometer() {
		return obs_thermometer;
	}
	public void setObs_thermometer(String obs_thermometer) {
		this.obs_thermometer = obs_thermometer;
	}
	public String getObs_windspeed() {
		return obs_windspeed;
	}
	public void setObs_windspeed(String obs_windspeed) {
		this.obs_windspeed = obs_windspeed;
	}
	public String getObs_rainfallmeter() {
		return obs_rainfallmeter;
	}
	public void setObs_rainfallmeter(String obs_rainfallmeter) {
		this.obs_rainfallmeter = obs_rainfallmeter;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getDelete_date() {
		return delete_date;
	}
	public void setDelete_date(String delete_date) {
		this.delete_date = delete_date;
	}

	
	
}
