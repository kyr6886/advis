package kr.dis.std.web.viewmodel;

import java.util.List;

import com.noaa.base.BaseViewModel;

import kr.dis.std.advis.resultmodel.StatisticsModel;
import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.dao.EventImgDto;
import kr.dis.std.event.dao.EventItemDto;
import kr.dis.std.event.dao.EventLocationDto;

public class EventViewModel extends BaseViewModel {
	
	private String paramTitle;
	private String paramDisType;
	private String paramDisDateStr;
	private String paramFileId;
	private String paramYear;
	private String paramMonth;
	private String paramDay;
	private String paramHour;
	private String paramEvtId;
	private String paramCtgId;
	private String paramEvtGrp;
	private int paramDepth;
	private String paramStDate;
	private String paramEndDate;
	private String paramCom_1;
	private String paramCom_2;
	private String paramCom_3;
	private String paramLat;
	private String paramLon;
	private String paramAreaCodes;
	private String paramStatus;
 
	private List<String> paramEvents;
	private List<EventDto> ResultList;
	private List<EventDto> eventList;
	private List<EventItemDto> eventItemList;
	private List<EventImgDto> eventImgList;
	private List<StatisticsModel> listStatistics;
	private EventDto resultEventInfo;
	private List<EventLocationDto> listEventLocation;
	private List<String> paramActionList;
	
	private String paramIsRoot;
	private String paramIsCs;
	
	
	
	
	public String getParamIsRoot() {
		return paramIsRoot;
	}
	public void setParamIsRoot(String paramIsRoot) {
		this.paramIsRoot = paramIsRoot;
	}
	public String getParamIsCs() {
		return paramIsCs;
	}
	public void setParamIsCs(String paramIsCs) {
		this.paramIsCs = paramIsCs;
	}
	public String getParamStatus() {
		return paramStatus;
	}
	public void setParamStatus(String paramStatus) {
		this.paramStatus = paramStatus;
	}
	public List<String> getParamActionList() {
		return paramActionList;
	}
	public void setParamActionList(List<String> paramActionList) {
		this.paramActionList = paramActionList;
	}
	public String getParamCom_1() {
		return paramCom_1;
	}
	public void setParamCom_1(String paramCom_1) {
		this.paramCom_1 = paramCom_1;
	}
	public String getParamCom_2() {
		return paramCom_2;
	}
	public void setParamCom_2(String paramCom_2) {
		this.paramCom_2 = paramCom_2;
	}
	public String getParamCom_3() {
		return paramCom_3;
	}
	public void setParamCom_3(String paramCom_3) {
		this.paramCom_3 = paramCom_3;
	}
	public String getParamLat() {
		return paramLat;
	}
	public void setParamLat(String paramLat) {
		this.paramLat = paramLat;
	}
	public String getParamLon() {
		return paramLon;
	}
	public void setParamLon(String paramLon) {
		this.paramLon = paramLon;
	}
	public String getParamAreaCodes() {
		return paramAreaCodes;
	}
	public void setParamAreaCodes(String paramAreaCodes) {
		this.paramAreaCodes = paramAreaCodes;
	}
	public List<EventLocationDto> getListEventLocation() {
		return listEventLocation;
	}
	public void setListEventLocation(List<EventLocationDto> listEventLocation) {
		this.listEventLocation = listEventLocation;
	}
	public List<EventDto> getEventList() {
		return eventList;
	}
	public void setEventList(List<EventDto> eventList) {
		this.eventList = eventList;
	}
	public String getParamDay() {
		return paramDay;
	}
	public void setParamDay(String paramDay) {
		this.paramDay = paramDay;
	}
	public String getParamHour() {
		return paramHour;
	}
	public void setParamHour(String paramHour) {
		this.paramHour = paramHour;
	}
	public String getParamEvtGrp() {
		return paramEvtGrp;
	}
	public void setParamEvtGrp(String paramEvtGrp) {
		this.paramEvtGrp = paramEvtGrp;
	}
	public EventDto getResultEventInfo() {
		return resultEventInfo;
	}
	public void setResultEventInfo(EventDto resultEventInfo) {
		this.resultEventInfo = resultEventInfo;
	}
	public List<StatisticsModel> getListStatistics() {
		return listStatistics;
	}
	public void setListStatistics(List<StatisticsModel> listStatistics) {
		this.listStatistics = listStatistics;
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
	public String getParamFileId() {
		return paramFileId;
	}
	public void setParamFileId(String paramFileId) {
		this.paramFileId = paramFileId;
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
	public String getParamEvtId() {
		return paramEvtId;
	}
	public void setParamEvtId(String paramEvtId) {
		this.paramEvtId = paramEvtId;
	}
	public String getParamCtgId() {
		return paramCtgId;
	}
	public void setParamCtgId(String paramCtgId) {
		this.paramCtgId = paramCtgId;
	}
	public List<EventDto> getResultList() {
		return ResultList;
	}
	public void setResultList(List<EventDto> resultList) {
		ResultList = resultList;
	}
	public List<EventItemDto> getEventItemList() {
		return eventItemList;
	}
	public void setEventItemList(List<EventItemDto> eventItemList) {
		this.eventItemList = eventItemList;
	}
	public int getParamDepth() {
		return paramDepth;
	}
	public void setParamDepth(int paramDepth) {
		this.paramDepth = paramDepth;
	}
	public List<EventImgDto> getEventImgList() {
		return eventImgList;
	}
	public void setEventImgList(List<EventImgDto> eventImgList) {
		this.eventImgList = eventImgList;
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
	
	
	
	
}
