package com.noaa.base.admin.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.base.account.dao.LoginLogDTO;
import com.noaa.base.account.dao.RoleDTO;
import com.noaa.base.account.dao.UserDTO;
import com.noaa.base.code.dao.CodeSysDTO;
import com.noaa.base.meta.dao.MetaAdmDTO;

import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.let.uss.umt.service.MberManageVO;
import egovframework.let.uss.umt.service.UserDefaultVO;
import egovframework.let.uss.umt.service.impl.MberManageDAO;




public class AdminAccountViewModel extends BaseViewModel {
	private UserDTO detail;
	private LoginLogDTO loginDetail;
	private List<UserDTO> list;
	private List<CodeSysDTO> listUserTypeCodes;
	private List<CodeSysDTO> listUserStatusCodes;
	private List<RoleDTO> listRoles;
	private List<LoginLogDTO> listLoginLog;
	private List<LoginLogDTO> chartLoginLog;
	private List<MetaAdmDTO> listAdm;
	private String paramUserName;
	private String paramUserID;
	private String paramUserType;
	private String paramUserStatus;
	private String paramUpdateDate;
	private int conn_cnt_tot; //총 접속횟수
	//egov-------------------------------------------
	private List<MberManageVO> listMberManageVO;
	private List<CmmnDetailCode> passwordHint_result;
	private List<CmmnDetailCode> sexdstnCode_result;
	private List<CmmnDetailCode> mberSttus_result;
	private List<CmmnDetailCode> groupId_result ;
	private MberManageVO mberManage;
	private UserDefaultVO userSearchVO;
	
	//egov-------------------------------------------
	
	
	
	public UserDefaultVO getUserSearchVO() {
		return userSearchVO;
	}
	public String getParamUserStatus() {
		return paramUserStatus;
	}
	public void setParamUserStatus(String paramUserStatus) {
		this.paramUserStatus = paramUserStatus;
	}
	public List<CodeSysDTO> getListUserStatusCodes() {
		return listUserStatusCodes;
	}
	public void setListUserStatusCodes(List<CodeSysDTO> listUserStatusCodes) {
		this.listUserStatusCodes = listUserStatusCodes;
	}
	public List<MetaAdmDTO> getListAdm() {
		return listAdm;
	}
	public void setListAdm(List<MetaAdmDTO> listAdm) {
		this.listAdm = listAdm;
	}
	public int getConn_cnt_tot() {
		return conn_cnt_tot;
	}
	public void setConn_cnt_tot(int conn_cnt_tot) {
		this.conn_cnt_tot = conn_cnt_tot;
	}
	public List<LoginLogDTO> getChartLoginLog() {
		return chartLoginLog;
	}
	public void setChartLoginLog(List<LoginLogDTO> chartLoginLog) {
		this.chartLoginLog = chartLoginLog;
	}
	public List<LoginLogDTO> getListLoginLog() {
		return listLoginLog;
	}
	public void setListLoginLog(List<LoginLogDTO> listLoginLog) {
		this.listLoginLog = listLoginLog;
	}
	public LoginLogDTO getLoginDetail() {
		return loginDetail;
	}
	public void setLoginDetail(LoginLogDTO loginDetail) {
		this.loginDetail = loginDetail;
	}
	public String getParamUpdateDate() {
		return paramUpdateDate;
	}
	public void setParamUpdateDate(String paramUpdateDate) {
		this.paramUpdateDate = paramUpdateDate;
	}
	public String getParamUserName() {
		return paramUserName;
	}
	public void setParamUserName(String paramUserName) {
		this.paramUserName = paramUserName;
	}
	public List<MberManageVO> getListMberManageVO() {
		return listMberManageVO;
	}
	public void setListMberManageVO(List<MberManageVO> listMberManageVO) {
		this.listMberManageVO = listMberManageVO;
	}
	public void setUserSearchVO(UserDefaultVO userSearchVO) {
		this.userSearchVO = userSearchVO;
	}
	public MberManageVO getMberManage() {
		return mberManage;
	}
	public void setMberManage(MberManageVO mberManage) {
		this.mberManage = mberManage;
	}
	public List<CmmnDetailCode> getPasswordHint_result() {
		return passwordHint_result;
	}
	public void setPasswordHint_result(List<CmmnDetailCode> passwordHint_result) {
		this.passwordHint_result = passwordHint_result;
	}
	public List<CmmnDetailCode> getSexdstnCode_result() {
		return sexdstnCode_result;
	}
	public void setSexdstnCode_result(List<CmmnDetailCode> sexdstnCode_result) {
		this.sexdstnCode_result = sexdstnCode_result;
	}
	public List<CmmnDetailCode> getMberSttus_result() {
		return mberSttus_result;
	}
	public void setMberSttus_result(List<CmmnDetailCode> mberSttus_result) {
		this.mberSttus_result = mberSttus_result;
	}
	public List<CmmnDetailCode> getGroupId_result() {
		return groupId_result;
	}
	public void setGroupId_result(List<CmmnDetailCode> groupId_result) {
		this.groupId_result = groupId_result;
	}
	public List<RoleDTO> getListRoles() {
		return listRoles;
	}
	public void setListRoles(List<RoleDTO> listRoles) {
		this.listRoles = listRoles;
	}
	public String getParamUserID() {
		return paramUserID;
	}
	public void setParamUserID(String paramUserID) {
		this.paramUserID = paramUserID;
	}
	public String getParamUserType() {
		return paramUserType;
	}
	public void setParamUserType(String paramUserType) {
		this.paramUserType = paramUserType;
	}
	public List<CodeSysDTO> getListUserTypeCodes() {
		return listUserTypeCodes;
	}
	public void setListUserTypeCodes(List<CodeSysDTO> listUserTypeCodes) {
		this.listUserTypeCodes = listUserTypeCodes;
	}

	public UserDTO getDetail() {
		return detail;
	}
	public void setDetail(UserDTO detail) {
		this.detail = detail;
	}
	public List<UserDTO> getList() {
		return list;
	}
	public void setList(List<UserDTO> list) {
		this.list = list;
	}
	
}
