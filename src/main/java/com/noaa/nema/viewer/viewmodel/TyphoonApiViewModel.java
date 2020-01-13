package com.noaa.nema.viewer.viewmodel;

import java.util.List;

import com.noaa.nema.viewer.typhoon.dao.TyphoonCastDateDto;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;
import com.noaa.nema.viewer.typhoon.dao.YearDmeTyphoonDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;

public class TyphoonApiViewModel extends BaseViewModel {

	
	private String paramYear;
	private String paramMonth;
	private String paramSeq;
	private String paramStDate;
	private String paramEndDate;
	private String paramTyphoonName;
	private YearDmeTyphoonDto typhoonInfo;
	
	private List<TyphoonCastDateDto> typYearList;
	private List<TyphoonCastDateDto> typMonthList;
	private List<TyphoonCastDateDto> typNameList;
	
	
	private List<TyphoonCastInfoDto> typhoonList;
	private List<TyphoonCastInfoDto> similarTyphoonList;
	
	
	private List<YearDmeDto> listDamage;
	private List<TyphoonCastInfoDto> listCurrentTyphoons;
	private String paramTimeSeq;
	
	
	
	public List<TyphoonCastInfoDto> getListCurrentTyphoons() {
		return listCurrentTyphoons;
	}
	public void setListCurrentTyphoons(List<TyphoonCastInfoDto> listCurrentTyphoons) {
		this.listCurrentTyphoons = listCurrentTyphoons;
	}
	public YearDmeTyphoonDto getTyphoonInfo() {
		return typhoonInfo;
	}
	public void setTyphoonInfo(YearDmeTyphoonDto typhoonInfo) {
		this.typhoonInfo = typhoonInfo;
	}
	public String getParamTyphoonName() {
		return paramTyphoonName;
	}
	public void setParamTyphoonName(String paramTyphoonName) {
		this.paramTyphoonName = paramTyphoonName;
	}
	public String getParamTimeSeq() {
		return paramTimeSeq;
	}
	public void setParamTimeSeq(String paramTimeSeq) {
		this.paramTimeSeq = paramTimeSeq;
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
	public String getParamSeq() {
		return paramSeq;
	}
	public void setParamSeq(String paramSeq) {
		this.paramSeq = paramSeq;
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
	public List<TyphoonCastDateDto> getTypYearList() {
		return typYearList;
	}
	public void setTypYearList(List<TyphoonCastDateDto> typYearList) {
		this.typYearList = typYearList;
	}
	public List<TyphoonCastDateDto> getTypMonthList() {
		return typMonthList;
	}
	public void setTypMonthList(List<TyphoonCastDateDto> typMonthList) {
		this.typMonthList = typMonthList;
	}
	public List<TyphoonCastDateDto> getTypNameList() {
		return typNameList;
	}
	public void setTypNameList(List<TyphoonCastDateDto> typNameList) {
		this.typNameList = typNameList;
	}
	public List<TyphoonCastInfoDto> getTyphoonList() {
		return typhoonList;
	}
	public void setTyphoonList(List<TyphoonCastInfoDto> typhoonList) {
		this.typhoonList = typhoonList;
	}
	public List<TyphoonCastInfoDto> getSimilarTyphoonList() {
		return similarTyphoonList;
	}
	public void setSimilarTyphoonList(List<TyphoonCastInfoDto> similarTyphoonList) {
		this.similarTyphoonList = similarTyphoonList;
	}
	public List<YearDmeDto> getListDamage() {
		return listDamage;
	}
	public void setListDamage(List<YearDmeDto> listDamage) {
		this.listDamage = listDamage;
	}
	
}
