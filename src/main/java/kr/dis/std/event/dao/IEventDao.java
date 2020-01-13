package kr.dis.std.event.dao;

import java.util.HashMap;
import java.util.List;

public interface IEventDao {
	public int Create(EventDto paramBean);
	public List<EventDto> searchItemList(EventDto paramBean);
	public List<EventDto> searchCtgList(EventDto paramBean);
	public List<EventImgDto> selectEventImgList(EventImgDto paramBean);
	public List<EventDto> selectDisEventItemList(EventDto paramBean);
	public List<EventDto> selectDisEventByPeriodList(EventDto paramBean);
	public List<EventDto> selectCtgIdDisEventByPeriodList(EventDto paramBean);
	public List<EventDto> listEventFileListAction(EventDto paramBean);
	public int deleteDisEvent(EventDto paramBean);
	public int deleteDisEventGroup(HashMap<String,Object> paramMap);
	public int deleteDisEventItem(EventDto paramBean);
	public int deleteDisEventFile(EventDto paramBean);
	public List<EventDto> selectListDisEventListByEvtYear(EventDto paramBean);
	public List<EventDto> selectListDisEventActionByDate(EventDto paramBean);
	public List<EventDto> selectListDisEventActionByGroup(EventDto paramBean);
	public List<EventDto> selectListDisEventByEvtGroup(EventDto paramBean);
	public EventDto selectDisEventGrpId(EventDto paramBean);
	public List<EventDto> listEventGropInfos(HashMap<String,Object> paramBean);
	public EventDto detailEventByEvtId(HashMap<String,Object> paramBean);
	
}
