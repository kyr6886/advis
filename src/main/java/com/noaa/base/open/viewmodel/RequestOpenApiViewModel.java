package com.noaa.base.open.viewmodel;

public class RequestOpenApiViewModel {
	private String paramUrl;
	private String paramQuery;
	private String paramKey;
	private int paramSize;
	private int paramPage;
	private String paramSort;
	
	
	public String getParamSort() {
		return paramSort;
	}
	public void setParamSort(String paramSort) {
		this.paramSort = paramSort;
	}
	public int getParamSize() {
		return paramSize;
	}
	public void setParamSize(int paramSize) {
		this.paramSize = paramSize;
	}
	public int getParamPage() {
		return paramPage;
	}
	public void setParamPage(int paramPage) {
		this.paramPage = paramPage;
	}
	public String getParamUrl() {
		return paramUrl;
	}
	public void setParamUrl(String paramUrl) {
		this.paramUrl = paramUrl;
	}
	public String getParamQuery() {
		return paramQuery;
	}
	public void setParamQuery(String paramQuery) {
		this.paramQuery = paramQuery;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	
	
}
