package com.noaa.base.open.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;
import com.noaa.base.open.dto.WetherRssDto;

public class WetherViewModel extends BaseViewModel {
	public List<WetherRssDto> listWetherRss;
	public WetherRssDto detailWetherRss;
	
	public List<WetherRssDto> getListWetherRss() {
		return listWetherRss;
	}
	public void setListWetherRss(List<WetherRssDto> listWetherRss) {
		this.listWetherRss = listWetherRss;
	}
	public WetherRssDto getDetailWetherRss() {
		return detailWetherRss;
	}
	public void setDetailWetherRss(WetherRssDto detailWetherRss) {
		this.detailWetherRss = detailWetherRss;
	}
	
	
}
