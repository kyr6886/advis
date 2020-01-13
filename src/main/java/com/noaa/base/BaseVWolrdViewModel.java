package com.noaa.base;

public class BaseVWolrdViewModel extends BaseViewModel{
	private String paramArea;
	private String paramKey;
	private int pageUnit;
	private int pageIndex;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public String getParamArea() {
		return paramArea;
	}
	public void setParamArea(String paramArea) {
		this.paramArea = paramArea;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	
}
