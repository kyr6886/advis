package com.noaa.base.admin.viewmodel;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.noaa.base.BaseViewModel;
import com.noaa.base.account.dao.RoleDTO;
import com.noaa.base.bbs.dao.BBSCategoryDTO;
import com.noaa.base.bbs.dao.BBSContentDTO;
import com.noaa.base.bbs.dao.BBSMasterDTO;
import com.noaa.base.file.dao.AttachFileDTO;

public class AdminBoardViewModel extends BaseViewModel {
	private BBSMasterDTO detailMaster;
	private List<BBSMasterDTO> listMaster;
	private BBSContentDTO detailContent;
	private List<BBSContentDTO> listContent;
	private List<BBSCategoryDTO> listCategory;
	private List<AttachFileDTO> listAttachFiles;
	private List<RoleDTO> listRoles;
	private String searchTitle;
	private String searchContent;
	private String searchName;
	private String searchStDate;
	private String searchEnDate;
	private String searchCategorySeq;
	private String searchSpellKor;
	private String searchSpellEn;
	private String searchOption;
	private int paramSeq;
	private String paramCategorys;
	
	

	public List<RoleDTO> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<RoleDTO> listRoles) {
		this.listRoles = listRoles;
	}

	public String getParamCategorys() {
		return paramCategorys;
	}

	public void setParamCategorys(String paramCategorys) {
		this.paramCategorys = paramCategorys;
	}

	public List<BBSMasterDTO> getListMaster() {
		return listMaster;
	}

	public void setListMaster(List<BBSMasterDTO> listMaster) {
		this.listMaster = listMaster;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	public List<AttachFileDTO> getListAttachFiles() {
		return listAttachFiles;
	}

	public void setListAttachFiles(List<AttachFileDTO> listAttachFiles) {
		this.listAttachFiles = listAttachFiles;
	}

	public String getSearchSpellKor() {
		return searchSpellKor;
	}

	public void setSearchSpellKor(String searchSpellKor) {
		this.searchSpellKor = searchSpellKor;
	}

	public String getSearchSpellEn() {
		return searchSpellEn;
	}

	public void setSearchSpellEn(String searchSpellEn) {
		this.searchSpellEn = searchSpellEn;
	}

	public int getParamSeq() {
		return paramSeq;
	}

	public void setParamSeq(int paramSeq) {
		this.paramSeq = paramSeq;
	}

	public String getSearchCategorySeq() {
		return searchCategorySeq;
	}

	public void setSearchCategorySeq(String searchCategorySeq) {
		this.searchCategorySeq = searchCategorySeq;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public String getSearchStDate() {
		return searchStDate;
	}

	public void setSearchStDate(String searchStDate) {
		this.searchStDate = searchStDate;
	}

	public String getSearchEnDate() {
		return searchEnDate;
	}

	public void setSearchEnDate(String searchEnDate) {
		this.searchEnDate = searchEnDate;
	}

	
	public List<BBSCategoryDTO> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<BBSCategoryDTO> listCategory) {
		this.listCategory = listCategory;
	}

	

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public List<BBSContentDTO> getListContent() {
		return listContent;
	}

	public void setListContent(List<BBSContentDTO> listContent) {
		this.listContent = listContent;
	}

	public BBSContentDTO getDetailContent() {
		return detailContent;
	}

	public void setDetailContent(BBSContentDTO detailContent) {
		this.detailContent = detailContent;
	}

	public BBSMasterDTO getDetailMaster() {
		return detailMaster;
	}

	public void setDetailMaster(BBSMasterDTO detailMaster) {
		this.detailMaster = detailMaster;
	}
}
