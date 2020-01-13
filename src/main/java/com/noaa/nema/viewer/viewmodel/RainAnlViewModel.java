package com.noaa.nema.viewer.viewmodel;

import java.util.List;

import com.noaa.nema.viewer.asos.dao.ObsAsosDto;

public class RainAnlViewModel {

	private String paramSido;
	private String paramStDate;
	private String paramEndDate;
	private String paramRnDay;
	
	private List<ObsAsosDto> listSidoAsos;
	
	
	
	public String getParamRnDay() {
		return paramRnDay;
	}

	public void setParamRnDay(String paramRnDay) {
		this.paramRnDay = paramRnDay;
	}

	public String getParamSido() {
		return paramSido;
	}

	public void setParamSido(String paramSido) {
		this.paramSido = paramSido;
	}

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

	public List<ObsAsosDto> getListSidoAsos() {
		return listSidoAsos;
	}

	public void setListSidoAsos(List<ObsAsosDto> listSidoAsos) {
		this.listSidoAsos = listSidoAsos;
	}

}
