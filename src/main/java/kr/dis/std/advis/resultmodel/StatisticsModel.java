package kr.dis.std.advis.resultmodel;

import java.util.List;

import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;



public class StatisticsModel {
	private String gb;
	private String gbName;
	private String code;
	private String codeName;
	private int count;
	private String year;
	private String month;
	private String day;
	private float damagePerson;
	private float damagePublic;
	private float damagePrivate;
	private float damageTotal;
	private float sum;
	private String damageName;
	private String parentYn;
	
	
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public String getParentYn() {
		return parentYn;
	}
	public void setParentYn(String parentYn) {
		this.parentYn = parentYn;
	}
	public String getDamageName() {
		return damageName;
	}
	public void setDamageName(String damageName) {
		this.damageName = damageName;
	}
	public float getDamagePerson() {
		return damagePerson;
	}
	public void setDamagePerson(float damagePerson) {
		this.damagePerson = damagePerson;
	}
	public float getDamagePublic() {
		return damagePublic;
	}
	public void setDamagePublic(float damagePublic) {
		this.damagePublic = damagePublic;
	}
	public float getDamagePrivate() {
		return damagePrivate;
	}
	public void setDamagePrivate(float damagePrivate) {
		this.damagePrivate = damagePrivate;
	}
	public float getDamageTotal() {
		return damageTotal;
	}
	public void setDamageTotal(float damageTotal) {
		this.damageTotal = damageTotal;
	}
	private List<YearDmeDto> listYearDme;
	
	
	public List<YearDmeDto> getListYearDme() {
		return listYearDme;
	}
	public void setListYearDme(List<YearDmeDto> listYearDme) {
		this.listYearDme = listYearDme;
	}
	public String getGb() {
		return gb;
	}
	public void setGb(String gb) {
		this.gb = gb;
	}
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	
	
}
