package com.noaa.nema.viewer.area.dao;

import java.util.List;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;


public class SectorDamageModel {
	private int countDamage;
	private double countPerson;
	private double countPublic;
	private double countPrivate;
	private int damageAreaCount;
	private int totalDmePerson;
	private double totalDmePbm;
	private double totalDmePub;
	private int sector;
	private int endSector;
	private List<YearDmeDto> listDmePerson;
	private List<YearDmeDto> listDmePub;
	private List<YearDmeDto> listDmePbm;
	
	
	public double getCountPerson() {
		return countPerson;
	}
	public void setCountPerson(double countPerson) {
		this.countPerson = countPerson;
	}
	public double getCountPublic() {
		return countPublic;
	}
	public void setCountPublic(double countPublic) {
		this.countPublic = countPublic;
	}
	public double getCountPrivate() {
		return countPrivate;
	}
	public void setCountPrivate(double countPrivate) {
		this.countPrivate = countPrivate;
	}
	public int getDamageAreaCount() {
		return damageAreaCount;
	}
	public void setDamageAreaCount(int damageAreaCount) {
		this.damageAreaCount = damageAreaCount;
	}
	public int getEndSector() {
		return endSector;
	}
	public void setEndSector(int endSector) {
		this.endSector = endSector;
	}
	public int getCountDamage() {
		return countDamage;
	}
	public void setCountDamage(int countDamage) {
		this.countDamage = countDamage;
	}
	public int getTotalDmePerson() {
		return totalDmePerson;
	}
	public void setTotalDmePerson(int totalDmePerson) {
		this.totalDmePerson = totalDmePerson;
	}
	public double getTotalDmePbm() {
		return totalDmePbm;
	}
	public void setTotalDmePbm(double totalDmePbm) {
		this.totalDmePbm = totalDmePbm;
	}
	public double getTotalDmePub() {
		return totalDmePub;
	}
	public void setTotalDmePub(double totalDmePub) {
		this.totalDmePub = totalDmePub;
	}
	public int getSector() {
		return sector;
	}
	public void setSector(int sector) {
		this.sector = sector;
	}
	public List<YearDmeDto> getListDmePerson() {
		return listDmePerson;
	}
	public void setListDmePerson(List<YearDmeDto> listDmePerson) {
		this.listDmePerson = listDmePerson;
	}
	public List<YearDmeDto> getListDmePub() {
		return listDmePub;
	}
	public void setListDmePub(List<YearDmeDto> listDmePub) {
		this.listDmePub = listDmePub;
	}
	public List<YearDmeDto> getListDmePbm() {
		return listDmePbm;
	}
	public void setListDmePbm(List<YearDmeDto> listDmePbm) {
		this.listDmePbm = listDmePbm;
	}
	
	
	
}
