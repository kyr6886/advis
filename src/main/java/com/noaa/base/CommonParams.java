package com.noaa.base;

import java.util.Date;

public class CommonParams {
	private String loginUserID;
	private String loginUserName;
	private Date requestDate;
	private String requestIP;
	private String sessID;
	private String deviceType;
	private String isMobilYN;
	private String userRole;
	private String AccType;
	private String AccTitle;
	

	public String getAccType() {
		return AccType;
	}
	public void setAccType(String accType) {
		AccType = accType;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getIsMobilYN() {
		return isMobilYN;
	}
	public void setIsMobilYN(String isMobilYN) {
		this.isMobilYN = isMobilYN;
	}
	public String getSessID() {
		return sessID;
	}
	public void setSessID(String sessID) {
		this.sessID = sessID;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public String getLoginUserID() {
		return loginUserID;
	}
	public void setLoginUserID(String loginUserID) {
		this.loginUserID = loginUserID;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getRequestIP() {
		return requestIP;
	}
	public void setRequestIP(String requestIP) {
		this.requestIP = requestIP;
	}
	public String getAccTitle() {
		return AccTitle;
	}
	public void setAccTitle(String accTitle) {
		AccTitle = accTitle;
	}
	
}
