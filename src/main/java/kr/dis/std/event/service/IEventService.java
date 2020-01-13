package kr.dis.std.event.service;

import java.util.List;
import com.noaa.base.CommonParams;
import com.noaa.nema.viewer.typhoon.dao.TyphoonCastInfoDto;

import kr.dis.std.event.dao.EventDto;
import kr.dis.std.event.dao.EventImgDto;
import kr.dis.std.event.dao.EventItemDto;
import kr.dis.std.event.dao.EventLocationDto;
import kr.dis.std.event.dao.EventsDto;

public interface IEventService {
	
	public int createEvent(EventDto paramBean,CommonParams common);
	public int createEventItem(List<EventItemDto> paramBean,EventDto paramEvent, CommonParams common);
	public int createEventFile(EventDto paramEvent, CommonParams common);
	public int deleteAllEventAction(EventDto paramBean);
    public int deleteEventGroup(String paramEventGroupId);
	public List<EventDto> searchItemList(EventDto paramBean);
	public List<EventItemDto> selectEventItemList(EventItemDto paramBean);
	public List<EventImgDto> selectEventImgList(EventImgDto paramBean);
	public List<EventImgDto> selectEventImgList(List<TyphoonCastInfoDto> paramList);
	public List<EventDto> selectDisEventItemList(EventDto paramBean);
	public List<EventDto> selectDisEventByPeriodList(EventDto paramBean);
	public List<EventDto> selectCtgIdDisEventByPeriodList(EventDto paramBean);
	public List<EventDto> listEventFileListAction(EventDto paramBean);
	public EventDto getListEventItemByLastEvent(List<EventDto> paramList);
	public List<EventDto> getEventListByDate(EventDto paramBean);
	
	public List<EventDto> selectListDisEventActionByDate(String paramCtgId, String paramYear, String paramMonth, String paramDay, String paramHour, String paramEvtGrpId);
	public List<EventDto> selectListDisEventByEvtGroup(String paramCtgId, String paramEvtGrp);
	public List<EventDto> selectListDisEventActionByGroup(String paramCtgId, String paramEvtGrp);
	public List<EventDto> listEventGroupInfos(String paramCtgId, String paramIsRoot, String paramIsCs);
	public EventDto selectDisEventGrpId(String paramCtgId, String paramStDate, String paramEndDate, String paramTitle);
	
	public List<EventLocationDto> listEventLocation(String paramDmeType,String paramStDate,String paramEndDate);
	public EventDto detailEventByEvtId(String paramEvtId);
}
