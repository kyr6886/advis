package kr.dis.std.web.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;

import kr.dis.std.advis.resultmodel.StatisticsModel;

public class MainViewModel extends BaseViewModel {
	private List<StatisticsModel> listStatDisasterMonthly;

	public List<StatisticsModel> getListStatDisasterMonthly() {
		return listStatDisasterMonthly;
	}

	public void setListStatDisasterMonthly(List<StatisticsModel> listStatDisasterMonthly) {
		this.listStatDisasterMonthly = listStatDisasterMonthly;
	}
	
}
