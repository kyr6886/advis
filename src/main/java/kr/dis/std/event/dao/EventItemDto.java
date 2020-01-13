package kr.dis.std.event.dao;

import java.util.ArrayList;
import java.util.List;

import com.noaa.base.BaseDTO;

public class EventItemDto extends BaseDTO {
	private int seq;
	private String evt_id;
	private String ctg_id;
	private int evt_num;
	private String contents;
	private String item_group;
	
	private String contentsTitle;
	private ArrayList<String> keywordList;
	
	private String evt_date;
	private String evt_time;
	private String evt_orgNm;
	private List<EventItemDto> eventItemList;
	
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
	public String getEvt_orgNm() {
		return evt_orgNm;
	}
	public void setEvt_orgNm(String evt_orgNm) {
		this.evt_orgNm = evt_orgNm;
	}
	public List<EventItemDto> getEventItemList() {
		return eventItemList;
	}
	public void setEventItemList(List<EventItemDto> eventItemList) {
		this.eventItemList = eventItemList;
	}
	public ArrayList<String> getKeywordList() {
		return keywordList;
	}
	public void setKeywordList(ArrayList<String> keywordList) {
		this.keywordList = keywordList;
	}
	public String getContentsTitle() {
		return contentsTitle;
	}
	public void setContentsTitle(String contentsTitle) {
		this.contentsTitle = contentsTitle;
	}
	public String getItem_group() {
		return item_group;
	}
	public void setItem_group(String item_group) {
		this.item_group = item_group;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getEvt_id() {
		return evt_id;
	}
	public void setEvt_id(String evt_id) {
		this.evt_id = evt_id;
	}
	public String getCtg_id() {
		return ctg_id;
	}
	public void setCtg_id(String ctg_id) {
		this.ctg_id = ctg_id;
	}
	public int getEvt_num() {
		return evt_num;
	}
	public void setEvt_num(int evt_num) {
		this.evt_num = evt_num;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
