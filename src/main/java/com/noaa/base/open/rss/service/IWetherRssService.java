package com.noaa.base.open.rss.service;

import java.util.List;

import com.noaa.base.open.dto.WetherRssDto;

public interface IWetherRssService {
	public List<WetherRssDto> list(String paramAdmCode);
}
