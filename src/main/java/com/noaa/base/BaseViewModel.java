package com.noaa.base;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.noaa.base.code.dao.CodeSysDTO;
import com.noaa.base.menu.dao.MenuDTO;




public class BaseViewModel {
	private String requestIP;
	private Date requestTime;
	private MenuDTO menuDetail;
	private List<MenuDTO> menus;
	private List<CodeSysDTO> codes;
	private int pageNo;
	private int pageSize;
	private String id;
	private int resultCount;
	private String resultData;
	private String resultMessage;
	private String menuHtml;
    private int totalCount;
    private String isValidYN;
    private String pg;
	private CommonParams commonParams;
	private String rememberMeYn;
	private List<MultipartFile> uploadFiles;
	private String smartEditor;
    private String statusCode;
    private String reason;
    private String returnURI;
    private String loginURI;
    private String loginView;
    private String paramOption;
	private String paramText;
	private String paramStartDate;
	private String paramEndDate;
	private int paramAvgTime; //몇분이내에 접속자수를 가져오기 위한 변수
	private String defaultView;
	private int user_tot_cnt; //총사용자수
	private int avg_use_time; // 평균사용시간
	private int cur_user_cnt; // 현재접속자수
	private boolean enableWriteYN;
	private String keyModule;
	private String keyExponent;
	


	public String getKeyModule() {
		return keyModule;
	}


	public void setKeyModule(String keyModule) {
		this.keyModule = keyModule;
	}


	public String getKeyExponent() {
		return keyExponent;
	}


	public void setKeyExponent(String keyExponent) {
		this.keyExponent = keyExponent;
	}


	public MenuDTO getMenuDetail() {
		return menuDetail;
	}


	public void setMenuDetail(MenuDTO menuDetail) {
		this.menuDetail = menuDetail;
	}


	public boolean isEnableWriteYN() {
		return enableWriteYN;
	}


	public void setEnableWriteYN(boolean enableWriteYN) {
		this.enableWriteYN = enableWriteYN;
	}


	public String getLoginView() {
		return loginView;
	}


	public void setLoginView(String loginView) {
		this.loginView = loginView;
	}


	public String getLoginURI() {
		return loginURI;
	}


	public void setLoginURI(String loginURI) {
		this.loginURI = loginURI;
	}


	public String getDefaultView() {
		return defaultView;
	}


	public void setDefaultView(String defaultView) {
		this.defaultView = defaultView;
	}


	public int getParamAvgTime() {
		return paramAvgTime;
	}


	public void setParamAvgTime(int paramAvgTime) {
		this.paramAvgTime = paramAvgTime;
	}

	public int getUser_tot_cnt() {
		return user_tot_cnt;
	}


	public void setUser_tot_cnt(int user_tot_cnt) {
		this.user_tot_cnt = user_tot_cnt;
	}


	public int getAvg_use_time() {
		return avg_use_time;
	}


	public void setAvg_use_time(int avg_use_time) {
		this.avg_use_time = avg_use_time;
	}


	public int getCur_user_cnt() {
		return cur_user_cnt;
	}


	public void setCur_user_cnt(int cur_user_cnt) {
		this.cur_user_cnt = cur_user_cnt;
	}


	public String getParamOption() {
		return paramOption;
	}


	public void setParamOption(String paramOption) {
		this.paramOption = paramOption;
	}


	public String getParamText() {
		return paramText;
	}


	public void setParamText(String paramText) {
		this.paramText = paramText;
	}


	public String getParamStartDate() {
		return paramStartDate;
	}


	public void setParamStartDate(String paramStartDate) {
		this.paramStartDate = paramStartDate;
	}


	public String getParamEndDate() {
		return paramEndDate;
	}


	public void setParamEndDate(String paramEndDate) {
		this.paramEndDate = paramEndDate;
	}


	public String getReturnURI() {
		return returnURI;
	}


	public void setReturnURI(String returnURI) {
		this.returnURI = returnURI;
	}


	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getSmartEditor() {
		return smartEditor;
	}


	public void setSmartEditor(String smartEditor) {
		this.smartEditor = smartEditor;
	}


	public List<MultipartFile> getUploadFiles(){
		return uploadFiles;
	}


	public void setUploadFiles(List<MultipartFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}


	public String getRememberMeYn() {
		return rememberMeYn;
	}


	public void setRememberMeYn(String rememberMeYn) {
		this.rememberMeYn = rememberMeYn;
	}
	
	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
	}
	public CommonParams getCommonParams() {
		return commonParams;
	}
	public void setCommonParams(CommonParams commonParams) {
		this.commonParams = commonParams;
	}
	public String getIsValidYN() {
		return isValidYN;
	}
	public void setIsValidYN(String isValidYN) {
		this.isValidYN = isValidYN;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getResultData() {
		return resultData;
	}
	public void setResultData(String resultData) {
		this.resultData = resultData;
	}
	public String getMenuHtml() {
		return menuHtml;
	}
	public void setMenuHtml(String menuHtml) {
		this.menuHtml = menuHtml;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<CodeSysDTO> getCodes() {
		return codes;
	}
	public void setCodes(List<CodeSysDTO> codes) {
		this.codes = codes;
	}
	public int getResultCount() {
		return resultCount;
	}
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
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
	public List<MenuDTO> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDTO> menus) {
		this.menus = menus;
	}
	public String getRequestIP() {
		return requestIP;
	}
	public void setRequestIP(String requestIP) {
		this.requestIP = requestIP;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	
}
