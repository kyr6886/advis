package com.noaa.nema.viewer.viewmodel;

import java.util.List;

import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;

public class AreaCodeViewModel {

	
	private String paramSido;
	private String paramGungu;
	
	private List<YearAreaCodeDto> listAreaSidoCodes;
	private List<YearAreaCodeDto> listAreaGunguCodes;
	
	private String paramStDate;
	private String paramEndDate;
	
	
	public String getParamStDate() {
		return paramStDate;
	}
	public void setParamStDate(String paramStDate) {
		this.paramStDate = paramStDate;
	}
	public String getParamEndDate() {
		return paramEndDate;
	}
	public void setParamEndDate(String paramEndDate) {
		this.paramEndDate = paramEndDate;
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
	public List<YearAreaCodeDto> getListAreaSidoCodes() {
		return listAreaSidoCodes;
	}
	public void setListAreaSidoCodes(List<YearAreaCodeDto> listAreaSidoCodes) {
		this.listAreaSidoCodes = listAreaSidoCodes;
	}
	public List<YearAreaCodeDto> getListAreaGunguCodes() {
		return listAreaGunguCodes;
	}
	public void setListAreaGunguCodes(List<YearAreaCodeDto> listAreaGunguCodes) {
		this.listAreaGunguCodes = listAreaGunguCodes;
	}
	
	
	
}
