package kr.dis.std.web.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.nema.viewer.typhoon.dao.TyphoonDamageDto;

import kr.dis.std.advis.dao.DamageAnlRainDto;
import kr.dis.std.advis.dao.DamageTyphoonDto;
import kr.dis.std.advis.resultmodel.StatisticsModel;

public class AnalysisViewModel extends BaseViewModel {
	private int paramStDamage;
	private int paramEndDamage;
	private String paramDamageName;
	private String paramDamageType;
	private int paramStDamagePerson;
	private int paramEndDamagePerson;
	private List<DamageTyphoonDto> listTyphoonDamageInfo;
	private List<StatisticsModel> listStatistics;
	
	
	public List<StatisticsModel> getListStatistics() {
		return listStatistics;
	}
	public void setListStatistics(List<StatisticsModel> listStatistics) {
		this.listStatistics = listStatistics;
	}
	public int getParamStDamage() {
		return paramStDamage;
	}
	public void setParamStDamage(int paramStDamage) {
		this.paramStDamage = paramStDamage;
	}
	public int getParamEndDamage() {
		return paramEndDamage;
	}
	public void setParamEndDamage(int paramEndDamage) {
		this.paramEndDamage = paramEndDamage;
	}
	public String getParamDamageName() {
		return paramDamageName;
	}
	public void setParamDamageName(String paramDamageName) {
		this.paramDamageName = paramDamageName;
	}
	public String getParamDamageType() {
		return paramDamageType;
	}
	public void setParamDamageType(String paramDamageType) {
		this.paramDamageType = paramDamageType;
	}
	public int getParamStDamagePerson() {
		return paramStDamagePerson;
	}
	public void setParamStDamagePerson(int paramStDamagePerson) {
		this.paramStDamagePerson = paramStDamagePerson;
	}
	public int getParamEndDamagePerson() {
		return paramEndDamagePerson;
	}
	public void setParamEndDamagePerson(int paramEndDamagePerson) {
		this.paramEndDamagePerson = paramEndDamagePerson;
	}
	public List<DamageTyphoonDto> getListTyphoonDamageInfo() {
		return listTyphoonDamageInfo;
	}
	public void setListTyphoonDamageInfo(List<DamageTyphoonDto> listTyphoonDamageInfo) {
		this.listTyphoonDamageInfo = listTyphoonDamageInfo;
	}
	
	
	
}
