package com.noaa.nema.viewer.year.dme.dao;

import java.util.List;

import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;

public class AreaBean extends YearAreaCodeDto  {

	private double totalPerson;
	private double totalPub;
	private double totalPmb;
	private List<YearDmePbmDto> listPbm;
	private List<YearDmePubDto> listPub;
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
	public double getTotalPmb() {
		return totalPmb;
	}
	public void setTotalPmb(double totalPmb) {
		this.totalPmb = totalPmb;
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
	
	
	
}
