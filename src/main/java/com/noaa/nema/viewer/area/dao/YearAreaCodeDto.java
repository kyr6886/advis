package com.noaa.nema.viewer.area.dao;

import java.util.List;

import com.noaa.nema.viewer.asos.dao.ObsAsosDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmePbmDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmePubDto;

public class YearAreaCodeDto {
	private String code;
	private String group_cd;
	private String sido;
	private String sigungu;
	private String code_level;
	
	private boolean validData;
	private double totalPerson;
	private double totalPub;
	private double totalPbm;

	private double totalRain;
	private double max_10m_rain;
	private double max_60m_rain;

	private List<YearDmePbmDto> listPbm;
	private List<YearDmePubDto> listPub;
	private List<ObsAsosDto> listRain;
	private List<YearDmeDto> listTot;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getGroup_cd() {
		return group_cd;
	}
	public void setGroup_cd(String group_cd) {
		this.group_cd = group_cd;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getCode_level() {
		return code_level;
	}
	public void setCode_level(String code_level) {
		this.code_level = code_level;
	}
	public boolean isValidData() {
		return validData;
	}
	public void setValidData(boolean validData) {
		this.validData = validData;
	}
	public double getTotalPerson() {
		return totalPerson;
	}
	public void setTotalPerson(double totalPerson) {
		this.totalPerson = totalPerson;
	}
	public double getTotalPub() {
		return totalPub;
	}
	public void setTotalPub(double totalPub) {
		this.totalPub = totalPub;
	}
	public double getTotalPbm() {
		return totalPbm;
	}
	public void setTotalPbm(double totalPbm) {
		this.totalPbm = totalPbm;
	}
	public List<YearDmePbmDto> getListPbm() {
		return listPbm;
	}
	public void setListPbm(List<YearDmePbmDto> listPbm) {
		this.listPbm = listPbm;
	}
	public List<YearDmePubDto> getListPub() {
		return listPub;
	}
	public void setListPub(List<YearDmePubDto> listPub) {
		this.listPub = listPub;
	}
	public double getTotalRain() {
		return totalRain;
	}
	public void setTotalRain(double totalRain) {
		this.totalRain = totalRain;
	}
	public double getMax_10m_rain() {
		return max_10m_rain;
	}
	public void setMax_10m_rain(double max_10m_rain) {
		this.max_10m_rain = max_10m_rain;
	}
	public double getMax_60m_rain() {
		return max_60m_rain;
	}
	public void setMax_60m_rain(double max_60m_rain) {
		this.max_60m_rain = max_60m_rain;
	}
	public List<ObsAsosDto> getListRain() {
		return listRain;
	}
	public void setListRain(List<ObsAsosDto> listRain) {
		this.listRain = listRain;
	}
	public List<YearDmeDto> getListTot() {
		return listTot;
	}
	public void setListTot(List<YearDmeDto> listTot) {
		this.listTot = listTot;
	}
	
}
