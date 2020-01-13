package kr.dis.std.web.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.nema.viewer.area.dao.YearAreaCodeDto;

import kr.dis.std.station.dao.DisDmeStationDto;

public class DmeStationViewModel extends BaseViewModel {
	private String paramArea;
	private String paramType;
	
	private List<DisDmeStationDto> listDmeStation;
	private List<DisDmeStationDto> listTypeDmeStation;
	
	private List<YearAreaCodeDto> listSido;

	public String getParamArea() {
		return paramArea;
	}
	public void setParamArea(String paramArea) {
		this.paramArea = paramArea;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public List<DisDmeStationDto> getListDmeStation() {
		return listDmeStation;
	}
	public void setListDmeStation(List<DisDmeStationDto> listDmeStation) {
		this.listDmeStation = listDmeStation;
	}
	public List<DisDmeStationDto> getListTypeDmeStation() {
		return listTypeDmeStation;
	}
	public void setListTypeDmeStation(List<DisDmeStationDto> listTypeDmeStation) {
		this.listTypeDmeStation = listTypeDmeStation;
	}
	public List<YearAreaCodeDto> getListSido() {
		return listSido;
	}
	public void setListSido(List<YearAreaCodeDto> listSido) {
		this.listSido = listSido;
	}
}