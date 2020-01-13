package kr.dis.std.event.dao;

import java.util.ArrayList;
import java.util.List;

import com.noaa.base.BaseDTO;

public class EventsDto extends BaseDTO {
	private String ctg_id;
	private String evt_id;
	private String title;
	private String evt_date;
	private String contents;
	private int evt_num;
	private int sort;
	private ArrayList<String> ctgArr;
	private String division;
	
	private String damage_code;
	private String beg_date;
	private String end_date;
	private List<EventItemDto> eventItemList;
	private List<String> keyWordList;
	
	private String evt_year;
	private List<EventItemDto> responseStatusEventList;
	
	private String evt_group;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String dis_org;
	private String dis_level_code;
	private String dis_act_lv1;
	private String dis_act_lv2;
	private String dis_act_lv3;
	private String dis_act_lv4;
	private String dis_act_lv5;
	private String dis_act_lv6;
	private String sys_title;
	private String dis_news;
	private String dis_dmg;
	private String dis_nty;
	
	
	
	public String getDis_nty() {
		return dis_nty;
	}
	public void setDis_nty(String dis_nty) {
		this.dis_nty = dis_nty;
	}
	public String getDis_dmg() {
		return dis_dmg;
	}
	public void setDis_dmg(String dis_dmg) {
		this.dis_dmg = dis_dmg;
	}
	public String getDis_news() {
		return dis_news;
	}
	public void setDis_news(String dis_news) {
		this.dis_news = dis_news;
	}
	public String getSys_title() {
		return sys_title;
	}
	public void setSys_title(String sys_title) {
		this.sys_title = sys_title;
	}
	public String getEvt_group() {
		return evt_group;
	}
	public void setEvt_group(String evt_group) {
		this.evt_group = evt_group;
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
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getDis_org() {
		return dis_org;
	}
	public void setDis_org(String dis_org) {
		this.dis_org = dis_org;
	}
	public String getDis_level_code() {
		return dis_level_code;
	}
	public void setDis_level_code(String dis_level_code) {
		this.dis_level_code = dis_level_code;
	}
	public String getDis_act_lv1() {
		return dis_act_lv1;
	}
	public void setDis_act_lv1(String dis_act_lv1) {
		this.dis_act_lv1 = dis_act_lv1;
	}
	public String getDis_act_lv2() {
		return dis_act_lv2;
	}
	public void setDis_act_lv2(String dis_act_lv2) {
		this.dis_act_lv2 = dis_act_lv2;
	}
	public String getDis_act_lv3() {
		return dis_act_lv3;
	}
	public void setDis_act_lv3(String dis_act_lv3) {
		this.dis_act_lv3 = dis_act_lv3;
	}
	public String getDis_act_lv4() {
		return dis_act_lv4;
	}
	public void setDis_act_lv4(String dis_act_lv4) {
		this.dis_act_lv4 = dis_act_lv4;
	}
	public String getDis_act_lv5() {
		return dis_act_lv5;
	}
	public void setDis_act_lv5(String dis_act_lv5) {
		this.dis_act_lv5 = dis_act_lv5;
	}
	public String getDis_act_lv6() {
		return dis_act_lv6;
	}
	public void setDis_act_lv6(String dis_act_lv6) {
		this.dis_act_lv6 = dis_act_lv6;
	}
	public String getEvt_year() {
		return evt_year;
	}
	public void setEvt_year(String evt_year) {
		this.evt_year = evt_year;
	}
	public List<EventItemDto> getResponseStatusEventList() {
		return responseStatusEventList;
	}
	public void setResponseStatusEventList(List<EventItemDto> responseStatusEventList) {
		this.responseStatusEventList = responseStatusEventList;
	}
	public List<String> getKeyWordList() {
		return keyWordList;
	}
	public void setKeyWordList(List<String> keyWordList) {
		this.keyWordList = keyWordList;
	}
	public String getDamage_code() {
		return damage_code;
	}
	public void setDamage_code(String damage_code) {
		this.damage_code = damage_code;
	}
	public String getBeg_date() {
		return beg_date;
	}
	public void setBeg_date(String beg_date) {
		this.beg_date = beg_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public List<EventItemDto> getEventItemList() {
		return eventItemList;
	}
	public void setEventItemList(List<EventItemDto> eventItemList) {
		this.eventItemList = eventItemList;
	}
	
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
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
