package com.noaa.nema.viewer.year.dme.dao;

import java.util.HashMap;
import java.util.List;

public interface IYearDmeImagesDao {
	public List<YearDmeImagesDto> list(HashMap<String,Object> paramMap);
	public List<YearDmeImagesDto> zipList(HashMap<String,Object> paramMap);
	public int registImages(YearDmeImagesDto paramDto);
	public int registReportImages(YearDmeImagesDto paramDto);
	public String selectCountReportImages();
	public int deleteImages(YearDmeImagesDto paramDto);
}
