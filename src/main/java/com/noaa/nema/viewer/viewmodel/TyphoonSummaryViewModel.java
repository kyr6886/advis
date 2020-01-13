package com.noaa.nema.viewer.viewmodel;

import java.util.List;

import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;
import com.noaa.nema.viewer.typhoon.dao.YearDmeTyphoonDto;

public class TyphoonSummaryViewModel extends BaseViewModel{
	private String begDate;
	private String endDate;
	private String typhoonName;
	
	private double privateDamage;
	private double publicDamage;
	private double totalDamage;
	private double totalPersonDamage;
	private List<YearDmeTyphoonDto> listYearDmeTyphoon;
	private List<TyphoonCastInfoDto> listTyphoonPosition;
	private TyphoonCastInfoDto detailTyphoonInfo;
	
	private List<TyphoonCastInfoDto> listWorldTyphoonsPosition;
	private List<TyphoonCastInfoDto> listSimilarTyphoons;
	private List<ParamViewModel> listSimilarTypParams;
	private String params;
	private String paramYear;
	private String paramMonth;
	private String paramName;
	private String paramStDate;
	private String paramEndDate;
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTyphoonName() {
		return typhoonName;
	}
	public void setTyphoonName(String typhoonName) {
		this.typhoonName = typhoonName;
	}
	public double getPrivateDamage() {
		return privateDamage;
	}
	public void setPrivateDamage(double privateDamage) {
		this.privateDamage = privateDamage;
	}
	public double getPublicDamage() {
		return publicDamage;
	}
	public void setPublicDamage(double publicDamage) {
		this.publicDamage = publicDamage;
	}
	public double getTotalDamage() {
		return totalDamage;
	}
	public void setTotalDamage(double totalDamage) {
		this.totalDamage = totalDamage;
	}
	public double getTotalPersonDamage() {
		return totalPersonDamage;
	}
	public void setTotalPersonDamage(double totalPersonDamage) {
		this.totalPersonDamage = totalPersonDamage;
	}
	public List<YearDmeTyphoonDto> getListYearDmeTyphoon() {
		return listYearDmeTyphoon;
	}
	public void setListYearDmeTyphoon(List<YearDmeTyphoonDto> listYearDmeTyphoon) {
		this.listYearDmeTyphoon = listYearDmeTyphoon;
	}
	public List<TyphoonCastInfoDto> getListTyphoonPosition() {
		return listTyphoonPosition;
	}
	public void setListTyphoonPosition(List<TyphoonCastInfoDto> listTyphoonPosition) {
		this.listTyphoonPosition = listTyphoonPosition;
	}
	public TyphoonCastInfoDto getDetailTyphoonInfo() {
		return detailTyphoonInfo;
	}
	public void setDetailTyphoonInfo(TyphoonCastInfoDto detailTyphoonInfo) {
		this.detailTyphoonInfo = detailTyphoonInfo;
	}
	public List<TyphoonCastInfoDto> getListWorldTyphoonsPosition() {
		return listWorldTyphoonsPosition;
	}
	public void setListWorldTyphoonsPosition(List<TyphoonCastInfoDto> listWorldTyphoonsPosition) {
		this.listWorldTyphoonsPosition = listWorldTyphoonsPosition;
	}
	public List<TyphoonCastInfoDto> getListSimilarTyphoons() {
		return listSimilarTyphoons;
	}
	public void setListSimilarTyphoons(List<TyphoonCastInfoDto> listSimilarTyphoons) {
		this.listSimilarTyphoons = listSimilarTyphoons;
	}
	public List<ParamViewModel> getListSimilarTypParams() {
		return listSimilarTypParams;
	}
	public void setListSimilarTypParams(List<ParamViewModel> listSimilarTypParams) {
		this.listSimilarTypParams = listSimilarTypParams;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getParamYear() {
		return paramYear;
	}
	public void setParamYear(String paramYear) {
		this.paramYear = paramYear;
	}
	public String getParamMonth() {
		return paramMonth;
	}
	public void setParamMonth(String paramMonth) {
		this.paramMonth = paramMonth;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
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
	
	
}
