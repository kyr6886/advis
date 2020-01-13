package com.noaa.nema.viewer.year.dme.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.noaa.base.BaseDao;
import com.noaa.nema.viewer.year.dme.dao.IYearDmeImagesDao;
import com.noaa.nema.viewer.year.dme.dao.YearDmeDto;
import com.noaa.nema.viewer.year.dme.dao.YearDmeImagesDto;
@Repository("yearDmeImagesDao")
public class YearDmeImagesDaoImpl extends BaseDao implements IYearDmeImagesDao {

	@Override
	public List<YearDmeImagesDto> list(HashMap<String, Object> paramMap) {
		
		return selectList("yearDmeImages.list",paramMap);
	}

	@Override
	public int registImages(YearDmeImagesDto paramDto) {
		return (int) insert("yearDmeImages.registImages",paramDto);
	}

	@Override
	public int deleteImages(YearDmeImagesDto paramDto) {
		return delete("yearDmeImages.deleteImages",paramDto);
	}

	@Override
	public int registReportImages(YearDmeImagesDto paramDto) {
		return (int) insert("yearDmeImages.registReportImages",paramDto);
	}

	@Override
	public String selectCountReportImages() {
		return (String) selectOne("yearDmeImages.selectCountReportImages");
	}

	@Override
	public List<YearDmeImagesDto> zipList(HashMap<String, Object> paramMap) {
		return selectList("yearDmeImages.selectReportImagesList",paramMap);
	}

}
