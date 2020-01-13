package kr.dis.std.category.dao;

import java.io.Serializable;
import java.util.Date;

public class CategoryDTO implements Serializable {
	
	private String ctg_id;
	private String title;
	private String contents;
	private int depth;
	private int sort;
	private String parent_ctg_id;
	private String parent_ctg_ids;
	private String create_user_id;
	private String update_user_id;
	private String use_yn;
	private String print_id;
	private String ctg_group;
	private String paramCtg_group;
	
	private String largeId;
	private String largeTitle;
	
	private String mediumId;
	private String mediumTitle;
	
	private String smallId;
	private String smallTitle;
	
	private String smallerId;
	private String smallerTitle;
	
	private String detailedId;
	private String detailedTitle;
	
	private String selected;
	private String selectedDepth;
	
	private String session_id;
	
	
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getDetailedId() {
		return detailedId;
	}
	public void setDetailedId(String detailedId) {
		this.detailedId = detailedId;
	}
	public String getDetailedTitle() {
		return detailedTitle;
	}
	public void setDetailedTitle(String detailedTitle) {
		this.detailedTitle = detailedTitle;
	}
	public String getSelectedDepth() {
		return selectedDepth;
	}
	public void setSelectedDepth(String selectedDepth) {
		this.selectedDepth = selectedDepth;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public String getLargeId() {
		return largeId;
	}
	public void setLargeId(String largeId) {
		this.largeId = largeId;
	}
	public String getLargeTitle() {
		return largeTitle;
	}
	public void setLargeTitle(String largeTitle) {
		this.largeTitle = largeTitle;
	}
	public String getMediumId() {
		return mediumId;
	}
	public void setMediumId(String mediumId) {
		this.mediumId = mediumId;
	}
	public String getMediumTitle() {
		return mediumTitle;
	}
	public void setMediumTitle(String mediumTitle) {
		this.mediumTitle = mediumTitle;
	}
	public String getSmallId() {
		return smallId;
	}
	public void setSmallId(String smallId) {
		this.smallId = smallId;
	}
	public String getSmallTitle() {
		return smallTitle;
	}
	public void setSmallTitle(String smallTitle) {
		this.smallTitle = smallTitle;
	}
	public String getSmallerId() {
		return smallerId;
	}
	public void setSmallerId(String smallerId) {
		this.smallerId = smallerId;
	}
	public String getSmallerTitle() {
		return smallerTitle;
	}
	public void setSmallerTitle(String smallerTitle) {
		this.smallerTitle = smallerTitle;
	}
	public String getParamCtg_group() {
		return paramCtg_group;
	}
	public void setParamCtg_group(String paramCtg_group) {
		this.paramCtg_group = paramCtg_group;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getParent_ctg_id() {
		return parent_ctg_id;
	}
	public void setParent_ctg_id(String parent_ctg_id) {
		this.parent_ctg_id = parent_ctg_id;
	}
	public String getParent_ctg_ids() {
		return parent_ctg_ids;
	}
	public void setParent_ctg_ids(String parent_ctg_ids) {
		this.parent_ctg_ids = parent_ctg_ids;
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
	public String getPrint_id() {
		return print_id;
	}
	public void setPrint_id(String print_id) {
		this.print_id = print_id;
	}
	public String getCtg_group() {
		return ctg_group;
	}
	public void setCtg_group(String ctg_group) {
		this.ctg_group = ctg_group;
	}
	
	
}
