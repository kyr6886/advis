package kr.dis.std.category.dao;

public class TbOcrFileDTO{
	private String ctg_id;
	private String evt_id;
	private String title;
	private String file_org_name;
	private String file_new_name;
	private String ctg_title;
	int pageNo;
	int pageSize;
	
	public String getCtg_title() {
		return ctg_title;
	}
	public void setCtg_title(String ctg_title) {
		this.ctg_title = ctg_title;
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
	public String getFile_org_name() {
		return file_org_name;
	}
	public void setFile_org_name(String file_org_name) {
		this.file_org_name = file_org_name;
	}
	public String getFile_new_name() {
		return file_new_name;
	}
	public void setFile_new_name(String file_new_name) {
		this.file_new_name = file_new_name;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}