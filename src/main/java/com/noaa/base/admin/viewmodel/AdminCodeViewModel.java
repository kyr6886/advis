package com.noaa.base.admin.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.base.code.dao.CodeGrpDTO;
import com.noaa.base.code.dao.CodeSysDTO;

public class AdminCodeViewModel extends BaseViewModel{
	private List<CodeGrpDTO> listCodeGrp;
	private List<CodeSysDTO> listCodeSys;
	
	private String paramCodeGrp;
	private String paramCodeGrpTxt;
	private String paramSysCode;
	private String paramSysCodeTxt;
	
	
	public String getParamSysCode() {
		return paramSysCode;
	}
	public void setParamSysCode(String paramSysCode) {
		this.paramSysCode = paramSysCode;
	}
	public String getParamSysCodeTxt() {
		return paramSysCodeTxt;
	}
	public void setParamSysCodeTxt(String paramSysCodeTxt) {
		this.paramSysCodeTxt = paramSysCodeTxt;
	}
	public String getParamCodeGrpTxt() {
		return paramCodeGrpTxt;
	}
	public void setParamCodeGrpTxt(String paramCodeGrpTxt) {
		this.paramCodeGrpTxt = paramCodeGrpTxt;
	}
	public String getParamCodeGrp() {
		return paramCodeGrp;
	}
	public void setParamCodeGrp(String paramCodeGrp) {
		this.paramCodeGrp = paramCodeGrp;
	}
	public List<CodeGrpDTO> getListCodeGrp() {
		return listCodeGrp;
	}
	public void setListCodeGrp(List<CodeGrpDTO> listCodeGrp) {
		this.listCodeGrp = listCodeGrp;
	}
	public List<CodeSysDTO> getListCodeSys() {
		return listCodeSys;
	}
	public void setListCodeSys(List<CodeSysDTO> listCodeSys) {
		this.listCodeSys = listCodeSys;
	}
	
	
}
