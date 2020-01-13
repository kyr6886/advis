package com.noaa.base.reporting.service;

public interface IReportingPdfService {
	public String createPdf(String cssPath,String fontPath,String htmlStr);
	public String readPdf(String paramFileName);
}
