package kr.dis.std.advis.dao;

import com.noaa.base.BaseDTO;

public class UniOcrDto extends BaseDTO  {
	private String u_id;
	private int u_seq;
	private String u_name;
	private String c_u_name;
	private String u_status;
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public int getU_seq() {
		return u_seq;
	}
	public void setU_seq(int u_seq) {
		this.u_seq = u_seq;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getC_u_name() {
		return c_u_name;
	}
	public void setC_u_name(String c_u_name) {
		this.c_u_name = c_u_name;
	}
	public String getU_status() {
		return u_status;
	}
	public void setU_status(String u_status) {
		this.u_status = u_status;
	}
	
	
	
}
