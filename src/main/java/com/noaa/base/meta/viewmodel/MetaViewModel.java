package com.noaa.base.meta.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.base.meta.dao.MetaAdmDTO;
import com.noaa.base.meta.dao.MetaLawDTO;

public class MetaViewModel extends BaseViewModel {
	private List<MetaAdmDTO> listAdm;
	private List<MetaLawDTO> listLaw;
	
	private String paramCode;
	private String paramSido;
	private String paramGungu;
	private String paramDong;
	private String paramRi;
	
	public List<MetaAdmDTO> getListAdm() {
		return listAdm;
	}
	public void setListAdm(List<MetaAdmDTO> listAdm) {
		this.listAdm = listAdm;
	}
	public List<MetaLawDTO> getListLaw() {
		return listLaw;
	}
	public void setListLaw(List<MetaLawDTO> listLaw) {
		this.listLaw = listLaw;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamSido() {
		return paramSido;
	}
	public void setParamSido(String paramSido) {
		this.paramSido = paramSido;
	}	
	public String getParamGungu() {
		return paramGungu;
	}
	public void setParamGungu(String paramGungu) {
		this.paramGungu = paramGungu;
	}
	public String getParamDong() {
		return paramDong;
	}
	public void setParamDong(String paramDong) {
		this.paramDong = paramDong;
	}
	public String getParamRi() {
		return paramRi;
	}
	public void setParamRi(String paramRi) {
		this.paramRi = paramRi;
	}
	
	
	
}
