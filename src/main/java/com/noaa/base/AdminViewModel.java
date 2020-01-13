package com.noaa.base;

import java.util.List;

import com.noaa.base.account.dao.UserDTO;
import com.noaa.base.bbs.dao.BBSContentDTO;
import com.noaa.base.menu.dao.MenuLogDTO;

public class AdminViewModel extends BaseViewModel {
	private UserDTO detailUser;
	private List<MenuLogDTO> listMenuLog;
	private List<MenuLogDTO> listVisitMenuLog;
	private List<BBSContentDTO> listBbsContent;
	
	
	public List<BBSContentDTO> getListBbsContent() {
		return listBbsContent;
	}

	public void setListBbsContent(List<BBSContentDTO> listBbsContent) {
		this.listBbsContent = listBbsContent;
	}

	public List<MenuLogDTO> getListVisitMenuLog() {
		return listVisitMenuLog;
	}

	public void setListVisitMenuLog(List<MenuLogDTO> listVisitMenuLog) {
		this.listVisitMenuLog = listVisitMenuLog;
	}

	public List<MenuLogDTO> getListMenuLog() {
		return listMenuLog;
	}

	public void setListMenuLog(List<MenuLogDTO> listMenuLog) {
		this.listMenuLog = listMenuLog;
	}

	public UserDTO getDetailUser() {
		return detailUser;
	}

	public void setDetailUser(UserDTO detailUser) {
		this.detailUser = detailUser;
	}
	
}
