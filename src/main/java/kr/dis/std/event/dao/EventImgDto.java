package kr.dis.std.event.dao;

import java.util.List;

import com.noaa.base.file.dao.AttachFileDTO;

public class EventImgDto extends AttachFileDTO {
	private int seq;
	private String file_grp_id;
	private String evt_id;
	private String ctg_id;
	private String title;
	private String create_user_id;
	private String update_user_id;
	private String use_yn;
	
	private int depth;
	private List<String> keyWordList;
	
	
	
	public List<String> getKeyWordList() {
		return keyWordList;
	}
	public void setKeyWordList(List<String> keyWordList) {
		this.keyWordList = keyWordList;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getFile_grp_id() {
		return file_grp_id;
	}
	public void setFile_grp_id(String file_grp_id) {
		this.file_grp_id = file_grp_id;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getUpdate_user_id() {
		return update_user_id;
	}
	public void setUpdate_user_id(String update_user_id) {
		this.update_user_id = update_user_id;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	
	
}
