package kr.dis.std.web.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;

import kr.dis.std.advis.dao.AdvisSearchDto;
import kr.dis.std.event.dao.EventDto;

public class AdvisSearchViewModel extends BaseViewModel {
	private List<String> paramEvents;
	private List<AdvisSearchDto> setResultSearchItem;
	private String paramTitle;
	private String paramDisType;
	private String paramDisDateStr;
	private String paramFileId;
	private String paramYear;
	private String paramMonth;
	private String paramEvtId;
	
	public String getParamEvtId() {
		return paramEvtId;
	}
	public void setParamEvtId(String paramEvtId) {
		this.paramEvtId = paramEvtId;
	}
	public List<AdvisSearchDto> getSetResultSearchItem() {
		return setResultSearchItem;
	}
	public void setSetResultSearchItem(List<AdvisSearchDto> setResultSearchItem) {
		this.setResultSearchItem = setResultSearchItem;
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
	public String getParamFileId() {
		return paramFileId;
	}
	public void setParamFileId(String paramFileId) {
		this.paramFileId = paramFileId;
	}
	public String getParamDisType() {
		return paramDisType;
	}
	public void setParamDisType(String paramDisType) {
		this.paramDisType = paramDisType;
	}
	public String getParamDisDateStr() {
		return paramDisDateStr;
	}
	public void setParamDisDateStr(String paramDisDateStr) {
		this.paramDisDateStr = paramDisDateStr;
	}
	public List<String> getParamEvents() {
		return paramEvents;
	}
	public void setParamEvents(List<String> paramEvents) {
		this.paramEvents = paramEvents;
	}
	public String getParamTitle() {
		return paramTitle;
	}
	public void setParamTitle(String paramTitle) {
		this.paramTitle = paramTitle;
	}
	
}
