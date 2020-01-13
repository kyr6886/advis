package com.noaa.base;

import java.util.List;

import com.noaa.base.account.dao.RoleDTO;
import com.noaa.base.account.dao.UserDTO;

import egovframework.com.cmm.LoginVO;

public class BaseAccountViewModel extends BaseViewModel{
	private String paramUserID;
	private String paramPwd;
	private String paramOldPwd;
	private String paramEmail;
	private String paramPhone;
	private String paramUserType;
	private String paramUserStatus;
	private String paramAdmCode;
	private UserDTO detailUser;
	private List<UserDTO> list;
	private List<RoleDTO> listRoles;
	//---[egov]
	private LoginVO loginInfo;
	//[egove]
	
	
	public String getParamUserID() {
		return paramUserID;
	}
	
	public List<RoleDTO> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<RoleDTO> listRoles) {
		this.listRoles = listRoles;
	}

	public String getParamEmail() {
		return paramEmail;
	}

	public void setParamEmail(String paramEmail) {
		this.paramEmail = paramEmail;
	}

	public String getParamPhone() {
		return paramPhone;
	}

	public void setParamPhone(String paramPhone) {
		this.paramPhone = paramPhone;
	}

	public String getParamAdmCode() {
		return paramAdmCode;
	}

	public void setParamAdmCode(String paramAdmCode) {
		this.paramAdmCode = paramAdmCode;
	}

	public String getParamUserType() {
		return paramUserType;
	}

	public void setParamUserType(String paramUserType) {
		this.paramUserType = paramUserType;
	}

	public String getParamUserStatus() {
		return paramUserStatus;
	}

	public void setParamUserStatus(String paramUserStatus) {
		this.paramUserStatus = paramUserStatus;
	}

	public List<UserDTO> getList() {
		return list;
	}
	public void setList(List<UserDTO> list) {
		this.list = list;
	}
	
	public UserDTO getDetailUser() {
		return detailUser;
	}
	public void setDetailUser(UserDTO detailUser) {
		this.detailUser = detailUser;
	}
	public LoginVO getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(LoginVO loginInfo) {
		this.loginInfo = loginInfo;
	}
	public void setParamUserID(String paramUserID) {
		this.paramUserID = paramUserID;
	}
	public String getParamPwd() {
		return paramPwd;
	}
	public void setParamPwd(String paramPwd) {
		this.paramPwd = paramPwd;
	}

	public String getParamOldPwd() {
		return paramOldPwd;
	}

	public void setParamOldPwd(String paramOldPwd) {
		this.paramOldPwd = paramOldPwd;
	}
	
}
