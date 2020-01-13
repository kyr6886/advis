package kr.dis.std.advis.resultmodel;

public class StaticsMonthlyModel {
	private String damage_code;
	private String damage_name;
	private String sido_code;
	private String beg_month;
	private int count;
	private int total;
	private float damage_money;
	private float rate;
	
	
	public float getDamage_money() {
		return damage_money;
	}
	public void setDamage_money(float damage_money) {
		this.damage_money = damage_money;
	}
	public String getDamage_code() {
		return damage_code;
	}
	public void setDamage_code(String damage_code) {
		this.damage_code = damage_code;
	}
	public String getDamage_name() {
		return damage_name;
	}
	public void setDamage_name(String damage_name) {
		this.damage_name = damage_name;
	}
	public String getSido_code() {
		return sido_code;
	}
	public void setSido_code(String sido_code) {
		this.sido_code = sido_code;
	}
	public String getBeg_month() {
		return beg_month;
	}
	public void setBeg_month(String beg_month) {
		this.beg_month = beg_month;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	
}
