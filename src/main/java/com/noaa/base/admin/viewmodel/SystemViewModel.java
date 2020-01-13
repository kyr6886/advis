package com.noaa.base.admin.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.base.file.dao.DBInfoDTO;

public class SystemViewModel extends BaseViewModel {
	private List<DBInfoDTO> dbList;

	public List<DBInfoDTO> getDbList() {
		return dbList;
	}

	public void setDbList(List<DBInfoDTO> dbList) {
		this.dbList = dbList;
	}
	
	

}
