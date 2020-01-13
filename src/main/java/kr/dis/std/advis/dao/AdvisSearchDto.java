package kr.dis.std.advis.dao;

import java.util.ArrayList;

import com.noaa.base.BaseDTO;

public class AdvisSearchDto extends BaseDTO {
	private String ctg_id;
	private String evt_id;
	private String title;
	private String evt_date;
	private String contents;
	private int evt_num;
	private int sort;
	private ArrayList<String> ctgArr;
	
	public ArrayList<String> getCtgArr() {
		return ctgArr;
	}
	public void setCtgArr(ArrayList<String> ctgArr) {
		this.ctgArr = ctgArr;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	private String file_grp_id;
	
	public String getFile_grp_id() {
		return file_grp_id;
	}
	public void setFile_grp_id(String file_grp_id) {
		this.file_grp_id = file_grp_id;
	}
	public String getCtg_id() {
		return ctg_id;
	}
	public void setCtg_id(String ctg_id) {
		this.ctg_id = ctg_id;
	}
	public String getEvt_id() {
		return evt_id;
	}
	public void setEvt_id(String evt_id) {
		this.evt_id = evt_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEvt_date() {
		return evt_date;
	}
	public void setEvt_date(String evt_date) {
		this.evt_date = evt_date;
	}
	public int getEvt_num() {
		return evt_num;
	}
	public void setEvt_num(int evt_num) {
		this.evt_num = evt_num;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
